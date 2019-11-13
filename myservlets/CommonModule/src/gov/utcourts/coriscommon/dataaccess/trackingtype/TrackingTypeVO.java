package gov.utcourts.coriscommon.dataaccess.trackingtype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TrackingTypeVO extends BaseVO { 

	private TypeString trackCode = new TypeString("track_code").setFieldDescriptor(TrackingTypeBO.TRACKCODE.clear()).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(TrackingTypeBO.DESCR.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(TrackingTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(TrackingTypeBO.REMOVEDFLAG.clear());
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(TrackingTypeBO.LOCNCODE.clear()).addForeignKey("location","locn_code",false).setNullable().setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(TrackingTypeBO.COURTTYPE.clear()).addForeignKey("location","court_type",false).setNullable().setAsPrimaryKey();

	public TrackingTypeVO() {
		super(TrackingTypeBO.TABLE, TrackingTypeBO.SYSTEM, TrackingTypeBO.CORIS_DISTRICT_DB.setSource("D"), TrackingTypeBO.CORIS_JUSTICE_DB.setSource("J"), TrackingTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TrackingTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TrackingTypeVO(String source) {
		super(TrackingTypeBO.TABLE, TrackingTypeBO.SYSTEM, TrackingTypeBO.CORIS_DISTRICT_DB.setSource("D"), TrackingTypeBO.CORIS_JUSTICE_DB.setSource("J"), TrackingTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TrackingTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(trackCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
	}

}