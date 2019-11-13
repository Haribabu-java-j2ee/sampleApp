package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.transdist.TransDistBO;

import java.math.BigDecimal;
import java.util.List;

public class RevenueDetailDTO {
	
	private int status;
	private String distrbCode;
	private String acctNum;
	private String descr;
	private BigDecimal revenue;
	private int count;
	private BigDecimal credit;
	
	private List<TransDistBO> tmRevProsecs;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDistrbCode() {
		return distrbCode;
	}
	public void setDistrbCode(String distrbCode) {
		this.distrbCode = distrbCode;
	}
	public String getAcctNum() {
		return acctNum;
	}
	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public BigDecimal getRevenue() {
		return revenue;
	}
	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal BigDecimal) {
		this.credit = credit;
	}
	public List<TransDistBO> getTmRevProsecs() {
		return tmRevProsecs;
	}
	public void setTmRevProsecs(List<TransDistBO> tmRevProsecs) {
		this.tmRevProsecs = tmRevProsecs;
	}
}
