package gov.utcourts.corisweb.servlet;



import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dto.DebtCollectionCaseDTO;
import gov.utcourts.coriscommon.dto.DebtCollectionSearchDTO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.coriscommon.xo.CaseTypeXO;
import gov.utcourts.coriscommon.xo.KaseXO;
import gov.utcourts.coriscommon.xo.PersonnelXO;

import gov.utcourts.corisweb.util.URLEncryption;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class OsdcEligibleLookupServlet
*/
@WebServlet("/OsdcEligibleLookupServlet")
public class OsdcEligibleLookupServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private double defaultBalance = new Double(100.00);
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public OsdcEligibleLookupServlet() {
      super();
      // TODO Auto-generated constructor stub
  }

  @Override
	protected void performTask(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
	  	String searchMode = request.getParameter("searchDivRadio");
	  	String source = request.getParameter("source");
	  	if (searchMode == null && source == null) {
	  		processGetRequest(request, response);
	  	} else {
	  		processPostRequest(request, response, source);
	  	}
	}
	 
	
	
	private void processGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		// Removed the isCORIS check.  It is handled by the SSO servlet and the getParams.  Selart  03/21/2019  Piv #164307361
		String page = "/jsp/osdcEligibleLookup.jsp";
		try {
			// get params
			int userId = 0;
			String userIDString = URLEncryption.getParamAsString(request, "userId");
			if (!userIDString.isEmpty()) {
				userId = Integer.parseInt(userIDString);
			}
			if (userId == 0){
				String sessionUserId = "";
				if (!TextUtil.isEmpty(sessionUserId))
					userId = Integer.parseInt(sessionUserId);
			} else {
				request.getSession().setAttribute("USER_ID", userId);
			}
			
			request.getSession().setAttribute("userId", userId);
			request.getSession().setAttribute("USER_ID", userId);
			String courtType = URLEncryption.getParamAsString(request, "courtType");
			String locationCode = URLEncryption.getParamAsString(request, "locationCode");
			String intCaseNumber = URLEncryption.getParamAsString(request, "intCaseNumber");
			if (intCaseNumber == null || "".equalsIgnoreCase(intCaseNumber)) {
				intCaseNumber = "0";
			}
			request.getSession().setAttribute("locationCode", locationCode);
			// set up the searchCriteria
			DebtCollectionSearchDTO sc = new DebtCollectionSearchDTO(); 	
			sc.setAccountTypes("'I', 'F', 'T'");
 
			sc.setLocationCode(locationCode);
			sc.setCourtType(courtType);
			sc.setBalance(defaultBalance);
			sc.setOverDueLimit(90); // overdue default - option values 1, 30, 60 or 90 (days)
			sc.setIntCaseNum(TextUtil.isEmpty(intCaseNumber) ? 0 : Integer.parseInt(intCaseNumber));
			if (TextUtil.isEmpty(courtType) || TextUtil.isEmpty(locationCode))
				page = "/logout.jsp";
			
			// If we have an int case number, run a search.
			if (sc.getIntCaseNum() > 0) {
				sc.setIgnoreOverDueLimit(true);
				List<DebtCollectionCaseDTO> results = new ArrayList<DebtCollectionCaseDTO>();
			 
				results = KaseXO.findDebtCollectionEligible(request, sc);
				 
				request.setAttribute("results", results);
				
				// Set the case number to be displayed.
				if (!results.isEmpty()) {
					sc.setCaseNumber(results.iterator().next().getCaseNum());
					request.setAttribute("searchMode", "byCase");
				}
			}
			
			// set some search defaults to populate some search fields
			request.setAttribute("searchCriteria", sc);
			
//			request.setAttribute("caseNumber", caseNumber);
			
			// get the list of judges	
			List<PersonnelBO>  judgeList = PersonnelXO.getActiveJudgesByLocation(sc.getLocationCode(), sc.getCourtType());
			request.setAttribute("judgeList", judgeList);
			
			// get the list of caseTypes		
			List<CaseTypeBO> caseTypes = CaseTypeXO.getCaseTypesByCourtType(sc.getCourtType());
			request.setAttribute("caseTypes", caseTypes);
			
			getServletContext().getRequestDispatcher(page + "?userId=" + userId).forward(request, response);	
				
		} catch (Exception e){
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	
	}
	
	private void processPostRequest(HttpServletRequest request, HttpServletResponse response,String source) throws ServletException, IOException 
	{
	
		String searchMode = request.getParameter("searchDivRadio");
		request.setAttribute("searchMode", searchMode);
		if("web".equals(source))
		{
			request.setAttribute("searchMode", "byAccount");
		}
		String courtType = URLEncryption.getParamAsString(request, "courtTypeHidden");
		String locationCode = URLEncryption.getParamAsString(request, "locnCodeHidden");		
		DebtCollectionSearchDTO sc = new DebtCollectionSearchDTO();
		Collection results = new ArrayList<DebtCollectionCaseDTO>();
		int userId = URLEncryption.getParamAsInt(request, "userId");
	
		if (userId == 0){
			//Need to change this code once userid get from database---Start
		
			if((request.getSession(false).getAttribute("USER_ID")==null) &&("web".equals(source) ))
			{
				userId = 69095;
				request.getSession().setAttribute("USER_ID", userId);
			}
			else
			{
				userId = (Integer) request.getSession(false).getAttribute("USER_ID");
			}
			//Need to change this code once userid get from database---End
		 
			
		} else {
			request.getSession().setAttribute("USER_ID", userId);
		}
		
		if (TextUtil.isEmpty(locationCode)){
			//Need to change later---Start

			if((request.getSession(false).getAttribute("locationCode")==null) && ("web".equals(source) ))
			{
				locationCode = "1868";
				request.getSession().setAttribute("locationCode", locationCode);
			}
			else
			{
				locationCode = (String) request.getSession(false).getAttribute("locationCode");
			}
			//Need to change later---End
		 
			
		} else {
			request.getSession().setAttribute("locationCode", locationCode);
		}
		
	   //  (REMOVE THIS SECTION LATER) 
		if ((courtType == null || "".equals(courtType)) && ("web".equals(source)))
		{
			courtType = "D";					
			
		}
		
		try {
			if ("byCase".equals(searchMode) ) {
				// get params
				String caseNumber = URLEncryption.getParamAsString(request, "caseNumber");
				String accountTypesSelected = URLEncryption.getParamAsString(request, "accountTypesHidden");
				String lastName = request.getParameter("lastName").toUpperCase();
				String firstName = request.getParameter("firstName").toUpperCase();
								
				
					
			 

				// set up the searchCriteria
				sc = new DebtCollectionSearchDTO(); 
				sc.setCourtType(courtType);
				sc.setLocationCode(locationCode); 
				sc.setCaseNumber(caseNumber);
				sc.setLastName(lastName);
				sc.setFirstName(firstName);
				
				// get the list of judges
				List<PersonnelBO> judgeList = PersonnelXO.getActiveJudgesByLocation(sc.getLocationCode(), sc.getCourtType());
				request.setAttribute("judgeList", judgeList);

				// get all the Case Types
				List<CaseTypeBO> caseTypes = CaseTypeXO.getCaseTypesByCourtType(sc.getCourtType());
				request.setAttribute("caseTypes", caseTypes);
				
				// get the caseIntNum
				KaseBO kaseBO = null;
				if (!TextUtil.isEmpty(caseNumber)){				
					kaseBO = KaseXO.getKaseByCaseNum(caseNumber, sc.getLocationCode(), sc.getCourtType());
				}
				
				if (kaseBO != null) {
					sc.setIntCaseNum(kaseBO.getIntCaseNum());
					sc.setAccountTypes(accountTypesSelected);
					sc.setOverDueLimit(90); // default for acccount lookup screen				
					sc.setJudgeId(0);
				}
				
				 
					if (sc.getIntCaseNum() > 0
						|| !TextUtil.isEmpty(sc.getFirstName())
						|| !TextUtil.isEmpty(sc.getLastName())) {
					sc.setIgnoreOverDueLimit(true);
					results = KaseXO.findDebtCollectionEligible(request, sc);
				   }
				 
				request.setAttribute("results", results);
				
				// send search info to repopulate search fields
				request.setAttribute("searchCriteria", sc);
				
			} else if ("byAccount".equals(searchMode) || ("web".equals(source) )) {	
				// set up the searchCriteria
				sc = new DebtCollectionSearchDTO();
				sc.setCourtType(courtType);
				sc.setLocationCode(locationCode); 
				sc.setIntCaseNum(0);
				sc.setAccountTypes(URLEncryption.getParamAsString(request, "accountTypesHidden"));
				if (sc.getAccountTypes() == null)
					sc.setAccountTypes("");
				sc.setJudgeId(URLEncryption.getParamAsInt(request, "judge"));
				sc.setFromSentDate(URLEncryption.getParamAsDate(request, "start_date"));
				sc.setToSentDate(URLEncryption.getParamAsDate(request, "end_date"));
				sc.setOverDueLimit(URLEncryption.getParamAsInt(request, "overdue"));
				sc.setBalance(URLEncryption.getParamAsDouble(request, "balanceAmt"));
				sc.setCaseType(URLEncryption.getParamAsString(request, "caseType"));
				if (sc.getCaseType() == null)
					sc.setCaseType("");
				
				// get the list of Judges			
				List<PersonnelBO> judgeList = PersonnelXO.getActiveJudgesByLocation(sc.getLocationCode(), sc.getCourtType());
				request.setAttribute("judgeList", judgeList);
				
				// get all the Case Types
				List<CaseTypeBO> caseTypes = CaseTypeXO.getCaseTypesByCourtType(sc.getCourtType());
				request.setAttribute("caseTypes", caseTypes);
						
				// Get the results 
				results = new ArrayList<DebtCollectionCaseDTO>();
			     if(!("web".equals(source)) )
			     {
			    	 results = KaseXO.findDebtCollectionEligible(request, sc);
			     }
					
					if (results.size() > 0){
						request.setAttribute("results", results);
					}
				 
			}	

			// send search info to repopulate search fields
			request.setAttribute("searchCriteria", sc);
			
			getServletContext().getRequestDispatcher("/jsp/osdcEligibleLookup.jsp").forward(request, response);
			
		} catch (Exception e){
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

	 

	

}
