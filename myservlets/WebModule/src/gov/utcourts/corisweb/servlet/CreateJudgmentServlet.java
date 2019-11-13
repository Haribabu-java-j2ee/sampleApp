package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dto.CaseHeaderDTO;
import gov.utcourts.coriscommon.util.CaseSentenceAccountProcessor;
import gov.utcourts.coriscommon.xo.KaseXO;
import gov.utcourts.coriscommon.xo.PersonnelXO;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.util.TextUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateJudgmentServlet
 */
@WebServlet("/CreateJudgmentServlet")
public class CreateJudgmentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "/jsp/status.jsp";
		try {
			// Removed the isCORIS check.  It is handled by the SSO servlet and the getParams.  Selart  03/21/2019  Piv #164307361
			String logName = URLEncryption.getParamAsString(request,"logName");
			String intCaseNumStr = URLEncryption.getParamAsString(request,"intCaseNumber");
			String courtType = URLEncryption.getParamAsString(request,"courtType");
			String partyIdStr = URLEncryption.getParamAsString(request,"partyId"); // changed to party ID.  Selart 11/27/2018 Piv #162257292
			String isPrisonStr = URLEncryption.getParamAsString(request,"isPrison");
			String acctNumbersStr = URLEncryption.getParamAsString(request,"acctNumbers");
			
			// Get the party ID if there is one.  Added 11/27/2018  Piv #162257292
			int partyId = 0;
			if (partyIdStr != null && partyIdStr.length() > 0) {
				partyId = Integer.parseInt(partyIdStr);
			}

			List<Integer> acctNumbers = null;
			if (TextUtil.isEmpty(logName) || TextUtil.isEmpty(intCaseNumStr) || TextUtil.isEmpty(courtType)
					|| TextUtil.isEmpty(isPrisonStr)) {
				throw new Exception("userID, intCaseNum, courtType, and isPrison must be provided!");
			} else {
		
				int intCaseNum = Integer.valueOf(intCaseNumStr);
				boolean isPrison = false;
				if ("Y".equals(isPrisonStr))
					isPrison = true;
				if (!TextUtil.isEmpty(acctNumbersStr)){
					acctNumbers = new ArrayList<Integer>();
					if (acctNumbersStr.indexOf("|") > 0){
						String[] acctNumberList = acctNumbersStr.split("\\|");
						for (String acctNumber : acctNumberList){
							acctNumbers.add(Integer.valueOf(acctNumber));
						}
					} else {
						acctNumbers.add(Integer.valueOf(acctNumbersStr));
					}
				}
				
				CaseHeaderDTO kaseDTO = KaseXO.findCaseHeader(courtType, intCaseNum);
				if (kaseDTO == null){
					throw new Exception("intCaseNum " + intCaseNum + "is not a valid case in the coris system.");
				}
				
				// Changed isApp to partyId.  Selart  11/27/2018  Piv #162257292
				PersonnelBO personnelBO = PersonnelXO.getPersonnelByLogNameLocnCodeCourtType(logName, kaseDTO.getLocnCode(), courtType);
				if (personnelBO == null) {
					throw new Exception(logName + " is not a valid username in the coris system.");
				}
				
				CaseSentenceAccountProcessor.createJudgmentEtc(courtType, intCaseNum, acctNumbers, personnelBO.getUseridSrl(), partyId, isPrison);
				
				request.setAttribute("IS_ERROR", "N");
				request.setAttribute("STATUS_MSG", "Judgments are created successfully!");
			}
		} catch (Exception e) {
			String msg = "Error occurred: " + e.getMessage();
			request.setAttribute("IS_ERROR", "Y");
			request.setAttribute("STATUS_MSG", msg);
		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);  
		}
		
	}
	
	private void sendStatus(HttpServletResponse response, String isError, String msg, int width, int height) throws IOException{
		URLEncryption urlEncrypt = new URLEncryption("/CorisWEB/jsp/status.jsp");
		String url = urlEncrypt.urlEncrypt("IS_ERROR=" + isError + "&STATUS_MSG=" + msg.replace("&", "%26"));
		response.setContentType("text/html");
	    StringBuffer strBuf = new StringBuffer();
	    strBuf.append("<html>\n");
	    strBuf.append(" <head>\n");
	    strBuf.append("     <script type='text/javascript'>\n");
	    strBuf.append("         function openInfoInANewWindow() {\n");
	    strBuf.append("             var left = (screen.width/2)-(" + width + "/2);\n");
	    strBuf.append("             var top = (screen.height/2)-(" + height + "/2);\n");
	    strBuf.append("             var mywin = window.open('" + url + "','Status','width=" + width + ",height=" + height + ",resizable=no,scrollbars=no,toolbar=no,menubar=no');\n");
	    strBuf.append("             mywin.moveTo(left, top);\n");
	    strBuf.append("             mywin.opener.close();\n");
	    strBuf.append("         }\n");
	    strBuf.append("     </script>\n");
	    strBuf.append(" </head>\n");
	    strBuf.append(" <body onload=\"openInfoInANewWindow()\">\n");
	    strBuf.append(" </body>\n");
	    strBuf.append("</html>\n");
	    ServletOutputStream servOutputStream;
	    servOutputStream = response.getOutputStream();
	    servOutputStream.println(strBuf.toString());
	    servOutputStream.flush();
	}
	
       
   
}
