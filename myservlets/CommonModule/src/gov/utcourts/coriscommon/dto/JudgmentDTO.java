package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;
import java.util.Date;

public class JudgmentDTO {
	
	private int intCaseNum;
	private int jdmtSeq;
	private Date filingDatetime;
	private Date jdmtDatetime;
	private BigDecimal jdmtAmt;
	private String jdmtNum;
	private String jdmtCode;
	private String note;
	private Date lastUpdDate;
	private String dispCode;
	private Date dispDate;
	private int useridSrl;
	
	private String judgmentDescr;
	private String jdmtTypeDescr;
	private String dispTypeDescr;
	
	public int getJdmtSeq() {
		return jdmtSeq;
	}
	public void setJdmtSeq(int jdmtSeq) {
		this.jdmtSeq = jdmtSeq;
	}
	public Date getFilingDatetime() {
		return filingDatetime;
	}
	public void setFilingDatetime(Date filingDatetime) {
		this.filingDatetime = filingDatetime;
	}
	public Date getJdmtDatetime() {
		return jdmtDatetime;
	}
	public void setJdmtDatetime(Date jdmtDatetime) {
		this.jdmtDatetime = jdmtDatetime;
	}
	public BigDecimal getJdmtAmt() {
		return jdmtAmt;
	}
	public void setJdmtAmt(BigDecimal jdmtAmt) {
		this.jdmtAmt = jdmtAmt;
	}
	public String getJdmtNum() {
		return jdmtNum;
	}
	public void setJdmtNum(String jdmtNum) {
		this.jdmtNum = jdmtNum;
	}
	public String getJdmtCode() {
		return jdmtCode;
	}
	public void setJdmtCode(String jdmtCode) {
		this.jdmtCode = jdmtCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getLastUpdDate() {
		return lastUpdDate;
	}
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
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
	public int getUseridSrl() {
		return useridSrl;
	}
	public void setUseridSrl(int useridSrl) {
		this.useridSrl = useridSrl;
	}
	public String getDispTypeDescr() {
		return dispTypeDescr;
	}
	public void setDispTypeDescr(String dispTypeDescr) {
		this.dispTypeDescr = dispTypeDescr;
	}
	public int getIntCaseNum() {
		return intCaseNum;
	}
	public void setIntCaseNum(int intCaseNum) {
		this.intCaseNum = intCaseNum;
	}
	public String getJdmtTypeDescr() {
		return jdmtTypeDescr;
	}
	public void setJdmtTypeDescr(String jdmtTypeDescr) {
		this.jdmtTypeDescr = jdmtTypeDescr;
	}
	public String getJudgmentDescr() {
		return judgmentDescr;
	}
	public void setJudgmentDescr(String judgmentDescr) {
		this.judgmentDescr = judgmentDescr;
	}
	
}
