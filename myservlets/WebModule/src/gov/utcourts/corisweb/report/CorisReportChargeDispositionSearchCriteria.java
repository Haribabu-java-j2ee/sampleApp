package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class CorisReportChargeDispositionSearchCriteria extends ReportBaseSearchCriteria {
	
	public CorisReportChargeDispositionSearchCriteria(HttpServletRequest request)  throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}
	private Date 	startDate;
	private Date 	endDate;
	private String	jdmtCode;
	private String	caseType;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getJdmtCode() {
		return jdmtCode;
	}
	public void setJdmtCode(String jdmtCode) {
		this.jdmtCode = jdmtCode;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
}
