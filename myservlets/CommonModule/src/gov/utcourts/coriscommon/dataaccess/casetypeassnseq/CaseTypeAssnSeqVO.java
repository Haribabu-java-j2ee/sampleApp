package gov.utcourts.coriscommon.dataaccess.casetypeassnseq;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseTypeAssnSeqVO extends BaseVO { 

	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(CaseTypeAssnSeqBO.CASETYPE.clear()).setAsPrimaryKey();
	private TypeInteger judgeCommId = new TypeInteger("judge_comm_id").setFieldDescriptor(CaseTypeAssnSeqBO.JUDGECOMMID.clear()).setAsPrimaryKey();
	private TypeInteger assnSeq = new TypeInteger("assn_seq").setFieldDescriptor(CaseTypeAssnSeqBO.ASSNSEQ.clear()).setAsPrimaryKey();
	private TypeString caseAssnLimit = new TypeString("case_assn_limit").setFieldDescriptor(CaseTypeAssnSeqBO.CASEASSNLIMIT.clear()).setAsPrimaryKey();

	public CaseTypeAssnSeqVO() {
		super(CaseTypeAssnSeqBO.TABLE, CaseTypeAssnSeqBO.SYSTEM, CaseTypeAssnSeqBO.CORIS_DISTRICT_DB.setSource("D"), CaseTypeAssnSeqBO.CORIS_JUSTICE_DB.setSource("J"), CaseTypeAssnSeqBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseTypeAssnSeqBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseTypeAssnSeqVO(String source) {
		super(CaseTypeAssnSeqBO.TABLE, CaseTypeAssnSeqBO.SYSTEM, CaseTypeAssnSeqBO.CORIS_DISTRICT_DB.setSource("D"), CaseTypeAssnSeqBO.CORIS_JUSTICE_DB.setSource("J"), CaseTypeAssnSeqBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseTypeAssnSeqBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(judgeCommId);
		this.getPropertyList().add(assnSeq);
		this.getPropertyList().add(caseAssnLimit);
	}

}