package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.casemevaluetext.CaseMeValueTextBO;

import java.sql.Connection;

import org.apache.log4j.Logger;

public class CaseMeValueTextXO {
	
	private static final Logger log = Logger.getLogger(AccountXO.class);
	
	public static CaseMeValueTextBO getCaseMeValueText(int meId, String meScreenId, String meFieldId, int meFieldSeq, String courtType, Connection conn) throws Exception {
		//////////////////////////////////////////////////////////////////////////////
		// Get Case Me Value Information
		//////////////////////////////////////////////////////////////////////////////
		CaseMeValueTextBO caseMeValueTextBO = new CaseMeValueTextBO(courtType).
		where(CaseMeValueTextBO.MEID, meId).
		where(CaseMeValueTextBO.MESCREENID, meScreenId).
		where(CaseMeValueTextBO.MEFIELDID, meFieldId).
		where(CaseMeValueTextBO.MEFIELDSEQ, meFieldSeq).
		setUseConnection(conn).
		find(); 
		return caseMeValueTextBO;
	}
	
	
}
