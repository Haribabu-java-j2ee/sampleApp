package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.partytype.PartyTypeBO;

public class CasePartyDTO {
	private PartyBO partyBO;
	private PartyCaseBO partyCaseBO;
	private PartyTypeBO partyTypeBO;
	
	public PartyBO getPartyBO() {
		return partyBO;
	}
	public void setPartyBO(PartyBO partyBO) {
		this.partyBO = partyBO;
	}
	public PartyCaseBO getPartyCaseBO() {
		return partyCaseBO;
	}
	public void setPartyCaseBO(PartyCaseBO partyCaseBO) {
		this.partyCaseBO = partyCaseBO;
	}
	public PartyTypeBO getPartyTypeBO() {
		return partyTypeBO;
	}
	public void setPartyTypeBO(PartyTypeBO partyTypeBO) {
		this.partyTypeBO = partyTypeBO;
	}

}
