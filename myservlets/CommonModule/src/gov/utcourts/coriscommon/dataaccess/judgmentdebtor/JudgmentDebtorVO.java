package gov.utcourts.coriscommon.dataaccess.judgmentdebtor;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JudgmentDebtorVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(JudgmentDebtorBO.INTCASENUM.clear()).addForeignKey("party_case","int_case_num",false).setAsPrimaryKey();
	private TypeInteger jdmtSeq = new TypeInteger("jdmt_seq").setFieldDescriptor(JudgmentDebtorBO.JDMTSEQ.clear()).setAsPrimaryKey();
	private TypeInteger detlSeq = new TypeInteger("detl_seq").setFieldDescriptor(JudgmentDebtorBO.DETLSEQ.clear()).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(JudgmentDebtorBO.PARTYNUM.clear()).addForeignKey("party_case","party_num",false).setAsPrimaryKey();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(JudgmentDebtorBO.PARTYCODE.clear()).addForeignKey("party_case","party_code",false).setAsPrimaryKey();

	public JudgmentDebtorVO() {
		super(JudgmentDebtorBO.TABLE, JudgmentDebtorBO.SYSTEM, JudgmentDebtorBO.CORIS_DISTRICT_DB.setSource("D"), JudgmentDebtorBO.CORIS_JUSTICE_DB.setSource("J"), JudgmentDebtorBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgmentDebtorBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JudgmentDebtorVO(String source) {
		super(JudgmentDebtorBO.TABLE, JudgmentDebtorBO.SYSTEM, JudgmentDebtorBO.CORIS_DISTRICT_DB.setSource("D"), JudgmentDebtorBO.CORIS_JUSTICE_DB.setSource("J"), JudgmentDebtorBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgmentDebtorBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(jdmtSeq);
		this.getPropertyList().add(detlSeq);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(partyCode);
	}

}