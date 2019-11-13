package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dto.CaseCriticalMessagesDTO;
import gov.utcourts.coriscommon.dto.CaseWarningsDTO;
import gov.utcourts.coriscommon.dto.CorisCaseHistoryDTO;
import gov.utcourts.coriscommon.dto.SafeGuardedDismissedPartyDTO;
import gov.utcourts.coriscommon.dto.SummaryEventDTO;
import gov.utcourts.coriscommon.sp.GetCaseCriticalMessagesStoredProcedure;
import gov.utcourts.coriscommon.sp.GetCaseWarningsStoredProcedure;
import gov.utcourts.coriscommon.util.CorisSecurityCommon;
import gov.utcourts.corisinterface.util.CorisCaseHistoryCommon;
import gov.utcourts.corisweb.util.CriticalMessageUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereCustomDescriptor;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.docmgr.objects.CaseDocSeqVO;
import gov.utcourts.docmgr.util.CaseDocSeqUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CorisSummaryEventReport {
	
	static Connection conn = null;
	
	private static Logger logger = Logger.getLogger(CorisSummaryEventReport.class.getName());
	private static final String FIND_PAGE = "/jsp/SummaryEvent.jsp";
	
	/**
	 * Handle find request
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String caseNum = URLEncryption.getParamAsString(request, "caseNum");
			String locnCode = URLEncryption.getParamAsString(request, "locnCode");
			String courtType = URLEncryption.getParamAsString(request, "courtType");
			String logName = URLEncryption.getParamAsString(request, "logName");
			
			/////////////////////////////////////////////////////
			// Connect to database
			/////////////////////////////////////////////////////
			if ("D".equals(courtType)){;
				conn = DatabaseConnection.getConnection(AccountBO.CORIS_DISTRICT_DB);
			} else {
				conn = DatabaseConnection.getConnection(AccountBO.CORIS_JUSTICE_DB);
			}
			/////////////////////////////////////////////////////
			// Security
			/////////////////////////////////////////////////////
			Boolean inquiryUser = CorisSecurityCommon.checkSecurityAccess(logName, "Inquirylogon", locnCode, courtType, conn);
			Boolean locnAccess = CorisSecurityCommon.checkLocationAccess(logName, locnCode, courtType, conn);
			Boolean managerUser = CorisSecurityCommon.checkSecurityAccess(logName, "managerreports", locnCode, courtType, conn);
			if (!locnAccess || managerUser) {
				inquiryUser = true; 
			}
			Boolean privateProtectedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "privateprotected", locnCode, courtType, conn);
			Boolean sealedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "sealed", locnCode, courtType, conn);
			Boolean expungedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "expunged", locnCode, courtType, conn);
			Boolean mentalAccess = CorisSecurityCommon.checkSecurityAccess(logName, "mental", locnCode, courtType, conn);
			Boolean safeGuardedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "safeguarded", locnCode, courtType, conn);			
			request.setAttribute("courtMaintAccess", CorisSecurityCommon.checkSecurityAccess(logName, "courtmaint", locnCode, courtType, conn));
			request.setAttribute("caseHistDelAccess", CorisSecurityCommon.checkSecurityAccess(logName, "casehistdel", locnCode, courtType, conn));

	
			/////////////////////////////////////////////////////
			// Case Data Initialized
			/////////////////////////////////////////////////////
			CorisCaseHistoryDTO corisCaseHistoryDTO = new CorisCaseHistoryDTO();
			
			/////////////////////////////////////////////////////
			// Kase Data
			/////////////////////////////////////////////////////
			corisCaseHistoryDTO.setKaseBO(new KaseBO(courtType)
			.where(KaseBO.CASENUM, caseNum)
			.where(KaseBO.LOCNCODE, locnCode)
			.setUseConnection(conn)
			.find());
			
			/////////////////////////////////////////////////////
			// Case Access
			/////////////////////////////////////////////////////
			Boolean caseAccess = false;
			if ("U".equals(corisCaseHistoryDTO.getKaseBO().getCaseSecurity())){
				caseAccess = true;
			} else if ("V".equals(corisCaseHistoryDTO.getKaseBO().getCaseSecurity())){
				caseAccess = true;
			} else if ("S".equals(corisCaseHistoryDTO.getKaseBO().getCaseSecurity())){
				caseAccess = sealedAccess;
			} else if ("X".equals(corisCaseHistoryDTO.getKaseBO().getCaseSecurity())){
				caseAccess = expungedAccess;
			} else if ("O".equals(corisCaseHistoryDTO.getKaseBO().getCaseSecurity())){
				caseAccess = privateProtectedAccess;
			}
			
			if (caseAccess){
				if("IC".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) || "IS".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
					caseAccess = mentalAccess;
				}else if ("AD".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) || "GA".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
					caseAccess = CorisSecurityCommon.grantAccess(logName, corisCaseHistoryDTO.getKaseBO().getCaseSecurity(), corisCaseHistoryDTO.getKaseBO().getLocnCode(), corisCaseHistoryDTO.getKaseBO().getCourtType(), conn);
					if (inquiryUser || !caseAccess){
						caseAccess = false;
					}
				}		
			}
		
			if (caseAccess){
				/////////////////////////////////////////////////////
				// Summary Event Data
				/////////////////////////////////////////////////////
				List<SummaryEventDTO> summaryEventListDTO = CorisCaseHistoryCommon.getSummaryEventData(corisCaseHistoryDTO, logName, locnCode, courtType, conn);
				
				for (SummaryEventDTO summaryEventDTO : summaryEventListDTO ) {
					
					if (!privateProtectedAccess  && ("O".equals(summaryEventDTO.getSecurity()))) {
						summaryEventDTO.setDmDocId(0);
					}
					if (!privateProtectedAccess  && ("V".equals(summaryEventDTO.getSecurity()))) {
						summaryEventDTO.setDmDocId(0);
					}
					if (!sealedAccess && ("S".equals(summaryEventDTO.getSecurity()))) { 
						summaryEventDTO.setDmDocId(0);
					}
					if (!expungedAccess && ("X".equals(summaryEventDTO.getSecurity()))) {
						summaryEventDTO.setDmDocId(0);
					}
					if (!mentalAccess && ("M".equals(summaryEventDTO.getSecurity()))) { 
						summaryEventDTO.setDmDocId(0);
					}

					if ("ORDER".equals(summaryEventDTO.getFuncId()) || "DOCUMENT".equals(summaryEventDTO.getFuncId())){
						if (summaryEventDTO.getDmDocId() > 0){
							List<CaseDocSeqVO> caseDocListSeqBO  = CaseDocSeqUtil.getDocumentSequence(caseNum, courtType+locnCode, courtType, summaryEventDTO.getDmDocId());
							
							if (caseDocListSeqBO.size() > 0){
								summaryEventDTO.setDocSeq(caseDocListSeqBO.get(0).getDocSeq());
							}
							
							caseDocListSeqBO=null;
						}
					}
					
					//////////////////////////////////////////////////////////
					// Inquiry Not allowed to see tracking
					//////////////////////////////////////////////////////////
					if (inquiryUser){
						if ("TRACKING".equals(summaryEventDTO.getFuncId())){
							summaryEventListDTO.get(summaryEventListDTO.indexOf(summaryEventDTO)).setFuncId("");
						}
					}
					//////////////////////////////////////////////////////////
					//cleanup
					//////////////////////////////////////////////////////////
					summaryEventDTO = null;
				}	
				
				String judgeName = CorisCaseHistoryCommon.getJudgeCommName(corisCaseHistoryDTO.getKaseBO().getAssnJudgeId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), null, conn);
				String commName = CorisCaseHistoryCommon.getJudgeCommName(corisCaseHistoryDTO.getKaseBO().getAssnCommId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), null, conn);
				
				request.setAttribute("judgeName", judgeName);
				request.setAttribute("commName", commName);
				request.setAttribute("summaryeventlistDTO", summaryEventListDTO);
				
				/////////////////////////////////////////////////////
				// Personnel Data
				/////////////////////////////////////////////////////
				PersonnelBO personnelBO = new PersonnelBO(courtType)
				.where(PersonnelBO.LOGNAME, logName)
				.where(PersonnelBO.LOCNCODE, locnCode)
				.where(PersonnelBO.COURTTYPE, courtType)
				.setUseConnection(conn)
				.find(PersonnelBO.USERIDSRL);
				
				List<CaseCriticalMessagesDTO> caseCriticalMessagesListDTO = GetCaseCriticalMessagesStoredProcedure.getCaseCriticalMessagesSP(personnelBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getIntCaseNum(), corisCaseHistoryDTO.getKaseBO().getCourtType(), conn);
				caseCriticalMessagesListDTO = CriticalMessageUtil.addCriticalMessageForJailRealeaseAgreements(corisCaseHistoryDTO.getKaseBO(),	caseCriticalMessagesListDTO);  // append critical message for jail release agreements
				caseCriticalMessagesListDTO = CriticalMessageUtil.addCriticalMessageForOffenderDna(corisCaseHistoryDTO.getKaseBO(),	caseCriticalMessagesListDTO);  			   // append critical message for offender dna
				caseCriticalMessagesListDTO = CriticalMessageUtil.addCriticalMessageForArrestWarrants(corisCaseHistoryDTO.getKaseBO(),	caseCriticalMessagesListDTO);  		   // append critical message for arrest warrants
				caseCriticalMessagesListDTO = CriticalMessageUtil.addCriticalMessageForProblemSolving(corisCaseHistoryDTO.getKaseBO(),	caseCriticalMessagesListDTO);  		   // append critical message for problem solving
				request.setAttribute("caseCriticalMessagesListDTO", caseCriticalMessagesListDTO);
				
				List<CaseWarningsDTO> caseWarningsListDTO = GetCaseWarningsStoredProcedure.getCaseWarningsSP(personnelBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getIntCaseNum(), "Y", corisCaseHistoryDTO.getKaseBO().getCourtType(), conn);
				request.setAttribute("caseWarningsListDTO", caseWarningsListDTO);
				
				List<PartyCaseBO> partyCaseListBO = (List<PartyCaseBO>) new PartyCaseBO(courtType)
				.where(PartyCaseBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum())
				.addWhereDescriptors(new WhereCustomDescriptor("(dismissed = 'Y' OR safeguarded = 'Y')"))
				.setUseConnection(conn)
				.search(PartyCaseBO.PARTYCODE, PartyCaseBO.PARTYNUM, PartyCaseBO.DISMISSED, PartyCaseBO.SAFEGUARDED);
				
				List<SafeGuardedDismissedPartyDTO> safeGuardedDismissedPartyListDTO = new ArrayList<SafeGuardedDismissedPartyDTO>();
				
				for (PartyCaseBO partyCaseBO : partyCaseListBO ){
				
					SafeGuardedDismissedPartyDTO safeGuardedDismissedPartyDTO = new SafeGuardedDismissedPartyDTO();
					
					
					PartyBO partyBO = new PartyBO(courtType)
					.where(PartyBO.PARTYNUM, partyCaseBO.getPartyNum())
					.setUseConnection(conn)
					.find();
					
					safeGuardedDismissedPartyDTO.setFirstName(partyBO.getFirstName());
					safeGuardedDismissedPartyDTO.setLastName(partyBO.getLastName());
					safeGuardedDismissedPartyDTO.setPartyCode(partyCaseBO.getPartyCode());
					
					if ("Y".equals(partyCaseBO.getDismissed())){
						safeGuardedDismissedPartyDTO.setType("D");
						
					}
					if ("Y".equals(partyCaseBO.getSafeguarded())){
						safeGuardedDismissedPartyDTO.setType("S");
					}
					
					safeGuardedDismissedPartyListDTO.add(safeGuardedDismissedPartyDTO);
					safeGuardedDismissedPartyDTO = null;
					partyBO = null;
				}
				
				request.setAttribute("safeGuardedDismissedPartyListDTO", safeGuardedDismissedPartyListDTO);

				
				//////////////////////////////////////////////////////////
				//cleanup
				//////////////////////////////////////////////////////////
				summaryEventListDTO = null;
				judgeName = null;
				commName = null;
				caseCriticalMessagesListDTO = null;
				caseWarningsListDTO = null;
				personnelBO = null;
				partyCaseListBO = null;
				safeGuardedDismissedPartyListDTO = null;
			}
			
			if (inquiryUser){
				request.setAttribute("inquiryUser", "Y");
			} else {
				request.setAttribute("inquiryUser", "N");
			}
			
		} catch (Exception e) {
			logger.error("find(HttpServletRequest request, HttpServletResponse response)", e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("conn close find(HttpServletRequest request, HttpServletResponse response)", e);
			}
			conn = null;
		}
	}
}
