package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.xo.AccountXO;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

public class OsdcTest {
	
	public static void main(String[] args) {
//		DebtCollectionSearchDTO mySearch = new DebtCollectionSearchDTO();
//		mySearch.setCourtType("D");
//		mySearch.setLocationCode("1868");
//		mySearch.setAccountTypes("'T','I','F'");
//		mySearch.setCountLimit(10);
//		mySearch.setAmountLimit(1.01);
//		mySearch.setOverDueLimit(30);
//		mySearch.setSortType(1);
//		mySearch.setIntCaseNum(7996289);
//		int count = 0;
//		
//		List<DebtCollectionCaseDTO> debtCollections = new ArrayList<DebtCollectionCaseDTO>();
//		List<DebtCollectionAccountDTO> accounts = new ArrayList<DebtCollectionAccountDTO>();
		DatabaseConnection.setUseJdbc();
		try {
//			int workPlease = AccountXO.getCountByAcctNumAndAcctFeeCode("D", 99, "OP");
//			System.out.println(workPlease);
//			debtCollections = CrimCaseBO.findDebtCollectionEligable(mySearch, true);
			
//			for (DebtCollectionCaseDTO singleCase : debtCollections) {
//				System.out.println("Case #".concat(singleCase.getCaseNumber()));
//				System.out.println("Amount: $".concat(Double.toString(singleCase.getBalance())));
//				accounts = AccountBO.findDebtCaseAccounts(singleCase.getIntCaseNumber(), mySearch.getCourtType(), true);
//				count = 0;
//				for (DebtCollectionAccountDTO account : accounts) {
//					count ++;
//					System.out.println(Integer.toString(count).concat(" - $").concat(Double.toString(account.getAmountDue() - account.getAmountPaid() - account.getAmountCredit())));
//				}
//			}
			
			// Test set-unset local debt collection.
//			int result = KaseBO.setLocalDebtCollectionFlag(4249, "D", true, true);
//			System.out.println("Number of rows updated = ".concat(Integer.toString(result)));
			
			// Test set osdc notes.
//			accounts = AccountBO.findDebtCaseAccounts(4249, "D", true);
//			for (DebtCollectionAccountDTO account : accounts) {
//				result = AccountBO.saveOsdcNoteByAccount(account.getAccountNumber(), 199, "This is a new note for account #".concat(Integer.toString(account.getAccountNumber())), "D", true);
//				System.out.println(Integer.toString(account.getAccountNumber()).concat(" - Updated rows = ").concat(Integer.toString(result)));
//			}
			
			// Test osdc account setting.
//			List<Integer> osdcAccounts = new ArrayList<Integer>();
//			List<String> notes = new ArrayList<String>();
//			osdcAccounts.add(new Integer(57835));
//			notes.add("This is another noter for account 57835");
//			osdcAccounts.add(new Integer(56471));
//			notes.add("This is another noter for account 56471");
//			
//			AccountBO.processAccountsForOsdc(osdcAccounts, 199, notes, "D", true);
//			osdcAccounts = null;
//			notes = null;
			
			// Test the end tracking.
//			count = TrackingBO.endTrackingByType(4249, "D", "FIN", 199, true);
//			System.out.println("Tracking ended? ".concat(Integer.toString(count)));
			
//    		CaseHistBO caseHistVo = new CaseHistBO();
//    		caseHistVo.setIntCaseNum(4249);
//    		caseHistVo.setClerkId(199);
//    		caseHistVo.setFuncId("HISTNOTE");
//    		caseHistVo.setRemovedFlag("N");
//    		caseHistVo.setChText("The balance on this case has been transferred to the Utah Office of State Debt Collection" +
//    				" (OSDC) for collection purposes.  Any payment made must be made to OSDC. OSDC is authorized by statute" +
//    				" to add interest and collection fees to the amount transferred.");
//    		caseHistVo.setCreateDatetime(Calendar.getInstance().getTime());
//    		caseHistVo.setEntryDatetime(caseHistVo.getCreateDatetime());
//    		
//    		CaseHistBO.insert("D", caseHistVo, true);

//			KaseBO.setCaseDebtCollectionFlag(4249, "D", "N", true);
			
    		// Insert a case history note.  (Per account or case?)
//    		CaseHistBO caseHistVo = new CaseHistBO();
//    		caseHistVo.setIntCaseNum(7996289);
//    		caseHistVo.setClerkId(199);
//    		caseHistVo.setFuncId("HISTNOTE");
//    		caseHistVo.setRemovedFlag("N");
//    		StringBuilder histNote = new StringBuilder("The balance of ");
//    		histNote.append(Float.toString(500 - 100 - 200));
//    		histNote.append(" for ");
//    		histNote.append("I");
//    		histNote.append(" on this case has been transferred to the Utah Office of State Debt Collection (OSDC) for collection purposes.");
//    		histNote.append(" Any payment made must be made to OSDC.");
//    		caseHistVo.setChText(histNote.toString());
//    		caseHistVo.setCreateDatetime(Calendar.getInstance().getTime());
//    		caseHistVo.setEntryDatetime(caseHistVo.getCreateDatetime());
//    		CaseHistBO.insert("D", caseHistVo, true);
    		
//    		List<String> notes = new ArrayList<String>();
//    		List<Integer> acctNumbs = new ArrayList<Integer>();
//    		notes.add("");
//    		acctNumbs.add(new Integer(43339));
//    		AccountBO.processAccountsForOsdc(acctNumbs, 86203, notes, "D", true);
		} catch (Exception e) {
			System.out.println("Exception: ".concat(e.toString()));
		}
//		debtCollections = null;
//		mySearch = null;
//		accounts = null;
	}

}
