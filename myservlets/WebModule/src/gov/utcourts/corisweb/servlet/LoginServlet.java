package gov.utcourts.corisweb.servlet;

import gov.utcourts.commonauth.CommonAuthBO;
import gov.utcourts.commonauth.common.CommonAuthConstants;
import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.securityfeature.SecurityFeatureBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionValues;
import gov.utcourts.corisweb.util.URLEncryption;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class CorisLoginServlet
 */
@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = -4917252337220027291L;
	
	private static Logger logger = Logger.getLogger(LoginServlet.class);
	
	private static final String LOGIN_PAGE = "/login.jsp";
	private static final String MENU_SERVLET = "/MenuServlet";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}
	
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = URLEncryption.getParamAsString(request, "username");
		String password = URLEncryption.getParamAsString(request, "password");
		
		if (TextUtil.isEmpty(username)) {
			getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);
		} else if (TextUtil.isEmpty(password)) {
			request.setAttribute(Constants.ERROR_MESSAGE, "Password is invalid.");
			getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);
		} else {
			try {
				CommonAuthBO commonAuthBO = CommonAuthBO.verifyUser(username, password);

				// checking if the user "hasSystem" will retrieve their coris username and put it in commonAuthBO.getUsername()
				String courtType = null;
				if (commonAuthBO.hasSystem(CommonAuthConstants.CORIS_DISTRICT)) {
					courtType = "D";
				} else if (commonAuthBO.hasSystem(CommonAuthConstants.CORIS_JUSTICE)) {
					courtType = "J";
				}
				
				int badPasswordLockout = new SecurityFeatureBO(courtType).where(SecurityFeatureBO.SECURITYFEATURE, "bad_password_lockout").find().getSecurityValue();
				int passwordAttempts = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, commonAuthBO.getUsername()).find().getPasswordAttempts();
				int graceLoginsLeft = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, commonAuthBO.getUsername()).find().getGraceLoginsLeft();
				if (passwordAttempts >= badPasswordLockout || graceLoginsLeft <= 0) {
					throw new Exception("Your account has been locked.  Too many login attempts.");
				} 
					
				if (commonAuthBO.isVerified()) {
					SessionValues.setSessionValues(request, commonAuthBO.getUsername(), courtType, null); 

					// reset password attempts
					new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, commonAuthBO.getUsername()).setPasswordAttempts(0).update();

					response.sendRedirect(request.getContextPath() + MENU_SERVLET);
				} else {
					// increase password attempts
					passwordAttempts++;
					new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, commonAuthBO.getUsername()).setPasswordAttempts(passwordAttempts).setGraceLoginsLeft(badPasswordLockout - passwordAttempts).update();
					
					throw new Exception("Invalid username and/or password");
				}
			} catch (Exception e) {
				logger.error("Exception in LoginServlet:", e);
				request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
				getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);
			}
		}
	}
	
	/**
	 * Convenience method used to forward a request from a servlet to another resource (servlet, JSP file, or HTML file).
	 * @param url String - The relative path to the resource.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void forward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{
		logger.debug(".forward(url, request, response) URL: [" + url + "]");
		if (url == null || url.length() < 1) {
			throw new ServletException("Missing URL.");
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
}
