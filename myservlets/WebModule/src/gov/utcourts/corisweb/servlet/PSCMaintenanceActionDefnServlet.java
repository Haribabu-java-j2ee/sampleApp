package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO;
import gov.utcourts.problemsolving.dataaccess.psstatusactionxref.PsStatusActionXrefBO;
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
* Servlet implementation class PSCMaintenanceActionDefnServlet
*/
@WebServlet("/PSCMaintenanceActionDefnServlet")
public class PSCMaintenanceActionDefnServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceActionDefnServlet() {
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
					String page = "/jsp/pscMaintenanceActionDefn.jsp";
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
			int psActionDefnId = TextUtil.getParamAsInt(request, "psActionDefnId");
			String psActionCode = TextUtil.getParamAsString(request, "psActionCode");
			String psActionDescr = TextUtil.getParamAsString(request, "psActionDescr");
			int defaultPsStatusDefnId = TextUtil.getParamAsInt(request, "psStatusDefnId");
			String save = TextUtil.getParamAsString(request, "save");
			List<PsStatusDefnBO> statusCodesList;
			if(!TextUtil.isEmpty(psActionCode) && !TextUtil.isEmpty(psActionDescr) && "true".equals(save)) {
				//insert into the database
				PsActionDefnBO psActionDefnBO = new PsActionDefnBO();
				psActionDefnBO.where(PsActionDefnBO.PSACTIONDEFNID, psActionDefnId);
				psActionDefnBO.setPsActionCode(psActionCode);
				psActionDefnBO.setPsActionDescr(psActionDescr);
				psActionDefnBO.setRemovedFlag("N");
				psActionDefnBO.setPsActionType("Y");
				psActionDefnBO.setDefaultPsStatusDefnId(defaultPsStatusDefnId);
				PsActionDefnBO actBO = (PsActionDefnBO)psActionDefnBO.insert();
				if(defaultPsStatusDefnId > 0){
					new PsStatusActionXrefBO().setPsActionDefnId(actBO.getPsActionDefnId()).setPsStatusDefnId(defaultPsStatusDefnId).insert();
				}
				psActionDefnBO = null;
			}else{
				//get the list of status codes and display the add popup
				statusCodesList = new PsStatusDefnBO().orderBy(PsStatusDefnBO.PSSTATUSCODE).search();
				request.setAttribute("mode", "add");
				request.setAttribute("statusCodesList", statusCodesList);
				String page = "/jsp/pscMaintenanceActionDefnAddEdit.jsp";
				getServletContext().getRequestDispatcher(page).forward(request, response);
				page = null;
			}
			psActionCode = null;
			psActionDescr = null;
			statusCodesList = null;
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
	
	private void addToActionStatusXref(int psActionDefnId, int defaultPsStatusDefnId) throws Exception {
		PsStatusActionXrefBO xvo = (PsStatusActionXrefBO) new PsStatusActionXrefBO().where(PsStatusActionXrefBO.PSACTIONDEFNID,psActionDefnId)
															.where(PsStatusActionXrefBO.PSSTATUSDEFNID, defaultPsStatusDefnId).find();
		if(xvo == null){
			new PsStatusActionXrefBO().setPsActionDefnId(psActionDefnId)
									  .setPsStatusDefnId(defaultPsStatusDefnId).insert();
		}
		
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psActionDefnId = TextUtil.getParamAsInt(request, "psActionDefnId");
			String psActionDescr = TextUtil.getParamAsString(request, "psActionDescr");
			int defaultPsStatusDefnId = TextUtil.getParamAsInt(request, "psStatusDefnId");
			String update = TextUtil.getParamAsString(request, "update");
			List<PsStatusDefnBO> statusCodesList;
			if(psActionDefnId > 0 && !TextUtil.isEmpty(psActionDescr) && "true".equals(update)) {
				//update the database
				PsActionDefnBO psActionDefnBO = new PsActionDefnBO();
				psActionDefnBO.where(PsActionDefnBO.PSACTIONDEFNID, psActionDefnId);
				psActionDefnBO.setPsActionDescr(psActionDescr);
				if(defaultPsStatusDefnId > 0){
					psActionDefnBO.setDefaultPsStatusDefnId(defaultPsStatusDefnId);
					addToActionStatusXref(psActionDefnId, defaultPsStatusDefnId);
				}else{
					psActionDefnBO.setAsNull(PsActionDefnBO.DEFAULTPSSTATUSDEFNID);
				}
				psActionDefnBO.update();
				
				psActionDefnBO = null;
			}else{
				//get the list of status codes and display the edit popup
				statusCodesList = new PsStatusDefnBO().orderBy(PsStatusDefnBO.PSSTATUSCODE).search();
				request.setAttribute("mode", "edit");
				request.setAttribute("statusCodesList", statusCodesList);
				String page = "/jsp/pscMaintenanceActionDefnAddEdit.jsp";
				getServletContext().getRequestDispatcher(page).forward(request, response);
				page = null;
			}
			psActionDescr = null;
			statusCodesList = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void setRemovedFlag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psActionDefnId = TextUtil.getParamAsInt(request, "psActionDefnId");
			String psActionRemovedFlag = TextUtil.getParamAsString(request, "psActionRemovedFlag");
			if(psActionDefnId > 0) {
				PsActionDefnBO psActionDefnBO = new PsActionDefnBO();
				psActionDefnBO.where(PsActionDefnBO.PSACTIONDEFNID, psActionDefnId);
				psActionDefnBO.setRemovedFlag(psActionRemovedFlag);
				psActionDefnBO.update();
				psActionDefnBO = null;
			}
			displayResults(request, response);
			psActionRemovedFlag = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void displayResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean displayDisabled = TextUtil.getParamAsBoolean(request, "displayDisabled");
			List<PsActionDefnBO> codesList;
			//get the updated list
			if(displayDisabled) {
				codesList = new PsActionDefnBO()
						.includeTables(new PsStatusDefnBO().setOuter())
						.addForeignKey(PsStatusDefnBO.PSSTATUSDEFNID, PsActionDefnBO.DEFAULTPSSTATUSDEFNID)
						.orderBy(PsActionDefnBO.PSACTIONCODE)
						.search(PsActionDefnBO.PSACTIONDEFNID, PsActionDefnBO.PSACTIONCODE, PsActionDefnBO.PSACTIONDESCR, PsActionDefnBO.REMOVEDFLAG, PsActionDefnBO.DEFAULTPSSTATUSDEFNID, PsStatusDefnBO.PSSTATUSCODE);
			}else{
				codesList = new PsActionDefnBO()
						.where(PsActionDefnBO.REMOVEDFLAG, "N")
						.includeTables(new PsStatusDefnBO().setOuter())
						.addForeignKey(PsStatusDefnBO.PSSTATUSDEFNID, PsActionDefnBO.DEFAULTPSSTATUSDEFNID)
						.orderBy(PsActionDefnBO.PSACTIONCODE)
						.search(PsActionDefnBO.PSACTIONDEFNID, PsActionDefnBO.PSACTIONCODE, PsActionDefnBO.PSACTIONDESCR, PsActionDefnBO.REMOVEDFLAG, PsActionDefnBO.DEFAULTPSSTATUSDEFNID, PsStatusDefnBO.PSSTATUSCODE);
			}
			request.setAttribute("codesList", codesList);
			String page = "/jsp/pscMaintenanceActionDefnResults.jsp";
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
