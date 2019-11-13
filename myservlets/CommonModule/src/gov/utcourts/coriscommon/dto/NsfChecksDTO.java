package gov.utcourts.coriscommon.dto;

public class NsfChecksDTO {

	private int status;
	private String caseNum;
	private String firstName;
	private String lastName;
	private String payorPartyNum;
	private double transAmt;
	private int journalNum;
	private String outType;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPayorPartyNum() {
		return payorPartyNum;
	}
	public void setPayorPartyNum(String payorPartyNum) {
		this.payorPartyNum = payorPartyNum;
	}
	public double getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(double transAmt) {
		this.transAmt = transAmt;
	}
	public int getJournalNum() {
		return journalNum;
	}
	public void setJournalNum(int journalNum) {
		this.journalNum = journalNum;
	}
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}

}
