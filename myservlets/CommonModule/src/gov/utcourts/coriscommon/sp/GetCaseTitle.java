package gov.utcourts.coriscommon.sp;

import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.civilcase.CivilCaseBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.securitytype.SecurityTypeBO;
import gov.utcourts.courtscommon.dispatcher.SearchDispatcher;
import gov.utcourts.coriscommon.dto.CasePartyDTO;
import gov.utcourts.coriscommon.util.CorisSecurityCommon;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SearchDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereCustomDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dispatcher.BaseSearchDispatcher;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

// Minors names are public in certain circumstances. Those circumstances are outlined in UCJA 4-202.02.
//(4)(O)      name of a minor (is private), except that the name of a minor party is public in the following 
//            district and justice court proceedings:
//	(4)(O)(i)     name change of a minor;
//	(4)(O)(ii)    guardianship or conservatorship for a minor;
//	(4)(O)(iii)   felony, misdemeanor, or infraction;
//	(4)(O)(iv)    protective orders; and
//	(4)(O)(v)     custody orders and decrees;



public class GetCaseTitle {

    private static final Logger logger = Logger.getLogger(GetCaseTitle.class.getName());
	static Connection conn = null;
	static boolean safeGuardedAccess = false;
	static boolean caseAccess = false;
	static boolean attorneyOverRide = false;
	static PersonnelBO personnelBO = null;
	
	public static String getCaseTitle(String logName, int intCaseNum, String courtType, Connection conn) throws Exception {
		StringBuilder title = new StringBuilder();
		String midCivilTitle = "";

		///////////////////////////////////////////////////////////////////////////
		// Get Case Information
		///////////////////////////////////////////////////////////////////////////
		KaseBO kaseBO = new KaseBO(courtType)
	   	.where(KaseBO.INTCASENUM, intCaseNum)
	   	.setUseConnection(conn)
		.find();
		
		caseAccess=true;
		
		if (!TextUtil.isEmpty(logName)){
			if (!"inquiry".equals(logName)){
				///////////////////////////////////////////////////////////////////////////
				// Get Personnel Information
				///////////////////////////////////////////////////////////////////////////
				if(kaseBO != null){
					personnelBO = new PersonnelBO(courtType)
					.where(PersonnelBO.LOGNAME, logName)
					.where(PersonnelBO.LOCNCODE, kaseBO.getLocnCode())
					.where(PersonnelBO.COURTTYPE, kaseBO.getCourtType())
					.setUseConnection(conn)
					.find();
				}
			
				///////////////////////////////////////////////////////////////////////////
				// if no Location Access then inquiry user
				///////////////////////////////////////////////////////////////////////////
				if (personnelBO != null){
					///////////////////////////////////////////////////////////////////////////
					// Restrict Case Types and Case Security
					///////////////////////////////////////////////////////////////////////////
					if ("IC".equals(kaseBO.getCaseType()) || "IS".equals(kaseBO.getCaseType())  || "OU".equals(kaseBO.getCaseType())){
						caseAccess = CorisSecurityCommon.checkSecurityAccess(personnelBO.getLogname(), "mental", personnelBO.getLocnCode(), personnelBO.getCourtType(), conn);
						if (caseAccess){
							if ("S".equals(kaseBO.getCaseSecurity())){
								///////////////////////////////////////////////////////////////////////////
								// Get Sealed Access
								///////////////////////////////////////////////////////////////////////////
								caseAccess = CorisSecurityCommon.checkSecurityAccess(personnelBO.getLogname(), "sealed", personnelBO.getLocnCode(), personnelBO.getCourtType(), conn);
							} else if ("X".equals(kaseBO.getCaseSecurity())){
								///////////////////////////////////////////////////////////////////////////
								// Get Expunged Access
								///////////////////////////////////////////////////////////////////////////
								caseAccess = CorisSecurityCommon.checkSecurityAccess(personnelBO.getLogname(), "expunged", personnelBO.getLocnCode(), personnelBO.getCourtType(), conn);
							} else if ("V".equals(kaseBO.getCaseSecurity()) || "O".equals(kaseBO.getCaseSecurity())){
								///////////////////////////////////////////////////////////////////////////
								// Get Private/Protected Access
								///////////////////////////////////////////////////////////////////////////
								caseAccess = CorisSecurityCommon.checkSecurityAccess(personnelBO.getLogname(), "privateprotected", personnelBO.getLocnCode(), personnelBO.getCourtType(), conn);
							}
						}
					} else if (!"V".equals(kaseBO.getCaseSecurity()) && !"U".equals(kaseBO.getCaseSecurity())){
						if (CorisSecurityCommon.checkSecurityAccess(personnelBO.getLogname(), "Inquirylogon", personnelBO.getLocnCode(), personnelBO.getCourtType(), conn)){
							///////////////////////////////////////////////////////////////////////////
							// Get Inquiry Access
							///////////////////////////////////////////////////////////////////////////
							caseAccess=false;
						} else if (CorisSecurityCommon.checkSecurityAccess(personnelBO.getLogname(), "managerreports", personnelBO.getLocnCode(), personnelBO.getCourtType(), conn)){
							///////////////////////////////////////////////////////////////////////////
							// Get Manager Access
							///////////////////////////////////////////////////////////////////////////
							caseAccess=false;
						} else if ("IC".equals(kaseBO.getCaseType()) || "IS".equals(kaseBO.getCaseType())  || "OU".equals(kaseBO.getCaseType())){
							///////////////////////////////////////////////////////////////////////////
							// Get Mental Access
							///////////////////////////////////////////////////////////////////////////
							caseAccess = CorisSecurityCommon.checkSecurityAccess(personnelBO.getLogname(), "mental", personnelBO.getLocnCode(), personnelBO.getCourtType(), conn);
						} else if ("S".equals(kaseBO.getCaseSecurity())){
							///////////////////////////////////////////////////////////////////////////
							// Get Sealed Access
							///////////////////////////////////////////////////////////////////////////
							caseAccess = CorisSecurityCommon.checkSecurityAccess(personnelBO.getLogname(), "sealed", personnelBO.getLocnCode(), personnelBO.getCourtType(), conn);
						} else if ("X".equals(kaseBO.getCaseSecurity())){
							///////////////////////////////////////////////////////////////////////////
							// Get Expunged Access
							///////////////////////////////////////////////////////////////////////////
							caseAccess = CorisSecurityCommon.checkSecurityAccess(personnelBO.getLogname(), "expunged", personnelBO.getLocnCode(), personnelBO.getCourtType(), conn);
						} else if ("V".equals(kaseBO.getCaseSecurity()) || "O".equals(kaseBO.getCaseSecurity())){
							///////////////////////////////////////////////////////////////////////////
							// Get Private/Protected Access
							///////////////////////////////////////////////////////////////////////////
							caseAccess = CorisSecurityCommon.checkSecurityAccess(personnelBO.getLogname(), "privateprotected", personnelBO.getLocnCode(), personnelBO.getCourtType(), conn);
						}
					}
				} else {
					if ("IC".equals(kaseBO.getCaseType()) || "IS".equals(kaseBO.getCaseType()) || "OU".equals(kaseBO.getCaseType())){
						caseAccess=false;
					} else if (!"U".equals(kaseBO.getCaseSecurity()) && !"V".equals(kaseBO.getCaseSecurity())){
						caseAccess=false;
					}
				}
			} else {
				if ("IC".equals(kaseBO.getCaseType()) || "IS".equals(kaseBO.getCaseType())  || "OU".equals(kaseBO.getCaseType())){
					caseAccess=false;
				} else if (!"U".equals(kaseBO.getCaseSecurity()) && !"V".equals(kaseBO.getCaseSecurity())){
					caseAccess=false;
				}
			}
		} else {
			if ("IC".equals(kaseBO.getCaseType()) || "IS".equals(kaseBO.getCaseType())  || "OU".equals(kaseBO.getCaseType())){
				caseAccess=false;
			} else if (!"U".equals(kaseBO.getCaseSecurity()) && !"V".equals(kaseBO.getCaseSecurity())){
				caseAccess=false;
			}
		}
		
		if (attorneyOverRide){
			caseAccess=true;
		}
		
		// /////////////////////////////////////////////////////////////////////////
		// Get Case Type
		// /////////////////////////////////////////////////////////////////////////
		if (kaseBO != null) {
			CaseTypeBO caseTypeBO = new CaseTypeBO(courtType).
			where(CaseTypeBO.CASETYPE, kaseBO.getCaseType()).
			setUseConnection(conn).
			find(CaseTypeBO.CATEGORY);

			if ("R".equals(caseTypeBO.getCategory())) {
				// /////////////////////////////////////////////////////////
				// Criminal Case Plaintiff Information "PLA"
				// /////////////////////////////////////////////////////////
				title.append(getPartyNameByPartyCode(kaseBO, "PLA", null, conn));

				// /////////////////////////////////////////////////////////
				// vs. line
				// /////////////////////////////////////////////////////////
				title.append(" vs. ");

				// /////////////////////////////////////////////////////////
				// Get Charge Violation Date
				// /////////////////////////////////////////////////////////
				ChargeBO chargeBO = new ChargeBO(courtType).where(ChargeBO.INTCASENUM, intCaseNum).where(ChargeBO.SEQ, 1).setUseConnection(conn).find();

				// /////////////////////////////////////////////////////////
				// Criminal Case Defendant Information "DEF"
				// /////////////////////////////////////////////////////////
				if (chargeBO != null) {
					title.append(getPartyNameByPartyCode(kaseBO, "DEF", chargeBO.getViolDatetime(), conn));
				} else if (kaseBO != null){
					title.append(getPartyNameByPartyCode(kaseBO, "DEF", kaseBO.getFilingDate(), conn));
				} else {
					title.append("?????");
				}
			} else {
				CivilCaseBO civilCaseBO = new CivilCaseBO(courtType).where(CivilCaseBO.INTCASENUM, kaseBO.getIntCaseNum()).setUseConnection(conn)
						.find(CivilCaseBO.TITLE, CivilCaseBO.TITLEPLANUM, CivilCaseBO.TITLEDEFNUM);

				//////////////////////////////////////////////////////////////////////// 
				// Per Paul Barron 01/30/2019 (Mary B also Meeting Ed's Desk)
				// Use title from civil case table, clerk responsible in party protected
				//////////////////////////////////////////////////////////////////////// 
				if (civilCaseBO != null) {
					if (!caseAccess){
						title.append("***** *****");
					} else if (!TextUtil.isEmpty(civilCaseBO.getTitle()) && (("P".equals(caseTypeBO.getCategory()) ||"IC".equals(kaseBO.getCaseType()) || "IS".equals(kaseBO.getCaseType()) || "OU".equals(kaseBO.getCaseType())))){
						title.append(civilCaseBO.getTitle());
					} else { 
						title.append(getInitalTitle(kaseBO.getCaseType()));
						if (TextUtil.isEmpty(title.toString())) {
							midCivilTitle = " vs. ";
						}
	
						// /////////////////////////////////////////////////////////
						// Civil Case Plaintiff Information base on title_pla_num
						// /////////////////////////////////////////////////////////
						if (civilCaseBO.getTitlePlaNum() > 0) {
							title.append(getPartyNameByIntCaseNum(kaseBO, civilCaseBO.getTitlePlaNum(), new Date(), conn));
						}
	
						// /////////////////////////////////////////////////////////
						// the vs title if not one of the others
						// /////////////////////////////////////////////////////////
						if (!TextUtil.isEmpty(midCivilTitle)) {
							title.append(midCivilTitle);
						}
	
						// /////////////////////////////////////////////////////////
						// Civil Case Defendant Information base on title_def_num
						// /////////////////////////////////////////////////////////
						if (civilCaseBO.getTitleDefNum() > 0) {
							title.append(getPartyNameByIntCaseNum(kaseBO, civilCaseBO.getTitleDefNum(), new Date(), conn));
						}
					}
				} else {
					title.append("????? vs. ?????");
				}
			}
		}
		kaseBO = null;
		personnelBO = null;
     	return title.toString();
	}
	/**
	 * This Method is unit tested properly for very different cases , 
	 * taking care of Leap Year days difference in a year, 
	 * and date cases month and Year boundary cases (12/31/1980, 01/01/1980 etc)
	**/

	public static int getAge(Date dateOfBirth, Date OffsViolDate) {
	    Calendar today = Calendar.getInstance();
	    Calendar birthDate = Calendar.getInstance();

	    int age = 0;

	    birthDate.setTime(dateOfBirth);
	    if (birthDate.after(today)) {
	        throw new IllegalArgumentException("Can't be born in the future");
	    }

	    age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

	    // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year   
	    if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
	            (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))){
	        age--;

	     // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
	    }else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) &&
	              (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))){
	        age--;
	    }
	    return age;
	}
	public static String getPartyNameByIntCaseNum(KaseBO kaseBO, int partyNum, Date ageDateCompare, Connection conn) {
		StringBuilder partyName = new StringBuilder();
		try {
			SearchDescriptor s1 = new SearchDescriptor(
				new PartyCaseBO(kaseBO.getCourtType())
				.where(PartyCaseBO.INTCASENUM, kaseBO.getIntCaseNum())
				.where(PartyCaseBO.PARTYNUM, partyNum)
				.setUseConnection(conn)
			);

			SearchDescriptor s2 = new SearchDescriptor(
				new PartyBO(kaseBO.getCourtType())
				.where(PartyCaseBO.PARTYNUM, partyNum)
				.setUseConnection(conn)
			);
			
			BaseSearchDispatcher searchDispatcher;
				searchDispatcher = new SearchDispatcher(s1, s2)
				.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
				.setResultContainer(new CasePartyDTO())
				.setUseConnection(conn)
				.search(PartyBO.LASTNAME, PartyBO.FIRSTNAME, PartyBO.BIRTHDATE, PartyCaseBO.PARTYCODE, PartyCaseBO.SAFEGUARDED).setIsolationDirtyRead(true);

			List<CasePartyDTO> casePartyListDTO = (List<CasePartyDTO>) searchDispatcher.setUseConnection(conn).getResults();
			
			if (casePartyListDTO.size() > 0 ){
				partyName.append(buildPartyName(casePartyListDTO.get(0), kaseBO, ageDateCompare, conn));
			} else {
				partyName.append("*****, *****");
			}
			
			s1=null;
			s2=null;
			casePartyListDTO = null;
		} catch (Exception e) {
            logger.error("getPartyNameByIntCaseNum(int intCaseNum, String courtType, int partyNum, Date ageDateCompare, Connection conn) " + e);
		}
		
		return partyName.toString();
	}
	
	public static String getPartyNameByPartyCode(KaseBO kaseBO, String partyCode, Date ageDateCompare, Connection conn) {
		StringBuilder partyName = new StringBuilder();
		try {
			SearchDescriptor s1 = new SearchDescriptor(
				new PartyCaseBO(kaseBO.getCourtType())
				.where(PartyCaseBO.INTCASENUM, kaseBO.getIntCaseNum())
				.where(PartyCaseBO.PARTYCODE, partyCode)
				.setUseConnection(conn)
			);

			SearchDescriptor s2 = new SearchDescriptor(
				new PartyBO(kaseBO.getCourtType())
				.setUseConnection(conn)
			);
			
			BaseSearchDispatcher searchDispatcher;
				searchDispatcher = new SearchDispatcher(s1, s2)
				.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
				.setResultContainer(new CasePartyDTO())
				.setUseConnection(conn)
				.search(PartyBO.LASTNAME, PartyBO.FIRSTNAME, PartyBO.BIRTHDATE, PartyCaseBO.PARTYCODE, PartyCaseBO.SAFEGUARDED);

			List<CasePartyDTO> casePartyListDTO = (List<CasePartyDTO>) searchDispatcher.setUseConnection(conn).getResults();
			
			if (casePartyListDTO.size() > 0 ){
				partyName.append(buildPartyName(casePartyListDTO.get(0), kaseBO, ageDateCompare, conn));
			} else {
				partyName.append("*****, *****");
			}
			
			s1=null;
			s2=null;
			casePartyListDTO = null;
		} catch (Exception e) {
			logger.error("getPartyNameByPartyCode(int intCaseNum, String courtType, String partyCode, Date ageDateCompare, Connection conn) " + e);		
		}
		
		return partyName.toString();
	}
	public static String buildPartyName(CasePartyDTO casePartyDTO, KaseBO kaseBO, Date ageDateCompare, Connection conn) {
		StringBuilder partyName = new StringBuilder();
		
		try {
			////////////////////////////////////////////////////////////////////////////
			// No case Access
			////////////////////////////////////////////////////////////////////////////
			if (!caseAccess){ 
				return "*****, *****";
			} else { 
				////////////////////////////////////////////////////////////////////////////
				// Safe guard Party
				////////////////////////////////////////////////////////////////////////////
				if ("Y".equals(casePartyDTO.getPartyCaseBO().getSafeguarded())){ 
					////////////////////////////////////////////////////////////////////////////
					// Email from Kristene Laterza  Fri, Mar 1, 2019 at 12:50 PM
					// if case type is one of these DO NOT display party name, 
					// party address is always protected 
					// No Applicable Severity (NA)
					// Involuntary Commitment MH (IC)
					// Involuntary Commitment SA (IS)
					// Registry Removal (RR)
					// Adoption (AD)
					// Gestational Agreement (GA)
					////////////////////////////////////////////////////////////////////////////
					if ("NA".equals(kaseBO.getCaseType()) || "IC".equals(kaseBO.getCaseType()) ||
					    "IS".equals(kaseBO.getCaseType()) || "RR".equals(kaseBO.getCaseType()) ||
						"AD".equals(kaseBO.getCaseType()) || "GA".equals(kaseBO.getCaseType())){
						////////////////////////////////////////////////////////////////////////////
						// Get Safe Guarded Access
						///////////////////////////////////////////////////////////////////////////
						if (!CorisSecurityCommon.checkSecurityAccess(personnelBO.getLogname(), "safeguarded", personnelBO.getLocnCode(), personnelBO.getCourtType(), conn)){
							return "*****, *****";
						}
					
					}
				}
				////////////////////////////////////////////////////////////////////////////
				// Public Can not see these party types in Title
				////////////////////////////////////////////////////////////////////////////
				if (!caseAccess){
					if ("MIN".equals(casePartyDTO.getPartyCaseBO().getPartyCode())){
						return "*****, *****";
					} else if ("ADP".equals(casePartyDTO.getPartyCaseBO().getPartyCode())){
						return "*****, *****";
					} else if ("ICP".equals(casePartyDTO.getPartyCaseBO().getPartyCode())){
						return "*****, *****";
					} else if ("PTP".equals(casePartyDTO.getPartyCaseBO().getPartyCode())){
						return "*****, *****";
					} else if ("VIC".equals(casePartyDTO.getPartyCaseBO().getPartyCode())){
						return "*****, *****";
					} else if ("WIT".equals(casePartyDTO.getPartyCaseBO().getPartyCode())){
						return "*****, *****";
					}
			   	}
				////////////////////////////////////////////////////////////////////////////
				// If 16 or younger
				////////////////////////////////////////////////////////////////////////////
				//if (casePartyDTO.getPartyBO().getBirthDate() != null){
				//	int age = getAge(casePartyDTO.getPartyBO().getBirthDate(), ageDateCompare);
				//	if (age <= 16){
				//		return "*****, *****";
				//	}
				//}
				
				if(TextUtil.isEmpty(casePartyDTO.getPartyBO().getFirstName())){
					partyName.append(casePartyDTO.getPartyBO().getLastName().trim());
				} else {
					partyName.append(casePartyDTO.getPartyBO().getFirstName().trim() + " " + casePartyDTO.getPartyBO().getLastName().trim());
				}
				
				FieldOperationDescriptor count = new FieldOperationDescriptor(PartyCaseBO.PARTYNUM, FieldOperationType.COUNT, new TypeInteger());
				PartyCaseBO partyCaseBO;
					partyCaseBO = new PartyCaseBO(kaseBO.getCourtType())
					.setFieldOperations(count)
					.where(PartyCaseBO.INTCASENUM, kaseBO.getIntCaseNum())
					.where(PartyCaseBO.PARTYCODE, casePartyDTO.getPartyCaseBO().getPartyCode())
					.setUseConnection(conn)
					.find();
						
				int etalCount = (Integer) partyCaseBO.get(count);
				if (etalCount > 1){
					partyName.append(" et al.");
				}
				
			}
		} catch(Exception e) {
			logger.error("buildPartyName(CasePartyDTO casePartyDTO, int intCaseNum, String courtType, Date ageDateCompare, Connection conn) " + e);		
		}
		return partyName.toString();
	}

	public static String getCaseTitleForAttorney(int intCaseNum, String courtType, int barNum, String barState, Connection conn) throws Exception {
		String title = "";
		String logName = "";
		
		AttyPartyBO attyPartyBO = new AttyPartyBO(courtType)
		.where(AttyPartyBO.INTCASENUM, intCaseNum)
		.where(AttyPartyBO.BARNUM, barNum)
		.where(AttyPartyBO.BARSTATE, barState)
		.setUseConnection(conn)
		.find();
		
		logName = "inquiry";
		attorneyOverRide = false;
		if (attyPartyBO != null){
			attorneyOverRide = true;
		}
		title = getCaseTitle(logName, intCaseNum, courtType, conn);
		
		attyPartyBO = null;
		attorneyOverRide = false;
		return title;
		
	}
	
	public static String getInitalTitle(String caseType){
		String title = "";
		if ("EI".equals(caseType)){
			title = "IN THE MATTER OF THE ESTATE OF ";
		} else if ("EF".equals(caseType)){
			title = "IN THE MATTER OF THE ESTATE OF ";
		} else if ("ES".equals(caseType)){
			title = "IN THE MATTER OF THE ESTATE OF ";
		} else if ("AD".equals(caseType)){
			title = "IN THE MATTER OF THE ADOPTION OF ";
		} else if ("NC".equals(caseType)){
			title = "IN THE MATTER OF THE NAME CHANGE OF ";
		} else if ("TR".equals(caseType)){
			title = "IN THE MATTER OF THE TRUST OF ";
		} else if ("MN".equals(caseType)){
			title = "IN THE MATTER OF ";
		} else if ("SU".equals(caseType)){
			title = "IN THE MATTER OF ";
		} else if ("OT".equals(caseType)){
			title = "IN THE MATTER OF ";
		} else if ("GA".equals(caseType)){
			title = "IN THE MATTER OF ";
		} else if ("CO".equals(caseType)){
			title = "IN THE MATTER OF THE CONSERVATORSHIP OF ";
		} else if ("GU".equals(caseType)){
			title = "IN THE MATTER OF THE GUARDIANSHIP OF ";
		} else if ("GB".equals(caseType)){
			title = "IN THE MATTER OF THE GUARDIANSHIP OF ";
		} else if ("GT".equals(caseType)){
			title = "IN THE MATTER OF THE GUARDIANSHIP OF ";
		} else if ("GM".equals(caseType)){
			title = "IN THE MATTER OF THE GUARDIANSHIP OF ";
		} else if ("IC".equals(caseType)){
			title = "IN THE MATTER OF ";
		} else if ("IS".equals(caseType)){
			title = "IN THE MATTER OF ";
		} else if ("MH".equals(caseType)){
			title = "IN THE MATTER OF THE MENTAL CONDITION OF ";
		}
		
		return title;
		
	}
	public static void main(String[] args) throws Exception {
		
		String courtType = "D";
		/////////////////////////////////////////////////////
		// Connect to database
		/////////////////////////////////////////////////////
		DatabaseConnection.setUseJdbc();
		if ("D".equals(courtType)){;
			conn = DatabaseConnection.getConnection(CaseTypeBO.CORIS_DISTRICT_DB);
		} else {
			conn = DatabaseConnection.getConnection(CaseTypeBO.CORIS_JUSTICE_DB);
		}
		
		try {
			
			List<KaseBO> kaseListBO = new KaseBO(courtType)
			.where(KaseBO.CASENUM, "094701334")
		   	.setUseConnection(conn)
			.search();
			String title="";

			for (KaseBO kaseBO : kaseListBO){
				title = getCaseTitle("inquiry", kaseBO.getIntCaseNum(), courtType, conn);
				//System.out.println(title);
				title = "";
			}
		} catch(Exception e) {
			logger.error("main " + e);		
		} finally {
			DatabaseConnection.setUseJndi();
			try {
				conn.close();
			} catch (SQLException e) {
				//System.out.println(e);
			}
			conn = null;
		}
	}
	
	public static boolean isAttorneyOverRide() {
		return attorneyOverRide;
	}
	public static void setAttorneyOverRide(boolean attorneyOverRide) {
		GetCaseTitle.attorneyOverRide = attorneyOverRide;
	}
}
