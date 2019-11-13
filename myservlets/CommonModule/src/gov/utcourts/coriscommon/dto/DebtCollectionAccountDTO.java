package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;
import java.util.Date;
/*
 * Debt Collection Account Data Transaction Object
 */
public class DebtCollectionAccountDTO {
	// From account table
	private int acctNum;
	private String acctType;
	private BigDecimal amtDue;
	private BigDecimal amtPaid;
	private BigDecimal amtCredit;
	private String status;
	private Date dueDate;
	private int userId;
	
	// From party table
	private int partyNum;
	private String firstName;
	private String lastName;
	
	// From kase table
	private String caseNum;
	private String localDebtColl;

	// From osdc_acct_history table
	private String osdcStatus;

	// From case_type table
	private String caseType;
	private String category;
	
	// From fee_type table or trust_type
	private String acctDescr;
	
	// From acct_fee table 
	private String feeCode;
	
	// From acct_trust table 
	private int interestAcctNum;
	
	private String note;
	
	private int daysPastDue;
	


	public int getAcctNum() {
		return acctNum;
	}

	public void setAcctNum(int acctNum) {
		this.acctNum = acctNum;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public BigDecimal getAmtDue() {
		return amtDue;
	}

	public void setAmtDue(BigDecimal amtDue) {
		this.amtDue = amtDue;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BigDecimal getAmtPaid() {
		return amtPaid;
	}

	public void setAmtPaid(BigDecimal amtPaid) {
		this.amtPaid = amtPaid;
	}

	public BigDecimal getAmtCredit() {
		return amtCredit;
	}

	public void setAmtCredit(BigDecimal amtCredit) {
		this.amtCredit = amtCredit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getPartyNum() {
		return partyNum;
	}

	public void setPartyNum(int partyNum) {
		this.partyNum = partyNum;
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

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getLocalDebtColl() {
		return localDebtColl;
	}

	public void setLocalDebtColl(String localDebtColl) {
		this.localDebtColl = localDebtColl;
	}

	public String getOsdcStatus() {
		return osdcStatus;
	}

	public void setOsdcStatus(String osdcStatus) {
		this.osdcStatus = osdcStatus;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getAcctDescr() {
		return acctDescr;
	}

	public void setAcctDescr(String acctDescr) {
		this.acctDescr = acctDescr;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getDaysPastDue() {
		return daysPastDue;
	}

	public void setDaysPastDue(int daysPastDue) {
		this.daysPastDue = daysPastDue;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public int getInterestAcctNum() {
		return interestAcctNum;
	}

	public void setInterestAcctNum(int interestAcctNum) {
		this.interestAcctNum = interestAcctNum;
	}	 
	
	
}
