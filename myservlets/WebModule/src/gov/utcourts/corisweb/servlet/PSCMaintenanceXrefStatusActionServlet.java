package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.ArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.IntegerArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO;
import gov.utcourts.problemsolving.dataaccess.psstatusactionxref.PsStatusActionXrefBO;
import gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceXrefStatusActionServlet
*/
@WebServlet("/PSCMaintenanceXrefStatusActionServlet")
public class PSCMaintenanceXrefStatusActionServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceXrefStatusActionServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
			switch (mode) {
				case SAVE:
					save(request, response);
					break;
				case EDIT:
					edit(request, response);
					break;
				case DISPLAYHEADER:
					displayHeader(request, response);
					break;
				case DISPLAYRESULTS:
					displayResults(request, response);
					break;
				default:
					String page = "/jsp/pscMaintenanceXrefStatusAction.jsp";
					getServletContext().getRequestDispatcher(page).forward(request, response);
					break;
			}	
			mode = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			int psStatusDefnId = TextUtil.getParamAsInt(request, "psStatusDefnId");
			int psActionDefnId = 0;
			List<PsActionDefnBO> actionDefnList = new PsActionDefnBO().orderBy(PsActionDefnBO.PSACTIONDEFNID).search();
			ArrayList<Integer> defaultStatusAction = new ArrayList<Integer>();
			for(PsActionDefnBO actionBO : actionDefnList) {
				if(actionBO.getDefaultPsStatusDefnId()==psStatusDefnId){
					defaultStatusAction.add(actionBO.getPsActionDefnId());
				}
			}
			String checkedValue = "";
			
			//delete all records for this particular psStatusDefnId
			if(psStatusDefnId > 0) {
				PsStatusActionXrefBO psStatusActionXrefBO = new PsStatusActionXrefBO();
				psStatusActionXrefBO.where(PsStatusActionXrefBO.PSSTATUSDEFNID, psStatusDefnId)
									.where(PsStatusActionXrefBO.PSACTIONDEFNID, Exp.NOT_IN, new IntegerArrayDescriptor(defaultStatusAction));
				psStatusActionXrefBO.delete();
				psStatusActionXrefBO = null;
			}
			
			//insert the checked boxes
			for(PsActionDefnBO actionListBO : actionDefnList) {
				psActionDefnId = actionListBO.getPsActionDefnId();
				checkedValue = TextUtil.getParamAsString(request, "action-"+psStatusDefnId+"-"+psActionDefnId);
				if("on".equals(checkedValue)){
					if(psStatusDefnId > 0 && psActionDefnId > 0) {
						PsStatusActionXrefBO psStatusActionXrefBO = new PsStatusActionXrefBO();
						psStatusActionXrefBO.setPsStatusActionId(0);
						psStatusActionXrefBO.setPsActionDefnId(psActionDefnId);
						psStatusActionXrefBO.setPsStatusDefnId(psStatusDefnId);
						psStatusActionXrefBO.insert();
						psStatusActionXrefBO = null;
					}
				}
			}
			
			checkedValue = null;
			actionDefnList = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getLists(request, response);
			//display the edit popup
			String page = "/jsp/pscMaintenanceXrefStatusActionAddEdit.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void getLists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psStatusDefnId = TextUtil.getParamAsInt(request, "psStatusDefnId");
			List<PsStatusActionXrefBO> xrefList = new ArrayList<PsStatusActionXrefBO>();
			List<PsActionDefnBO> actionDefnList;
			List<PsStatusDefnBO> statusDefnList = new ArrayList<PsStatusDefnBO>();
			
			//get the xref list
			PsStatusActionXrefBO xrefListBO = new PsStatusActionXrefBO();
			xrefListBO.includeTables(new PsActionDefnBO().setOuter().orderBy(PsActionDefnBO.PSACTIONCODE));
			xrefListBO.addForeignKey(PsActionDefnBO.PSACTIONDEFNID, PsStatusActionXrefBO.PSACTIONDEFNID);
			xrefListBO.includeTables(new PsStatusDefnBO().setOuter().orderBy(PsStatusDefnBO.PSSTATUSCODE));
			xrefListBO.addForeignKey(PsStatusDefnBO.PSSTATUSDEFNID, PsStatusActionXrefBO.PSSTATUSDEFNID);
			if(psStatusDefnId > 0) {
				xrefListBO.where(PsStatusActionXrefBO.PSSTATUSDEFNID, psStatusDefnId);
			}
			xrefList = xrefListBO.search(PsStatusActionXrefBO.PSSTATUSACTIONID
					, PsStatusActionXrefBO.PSSTATUSDEFNID
					, PsStatusActionXrefBO.PSACTIONDEFNID
					, PsStatusDefnBO.PSSTATUSDEFNID
					, PsStatusDefnBO.PSSTATUSCODE
					, PsActionDefnBO.REMOVEDFLAG
					, PsActionDefnBO.PSACTIONCODE
					, PsActionDefnBO.PSACTIONDESCR);
			
			//get the action codes list
			actionDefnList = new PsActionDefnBO().orderBy(PsActionDefnBO.PSACTIONCODE).search();
			
			//get the list of status types
			PsStatusDefnBO statusDefnListBO = new PsStatusDefnBO();
			statusDefnListBO.orderBy(PsStatusDefnBO.PSSTATUSCODE);
			if(psStatusDefnId > 0) {
				statusDefnListBO.where(PsStatusDefnBO.PSSTATUSDEFNID, psStatusDefnId);
			}
			statusDefnList = statusDefnListBO.search();

			request.setAttribute("xrefList", xrefList);
			request.setAttribute("actionDefnList", actionDefnList);
			request.setAttribute("statusDefnList", statusDefnList);
			
			xrefList = null;
			xrefListBO = null;
			actionDefnList = null;
			statusDefnList = null;
			statusDefnListBO = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void displayResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getLists(request, response);
			//display the table results
			String page = "/jsp/pscMaintenanceXrefStatusActionResults.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
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
