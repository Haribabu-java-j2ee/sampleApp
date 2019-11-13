package gov.utcourts.corisweb.report;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

public class CorisAttorneysForCaseReportCriteria extends ReportBaseSearchCriteria {
	
	public CorisAttorneysForCaseReportCriteria(HttpServletRequest request) throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}

	private String caseNum;
	private boolean includeDetached;
	private Date attachDatetimeStart;
	private Date attachDatetimeEnd;
	private Date attachDatetimeEndPlusOne;
	private PageMode mode;
	private String action;
	private int attyPartyId;
	private Date attachDatetime;
	private Date detachDatetime;
	private int userid;
	private SimpleDateFormat dateFormat;
	private String reportFileName;
	private String reportTitle;
	private int sortColumn;
	private String sortDirection;

	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public int getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(int sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	public String getReportFileName() {
		return reportFileName;
	}
	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}
	public Date getAttachDatetimeEndPlusOne() {
		return attachDatetimeEndPlusOne;
	}
	public void setAttachDatetimeEndPlusOne(Date attachDatetimeEndPlusOne) {
		this.attachDatetimeEndPlusOne = attachDatetimeEndPlusOne;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public boolean isIncludeDetached() {
		return includeDetached;
	}
	public void setIncludeDetached(boolean includeDetached) {
		this.includeDetached = includeDetached;
	}
	public Date getAttachDatetimeStart() {
		return attachDatetimeStart;
	}
	public void setAttachDatetimeStart(Date attachDatetimeStart) {
		this.attachDatetimeStart = attachDatetimeStart;
	}
	public Date getAttachDatetimeEnd() {
		return attachDatetimeEnd;
	}
	public void setAttachDatetimeEnd(Date attachDatetimeEnd) {
		this.attachDatetimeEnd = attachDatetimeEnd;
	}
	public PageMode getMode() {
		return mode;
	}
	public void setMode(PageMode mode) {
		this.mode = mode;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getAttyPartyId() {
		return attyPartyId;
	}
	public void setAttyPartyId(int attyPartyId) {
		this.attyPartyId = attyPartyId;
	}
	public Date getAttachDatetime() {
		return attachDatetime;
	}
	public void setAttachDatetime(Date attachDatetime) {
		this.attachDatetime = attachDatetime;
	}
	public Date getDetachDatetime() {
		return detachDatetime;
	}
	public void setDetachDatetime(Date detachDatetime) {
		this.detachDatetime = detachDatetime;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
}
