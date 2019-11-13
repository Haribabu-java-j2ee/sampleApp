package gov.utcourts.coriscommon.dataaccess.casemejudgment;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseMeJudgmentVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CaseMeJudgmentBO.INTCASENUM.clear());
	private TypeInteger jdmtSeq = new TypeInteger("jdmt_seq").setFieldDescriptor(CaseMeJudgmentBO.JDMTSEQ.clear());
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(CaseMeJudgmentBO.MEID.clear()).addForeignKey("case_me","me_id",false);

	public CaseMeJudgmentVO() {
		super(CaseMeJudgmentBO.TABLE, CaseMeJudgmentBO.SYSTEM, CaseMeJudgmentBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeJudgmentBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeJudgmentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeJudgmentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseMeJudgmentVO(String source) {
		super(CaseMeJudgmentBO.TABLE, CaseMeJudgmentBO.SYSTEM, CaseMeJudgmentBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeJudgmentBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeJudgmentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeJudgmentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(jdmtSeq);
		this.getPropertyList().add(meId);
	}

}