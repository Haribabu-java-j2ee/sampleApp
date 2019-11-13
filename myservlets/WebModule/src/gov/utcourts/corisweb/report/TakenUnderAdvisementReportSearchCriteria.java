package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class TakenUnderAdvisementReportSearchCriteria extends ReportBaseSearchCriteria {
	
	public TakenUnderAdvisementReportSearchCriteria(HttpServletRequest request) throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}
	
	private Date dateFrom;
	private Date dateTo;
	private String judgeComm;
	
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public String getJudgeComm() {
		return judgeComm;
	}
	public void setJudgeComm(String judgeComm) {
		this.judgeComm = judgeComm;
	}
	
}
