package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.party.PartyBO;

import java.math.BigDecimal;

public class OsdcXmlHeaderDTO {
	private int intCaseNum;
	private String locnCode;
	private String courtType;  
	private String caseType;
	private String descr;
	private String caseNum;
	private boolean  prosecutorSplit;
	private int assnJudgeId;  
	private boolean agencySupervised;
	private String prosecAgency;
	private BigDecimal interestRate;
	private PartyBO partyBO;
	private String localDebtColl;
	private String debtCollection;
	private String partyCode;
	
 
	public String getCourtType() {
		return courtType;
	}
	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}
	
	
	public boolean isProsecutorSplit() {
		return prosecutorSplit;
	}
	public void setProsecutorSplit(boolean prosecutorSplit) {
		this.prosecutorSplit = prosecutorSplit;
	}
	 
	public boolean isAgencySupervised() {
		return agencySupervised;
	}
	public void setAgencySupervised(boolean agencySupervised) {
		this.agencySupervised = agencySupervised;
	}
	 
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public PartyBO getPartyBO() {
		return partyBO;
	}
	public void setPartyBO(PartyBO partyBO) {
		this.partyBO = partyBO;
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
	
	public int getIntCaseNum() {
		return intCaseNum;
	}
	public void setIntCaseNum(int intCaseNum) {
		this.intCaseNum = intCaseNum;
	}
 
	public String getDebtCollection() {
		return debtCollection;
	}
	public void setDebtCollection(String debtCollection) {
		this.debtCollection = debtCollection;
	}
	public String getLocnCode() {
		return locnCode;
	}
	public void setLocnCode(String locnCode) {
		this.locnCode = locnCode;
	}
	public int getAssnJudgeId() {
		return assnJudgeId;
	}
	public void setAssnJudgeId(int assnJudgeId) {
		this.assnJudgeId = assnJudgeId;
	}
	public String getProsecAgency() {
		return prosecAgency;
	}
	public void setProsecAgency(String prosecAgency) {
		this.prosecAgency = prosecAgency;
	}
	public String getLocalDebtColl() {
		return localDebtColl;
	}
	public void setLocalDebtColl(String localDebtColl) {
		this.localDebtColl = localDebtColl;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getPartyCode() {
		return partyCode;
	}
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	
	
	
}
