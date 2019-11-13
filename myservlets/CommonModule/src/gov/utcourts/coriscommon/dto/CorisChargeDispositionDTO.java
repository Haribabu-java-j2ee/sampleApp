package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.summaryevent.SummaryEventBO;

import java.util.Date;
import java.util.List;

public class CorisChargeDispositionDTO {
				
	String		locnCode;
	String		courtType;
	int			intCaseNum;
	String		caseNum;
	String 		caseType;
	Date		filingDate;
	String      descr;
	String		lastName;
	String		firstName;
	
	List<ChargeBO>			chargeList;
	List<SummaryEventBO>	summaryEventList;
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
	public int getIntCaseNum() {
		return intCaseNum;
	}
	public void setIntCaseNum(int intCaseNum) {
		this.intCaseNum = intCaseNum;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public Date getFilingDate() {
		return filingDate;
	}
	public void setFilingDate(Date filingDate) {
		this.filingDate = filingDate;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public List<ChargeBO> getChargeList() {
		return chargeList;
	}
	public void setChargeList(List<ChargeBO> chargeList) {
		this.chargeList = chargeList;
	}
	public List<SummaryEventBO> getSummaryEventList() {
		return summaryEventList;
	}
	public void setSummaryEventList(List<SummaryEventBO> summaryEventList) {
		this.summaryEventList = summaryEventList;
	}

}
