package gov.utcourts.coriscommon.xo;

import java.util.ArrayList;
import java.util.List;

import gov.utcourts.coriscommon.dataaccess.accountjudgmentxref.AccountJudgmentXrefBO;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

public class AccountJudgmentXrefXO {
	
	public static int getAccountNumByJudgmentDetail(String courtType, int intCaseNum, int jdmtSeq, int detlSeq) throws Exception{
		int ret = 0;
		AccountJudgmentXrefBO vo = new AccountJudgmentXrefBO(courtType)
		.where(AccountJudgmentXrefBO.INTCASENUM, intCaseNum)
		.where(AccountJudgmentXrefBO.JDMTSEQ, jdmtSeq)
		.where(AccountJudgmentXrefBO.DETLSEQ, detlSeq).setReturnNull(true).find();
		
		if (vo != null){
			ret = vo.getAcctNum();
		}
		
		return ret;
	}
	
	public static List<Integer> getAccountNumsByJudgment(String courtType, int intCaseNum, int jdmtSeq) throws Exception{
		List<Integer> ret = new ArrayList<Integer>();
		List<AccountJudgmentXrefBO> vos = new AccountJudgmentXrefBO(courtType)
		.where(AccountJudgmentXrefBO.INTCASENUM, intCaseNum)
		.where(AccountJudgmentXrefBO.JDMTSEQ, jdmtSeq)
		.setReturnNull(true)
		.search();
		
		if (vos != null && vos.size() > 0){
			for (AccountJudgmentXrefBO xrefBO : vos){
				ret.add(xrefBO.getAcctNum());
			}
		}
		
		return ret;
	}
	
	public static void main(String[] args) throws Exception{
		DatabaseConnection.setUseJdbc();
		List<Integer> list = getAccountNumsByJudgment("D", 8085763, 1);
		for (int i:list){
			System.out.println(i);
		}
	}

}
