package gov.utcourts.corisweb.report;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

public class CorisReportLateFilingReportCriteria extends ReportBaseSearchCriteria {
	
	public CorisReportLateFilingReportCriteria(HttpServletRequest request) throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}
	
	private Date caseFilingFrom;
	private Date caseFilingTo;
	private Date violationDateFrom;
	private Date violationDateTo;
	
	public Date getCaseFilingFrom() {
		return caseFilingFrom;
	}
	public void setCaseFilingFrom(Date caseFilingFrom) {
		this.caseFilingFrom = caseFilingFrom;
	}
	public Date getCaseFilingTo() {
		return caseFilingTo;
	}
	public void setCaseFilingTo(Date caseFilingTo) {
		this.caseFilingTo = caseFilingTo;
	}
	public Date getViolationDateFrom() {
		return violationDateFrom;
	}
	public void setViolationDateFrom(Date violationDateFrom) {
		this.violationDateFrom = violationDateFrom;
	}
	public Date getViolationDateTo() {
		return violationDateTo;
	}
	public void setViolationDateTo(Date violationDateTo) {
		this.violationDateTo = violationDateTo;
	}
	
	
	
}
