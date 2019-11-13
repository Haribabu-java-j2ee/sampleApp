package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.state.StateBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.CorisAttorneyLookupReportCriteria;
import gov.utcourts.corisweb.report.CorisAttorneyLookupReportGenerator;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

/**
* Servlet implementation class CorisAttorneyLookupServlet
*/
@WebServlet("/CorisAttorneyLookupServlet")
public class CorisAttorneyLookupServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisAttorneyLookupServlet.class.getName());
	private static String FILE_NAME = "CorisAttorneyLookupServlet";
	private static String REPORT_TITLE = "Attorney Lookup Report";
	private static String REPORT_FILE_NAME = "Attorney_Lookup_Report";
	private static String errorMessage = "";
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CorisAttorneyLookupServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CorisAttorneyLookupReportCriteria myCriteria = null;
		try {
			myCriteria = (CorisAttorneyLookupReportCriteria) generateReportCriteria(request);
			request.setAttribute("myCriteria", myCriteria);
			if (myCriteria.getLogName() == null || "".equals(myCriteria.getLogName())) {
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				switch (myCriteria.getMode()) {
					case SAVE:
						saveReport(request, response, myCriteria);
						break;
					case EMAIL:
						emailReport(response, myCriteria);
						break;
					case FIND:
						find(request, response, myCriteria);
						break;
					default:
						find(request, response, myCriteria);
						break;
				}	
			}
		} catch (Exception e) {
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error(FILE_NAME+" performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
		if (!TextUtil.isEmpty(errorMessage)) {
			request.setAttribute("errorMessage", errorMessage);
		}
		errorMessage = null;
	}
	
	/**
	 * Searches for all attorneys based on search criteria (barNum, name, etc.) and displays the results.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param CorisAttorneyLookupReportCriteria criteria
	 * @throws Exception
	 */
	private void find(HttpServletRequest request, HttpServletResponse response, CorisAttorneyLookupReportCriteria criteria) throws Exception {
		try {
			request.setAttribute("attorneyList", (List<AttorneyBO>) getReportData(criteria));
			request.setAttribute("statesList", new StateBO(criteria.getCourtReadOnlyDB()).orderBy(StateBO.NAME).search(StateBO.STATECODE, StateBO.NAME));
		} catch (Exception e) {
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error(FILE_NAME+" find(HttpServletRequest request, HttpServletResponse response, CorisAttorneyLookupReportCriteria criteria)", e);
		}
		getServletContext().getRequestDispatcher("/jsp/corisAttorneyLookup.jsp").forward(request, response);
	}
	
	/**
	 * Emails the Attorney Lookup search results to the logged in user according to the format they selected (PDF, XLSX, etc.)
	 * 
	 * @param HttpServletResponse response
	 * @param CorisAttorneyLookupReportCriteria criteria
	 * @throws Exception
	 */
	private void emailReport(HttpServletResponse response, CorisAttorneyLookupReportCriteria criteria) throws Exception {
		try {
			this.emailReport(REPORT_TITLE, "Attached please find "+REPORT_TITLE+" in " + criteria.getReportformat() + " format.", this.generateReport(criteria), criteria);
		} catch (Exception e) {
			errorMessage = "There was an error emailing the report.";
			logger.error(FILE_NAME+" emailReport(CorisAttorneyLookupReportCriteria criteria)", e);
		}
	}
	
	/**
	 * Query to get the search results for the Attorney Lookup report.
	 * 
	 * @param ReportBaseSearchCriteria filter
	 * @return List<?>
	 * @throws Exception
	 */
	@Override
	List<?> getReportData(ReportBaseSearchCriteria filter) throws Exception {
		List<AttorneyBO> attyList = null;
		CorisAttorneyLookupReportCriteria criteria = (CorisAttorneyLookupReportCriteria) filter;
		//does attorney exist
		int attyBarNumExists = 0;
		if(criteria.getBarNum() > 0 && !"".equals(criteria.getBarState())){
			attyBarNumExists = new AttorneyBO(criteria.getCourtReadOnlyDB())
					.where(AttorneyBO.BARNUM, criteria.getBarNum())
					.where(AttorneyBO.BARSTATE, criteria.getBarState())
					.find().getBarNum();
		}
		if(attyBarNumExists > 0 && (criteria.getBarNum() > 0) || !TextUtil.isEmpty(criteria.getAttyLastName())){
			AttorneyBO attorneyBO = new AttorneyBO(criteria.getCourtReadOnlyDB());
			if(criteria.getBarNum() > 0){
				attorneyBO.where(AttorneyBO.BARNUM, criteria.getBarNum());
			}
			if(!TextUtil.isEmpty(criteria.getBarState())){
				attorneyBO.where(Exp.upper(AttorneyBO.BARSTATE), Exp.MATCHES, criteria.getBarState().toUpperCase());
			}
			if(!TextUtil.isEmpty(criteria.getAttyLastName())){
				attorneyBO.where(Exp.upper(AttorneyBO.LASTNAME), Exp.MATCHES, criteria.getAttyLastName().toUpperCase());
			}
			if(!TextUtil.isEmpty(criteria.getAttyFirstName())){
				attorneyBO.where(Exp.upper(AttorneyBO.FIRSTNAME), Exp.MATCHES, criteria.getAttyFirstName().toUpperCase());
			}

			// order by
			List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(filter);
			if (orderByPairs != null) {
				for (OrderByPair orderByPair : orderByPairs) {
					switch(orderByPair.getColumnPosition()) {
						case 0:	
							attorneyBO.orderBy(AttorneyBO.LASTNAME, orderByPair.getDirectionType());
							break;
						case 1:	
							attorneyBO.orderBy(AttorneyBO.FIRSTNAME, orderByPair.getDirectionType());
							break;
						case 2:	
							attorneyBO.orderBy(AttorneyBO.BARNUM, orderByPair.getDirectionType());
							break;
						case 3:	
							attorneyBO.orderBy(AttorneyBO.BARSTATE, orderByPair.getDirectionType());
							break;
						case 4:	
							attorneyBO.orderBy(AttorneyBO.BARSTATUS, orderByPair.getDirectionType());
							break;
						case 5:	
							attorneyBO.orderBy(AttorneyBO.CITY, orderByPair.getDirectionType());
							break;
						case 6:	
							attorneyBO.orderBy(AttorneyBO.STATECODE, orderByPair.getDirectionType());
							break;
						case 7:	
							attorneyBO.orderBy(AttorneyBO.COUNTRY, orderByPair.getDirectionType());
							break;
						case 8:
							attorneyBO.orderBy(AttorneyBO.BUSPHONE, orderByPair.getDirectionType());
							break;
						case 9:	
							attorneyBO.orderBy(AttorneyBO.EMAILADDRESS, orderByPair.getDirectionType());
							break;
					}
				}
			}
			attyList = attorneyBO.limit(Constants.MAX_RESULTS).search();
		}
		return attyList;
	}
	
	/**
	 * Sets all the criteria from the incoming form submission (like case number, date range, court type, location) so it can be used when generating or emailing a report. 
	 * 
	 * @param HttpServletRequest request
	 * @return ReportBaseSearchCriteria
	 * @throws Exception 
	 */
	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisAttorneyLookupReportCriteria criteria = new CorisAttorneyLookupReportCriteria(request);
		criteria.setMode(PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode")));
		if (URLEncryption.getParamAsInteger(request, "barNum") != null) {
			criteria.setBarNum(URLEncryption.getParamAsInteger(request, "barNum"));
		}
		criteria.setBarState(URLEncryption.getParamAsString(request, "barState"));
		criteria.setAttyLastName(URLEncryption.getParamAsString(request, "attyLastName"));
		criteria.setAttyFirstName(URLEncryption.getParamAsString(request, "attyFirstName"));
		criteria.setReportFileName(REPORT_FILE_NAME);
		criteria.setReportTitle(REPORT_TITLE);
		criteria.setShowDetails("true"); 
		if ( "false".equals(URLEncryption.getParamAsString(request, "showDetails")) ) { //default - this is a hack to keep the details page from popping up and causing the alerts to disappear
			criteria.setShowDetails(URLEncryption.getParamAsString(request, "showDetails"));
		}
		criteria.setSortColumn(URLEncryption.getParamAsInt(request, "sortColumn"));
		criteria.setSortDirection(URLEncryption.getParamAsString(request, "sortDirection"));
		return criteria;
	}

	/**
	 * Generates the Attorneys for Case report in whatever format was selected (PDF, XLSX, etc.).
	 * 
	 * @param ReportBaseSearchCriteria criteria
	 * @return byte[]
	 * @throws Exception 
	 */
	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		return new CorisAttorneyLookupReportGenerator(criteria).generateReport((List<AttorneyBO>) getReportData(criteria));
	}
	
}
