package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO;
import gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO;
import gov.utcourts.problemsolving.dataaccess.pscourtlocationxref.PsCourtLocationXrefBO;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class PSCMaintenanceXrefCourtLocationServlet
*/
@WebServlet("/PSCMaintenanceXrefCourtLocationServlet")
public class PSCMaintenanceXrefCourtLocationServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMaintenanceXrefCourtLocationServlet() {
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
				case DELETE:
					delete(request, response);
					break;
				case DISPLAYHEADER:
					displayHeader(request, response);
					break;
				case DISPLAYDETAILS:
					displayDetails(request, response);
					break;
				default:
					String page = "/jsp/pscMaintenanceXrefCourtLocation.jsp";
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
			int courtProfileId = TextUtil.getParamAsInt(request, "courtProfileId");
			int psCourtDefnId = TextUtil.getParamAsInt(request, "psCourtDefnId");
			if(courtProfileId > 0 && psCourtDefnId > 0) {
				PsCourtLocationXrefBO psCourtLocationXrefBO = new PsCourtLocationXrefBO();
				psCourtLocationXrefBO.setCourtProfileId(courtProfileId);
				psCourtLocationXrefBO.setPsCourtDefnId(psCourtDefnId);
				psCourtLocationXrefBO.insert();
				psCourtLocationXrefBO = null;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psCourtLocationXrefId = TextUtil.getParamAsInt(request, "psCourtLocationXrefId");
			if(psCourtLocationXrefId > 0) {
				PsCourtLocationXrefBO psCourtLocationXrefBO = new PsCourtLocationXrefBO();
				psCourtLocationXrefBO.where(PsCourtLocationXrefBO.PSCOURTLOCATIONXREFID, psCourtLocationXrefId);
				psCourtLocationXrefBO.delete();
				psCourtLocationXrefBO = null;
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void displayDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<PsCourtLocationXrefBO> xrefList;
			List<CourtProfileBO> courtCodesList;
			List<PsCourtDefnBO> psCourtCodesList;
			xrefList = new PsCourtLocationXrefBO()
					.includeTables(new CourtProfileBO().setOuter().orderBy(CourtProfileBO.COURTTITLE))
					.addForeignKey(CourtProfileBO.COURTPROFILEID, PsCourtLocationXrefBO.COURTPROFILEID)
					.includeTables(new PsCourtDefnBO().setOuter())
					.addForeignKey(PsCourtDefnBO.PSCOURTDEFNID, PsCourtLocationXrefBO.PSCOURTDEFNID)
					.search(PsCourtLocationXrefBO.PSCOURTLOCATIONXREFID
							, PsCourtLocationXrefBO.COURTPROFILEID
							, CourtProfileBO.COURTLOCNCODE
							, CourtProfileBO.COURTTYPE
							, CourtProfileBO.COURTTITLE
							, PsCourtLocationXrefBO.PSCOURTDEFNID
							, PsCourtDefnBO.PSCOURTCODE
							, PsCourtDefnBO.PSCODETYPE
							, PsCourtDefnBO.REMOVEDFLAG
							, PsCourtDefnBO.PSCODEDESCR);
			request.setAttribute("xrefList", xrefList);
			courtCodesList = new CourtProfileBO().orderBy(CourtProfileBO.COURTTITLE).search();
			request.setAttribute("courtCodesList", courtCodesList);
			psCourtCodesList = new PsCourtDefnBO().orderBy(PsCourtDefnBO.PSCOURTCODE).search();
			request.setAttribute("psCourtCodesList", psCourtCodesList);
			String page = "/jsp/pscMaintenanceXrefCourtLocationDetails.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			xrefList = null;
			courtCodesList = null;
			psCourtCodesList = null;
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
