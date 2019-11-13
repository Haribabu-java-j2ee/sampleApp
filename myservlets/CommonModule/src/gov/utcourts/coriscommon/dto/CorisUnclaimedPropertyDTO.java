package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CorisUnclaimedPropertyDTO {

	int			journalNum;
	int			transNum;
	int			acctTrustAcctNum;
	int			origAccountNum;
	BigDecimal	amtPaid;
	String		checkNum;
	String 		caseNum;
	int			intJournalNum;
	Date		transDateTime;
	Date		lastTransDateTime;
	String		locnCode;
	String 		courtType;
	String		acctType;
	String		acctDescr;
	String		lastName;
	String		firstName;
	String 		ssn;
	String		address1;
	String		address2;
	String		city;
	String		state;
	String		zip;
	public int getJournalNum() {
		return journalNum;
	}
	public void setJournalNum(int journalNum) {
		this.journalNum = journalNum;
	}
	public int getTransNum() {
		return transNum;
	}
	public void setTransNum(int transNum) {
		this.transNum = transNum;
	}
	public int getAcctTrustAcctNum() {
		return acctTrustAcctNum;
	}
	public void setAcctTrustAcctNum(int acctTrustAcctNum) {
		this.acctTrustAcctNum = acctTrustAcctNum;
	}
	public int getOrigAccountNum() {
		return origAccountNum;
	}
	public void setOrigAccountNum(int origAccountNum) {
		this.origAccountNum = origAccountNum;
	}
	public BigDecimal getAmtPaid() {
		return amtPaid;
	}
	public void setAmtPaid(BigDecimal amtPaid) {
		this.amtPaid = amtPaid;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public int getIntJournalNum() {
		return intJournalNum;
	}
	public void setIntJournalNum(int intJournalNum) {
		this.intJournalNum = intJournalNum;
	}
	public Date getTransDateTime() {
		return transDateTime;
	}
	public void setTransDateTime(Date transDateTime) {
		this.transDateTime = transDateTime;
	}
	public Date getLastTransDateTime() {
		return lastTransDateTime;
	}
	public void setLastTransDateTime(Date lastTransDateTime) {
		this.lastTransDateTime = lastTransDateTime;
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
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	public String getAcctDescr() {
		return acctDescr;
	}
	public void setAcctDescr(String acctDescr) {
		this.acctDescr = acctDescr;
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
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
}
