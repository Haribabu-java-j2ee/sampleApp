package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.util.WebReportUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class CasePendingAgeReportSearchCriteria extends ReportBaseSearchCriteria {
	
	public CasePendingAgeReportSearchCriteria(HttpServletRequest request) throws Exception {
		super(request);
		if (request != null)
			WebReportUtil.initBaseReportCriteria(this, request);
	}
	
	private Date pendingDate;
	private boolean includeUnassigned;
	private String active;
	private String judgeComm;
	private int sessionUserId;
	private List<JudgeCommInfo> judgeCommInfos;
	private List<ReportCounter> searchResults;
	
	public class JudgeCommInfo {
		public int m_judge_id;
		public String m_rpt_type;
		public String l_last_name;
		public String l_first_name;
		public String m_judge_name;
		
		JudgeCommInfo(String m_rpt_type, int m_judge_id, String courtType) throws Exception {
			this.m_rpt_type = m_rpt_type;
			this.m_judge_id = m_judge_id;
			
	        if (m_judge_id > 0) {
	        	PersonnelBO judge = new PersonnelBO(courtType).where(PersonnelBO.USERIDSRL, m_judge_id).find(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME, PersonnelBO.TYPE);
	        	this.l_last_name = judge.getLastName();
	        	this.l_first_name = judge.getFirstName();
	        	judge = null;
	        }
		}
	}
	
	public static class ReportCounter {
		public JudgeCommInfo judge = null;
		 
		public int m_case_days = 0;
		public int m_num_days = 0;
		public int m_pend_cases = 0;
		 
		public int g_case_days = 0;
		public int g_num_days = 0;
		public int g_pend_cases = 0;
		public int gcount30 = 0;
		public int gcount60 = 0;
		public int gcount90 = 0;
		public int gcount180 = 0;
		public int gcount360 = 0;
		public int gcount720 = 0;
		public int gcountover = 0;
		 
		public int gt_case_days = 0;
		public int gt_num_days = 0;
		public int gt_pend_cases = 0;
		public int gtcount30 = 0;
		public int gtcount60 = 0;
		public int gtcount90 = 0;
		public int gtcount180 = 0;
		public int gtcount360 = 0;
		public int gtcount720 = 0;
		public int gtcountover = 0;
		 
		public int m_listcase = 0;
		public int m_myintcasenum = 0;
		public String m_mycasenum = null;
		public int m_mycasedays = 0;
		public String m_mycasetype = null;
		 
		public List<Record> ma_casecount = new ArrayList<Record>();
		public final int mcasecount_x = 200;
		public int mcasecount_t;
	}
	
	public static class Record {
		public String rpt_category;
		public String rpt_cat_name;
		public String case_type;
		public String descr;
		public int count30;
		public int count60;
		public int count90;
		public int count180;
		public int count360;
		public int count720;
		public int countover;
		public int casedays;
		public int pendcases;
	}
	 
	public List<JudgeCommInfo> getJudgeCommInfos() {
		return judgeCommInfos;
	}
	public void setJudgeCommInfos(List<JudgeCommInfo> judgeCommInfos) {
		this.judgeCommInfos = judgeCommInfos;
	}
	public void addJudgeCommInfo(JudgeCommInfo judgeCommInfo) {
		if (this.judgeCommInfos == null)
			this.judgeCommInfos = new ArrayList<JudgeCommInfo>();
		
		this.judgeCommInfos.add(judgeCommInfo);
	}
	public void addJudgeCommInfo(String judgeType, int judgeId, String courtType) throws Exception {
		addJudgeCommInfo(new JudgeCommInfo(judgeType, judgeId, courtType));
	}
	public Date getPendingDate() {
		return pendingDate;
	}
	public void setPendingDate(Date pendingDate) {
		this.pendingDate = pendingDate;
	}
	public boolean includeUnassigned() {
		return includeUnassigned;
	}
	public void setIncludeUnassigned(boolean includeUnassigned) {
		this.includeUnassigned = includeUnassigned;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getJudgeComm() {
		return judgeComm;
	}
	public void setJudgeComm(String judgeComm) throws Exception {
		this.judgeComm = judgeComm;
		
		List<String> judges = getSelectedJudges();
		if (judges.size() == 0) {
			addJudgeCommInfo("B", 0, this.getCourtReadOnlyDB());
		} else {
			for (String judge : judges) {
				String judgeType = judge.substring(0, 1);
				int judgeId = Integer.parseInt(judge.substring(1, judge.length()));
				addJudgeCommInfo(judgeType, judgeId, this.getCourtReadOnlyDB());
			}
		}
	}
	public int getSessionUserId() {
		return sessionUserId;
	}
	public void setSessionUserId(int sessionUserId) {
		this.sessionUserId = sessionUserId;
	}
	
	// helper methods
	public String getReportType() {
		String m_rpt_type = "";
		
		// no judges selected
		if (getSelectedJudges().size() == 0) 
			m_rpt_type = "B";
		
		return m_rpt_type;
	}
	
	public List<String> getSelectedJudges() {
		List<String> judges = new ArrayList<String>();
		String judgeComm = getJudgeComm();
		if (!TextUtil.isEmpty(judgeComm)) {
			if (judgeComm.contains(",")) {
				String[] mulipleJudges = judgeComm.split(",");
				for (String judge : mulipleJudges) {
					judges.add(judge);
				}
			} else {
				judges.add(judgeComm);
			}
		}
		return judges;
	}
	
	public int getSelectedJudgeCount() {
		return getSelectedJudges().size();
	}
	
	public boolean allJudges() {
		return getSelectedJudges().size() == 0;
	}
	
	public List<ReportCounter> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<ReportCounter> searchResults) {
		this.searchResults = searchResults;
	}
	
}
