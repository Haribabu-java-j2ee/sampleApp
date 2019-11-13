package gov.utcourts.coriscommon.dataaccess.bcisuspense;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BciSuspenseVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(BciSuspenseBO.INTCASENUM.clear()).addForeignKey("crim_case","int_case_num",false).setAsPrimaryKey();

	public BciSuspenseVO() {
		super(BciSuspenseBO.TABLE, BciSuspenseBO.SYSTEM, BciSuspenseBO.CORIS_DISTRICT_DB.setSource("D"), BciSuspenseBO.CORIS_JUSTICE_DB.setSource("J"), BciSuspenseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BciSuspenseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BciSuspenseVO(String source) {
		super(BciSuspenseBO.TABLE, BciSuspenseBO.SYSTEM, BciSuspenseBO.CORIS_DISTRICT_DB.setSource("D"), BciSuspenseBO.CORIS_JUSTICE_DB.setSource("J"), BciSuspenseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BciSuspenseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
	}

}