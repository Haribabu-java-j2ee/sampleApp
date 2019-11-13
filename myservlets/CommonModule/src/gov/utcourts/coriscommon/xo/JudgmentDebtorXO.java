package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.judgmentdebtor.JudgmentDebtorBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dto.JudgmentPartyDTO;
import gov.utcourts.coriscommon.util.SystemException;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.util.List;

import org.apache.log4j.Logger;

public class JudgmentDebtorXO implements BaseConstants {
	private static final Logger logger = Logger.getLogger(JudgmentDebtorXO.class);
	public static int PRINT_SQL = RUN;
	private static final String EXCEP_JUDGMENT_DEBTOR_XO = "Exception in JudgmentDebtorXO: ";
	
	public static void main(String args[]) throws Exception {
		DatabaseConnection.setUseJdbc();
		//getCaseTypesByCourtType("D");
		test("D",7996289);
		
	}
	
	private static void test(String courtType, int intCaseNum) throws Exception {
		
//		SearchDescriptor judgmentDebtorSD=new SearchDescriptor(new JudgmentDebtorBO(courtType).where(JudgmentDebtorBO.INTCASENUM, intCaseNum));
//    	SearchDescriptor partySD=new SearchDescriptor(new PartyBO(courtType));
//    	
//    	BaseSearchDispatcher searchDispatcher=new SearchDispatcher(judgmentDebtorSD,partySD)
//    	.addForeignKey(PartyBO.PARTYNUM, JudgmentDebtorBO.PARTYNUM)
//    	.setResultContainer(new JudgmentPartyDTO())
//    	.toString(PRINT_SQL)
//    	.search(JudgmentDebtorBO.ALL_FIELDS,PartyBO.ALL_FIELDS);
		
		new JudgmentDebtorBO(courtType)
		.where(JudgmentDebtorBO.INTCASENUM, intCaseNum)
    	.includeTables(new PartyBO(courtType))
    	.addForeignKey(PartyBO.PARTYNUM, JudgmentDebtorBO.PARTYNUM)
    	.setResultContainer(new JudgmentPartyDTO())
    	.toString(PRINT_SQL)
    	.search(JudgmentDebtorBO.ALL_FIELDS, PartyBO.ALL_FIELDS);
		
	}
	
	/**
	 * Find judgment debtors for a case
	 * @param intCaseNum
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<JudgmentPartyDTO> findJudgmentDebtors(String courtType,int intCaseNum) throws Exception {

		List<JudgmentPartyDTO> result = null;
        try {
        	
//        	SearchDescriptor judgmentDebtorSD=new SearchDescriptor(new JudgmentDebtorBO(courtType).where(JudgmentDebtorBO.INTCASENUM, intCaseNum));
//        	SearchDescriptor partySD=new SearchDescriptor(new PartyBO(courtType));
//        	
//        	BaseSearchDispatcher searchDispatcher=new SearchDispatcher(judgmentDebtorSD,partySD)
//        	.addForeignKey(PartyBO.PARTYNUM, JudgmentDebtorBO.PARTYNUM)
//        	.setResultContainer(new JudgmentPartyDTO())
//        	.toString(PRINT_SQL)
//        	.search(JudgmentDebtorBO.ALL_FIELDS,PartyBO.ALL_FIELDS);
        	
        	
    		List<JudgmentPartyDTO> results = new JudgmentDebtorBO(courtType)
    		.where(JudgmentDebtorBO.INTCASENUM, intCaseNum)
        	.includeTables(new PartyBO(courtType))
        	.addForeignKey(PartyBO.PARTYNUM, JudgmentDebtorBO.PARTYNUM)
        	.setResultContainer(new JudgmentPartyDTO())
        	.toString(PRINT_SQL)
        	.searchAndGetResults(JudgmentDebtorBO.ALL_FIELDS, PartyBO.ALL_FIELDS);
    		
            if (results != null && results.size() > 0) {
            	result = results;
            }
            
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append(EXCEP_JUDGMENT_DEBTOR_XO+" method findJudgmentDebtors");
            sb.append(e.getMessage());
            logger.error(sb.toString());
            throw new SystemException(e, EXCEP_JUDGMENT_DEBTOR_XO);
        }  
        return result;
    }
 
}
