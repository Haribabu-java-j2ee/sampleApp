package gov.utcourts.coriscommon.dto;

import java.util.Date;

public class DebtPaymentDTO {
	private String caseNumber;
	private String caseTypeDescr;
	private int accountNumber;
	private String accountType;
	private double amountDue;
	private double amountPaid;
	private double amountCredit;
	private double revenueAmount;
	private double trustAmount;
	private int journalNum;
	private int transNum;
	private String tenderType;
	private String outType;
	private Date transDateTime;
	private String payerFirstName;
	private String payerLastName;
	private String payeeFirstName;
	private String payeeLastName;
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCaseTypeDescr() {
		return caseTypeDescr;
	}
	public void setCaseTypeDescr(String caseTypeDescr) {
		this.caseTypeDescr = caseTypeDescr;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public double getAmountCredit() {
		return amountCredit;
	}
	public void setAmountCredit(double amountCredit) {
		this.amountCredit = amountCredit;
	}
	public double getRevenueAmount() {
		return revenueAmount;
	}
	public void setRevenueAmount(double revenueAmount) {
		this.revenueAmount = revenueAmount;
	}
	public double getTrustAmount() {
		return trustAmount;
	}
	public void setTrustAmount(double trustAmount) {
		this.trustAmount = trustAmount;
	}
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
	public String getTenderType() {
		return tenderType;
	}
	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}
	public String getOutType() {
		return outType;
	}
	public void setOutType(String outType) {
		this.outType = outType;
	}
	public Date getTransDateTime() {
		return transDateTime;
	}
	public void setTransDateTime(Date transDateTime) {
		this.transDateTime = transDateTime;
	}
	public String getPayerFirstName() {
		return payerFirstName;
	}
	public void setPayerFirstName(String payerFirstName) {
		this.payerFirstName = payerFirstName;
	}
	public String getPayerLastName() {
		return payerLastName;
	}
	public void setPayerLastName(String payerLastName) {
		this.payerLastName = payerLastName;
	}
	public String getPayeeFirstName() {
		return payeeFirstName;
	}
	public void setPayeeFirstName(String payeeFirstName) {
		this.payeeFirstName = payeeFirstName;
	}
	public String getPayeeLastName() {
		return payeeLastName;
	}
	public void setPayeeLastName(String payeeLastName) {
		this.payeeLastName = payeeLastName;
	}
  
}
