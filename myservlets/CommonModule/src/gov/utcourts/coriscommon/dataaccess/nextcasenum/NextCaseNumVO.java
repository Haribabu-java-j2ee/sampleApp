package gov.utcourts.coriscommon.dataaccess.nextcasenum;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class NextCaseNumVO extends BaseVO { 

	private TypeString caseNumCode = new TypeString("case_num_code").setFieldDescriptor(NextCaseNumBO.CASENUMCODE.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(NextCaseNumBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(NextCaseNumBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setAsPrimaryKey();
	private TypeString nextCaseNum = new TypeString("next_case_num").setFieldDescriptor(NextCaseNumBO.NEXTCASENUM.clear()).setNullable();

	public NextCaseNumVO() {
		super(NextCaseNumBO.TABLE, NextCaseNumBO.SYSTEM, NextCaseNumBO.CORIS_DISTRICT_DB.setSource("D"), NextCaseNumBO.CORIS_JUSTICE_DB.setSource("J"), NextCaseNumBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NextCaseNumBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public NextCaseNumVO(String source) {
		super(NextCaseNumBO.TABLE, NextCaseNumBO.SYSTEM, NextCaseNumBO.CORIS_DISTRICT_DB.setSource("D"), NextCaseNumBO.CORIS_JUSTICE_DB.setSource("J"), NextCaseNumBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NextCaseNumBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(caseNumCode);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(nextCaseNum);
	}

}