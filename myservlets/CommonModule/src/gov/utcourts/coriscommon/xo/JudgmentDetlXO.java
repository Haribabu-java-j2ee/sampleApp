package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.disptype.DispTypeBO;
import gov.utcourts.coriscommon.dataaccess.judgmentdetl.JudgmentDetlBO;
import gov.utcourts.courtscommon.dispatcher.SearchDispatcher;
import gov.utcourts.coriscommon.dto.JudgmentDetlDTO;
import gov.utcourts.coriscommon.util.SystemException;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SearchDescriptor;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dispatcher.BaseSearchDispatcher;

import java.util.List;

import org.apache.log4j.Logger;

 

public class JudgmentDetlXO implements BaseConstants {
	private static final Logger logger = Logger.getLogger(JudgmentDetlXO.class);
	public static int PRINT_SQL = RUN;
	private static final String EXCEP_JUDGMENTDETL_XO = "Exception in JudgmentDetlXO: ";
	
	public static void main(String args[]) throws Exception {
		DatabaseConnection.setUseJdbc();
		//getCaseTypesByCourtType("D");
		List<JudgmentDetlDTO> list = findJudgmentDetails("D",8078528, 1); //8078528, 7742754
		for (JudgmentDetlDTO dto:list){
			System.out.println(dto.getJdmtSeq() + " " + dto.getSeq());
		}
		
	}
	
	
	
	/**
	 * Find judgment details for a case and its judgment
	 * @param intCaseNum
	 * @param judgmentSeq
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JudgmentDetlDTO> findJudgmentDetails(String courtType,int intCaseNum, int judgmentSeq) throws Exception {

		List<JudgmentDetlDTO> results = null;
        try {
        	
//        	SearchDescriptor judgmentDetlSD=new SearchDescriptor(new JudgmentDetlBO(courtType)
//        	.where(JudgmentDetlBO.INTCASENUM, intCaseNum)
//        	.where(JudgmentDetlBO.JDMTSEQ, judgmentSeq).orderBy(JudgmentDetlBO.DETLSEQ).as(JudgmentDetlBO.DESCR, "detl_descr"));
//        	
//        	SearchDescriptor dispTypeSD=new SearchDescriptor(new DispTypeBO(courtType).setOuter().as(DispTypeBO.DESCR, "disp_descr"));
//        	
//        	BaseSearchDispatcher searchDispatcher=new SearchDispatcher(judgmentDetlSD,dispTypeSD)
//        	.addForeignKey(JudgmentDetlBO.DISPCODE, DispTypeBO.DISPCODE)
//        	.toString(PRINT_SQL)
//        	.setResultContainer(new JudgmentDetlDTO())
//        	.search(JudgmentDetlBO.INTCASENUM,
//					JudgmentDetlBO.JDMTSEQ,
//					JudgmentDetlBO.DETLSEQ,
//					JudgmentDetlBO.SEQ,
//					JudgmentDetlBO.DESCR,
//					JudgmentDetlBO.AMT,
//					JudgmentDetlBO.DISPCODE,
//					JudgmentDetlBO.DISPDATE,
//					DispTypeBO.DESCR);
//
//        	result=(List<JudgmentDetlDTO>)searchDispatcher.getResults();
            
        	results = new JudgmentDetlBO(courtType)
        	.where(JudgmentDetlBO.INTCASENUM, intCaseNum)
        	.where(JudgmentDetlBO.JDMTSEQ, judgmentSeq)
        	.orderBy(JudgmentDetlBO.DETLSEQ)
        	.setFieldAlias(JudgmentDetlBO.DESCR, "detl_descr")
        	.includeTables(new DispTypeBO(courtType).setOuter().setFieldAlias(DispTypeBO.DESCR, "disp_descr"))
        	.addForeignKey(JudgmentDetlBO.DISPCODE, DispTypeBO.DISPCODE)
        	.toString(PRINT_SQL)
        	.setResultContainer(new JudgmentDetlDTO())
        	.searchAndGetResults(
        		JudgmentDetlBO.INTCASENUM, JudgmentDetlBO.JDMTSEQ, JudgmentDetlBO.DETLSEQ, JudgmentDetlBO.SEQ,
				JudgmentDetlBO.DESCR, JudgmentDetlBO.AMT, JudgmentDetlBO.DISPCODE, JudgmentDetlBO.DISPDATE,	DispTypeBO.DESCR
			);
    		
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append(EXCEP_JUDGMENTDETL_XO+" method findJudgmentDetails");
            sb.append(e.getMessage());
            logger.error(sb.toString());
            throw new SystemException(e, EXCEP_JUDGMENTDETL_XO);
        } 
        return results;
    }
	
}
