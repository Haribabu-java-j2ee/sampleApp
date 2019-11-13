package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CorisCaseHistoryAllReport;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.docmgr.util.DocDirectUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


@WebServlet("/CorisCaseHistoryAllServlet")
public class CorisCaseHistoryAllServlet extends BaseServlet {
	private static final long serialVersionUID = -3335412659844L;
	
	private static Logger logger = Logger.getLogger(CorisCaseHistoryAllServlet.class.getName());
	
	//static  "";
		
	/**
	  * @see HttpServlet#HttpServlet()
	*/
	public CorisCaseHistoryAllServlet() {
	     super();
	}
	  
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] chba = null;
		try {
			
			String caseNum = URLEncryption.getParamAsString(request, "caseNum");
			String locnCode = URLEncryption.getParamAsString(request, "locnCode");
			String courtType = URLEncryption.getParamAsString(request, "courtType");
			String logName = URLEncryption.getParamAsString(request, "logName");
			String daysPast = URLEncryption.getParamAsString(request, "daysPast");
			
			// ///////////////////////////////////////////////////////////////
			// All Case Numbers
			// ///////////////////////////////////////////////////////////////
			String caseNumbers[];
			caseNumbers = caseNum.split("[|]");
			
			// ///////////////////////////////////////////////////////////////
			// Create new document
			// ///////////////////////////////////////////////////////////////
			List<byte[]> documentList = new ArrayList<byte[]>();
			byte[]  casehistoryPdf = null;

			for (int i=0; i < caseNumbers.length; i++){
				if (!TextUtil.isEmpty(caseNumbers[i])){
					String caseNumber = caseNumbers[i];
					casehistoryPdf = new CorisCaseHistoryAllReport().processCaseHistoryAll(caseNumber, locnCode, courtType, logName, "", daysPast, response);
					if (casehistoryPdf != null && casehistoryPdf.length > 0){
						documentList.add(casehistoryPdf);
					}
				}
			}
			
			chba = DocDirectUtil.mergePDF(documentList, false);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=sample.pdf");
			response.getOutputStream().write(chba);
			response.getOutputStream().flush();			
			
						
		} catch (Exception e) {
			logger.error("performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
}
