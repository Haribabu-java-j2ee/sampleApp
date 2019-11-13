package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.dataaccess.party.PartyBO;

public class PartyObject {
	private PartyBO partyBO;
	
	public PartyObject (PartyBO partyBO){
		this.partyBO = partyBO; 
	}
	public PartyBO getPartyBO() {
		return this.partyBO;
	}
}
