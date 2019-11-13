package gov.utcourts.corisweb.servlet;

import gov.utcourts.commonauth.CommonAuthBO;
import gov.utcourts.commonauth.common.CommonAuthConstants;
import gov.utcourts.commonauth.util.CorisEncryption;
import gov.utcourts.commonauth.util.RandomPassword;
import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.securityfeature.SecurityFeatureBO;
import gov.utcourts.coriscommon.util.DateUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.util.TextUtil;
import gov.utcourts.notifications.util.CorisNotificationEmailCommon;
import gov.utcourts.personnel.AllUserVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

/**
 * Servlet implementation class PasswordServlet
 */
@WebServlet("/PasswordServlet")
public class PasswordServlet extends BaseServlet {
	private static final long serialVersionUID = 5611241121L;
	private static Logger logger = Logger.getLogger(PasswordServlet.class);
       
	public static final String MSG_Username_Not_Found = "Username not found or invalid.";
	public static final String MSG_Fields_Required = "All Fields are required.";
	public static final String MSG_Old_Password = "Current password is invalid. Must match your current password.";
	public static final String MSG_Dont_Match = "New Password and Retype Password do not match.";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordServlet() {
        super();
    }

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = URLEncryption.getParamAsString(request, "action");
		if ("change".equalsIgnoreCase(action)) {
			changePassword(request, response);
		} else if ("forgot".equalsIgnoreCase(action)) {
			forgotPassword(request, response);
		} else if ("checkExpiration".equalsIgnoreCase(action)) {
			checkExpiration(request, response);
		} else if ("graceLoginsLeft".equalsIgnoreCase(action)) {
			graceLoginsLeft(request, response);
		}
	}
	
	private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject results = new JSONObject();
		try {
			String errorMsg = null;
			
			String logname = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			String oldPassword = URLEncryption.getParamAsString(request, "currentPassword");
			String password1 = URLEncryption.getParamAsString(request, "newPassword");
			String password2 = URLEncryption.getParamAsString(request, "confirmPassword");

			boolean isValidated = false;
			CommonAuthBO commonAuthBO = CommonAuthBO.verifyUser(logname, oldPassword);
			if (commonAuthBO.isVerified()) {
				isValidated = true;
			}
			
			int graceLogins = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "grace_logins").find().getSecurityValue();
			
			List<String> errors = getAndValidatePassword(isValidated, oldPassword, password1, password2);
			if (errors != null && (errors.size()>0)) {
				errorMsg = convertListToString(errors);
			} else {
				try {
					int password_expire_days = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_expire_days").find().getSecurityValue();
					Date expireDate = DateUtil.addDays(new Date(), password_expire_days);
					String p = CorisEncryption.encrypt(password1);
					
					// update both district and justice passwords
					new PersonnelBO("D").where(PersonnelBO.LOGNAME, logname).setUserPasswd(p).setPasswordAttempts(0).setPasswordExpDate(expireDate).setGraceLoginsLeft(graceLogins).update();
					new PersonnelBO("J").where(PersonnelBO.LOGNAME, logname).setUserPasswd(p).setPasswordAttempts(0).setPasswordExpDate(expireDate).setGraceLoginsLeft(graceLogins).update();
					
				} catch (Exception e) {
					errorMsg = e.getMessage();
					logger.error("Exception changing password.  Username: ".concat(logname), e);
				}
			}
	
			if (!TextUtil.isEmpty(errorMsg)){
				results.put("error", errorMsg);
			} else {
				results.put("success", "Password changed.");
			}

			errors = null;
		} catch(Exception e) {
			logger.error("Unable to change password: " + e.getMessage(), e);
			results.put("failure", true);
			results.put("message", e.getMessage());			
		}

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(results.toString());
	}
	
	private List<String> getAndValidatePassword(boolean isValidated, String oldPassword, String password1, String password2) {
		List<String> errors = new ArrayList<String>();
		if (TextUtil.isEmpty(oldPassword)){
			errors.add(MSG_Fields_Required);
		}
		if (TextUtil.isEmpty(password1)){
			errors.add(MSG_Fields_Required);
		}
		if (TextUtil.isEmpty(password2)){
			errors.add(MSG_Fields_Required);
		}
		if (!password1.equals(password2)){
			errors.add(MSG_Dont_Match);
		}

		// password security
		StringBuilder MSG_Pwd_Not_Secure = new StringBuilder(); 
		try {
			int password_min_length = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_min_length").find().getSecurityValue();
			int password_max_length = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_max_length").find().getSecurityValue();
			int password_alpha = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_alpha").find().getSecurityValue();
			int password_numbers = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_numbers").find().getSecurityValue();
			int password_special = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_special").find().getSecurityValue();
			
			boolean secure = true;
				
			MSG_Pwd_Not_Secure.append("Passwords must be " + password_min_length + "-" + password_max_length + " characters in length");
			if (password1.length() < password_min_length || password1.length() > password_max_length) {
				secure = false;
			}
			if (password_numbers > 0) {
				MSG_Pwd_Not_Secure.append(", contain a number");
				
				if (!validatePasswordStrength(password1, ".*\\d+.*"))
					secure = false;
			}
			if (password_alpha > 0) {
				MSG_Pwd_Not_Secure.append(", and contain a letter");
				
				if (!validatePasswordStrength(password1, "^.*[a-zA-Z].*$"))
					secure = false;
			}
			if (password_special > 0) {
				MSG_Pwd_Not_Secure.append(" and contain a special character (e.g. !@#$%^&*)");
				
				if (!validatePasswordStrength(password1, "^.*[!@#$%^&*].*$"))
					secure = false;
			}
			
			if (!secure)
				errors.add(MSG_Pwd_Not_Secure.toString());
			
			MSG_Pwd_Not_Secure = null;
				
		} catch(Exception e) {
			errors.add("Unexepected Error: " + e.getMessage());
		}
		
		if (!isValidated){
			errors.add(MSG_Old_Password);
		}
		return errors;
	}	
	
	public static final boolean validatePasswordStrength(String password){
		/**
		 * Password Regular Expression
		 * (?=.*\d)			#   must contains one digit from 0-9
		 * (?=.*[a-z])		#   must contains one lowercase character
		 * (?=.*[A-Z])		#   must contains one uppercase character -- REMOVED 
		 * (?=.*[!@#$%^&*])	#   must contains one special character
		 * .				#   match anything with previous condition checking
		 * {8,20}			#   length at least 8 characters and maximum of 20	
		 */
		
		// final String PasswordRegEx = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,20}";
		final String PasswordRegEx = "(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&*]).{8,20}";
		return password.matches(PasswordRegEx);
	}
	
	public static final boolean validatePasswordStrength(String password, String regEx){
		/**
		 * Password Regular Expression
		 * (?=.*\d)			#   must contains one digit from 0-9
		 * (?=.*[a-z])		#   must contains one lowercase character
		 * (?=.*[A-Z])		#   must contains one uppercase character -- REMOVED 
		 * (?=.*[!@#$%^&*])	#   must contains one special character
		 * .				#   match anything with previous condition checking
		 * {8,20}			#   length at least 8 characters and maximum of 20	
		 */
		return password.matches(regEx);
	}
	
	/**
	 * @param List<String> list
	 * @return String
	 */
	public static String convertListToString(List<String> list) {
		//Convert a message list to a html bullet list to display in a popup alert window
		StringBuilder message = new StringBuilder();
		if (list != null && list.size() > 0){
			if (list.size() == 1) {
				//Don't do a bullet list, just return the string
				message.append(list.get(0));
			} else {
				message.append("<ul>");
				for (String item: list) {
					message.append("<li>" + item + "</li>"); 
				}
				message.append("</ul>");
			}			
		}
		return message.toString();
	}
	
	private void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		JSONObject results = new JSONObject();
		try {
			String logname = URLEncryption.getParamAsString(request, "logname");
			String emailAddress = URLEncryption.getParamAsString(request, "email");
			
			boolean successful = false;

			String courtType = "D";
			
			PersonnelBO personnelBO = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logname).setReturnNull(true).find(PersonnelBO.LOGNAME, PersonnelBO.EMAILADDRESS);
			if (personnelBO == null) {
				courtType = "J";
				personnelBO = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logname).setReturnNull(true).find(PersonnelBO.LOGNAME, PersonnelBO.EMAILADDRESS);
			}
			
			if (personnelBO == null) {
				results.put("error", "Password not sent. Email address does not match.");
			} else {
				List<AllUserVO> allUsers = CommonAuthBO.getUserByLogname(logname);
				for (AllUserVO allUserVO : allUsers) {
					if (allUserVO.getSystemId() == CommonAuthConstants.CORIS_DISTRICT || allUserVO.getSystemId() == CommonAuthConstants.CORIS_JUSTICE) {
						if (emailAddress.equalsIgnoreCase(personnelBO.getEmailAddress())) {
							successful = true;
							break;
						}
					}
				}
				
				if (!successful) {
					results.put("error", "Password not sent. Email address does not match.");
				} else {
					String plainTextPassword = RandomPassword.getPassword();
					String newPassword = CorisEncryption.encrypt(plainTextPassword);
					int password_expire_days = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_expire_days").find().getSecurityValue();
					Date expireDate = DateUtil.addDays(new Date(), password_expire_days);
										
					// update both district and justice passwords
					new PersonnelBO("D").where(PersonnelBO.LOGNAME, logname).setUserPasswd(newPassword).setPasswordAttempts(0).setPasswordExpDate(expireDate).update();
					new PersonnelBO("J").where(PersonnelBO.LOGNAME, logname).setUserPasswd(newPassword).setPasswordAttempts(0).setPasswordExpDate(expireDate).update();
					
					// send email notification
					StringBuilder content = new StringBuilder();
					content.append("You have requested a new password for Coris.");
					content.append("<br><br>Your password is " + plainTextPassword);
					CorisNotificationEmailCommon.corisNotificationEmail(
						1, 
						Constants.SYSTEM_ID,
						null, 
						"Coris Password change", 
						content.toString(), 
						emailAddress,
						Constants.RETURN_EMAIL,
				        null,
				        null,
				        courtType,  
						null, 
						false
					);
					
					results.put("success", "Check your email for a message containing your new password.");
				}
			}
		} catch(Exception e) {
			logger.error("Unable to send password: " + e.getMessage(), e);
			results.put("error", e.getMessage());			
		}
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(results.toString());
	}
	
	private void checkExpiration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		JSONObject results = new JSONObject();
		try {
			CommonAuthBO commonAuthBO = CommonAuthBO.verifyUser(URLEncryption.getParamAsString(request, "logname"), "password");
			String logname = commonAuthBO.getSystemUsername(CommonAuthConstants.CORIS_DISTRICT, CommonAuthConstants.CORIS_JUSTICE);
			
			boolean successful = false;

			String courtType = "D";
			
			PersonnelBO personnelBO = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logname).setReturnNull(true).find(PersonnelBO.LOGNAME, PersonnelBO.PASSWORDEXPDATE, PersonnelBO.GRACELOGINSLEFT);
			if (personnelBO == null) {
				courtType = "J";
				personnelBO = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logname).setReturnNull(true).find(PersonnelBO.LOGNAME, PersonnelBO.PASSWORDEXPDATE, PersonnelBO.GRACELOGINSLEFT);
			}
			
			if (personnelBO == null) {
				// do nothing... user could be logging in with credentials from another system
				results.put("success", "Alternate login credentials");
			} else {
				if (personnelBO.getPasswordExpDate().after(new Date()))
					successful = true;
				
				if (!successful) {
					results.put("error", "Your password has expired. You have " + personnelBO.getGraceLoginsLeft() + " grace logins left. Do you want to continue?");
				} else {
					results.put("success", "Password has not expired.");
				}
			}
		} catch(Exception e) {
			logger.error("Unable to send password: " + e.getMessage(), e);
			results.put("error", e.getMessage());			
		}
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(results.toString());
	}
	
	private void graceLoginsLeft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		JSONObject results = new JSONObject();
		try {
			String logname = URLEncryption.getParamAsString(request, "logname");
			
			String courtType = "D";
			
			PersonnelBO personnelBO = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logname).setReturnNull(true).find(PersonnelBO.LOGNAME, PersonnelBO.PASSWORDEXPDATE, PersonnelBO.GRACELOGINSLEFT);
			if (personnelBO == null) {
				courtType = "J";
				personnelBO = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logname).setReturnNull(true).find(PersonnelBO.LOGNAME, PersonnelBO.PASSWORDEXPDATE, PersonnelBO.GRACELOGINSLEFT);
			}
			
			if (personnelBO == null) {
				results.put("error", "Invalid logname - " + logname);
			} else {
				int graceLoginsLeft = personnelBO.getGraceLoginsLeft() - 1;
				new PersonnelBO(courtType).setGraceLoginsLeft(graceLoginsLeft).where(PersonnelBO.LOGNAME, logname).update();
				results.put("success", "Decreased number of grace logins left.");
			}
		} catch(Exception e) {
			logger.error("Unable to send password: " + e.getMessage(), e);
			results.put("error", e.getMessage());			
		}
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(results.toString());
	}
	
	public static void main(String[] args) {
		DatabaseConnection.setUseJdbc();
		
		String password1 = "Coris666"; 
			
		// password security
		StringBuilder MSG_Pwd_Not_Secure = new StringBuilder(); 
		try {
			int password_min_length = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_min_length").find().getSecurityValue();
			int password_max_length = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_max_length").find().getSecurityValue();
			int password_alpha = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_alpha").find().getSecurityValue();
			int password_numbers = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_numbers").find().getSecurityValue();
			int password_special = new SecurityFeatureBO("D").where(SecurityFeatureBO.SECURITYFEATURE, "password_special").find().getSecurityValue();
			
			boolean secure = true;
				
			MSG_Pwd_Not_Secure.append("Passwords must be " + password_min_length + "-" + password_max_length + " characters in length");
			if (password1.length() < password_min_length || password1.length() > password_max_length) {
				secure = false;
			}
			if (password_numbers > 0) {
				MSG_Pwd_Not_Secure.append(", contain a number");
				
				if (!validatePasswordStrength(password1, ".*\\d+.*"))
					secure = false;
			}
			if (password_alpha > 0) {
				MSG_Pwd_Not_Secure.append(", contain a letter");
				
				if (!validatePasswordStrength(password1, "^.*[a-zA-Z].*$"))
					secure = false;
			}
			if (password_special > 0) {
				MSG_Pwd_Not_Secure.append(" and contain a special character (e.g. !@#$%^&*)");
				
				if (!validatePasswordStrength(password1, "^.*[!@#$%^&*].*$"))
					secure = false;
			}
			
			if (!secure)
				//errors.add(MSG_Pwd_Not_Secure);
				System.out.println(MSG_Pwd_Not_Secure.toString());
			
			MSG_Pwd_Not_Secure = null;
				
		} catch(Exception e) {
			
		}
	}
}
