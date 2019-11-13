package gov.utcourts.coriscommon.report;

import javax.servlet.http.HttpServletRequest;


public class ReportBaseSearchCriteria {

	private String courtType;
	private String reportformat;
	private String locnCode;
	private String locnDescr;
	private String reportFileName;
	private String reportName;
	private String logName;
	private String orderBy;
	private String courtReadOnlyDB;
	
	public ReportBaseSearchCriteria(HttpServletRequest request) {
		super();
	}

	/**
	 * @return
	 */
	public String getCourtType() {
		return courtType;
	}
	/**
	 * @param courtType
	 */
	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}
	/**
	 * @return
	 */
	public String getReportformat() {
		return reportformat;
	}
	/**
	 * @param reportformat
	 */
	public void setReportformat(String reportformat) {
		this.reportformat = reportformat;
	}
	
	public String getReportFileName() {
		return reportFileName;
	}
	
	public void setReportFileName(String reportName) {
		this.reportFileName = reportName;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getLocnDescr() {
		return (locnDescr == null) ? "" : locnDescr;
	}
	
	public void setLocnDescr(String locnDescr) {
		this.locnDescr = locnDescr;
	}
	
	public String getLocnCode() {
		return locnCode;
	}
	
	public void setLocnCode(String locnCode) {
		this.locnCode = locnCode;
	}
	
	public String getLogName() {
		return logName;
	}
	
	public void setLogName(String logName) {
		this.logName = logName;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getCourtReadOnlyDB() {
		return courtReadOnlyDB;
	}
	
	public void setCourtReadOnlyDB(String courtReadOnlyDB) {
		this.courtReadOnlyDB = courtReadOnlyDB;
	}

}
