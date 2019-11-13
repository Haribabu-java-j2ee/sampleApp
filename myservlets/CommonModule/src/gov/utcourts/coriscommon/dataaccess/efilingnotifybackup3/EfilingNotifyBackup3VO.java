package gov.utcourts.coriscommon.dataaccess.efilingnotifybackup3;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EfilingNotifyBackup3VO extends BaseVO { 

	private TypeInteger notifySrl = new TypeInteger("notify_srl").setFieldDescriptor(EfilingNotifyBackup3BO.NOTIFYSRL.clear());
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(EfilingNotifyBackup3BO.INTCASENUM.clear());
	private TypeString notificationType = new TypeString("notification_type").setFieldDescriptor(EfilingNotifyBackup3BO.NOTIFICATIONTYPE.clear());
	private TypeDate eventDatetime = new TypeDate("event_datetime").setFieldDescriptor(EfilingNotifyBackup3BO.EVENTDATETIME.clear());
	private TypeString eventId = new TypeString("event_id").setFieldDescriptor(EfilingNotifyBackup3BO.EVENTID.clear());
	private TypeString notifyMessage = new TypeString("notify_message").setFieldDescriptor(EfilingNotifyBackup3BO.NOTIFYMESSAGE.clear()).setNullable();

	public EfilingNotifyBackup3VO() {
		super(EfilingNotifyBackup3BO.TABLE, EfilingNotifyBackup3BO.SYSTEM, EfilingNotifyBackup3BO.CORIS_DISTRICT_DB.setSource("D"), EfilingNotifyBackup3BO.CORIS_JUSTICE_DB.setSource("J"), EfilingNotifyBackup3BO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingNotifyBackup3BO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EfilingNotifyBackup3VO(String source) {
		super(EfilingNotifyBackup3BO.TABLE, EfilingNotifyBackup3BO.SYSTEM, EfilingNotifyBackup3BO.CORIS_DISTRICT_DB.setSource("D"), EfilingNotifyBackup3BO.CORIS_JUSTICE_DB.setSource("J"), EfilingNotifyBackup3BO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingNotifyBackup3BO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
			((TypeDate) this.getPropertyList().get(EfilingNotifyBackup3BO.EVENTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(EfilingNotifyBackup3BO.EVENTDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}