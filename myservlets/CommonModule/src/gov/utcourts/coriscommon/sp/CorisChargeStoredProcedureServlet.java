package gov.utcourts.coriscommon.sp;

import gov.utcourts.courtscommon.dispatcher.StoredProcedureDispatcher;
import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StoredProcedureDescriptor;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dispatcher.BaseStoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.parameters.InputParameters;
import gov.utcourts.courtscommon.dispatcher.worker.OutputContainer;

import java.sql.Connection;
import java.util.List;

public class CorisChargeStoredProcedureServlet implements BaseConstants {
	
	public static void main(String[] args) {
		
		DatabaseConnection.setUseJdbc();
		
		try {
			
			System.out.println(checkMandatroyAppearanaceSP(0, 5518687,"D", null));
			
		} catch (Exception e) {
			System.out.println("Error - " + e.getLocalizedMessage());
		} finally {
			DatabaseConnection.setUseJndi();
		}
		
	}
	
	public static String checkMandatroyAppearanaceSP(int useridSrl, int intCaseNum, String courtType, Connection conn) throws Exception {
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
			"check_mand_appear",
			"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
			new InputParameters().addParameters(
				new TypeInteger().setValue(useridSrl),
				new TypeInteger().setValue(intCaseNum)
				
			),
			new OutputContainer().addResultTypes(
				new TypeInteger(),		// status  <- check this column to see if there were results from the stored proc
				new TypeString()	// Mandatory Appearance Flag Y/N
			)
		).setUseConnection(conn);
		
		BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure).toString(RUN).executeQuery();
		
		List<OutputContainer> results = storedProcedureFactory.getResults();
		
		return results.get(0).getFields().get(1).getValue().toString().trim();
	}
	public static String checkMandatroyAppearanaceByChargeSP(int useridSrl, int intCaseNum, String locnCode, String courtType, int seq, Connection conn) throws Exception {
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
			"check_mand_appear_by_charge",
			"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
			new InputParameters().addParameters(
				new TypeInteger().setValue(useridSrl),
				new TypeInteger().setValue(intCaseNum),
				new TypeString().setValue(locnCode),
				new TypeString().setValue(courtType),
				new TypeInteger().setValue(seq)
			),
			new OutputContainer().addResultTypes(
				new TypeInteger(),		// status  <- check this column to see if there were results from the stored proc
				new TypeString()	// Mandatory Appearance Flag Y/N
			)
		).setUseConnection(conn);
		
		BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure).toString(RUN).executeQuery();
		
		List<OutputContainer> results = storedProcedureFactory.getResults();
		
		return results.get(0).getFields().get(1).getValue().toString().trim();
	}
}
