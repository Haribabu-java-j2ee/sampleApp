package gov.utcourts.coriscommon.dto;

import java.util.Date;

public class SummaryEventDTO{
	private int	status;
	private int	docSeq;
	private String eventDatetime;
	private String createDatetime;
	private String funcId;
	private String descr;
	private String key1;
	private String key2;
	private String key3;
	private String key4;
	private int dmDocId;
	private String security;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDocSeq() {
		return docSeq;
	}
	public void setDocSeq(int docSeq) {
		this.docSeq = docSeq;
	}
	public String getEventDatetime() {
		return eventDatetime;
	}
	public void setEventDatetime(String eventDatetime) {
		this.eventDatetime = eventDatetime;
	}
	public String getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(String createDatetime) {
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
	public int getDmDocId() {
		return dmDocId;
	}
	public void setDmDocId(int dmDocId) {
		this.dmDocId = dmDocId;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
}
