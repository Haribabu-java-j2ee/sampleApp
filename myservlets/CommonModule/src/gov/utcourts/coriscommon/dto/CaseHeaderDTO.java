package gov.utcourts.coriscommon.dto;

public class CaseHeaderDTO {
	
	private int intCaseNum;
	private String locnCode;
	private String courtType;  
	private String caseNum;
	private String caseType;
	private String localDebtColl;
	private String debtCollection;
	private int assnJudgeId;  
	private String prosecAgency;
	private String descr;	
 
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
	 
	
	
}
