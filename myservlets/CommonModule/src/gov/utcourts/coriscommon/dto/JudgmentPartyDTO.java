package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.party.PartyBO;

public class JudgmentPartyDTO {
	
	private int jdmtSeq;
	private int detlSeq;
	private PartyBO partyBO;
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
	public PartyBO getPartyBO() {
		return partyBO;
	}
	public void setPartyBO(PartyBO partyBO) {
		this.partyBO = partyBO;
	}
	
	

}
