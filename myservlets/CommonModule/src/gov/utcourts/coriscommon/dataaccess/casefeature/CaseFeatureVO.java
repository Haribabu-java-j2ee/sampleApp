package gov.utcourts.coriscommon.dataaccess.casefeature;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseFeatureVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CaseFeatureBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeString featureCode = new TypeString("feature_code").setFieldDescriptor(CaseFeatureBO.FEATURECODE.clear()).setAsPrimaryKey();
	private TypeString featureValue = new TypeString("feature_value").setFieldDescriptor(CaseFeatureBO.FEATUREVALUE.clear()).setNullable();

	public CaseFeatureVO() {
		super(CaseFeatureBO.TABLE, CaseFeatureBO.SYSTEM, CaseFeatureBO.CORIS_DISTRICT_DB.setSource("D"), CaseFeatureBO.CORIS_JUSTICE_DB.setSource("J"), CaseFeatureBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseFeatureBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseFeatureVO(String source) {
		super(CaseFeatureBO.TABLE, CaseFeatureBO.SYSTEM, CaseFeatureBO.CORIS_DISTRICT_DB.setSource("D"), CaseFeatureBO.CORIS_JUSTICE_DB.setSource("J"), CaseFeatureBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseFeatureBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(featureCode);
		this.getPropertyList().add(featureValue);
	}

}