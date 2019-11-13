package gov.utcourts.corisweb.util;

import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.coriscommon.util.TextUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {
	
	public static final String USER_FULL_NAME = "userFullName";
	public static final String CRITICAL_MESSAGE = "criticalMessage";
	
	/**
	 * Retrieve user's name
	 * 
	 * @param request HttpServletRequest
	 * @return String
	 * @throws Exception
	 */
	public static String getCurrentUserFullName(HttpServletRequest request) throws Exception {
		String firstName = "";
		String lastName = "";
		try {
			String courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
			String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			
			PersonnelBO personnelBO = new PersonnelBO(courtType)
			.where(PersonnelBO.LOGNAME, logName)
			.where(PersonnelBO.COURTTYPE, courtType)
			.find();
			
			firstName = personnelBO.getFirstName() + " ";
			lastName = personnelBO.getLastName();
		} catch (Exception e) {
			throw new Exception("Exception retrieving current user id", e);
		}
		return TextUtil.isEmpty(firstName.trim()) ? lastName : firstName + lastName;
	}
	
	/**
	 * 
	 * @param request HttpServletRequest
	 * @return String
	 * @throws Exception
	 */
	public static String getCriticalMessage(HttpServletRequest request) throws Exception {
		String criticalMessage = "";
		try {
			String courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
			String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			
			// check password expiration
			Date passwodExpirationDate = new PersonnelBO(courtType)
			.where(PersonnelBO.LOGNAME, logName)
			.where(PersonnelBO.COURTTYPE, courtType)
			.find()
			.getPasswordExpDate();
			
			if (passwodExpirationDate.before(new Date()))
				criticalMessage = "PASSWORD EXPIRED";
			
		} catch (Exception e) {
			throw new Exception("Exception retrieving critical messages", e);
		}
		return TextUtil.isEmpty(criticalMessage) ? "" : criticalMessage + "&nbsp;&nbsp;";
	}

}
