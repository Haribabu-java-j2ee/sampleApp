package gov.utcourts.coriscommon.dataaccess.efilingnotifybackup;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EfilingNotifyBackupVO extends BaseVO { 

	private TypeInteger notifySrl = new TypeInteger("notify_srl").setFieldDescriptor(EfilingNotifyBackupBO.NOTIFYSRL.clear());
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(EfilingNotifyBackupBO.INTCASENUM.clear());
	private TypeString notificationType = new TypeString("notification_type").setFieldDescriptor(EfilingNotifyBackupBO.NOTIFICATIONTYPE.clear());
	private TypeDate eventDatetime = new TypeDate("event_datetime").setFieldDescriptor(EfilingNotifyBackupBO.EVENTDATETIME.clear());
	private TypeString eventId = new TypeString("event_id").setFieldDescriptor(EfilingNotifyBackupBO.EVENTID.clear());
	private TypeString notifyMessage = new TypeString("notify_message").setFieldDescriptor(EfilingNotifyBackupBO.NOTIFYMESSAGE.clear()).setNullable();

	public EfilingNotifyBackupVO() {
		super(EfilingNotifyBackupBO.TABLE, EfilingNotifyBackupBO.SYSTEM, EfilingNotifyBackupBO.CORIS_DISTRICT_DB.setSource("D"), EfilingNotifyBackupBO.CORIS_JUSTICE_DB.setSource("J"), EfilingNotifyBackupBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingNotifyBackupBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EfilingNotifyBackupVO(String source) {
		super(EfilingNotifyBackupBO.TABLE, EfilingNotifyBackupBO.SYSTEM, EfilingNotifyBackupBO.CORIS_DISTRICT_DB.setSource("D"), EfilingNotifyBackupBO.CORIS_JUSTICE_DB.setSource("J"), EfilingNotifyBackupBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingNotifyBackupBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
			((TypeDate) this.getPropertyList().get(EfilingNotifyBackupBO.EVENTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(EfilingNotifyBackupBO.EVENTDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}