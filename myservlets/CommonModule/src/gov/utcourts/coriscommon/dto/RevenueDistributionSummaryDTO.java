package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.transdist.TransDistBO;

import java.util.ArrayList;
import java.util.List;

public class RevenueDistributionSummaryDTO {
	
	private double totalRevenue;
	private double totalNsf;
	private double ybTotal;
	private double totalReverse;
	private double totalAdjustment;
	
	private List<TransDistBO> ybDistributions;
	List<RevenueDetailDTO> distrDetails;
	List<NsfChecksDTO> nsfChecks;
	List<RevenueProsecDTO> revenueProsecs;
	List<RevenueHeLeaDTO> revenueHeLeas;
	
	//part 9
	public double getTotalNsf() {
		totalNsf = 0.0;
		if(nsfChecks != null){
			for(NsfChecksDTO dto:nsfChecks){
				totalNsf += dto.getTransAmt();
			}
		}
		return totalNsf;
	}

	//part 2
	public List<RevenueDetailDTO> getDistrDetails() {
		if(distrDetails == null){
			distrDetails = new ArrayList<RevenueDetailDTO>();
		}
		return distrDetails;
	}

	public void setDistrDetails(List<RevenueDetailDTO> distrDetails) {
		this.distrDetails = distrDetails;
	}

	public void setTotalNsf(double totalNsf) {
		this.totalNsf = totalNsf;
	}

	//part 8
	public List<NsfChecksDTO> getNsfChecks() {
		if(nsfChecks==null){
			nsfChecks = new ArrayList<NsfChecksDTO>();
		}
		return nsfChecks;
	}

	public void setNsfChecks(List<NsfChecksDTO> nsfChecks) {
		this.nsfChecks = nsfChecks;
	}

	//part 3
	public double getTotalRevenue() {
		totalRevenue = 0.0;
		if(this.distrDetails != null){
			for(RevenueDetailDTO rev:distrDetails){
				if(rev.getRevenue() != null){
					totalRevenue += rev.getRevenue().doubleValue();
				}
			}
		}
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	//part 4
	public List<RevenueProsecDTO> getRevenueProsecs() {
		if(revenueProsecs == null){
			revenueProsecs = new ArrayList<RevenueProsecDTO>();
		}
		return revenueProsecs;
	}

	public void setRevenueProsecs(List<RevenueProsecDTO> revenueProsecs) {
		this.revenueProsecs = revenueProsecs;
	}

	//part 5
	public List<TransDistBO> getYbDistributions() {
		if(ybDistributions == null){
			ybDistributions = new ArrayList<TransDistBO>();
		}
		return ybDistributions;
	}

	public void setYbDistributions(List<TransDistBO> ybDistributions) {
		this.ybDistributions = ybDistributions;
	}
	//part 5
	public double getYbTotal() {
		return ybTotal;
	}

	public void setYbTotal(double ybTotal) {
		this.ybTotal = ybTotal;
	}

	//part 4-b
	public List<RevenueHeLeaDTO> getRevenueHeLeas() {
		if(revenueHeLeas == null){
			revenueHeLeas = new ArrayList<RevenueHeLeaDTO>();
		}
		return revenueHeLeas;
	}

	public void setRevenueHeLeas(List<RevenueHeLeaDTO> revenueHeLeas) {
		this.revenueHeLeas = revenueHeLeas;
	}

	public double getTotalReverse() {
		return totalReverse;
	}

	public void setTotalReverse(double totalReverse) {
		this.totalReverse = totalReverse;
	}

	public double getTotalAdjustment() {
		return totalAdjustment;
	}

	public void setTotalAdjustment(double totalAdjustment) {
		this.totalAdjustment = totalAdjustment;
	}	
}
