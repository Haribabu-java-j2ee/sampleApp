package gov.utcourts.corisweb.servlet;

import gov.utcourts.applicationauthen.ws.ApplicationAuthenticationProxy;
import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.corisweb.session.SessionValues;
import gov.utcourts.corisweb.util.Base64Encoding;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.util.TextUtil;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CorSingleSignOnServletisServlet
 */
@WebServlet("/sso")
public class sso extends BaseServlet {
	
	private static final long serialVersionUID = 45114415221L;
       
	private static final String MENU_SERVLET = "/MenuServlet";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public sso() {
        super();
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
	    	boolean authenticated = false;
			String requestedSystem = Constants.APPLICATION_AUTHENTICAION_WS_REQUESTED_SYSTEM;
	    	String applicationKey = Constants.APPLICATION_AUTHENTICAION_WS_APPLICATION_KEY;

	    	boolean isWorkSpace = URLEncryption.getParamAsString(request,"e").startsWith("N");

	    	
	    	// put the objects on the request after receiving from Coris
			HashMap<String, String> params; 
			
			if (!isWorkSpace){
				// From PowerBuilder
				String encodedParameters = URLEncryption.getParamAsStringFromRequest(request, URLEncryption.FROM_CORIS_PARAMETER);				
				params = Base64Encoding.urlDecodeByte64(encodedParameters);
			} else {
				// From Java Code
				String encryptedParameters = URLEncryption.getParamAsStringFromRequest(request, URLEncryption.REQUEST_PARAMETER); 
				params = URLEncryption.getParameterPairs(encryptedParameters);
			}
			
			StringBuilder parameterString = new StringBuilder();
			for (String key : params.keySet()) {
				request.setAttribute(key, params.get(key));
				parameterString.append(TextUtil.isEmpty(parameterString.toString()) ? "?" : "&");
				parameterString.append(key + "=" + params.get(key));
			}
			request.setAttribute("parameterString", parameterString.toString());
	    	
	    	String callingSystem = params.get("callingSystem"); 	
			String transactionId = params.get("transactionId"); 	
			String transactionKey = params.get("transactionKey"); 	
			String logname = params.get("logName");					
			String courtType = params.get("courtType");				
			String locnCode = params.get("locnCode");				
			
			// single sign on
			ApplicationAuthenticationProxy applicationAuthenticationProxy = new ApplicationAuthenticationProxy();
			applicationAuthenticationProxy.setEndpoint(Constants.APPLICATION_AUTHENTICAION_WS_ENDPOINT);
			gov.utcourts.applicationauthen.ReturnCode authentication = applicationAuthenticationProxy.validate(requestedSystem, callingSystem, applicationKey, transactionId, transactionKey);
			if (authentication != null && "60".equals(authentication.getReasonCode())) {
				authenticated = true;
			}
			
			applicationAuthenticationProxy = null;
			authentication = null;
			
			if (authenticated) {
				if (TextUtil.isEmpty(logname)) {
					request.setAttribute("ErrorMessage", "logname must be provided.");
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				} else {
					request.getSession().invalidate();
					SessionValues.setSessionValues(request, logname, courtType, locnCode);   		// store in the session
					
					if ("N".equalsIgnoreCase(params.get("isCoris")) && "getPDF".equalsIgnoreCase(params.get("mode"))) {
						getServletContext().getRequestDispatcher("/" + params.get("destination")).forward(request, response);
					} else {
						getServletContext().getRequestDispatcher(MENU_SERVLET).forward(request, response);
					}
				}
			} else {
				request.setAttribute("ErrorMessage", "Invalid calling system or key.");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
    	} catch (Exception e) {
    		throw new ServletException(e.getMessage());
    	}
    }

}
