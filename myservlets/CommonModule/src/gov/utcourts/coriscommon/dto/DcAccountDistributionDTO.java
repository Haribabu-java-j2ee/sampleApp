package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;

public class DcAccountDistributionDTO {
	private int acctNum;
	private String distCode;
	private String descr;
	private BigDecimal amtDue;
	private BigDecimal amtPaid;
	private BigDecimal amtCredit;
	public int getAcctNum() {
		return acctNum;
	}
	public void setAcctNum(int acctNum) {
		this.acctNum = acctNum;
	}
	public String getDistCode() {
		return distCode;
	}
	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public BigDecimal getAmtDue() {
		return amtDue;
	}
	public void setAmtDue(BigDecimal amtDue) {
		this.amtDue = amtDue;
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
	 
}
