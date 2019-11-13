package gov.utcourts.coriscommon.dataaccess.rotateattorney;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class RotateAttorneyVO extends BaseVO { 

	private TypeInteger barNum = new TypeInteger("bar_num").setFieldDescriptor(RotateAttorneyBO.BARNUM.clear());
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(RotateAttorneyBO.CASENUM.clear());
	private TypeString actionType = new TypeString("action_type").setFieldDescriptor(RotateAttorneyBO.ACTIONTYPE.clear()).setNullable();

	public RotateAttorneyVO() {
		super(RotateAttorneyBO.TABLE, RotateAttorneyBO.SYSTEM, RotateAttorneyBO.CORIS_DISTRICT_DB.setSource("D"), RotateAttorneyBO.CORIS_JUSTICE_DB.setSource("J"), RotateAttorneyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), RotateAttorneyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public RotateAttorneyVO(String source) {
		super(RotateAttorneyBO.TABLE, RotateAttorneyBO.SYSTEM, RotateAttorneyBO.CORIS_DISTRICT_DB.setSource("D"), RotateAttorneyBO.CORIS_JUSTICE_DB.setSource("J"), RotateAttorneyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), RotateAttorneyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(barNum);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(actionType);
	}

}