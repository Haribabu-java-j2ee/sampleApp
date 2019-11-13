package gov.utcourts.coriscommon.sp;

import gov.utcourts.courtscommon.dispatcher.StoredProcedureDispatcher;
import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dto.CaseWarningsDTO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StoredProcedureDescriptor;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dispatcher.BaseStoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.parameters.InputParameters;
import gov.utcourts.courtscommon.dispatcher.worker.OutputContainer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class GetCaseWarningsStoredProcedure{

    private static final Logger logger = Logger.getLogger(GetCaseWarningsStoredProcedure.class.getName());
	
	public static void main(String[] args) {
		DatabaseConnection.setUseJdbc();
				
		try {
			
			List<CaseWarningsDTO> caseWarningsListDTO = getCaseWarningsSP(0, 1993908, "Y", "D", null);
			
			for (CaseWarningsDTO caseWarningsDTO :caseWarningsListDTO){
				System.out.println(caseWarningsDTO.getWarning());
			}
			
		} catch (Exception e) {
			System.out.println("Error - " + e.getLocalizedMessage());
		} finally {
		}
		
	}
	
    /////////////////////////////////////////////////////
	// Case Data Initialized
	/////////////////////////////////////////////////////
	public static List<CaseWarningsDTO> getCaseWarningsSP(int useridSrl, int intCaseNum, String psWarnings, String courtType, Connection conn) throws Exception {
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
			"get_case_warnings",
			"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
			new InputParameters().addParameters(
				new TypeInteger().setValue(useridSrl),
				new TypeInteger().setValue(intCaseNum),
				new TypeString().setValue(psWarnings)
				
			),
			new OutputContainer().addResultTypes(
				new TypeInteger(),		// status  <- check this column to see if there were results from the stored proc
				new TypeDate(),		// DateTime
				new TypeString(),	// Warning
				new TypeString(),	// LogName
				new TypeInteger()		// Rowid
			)
		);
		
		BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure).setUseConnection(conn).executeQuery();
		
		List<OutputContainer> resultsList = storedProcedureFactory.getResults();
		
		List<CaseWarningsDTO> caseWarningsListDTO = new ArrayList<CaseWarningsDTO>();
		
		if (resultsList != null){
			for(OutputContainer result :resultsList){
				
				caseWarningsListDTO.add(new CaseWarningsDTO((Integer) result.getFields().get(0).getValue(), 
							(Date) result.getFields().get(1).getValue(), 
							(String) result.getFields().get(2).getValue(), 
							(String) result.getFields().get(3).getValue(), 
							(Integer) result.getFields().get(4).getValue()));
			}
		}
			
		return caseWarningsListDTO;
	}
}
