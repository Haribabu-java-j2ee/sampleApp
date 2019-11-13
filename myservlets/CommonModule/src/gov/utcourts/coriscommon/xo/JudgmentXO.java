package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.disptype.DispTypeBO;
import gov.utcourts.coriscommon.dataaccess.jdmttype.JdmtTypeBO;
import gov.utcourts.coriscommon.dataaccess.judgment.JudgmentBO;
import gov.utcourts.coriscommon.dataaccess.judgmentcreditor.JudgmentCreditorBO;
import gov.utcourts.coriscommon.dataaccess.judgmentdebtor.JudgmentDebtorBO;
import gov.utcourts.coriscommon.dataaccess.judgmentdetl.JudgmentDetlBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dto.JudgmentDTO;
import gov.utcourts.coriscommon.util.SystemException;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

public class JudgmentXO implements BaseConstants {
	private static final Logger logger = Logger.getLogger(JudgmentXO.class);
	public static int PRINT_SQL = RUN;
	private static final String EXCEP_JUDGMENT_XO = "Exception in JudgmentXO: ";
	
	public static void main(String args[]) throws Exception {
		
		DatabaseConnection.setUseJdbc();
		
		List<JudgmentDTO> list = findJudgments("D", 8078528);
		for (JudgmentDTO dto : list){
			System.out.println(
				dto.getJdmtSeq() 
				+ " " + dto.getIntCaseNum() 
				+ " " + dto.getJdmtCode() 
				+ " " + dto.getJdmtAmt()
				+ " " + dto.getJudgmentDescr()
				+ " " + dto.getDispTypeDescr() 
				+ " " + dto.getJdmtTypeDescr()
			);
		}
		
	}

	/**
	 * @param intCaseNum
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JudgmentDTO> findJudgments(String courtType,int intCaseNum) throws Exception {

		List<JudgmentDTO> result = null;
        try {
        	
//        	SearchDescriptor judgmentSD=new SearchDescriptor(new JudgmentBO(courtType).where(JudgmentBO.INTCASENUM, intCaseNum).as(JudgmentBO.DESCR, "judgment_descr"));
//        	SearchDescriptor jdmtTypeSD=new SearchDescriptor(new JdmtTypeBO(courtType).as(JdmtTypeBO.DESCR, "jdmt_type_descr"));
//        	SearchDescriptor dispTypeSD=new SearchDescriptor(new DispTypeBO(courtType).setOuter().as(DispTypeBO.DESCR, "disp_type_descr"));
//        	
//        	BaseSearchDispatcher searchDispatcher=new SearchDispatcher(judgmentSD,jdmtTypeSD,dispTypeSD)
//        	.addForeignKey(JudgmentBO.JDMTCODE, JdmtTypeBO.JDMTCODE)
//        	.addForeignKey(JudgmentBO.DISPCODE, DispTypeBO.DISPCODE)
//        	.setResultContainer(new JudgmentDTO())
//        	.toString(PRINT_SQL)
//        	.search(JudgmentBO.INTCASENUM,
//	        	JudgmentBO.JDMTSEQ,
//	        	JudgmentBO.FILINGDATETIME,
//	        	JudgmentBO.JDMTDATETIME,
//	        	JudgmentBO.JDMTAMT,
//	        	JudgmentBO.JDMTNUM,
//	        	JudgmentBO.JDMTCODE,
//	        	JudgmentBO.NOTE,
//	        	JudgmentBO.LASTUPDDATE,
//	        	JudgmentBO.DISPCODE,
//	        	JudgmentBO.DISPDATE,
//	        	JudgmentBO.USERIDSRL,
//	        	JudgmentBO.DESCR,
//	        	JdmtTypeBO.DESCR,
//	        	DispTypeBO.DESCR
//	        );
//       
//        	result=(List<JudgmentDTO>)searchDispatcher.getResults();
        	
        	result = new JudgmentBO(courtType)
    		.where(JudgmentBO.INTCASENUM, intCaseNum)
    		.setFieldAlias(JudgmentBO.DESCR, "judgment_descr")
    		.includeTables(
    			new JdmtTypeBO(courtType).setFieldAlias(JdmtTypeBO.DESCR, "jdmt_type_descr"),
    			new DispTypeBO(courtType).setOuter().setFieldAlias(DispTypeBO.DESCR, "disp_type_descr")
    		)
        	.addForeignKey(JudgmentBO.JDMTCODE, JdmtTypeBO.JDMTCODE)
        	.addForeignKey(JudgmentBO.DISPCODE, DispTypeBO.DISPCODE)
        	.setResultContainer(new JudgmentDTO())
        	.toString(PRINT_SQL)
        	.searchAndGetResults(
        		JudgmentBO.INTCASENUM, JudgmentBO.JDMTSEQ, JudgmentBO.FILINGDATETIME, JudgmentBO.JDMTDATETIME, JudgmentBO.JDMTAMT,
	        	JudgmentBO.JDMTNUM,	JudgmentBO.JDMTCODE, JudgmentBO.NOTE, JudgmentBO.LASTUPDDATE, JudgmentBO.DISPCODE, JudgmentBO.DISPDATE,
	        	JudgmentBO.USERIDSRL, JudgmentBO.DESCR,	JdmtTypeBO.DESCR, DispTypeBO.DESCR
	        );
    		
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append(EXCEP_JUDGMENT_XO+" method findJudgments");
            sb.append(e.getMessage());
            logger.error(sb.toString());
            throw new SystemException(e, EXCEP_JUDGMENT_XO);
        }  
        return result;
    }
	
	public static JudgmentBO createJudgment(JudgmentBO jdmtBO, String courtType) throws Exception{
		return new JudgmentBO(courtType)
			.setIntCaseNum(jdmtBO.getIntCaseNum())
			.setJdmtAmt(jdmtBO.getJdmtAmt())
			.setJdmtDatetime(Calendar.getInstance().getTime())
			.setJdmtSeq(jdmtBO.getJdmtSeq())
			.setFilingDatetime(jdmtBO.getFilingDatetime())
			.setJdmtCode(jdmtBO.getJdmtCode())
			.insert();
	}

	public static int getJudgmentSeq(int intCaseNum, String courtType) throws Exception {
		JudgmentBO judgmentBO = new JudgmentBO(courtType).max(JudgmentBO.INTCASENUM).where(JudgmentBO.INTCASENUM, intCaseNum).setReturnNull(true).find();
		if (judgmentBO != null) {
			return (Integer) judgmentBO.get("seq") + 1;
		} else {
			return 1;
		}
	}

	public static JudgmentDetlBO createJudgmentDetl(JudgmentBO jdmtBO, int seq, String descr, String courtType) throws Exception {
		int detlSeq = 1;
		
		//insert into judgment_detl
		return new JudgmentDetlBO(courtType)
			.setIntCaseNum(jdmtBO.getIntCaseNum())
			.setJdmtSeq(jdmtBO.getJdmtSeq())
			.setSeq(seq)
			.setDetlSeq(detlSeq)
			.setDescr(descr)
			.setAmt(jdmtBO.getJdmtAmt())
			.toString(PRINT_SQL)
			.insert();
	}

	public static void createJudgmentDebitor(JudgmentDetlBO detlBO, PartyCaseBO pcBO, String courtType) throws Exception {
		new JudgmentDebtorBO(courtType)
			.setIntCaseNum(detlBO.getIntCaseNum())
			.setJdmtSeq(detlBO.getJdmtSeq())
			.setDetlSeq(detlBO.getDetlSeq())
			.setPartyCode(pcBO.getPartyCode())
			.setPartyNum(pcBO.getPartyNum())
			.toString(PRINT_SQL)
			.insert();
	}

	public static void createJudgmentCreditor(JudgmentDetlBO detlBO, PartyCaseBO pcBO, String courtType) throws Exception {
		new JudgmentCreditorBO(courtType)
		.setIntCaseNum(detlBO.getIntCaseNum())
		.setJdmtSeq(detlBO.getJdmtSeq())
		.setDetlSeq(detlBO.getDetlSeq())
		.setPartyCode(pcBO.getPartyCode())
		.setPartyNum(pcBO.getPartyNum())
		.toString(PRINT_SQL)
		.insert();
	}

	 
}
