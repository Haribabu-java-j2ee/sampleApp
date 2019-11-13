package gov.utcourts.corisweb.util;

import gov.utcourts.coris.pbws.CorisPowerBuilderWebService;
import gov.utcourts.coris.ws.results.PSCriticalMessageWrapper;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dto.CaseCriticalMessagesDTO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.dmzercommon.dataaccess.jailrelease.jailreleaseagreement.JailReleaseAgreementBO;
import gov.utcourts.dmzercommon.dataaccess.offenderdna.offenderdna.OffenderDnaBO;
import gov.utcourts.notifications.util.TextUtil;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class CriticalMessageUtil {
	
	private static Logger logger = Logger.getLogger(CriticalMessageUtil.class.getName());

	public static List<CaseCriticalMessagesDTO> addCriticalMessageForJailRealeaseAgreements(KaseBO kaseBO, List<CaseCriticalMessagesDTO> caseCriticalMessagesList) {
		try {
			String caseType = kaseBO.getCaseType();
			if (
			   "NA".equals(caseType) || 
			   "PO".equals(caseType) ||
			   "SK".equals(caseType) ||
			   "FS".equals(caseType) ||
			   "MO".equals(caseType) ||
			   "FJ".equals(caseType) ||
			   "MD".equals(caseType)
			) {

				// check to see if there are jra's
				int numberOfJailReleaseRecords = new JailReleaseAgreementBO()
				.where(JailReleaseAgreementBO.OTN, getOtn(kaseBO))
				.where(JailReleaseAgreementBO.COURTIDNEXTAPPEAR, getCourtLocation(kaseBO))
				.search()
				.size();
				
				String criticalMessage = null;
				if (numberOfJailReleaseRecords == 1) {
					criticalMessage = "Jail Release Agreement - " + getOtn(kaseBO);
				} else if (numberOfJailReleaseRecords > 1) {
					criticalMessage = "Jail Release Agreement (Possible) - " + getOtn(kaseBO);
				}
				
				// add critical message
				if (!TextUtil.isEmpty(criticalMessage)) {
					int status = 0; 
					caseCriticalMessagesList.add(new CaseCriticalMessagesDTO(status, criticalMessage));
				}
			}
			
		} catch(Exception e) {
			logger.error("Error retrieving Jail Release Agreements: " + e.getMessage());
		}
		return caseCriticalMessagesList;
	}

	public static List<CaseCriticalMessagesDTO> addCriticalMessageForOffenderDna(KaseBO kaseBO, List<CaseCriticalMessagesDTO> caseCriticalMessagesList) {
		try {
			String caseType = kaseBO.getCaseType();
			if (
			   "FS".equals(caseType) || 
			   "MO".equals(caseType) ||
			   "MD".equals(caseType)
			) {
				
				// check to see if there is offender dna
				int numberOfOffenderDnaRecords = new OffenderDnaBO()
				.where(OffenderDnaBO.OTN, getOtn(kaseBO))
				.where(OffenderDnaBO.PRELIMHEARINGDATE, Exp.IS_NULL)
				.search()
				.size();
				
				String criticalMessage = null;
				if (numberOfOffenderDnaRecords == 1) {
					criticalMessage = "DNA Process - " + getOtn(kaseBO);
				} else if (numberOfOffenderDnaRecords > 1) {
					criticalMessage = "DNA Process (Possible) - " + getOtn(kaseBO);
				}
				
				// add critical message
				if (!TextUtil.isEmpty(criticalMessage)) {
					int status = 0; 
					caseCriticalMessagesList.add(new CaseCriticalMessagesDTO(status, criticalMessage));
				}
			}
			
		} catch(Exception e) {
			logger.error("Error retrieving Offender DNA: " + e.getMessage());
		}
		return caseCriticalMessagesList;
	}
	
	public static List<CaseCriticalMessagesDTO> addCriticalMessageForArrestWarrants(KaseBO kaseBO, List<CaseCriticalMessagesDTO> caseCriticalMessagesList) {
		try {
			
			// check for coris warrant
			int warrantNo = new gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO(kaseBO.getCourtType())
			.includeFields(gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO.WARRNUM)
			.where(gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO.INTCASENUM, kaseBO.getIntCaseNum())
			.where(gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO.ISSUEDATE, Exp.IS_NOT_NULL)
			.where(
				new Expression(
					Exp.LEFT_PARENTHESIS,
						Exp.LEFT_PARENTHESIS,
							gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO.RECALLDATE, Exp.IS_NULL,
							Exp.AND,
							gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO.EXPDATE, Exp.GREATER_THAN, new Date(),
						Exp.RIGHT_PARENTHESIS,
						Exp.OR,
						Exp.LEFT_PARENTHESIS,
							gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO.RECALLDATE, Exp.IS_NULL,
							Exp.AND,
							gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO.EXPDATE, Exp.IS_NULL,
						Exp.RIGHT_PARENTHESIS,
					Exp.RIGHT_PARENTHESIS
				)
			)
			.find()
			.getWarrNum();
			
			// check to see if there is an arrest warrant on dmz
			if (warrantNo > 0) {
				
				int numberOfArrestWarrants = new gov.utcourts.dmzercommon.dataaccess.arrestwarrant.warrant.WarrantBO()
				.where(gov.utcourts.dmzercommon.dataaccess.arrestwarrant.warrant.WarrantBO.COURTCASENO, kaseBO.getCaseNum())
				.where(gov.utcourts.dmzercommon.dataaccess.arrestwarrant.warrant.WarrantBO.COURTLOCATION, kaseBO.getLocnCode())
				.where(gov.utcourts.dmzercommon.dataaccess.arrestwarrant.warrant.WarrantBO.COURTTYPE, kaseBO.getCourtType())
				.where(gov.utcourts.dmzercommon.dataaccess.arrestwarrant.warrant.WarrantBO.WARRANTNO, warrantNo)
				.search()
				.size();
				
				String criticalMessage = null;
				if (numberOfArrestWarrants == 0) {
					criticalMessage = "Warrant " + warrantNo + " Not on Statewide!  Please Resend!";
				}
				
				// add critical message
				if (!TextUtil.isEmpty(criticalMessage)) {
					int status = 0; 
					caseCriticalMessagesList.add(new CaseCriticalMessagesDTO(status, criticalMessage));
				}
			}
			
		} catch(Exception e) {
			logger.error("Error retrieving Arrest Warrants: " + e.getMessage());
		}
		return caseCriticalMessagesList;
	}
	
	public static List<CaseCriticalMessagesDTO> addCriticalMessageForProblemSolving(KaseBO kaseBO, List<CaseCriticalMessagesDTO> caseCriticalMessagesList) {
		try {
			CorisPowerBuilderWebService corisPowerBuilderWebService = new CorisPowerBuilderWebService();
			PSCriticalMessageWrapper wrapper = corisPowerBuilderWebService.getCasePSCriticalMessage(kaseBO.getIntCaseNum(), kaseBO.getCourtType(), "username", "password");
			if (wrapper != null && !TextUtil.isEmpty(wrapper.getPscCriticalMessage())) {
				int status = 0; 
				caseCriticalMessagesList.add(new CaseCriticalMessagesDTO(status, wrapper.getPscCriticalMessage()));
			}
			corisPowerBuilderWebService = null;
		} catch(Exception e) {
			logger.error("Error retrieving Critical messages for problem solving: " + e.getMessage());
		}
		return caseCriticalMessagesList;
	}
	
	
	private static String getOtn(KaseBO kaseBO) throws Exception {
		return new PartyCaseBO(kaseBO.getCourtType())
		.where(PartyCaseBO.INTCASENUM, kaseBO.getIntCaseNum())
		.where(PartyCaseBO.PARTYCODE, "DEF")
		.find()
		.getOtn();
	}
	
	private static String getCourtLocation(KaseBO kaseBO) {
		return kaseBO.getCourtType() + kaseBO.getLocnCode();
	}
}
