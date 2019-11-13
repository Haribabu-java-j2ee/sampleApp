package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.casehist.CaseHistBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.summaryevent.SummaryEventBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.json.java.JSONObject;

/**
* Servlet implementation class CaseSummaryAjaxServlet
*/
@WebServlet("/CaseSummaryAjaxServlet")
public class CaseSummaryAjaxServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -3115445886L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public CaseSummaryAjaxServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObj = new JSONObject();
		try {
			String mode = TextUtil.getParamAsString(request, "mode");
			
			String funcId = TextUtil.getParamAsString(request, "funcId");
			String courtType = TextUtil.getParamAsString(request, "courtType");
			String caseNum = TextUtil.getParamAsString(request, "caseNum");
			String locnCode = TextUtil.getParamAsString(request, "locnCode");
			String eventDatetime = TextUtil.getParamAsString(request, "eventDatetime");
			String createDatetime = TextUtil.getParamAsString(request, "createDatetime");
			
			int intCaseNum = new KaseBO(courtType).where(KaseBO.CASENUM, caseNum).where(KaseBO.LOCNCODE, locnCode).find(KaseBO.INTCASENUM).getIntCaseNum();
			
			DateFormat dateFormat = Constants.dateFormatDatetimeMilliseconds;
			
			if ("delete".equals(mode)) {
				
				// case_hist - set the removed_flag
				new CaseHistBO(courtType)
				.where(CaseHistBO.FUNCID, funcId)
				.where(CaseHistBO.INTCASENUM, intCaseNum)
				.where(CaseHistBO.ENTRYDATETIME, dateFormat.parse(eventDatetime))
				.where(CaseHistBO.CREATEDATETIME, dateFormat.parse(createDatetime))
				.setRemovedFlag("Y")
				.update();

				// summary_event - set the removed_flag and display_flag
				new SummaryEventBO(courtType)
				.where(SummaryEventBO.FUNCID, funcId)
				.where(SummaryEventBO.INTCASENUM, intCaseNum)
				.where(SummaryEventBO.EVENTDATETIME, dateFormat.parse(eventDatetime))
				.where(SummaryEventBO.CREATEDATETIME, dateFormat.parse(createDatetime))
				.setRemovedFlag("Y")
				.setDisplayFlag("N")
				.update();

			}
			
			jsonObj.put("error", false);
			jsonObj.put("message", "Case summary event removed.");
				
			// cleanup
			mode = null;
			courtType = null;
			caseNum = null;
			locnCode = null;
			eventDatetime = null;
			createDatetime = null;

		} catch (Exception e) {
			jsonObj.put("error", true);
			jsonObj.put("message", e.getMessage());
			
			throw new ServletException(e.getMessage());	
		}
		
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.println(jsonObj.toString());
		out.flush();
		out.close();
		out = null;
	}
}
