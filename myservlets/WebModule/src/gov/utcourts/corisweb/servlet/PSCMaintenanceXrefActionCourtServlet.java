package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.psactioncourtxref.PsActionCourtXrefBO;
import gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO;
import gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceXrefActionCourtServlet
*/
@WebServlet("/PSCMaintenanceXrefActionCourtServlet")
public class PSCMaintenanceXrefActionCourtServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceXrefActionCourtServlet() {
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
					String page = "/jsp/pscMaintenanceXrefActionCourt.jsp";
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
			
			int psCourtDefnId = TextUtil.getParamAsInt(request, "psCourtDefnId");
			int psActionDefnId = 0;
			List<PsActionDefnBO> actionDefnList = new PsActionDefnBO().orderBy(PsActionDefnBO.PSACTIONDEFNID).search();
			String checkedValue = "";
			
			//delete all records for this particular psCourtDefnId
			if(psCourtDefnId > 0) {
				PsActionCourtXrefBO psActionCourtXrefBO = new PsActionCourtXrefBO();
				psActionCourtXrefBO.where(PsActionCourtXrefBO.PSCOURTDEFNID, psCourtDefnId);
				psActionCourtXrefBO.delete();
				psActionCourtXrefBO = null;
			}
			
			//insert the checked boxes
			for(PsActionDefnBO actionListBO : actionDefnList) {
				psActionDefnId = actionListBO.getPsActionDefnId();
				checkedValue = TextUtil.getParamAsString(request, "action-"+psCourtDefnId+"-"+psActionDefnId);
				if("on".equals(checkedValue)){
					if(psCourtDefnId > 0 && psActionDefnId > 0) {
						PsActionCourtXrefBO psActionCourtXrefBO = new PsActionCourtXrefBO();
						psActionCourtXrefBO.setPsActionCourtId(0);
						psActionCourtXrefBO.setPsActionDefnId(psActionDefnId);
						psActionCourtXrefBO.setPsCourtDefnId(psCourtDefnId);
						psActionCourtXrefBO.insert();
						psActionCourtXrefBO = null;
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
			String page = "/jsp/pscMaintenanceXrefActionCourtAddEdit.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void getLists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psCourtDefnId = TextUtil.getParamAsInt(request, "psCourtDefnId");
			List<PsActionCourtXrefBO> xrefList = new ArrayList<PsActionCourtXrefBO>();
			List<PsActionDefnBO> actionDefnList;
			List<PsCourtDefnBO> courtDefnList = new ArrayList<PsCourtDefnBO>();
			
			//get the xref list
			PsActionCourtXrefBO xrefListBO = new PsActionCourtXrefBO();
			xrefListBO.includeTables(new PsActionDefnBO().setOuter().orderBy(PsActionDefnBO.PSACTIONCODE));
			xrefListBO.addForeignKey(PsActionDefnBO.PSACTIONDEFNID, PsActionCourtXrefBO.PSACTIONDEFNID);
			xrefListBO.includeTables(new PsCourtDefnBO().setOuter().orderBy(PsCourtDefnBO.PSCOURTCODE));
			xrefListBO.addForeignKey(PsCourtDefnBO.PSCOURTDEFNID, PsActionCourtXrefBO.PSCOURTDEFNID);
			if(psCourtDefnId > 0) {
				xrefListBO.where(PsActionCourtXrefBO.PSCOURTDEFNID, psCourtDefnId);
			}
			xrefList = xrefListBO.search(PsActionCourtXrefBO.PSACTIONCOURTID
					, PsActionCourtXrefBO.PSCOURTDEFNID
					, PsActionCourtXrefBO.PSACTIONDEFNID
					, PsCourtDefnBO.PSCOURTDEFNID
					, PsCourtDefnBO.PSCOURTCODE
					, PsActionDefnBO.REMOVEDFLAG
					, PsActionDefnBO.PSACTIONCODE
					, PsActionDefnBO.PSACTIONDESCR);
			
			//get the action defn list
			actionDefnList = new PsActionDefnBO().orderBy(PsActionDefnBO.PSACTIONCODE).search();
			
			//get the list of court types
			PsCourtDefnBO courtDefnListBO = new PsCourtDefnBO();
			courtDefnListBO.orderBy(PsCourtDefnBO.PSCOURTCODE);
			if(psCourtDefnId > 0) {
				courtDefnListBO.where(PsCourtDefnBO.PSCOURTDEFNID, psCourtDefnId);
			}
			courtDefnList = courtDefnListBO.search();

			request.setAttribute("xrefList", xrefList);
			request.setAttribute("actionDefnList", actionDefnList);
			request.setAttribute("courtDefnList", courtDefnList);
			
			xrefList = null;
			xrefListBO = null;
			actionDefnList = null;
			courtDefnList = null;
			courtDefnListBO = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void displayResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getLists(request, response);
			//display the table results
			String page = "/jsp/pscMaintenanceXrefActionCourtResults.jsp";
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
