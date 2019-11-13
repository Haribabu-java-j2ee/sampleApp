package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.coriscommon.xo.PersonnelXO;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO;
import gov.utcourts.problemsolving.dataaccess.psphasedefn.PsPhaseDefnBO;
import gov.utcourts.problemsolving.xo.PsPhaseDefnXO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class PSCMaintenancePhaseDefnServlet
 */
@WebServlet(description = "Servlet for problem solving court phase definition", urlPatterns = { "/PSCMaintenancePhaseDefnServlet" })
public class PSCMaintenancePhaseDefnServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PSCMaintenancePhaseDefnServlet.class.getName());
	
    private static final String JSP_PAGE = "/jsp/pscMaintenancePhaseDefn.jsp";  
    private static final String RESULT_JSP = "/jsp/pscMaintenancePhaseDefnResults.jsp";
    private static final String EDIT_JSP = "/jsp/pscMaintenancePhaseDefnAddEdit.jsp";
    /**
     * @see BaseServlet#BaseServlet()
     */
    public PSCMaintenancePhaseDefnServlet() {
        super();
    }

	/* (non-Javadoc)
	 * @see gov.utcourts.corisweb.servlet.BaseServlet#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = TextUtil.getParamAsString(request, "mode");
		try {
			if ("list".equalsIgnoreCase(mode)) {
				findPSPhases(request);
				getServletContext().getRequestDispatcher(RESULT_JSP).forward(request, response);
				return;
			} else if ("edit".equalsIgnoreCase(mode) || "add".equalsIgnoreCase(mode)) {
				PsPhaseDefnBO vo = getPhaseDefnFromRequest(request);
				if("edit".equalsIgnoreCase(mode)){
					vo = new PsPhaseDefnBO().where(PsPhaseDefnBO.PSPHASEDEFNID, vo.getPsPhaseDefnId()).find();
				}
				request.setAttribute("phaseDefn", vo);
				request.setAttribute("mode", "edit".equalsIgnoreCase(mode)?"edit":"add");
				getServletContext().getRequestDispatcher(EDIT_JSP).forward(request, response);
				return;
			}else if("update".equalsIgnoreCase(mode)){
				updatePsPhase(request);
			}else if("create".equalsIgnoreCase(mode)){
				createPsPhase(request);
				findPSPhases(request);
				getServletContext().getRequestDispatcher(RESULT_JSP).forward(request, response);
				return;
			}else if("displayHeader".equalsIgnoreCase(mode)){
				displayHeader(request, response);
				return;
			}else if ("set-remove".equalsIgnoreCase(mode)) {
				setRemoveFlag(request);
				findPSPhases(request);
				getServletContext().getRequestDispatcher(RESULT_JSP).forward(request, response);
				return;
			}
			
			generateUserAccessableCourtLocnList(request);
			getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
			String err = e.getMessage();
			if(e instanceof SQLException){
				if(err.contains("Unique constraint")){
					err = "Phase definition already defined. Please define a new one.";
				}else {
					err = "Database error occurred.";
				}
			}
			throw new ServletException(err);
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
	
	private void generateUserAccessableCourtLocnList(HttpServletRequest request) throws Exception {
		String courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
		String userName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
		PersonnelBO user = null;
		List<CourtProfileBO> allCourts = new CourtProfileBO().search();
		List<CourtProfileBO> psCourts = new ArrayList<CourtProfileBO>();
		for(CourtProfileBO cvo:allCourts){
			user = PersonnelXO.getPersonnelByLogNameLocnCodeCourtType(userName, cvo.getCourtLocnCode(), courtType);
			if(user != null){
				psCourts.add(cvo);
			}
		}
		request.setAttribute("userCourts", psCourts);
	}

	/**
	 * @param request
	 * @throws Exception
	 */
	private void setRemoveFlag(HttpServletRequest request) throws Exception {
		int phaseId = TextUtil.getParamAsInt(request, "phaseDefnId");
		String removedFlag = TextUtil.getParamAsString(request, "removedFlag");
		new PsPhaseDefnBO().where(PsPhaseDefnBO.PSPHASEDEFNID, phaseId)
					.setRemovedFlag(removedFlag).update(PsPhaseDefnBO.REMOVEDFLAG);
	}

	private void createPsPhase(HttpServletRequest request) throws Exception {
		PsPhaseDefnBO phase = getPhaseDefnFromRequest(request);
		PsPhaseDefnXO.createPhaseDefn(phase);
	}

	/**
	 * @param request
	 * @param locnCode
	 * @param courtType
	 * @throws Exception
	 */
	private void updatePsPhase(HttpServletRequest request) throws Exception {
		PsPhaseDefnBO phaseDefn = getPhaseDefnFromRequest(request);
		PsPhaseDefnXO.updatePhase(phaseDefn);
	}

	/**
	 * @param request
	 * @return
	 */
	private PsPhaseDefnBO getPhaseDefnFromRequest(HttpServletRequest request) {
		PsPhaseDefnBO vo = new PsPhaseDefnBO();
		vo.setPsPhaseDefnId(TextUtil.getParamAsInt(request, "phaseDefnId"));
		vo.setPsPhaseCode(TextUtil.getParamAsString(request, "phaseCode"));
		vo.setPsPhaseDescr(TextUtil.getParamAsString(request, "descr"));
		vo.setPsActionDefnId(TextUtil.getParamAsInt(request, "actionId"));
		return vo;
	}

	/**
	 * @param userLocnCode
	 * @param courtType
	 * @return
	 * @throws Exception
	 */
	private void findPSPhases(HttpServletRequest request) throws Exception {
		String displayRemoved = TextUtil.getParamAsString(request, "showRemoved");
		request.setAttribute("PhaseDefnList", PsPhaseDefnXO.getPsPhaseList(displayRemoved));
	}


}
