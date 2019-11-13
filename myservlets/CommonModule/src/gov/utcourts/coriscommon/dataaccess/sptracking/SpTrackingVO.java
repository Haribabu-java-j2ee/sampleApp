package gov.utcourts.coriscommon.dataaccess.sptracking;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SpTrackingVO extends BaseVO { 

	private TypeString spName = new TypeString("sp_name").setFieldDescriptor(SpTrackingBO.SPNAME.clear()).setAsPrimaryKey();
	private TypeString trackSp = new TypeString("track_sp").setFieldDescriptor(SpTrackingBO.TRACKSP.clear());

	public SpTrackingVO() {
		super(SpTrackingBO.TABLE, SpTrackingBO.SYSTEM, SpTrackingBO.CORIS_DISTRICT_DB.setSource("D"), SpTrackingBO.CORIS_JUSTICE_DB.setSource("J"), SpTrackingBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SpTrackingBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SpTrackingVO(String source) {
		super(SpTrackingBO.TABLE, SpTrackingBO.SYSTEM, SpTrackingBO.CORIS_DISTRICT_DB.setSource("D"), SpTrackingBO.CORIS_JUSTICE_DB.setSource("J"), SpTrackingBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SpTrackingBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(spName);
		this.getPropertyList().add(trackSp);
	}

}