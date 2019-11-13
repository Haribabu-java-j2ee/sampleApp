package gov.utcourts.corisweb.xo;

import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.personcentral.dataaccess.sidpartycentral.SidPartyCentralBO;
import gov.utcourts.personcentral.xo.SidPartyCentralXO;
import gov.utcourts.problemsolving.dataaccess.psparticipant.PsParticipantBO;

public class PsParticipantXO {

	public static PsParticipantBO getParticipantData(int intCaseNum, String courtType) throws Exception{
		int sid = 0;
		PsParticipantBO parti = new PsParticipantBO();
		PartyCaseBO pvo = new PartyCaseBO(courtType).where(PartyCaseBO.PARTYCODE,"DEF")
									.where(PartyCaseBO.INTCASENUM,intCaseNum).find();
		if(pvo != null){
			PartyBO party = new PartyBO(courtType).where(PartyBO.PARTYNUM,pvo.getPartyNum()).find();
			parti.setPartyNum(party.getPartyNum());
			parti.setPsBirthDate(party.getBirthDate());
			parti.setPsFirstName(party.getFirstName());
			parti.setPsLastName(party.getLastName());
			parti.setPsSsn(party.getSsn());
			SidPartyCentralBO sidParty = SidPartyCentralXO.getSidPartyCentral(pvo.getPartyNum(), courtType, null);
			if(sidParty != null && !TextUtil.isEmpty(sidParty.getSid())){
				sid = Integer.parseInt(sidParty.getSid());
			}
			parti.setPsSid(sid);
		}
		
		return parti;
	}
}
