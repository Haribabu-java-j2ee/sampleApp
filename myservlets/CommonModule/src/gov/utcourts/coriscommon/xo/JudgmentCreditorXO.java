package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.judgmentcreditor.JudgmentCreditorBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dto.JudgmentPartyDTO;
import gov.utcourts.coriscommon.util.SystemException;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.util.List;

import org.apache.log4j.Logger;

public class JudgmentCreditorXO implements BaseConstants {
	private static final Logger logger = Logger.getLogger(JudgmentCreditorXO.class);
	public static int PRINT_SQL = RUN;
	private static final String EXCEP_JUDGMENT_CREDITOR_XO = "Exception in JudgmentCreditorXO: ";
	
	public static void main(String args[]) throws Exception {
		DatabaseConnection.setUseJdbc();
		//getCaseTypesByCourtType("D");
		test("D",7996289);
		
	}
	
	private static void test(String courtType, int intCaseNum) throws Exception {
		
//		SearchDescriptor judgmentCreditorSD=new SearchDescriptor(new JudgmentCreditorBO(courtType).where(JudgmentCreditorBO.INTCASENUM, intCaseNum));
//    	SearchDescriptor partySD=new SearchDescriptor(new PartyBO(courtType));
//    	
//    	BaseSearchDispatcher searchDispatcher=new SearchDispatcher(judgmentCreditorSD,partySD)
//    	.addForeignKey(PartyBO.PARTYNUM, JudgmentCreditorBO.PARTYNUM)
//    	.setResultContainer(new JudgmentPartyDTO())
//    	.toString(PRINT_SQL)
//    	.search(JudgmentCreditorBO.ALL_FIELDS,PartyBO.ALL_FIELDS);
		
		new JudgmentCreditorBO(courtType)
		.where(JudgmentCreditorBO.INTCASENUM, intCaseNum)
		.includeTables(new PartyBO(courtType))
    	.addForeignKey(PartyBO.PARTYNUM, JudgmentCreditorBO.PARTYNUM)
    	.setResultContainer(new JudgmentPartyDTO())
    	.toString(PRINT_SQL)
    	.search(JudgmentCreditorBO.ALL_FIELDS, PartyBO.ALL_FIELDS);
		
	}
	
	/**
	 * Find judgment creditors of a case
	 * @param intCaseNum
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<JudgmentPartyDTO> findJudgmentCreditors(String courtType, int intCaseNum) throws Exception {
		List<JudgmentPartyDTO> result = null;
        try {
        	
    		List<JudgmentPartyDTO> results = new JudgmentCreditorBO(courtType)
    		.where(JudgmentCreditorBO.INTCASENUM, intCaseNum)
        	.includeTables(new PartyBO(courtType))
        	.addForeignKey(PartyBO.PARTYNUM, JudgmentCreditorBO.PARTYNUM)
        	.setResultContainer(new JudgmentPartyDTO())
        	.toString(PRINT_SQL)
        	.searchAndGetResults(JudgmentCreditorBO.ALL_FIELDS, PartyBO.ALL_FIELDS);
        	
            if (results != null && results.size() > 0) {
            	result = results;
            }
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append(EXCEP_JUDGMENT_CREDITOR_XO+" method findJudgmentCreditors");
            sb.append(e.getMessage());
            logger.error(sb.toString());
            throw new SystemException(e, EXCEP_JUDGMENT_CREDITOR_XO);
        }  
        return result;
    }
	 
}
