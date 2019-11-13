package gov.utcourts.corisweb.util;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.BatchInsertDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.BatchUpdateDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dispatcher.BaseTransactionDispatcher;
import gov.utcourts.courtscommon.dispatcher.TransactionDispatcher;
import gov.utcourts.notifications.util.CorisNotificationEmailCommon;

public class CorisAttorneyUtil {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CorisAttorneyUtil.class.getName());

	public static List<BaseVO> attachAttorney(List<BaseVO> valuesAttach, String courtType, int barNumAttach, String barStateAttach, int intCaseNum, int partyNum, String partyCode, String attyType, Date now, int userid) throws Exception {
		try {
			AttyPartyBO attyPartyAttachBO = new AttyPartyBO(courtType).setBarNum(barNumAttach)
					.setBarState(barStateAttach)
					.setIntCaseNum(intCaseNum)
					.setPartyNum(partyNum)
					.setPartyCode(partyCode)
					.setAttyType(attyType)
					.setCreateDatetime(now)
					.setAttachDatetime(now)
					.setAttachUserid(userid);
			valuesAttach.add(attyPartyAttachBO.getBaseVO());
			attyPartyAttachBO = null;		
		} catch (Exception e) {
			logger.error("CorisAttorneyUtil List<BaseVO> attachAttorney(List<BaseVO> valuesAttach, String courtType, int barNumAttach, String barStateAttach, int intCaseNum, int partyNum, String partyCode, String attyType, Date now, int userid)", e);
		}
		return valuesAttach;
	}
	
	public static List<BaseVO> detachAttorney(List<BaseVO> valuesDetach, String courtType, int userid, int attyPartyId, Date now) throws Exception {
		try {
			AttyPartyBO attyPartyDetachBO = new AttyPartyBO(courtType).setDetachDatetime(now)
					.setDetachUserid(userid)
					.where(AttyPartyBO.ATTYPARTYID, attyPartyId);
			valuesDetach.add(attyPartyDetachBO.getBaseVO());
			attyPartyDetachBO = null;
		} catch (Exception e) {
			logger.error("CorisAttorneyUtil List<BaseVO> detachAttorney(List<BaseVO> valuesDetach, String courtType, int userid, int attyPartyId, Date now)", e);
		}
		return valuesDetach;
	}
	
	public static List<BaseVO> updateDetachProSe(List<BaseVO> valuesDetachProSe, String courtType, int partyNum, String partyCode, int intCaseNum) throws Exception {
		//update the party_case.pro_se column to null, if there are no other attorneys attached to this case/party
		int count = new AttyPartyBO(courtType)
				.where(AttyPartyBO.INTCASENUM, intCaseNum)
				.where(AttyPartyBO.PARTYNUM, partyNum)
				.where(AttyPartyBO.PARTYCODE, partyCode)
				.where(AttyPartyBO.DETACHDATETIME, Exp.IS_NULL)
				.find(AttyPartyBO.INTCASENUM.count())
				.getCount();
		if(count == 0){
			new PartyCaseBO(courtType)
				.setProSe(null)
				.where(PartyCaseBO.INTCASENUM, intCaseNum)
				.where(PartyCaseBO.PARTYCODE, partyCode)
				.where(PartyCaseBO.PARTYNUM, partyNum)
				.update();
		}
		return valuesDetachProSe;
	}
	
	public static List<BaseVO> updateAttachProSe(List<BaseVO> valuesDetachProSe, String courtType, int partyNum, String partyCode, int intCaseNum) throws Exception {
		//update the party_case.pro_se column to "N"
		new PartyCaseBO(courtType)
			.setProSe("N")
			.where(PartyCaseBO.INTCASENUM, intCaseNum)
			.where(PartyCaseBO.PARTYCODE, partyCode)
			.where(PartyCaseBO.PARTYNUM, partyNum)
			.update();	
		return valuesDetachProSe;
	}

	public static void processBatch(List<BaseVO> valuesAttach, List<BaseVO> valuesDetach, List<BaseVO> valuesAttachProSe, List<BaseVO> valuesDetachProSe, String courtType, String emailAddress, int caseCount, int barNumDetach, String barStateDetach, int barNumAttach, String barStateAttach) throws Exception {
		try {
			
			String message = "Status of Attorney Management - Mass Transfer:<br>";
			
			if (valuesAttach.size() > 0) {
				BatchInsertDescriptor batchInsertDescriptor = new BatchInsertDescriptor()
						.setInsertIntoTable(
								new AttyPartyBO(courtType).includeFields(
										AttyPartyBO.BARNUM,
										AttyPartyBO.BARSTATE,
										AttyPartyBO.INTCASENUM,
										AttyPartyBO.PARTYNUM,
										AttyPartyBO.PARTYCODE,
										AttyPartyBO.ATTYTYPE,
										AttyPartyBO.CREATEDATETIME,
										AttyPartyBO.ATTACHDATETIME,
										AttyPartyBO.ATTACHUSERID
								)
						)
						.setValues(valuesAttach);	
				BaseTransactionDispatcher insertDispatcher = new TransactionDispatcher(batchInsertDescriptor)
						.execute(); 

				if (insertDispatcher.successful()) {
					message += "<br>Success: "+caseCount+" cases/parties have been attached to attorney "+barNumAttach+"/"+barStateAttach;
				} else {
					logger.error("transaction failed " +insertDispatcher.getErrorMessage());
					message += "<br>Error: "+caseCount+" cases/parties have not been attached to attorney "+barNumAttach+"/"+barStateAttach;
				}

			}

			if (valuesDetach.size() > 0) {
				
				BatchUpdateDescriptor batchUpdateDescriptor = new BatchUpdateDescriptor()
						.setUpdateTable(
								new AttyPartyBO(courtType)
										.includeFields(
													AttyPartyBO.DETACHDATETIME,
													AttyPartyBO.DETACHUSERID
										)
						)
						.setValues(valuesDetach);	
				BaseTransactionDispatcher updateDispatcher = new TransactionDispatcher(batchUpdateDescriptor)
						.execute();
				
				if (updateDispatcher.successful()) {
					message += "<br>Success: "+caseCount+" cases/parties have been detached from attorney "+barNumDetach+"/"+barStateDetach;
				} else {
					logger.error("transaction failed " +updateDispatcher.getErrorMessage());
					message += "<br>Error: "+caseCount+" cases/parties have not been detached for attorney "+barNumDetach+"/"+barStateDetach;
				}
				
			}

			if (valuesAttachProSe.size() > 0) {
				
				BatchUpdateDescriptor batchUpdateDescriptor = new BatchUpdateDescriptor()
						.setUpdateTable(
								new PartyCaseBO(courtType)
										.includeFields(
													PartyCaseBO.PROSE
										)
						)
						.setValues(valuesAttachProSe);	
				BaseTransactionDispatcher updateDispatcher = new TransactionDispatcher(batchUpdateDescriptor)
						.execute();
				
				if (updateDispatcher.successful()) {
					message += "<br>Success: Party pro-se status has been updated.";
				} else {
					logger.error("transaction failed " +updateDispatcher.getErrorMessage());
					message += "<br>Error: Party pro-se status has not been updated.";
				}
				
			}

			if (valuesDetachProSe.size() > 0) {
				
				BatchUpdateDescriptor batchUpdateDescriptor = new BatchUpdateDescriptor()
						.setUpdateTable(
								new PartyCaseBO(courtType)
										.includeFields(
													PartyCaseBO.PROSE
										)
						)
						.setValues(valuesDetachProSe);	
				BaseTransactionDispatcher updateDispatcher = new TransactionDispatcher(batchUpdateDescriptor)
						.execute();
				
				if (updateDispatcher.successful()) {
					message += "<br>Success: Party pro-se status has been updated.";
				} else {
					logger.error("transaction failed " +updateDispatcher.getErrorMessage());
					message += "<br>Error: Party pro-se status has not been updated.";
				}
				
			}

			CorisNotificationEmailCommon.corisNotificationEmail(
					999999999,
					Constants.SYSTEM_ID,
					null, 
					"Attorney Management - Mass Transfer Status", 
			        message, 
			        emailAddress, 
			        Constants.RETURN_EMAIL,
			        null,
			        null,
			        courtType, 
			        null, 
			        false
			);
			
		} catch (Exception e) {
			logger.error("CorisAttorneyUtil processAttachBatch(List<BaseVO> values, String courtType)", e);
		}
	}
	
	public static List<LocationBO> getLocationList(HttpServletRequest request, String courtType) throws Exception {
		List<LocationBO> locationListBO = new LocationBO(courtType)
				.includeTables(
						new PersonnelBO(courtType)
								.where(PersonnelBO.COURTTYPE, courtType)
								.where(PersonnelBO.LOGNAME, request.getSession().getAttribute(SessionVariables.LOG_NAME))
								.where(PersonnelBO.REMOVEDFLAG, "N")
				)
				.addForeignKey(LocationBO.LOCNCODE, PersonnelBO.LOCNCODE)
				.addForeignKey(LocationBO.COURTTYPE, PersonnelBO.COURTTYPE)
				.orderBy(LocationBO.LOCNDESCR)
				.limit(0)
				.search(LocationBO.LOCNCODE, LocationBO.LOCNDESCR, PersonnelBO.DEFLTLOGIN);
		return locationListBO;
	}

}
