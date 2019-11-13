package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.psrewarddefn.PsRewardDefnBO;
import gov.utcourts.problemsolving.dataaccess.psrewardleveldefn.PsRewardLevelDefnBO;
import gov.utcourts.problemsolving.dataaccess.pssanctionleveldefn.PsSanctionLevelDefnBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceRewardDefnServlet
*/
@WebServlet("/PSCMaintenanceRewardDefnServlet")
public class PSCMaintenanceRewardDefnServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceRewardDefnServlet() {
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
			int level = TextUtil.getParamAsInt(request, "level");
			int rewDefnId = TextUtil.getParamAsInt(request, "rewDefnId");
			String flag = TextUtil.getParamAsString(request, "flag");
			String mode = TextUtil.getParamAsString(request, "mode");
			String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
			String displayResults = TextUtil.getParamAsString(request, "displayResults");
			String displayHeader = TextUtil.getParamAsString(request, "displayHeader");
			List<PsRewardDefnBO> codesList;
			List<PsRewardLevelDefnBO> rewardLevelDefnList = new PsRewardLevelDefnBO().orderBy(PsRewardLevelDefnBO.REWARDLEVELSRL).search();
			request.setAttribute("rewardLevelDefnList", rewardLevelDefnList);
			
			//pass along a few things
			request.setAttribute("code", code);
			request.setAttribute("descr", descr);
			request.setAttribute("level", level);
			request.setAttribute("rewDefnId", rewDefnId);
			request.setAttribute("flag", flag);
			request.setAttribute("mode", mode);
			request.setAttribute("displayDisabled", displayDisabled);

			//do some insert and update work, if needed
			if("add".equals(mode) && (code != null && descr != null)) {
				PsRewardDefnBO psRewardDefnBO = new PsRewardDefnBO();
				psRewardDefnBO.setPsRewardCode(code);
				psRewardDefnBO.setPsRewardDescr(descr);
				psRewardDefnBO.setPsRewardLevel(level);
				psRewardDefnBO.setRemovedFlag("N");
				psRewardDefnBO.insert();
				psRewardDefnBO = null;
			} else if("edit".equals(mode) && (descr != null)) {
				PsRewardDefnBO psRewardDefnBO = new PsRewardDefnBO();
				psRewardDefnBO.where(PsRewardDefnBO.PSREWARDCODE, code);
				psRewardDefnBO.setPsRewardDescr(descr);
				psRewardDefnBO.setPsRewardLevel(level);
				psRewardDefnBO.update();
				psRewardDefnBO = null;
			} else if(("disable".equals(mode) || "enable".equals(mode)) && (!"".equals(code) && !"".equals(descr))) {
				PsRewardDefnBO psRewardDefnBO = new PsRewardDefnBO();
				psRewardDefnBO.where(PsRewardDefnBO.PSREWARDCODE, code);
				psRewardDefnBO.setRemovedFlag(flag);
				psRewardDefnBO.update();
				psRewardDefnBO = null;
			}
			
			//get the updated lists, if requested
			if("true".equals(displayResults)){
				if("true".equals(displayDisabled)) {
					codesList = new PsRewardDefnBO().orderBy(PsRewardDefnBO.PSREWARDCODE).search();
				}else{
					codesList = new PsRewardDefnBO().where(PsRewardDefnBO.REMOVEDFLAG, "N").orderBy(PsRewardDefnBO.PSREWARDCODE).search();
				}
				request.setAttribute("codesList", codesList);
			}
				
			//set the page
			String page = "/jsp/pscMaintenanceRewardDefn.jsp"; //default page
			if("editPopup".equals(mode) || "addPopup".equals(mode)){
				page = "/jsp/pscMaintenanceRewardDefnAddEdit.jsp"; //popcorn popup page to display the add or edit form
			}
			else if("true".equals(displayResults)){
				page = "/jsp/pscMaintenanceRewardDefnResults.jsp"; //display the results table in the div tag on the main page because this will be coming from an .ajax call
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
