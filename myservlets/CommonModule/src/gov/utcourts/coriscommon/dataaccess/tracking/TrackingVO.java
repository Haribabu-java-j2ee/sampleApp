package gov.utcourts.coriscommon.dataaccess.tracking;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TrackingVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(TrackingBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeString trackCode = new TypeString("track_code").setFieldDescriptor(TrackingBO.TRACKCODE.clear()).setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(TrackingBO.CREATEDATETIME.clear()).setAsPrimaryKey();
	private TypeDate endDatetime = new TypeDate("end_datetime").setFieldDescriptor(TrackingBO.ENDDATETIME.clear()).setNullable();
	private TypeDate reviewDate = new TypeDate("review_date").setFieldDescriptor(TrackingBO.REVIEWDATE.clear());
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(TrackingBO.CLERKID.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeInteger endUseridSrl = new TypeInteger("end_userid_srl").setFieldDescriptor(TrackingBO.ENDUSERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable();

	public TrackingVO() {
		super(TrackingBO.TABLE, TrackingBO.SYSTEM, TrackingBO.CORIS_DISTRICT_DB.setSource("D"), TrackingBO.CORIS_JUSTICE_DB.setSource("J"), TrackingBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TrackingBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TrackingVO(String source) {
		super(TrackingBO.TABLE, TrackingBO.SYSTEM, TrackingBO.CORIS_DISTRICT_DB.setSource("D"), TrackingBO.CORIS_JUSTICE_DB.setSource("J"), TrackingBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TrackingBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(trackCode);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(endDatetime);
		this.getPropertyList().add(reviewDate);
		this.getPropertyList().add(clerkId);
		this.getPropertyList().add(endUseridSrl);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(TrackingBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(TrackingBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(TrackingBO.ENDDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(TrackingBO.ENDDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(TrackingBO.REVIEWDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(TrackingBO.REVIEWDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}