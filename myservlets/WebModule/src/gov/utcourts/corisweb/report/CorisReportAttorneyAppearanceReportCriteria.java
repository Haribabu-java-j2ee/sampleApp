package gov.utcourts.corisweb.report;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;

public class CorisReportAttorneyAppearanceReportCriteria extends ReportBaseSearchCriteria {
	
	public CorisReportAttorneyAppearanceReportCriteria(HttpServletRequest request) throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}

	private PageMode mode;
	private String reportFileName;
	private String reportTitle;
	private int sortColumn;
	private String sortDirection;
	private int barNum;
	private String barState;
	private Date startDate;
	private Date endDate;
	private Date endDatePlusOne;
	private int judgeId;
	
	public int getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(int judgeId) {
		this.judgeId = judgeId;
	}
	public Date getEndDatePlusOne() {
		return endDatePlusOne;
	}
	public void setEndDatePlusOne(Date endDatePlusOne) {
		this.endDatePlusOne = endDatePlusOne;
	}
	public int getBarNum() {
		return barNum;
	}
	public void setBarNum(int barNum) {
		this.barNum = barNum;
	}
	public String getBarState() {
		return barState;
	}
	public void setBarState(String barState) {
		this.barState = barState;
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
	public PageMode getMode() {
		return mode;
	}
	public void setMode(PageMode mode) {
		this.mode = mode;
	}
	public String getReportFileName() {
		return reportFileName;
	}
	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}
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

}
