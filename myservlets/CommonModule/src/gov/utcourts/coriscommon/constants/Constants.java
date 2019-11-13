package gov.utcourts.coriscommon.constants;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import gov.utcourts.coriscommon.common.XMLConfig;

public class Constants {

    /**
     * Properties file for Coris Common
     */
    public static final String CORIS_COMMON_PROPERTIES = "/usr/local/WebSphere/AppServer/customlib/properties/CorisCommon_Properties.xml";
    
    public static final String SYSTEM_VERSION = XMLConfig.getProperty("PROPERTY.system_version");
    public static final String WEB_ROOT = "/CorisWEB/";
    
    /**
     * Constructor
     */
    protected Constants() {
    }

    /**
     * Database column names
     */
	public static final SimpleDateFormat dateFormatCoris = new SimpleDateFormat("MM/dd/yyyy");
	public static final SimpleDateFormat timeSecondsFormat = new SimpleDateFormat("hh:mm:ss a");
	public static final SimpleDateFormat dateFormatDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat dateFormatDatetimeMilliseconds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static final String ADDRESS_1 = "address_1";
    public static final String ADDRESS_2 = "address_2";
    public static final String APPEAR_DATE = "appear_date";
    public static final String BADGE_NUM = "badge_num";
    public static final String BAR_NUM = "bar_num";
    public static final String SHERIFF_NUM = "sheriff_num";
    public static final String BAR_STATE = "bar_state";
    public static final String BIRTH_DATE = "birth_date";
    public static final String CASE_NUM = "case_num";
    public static final String CASE_TITLE = "case_title";
    public static final String CASE_TYPE = "case_type";
    public static final String CASE_TYPE_DESCR = "case_type_descr";
    public static final String CASE_TYPE_CATEGORY = "case_type_category";
    public static final String CIT_NUM = "cit_num";
    public static final String CITY = "city";
    public static final String COURT_TITLE = "court_title";
    public static final String COURT_TYPE = "court_type";
    public static final String DESCR = "descr";
    public static final String DISTRICT_COURT_TYPE = "D";
    public static final String EVENT_DESCR = "event_descr";
    public static final String FIRST_NAME = "first_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String HEARING_DESCR = "hearing_descr";
    public static final String INT_CASE_NUM = "intCaseNum";
    public static final String ISSUE_DATE = "issue_date";
    public static final String JUDGE_ID = "judge_id";
    public static final String JUSTICE_COURT_TYPE = "J";
    public static final String LAST_NAME = "last_name";
    public static final String JUDGE_FIRST_NAME = "judge_first_name";
    public static final String JUDGE_LAST_NAME = "judge_last_name";
    public static final String LEA = "lea";
    public static final String LEA_CASE_NUM = "lea_case_num";
    public static final String LOCN_CODE = "locn_code";
    public static final String OFFENCE_DESCR = "offence_descr";
    public static final String OTN = "otn";
    public static final String PARTY_CODE = "party_code";
    public static final String PARTY_NUM = "party_num";
    public static final String PRIORITY = "priority";
    public static final String PROSEC_AGENCY = "prosec_agency";
    public static final String PROSEC_AGENCY_NUM = "prosec_agency_num";
    public static final String RELATED_PARTY_NUM = "related_party_num";
    public static final String ROOM = "room";
    public static final String SEQ = "seq";
    public static final String SEVERITY = "severity";
    public static final String HEARING_TIME = "hearing_time";
    public static final String USERID_SRL = "userid_srl";
    public static final String VIOL_CODE = "viol_code";
    public static final String VIOL_DATETIME = "viol_datetime";
    public static final String DV_FLAG = "dv_flag";
    public static final String DEFENDANT_COUNT = "defendant_count";
    public static final String PLAINTIFF_COUNT = "plaintiff_count";
    public static final String TITLE_PLA_NUM = "title_pla_num";
    public static final String TITLE_DEF_NUM = "title_def_num";
    public static final String CASE_SECURITY = "case_security";
    public static final String DEBT_COLL_NOTE = "debt_coll_note";
    public static final String EDOC_NUM = "edoc_num";
    public static final String DEBT_COLL_USERID = "debt_coll_userid";
    public static final String DEBT_COLL_DATE = "debt_coll_date";
    public static final String LOCAL_DEBT_COLL = "local_debt_coll";
    public static final String TRANSFERRED = "transferred";
    public static final String DEBT_COLLECTION = "debt_collection";
    public static final String TRACK_DATE = "track_date";
    public static final String CAL_STATUS = "cal_status";
    public static final String LINKED_DEF_ID = "linked_def_id";
    public static final String LINKED_CASE_ID = "linked_case_id";
    public static final String NOTE = "note";
    public static final String DOM_VIOLENCE = "dom_violence";
    public static final String DISP_ID = "disp_id";
    public static final String ASSN_COMM_ID = "assn_comm_id";
    public static final String ASSN_JUDGE_ID = "assn_judge_id";
    public static final String ARCHIVE_DATE = "archive_date";
    public static final String REF_NUM = "ref_num";
    public static final String DISP_CODE = "disp_code";
    public static final String DISP_DATE = "disp_date";
    public static final String FILING_DATE = "filing_date";
    public static final String FILING_TYPE = "filing_type";

	public static final String CORIS_LOGNAME = "dpx";

    /**
     * Exceptions
     */
    public static final String ERROR_CONFIGURING_LOG4J = "ERROR configuring Log4J: ";
    public static final String USER_EXCEPTION_MESSAGE = "UserExceptionMessage";
    public static final String BUFFERED_WRITER_EXCEPTION = "Exception in FileUtils.closeBufferedWriter: ";
    public static final String INPUT_STREAM_EXCEPTION = "Exception in FileUtils.closeInputStream: ";
    public static final String FTP_CLIENT_EXCEPTION = "Exception in FileUtils.closeFTPClient: ";

    /**
     * Formats and Syntax
     */
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
	public static SimpleDateFormat longDateTimeTextFormat = new SimpleDateFormat("MMMMM dd, yyyy hh:mm a");
	public static final SimpleDateFormat queryDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat queryDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
	public static SimpleDateFormat longTextFormat = new SimpleDateFormat("MMMMM dd, yyyy");
    public static final DecimalFormat accountingDecimalFormatter = new DecimalFormat("#,##0.00");
    public static final DecimalFormat receiptDecimalFormatter = new DecimalFormat("$##,##0.00");
    public static final boolean APPEND_TO_FILE = true;
    public static final String BLANK_SPACE = "";
    public static final String COMMA = ",";
    public static final String DATE_FORMAT_STRING = "MM-dd-yyyy";
    public static final DecimalFormat defaultDecimalFormat = new DecimalFormat("###0.00");
    public static final long DUPLICATE_RECORD = -12345;
    public static final String FILE_SEPARATOR = "/";
    public static final String IN_CLAUSE = "_IN_CLAUSE_";
    public static final int IN_CLAUSE_SIZE = 1000;
    public static final long IN_USE_AS_FK = -692;
    public static final long INVALID_ID = -1;
    public static final int INVALID_ID_INT = -1;
    public static final String LINE_SEPERATOR = System.getProperty("line.separator");
    public static final String FILE_SEPERATOR = System.getProperty("file.separator");
    public static final String SET_ISOLATION_TO_COMMITTED_READ = "set isolation to committed read";
    public static final String SET_ISOLATION_TO_DIRTY_READ = "set isolation to dirty read";
    public static final String SYSTEM = XMLConfig.getProperty("PROPERTY.system");
    public static final String UNKNOWN = "UNKNOWN";
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String VALUE_OBJECT_TO_BUSINESS_OBJECT = "Exception Building Business Object from Value Object";
    public static final String VALUE_SEPERATOR = "|";
    public static final String NO = "N";
    public static final String YES = "Y";
	public static final String DATE_FORMAT_YEAR_FIRST = "yyyy-MM-dd";
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");
	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MM-dd-yyyy hh:mm a");

	public static final int MAX_RESULTS = Integer.parseInt(XMLConfig.getProperty("PROPERTY.max_results"));
	public static final String RETURN_EMAIL = XMLConfig.getProperty("PROPERTY.return_email");
	public static final int SYSTEM_ID = Integer.parseInt(XMLConfig.getProperty("PROPERTY.system_id"));
	public static final String SYSTEM_NAME = XMLConfig.getProperty("PROPERTY.system_name");
	public static final String ENV = XMLConfig.getProperty("PROPERTY.server.env");
	
	/**	Handle to the 'message' request attribute. */
	public static final String ERROR_MESSAGE = "errorMessage";
	
	public static final String APPLICATION_AUTHENTICAION_WS_ENDPOINT = XMLConfig.getProperty("PROPERTY.web_services.applicationauth_ws.endpoint");
	public static final String APPLICATION_AUTHENTICAION_WS_REQUESTED_SYSTEM = "CorisWEB";
	public static final String APPLICATION_AUTHENTICAION_WS_APPLICATION_KEY = "9Ojdh@JKL8)SSP98jn2uG@!98";
	public static final String DOC_DIRECT_URL = XMLConfig.getProperty("PROPERTY.docdirect.url");
	public static final String DOC_DIRECT_APP_AUTH_KEY = XMLConfig.getProperty("PROPERTY.docdirect.app_auth_ws_key");
	public static final String TRANSACTION_WS_ENDPOINT = XMLConfig.getProperty("PROPERTY.transactionkey_ws_endpoint");
	public static final String DOCMGR_USER = XMLConfig.getProperty("PROPERTY.docdirect.docmgruser");
	
	public static final String MINUTE_ID = "meId";
	
	public static final Map<String, String> CASE_SEC_CODE_MAP = new HashMap<String, String>()
	{
		private static final long serialVersionUID = -4851328128503727682L;

		{
	    	put("U", "PUBLIC");
			put("V", "PRIVATE");
			put("S", "SEALED");
			put("X", "EXPUNGED");
	    };
	};
	
	public static final Map<String, String> DOC_SEC_CODE_MAP = new HashMap<String, String>()
	{
		private static final long serialVersionUID = 6134159720351130622L;

		{
	    	put("U", "PUBLIC");
			put("V", "PRIVATE");
			put("S", "SEALED");
			put("O", "PROTECTED");
	    };
	};

}
