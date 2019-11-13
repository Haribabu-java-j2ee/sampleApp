package gov.utcourts.corisweb.report;

import javax.servlet.http.HttpServletRequest;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

public class CorisAttorneyLookupReportCriteria extends ReportBaseSearchCriteria {
	
	public CorisAttorneyLookupReportCriteria(HttpServletRequest request) throws Exception {
		super(request);
		WebReportUtil.initBaseReportCriteria(this, request);
	}

	private PageMode mode;
	private int barNum;
	private String barState;
	private String reportFileName;
	private String reportTitle;
	private int sortColumn;
	private String sortDirection;
	private String attyLastName;
	private String attyFirstName;
	private String showDetails;
	
	public String getShowDetails() {
		return showDetails;
	}
	public void setShowDetails(String showDetails) {
		this.showDetails = showDetails;
	}
	public String getAttyLastName() {
		return attyLastName;
	}
	public void setAttyLastName(String attyLastName) {
		this.attyLastName = attyLastName;
	}
	public String getAttyFirstName() {
		return attyFirstName;
	}
	public void setAttyFirstName(String attyFirstName) {
		this.attyFirstName = attyFirstName;
	}
	public PageMode getMode() {
		return mode;
	}
	public void setMode(PageMode mode) {
		this.mode = mode;
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
