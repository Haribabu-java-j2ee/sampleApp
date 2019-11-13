package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.vehicle.VehicleBO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CorisWarrantCivilDTO {
				
	int			kaseIntCaseNum;
	//Sortable Columns
	String		locnCode;
	String		courtType;
	String		caseNum;
	String		partyCode;
	Date		documentDate;
	
	String		partyLastName;
	String		partyFirstName;
	String		clerkLastName;
	String		clerkFirstName;
	public int getKaseIntCaseNum() {
		return kaseIntCaseNum;
	}
	public void setKaseIntCaseNum(int kaseIntCaseNum) {
		this.kaseIntCaseNum = kaseIntCaseNum;
	}
	public String getLocnCode() {
		return locnCode;
	}
	public void setLocnCode(String locnCode) {
		this.locnCode = locnCode;
	}
	public String getCourtType() {
		return courtType;
	}
	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getPartyCode() {
		return partyCode;
	}
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	public Date getDocumentDate() {
		return documentDate;
	}
	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}
	public String getPartyLastName() {
		return partyLastName;
	}
	public void setPartyLastName(String partyLastName) {
		this.partyLastName = partyLastName;
	}
	public String getPartyFirstName() {
		return partyFirstName;
	}
	public void setPartyFirstName(String partyFirstName) {
		this.partyFirstName = partyFirstName;
	}
	public String getClerkLastName() {
		return clerkLastName;
	}
	public void setClerkLastName(String clerkLastName) {
		this.clerkLastName = clerkLastName;
	}
	public String getClerkFirstName() {
		return clerkFirstName;
	}
	public void setClerkFirstName(String clerkFirstName) {
		this.clerkFirstName = clerkFirstName;
	}
}
