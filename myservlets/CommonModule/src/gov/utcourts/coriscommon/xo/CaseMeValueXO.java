package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.caseme.CaseMeBO;
import gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO;
import gov.utcourts.courtscommon.dispatcher.SearchDispatcher;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SearchDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dispatcher.BaseSearchDispatcher;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

public class CaseMeValueXO {
	
	private static final Logger log = Logger.getLogger(AccountXO.class);
	
	
	public static int getCaseMeValueCount(int meId, String meScreenId, String meFieldId, String courtType, Connection conn) throws Exception {
		int maxNumber = 0;
	
		//////////////////////////////////////////////////////////////////////////////
		// Get Case Me Value Sequence Information 
		//////////////////////////////////////////////////////////////////////////////
		FieldOperationDescriptor max = new FieldOperationDescriptor(CaseMeValueBO.MEFIELDSEQ, FieldOperationType.MAX, new TypeInteger());
		CaseMeValueBO caseMeValueCountBO = new CaseMeValueBO(courtType)
		.setFieldOperations(max)
		.where(CaseMeValueBO.MEID, meId)
		.where(CaseMeValueBO.MESCREENID, meScreenId)
		.where(CaseMeValueBO.MEFIELDID, meFieldId)
		.setUseConnection(conn)
		.find();
		
		maxNumber = (Integer) caseMeValueCountBO.get(max);
		
		return maxNumber;
	}
	
	public static List<CaseMeValueBO> getCaseMeValue(int meId, int meTypeSeq, String meScreenId, String meFieldId, int meFieldSeq, String courtType, Connection conn) throws Exception {
		//////////////////////////////////////////////////////////////////////////////
		// Get Case Me Value Information
		//////////////////////////////////////////////////////////////////////////////
		List<CaseMeValueBO> caseMeValueListBO = new CaseMeValueBO(courtType).
		where(CaseMeValueBO.MEID, meId).
		where(CaseMeValueBO.METYPESEQ, meTypeSeq).
		where(CaseMeValueBO.MESCREENID, meScreenId).
		where(CaseMeValueBO.MEFIELDID, meFieldId).
		where(CaseMeValueBO.MEFIELDSEQ, meFieldSeq).
		setUseConnection(conn).
		search(); 
		return caseMeValueListBO;
	}
	
	@SuppressWarnings("unchecked")
	public static List<CaseMeValueBO> getCaseMeValuesList(int intCaseNum, int meId, String courtType) throws Exception{
		
		SearchDescriptor cm = new SearchDescriptor(new CaseMeBO(courtType)
			.where(CaseMeBO.INTCASENUM,intCaseNum)
			.where(CaseMeBO.MEID,meId));
		SearchDescriptor cmv = new SearchDescriptor(new CaseMeValueBO(courtType));
		
		BaseSearchDispatcher search = new SearchDispatcher(cm,cmv)
			.addForeignKey(CaseMeBO.MEID, CaseMeValueBO.MEID)
			.search();
		
		return cmv.getResults();
	}
	
}
