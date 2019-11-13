package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.coriscommon.xo.AccountXO;
import gov.utcourts.coriscommon.xo.PersonnelXO;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class OsdcAccountAtSentenceServlet
 */
@WebServlet("/OsdcAtSentencingServlet")
public class OsdcAtSentencingServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(OsdcAtSentencingServlet.class);
	static String ERROR_JSP = "errors.jsp";

	/**
	 * @see BaseServlet#BaseServlet()
	 */
	public OsdcAtSentencingServlet() {
		super();
	}

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			DatabaseConnection.setUseJndi();
			int userId = URLEncryption.getParamAsInt(request,"userId");
			String courtType = URLEncryption.getParamAsString(request,"courtType");

			if (userId == 0) {
				handleError(request, response, new Exception("userId field is required."));
				return;
			} else {
				PersonnelBO personnelBO = PersonnelXO.getPersonnelByUserIdSrl(userId, courtType);
				if (personnelBO == null) {
					handleError(request, response, new Exception("'" + userId + "is not a valid username in the coris system."));
					return;
				}
			}

			int intCaseNum = URLEncryption.getParamAsInt(request,"intCaseNum");

			List<AccountBO> accounts = AccountXO.getAccountsByIntCaseNum(intCaseNum, courtType);

			List<Integer> acctNums = new ArrayList<Integer>();
			List<String> notes = new ArrayList<String>();
			// if(partyNum > 0){
			for (AccountBO acct : accounts) {
				// if(partyNum == acct.getPartyNum()){
				acctNums.add(acct.getAcctNum());
				notes.add("OSDC account prepared at sentencing");
				// }
			}
			// }
			AccountXO.processAccountsForOsdc(acctNums, userId, notes,courtType);
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			log.error("Error in " + OsdcAtSentencingServlet.class.getName(),
					e);
			try {
				handleError(request, response, e);
			} catch (Exception e1) {
				log.error("Unhandled error in "
						+ OsdcAtSentencingServlet.class.getName(), e);
			}
		}
	}

	/**
	 * The error will be displayed without frames
	 */
	void handleError(HttpServletRequest request, HttpServletResponse response,
			Exception e) throws ServletException, IOException {
		log.error("Error in OsdcAccountAtSentenceServlet", e);
		String errorMsg = e.getMessage();
		if (TextUtil.isEmpty(errorMsg)) {
			errorMsg = e.toString();
		}
		response.setContentType("text/html");
		response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, errorMsg);
	}

	boolean canAccess(String transactionKey, String transactionId,
			String callingSystem, String securityCode) throws Exception {

		boolean authenticated = false;

		try {

			/*
			 * String reqCode =
			 * SystemVariablesBO.get(callingSystem.toUpperCase());
			 * 
			 * if (!securityCode.equals(reqCode)) throw new Exception
			 * ("Incorrect security code is found.");
			 * 
			 * ApplicationAuthenticationProxy proxy = new
			 * ApplicationAuthenticationProxy();
			 * proxy.setEndpoint(Constants.APP_AUTHENTICAION_WS_ENDPOINT);
			 * ReturnCode authentication = proxy.validate("Protective Orders",
			 * callingSystem,
			 * Constants.APP_AUTHENTICATION_JNDI_ENCRYPTED_STRING,
			 * transactionId, transactionKey);
			 * 
			 * if (authentication != null &&
			 * "60".equals(authentication.getReasonCode())) { authenticated =
			 * true; } else { throw new Exception
			 * (authentication.getReasonCode() + "-" +
			 * authentication.getReason()); } proxy = null;
			 */
		} catch (Exception e) {
			log.error("Unable to validate transaction key", e);
			throw new Exception("Unable to validate transaction key - "
					+ e.toString());
		}
		return authenticated;
	}

}
