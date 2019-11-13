package gov.utcourts.coriscommon.dataaccess.calendar;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CalendarVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CalendarBO.INTCASENUM.clear()).addForeignKey("cal_lang","int_case_num",false).addForeignKey("cal_note","int_case_num",false).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeDate appearDate = new TypeDate("appear_date").setFieldDescriptor(CalendarBO.APPEARDATE.clear()).addForeignKey("cal_lang","appear_date",false).addForeignKey("cal_note","appear_date",false).setAsPrimaryKey();
	private TypeString hearingCode = new TypeString("hearing_code").setFieldDescriptor(CalendarBO.HEARINGCODE.clear()).addForeignKey("cal_lang","hearing_code",false).addForeignKey("cal_note","hearing_code",false).setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(CalendarBO.CREATEDATETIME.clear()).addForeignKey("cal_lang","create_datetime",false).addForeignKey("cal_note","create_datetime",false).setAsPrimaryKey();
	private TypeDate time = new TypeDate("time").setFieldDescriptor(CalendarBO.TIME.clear());
	private TypeString room = new TypeString("room").setFieldDescriptor(CalendarBO.ROOM.clear()).setNullable();
	private TypeInteger judgeId = new TypeInteger("judge_id").setFieldDescriptor(CalendarBO.JUDGEID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeDate cancelDatetime = new TypeDate("cancel_datetime").setFieldDescriptor(CalendarBO.CANCELDATETIME.clear()).setNullable();
	private TypeInteger multiDayHearing = new TypeInteger("multi_day_hearing").setFieldDescriptor(CalendarBO.MULTIDAYHEARING.clear()).setNullable();
	private TypeString hearingLen = new TypeString("hearing_len").setFieldDescriptor(CalendarBO.HEARINGLEN.clear()).setNullable();
	private TypeInteger priority = new TypeInteger("priority").setFieldDescriptor(CalendarBO.PRIORITY.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(CalendarBO.USERIDSRL.clear());
	private TypeInteger cancelUseridSrl = new TypeInteger("cancel_userid_srl").setFieldDescriptor(CalendarBO.CANCELUSERIDSRL.clear()).setNullable();
	private TypeString cancelReason = new TypeString("cancel_reason").setFieldDescriptor(CalendarBO.CANCELREASON.clear()).setNullable();
	private TypeString eventDescr = new TypeString("event_descr").setFieldDescriptor(CalendarBO.EVENTDESCR.clear()).setNullable();
	private TypeString courtReporter = new TypeString("court_reporter").setFieldDescriptor(CalendarBO.COURTREPORTER.clear()).setNullable();
	private TypeInteger lastModUseridsrl = new TypeInteger("last_mod_useridsrl").setFieldDescriptor(CalendarBO.LASTMODUSERIDSRL.clear()).setNullable();

	public CalendarVO() {
		super(CalendarBO.TABLE, CalendarBO.SYSTEM, CalendarBO.CORIS_DISTRICT_DB.setSource("D"), CalendarBO.CORIS_JUSTICE_DB.setSource("J"), CalendarBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CalendarBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CalendarVO(String source) {
		super(CalendarBO.TABLE, CalendarBO.SYSTEM, CalendarBO.CORIS_DISTRICT_DB.setSource("D"), CalendarBO.CORIS_JUSTICE_DB.setSource("J"), CalendarBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CalendarBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
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
		this.getPropertyList().add(eventDescr);
		this.getPropertyList().add(courtReporter);
		this.getPropertyList().add(lastModUseridsrl);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CalendarBO.APPEARDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CalendarBO.APPEARDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CalendarBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CalendarBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CalendarBO.TIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CalendarBO.TIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CalendarBO.CANCELDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CalendarBO.CANCELDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}