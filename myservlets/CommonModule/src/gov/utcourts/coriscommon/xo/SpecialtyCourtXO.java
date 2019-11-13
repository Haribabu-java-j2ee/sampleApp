package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.caseme.CaseMeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.specialtycourt.SpecialtyCourtBO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;

import java.sql.Connection;
import java.util.List;

public class SpecialtyCourtXO {
	
	/**
	 * @param courtType
	 * @param conn 
	 * @return
	 * @throws Exception
	 */
	public static List<SpecialtyCourtBO> getSpecialtyCourtDataForPSConversion(String courtType, Connection conn) throws Exception{
		
//		SELECT spc.*, pc.party_num from specialty_court spc, party_case pc where create_datetime >= to_date('01/01/2016 00:00:00','%d/%m/%Y %H:%M:%S') 
//		and pc.int_case_num = spc.int_case_num and pc.party_code = 'DEF';
		return new SpecialtyCourtBO(courtType).setUseConnection(conn)
			.where(new FindDescriptor(SpecialtyCourtBO.CREATEDATETIME)
			.setCustomSearch(">= to_date('01/01/2016 00:00:00','%d/%m/%Y %H:%M:%S')"))
			.includeFields(SpecialtyCourtBO.ALL_FIELDS)
			.includeTables(new PartyCaseBO(courtType)
					.where(PartyCaseBO.PARTYCODE,"DEF")
					.includeFields(PartyCaseBO.PARTYNUM))
			.addForeignKey(SpecialtyCourtBO.INTCASENUM, PartyCaseBO.INTCASENUM)
//			.toString(BaseConstants.PRINT + BaseConstants.RUN)
			.search();
	}
	
	/**
	 * @param intCaseNum
	 * @param court
	 * @param conn 
	 * @return
	 * @throws Exception
	 */
	public static KaseBO getSpecialtyCourtCaseInfo(int intCaseNum, String court, Connection conn) throws Exception{
		
		return new KaseBO(court).setUseConnection(conn)
								.where(KaseBO.INTCASENUM, intCaseNum)
								.includeFields(KaseBO.INTCASENUM,KaseBO.USERIDSRL,KaseBO.LOCNCODE, KaseBO.COURTTYPE, KaseBO.ASSNJUDGEID)
//								.toString(BaseConstants.PRINT + BaseConstants.RUN)
								.find();
		
//		List<KaseBO> list = new KaseBO(court)
//						.setUseConnection(conn)
//						.where(KaseBO.INTCASENUM, intCaseNum)
//						.includeFields(KaseBO.INTCASENUM,KaseBO.USERIDSRL,KaseBO.LOCNCODE, KaseBO.COURTTYPE, KaseBO.ASSNJUDGEID)
//						.includeTables(new CaseMeBO(court).where(CaseMeBO.INTCASENUM, intCaseNum)
//								.includeFields(CaseMeBO.MEID, CaseMeBO.MEDATETIME,CaseMeBO.CLERKID)
//								.orderBy(CaseMeBO.MEDATETIME,DirectionType.DESC))
//						.addForeignKey(KaseBO.INTCASENUM, CaseMeBO.INTCASENUM)
//						.toString(BaseConstants.PRINT + BaseConstants.RUN)
//						.search();
//		if(list != null && list.size() > 0){
//			return list.get(0);
//		}
//		
//		return null;
	}
}
