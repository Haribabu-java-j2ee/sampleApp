package gov.utcourts.coriscommon.dataaccess.accountjudgmentxref;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AccountJudgmentXrefVO extends BaseVO { 

	private TypeInteger accountJudgmentXrefId = new TypeInteger("account_judgment_xref_id").setFieldDescriptor(AccountJudgmentXrefBO.ACCOUNTJUDGMENTXREFID.clear()).setAsPrimaryKey();
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(AccountJudgmentXrefBO.ACCTNUM.clear()).addForeignKey("account","acct_num",false);
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(AccountJudgmentXrefBO.INTCASENUM.clear()).addForeignKey("judgment_detl","int_case_num",false);
	private TypeInteger jdmtSeq = new TypeInteger("jdmt_seq").setFieldDescriptor(AccountJudgmentXrefBO.JDMTSEQ.clear()).addForeignKey("judgment_detl","jdmt_seq",false);
	private TypeInteger detlSeq = new TypeInteger("detl_seq").setFieldDescriptor(AccountJudgmentXrefBO.DETLSEQ.clear()).addForeignKey("judgment_detl","detl_seq",false);
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(AccountJudgmentXrefBO.SEQ.clear()).addForeignKey("judgment_detl","seq",false);

	public AccountJudgmentXrefVO() {
		super(AccountJudgmentXrefBO.TABLE, AccountJudgmentXrefBO.SYSTEM, AccountJudgmentXrefBO.CORIS_DISTRICT_DB.setSource("D"), AccountJudgmentXrefBO.CORIS_JUSTICE_DB.setSource("J"), AccountJudgmentXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AccountJudgmentXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AccountJudgmentXrefVO(String source) {
		super(AccountJudgmentXrefBO.TABLE, AccountJudgmentXrefBO.SYSTEM, AccountJudgmentXrefBO.CORIS_DISTRICT_DB.setSource("D"), AccountJudgmentXrefBO.CORIS_JUSTICE_DB.setSource("J"), AccountJudgmentXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AccountJudgmentXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(accountJudgmentXrefId);
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(jdmtSeq);
		this.getPropertyList().add(detlSeq);
		this.getPropertyList().add(seq);
	}

}