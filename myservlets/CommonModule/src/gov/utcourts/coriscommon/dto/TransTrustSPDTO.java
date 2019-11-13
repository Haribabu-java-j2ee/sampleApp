package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;
import java.util.Date;


public class TransTrustSPDTO{
	int			status;
	BigDecimal	amtPaid;
	int			priority;
	String		trustCode;
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getTrustCode() {
		return trustCode;
	}
	public void setTrustCode(String trustCode) {
		this.trustCode = trustCode;
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
