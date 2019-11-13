package gov.utcourts.corisweb.session;

import gov.utcourts.coriscommon.dataaccess.persapplic.PersApplicBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.roleapplicxref.RoleapplicXrefBO;
import gov.utcourts.corisweb.util.RoleUtil;
import gov.utcourts.corisweb.util.RoleUtil.Application;
import gov.utcourts.corisweb.util.RoleUtil.Role;
import gov.utcourts.courtscommon.util.TextUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class SessionValues {

	private static Logger logger = Logger.getLogger(SessionValues.class);
	
	public static void setValue(HttpServletRequest request, String key, Object value) {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute(key, value);
		} catch (Exception e) {
			logger.error("Exception setting session information", e);	
		}		
	}
	
	public static void setSessionValues(HttpServletRequest request, String logname, String courtType, String locnCode) throws Exception {
		setValue(request, SessionVariables.LOG_NAME, logname);
		setValue(request, SessionVariables.COURT_TYPE, courtType);  
		
		// location
		if (!TextUtil.isEmpty(locnCode)) {
			setValue(request, SessionVariables.LOCN_CODE, locnCode);
		} else {
			// check to see if the user has more than one location
			List<PersonnelBO> locations = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logname).search(PersonnelBO.LOCNCODE, PersonnelBO.DEFLTLOGIN);
			if (locations == null) {
				throw new Exception("No locations available for user.");
			} else {
				if (locations.size() == 1) {
					locnCode = locations.get(0).getLocnCode();
				} else {
					// get default location
					for (PersonnelBO location : locations) {
						if ("Y".equalsIgnoreCase(location.getDefltLogin())) {
							locnCode = location.getLocnCode();
							break;
						}
					}
					
					// no default location specified, take the first one
					if (locnCode == null)
						locnCode = locations.get(0).getLocnCode();
				}
			}
			locations = null;
			
			setValue(request, SessionVariables.LOCN_CODE, locnCode);
		}
		
		// applications (roles)
		PersonnelBO personnelBO = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logname).where(PersonnelBO.LOCNCODE, locnCode).find(PersonnelBO.USERIDSRL, PersonnelBO.TYPE);
		if (personnelBO != null && personnelBO.getUseridSrl() > 0) {
			List<RoleapplicXrefBO> roleApplicXrefs = new RoleapplicXrefBO(courtType)
			.includeTables(
				new PersApplicBO(courtType).where(PersApplicBO.USERIDSRL, personnelBO.getUseridSrl())				
			)
			.addForeignKey(RoleapplicXrefBO.APPLICID, PersApplicBO.APPLICID)
			.search(RoleapplicXrefBO.ROLEID, RoleapplicXrefBO.APPLICID); 
			
			List<Integer> roles = new ArrayList<Integer>();
			if (roleApplicXrefs != null && roleApplicXrefs.size() > 0) {
				for (RoleapplicXrefBO xref : roleApplicXrefs) {
					roles.add(xref.getRoleId());
				}
				
				// check for clerk, judge and commissioner "roles"
				if (!RoleUtil.containsApplication(roleApplicXrefs, Application.INQUIRY_LOGON, Application.MANAGER_REPORTS)) {		// exclude users with inquirylogon or manager_reports
					if ("J".equalsIgnoreCase(personnelBO.getType())) {
						roles.add(Role.JUDGE.roleId);
					} else if ("C".equalsIgnoreCase(personnelBO.getType())) {
						roles.add(Role.COMMISSIONER.roleId);
					} else if ("K".equalsIgnoreCase(personnelBO.getType())) {
						roles.add(Role.CLERK.roleId);
					}
				}
				
				setValue(request, SessionVariables.USER_ROLE, roles);
			} else {
				roles.add(Role.UNASSIGNED.roleId);
				setValue(request, SessionVariables.USER_ROLE, roles);
			}
			roles = null;
		}

	}
	
}
