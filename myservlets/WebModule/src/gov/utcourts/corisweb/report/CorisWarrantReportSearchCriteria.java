package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class CorisWarrantReportSearchCriteria extends ReportBaseSearchCriteria {
	
	public CorisWarrantReportSearchCriteria(HttpServletRequest request)  throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}
	private Date startDate;
	private Date endDate;
	private boolean vehicle;
	private boolean address;
	private boolean charges;
	private String warrantStatus;
	private String warrantType;
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
	public boolean isVehicle() {
		return vehicle;
	}
	public void setVehicle(boolean vehicle) {
		this.vehicle = vehicle;
	}
	public boolean isAddress() {
		return address;
	}
	public void setAddress(boolean address) {
		this.address = address;
	}
	public boolean isCharges() {
		return charges;
	}
	public void setCharges(boolean charges) {
		this.charges = charges;
	}
	public String getWarrantStatus() {
		return warrantStatus;
	}
	public void setWarrantStatus(String warrantStatus) {
		this.warrantStatus = warrantStatus;
	}
	public String getWarrantType() {
		return warrantType;
	}
	public void setWarrantType(String warrantType) {
		this.warrantType = warrantType;
	}
	public int getJudge() {
		return judge;
	}
	public void setJudge(int judge) {
		this.judge = judge;
	}
}
