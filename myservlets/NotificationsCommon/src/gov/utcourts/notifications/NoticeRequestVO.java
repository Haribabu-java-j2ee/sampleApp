package gov.utcourts.notifications;

import gov.utcourts.notifications.common.NoticeType;
import gov.utcourts.notifications.dataaccess.BaseVO;
import gov.utcourts.notifications.util.TextUtil;

import java.util.Date;

public class NoticeRequestVO extends BaseVO {
	private int requestId;
	private int systemId;
	private int attempts;
	private NoticeType noticeType;
	private String caseMgmtId;
	private String caseMgmtTable;
	private String sendTo;
	private String sendFrom;
	private String sendCc;
	private String sendBcc;
	private String subject;
	private String content;
	private String filename;
	private byte[] attachment;
	private Date scheduledTime;
	private String court;
	
	public int getRequestId() {
		return requestId;
	}
	public String getRequestIdAsString() {
		return Integer.toString(requestId);
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getSystemId() {
		return systemId;
	}
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	public NoticeType getNoticeType() {
		if (noticeType == null)
			noticeType = NoticeType.EMAIL;
			
		return noticeType;
	}
	public void setNoticeType(NoticeType noticeType) {
		this.noticeType = noticeType;
	}
	public String getCaseMgmtId() {
		return caseMgmtId;
	}
	public void setCaseMgmtId(String caseMgmtId) {
		this.caseMgmtId = caseMgmtId;
	}
	public String getCaseMgmtTable() {
		return caseMgmtTable;
	}
	public void setCaseMgmtTable(String caseMgmtTable) {
		if (TextUtil.isEmpty(caseMgmtTable)) 
			caseMgmtTable = "";
			
		this.caseMgmtTable = caseMgmtTable;
	}
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getSendFrom() {
		return sendFrom;
	}
	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}
	public String getSendCc() {
		return sendCc;
	}
	public void setSendCc(String sendCc) {
		this.sendCc = sendCc;
	}
	public String getSendBcc() {
		return sendBcc;
	}
	public void setSendBcc(String sendBcc) {
		this.sendBcc = sendBcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getAttachment() {
		return attachment;
	}
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	public Date getScheduledTime() {
		return scheduledTime;
	}
	public String getScheduledTimeAsString() {
		return TextUtil.printDatetime(scheduledTime, TextUtil.DEFAULT_DATETIME_FORMAT);
	}
	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
}
