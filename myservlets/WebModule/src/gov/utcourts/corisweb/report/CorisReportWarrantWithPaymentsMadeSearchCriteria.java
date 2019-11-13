package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class CorisReportWarrantWithPaymentsMadeSearchCriteria extends ReportBaseSearchCriteria {
	
	public CorisReportWarrantWithPaymentsMadeSearchCriteria(HttpServletRequest request)  throws Exception {
		
		
		super(request);
		
		
		if (request != null){
		
			WebReportUtil.initBaseReportCriteria(this, request);
		}
	}
	private Date startDate;
	private Date endDate;
	private String warrantType;
	private BigDecimal amountPaid;
	private String partial;
	private int judge;
	
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
	public String getWarrantType() {
		return warrantType;
	}
	public void setWarrantType(String warrantType) {
		this.warrantType = warrantType;
	}
	public BigDecimal getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getPartial() {
		return partial;
	}
	public void setPartial(String partial) {
		this.partial = partial;
	}
	public int getJudge() {
		return judge;
	}
	public void setJudge(int judge) {
		this.judge = judge;
	}
	
}
