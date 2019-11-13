package gov.utcourts.coriscommon.dataaccess.scalendar;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SCalendarVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SCalendarBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SCalendarBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SCalendarBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SCalendarBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SCalendarBO.SOPERATION.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SCalendarBO.INTCASENUM.clear()).setNullable();
	private TypeDate appearDate = new TypeDate("appear_date").setFieldDescriptor(SCalendarBO.APPEARDATE.clear()).setNullable().setAsPrimaryKey();
	private TypeString hearingCode = new TypeString("hearing_code").setFieldDescriptor(SCalendarBO.HEARINGCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(SCalendarBO.CREATEDATETIME.clear()).setNullable().setAsPrimaryKey();
	private TypeDate time = new TypeDate("time").setFieldDescriptor(SCalendarBO.TIME.clear()).setNullable();
	private TypeString room = new TypeString("room").setFieldDescriptor(SCalendarBO.ROOM.clear()).setNullable();
	private TypeInteger judgeId = new TypeInteger("judge_id").setFieldDescriptor(SCalendarBO.JUDGEID.clear()).setNullable();
	private TypeDate cancelDatetime = new TypeDate("cancel_datetime").setFieldDescriptor(SCalendarBO.CANCELDATETIME.clear()).setNullable();
	private TypeInteger multiDayHearing = new TypeInteger("multi_day_hearing").setFieldDescriptor(SCalendarBO.MULTIDAYHEARING.clear()).setNullable();
	private TypeString hearingLen = new TypeString("hearing_len").setFieldDescriptor(SCalendarBO.HEARINGLEN.clear()).setNullable();
	private TypeInteger priority = new TypeInteger("priority").setFieldDescriptor(SCalendarBO.PRIORITY.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(SCalendarBO.USERIDSRL.clear()).setNullable();
	private TypeInteger cancelUseridSrl = new TypeInteger("cancel_userid_srl").setFieldDescriptor(SCalendarBO.CANCELUSERIDSRL.clear()).setNullable();
	private TypeString cancelReason = new TypeString("cancel_reason").setFieldDescriptor(SCalendarBO.CANCELREASON.clear()).setNullable();

	public SCalendarVO() {
		super(SCalendarBO.TABLE, SCalendarBO.SYSTEM, SCalendarBO.CORIS_DISTRICT_DB.setSource("D"), SCalendarBO.CORIS_JUSTICE_DB.setSource("J"), SCalendarBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SCalendarBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SCalendarVO(String source) {
		super(SCalendarBO.TABLE, SCalendarBO.SYSTEM, SCalendarBO.CORIS_DISTRICT_DB.setSource("D"), SCalendarBO.CORIS_JUSTICE_DB.setSource("J"), SCalendarBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SCalendarBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
		this.getPropertyList().add(appearDate);
		this.getPropertyList().add(hearingCode);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(time);
		this.getPropertyList().add(room);
		this.getPropertyList().add(judgeId);
		this.getPropertyList().add(cancelDatetime);
		this.getPropertyList().add(multiDayHearing);
		this.getPropertyList().add(hearingLen);
		this.getPropertyList().add(priority);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(cancelUseridSrl);
		this.getPropertyList().add(cancelReason);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SCalendarBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SCalendarBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SCalendarBO.APPEARDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SCalendarBO.APPEARDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SCalendarBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SCalendarBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SCalendarBO.TIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SCalendarBO.TIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SCalendarBO.CANCELDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SCalendarBO.CANCELDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}