package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.PSCReferralDetailReport;
import gov.utcourts.corisweb.xo.PsReferralXO;
import gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.pdf.PdfTemplate;

/**
 * Servlet implementation class PSCReferralDetailPdfServlet
 */
@WebServlet(description = "Print Case Referral Detail PDF", urlPatterns = { "/PSCReferralDetailPdfServlet" })
public class PSCReferralDetailPdfServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;
	
	static PdfTemplate template;   
    /**
     * @see BaseServlet#BaseServlet()
     */
    public PSCReferralDetailPdfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] pdfData = null;
		ServletOutputStream sout = response.getOutputStream();
		int refId = TextUtil.getParamAsInt(request, "referralId");
		try{
			if(refId > 0){
				List<PsReferralBO> refCases = PsReferralXO.getReferral(refId);
				PSCReferralDetailReport refPdf = new PSCReferralDetailReport();
				pdfData = refPdf.generateReferralDetails(refCases);
				
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "inline; filename=sample.pdf");
				sout.write(pdfData);
				sout.flush();			
			}else {
				throw new ServletException("No referral found.");
			}
		}catch(Exception e){
			throw new IOException("Fail to get referral details PDF");
		}finally {
			try {
				sout.close();
			} catch (IOException ioe) {
				// do nothing
			}
		}
	}

}
