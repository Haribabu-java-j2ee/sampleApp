package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
 
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.ConditionalType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.util.List;

import org.apache.log4j.Logger;

 

public class CaseTypeXO implements BaseConstants {
	private static final Logger log = Logger.getLogger(CaseTypeXO.class);
	public static int PRINT_SQL = RUN;
	
	public static void main(String args[]) throws Exception
	{
		DatabaseConnection.setUseJdbc();
		getCaseTypesByCourtType("D");
		
	}
	
	public static List<CaseTypeBO> getCaseTypesByCourtType(String courtType) throws Exception
	{
		

		try{
			return new CaseTypeBO(courtType).
			where(new FindDescriptor("(",CaseTypeBO.VALIDCOURT,"B",null),new FindDescriptor(null,CaseTypeBO.VALIDCOURT,courtType,")")
			.setConditionalType(ConditionalType.OR)).orderBy(CaseTypeBO.DESCR, DirectionType.ASC).toString(PRINT_SQL).search(CaseTypeBO.CASETYPE,CaseTypeBO.DESCR);
			
		} catch(Exception e) {
			log.error("Exception in CaseTypeXO getCaseTypesByCourtType ",e);
			throw e;
		} 
	
	}
}
