package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO;
import gov.utcourts.problemsolving.dataaccess.pssanctioncourtxref.PsSanctionCourtXrefBO;
import gov.utcourts.problemsolving.dataaccess.pssanctiondefn.PsSanctionDefnBO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceXrefSanctionCourtServlet
*/
@WebServlet("/PSCMaintenanceXrefSanctionCourtServlet")
public class PSCMaintenanceXrefSanctionCourtServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceXrefSanctionCourtServlet() {
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
					String page = "/jsp/pscMaintenanceXrefSanctionCourt.jsp";
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
			
			int psCourtProfileId = TextUtil.getParamAsInt(request, "psCourtProfileId");
			int psSanctionDefnId = 0;
//			int psSanctionLevel = 0;
			List<PsSanctionDefnBO> sanctionDefnList = new PsSanctionDefnBO().orderBy(PsSanctionDefnBO.PSSANCTIONDEFNID).search();
			String checkedValue = "";
			
			//delete all records for this particular psCourtProfileId
			if(psCourtProfileId > 0) {
				PsSanctionCourtXrefBO psSanctionCourtXrefBO = new PsSanctionCourtXrefBO();
				psSanctionCourtXrefBO.where(PsSanctionCourtXrefBO.COURTPROFILEID, psCourtProfileId);
				psSanctionCourtXrefBO.delete();
				psSanctionCourtXrefBO = null;
			}
			
			//insert the checked boxes along with their level
			for(PsSanctionDefnBO sanctionListBO : sanctionDefnList) {
				psSanctionDefnId = sanctionListBO.getPsSanctionDefnId();
				checkedValue = TextUtil.getParamAsString(request, "sanction-"+psCourtProfileId+"-"+psSanctionDefnId);
				if("on".equals(checkedValue)){
//					psSanctionLevel = TextUtil.getParamAsInt(request, "level-"+psCourtProfileId+"-"+psSanctionDefnId);
					if(psCourtProfileId > 0 && psSanctionDefnId > 0 ) {
						PsSanctionCourtXrefBO psSanctionCourtXrefBO = new PsSanctionCourtXrefBO();
						psSanctionCourtXrefBO.setPsSanctionDefnId(psSanctionDefnId);
						psSanctionCourtXrefBO.setCourtProfileId(psCourtProfileId);
//						psSanctionCourtXrefBO.setPsSanctionLevel(psSanctionLevel);
						psSanctionCourtXrefBO.insert();
						psSanctionCourtXrefBO = null;
					}
				}
			}
			
			checkedValue = null;
			sanctionDefnList = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getLists(request, response);
			//display the edit popup
			String page = "/jsp/pscMaintenanceXrefSanctionCourtAddEdit.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void getLists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psCourtProfileId = TextUtil.getParamAsInt(request, "psCourtProfileId");
			List<PsSanctionCourtXrefBO> xrefList = new ArrayList<PsSanctionCourtXrefBO>();
			List<PsSanctionDefnBO> sanctionDefnList;
			List<CourtProfileBO> courtProfilesList = new ArrayList<CourtProfileBO>();
			
			//get the xref list
			PsSanctionCourtXrefBO xrefListBO = new PsSanctionCourtXrefBO();
			xrefListBO.includeTables(new PsSanctionDefnBO().setOuter().orderBy(PsSanctionDefnBO.PSSANCTIONCODE));
			xrefListBO.addForeignKey(PsSanctionDefnBO.PSSANCTIONDEFNID, PsSanctionCourtXrefBO.PSSANCTIONDEFNID);
			xrefListBO.includeTables(new CourtProfileBO().setOuter().orderBy(CourtProfileBO.COURTTITLE));
			xrefListBO.addForeignKey(CourtProfileBO.COURTPROFILEID, PsSanctionCourtXrefBO.COURTPROFILEID);
			if(psCourtProfileId > 0) {
				xrefListBO.where(PsSanctionCourtXrefBO.COURTPROFILEID, psCourtProfileId);
			}
			xrefList = xrefListBO.search(PsSanctionCourtXrefBO.PSSANCTIONCOURTXREFID
					, PsSanctionCourtXrefBO.COURTPROFILEID
					, PsSanctionCourtXrefBO.PSSANCTIONDEFNID
					, CourtProfileBO.COURTPROFILEID
					, CourtProfileBO.COURTLOCNCODE
					, CourtProfileBO.COURTTYPE
					, CourtProfileBO.COURTTITLE
					, PsSanctionDefnBO.REMOVEDFLAG
					, PsSanctionDefnBO.PSSANCTIONCODE
					, PsSanctionDefnBO.PSSANCTIONDESCR);
			
			//get the sanction defn list
			sanctionDefnList = new PsSanctionDefnBO().orderBy(PsSanctionDefnBO.PSSANCTIONCODE).search();
			
			//get the list of courts
			CourtProfileBO courtProfilesListBO = new CourtProfileBO();
			courtProfilesListBO.orderBy(CourtProfileBO.COURTTYPE);
			courtProfilesListBO.orderBy(CourtProfileBO.COURTTITLE);
			if(psCourtProfileId > 0) {
				courtProfilesListBO.where(CourtProfileBO.COURTPROFILEID, psCourtProfileId);
			}
			courtProfilesList = courtProfilesListBO.search();

			request.setAttribute("xrefList", xrefList);
			request.setAttribute("sanctionDefnList", sanctionDefnList);
			request.setAttribute("courtProfilesList", courtProfilesList);
			
			xrefList = null;
			xrefListBO = null;
			sanctionDefnList = null;
			courtProfilesList = null;
			courtProfilesListBO = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void displayResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getLists(request, response);
			//display the table results
			String page = "/jsp/pscMaintenanceXrefSanctionCourtResults.jsp";
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
