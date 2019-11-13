package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.acctadj.AcctAdjBO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeDouble;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class AcctAdjXO implements BaseConstants {
	
	private static final Logger log = Logger.getLogger(AcctAdjXO.class);
	
	public static int PRINT_SQL = RUN;
	
	public static List<AcctAdjBO> getAcctAdjsByAcctNum(int acctNum, String courtType) throws Exception {
		log.debug("<< Entering  AcctAdjFacade.getAcctAdjsByAcctNum(int acctNum, String courtType, boolean useStatic)  >>");

		try{
			return new AcctAdjBO(courtType).where(AcctAdjBO.ACCTNUM,acctNum).toString(PRINT_SQL).search();
		} catch(Exception e) {
			log.error("Exception in AcctAdjFacade.getAcctAdjsByAcctNum(int acctNum, String courtType, boolean useStatic) ",e);
			throw e;
		} 
	}
	
	public static List<AcctAdjBO> getAcctAdjsSumByAcctNum(int acctNum, String courtType) throws Exception {
		log.debug("<< Entering  AcctAdjFacade.getAcctAdjsSumByAcctNum(int acctNum, String courtType, boolean useStatic)  >>");
		try{
			FieldOperationDescriptor max = new FieldOperationDescriptor(AcctAdjBO.ADJDATETIME, FieldOperationType.MAX, new TypeDate());
			FieldOperationDescriptor sum = new FieldOperationDescriptor(AcctAdjBO.ADJAMT, FieldOperationType.SUM, new TypeInteger());
			return new AcctAdjBO(courtType).setFieldOperations(sum, max)
	    				.where(AcctAdjBO.ACCTNUM,acctNum)
	    				.where(AcctAdjBO.ADJTYPE,"I")
	    				.groupBy(3)
	    				.orderBy(2).toString(PRINT_SQL).search(AcctAdjBO.REASON);
			
		} catch(Exception e) {
			log.error("Exception in AcctAdjFacade.getAcctAdjsSumByAcctNum(int acctNum, String courtType, boolean useStatic) ",e);
			throw e;
		} 
	}
	
	public static List<AcctAdjBO> getAcctAdjsSumInterestByAcctNum(int acctNum, String courtType) throws Exception {
		log.debug("<< Entering  AcctAdjFacade.getAcctAdjsSumInterestByAcctNum(int acctNum, String courtType, boolean useStatic)  >>");
//		List<AcctAdjBO> accountAdjs = new ArrayList<AcctAdjBO>();
		try{
			FieldOperationDescriptor max = new FieldOperationDescriptor(AcctAdjBO.ADJDATETIME, FieldOperationType.MAX, new TypeDate());
			FieldOperationDescriptor sum = new FieldOperationDescriptor(AcctAdjBO.ADJAMT, FieldOperationType.SUM, new TypeInteger());
	    	return new AcctAdjBO(courtType)
	    				.setFieldOperations(sum, max)
	    				.where(AcctAdjBO.ACCTNUM,acctNum)
	    				.where(new FindDescriptor(AcctAdjBO.ADJTYPE).setCustomSearch("IS NULL").setCustomSearch("OR <> 'I'"))
	    				.groupBy(3)
	    				.orderBy(2).toString(PRINT_SQL).search(AcctAdjBO.REASON);
			
		} catch(Exception e) {
			log.error("Exception in AcctAdjFacade.getAcctAdjsSumInterestByAcctNum(int acctNum, String courtType, boolean useStatic) ",e);
			throw e;
		} 
	}
	
	public static double getAcctAdjsSumWaivedAcctNum(int acctNum, String courtType) throws Exception {
		log.debug("<< Entering  AcctAdjFacade.getAcctAdjsSumWaivedByAcctNum(int acctNum, String courtType, boolean useStatic)  >>");
		try{
			FieldOperationDescriptor sum = new FieldOperationDescriptor(AcctAdjBO.ADJAMT, FieldOperationType.SUM, new TypeDouble());
			Double adjSum = (Double)new AcctAdjBO(courtType)
						.setFieldOperations(sum)
						.where(AcctAdjBO.ACCTNUM,acctNum)
						.where(AcctAdjBO.ADJTYPE,"W").toString(PRINT_SQL)
						.setReturnNull(true)
						.find().get(sum);
			if(adjSum != null){
				return adjSum.doubleValue();
			}
			return 0.0;
			
		} catch(Exception e) {
			log.error("Exception in AcctAdjFacade.getAcctAdjsSumWaivedByAcctNum(int acctNum, String courtType, boolean useStatic) ",e);
			throw e;
		} 
	}
	
	/**
	* getAcctAdjsByAcctNumAdjDateTime - finds all account adjustments (AcctAdjBO) associated to the acctNum/courtType passed in.
	* This method also uses the useStatic for connection either through the batch or web.
	* @param Int acctNum
	* @parm AdjDateTime
	* @param String courtType
	* @param Boolean useStatic
	* @return AcctAdjBO - returns a list of all AcctAdjBO table data
	* @throws Exception 
	*/
	public static List<AcctAdjBO> getAcctAdjsByAcctNumAdjDateTime(int acctNum, Date adjDateTime, String courtType) throws Exception {
		log.debug("<< Entering  AcctAdjFacade.getAcctAdjsByAcctNumAdjDateTime(int acctNum, Date adjDateTime, String courtType, boolean useStatic) >>");
		
		try{

			return new AcctAdjBO(courtType)
					.where(AcctAdjBO.ACCTNUM,acctNum)
					.where(AcctAdjBO.ADJDATETIME, adjDateTime)
					.orderBy(AcctAdjBO.ADJDATETIME,  DirectionType.ASC).toString(PRINT_SQL).search();
		} catch(Exception e) {
			log.error("Exception in AcctAdjFacade.getAcctAdjsByAcctNumAdjDateTime(int acctNum, Date adjDateTime, String courtType, boolean useStatic) ",e);
			throw e;
		} 
	}	
	
	/**
	 * Insert an adjustment for an account
	 * 
	 * @param AcctAdjBO = The account distribution to be inserted
	 * @return int
	 * @throws Exception
	 */
	public static void insert(AcctAdjBO acctAdjBO, String courtType) throws Exception {
		try {
			new AcctAdjBO(courtType)
				.setAcctNum(acctAdjBO.getAcctNum())
				.setDistCode(acctAdjBO.getDistCode())
				.setSeq(acctAdjBO.getSeq())
				.setAdjAmt(acctAdjBO.getAdjAmt())
				.setReason(acctAdjBO.getReason())
				.setUseridSrl(acctAdjBO.getUseridSrl())
				.setAdjType(acctAdjBO.getAdjType())
				.setEffectiveDate(acctAdjBO.getEffectiveDate()).toString(PRINT_SQL).insert();
			
		} catch (Exception e) {
			log.error("Exception in AcctAdjDAO.insert(AcctAdjBO acctAdjBO): account number = ".concat(Integer.toString(acctAdjBO.getAcctNum())), e);
			throw e;
		}

	}
	
	/**
	 * Get the next sequence number for an account.
	 * 
	 * @param int accountNumber
	 * @return int
	 * @throws Exception
	 */
	public static int getNextSequenceNumber(Connection connection, int accountNumber, String courtType)
			throws Exception {

		try {
			FieldOperationDescriptor max = new FieldOperationDescriptor(AcctAdjBO.SEQ, FieldOperationType.MAX, new TypeInteger());
			
			return ((Integer) new AcctAdjBO(courtType).setUseConnection(connection)
						.setFieldOperations(max)
						.where(AcctAdjBO.ACCTNUM,accountNumber)
						.toString(PRINT_SQL).find().get(max)).intValue() + 1;

		} catch (Exception e) {
			log.error(
					"Exception in AcctAdjDAO.insert(AcctAdjBO acctAdjBO): account number = "
							.concat(Integer.toString(accountNumber)), e);
			throw e;
		}
	}
	
	public static void main(String[] args){
		DatabaseConnection.setUseJdbc();
		String courtType = "D";
    	try {
//    		getNextSequenceNumber(12345,courtType);
//    		AcctAdjXO.getAcctAdjsByAcctNum(1111, courtType);
//    		AcctAdjXO.getAcctAdjsByAcctNumAdjDateTime(11111, Calendar.getInstance().getTime(), courtType);
//    		AcctAdjXO.getAcctAdjsSumByAcctNum(20022, courtType);
//    		AcctAdjXO.getAcctAdjsSumInterestByAcctNum(10254, courtType);
//    		FieldOperationDescriptor max = new FieldOperationDescriptor(AcctAdjBO.ADJDATETIME, FieldOperationType.MAX, new TypeInteger());
//    		FieldOperationDescriptor sum = new FieldOperationDescriptor(AcctAdjBO.ADJAMT, FieldOperationType.SUM, new TypeInteger());
//			List<AcctAdjBO> accts =  new AcctAdjBO("D")
//						.setFieldOperations(sum, max)
//						.where(AcctAdjBO.ACCTNUM,1111111)
//						.where(AcctAdjBO.ADJTYPE,"I").groupBy(3).orderBy(2).toString(true).search(AcctAdjBO.REASON);
//    		AcctAdjXO.getAcctAdjsSumWaivedAcctNum(227, "D");
//    		AcctAdjXO.getAcctAdjsByAcctNumAdjDateTime(11222, Calendar.getInstance().getTime(), courtType);
//    		int nxsq = AcctAdjXO.getNextSequenceNumber(123456, courtType);
//    		System.out.println("next-seq=" + nxsq);
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
