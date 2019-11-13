package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.state.StateBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

/**
* Servlet implementation class CorisAttorneyAttachDetachLookupServlet
*/
@WebServlet("/CorisAttorneyAttachDetachLookupServlet")
public class CorisAttorneyAttachDetachLookupServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CorisAttorneyAttachDetachLookupServlet.class.getName());
     
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CorisAttorneyAttachDetachLookupServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String page = "";

			String courtType = URLEncryption.getParamAsString(request, "courtType"); //get passed in value
			if (courtType == null || "".equals(courtType)) {
				courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE); //default	
			}
			request.setAttribute("courtType", courtType);

			String locnCode = URLEncryption.getParamAsString(request, "locnCode"); //get passed in value
			if (locnCode == null || "".equals(locnCode)) {
				locnCode = (String) request.getSession().getAttribute(SessionVariables.LOCN_CODE); //default	
			}
			request.setAttribute("locnCode", locnCode);

			String caseNum = URLEncryption.getParamAsString(request, "caseNum"); //get passed in value
			request.setAttribute("caseNum", caseNum);
			
			String tableName = URLEncryption.getParamAsString(request, "tableName");
			request.setAttribute("tableName", tableName);	
			
			String tabName = URLEncryption.getParamAsString(request, "tabName");
			request.setAttribute("tabName", tabName);
			
			String parentId = URLEncryption.getParamAsString(request, "parentId");
			request.setAttribute("parentId", parentId);

			String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME); //default

			if(logName == null || "".equals(logName)) {
				page = "/login.jsp";
				getServletContext().getRequestDispatcher(page).forward(request, response);
			} else {
				PageMode mode = PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode"));
				switch (mode) {
					case FIND:
						find(request, response, tabName, courtType, tableName, caseNum, locnCode);
						break;
					default:
						defaultPage(request, response, courtType);
						break;
				}	
				mode = null;
			}
		} catch (Exception e){
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error("CorisAttorneyAttachDetachLookupServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}

	private void defaultPage(HttpServletRequest request, HttpServletResponse response, String courtType) throws Exception {
		String page = "/jsp/corisAttorneyAttachDetachLookup.jsp";
		getStates(request, courtType);
		getServletContext().getRequestDispatcher(page).forward(request, response);
		page = null;
	}

	private void find(HttpServletRequest request, HttpServletResponse response, String tabName, String courtType, String tableName, String caseNum, String locnCode) throws Exception {
		String page = "/jsp/corisAttorneyAttachDetachLookupTable.jsp";
		request.setAttribute("attyList", getAttorneyList(request, courtType));
		getStates(request, courtType);
		getServletContext().getRequestDispatcher(page).forward(request, response);
		page = null;
	}

	private List<AttorneyBO> getAttorneyList(HttpServletRequest request, String courtType) throws Exception {
		List<AttorneyBO> attyList = null;
		if ((URLEncryption.getParamAsInt(request, "barNum") > 0) || !TextUtil.isEmpty(URLEncryption.getParamAsString(request, "attyLastName"))) {
			AttorneyBO attorneyBO = new AttorneyBO(courtType);
			if (URLEncryption.getParamAsInt(request, "barNum") > 0 && !TextUtil.isEmpty(URLEncryption.getParamAsString(request, "barState"))) {
				attorneyBO.where(AttorneyBO.BARNUM, URLEncryption.getParamAsInt(request, "barNum"));
				attorneyBO.where(Exp.upper(AttorneyBO.BARSTATE), Exp.MATCHES, URLEncryption.getParamAsString(request, "barState").toUpperCase());
			}
			if (!TextUtil.isEmpty(URLEncryption.getParamAsString(request, "attyLastName"))) {
				attorneyBO.where(Exp.upper(AttorneyBO.LASTNAME), Exp.MATCHES, URLEncryption.getParamAsString(request, "attyLastName").toUpperCase());
			}
			if (!TextUtil.isEmpty(URLEncryption.getParamAsString(request, "attyFirstName"))) {
				attorneyBO.where(Exp.upper(AttorneyBO.FIRSTNAME), Exp.MATCHES, URLEncryption.getParamAsString(request, "attyFirstName").toUpperCase());
			}
			attorneyBO.limit(Constants.MAX_RESULTS);
			attyList = attorneyBO.search(); 
			attorneyBO = null;
		}
		return attyList;
	}
	
	private void getStates(HttpServletRequest request, String courtType) throws Exception {
		try{
			List<StateBO> stateBO = new StateBO(courtType).orderBy(StateBO.NAME).search();
			request.setAttribute("statesList", stateBO);
		}catch(Exception e){
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error("CorisAttorneyAttachDetachLookupServlet getStates(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
}
