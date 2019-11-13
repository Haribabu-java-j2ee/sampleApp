package gov.utcourts.coriscommon.dto;

import java.math.BigDecimal;

public class FeeDTO {
	String feeCode;
	BigDecimal amount;
	
	public FeeDTO(String feeCode, BigDecimal amount){
		this.feeCode = feeCode;
		this.amount = amount;
	}
	public String getFeeCode() {
		return feeCode;
	}
	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
