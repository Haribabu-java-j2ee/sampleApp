package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;

public class RevenueProsecDTO {
	private int status;
	private String prosecAgency;
	private String prosecDescr;
	private BigDecimal fnAmt;
	private BigDecimal pnAmt;
	private BigDecimal heAmt;
	private BigDecimal edAmt;
	private BigDecimal ewAmt;
	private BigDecimal trAmt;
	private BigDecimal tmAmt;
	private BigDecimal irAmt;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getProsecAgency() {
		return prosecAgency;
	}
	public void setProsecAgency(String prosecAgency) {
		this.prosecAgency = prosecAgency;
	}
	public String getProsecDescr() {
		return prosecDescr;
	}
	public void setProsecDescr(String prosecDescr) {
		this.prosecDescr = prosecDescr;
	}
	public BigDecimal getFnAmt() {
		return fnAmt;
	}
	public void setFnAmt(BigDecimal fnAmt) {
		this.fnAmt = fnAmt;
	}
	public BigDecimal getPnAmt() {
		return pnAmt;
	}
	public void setPnAmt(BigDecimal pnAmt) {
		this.pnAmt = pnAmt;
	}
	public BigDecimal getHeAmt() {
		return heAmt;
	}
	public void setHeAmt(BigDecimal heAmt) {
		this.heAmt = heAmt;
	}
	public BigDecimal getEdAmt() {
		return edAmt;
	}
	public void setEdAmt(BigDecimal edAmt) {
		this.edAmt = edAmt;
	}
	public BigDecimal getEwAmt() {
		return ewAmt;
	}
	public void setEwAmt(BigDecimal ewAmt) {
		this.ewAmt = ewAmt;
	}
	public BigDecimal getTrAmt() {
		return trAmt;
	}
	public void setTrAmt(BigDecimal trAmt) {
		this.trAmt = trAmt;
	}
	public BigDecimal getTmAmt() {
		return tmAmt;
	}
	public void setTmAmt(BigDecimal tmAmt) {
		this.tmAmt = tmAmt;
	}
	public BigDecimal getIrAmt() {
		return irAmt;
	}
	public void setIrAmt(BigDecimal irAmt) {
		this.irAmt = irAmt;
	}

}
