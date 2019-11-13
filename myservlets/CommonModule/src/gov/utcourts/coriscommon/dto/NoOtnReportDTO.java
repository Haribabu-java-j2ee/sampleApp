package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;

import java.util.List;

public class NoOtnReportDTO {
	
	private int totalCases;
	private int totalOtns;
	private int totalCitations;
	private int casesWithout;
	private int casesWithArrestDate;
	private int casesWithDob;
	private int casesWithDobOtn;
	private int casesWithDoaOtn;
	private int totalCharges;
	private int totalFelonyCharges;
	private int totalMisdemCharges;
	private List<KaseBO> kases;
	
	
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	public int getTotalOtns() {
		return totalOtns;
	}
	public void setTotalOtns(int totalOtns) {
		this.totalOtns = totalOtns;
	}
	public int getTotalCitations() {
		return totalCitations;
	}
	public void setTotalCitations(int totalCitations) {
		this.totalCitations = totalCitations;
	}
	public int getCasesWithout() {
		return casesWithout;
	}
	public void setCasesWithout(int casesWithout) {
		this.casesWithout = casesWithout;
	}
	public int getCasesWithArrestDate() {
		return casesWithArrestDate;
	}
	public void setCasesWithArrestDate(int casesWithArrestDate) {
		this.casesWithArrestDate = casesWithArrestDate;
	}
	public int getCasesWithDob() {
		return casesWithDob;
	}
	public void setCasesWithDob(int casesWithDob) {
		this.casesWithDob = casesWithDob;
	}
	public int getCasesWithDobOtn() {
		return casesWithDobOtn;
	}
	public void setCasesWithDobOtn(int casesWithDobOtn) {
		this.casesWithDobOtn = casesWithDobOtn;
	}
	public int getCasesWithDoaOtn() {
		return casesWithDoaOtn;
	}
	public void setCasesWithDoaOtn(int casesWithDoaOtn) {
		this.casesWithDoaOtn = casesWithDoaOtn;
	}
	public int getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(int totalCharges) {
		this.totalCharges = totalCharges;
	}
	public int getTotalFelonyCharges() {
		return totalFelonyCharges;
	}
	public void setTotalFelonyCharges(int totalFelonyCharges) {
		this.totalFelonyCharges = totalFelonyCharges;
	}
	public int getTotalMisdemCharges() {
		return totalMisdemCharges;
	}
	public void setTotalMisdemCharges(int totalMisdemCharges) {
		this.totalMisdemCharges = totalMisdemCharges;
	}
	public List<KaseBO> getKases() {
		return kases;
	}
	public void setKases(List<KaseBO> kases) {
		this.kases = kases;
	}
}
