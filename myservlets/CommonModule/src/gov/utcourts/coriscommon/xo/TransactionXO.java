package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.journal.JournalBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.trans.TransBO;
import gov.utcourts.coriscommon.dataaccess.transdetl.TransDetlBO;
import gov.utcourts.coriscommon.dataaccess.transdist.TransDistBO;
import gov.utcourts.coriscommon.dto.DebtPaymentDTO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.util.List;

import org.apache.log4j.Logger;

public class TransactionXO implements BaseConstants {
	private static final Logger logger = Logger.getLogger(TransactionXO.class);
	public static int PRINT_SQL = RUN;
	
	
	public static void main(String args[]) throws Exception {
		DatabaseConnection.setUseJdbc();
		searchDebtPayments("D",7996289);
	}
	
	@SuppressWarnings("unchecked")
	public static List<DebtPaymentDTO> searchDebtPayments(String courtType, int intCaseNum) throws Exception {	 
		try {

//			AccountBO accountBO = new AccountBO(courtType)
//					.as("account1")
//					.includeFields(AccountBO.ACCTNUM, AccountBO.ACCTTYPE, AccountBO.AMTDUE)
//					.where(AccountBO.INTCASENUM, intCaseNum)
//					.as(AccountBO.ACCTNUM, "accountNumber")
//					.as(AccountBO.ACCTTYPE, "accountType")
//					.as(AccountBO.AMTDUE, "amountDue")
//					.where(new FindDescriptor(AccountBO.ACCTTYPE,Exp.IN,new StringArrayDescriptor("F","I","T")))
//					.setDistinct().orderBy(AccountBO.ACCTNUM);
//
//			JournalBO journalBO = new JournalBO(courtType)
//					.as("journal1")
//					.as(JournalBO.JOURNALNUM, "journalNum")
//					.includeFields(JournalBO.JOURNALNUM);
//
//			TransDetlBO transDetlBO = new TransDetlBO(courtType)
//					.as("transDetl1")
//					.as(TransDetlBO.TENDERTYPE, "tenderType")
//					.as(TransDetlBO.REVNAMT, "revenueAmount")
//					.as(TransDetlBO.TRUSTAMT, "trustAmount")
//					.includeFields(TransDetlBO.TENDERTYPE, TransDetlBO.REVNAMT, TransDetlBO.TRUSTAMT);
//
//			TransDistBO transDistBO = new TransDistBO(courtType)
//					.as("transDist1")
//					.as(TransDistBO.TRANSNUM, "transNum")
//					.as(TransDistBO.AMTPAID, "amountPaid")
//					.as(TransDistBO.AMTCREDIT, "amountCredit")
//					.includeFields(TransDistBO.TRANSNUM, TransDistBO.AMTPAID, TransDistBO.AMTCREDIT);
//
//			KaseBO kaseBO = new KaseBO(courtType).as("kase1")
//					.as(KaseBO.CASENUM, "caseNumber")
//					.includeFields(KaseBO.CASENUM);
//
//			CaseTypeBO caseTypeBO = new CaseTypeBO(courtType)
//					.as("caseType1")
//					.as(CaseTypeBO.DESCR, "caseTypeDescr")
//					.includeFields(CaseTypeBO.DESCR);
//
//			TransBO transBO = new TransBO(courtType)
//					.as("trans1")
//					.where(
//						new FindDescriptor("(", TransBO.OUTTYPE, Exp.IS_NULL, "", ""),
//						new FindDescriptor(null, TransBO.OUTTYPE, Exp.EQUALS, "R", ")").setConditionalType(ConditionalType.OR)
//					)
//					.includeFields(TransBO.TRANSDATETIME, TransBO.OUTTYPE)
//					.orderBy(TransBO.TRANSDATETIME)
//					.as(TransBO.TRANSDATETIME, "transDateTime")
//					.as(TransBO.OUTTYPE, "outType");
//
//			PartyBO partyBO1 = new PartyBO(courtType).as("party1")
//				.includeFields(
//					new FieldConstant("party1.last_name", "payerLastName"),
//					new FieldConstant("party1.first_name", "payerFirstName")
//				)
//				.setOuter();
//
//			PartyBO partyBO2 = new PartyBO("D").as("party2")
//				.includeFields(
//					new FieldConstant("party2.last_name", "payeeLastName"),
//					new FieldConstant("party2.first_name", "payeeFirstName")
//				)
//				.setOuter();
//
//			SearchDescriptor accountSD = new SearchDescriptor(accountBO);
//			SearchDescriptor journalSD = new SearchDescriptor(journalBO);
//			SearchDescriptor transDetlSD = new SearchDescriptor(transDetlBO);
//			SearchDescriptor transDistSD = new SearchDescriptor(transDistBO);
//			SearchDescriptor kaseSD = new SearchDescriptor(kaseBO);
//			SearchDescriptor caseTypeSD = new SearchDescriptor(caseTypeBO);
//			SearchDescriptor transSD = new SearchDescriptor(transBO);
//			SearchDescriptor partySD1 = new SearchDescriptor(partyBO1);
//			SearchDescriptor partySD2 = new SearchDescriptor(partyBO2);
//
//			BaseSearchDispatcher searchDispatcher = new SearchDispatcher(accountSD,
//					journalSD, transDetlSD, transDistSD, transSD, kaseSD,
//					caseTypeSD, partySD1, partySD2)
//					.addForeignKey(
//							new TableAndFieldDescriptor(partyBO2.getTableAlias(), PartyBO.PARTYNUM),
//							new TableAndFieldDescriptor(transBO.getTableAlias(), TransBO.PAYORPARTYNUM))
//					.addForeignKey(
//							new TableAndFieldDescriptor(partyBO1.getTableAlias(), PartyBO.PARTYNUM),
//							new TableAndFieldDescriptor(accountBO.getTableAlias(), AccountBO.PARTYNUM))
//					.addForeignKey(JournalBO.INTJOURNALNUM,	TransDetlBO.INTJOURNALNUM)
//					.addForeignKey(AccountBO.ACCTNUM, TransDistBO.ACCTNUM)
//					.addForeignKey(TransDistBO.INTJOURNALNUM, TransDetlBO.INTJOURNALNUM)
//					.addForeignKey(TransDistBO.TRANSNUM, TransDetlBO.TRANSNUM)
//					.addForeignKey(KaseBO.INTCASENUM, AccountBO.INTCASENUM)
//					.addForeignKey(CaseTypeBO.CASETYPE,	KaseBO.CASETYPE)
//					.addForeignKey(TransDetlBO.INTJOURNALNUM, TransBO.INTJOURNALNUM)
//					.addForeignKey(TransDetlBO.TRANSNUM, TransBO.TRANSNUM)
//					.setResultContainer(new DebtPaymentDTO())
//					.toString(PRINT_SQL).search();
//			
//			List<DebtPaymentDTO> results = (List<DebtPaymentDTO>) searchDispatcher.getResults();			
//		 
//			return results;

			String party1alias = "party1";
    		String party2alias = "party2";
    		String accountAlias = "account1";
    		String transAlias = "trans1";

    		return new AccountBO(courtType).as(accountAlias).setDistinct()
			.includeFields(AccountBO.ACCTNUM.as("accountNumber"), AccountBO.ACCTTYPE.as("accountType"), AccountBO.AMTDUE.as("amountDue"))
			.where(AccountBO.INTCASENUM, intCaseNum)
			.where(AccountBO.ACCTTYPE, Exp.IN, new StringArrayDescriptor("F","I","T"))
			.orderBy(AccountBO.ACCTNUM)
			.includeTables(
				new JournalBO(courtType).as("journal1").includeFields(JournalBO.JOURNALNUM.as("journalNum")),
				new TransDetlBO(courtType).as("transDetl1").includeFields(TransDetlBO.TENDERTYPE.as("tenderType"), TransDetlBO.REVNAMT.as("revenueAmount"), TransDetlBO.TRUSTAMT.as("trustAmount")),
				new TransDistBO(courtType).as("transDist1").includeFields(TransDistBO.TRANSNUM.as("transNum"), TransDistBO.AMTPAID.as("amountPaid"), TransDistBO.AMTCREDIT.as("amountCredit")),
				new KaseBO(courtType).as("kase1").includeFields(KaseBO.CASENUM.as("caseNumber")),
				new CaseTypeBO(courtType).as("caseType1").includeFields(CaseTypeBO.DESCR.as("caseTypeDescr")),
				new TransBO(courtType).as(transAlias).includeFields(TransBO.TRANSDATETIME.as("transDateTime"), TransBO.OUTTYPE.as("outType")).orderBy(TransBO.TRANSDATETIME)
				.where(
					Exp.LEFT_PARENTHESIS, 
						TransBO.OUTTYPE, Exp.IS_NULL,
						Exp.OR,
						TransBO.OUTTYPE, Exp.EQUALS, "R",
					Exp.RIGHT_PARENTHESIS
				),
				new PartyBO(courtType).as(party1alias).setOuter().includeFields(new FieldConstant("party1.last_name", "payerLastName"), new FieldConstant("party1.first_name", "payerFirstName")),
				new PartyBO(courtType).as(party2alias).setOuter().includeFields(new FieldConstant("party2.last_name", "payeeLastName"), new FieldConstant("party2.first_name", "payeeFirstName"))
			)
			.addForeignKey(
				new TableAndFieldDescriptor(party2alias, PartyBO.PARTYNUM),
				new TableAndFieldDescriptor(transAlias, TransBO.PAYORPARTYNUM)
			)
			.addForeignKey(
				new TableAndFieldDescriptor(party1alias, PartyBO.PARTYNUM),
				new TableAndFieldDescriptor(accountAlias, AccountBO.PARTYNUM)
			)
			.addForeignKey(JournalBO.INTJOURNALNUM,	TransDetlBO.INTJOURNALNUM)
			.addForeignKey(AccountBO.ACCTNUM, TransDistBO.ACCTNUM)
			.addForeignKey(TransDistBO.INTJOURNALNUM, TransDetlBO.INTJOURNALNUM)
			.addForeignKey(TransDistBO.TRANSNUM, TransDetlBO.TRANSNUM)
			.addForeignKey(KaseBO.INTCASENUM, AccountBO.INTCASENUM)
			.addForeignKey(CaseTypeBO.CASETYPE,	KaseBO.CASETYPE)
			.addForeignKey(TransDetlBO.INTJOURNALNUM, TransBO.INTJOURNALNUM)
			.addForeignKey(TransDetlBO.TRANSNUM, TransBO.TRANSNUM)
			.setResultContainer(new DebtPaymentDTO())
			.toString(PRINT_SQL)
			.searchAndGetResults();
    		
		} catch (Exception e) {
			logger.info(" TransactionXOsearchDebtPayments() [Exception]", e);
			throw e;
		}
	}
	
}
