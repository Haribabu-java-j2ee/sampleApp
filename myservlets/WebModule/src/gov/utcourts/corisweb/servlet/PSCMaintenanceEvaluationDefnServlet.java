package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO;
import gov.utcourts.problemsolving.dataaccess.psevaluationdefn.PsEvaluationDefnBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceEvaluationDefnServlet
*/
@WebServlet("/PSCMaintenanceEvaluationDefnServlet")
public class PSCMaintenanceEvaluationDefnServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceEvaluationDefnServlet() {
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
			int evalDefnId = TextUtil.getParamAsInt(request, "evalDefnId");
			int courtId = TextUtil.getParamAsInt(request, "courtId");
			String flag = TextUtil.getParamAsString(request, "flag");
			String mode = TextUtil.getParamAsString(request, "mode");
			String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
			String displayResults = TextUtil.getParamAsString(request, "displayResults");
			String displayHeader = TextUtil.getParamAsString(request, "displayHeader");
			List<PsEvaluationDefnBO> codesList;
			List<PsCourtDefnBO> codesList2;
			
			//pass along a few things
			request.setAttribute("code", code);
			request.setAttribute("descr", descr);
			request.setAttribute("courtId", courtId);
			request.setAttribute("evalDefnId", evalDefnId);
			request.setAttribute("flag", flag);
			request.setAttribute("mode", mode);
			request.setAttribute("displayDisabled", displayDisabled);
			request.setAttribute("evalDefnId", evalDefnId);

			//do some insert and update work, if needed
			if("add".equals(mode) && (code != null || descr != null || courtId > 0)) {
				PsEvaluationDefnBO psEvaluationDefnBO = new PsEvaluationDefnBO();
				psEvaluationDefnBO.setPsEvaluationCode(code);
				psEvaluationDefnBO.setPsEvaluationDescr(descr);
				psEvaluationDefnBO.setPsCourtDefnId(courtId);
				psEvaluationDefnBO.setRemovedFlag("N");
				psEvaluationDefnBO.insert();
				psEvaluationDefnBO = null;
			} else if("edit".equals(mode) && (code != null || descr != null || courtId > 0)) {
				PsEvaluationDefnBO psEvaluationDefnBO = new PsEvaluationDefnBO();
				psEvaluationDefnBO.where(PsEvaluationDefnBO.PSEVALUATIONDEFNID, evalDefnId);
				psEvaluationDefnBO.setPsEvaluationCode(code);
				psEvaluationDefnBO.setPsEvaluationDescr(descr);
				psEvaluationDefnBO.setPsCourtDefnId(courtId);
				psEvaluationDefnBO.update();
				psEvaluationDefnBO = null;
			} else if(("disable".equals(mode) || "enable".equals(mode)) && (!"".equals(code) && !"".equals(descr))) {
				PsEvaluationDefnBO psEvaluationDefnBO = new PsEvaluationDefnBO();
				psEvaluationDefnBO.where(PsEvaluationDefnBO.PSEVALUATIONCODE, code);
				psEvaluationDefnBO.setRemovedFlag(flag);
				psEvaluationDefnBO.update();
				psEvaluationDefnBO = null;
			}
			
			//get the updated list, if requested
			if("true".equals(displayResults)){
				if("true".equals(displayDisabled)) {
					codesList = new PsEvaluationDefnBO().orderBy(PsEvaluationDefnBO.PSEVALUATIONCODE).search();
					codesList2 = new PsCourtDefnBO().orderBy(PsCourtDefnBO.PSCODEDESCR).search();
				}else{
					codesList = new PsEvaluationDefnBO().where(PsEvaluationDefnBO.REMOVEDFLAG, "N").orderBy(PsEvaluationDefnBO.PSEVALUATIONCODE).search();
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
			String page = "/jsp/pscMaintenanceEvaluationDefn.jsp"; //default page
			if("edit".equals(mode) || "add".equals(mode)){
				page = "/jsp/pscMaintenanceEvaluationDefnAddEdit.jsp"; //popcorn popup page to display the add or edit form
			}
			else if("true".equals(displayResults)){
				page = "/jsp/pscMaintenanceEvaluationDefnResults.jsp"; //display the results table in the div tag on the main page because this will be coming from an .ajax call
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
