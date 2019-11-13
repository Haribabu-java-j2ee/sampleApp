package gov.utcourts.coriscommon.dataaccess.bci;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BciVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(BciBO.INTCASENUM.clear()).addForeignKey("crim_case","int_case_num",false).setAsPrimaryKey();

	public BciVO() {
		super(BciBO.TABLE, BciBO.SYSTEM, BciBO.CORIS_DISTRICT_DB.setSource("D"), BciBO.CORIS_JUSTICE_DB.setSource("J"), BciBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BciBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BciVO(String source) {
		super(BciBO.TABLE, BciBO.SYSTEM, BciBO.CORIS_DISTRICT_DB.setSource("D"), BciBO.CORIS_JUSTICE_DB.setSource("J"), BciBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BciBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
	}

}