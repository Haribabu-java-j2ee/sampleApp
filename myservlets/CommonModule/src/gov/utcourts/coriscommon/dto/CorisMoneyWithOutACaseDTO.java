package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CorisMoneyWithOutACaseDTO {
	
	public CorisMoneyWithOutACaseDTO (int status, int noOfItems, String lastName, String firstName,
									  Date transDateTime,	int accountNumber, String tenderType, 
									  BigDecimal amtPaid, String checkStubDescr, String accountNote,
									  String address1, String address2, String city, String state, 
									  String zip, String country){
		this.status = status;
		this.noOfItems = noOfItems;
		this.lastName = lastName;
		this.firstName = firstName;
		this.transDateTime = transDateTime;
		this.accountNumber = accountNumber;
		this.tenderType = tenderType;
		this.amtPaid = amtPaid;
		this.checkStubDescr = checkStubDescr;
		this.accountNote = accountNote;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}
				
	int			status;
	int			noOfItems;
	String		lastName;
	String		firstName;
	Date		transDateTime;
	int			accountNumber;
	String		tenderType;
	BigDecimal	amtPaid;
	String		checkStubDescr;
	String		accountNote;
	String		address1;
	String		address2;
	String		city;
	String		state;
	String		zip;
	String		country;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getNoOfItems() {
		return noOfItems;
	}
	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
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
	public Date getTransDateTime() {
		return transDateTime;
	}
	public void setTransDateTime(Date transDateTime) {
		this.transDateTime = transDateTime;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getTenderType() {
		return tenderType;
	}
	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}
	public BigDecimal getAmtPaid() {
		return amtPaid;
	}
	public void setAmtPaid(BigDecimal amtPaid) {
		this.amtPaid = amtPaid;
	}
	public String getCheckStubDescr() {
		return checkStubDescr;
	}
	public void setCheckStubDescr(String checkStubDescr) {
		this.checkStubDescr = checkStubDescr;
	}
	public String getAccountNote() {
		return accountNote;
	}
	public void setAccountNote(String accountNote) {
		this.accountNote = accountNote;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
