package gov.utcourts.coriscommon.dto;

import java.util.Date;

public class SummaryEventCaseHistoryDTO{
	private int	intCaseNum;
	private Date eventDatetime;
	private Date createDatetime;
	private String funcId;
	private String descr;
	private String key1;
	private String key2;
	private String key3;
	private String key4;
	private String chText;
	private int clerkId;
	private long dmDocId;
	private String docSecurity;
	private long meId;
	private String meSecurity;
	
	public int getIntCaseNum() {
		return intCaseNum;
	}
	public void setIntCaseNum(int intCaseNum) {
		this.intCaseNum = intCaseNum;
	}
	public Date getEventDatetime() {
		return eventDatetime;
	}
	public void setEventDatetime(Date eventDatetime) {
		this.eventDatetime = eventDatetime;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
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
	public String getChText() {
		return chText;
	}
	public void setChText(String chText) {
		this.chText = chText;
	}
	public int getClerkId() {
		return clerkId;
	}
	public void setClerkId(int clerkId) {
		this.clerkId = clerkId;
	}
	public long getDmDocId() {
		return dmDocId;
	}
	public void setDmDocId(long dmDocId) {
		this.dmDocId = dmDocId;
	}
	public String getDocSecurity() {
		return docSecurity;
	}
	public void setDocSecurity(String docSecurity) {
		this.docSecurity = docSecurity;
	}
	public long getMeId() {
		return meId;
	}
	public void setMeId(long meId) {
		this.meId = meId;
	}
	public String getMeSecurity() {
		return meSecurity;
	}
	public void setMeSecurity(String meSecurity) {
		this.meSecurity = meSecurity;
	}
}
