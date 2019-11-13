package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.vehicle.VehicleBO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CorisWarrantWithPaymentsMadeDTO {
				
	int			kaseIntCaseNum;
	//Sortable Columns
	String		locnCode;
	String		courtType;
	String		caseNum;
	String 		caseType;
	String		judgeLastName;
	String		judgeFirstName;
	String		defLastName;
	String		defFirstName;
	String		ftaStatus;
	String		status;
	BigDecimal	amtPaid;
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
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
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
	public String getFtaStatus() {
		return ftaStatus;
	}
	public void setFtaStatus(String ftaStatus) {
		this.ftaStatus = ftaStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getAmtPaid() {
		return amtPaid;
	}
	public void setAmtPaid(BigDecimal amtPaid) {
		this.amtPaid = amtPaid;
	}
	
	
}
