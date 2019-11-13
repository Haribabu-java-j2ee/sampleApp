package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.acctfee.AcctFeeBO;
import gov.utcourts.coriscommon.dataaccess.acctwaiver.AcctWaiverBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.civilcase.CivilCaseBO;
import gov.utcourts.coriscommon.dataaccess.disptype.DispTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.summaryevent.SummaryEventBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.sp.GetCaseTitle;
import gov.utcourts.coriscommon.util.CorisSecurityCommon;
import gov.utcourts.coriscommon.util.DateUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.CorisReportNameIndexCivilReportCriteria;
import gov.utcourts.corisweb.report.CorisReportNameIndexCivilReportGenerator;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.CorisAttorneyUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;

/**
* Servlet implementation class CorisReportNameIndexCivilServlet
*/
@WebServlet("/CorisReportNameIndexCivilServlet") 
public class CorisReportNameIndexCivilServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisReportNameIndexCivilServlet.class.getName());
	private static String FILE_NAME = "CorisReportNameIndexCivilServlet";
	private static String REPORT_TITLE = "Name Index - Civil Report";
	private static String REPORT_FILE_NAME = "Name_Index_Civil_Report";
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CorisReportNameIndexCivilServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CorisReportNameIndexCivilReportCriteria myCriteria = null;
		try {
			myCriteria = (CorisReportNameIndexCivilReportCriteria) generateReportCriteria(request);
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
	 * @param CorisReportNameIndexCivilReportCriteria criteria
	 * @throws Exception
	 */
	private void find(HttpServletRequest request, HttpServletResponse response, CorisReportNameIndexCivilReportCriteria criteria) throws Exception {
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
			logger.error(FILE_NAME+" find(HttpServletRequest request, HttpServletResponse response, CorisReportNameIndexCivilReportCriteria criteria)", e);
		}
		getServletContext().getRequestDispatcher("/jsp/corisReportNameIndexCivil.jsp").forward(request, response);
	}
	
	/**
	 * Get the list of case types to populate the form dropdown list
	 * 
	 * @param HttpServletRequest request
	 * @param CorisReportNameIndexCivilReportCriteria criteria
	 * @return List<CaseTypeBO>
	 * @throws Exception
	 */
	public static List<CaseTypeBO> getCaseTypeList(CorisReportNameIndexCivilReportCriteria criteria) throws Exception {
		List<CaseTypeBO> caseTypeListBO = new CaseTypeBO(criteria.getCourtReadOnlyDB())
				.where(CaseTypeBO.CATEGORY, Exp.NOT_IN, new StringArrayDescriptor("R")) //exclude criminal category
				.where(CaseTypeBO.REMOVEDFLAG, "N")
				.orderBy(CaseTypeBO.DESCR)
				.limit(0)
				.search(CaseTypeBO.CASETYPE, CaseTypeBO.DESCR, CaseTypeBO.CATEGORY);
		return caseTypeListBO;
	}
	
	/**
	 * Emails the search results to the logged in user according to the format they selected (PDF, XLSX, etc.)
	 * 
	 * @param HttpServletResponse response
	 * @param CorisReportNameIndexCivilReportCriteria criteria
	 * @throws Exception
	 */
	private void emailReport(HttpServletResponse response, CorisReportNameIndexCivilReportCriteria criteria) throws Exception {
		try {
			this.emailReport(REPORT_TITLE, "Attached please find "+REPORT_TITLE+" in " + criteria.getReportformat() + " format.", this.generateReport(criteria), criteria);
		} catch (Exception e) {
			logger.error(FILE_NAME+" emailReport(CorisReportNameIndexCivilReportCriteria criteria)", e);
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
		CorisReportNameIndexCivilReportCriteria criteria = (CorisReportNameIndexCivilReportCriteria) filter;
		if (criteria.getReportDateStart() != null && criteria.getReportDateEnd() != null) {

			List<String> category = new ArrayList<String>();
			if ("V".equals(criteria.getCategory()) || "A".equals(criteria.getCategory())) { //Civil
				category.add("V");	
			}
			if ("D".equals(criteria.getCategory()) || "A".equals(criteria.getCategory())) { //Domestic
				category.add("D");	
			}
			if ("P".equals(criteria.getCategory())) { //don't include Probate unless it is specifically selected
				category.add("P");
			}
			if ("S".equals(criteria.getCategory()) || "A".equals(criteria.getCategory())) { //Small Claims
				category.add("S");	
			}
			
			KaseBO kaseBO = new KaseBO(criteria.getCourtReadOnlyDB()).as("kase")
					.includeTables(
							new PartyCaseBO(criteria.getCourtReadOnlyDB()).as("partyCaseDef")
									.where(PartyCaseBO.PARTYCODE, "DEF")
									.where(PartyCaseBO.PARTYCODE, "RES")
									.includeFields(PartyCaseBO.PARTYCODE),
							new PartyBO(criteria.getCourtReadOnlyDB()).as("partyDef")
									.includeFields(new FieldConstant("partyDef.last_name").as("def_last_name"), new FieldConstant("partyDef.first_name").as("def_first_name")),
							new PartyCaseBO(criteria.getCourtReadOnlyDB()).as("partyCasePla")
									.where(PartyCaseBO.PARTYCODE, "PLA")
									.where(PartyCaseBO.PARTYCODE, "PET")
									.includeFields(PartyCaseBO.NO_FIELDS),
							new PartyBO(criteria.getCourtReadOnlyDB()).as("partyPla")
									.includeFields(new FieldConstant("partyPla.last_name").as("pla_last_name"), new FieldConstant("partyPla.first_name").as("pla_first_name")),
							new CaseTypeBO(criteria.getCourtReadOnlyDB()).as("caseType")
									.where(CaseTypeBO.CATEGORY, Exp.IN, category)
									.includeFields(CaseTypeBO.DESCR.as("case_type_descr"), CaseTypeBO.CASETYPE, CaseTypeBO.CATEGORY),
							new PersonnelBO(criteria.getCourtReadOnlyDB()).as("personnel")
									.includeFields(PersonnelBO.LOGNAME),
							new DispTypeBO(criteria.getCourtReadOnlyDB()).as("dispType")
									.includeFields(DispTypeBO.DISPCODE, DispTypeBO.DESCR.as("disp_type_descr")),
							new CivilCaseBO(criteria.getCourtReadOnlyDB()).as("civilCase")
									.includeFields(CivilCaseBO.AMTINCONTROVERSY),
							new PersonnelBO(criteria.getCourtReadOnlyDB()).as("personnelJudge")
									.includeFields(new FieldConstant("personnelJudge.last_name").as("current_judge_last_name"), new FieldConstant("personnelJudge.first_name").as("current_judge_first_name")),
							new PersonnelBO(criteria.getCourtReadOnlyDB()).as("personnelComm")
									.includeFields(new FieldConstant("personnelComm.last_name").as("commissioner_last_name"), new FieldConstant("personnelComm.first_name").as("commissioner_first_name"))
					)
					.addJoin(JoinType.LEFT, "party_case partyCaseDef", new FieldConstant("partyCaseDef.int_case_num"), KaseBO.INTCASENUM)
					.addJoin(JoinType.LEFT, "party partyDef", new FieldConstant("partyDef.party_num"), new FieldConstant("partyCaseDef.party_num"))
					.addJoin(JoinType.LEFT, "party_case partyCasePla", new FieldConstant("partyCasePla.int_case_num"), KaseBO.INTCASENUM)
					.addJoin(JoinType.LEFT, "party partyPla", new FieldConstant("partyPla.party_num"), new FieldConstant("partyCasePla.party_num"))
					.addJoin(JoinType.LEFT, CaseTypeBO.TABLE.getTableName(), CaseTypeBO.CASETYPE, KaseBO.CASETYPE)
					.addJoin(JoinType.LEFT, "personnel personnel", new FieldConstant("personnel.userid_srl"), KaseBO.USERIDSRL)
					.addJoin(JoinType.LEFT, DispTypeBO.TABLE.getTableName(), DispTypeBO.DISPCODE, KaseBO.DISPCODE)
					.addJoin(JoinType.LEFT, CivilCaseBO.TABLE.getTableName(), CivilCaseBO.INTCASENUM, KaseBO.INTCASENUM)
					.addJoin(JoinType.LEFT, "personnel personnelJudge", new FieldConstant("personnelJudge.userid_srl"), KaseBO.ASSNJUDGEID)
					.addJoin(JoinType.LEFT, "personnel personnelComm", new FieldConstant("personnelComm.userid_srl"), KaseBO.ASSNCOMMID);

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
						case 0:	//Defendant Name
							kaseBO.orderBy("def_last_name", orderByPair.getDirectionType());
							kaseBO.orderBy("def_first_name", orderByPair.getDirectionType());
							break;
						case 1:	//Plaintiff Name
							kaseBO.orderBy("pla_last_name", orderByPair.getDirectionType());
							kaseBO.orderBy("pla_first_name", orderByPair.getDirectionType());
							break;
						case 2:	//Filed By
							kaseBO.orderBy(PersonnelBO.LOGNAME, orderByPair.getDirectionType());
							break;
						case 3: //Filed Date	
							kaseBO.orderBy(KaseBO.FILINGDATE, orderByPair.getDirectionType());
							break;
						case 4: //Filing Type
							kaseBO.orderBy(KaseBO.FILINGTYPE, orderByPair.getDirectionType());
							break;
						case 5:	//Case Number
							kaseBO.orderBy(KaseBO.CASENUM, orderByPair.getDirectionType());
							break;
						case 6: //Case Type	
							kaseBO.orderBy(CaseTypeBO.DESCR, orderByPair.getDirectionType());
							break;
						case 7:	//Case Title
							//not sorted here because this info is retrieved and sorted down below
							break;
						case 8:	//Amount in Controversy
							kaseBO.orderBy(CivilCaseBO.AMTINCONTROVERSY, orderByPair.getDirectionType());
							break;
						case 9:	//Waiver Status
							//not sorted here because this info is retrieved and sorted down below
							break;
						case 10: //Amount Paid/Credit
							//not sorted here because this info is retrieved and sorted down below
							break;
						case 11: //Disposition	
							kaseBO.orderBy(DispTypeBO.DESCR, orderByPair.getDirectionType());
							break;
						case 12: //Disposition Date
							kaseBO.orderBy(KaseBO.DISPDATE, orderByPair.getDirectionType());
							break;
						case 13: //Original Judge
							//not sorted here because this info is retrieved and sorted down below
							break;
						case 14: //Current Judge
							kaseBO.orderBy("current_judge_last_name", orderByPair.getDirectionType());
							kaseBO.orderBy("current_judge_first_name", orderByPair.getDirectionType());
							break;
						case 15: //Commissioner
							kaseBO.orderBy("commissioner_last_name", orderByPair.getDirectionType());
							kaseBO.orderBy("commissioner_first_name", orderByPair.getDirectionType());
							break;
					}
				}
			}
			
			reportList = kaseBO.search();
			
			//get a few additional pieces of info and sort the list, if it was selected by the user for one of these pieces
			int counter = 0;
			HashMap<String, Boolean> caseAccessLevel = CorisSecurityCommon.getUserSecurityLevels(criteria, null);
			for (KaseBO kaseTempBO : new ArrayList<KaseBO>(reportList)) {
				
				//get original judge name
				SummaryEventBO summaryEventBO = new SummaryEventBO(criteria.getCourtReadOnlyDB())
						.includeFields(SummaryEventBO.NO_FIELDS)
						.includeTables(
								new PersonnelBO(criteria.getCourtReadOnlyDB())
										.includeFields(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME)
										.where(PersonnelBO.TYPE, "J")
						)
						.addForeignKey(SummaryEventBO.KEY1, PersonnelBO.USERIDSRL)
						.where(SummaryEventBO.FUNCID, "JUDGHIST")
						.where(SummaryEventBO.INTCASENUM, kaseTempBO.getIntCaseNum())
						.where(
								SummaryEventBO.CREATEDATETIME, Exp.EQUALS, 
								Exp.select(new SummaryEventBO(criteria.getCourtReadOnlyDB())
										.includeFields(new FieldConstant("min(create_datetime)"))
										.includeTables(new PersonnelBO(criteria.getCourtReadOnlyDB()).includeFields(PersonnelBO.NO_FIELDS))
										.addForeignKey(SummaryEventBO.KEY1, PersonnelBO.USERIDSRL)
										.where(SummaryEventBO.FUNCID, "JUDGHIST")
										.where(SummaryEventBO.INTCASENUM, kaseTempBO.getIntCaseNum())
										.where(PersonnelBO.TYPE, "J")
								)
						)
						.find();
				reportList.get(counter).set("original_judge_last_name", summaryEventBO.get(PersonnelBO.LASTNAME));
				reportList.get(counter).set("original_judge_first_name", summaryEventBO.get(PersonnelBO.FIRSTNAME));

				//get the total of civil filing fees (Amount Paid/Credit)
				AccountBO accountBO = new AccountBO(criteria.getCourtReadOnlyDB())
						.includeFields(
								new Expression(Exp.SUM, Exp.LEFT_PARENTHESIS, AccountBO.AMTPAID, Exp.PLUS, AccountBO.AMTCREDIT, Exp.RIGHT_PARENTHESIS).as("amount_paid")
						)
						.includeTables(
								new AcctFeeBO(criteria.getCourtReadOnlyDB())
								.includeFields(AcctFeeBO.NO_FIELDS)
								.where(AcctFeeBO.FEECODE, Exp.IN, new StringArrayDescriptor("CA", "CD", "DC", "DI", "DM", "DT", "JE", "KP", "TS", "VS", 
										"DE", "DO", "JF", "SO", "A1", "A2", "A3", "A4", "A5", "A6", 
										"CP", "FP", "GB", "V1", "XP", "GS", "S1", "S2", "S3", "S4", 
										"S5", "S6", "SA", "TD", "AA", "AB", "AG", "AP", "AQ", "C1", 
										"C2", "C3", "C4", "DJ", "FD", "FT", "GA", "HL", "JC", "JD", 
										"K1", "K2", "K3", "M1", "M2", "M3", "OR", "PE", "R1", "R2", 
										"R3", "T1", "T2", "T3", "TL", "WA", "WR", "WX", "ER", "I1",
										"I2", "I3", "PT"))
						)
						.addForeignKey(AccountBO.ACCTNUM, AcctFeeBO.ACCTNUM)
						.where(AccountBO.INTCASENUM, kaseTempBO.getIntCaseNum())
						.find();
				reportList.get(counter).set("amount_paid", accountBO.get("amount_paid"));
				
				//get the highest priority waiver status code based on the order 1-R, 2-P, 3-D, 4-W, 5-G (1 highest, 5 lowest)
				List<AcctWaiverBO> acctWaiverBO = new AcctWaiverBO(criteria.getCourtReadOnlyDB())
						.includeFields(AcctWaiverBO.WAIVERSTATUS)
						.includeTables(
								new AccountBO(criteria.getCourtReadOnlyDB()),
								new KaseBO(criteria.getCourtReadOnlyDB())
										.where(KaseBO.INTCASENUM, kaseTempBO.getIntCaseNum())
						)
						.addForeignKey(AccountBO.ACCTNUM, AcctWaiverBO.ACCTNUM)
						.addForeignKey(KaseBO.INTCASENUM, AccountBO.INTCASENUM)
						.where(AcctWaiverBO.WAIVERSTATUS, Exp.NOT_EQUALS, "")
						.search();
				String waiver_status = "";
				int waiver_index = 0;
				int temp = 0;
				for (AcctWaiverBO acctWaiverTempBO : acctWaiverBO) {
					if ("R".equals(acctWaiverTempBO.getWaiverStatus())) {
						waiver_index = 1;
					} else if ("P".equals(acctWaiverTempBO.getWaiverStatus())) {
						waiver_index = 2;
					} else if ("D".equals(acctWaiverTempBO.getWaiverStatus())) {
						waiver_index = 3;
					} else if ("W".equals(acctWaiverTempBO.getWaiverStatus())) {
						waiver_index = 4;
					} else if ("G".equals(acctWaiverTempBO.getWaiverStatus())) {
						waiver_index = 5;
					} 
					if (waiver_index > temp) {
						temp = waiver_index;
					}
				}
				switch (temp) {
					case 1: waiver_status = "R"; break;
					case 2: waiver_status = "P"; break;
					case 3: waiver_status = "D"; break;
					case 4: waiver_status = "W"; break;
					case 5: waiver_status = "G"; break;
				}
				reportList.get(counter).set("waiver_status", waiver_status);
				
				//get Case Title
				reportList.get(counter).set("case_title", GetCaseTitle.getCaseTitle(criteria.getLogName(), kaseTempBO.getIntCaseNum(), criteria.getCourtType(), null));
				
				counter++;
			}
			
			//sort the list, if it was one of these additional info that couldn't be sorted in the main query
			if (orderByPairs != null) {
				for (final OrderByPair orderByPair : orderByPairs) {
					switch(orderByPair.getColumnPosition()) {
						case 7: //Case Title
							Collections.sort(reportList, new Comparator<KaseBO>() {
								@Override
								public int compare(KaseBO s1, KaseBO s2) {
									int stringCompare = 0;
									try {
										if ("ASC".equals(orderByPair.getDirectionType().name())) {
											stringCompare = ((String) s1.get("case_title")).compareTo((String) s2.get("case_title"));
										} else {
											stringCompare = ((String) s2.get("case_title")).compareTo((String) s1.get("case_title"));
										}
									} catch(Exception e) {
										logger.error(FILE_NAME+" case_title Collections.sort ", e);
									}
									return stringCompare;
								}
							});
							break;
						case 9:	//Waiver Status
							Collections.sort(reportList, new Comparator<KaseBO>() {
								@Override
								public int compare(KaseBO s1, KaseBO s2) {
									int stringCompare = 0;
									try {
										if ("ASC".equals(orderByPair.getDirectionType().name())) {
											stringCompare = ((String) s1.get("waiver_status")).compareTo((String) s2.get("waiver_status"));
										} else {
											stringCompare = ((String) s2.get("waiver_status")).compareTo((String) s1.get("waiver_status"));
										}
									} catch(Exception e) {
										logger.error(FILE_NAME+" waiver_status Collections.sort ", e);
									}
									return stringCompare;
								}
							});
							break;
						case 10:	//Amount Paid/Credit
							Collections.sort(reportList, new Comparator<KaseBO>() {
								@Override
								public int compare(KaseBO s1, KaseBO s2) {
									int stringCompare = 0;
									try {
										if (s1.get("amount_paid") != null && s2.get("amount_paid") != null) {
											if ("ASC".equals(orderByPair.getDirectionType().name())) {
												stringCompare = ((String) s1.get("amount_paid")).compareTo((String) s2.get("amount_paid"));
											} else {
												stringCompare = ((String) s2.get("amount_paid")).compareTo((String) s1.get("amount_paid"));
											}
										}
									} catch(Exception e) {
										logger.error(FILE_NAME+" amount_paid Collections.sort ", e);
									}
									return stringCompare;
								}
							});
							break;
						case 13: //Original Judge
							Collections.sort(reportList, new Comparator<KaseBO>() {
								@Override
								public int compare(KaseBO s1, KaseBO s2) {
									int stringCompare = 0;
									try {
										if ("ASC".equals(orderByPair.getDirectionType().name())) {
											stringCompare = ((String) s1.get("original_judge_last_name")).compareTo((String) s2.get("original_judge_last_name"));
										} else {
											stringCompare = ((String) s2.get("original_judge_last_name")).compareTo((String) s1.get("original_judge_last_name"));
										}
									} catch(Exception e) {
										logger.error(FILE_NAME+" original_judge_last_name Collections.sort ", e);
									}
									return stringCompare;
								}
							});
							break;
					}
				}
			}
			
		}
		return reportList;
	}
	
	/**
	 * Gets the footer totals for this report.
	 * 
	 * @param CorisReportNameIndexCivilReportCriteria criteria
	 * @param List<KaseBO> newReportList
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Integer> getFooterTotals(CorisReportNameIndexCivilReportCriteria criteria, List<KaseBO> reportList) throws Exception {
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
		CorisReportNameIndexCivilReportCriteria criteria = new CorisReportNameIndexCivilReportCriteria(request);
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
		criteria.setCategory(URLEncryption.getParamAsString(request, "reportCategory"));
		
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
		return new CorisReportNameIndexCivilReportGenerator(criteria).generateReport((List<KaseBO>) getReportData(criteria));
	}
	
}
