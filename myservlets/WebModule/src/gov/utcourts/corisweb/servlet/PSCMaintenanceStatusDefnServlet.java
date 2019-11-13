package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceStatusDefnServlet
*/
@WebServlet("/PSCMaintenanceStatusDefnServlet")
public class PSCMaintenanceStatusDefnServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceStatusDefnServlet() {
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
					String page = "/jsp/pscMaintenanceStatusDefn.jsp";
					getServletContext().getRequestDispatcher(page).forward(request, response);
					break;
			}	
			mode = null;
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
	
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psStatusDefnId = TextUtil.getParamAsInt(request, "psStatusDefnId");
			String psStatusCode = TextUtil.getParamAsString(request, "psStatusCode");
			String psStatusDescr = TextUtil.getParamAsString(request, "psStatusDescr");
			String save = TextUtil.getParamAsString(request, "save");
			if(!TextUtil.isEmpty(psStatusCode) && !TextUtil.isEmpty(psStatusDescr) && "true".equals(save)) {
				//insert into the database
				PsStatusDefnBO psStatusDefnBO = new PsStatusDefnBO();
				psStatusDefnBO.where(PsStatusDefnBO.PSSTATUSDEFNID, psStatusDefnId);
				psStatusDefnBO.setPsStatusCode(psStatusCode);
				psStatusDefnBO.setPsStatusDescr(psStatusDescr);
				psStatusDefnBO.setRemovedFlag("N");
				psStatusDefnBO.insert();
				psStatusDefnBO = null;
			}else{
				//display the add popup
				request.setAttribute("mode", "add");
				String page = "/jsp/pscMaintenanceStatusDefnAddEdit.jsp";
				getServletContext().getRequestDispatcher(page).forward(request, response);
				page = null;
			}
			psStatusCode = null;
			psStatusDescr = null;
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
			int psStatusDefnId = TextUtil.getParamAsInt(request, "psStatusDefnId");
			String psStatusDescr = TextUtil.getParamAsString(request, "psStatusDescr");
			String update = TextUtil.getParamAsString(request, "update");
			if(psStatusDefnId > 0 && !TextUtil.isEmpty(psStatusDescr) && "true".equals(update)) {
				//update the database
				PsStatusDefnBO psStatusDefnBO = new PsStatusDefnBO();
				psStatusDefnBO.where(PsStatusDefnBO.PSSTATUSDEFNID, psStatusDefnId);
				psStatusDefnBO.setPsStatusDescr(psStatusDescr);
				psStatusDefnBO.update();
				psStatusDefnBO = null;
			}else{
				//display the edit popup
				request.setAttribute("mode", "edit");
				String page = "/jsp/pscMaintenanceStatusDefnAddEdit.jsp";
				getServletContext().getRequestDispatcher(page).forward(request, response);
				page = null;
			}
			psStatusDescr = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void setRemovedFlag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psStatusDefnId = TextUtil.getParamAsInt(request, "psStatusDefnId");
			String psStatusRemovedFlag = TextUtil.getParamAsString(request, "psStatusRemovedFlag");
			if(psStatusDefnId > 0) {
				PsStatusDefnBO psStatusDefnBO = new PsStatusDefnBO();
				psStatusDefnBO.where(PsStatusDefnBO.PSSTATUSDEFNID, psStatusDefnId);
				psStatusDefnBO.setRemovedFlag(psStatusRemovedFlag);
				psStatusDefnBO.update();
				psStatusDefnBO = null;
			}
			displayResults(request, response);
			psStatusRemovedFlag = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void displayResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean displayDisabled = TextUtil.getParamAsBoolean(request, "displayDisabled");
			List<PsStatusDefnBO> codesList;
			//get the updated list
			if(displayDisabled) {
				codesList = new PsStatusDefnBO()
						.orderBy(PsStatusDefnBO.PSSTATUSCODE)
						.search(PsStatusDefnBO.PSSTATUSDEFNID, PsStatusDefnBO.PSSTATUSCODE, PsStatusDefnBO.PSSTATUSDESCR, PsStatusDefnBO.REMOVEDFLAG);
			}else{
				codesList = new PsStatusDefnBO()
						.where(PsStatusDefnBO.REMOVEDFLAG, "N")
						.orderBy(PsStatusDefnBO.PSSTATUSCODE)
						.search(PsStatusDefnBO.PSSTATUSDEFNID, PsStatusDefnBO.PSSTATUSCODE, PsStatusDefnBO.PSSTATUSDESCR, PsStatusDefnBO.REMOVEDFLAG);
			}
			request.setAttribute("codesList", codesList);
			String page = "/jsp/pscMaintenanceStatusDefnResults.jsp";
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
