package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.dataaccess.party.PartyBO;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class PartyRepository {
	
	private static Logger logger = Logger.getLogger(PartyRepository.class);
	
	/*
	 * used for storing miscellaneous page objects
	 * 
	 */
	protected HashMap<Integer, PartyObject> Party = new HashMap<Integer, PartyObject>();
	
	public PartyRepository addParty(int partyNum, PartyObject party) {
		Party.put(partyNum, party);
		return this;
	}
	
	public PartyRepository addParty(PartyBO PartyBO){
		PartyObject newPerson = new PartyObject(PartyBO);
		Party.put(newPerson.getPartyBO().getPartyNum(), newPerson);
		return this;
	}
	
	public PartyObject getPerson(int key) {
		return Party.get(key);
	}
	
}
