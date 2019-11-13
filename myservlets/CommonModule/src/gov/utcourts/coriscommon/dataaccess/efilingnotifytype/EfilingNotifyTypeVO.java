package gov.utcourts.coriscommon.dataaccess.efilingnotifytype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EfilingNotifyTypeVO extends BaseVO { 

	private TypeString notificationType = new TypeString("notification_type").setFieldDescriptor(EfilingNotifyTypeBO.NOTIFICATIONTYPE.clear()).addForeignKey("efiling_notify","notification_type",true).setAsPrimaryKey();
	private TypeString certifyNef = new TypeString("certify_nef").setFieldDescriptor(EfilingNotifyTypeBO.CERTIFYNEF.clear());

	public EfilingNotifyTypeVO() {
		super(EfilingNotifyTypeBO.TABLE, EfilingNotifyTypeBO.SYSTEM, EfilingNotifyTypeBO.CORIS_DISTRICT_DB.setSource("D"), EfilingNotifyTypeBO.CORIS_JUSTICE_DB.setSource("J"), EfilingNotifyTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingNotifyTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EfilingNotifyTypeVO(String source) {
		super(EfilingNotifyTypeBO.TABLE, EfilingNotifyTypeBO.SYSTEM, EfilingNotifyTypeBO.CORIS_DISTRICT_DB.setSource("D"), EfilingNotifyTypeBO.CORIS_JUSTICE_DB.setSource("J"), EfilingNotifyTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingNotifyTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(notificationType);
		this.getPropertyList().add(certifyNef);
	}

}