package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;
import java.util.Date;

public class JudgmentDetlDTO {
	
	private int intCaseNum;
	private int jdmtSeq;
	private int detlSeq;
	private int seq;
	
	private BigDecimal amt;
	private String dispCode;
	private Date dispDate;
	
	private String detlDescr;
	private String dispDescr;
	public int getIntCaseNum() {
		return intCaseNum;
	}
	public void setIntCaseNum(int intCaseNum) {
		this.intCaseNum = intCaseNum;
	}
	public int getJdmtSeq() {
		return jdmtSeq;
	}
	public void setJdmtSeq(int jdmtSeq) {
		this.jdmtSeq = jdmtSeq;
	}
	public int getDetlSeq() {
		return detlSeq;
	}
	public void setDetlSeq(int detlSeq) {
		this.detlSeq = detlSeq;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getDispCode() {
		return dispCode;
	}
	public void setDispCode(String dispCode) {
		this.dispCode = dispCode;
	}
	public Date getDispDate() {
		return dispDate;
	}
	public void setDispDate(Date dispDate) {
		this.dispDate = dispDate;
	}
	public String getDetlDescr() {
		return detlDescr;
	}
	public void setDetlDescr(String detlDescr) {
		this.detlDescr = detlDescr;
	}
	public String getDispDescr() {
		return dispDescr;
	}
	public void setDispDescr(String dispDescr) {
		this.dispDescr = dispDescr;
	}
	
	
}
