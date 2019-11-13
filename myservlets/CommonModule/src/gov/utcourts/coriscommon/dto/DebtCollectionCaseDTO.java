package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.common.Name;

import java.util.Date;

public class DebtCollectionCaseDTO implements Name{
	// From kase table
	private int intCaseNum;
	private String caseNum;
	private String localDebtColl;
	private String debtCollection;
	private String debtCollStatusDesc; // descriptions from code_description_xref
	private String caseType;
	
	// From party table
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String ssn;
	
	// From account table
	private int userId;
	private Date dueDate;
	private double balance;

	// From transaction tables
	private Date lastPaidDate;
	
	// From events table
	private Date sentenceDate;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	 
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return "";
	}	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNameSuffix() {
		return "";
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getLastPaidDate() {
		return lastPaidDate;
	}
	public void setLastPaidDate(Date lastPaidDate) {
		this.lastPaidDate = lastPaidDate;
	}
	public Date getSentenceDate() {
		return sentenceDate;
	}
	public void setSentenceDate(Date sentenceDate) {
		this.sentenceDate = sentenceDate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
 
 
	public String getDebtCollStatusDesc() {
		return debtCollStatusDesc;
	}
	public void setDebtCollStatusDesc(String debtCollStatusDesc) {
		this.debtCollStatusDesc = debtCollStatusDesc;
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
	public String getDebtCollection() {
		return debtCollection;
	}
	public void setDebtCollection(String debtCollection) {
		this.debtCollection = debtCollection;
	}
}
