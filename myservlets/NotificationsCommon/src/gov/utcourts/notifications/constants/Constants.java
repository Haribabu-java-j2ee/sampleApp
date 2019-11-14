package gov.utcourts.notifications.constants;

import gov.utcourts.notifications.common.XMLConfig;
import gov.utcourts.notifications.util.ValidationUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public interface Constants {
	/** Id used to specify the id is invalid. */
	public static final long INVALID_ID = -1;
	/** Id used to specify the id is invalid. */
	public static final int INVALID_ID_INT = -1;
	/** used to format dates for display.*/
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	/** Used to format dates for viewing the time only.*/
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
	public static final SimpleDateFormat hourMinuteSecFormat = new SimpleDateFormat("hh:mm:ss");
	/** Used to format dates for time and date.*/
	public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
	/** Used to parse and format the hour.*/
	public static SimpleDateFormat hourFormat = new SimpleDateFormat("hh");
	/** Used to parse and format the minute.*/
	public static SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
	/** Used to parse and format the meridiem.*/
	public static SimpleDateFormat meridiemFormat = new SimpleDateFormat("a");
	/** Used to parse and format the meridiem.*/
	public static SimpleDateFormat longTextFormat = new SimpleDateFormat("MMMMM dd, yyyy");
	/** Used to format decimals. */
	public static final DecimalFormat accountingDecimalFormatter = new DecimalFormat("#,##0.00");
	/** String format for dates in care.*/
	public static final String dateFormatString = "yyyy-MM-dd";
	public static final SimpleDateFormat dateSlashFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public static final String UNKNOWN = "UNKNOWN";
	
	public static final SimpleDateFormat hourMinute = new SimpleDateFormat("hh:mm");
	public static final SimpleDateFormat dateTimeLong = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
	/** this will get us a 0-23 hr format */
	public static final SimpleDateFormat dateTimeKLong = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat militaryTimeFormat = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat hourTimeFormat = new SimpleDateFormat("hh:mm");
	public static final SimpleDateFormat MilitaryMinuteSecFormat = new SimpleDateFormat("HH:mm:ss");
	
	public static final String dateTimeLongStr = "yyyy-MM-dd hh:mm";
	public static final String dateTimeKLongStr = "yyyy-MM-dd HH:mm";
	public static final String hourMinuteString = "hh:mm";
	public static final String MilitaryMinuteString = "HH:mm";
	public static final String timeFormatString = "hh:mm a";
	
	public final static SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	public final static SimpleDateFormat yearFmt = new SimpleDateFormat("yyyy");
	
	public static final String RESULTS = "results";
	public static final String FIRST_TIME = "firstTime";
	
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
	
	public static final String APPLICATION_AUTHENTICAION_WS_ENDPOINT = XMLConfig.getProperty("PROPERTY.applicationauth_ws.endpoint");
	public static final String APPLICATION_AUTHENTICAION_REQUESTED_SYSTEM = XMLConfig.getProperty("PROPERTY.applicationauth_ws.requested_system");
	public static final String APPLICATION_AUTHENTICATION_KEY_FOR_NOTIFICATIONS = XMLConfig.getProperty("PROPERTY.applicationauth_ws.notifications_key");
	
	public static final String SYSTEM_VERSION = XMLConfig.getProperty("PROPERTY.notifications.system_version");
	
	public static final String COMMONEMAILVERIFY_WEB_URL = XMLConfig.getProperty("PROPERTY.commonemailverify.web_url");
	public static final String COMMONEMAILVERIFY_HELPDESK_EMAIL = XMLConfig.getProperty("PROPERTY.commonemailverify.helpdesk_email");
	
	public static final String PAGE_VARIABLES = "pageVariables";
	
	public static final String EMAIL_WS_ENDPOINT = XMLConfig.getProperty("PROPERTY.email_ws.endpoint");
	public static final String EMAIL_WS_SENDEREMAIL = XMLConfig.getProperty("PROPERTY.email_ws.senderemail");
	public static final String EMAIL_WS_CALLING_SYSYEM = XMLConfig.getProperty("PROPERTY.email_ws.calling_system");
	public static final String EMAIL_WS_SECURITY_CERT_SYSTEM = XMLConfig.getProperty("PROPERTY.email_ws.security_cert_system");
	public static final String EMAIL_WS_SECURITY_CERT_TEST_PASSWORD = XMLConfig.getProperty("PROPERTY.email_ws.security_cert_test_password");
	public static final String EMAIL_WS_SECURITY_CERT_PROD_PASSWORD = XMLConfig.getProperty("PROPERTY.email_ws.security_cert_prod_password");
	
	public static final String SMS_VERIFICATION_URL = XMLConfig.getProperty("PROPERTY.sms_verification.url");
	public static final String SMS_VERIFICATION_USER = XMLConfig.getProperty("PROPERTY.sms_verification.user");
	public static final String SMS_VERIFICATION_PASSWORD = XMLConfig.getProperty("PROPERTY.sms_verification.password");
	
	public static final int MAIL_FILESIZE = Integer.parseInt(XMLConfig.getProperty("PROPERTY.mail.file.limit"));
	public static final List<String> MAIL_FILE_EXTENSIONS = ValidationUtil.getMailExtensions();
	
	public static final int MAXIMUM_NUMBER_OF_EMAIL_ATTEMPTS = Integer.parseInt(XMLConfig.getProperty("PROPERTY.notifications.maximum_number_of_attempts"));
	
	public static final String NOTIFICATIONS = "Notifications";
	public static final String NOTIFICATIONS_CONTEXT = XMLConfig.getProperty("PROPERTY.web_context.notifications");
	
	public static final String TRANSACTION_KEY_WS_ENDPOINT = XMLConfig.getProperty("PROPERTY.transactionkey_ws.endpoint");
	public static final String SYSTEMS_WITH_NO_NOTICE_STATUS_UPDATES = XMLConfig.getProperty("PROPERTY.excluded_systems.noticestatus_update");
}
