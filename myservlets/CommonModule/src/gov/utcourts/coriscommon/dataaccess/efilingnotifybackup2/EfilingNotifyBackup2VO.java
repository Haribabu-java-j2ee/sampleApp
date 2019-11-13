package gov.utcourts.coriscommon.dataaccess.efilingnotifybackup2;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EfilingNotifyBackup2VO extends BaseVO { 

	private TypeInteger notifySrl = new TypeInteger("notify_srl").setFieldDescriptor(EfilingNotifyBackup2BO.NOTIFYSRL.clear());
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(EfilingNotifyBackup2BO.INTCASENUM.clear());
	private TypeString notificationType = new TypeString("notification_type").setFieldDescriptor(EfilingNotifyBackup2BO.NOTIFICATIONTYPE.clear());
	private TypeDate eventDatetime = new TypeDate("event_datetime").setFieldDescriptor(EfilingNotifyBackup2BO.EVENTDATETIME.clear());
	private TypeString eventId = new TypeString("event_id").setFieldDescriptor(EfilingNotifyBackup2BO.EVENTID.clear());
	private TypeString notifyMessage = new TypeString("notify_message").setFieldDescriptor(EfilingNotifyBackup2BO.NOTIFYMESSAGE.clear()).setNullable();

	public EfilingNotifyBackup2VO() {
		super(EfilingNotifyBackup2BO.TABLE, EfilingNotifyBackup2BO.SYSTEM, EfilingNotifyBackup2BO.CORIS_DISTRICT_DB.setSource("D"), EfilingNotifyBackup2BO.CORIS_JUSTICE_DB.setSource("J"), EfilingNotifyBackup2BO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingNotifyBackup2BO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EfilingNotifyBackup2VO(String source) {
		super(EfilingNotifyBackup2BO.TABLE, EfilingNotifyBackup2BO.SYSTEM, EfilingNotifyBackup2BO.CORIS_DISTRICT_DB.setSource("D"), EfilingNotifyBackup2BO.CORIS_JUSTICE_DB.setSource("J"), EfilingNotifyBackup2BO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingNotifyBackup2BO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
			((TypeDate) this.getPropertyList().get(EfilingNotifyBackup2BO.EVENTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(EfilingNotifyBackup2BO.EVENTDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}