package gov.utcourts.coriscommon.dataaccess.stracking;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class STrackingVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(STrackingBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(STrackingBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(STrackingBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(STrackingBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(STrackingBO.SOPERATION.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(STrackingBO.INTCASENUM.clear()).setNullable();
	private TypeString trackCode = new TypeString("track_code").setFieldDescriptor(STrackingBO.TRACKCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(STrackingBO.CREATEDATETIME.clear()).setNullable().setAsPrimaryKey();
	private TypeDate endDatetime = new TypeDate("end_datetime").setFieldDescriptor(STrackingBO.ENDDATETIME.clear()).setNullable();
	private TypeDate reviewDate = new TypeDate("review_date").setFieldDescriptor(STrackingBO.REVIEWDATE.clear()).setNullable();
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(STrackingBO.CLERKID.clear()).setNullable();
	private TypeInteger endUseridSrl = new TypeInteger("end_userid_srl").setFieldDescriptor(STrackingBO.ENDUSERIDSRL.clear()).setNullable();

	public STrackingVO() {
		super(STrackingBO.TABLE, STrackingBO.SYSTEM, STrackingBO.CORIS_DISTRICT_DB.setSource("D"), STrackingBO.CORIS_JUSTICE_DB.setSource("J"), STrackingBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), STrackingBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public STrackingVO(String source) {
		super(STrackingBO.TABLE, STrackingBO.SYSTEM, STrackingBO.CORIS_DISTRICT_DB.setSource("D"), STrackingBO.CORIS_JUSTICE_DB.setSource("J"), STrackingBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), STrackingBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sIntCaseNum);
		this.getPropertyList().add(sUseridSrl);
		this.getPropertyList().add(sDatetime);
		this.getPropertyList().add(sMeId);
		this.getPropertyList().add(sOperation);
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
			((TypeDate) this.getPropertyList().get(STrackingBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(STrackingBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(STrackingBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(STrackingBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(STrackingBO.ENDDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(STrackingBO.ENDDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(STrackingBO.REVIEWDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(STrackingBO.REVIEWDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}