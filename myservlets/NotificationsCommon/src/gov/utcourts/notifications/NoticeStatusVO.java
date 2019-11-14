package gov.utcourts.notifications;

import gov.utcourts.notifications.common.NoticeType;
import gov.utcourts.notifications.dataaccess.BaseVO;

import java.util.Date;

public class NoticeStatusVO extends BaseVO {
	private int statusId;
	private int requestId;
	private int systemId;
	private NoticeType noticeType;
	private String caseMgmtId;
	private String caseMgmtTable;
	private String sendTo;
	private String sendFrom;
	private String sendCc;
	private String sendBcc;
	private String subject;
	private Date dateSent;
	private boolean processed = false;
	private boolean attachment = false;
	private String court;
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getRequestId() {
		return requestId;
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
	public NoticeType getNoticeType() {
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
	public Date getDateSent() {
		return dateSent;
	}
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}
	public boolean isProcessed() {
		return processed;
	}
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	public boolean isAttachment() {
		return attachment;
	}
	public void setAttachment(boolean attachment) {
		this.attachment = attachment;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
}
