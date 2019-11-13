package gov.utcourts.corisweb.servlet;

import gov.utcourts.corisweb.util.PdfUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisinterface.report.CorisDomesticRelationsInjunctionDoc;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DomesticRelationsInjunctionServlet
 */
@WebServlet("/DomesticRelationsInjunctionServlet")
public class DomesticRelationsInjunctionServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DomesticRelationsInjunctionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String logName = URLEncryption.getParamAsString(request,"logName");
		String intCaseNumStr = URLEncryption.getParamAsString(request,"intCaseNumber");
		String courtType = URLEncryption.getParamAsString(request,"courtType");
		
		try {
			CorisDomesticRelationsInjunctionDoc engine = new CorisDomesticRelationsInjunctionDoc();
			byte[] bytes = engine.generateDocument(courtType, Integer.valueOf(intCaseNumStr), logName);
			PdfUtil.showPDF(bytes, response);
		} catch (Exception e){
			String errMsg = "Error occurred while creating Domestic Relations Injunction document: " + e.getMessage();
			PdfUtil.showPDFError(errMsg.getBytes(), response);
		} 
	}

}
