package gov.utcourts.coriscommon.dataaccess.efilingactivity;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EfilingActivityVO extends BaseVO { 

	private TypeInteger efilActivitySrl = new TypeInteger("efil_activity_srl").setFieldDescriptor(EfilingActivityBO.EFILACTIVITYSRL.clear()).setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(EfilingActivityBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setNullable();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(EfilingActivityBO.CREATEDATETIME.clear()).setNullable();
	private TypeString efilActivityType = new TypeString("efil_activity_type").setFieldDescriptor(EfilingActivityBO.EFILACTIVITYTYPE.clear());
	private TypeString eventId = new TypeString("event_id").setFieldDescriptor(EfilingActivityBO.EVENTID.clear()).setNullable();
	private TypeString efilTrackingNum = new TypeString("efil_tracking_num").setFieldDescriptor(EfilingActivityBO.EFILTRACKINGNUM.clear()).setNullable();
	private TypeString efspCode = new TypeString("efsp_code").setFieldDescriptor(EfilingActivityBO.EFSPCODE.clear()).setNullable();
	private TypeString orgCode = new TypeString("org_code").setFieldDescriptor(EfilingActivityBO.ORGCODE.clear()).setNullable();
	private TypeInteger barNum = new TypeInteger("bar_num").setFieldDescriptor(EfilingActivityBO.BARNUM.clear()).setNullable();
	private TypeString filerName = new TypeString("filer_name").setFieldDescriptor(EfilingActivityBO.FILERNAME.clear()).setNullable();
	private TypeString note = new TypeString("note").setFieldDescriptor(EfilingActivityBO.NOTE.clear()).setNullable();

	public EfilingActivityVO() {
		super(EfilingActivityBO.TABLE, EfilingActivityBO.SYSTEM, EfilingActivityBO.CORIS_DISTRICT_DB.setSource("D"), EfilingActivityBO.CORIS_JUSTICE_DB.setSource("J"), EfilingActivityBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingActivityBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EfilingActivityVO(String source) {
		super(EfilingActivityBO.TABLE, EfilingActivityBO.SYSTEM, EfilingActivityBO.CORIS_DISTRICT_DB.setSource("D"), EfilingActivityBO.CORIS_JUSTICE_DB.setSource("J"), EfilingActivityBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingActivityBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(efilActivitySrl);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(efilActivityType);
		this.getPropertyList().add(eventId);
		this.getPropertyList().add(efilTrackingNum);
		this.getPropertyList().add(efspCode);
		this.getPropertyList().add(orgCode);
		this.getPropertyList().add(barNum);
		this.getPropertyList().add(filerName);
		this.getPropertyList().add(note);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(EfilingActivityBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(EfilingActivityBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}