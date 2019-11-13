package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;

public class WorkCalCaseChargeDTO extends ChargeBO {

	private String offenseDescr;
	private String court;
	private String severityDescr;
	
	public WorkCalCaseChargeDTO(String court) {
		super(court);
		this.court = court;
	}

	public String getOffenseDescr() {
		return offenseDescr;
	}

	public void setOffenseDescr(String offenseDescr) {
		this.offenseDescr = offenseDescr;
	} 

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public String getSeverityDescr() {
		return severityDescr;
	}

	public void setSeverityDescr(String severityDescr) {
		this.severityDescr = severityDescr;
	}

}
