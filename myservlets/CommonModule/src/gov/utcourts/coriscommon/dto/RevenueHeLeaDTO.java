package gov.utcourts.coriscommon.dto;

public class RevenueHeLeaDTO {
	private int status;
	private String lea;
	private String leaDescr;
	private double revLeaAmt;
	private double heLeaAmt;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLea() {
		return lea;
	}
	public void setLea(String lea) {
		this.lea = lea;
	}
	public String getLeaDescr() {
		return leaDescr;
	}
	public void setLeaDescr(String leaDescr) {
		this.leaDescr = leaDescr;
	}
	public double getRevLeaAmt() {
		return revLeaAmt;
	}
	public void setRevLeaAmt(double revLeaAmt) {
		this.revLeaAmt = revLeaAmt;
	}
	public double getHeLeaAmt() {
		return heLeaAmt;
	}
	public void setHeLeaAmt(double heLeaAmt) {
		this.heLeaAmt = heLeaAmt;
	}
	
	
}
