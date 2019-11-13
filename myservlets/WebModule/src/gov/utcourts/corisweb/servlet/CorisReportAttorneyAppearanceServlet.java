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
import gov.utcourts.coriscommon.dataaccess.attypresent.AttyPresentBO;
import gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.state.StateBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.DateUtil;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.CorisReportAttorneyAppearanceReportCriteria;
import gov.utcourts.corisweb.report.CorisReportAttorneyAppearanceReportGenerator;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.CorisAttorneyUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

/**
* Servlet implementation class CorisReportAttorneyAppearanceServlet
*/
@WebServlet("/CorisReportAttorneyAppearanceServlet")
public class CorisReportAttorneyAppearanceServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisReportAttorneyAppearanceServlet.class.getName());
	private static String FILE_NAME = "CorisReportAttorneyAppearanceServlet";
	private static String REPORT_TITLE = "Attorney Appearances";
	private static String REPORT_FILE_NAME = "Attorney_Appearances";
	private static String errorMessage = "";
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CorisReportAttorneyAppearanceServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject retValObj = new JSONObject();
		CorisReportAttorneyAppearanceReportCriteria myCriteria = null;
		boolean useJSON = true;
		try {
			myCriteria = (CorisReportAttorneyAppearanceReportCriteria) generateReportCriteria(request);
			request.setAttribute("myCriteria", myCriteria);
			if (myCriteria.getLogName() == null || "".equals(myCriteria.getLogName())) {
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				switch (myCriteria.getMode()) {
					case SAVE:
						useJSON = false;
						saveReport(request, response, myCriteria);
						break;
					case EMAIL:
						useJSON = false;
						emailReport(response, myCriteria);
						break;
					case FIND:
						useJSON = false;
						find(request, response, myCriteria);
						break;
					default:
						useJSON = false;
						find(request, response, myCriteria);
						break;
				}	
			}
		} catch (Exception e) {
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error(FILE_NAME+" performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
		if (!TextUtil.isEmpty(errorMessage)) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", errorMessage);
		} else {
			retValObj.put("error", false);
		}
		if (useJSON) {
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(retValObj.toString());
		}
		retValObj = null;
		errorMessage = null;
	}
	
	/**
	 * Searches for and displays the results.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param CorisReportAttorneyAppearanceReportCriteria criteria
	 * @throws Exception
	 */
	private void find(HttpServletRequest request, HttpServletResponse response, CorisReportAttorneyAppearanceReportCriteria criteria) throws Exception {
		try {
			request.setAttribute("locationDistrictList", CorisAttorneyUtil.getLocationList(request, "D"));
			request.setAttribute("locationJusticeList", CorisAttorneyUtil.getLocationList(request, "J"));
			request.setAttribute("statesList", new StateBO(criteria.getCourtReadOnlyDB()).orderBy(StateBO.NAME).search(StateBO.STATECODE, StateBO.NAME));
			request.setAttribute("judgeJusticeList", getJudgeList(request, "J", criteria));
			request.setAttribute("judgeDistrictList", getJudgeList(request, "D", criteria));
			request.setAttribute("appearanceList", (List<AttyPresentBO>) getReportData(criteria));
		} catch (Exception e) {
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error(FILE_NAME+" find(HttpServletRequest request, HttpServletResponse response, CorisReportAttorneyAppearanceReportCriteria criteria)", e);
		}
		getServletContext().getRequestDispatcher("/jsp/corisReportAttorneyAppearance.jsp").forward(request, response);
	}
	
	/**
	 * Gathers the list of judges
	 * 
	 * @param HttpServletRequest request
	 * @param String courtType
	 * @param CorisReportAttorneyAppearanceReportCriteria criteria
	 * @return List<PersonnelBO>
	 * @throws Exception
	 */
	public static List<PersonnelBO> getJudgeList(HttpServletRequest request, String courtType, CorisReportAttorneyAppearanceReportCriteria criteria) throws Exception {
		List<PersonnelBO> judgeListBO = new PersonnelBO(courtType)
				.where(PersonnelBO.COURTTYPE, courtType)
				.where(PersonnelBO.TYPE, "J")
				.where(PersonnelBO.REMOVEDFLAG, "N")
				.orderBy(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME)
				.search(
						PersonnelBO.LASTNAME, 
						PersonnelBO.FIRSTNAME, 
						PersonnelBO.USERIDSRL,
						PersonnelBO.COURTTYPE,
						PersonnelBO.LOCNCODE,
						PersonnelBO.LOGNAME
				);
		return judgeListBO;
	}
	
	/**
	 * Emails the search results to the logged in user according to the format they selected (PDF, XLSX, etc.)
	 * 
	 * @param HttpServletResponse response
	 * @param CorisReportAttorneyAppearanceReportCriteria criteria
	 * @throws Exception
	 */
	private void emailReport(HttpServletResponse response, CorisReportAttorneyAppearanceReportCriteria criteria) throws Exception {
		try {
			this.emailReport(REPORT_TITLE, "Attached please find "+REPORT_TITLE+" in " + criteria.getReportformat() + " format.", this.generateReport(criteria), criteria);
		} catch (Exception e) {
			errorMessage = "There was an error emailing the report.";
			logger.error(FILE_NAME+" emailReport(CorisReportAttorneyAppearanceReportCriteria criteria)", e);
		}
	}
	
	/**
	 * Query to get the search results for the report.
	 * 
	 * @param ReportBaseSearchCriteria filter
	 * @return List<?>
	 * @throws Exception
	 */
	@Override
	List<?> getReportData(ReportBaseSearchCriteria filter) throws Exception {
		List<AttyPresentBO> appearanceList = null;
		CorisReportAttorneyAppearanceReportCriteria criteria = (CorisReportAttorneyAppearanceReportCriteria) filter;
		
		if (criteria.getJudgeId() > 0 || (criteria.getBarNum() > 0 && !TextUtil.isEmpty(criteria.getBarState()))) {
			
			CaseMeValueBO caseMeValueBO = new CaseMeValueBO(criteria.getCourtReadOnlyDB()).as("s_cmv")
					.includeTables(
							new AttyPresentBO(criteria.getCourtReadOnlyDB()).as("s_ap").includeFields(AttyPresentBO.NO_FIELDS),
							new PersonnelBO(criteria.getCourtReadOnlyDB()).as("s_p").includeFields(PersonnelBO.NO_FIELDS)
					)
					.addForeignKey(AttyPresentBO.MEID, CaseMeValueBO.MEID)
					.addForeignKey(AttyPresentBO.JUDGEID, PersonnelBO.USERIDSRL)
					.where(new TableAndFieldDescriptor("s_ap", AttyPresentBO.BARNUM), Exp.EQUALS, new TableAndFieldDescriptor("ap", AttyPresentBO.BARNUM))
					.where(new TableAndFieldDescriptor("s_ap", AttyPresentBO.BARSTATE), Exp.EQUALS, new TableAndFieldDescriptor("ap", AttyPresentBO.BARSTATE))
					.where(new TableAndFieldDescriptor("s_p", PersonnelBO.USERIDSRL), Exp.EQUALS, new TableAndFieldDescriptor("ap", AttyPresentBO.JUDGEID))
					.where(CaseMeValueBO.MESCREENID, "HRGTRL")
					.where(CaseMeValueBO.MEFIELDID, "TRIAL")
					.where(PersonnelBO.LOCNCODE, criteria.getLocnCode())
					.where(PersonnelBO.COURTTYPE, criteria.getCourtType());

					if (criteria.getStartDate() != null || criteria.getEndDatePlusOne() != null) {
						if (criteria.getStartDate() != null && criteria.getEndDatePlusOne() != null) {
							caseMeValueBO.where(Exp.LEFT_PARENTHESIS, AttyPresentBO.CREATEDATETIME, 
									Exp.GREATER_THAN_OR_EQUAL_TO, criteria.getStartDate(), 
									Exp.AND, AttyPresentBO.CREATEDATETIME, 
									Exp.LESS_THAN_OR_EQUAL_TO, criteria.getEndDatePlusOne(), Exp.RIGHT_PARENTHESIS);
						} else if(criteria.getStartDate() != null) {
							caseMeValueBO.where(AttyPresentBO.CREATEDATETIME, Exp.GREATER_THAN_OR_EQUAL_TO, criteria.getStartDate());
						}
					}
					
					caseMeValueBO.count(CaseMeValueBO.ALL_FIELDS);
					
			AttyPresentBO attyPresentBO = new AttyPresentBO(criteria.getCourtReadOnlyDB()).as("ap")
					.includeTables(
							new PersonnelBO(criteria.getCourtReadOnlyDB()).as("p")
									.includeFields(
											PersonnelBO.LOGNAME,
											new FieldConstant("p.first_name").as("judge_first_name"),
											new FieldConstant("p.last_name").as("judge_last_name"),
											PersonnelBO.LOCNCODE,
											PersonnelBO.COURTTYPE
									)
									.groupBy(
											PersonnelBO.LOGNAME,
											PersonnelBO.LASTNAME,
											PersonnelBO.FIRSTNAME,
											PersonnelBO.LOCNCODE,
											PersonnelBO.COURTTYPE
									),
							new AttorneyBO(criteria.getCourtReadOnlyDB()).as("a")
									.includeFields(
											new FieldConstant("a.last_name").as("attorney_last_name"),
											new FieldConstant("a.first_name").as("attorney_first_name"),
											AttorneyBO.ADDRESS1,
											AttorneyBO.ADDRESS2,
											AttorneyBO.CITY,
											AttorneyBO.STATECODE,
											AttorneyBO.ZIPCODE,
											AttorneyBO.EMAILADDRESS
									)
									.groupBy(
											AttorneyBO.LASTNAME,
											AttorneyBO.FIRSTNAME,
											AttorneyBO.ADDRESS1,
											AttorneyBO.ADDRESS2,
											AttorneyBO.CITY,
											AttorneyBO.STATECODE,
											AttorneyBO.ZIPCODE,
											AttorneyBO.EMAILADDRESS
									),
							new LocationBO(criteria.getCourtReadOnlyDB()).as("l")
									.includeFields(
											LocationBO.LOCNDESCR
									)
									.groupBy(
											LocationBO.LOCNDESCR
									)
					)
					.addForeignKey(AttyPresentBO.JUDGEID, PersonnelBO.USERIDSRL)
					.addForeignKey(AttyPresentBO.BARNUM, AttorneyBO.BARNUM)
					.addForeignKey(AttyPresentBO.BARSTATE, AttorneyBO.BARSTATE)
					.addForeignKey(PersonnelBO.LOCNCODE, LocationBO.LOCNCODE)
					.includeFields(
							AttyPresentBO.JUDGEID,
							AttyPresentBO.BARNUM,
							AttyPresentBO.BARSTATE,
							new FieldConstant("count(*)").as("count_misc"),
							new FieldConstant(Exp.select(caseMeValueBO)).as("count_trial")
					);
							
					if (criteria.getBarNum() > 0 && !TextUtil.isEmpty(criteria.getBarState())) {
						attyPresentBO.where(AttyPresentBO.BARNUM, criteria.getBarNum());
						attyPresentBO.where(AttyPresentBO.BARSTATE, criteria.getBarState());
					}
					
					if (criteria.getJudgeId() > 0) {
						attyPresentBO.where(AttyPresentBO.JUDGEID, criteria.getJudgeId());
					}
	
					if (!TextUtil.isEmpty(criteria.getLocnCode())) {
						attyPresentBO.where(PersonnelBO.LOCNCODE, criteria.getLocnCode());
					}

					attyPresentBO.where(PersonnelBO.COURTTYPE, criteria.getCourtType());
					
					if (criteria.getStartDate() != null || criteria.getEndDatePlusOne() != null) {
						if (criteria.getStartDate() != null && criteria.getEndDatePlusOne() != null) {
							attyPresentBO.where(Exp.LEFT_PARENTHESIS, AttyPresentBO.CREATEDATETIME, 
									Exp.GREATER_THAN_OR_EQUAL_TO, criteria.getStartDate(), 
									Exp.AND, AttyPresentBO.CREATEDATETIME, 
									Exp.LESS_THAN_OR_EQUAL_TO, criteria.getEndDatePlusOne(), Exp.RIGHT_PARENTHESIS);
						} else if(criteria.getStartDate() != null) {
							attyPresentBO.where(AttyPresentBO.CREATEDATETIME, Exp.GREATER_THAN_OR_EQUAL_TO, criteria.getStartDate());
						}
					}
					
					attyPresentBO.groupBy(
							AttyPresentBO.JUDGEID,
							AttyPresentBO.BARNUM,
							AttyPresentBO.BARSTATE
					);
				
					// order by
					List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(filter);
					if (orderByPairs != null) {
						for (OrderByPair orderByPair : orderByPairs) {
							switch(orderByPair.getColumnPosition()) {
								case 0:	//Judge Name
									attyPresentBO.orderBy(PersonnelBO.LASTNAME);
									attyPresentBO.orderBy(PersonnelBO.FIRSTNAME, orderByPair.getDirectionType());
									break;
								case 1:	//Location
									attyPresentBO.orderBy(LocationBO.LOCNDESCR, orderByPair.getDirectionType());
									break;
								case 2:	//Attorney Name
									attyPresentBO.orderBy(AttorneyBO.LASTNAME);
									attyPresentBO.orderBy(AttorneyBO.FIRSTNAME, orderByPair.getDirectionType());
									break;
								case 3:	//Attorney Bar Number
									attyPresentBO.orderBy(AttorneyBO.BARNUM, orderByPair.getDirectionType());
									break;
								case 4:	//Attorney Bar State
									attyPresentBO.orderBy(AttorneyBO.BARSTATE, orderByPair.getDirectionType());
									break;
								case 5:	//Attorney Email Address
									attyPresentBO.orderBy(AttorneyBO.EMAILADDRESS, orderByPair.getDirectionType());
									break;
							}
						}
					}
					
			appearanceList = attyPresentBO.limit(Constants.MAX_RESULTS).search();
		}
		return appearanceList;
	}
	
	/**
	 * Sets all the criteria from the incoming form submission so it can be used when generating a report. 
	 * 
	 * @param HttpServletRequest request
	 * @return ReportBaseSearchCriteria
	 * @throws Exception 
	 */
	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisReportAttorneyAppearanceReportCriteria criteria = new CorisReportAttorneyAppearanceReportCriteria(request);
		criteria.setMode(PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode")));
		criteria.setCourtType(URLEncryption.getParamAsString(request, "courtType"));
		if (criteria.getCourtType() == null || "".equals(criteria.getCourtType())) {
			criteria.setCourtType((String) request.getSession().getAttribute(SessionVariables.COURT_TYPE));	
		}
		criteria.setLocnCode(URLEncryption.getParamAsString(request, "locnCode"));
		if (criteria.getLocnCode() == null || "".equals(criteria.getLocnCode())) {
			criteria.setLocnCode((String) request.getSession().getAttribute(SessionVariables.LOCN_CODE));	
		}
		criteria.setLogName((String) request.getSession().getAttribute(SessionVariables.LOG_NAME));
		criteria.setBarNum(0);
		if (URLEncryption.getParamAsInteger(request, "barNum") != null) {
			criteria.setBarNum(URLEncryption.getParamAsInteger(request, "barNum"));
		}
		criteria.setBarState("");
		if (URLEncryption.getParamAsString(request, "barState") != null) {
			criteria.setBarState(URLEncryption.getParamAsString(request, "barState"));
		}
		criteria.setReportFileName(REPORT_FILE_NAME);
		criteria.setReportTitle(REPORT_TITLE);
		criteria.setStartDate(URLEncryption.getParamAsDate(request, "startDate"));
		criteria.setEndDate(URLEncryption.getParamAsDate(request, "endDate"));
		criteria.setEndDatePlusOne(DateUtil.addDays(URLEncryption.getParamAsDate(request, "endDate"), 1)); //always add one day to the end date because it's setting the HH:MM:SS to 00:00:00 instead of 23:59:59
		criteria.setJudgeId(0);
		if (URLEncryption.getParamAsInteger(request, "judgeId") != null) {
			criteria.setJudgeId(URLEncryption.getParamAsInteger(request, "judgeId"));
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
		return new CorisReportAttorneyAppearanceReportGenerator(criteria).generateReport((List<AttyPresentBO>) getReportData(criteria));
	}
	
}
