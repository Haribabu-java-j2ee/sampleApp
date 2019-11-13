package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO;
import gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO;
import gov.utcourts.problemsolving.dataaccess.pscourtlocationxref.PsCourtLocationXrefBO;

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
@WebServlet("/PSCMaintenanceXrefCourtLocationRedoServlet")
public class PSCMaintenanceXrefCourtLocationRedoServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceXrefCourtLocationRedoServlet() {
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
					String page = "/jsp/pscMaintenanceXrefCourtLocationRedo.jsp";
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
			int courtProfileId = TextUtil.getParamAsInt(request, "courtProfileId");
			int psCourtDefnId = TextUtil.getParamAsInt(request, "psCourtDefnId");
			List<PsCourtDefnBO> psCourtDefnList = new PsCourtDefnBO().orderBy(PsCourtDefnBO.PSCOURTDEFNID).search();
			String checkedValue = "";
			
			//delete all records for this particular psCourtDefnId
			if(courtProfileId > 0) {
				PsCourtLocationXrefBO psCourtLocationXrefBO = new PsCourtLocationXrefBO();
				psCourtLocationXrefBO.where(PsCourtLocationXrefBO.COURTPROFILEID, courtProfileId);
				psCourtLocationXrefBO.delete();
				psCourtLocationXrefBO = null;
			}
			
			//insert the checked boxes
			for(PsCourtDefnBO psCourtDefnBO : psCourtDefnList) {
				psCourtDefnId = psCourtDefnBO.getPsCourtDefnId();
				checkedValue = TextUtil.getParamAsString(request, "action-"+courtProfileId+"-"+psCourtDefnId);
				if("on".equals(checkedValue)){
					if(courtProfileId > 0 && psCourtDefnId > 0) {
						PsCourtLocationXrefBO psCourtLocationXrefBO = new PsCourtLocationXrefBO();
						psCourtLocationXrefBO.setPsCourtLocationXrefId(0);
						psCourtLocationXrefBO.setCourtProfileId(courtProfileId);
						psCourtLocationXrefBO.setPsCourtDefnId(psCourtDefnId);
						psCourtLocationXrefBO.insert();
						psCourtLocationXrefBO = null;
					}
				}
			}
			
			checkedValue = null;
			psCourtDefnList = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getLists(request, response);
			//display the edit popup
			String page = "/jsp/pscMaintenanceXrefCourtLocationAddEdit.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void getLists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psCourtDefnId = TextUtil.getParamAsInt(request, "psCourtDefnId");
			int courtProfileId = TextUtil.getParamAsInt(request, "courtProfileId");
			List<PsCourtLocationXrefBO> xrefList = new ArrayList<PsCourtLocationXrefBO>();
			List<CourtProfileBO> courtProfileList;
			List<PsCourtDefnBO> psCourtDefnList = new ArrayList<PsCourtDefnBO>();
			
			//get the xref list
			PsCourtLocationXrefBO xrefListBO = new PsCourtLocationXrefBO();
			xrefListBO.includeTables(new PsCourtDefnBO().setOuter().orderBy(PsCourtDefnBO.PSCOURTCODE));
			xrefListBO.addForeignKey(PsCourtDefnBO.PSCOURTDEFNID, PsCourtLocationXrefBO.PSCOURTDEFNID);
			xrefListBO.includeTables(new CourtProfileBO().setOuter().orderBy(CourtProfileBO.COURTTITLE));
			xrefListBO.addForeignKey(CourtProfileBO.COURTPROFILEID, PsCourtLocationXrefBO.COURTPROFILEID);
			if(courtProfileId > 0) {
				xrefListBO.where(PsCourtLocationXrefBO.COURTPROFILEID, courtProfileId);
			}
			xrefList = xrefListBO.search(PsCourtLocationXrefBO.PSCOURTLOCATIONXREFID
					, PsCourtLocationXrefBO.COURTPROFILEID
					, PsCourtLocationXrefBO.PSCOURTDEFNID
					, CourtProfileBO.COURTPROFILEID
					, CourtProfileBO.COURTTITLE
					, PsCourtDefnBO.REMOVEDFLAG
					, PsCourtDefnBO.PSCOURTCODE
					, PsCourtDefnBO.PSCODEDESCR);
			
			//get the action defn list
			psCourtDefnList = new PsCourtDefnBO().orderBy(PsCourtDefnBO.PSCODEDESCR).search();
			
			//get the list of court types
			CourtProfileBO courtProfileListBO = new CourtProfileBO();
			courtProfileListBO.orderBy(CourtProfileBO.COURTTITLE);
			if(courtProfileId > 0) {
				courtProfileListBO.where(CourtProfileBO.COURTPROFILEID, courtProfileId);
			}
			courtProfileList = courtProfileListBO.search();

			request.setAttribute("xrefList", xrefList);
			request.setAttribute("courtProfileList", courtProfileList);
			request.setAttribute("psCourtDefnList", psCourtDefnList);
			
			xrefList = null;
			xrefListBO = null;
			courtProfileList = null;
			psCourtDefnList = null;
			courtProfileListBO = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void displayResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getLists(request, response);
			//display the table results
			String page = "/jsp/pscMaintenanceXrefCourtLocationResults.jsp";
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
