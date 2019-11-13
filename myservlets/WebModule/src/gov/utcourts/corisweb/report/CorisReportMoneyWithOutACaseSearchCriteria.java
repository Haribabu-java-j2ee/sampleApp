package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class CorisReportMoneyWithOutACaseSearchCriteria extends ReportBaseSearchCriteria {
	
	public CorisReportMoneyWithOutACaseSearchCriteria(HttpServletRequest request)  throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}
	private String	payorType;
	
	public String getPayorType() {
		return payorType;
	}
	public void setPayorType(String payorType) {
		this.payorType = payorType;
	}
	
}
