package gov.utcourts.corisweb.report;

import java.util.Date;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

import javax.servlet.http.HttpServletRequest;

public class CorisDomModiPendingReportSearchCriteria extends ReportBaseSearchCriteria {
	
	private Date startDate;
	private Date endDate;

	public CorisDomModiPendingReportSearchCriteria(HttpServletRequest request) throws Exception {
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

}
