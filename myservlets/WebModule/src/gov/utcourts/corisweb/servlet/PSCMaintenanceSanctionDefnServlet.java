package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.problemsolving.dataaccess.pssanctiondefn.PsSanctionDefnBO;
import gov.utcourts.problemsolving.dataaccess.pssanctionleveldefn.PsSanctionLevelDefnBO;

/**
 * Servlet implementation class PSCSanctionDefnServlet
 */
@WebServlet("/PSCMaintenanceSanctionDefnServlet")
public class PSCMaintenanceSanctionDefnServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PSCMaintenanceSanctionDefnServlet.class.getName());
    private static final String SANCTION_JSP = "/jsp/pscMaintenanceSanctionDefn.jsp";
    private static final String EDIT_JSP = "/jsp/pscMaintenanceSanctionDefnAddEdit.jsp";
    private static final String RESULT_JSP = "/jsp/pscMaintenanceSanctionDefnResults.jsp";
    /**
     * @see BaseServlet#BaseServlet()
     */
    public PSCMaintenanceSanctionDefnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.corisweb.servlet.BaseServlet#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = TextUtil.getParamAsString(request, "mode");
		boolean showRemoved = TextUtil.getParamAsBooleanValue(request, "showRemoved");
		try {
			
			if ("edit".equalsIgnoreCase(mode) || "add".equalsIgnoreCase(mode)) {
				request.setAttribute("sanctionDefn", getSanctionFromRequest(request));
				request.setAttribute("sanctionLevelDefnList",getSanctionLevelDefns());
				request.setAttribute("mode", "edit".equalsIgnoreCase(mode)?"edit":"add");
				getServletContext().getRequestDispatcher(EDIT_JSP).forward(request, response);
				return;
			} else if ("update".equalsIgnoreCase(mode)) {
				updateSanctionDefn(request);
			} else if("create".equalsIgnoreCase(mode)){
				createSanctionDefn(request);
			} else if("list".equalsIgnoreCase(mode)){
				request.setAttribute("sanctionDefnList",getSanctionDefns(showRemoved));
				request.setAttribute("sanctionLevelDefnList",getSanctionLevelDefns());
				getServletContext().getRequestDispatcher(RESULT_JSP).forward(request, response);
				return;
			}else if("displayHeader".equalsIgnoreCase(mode)){
				displayHeader(request, response);
				return;
			} else if("set-removedFlag".equalsIgnoreCase(mode)){
				new PsSanctionDefnBO().where(PsSanctionDefnBO.PSSANCTIONCODE,TextUtil.getParamAsString(request, "sanctionCode"))
								.setRemovedFlag(TextUtil.getParamAsString(request, "removedFlag")).update(PsSanctionDefnBO.REMOVEDFLAG);
				request.setAttribute("sanctionDefnList",getSanctionDefns(showRemoved));
				getServletContext().getRequestDispatcher(RESULT_JSP).forward(request, response);
				return;
			}
			
			getServletContext().getRequestDispatcher(SANCTION_JSP).forward(request, response);
			
		} catch (Exception e) {
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

	public void displayHeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String page = "/jsp/pscHeader.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param showRemoved
	 * @return
	 * @throws Exception
	 */
	private List<PsSanctionDefnBO> getSanctionDefns(boolean showRemoved) throws Exception {
		if(showRemoved){
			return new PsSanctionDefnBO().search();
		}else {
			return new PsSanctionDefnBO().where(PsSanctionDefnBO.REMOVEDFLAG,"N").search();
		}
	}
	
	private List<PsSanctionLevelDefnBO> getSanctionLevelDefns() throws Exception {
		return new PsSanctionLevelDefnBO().orderBy(PsSanctionLevelDefnBO.SANCTIONLEVELSRL).search();
	}

	/**
	 * @param request
	 * @throws Exception
	 */
	private void createSanctionDefn(HttpServletRequest request) throws Exception {
		PsSanctionDefnBO vo = getSanctionFromRequest(request);
		new PsSanctionDefnBO().setPsSanctionCode(vo.getPsSanctionCode())
			.setPsSanctionDescr(vo.getPsSanctionDescr())
			.setPsSanctionLevel(vo.getPsSanctionLevel())
			.setRemovedFlag(TextUtil.isEmpty(vo.getRemovedFlag())?"N":vo.getRemovedFlag())
			.insert();
		
	}

	/**
	 * @param request
	 * @throws Exception
	 */
	private void updateSanctionDefn(HttpServletRequest request) throws Exception {
		PsSanctionDefnBO vo = getSanctionFromRequest(request);
		new PsSanctionDefnBO().where(PsSanctionDefnBO.PSSANCTIONCODE, vo.getPsSanctionCode())
				.setPsSanctionDescr(vo.getPsSanctionDescr())
				.setPsSanctionLevel(vo.getPsSanctionLevel())
				.update();
	}

	/**
	 * @param request
	 * @return
	 */
	private PsSanctionDefnBO getSanctionFromRequest(HttpServletRequest request) {
		PsSanctionDefnBO vo = new PsSanctionDefnBO();
		vo.setPsSanctionCode(TextUtil.getParamAsString(request, "sanctionCode"));
		vo.setPsSanctionDescr(TextUtil.getParamAsString(request, "descr"));
		vo.setRemovedFlag(TextUtil.getParamAsString(request, "removedFlag"));
		vo.setPsSanctionLevel(TextUtil.getParamAsInt(request, "level"));
		
		return vo;
	}

}
