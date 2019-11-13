package gov.utcourts.corisweb.report;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

public class CorisReportRevDistSumSearchCriteria extends ReportBaseSearchCriteria {
	
	private int startJournal;
	private int endJournal;
	private Date endDate;

	public CorisReportRevDistSumSearchCriteria(HttpServletRequest request) throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}

	public int getStartJournal() {
		return startJournal;
	}

	public void setStartJournal(int startJournal) {
		this.startJournal = startJournal;
	}

	public int getEndJournal() {
		return endJournal;
	}

	public void setEndJournal(int endJournal) {
		this.endJournal = endJournal;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
