package gov.utcourts.coriscommon.dataaccess.newtrackingtype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class NewTrackingTypeVO extends BaseVO { 

	private TypeString descr = new TypeString("descr").setFieldDescriptor(NewTrackingTypeBO.DESCR.clear()).setNullable();
	private TypeString trackCode = new TypeString("track_code").setFieldDescriptor(NewTrackingTypeBO.TRACKCODE.clear()).setNullable();
	private TypeString fix = new TypeString("fix").setFieldDescriptor(NewTrackingTypeBO.FIX.clear()).setNullable();

	public NewTrackingTypeVO() {
		super(NewTrackingTypeBO.TABLE, NewTrackingTypeBO.SYSTEM, NewTrackingTypeBO.CORIS_DISTRICT_DB.setSource("D"), NewTrackingTypeBO.CORIS_JUSTICE_DB.setSource("J"), NewTrackingTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NewTrackingTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public NewTrackingTypeVO(String source) {
		super(NewTrackingTypeBO.TABLE, NewTrackingTypeBO.SYSTEM, NewTrackingTypeBO.CORIS_DISTRICT_DB.setSource("D"), NewTrackingTypeBO.CORIS_JUSTICE_DB.setSource("J"), NewTrackingTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NewTrackingTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(descr);
		this.getPropertyList().add(trackCode);
		this.getPropertyList().add(fix);
	}

}