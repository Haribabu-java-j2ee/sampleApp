package gov.utcourts.corisweb.xo;

import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.problemsolving.constants.PSCDefnConstants;
import gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO;
import gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO;
import gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO;
import gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO;
import gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO;
import gov.utcourts.problemsolving.xo.PsReferralHistoryXO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

/**
 * @author hongbo.tang
 *
 */
public class PsReferralXO {
	
	
	/**
	 * @param ref
	 * @throws Exception
	 */
	public static HashMap<String, Integer> createPsReferral(PsReferralBO ref, String logName, Connection pscConn) throws Exception{
		PsReferralBO refBO = new PsReferralBO().setUseConnection(pscConn);
		PsReferralBO refTwo = (PsReferralBO) refBO
							.setPsClerkId(ref.getPsClerkId())
							.setCreateDatetime(ref.getCreateDatetime())
							.setPsCourtDefnId(ref.getPsCourtDefnId())
							.setCourtProfileId(ref.getCourtProfileId())
							.setPsAssignedJudgeId(ref.getPsAssignedJudgeId())
							.setPsParticipantId(ref.getPsParticipantId())
							.setPsReferralDate(ref.getPsReferralDate())
							.setPsStatusDefnId(ref.getPsStatusDefnId())
							.setPsPhaseDefnId(ref.getPsPhaseDefnId())
							.setPsCompliantSinceDate(ref.getPsCompliantSinceDate())
							.insert();
		
		int histSeq = PsReferralHistoryXO.saveInitialCorisCaseReferralHistory(refTwo.getPsReferralId(), logName, pscConn);
		
		HashMap<String, Integer> retMap = new HashMap<String, Integer>();
		retMap.put("referralId", refTwo.getPsReferralId());
		retMap.put("psHistoryId", histSeq);
		return retMap;
		
	}

	/**
	 * @param ref
	 * @param actionCode
	 * @param note
	 * @param psUserId
	 * @param psActionDate
	 * @throws Exception
	 */
	public static void updatePsReferral(PsReferralBO ref, Connection conn) throws Exception {
		new PsReferralBO().setUseConnection(conn)
						  .where(PsReferralBO.PSREFERRALID, ref.getPsReferralId())
						  .setPsCourtDefnId(ref.getPsCourtDefnId())
						  .setCourtProfileId(ref.getCourtProfileId())
						  .setPsPhaseDefnId(ref.getPsPhaseDefnId())
						  .setPsStatusDefnId(ref.getPsStatusDefnId())
						  .setPsCompliantSinceDate(ref.getPsCompliantSinceDate())
						  .setPsReferralDate(ref.getPsReferralDate())
						  .update();
	}
	
	/**
	 * @param caseId
	 * @return
	 * @throws Exception
	 */
	public static PsReferralBO getPsCaseReferral(int caseId, Connection conn) throws Exception{
		int referralId = new PsReferralCaseBO()
							.setUseConnection(conn)
							.where(PsReferralCaseBO.CASEIDENTIFIER,caseId)
							.find(PsReferralCaseBO.PSREFERRALID)
							.getPsReferralId();
		return new PsReferralBO()
				.setUseConnection(conn)
				.where(PsReferralBO.PSREFERRALID,referralId)
				.find();
	}
	
	/**
	 * @param referralId
	 * @param caseId
	 * @param jurisdiction
	 * @throws Exception
	 */
	public static void addPsReferralCase(int referralId, int caseId, int psHistoryId, String jurisdiction, Connection conn) throws Exception{

		new PsReferralCaseBO().setUseConnection(conn)
							  .setPsReferralId(referralId)
							  .setCaseidentifier(caseId)
							  .setJurisdiction(jurisdiction)
							  .setPsHistoryId(psHistoryId)
							  .insert();
	}

	/**
	 * @param intCaseNum
	 * @param courtType
	 * @param conn 
	 * @return
	 * @throws Exception
	 */
	public static List<PsReferralBO> findCaseReferrals(int intCaseNum, String courtType, boolean getDeleted, Connection conn) throws Exception {
		PsReferralBO refBO = new PsReferralBO().setUseConnection(conn).includeFields(PsReferralBO.ALL_FIELDS);
		if (!getDeleted) {
			int deleteStatusId = new PsStatusDefnBO().setUseConnection(conn).where(PsStatusDefnBO.PSSTATUSCODE, PSCDefnConstants.STATUS_DELETE_CODE)
													 .find(PsStatusDefnBO.PSSTATUSDEFNID).getPsStatusDefnId();
			refBO.where(PsReferralBO.PSSTATUSDEFNID, Exp.NOT_EQUALS, deleteStatusId);
		}
		return refBO.includeTables(new PsReferralCaseBO().where(PsReferralCaseBO.CASEIDENTIFIER, intCaseNum).where(PsReferralCaseBO.JURISDICTION, courtType),
						   	   new PsCourtDefnBO().includeFields(PsCourtDefnBO.PSCODEDESCR), new CourtProfileBO().includeFields(CourtProfileBO.COURTTITLE))
				.addForeignKey(PsReferralBO.PSREFERRALID, PsReferralCaseBO.PSREFERRALID).addForeignKey(PsReferralBO.PSCOURTDEFNID, PsCourtDefnBO.PSCOURTDEFNID)
				.addForeignKey(PsReferralBO.COURTPROFILEID, CourtProfileBO.COURTPROFILEID).orderBy(PsReferralBO.PSREFERRALID, DirectionType.DESC).search();
	}

	public static List<PsReferralBO> getReferral(int refId) throws Exception {
		return new PsReferralBO().includeFields(PsReferralBO.ALL_FIELDS)
								.where(PsReferralBO.PSREFERRALID, refId)
								.includeTables(new PsReferralCaseBO().includeFields(PsReferralCaseBO.CASEIDENTIFIER,PsReferralCaseBO.PSHISTORYID, PsReferralCaseBO.JURISDICTION))
								.includeTables(new CourtProfileBO().includeFields(CourtProfileBO.COURTTITLE))
								.includeTables(new PsCourtDefnBO().includeFields(PsCourtDefnBO.PSCODEDESCR))
								.addForeignKey(PsReferralBO.PSREFERRALID, PsReferralCaseBO.PSREFERRALID)
								.addForeignKey(PsReferralBO.COURTPROFILEID, CourtProfileBO.COURTPROFILEID)
								.addForeignKey(PsReferralBO.PSCOURTDEFNID, PsCourtDefnBO.PSCOURTDEFNID)
								.search();
	}
	
	public static List<PsReferralBO> getCaseActivePsCourtReferral(int intCaseNum, String courtType, int psCourtId, Connection conn) throws Exception{
		int deleteStatusId = new PsStatusDefnBO().setUseConnection(conn).where(PsStatusDefnBO.PSSTATUSCODE, PSCDefnConstants.STATUS_DELETE_CODE)
		 										.find(PsStatusDefnBO.PSSTATUSDEFNID).getPsStatusDefnId();
		return new PsReferralBO().where(PsReferralBO.PSCOURTDEFNID,psCourtId).where(PsReferralBO.PSSTATUSDEFNID, Exp.NOT_EQUALS, deleteStatusId)
								.includeTables(new PsReferralCaseBO().where(PsReferralCaseBO.CASEIDENTIFIER, intCaseNum).where(PsReferralCaseBO.JURISDICTION,courtType))
								.addForeignKey(PsReferralBO.PSREFERRALID, PsReferralCaseBO.PSREFERRALID)
								.search();
	}
}
