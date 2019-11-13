package gov.utcourts.coriscommon.dataaccess.staytype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class StayTypeVO extends BaseVO { 

	private TypeString stayCode = new TypeString("stay_code").setFieldDescriptor(StayTypeBO.STAYCODE.clear()).addForeignKey("stay","stay_code",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(StayTypeBO.DESCR.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(StayTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(StayTypeBO.REMOVEDFLAG.clear());

	public StayTypeVO() {
		super(StayTypeBO.TABLE, StayTypeBO.SYSTEM, StayTypeBO.CORIS_DISTRICT_DB.setSource("D"), StayTypeBO.CORIS_JUSTICE_DB.setSource("J"), StayTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), StayTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public StayTypeVO(String source) {
		super(StayTypeBO.TABLE, StayTypeBO.SYSTEM, StayTypeBO.CORIS_DISTRICT_DB.setSource("D"), StayTypeBO.CORIS_JUSTICE_DB.setSource("J"), StayTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), StayTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(stayCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
	}

}