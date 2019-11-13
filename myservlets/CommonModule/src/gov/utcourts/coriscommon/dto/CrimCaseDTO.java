package gov.utcourts.coriscommon.dto;

import java.util.Date;

public class CrimCaseDTO extends KaseDTO {

	private String citNum;
    private String lea;
    private String leaCaseNum;
    private String prosecAgency;
    private String prosecAgencyNum;
    private String otn;
    private String partyCode;
    private int partyNum;
    private Date issueDate;
    private String sheriffNum;
    private String dvFlag;
    
    String court;
    public CrimCaseDTO(String court) {
		super(court);
		this.court = court;
	}
    
//	public int getIntCaseNum() {
//		return intCaseNum;
//	}
	public String getCitNum() {
		return citNum;
	}
	public String getLea() {
		return lea;
	}
	public String getLeaCaseNum() {
		return leaCaseNum;
	}
	public String getProsecAgency() {
		return prosecAgency;
	}
	public String getProsecAgencyNum() {
		return prosecAgencyNum;
	}
//	public String getCaseNum() {
//		return caseNum;
//	}
	public String getOtn() {
		return otn;
	}
	public String getPartyCode() {
		return partyCode;
	}
	public int getPartyNum() {
		return partyNum;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public String getSheriffNum() {
		return sheriffNum;
	}
//	public void setIntCaseNum(int intCaseNum) {
//		this.intCaseNum = intCaseNum;
//	}
	public void setCitNum(String citNum) {
		this.citNum = citNum;
	}
	public void setLea(String lea) {
		this.lea = lea;
	}
	public void setLeaCaseNum(String leaCaseNum) {
		this.leaCaseNum = leaCaseNum;
	}
	public void setProsecAgency(String prosecAgency) {
		this.prosecAgency = prosecAgency;
	}
	public void setProsecAgencyNum(String prosecAgencyNum) {
		this.prosecAgencyNum = prosecAgencyNum;
	}
//	public void setCaseNum(String caseNum) {
//		this.caseNum = caseNum;
//	}
	public void setOtn(String otn) {
		this.otn = otn;
	}
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	public void setPartyNum(int partyNum) {
		this.partyNum = partyNum;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public void setSheriffNum(String sheriffNum) {
		this.sheriffNum = sheriffNum;
	}

	public String getDvFlag() {
		return dvFlag;
	}

	public void setDvFlag(String dvFlag) {
		this.dvFlag = dvFlag;
	}
	

//	public String getDomViolence() {
//		return domViolence;
//	}
//
//	public void setDomViolence(String domViolence) {
//		this.domViolence = domViolence;
//	}
}
