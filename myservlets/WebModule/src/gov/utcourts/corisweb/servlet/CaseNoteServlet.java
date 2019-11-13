package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.util.CharacterFilter;
import gov.utcourts.courtscommon.constants.BaseConstants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.ws.portletcontainer.util.URLEncoder;

/**
* Servlet implementation class CaseNoteServlet
*/
@WebServlet("/CaseNoteServlet")
public class CaseNoteServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -4215111253L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public CaseNoteServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String courtType = TextUtil.getParamAsString(request, "courtType");
			String caseNum = TextUtil.getParamAsString(request, "caseNum");
			String locnCode = TextUtil.getParamAsString(request, "locnCode");
			String caseNote = TextUtil.getParamAsString(request, "caseNote");
			int intCaseNum = new KaseBO(courtType).where(KaseBO.CASENUM, caseNum).where(KaseBO.LOCNCODE, locnCode).find(KaseBO.INTCASENUM).getIntCaseNum();

			// filter for bad input
			caseNote = CharacterFilter.filter(caseNote);
			
			String mode = TextUtil.getParamAsString(request, "mode");
			if ("update".equals(mode)) {
				new KaseBO(courtType).where(KaseBO.INTCASENUM, intCaseNum).setNote(caseNote).update();
			} else if ("edit".equals(mode)) {
				caseNote = new KaseBO(courtType).where(KaseBO.INTCASENUM, intCaseNum).find(KaseBO.NOTE).getNote();
			} else if ("delete".equals(mode)) {
				new KaseBO(courtType).where(KaseBO.INTCASENUM, intCaseNum).setNote("").update();   // blank out the note and save it
			}
			
			// set the page
			String page = "/jsp/caseNotePopup.jsp?caseNote=" + URLEncoder.encode(caseNote, "UTF-8"); // popcorn popup page to display the add or edit form
			
			// display the selected page
			getServletContext().getRequestDispatcher(page).forward(request, response);
			
			// cleanup
			page = null;
			mode = null;
			courtType = null;
			caseNum = null;
			locnCode = null;

		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
