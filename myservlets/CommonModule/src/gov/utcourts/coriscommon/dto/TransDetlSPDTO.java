package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;
import java.util.Date;


public class TransDetlSPDTO{
	int			status;
	BigDecimal	creditAmt;
	BigDecimal	cardAmt;
	BigDecimal	cashAmt;
	BigDecimal	checkAmt;
    String 		checkNum; 
    String 		checkType; 
    String 		cardType; 
    String 		lastName; 
    String 		firstName;
    Date		transDate;
    String 		cashier; 
    BigDecimal	transAmt; 
    BigDecimal	nonCashAmt;
    BigDecimal	transferAmt; 
    String 		transNote; 
    String 		supervisor; 
    String 		mailLogFlag;
    BigDecimal	achAmt;
    
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public BigDecimal getCreditAmt() {
		return creditAmt;
	}
	public void setCreditAmt(BigDecimal creditAmt) {
		this.creditAmt = creditAmt;
	}
	public BigDecimal getCardAmt() {
		return cardAmt;
	}
	public void setCardAmt(BigDecimal cardAmt) {
		this.cardAmt = cardAmt;
	}
	public BigDecimal getCashAmt() {
		return cashAmt;
	}
	public void setCashAmt(BigDecimal cashAmt) {
		this.cashAmt = cashAmt;
	}
	public BigDecimal getCheckAmt() {
		return checkAmt;
	}
	public void setCheckAmt(BigDecimal checkAmt) {
		this.checkAmt = checkAmt;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	public Date getTransDate() {
		return transDate;
	}
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	public String getCashier() {
		return cashier;
	}
	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public BigDecimal getNonCashAmt() {
		return nonCashAmt;
	}
	public void setNonCashAmt(BigDecimal nonCashAmt) {
		this.nonCashAmt = nonCashAmt;
	}
	public BigDecimal getTransferAmt() {
		return transferAmt;
	}
	public void setTransferAmt(BigDecimal transferAmt) {
		this.transferAmt = transferAmt;
	}
	public String getTransNote() {
		return transNote;
	}
	public void setTransNote(String transNote) {
		this.transNote = transNote;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getMailLogFlag() {
		return mailLogFlag;
	}
	public void setMailLogFlag(String mailLogFlag) {
		this.mailLogFlag = mailLogFlag;
	}
	public BigDecimal getAchAmt() {
		return achAmt;
	}
	public void setAchAmt(BigDecimal achAmt) {
		this.achAmt = achAmt;
	}
}
