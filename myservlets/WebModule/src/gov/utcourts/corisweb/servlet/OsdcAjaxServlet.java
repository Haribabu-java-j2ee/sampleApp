package gov.utcourts.corisweb.servlet;

//import gov.utcourts.coriscommon.beans.AccountBO;
import gov.utcourts.coriscommon.dataaccess.osdcaccthistory.OsdcAcctHistoryBO;
import gov.utcourts.coriscommon.dto.DebtCollectionAccountDTO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.coriscommon.xo.AccountXO;
import gov.utcourts.coriscommon.xo.KaseXO;
import gov.utcourts.coriscommon.xo.OsdcAcctHistoryXO;
import gov.utcourts.coriscommon.xo.OsdcCancelXO;
import gov.utcourts.coriscommon.xo.OsdcXmlXO;
import gov.utcourts.corisweb.util.Base64Encoding;
import gov.utcourts.corisweb.util.URLEncryption;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/**
 * Servlet implementation class OsdcAjaxServlet
 */
@WebServlet("/OsdcAjaxServlet")
public class OsdcAjaxServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OsdcAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//DatabaseConnection.setUseJdbc();
		String mode = URLEncryption.getParamAsString(request, "mode");
		int userId = URLEncryption.getParamAsInt(request, "userId");
		if (userId == 0){
			userId = (Integer) request.getSession(false).getAttribute("USER_ID");
		} else {
			request.getSession().setAttribute("USER_ID", userId);
		}
		
		JSONObject jsonObj = new JSONObject();
		
		try {
			if ("setFlag".equals(mode)){
				// Set local debt collection flag
				String courtType = URLEncryption.getParamAsString(request, "courtType");
				int intCaseNum = URLEncryption.getParamAsInt(request, "intCaseNum");
				
				//Notice flag is current status and we need to reverse it
				String flag = URLEncryption.getParamAsString(request, "localDebtFlag");
				boolean settingLocalDebtCollection = false;
				
				if (flag == null || "".equals(flag) || "N".equals(flag))
					settingLocalDebtCollection = true;
				
				if (settingLocalDebtCollection) {
					List<DebtCollectionAccountDTO> accounts = AccountXO.findDebtCaseAccounts(intCaseNum, courtType);
					String osdcStatus = null;
					for (DebtCollectionAccountDTO account : accounts){
						osdcStatus = TextUtil.print(account.getOsdcStatus());
						if ("R".equals(osdcStatus) || "S".equals(osdcStatus)){
							jsonObj.put("error", true);
							jsonObj.put("message", "Warning: Unable to set the debt collection to local because at least one account is already in OSDC.");
						}
					}
				}
				if (!jsonObj.containsKey("error")) {
					KaseXO.setLocalDebtCollectionFlag(intCaseNum, courtType, settingLocalDebtCollection);
					if (settingLocalDebtCollection)
						jsonObj.put("message", "Local Debt Collection has been set.");
					else
						jsonObj.put("message", "Local Debt Collection has been removed.");
					jsonObj.put("status", settingLocalDebtCollection?"Y":"N");
				}

			} else if ("osdcSend".equals(mode)){
				
				HashMap<String, String> variables = Base64Encoding.parseQueryString(request.getParameter("frmData"));
				String courtType = variables.get("courtType");
				int intCaseNum = Integer.valueOf(variables.get("intCaseNum"));
				
				if ("J".equals(courtType) && OsdcXmlXO.findOsdcEligibility(courtType, intCaseNum) == 0)
					throw new Exception("OSDC Processing is not set up for this court.");	
					
				List<Integer> accountNumbers = new ArrayList<Integer>();
				List<String> notes = new ArrayList<String>();
				String key;
				int accountNum; 
				Iterator it = variables.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        key = (String) pair.getKey();			       
			        if (key.indexOf("checkAccount") == 0){
			        	accountNum = Integer.valueOf((String) pair.getValue());
			        	accountNumbers.add(accountNum);
						notes.add(variables.get("note" + accountNum));
			        }
			    }
			    
			    JSONArray array = new JSONArray();
			    JSONObject accountObj = null; 
			    String message=accountNumbers.size() + " account(s) were sent to OSDC successfully.";
				if (accountNumbers.size() > 0){
					try{
						
						AccountXO.processAccountsForOsdc(accountNumbers, userId, notes, courtType);
					
					}
					catch(Exception e)
					{
						
					 
						if(e.getMessage()!=null && e.getMessage().startsWith("Judgment Warning:"))
						{
							message=message+"<br>"+"<font color=\"red\">"+e.getMessage()+"</font>";
						}
						
						else
						{
							throw e;
						}
					}
					for (int account : accountNumbers){
						accountObj = new JSONObject();
						accountObj.put("accountNum", account);
						array.add(accountObj);
					}
				}
			    
				jsonObj.put("courtType", courtType);
				jsonObj.put("intCaseNum", intCaseNum);
				jsonObj.put("userId", userId);
			    jsonObj.put("message",message);
			    
			} else if ("saveNote".equals(mode)){
				
				HashMap<String, String> variables = Base64Encoding.parseQueryString(request.getParameter("frmData"));
				
				String courtType = variables.get("courtType");
				int intCaseNum = Integer.valueOf(variables.get("intCaseNum"));
				
				List<DebtCollectionAccountDTO> accounts = AccountXO.findDebtCaseAccounts(intCaseNum, courtType);
				String note = "";
				for (DebtCollectionAccountDTO account:accounts){
					note = variables.get("note" + account.getAcctNum());
					AccountXO.saveOsdcNoteByAccount(account.getAcctNum(), userId, note, courtType);
				}
				
				jsonObj.put("message", "Notes are saved successfully.");
				
			} else if ("recall".equals(mode)){
				
				String courtType = request.getParameter("courtType");
				int intCaseNum = Integer.valueOf(request.getParameter("intCaseNum"));
				int accountNum = Integer.valueOf(request.getParameter("accountNum"));
				String message="";
				String beforeRecallAccountStatus = AccountXO.getStateDebtCollStatusByAccount(null, accountNum, courtType);
				recallAccNumAndAddCaseHistory(userId, courtType, intCaseNum, accountNum, beforeRecallAccountStatus);
				
				// Count how many accounts are still at State Debt Collection
				boolean useStatic = true; // Change later!?
				int numAcctsOnCaseAtOsdc = 0;
				List<DebtCollectionAccountDTO> listDebtCollAcctsOnCase = AccountXO.findDebtCaseAccounts(intCaseNum, courtType);
				for (DebtCollectionAccountDTO account : listDebtCollAcctsOnCase){
					OsdcAcctHistoryBO osdcAcctHistoryBO=OsdcAcctHistoryXO.getMostRecentStatusByAcctNum(account.getAcctNum(), courtType);
					if (osdcAcctHistoryBO!=null && "S".equalsIgnoreCase(osdcAcctHistoryBO.getOsdcStatus()))
						numAcctsOnCaseAtOsdc++;
				}
				
				
				if("S".equalsIgnoreCase(beforeRecallAccountStatus))
				{
					message = "Account(s) "+ accountNum+" recalled successfully. Please notify the Office of State Debt Collection at (801) 538-9645 or through dcART.";
				}
				else if("R".equalsIgnoreCase(beforeRecallAccountStatus))
				{
					message ="Account send to OSDC has been cancelled successfully.";
				}
					 
				jsonObj.put("courtType", courtType);
				jsonObj.put("intCaseNum", intCaseNum);
				jsonObj.put("userId", userId);
				jsonObj.put("numAcctsOnCaseAtOsdc", numAcctsOnCaseAtOsdc);
			    jsonObj.put("message", message);
			    
			} else if ("recallAll".equals(mode)){
				
				String courtType = request.getParameter("courtType");
				int intCaseNum = Integer.valueOf(request.getParameter("intCaseNum"));
				List<Integer> accNumList=OsdcCancelXO.getAccNumListFromDcAccount(intCaseNum,courtType); 
				
				String accountsSent = "";
				String accountsSchedule = "";
				String message="";
				Map<Integer, String> beforeRecallAccNumAndOsdcStatusMap=AccountXO.getMapOfAccNumAndLatestOsdcStatusByAccNums(null, accNumList, courtType);
				
				for(Integer accNum:beforeRecallAccNumAndOsdcStatusMap.keySet())
				{
					if(beforeRecallAccNumAndOsdcStatusMap.get(accNum)!=null)
					{
						if("S".equalsIgnoreCase(beforeRecallAccNumAndOsdcStatusMap.get(accNum)))
						{
							 
							accountsSent = accountsSent +" "+accNum+", ";
						}
						
						else if("R".equalsIgnoreCase(beforeRecallAccNumAndOsdcStatusMap.get(accNum)))
						{
							 
							accountsSchedule = accountsSchedule +", ";
						}
					}
					
					
				}
				
				recallAllAccNumsAndAddCaseHistory(userId, courtType, intCaseNum,accNumList,beforeRecallAccNumAndOsdcStatusMap);
				
				
				
				// Count how many accounts are still at State Debt Collection
				boolean useStatic = true; // Change later!?
				int numAcctsOnCaseAtOsdc = 0;
				List<DebtCollectionAccountDTO> listDebtCollAcctsOnCase = AccountXO.findDebtCaseAccounts(intCaseNum, courtType);
				for (DebtCollectionAccountDTO account : listDebtCollAcctsOnCase){
					OsdcAcctHistoryBO osdcAcctHistoryBO=OsdcAcctHistoryXO.getMostRecentStatusByAcctNum(account.getAcctNum(), courtType);
					if (osdcAcctHistoryBO!=null && "S".equalsIgnoreCase(osdcAcctHistoryBO.getOsdcStatus()))
						numAcctsOnCaseAtOsdc++;
				}
				 if(accountsSchedule.length() > 0)
				 {
					 message = "Account send to OSDC has been cancelled successfully.";
				 }
				
				 if(accountsSent.length() > 0)
				 {
					 if(message.length() > 0)
					 {
						 message = message +"<br><br>";
					 }
					 accountsSent=accountsSent.substring(0, accountsSent.lastIndexOf(','));
					 message = message + "Account(s) "+ accountsSent+" recalled successfully. Please notify the Office of State Debt Collection at (801) 538-9645 or through dcART.";
				 }
				 
				
				
				jsonObj.put("courtType", courtType);
				jsonObj.put("intCaseNum", intCaseNum);
				jsonObj.put("userId", userId);
				jsonObj.put("numAcctsOnCaseAtOsdc", numAcctsOnCaseAtOsdc);				
			    jsonObj.put("message", message);
				jsonObj.put("error", false);
				
			} else if ("calcInterest".equals(mode)){
				String courtType = URLEncryption.getParamAsString(request, "courtType");
				int intCaseNum = URLEncryption.getParamAsInt(request, "intCaseNum");
			//	AccountBO accountBO = new AccountBO();
				double[] retValue = AccountXO.calcInterestAccrued(userId, intCaseNum, courtType);
				String interestMsg = "No Message";
				if (retValue[0] == 0) { // zero means stored proc ran successfully
					 if (retValue[1] > 0) {
						 interestMsg = "Unpaid interest totalling " + TextUtil.currencyFormatter.format(retValue[1]) + " has been added to the amount due";
					 }
				} else {
					interestMsg = "Failed to return interest accrued.";
				}
				jsonObj.put("interestMsg", interestMsg);

			}
			
			else if ("countAtOsdc".equals(mode)){
							
							String courtType = request.getParameter("courtType");
							int intCaseNum = Integer.valueOf(request.getParameter("intCaseNum"));
							int accountNum = Integer.valueOf(request.getParameter("accountNum"));
			
							// Count how many accounts are still at State Debt Collection
							boolean useStatic = true; // Change later!?
							int numAcctsOnCaseAtOsdc = 0;
							List<DebtCollectionAccountDTO> listDebtCollAcctsOnCase = AccountXO.findDebtCaseAccounts(intCaseNum, courtType);
							for (DebtCollectionAccountDTO account : listDebtCollAcctsOnCase){
								
								OsdcAcctHistoryBO osdcAcctHistoryBO=OsdcAcctHistoryXO.getMostRecentStatusByAcctNum(account.getAcctNum(), courtType);
								if (osdcAcctHistoryBO!=null && "S".equalsIgnoreCase(osdcAcctHistoryBO.getOsdcStatus()))
									numAcctsOnCaseAtOsdc++;
							}
						    
							jsonObj.put("courtType", courtType);
							jsonObj.put("intCaseNum", intCaseNum);
							jsonObj.put("userId", userId);
							jsonObj.put("numAcctsOnCaseAtOsdc", numAcctsOnCaseAtOsdc);
							jsonObj.put("message", "");
			}
            
			
		} catch (Exception e) {		
			jsonObj.put("error", true);
			jsonObj.put("message", "Error occurred: " + e.getMessage());
			//throw new ServletException(e.getMessage());
		} 
		
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.println(jsonObj.toString());
		out.flush();
		out.close();
		out = null;
		
		
	}

	private void recallAccNumAndAddCaseHistory(int userId, String courtType, int intCaseNum,
			int accountNum,String beforeRecallAccountStatus) throws Exception {
		
		try
		{
			//String beforeRecallAccountStatus = AccountXO.getStateDebtCollStatusByAccount(null, accountNum, courtType);
			boolean addCaseHist=false;
			
			if(beforeRecallAccountStatus!=null && "S".equalsIgnoreCase(beforeRecallAccountStatus))
			{
				addCaseHist=true;
			}
			
			OsdcCancelXO.recallOsdcAccountNum(courtType, userId, accountNum);
			String afterRecallAccountStatus = AccountXO.getStateDebtCollStatusByAccount(null, accountNum, courtType);
			
			if(addCaseHist && afterRecallAccountStatus!=null && "C".equalsIgnoreCase(afterRecallAccountStatus))
			{
				OsdcXmlXO.addCaseHistory(courtType, intCaseNum, "Account(s) recalled from the Office of State Debt Collection", userId);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		
	}

	private void recallAllAccNumsAndAddCaseHistory(int userId, String courtType,
			int intCaseNum,List<Integer> accNumList,Map<Integer, String> beforeRecallAccNumAndOsdcStatusMap) throws Exception {
		
		try{
			//List<Integer> accNumList=OsdcCancelXO.getAccNumListFromDcAccount(intCaseNum,courtType);
			
			//Map<Integer, String> beforeRecallAccNumAndOsdcStatusMap=AccountXO.getMapOfAccNumAndLatestOsdcStatusByAccNums(null, accNumList, courtType);
			
			OsdcCancelXO.recallOsdcIntCaseNum(courtType, userId, intCaseNum);
			
			Map<Integer, String> afterRecallAccNumAndOsdcStatusMap=AccountXO.getMapOfAccNumAndLatestOsdcStatusByAccNums(null, accNumList, courtType);
			 
			boolean addCaseHist=false;
			
			for(Integer accNum:beforeRecallAccNumAndOsdcStatusMap.keySet())
			{
				if(beforeRecallAccNumAndOsdcStatusMap.get(accNum)!=null && "S".equalsIgnoreCase(beforeRecallAccNumAndOsdcStatusMap.get(accNum)))
				{
					addCaseHist=true;
					break;
				}
				
			}
			 
			if(addCaseHist)
			{
				 for(Integer accNum:afterRecallAccNumAndOsdcStatusMap.keySet()){
					 
					 if(afterRecallAccNumAndOsdcStatusMap.get(accNum)!=null && "C".equalsIgnoreCase(afterRecallAccNumAndOsdcStatusMap.get(accNum)))
					 {
						 OsdcXmlXO.addCaseHistory(courtType, intCaseNum, "Account(s) recalled from the Office of State Debt Collection", userId);
						 break;
					 }
					 
				 }					
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		
	}


}
