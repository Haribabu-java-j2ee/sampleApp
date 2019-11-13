package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dto.CaseHeaderDTO;
import gov.utcourts.coriscommon.dto.DebtCollectionAccountDTO;
import gov.utcourts.coriscommon.dto.DebtCollectionSearchDTO;
import gov.utcourts.coriscommon.dto.DebtPaymentDTO;
import gov.utcourts.coriscommon.dto.JudgmentDTO;
import gov.utcourts.coriscommon.xo.AccountXO;
import gov.utcourts.coriscommon.xo.CaseHistoryXO;
import gov.utcourts.coriscommon.xo.ChargeXO;
import gov.utcourts.coriscommon.xo.JudgmentXO;
import gov.utcourts.coriscommon.xo.KaseXO;
import gov.utcourts.coriscommon.xo.TransactionXO;
import gov.utcourts.corisweb.container.StringBuilderContainer;
import gov.utcourts.corisweb.util.URLEncryption;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OsdcServlet
 */
@WebServlet("/OsdcDetailServlet")
public class OsdcDetailServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OsdcDetailServlet() {
        super();

    }

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = URLEncryption.getParamAsInt(request, "userId");
		if (userId == 0){
			userId = (Integer) request.getSession(false).getAttribute("USER_ID");
		} else {
			request.getSession().setAttribute("USER_ID", userId);
		}
		String mode = URLEncryption.getParamAsString(request, "mode");
		String courtType = URLEncryption.getParamAsString(request, "courtType");
		int intCaseNum = URLEncryption.getParamAsInt(request, "intCaseNum");
		request.setAttribute("searchMode", URLEncryption.getParamAsString(request, "searchMode"));
	
		
		
		try {
			
			if ("caseHistory".equals(mode)){
				
				byte[] bytes = CaseHistoryXO.getCaseHistory(courtType, intCaseNum);
				ServletOutputStream sout = response.getOutputStream();
		
				response.setContentType("application/pdf");
				sout.write(bytes);
				
				sout.flush();
				sout.close();
				sout =null;
				bytes = null;
				
				return;	
			} else {
				CaseHeaderDTO header = KaseXO.findCaseHeader(courtType, intCaseNum);
				List<DebtCollectionAccountDTO> accounts = AccountXO.findDebtCaseAccounts(intCaseNum, courtType);
				List<DebtPaymentDTO> payments = TransactionXO.searchDebtPayments(courtType, intCaseNum);
				List <JudgmentDTO> dto = JudgmentXO.findJudgments(courtType, intCaseNum);
				StringBuilder sb = new StringBuilder();
				for(JudgmentDTO jgDto : dto){
					sb.append(jgDto.getJdmtCode());
				}
				String judgmentCodes = sb.toString();
				String debtCollStatusDesc = URLEncryption.getParamAsString(request, "debtCollStatusDesc");
//				int acctFeeCount = AccountXO.getAcctFeeCountByCourtAndIntCaseNum(courtType, intCaseNum);
				int bailCount = AccountXO.getBailCountByIntCaseNum(courtType, intCaseNum);
				int bondCount = AccountXO.getBondCountByIntCaseNum(courtType, intCaseNum);
				int trustCount = AccountXO.getTrustCountByIntCaseNum(courtType, intCaseNum);
				int acctTrustCount = AccountXO.getJSTrustCountByIntCaseNum(courtType, intCaseNum);
				int chargeCount2 = ChargeXO.getPAFeeCountByIntCase(courtType, intCaseNum);
				boolean isCrimCase=KaseXO.isCrimCase(courtType,intCaseNum);
				int debtCollCount = 0;				
				if(isCrimCase)
				{
					debtCollCount=DebtCollectionSearchDTO.getChargesByCourtTypeAndIntCaseNum(courtType, intCaseNum);
				}
				
				KaseBO kaseBO = AccountXO.getKase(courtType, intCaseNum);
				
				
				StringBuilderContainer warningMessages = new StringBuilderContainer();
//				if (acctFeeCount > 0) {
//					warningMessages.append("Plea in Abeyance Fee.");
//				} else {
//					warningMessages.append("Plea in Abeyance.");
//				}
				
				if (kaseBO != null && kaseBO.getLinkedCaseId() > 0)
					warningMessages.append("This Case is linked to other cases");
				
				if (kaseBO != null && kaseBO.getLinkedDefId() > 0)
					warningMessages.append("This case is linked to other defendants");

				if (bailCount > 0) 
					warningMessages.append("Undisposed Bail.");
				
				if (bondCount > 0) 
					warningMessages.append("Undisposed Bond.");
			
				if (trustCount > 0) 
					warningMessages.append("Trust Check to be Printed.");
				
				if (chargeCount2 == 1)
					warningMessages.append("Plea in Abeyance.");
				else if(chargeCount2 == 2)
					warningMessages.append("Plea in Abeyance Fee.");
				
				if (acctTrustCount > 0)
					warningMessages.append("This case is linked Jointly and Severally");
				
				
				if (isCrimCase && debtCollCount < 1) 
					warningMessages.append("Warning, No charges on this case have a guilty type disposition.");
				
				request.setAttribute("CASE_HEADER", header);
				request.setAttribute("ACCOUNT_DETAIL", accounts);
				request.setAttribute("PAYMENTS", payments);
				request.setAttribute("WARNING_MESSAGES", warningMessages.toString());
				request.setAttribute("debtCollStatusDesc", debtCollStatusDesc);
			}
			
		} catch (Exception e){
			e.printStackTrace();
			String errorMsg = "Error occurred: ";
			if (e.getMessage() == null){
				errorMsg = errorMsg + "Unknown reason.";
			} else {
				errorMsg = errorMsg + e.getMessage();
			}
			request.setAttribute("ERROR_MSG", errorMsg);
			throw new ServletException(errorMsg);
		}
		
		getServletContext().getRequestDispatcher("/jsp/osdcAccount.jsp").forward(request, response);
	}
}
