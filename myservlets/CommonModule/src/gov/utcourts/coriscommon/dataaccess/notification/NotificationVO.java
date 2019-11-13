package gov.utcourts.coriscommon.dataaccess.notification;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class NotificationVO extends BaseVO { 

	private TypeString notifyType = new TypeString("notify_type").setFieldDescriptor(NotificationBO.NOTIFYTYPE.clear()).setNullable().setAsPrimaryKey();
	private TypeString emailAddress = new TypeString("email_address").setFieldDescriptor(NotificationBO.EMAILADDRESS.clear()).setNullable().setAsPrimaryKey();
	private TypeString name = new TypeString("name").setFieldDescriptor(NotificationBO.NAME.clear()).setNullable();
	private TypeString organization = new TypeString("organization").setFieldDescriptor(NotificationBO.ORGANIZATION.clear()).setNullable();

	public NotificationVO() {
		super(NotificationBO.TABLE, NotificationBO.SYSTEM, NotificationBO.CORIS_DISTRICT_DB.setSource("D"), NotificationBO.CORIS_JUSTICE_DB.setSource("J"), NotificationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NotificationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public NotificationVO(String source) {
		super(NotificationBO.TABLE, NotificationBO.SYSTEM, NotificationBO.CORIS_DISTRICT_DB.setSource("D"), NotificationBO.CORIS_JUSTICE_DB.setSource("J"), NotificationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NotificationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(notifyType);
		this.getPropertyList().add(emailAddress);
		this.getPropertyList().add(name);
		this.getPropertyList().add(organization);
	}

}