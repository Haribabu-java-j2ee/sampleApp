package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO;
import gov.utcourts.problemsolving.dataaccess.psgoaldefn.PsGoalDefnBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceGoalDefnServlet
*/
@WebServlet("/PSCMaintenanceGoalDefnServlet")
public class PSCMaintenanceGoalDefnServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceGoalDefnServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
			switch (mode) {
				case ADD:
					add(request, response);
					break;
				case EDIT:
					edit(request, response);
					break;
				case DISABLE:
					setRemovedFlag(request, response);
					break;
				case ENABLE:
					setRemovedFlag(request, response);
					break;
				case DISPLAYHEADER:
					displayHeader(request, response);
					break;
				case DISPLAYRESULTS:
					displayResults(request, response);
					break;
				default:
					String page = "/jsp/pscMaintenanceGoalDefn.jsp";
					getServletContext().getRequestDispatcher(page).forward(request, response);
					break;
			}	
			mode = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psGoalDefnId = TextUtil.getParamAsInt(request, "psGoalDefnId");
			String psGoalCode = TextUtil.getParamAsString(request, "psGoalCode");
			String psGoalDescr = TextUtil.getParamAsString(request, "psGoalDescr");
			int psCourtDefnId = TextUtil.getParamAsInt(request, "psCourtDefnId");
			String save = TextUtil.getParamAsString(request, "save");
			List<PsCourtDefnBO> courtCodesList;
			if(!TextUtil.isEmpty(psGoalCode) && !TextUtil.isEmpty(psGoalDescr) && "true".equals(save)) {
				//insert into the database
				PsGoalDefnBO psGoalDefnBO = new PsGoalDefnBO();
				psGoalDefnBO.where(PsGoalDefnBO.PSGOALDEFNID, psGoalDefnId);
				psGoalDefnBO.setPsGoalCode(psGoalCode);
				psGoalDefnBO.setPsGoalDescr(psGoalDescr);
				psGoalDefnBO.setRemovedFlag("N");
				psGoalDefnBO.setPsCourtDefnId(psCourtDefnId);
				psGoalDefnBO.insert();
				psGoalDefnBO = null;
			}else{
				//get the list of court codes and display the add popup
				courtCodesList = new PsCourtDefnBO().orderBy(PsCourtDefnBO.PSCOURTCODE).search();
				request.setAttribute("mode", "add");
				request.setAttribute("courtCodesList", courtCodesList);
				String page = "/jsp/pscMaintenanceGoalDefnAddEdit.jsp";
				getServletContext().getRequestDispatcher(page).forward(request, response);
				page = null;
			}
			psGoalCode = null;
			psGoalDescr = null;
			courtCodesList = null;
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
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psGoalDefnId = TextUtil.getParamAsInt(request, "psGoalDefnId");
			String psGoalDescr = TextUtil.getParamAsString(request, "psGoalDescr");
			int psCourtDefnId = TextUtil.getParamAsInt(request, "psCourtDefnId");
			String update = TextUtil.getParamAsString(request, "update");
			List<PsCourtDefnBO> courtCodesList;
			if(psGoalDefnId > 0 && !TextUtil.isEmpty(psGoalDescr) && "true".equals(update)) {
				//update the database
				PsGoalDefnBO psGoalDefnBO = new PsGoalDefnBO();
				psGoalDefnBO.where(PsGoalDefnBO.PSGOALDEFNID, psGoalDefnId);
				psGoalDefnBO.setPsGoalDescr(psGoalDescr);
				if(psCourtDefnId > 0){
					psGoalDefnBO.setPsCourtDefnId(psCourtDefnId);
				}else{
					psGoalDefnBO.setAsNull(PsGoalDefnBO.PSCOURTDEFNID);
				}
				psGoalDefnBO.update();
				psGoalDefnBO = null;
			}else{
				//get the list of court codes and display the edit popup
				courtCodesList = new PsCourtDefnBO().orderBy(PsCourtDefnBO.PSCOURTCODE).search();
				request.setAttribute("mode", "edit");
				request.setAttribute("courtCodesList", courtCodesList);
				String page = "/jsp/pscMaintenanceGoalDefnAddEdit.jsp";
				getServletContext().getRequestDispatcher(page).forward(request, response);
				page = null;
			}
			psGoalDescr = null;
			courtCodesList = null;
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
	
	public void setRemovedFlag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psGoalDefnId = TextUtil.getParamAsInt(request, "psGoalDefnId");
			String psGoalRemovedFlag = TextUtil.getParamAsString(request, "psGoalRemovedFlag");
			if(psGoalDefnId > 0) {
				PsGoalDefnBO psGoalDefnBO = new PsGoalDefnBO();
				psGoalDefnBO.where(PsGoalDefnBO.PSGOALDEFNID, psGoalDefnId);
				psGoalDefnBO.setRemovedFlag(psGoalRemovedFlag);
				psGoalDefnBO.update();
				psGoalDefnBO = null;
			}
			displayResults(request, response);
			psGoalRemovedFlag = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void displayResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean displayDisabled = TextUtil.getParamAsBoolean(request, "displayDisabled");
			List<PsGoalDefnBO> codesList;
			//get the updated list
			if(displayDisabled) {
				codesList = new PsGoalDefnBO()
						.includeTables(new PsCourtDefnBO().setOuter())
						.addForeignKey(PsCourtDefnBO.PSCOURTDEFNID, PsGoalDefnBO.PSCOURTDEFNID)
						.orderBy(PsGoalDefnBO.PSGOALCODE)
						.search(PsGoalDefnBO.PSGOALDEFNID, PsGoalDefnBO.PSGOALCODE, PsGoalDefnBO.PSGOALDESCR, PsGoalDefnBO.REMOVEDFLAG, PsGoalDefnBO.PSCOURTDEFNID, PsCourtDefnBO.PSCOURTCODE);
			}else{
				codesList = new PsGoalDefnBO()
						.where(PsGoalDefnBO.REMOVEDFLAG, "N")
						.includeTables(new PsCourtDefnBO().setOuter())
						.addForeignKey(PsCourtDefnBO.PSCOURTDEFNID, PsGoalDefnBO.PSCOURTDEFNID)
						.orderBy(PsGoalDefnBO.PSGOALCODE)
						.search(PsGoalDefnBO.PSGOALDEFNID, PsGoalDefnBO.PSGOALCODE, PsGoalDefnBO.PSGOALDESCR, PsGoalDefnBO.REMOVEDFLAG, PsGoalDefnBO.PSCOURTDEFNID, PsCourtDefnBO.PSCOURTCODE);
			}
			request.setAttribute("codesList", codesList);
			String page = "/jsp/pscMaintenanceGoalDefnResults.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			codesList = null;
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void displayHeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String page = "/jsp/pscHeader.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	

	
}
