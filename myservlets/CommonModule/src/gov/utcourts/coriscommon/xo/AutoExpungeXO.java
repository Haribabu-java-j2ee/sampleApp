package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.autoexpungement.AutoExpungementBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dto.AutoExpungementResponseDTO;
import gov.utcourts.coriscommon.dto.ExpungementResponseDTO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dispatcher.StoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.worker.OutputContainer;
import gov.utcourts.personcentral.dataaccess.sidpartycasexref.SidPartyCaseXrefBO;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

/**
 * @author subba.kondabala
 *
 */ 
public class AutoExpungeXO implements BaseConstants { 

	private static Logger logger = Logger.getLogger(AutoExpungeXO.class);	 
	private static final String HEADER="Case Number,Court Location,ORI,First Name,Last Name,Prosecuting Case Number";
	
	public static void main(String args[]) throws Exception {

		DatabaseConnection.setUseJdbc();
	 
		
		//getPartyNum(123456, "DEF", getCorisDatabaseConnection("D"));	
		
		//executeExpungeCase(12345678, 12345678, "D", getCorisDatabaseConnection("D"), getPartyNum(123456, "DEF", getCorisDatabaseConnection("D")));
		
		//updateKase(12345678, "XA", "X",  getCorisDatabaseConnection("D"));
		
		//updateAutoExpungementStatus(12345678, "CE", getCorisDatabaseConnection("D"));
		
		//deleteFromSidPartyCaseXref(12345678, getPersonCentralDatabaseConnection());
		
		/*
		   //This block of used for testing  sendCsvToDps
		    List<ExpungementResponseDTO> expungementResponseDTOList =new ArrayList<ExpungementResponseDTO>();
		
		    ExpungementResponseDTO expungementResponse1 = new ExpungementResponseDTO();
			expungementResponse1.setCaseNumber("1");
			expungementResponse1.setCourtLocation("10");
			expungementResponse1.setFirstName("f1");
			expungementResponse1.setLastName("l1");
			expungementResponse1.setOri("o1");
			expungementResponse1.setProsecutingCaseNumber("p1");
			
			
			ExpungementResponseDTO expungementResponse2 = new ExpungementResponseDTO();
			expungementResponse2.setCaseNumber("1");
			expungementResponse2.setCourtLocation("10");
			expungementResponse2.setFirstName("f1");
			expungementResponse2.setLastName("l1");
			expungementResponse2.setOri("o1");
			expungementResponse2.setProsecutingCaseNumber("p1");
			
		
			
			expungementResponseDTOList.add(expungementResponse1);
			expungementResponseDTOList.add(expungementResponse2);
			
			
			sendCsvToDps(expungementResponseDTOList);
			
			//End of sendCsvToDps
			*/
			
			
		
		
	/*	//This block of code is used to test sendXmlToDps method
		AutoExpungementResponseDTO	autoExpungementResponse = new AutoExpungementResponseDTO();
		 
		List<ExpungementResponseDTO> expungementResponseList = new java.util.ArrayList<ExpungementResponseDTO>();
		
		ExpungementResponseDTO expungementResponse1 = new ExpungementResponseDTO();
		expungementResponse1.setCaseNumber("1");
		expungementResponse1.setCourtLocation("10");
		expungementResponse1.setFirstName("f1");
		expungementResponse1.setLastName("l1");
		expungementResponse1.setOri("o1");
		expungementResponse1.setProsecutingCaseNumber("p1");
		
		ExpungementResponseDTO expungementResponse2 = new ExpungementResponseDTO();
		expungementResponse2.setCaseNumber("1");
		expungementResponse2.setCourtLocation("10");
		expungementResponse2.setFirstName("f1");
		expungementResponse2.setLastName("l1");
		expungementResponse2.setOri("o1");
		expungementResponse2.setProsecutingCaseNumber("p1");
		
		
		expungementResponseList.add(expungementResponse1);
		expungementResponseList.add(expungementResponse2);
		autoExpungementResponse.setExpungementResponseList(expungementResponseList);
			
		sendXmlToDps(autoExpungementResponse);
		
		//End of sendXmlToDps
		*/
		
		
		
		
		
	 
	}
	 
	
	/**
	 * @param expungementResponseDTOList
	 * @throws Exception
	 */
	public static void sendCsvToDps(List<ExpungementResponseDTO> expungementResponseDTOList) throws Exception
	{
		logger.debug("Start of AutoExpungeXO sendCsvToDps(List<ExpungementResponseDTO> expungementResponseDTOList)");
		  try
		  {
			  if(expungementResponseDTOList != null && expungementResponseDTOList.size() > 0)
				 {   
					  new File(System.getProperty("user.dir")+"/tmp").mkdir();
					  File file = new File(System.getProperty("user.dir")+"/tmp/AutoExpungementResponse.csv");			 
					  StringBuilder fileContent=new StringBuilder();
					  fileContent.append(HEADER);
					  fileContent.append(System.getProperty("line.separator"));
					  
					  for(ExpungementResponseDTO expungementResponseDTO:expungementResponseDTOList)
					   {				  
						  fileContent.append(expungementResponseDTO.toString());
						  fileContent.append(System.getProperty("line.separator"));					 
					   }
					  
					  org.apache.commons.io.FileUtils.write(file, fileContent, Charset.forName("UTF-8"), false);
					  logger.debug("end of file creation");
					 
				 }
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  logger.error("Exception in AutoExpungeXO sendCsvToDps(List<ExpungementResponseDTO> expungementResponseDTOList)",e);
			  throw e;
		  }
		
		  logger.debug("End of AutoExpungeXO sendCsvToDps(List<ExpungementResponseDTO> expungementResponseDTOList)");	
		
		
	}
	
	
	/**
	 * @param autoExpungementResponse
	 * @throws Exception
	 */
	public static void sendXmlToDps(AutoExpungementResponseDTO autoExpungementResponse) throws Exception 
	{
		logger.debug("Start of AutoExpungeXO sendXmlToDps(AutoExpungementResponseDTO autoExpungementResponse)");
		
		try
		{
			if(autoExpungementResponse != null && autoExpungementResponse.getExpungementResponseList() != null && autoExpungementResponse.getExpungementResponseList().size() > 0)
			{
				Writer writer =new StringWriter();
				JAXBContext jaxbContent =  JAXBContext.newInstance(AutoExpungementResponseDTO.class);
				Marshaller jaxbMarshaller=jaxbContent.createMarshaller();
		 
				jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);//To remove default xmlheaders
				writer.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n", 0,  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".length());	 
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(autoExpungementResponse, writer);
				
				String outPutXml = new StringBuilder(writer.toString()
						           .replaceFirst("<AutoExpungementResponseFile", 
								                 "<AutoExpungementResponseFile xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.example.org\""))
				                   .toString();
				System.out.println(outPutXml);
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Exception in AutoExpungeXO sendXmlToDps(AutoExpungementResponseDTO autoExpungementResponse)",e);
			throw e;
		}
		
		
		logger.debug("End of AutoExpungeXO sendXmlToDps(AutoExpungementResponseDTO autoExpungementResponse)");	
		
	}
	 
	/**
	 * AutoExpunge case
	 * @param useridSrl
	 * @param intCaseNum
	 * @param courtType
	 * @throws Exception
	 */
	public static void autoExpungeCase(int useridSrl, int intCaseNum, String courtType) throws Exception {
		logger.debug("Start of AutoExpungeXO autoExpungeCase(int useridSrl, int intCaseNum, String courtType)");
		
		Connection corisConn = getCorisDatabaseConnection(courtType);
		Connection personCentralConn = getPersonCentralDatabaseConnection();
		try{
			corisConn.setAutoCommit(false);
			personCentralConn.setAutoCommit(false);
			
			
			//get party num from party_case table
			int partyNum = getPartyNum(intCaseNum, "DEF", corisConn);			
			
			//Calling expunge_case stored procedure
			executeExpungeCase(useridSrl, intCaseNum, courtType, corisConn, partyNum);
				
		    //Update kase table 	 
			updateKase(intCaseNum, "XA", "X", corisConn);
			
			//update the status flag in auto_expungement table
			updateAutoExpungementStatus(intCaseNum, "CE", corisConn);
			
			//Delete intCaseNum from table SID_party_case_xref			
		    deleteFromSidPartyCaseXref(intCaseNum, personCentralConn);
			
	 
		} catch(Exception e) {
			try{
				e.printStackTrace();	
				corisConn.rollback();
				personCentralConn.rollback();
				logger.error("Exception in AutoExpungeXO expungeCase(int useridSrl, int intCaseNum, String courtType) for intcasenum "+intCaseNum,e);
				throw e;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();	
				logger.error("Exception in AutoExpungeXO expungeCase(int useridSrl, int intCaseNum, String courtType) while rollback intcasenum "+intCaseNum,ex);
				throw e;
			}
		} 
		 finally {
			 try{
				 corisConn.setAutoCommit(true);
				 corisConn.close();
				 corisConn = null;	
				 
				 personCentralConn.setAutoCommit(true);
				 personCentralConn.close();
				 personCentralConn = null;
			 }
			 
			 catch(Exception e)
			 {
				    e.printStackTrace();	
					logger.error("Exception in AutoExpungeXO expungeCase(int useridSrl, int intCaseNum, String courtType) in finally block intcasenum "+intCaseNum,e);
					throw e;
			 }
			}
		 logger.debug("End of AutoExpungeXO autoExpungeCase(int useridSrl, int intCaseNum, String courtType)");
	}


	 

	/**
	 * update the status flag in auto_expungement table
	 * @param intCaseNum
	 * @param statusId
	 * @param corisConn
	 * @throws Exception
	 */
	private static void updateAutoExpungementStatus(int intCaseNum, String statusId, Connection corisConn) throws Exception {
		new AutoExpungementBO()
		.setUseConnection(corisConn)
		.setAexpStatusId(statusId)
		.where(AutoExpungementBO.INTCASENUM, intCaseNum)
		.toString(RUN)
		.update();
	}


	/**
	 * Delete intCaseNum from table SID_party_case_xref
	 * @param intCaseNum
	 * @param personCentralConn
	 * @throws Exception
	 */
	private static void deleteFromSidPartyCaseXref(int intCaseNum, Connection personCentralConn) throws Exception {
		new SidPartyCaseXrefBO()
		.setUseConnection(personCentralConn)
		.where(SidPartyCaseXrefBO.CASEID, intCaseNum)
		.toString(RUN)
		.delete();
	}


	/**
	 * Update kase table
	 * @param intCaseNum
	 * @param dispCode
	 * @param security
	 * @param corisConn
	 * @throws Exception
	 */
	private static void updateKase(int intCaseNum, String dispCode, String security, Connection corisConn) throws Exception {
		new KaseBO()
		.setUseConnection(corisConn)
		.setDispCode(dispCode)
		.setCaseSecurity(security)
		.where(KaseBO.INTCASENUM, intCaseNum)
		.toString(RUN)
		.update();
	}


	/**
	 * Calling expunge_case stored procedure
	 * @param useridSrl
	 * @param intCaseNum
	 * @param courtType
	 * @param corisConn
	 * @param partyNum
	 * @throws Exception
	 */
	private static void executeExpungeCase(int useridSrl, int intCaseNum, String courtType, Connection corisConn, int partyNum) throws Exception {
		@SuppressWarnings("unchecked")
		List<OutputContainer> outputContainerResults =	new StoredProcedureDispatcher("D".equalsIgnoreCase(courtType)?KaseBO.CORIS_DISTRICT_DB : KaseBO.CORIS_DISTRICT_DB, 
				                                                                      "expunge_case", useridSrl, intCaseNum, partyNum, "N")
		                                                .setUseConnection(corisConn)
			                                            .toString(RUN)
			                                            .executeQuery()
			                                            .getResults();
		
		if(outputContainerResults != null && outputContainerResults.size() == 1 && (((Integer)outputContainerResults.get(0).getColumn(0)) == 0))
		{
			logger.debug("Auto expunge expunge_case stored procedure successful");
		}
		else
		{
			logger.error("Auto expunge case failed, reason is expunge_case stored procedure unsuccessful for intcasenum "+intCaseNum);
			throw new Exception("Auto expunge case failed, reason is expunge_case stored procedure unsuccessful for intcasenum "+intCaseNum);				
			
		}
	}


	/**
	 * get party num from party_case table
	 * @param intCaseNum
	 * @param partyCode
	 * @param corisConn
	 * @return int
	 * @throws Exception
	 */
	private static int getPartyNum(int intCaseNum, String partyCode, Connection corisConn) throws Exception {
		int partyNum = 0;
		List<PartyCaseBO> partyCaseBOList=new PartyCaseBO()
		.setUseConnection(corisConn)
		.includeFields(PartyCaseBO.PARTYNUM)
		.where(PartyCaseBO.INTCASENUM, intCaseNum)
		.where(PartyCaseBO.PARTYCODE, partyCode)
		.toString(RUN)
		.search();
		
		if(partyCaseBOList != null && partyCaseBOList.size() > 0)
		{
			if(partyCaseBOList.size() == 1)
			{
				partyNum = partyCaseBOList.get(0).getPartyNum();
			}
			
			else
			{
				logger.error("Auto expunge case failed, reason is more than one defandant exist for intcasenum "+intCaseNum);
				throw new Exception("Auto expunge case failed, reason is more than one defandant exist for intcasenum "+intCaseNum);
			}
		}
		else
		{
			logger.error("Auto expunge case failed, reason is no defandant party num exist for intcasenum "+intCaseNum);
			throw new Exception("Auto expunge case failed, reason is no defandant party num exist for intcasenum "+intCaseNum);
		}
		return partyNum;
	}


	private static Connection getPersonCentralDatabaseConnection() throws Exception {
		Connection personCentralConn = DatabaseConnection.getConnection(SidPartyCaseXrefBO.PERSON_CENTRAL_DB);
		return personCentralConn;
	}


	private static Connection getCorisDatabaseConnection(String courtType) throws Exception {
		Connection corisConn = "D".equalsIgnoreCase(courtType) ? DatabaseConnection.getConnection(KaseBO.CORIS_DISTRICT_DB)
				  : DatabaseConnection.getConnection(KaseBO.CORIS_JUSTICE_DB);
		return corisConn;
	}
	 
}
