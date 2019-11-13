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

import java.util.List;

public class CorisPersonnelStoredProcedureServlet implements BaseConstants {
	
	public static boolean validateUserSP(String logName, String password, String encrypt, String courtType) throws Exception {
		
		boolean isVerified=false;
		
		
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
			"verify_user",
			"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
			new InputParameters().addParameters(
				new TypeString().setValue(logName),
				new TypeString().setValue(password),
				new TypeString().setValue(encrypt)
				
			),
			new OutputContainer().addResultTypes(
				new TypeInteger(),	// status  <- check this column to see if there were results from the stored proc
				new TypeInteger()	// verified
			)
		);
		
		BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure).toString(RUN).executeQuery();
		
		List<OutputContainer> results = storedProcedureFactory.getResults();
		
		if ((Integer) results.get(0).getFields().get(1).getValue() > 0 ){
			isVerified=true;
		}
		
		return isVerified;
		
	}
}
