package gov.utcourts.coriscommon.dataaccess.autoexpungementemailnotification;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AutoExpungementEmailNotificationVO extends BaseVO { 

	private TypeInteger autoExpungementEmailNotificationId = new TypeInteger("auto_expungement_email_notification_id").setFieldDescriptor(AutoExpungementEmailNotificationBO.AUTOEXPUNGEMENTEMAILNOTIFICATIONID.clear()).setAsPrimaryKey();
	private TypeString fileFormat = new TypeString("file_format").setFieldDescriptor(AutoExpungementEmailNotificationBO.FILEFORMAT.clear());
	private TypeString agencyCode = new TypeString("agency_code").setFieldDescriptor(AutoExpungementEmailNotificationBO.AGENCYCODE.clear());
	private TypeString emailAddress = new TypeString("email_address").setFieldDescriptor(AutoExpungementEmailNotificationBO.EMAILADDRESS.clear());

	public AutoExpungementEmailNotificationVO() {
		super(AutoExpungementEmailNotificationBO.TABLE, AutoExpungementEmailNotificationBO.SYSTEM, AutoExpungementEmailNotificationBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementEmailNotificationBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementEmailNotificationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementEmailNotificationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AutoExpungementEmailNotificationVO(String source) {
		super(AutoExpungementEmailNotificationBO.TABLE, AutoExpungementEmailNotificationBO.SYSTEM, AutoExpungementEmailNotificationBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementEmailNotificationBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementEmailNotificationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementEmailNotificationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(autoExpungementEmailNotificationId);
		this.getPropertyList().add(fileFormat);
		this.getPropertyList().add(agencyCode);
		this.getPropertyList().add(emailAddress);
	}

}