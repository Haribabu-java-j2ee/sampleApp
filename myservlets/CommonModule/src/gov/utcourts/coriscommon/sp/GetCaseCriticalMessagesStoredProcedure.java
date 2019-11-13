package gov.utcourts.coriscommon.sp;

import gov.utcourts.courtscommon.dispatcher.StoredProcedureDispatcher;
import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dto.CaseCriticalMessagesDTO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StoredProcedureDescriptor;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dispatcher.BaseStoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.parameters.InputParameters;
import gov.utcourts.courtscommon.dispatcher.worker.OutputContainer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class GetCaseCriticalMessagesStoredProcedure{

    private static final Logger logger = Logger.getLogger(GetCaseCriticalMessagesStoredProcedure.class.getName());
	
	public static void main(String[] args) {
		DatabaseConnection.setUseJdbc();
				
		try {
			
			List<CaseCriticalMessagesDTO> caseCriticalMessagesListDTO = getCaseCriticalMessagesSP(0, 11142773, "D", null);
			
			for (CaseCriticalMessagesDTO caseCriticalMessagesDTO :caseCriticalMessagesListDTO){
				System.out.println(caseCriticalMessagesDTO.getTitle());
			}
			
		} catch (Exception e) {
			System.out.println("Error - " + e.getLocalizedMessage());
		} finally {
		}
		
	}
	
    /////////////////////////////////////////////////////
	// Case Data Initialized
	/////////////////////////////////////////////////////
	public static List<CaseCriticalMessagesDTO> getCaseCriticalMessagesSP(int useridSrl, int intCaseNum, String courtType, Connection conn) throws Exception {
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
			"get_critical_messages",
			"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
			new InputParameters().addParameters(
				new TypeInteger().setValue(useridSrl),
				new TypeInteger().setValue(intCaseNum),
				new TypeInteger().setValue(0),
				new TypeString().setValue("")
				
			),
			new OutputContainer().addResultTypes(
				new TypeInteger(),		// status  <- check this column to see if there were results from the stored proc
				new TypeString()	// Title
			)
		).setUseConnection(conn);
		
		BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure).executeQuery();
		
		List<OutputContainer> resultsList = storedProcedureFactory.getResults();
		
		List<CaseCriticalMessagesDTO> caseCriticalMessagesListDTO = new ArrayList<CaseCriticalMessagesDTO>();
		
		if (resultsList != null){
			for(OutputContainer result :resultsList){
				caseCriticalMessagesListDTO.add(new CaseCriticalMessagesDTO((Integer) result.getFields().get(0).getValue(), (String) result.getFields().get(1).getValue()));
			}
		}	
		
		return caseCriticalMessagesListDTO;
	}
}
