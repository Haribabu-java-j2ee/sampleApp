package gov.utcourts.notifications;

import gov.utcourts.notifications.dataaccess.BaseVO;

import java.util.Date;

public class NoticeSubscribeVO extends BaseVO {
	private int subscribeId;
	private int systemId;
	private String caseMgmtId;
	private String caseMgmtTable;
	private String mobile;
	private Date mobileVerified;
	private String email;
	private Date emailVerified;
	private String smsAddress;
	private String smsProvider;

	public int getSubscribeId() {
		return subscribeId;
	}
	public void setSubscribeId(int subscribeId) {
		this.subscribeId = subscribeId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSmsAddress() {
		return smsAddress;
	}
	public void setSmsAddress(String smsAddress) {
		this.smsAddress = smsAddress;
	}
	public String getSmsProvider() {
		return smsProvider;
	}
	public void setSmsProvider(String smsProvider) {
		this.smsProvider = smsProvider;
	}
	public int getSystemId() {
		return systemId;
	}
	public String getSystemIdAsString() {
		return Integer.toString(systemId);
	}
	public void setSystemId(int systemId) {
		this.systemId = systemId;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getMobileVerified() {
		return mobileVerified;
	}
	public void setMobileVerified(Date mobileVerified) {
		this.mobileVerified = mobileVerified;
	}
	public Date getEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(Date emailVerified) {
		this.emailVerified = emailVerified;
	}
}
