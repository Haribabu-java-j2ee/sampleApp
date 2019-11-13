package gov.utcourts.corisweb.util;

import gov.utcourts.coriscommon.dataaccess.persapplic.PersApplicBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.roleapplicxref.RoleapplicXrefBO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.IntegerArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.notifications.util.SessionVariables;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RoleUtil {
	
	public enum Role { 
		IT_ADMIN 			 (4),  		// Developers application in CORIS
		COURT_SERVICES		 (5),
		LOCAL_MAINTENANCE	 (6), 
		CLERK				 (996),		// Determined in SessionValues.java
		JUDGE				 (997),
		COMMISSIONER		 (998),
		UNASSIGNED	 		 (999); 
		
		Role (int roleId) {
			this.roleId = roleId;
		}
		
		public int roleId; 
	} 

	public enum Application { 
		INQUIRY_LOGON		 (45),
		MANAGER_REPORTS		 (88);
				
		Application (int applicId) {
			this.applicId = applicId;
		}
		
		public int applicId; 
	} 
	
	/**
	 * check to see if the logged in user has any of the specified roles
	 */
	public static boolean hasRole(HttpServletRequest request, Role... roles) throws Exception {
		List<Integer> rolesFromSession = (List<Integer>) request.getSession().getAttribute(SessionVariables.USER_ROLE);
		if (rolesFromSession != null && rolesFromSession.size() > 0) {
			for (Role role : roles) {
	            if (rolesFromSession.contains(role.roleId)) 
	            	return true;
	        }
		}
		return false;
	}
		
	/**
	 * get roles by userid_srl
	 */
	public static List<Integer> getRoles(int useridSrl, String courtType) throws Exception {
		List<Integer> roles = new ArrayList<Integer>();
		
		List<PersApplicBO> persApplicVOs = new PersApplicBO(courtType).where(PersApplicBO.USERIDSRL, useridSrl).search(PersApplicBO.APPLICID);
		IntegerArrayDescriptor applicIds = new IntegerArrayDescriptor(PersApplicBO.APPLICID, persApplicVOs);
		
		if (applicIds != null && applicIds.size() > 0) {
			List<RoleapplicXrefBO> RoleapplicXrefVOs = new RoleapplicXrefBO(courtType).where(new Expression(RoleapplicXrefBO.APPLICID, Exp.IN, applicIds)).toString(BaseConstants.PRINT + BaseConstants.RUN).search(RoleapplicXrefBO.ROLEID);
			if (RoleapplicXrefVOs != null && RoleapplicXrefVOs.size() > 0) {
				for (RoleapplicXrefBO roleapplicXrefBO : RoleapplicXrefVOs) {
					if (!roles.contains(roleapplicXrefBO.getRoleId()))
						roles.add(roleapplicXrefBO.getRoleId());
				}
			}
		}
			
		return roles;
	}

	/**
	 * get roles by logName
	 */
	public static List<Integer> getRoles(String logname, String courtType) throws Exception {
		List<Integer> roles = new ArrayList<Integer>();
		
		List<PersonnelBO> personnelBOs = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logname).search(PersonnelBO.USERIDSRL);
		IntegerArrayDescriptor useridSrls = new IntegerArrayDescriptor(PersonnelBO.USERIDSRL, personnelBOs);
		
		List<PersApplicBO> persApplicVOs = new PersApplicBO(courtType).where(new Expression(PersApplicBO.USERIDSRL, Exp.IN, useridSrls)).search(PersApplicBO.APPLICID);
		IntegerArrayDescriptor applicIds = new IntegerArrayDescriptor(PersApplicBO.APPLICID, persApplicVOs);
		
		if (applicIds != null && applicIds.size() > 0) {
			List<RoleapplicXrefBO> RoleapplicXrefVOs = new RoleapplicXrefBO(courtType).where(new Expression(RoleapplicXrefBO.APPLICID, Exp.IN, applicIds)).search(RoleapplicXrefBO.ROLEID);
			if (RoleapplicXrefVOs != null && RoleapplicXrefVOs.size() > 0) {
				for (RoleapplicXrefBO roleapplicXrefBO : RoleapplicXrefVOs) {
					if (!roles.contains(roleapplicXrefBO.getRoleId()))
						roles.add(roleapplicXrefBO.getRoleId());
				}
			}
		}
			
		return roles;
	}
	
	public static boolean containsApplication(List<RoleapplicXrefBO> roleApplicXrefs, Application... applications) {
		if (roleApplicXrefs != null && roleApplicXrefs.size() > 0 && applications.length > 0) {
			for (Application application : applications) {
				for (RoleapplicXrefBO roleApplicXref : roleApplicXrefs) {
		            if (roleApplicXref.getApplicId() == application.applicId) 
		            	return true;
		        }
			}
		}
		return false;
	}
			
	public static void main(String[] args) {
		try {
			DatabaseConnection.setUseJdbc();
			
			System.out.println(getRoles(70127, "D"));
			System.out.println(getRoles("angien", "D"));
			
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
	}
}
