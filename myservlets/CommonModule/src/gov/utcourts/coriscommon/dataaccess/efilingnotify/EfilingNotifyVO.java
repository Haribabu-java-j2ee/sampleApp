package gov.utcourts.coriscommon.dataaccess.efilingnotify;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EfilingNotifyVO extends BaseVO { 

	private TypeInteger notifySrl = new TypeInteger("notify_srl").setFieldDescriptor(EfilingNotifyBO.NOTIFYSRL.clear()).setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(EfilingNotifyBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeString notificationType = new TypeString("notification_type").setFieldDescriptor(EfilingNotifyBO.NOTIFICATIONTYPE.clear()).addForeignKey("efiling_notify_type","notification_type",false).setNullable();
	private TypeDate eventDatetime = new TypeDate("event_datetime").setFieldDescriptor(EfilingNotifyBO.EVENTDATETIME.clear()).setNullable();
	private TypeString eventId = new TypeString("event_id").setFieldDescriptor(EfilingNotifyBO.EVENTID.clear()).setNullable();
	private TypeString notifyMessage = new TypeString("notify_message").setFieldDescriptor(EfilingNotifyBO.NOTIFYMESSAGE.clear()).setNullable();

	public EfilingNotifyVO() {
		super(EfilingNotifyBO.TABLE, EfilingNotifyBO.SYSTEM, EfilingNotifyBO.CORIS_DISTRICT_DB.setSource("D"), EfilingNotifyBO.CORIS_JUSTICE_DB.setSource("J"), EfilingNotifyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingNotifyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EfilingNotifyVO(String source) {
		super(EfilingNotifyBO.TABLE, EfilingNotifyBO.SYSTEM, EfilingNotifyBO.CORIS_DISTRICT_DB.setSource("D"), EfilingNotifyBO.CORIS_JUSTICE_DB.setSource("J"), EfilingNotifyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingNotifyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(notifySrl);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(notificationType);
		this.getPropertyList().add(eventDatetime);
		this.getPropertyList().add(eventId);
		this.getPropertyList().add(notifyMessage);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(EfilingNotifyBO.EVENTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(EfilingNotifyBO.EVENTDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}