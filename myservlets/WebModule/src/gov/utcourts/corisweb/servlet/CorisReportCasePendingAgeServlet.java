package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.judgment.JudgmentBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.stay.StayBO;
import gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.DateUtil;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CasePendingAgeReportGenerator;
import gov.utcourts.corisweb.report.CasePendingAgeReportSearchCriteria;
import gov.utcourts.corisweb.report.CasePendingAgeReportSearchCriteria.JudgeCommInfo;
import gov.utcourts.corisweb.report.CasePendingAgeReportSearchCriteria.Record;
import gov.utcourts.corisweb.report.CasePendingAgeReportSearchCriteria.ReportCounter;
import gov.utcourts.corisweb.report.CasePendingSP;
import gov.utcourts.corisweb.report.CasePendingSP.ReturnValues;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
 * Servlet implementation class CorisReportCasePendingAgeServlet
 */
@WebServlet("/CorisReportCasePendingAgeServlet")
public class CorisReportCasePendingAgeServlet extends ReportBaseServlet {
	
	private static final long serialVersionUID = 1245441155L;
	
	public static final String SEARCH_PAGE = "/jsp/corisReportCasePendingAge.jsp";
	
	private static Logger logger = Logger.getLogger(CorisReportCasePendingAgeServlet.class.getName());
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CorisReportCasePendingAgeServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageMode mode = PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode"));
		try {
			CasePendingAgeReportSearchCriteria criteria = this.generateReportCriteria(request);
			switch (mode) {
				case SAVE:
					saveReport(request, response, criteria);
					break;
				case EMAIL:
					emailReport(criteria);
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
			String errorMessage = "Failed to " + mode + " age of pending caseload report: " + e.getMessage(); 
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
	CasePendingAgeReportSearchCriteria generateReportCriteria(HttpServletRequest request)	throws Exception {
		CasePendingAgeReportSearchCriteria criteria = new CasePendingAgeReportSearchCriteria(request);
		criteria.setReportFileName("casePendingAge");
		criteria.setPendingDate(URLEncryption.getParamAsDate(request, "pendingDate"));
		criteria.setIncludeUnassigned(URLEncryption.getParamAsBoolean(request, "includeUnassigned"));
		criteria.setActive(URLEncryption.getParamAsString(request, "m_active"));
		criteria.setJudgeComm(URLEncryption.getParamAsString(request, "multipleJudges"));
		criteria.setSessionUserId(
			new PersonnelBO(criteria.getCourtType())
			.where(PersonnelBO.LOGNAME, criteria.getLogName())
			.where(PersonnelBO.LOCNCODE, criteria.getLocnCode())
			.where(PersonnelBO.COURTTYPE, criteria.getCourtType())
			.find()
			.getUseridSrl()
		);
		return criteria;
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		@SuppressWarnings("unchecked")
		List<ReportCounter> searchResults = (List<ReportCounter>) getReportData(criteria);
		return new CasePendingAgeReportGenerator(criteria).generateReport(searchResults);
	}
	
	/* 
	 * @param CasePendingAgeReportSearchCriteria criteria
	 * @throws Exception
	 */
	private void emailReport(CasePendingAgeReportSearchCriteria criteria) throws Exception {
		String subject = CasePendingAgeReportGenerator.REPORT_NAME;
		String content = "Attached please find the " + CasePendingAgeReportGenerator.REPORT_NAME + " in " + criteria.getReportformat() + "format";
		this.emailReport(subject, content, this.generateReport(criteria), criteria);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return getSearchResults((CasePendingAgeReportSearchCriteria) criteria);
	}
	
	public List<ReportCounter> getSearchResults(CasePendingAgeReportSearchCriteria criteria) throws Exception {
		List<ReportCounter> reportCounters = new ArrayList<ReportCounter>();
		try {
			 Date m_end_date = criteria.getPendingDate();
			 
			 String db = criteria.getCourtReadOnlyDB();
			 List<CaseTypeBO> c_case_type = new CaseTypeBO(db).where(CaseTypeBO.CASETYPE, Exp.NOT_IN, new StringArrayDescriptor("NA","UR","TJ","AW","TP","UF","UC","FJ","AJ","FD","TL","HL","SL","WL")).orderBy(CaseTypeBO.RPTCATEGORY, CaseTypeBO.DESCR).search(CaseTypeBO.RPTCATEGORY, CaseTypeBO.CASETYPE, CaseTypeBO.DESCR);
		 
			 List<JudgeCommInfo> judgeCommmInfos = criteria.getJudgeCommInfos();
			 for (JudgeCommInfo judge : judgeCommmInfos) {    

				 ReportCounter rc = new ReportCounter();
				 rc.mcasecount_t = -1;
		 	 
				 for (CaseTypeBO caseType : c_case_type) {
					 rc.mcasecount_t++;
		    	 
					 if (rc.mcasecount_t >= rc.mcasecount_x) 
						 throw new Exception("Exception: Too many case type rows.");
		    	 
					 rc.ma_casecount.add(new Record());
					 rc.ma_casecount.get(rc.mcasecount_t).rpt_category = caseType.getRptCategory();
					 if ("C".equalsIgnoreCase(caseType.getRptCategory())) {
						 rc.ma_casecount.get(rc.mcasecount_t).rpt_cat_name = "CRIMINAL";
					 } else if ("D".equalsIgnoreCase(caseType.getRptCategory())) {
						 rc.ma_casecount.get(rc.mcasecount_t).rpt_cat_name = "DOMESTIC";
					 } else if ("F".equalsIgnoreCase(caseType.getRptCategory())) {	 
						 rc.ma_casecount.get(rc.mcasecount_t).rpt_cat_name = "TRAFFIC";
					 } else if ("G".equalsIgnoreCase(caseType.getRptCategory())) {
						 rc.ma_casecount.get(rc.mcasecount_t).rpt_cat_name = "GENERAL CIVIL";
					 } else if ("J".equalsIgnoreCase(caseType.getRptCategory())) {
						 rc.ma_casecount.get(rc.mcasecount_t).rpt_cat_name = "JUDGMENTS";
					 } else if ("P".equalsIgnoreCase(caseType.getRptCategory())) {
						 rc.ma_casecount.get(rc.mcasecount_t).rpt_cat_name = "PROBATE";
					 } else if ("R".equalsIgnoreCase(caseType.getRptCategory())) {
						 rc.ma_casecount.get(rc.mcasecount_t).rpt_cat_name = "PROPERTY RIGHTS";
					 } else if ("T".equalsIgnoreCase(caseType.getRptCategory())) {
						 rc.ma_casecount.get(rc.mcasecount_t).rpt_cat_name = "TORT";
					 } else {
						 rc.ma_casecount.get(rc.mcasecount_t).rpt_cat_name = "UNKNOWN";
					 }
		    	 
					 rc.ma_casecount.get(rc.mcasecount_t).case_type = caseType.getCaseType();
					 rc.ma_casecount.get(rc.mcasecount_t).descr = caseType.getDescr();
				 }
		     
		         int m_userid_srl = criteria.getSessionUserId();
		         String m_active = ((CasePendingAgeReportSearchCriteria) criteria).getActive();
						
		         KaseBO sqlstmt = new KaseBO(db).as("c1")
		         .includeFields(KaseBO.CASETYPE, KaseBO.ASSNJUDGEID, KaseBO.ASSNCOMMID, KaseBO.INTCASENUM, KaseBO.FILINGDATE, KaseBO.DISPDATE, KaseBO.DISPID, KaseBO.DEBTCOLLDATE, KaseBO.DEBTCOLLECTION)
		         .includeTables(
		        	new PersonnelBO(db).as("personnel").where(PersonnelBO.USERIDSRL, m_userid_srl).includeFields(PersonnelBO.NO_FIELDS),
					new CaseTypeBO(db).as("case_type").includeFields(CaseTypeBO.CASETYPE, CaseTypeBO.CATEGORY) 
		         )
		         .where(KaseBO.LOCNCODE, Exp.EQUALS, PersonnelBO.LOCNCODE)
		         .where(KaseBO.COURTTYPE, Exp.EQUALS, PersonnelBO.COURTTYPE)
		         .where(KaseBO.FILINGDATE, Exp.LESS_THAN_OR_EQUAL_TO, m_end_date)
		         .where(KaseBO.CASETYPE, Exp.NOT_IN, new StringArrayDescriptor("NA","UR","TJ","AW","TP","UF","UC","FJ","AJ","FD","TL","HL","SL","WL"))
		         .addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE);

		         if ("a".equalsIgnoreCase(m_active)) {
	        	 
		        	 // all cases
		        	 sqlstmt.where(
						Exp.LEFT_PARENTHESIS,
							KaseBO.DISPDATE, Exp.GREATER_THAN, m_end_date,
							Exp.OR,
							Exp.LEFT_PARENTHESIS,
								KaseBO.DISPDATE, Exp.IS_NULL,
								Exp.AND,
								Exp.LEFT_PARENTHESIS,
									CaseTypeBO.CATEGORY, Exp.NOT_EQUALS, "R",
									Exp.AND,
									Exp.LEFT_PARENTHESIS,
										Exp.NOT_EXISTS, Exp.select(new JudgmentBO(db).where(KaseBO.INTCASENUM, Exp.EQUALS, JudgmentBO.INTCASENUM)),
										Exp.OR,
										Exp.EXISTS, Exp.select(new JudgmentBO(db).where(KaseBO.INTCASENUM, Exp.EQUALS, JudgmentBO.INTCASENUM).where(Exp.date(JudgmentBO.JDMTDATETIME), Exp.GREATER_THAN, m_end_date)),
									Exp.RIGHT_PARENTHESIS,
								Exp.RIGHT_PARENTHESIS,	
								Exp.OR,
								Exp.LEFT_PARENTHESIS,
									CaseTypeBO.CATEGORY, Exp.EQUALS, "R",
									Exp.AND,
									Exp.LEFT_PARENTHESIS,
										Exp.EXISTS, Exp.select(new ChargeBO(db).where(KaseBO.INTCASENUM, Exp.EQUALS, ChargeBO.INTCASENUM).where(ChargeBO.JDMTDATE, Exp.GREATER_THAN, m_end_date)),
										Exp.OR,
										Exp.EXISTS, Exp.select(new ChargeBO(db).where(KaseBO.INTCASENUM, Exp.EQUALS, ChargeBO.INTCASENUM).where(ChargeBO.JDMTDATE, Exp.IS_NULL)),
									Exp.RIGHT_PARENTHESIS,	
								Exp.RIGHT_PARENTHESIS,
							Exp.RIGHT_PARENTHESIS,
						Exp.RIGHT_PARENTHESIS
					);
		        	 
		         } else if ("v".equalsIgnoreCase(m_active)) {
	        	 
	        	 	// active cases
					sqlstmt.where(
						Exp.LEFT_PARENTHESIS,
							KaseBO.DISPDATE, Exp.GREATER_THAN, m_end_date,
							Exp.OR,
							Exp.LEFT_PARENTHESIS,
								KaseBO.DISPDATE, Exp.IS_NULL,
								Exp.AND,
								Exp.LEFT_PARENTHESIS,
									CaseTypeBO.CATEGORY, Exp.NOT_EQUALS, "R",
									Exp.AND,
									Exp.LEFT_PARENTHESIS,
										Exp.NOT_EXISTS, Exp.select(new JudgmentBO(db).where(JudgmentBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM)),
										Exp.OR,	Exp.EXISTS,	Exp.select(new JudgmentBO(db).where(JudgmentBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM).where(Exp.date(JudgmentBO.JDMTDATETIME), Exp.GREATER_THAN, m_end_date)),
									Exp.RIGHT_PARENTHESIS,
								Exp.RIGHT_PARENTHESIS,
								Exp.OR,
								Exp.LEFT_PARENTHESIS,
									CaseTypeBO.CATEGORY, Exp.EQUALS, "R",
									Exp.AND,
									Exp.LEFT_PARENTHESIS,
										Exp.EXISTS, Exp.select(new ChargeBO(db).where(ChargeBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM).where(ChargeBO.JDMTDATE, Exp.GREATER_THAN, m_end_date)),
										Exp.OR, Exp.EXISTS, Exp.select(new ChargeBO(db).where(ChargeBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM).where(ChargeBO.JDMTDATE, Exp.IS_NULL)),
									Exp.RIGHT_PARENTHESIS,
								Exp.RIGHT_PARENTHESIS,
							Exp.RIGHT_PARENTHESIS,
						Exp.RIGHT_PARENTHESIS,
						Exp.AND, Exp.NOT_EXISTS, Exp.select(new WarrantBO(db).where(WarrantBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM).where(WarrantBO.ISSUEDATE, Exp.LESS_THAN, m_end_date).where(Exp.LEFT_PARENTHESIS, WarrantBO.RECALLDATE, Exp.IS_NULL, Exp.OR, WarrantBO.RECALLDATE, Exp.GREATER_THAN, m_end_date, Exp.RIGHT_PARENTHESIS).where(Exp.LEFT_PARENTHESIS, WarrantBO.EXPDATE, Exp.GREATER_THAN, m_end_date, Exp.OR, WarrantBO.EXPDATE, Exp.IS_NULL, Exp.RIGHT_PARENTHESIS)),
						Exp.AND, Exp.NOT_EXISTS, Exp.select(new StayBO(db).where(StayBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM).where(StayBO.BEGINDATE, Exp.LESS_THAN, m_end_date).where(Exp.LEFT_PARENTHESIS, StayBO.ENDDATE, Exp.GREATER_THAN, m_end_date, Exp.OR, StayBO.ENDDATE, Exp.IS_NULL, Exp.RIGHT_PARENTHESIS))
					);
	        	 
	         } else if ("i".equalsIgnoreCase(m_active)) {
	        	 
	    	 	// inactive cases
				sqlstmt.where(
					Exp.LEFT_PARENTHESIS,
						KaseBO.DISPDATE, Exp.GREATER_THAN, m_end_date,
						Exp.OR,
						Exp.LEFT_PARENTHESIS,
							KaseBO.DISPDATE, Exp.IS_NULL,
							Exp.AND,
							Exp.LEFT_PARENTHESIS,
								CaseTypeBO.CATEGORY, Exp.NOT_EQUALS, "R",
								Exp.AND,
								Exp.LEFT_PARENTHESIS,
									Exp.NOT_EXISTS, Exp.select(new JudgmentBO(db).where(JudgmentBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM)),
									Exp.OR,	Exp.EXISTS,	Exp.select(new JudgmentBO(db).where(JudgmentBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM).where(Exp.date(JudgmentBO.JDMTDATETIME), Exp.GREATER_THAN, m_end_date)),
								Exp.RIGHT_PARENTHESIS,
							Exp.RIGHT_PARENTHESIS,
							Exp.OR,
							Exp.LEFT_PARENTHESIS,
								CaseTypeBO.CATEGORY, Exp.EQUALS, "R",
								Exp.AND,
								Exp.LEFT_PARENTHESIS,
									Exp.EXISTS, Exp.select(new ChargeBO(db).where(ChargeBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM).where(ChargeBO.JDMTDATE, Exp.GREATER_THAN, m_end_date)),
									Exp.OR, Exp.EXISTS, Exp.select(new ChargeBO(db).where(ChargeBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM).where(ChargeBO.JDMTDATE, Exp.IS_NULL)),
								Exp.RIGHT_PARENTHESIS,
							Exp.RIGHT_PARENTHESIS,
						Exp.RIGHT_PARENTHESIS,
					Exp.RIGHT_PARENTHESIS,
					Exp.AND,
					Exp.LEFT_PARENTHESIS,
						Exp.EXISTS, Exp.select(new WarrantBO(db).where(WarrantBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM).where(WarrantBO.ISSUEDATE, Exp.LESS_THAN_OR_EQUAL_TO, m_end_date).where(Exp.LEFT_PARENTHESIS, WarrantBO.RECALLDATE, Exp.IS_NULL, Exp.OR, WarrantBO.RECALLDATE, Exp.GREATER_THAN, m_end_date, Exp.RIGHT_PARENTHESIS).where(Exp.LEFT_PARENTHESIS, WarrantBO.EXPDATE, Exp.GREATER_THAN, m_end_date, Exp.OR, WarrantBO.EXPDATE, Exp.IS_NULL, Exp.RIGHT_PARENTHESIS)),
						Exp.OR, Exp.EXISTS, Exp.select(new StayBO(db).where(StayBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM).where(StayBO.BEGINDATE, Exp.LESS_THAN, m_end_date).where(Exp.LEFT_PARENTHESIS, StayBO.ENDDATE, Exp.GREATER_THAN, m_end_date, Exp.OR, StayBO.ENDDATE, Exp.IS_NULL, Exp.RIGHT_PARENTHESIS)),
					Exp.RIGHT_PARENTHESIS
				);
				
	         }
	         
	         String m_rpt_type = judge.m_rpt_type;
	         int m_judge_id = judge.m_judge_id;
			         
	       	 if ("J".equals(m_rpt_type)) {
	       		 judge.m_judge_name = "Judge";
	       		 sqlstmt.where(KaseBO.ASSNJUDGEID, Exp.EQUALS, m_judge_id);
	       	 } else if ("C".equals(m_rpt_type)) {
	       		 judge.m_judge_name = "Commissioner";
	       		sqlstmt.where(KaseBO.ASSNCOMMID, Exp.EQUALS, m_judge_id);
	       	 } else {
	       		 judge.m_judge_name = "Judge/Comm";
	       	 }
	       	 
	         if (m_judge_id < 0) {
	
	        	 if ("J".equals(m_rpt_type)) {
	        		 judge.m_judge_name = "Judge : UNASSIGNED";
	        		 sqlstmt.where(KaseBO.ASSNJUDGEID, Exp.IS_NULL);
	        	 }
			        	 
	        	 if ("C".equals(m_rpt_type)) {
	        		 judge.m_judge_name = "Commissioner : UNASSIGNED";
	        		 sqlstmt.where(KaseBO.ASSNCOMMID, Exp.IS_NULL);
	        	 }
			        	 
	         } else if (m_judge_id == 0) {
			        	 
	        	 if ("B".equals(m_rpt_type)) {
	        		 judge.m_judge_name = "ALL Judges/Commissioners";
	        	 } else {
	        		 judge.m_judge_name = judge.m_judge_name + ": ALL ASSIGNED";
	        		 sqlstmt.where(Exp.LEFT_PARENTHESIS, KaseBO.ASSNCOMMID, Exp.IS_NOT_NULL, Exp.OR, KaseBO.ASSNJUDGEID, Exp.IS_NOT_NULL, Exp.RIGHT_PARENTHESIS);
	        	 }
			        	 
	         } else if (m_judge_id > 0) {
	
	        	 if (!TextUtil.isEmpty(judge.l_first_name)) {
			     	 judge.m_judge_name = judge.m_judge_name.trim() + ": " + judge.l_first_name.trim() + " " + judge.l_last_name.trim();
	        	 } else {
	        		 judge.m_judge_name = judge.m_judge_name.trim() + ": " + judge.l_last_name.trim();
	        	 }
	        	 
		    }	 
			         	 
	        List<KaseBO> s_case_pend = sqlstmt.search();
	        for (KaseBO c_case_pend : s_case_pend) {
		       	 boolean ok = false;
		
		       	 if ("B".equalsIgnoreCase(m_rpt_type)) {
	
		       		 if (m_judge_id < 0) {
		       			 
	        			 if (c_case_pend.getAssnJudgeId() == 0 && c_case_pend.getAssnCommId() == 0)
	        				 ok = true;
		        		 } else if (m_judge_id == 0) {
		        			 ok = true;
		        		 } else {
		        			 if (c_case_pend.getAssnJudgeId() == m_judge_id || c_case_pend.getAssnCommId() == m_judge_id) 
		        				 ok = true;
		        		 }
		       		 
	        	 } else if ("J".equalsIgnoreCase(m_rpt_type)) {
		        		 
	        		 if (m_judge_id < 0) {
		    			 if (c_case_pend.getAssnJudgeId() == 0)
	        				 ok = true;
	        		 } else if (m_judge_id == 0) {
	        			 if (c_case_pend.getAssnJudgeId() > 0)
	        				 ok = true;
	        		 } else {
	        			 if (c_case_pend.getAssnJudgeId() > 0 && c_case_pend.getAssnJudgeId() == m_judge_id)
	        				 ok = true;
		        	 }
	
	        	} else {
			        		 
	        		 if (m_judge_id < 0) {
	        			 if (c_case_pend.getAssnJudgeId() == 0 && c_case_pend.getAssnCommId() == 0)
	        				 ok = true;
	        		 } else if (m_judge_id == 0) {
	        			 if (c_case_pend.getAssnCommId() > 0)
	        				 ok = true;
	        		 } else {
	        			 if (c_case_pend.getAssnCommId() > 0 && c_case_pend.getAssnCommId() == m_judge_id)
	        				 ok = true;
	        		 }	 
	        	} 
			        		 
	        	if (!ok)
	        		 continue;
			        	 
	        	List<ReturnValues> storedProcResults = CasePendingSP.calcPending(m_userid_srl, c_case_pend, m_end_date, db);
	          	if (storedProcResults != null && storedProcResults.size() > 0) {
	          		for (ReturnValues storedProcResult : storedProcResults) {
	          			int l_icn = (Integer) storedProcResult.p_int_case_num;
		          		Date l_true_disp_date = (Date) storedProcResult.p_disp_date;
		          		int l_disp_id = (Integer) storedProcResult.p_disp_id;
		          		int l_staydays = (Integer) storedProcResult.p_tot_stay_days;
		          		int l_dispdays = (Integer) storedProcResult.p_tot_disp_days;
				          		
		          		if (l_true_disp_date != null && l_true_disp_date.after(m_end_date)) {
		          			continue;
				        }
		          		
		          		rc.m_num_days = DateUtil.getDaysBetween(m_end_date, c_case_pend.getFilingDate());
		          		rc.m_num_days = rc.m_num_days - l_staydays;
			
			          	if (rc.m_num_days < 0)
			          		rc.m_num_days = 0;
				          		 
			          	for (int arr=0; arr < rc.mcasecount_t; arr++) {
				          			 
		          			 if (rc.ma_casecount.get(arr).case_type.equalsIgnoreCase(c_case_pend.getCaseType())) {
				          				 
		          				 rc.ma_casecount.get(arr).pendcases++; 
		          				 rc.ma_casecount.get(arr).casedays = rc.ma_casecount.get(arr).casedays + rc.m_num_days;
				          				 
		       				 	 if (rc.m_num_days >= 0 && rc.m_num_days < 31) {
		       				 		 rc.ma_casecount.get(arr).count30++;
		       				 	 } else if (rc.m_num_days > 30 && rc.m_num_days < 61) {
		       				 		 rc.ma_casecount.get(arr).count60++;
		       				 	 } else if (rc.m_num_days > 60 && rc.m_num_days < 91) {
		       				 		 rc.ma_casecount.get(arr).count90++;
		       				 	 } else if (rc.m_num_days > 90 && rc.m_num_days < 181) {
		       				 		 rc.ma_casecount.get(arr).count180++;
	          				 	 } else if (rc.m_num_days > 180 && rc.m_num_days < 361) {
	          				 		 rc.ma_casecount.get(arr).count360++;
	          				 	 } else if (rc.m_num_days > 360 && rc.m_num_days < 721) {
	          				 		 rc.ma_casecount.get(arr).count720++;
	          				 	 } else if (rc.m_num_days > 720) {
	         				 		 rc.ma_casecount.get(arr).countover++;
	          				 	 }
		       				 	 
		          		 	 }
			          	}
		          		rc.m_listcase = 0;
		          	 }   // for
		       	 }  // if
			
		    }
			         
	        rc.judge = judge;
			reportCounters.add(rc);
			rc = null;
			         
		} // JudgeCommInfos
	         
			
		} catch (Exception e) {
			System.out.println("Exception in search: " + e.getMessage());
			throw e;
		}
		
		return reportCounters;
	}
	
	public void getLocnList(HttpServletRequest request, CasePendingAgeReportSearchCriteria criteria) throws Exception {
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
	
	private void changeJudgeList(HttpServletRequest request, HttpServletResponse response, CasePendingAgeReportSearchCriteria criteria) throws Exception {
		try {
			JSONObject retValObj = new JSONObject();
			
			List<PersonnelBO> judgeAndComms = getJudgeCommList(criteria.getCourtType(), criteria.getLocnCode());
			
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
