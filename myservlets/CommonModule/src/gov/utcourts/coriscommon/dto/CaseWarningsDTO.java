package gov.utcourts.coriscommon.dto;

import java.util.Date;

public class CaseWarningsDTO {
	
	public CaseWarningsDTO (int status, Date warningDateTime, String warning, String logName, int rowId){
		this.status = status;
		this.warningDateTime = warningDateTime; 
		this.warning = warning;
		this.logName = logName;
		this.rowId = rowId;
	}
	
	int 	status;
	Date	warningDateTime;
	String 	warning;
	String 	logName;
	int		rowId;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getWarningDateTime() {
		return warningDateTime;
	}
	public void setWarningDateTime(Date warningDateTime) {
		this.warningDateTime = warningDateTime;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
}
