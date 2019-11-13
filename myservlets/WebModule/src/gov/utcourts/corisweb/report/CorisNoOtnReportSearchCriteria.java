package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.util.WebReportUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class CorisNoOtnReportSearchCriteria extends ReportBaseSearchCriteria {
	
	private boolean allDistrictCourt;
	private boolean allOnLocalDB;
	private boolean thisCourtOnly;
	private Date startDate;
	private Date endDate;
	private int userIdSrl;
	private boolean includeStatus;
	
	public CorisNoOtnReportSearchCriteria(HttpServletRequest request) {
		super(request);
		try {
			WebReportUtil.initBaseReportCriteria(this, request);
			this.userIdSrl = new PersonnelBO(this.getCourtReadOnlyDB()).includeFields(PersonnelBO.USERIDSRL)
							.where(PersonnelBO.LOGNAME,this.getLogName()).find(PersonnelBO.USERIDSRL).getUseridSrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAllDistrictCourt() {
		return allDistrictCourt;
	}
	public void setAllDistrictCourt(boolean allDistrictCourt) {
		this.allDistrictCourt = allDistrictCourt;
	}
	public boolean isAllOnLocalDB() {
		return allOnLocalDB;
	}
	public void setAllOnLocalDB(boolean allOnLocalDB) {
		this.allOnLocalDB = allOnLocalDB;
	}
	public boolean isThisCourtOnly() {
		return thisCourtOnly;
	}
	public void setThisCourtOnly(boolean thisCourtOnly) {
		this.thisCourtOnly = thisCourtOnly;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getUserIdSrl() {
		return userIdSrl;
	}

	public void setUserIdSrl(int userIdSrl) {
		this.userIdSrl = userIdSrl;
	}

	public boolean isIncludeStatus() {
		return includeStatus;
	}

	public void setIncludeStatus(boolean includeStatus) {
		this.includeStatus = includeStatus;
	}

}
