package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.acctfee.AcctFeeBO;
import gov.utcourts.coriscommon.dataaccess.accttrust.AcctTrustBO;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.casehist.CaseHistBO;
import gov.utcourts.coriscommon.dataaccess.caseme.CaseMeBO;
import gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.courtprocess.CourtProcessBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.dcaccount.DcAccountBO;
import gov.utcourts.coriscommon.dataaccess.dcacctdist.DcAcctDistBO;
import gov.utcourts.coriscommon.dataaccess.dcaccttrust.DcAcctTrustBO;
import gov.utcourts.coriscommon.dataaccess.debtcoll.DebtCollBO;
import gov.utcourts.coriscommon.dataaccess.disttype.DistTypeBO;
import gov.utcourts.coriscommon.dataaccess.feetype.FeeTypeBO;
import gov.utcourts.coriscommon.dataaccess.jdmttype.JdmtTypeBO;
import gov.utcourts.coriscommon.dataaccess.judgmentcreditor.JudgmentCreditorBO;
import gov.utcourts.coriscommon.dataaccess.judgmentdebtor.JudgmentDebtorBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.offense.OffenseBO;
import gov.utcourts.coriscommon.dataaccess.osdcaccthistory.OsdcAcctHistoryBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.partytype.PartyTypeBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.pleatype.PleaTypeBO;
import gov.utcourts.coriscommon.dataaccess.postjdmtintrate.PostJdmtIntRateBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.sentence.SentenceBO;
import gov.utcourts.coriscommon.dataaccess.severitytype.SeverityTypeBO;
import gov.utcourts.coriscommon.dataaccess.trusttype.TrustTypeBO;
import gov.utcourts.coriscommon.dto.CasePartyDTO;
import gov.utcourts.coriscommon.dto.ChargeDTO;
import gov.utcourts.coriscommon.dto.DcAccountDTO;
import gov.utcourts.coriscommon.dto.DcAccountDistributionDTO;
import gov.utcourts.coriscommon.dto.JudgmentPartyDTO;
import gov.utcourts.coriscommon.dto.OsdcXmlHeaderDTO;
import gov.utcourts.coriscommon.util.SystemException;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

 

public class OsdcXmlXO implements BaseConstants {
	private static final Logger logger = Logger.getLogger(OsdcXmlXO.class);
	public static int PRINT_SQL = RUN;
	private static final String EXCEP_OSDC_XML_XO = "Exception in OsdcXmlXO: ";
	static DecimalFormat moneyFmt = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
	
	public static void main(String args[]) throws Exception
	{
		//DatabaseConnection.setUseJdbc();
		/*List<Integer> accountNums=new ArrayList<Integer>();
		accountNums.add(17592226);
		accountNums.add(17592225);*/
		//System.out.println(getUserId("D", 11460996));
		//System.out.println(getPrisonFlag("D", 11431092)); //11427511, 11460996
		// findFFAccounts("D", 11789599,  accountNums);
		// findTrustAccounts("D", 11789599,  accountNums);
		
		//System.out.println(getAccountDetailType("D", "I", 12345));
		//System.out.println(getAccountDetailType("D", "T", 521716));
		//System.out.println(getAccountDetailType("J", "F", 7042760));
		
		//test("D",7996289);
		
		/*
		System.out.println("********Testing findMaster:");
		OsdcXmlHeaderDTO master =  findCaseMaster("D", 41451); // 7996289);
		System.out.println(master.getCaseNum());
		
		System.out.println("********Testing findOsdcCandidates:");
		HashMap<Integer, List<Integer>> map = findOsdcCandidates("D", null);
		for (Integer intCaseNum : map.keySet())  
            System.out.println("key: " + intCaseNum); 
		
		System.out.println("********Testing findOsdcEligibility:");
		System.out.println(findOsdcEligibility("D", 7047671));
		
		System.out.println("********Testing getCaseParties:");
		List<CasePartyDTO> caseParties = getCaseParties("D", 7047671);
		for (CasePartyDTO caseParty:caseParties ){
			System.out.println(caseParty.getPartyBO().getLastName());
		}
		
		System.out.println("********Testing findTrustAccounts:");
		List<Integer> acctNums = new ArrayList<Integer>();
		acctNums.add(44360);
		acctNums.add(50277);
		List<DcAccountDTO> trustAccounts = findTrustAccounts("D", 7047671, acctNums);
		for (DcAccountDTO account:trustAccounts ){
			System.out.println(account.getAcctNum());
		}
		
		System.out.println("********Testing findHighestSeverity:");
		System.out.println(findHighestSeverity("D", 7047671));
		
		System.out.println("********Testing findCaseSupervised:");
		System.out.println(findCaseSupervised("D", 7047671));
		
		System.out.println("********Testing findChargeByCase:");
		List<ChargeDTO> charges = findChargeByCase("D", 8085763);
		for (ChargeDTO charge:charges){
			System.out.println(charge.getOffenseBO().getDescr() + " " + charge.getPleaTypeBO().getDescr() + " " + charge.getJdmtTypeBO().getDescr());
		}
		
		System.out.println("********Testing getPayeePartyNumForFineAndFee");
		System.out.println(getPayeePartyNumForFineAndFee("J", 8085366));
		
		
		System.out.println("********Testing findDefendantAttorneys:");
		List<AttorneyBO> list = findDefendantAttorneys("D", 674);
		for (AttorneyBO vo:list){
			System.out.println(vo.getBarNum());
		}
		
		System.out.println("********Testing attorneyExists:");
		System.out.println(attorneyExists("D", 12094, "UT", 8029867, 1041558041, "CRE"));
		
		System.out.println("********Testing findOsdcCreditorPartyNum:");
		System.out.println(findOsdcCreditorPartyNum("D", 674));
		
		System.out.println("********Testing findJudgmentCreditors:");
		List<JudgmentPartyDTO> creditors = findJudgmentCreditors("D", 450, 1, 2);
		for (JudgmentPartyDTO dto:creditors){
			System.out.println(dto.getPartyBO().getLastName() + " " + dto.getDetlSeq());
		}
		
		System.out.println("********Testing findJudgmentDebtors:");
		List<JudgmentPartyDTO> debtors = findJudgmentDebtors("D", 450, 1, 2);
		for (JudgmentPartyDTO dto:debtors){
			System.out.println(dto.getPartyBO().getLastName() + " " + dto.getDetlSeq());
		}
		
		System.out.println("********Testing findAccountDistributions:");
		List<DcAccountDistributionDTO> dists = findAccountDistributions("D", 58322);
		for (DcAccountDistributionDTO dto:dists){
			System.out.println(dto.getAcctNum() + " " + dto.getDescr() + " " + dto.getAmtDue());
		}
		
		*/
		
		//System.out.println("********Testing findCaseInterestRate:");
		//System.out.println(findCaseInterestRate("D", 11119499));
		
	}
	
	
	
	public static HashMap<Integer, List<Integer>> findOsdcCandidates(String courtType, List<Integer> acctNums) throws Exception {

		HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		
//    	SearchDescriptor s1 = new SearchDescriptor(
//			new DcAccountBO(courtType).orderBy(new SortDescriptor("int_case_num"))
//		);
//    	
//    	SearchDescriptor s2 = null;
//    	
//    	if (acctNums == null || acctNums.size() == 0){
//    		s2 = new SearchDescriptor(
//    			new DebtCollBO(courtType).orderBy(new SortDescriptor("acct_num"))
//    		);
//    	} else {
//    		
//    		s2 = new SearchDescriptor(
//    			new DebtCollBO(courtType)
//    				.where(
//    						new Expression(Exp.LEFT_PARENTHESIS, DebtCollBO.ACCTNUM, Exp.IN, new IntegerArrayDescriptor(acctNums), Exp.RIGHT_PARENTHESIS))
//    				.orderBy(new SortDescriptor("acct_num"))
//    		);
//    	}
//    
//    	BaseSearchDispatcher sp = new SearchDispatcher(s1, s2)
//    		.setDistinct()
//    		.addForeignKey(DcAccountBO.ACCTNUM, DebtCollBO.ACCTNUM);
//    	sp.search();
//    	
//		List<DcAccountBO> s1Results = (List<DcAccountBO>) s1.getResults();
		
		DebtCollBO debtCollBO = new DebtCollBO(courtType).orderBy(DebtCollBO.ACCTNUM);
		if (acctNums != null && acctNums.size() > 0)
			debtCollBO.where(DebtCollBO.ACCTNUM, Exp.IN, acctNums);
		
		List<DcAccountBO> s1Results = new DcAccountBO(courtType)
		.setDistinct()
		.includeTables(debtCollBO)
     	.addForeignKey(DcAccountBO.ACCTNUM, DebtCollBO.ACCTNUM)
     	.orderBy(DcAccountBO.INTCASENUM)
		.search();
    	  
    	
    	int prevIntCaseNum = 0;
        int intCaseNum = -1;
        List<Integer> accountNums = new ArrayList<Integer>();
        
        for (int i=0; i < s1Results.size(); i++) {
        	intCaseNum = s1Results.get(i).getIntCaseNum();
        	if (intCaseNum != prevIntCaseNum && prevIntCaseNum != 0){
        		map.put(prevIntCaseNum, accountNums);
        		accountNums = new ArrayList<Integer>();
        	}
        	prevIntCaseNum = intCaseNum;
        	accountNums.add(s1Results.get(i).getAcctNum());
        }
        
        if (prevIntCaseNum != 0){
        	map.put(intCaseNum, accountNums);
        }
        	
        return map;	
    }
	
	public static int findOsdcCreditorPartyNum(String courtType, int intCaseNum) throws Exception {
		
//		FieldOperationDescriptor min = new FieldOperationDescriptor(PartyBO.PARTYNUM, FieldOperationType.MIN, new TypeInteger());
//		SearchDescriptor s1 = new SearchDescriptor(
//			new PartyCaseBO(courtType).where(PartyCaseBO.INTCASENUM, intCaseNum).where(PartyCaseBO.PARTYCODE, "CRE")
//		); 
//		
//		SearchDescriptor s2 = new SearchDescriptor(
//			new PartyBO(courtType).setFieldOperations(min).addWhereDescriptors(new WhereCustomDescriptor("t2.last_name matches 'STATE DEBT COLLECT*'"))
//		);
//		
//		BaseSearchDispatcher searchDispatcher = new SearchDispatcher(s1, s2)
//		.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
//		//.toString(true)
//		.search(PartyCaseBO.NO_FIELDS);
//		
//		PartyBO vo = (PartyBO) s2.getResults().get(0);
//		int result = (Integer) vo.get(min);
//		return result;
		
		int min = (Integer) new PartyBO(courtType)
		.min(PartyBO.PARTYNUM)
		.includeTables(new PartyCaseBO(courtType).where(PartyCaseBO.INTCASENUM, intCaseNum).where(PartyCaseBO.PARTYCODE, "CRE"))
		.where(PartyBO.LASTNAME, Exp.MATCHES, "STATE DEBT COLLECT*'")
		.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
		.find(PartyCaseBO.NO_FIELDS)
		.getMin();
		
		return min;
	}
	

	
	public static BigDecimal findCaseInterestRate(String courtType, int intCaseNum) throws Exception {
		
//		FieldOperationDescriptor max = new FieldOperationDescriptor(PostJdmtIntRateBO.FIRSTEFFECTDATE, FieldOperationType.MAX, new TypeDate()).removeAlias();
//		FieldOperationDescriptor min = new FieldOperationDescriptor(AccountBO.INTEFFECTDATE, FieldOperationType.MIN, new TypeDate()).removeAlias();
//			
//		WhereSelectDescriptor whereSub = new WhereSelectDescriptor(
//			new TableAndFieldDescriptor(PostJdmtIntRateBO.FIRSTEFFECTDATE), 
//			new AccountBO(courtType).setFieldOperations(min).where(AccountBO.INTCASENUM, intCaseNum),
//			Exp.LESS_THAN
//		);
//				
//		WhereSelectDescriptor where = new WhereSelectDescriptor(new TableAndFieldDescriptor(PostJdmtIntRateBO.FIRSTEFFECTDATE), 
//			new PostJdmtIntRateBO(courtType).setFieldOperations(max).addWhereDescriptors(whereSub), 
//			Exp.EQUALS
//		);
//			
//		PostJdmtIntRateBO vo = new PostJdmtIntRateBO(courtType)
//		.addWhereDescriptors(where)
//		.find(PostJdmtIntRateBO.INTERESTRATE);
		
		PostJdmtIntRateBO bo = new PostJdmtIntRateBO(courtType)
		.where(PostJdmtIntRateBO.FIRSTEFFECTDATE, Exp.EQUALS, Exp.select(
			new PostJdmtIntRateBO(courtType).max(PostJdmtIntRateBO.FIRSTEFFECTDATE).where(PostJdmtIntRateBO.FIRSTEFFECTDATE, Exp.LESS_THAN, Exp.select(
				new AccountBO(courtType).min(AccountBO.INTEFFECTDATE).where(AccountBO.INTCASENUM, intCaseNum)))
			)
		).setReturnNull(true)
		.find(PostJdmtIntRateBO.INTERESTRATE);
		
		if (bo == null)
			return new BigDecimal(0);
		else 
			return bo.getInterestRate();
		
		
	}
	
	public static int findOsdcEligibility(String courtType, int intCaseNum) throws Exception {
//		FieldOperationDescriptor count = new FieldOperationDescriptor(CourtProcessBO.PROCESSID, FieldOperationType.COUNT, new TypeInteger());
//		CourtProcessBO courtProcessBO = new CourtProcessBO(courtType)
//		.setFieldOperations(count)
//		.where(CourtProcessBO.PROCESSID, 300)
//		.where(CourtProcessBO.COURTTYPE, courtType)
//		.addWhereDescriptors(new WhereCustomDescriptor("(upper(t1.value) matches 'Y*')"))
//		.addWhereDescriptors(
//				new WhereSelectDescriptor(
//					new TableAndFieldDescriptor(KaseBO.LOCNCODE),
//					new KaseBO(courtType).where(KaseBO.INTCASENUM, intCaseNum), 
//					Exp.EQUALS
//				)
//		).find();
//		
//		int result = (Integer) courtProcessBO.get(count);
//		return result;
		
		CourtProcessBO courtProcessBO = new CourtProcessBO(courtType)
		.count(CourtProcessBO.PROCESSID)
		.where(CourtProcessBO.PROCESSID, 300)
		.where(CourtProcessBO.COURTTYPE, courtType)
		.where(Exp.upper(CourtProcessBO.VALUE), Exp.MATCHES, "Y*")
		.where(CourtProcessBO.LOCNCODE, Exp.EQUALS, Exp.select(new KaseBO(courtType).includeFields(KaseBO.LOCNCODE).where(KaseBO.INTCASENUM, intCaseNum)))
		.find();
		
		return courtProcessBO.getCount();
	}
	
	public static void deleteDebtCollection(String courtType, int accountNum) throws Exception {
		new DebtCollBO(courtType).where(DebtCollBO.ACCTNUM, accountNum).delete();
	}
	
	public static void insertAttorney(String courtType, AttyPartyBO vo) throws Exception {
		new AttyPartyBO(courtType)
		.setBarNum(vo.getBarNum())
		.setBarState(vo.getBarState())
		.setAttyType(vo.getAttyType())
		.setIntCaseNum(vo.getIntCaseNum())
		.setPartyNum(vo.getPartyNum())
		.setPartyCode(vo.getPartyCode())
		.insert();
	}
	
	@SuppressWarnings("unchecked")
	public static List<CasePartyDTO> getCaseParties(String courtType, int intCaseNum) throws Exception {
//		SearchDescriptor s1 = new SearchDescriptor(
//			new PartyCaseBO(courtType).where(PartyCaseBO.INTCASENUM, intCaseNum)
//		);
//
//		SearchDescriptor s2 = new SearchDescriptor(
//			new PartyBO(courtType)
//		);
//		
//		SearchDescriptor s3 = new SearchDescriptor(
//			new PartyTypeBO(courtType)
//		);
//
//		BaseSearchDispatcher searchDispatcher = new SearchDispatcher(s1, s2, s3)
//		.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
//		.addForeignKey(PartyCaseBO.PARTYCODE, PartyTypeBO.PARTYCODE)
//		
//		.setResultContainer(new CasePartyDTO())
//		.search(PartyBO.ALL_FIELDS, PartyTypeBO.PARTYCODE, PartyTypeBO.DESCR);
//
//		List<CasePartyDTO> result = (List<CasePartyDTO>) searchDispatcher.getResults();
		
		List<CasePartyDTO> results = new PartyCaseBO(courtType).where(PartyCaseBO.INTCASENUM, intCaseNum)
		.includeTables(
			new PartyBO(courtType),
			new PartyTypeBO(courtType)
			)
			.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
			.addForeignKey(PartyCaseBO.PARTYCODE, PartyTypeBO.PARTYCODE)
		.setResultContainer(new CasePartyDTO())
		.searchAndGetResults(PartyBO.ALL_FIELDS, PartyTypeBO.PARTYCODE, PartyTypeBO.DESCR);
		
		return results;
	}
	
	public static CasePartyDTO getParty(String courtType, int partyNum) throws Exception {
		
		PartyBO partyBO = new PartyBO(courtType).where(PartyBO.PARTYNUM, partyNum).find();
		PartyTypeBO partyTypeBO = new PartyTypeBO(courtType).where(PartyTypeBO.PARTYCODE, "CRE").find(); 
		CasePartyDTO dto = new CasePartyDTO();
		dto.setPartyBO(partyBO);
		dto.setPartyTypeBO(partyTypeBO);
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public static List<DcAccountDTO> findTrustAccounts(String courtType, int intCaseNum, List<Integer> accountNums) throws Exception {
//		SearchDescriptor s1 = new SearchDescriptor(
//				new DcAccountBO(courtType)
//				.where(DcAccountBO.INTCASENUM, intCaseNum)
//				.where(DcAccountBO.ACCTTYPE, "T")
//				.where(DcAccountBO.ACCTNUM, Exp.IN,  new StringArrayDescriptor(getSqlInClauseParms(accountNums))));
//	 
//
//		SearchDescriptor s2 = new SearchDescriptor(
//			new DcAcctTrustBO(courtType)
//		);
//		
//		SearchDescriptor s3 = new SearchDescriptor(
//			new PartyCaseBO(courtType)
//		);
//		
//		BaseSearchDispatcher searchDispatcher = new SearchDispatcher(s1, s2, s3)
//		.setDistinct()
//		.addForeignKey(DcAccountBO.ACCTNUM, DcAcctTrustBO.ACCTNUM)
//		.addForeignKey(DcAccountBO.INTCASENUM, PartyCaseBO.INTCASENUM)
//		.addForeignKey(PartyCaseBO.PARTYNUM, DcAcctTrustBO.PAYEEPARTYNUM)
//		.setResultContainer(new DcAccountDTO()).toString(PRINT_SQL)
//		.search(DcAccountBO.ACCTNUM, DcAccountBO.ACCTTYPE, DcAccountBO.AMTDUE, DcAccountBO.AMTPAID, 
//				DcAccountBO.AMTCREDIT, DcAccountBO.INTEFFECTDATE,
//				DcAcctTrustBO.TRUSTCODE, DcAccountBO.PARTYNUM, DcAcctTrustBO.PAYEEPARTYNUM);
//		
//		List<DcAccountDTO> result = (List<DcAccountDTO>) searchDispatcher.getResults();
//		return result;
   	
		List<DcAccountDTO> results = new DcAccountBO(courtType).setDistinct()
		.includeTables(
			new DcAcctTrustBO(courtType),
			new PartyCaseBO(courtType)
		)
		.where(DcAccountBO.INTCASENUM, intCaseNum)
		.where(DcAccountBO.ACCTTYPE, "T")
		.where(DcAccountBO.ACCTNUM, Exp.IN, accountNums)
		.where(new Expression(    					 			 
				 Exp.LEFT_PARENTHESIS,
				 DcAccountBO.AMTDUE,
				 Exp.MINUS,							 
				 DcAccountBO.AMTPAID,
				 Exp.MINUS,
				 DcAccountBO.AMTCREDIT,
				 Exp.RIGHT_PARENTHESIS,
				 Exp.GREATER_THAN,
				 0
				 ))
		.addForeignKey(DcAccountBO.ACCTNUM, DcAcctTrustBO.ACCTNUM)
		.addForeignKey(DcAccountBO.INTCASENUM, PartyCaseBO.INTCASENUM)
		.addForeignKey(PartyCaseBO.PARTYNUM, DcAcctTrustBO.PAYEEPARTYNUM)
		.setResultContainer(new DcAccountDTO())
		.toString(PRINT_SQL)
		.searchAndGetResults(
			DcAccountBO.ACCTNUM, DcAccountBO.ACCTTYPE, DcAccountBO.AMTDUE, DcAccountBO.AMTPAID, 
			DcAccountBO.AMTCREDIT, DcAccountBO.INTEFFECTDATE,
			DcAcctTrustBO.TRUSTCODE, DcAccountBO.PARTYNUM, DcAcctTrustBO.PAYEEPARTYNUM
		);
		
		return results;
	}
	
	public static int findHighestSeverity(String courtType, int intCaseNum) throws Exception {
//		FieldOperationDescriptor max = new FieldOperationDescriptor(SeverityTypeBO.SEVERITYLVL, FieldOperationType.MAX, new TypeInteger());
//		SearchDescriptor s1 = new SearchDescriptor(
//			new ChargeBO(courtType).where(ChargeBO.INTCASENUM, intCaseNum)
//		); 
//		
//		SearchDescriptor s2 = new SearchDescriptor(
//			new SeverityTypeBO(courtType).setFieldOperations(max)
//		);
//		
//		SearchDescriptor s3 = new SearchDescriptor(
//			new JdmtTypeBO(courtType).where(JdmtTypeBO.TYPE, "G")
//		);
//		
//		BaseSearchDispatcher searchDispatcher = new SearchDispatcher(s1, s2, s3)
//		.addForeignKey(ChargeBO.SEVERITY, SeverityTypeBO.SEVERITYCODE)
//		.addForeignKey(ChargeBO.JDMTCODE, JdmtTypeBO.JDMTCODE)
//		//.toString(true)
//		.search(SeverityTypeBO.NO_FIELDS);
//		
//		SeverityTypeBO vo = (SeverityTypeBO) s2.getResults().get(0);
//		int result = (Integer) vo.get(max);
//		return result;
		
		int max = (Integer) new ChargeBO(courtType).where(ChargeBO.INTCASENUM, intCaseNum)
		.includeTables(
			new SeverityTypeBO(courtType).max(SeverityTypeBO.SEVERITYLVL),
			new JdmtTypeBO(courtType).where(JdmtTypeBO.TYPE, "G")
		)
		.addForeignKey(ChargeBO.SEVERITY, SeverityTypeBO.SEVERITYCODE)
		.addForeignKey(ChargeBO.JDMTCODE, JdmtTypeBO.JDMTCODE)
		.find(SeverityTypeBO.NO_FIELDS)
		.getMax();
		
		return max;
	}
	
	public static int findCaseSupervised(String courtType, int intCaseNum) throws Exception {
//		FieldOperationDescriptor count = new FieldOperationDescriptor(CaseMeBO.MEID, FieldOperationType.COUNT, new TypeInteger());
//		SearchDescriptor s1 = new SearchDescriptor(
//			new CaseMeBO(courtType).setFieldOperations(count).where(CaseMeBO.PRINTFILENAME, "minutes").where(CaseMeBO.INTCASENUM, intCaseNum)
//		); 
//		
//		SearchDescriptor s2 = new SearchDescriptor(
//			new CaseMeValueBO(courtType).where(CaseMeValueBO.MESCREENID, "SENTPROB").where(CaseMeValueBO.MEFIELDID, "SUPERBY")
//		);
//		
//		BaseSearchDispatcher searchDispatcher = new SearchDispatcher(s1, s2)
//		.addForeignKey(CaseMeBO.MEID, CaseMeValueBO.MEID)
//		//.toString(true)
//		.search(CaseMeBO.NO_FIELDS);
//		
//		CaseMeBO vo = (CaseMeBO) s1.getResults().get(0);
//		int result = (Integer) vo.get(count);
//		
//		return result;
		
		int count = new CaseMeBO(courtType).count(CaseMeBO.MEID).where(CaseMeBO.PRINTFILENAME, "minutes").where(CaseMeBO.INTCASENUM, intCaseNum)
		.includeTables(
			new CaseMeValueBO(courtType).where(CaseMeValueBO.MESCREENID, "SENTPROB").where(CaseMeValueBO.MEFIELDID, "SUPERBY")
		)
		.addForeignKey(CaseMeBO.MEID, CaseMeValueBO.MEID)
		.find(CaseMeBO.NO_FIELDS)
		.getCount();     
		
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ChargeDTO> findChargeByCase(String courtType, int intCaseNum) throws Exception {
		
//		SearchDescriptor s1 = new SearchDescriptor(
//		new ChargeBO(courtType).where(ChargeBO.INTCASENUM, intCaseNum).where(ChargeBO.PARTYCODE, "DEF")
//		); 
//
//		SearchDescriptor s2 = new SearchDescriptor(
//		new PleaTypeBO(courtType)
//		.as(PleaTypeBO.DESCR, "pleatype_descr")
//		);
//
//		SearchDescriptor s3 = new SearchDescriptor(
//		new JdmtTypeBO(courtType).where(JdmtTypeBO.TYPE, "G")
//		.as(JdmtTypeBO.DESCR, "jdmttype_descr")
//		.as(JdmtTypeBO.CATEGORY, "jdmttype_category")
//		); 
//
//		SearchDescriptor s4 = new SearchDescriptor(
//		new OffenseBO(courtType).setOuter()
//		.as(OffenseBO.DESCR, "offense_descr")
//		.as(OffenseBO.CATEGORY, "offense_category")
//		);
//
//		BaseSearchDispatcher searchDispatcher = new SearchDispatcher(s1, s2, s3, s4)
//		.addForeignKey(ChargeBO.PLEACODE, PleaTypeBO.PLEACODE)
//		.addForeignKey(ChargeBO.JDMTCODE, JdmtTypeBO.JDMTCODE)
//		.addForeignKey(ChargeBO.OFFSVIOLCODE, OffenseBO.OFFSVIOLCODE)
//		.addForeignKey(ChargeBO.OFFSEFFECTDATE, OffenseBO.LASTEFFECTDATE)
//		.setResultContainer(new ChargeDTO())
//		//.toString(true)
//		.search();
//		
//		return (List<ChargeDTO>) searchDispatcher.getResults();
		
		return new ChargeBO(courtType).where(ChargeBO.INTCASENUM, intCaseNum).where(ChargeBO.PARTYCODE, "DEF")
		.includeTables(
			new PleaTypeBO(courtType).setOuter().as(PleaTypeBO.DESCR, "pleatype_descr"),
			new JdmtTypeBO(courtType).where(JdmtTypeBO.TYPE, "G").as(JdmtTypeBO.DESCR, "jdmttype_descr").as(JdmtTypeBO.CATEGORY, "jdmttype_category"),
			new OffenseBO(courtType).setOuter().as(OffenseBO.DESCR, "offense_descr").as(OffenseBO.CATEGORY, "offense_category")
		)
		.addForeignKey(ChargeBO.PLEACODE, PleaTypeBO.PLEACODE)
		.addForeignKey(ChargeBO.JDMTCODE, JdmtTypeBO.JDMTCODE)
		.addForeignKey(ChargeBO.OFFSVIOLCODE, OffenseBO.OFFSVIOLCODE)
		.addForeignKey(ChargeBO.OFFSEFFECTDATE, OffenseBO.LASTEFFECTDATE)
		.setResultContainer(new ChargeDTO())
		.searchAndGetResults();  
			
	}
	
	public static List<AttorneyBO> findDefendantAttorneys(String courtType, int intCaseNum) throws Exception {
//		SearchDescriptor s1 = new SearchDescriptor(
//			new AttorneyBO(courtType)
//		); 
//		
//		SearchDescriptor s2 = new SearchDescriptor(
//			new AttyPartyBO(courtType).where(AttyPartyBO.INTCASENUM, intCaseNum).where(AttyPartyBO.PARTYCODE, "DEF")
//		);
//		
//		BaseSearchDispatcher searchDispatcher = new SearchDispatcher(s1, s2)
//		.addForeignKey(AttorneyBO.BARNUM, AttyPartyBO.BARNUM)
//		.addForeignKey(AttorneyBO.BARSTATE, AttyPartyBO.BARSTATE)
//		//.toString(true)
//		.search();
//		
//		List<AttorneyBO> result = (List<AttorneyBO>) s1.getResults();
		
		List<AttorneyBO> results = new AttorneyBO(courtType)
		.includeTables(
			new AttyPartyBO(courtType).where(AttyPartyBO.INTCASENUM, intCaseNum).where(AttyPartyBO.PARTYCODE, "DEF")
		)
		.addForeignKey(AttorneyBO.BARNUM, AttyPartyBO.BARNUM)
		.addForeignKey(AttorneyBO.BARSTATE, AttyPartyBO.BARSTATE)
		.search();	
		
		return results;
	}
	
	public static boolean attorneyExists(String courtType, int barNum, String barState, int intCaseNum, int partyNum, String partyCode) throws Exception {
		
		int count = new AttyPartyBO(courtType)
		.count(AttyPartyBO.BARNUM)
		.where(AttyPartyBO.BARNUM, barNum)
		.where(AttyPartyBO.BARSTATE, barState)
		.where(AttyPartyBO.INTCASENUM, intCaseNum)
		.where(AttyPartyBO.PARTYNUM, partyNum)
		.where(AttyPartyBO.PARTYCODE, partyCode)
		.find()
		.getCount();
		
		return count == 0 ? false : true;
	}
	

    /**
	 * Find case header info
	 * @param intCaseNum
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static OsdcXmlHeaderDTO  findCaseMaster(String courtType, int intCaseNum) throws Exception { 
		OsdcXmlHeaderDTO osdcXmlHeaderDTO = null;
		 
		try{ 
//			KaseBO kaseBO=new KaseBO(courtType).where(KaseBO.INTCASENUM, intCaseNum);
//	        CrimCaseBO crimCaseBO=new CrimCaseBO(courtType).setOuter();
//	        PartyBO partyBO=new PartyBO(courtType);
//	        PartyCaseBO partyCaseBO=new PartyCaseBO(courtType).addWhereDescriptors(new WhereCustomDescriptor("t4.party_code in ('DEF', 'RES')"));
//	        CaseTypeBO caseTypeBO=new CaseTypeBO(courtType);
//	        
//	        SearchDescriptor kaseSD=new SearchDescriptor(kaseBO);
//	        SearchDescriptor crimCaseSD=new SearchDescriptor(crimCaseBO);
//	        SearchDescriptor partySD=new SearchDescriptor(partyBO);
//	        SearchDescriptor partyCaseSD=new SearchDescriptor(partyCaseBO);
//	        SearchDescriptor caseTypeSD=new SearchDescriptor(caseTypeBO);
//			
//	        BaseSearchDispatcher searchFactory=new SearchDispatcher(kaseSD,crimCaseSD,partySD,partyCaseSD,caseTypeSD)
//	       .addForeignKey(KaseBO.INTCASENUM, PartyCaseBO.INTCASENUM)
//	       .addForeignKey(KaseBO.INTCASENUM, CrimCaseBO.INTCASENUM)
//	       .addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE)
//	       .addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
//	       .setResultContainer(new OsdcXmlHeaderDTO())
//	       .toString(PRINT_SQL)
//	       .search(KaseBO.INTCASENUM,KaseBO.LOCNCODE,KaseBO.COURTTYPE,KaseBO.CASENUM,KaseBO.CASETYPE,KaseBO.LOCALDEBTCOLL,KaseBO.DEBTCOLLECTION,CaseTypeBO.DESCR,
//	      		 PartyCaseBO.PARTYCODE,PartyBO.PARTYNUM,
//	      		 PartyBO.LASTNAME,
//	      		 PartyBO.FIRSTNAME,
//	      		 PartyBO.ADDRESS1,
//	      		 PartyBO.ADDRESS2,
//	      		 PartyBO.CITY,
//	      		 PartyBO.STATECODE,
//	      		 PartyBO.ZIPCODE,
//	      		 PartyBO.SSN,
//	      		 PartyBO.HOMEPHONE,
//	      		 PartyBO.BUSPHONE,
//	      		 PartyBO.BIRTHDATE,
//	      		 PartyBO.GENDER,
//	      		 PartyBO.DRLICNUM,
//	      		 PartyBO.DRLICSTATE,
//	      		 PartyBO.EMPLOYERNAME,
//	      		 PartyBO.EMPADDR,
//	      		 KaseBO.ASSNJUDGEID,CrimCaseBO.PROSECAGENCY);
//			 
//			 List<OsdcXmlHeaderDTO>  results= (List<OsdcXmlHeaderDTO>) searchFactory.getResults();    	
			 
			
    		List<OsdcXmlHeaderDTO> results = new KaseBO(courtType).where(KaseBO.INTCASENUM, intCaseNum)
    		.includeTables(
    			new CrimCaseBO(courtType).setOuter(),
    			new PartyBO(courtType),
    			new PartyCaseBO(courtType).where(PartyCaseBO.PARTYCODE, Exp.IN, new StringArrayDescriptor("DEF", "RES")),
    			new CaseTypeBO(courtType)
    		)
	       	.addForeignKey(KaseBO.INTCASENUM, PartyCaseBO.INTCASENUM)
	       	.addForeignKey(KaseBO.INTCASENUM, CrimCaseBO.INTCASENUM)
	       	.addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE)
	       	.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
	       	.setResultContainer(new OsdcXmlHeaderDTO())
	       	.toString(PRINT_SQL)
	       	.searchAndGetResults(
	       		KaseBO.INTCASENUM,KaseBO.LOCNCODE,KaseBO.COURTTYPE,KaseBO.CASENUM,KaseBO.CASETYPE,KaseBO.LOCALDEBTCOLL,KaseBO.DEBTCOLLECTION,CaseTypeBO.DESCR,
	      		PartyCaseBO.PARTYCODE,PartyBO.PARTYNUM, PartyBO.LASTNAME, PartyBO.FIRSTNAME, PartyBO.ADDRESS1, PartyBO.ADDRESS2, PartyBO.CITY, PartyBO.STATECODE,
	      		PartyBO.ZIPCODE, PartyBO.SSN, PartyBO.HOMEPHONE, PartyBO.BUSPHONE, PartyBO.BIRTHDATE, PartyBO.GENDER, PartyBO.DRLICNUM, PartyBO.DRLICSTATE,
	      		PartyBO.EMPLOYERNAME, PartyBO.EMPADDR, KaseBO.ASSNJUDGEID, CrimCaseBO.PROSECAGENCY
	      	);
    		
			if (results != null && results.size() > 0) {
				osdcXmlHeaderDTO=results.get(0);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return osdcXmlHeaderDTO;
	}
	
	/**
	 * Find Fine & Fee accounts
	 * @param intCaseNum
	 * @param accountNums
	 * @return
	 * @throws Exception
	 */
	public static List<DcAccountBO> findFFAccounts(String courtType,int intCaseNum, List<Integer> accountNums) throws Exception {
	 
        try {
        	
//           return  new DcAccountBO(courtType).where(DcAccountBO.INTCASENUM, intCaseNum).where(new FindDescriptor(DcAccountBO.ACCTNUM,Exp.IN,new StringArrayDescriptor(getSqlInClauseParms(accountNums))))
//            .where(new FindDescriptor(DcAccountBO.ACCTTYPE,Exp.IN,new StringArrayDescriptor("I","F"))).toString(PRINT_SQL).search(DcAccountBO.ACCTNUM,DcAccountBO.ACCTTYPE,DcAccountBO.AMTDUE,DcAccountBO.AMTPAID,
//            		DcAccountBO.AMTCREDIT,DcAccountBO.PARTYNUM,DcAccountBO.INTEFFECTDATE);
        	 
    		return new DcAccountBO(courtType)
    		.where(DcAccountBO.INTCASENUM, intCaseNum)
    		.where(DcAccountBO.ACCTNUM, Exp.IN, accountNums)
            .where(DcAccountBO.ACCTTYPE, Exp.IN, new StringArrayDescriptor("I","F"))
            .where(new Expression(    					 			 
	   				 Exp.LEFT_PARENTHESIS,
					 DcAccountBO.AMTDUE,
					 Exp.MINUS,							 
					 DcAccountBO.AMTPAID,
					 Exp.MINUS,
					 DcAccountBO.AMTCREDIT,
					 Exp.RIGHT_PARENTHESIS,
					 Exp.GREATER_THAN,
					 0
					 ))
            .toString(PRINT_SQL)
            .search(
            	DcAccountBO.ACCTNUM, DcAccountBO.ACCTTYPE, DcAccountBO.AMTDUE, DcAccountBO.AMTPAID,
            	DcAccountBO.AMTCREDIT,DcAccountBO.PARTYNUM,DcAccountBO.INTEFFECTDATE
            );
 
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append(EXCEP_OSDC_XML_XO+" method findFFAccounts");
            sb.append(e.getMessage());
            logger.error(sb.toString());
            throw new SystemException(e, EXCEP_OSDC_XML_XO);
        }  
       
    }
	
	@SuppressWarnings("unchecked")
	public static List<JudgmentPartyDTO> findJudgmentCreditors(String courtType, int intCaseNum, int jdmtSeq, int detlSeq) throws Exception{
		
//		SearchDescriptor s1 = new SearchDescriptor(
//			new JudgmentCreditorBO(courtType)
//			.where(JudgmentCreditorBO.INTCASENUM, intCaseNum)
//			.where(JudgmentCreditorBO.JDMTSEQ, jdmtSeq).where(JudgmentCreditorBO.DETLSEQ, detlSeq)
//		); 
//		
//		SearchDescriptor s2 = new SearchDescriptor(
//			new PartyBO(courtType)
//		);
//		
//		BaseSearchDispatcher searchDispatcher = new SearchDispatcher(s1, s2)
//		.addForeignKey(JudgmentCreditorBO.PARTYNUM, PartyBO.PARTYNUM)
//		.setResultContainer(new JudgmentPartyDTO())
//		//.toString(true)
//		.search();
//		
//		List<JudgmentPartyDTO> result = (List<JudgmentPartyDTO>) searchDispatcher.getResults();
		
		List<JudgmentPartyDTO> results = new JudgmentCreditorBO(courtType)
		.includeTables(new PartyBO(courtType))
		.where(JudgmentCreditorBO.INTCASENUM, intCaseNum)
		.where(JudgmentCreditorBO.JDMTSEQ, jdmtSeq)
		.where(JudgmentCreditorBO.DETLSEQ, detlSeq)
		.addForeignKey(JudgmentCreditorBO.PARTYNUM, PartyBO.PARTYNUM)
		.setResultContainer(new JudgmentPartyDTO())
		.searchAndGetResults();
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public static List<JudgmentPartyDTO> findJudgmentDebtors(String courtType, int intCaseNum, int jdmtSeq, int detlSeq) throws Exception{
		
//		SearchDescriptor s1 = new SearchDescriptor(
//			new JudgmentDebtorBO(courtType)
//			.where(JudgmentDebtorBO.INTCASENUM, intCaseNum)
//			.where(JudgmentDebtorBO.JDMTSEQ, jdmtSeq).where(JudgmentDebtorBO.DETLSEQ, detlSeq)
//		); 
//		
//		SearchDescriptor s2 = new SearchDescriptor(
//			new PartyBO(courtType)
//		);
//		
//		BaseSearchDispatcher searchDispatcher = new SearchDispatcher(s1, s2)
//		.addForeignKey(JudgmentDebtorBO.PARTYNUM, PartyBO.PARTYNUM)
//		.setResultContainer(new JudgmentPartyDTO())
//		//.toString(true)
//		.search();
//		
//		List<JudgmentPartyDTO> result = (List<JudgmentPartyDTO>) searchDispatcher.getResults();
		
		List<JudgmentPartyDTO> results = new JudgmentDebtorBO(courtType)
		.includeTables(new PartyBO(courtType))
		.where(JudgmentDebtorBO.INTCASENUM, intCaseNum)
		.where(JudgmentDebtorBO.JDMTSEQ, jdmtSeq)
		.where(JudgmentDebtorBO.DETLSEQ, detlSeq)
		.addForeignKey(JudgmentDebtorBO.PARTYNUM, PartyBO.PARTYNUM)
		.setResultContainer(new JudgmentPartyDTO())
		.searchAndGetResults();
		
		return results;
	}
	
	/**
	 * Find account distributions
	 * @param accountNum
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<DcAccountDistributionDTO> findAccountDistributions(String courtType,int accountNum) throws Exception {

		List<DcAccountDistributionDTO> results = new ArrayList<DcAccountDistributionDTO>();
        try {
        	
//    		SearchDescriptor dDcAcctDistSD = new SearchDescriptor(
//    			new DcAcctDistBO(courtType).where(DcAcctDistBO.ACCTNUM, accountNum)
//    		);
//
//    		SearchDescriptor distTypeSD = new SearchDescriptor( new DistTypeBO(courtType));
//    			
//    		BaseSearchDispatcher searchDispatcher = new SearchDispatcher(dDcAcctDistSD, distTypeSD)
//    			.addForeignKey(DcAcctDistBO.DISTCODE, DistTypeBO.DISTCODE)
//    			.setResultContainer(new DcAccountDistributionDTO())
//    			//.toString(PRINT_SQL)
//    			.search(
//    					DcAcctDistBO.ACCTNUM, 
//    					DcAcctDistBO.DISTCODE,
//    					DistTypeBO.DESCR,
//    					DcAcctDistBO.AMTDUE,
//    					DcAcctDistBO.AMTPAID,
//    					DcAcctDistBO.AMTCREDIT
//    			);
        	
        	results = new DcAcctDistBO(courtType)
    		.includeTables(new DistTypeBO(courtType))
    		.where(DcAcctDistBO.ACCTNUM, accountNum)
   			.addForeignKey(DcAcctDistBO.DISTCODE, DistTypeBO.DISTCODE)
   			.setResultContainer(new DcAccountDistributionDTO())
   			.searchAndGetResults(
    			DcAcctDistBO.ACCTNUM, 
    			DcAcctDistBO.DISTCODE,
    			DistTypeBO.DESCR,
    			DcAcctDistBO.AMTDUE,
    			DcAcctDistBO.AMTPAID,
    			DcAcctDistBO.AMTCREDIT
    		);
    			
   			return results;    				
    	  
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append(EXCEP_OSDC_XML_XO+" method findAccountDistributions");
            sb.append(e.getMessage());
            logger.error(sb.toString());
            throw new SystemException(e, EXCEP_OSDC_XML_XO);
        }  
    }
	
	//This method does not touch case_party table. It should have already been added
	//We assume that judgment creation has taken care of it already
	public static int getPayeePartyNumForFineAndFee(String courtType, int intCaseNum) throws Exception{
		int partyNum = 0;
		if ("J".equals(courtType)) {
			KaseBO kaseBO = new KaseBO(courtType).where(KaseBO.INTCASENUM, intCaseNum).find();
			ProfileBO profileBO = new ProfileBO(courtType).where(ProfileBO.LOCNCODE, kaseBO.getLocnCode()).where(ProfileBO.COURTTYPE, courtType).find();
			partyNum = (Integer) new PartyBO(courtType).min(PartyBO.PARTYNUM).where(PartyBO.LASTNAME, profileBO.getCourtTitle()).find().getMin();
		}
		return partyNum;
	}
	
//	private static List<String> getSqlInClauseParms(List<Integer> list){
//		
//		List<String> inParamValues=new ArrayList<String>();
//		StringBuffer result = new StringBuffer();
//		for (int num:list){
//			inParamValues.add(Integer.toString(num));
//		}
//		 
//		return inParamValues;
//	}
	
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
	
	public static void addCaseHistory(String courtType, int intCaseNum, String histNote, int userId) throws Exception{
        //If userId is passed in, we will use it. For example, when recalling OSDC from servlet.
		//If user Id is missing, we will use the most recent one with "R". For example, when running in batch
		//If still not present, use dpx.
		if (userId == 0) {
			userId = getUserId(courtType,  intCaseNum);
			//Get dpx
			if (userId == 0){
				KaseBO kaseBO = new KaseBO(courtType).where(KaseBO.INTCASENUM, intCaseNum).find(KaseBO.LOCNCODE);
				PersonnelBO personnelBO = PersonnelXO.getPersonnelByLogNameLocnCodeCourtType("dpx", kaseBO.getLocnCode(), courtType);
				userId = personnelBO.getUseridSrl();
			}
		}
		//Enter a record
		CaseHistBO caseHistBo = new CaseHistBO(courtType);
		caseHistBo.setIntCaseNum(intCaseNum);
		caseHistBo.setClerkId(userId);
		caseHistBo.setFuncId("HISTNOTE");
		caseHistBo.setRemovedFlag("N");	
		caseHistBo.setChText(histNote);
		caseHistBo.setCreateDatetime(Calendar.getInstance().getTime());
		caseHistBo.setEntryDatetime(caseHistBo.getCreateDatetime());
		
		caseHistBo.insert();
	}
		
	
	public static String getAccountDetailType(String courtType, String acctTypeCode, int acctNum) throws Exception{
		String retVal = "";
		if ("I".equals(acctTypeCode))
			retVal = "Fine";
		else if ("T".equals(acctTypeCode)) {
			
//			SearchDescriptor acctTrustSD = new SearchDescriptor(
//	    			new AcctTrustBO(courtType).where(AcctTrustBO.ACCTNUM, acctNum)
//	    	);
//
//	    	SearchDescriptor trustTypeSD = new SearchDescriptor( new TrustTypeBO(courtType));
//	    			
//	    	BaseSearchDispatcher searchDispatcher = new SearchDispatcher(acctTrustSD, trustTypeSD)
//    			.addForeignKey(AcctTrustBO.TRUSTCODE, TrustTypeBO.TRUSTCODE)
//    			.search(
//    					TrustTypeBO.DESCR 
//    			);
//	    			
//	    	
//	    	List<TrustTypeBO> results = (List<TrustTypeBO>) trustTypeSD.getResults();
//	    	retVal = results.get(0).getDescr();
			
    		retVal = (String) new AcctTrustBO(courtType)
    		.includeTables(new TrustTypeBO(courtType))
    		.where(AcctTrustBO.ACCTNUM, acctNum)
	    	.addForeignKey(AcctTrustBO.TRUSTCODE, TrustTypeBO.TRUSTCODE)
			.find(TrustTypeBO.DESCR)
			.get(TrustTypeBO.DESCR);
	    		
		} else if ("F".equals(acctTypeCode)) {
			// Get the acct_fee information.  Added 06/24/2019  Selart  Piv #166877621
			AcctFeeBO acctFeeBo = new AcctFeeBO(courtType)
			.where(AcctFeeBO.ACCTNUM, acctNum)
			.find();
			
			retVal = (String) new FeeTypeBO(courtType).includeFields(FeeTypeBO.DESCR)
			.where(FeeTypeBO.FEECODE, acctFeeBo.getFeeCode())
			.where(FeeTypeBO.LASTEFFECTDATE,
					Exp.in(new FeeTypeBO(courtType).min(FeeTypeBO.LASTEFFECTDATE)
							   .where(FeeTypeBO.LASTEFFECTDATE, Exp.GREATER_THAN_OR_EQUAL_TO, acctFeeBo.getFeeEffectDate())
							   .where(FeeTypeBO.FEECODE, acctFeeBo.getFeeCode())
				)
			)
			.toString(PRINT_SQL)
   			.find(FeeTypeBO.DESCR)
   			.get(FeeTypeBO.DESCR);
		}
		
		return retVal;
	}
	
	/*
	 * Only needed for batch
	 */
	public static int getUserId(String courtType, int intCaseNum) throws Exception{
		int userId = 0;
		//select osdc_create_userid from osdc_acct_history where osdc_acct_seq in (select max(t1.osdc_acct_seq) as max 
		//from osdc_acct_history t1,account t2 
		//where t1.osdc_status='R' and t2.int_case_num=11460996 and t1.acct_num=t2.acct_num);
		OsdcAcctHistoryBO bo = new OsdcAcctHistoryBO(courtType).includeFields(OsdcAcctHistoryBO.OSDCCREATEUSERID)
		.where(OsdcAcctHistoryBO.OSDCACCTSEQ, Exp.in(
			new OsdcAcctHistoryBO(courtType).max(OsdcAcctHistoryBO.OSDCACCTSEQ)
			.includeTables(new AccountBO(courtType).includeFields(AccountBO.NO_FIELDS))
			.addForeignKey(OsdcAcctHistoryBO.ACCTNUM, AccountBO.ACCTNUM)
			.where(OsdcAcctHistoryBO.OSDCSTATUS, "R")
			.where(AccountBO.INTCASENUM, intCaseNum)
		))
		.setReturnNull(true).find();
		
		if (bo != null)
			userId = bo.getOsdcCreateUserid();
		
	    return userId;
	}
	
	
	public static String getPrisonFlag(String courtType, int intCaseNum) throws Exception{
		String retVal = "N";
		List<SentenceBO> list = new SentenceBO(courtType).as("t1")
			.where(SentenceBO.INTCASENUM, intCaseNum)
			.where(SentenceBO.SENTCODE,	Exp.EQUALS,	"PRSNSENT")
			.where(Exp.NOT, Exp.exists(
					new SentenceBO(courtType)
					.includeFields(new FieldConstant("1"))
					.where(SentenceBO.INTCASENUM, Exp.EQUALS, Exp.literal("t1.int_case_num"))
					.where(SentenceBO.SENTCODE, Exp.EQUALS, "PRSNSUSP")
				)
			)
			.search();
		
		if (list != null && list.size() >0)
			retVal = "Y";
		else {
			List<CaseMeValueBO> caseMeValues = new CaseMeValueBO(courtType)
				.where(CaseMeValueBO.MEFIELDID, "ORIGPRSN")
				.where(CaseMeValueBO.MEID, Exp.in(new CaseMeBO(courtType).includeFields(CaseMeBO.MEID).where(CaseMeBO.INTCASENUM, intCaseNum)))
				.search();
			
			if (caseMeValues != null && caseMeValues.size() > 0)
				retVal = "Y";
			
		} 
		
		return retVal;
	}

}
