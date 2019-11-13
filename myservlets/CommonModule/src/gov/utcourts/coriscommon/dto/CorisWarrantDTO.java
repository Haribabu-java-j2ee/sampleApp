package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.vehicle.VehicleBO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CorisWarrantDTO {
				
	int			kaseIntCaseNum;
	//Sortable Columns
	String		locnCode;
	String		courtType;
	String		caseNum;
	String		judgeLastName;
	String		judgeFirstName;
	Date		orderDate;
	Date		issueDate;
	Date 		expDate;
	Date		recallDate;
	BigDecimal	bailAmt;
	
	String		defLastName;
	String		defFirstName;
	Date		birthDate;
	String 		address1;
	String 		address2;
	String 		city;
	String 		stateCode;
	String 		zipCode;
	String 		homePhone;
	String 		busPhone;
	
	VehicleBO	vehicle;
	List<ChargeBO>	chargeList;

	public int getKaseIntCaseNum() {
		return kaseIntCaseNum;
	}

	public void setKaseIntCaseNum(int kaseIntCaseNum) {
		this.kaseIntCaseNum = kaseIntCaseNum;
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

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getJudgeLastName() {
		return judgeLastName;
	}

	public void setJudgeLastName(String judgeLastName) {
		this.judgeLastName = judgeLastName;
	}

	public String getJudgeFirstName() {
		return judgeFirstName;
	}

	public void setJudgeFirstName(String judgeFirstName) {
		this.judgeFirstName = judgeFirstName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getRecallDate() {
		return recallDate;
	}

	public void setRecallDate(Date recallDate) {
		this.recallDate = recallDate;
	}

	public BigDecimal getBailAmt() {
		return bailAmt;
	}

	public void setBailAmt(BigDecimal bailAmt) {
		this.bailAmt = bailAmt;
	}

	public String getDefLastName() {
		return defLastName;
	}

	public void setDefLastName(String defLastName) {
		this.defLastName = defLastName;
	}

	public String getDefFirstName() {
		return defFirstName;
	}

	public void setDefFirstName(String defFirstName) {
		this.defFirstName = defFirstName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getBusPhone() {
		return busPhone;
	}

	public void setBusPhone(String busPhone) {
		this.busPhone = busPhone;
	}

	public VehicleBO getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleBO vehicle) {
		this.vehicle = vehicle;
	}

	public List<ChargeBO> getChargeList() {
		return chargeList;
	}

	public void setChargeList(List<ChargeBO> chargeList) {
		this.chargeList = chargeList;
	}

}
