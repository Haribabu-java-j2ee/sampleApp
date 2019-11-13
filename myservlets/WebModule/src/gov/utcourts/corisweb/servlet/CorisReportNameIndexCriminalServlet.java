package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.disptype.DispTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.offense.OffenseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.severitytype.SeverityTypeBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.DateUtil;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.CorisReportNameIndexCriminalReportCriteria;
import gov.utcourts.corisweb.report.CorisReportNameIndexCriminalReportGenerator;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.CorisAttorneyUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;

/**
* Servlet implementation class CorisReportNameIndexCriminalServlet
*/
@WebServlet("/CorisReportNameIndexCriminalServlet") 
public class CorisReportNameIndexCriminalServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisReportNameIndexCriminalServlet.class.getName());
	private static String FILE_NAME = "CorisReportNameIndexCriminalServlet";
	private static String REPORT_TITLE = "Name Index - Criminal Report";
	private static String REPORT_FILE_NAME = "Name_Index_Criminal_Report";
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CorisReportNameIndexCriminalServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CorisReportNameIndexCriminalReportCriteria myCriteria = null;
		try {
			myCriteria = (CorisReportNameIndexCriminalReportCriteria) generateReportCriteria(request);
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
	}
	
	/**
	 * Searches for all attorneys on a case and displays the results.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param CorisReportNameIndexCriminalReportCriteria criteria
	 * @throws Exception
	 */
	private void find(HttpServletRequest request, HttpServletResponse response, CorisReportNameIndexCriminalReportCriteria criteria) throws Exception {
		try {
			request.setAttribute("locationDistrictList", CorisAttorneyUtil.getLocationList(request, "D"));
			request.setAttribute("locationJusticeList", CorisAttorneyUtil.getLocationList(request, "J"));
			request.setAttribute("caseTypeList", getCaseTypeList(criteria));
			request.setAttribute("caseType", criteria.getCaseType());
			List<KaseBO> reportList = (List<KaseBO>) getReportData(criteria);
			request.setAttribute("reportList", reportList);
			request.setAttribute("totalCounts", getFooterTotals(criteria, reportList));
		} catch (Exception e) {
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error(FILE_NAME+" find(HttpServletRequest request, HttpServletResponse response, CorisReportNameIndexCriminalReportCriteria criteria)", e);
		}
		getServletContext().getRequestDispatcher("/jsp/corisReportNameIndexCriminal.jsp").forward(request, response);
	}
	
	/**
	 * Get the list of case types to populate the form dropdown list
	 * 
	 * @param HttpServletRequest request
	 * @param CorisReportNameIndexCriminalReportCriteria criteria
	 * @return List<CaseTypeBO>
	 * @throws Exception
	 */
	public static List<CaseTypeBO> getCaseTypeList(CorisReportNameIndexCriminalReportCriteria criteria) throws Exception {
		List<CaseTypeBO> caseTypeListBO = new CaseTypeBO(criteria.getCourtReadOnlyDB())
				.where(new FindDescriptor(CaseTypeBO.RPTCATEGORY, Exp.IN, new StringArrayDescriptor("F","C"))) //F = Traffic, C = Criminal
				.where(CaseTypeBO.REMOVEDFLAG, "N")
				.orderBy(CaseTypeBO.DESCR)
				.limit(0)
				.search(CaseTypeBO.CASETYPE, CaseTypeBO.DESCR, CaseTypeBO.RPTCATEGORY);
		return caseTypeListBO;
	}
	
	/**
	 * Emails the search results to the logged in user according to the format they selected (PDF, XLSX, etc.)
	 * 
	 * @param HttpServletResponse response
	 * @param CorisReportNameIndexCriminalReportCriteria criteria
	 * @throws Exception
	 */
	private void emailReport(HttpServletResponse response, CorisReportNameIndexCriminalReportCriteria criteria) throws Exception {
		try {
			this.emailReport(REPORT_TITLE, "Attached please find "+REPORT_TITLE+" in " + criteria.getReportformat() + " format.", this.generateReport(criteria), criteria);
		} catch (Exception e) {
			logger.error(FILE_NAME+" emailReport(CorisReportNameIndexCriminalReportCriteria criteria)", e);
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
		List<KaseBO> reportList = null;
		CorisReportNameIndexCriminalReportCriteria criteria = (CorisReportNameIndexCriminalReportCriteria) filter;
		if (criteria.getReportDateStart() != null && criteria.getReportDateEnd() != null) {

			List<String> reportCategory = new ArrayList<String>();
			if ("C".equals(criteria.getReportCategory()) || "B".equals(criteria.getReportCategory())) {
				reportCategory.add("C");	
			}
			if ("F".equals(criteria.getReportCategory()) || "B".equals(criteria.getReportCategory())) {
				reportCategory.add("F");	
			}
			
			KaseBO kaseBO = new KaseBO(criteria.getCourtReadOnlyDB()).as("kase")
					.includeTables(
							new PartyCaseBO(criteria.getCourtReadOnlyDB()).as("partyCase")
									.where(PartyCaseBO.PARTYCODE, "DEF")
									.includeFields(PartyCaseBO.NO_FIELDS),
							new PartyBO(criteria.getCourtReadOnlyDB()).as("party")
									.includeFields(PartyBO.LASTNAME, PartyBO.FIRSTNAME),
							new CaseTypeBO(criteria.getCourtReadOnlyDB()).as("caseType")
									.where(CaseTypeBO.RPTCATEGORY, Exp.IN, reportCategory)
									.includeFields(CaseTypeBO.DESCR.as("case_type_descr"), CaseTypeBO.CASETYPE, CaseTypeBO.RPTCATEGORY),
							new PersonnelBO(criteria.getCourtReadOnlyDB()).as("personnel")
									.includeFields(PersonnelBO.LOGNAME),
							new DispTypeBO(criteria.getCourtReadOnlyDB()).as("dispType")
									.includeFields(DispTypeBO.DISPCODE, DispTypeBO.DESCR.as("disp_type_descr")),
							new CrimCaseBO(criteria.getCourtReadOnlyDB()).as("crimCase")
									.includeFields(CrimCaseBO.SHERIFFNUM)
					)
					.addJoin(JoinType.LEFT, PartyCaseBO.TABLE.getTableName(), PartyCaseBO.INTCASENUM, KaseBO.INTCASENUM)
					.addJoin(JoinType.LEFT, PartyBO.TABLE.getTableName(), PartyBO.PARTYNUM, PartyCaseBO.PARTYNUM)
					.addJoin(JoinType.LEFT, CaseTypeBO.TABLE.getTableName(), CaseTypeBO.CASETYPE, KaseBO.CASETYPE)
					.addJoin(JoinType.LEFT, PersonnelBO.TABLE.getTableName(), PersonnelBO.USERIDSRL, KaseBO.USERIDSRL)
					.addJoin(JoinType.LEFT, DispTypeBO.TABLE.getTableName(), DispTypeBO.DISPCODE, KaseBO.DISPCODE)
					.addJoin(JoinType.LEFT, CrimCaseBO.TABLE.getTableName(), CrimCaseBO.INTCASENUM, KaseBO.INTCASENUM);

					if ("F".equals(criteria.getTypeOfDate())) {
						kaseBO.where(Exp.LEFT_PARENTHESIS, KaseBO.FILINGDATE, 
						Exp.GREATER_THAN_OR_EQUAL_TO, criteria.getReportDateStart(), 
						Exp.AND, KaseBO.FILINGDATE, 
						Exp.LESS_THAN_OR_EQUAL_TO, criteria.getReportDateEndPlusOne(), Exp.RIGHT_PARENTHESIS);
					} else if ("D".equals(criteria.getTypeOfDate())) {
						kaseBO.where(Exp.LEFT_PARENTHESIS, KaseBO.DISPDATE, 
						Exp.GREATER_THAN_OR_EQUAL_TO, criteria.getReportDateStart(), 
						Exp.AND, KaseBO.DISPDATE, 
						Exp.LESS_THAN_OR_EQUAL_TO, criteria.getReportDateEndPlusOne(), Exp.RIGHT_PARENTHESIS);
						kaseBO.where(KaseBO.DISPCODE, Exp.IS_NOT_NULL);
					}
					
					kaseBO.where(KaseBO.LOCNCODE, criteria.getLocnCode())
					.where(KaseBO.COURTTYPE, criteria.getCourtType())
					.where(KaseBO.CASETYPE, Exp.IN, criteria.getCaseType())
					.includeFields(KaseBO.INTCASENUM, KaseBO.CASENUM, KaseBO.CASETYPE, KaseBO.FILINGDATE, KaseBO.FILINGTYPE, KaseBO.DISPDATE, KaseBO.CASESECURITY);
			
			// order by
			List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(filter);
			if (orderByPairs != null) {
				for (OrderByPair orderByPair : orderByPairs) {
					switch(orderByPair.getColumnPosition()) {
						case 0:	
							kaseBO.orderBy(PartyBO.LASTNAME);
							kaseBO.orderBy(PartyBO.FIRSTNAME, orderByPair.getDirectionType());
							break;
						case 1:	
							kaseBO.orderBy(PersonnelBO.LOGNAME, orderByPair.getDirectionType());
							break;
						case 2:	
							kaseBO.orderBy(KaseBO.FILINGTYPE, orderByPair.getDirectionType());
							break;
						case 3:	
							kaseBO.orderBy(KaseBO.CASENUM, orderByPair.getDirectionType());
							break;
						case 4:	
							kaseBO.orderBy(CaseTypeBO.DESCR, orderByPair.getDirectionType());
							break;
						case 5:	
							kaseBO.orderBy(CrimCaseBO.SHERIFFNUM, orderByPair.getDirectionType());
							break;
						case 6:	
							kaseBO.orderBy(KaseBO.FILINGDATE, orderByPair.getDirectionType());
							break;
						case 7:	
							kaseBO.orderBy(DispTypeBO.DESCR, orderByPair.getDirectionType());
							break;
						case 8:
							kaseBO.orderBy(KaseBO.DISPDATE, orderByPair.getDirectionType());
							break;
					}
				}
			}
			reportList = kaseBO.search();
			
			int counter = 0;
			for (KaseBO kaseTempBO : new ArrayList<KaseBO>(reportList)) {
				//get the charge with the highest severity
				ChargeBO chargeListBO = new ChargeBO(criteria.getCourtReadOnlyDB())
						.includeFields(ChargeBO.NO_FIELDS)
						.includeTables(
								new OffenseBO(criteria.getCourtReadOnlyDB())
										.includeFields(OffenseBO.DESCR.as("offense_descr")),
								new SeverityTypeBO(criteria.getCourtReadOnlyDB())
										.includeFields(SeverityTypeBO.SEVERITYLVL)
										.orderBy(SeverityTypeBO.SEVERITYLVL, DirectionType.DESC)
						)
						.addForeignKey(OffenseBO.OFFSVIOLCODE, ChargeBO.OFFSVIOLCODE)
						.addForeignKey(SeverityTypeBO.SEVERITYCODE, ChargeBO.SEVERITY)
						.where(ChargeBO.INTCASENUM, kaseTempBO.getIntCaseNum())
						.orderBy(ChargeBO.SEQ, DirectionType.ASC)
						.limit(1)
						.find();
				//get the number of other charges and put an asterisk in front of the description if there are multiple charges
				int chargeCount = new ChargeBO(criteria.getCourtReadOnlyDB())
						.includeTables(new SeverityTypeBO(criteria.getCourtReadOnlyDB()))
						.addForeignKey(ChargeBO.SEVERITY, SeverityTypeBO.SEVERITYCODE)
						.where(ChargeBO.INTCASENUM, kaseTempBO.getIntCaseNum())
						.find(ChargeBO.INTCASENUM.count()).getCount();
				reportList.get(counter++).set("offense_descr", ((chargeCount > 1)?"*":"")+chargeListBO.get("offense_descr"));
			}
			
		}
		return reportList;
	}

	/**
	 * Gets the footer totals for this report.
	 * 
	 * @param CorisReportNameIndexCriminalReportCriteria criteria
	 * @param List<KaseBO> newReportList
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Integer> getFooterTotals(CorisReportNameIndexCriminalReportCriteria criteria, List<KaseBO> reportList) throws Exception {
		Map<String, Integer> totalCounts = new HashMap<String, Integer>();
		if (reportList != null && reportList.size() > 0) {
			int totalCount = 0;
			for (CaseTypeBO caseTypesBO : getCaseTypeList(criteria)) {
				for (KaseBO kaseTotalsBO : reportList) {
					if (kaseTotalsBO.get(CaseTypeBO.CASETYPE).equals(caseTypesBO.getCaseType())) {
						if (totalCounts.get(caseTypesBO.getCaseType()) != null) {
							totalCounts.put(caseTypesBO.getCaseType(), Integer.valueOf(totalCounts.get(caseTypesBO.getCaseType()) + 1));
						} else {
							totalCounts.put(caseTypesBO.getCaseType(), Integer.valueOf(1));
						}
						totalCount++;
					}
				}
			}
		}
		return totalCounts;
	}	
	
	/**
	 * Sets all the criteria from the incoming form submission (like court type, location) so it can be used when generating or emailing a report. 
	 * 
	 * @param HttpServletRequest request
	 * @return ReportBaseSearchCriteria
	 * @throws Exception 
	 */
	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisReportNameIndexCriminalReportCriteria criteria = new CorisReportNameIndexCriminalReportCriteria(request);
		criteria.setMode(PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode")));
		criteria.setCourtType(URLEncryption.getParamAsString(request, "courtType"));
		if (criteria.getCourtType() == null || "".equals(criteria.getCourtType())) {
			criteria.setCourtType((String) request.getSession().getAttribute(SessionVariables.COURT_TYPE));	
		}
		criteria.setLocnCode(URLEncryption.getParamAsString(request, "locnCode"));
		if (criteria.getLocnCode() == null || "".equals(criteria.getLocnCode())) {
			criteria.setLocnCode((String) request.getSession().getAttribute(SessionVariables.LOCN_CODE));	
		}
		criteria.setReportFileName(REPORT_FILE_NAME);
		criteria.setReportTitle(REPORT_TITLE);
		criteria.setReportDateStart(URLEncryption.getParamAsDate(request, "reportDateStart"));
		criteria.setReportDateEnd(URLEncryption.getParamAsDate(request, "reportDateEnd"));
		criteria.setReportDateEndPlusOne(DateUtil.addDays(URLEncryption.getParamAsDate(request, "reportDateEnd"), 1)); //always add one day to the end date because it's setting the HH:MM:SS to 00:00:00 instead of 23:59:59
		criteria.setDateFormat(Constants.dateFormatCoris);
		criteria.setSortColumn(URLEncryption.getParamAsInt(request, "sortColumn"));
		criteria.setSortDirection(URLEncryption.getParamAsString(request, "sortDirection"));
		criteria.setTypeOfDate(URLEncryption.getParamAsString(request, "typeOfDate"));
		criteria.setReportCategory(URLEncryption.getParamAsString(request, "reportCategory"));
		
		List<String> caseType = new ArrayList<String>();
		String[] items = request.getParameterValues("caseType");
		if(items != null) {
			for(int loopIndex = 0; loopIndex < items.length; loopIndex++){
				caseType.add(items[loopIndex]);
			}
		} else {
			caseType.add("");
		}
		criteria.setCaseType(caseType);
		
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
		return new CorisReportNameIndexCriminalReportGenerator(criteria).generateReport((List<KaseBO>) getReportData(criteria));
	}
	
}
