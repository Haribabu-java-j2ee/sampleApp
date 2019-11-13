package gov.utcourts.coriscommon.dataaccess.casetypeassn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseTypeAssnVO extends BaseVO { 

	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(CaseTypeAssnBO.CASETYPE.clear()).addForeignKey("case_type","case_type",false).setAsPrimaryKey();
	private TypeInteger judgeCommId = new TypeInteger("judge_comm_id").setFieldDescriptor(CaseTypeAssnBO.JUDGECOMMID.clear()).addForeignKey("personnel","userid_srl",false).setAsPrimaryKey();
	private TypeInteger weight = new TypeInteger("weight").setFieldDescriptor(CaseTypeAssnBO.WEIGHT.clear());
	private TypeInteger extraCaseCnt = new TypeInteger("extra_case_cnt").setFieldDescriptor(CaseTypeAssnBO.EXTRACASECNT.clear());
	private TypeString caseAssnLimit = new TypeString("case_assn_limit").setFieldDescriptor(CaseTypeAssnBO.CASEASSNLIMIT.clear()).setAsPrimaryKey();

	public CaseTypeAssnVO() {
		super(CaseTypeAssnBO.TABLE, CaseTypeAssnBO.SYSTEM, CaseTypeAssnBO.CORIS_DISTRICT_DB.setSource("D"), CaseTypeAssnBO.CORIS_JUSTICE_DB.setSource("J"), CaseTypeAssnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseTypeAssnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseTypeAssnVO(String source) {
		super(CaseTypeAssnBO.TABLE, CaseTypeAssnBO.SYSTEM, CaseTypeAssnBO.CORIS_DISTRICT_DB.setSource("D"), CaseTypeAssnBO.CORIS_JUSTICE_DB.setSource("J"), CaseTypeAssnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseTypeAssnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(judgeCommId);
		this.getPropertyList().add(weight);
		this.getPropertyList().add(extraCaseCnt);
		this.getPropertyList().add(caseAssnLimit);
	}

}