package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO;
import gov.utcourts.problemsolving.dataaccess.psappearancedefn.PsAppearanceDefnBO;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceAppearanceDefnServlet
*/
@WebServlet("/PSCMaintenanceAppearanceDefnServlet")
public class PSCMaintenanceAppearanceDefnServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceAppearanceDefnServlet() {
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
			int appearDefnId = TextUtil.getParamAsInt(request, "appearDefnId");
			int actionId = TextUtil.getParamAsInt(request, "actionId");
			String flag = TextUtil.getParamAsString(request, "flag");
			String minText = TextUtil.getParamAsString(request, "minText");
			String mode = TextUtil.getParamAsString(request, "mode");
			String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
			String displayResults = TextUtil.getParamAsString(request, "displayResults");
			String displayHeader = TextUtil.getParamAsString(request, "displayHeader");
			List<PsAppearanceDefnBO> codesList;
			
			//pass along a few things
			request.setAttribute("code", code);
			request.setAttribute("descr", descr);
			request.setAttribute("actionId", actionId);
			request.setAttribute("flag", flag);
			request.setAttribute("mode", mode);
			request.setAttribute("minText", minText);
			request.setAttribute("displayDisabled", displayDisabled);
			request.setAttribute("appearDefnId", appearDefnId);

			//do some insert and update work, if needed
			if("add".equals(mode) && (code != null || descr != null || actionId > 0)) {
				PsAppearanceDefnBO psAppearanceDefnBO = new PsAppearanceDefnBO();
				psAppearanceDefnBO.setPsAppearanceCode(code);
				psAppearanceDefnBO.setPsAppearanceDescr(descr);
				psAppearanceDefnBO.setPsActionDefnId(actionId);
				psAppearanceDefnBO.setPsMinutesText(minText);
				psAppearanceDefnBO.setRemovedFlag("N");
				psAppearanceDefnBO.insert();
				psAppearanceDefnBO = null;
			} else if("edit".equals(mode) && (code != null || descr != null || actionId > 0)) {
				PsAppearanceDefnBO psAppearanceDefnBO = new PsAppearanceDefnBO();
				psAppearanceDefnBO.where(PsAppearanceDefnBO.PSAPPEARANCEDEFNID, appearDefnId);
				psAppearanceDefnBO.setPsAppearanceCode(code);
				psAppearanceDefnBO.setPsAppearanceDescr(descr);
				psAppearanceDefnBO.setPsActionDefnId(actionId);
				psAppearanceDefnBO.setPsMinutesText(minText);
				psAppearanceDefnBO.update();
				psAppearanceDefnBO = null;
			} else if(("disable".equals(mode) || "enable".equals(mode)) && (!"".equals(code) && !"".equals(descr))) {
				PsAppearanceDefnBO psAppearanceDefnBO = new PsAppearanceDefnBO();
				psAppearanceDefnBO.where(PsAppearanceDefnBO.PSAPPEARANCECODE, code);
				psAppearanceDefnBO.setRemovedFlag(flag);
				psAppearanceDefnBO.update();
				psAppearanceDefnBO = null;
			}
			
			//get the updated list, if requested
			if("true".equals(displayResults)){
				if("true".equals(displayDisabled)) {
					codesList = new PsAppearanceDefnBO().orderBy(PsAppearanceDefnBO.PSAPPEARANCECODE).search();
				}else{
					codesList = new PsAppearanceDefnBO().where(PsAppearanceDefnBO.REMOVEDFLAG, "N").orderBy(PsAppearanceDefnBO.PSAPPEARANCECODE).search();
				}
				request.setAttribute("codesList", codesList);
			}
				
			//set the page
			String page = "/jsp/pscMaintenanceAppearanceDefn.jsp"; //default page
			if("edit".equals(mode) || "add".equals(mode)){
				page = "/jsp/pscMaintenanceAppearanceDefnAddEdit.jsp"; //popcorn popup page to display the add or edit form
			}
			else if("true".equals(displayResults)){
				page = "/jsp/pscMaintenanceAppearanceDefnResults.jsp"; //display the results table in the div tag on the main page because this will be coming from an .ajax call
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
		}
	}
	
}
