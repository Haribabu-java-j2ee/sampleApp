package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;
import java.util.Date;


public class TransRevnSPDTO{
	int			status;
	BigDecimal	amtPaid;
	String		distCode;
	BigDecimal	amtCredit;
    String 		caseNum; 
    String 		descr;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public BigDecimal getAmtPaid() {
		return amtPaid;
	}
	public void setAmtPaid(BigDecimal amtPaid) {
		this.amtPaid = amtPaid;
	}
	public String getDistCode() {
		return distCode;
	}
	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}
	public BigDecimal getAmtCredit() {
		return amtCredit;
	}
	public void setAmtCredit(BigDecimal amtCredit) {
		this.amtCredit = amtCredit;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	} 
}
