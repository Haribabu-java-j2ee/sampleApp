package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.summaryevent.SummaryEventBO;
import gov.utcourts.coriscommon.util.TextUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CorisSingleReportParameters {

	String meId;
	int intCaseNum;
	String caseNumber;
	String courtType;
	String logName;
	String locnCode;
	String funcId;
	String descr;
	String key1;
	String key2;
	String key3;
	String key4;
	Date createDateTime;
	Date eventDateTime;
	HttpServletResponse response = null;
	String email = null;
	String sign = null;
	
	public CorisSingleReportParameters(String meId, String caseNumber, String courtType, String logName, String locnCode, String funcId) throws Exception{
		this.meId = meId;
		this.caseNumber = caseNumber;
		this.courtType = courtType;
		this.logName = logName;
		this.locnCode = locnCode;
		this.funcId = funcId;
		
		KaseBO kaseBO = new KaseBO(courtType).
		where(KaseBO.CASENUM, caseNumber).
		where(KaseBO.LOCNCODE, locnCode).
		where(KaseBO.COURTTYPE, courtType).
		find();
		
		SummaryEventBO summaryEventBO = new SummaryEventBO(courtType).
		where(SummaryEventBO.INTCASENUM, kaseBO.getIntCaseNum()).
		where(SummaryEventBO.FUNCID, funcId).
		where(SummaryEventBO.KEY1, meId).
		find();
		
		this.intCaseNum = summaryEventBO.getIntCaseNum();
		this.funcId = summaryEventBO.getFuncId();
		this.createDateTime = summaryEventBO.getCreateDatetime();
		this.eventDateTime = summaryEventBO.getEventDatetime();
		this.descr = summaryEventBO.getDescr();
		this.key1 = summaryEventBO.getKey1();
		this.key2 = summaryEventBO.getKey2();
		this.key3 = summaryEventBO.getKey3();
		this.key4 = summaryEventBO.getKey4();
		this.email = "N";
		this.sign = "Y";		
	}
	
	public CorisSingleReportParameters(HttpServletRequest request, HttpServletResponse response){
		this.response = response;
		this.meId = getParameterString(request, "meId"); 
		this.intCaseNum = getParameterInt(request, "intCaseNum");
		this.caseNumber = getParameterString(request, "caseNum");
		this.courtType = getParameterString(request, "courtType");
		this.logName = getParameterString(request, "logName");
		this.locnCode = getParameterString(request, "locnCode");
		this.funcId =  getParameterString(request, "funcId");
		
		this.createDateTime = getParameterDateTime(request, "createDatetime", "yyyy-MM-dd HH:mm:ss.SSS");
		this.eventDateTime = getParameterDateTime(request, "eventDatetime", "yyyy-MM-dd HH:mm:ss.SSS");
		this.descr = getParameterString(request, "descr");
		this.key1 = getParameterString(request, "key1");
		this.key2 = getParameterString(request, "key2");
		this.key3 = getParameterString(request, "key3");
		this.key4 = getParameterString(request, "key4");
		if (TextUtil.isEmpty(getParameterString(request, "email"))){
			this.email = "N";
		} else {
			this.email = getParameterString(request, "email");
		}
		if (TextUtil.isEmpty(getParameterString(request, "sign"))){
			this.sign = "N";
		} else {
			this.sign = getParameterString(request, "sign");
		}
	}
	
	private static String getParameterString(HttpServletRequest request, String key) {
		String value = TextUtil.getEncryptedParamAsString(request, key);
		if (TextUtil.isEmpty(value))
			value = TextUtil.getParamAsString(request, key);
		
		return value;
	}
	
	private static int getParameterInt(HttpServletRequest request, String key) {
		int value = TextUtil.getEncryptedParamAsInt(request, key);
		if (value == 0)
			value = TextUtil.getParamAsInt(request, key);
		
		return value;
	}
	
	private static Date getParameterDateTime(HttpServletRequest request, String key, String dateFormat) {
		Date value = TextUtil.getEncryptedParamAsDate(request, key, dateFormat);
		if (value == null)
			value = TextUtil.getParamAsDate(request, key, dateFormat);
		
		return value;
	}
	
	public String getMeId() {
		return meId;
	}
	public void setMeId(String meId) {
		this.meId = meId;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCourtType() {
		return courtType;
	}
	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public String getLocnCode() {
		return locnCode;
	}
	public void setLocnCode(String locnCode) {
		this.locnCode = locnCode;
	}
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	public int getIntCaseNum() {
		return intCaseNum;
	}
	public void setIntCaseNum(int intCaseNum) {
		this.intCaseNum = intCaseNum;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
	}
	public String getKey3() {
		return key3;
	}
	public void setKey3(String key3) {
		this.key3 = key3;
	}
	public String getKey4() {
		return key4;
	}
	public void setKey4(String key4) {
		this.key4 = key4;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(Date eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
