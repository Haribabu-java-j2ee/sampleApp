package gov.utcourts.corisweb.report;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

public class CorisReportCaseStayLookupReportCriteria extends ReportBaseSearchCriteria {
	
	public CorisReportCaseStayLookupReportCriteria(HttpServletRequest request) throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}
	private Date stayDateFrom;
	private Date stayDateTo;
	private String caseType;
	private String stayReason;
	private int judgeCommId;
	
	public Date getStayDateFrom() {
		return stayDateFrom;
	}
	public void setStayDateFrom(Date stayDateFrom) {
		this.stayDateFrom = stayDateFrom;
	}
	public Date getStayDateTo() {
		return stayDateTo;
	}
	public void setStayDateTo(Date stayDateTo) {
		this.stayDateTo = stayDateTo;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getStayReason() {
		return stayReason;
	}
	public void setStayReason(String stayReason) {
		this.stayReason = stayReason;
	}
	public int getJudgeCommId() {
		return judgeCommId;
	}
	public void setJudgeCommId(int judgeCommId) {
		this.judgeCommId = judgeCommId;
	}
	
}
