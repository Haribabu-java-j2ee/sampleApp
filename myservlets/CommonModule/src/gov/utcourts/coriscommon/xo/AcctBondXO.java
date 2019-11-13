package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.acctbond.AcctBondBO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import org.apache.log4j.Logger;

public class AcctBondXO implements BaseConstants {
	private static final Logger log = Logger.getLogger(AcctBondXO.class);
	public static int PRINT_SQL = RUN;
	
	public static AcctBondBO getAcctBondByAcctNum(int acctNum, String courtType) throws Exception {
		try{
			return new AcctBondBO(courtType).where(AcctBondBO.ACCTNUM,acctNum).toString(PRINT_SQL).find();
		}catch(Exception e){
			log.error("Exception in AcctBondBO getAcctBondsByIntCaseNum(int acctNum)",e);
			throw e;
		}
	}
	
	public static void main(String[] args){
		try {
			DatabaseConnection.setUseJdbc();
			AcctBondBO acctBond = AcctBondXO.getAcctBondByAcctNum(12455, "D");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
