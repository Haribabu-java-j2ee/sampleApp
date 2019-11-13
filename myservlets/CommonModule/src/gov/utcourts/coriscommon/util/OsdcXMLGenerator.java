package gov.utcourts.coriscommon.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.net.util.Base64;

import gov.utcourts.coriscommon.common.XMLConfig;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.dcaccount.DcAccountBO;
import gov.utcourts.coriscommon.dto.CasePartyDTO;
import gov.utcourts.coriscommon.dto.ChargeDTO;
import gov.utcourts.coriscommon.dto.DcAccountDTO;
import gov.utcourts.coriscommon.dto.DcAccountDistributionDTO;
import gov.utcourts.coriscommon.dto.JudgmentDTO;
import gov.utcourts.coriscommon.dto.JudgmentDetlDTO;
import gov.utcourts.coriscommon.dto.JudgmentPartyDTO;
import gov.utcourts.coriscommon.dto.OsdcXmlHeaderDTO;
import gov.utcourts.coriscommon.xo.AccountJudgmentXrefXO;
import gov.utcourts.coriscommon.xo.AccountXO;
import gov.utcourts.coriscommon.xo.JudgmentDetlXO;
import gov.utcourts.coriscommon.xo.JudgmentXO;
import gov.utcourts.coriscommon.xo.KaseXO;
import gov.utcourts.coriscommon.xo.OsdcAcctHistoryXO;
import gov.utcourts.coriscommon.xo.OsdcXmlXO;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

public class OsdcXMLGenerator {
	static StringBuffer logString = new StringBuffer();
	static SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
	static SimpleDateFormat fmtTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	static DecimalFormat moneyFmt = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
	static {
		DecimalFormatSymbols symbols = moneyFmt.getDecimalFormatSymbols();
		symbols.setCurrencySymbol(""); // Don't use null.
		moneyFmt.setDecimalFormatSymbols(symbols);
	}
	
	/**
	 * Process OSDC based on the entries in debt_coll table. It creates 2 files: xml file
	 * and log file
	 * @param courtType
	 * @param fileName
	 * @param filePath
	 * @param mode
	 * @throws Exception
	 */
	public static void processOsdc(String courtType, String fileName, String filePath, String mode, List<Integer> acctNums) throws Exception{
		logString = new StringBuffer();
		StringBuffer result = new StringBuffer();
		HashMap<Integer, List<Integer>> map = OsdcXmlXO.findOsdcCandidates(courtType, acctNums);
		if (map.isEmpty()){
			logString.append("Nothing to process").append(System.getProperty("line.separator"));
			throw new SystemException("Nothing to process");
		} else {
			logString.append("Total cases in debt_coll: ").append(map.keySet().size()).append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));
			result.append("<OSDC Version=\"1.0\">");
			int i = 1;
			for (Integer intCaseNum : map.keySet()) {
				StringBuffer xml = new StringBuffer();
				try {
					if ("J".equals(courtType) && OsdcXmlXO.findOsdcEligibility(courtType, intCaseNum) == 0){
						logString.append("Court J int_case_num " + intCaseNum + " NOT set up for OSDC reporting, case may be deleted from debt_coll.").append(System.getProperty("line.separator"));	
						deleteAccount(mode, courtType, map.get(intCaseNum));
					} else {						
						if(OsdcXmlXO.findFFAccounts(courtType,  intCaseNum, map.get(intCaseNum)).size()>0 || OsdcXmlXO.findTrustAccounts(courtType,  intCaseNum, map.get(intCaseNum)).size()>0)
						{
							xml = createOsdcXML(mode, i, courtType,  intCaseNum, map.get(intCaseNum));
							attachAttorney(mode, courtType, intCaseNum);
							deleteAccount(mode, courtType, map.get(intCaseNum));
							result.append(xml);
							i++;
						}						
						
					}
				} catch (Exception e ){
					System.out.println("Error: " + intCaseNum);
					e.printStackTrace();
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					String sStackTrace = sw.toString(); // stack trace as a string
					logString.append("Error Occurred (int_case_num: " + intCaseNum + ": ").append(sStackTrace).append(System.getProperty("line.separator"));
				}
			}
			result.append("</OSDC>");
			PrintWriter writer = new PrintWriter(filePath + File.separator + fileName + ".xml");
			writer.print(result.toString().replace("&", "&amp;"));
			writer.close();
			writer = new PrintWriter(filePath + File.separator + fileName + ".log");
			writer.print(logString.toString());
			writer.close();
			ftpFile(fileName + ".xml", filePath);
		}
		
	}
	
	public static void ftpFile(String file, String fromDirectory) throws IOException  {
		try {
			String serverName = XMLConfig.getProperty("PROPERTY.osdc.servername");
			String userName = XMLConfig.getProperty("PROPERTY.osdc.username");
			String password = XMLConfig.getProperty("PROPERTY.osdc.password");
			String uploadDirectory = XMLConfig.getProperty("PROPERTY.osdc.osdc_ftp_directory");
			StringBuilder uploadFile = new StringBuilder(fromDirectory).append(File.separator).append(file);
			FTPUtil.ftpFile(uploadFile.toString(), file, serverName, userName, password, uploadDirectory);
			System.out.println("FTP Finished for file: " + file);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Build OSDC XML based on a case and accounts in it
	 * @param courtType
	 * @param intCaseNum
	 * @param accountNums
	 * @return a stringbuffer for OSDC XML
	 * @throws Exception
	 */
	public static StringBuffer createOsdcXML(String mode, int index, String courtType, int intCaseNum, List<Integer> accountNums) throws Exception{
		StringBuffer xml = new StringBuffer("<Section Sequence=\"" + index + "\">");
		HashMap<Integer, CasePartyDTO> partyMap = getPartyMap(courtType, intCaseNum, accountNums);
		xml.append(buildCaseXML(courtType, intCaseNum));
		xml.append(buildPartyXML(courtType, intCaseNum, partyMap));
		xml.append(buildAccountXML(mode, courtType, intCaseNum, accountNums, partyMap));
		xml.append(buildAttorneyXML(courtType, intCaseNum));
		if (KaseXO.isCrimCase(courtType, intCaseNum) && AccountXO.hasDefendantAsPayor(courtType, accountNums))
			xml.append(buildChargeXML(courtType, intCaseNum));
		xml.append(buildJudgmentXML(courtType, intCaseNum, partyMap, accountNums));
		xml.append("</Section>");
		
		return xml;
	}
	
	private static void deleteAccount(String mode, String courtType, List<Integer> accountNums) throws Exception{
		if ("P".equals(mode)){
			for (int accountNum : accountNums){
				OsdcAcctHistoryXO.updateStatusToSent(accountNum, courtType);
				OsdcXmlXO.deleteDebtCollection(courtType, accountNum);
			}
		}
	}
	
	/**
	 * Prepare a hashmap for party
	 * @param courtType
	 * @param intCaseNum
	 * @return a hashmap in <partyNum, partyBO>
	 * @throws Exception
	 */
	private static HashMap<Integer, CasePartyDTO> getPartyMap(String courtType, int intCaseNum, List<Integer> accountNums) throws Exception{
		HashMap<Integer, CasePartyDTO> partyMap = new HashMap<Integer, CasePartyDTO>();
		
		List<CasePartyDTO> parties = OsdcXmlXO.getCaseParties(courtType, intCaseNum);
		for (CasePartyDTO dto:parties){
			partyMap.put(dto.getPartyBO().getPartyNum(), dto);
		}
		
		//State of Utah
		List<DcAccountBO> ffAccounts = null;
		if (partyMap.get(4) == null){
			ffAccounts = OsdcXmlXO.findFFAccounts(courtType, intCaseNum, accountNums);
			if (ffAccounts.size() > 0){
				partyMap.put(4, OsdcXmlXO.getParty(courtType, 4));
			}
		}
		
		//Justice Court
		if ("J".equals(courtType)){
			int payeePartyNum = OsdcXmlXO.getPayeePartyNumForFineAndFee(courtType, intCaseNum);
			if (partyMap.get(payeePartyNum) == null){
				if (ffAccounts == null)
					ffAccounts = OsdcXmlXO.findFFAccounts(courtType, intCaseNum, accountNums);
				if (ffAccounts.size() > 0){
					partyMap.put(payeePartyNum, OsdcXmlXO.getParty(courtType, payeePartyNum));
				}
			}
		}

		return partyMap;
	}
	
	/**
	 * Attach attorney to OSDC creditor
	 * @param courtType
	 * @param intCaseNum
	 * @throws Exception
	 */
	public static void attachAttorney(String mode, String courtType, int intCaseNum) throws Exception{
		//Attach attorney to OSDC creditor
		if ("P".equals(mode)){
			int partyNum = OsdcXmlXO.findOsdcCreditorPartyNum(courtType, intCaseNum);
			if (partyNum > 0){
				if (!OsdcXmlXO.attorneyExists(courtType, 12094, "UT", intCaseNum, partyNum, "CRE")){
					AttyPartyBO apBO = new AttyPartyBO(courtType);
					apBO.setBarNum(12094);
					apBO.setBarState("UT");
					apBO.setAttyType("P");
					apBO.setIntCaseNum(intCaseNum);
					apBO.setPartyNum(partyNum);
					apBO.setPartyCode("CRE");
					OsdcXmlXO.insertAttorney(courtType, apBO);
					logString.append("  Attorney 12094 attached to Creditor OSDC.").append(System.getProperty("line.separator"));
				}
			}
		}
	}
	
	
	/**
	 * Build case xml
	 * @param courtType
	 * @param intCaseNum
	 * @return a stringbuffer for case xml
	 * @throws Exception
	 */
	private static StringBuffer buildCaseXML(String courtType, int intCaseNum) throws Exception{
		StringBuffer xml = new StringBuffer();
		OsdcXmlHeaderDTO masterBO = OsdcXmlXO.findCaseMaster(courtType, intCaseNum);
		logString.append("*** Case(icn)").append(System.getProperty("line.separator"));
		logString.append("Court Case: ").append(courtType).append(masterBO.getLocnCode()).append(" ").append(masterBO.getCaseNum()).append(System.getProperty("line.separator"));
		xml.append("<CaseInfo>");
		xml.append(getTagValueString("CourtType", masterBO.getCourtType()));
		xml.append(getTagValueString("CaseNum", masterBO.getCaseNum()));
		xml.append(getTagValueString("CaseType", masterBO.getDescr(), "Code", masterBO.getCaseType()));
		xml.append(getTagValueString("Location", masterBO.getLocnCode()));
		xml.append(getTagValueString("AssignedJudgeId", "" + masterBO.getAssnJudgeId()));
		if (masterBO.getProsecAgency() != null){ //Only criminal case
			xml.append(getTagValueString("ProsecutorAgency", masterBO.getProsecAgency()));
			//Prosecutor Split
			int severity = OsdcXmlXO.findHighestSeverity(courtType, intCaseNum);
			boolean prosecutorSplit = false;
			if (severity <= 40 )
				prosecutorSplit = true;
			
			if (prosecutorSplit)
				logString.append("prosec_split: ").append("Y").append(System.getProperty("line.separator"));
			else
				logString.append("prosec_split: ").append("N").append(System.getProperty("line.separator"));
			
			masterBO.setProsecutorSplit(prosecutorSplit);
			xml.append(getTagValueString("HighestSeverityLevel", "" + severity));
			xml.append(getTagValueString("ProsecutorSplit", prosecutorSplit?"Y":"N"));
		}
		
		//Prison
		String prisonFlag = OsdcXmlXO.getPrisonFlag(courtType, intCaseNum);
		if ("Y".equals(prisonFlag))
			logString.append("Defendant sent to prison: ").append("Y").append(System.getProperty("line.separator"));
		else
			logString.append("Defendant sent to prison: ").append("N").append(System.getProperty("line.separator"));
		xml.append(getTagValueString("DefendantSentToPrison", prisonFlag));
		
		//Agency Supervised
		boolean agencySupervised = OsdcXmlXO.findCaseSupervised(courtType, intCaseNum) > 0?true:false;
		if (agencySupervised)
			logString.append("Agency Supervised").append(System.getProperty("line.separator"));
		else
			logString.append("NOT Agency Supervised").append(System.getProperty("line.separator"));
		
		masterBO.setAgencySupervised(agencySupervised);
		xml.append(getTagValueString("AgencySupervised", agencySupervised?"Y":"N"));
		
		//Interest
		BigDecimal interestRate = OsdcXmlXO.findCaseInterestRate(courtType, intCaseNum);
		if (agencySupervised)
			logString.append("interest rate: ").append(interestRate).append(System.getProperty("line.separator"));
		masterBO.setInterestRate(interestRate);
		xml.append(getTagValueString("InterestRate", "" + interestRate));
		xml.append("</CaseInfo>");
		
		return xml;
	}
	
	/**
	 * Build party XML
	 * @param courtType
	 * @param intCaseNum
	 * @param accountNums
	 * @param partyMap partyMap A hashmap in <partyNum, PartyBO>
	 * @return a string buffer for party xml
	 * @throws Exception
	 */
	private static StringBuffer buildPartyXML(String courtType, int intCaseNum, HashMap<Integer, CasePartyDTO> partyMap) throws SystemException{
		
		StringBuffer xml = new StringBuffer();
		xml.append("<Parties>");
		for (Integer partyNum : partyMap.keySet()) {
			xml.append("<Party PartyNumber=\"" + partyNum + "\">");
			CasePartyDTO dto = partyMap.get(partyNum);
			xml.append(getTagValueString("PartyType", dto.getPartyTypeBO().getDescr(), "Code", dto.getPartyTypeBO().getPartyCode()));
			xml.append(getTagValueCdataString("LastName", dto.getPartyBO().getLastName()));
			xml.append(getTagValueCdataString("FirstName", dto.getPartyBO().getFirstName()));
			xml.append(getTagValueString("MiddleName", ""));
			xml.append(getTagValueString("Address1", dto.getPartyBO().getAddress1()));
			xml.append(getTagValueString("Address2", dto.getPartyBO().getAddress2()));
			xml.append(getTagValueString("City", dto.getPartyBO().getCity()));
			xml.append(getTagValueString("StateCode",dto.getPartyBO().getStateCode()));
			xml.append(getTagValueString("ZipCode", dto.getPartyBO().getZipCode()));
			xml.append(getTagValueString("HomePhone", dto.getPartyBO().getHomePhone()));
			xml.append(getTagValueString("BusinessPhone", dto.getPartyBO().getBusPhone()));
			xml.append(getTagValueString("Gender", dto.getPartyBO().getGender()));
			
			if (dto.getPartyBO().getSsn() != null && dto.getPartyBO().getSsn().trim().length() > 0){
				String encoded = dto.getPartyBO().getSsn(); //getBase64Encoded(dto.getPartyBO().getSsn());
				xml.append(getTagValueString("SocialSecurityNumber", encoded));
			} else
				xml.append(getTagValueString("SocialSecurityNumber", null));
			
			if (dto.getPartyBO().getBirthDate() != null){
				String encoded = fmt.format(dto.getPartyBO().getBirthDate()); //getBase64Encoded(fmt.format(dto.getPartyBO().getBirthDate()));
				xml.append(getTagValueString("BirthDate", encoded));
			} else
				xml.append(getTagValueString("BirthDate", null));
			
			if (dto.getPartyBO().getDrLicNum() != null && dto.getPartyBO().getDrLicNum().trim().length() > 0){ 
				String encoded = dto.getPartyBO().getDrLicNum(); //getBase64Encoded(dto.getPartyBO().getDrLicNum());
				xml.append(getTagValueString("DriverLicenseNum", encoded));
			} else {
				xml.append(getTagValueString("DriverLicenseNum", null));
			}
			
			xml.append(getTagValueString("DriverLicenseState", dto.getPartyBO().getDrLicState()));
			xml.append(getTagValueString("EmployerName", dto.getPartyBO().getEmployerName()));
			xml.append(getTagValueString("EmployerAddress",dto.getPartyBO().getEmpAddr()));
			xml.append("</Party>");
		}
		
		xml.append("</Parties>");
		
		return xml;
	}
	
	/**
	 * Build attorney XML
	 * @param courtType
	 * @param intCaseNum
	 * @return a stringbuffer for attorney xml
	 * @throws Exception
	 */
	private static StringBuffer buildAttorneyXML(String courtType, int intCaseNum) throws Exception{
		StringBuffer xml = new StringBuffer();
		List<AttorneyBO> attorneys = OsdcXmlXO.findDefendantAttorneys(courtType, intCaseNum);
		if (attorneys.size() > 0){
			xml.append("<Attorneys>");
			for (AttorneyBO attorneyBO : attorneys){
				logString.append("T record(trust,acct #): ").append(attorneyBO.getBarState()).append(attorneyBO.getBarNum()).append(System.getProperty("line.separator"));
				xml.append("<Attorney>");
				xml.append(getTagValueString("BarNum", Integer.toString(attorneyBO.getBarNum())));
				xml.append(getTagValueString("BarState", attorneyBO.getBarState()));
				xml.append(getTagValueString("FirstName", attorneyBO.getFirstName()));
				xml.append(getTagValueString("LastName", attorneyBO.getLastName()));
				xml.append(getTagValueString("Address1", attorneyBO.getAddress1()));
				xml.append(getTagValueString("Address2", attorneyBO.getAddress2()));
				xml.append(getTagValueString("City", attorneyBO.getCity()));
				xml.append(getTagValueString("StateCode", attorneyBO.getStateCode()));
				xml.append(getTagValueString("ZipCode", attorneyBO.getZipCode()));
				xml.append(getTagValueString("BusinessPhone", attorneyBO.getBusPhone()));
				xml.append(getTagValueString("FaxNum", attorneyBO.getFaxNum()));
				xml.append(getTagValueString("CellPhone", null)); //Not ready yet
				xml.append("</Attorney>");
			}
			xml.append("</Attorneys>");
		}
		return xml;
	}
	
	/**
	 * Build charge XML
	 * @param courtType
	 * @param intCaseNum
	 * @return a stringbuffer for charge xml
	 * @throws Exception
	 */
	private static StringBuffer buildChargeXML(String courtType, int intCaseNum) throws Exception{
		StringBuffer xml = new StringBuffer();
		List<ChargeDTO> charges = OsdcXmlXO.findChargeByCase(courtType, intCaseNum);
		if (charges.size() > 0){
			xml.append("<Charges>");
			for (ChargeDTO dto : charges){
				logString.append("C record(charge#): ").append(dto.getChargeBO().getSeq()).append(System.getProperty("line.separator"));
				xml.append("<Charge>");
				xml.append(getTagValueString("Sequence", "" + dto.getChargeBO().getSeq()));
				
				xml.append(getTagValueString("GovCode", dto.getChargeBO().getGovCode()));
				xml.append(getTagValueString("ViolationCode", dto.getChargeBO().getViolCode()));
				xml.append(getTagValueString("ViolationDateTime", fmtTime.format(dto.getChargeBO().getViolDatetime())));
				xml.append(getTagValueString("ViolationLocation", dto.getChargeBO().getViolLocn()));
				xml.append(getTagValueCdataString("OffenseViolationCode", dto.getOffenseBO().getDescr(), "Code", dto.getChargeBO().getOffsViolCode()));
				xml.append(getTagValueString("OffenseViolationEffectiveDate", fmt.format(dto.getChargeBO().getOffsEffectDate())));
				xml.append(getTagValueString("InchoateFlag", dto.getChargeBO().getInchoateFlag()));
				xml.append(getTagValueString("Severity", dto.getChargeBO().getSeverity()));
				xml.append(getTagValueString("PleaCode", dto.getPleaTypeBO().getDescr(), "Code", dto.getChargeBO().getPleaCode()));
				if (dto.getChargeBO().getPleaDate() != null)
					xml.append(getTagValueString("PleaDate", fmt.format(dto.getChargeBO().getPleaDate())));
				else
					xml.append(getTagValueString("PleaDate", null));
				xml.append(getTagValueString("JudgmentCode", dto.getJdmtTypeBO().getDescr(), "Code", dto.getChargeBO().getJdmtCode()));
				if (dto.getChargeBO().getJdmtDate() != null)
					xml.append(getTagValueString("JudgmentDate", fmt.format(dto.getChargeBO().getJdmtDate())));
				else
					xml.append(getTagValueString("JudgmentDate", null));
				xml.append("</Charge>");
			}
			xml.append("</Charges>");
		}
		return xml;
	}
	
	/**
	 * Build account XML
	 * @param courtType
	 * @param intCaseNum
	 * @param accountNums
	 * @param partyMap partyMap A hashmap in <partyNum, PartyBO>
	 * @return a string buffer for judgment xml
	 * @throws Exception
	 */
	private static StringBuffer buildAccountXML(String mode, String courtType, int intCaseNum, List<Integer> accountNums, HashMap<Integer, CasePartyDTO> partyMap) throws Exception{
		StringBuffer xml = new StringBuffer();
		int justiceCourtPartyNum = 0;
		if ("J".equals(courtType)){
			justiceCourtPartyNum = OsdcXmlXO.getPayeePartyNumForFineAndFee(courtType, intCaseNum);
		}
		xml.append("<Accounts>");
		List<DcAccountBO> ffAccounts = OsdcXmlXO.findFFAccounts(courtType, intCaseNum, accountNums);
		for (DcAccountBO accountBO : ffAccounts){
			logString.append("A record(fee/fine, acct #): ").append(accountBO.getAcctNum()).append(System.getProperty("line.separator"));
			xml.append("<Account>");
			xml.append(getTagValueString("AccountNum", "" + accountBO.getAcctNum()));
			xml.append(getTagValueString("AccountType", getAccountType(accountBO.getAcctType()), "Code", accountBO.getAcctType()));
			xml.append(getTagValueString("AmountDue", moneyFmt.format(accountBO.getAmtDue())));
			xml.append(getTagValueString("AmountPaid", moneyFmt.format(accountBO.getAmtPaid())));
			xml.append(getTagValueString("AmountCredit", moneyFmt.format(accountBO.getAmtCredit())));
			if (accountBO.getIntEffectDate() != null)
				xml.append(getTagValueString("InterestEffectiveDate", fmt.format(accountBO.getIntEffectDate())));
			else
				xml.append(getTagValueString("InterestEffectiveDate", null));
			//State of UTAH
			xml.append(getTagValueString("Payee", null, "PartyNumber", "4")); //Hard coded
			//Justice Court
			if (justiceCourtPartyNum > 0)
				xml.append(getTagValueString("Payee", null, "PartyNumber", "" + justiceCourtPartyNum)); 
			
			xml.append(getTagValueString("Payer", null, "PartyNumber", "" + accountBO.getPartyNum()));
			List<DcAccountDistributionDTO> distributions = OsdcXmlXO.findAccountDistributions(courtType, accountBO.getAcctNum());
			for (DcAccountDistributionDTO dist : distributions){
				logString.append("D record(dist_code): ").append(dist.getDistCode()).append(System.getProperty("line.separator"));
				xml.append("<AccountDistribution>");
				xml.append(getTagValueString("DistributionCode", dist.getDescr(), "Code", dist.getDistCode()));
				xml.append(getTagValueString("AmountDue", moneyFmt.format(dist.getAmtDue())));
				xml.append(getTagValueString("AmountPaid", moneyFmt.format(dist.getAmtPaid())));
				xml.append(getTagValueString("AmountCredit", moneyFmt.format(dist.getAmtCredit())));
				xml.append("</AccountDistribution>");
			}
			xml.append("</Account>");
			
			if ("P".equals(mode)){
				BigDecimal balance = accountBO.getAmtDue().subtract(accountBO.getAmtPaid().add(accountBO.getAmtCredit()));
				String detailType = OsdcXmlXO.getAccountDetailType(courtType, accountBO.getAcctType(), accountBO.getAcctNum());
				StringBuilder histNote = new StringBuilder("The balance of ");
				histNote.append(moneyFmt.format(balance));
				histNote.append(" for ");
				histNote.append(detailType);
				histNote.append(" on this case has been transferred to the Utah Office of State Debt Collection (OSDC) for collection purposes.");
				histNote.append(" Any payment made must be made to OSDC.");
				OsdcXmlXO.addCaseHistory(courtType, intCaseNum, histNote.toString(), 0);
			}
		}
		
		List<DcAccountDTO> trustAccounts = OsdcXmlXO.findTrustAccounts(courtType, intCaseNum, accountNums);
		for (DcAccountDTO dto : trustAccounts){
			logString.append("A record(trust,acct #): ").append(dto.getAcctNum()).append(System.getProperty("line.separator"));
			xml.append("<Account>");
			xml.append(getTagValueString("AccountNum", "" + dto.getAcctNum()));
			xml.append(getTagValueString("AccountType", getAccountType(dto.getAcctType()), "Code", dto.getAcctType()));
			xml.append(getTagValueString("TrustCode", dto.getTrustCode()));
			xml.append(getTagValueString("AmountDue", moneyFmt.format(dto.getAmtDue())));
			xml.append(getTagValueString("AmountPaid", moneyFmt.format(dto.getAmtPaid())));
			xml.append(getTagValueString("AmountCredit", moneyFmt.format(dto.getAmtCredit())));
			xml.append(getTagValueString("Payee", null, "PartyNumber", "" + dto.getPayeePartyNum()));
			xml.append(getTagValueString("Payer", null, "PartyNumber", "" + dto.getPartyNum()));
			if (dto.getIntEffectDate() != null)
				xml.append(getTagValueString("InterestEffectiveDate", fmt.format(dto.getIntEffectDate())));
			else
				xml.append(getTagValueString("InterestEffectiveDate", null));
			List<DcAccountDistributionDTO> distributions = OsdcXmlXO.findAccountDistributions(courtType, dto.getAcctNum());
			for (DcAccountDistributionDTO dist : distributions){
				logString.append("D record(dist_code): ").append(dist.getDistCode()).append(System.getProperty("line.separator"));
				xml.append("<AccountDistribution>");
				xml.append(getTagValueString("DistributionCode", dist.getDescr(), "Code", dist.getDistCode()));
				xml.append(getTagValueString("AmountDue", moneyFmt.format(dist.getAmtDue())));
				xml.append(getTagValueString("AmountPaid", moneyFmt.format(dist.getAmtPaid())));
				xml.append(getTagValueString("AmountCredit", moneyFmt.format(dist.getAmtCredit())));
				xml.append("</AccountDistribution>");
			}
			xml.append("</Account>");
			
			if ("P".equals(mode)){
				BigDecimal balance = dto.getAmtDue().subtract(dto.getAmtPaid().add(dto.getAmtCredit()));
				String detailType = OsdcXmlXO.getAccountDetailType(courtType, dto.getAcctType(), dto.getAcctNum());
				StringBuilder histNote = new StringBuilder("The balance of ");
				histNote.append(moneyFmt.format(balance));
				histNote.append(" for ");
				histNote.append(detailType);
				histNote.append(" on this case has been transferred to the Utah Office of State Debt Collection (OSDC) for collection purposes.");
				histNote.append(" Any payment made must be made to OSDC.");
				OsdcXmlXO.addCaseHistory(courtType, intCaseNum, histNote.toString(), 0);
			}
		}
		
		xml.append("</Accounts>");
		
		return xml;
	}
	
	/**
	 * Build judgment XML
	 * @param courtType
	 * @param intCaseNum
	 * @param partyMap A hashmap in <partyNum, PartyBO>
	 * @return a string buffer for judgment xml
	 * @throws Exception
	 */
	private static StringBuffer buildJudgmentXML(String courtType, int intCaseNum, HashMap<Integer, CasePartyDTO> partyMap, List<Integer> accountNums) throws Exception{
		StringBuffer xml = new StringBuffer();
		List<JudgmentDTO> judgments = JudgmentXO.findJudgments(courtType, intCaseNum);
		if (judgments.size() > 0){
			xml.append("<Judgments>");
			for (JudgmentDTO judgmentBO : judgments){
				logString.append("J record(judgment#): ").append(judgmentBO.getJdmtSeq()).append(System.getProperty("line.separator"));
				
				//If this judgment has nothing to do with the accounts we are sending to OSDC
				//do not include it
				if (!judgmentRelated(courtType, intCaseNum, judgmentBO.getJdmtSeq(), accountNums))
					continue;
				
				xml.append("<Judgment>");
				xml.append(getTagValueString("JudgmentSequence", "" + judgmentBO.getJdmtSeq()));
				xml.append(getTagValueString("FilingDateTime", fmtTime.format(judgmentBO.getFilingDatetime())));
				xml.append(getTagValueString("JudgmentDateTime", fmtTime.format(judgmentBO.getJdmtDatetime())));
				xml.append(getTagValueString("JudgmentAmount", moneyFmt.format(judgmentBO.getJdmtAmt())));
				xml.append(getTagValueString("JudgmentCode", judgmentBO.getJudgmentDescr(), "Code", judgmentBO.getJdmtCode()));
				
				List<JudgmentDetlDTO> judgmentDetails = JudgmentDetlXO.findJudgmentDetails(courtType, intCaseNum, judgmentBO.getJdmtSeq());
				for (JudgmentDetlDTO detailBO : judgmentDetails){
					logString.append("U record(judgmt#|detl#): ").append(judgmentBO.getJdmtSeq()).append("|").append(detailBO.getDetlSeq()).append(System.getProperty("line.separator"));
					int judgmentAcctNum = AccountJudgmentXrefXO.getAccountNumByJudgmentDetail(courtType, intCaseNum, judgmentBO.getJdmtSeq(), detailBO.getDetlSeq());
					if (judgmentAcctNum > 0)
						xml.append("<JudgmentDetail AccountNum=\"" + judgmentAcctNum + "\">");
					else
						xml.append("<JudgmentDetail>");
					xml.append(getTagValueString("DetailSequence", "" + detailBO.getDetlSeq()));
					xml.append(getTagValueString("Sequence", "" + detailBO.getSeq()));
					xml.append(getTagValueString("Description", detailBO.getDetlDescr()));
					xml.append(getTagValueString("Amount", moneyFmt.format(detailBO.getAmt())));
					
					//Step 9 get Creditors
					List<JudgmentPartyDTO> creditors = OsdcXmlXO.findJudgmentCreditors(courtType, intCaseNum, judgmentBO.getJdmtSeq(), detailBO.getDetlSeq());
					if (creditors.size() > 0){
						xml.append("<Creditors>");
						for (JudgmentPartyDTO creditor : creditors){
							logString.append("R record(jdmt-detl-creditor): ").append(judgmentBO.getJdmtSeq()).append("-").append(detailBO.getDetlSeq())
							.append(" - ").append(partyMap.get(creditor.getPartyBO().getPartyNum()).getPartyBO().getLastName()).append(System.getProperty("line.separator"));
							xml.append(getTagValueString("Creditor", null, "PartyNumber", "" + creditor.getPartyBO().getPartyNum()));
						}
						xml.append("</Creditors>");
					}
					//Step 9 get Creditors
					List<JudgmentPartyDTO> debtors = OsdcXmlXO.findJudgmentDebtors(courtType, intCaseNum, judgmentBO.getJdmtSeq(), detailBO.getDetlSeq());
					if (debtors.size() > 0){
						xml.append("<Debtors>");
						for (JudgmentPartyDTO debtor : debtors){
							logString.append("R record(jdmt-detl-debtor): ").append(judgmentBO.getJdmtSeq()).append("-").append(detailBO.getDetlSeq())
							.append(" - ").append(partyMap.get(debtor.getPartyBO().getPartyNum()).getPartyBO().getFirstName()).append(" ")
							.append(partyMap.get(debtor.getPartyBO().getPartyNum()).getPartyBO().getLastName()).append(System.getProperty("line.separator"));
							xml.append(getTagValueString("Debtor", null, "PartyNumber", "" + debtor.getPartyBO().getPartyNum()));
						}
						xml.append("</Debtors>");
					}
					xml.append("</JudgmentDetail>");
				}
				xml.append("</Judgment>");
			}
			xml.append("</Judgments>");
		}
		
		return xml;
	}
	
	private static boolean judgmentRelated(String courtType, int intCaseNum, int jdmtSeq, List<Integer> acctNums) throws Exception{
		//1 is always the only account in F and I, and the dominant account in T
		List<Integer> acctNumsInJudgment = AccountJudgmentXrefXO.getAccountNumsByJudgment(courtType, intCaseNum, jdmtSeq);
		//case level old judgments should always be included. It should not happen but just in case...
		if (acctNumsInJudgment == null || acctNumsInJudgment.size() == 0){
			return true;
		} else { //new judgment - link to account
			//We will return true if any of the accounts is related.
			for (int acctNum:acctNums){
				if (acctNumsInJudgment.contains(acctNum))
					return true;
			}
		}
		return false;
	}
	/**
	 * Build a xml line <tag>value</tag>
	 * @param tag
	 * @param value
	 * @return
	 */
	private static StringBuffer getTagValueString(String tag, String value){
		StringBuffer sb = new StringBuffer("<").append(tag).append(">");
		if (value != null && value.trim().length() > 0){
			 sb.append(value);
		}
		sb.append("</").append(tag).append(">");
		return sb;
	}
	
	/**
	 * Build a xml line with CDATA tag <tag>![CDATA[value]]</tag>
	 *  Added 01/30/2019.  Selart  Piv #163598349
	 * @param tag
	 * @param value
	 * @return
	 */
	private static StringBuffer getTagValueCdataString(String tag, String value){
		StringBuffer sb = new StringBuffer("<").append(tag).append(">");
		if (value != null && value.trim().length() > 0){
			 sb.append("<![CDATA[").append(value).append("]]>");
		}
		sb.append("</").append(tag).append(">");
		return sb;
	}
	
	/**
	 * Build a xml line <tag attrName = "attrValue">value</tag>
	 * @param tag
	 * @param value
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	private static StringBuffer getTagValueString(String tag, String value, String attrName, String attrValue){
		StringBuffer sb = new StringBuffer("<").append(tag);
		if (attrValue != null && attrValue.trim().length()>0)
		sb.append(" ").append(attrName).append("=").append("\"").append(attrValue).append("\"");
		sb.append(">");
		if (value != null && value.trim().length() > 0){
			 sb.append(value);
		}
		sb.append("</").append(tag).append(">");
		return sb;
	}
	
	/**
	 * Build a xml line with CDATA tag <tag attrName = "attrValue">![CDATA[value]]</tag>
	 *  Added 01/30/2019.  Selart  Piv #163598349
	 * @param tag
	 * @param value
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	private static StringBuffer getTagValueCdataString(String tag, String value, String attrName, String attrValue){
		StringBuffer sb = new StringBuffer("<").append(tag);
		if (attrValue != null && attrValue.trim().length()>0)
		sb.append(" ").append(attrName).append("=").append("\"").append(attrValue).append("\"");
		sb.append(">");
		if (value != null && value.trim().length() > 0){
			 sb.append("<![CDATA[").append(value).append("]]>");
		}
		sb.append("</").append(tag).append(">");
		return sb;
	}
	
	/**
	 * @param tag
	 * @param value
	 * @param attrNames
	 * @param attrValues
	 * @return
	 */
	private static StringBuffer getTagValueString(String tag, String value, List<String> attrNames, List<String> attrValues){
		StringBuffer sb = new StringBuffer("<").append(tag);
		for (int i = 0; i<attrNames.size(); i++){
			sb.append(" ").append(attrNames.get(i)).append("=").append("\"").append(attrValues.get(i)).append("\"");
		}
		sb.append(">");
		if (value != null && value.trim().length() > 0){
			 sb.append(value);
		}
		sb.append("</").append(tag).append(">");
		return sb;
	}
	
	/**
	 * @param str string to be encoded
	 * @return encoded string
	 */
	private static String getBase64Encoded(String str){
		byte[] encodedBytes = Base64.encodeBase64(str.getBytes());
		return new String(encodedBytes);
	}
	
	/**
	 * @param accountTypeCode
	 * @return
	 */
	public static String getAccountType(String accountTypeCode){
		 if ("F".equals(accountTypeCode))
			 return "Fee";
		 else if ("I".equals(accountTypeCode))
			 return "Fine";
		 else if ("O".equals(accountTypeCode))
			 return "Bond";
		 else if ("B".equals(accountTypeCode))
			 return "Bail";
		 else if ("T".equals(accountTypeCode))
			 return "Trust";
		 
        return "";
	}
	
	public static void main(String[] args) throws Exception{
		long start = System.currentTimeMillis();
		DatabaseConnection.setUseJdbc();
		
		//args = new String[4];
		//args[0] = "DC111111_111";
		//args[1] = "/tmp";
		//args[2] = "D";
		//args[3] = "T";
		//args[4] = "14000954";
		//args[5] = "58764";
		
		
		if (args.length < 4){
			System.out.println("Proper Usage is: filename(no suffix), filepath(example: /temp/osdc), courttype(D or J), mode(P or T).");
	        System.exit(0);
		} else {
			try {
				String fileName = args[0];
				if ("".equals(fileName)){
					System.out.println("File name cannot be empty.");
					System.exit(0);
				}
				String filePath = args[1];
				if ("".equals(filePath)){
					System.out.println("File path cannot be empty.");
					System.exit(0);
				}
				String courtType = args[2];
				if (!"D".equals(courtType) && !"J".equals(courtType)){
					System.out.println("Court Type can only be D or J not "+courtType);
					System.exit(0);
				}
				String mode = args[3];
				if (!"P".equals(mode) && !"T".equals(mode)){
					System.out.println("Mode can only be T or P not "+mode);
					System.exit(0);
				}
				
				List<Integer> acctNums = null;
				if (args.length > 4){
					acctNums = new ArrayList<Integer>();
					for (int i = 4; i < args.length; i++){
						acctNums.add(Integer.valueOf(args[i]));
					}
				}
				processOsdc(courtType, fileName, filePath, mode, acctNums);
				long finish = System.currentTimeMillis();
				long timeElapsed = finish - start;
				System.out.println("timeElapsed : " + timeElapsed);
				//StringBuffer buffer = createOsdcXML("D", intCaseNum, accountNums);
				//System.out.println(buffer);
				//System.out.println(logString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
