package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.util.SAPEncryptDecrypt;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.transactionkey.ws.TransactionKeyProxy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.json.java.JSONObject;

/**
* Servlet implementation class DocMgrServlet
*/
@WebServlet("/DocMgrServlet")
public class DocMgrServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
     
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public DocMgrServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String callingSystem = URLEncryption.getParamAsString(request,"systemName");

			//get the passed in params
			String caseLocation = URLEncryption.getParamAsString(request, "locnCode");
			String caseNumber = URLEncryption.getParamAsString(request,"caseNum");
			String docId = URLEncryption.getParamAsString(request, "docId");

			//these constants need to be put in a constants file somewhere, once we know which constants file we are going to use for this project
			String TRANSACTION_KEY_WS_ENDPOINT = Constants.TRANSACTION_WS_ENDPOINT;
			String CALLING_SYSTEM = Constants.SYSTEM_NAME;
			String APP_AUTHEN_KEY = Constants.DOC_DIRECT_APP_AUTH_KEY;
			String DOC_DIRECT_URL = Constants.DOC_DIRECT_URL;
			String DOCMGR_USER = Constants.DOCMGR_USER;
			
			TransactionKeyProxy transactionKeyProxy = new TransactionKeyProxy();
		    transactionKeyProxy.setEndpoint(TRANSACTION_KEY_WS_ENDPOINT);
		    gov.utcourts.transactionkey.ReturnCode transactionKey = transactionKeyProxy.generateKey("Document Direct", CALLING_SYSTEM, APP_AUTHEN_KEY);
		    
		    if (transactionKey != null && "60".equals(transactionKey.getReasonCode())) {
		    	SAPEncryptDecrypt de = new SAPEncryptDecrypt("DESede", SAPEncryptDecrypt.DEFAULT_ENCRYPTION_KEY);
		    	JSONObject json = new JSONObject();
		    	json.put("url",  DOC_DIRECT_URL);
		    	json.put("authKey", APP_AUTHEN_KEY);
		    	json.put("docId", de.encrypt(docId));
		    	json.put("callingSystem", de.encrypt(CALLING_SYSTEM));
		    	json.put("docMgrUser", de.encrypt(DOCMGR_USER));
		    	json.put("transactionKey", transactionKey.getTransactionKey());
		    	json.put("transactionId", transactionKey.getTransactionId());
		    	json.put("userId", de.encrypt(""+0));
		    	json.put("caseLocation", de.encrypt(caseLocation));
		    	json.put("cost", de.encrypt("0"));
		    	json.put("documentTitle", "Coris Case History - Case Number "+caseNumber);
		    	
		    	response.setContentType("text/json");
		    	response.setHeader("Cache-Control", "no-cache");
		    	response.getWriter().write(json.toString());

		    	return;
		    } 		
		  
		    
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
