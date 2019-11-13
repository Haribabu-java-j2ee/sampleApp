package gov.utcourts.corisweb.report;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

public class CorisDomViolReportCriteria extends ReportBaseSearchCriteria {
	
	private Date startDate;
	private Date endDate;
	private boolean filingDateType;
	private boolean dispositionDateType;

	public CorisDomViolReportCriteria(HttpServletRequest request) throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}

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

	public boolean isFilingDateType() {
		return filingDateType;
	}

	public void setFilingDateType(boolean filingDateType) {
		this.filingDateType = filingDateType;
	}

	public boolean isDispositionDateType() {
		return dispositionDateType;
	}

	public void setDispositionDateType(boolean dispositionDateType) {
		this.dispositionDateType = dispositionDateType;
	}
	
	

}
