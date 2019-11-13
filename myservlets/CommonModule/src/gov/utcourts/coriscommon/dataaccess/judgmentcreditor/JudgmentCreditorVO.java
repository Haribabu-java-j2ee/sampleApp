package gov.utcourts.coriscommon.dataaccess.judgmentcreditor;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JudgmentCreditorVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(JudgmentCreditorBO.INTCASENUM.clear()).addForeignKey("party_case","int_case_num",false).setAsPrimaryKey();
	private TypeInteger jdmtSeq = new TypeInteger("jdmt_seq").setFieldDescriptor(JudgmentCreditorBO.JDMTSEQ.clear()).setAsPrimaryKey();
	private TypeInteger detlSeq = new TypeInteger("detl_seq").setFieldDescriptor(JudgmentCreditorBO.DETLSEQ.clear()).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(JudgmentCreditorBO.PARTYNUM.clear()).addForeignKey("party_case","party_num",false).setAsPrimaryKey();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(JudgmentCreditorBO.PARTYCODE.clear()).addForeignKey("party_case","party_code",false).setAsPrimaryKey();

	public JudgmentCreditorVO() {
		super(JudgmentCreditorBO.TABLE, JudgmentCreditorBO.SYSTEM, JudgmentCreditorBO.CORIS_DISTRICT_DB.setSource("D"), JudgmentCreditorBO.CORIS_JUSTICE_DB.setSource("J"), JudgmentCreditorBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgmentCreditorBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JudgmentCreditorVO(String source) {
		super(JudgmentCreditorBO.TABLE, JudgmentCreditorBO.SYSTEM, JudgmentCreditorBO.CORIS_DISTRICT_DB.setSource("D"), JudgmentCreditorBO.CORIS_JUSTICE_DB.setSource("J"), JudgmentCreditorBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgmentCreditorBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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