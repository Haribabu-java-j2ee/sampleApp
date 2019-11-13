package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class CorisWarrantCivilReportSearchCriteria extends ReportBaseSearchCriteria {
	
	public CorisWarrantCivilReportSearchCriteria(HttpServletRequest request)  throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}
	private Date startDate;
	private Date endDate;
	private String warrantStatus;
	
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
	public String getWarrantStatus() {
		return warrantStatus;
	}
	public void setWarrantStatus(String warrantStatus) {
		this.warrantStatus = warrantStatus;
	}
}
