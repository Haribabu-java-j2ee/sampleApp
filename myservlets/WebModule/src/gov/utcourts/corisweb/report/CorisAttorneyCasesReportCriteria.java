package gov.utcourts.corisweb.report;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

public class CorisAttorneyCasesReportCriteria extends ReportBaseSearchCriteria {
	
	public CorisAttorneyCasesReportCriteria(HttpServletRequest request) throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}
	
	private int barNum;
	private String barState;
	private String caseNum;
	private Date attachDatetimeStart;
	private Date attachDatetimeEnd;
	private Date casesFiledFrom;
	private Date casesFiledTo;
	private boolean includeDetached;
	
	
	public int getBarNum() {
		return barNum;
	}
	public void setBarNum(int barNum) {
		this.barNum = barNum;
	}
	public String getBarState() {
		return barState;
	}
	public void setBarState(String barState) {
		this.barState = barState;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public Date getAttachDatetimeStart() {
		return attachDatetimeStart;
	}
	public void setAttachDatetimeStart(Date attachDatetimeStart) {
		this.attachDatetimeStart = attachDatetimeStart;
	}
	public Date getAttachDatetimeEnd() {
		return attachDatetimeEnd;
	}
	public void setAttachDatetimeEnd(Date attachDatetimeEnd) {
		this.attachDatetimeEnd = attachDatetimeEnd;
	}
	public Date getCasesFiledFrom() {
		return casesFiledFrom;
	}
	public void setCasesFiledFrom(Date casesFiledFrom) {
		this.casesFiledFrom = casesFiledFrom;
	}
	public Date getCasesFiledTo() {
		return casesFiledTo;
	}
	public void setCasesFiledTo(Date casesFiledTo) {
		this.casesFiledTo = casesFiledTo;
	}
	public boolean isIncludeDetached() {
		return includeDetached;
	}
	public void setIncludeDetached(boolean includeDetached) {
		this.includeDetached = includeDetached;
	}
	
}
