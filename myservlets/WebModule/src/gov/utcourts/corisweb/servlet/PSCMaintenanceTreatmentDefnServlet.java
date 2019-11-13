package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO;
import gov.utcourts.problemsolving.dataaccess.pstreatmentdefn.PsTreatmentDefnBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceTreatmentDefnServlet
*/
@WebServlet("/PSCMaintenanceTreatmentDefnServlet")
public class PSCMaintenanceTreatmentDefnServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceTreatmentDefnServlet() {
      super();	
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//initialize some things (or set them to null if there isn't an incoming value)
			String code = TextUtil.getParamAsString(request, "code");
			String descr = TextUtil.getParamAsString(request, "descr");
			int treatDefnId = TextUtil.getParamAsInt(request, "treatDefnId");
			int courtId = TextUtil.getParamAsInt(request, "courtId");
			String flag = TextUtil.getParamAsString(request, "flag");
			String mode = TextUtil.getParamAsString(request, "mode");
			String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
			String displayResults = TextUtil.getParamAsString(request, "displayResults");
			String displayHeader = TextUtil.getParamAsString(request, "displayHeader");
			List<PsTreatmentDefnBO> codesList;
			List<PsCourtDefnBO> codesList2;
			
			//pass along a few things
			request.setAttribute("code", code);
			request.setAttribute("descr", descr);
			request.setAttribute("courtId", courtId);
			request.setAttribute("flag", flag);
			request.setAttribute("mode", mode);
			request.setAttribute("displayDisabled", displayDisabled);
			request.setAttribute("treatDefnId", treatDefnId);

			//do some insert and update work, if needed
			if("add".equals(mode) && (code != null || descr != null || courtId > 0)) {
				PsTreatmentDefnBO psTreatmentDefnBO = new PsTreatmentDefnBO();
				psTreatmentDefnBO.setPsTreatmentCode(code);
				psTreatmentDefnBO.setPsTreatmentDescr(descr);
				psTreatmentDefnBO.setPsCourtDefnId(courtId);
				psTreatmentDefnBO.setRemovedFlag("N");
				psTreatmentDefnBO.insert();
				psTreatmentDefnBO = null;
			} else if("edit".equals(mode) && (code != null || descr != null || courtId > 0)) {
				PsTreatmentDefnBO psTreatmentDefnBO = new PsTreatmentDefnBO();
				psTreatmentDefnBO.where(PsTreatmentDefnBO.PSTREATMENTDEFNID, treatDefnId);
				psTreatmentDefnBO.setPsTreatmentCode(code);
				psTreatmentDefnBO.setPsTreatmentDescr(descr);
				psTreatmentDefnBO.setPsCourtDefnId(courtId);
				psTreatmentDefnBO.update();
				psTreatmentDefnBO = null;
			} else if(("disable".equals(mode) || "enable".equals(mode)) && (!"".equals(code) && !"".equals(descr))) {
				PsTreatmentDefnBO psTreatmentDefnBO = new PsTreatmentDefnBO();
				psTreatmentDefnBO.where(PsTreatmentDefnBO.PSTREATMENTCODE, code);
				psTreatmentDefnBO.setRemovedFlag(flag);
				psTreatmentDefnBO.update();
				psTreatmentDefnBO = null;
			}
			
			//get the updated list, if requested
			if("true".equals(displayResults)){
				if("true".equals(displayDisabled)) {
					codesList = new PsTreatmentDefnBO().orderBy(PsTreatmentDefnBO.PSTREATMENTCODE).search();
					codesList2 = new PsCourtDefnBO().orderBy(PsCourtDefnBO.PSCODEDESCR).search();
				}else{
					codesList = new PsTreatmentDefnBO().where(PsTreatmentDefnBO.REMOVEDFLAG, "N").orderBy(PsTreatmentDefnBO.PSTREATMENTCODE).search();
					codesList2 = new PsCourtDefnBO().where(PsCourtDefnBO.REMOVEDFLAG, "N").orderBy(PsCourtDefnBO.PSCODEDESCR).search();
				}
				request.setAttribute("codesList", codesList);
				request.setAttribute("codesList2", codesList2);
			}
			if("edit".equals(mode) || "add".equals(mode)){
				codesList2 = new PsCourtDefnBO().orderBy(PsCourtDefnBO.PSCODEDESCR).search();
				request.setAttribute("codesList2", codesList2);
			}
				
			//set the page
			String page = "/jsp/pscMaintenanceTreatmentDefn.jsp"; //default page
			if("edit".equals(mode) || "add".equals(mode)){
				page = "/jsp/pscMaintenanceTreatmentDefnAddEdit.jsp"; //popcorn popup page to display the add or edit form
			}
			else if("true".equals(displayResults)){
				page = "/jsp/pscMaintenanceTreatmentDefnResults.jsp"; //display the results table in the div tag on the main page because this will be coming from an .ajax call
			}
			if("true".equals(displayHeader)){
				page = "/jsp/pscHeader.jsp"; //display the header and top nav bar
			}
			
			//display the selected page
			getServletContext().getRequestDispatcher(page).forward(request, response);
			
			//cleanup
			page = null;
			mode = null;
			code = null;
			descr = null;
			flag = null;
			displayDisabled = null;
			displayResults = null;
			codesList = null;

		} catch (Exception e){
			e.printStackTrace();
			String err = e.getMessage();
			if(e instanceof SQLException){
				if(err.contains("Unique constraint")){
					err = "Unique Constraint";
				}else {
					err = "Database error occurred.";
				}
			}
			throw new ServletException(err);
		}
	}
	
}
