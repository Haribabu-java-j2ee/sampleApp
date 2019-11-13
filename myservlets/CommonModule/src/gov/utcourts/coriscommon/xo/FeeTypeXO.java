package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.feetype.FeeTypeBO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereCustomDescriptor;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;



public class FeeTypeXO {
	
	public static void main(String args[]) throws Exception {
		DatabaseConnection.setUseJdbc();
		System.out.println(getFeeCodeByDescr("D", "COURT COSTS"));
	}
	
	public static String getFeeCodeByDescr(String courtType, String descr) throws Exception{
		FeeTypeBO vo = new FeeTypeBO(courtType).where(FeeTypeBO.DESCR, descr)
			.addWhereDescriptors(new WhereCustomDescriptor("t1.last_effect_date > today" )).find();
		
		return vo.getFeeCode();
	}
	

}


