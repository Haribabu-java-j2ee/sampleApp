package gov.utcourts.corisweb.report;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

public class CorisReportUserLoginReportCriteria extends ReportBaseSearchCriteria {
	
	public CorisReportUserLoginReportCriteria(HttpServletRequest request) throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}
	private Date loginFrom;
	private Date loginTo;
	private String status;
	private String selectedLogName;
	
	public Date getLoginFrom() {
		return loginFrom;
	}
	public void setLoginFrom(Date loginFrom) {
		this.loginFrom = loginFrom;
	}
	public Date getLoginTo() {
		return loginTo;
	}
	public void setLoginTo(Date loginTo) {
		this.loginTo = loginTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSelectedLogName() {
		return selectedLogName;
	}
	public void setSelectedLogName(String selectedLogName) {
		this.selectedLogName = selectedLogName;
	}
	
}
