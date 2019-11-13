package gov.utcourts.coriscommon.sp;

import gov.utcourts.courtscommon.dispatcher.StoredProcedureDispatcher;
import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dto.TransDetlSPDTO;
import gov.utcourts.coriscommon.dto.TransRevnSPDTO;
import gov.utcourts.coriscommon.dto.TransTrustSPDTO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StoredProcedureDescriptor;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dispatcher.BaseStoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.parameters.InputParameters;
import gov.utcourts.courtscommon.dispatcher.worker.OutputContainer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CorisAccountStoredProcedureServlet implements BaseConstants {
	
	public static void main(String[] args) {
		
		DatabaseConnection.setUseJdbc();
				
		try {
			
			//System.out.println(calculateBailForfitureByChargeSP(0, 12694932, 1, "J", null));
			//calculateEnhanceAmtSP(0, 12694932, "J", null);
			calculateLastPaymentDateSP(0, 13296898, "J", null);
			
		} catch (Exception e) {
			System.out.println("Error - " + e.getLocalizedMessage());
		} finally {
		}
		
	}
	
	public static BigDecimal calculateBailForfitureByChargeSP(int useridSrl, int intCaseNum, int chargeSeq, String courtType, Connection conn) throws Exception {
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
			"calc_bf_by_charge",
			"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
			new InputParameters().addParameters(
				new TypeInteger().setValue(useridSrl),
				new TypeInteger().setValue(intCaseNum),
				new TypeInteger().setValue(chargeSeq)
				
			),
			new OutputContainer().addResultTypes(
				new TypeInteger(),			// status  <- check this column to see if there were results from the stored proc
				new TypeBigDecimal()	// Bail Amount
			)
		).setUseConnection(conn);
		
		BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure).toString(RUN).executeQuery();
		
		List<OutputContainer> results = storedProcedureFactory.getResults();
		
		return (BigDecimal) results.get(0).getFields().get(1).getValue();
	}
	
	public static BigDecimal[] calculateEnhanceAmtSP(int useridSrl, int intCaseNum, String courtType, Connection conn) throws Exception {
		BigDecimal[] enhanceAmt = {BigDecimal.ZERO, BigDecimal.ZERO};
		
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
			"get_enhance_amt",
			"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
			new InputParameters().addParameters(
				new TypeInteger().setValue(useridSrl),
				new TypeInteger().setValue(intCaseNum)
				
			),
			new OutputContainer().addResultTypes(
				new TypeInteger(),			// status  <- check this column to see if there were results from the stored proc
				new TypeBigDecimal(),	// Delinquent Enhancement
				new TypeBigDecimal()	// Warrant Enhancement
			)
		).setUseConnection(conn);
		
		BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure).toString(RUN).executeQuery();
		
		List<OutputContainer> results = storedProcedureFactory.getResults();
		enhanceAmt[0] = (BigDecimal) results.get(0).getFields().get(1).getValue();
		enhanceAmt[1] = (BigDecimal) results.get(0).getFields().get(2).getValue();
		return (BigDecimal[]) enhanceAmt;
	}
	
	public static Date calculateLastPaymentDateSP(int useridSrl, int acctNum, String courtType, Connection conn) throws Exception {
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
			"get_last_pymt_date",
			"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
			new InputParameters().addParameters(
				new TypeInteger().setValue(useridSrl),
				new TypeInteger().setValue(acctNum)
				
			),
			new OutputContainer().addResultTypes(
				new TypeInteger(),		// status  <- check this column to see if there were results from the stored proc
				new TypeDate(),		// Last Payment Date
				new TypeDate()		// Create Date
			)
		).setUseConnection(conn);
		
		BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure).toString(RUN).executeQuery();
		
		List<OutputContainer> results = storedProcedureFactory.getResults();
		
		return (Date) results.get(0).getFields().get(1).getValue();
	}
	
	
	public static String getTransDescriptionSP(int useridSrl, int intJournalNum, int transNum, String courtType, Connection conn) throws Exception {
		// ///////////////////////////////////////////////////////////////
		// Stored Procedure Trans Description Type
		// ///////////////////////////////////////////////////////////////
		StoredProcedureDescriptor storedProcedureTransDescription = new StoredProcedureDescriptor (
				"get_trans_descrip",
				"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
				new InputParameters().addParameters(
					new TypeInteger().setValue(useridSrl),
					new TypeInteger().setValue(intJournalNum),
					new TypeInteger().setValue(transNum)
					
				),
				new OutputContainer().addResultTypes(
					new TypeInteger(),					
					new TypeString()
	
				)
			).setUseConnection(conn);
			
		BaseStoredProcedureDispatcher storedProcedureTransDescriptionFactory = new StoredProcedureDispatcher(storedProcedureTransDescription).executeQuery();
			
		List<OutputContainer> transDescriptionResults = storedProcedureTransDescriptionFactory.getResults();
		
		storedProcedureTransDescription = null;
		storedProcedureTransDescriptionFactory = null;
		
		return transDescriptionResults.get(0).getFields().get(1).getValue().toString();

	}	
	
	
	public static String getTransAccountTypeSP(int useridSrl, int journalNum, int transNum, String courtType, Connection conn) throws Exception {
		// ///////////////////////////////////////////////////////////////
		// Stored Procedure Trans Account Type
		// ///////////////////////////////////////////////////////////////
		StoredProcedureDescriptor storedProcedureTransAcctType = new StoredProcedureDescriptor (
				"get_trans_acct_typ",
				"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
				new InputParameters().addParameters(
					new TypeInteger().setValue(useridSrl),
					new TypeInteger().setValue(journalNum),
					new TypeInteger().setValue(transNum)
					
				),
				new OutputContainer().addResultTypes(
					new TypeInteger(),					
					new TypeString()
	
				)
			).setUseConnection(conn);
			
		BaseStoredProcedureDispatcher storedProcedureTransAcctTypeFactory = new StoredProcedureDispatcher(storedProcedureTransAcctType).executeQuery();
			
		List<OutputContainer> transAcctTypeResults = storedProcedureTransAcctTypeFactory.getResults();
		
		return transAcctTypeResults.get(0).getFields().get(1).getValue().toString();
	}

	// ///////////////////////////////////////////////////////////////
	// Stored Procedure Trans Detail
	// ///////////////////////////////////////////////////////////////
	public static TransDetlSPDTO getTransDetlSP(int useridSrl, int journalNum, int transNum, String courtType, Connection conn) throws Exception {
		TransDetlSPDTO transDetlSPDTO = new TransDetlSPDTO();
		
		StoredProcedureDescriptor storedProcedureTransDetl = new StoredProcedureDescriptor (
			"get_trans_detl",
			"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
			new InputParameters().addParameters(
				new TypeInteger().setValue(useridSrl),
				new TypeInteger().setValue(journalNum),
				new TypeInteger().setValue(transNum)
				
			),
			new OutputContainer().addResultTypes(
				new TypeInteger(),					
				new TypeBigDecimal(),		
				new TypeBigDecimal(),		
				new TypeBigDecimal(),		
				new TypeBigDecimal(),		
				new TypeString(),			
				new TypeString(),			
				new TypeString(),			
				new TypeString(),			
				new TypeString(),			
				new TypeDate(),				
				new TypeString(),			
				new TypeBigDecimal(),		
				new TypeBigDecimal(),		
				new TypeBigDecimal(),		
				new TypeString(),			
				new TypeString(),			
				new TypeString(),			
				new TypeBigDecimal()		
			)
		).setUseConnection(conn);
		
		BaseStoredProcedureDispatcher storedProcedureTransDetlFactory = new StoredProcedureDispatcher(storedProcedureTransDetl).executeQuery();
		
		List<OutputContainer> transDetlResults = storedProcedureTransDetlFactory.getResults();
		transDetlSPDTO.setStatus((Integer) transDetlResults.get(0).getFields().get(0).getValue());
		transDetlSPDTO.setCreditAmt((BigDecimal) transDetlResults.get(0).getFields().get(1).getValue());
		transDetlSPDTO.setCardAmt((BigDecimal) transDetlResults.get(0).getFields().get(2).getValue());
		transDetlSPDTO.setCashAmt((BigDecimal) transDetlResults.get(0).getFields().get(3).getValue());
		transDetlSPDTO.setCheckAmt((BigDecimal) transDetlResults.get(0).getFields().get(4).getValue());
		transDetlSPDTO.setCheckNum((String) transDetlResults.get(0).getFields().get(5).getValue());
		transDetlSPDTO.setCheckType((String) transDetlResults.get(0).getFields().get(6).getValue());
		transDetlSPDTO.setCardType((String) transDetlResults.get(0).getFields().get(7).getValue());
		transDetlSPDTO.setLastName((String) transDetlResults.get(0).getFields().get(8).getValue());
		transDetlSPDTO.setFirstName((String)transDetlResults.get(0).getFields().get(9).getValue());
		transDetlSPDTO.setTransDate((Date)transDetlResults.get(0).getFields().get(10).getValue());
		transDetlSPDTO.setCashier((String)transDetlResults.get(0).getFields().get(11).getValue());
		transDetlSPDTO.setTransAmt((BigDecimal) transDetlResults.get(0).getFields().get(12).getValue());
		transDetlSPDTO.setNonCashAmt((BigDecimal) transDetlResults.get(0).getFields().get(13).getValue());
		transDetlSPDTO.setTransferAmt((BigDecimal) transDetlResults.get(0).getFields().get(14).getValue());
		transDetlSPDTO.setTransNote((String)transDetlResults.get(0).getFields().get(15).getValue());
		transDetlSPDTO.setSupervisor((String)transDetlResults.get(0).getFields().get(16).getValue());
		transDetlSPDTO.setMailLogFlag((String)transDetlResults.get(0).getFields().get(17).getValue());
		transDetlSPDTO.setAchAmt((BigDecimal) transDetlResults.get(0).getFields().get(18).getValue());
		
		storedProcedureTransDetl = null; 
		storedProcedureTransDetlFactory = null;
		transDetlResults = null;
		
		return transDetlSPDTO;
		
	}


	// ///////////////////////////////////////////////////////////////
	// Stored Procedure Trans Revenue
	// ///////////////////////////////////////////////////////////////
	public static List<TransRevnSPDTO> getTransRevnSP(int useridSrl, int journalNum, int transNum, String courtType, Connection conn) throws Exception {
		List<TransRevnSPDTO> transRevnListSPDTO = new ArrayList<TransRevnSPDTO>();

		StoredProcedureDescriptor storedProcedureTransRevn = new StoredProcedureDescriptor (
				"get_trans_revn",
				"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
				new InputParameters().addParameters(
					new TypeInteger().setValue(useridSrl),
					new TypeInteger().setValue(journalNum),
					new TypeInteger().setValue(transNum)
					
				),
				new OutputContainer().addResultTypes(
					new TypeInteger(),					
					new TypeBigDecimal(),					
					new TypeString(),
					new TypeBigDecimal(),					
					new TypeString(),
					new TypeString()
				)
			).setUseConnection(conn);
			
		BaseStoredProcedureDispatcher storedProcedureTransRevnFactory = new StoredProcedureDispatcher(storedProcedureTransRevn).executeQuery();
			
		List<OutputContainer> transRevnResultsList = storedProcedureTransRevnFactory.getResults();
		
		if (transRevnResultsList != null){
			for (OutputContainer transRevnResults :transRevnResultsList){
				TransRevnSPDTO transRevnSPDTO = new TransRevnSPDTO();
				transRevnSPDTO.setStatus((Integer) transRevnResultsList.get(transRevnResultsList.indexOf(transRevnResults)).getFields().get(0).getValue());
				transRevnSPDTO.setAmtPaid((BigDecimal) transRevnResultsList.get(transRevnResultsList.indexOf(transRevnResults)).getFields().get(1).getValue());
				transRevnSPDTO.setDistCode((String) transRevnResultsList.get(transRevnResultsList.indexOf(transRevnResults)).getFields().get(2).getValue());
				transRevnSPDTO.setAmtCredit((BigDecimal) transRevnResultsList.get(transRevnResultsList.indexOf(transRevnResults)).getFields().get(3).getValue());
				transRevnSPDTO.setCaseNum((String) transRevnResultsList.get(transRevnResultsList.indexOf(transRevnResults)).getFields().get(4).getValue());
				transRevnSPDTO.setDescr((String) transRevnResultsList.get(transRevnResultsList.indexOf(transRevnResults)).getFields().get(5).getValue());
				transRevnListSPDTO.add(transRevnSPDTO); 
				transRevnSPDTO = null;
			}
		}	
		storedProcedureTransRevn = null; 
		storedProcedureTransRevnFactory = null;
		transRevnResultsList = null;
		
		return transRevnListSPDTO;
		
	}	
	// ///////////////////////////////////////////////////////////////
	// Stored Procedure Trans Trust
	// ///////////////////////////////////////////////////////////////
	public static List<TransTrustSPDTO> getTransTrustSP(int useridSrl, int journalNum, int transNum, String courtType, Connection conn) throws Exception {
		List<TransTrustSPDTO> transTrustListSPDTO = new ArrayList<TransTrustSPDTO>();
		
		StoredProcedureDescriptor storedProcedureTransTrust = new StoredProcedureDescriptor (
				" get_trans_trust",
				"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
				new InputParameters().addParameters(
					new TypeInteger().setValue(useridSrl),
					new TypeInteger().setValue(journalNum),
					new TypeInteger().setValue(transNum)
				),
				new OutputContainer().addResultTypes(
					new TypeInteger(),					
					new TypeBigDecimal(),					
					new TypeInteger(),
					new TypeString(),
					new TypeString(),
					new TypeString()
				)
			).setUseConnection(conn);
			
		BaseStoredProcedureDispatcher storedProcedureTransTrustFactory = new StoredProcedureDispatcher(storedProcedureTransTrust).executeQuery();
			
		List<OutputContainer> transTrustResultsList = storedProcedureTransTrustFactory.getResults();
		
		if (transTrustResultsList != null){
			for (OutputContainer transTrustResults :transTrustResultsList){
				TransTrustSPDTO transTrustSPDTO = new TransTrustSPDTO();
				transTrustSPDTO.setStatus((Integer) transTrustResultsList.get(transTrustResultsList.indexOf(transTrustResults)).getFields().get(0).getValue());
				transTrustSPDTO.setAmtPaid((BigDecimal) transTrustResultsList.get(transTrustResultsList.indexOf(transTrustResults)).getFields().get(1).getValue());
				transTrustSPDTO.setPriority((Integer) transTrustResultsList.get(transTrustResultsList.indexOf(transTrustResults)).getFields().get(2).getValue());
				transTrustSPDTO.setTrustCode((String) transTrustResultsList.get(transTrustResultsList.indexOf(transTrustResults)).getFields().get(3).getValue());
				transTrustSPDTO.setCaseNum((String) transTrustResultsList.get(transTrustResultsList.indexOf(transTrustResults)).getFields().get(4).getValue());
				transTrustSPDTO.setDescr((String) transTrustResultsList.get(transTrustResultsList.indexOf(transTrustResults)).getFields().get(5).getValue());
				transTrustListSPDTO.add(transTrustSPDTO); 
				transTrustSPDTO = null;
			}
		}	
		storedProcedureTransTrust = null; 
		storedProcedureTransTrustFactory = null;
		transTrustResultsList = null;
		return transTrustListSPDTO;
	}
	// ///////////////////////////////////////////////////////////////
	// Stored Procedure Trans Trust
	// ///////////////////////////////////////////////////////////////
	public static String getCreditCardSP(int useridSrl, int intJournalNum, int transNum, String courtType, Connection conn) throws Exception {
		// ///////////////////////////////////////////////////////////////
		// Stored Procedure Trans Account Type
		// ///////////////////////////////////////////////////////////////
		StoredProcedureDescriptor storedGetCreditCard = new StoredProcedureDescriptor (
				"get_credit_card",
				"D".equals(courtType) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
				new InputParameters().addParameters(
					new TypeInteger().setValue(useridSrl),
					new TypeInteger().setValue(intJournalNum),
					new TypeInteger().setValue(transNum)
					
				),
				new OutputContainer().addResultTypes(
					new TypeString()
				)
			).setUseConnection(conn);
			
		BaseStoredProcedureDispatcher storedGetCreditCardTypeFactory = new StoredProcedureDispatcher(storedGetCreditCard).executeQuery();
			
		List<OutputContainer> storedGetCreditCardResults = storedGetCreditCardTypeFactory.getResults();
		
		return storedGetCreditCardResults.get(0).getFields().get(0).getValue().toString();
	}
}
