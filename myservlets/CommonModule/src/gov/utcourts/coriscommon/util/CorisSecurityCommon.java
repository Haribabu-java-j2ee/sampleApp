package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.dataaccess.application.ApplicationBO;
import gov.utcourts.coriscommon.dataaccess.persapplic.PersApplicBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.securitytype.SecurityTypeBO;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.courtscommon.constants.BaseConstants;

import java.sql.Connection;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class CorisSecurityCommon implements BaseConstants {
	
	private static Logger logger = Logger.getLogger(CorisSecurityCommon.class.getName());
	
	public static String INQUIRYLOGON = "Inquirylogon";
	public static String PRIVATEPROTECTED = "privateprotected";
	public static String SEALED = "sealed";
	public static String EXPUNGED = "expunged";
	public static String MENTAL = "mental";
	public static String SAFEGUARDED = "safeguarded";

	
	public static boolean checkSecurityAccess(String logName, String applicName, String locnCode, String courtType, Connection conn){
		Boolean access = false;
		try {
			
			/////////////////////////////////////////////////////
			// Get PersonnelVO
			/////////////////////////////////////////////////////
			PersonnelBO personnelBO = new PersonnelBO(courtType).
			where(PersonnelBO.LOGNAME, logName).
			where(PersonnelBO.LOCNCODE, locnCode).
			where(PersonnelBO.COURTTYPE, courtType).
			setUseConnection(conn).
			find(PersonnelBO.USERIDSRL);
			
			/////////////////////////////////////////////////////
			// Get ApplicationVO
			/////////////////////////////////////////////////////
			ApplicationBO applicationBO = new ApplicationBO(courtType).
			where(ApplicationBO.APPLICNAME, applicName).
			setUseConnection(conn).
			find(ApplicationBO.APPLICID);
	
			/////////////////////////////////////////////////////
			// Get Application ID Status
			/////////////////////////////////////////////////////
			PersApplicBO persApplicBO = (personnelBO == null) ? null : new PersApplicBO(courtType).
			where(PersApplicBO.USERIDSRL, personnelBO.getUseridSrl()).
			where(PersApplicBO.APPLICID, applicationBO.getApplicId()).
			setUseConnection(conn).
			setReturnNull(true).
			find(PersApplicBO.APPLICID);

			/////////////////////////////////////////////////////
			// If access grant access
			/////////////////////////////////////////////////////
			if (persApplicBO == null){
				access = false;
			} else {
				access = true;
			}
			/////////////////////////////////////////////////////
			// Clean Up
			/////////////////////////////////////////////////////
			personnelBO = null;
			applicationBO = null;
			persApplicBO = null; 
		} catch (Exception e) {
			logger.error("CaseHistoryCommon checkSecurityAccess(String logName, String applicName, String locnCode, String courtType)", e);
		}
	
		return access;
	}
	public static boolean checkLocationAccess(String logName, String locnCode, String courtType, Connection conn){
		Boolean access = false;
		try {
			
			/////////////////////////////////////////////////////
			// Get PersonnelVO
			/////////////////////////////////////////////////////
			PersonnelBO personnelBO = new PersonnelBO(courtType).
			where(PersonnelBO.LOGNAME, logName).
			where(PersonnelBO.LOCNCODE, locnCode).
			setUseConnection(conn).
			find(PersonnelBO.USERIDSRL);
			

			/////////////////////////////////////////////////////
			// If access grant access
			/////////////////////////////////////////////////////
			if (personnelBO == null){
				access = false;
			} else {
				access = true;
			}
			/////////////////////////////////////////////////////
			// Clean Up
			/////////////////////////////////////////////////////
			personnelBO = null;
		} catch (Exception e) {
			logger.error("CaseHistoryCommon checkLocationAccess(String logName, String locnCode, String courtType)", e);
		}
	
		return access;
	}
	public static SecurityTypeBO getSecurityType(String security, String courtType, Connection conn){
		SecurityTypeBO securityTypeBO = null;
		/////////////////////////////////////////////////////////////////
		// Create Security Type 
		/////////////////////////////////////////////////////////////////
		try {
			securityTypeBO  = new SecurityTypeBO(courtType).
			where(SecurityTypeBO.CODE, security).
			setUseConnection(conn).
			find();
		} catch (Exception e) {
			logger.error("Process SecurityTypeBO.getSecurityTypeByCode(corisCaseHistoryDTO.getKaseBO().getCaseSecurity(), corisCaseHistoryDTO.getKaseBO().getCourtType(), useStatic)", e);
		}
		
		return securityTypeBO;
	}
	
	public static Boolean grantAccess(String logName, String security, String locnCode, String courtType, Connection conn){
		Boolean access = false;
		if ("U".equals(security)){
			access = true;
		} else if ("V".equals(security)){
			access = checkSecurityAccess(logName, "privateprotected", locnCode, courtType, conn);
		} else if ("O".equals(security)){
			access = checkSecurityAccess(logName, "privateprotected", locnCode, courtType, conn);
		} else if ("S".equals(security)){
			access = checkSecurityAccess(logName, "sealed", locnCode, courtType, conn);
		} else if ("X".equals(security)){
			access = checkSecurityAccess(logName, "expunged", locnCode, courtType, conn);
		} else if ("M".equals(security)){
			access = checkSecurityAccess(logName, "mental", locnCode, courtType, conn);
		} else if ("V".equals(security)){
			access = checkSecurityAccess(logName, "safeguarded", locnCode, courtType, conn);
		}	
		return access;
	}
	public static String getLogName(int useridSrl, String courtType, PersonnelRepository personnelRepository, Connection conn){
		String logName = "";
		try {
			if (useridSrl > 0){
				PersonnelObject person = null;
				if (personnelRepository != null){
					person = personnelRepository.getPerson(useridSrl);
				}
				
				if (person == null){
					/////////////////////////////////////////////////////
					// Get PersonnelVO
					/////////////////////////////////////////////////////
					PersonnelBO personnelBO = new PersonnelBO(courtType).
					where(PersonnelBO.USERIDSRL, useridSrl).
					setUseConnection(conn).
					find(PersonnelBO.USERIDSRL, PersonnelBO.LOGNAME, PersonnelBO.FIRSTNAME, PersonnelBO.LASTNAME, PersonnelBO.TYPE);
					
					personnelRepository.addPerson(personnelBO);
					
					logName = personnelBO.getLogname().trim();
				} else {
					logName = person.getLogName(); 
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return logName;
	}
	public static int getUseridSrl(String logName, String locnCode, String courtType){
		int useridSrl = 0;
		
		try {
			PersonnelBO personnelBO = new PersonnelBO(courtType)
			.where(PersonnelBO.LOGNAME, logName)
			.where(PersonnelBO.LOCNCODE, locnCode)
			.where(PersonnelBO.COURTTYPE, courtType)
			.find(PersonnelBO.USERIDSRL);
			useridSrl = personnelBO.getUseridSrl();
			personnelBO = null;
		} catch (Exception e) {
			logger.error("getUseridSrl(String logName, String locnCode, String courtType)", e);
		}
				
		return useridSrl;
	}
	
	/**
	 * Used in presentation layer to filter case displays
	 * @param caseAccessLevel
	 * @param caseType
	 * @param caseSecurity
	 * @return -- boolean: if false, only case_num displayed without hyper link
	 */
	public static boolean hasCaseAccess(HashMap<String, Integer> caseAccessLevel, String caseType,String caseSecurity){
		return hasCaseTypeAccess(caseAccessLevel, caseType) && hasCaseSecurityAccess(caseAccessLevel, caseSecurity);
	}
	
	/**
	 * Used in presentation layer
	 * @param caseAccessLevel
	 * @param kaseType
	 * @param partyCode
	 * @return
	 */
	public static boolean hasPartyAccess(HashMap<String, Boolean> caseAccessLevel, String kaseType, String partyCode) {
		
		if (!"CO".equals(kaseType) && !"CS".equals(kaseType) && !"FD".equals(kaseType) && !"FS".equals(kaseType)
				&& !"GU".equals(kaseType) && !"IF".equals(kaseType) && !"MD".equals(kaseType) && !"MO".equals(kaseType)
				&& !"NA".equals(kaseType) && !"PC".equals(kaseType) && !"PN".equals(kaseType) && !"PO".equals(kaseType)
				&& !"TC".equals(kaseType) && !"TN".equals(kaseType)) {
			if (caseAccessLevel.get(MENTAL) == null) {
				if ("MIN".equals(partyCode)) { // MIN = Minor Child
					// do a break or a continue or whatever needs to be done to
					// skip getting the party name because the party name cannot
					// be displayed
					return false;
				}
			}
		}

		// Party Type
			// party_code descr
			// ADP Adoptee
			// BON Bondsman
			// ICP Incomp/Incap Person
			// PTP Protected Person
			// PYE Payee
			// PYR Payor
			// VIC Victim
			// WIT Witness
		if ("ADP".equals(partyCode) || "BON".equals(partyCode) || "ICP".equals(partyCode) || "PTP".equals(partyCode)
				|| "PYE".equals(partyCode) || "PYR".equals(partyCode) || "VIC".equals(partyCode)
				|| "WIT".equals(partyCode)) {
			if (caseAccessLevel.get(SAFEGUARDED) == null) {
				// do a break or continue or whatever needs to be done to skip
				// getting the party name because the party name cannot be
				// displayed
				return false;
			}
		}

		return !caseAccessLevel.isEmpty();

	}
	
	/**
	 * Generate user level security roles -- all the user access rights
	 * @param criteria
	 * @param conn
	 * @return
	 */
	public static HashMap<String, Boolean> getUserSecurityLevels(ReportBaseSearchCriteria criteria, Connection conn){
		HashMap<String, Boolean> levelMap = new HashMap<String, Boolean>();

		if(CorisSecurityCommon.checkSecurityAccess(criteria.getLogName(), INQUIRYLOGON, criteria.getLocnCode(), criteria.getCourtType(), conn)){
			levelMap.put(INQUIRYLOGON, true);
		}

		if(CorisSecurityCommon.checkSecurityAccess(criteria.getLogName(), PRIVATEPROTECTED, criteria.getLocnCode(), criteria.getCourtType(), conn)){
			levelMap.put(PRIVATEPROTECTED, true);
		}
		if(CorisSecurityCommon.checkSecurityAccess(criteria.getLogName(), SEALED, criteria.getLocnCode(), criteria.getCourtType(), conn)){
			levelMap.put(SEALED, true);
		}
		if(CorisSecurityCommon.checkSecurityAccess(criteria.getLogName(), EXPUNGED, criteria.getLocnCode(), criteria.getCourtType(), conn)){
			levelMap.put(EXPUNGED, true);
		}
			
		if(CorisSecurityCommon.checkSecurityAccess(criteria.getLogName(), MENTAL, criteria.getLocnCode(), criteria.getCourtType(), conn)){
			levelMap.put(MENTAL, true);
		}
		if(CorisSecurityCommon.checkSecurityAccess(criteria.getLogName(), SAFEGUARDED, criteria.getLocnCode(), criteria.getCourtType(), conn)){
			levelMap.put(SAFEGUARDED,true );
		}
				
		return levelMap;
	}
	
	/**
	 * Used to filter out mental and safe-guarded cases
	 * @param caseAccessLevel
	 * @return List<String> -- list of special case types that is not accessible based the on user security access levels
	 */
	private static boolean hasCaseTypeAccess(HashMap<String, Integer> caseAccessLevel, String caseType) {
		
		if(caseAccessLevel.get(MENTAL) == null){
			return !("IC".equalsIgnoreCase(caseType) || "IS".equalsIgnoreCase(caseType));
		}
		if(caseAccessLevel.get(SAFEGUARDED) == null){
			return !("AD".equalsIgnoreCase(caseType) || "GA".equalsIgnoreCase(caseType));
		}		
		return true;
	}
	
	/**
	 * Can be used to filter out cases based on Kase.case_security
	 * @param caseAccessLevel
	 * @return List<String> -- accessible case securities list
	 */
	private static boolean hasCaseSecurityAccess(HashMap<String, Integer> caseAccessLevel, String caseSecurity) {
		
		if(caseAccessLevel.get(INQUIRYLOGON) != null && "U".equalsIgnoreCase(caseSecurity)){
			return true; 
		}
		if(caseAccessLevel.get(PRIVATEPROTECTED) != null && ("V".equalsIgnoreCase(caseSecurity) || "O".equalsIgnoreCase(caseSecurity))){
			return true;
		}
		if(caseAccessLevel.get(SEALED) != null &&  "S".equalsIgnoreCase(caseSecurity)){
			return true;
		}
		if(caseAccessLevel.get(EXPUNGED) != null && "X".equalsIgnoreCase(caseSecurity)){
			return true;
		}
		
		return "U".equalsIgnoreCase(caseSecurity);
	}
	
}



