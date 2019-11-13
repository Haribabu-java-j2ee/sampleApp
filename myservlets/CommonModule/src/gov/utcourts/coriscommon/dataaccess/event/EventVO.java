package gov.utcourts.coriscommon.dataaccess.event;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EventVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(EventBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeString eventCode = new TypeString("event_code").setFieldDescriptor(EventBO.EVENTCODE.clear()).setAsPrimaryKey();
	private TypeDate beginDatetime = new TypeDate("begin_datetime").setFieldDescriptor(EventBO.BEGINDATETIME.clear()).setAsPrimaryKey();
	private TypeDate endDatetime = new TypeDate("end_datetime").setFieldDescriptor(EventBO.ENDDATETIME.clear());
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(EventBO.CLERKID.clear()).setNullable();
	private TypeInteger judgeId = new TypeInteger("judge_id").setFieldDescriptor(EventBO.JUDGEID.clear()).setNullable();
	private TypeInteger reporterId = new TypeInteger("reporter_id").setFieldDescriptor(EventBO.REPORTERID.clear()).setNullable();
	private TypeString tapeId = new TypeString("tape_id").setFieldDescriptor(EventBO.TAPEID.clear()).setNullable();
	private TypeString tapeCnt = new TypeString("tape_cnt").setFieldDescriptor(EventBO.TAPECNT.clear()).setNullable();
	private TypeString recordType = new TypeString("record_type").setFieldDescriptor(EventBO.RECORDTYPE.clear()).setNullable();
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(EventBO.MEID.clear()).addForeignKey("case_me","me_id",false).setNullable();
	private TypeString value = new TypeString("value").setFieldDescriptor(EventBO.VALUE.clear()).setNullable();

	public EventVO() {
		super(EventBO.TABLE, EventBO.SYSTEM, EventBO.CORIS_DISTRICT_DB.setSource("D"), EventBO.CORIS_JUSTICE_DB.setSource("J"), EventBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EventBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EventVO(String source) {
		super(EventBO.TABLE, EventBO.SYSTEM, EventBO.CORIS_DISTRICT_DB.setSource("D"), EventBO.CORIS_JUSTICE_DB.setSource("J"), EventBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EventBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(eventCode);
		this.getPropertyList().add(beginDatetime);
		this.getPropertyList().add(endDatetime);
		this.getPropertyList().add(clerkId);
		this.getPropertyList().add(judgeId);
		this.getPropertyList().add(reporterId);
		this.getPropertyList().add(tapeId);
		this.getPropertyList().add(tapeCnt);
		this.getPropertyList().add(recordType);
		this.getPropertyList().add(meId);
		this.getPropertyList().add(value);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(EventBO.BEGINDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(EventBO.BEGINDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(EventBO.ENDDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(EventBO.ENDDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}