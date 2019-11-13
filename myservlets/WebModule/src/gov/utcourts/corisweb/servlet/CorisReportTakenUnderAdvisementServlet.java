package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.tracking.TrackingBO;
import gov.utcourts.coriscommon.dataaccess.trackingtype.TrackingTypeBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.TakenUnderAdvisementReportGenerator;
import gov.utcourts.corisweb.report.TakenUnderAdvisementReportSearchCriteria;
import gov.utcourts.corisweb.report.TrackingReportUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/**
 * Servlet implementation class CorisReportTakenUnderAdvisementServlet
 */
@WebServlet("/CorisReportTakenUnderAdvisementServlet")
public class CorisReportTakenUnderAdvisementServlet extends ReportBaseServlet {
	
	private static final long serialVersionUID = 5623441112L;
	
	public static final String SEARCH_PAGE = "/jsp/corisReportTakenUnderAdvisement.jsp";
	public static final String RESULTS_PAGE = "/jsp/corisReportTakenUnderAdvisementResults.jsp";
	
	private static Logger logger = Logger.getLogger(CorisReportTakenUnderAdvisementServlet.class.getName());
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CorisReportTakenUnderAdvisementServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
		try {
			TakenUnderAdvisementReportSearchCriteria criteria = this.generateReportCriteria(request);
			switch (mode) {
				case SAVE:
					saveReport(request, response, criteria);
					break;
				case EMAIL:
					emailReport(criteria);
					break;
				case FIND:
					request.setAttribute("searchResults", getReportData(criteria));
					request.getRequestDispatcher(RESULTS_PAGE).forward(request, response);
					break;
				case OPEN:
					request.getRequestDispatcher(RESULTS_PAGE).forward(request, response);
					break;
				case DISPLAYSEARCHFORM:
					changeJudgeList(request, response, criteria);
					break;
				default:
					getLocnList(request, criteria);
					getServletContext().getRequestDispatcher(SEARCH_PAGE).forward(request, response);
					break;
			}
			
		} catch (Exception e) {
			String errorMessage = "Failed to " + mode + " taken under advisement report: " + e.getMessage(); 
			logger.error(errorMessage);
			
			// error handling
			JSONObject retValObj = new JSONObject();
			retValObj.put("success", false);
			retValObj.put("errorMessage", errorMessage);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(retValObj.toString());
		}
	}

	@Override
	TakenUnderAdvisementReportSearchCriteria generateReportCriteria(HttpServletRequest request)	throws Exception {
		TakenUnderAdvisementReportSearchCriteria criteria = new TakenUnderAdvisementReportSearchCriteria(request);
		criteria.setReportFileName("takenUnderAdvisement");
		criteria.setDateFrom(URLEncryption.getParamAsDate(request, "dateFrom"));
		criteria.setDateTo(URLEncryption.getParamAsDate(request, "dateTo"));
		criteria.setJudgeComm(URLEncryption.getParamAsString(request, "judgeComm"));
		return criteria;
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		@SuppressWarnings("unchecked")
		List<TrackingBO> searchResults = (List<TrackingBO>) getReportData(criteria);
		return new TakenUnderAdvisementReportGenerator(criteria).generateReport(searchResults);
	}
	
	/* 
	 * @param TakenUnderAdvisementReportSearchCriteria criteria
	 * @throws Exception
	 */
	private void emailReport(TakenUnderAdvisementReportSearchCriteria criteria) throws Exception {
		String subject = TakenUnderAdvisementReportGenerator.REPORT_NAME;
		String content = "Attached please find the " + TakenUnderAdvisementReportGenerator.REPORT_NAME + " in " + criteria.getReportformat() + "format";
		this.emailReport(subject, content, this.generateReport(criteria), criteria);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return getSearchResults((TakenUnderAdvisementReportSearchCriteria) criteria);
	}
	
	public List<TrackingBO> getSearchResults(TakenUnderAdvisementReportSearchCriteria reportCriteria) throws Exception {
		List<TrackingBO> searchResults = null;
		try {
			String READ_ONLY_CONNECTION = reportCriteria.getCourtReadOnlyDB();
			
			KaseBO kaseBO = new KaseBO(READ_ONLY_CONNECTION).as("kase").includeFields(KaseBO.CASENUM, KaseBO.CASETYPE);
			PersonnelBO judgeBO = new PersonnelBO(READ_ONLY_CONNECTION).as("pjudge").includeFields(new FieldConstant("pjudge.logname").as("judge_logname"), new FieldConstant("pjudge.last_name").as("judge_lastname"), new FieldConstant("pjudge.first_name").as("judge_firstname"));
			
			// judge/comm
			if (!TextUtil.isEmpty(reportCriteria.getJudgeComm())) {
				
				if ("unassign".equals(reportCriteria.getJudgeComm())) {
					reportCriteria.setReportName(TakenUnderAdvisementReportGenerator.REPORT_NAME + " - Unassigned Cases");
					
					kaseBO.where(KaseBO.ASSNJUDGEID, Exp.IS_NULL);
				} else {
					reportCriteria.setReportName(TakenUnderAdvisementReportGenerator.REPORT_NAME + " - " + TrackingReportUtil.getJudgeCommName(reportCriteria, reportCriteria.getJudgeComm()));
					
					judgeBO.where(Exp.literal("pjudge.logname"), Exp.EQUALS, reportCriteria.getJudgeComm());
				}
				
			} else {
				reportCriteria.setReportName(TakenUnderAdvisementReportGenerator.REPORT_NAME);
				
				// outer join on judge, if not specified
				judgeBO.setOuter();
			}
			
			TrackingBO searchCriteria = new TrackingBO(READ_ONLY_CONNECTION).as("tracking")
			// .limit(100)
			.includeFields(TrackingBO.TRACKCODE, TrackingBO.REVIEWDATE, TrackingBO.CREATEDATETIME)
			.includeTables(
				kaseBO,
				new TrackingTypeBO(READ_ONLY_CONNECTION).as("tracking_type").includeFields(TrackingTypeBO.DESCR).setOuter(),
				new ProfileBO(READ_ONLY_CONNECTION).as("profile").includeFields(ProfileBO.COURTTITLE).setOuter(),
				new PartyCaseBO(READ_ONLY_CONNECTION).as("party_case").includeFields(PartyCaseBO.PARTYNUM),
				new PartyBO(READ_ONLY_CONNECTION).as("party").includeFields(PartyBO.LASTNAME, PartyBO.FIRSTNAME),
				new PersonnelBO(READ_ONLY_CONNECTION).as("pclerk").includeFields(new FieldConstant("pclerk.logname").as("clerk_logname")).setOuter(),
				judgeBO
			)
			.setOuter(new PartyCaseBO(READ_ONLY_CONNECTION), new PartyBO(READ_ONLY_CONNECTION))
			.addForeignKey(KaseBO.INTCASENUM, TrackingBO.INTCASENUM)
			.addForeignKey(TrackingTypeBO.TRACKCODE, TrackingBO.TRACKCODE)
			.addForeignKey(TrackingTypeBO.LOCNCODE, KaseBO.LOCNCODE)
			.addForeignKey(TrackingTypeBO.COURTTYPE, KaseBO.COURTTYPE)
			.addForeignKey(ProfileBO.LOCNCODE, KaseBO.LOCNCODE)
			.addForeignKey(ProfileBO.COURTTYPE, KaseBO.COURTTYPE)
			.addForeignKey(PartyCaseBO.INTCASENUM, KaseBO.INTCASENUM)
			.addForeignKey(PartyBO.PARTYNUM, PartyCaseBO.PARTYNUM)
			.addForeignKey(new TableAndFieldDescriptor("pclerk", PersonnelBO.USERIDSRL), new TableAndFieldDescriptor(TrackingBO.CLERKID))
			.addForeignKey(new TableAndFieldDescriptor("pjudge", PersonnelBO.USERIDSRL), new TableAndFieldDescriptor(KaseBO.ASSNJUDGEID))
			.where(PartyCaseBO.PARTYCODE, Exp.IN, new StringArrayDescriptor("DEF", "PET", "PLA", "RES"))
			.where(PartyCaseBO.PARTYNUM, Exp.IN, 
				Exp.select(new PartyCaseBO(READ_ONLY_CONNECTION).includeFields(new FieldConstant("min(party_num)").as("min")).where(PartyCaseBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM).where(PartyCaseBO.PARTYCODE, Exp.IN, new StringArrayDescriptor("DEF", "PET", "PLA", "RES")))
			)
			.where(TrackingBO.ENDDATETIME, Exp.IS_NULL)
			.where(KaseBO.LOCNCODE, reportCriteria.getLocnCode())
			.where(KaseBO.COURTTYPE, reportCriteria.getCourtType())
			.where(TrackingBO.TRACKCODE, "TUA");
			
			if (reportCriteria.getDateFrom() != null)
				searchCriteria.where(TrackingBO.REVIEWDATE, Exp.GREATER_THAN_OR_EQUAL_TO, reportCriteria.getDateFrom());
			
			if (reportCriteria.getDateTo() != null)
				searchCriteria.where(TrackingBO.REVIEWDATE, Exp.LESS_THAN_OR_EQUAL_TO, reportCriteria.getDateTo());
		
			// order by
			List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(reportCriteria);
			if (orderByPairs != null) {
				for (OrderByPair orderByPair : orderByPairs) {
					switch(orderByPair.getColumnPosition()) {
						case 0:	searchCriteria.orderBy(TrackingBO.REVIEWDATE, 		orderByPair.getDirectionType()); 	
								break;
						case 1:	searchCriteria.orderBy(KaseBO.CASENUM, 				orderByPair.getDirectionType()); 	
								break;
						case 2:	searchCriteria.orderBy(KaseBO.CASETYPE, 			orderByPair.getDirectionType()); 	
								break;
						case 3:	searchCriteria.orderBy(PartyBO.LASTNAME, 			orderByPair.getDirectionType()); 	
								searchCriteria.orderBy(PartyBO.FIRSTNAME, 			orderByPair.getDirectionType()); 	
								break;
						case 4:	searchCriteria.orderBy(TrackingBO.CREATEDATETIME, 	orderByPair.getDirectionType()); 	
								break;
						case 5:	searchCriteria.orderBy("pclerk.logname", 			orderByPair.getDirectionType());
								break;
						case 6:	searchCriteria.orderBy("pjudge.logname", 			orderByPair.getDirectionType()); 	
								break;
						case 7:	searchCriteria.orderBy(TrackingBO.CREATEDATETIME, 	orderByPair.getDirectionType()); 	
								break;
					}
				}
			}
			
			searchResults = searchCriteria.search();
		} catch (Exception e) {
			logger.error("Exception in search: " + e.getMessage());
			throw e;
		}
		
		return searchResults;
	}
	
	public void getLocnList(HttpServletRequest request, TakenUnderAdvisementReportSearchCriteria criteria) throws Exception {
		try {
			List<LocationBO> distLocationList = getLocations("D", criteria.getLogName());
			List<LocationBO> justLocationList = getLocations("J", criteria.getLogName());
			request.setAttribute("distLocationList", distLocationList);
			request.setAttribute("justLocationList", justLocationList);
			List<LocationBO> allLocations = new ArrayList<LocationBO>(distLocationList);
			allLocations.addAll(new ArrayList<LocationBO>(justLocationList));
			Collections.sort(allLocations, new Comparator<LocationBO>() {
				@Override
				public int compare(LocationBO s1, LocationBO s2) {
					return s1.getLocnDescr().compareTo(s2.getLocnDescr());
				}
			});
			request.setAttribute("locationList", allLocations);
			
			distLocationList = null;
			justLocationList = null;
			allLocations = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private static List<LocationBO> getLocations(String courtType, String logName) throws Exception {
		return new LocationBO(courtType).setOuter()
		.includeTables(
			new PersonnelBO(courtType).where(PersonnelBO.COURTTYPE, courtType).where(PersonnelBO.LOGNAME, logName)
		)
		.addForeignKey(PersonnelBO.LOCNCODE, LocationBO.LOCNCODE)
		.orderBy(LocationBO.LOCNDESCR)
		.search(LocationBO.LOCNCODE, LocationBO.LOCNDESCR);
	}
	
	private void changeJudgeList(HttpServletRequest request, HttpServletResponse response, TakenUnderAdvisementReportSearchCriteria criteria) throws Exception {
		try {
			JSONObject retValObj = new JSONObject();
			
			List<PersonnelBO> judgeAndComms = getJudgeCommList(criteria.getCourtType(), criteria.getLocnCode());
			
			// unassigned
			judgeAndComms.add(
				new PersonnelBO(criteria.getCourtType()).setLastName("Unassigned").setFirstName("").setType("unassign").setLogname("unassign")
			);
			
			JSONArray retValArr = new JSONArray();
			for (PersonnelBO judgeComms : judgeAndComms) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("logName", judgeComms.getLogname());
				jsonObject.put("type", judgeComms.getType());
				jsonObject.put("useridSrl", judgeComms.getUseridSrl());
				jsonObject.put("firstName", judgeComms.getFirstName());
				jsonObject.put("lastName", judgeComms.getLastName());
				retValArr.add(jsonObject);
			}
			retValObj.put("judgeCommList", retValArr);
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "0");
			response.getWriter().write(retValObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private List<PersonnelBO> getJudgeCommList(String courtType, String locnCode) throws Exception {
		return new PersonnelBO(courtType)
		.where(PersonnelBO.TYPE, Exp.IN, new StringArrayDescriptor("J", "C"))
		.where(PersonnelBO.REMOVEDFLAG, "N")
		.where(PersonnelBO.COURTTYPE, courtType)
		.where(PersonnelBO.LOCNCODE, locnCode)
		.orderBy(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME)
		.search(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME, PersonnelBO.LOGNAME, PersonnelBO.USERIDSRL, PersonnelBO.TYPE);
	}
	
}
