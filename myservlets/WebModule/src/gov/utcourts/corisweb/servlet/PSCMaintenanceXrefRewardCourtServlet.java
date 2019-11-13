package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO;
import gov.utcourts.problemsolving.dataaccess.psrewardcourtxref.PsRewardCourtXrefBO;
import gov.utcourts.problemsolving.dataaccess.psrewarddefn.PsRewardDefnBO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceXrefRewardCourtServlet
*/
@WebServlet("/PSCMaintenanceXrefRewardCourtServlet")
public class PSCMaintenanceXrefRewardCourtServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceXrefRewardCourtServlet() {
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
					String page = "/jsp/pscMaintenanceXrefRewardCourt.jsp";
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
			int psRewardDefnId = 0;
			List<PsRewardDefnBO> rewardDefnList = new PsRewardDefnBO().orderBy(PsRewardDefnBO.PSREWARDDEFNID).search();
			String checkedValue = "";
			
			//delete all records for this particular psCourtProfileId
			if(psCourtProfileId > 0) {
				PsRewardCourtXrefBO psRewardCourtXrefBO = new PsRewardCourtXrefBO();
				psRewardCourtXrefBO.where(PsRewardCourtXrefBO.COURTPROFILEID, psCourtProfileId);
				psRewardCourtXrefBO.delete();
				psRewardCourtXrefBO = null;
			}
			
			//insert the checked boxes
			for(PsRewardDefnBO rewardListBO : rewardDefnList) {
				psRewardDefnId = rewardListBO.getPsRewardDefnId();
				checkedValue = TextUtil.getParamAsString(request, "reward-"+psCourtProfileId+"-"+psRewardDefnId);
				if("on".equals(checkedValue)){
					if(psCourtProfileId > 0 && psRewardDefnId > 0) {
						PsRewardCourtXrefBO psRewardCourtXrefBO = new PsRewardCourtXrefBO();
						psRewardCourtXrefBO.setPsRewardDefnId(psRewardDefnId);
						psRewardCourtXrefBO.setCourtProfileId(psCourtProfileId);
						psRewardCourtXrefBO.insert();
						psRewardCourtXrefBO = null;
					}
				}
			}
			
			checkedValue = null;
			rewardDefnList = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getLists(request, response);
			//display the edit popup
			String page = "/jsp/pscMaintenanceXrefRewardCourtAddEdit.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void getLists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psCourtProfileId = TextUtil.getParamAsInt(request, "psCourtProfileId");
			List<PsRewardCourtXrefBO> xrefList = new ArrayList<PsRewardCourtXrefBO>();
			List<PsRewardDefnBO> rewardDefnList;
			List<CourtProfileBO> courtProfilesList = new ArrayList<CourtProfileBO>();
			
			//get the xref list
			PsRewardCourtXrefBO xrefListBO = new PsRewardCourtXrefBO();
			xrefListBO.includeTables(new PsRewardDefnBO().setOuter().orderBy(PsRewardDefnBO.PSREWARDCODE));
			xrefListBO.addForeignKey(PsRewardDefnBO.PSREWARDDEFNID, PsRewardCourtXrefBO.PSREWARDDEFNID);
			xrefListBO.includeTables(new CourtProfileBO().setOuter().orderBy(CourtProfileBO.COURTTITLE));
			xrefListBO.addForeignKey(CourtProfileBO.COURTPROFILEID, PsRewardCourtXrefBO.COURTPROFILEID);
			if(psCourtProfileId > 0) {
				xrefListBO.where(PsRewardCourtXrefBO.COURTPROFILEID, psCourtProfileId);
			}
			xrefList = xrefListBO.search(PsRewardCourtXrefBO.PSREWARDCOURTXREFID
					, PsRewardCourtXrefBO.COURTPROFILEID
					, PsRewardCourtXrefBO.PSREWARDDEFNID
					, CourtProfileBO.COURTPROFILEID
					, CourtProfileBO.COURTLOCNCODE
					, CourtProfileBO.COURTTYPE
					, CourtProfileBO.COURTTITLE
					, PsRewardDefnBO.REMOVEDFLAG
					, PsRewardDefnBO.PSREWARDCODE
					, PsRewardDefnBO.PSREWARDDESCR);
			
			//get the reward defn list
			rewardDefnList = new PsRewardDefnBO().orderBy(PsRewardDefnBO.PSREWARDCODE).search();
			
			//get the list of courts
			CourtProfileBO courtProfilesListBO = new CourtProfileBO();
			courtProfilesListBO.orderBy(CourtProfileBO.COURTTYPE);
			courtProfilesListBO.orderBy(CourtProfileBO.COURTTITLE);
			if(psCourtProfileId > 0) {
				courtProfilesListBO.where(CourtProfileBO.COURTPROFILEID, psCourtProfileId);
			}
			courtProfilesList = courtProfilesListBO.search();

			request.setAttribute("xrefList", xrefList);
			request.setAttribute("rewardDefnList", rewardDefnList);
			request.setAttribute("courtProfilesList", courtProfilesList);
			
			xrefList = null;
			xrefListBO = null;
			rewardDefnList = null;
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
			String page = "/jsp/pscMaintenanceXrefRewardCourtResults.jsp";
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
