package gov.utcourts.corisweb.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

public class CorisReportNameIndexCriminalReportCriteria extends ReportBaseSearchCriteria {
	
	public CorisReportNameIndexCriminalReportCriteria(HttpServletRequest request) throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}

	private Date reportDateStart;
	private Date reportDateEnd;
	private Date reportDateEndPlusOne;
	private PageMode mode;
	private SimpleDateFormat dateFormat;
	private String reportFileName;
	private String reportTitle;
	private int sortColumn;
	private String sortDirection;
	private String typeOfDate;
	private List<String> caseType;
	private String reportCategory;

	public List<String> getCaseType() {
		return caseType;
	}
	public void setCaseType(List<String> caseType) {
		this.caseType = caseType;
	}
	public String getTypeOfDate() {
		return typeOfDate;
	}
	public void setTypeOfDate(String typeOfDate) {
		this.typeOfDate = typeOfDate;
	}
	public String getReportCategory() {
		return reportCategory;
	}
	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}
	public Date getReportDateStart() {
		return reportDateStart;
	}
	public void setReportDateStart(Date reportDateStart) {
		this.reportDateStart = reportDateStart;
	}
	public Date getReportDateEnd() {
		return reportDateEnd;
	}
	public void setReportDateEnd(Date reportDateEnd) {
		this.reportDateEnd = reportDateEnd;
	}
	public Date getReportDateEndPlusOne() {
		return reportDateEndPlusOne;
	}
	public void setReportDateEndPlusOne(Date reportDateEndPlusOne) {
		this.reportDateEndPlusOne = reportDateEndPlusOne;
	}
	public PageMode getMode() {
		return mode;
	}
	public void setMode(PageMode mode) {
		this.mode = mode;
	}
	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
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
