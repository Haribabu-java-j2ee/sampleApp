package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class CorisReportUnclaimedPropertySearchCriteria extends ReportBaseSearchCriteria {
	
	public CorisReportUnclaimedPropertySearchCriteria(HttpServletRequest request)  throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}
	private Date	startDate;
	private Date	endDate;
	private int		startJournal;
	private int		endJournal;
	
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
	
}
