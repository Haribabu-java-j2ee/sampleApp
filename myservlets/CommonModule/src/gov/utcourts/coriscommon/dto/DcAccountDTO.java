package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DcAccountDTO {
	
	private int acctNum;
	private String acctType;
	String trustCode;
	private BigDecimal amtDue;
	private BigDecimal amtPaid;
	private BigDecimal amtCredit; 
	private Date intEffectDate;
	private int partyNum;
	private int payeePartyNum;
	
	public int getPayeePartyNum() {
		return payeePartyNum;
	}
	public void setPayeePartyNum(int payeePartyNum) {
		this.payeePartyNum = payeePartyNum;
	}
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
	public String getTrustCode() {
		return trustCode;
	}
	public void setTrustCode(String trustCode) {
		this.trustCode = trustCode;
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
	public Date getIntEffectDate() {
		return intEffectDate;
	}
	public void setIntEffectDate(Date intEffectDate) {
		this.intEffectDate = intEffectDate;
	}
	public int getPartyNum() {
		return partyNum;
	}
	public void setPartyNum(int partyNum) {
		this.partyNum = partyNum;
	}
	

}
