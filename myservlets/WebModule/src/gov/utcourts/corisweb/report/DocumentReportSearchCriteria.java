package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ibm.json.java.JSONObject;

public class DocumentReportSearchCriteria extends ReportBaseSearchCriteria {
	private Date startDate;
	private Date endDate;
	private boolean docWithoutImgOnly; //document 4gl report specific
	private int clerkId;
//	String orderBy1;
//	String orderBy2;
//	String orderBy3;
//	String orderBy;
	
	public DocumentReportSearchCriteria(HttpServletRequest request) throws Exception{
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
	public boolean isDocWithoutImgOnly() {
		return docWithoutImgOnly;
	}
	public void setDocWithoutImgOnly(boolean docWithoutImgOnly) {
		this.docWithoutImgOnly = docWithoutImgOnly;
	}
	public int getClerkId() {
		return clerkId;
	}
	public void setClerkId(int clerkId) {
		this.clerkId = clerkId;
	}
//	@Override
//	public String getReportFileName() {
//		return "documentReport";
//	}

//	public String getOrderBy1() {
//		return orderBy1;
//	}
//
//	public void setOrderBy1(String orderBy1) {
//		this.orderBy1 = orderBy1;
//	}
//
//	public String getOrderBy2() {
//		return orderBy2;
//	}
//
//	public void setOrderBy2(String orderBy2) {
//		this.orderBy2 = orderBy2;
//	}

//	public String getOrderBy3() {
//		return orderBy3;
//	}
//
//	public void setOrderBy3(String orderBy3) {
//		this.orderBy3 = orderBy3;
//	}
//	
//	public String getOrderBy(){
//		return orderBy;
//	}
//	
//	public void setOrderBy(JSONObject order){
//	}
}
