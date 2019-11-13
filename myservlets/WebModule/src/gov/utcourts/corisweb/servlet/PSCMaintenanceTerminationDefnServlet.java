package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.psterminationdefn.PsTerminationDefnBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceTerminationDefnServlet
*/
@WebServlet("/PSCMaintenanceTerminationDefnServlet")
public class PSCMaintenanceTerminationDefnServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceTerminationDefnServlet() {
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
					String page = "/jsp/pscMaintenanceTerminationDefn.jsp";
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
			int psTerminationDefnId = TextUtil.getParamAsInt(request, "psTerminationDefnId");
			String psTerminationCode = TextUtil.getParamAsString(request, "psTerminationCode");
			String psTerminationDescr = TextUtil.getParamAsString(request, "psTerminationDescr");
			String save = TextUtil.getParamAsString(request, "save");
			if(!TextUtil.isEmpty(psTerminationCode) && !TextUtil.isEmpty(psTerminationDescr) && "true".equals(save)) {
				//insert into the database
				PsTerminationDefnBO psTerminationDefnBO = new PsTerminationDefnBO();
				psTerminationDefnBO.where(PsTerminationDefnBO.PSTERMINATIONDEFNID, psTerminationDefnId);
				psTerminationDefnBO.setPsTerminationCode(psTerminationCode);
				psTerminationDefnBO.setPsTerminationDescr(psTerminationDescr);
				psTerminationDefnBO.setRemovedFlag("N");
				psTerminationDefnBO.insert();
				psTerminationDefnBO = null;
			}else{
				//display the add popup
				request.setAttribute("mode", "add");
				String page = "/jsp/pscMaintenanceTerminationDefnAddEdit.jsp";
				getServletContext().getRequestDispatcher(page).forward(request, response);
				page = null;
			}
			psTerminationCode = null;
			psTerminationDescr = null;
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
			int psTerminationDefnId = TextUtil.getParamAsInt(request, "psTerminationDefnId");
			String psTerminationDescr = TextUtil.getParamAsString(request, "psTerminationDescr");
			String update = TextUtil.getParamAsString(request, "update");
			if(psTerminationDefnId > 0 && !TextUtil.isEmpty(psTerminationDescr) && "true".equals(update)) {
				//update the database
				PsTerminationDefnBO psTerminationDefnBO = new PsTerminationDefnBO();
				psTerminationDefnBO.where(PsTerminationDefnBO.PSTERMINATIONDEFNID, psTerminationDefnId);
				psTerminationDefnBO.setPsTerminationDescr(psTerminationDescr);
				psTerminationDefnBO.update();
				psTerminationDefnBO = null;
			}else{
				//display the edit popup
				request.setAttribute("mode", "edit");
				String page = "/jsp/pscMaintenanceTerminationDefnAddEdit.jsp";
				getServletContext().getRequestDispatcher(page).forward(request, response);
				page = null;
			}
			psTerminationDescr = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void setRemovedFlag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psTerminationDefnId = TextUtil.getParamAsInt(request, "psTerminationDefnId");
			String psTerminationRemovedFlag = TextUtil.getParamAsString(request, "psTerminationRemovedFlag");
			if(psTerminationDefnId > 0) {
				PsTerminationDefnBO psTerminationDefnBO = new PsTerminationDefnBO();
				psTerminationDefnBO.where(PsTerminationDefnBO.PSTERMINATIONDEFNID, psTerminationDefnId);
				psTerminationDefnBO.setRemovedFlag(psTerminationRemovedFlag);
				psTerminationDefnBO.update();
				psTerminationDefnBO = null;
			}
			displayResults(request, response);
			psTerminationRemovedFlag = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void displayResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean displayDisabled = TextUtil.getParamAsBoolean(request, "displayDisabled");
			List<PsTerminationDefnBO> codesList;
			//get the updated list
			if(displayDisabled) {
				codesList = new PsTerminationDefnBO()
						.orderBy(PsTerminationDefnBO.PSTERMINATIONCODE)
						.search(PsTerminationDefnBO.PSTERMINATIONDEFNID, PsTerminationDefnBO.PSTERMINATIONCODE, PsTerminationDefnBO.PSTERMINATIONDESCR, PsTerminationDefnBO.REMOVEDFLAG);
			}else{
				codesList = new PsTerminationDefnBO()
						.where(PsTerminationDefnBO.REMOVEDFLAG, "N")
						.orderBy(PsTerminationDefnBO.PSTERMINATIONCODE)
						.search(PsTerminationDefnBO.PSTERMINATIONDEFNID, PsTerminationDefnBO.PSTERMINATIONCODE, PsTerminationDefnBO.PSTERMINATIONDESCR, PsTerminationDefnBO.REMOVEDFLAG);
			}
			request.setAttribute("codesList", codesList);
			String page = "/jsp/pscMaintenanceTerminationDefnResults.jsp";
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
