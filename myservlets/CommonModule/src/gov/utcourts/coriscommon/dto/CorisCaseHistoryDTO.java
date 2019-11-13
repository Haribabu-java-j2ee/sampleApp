package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.civilcase.CivilCaseBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.county.CountyBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.summaryevent.SummaryEventBO;


public class CorisCaseHistoryDTO {
	KaseBO		kaseBO;
	ProfileBO 		profileBO;
	CivilCaseBO 	civilCaseBO;
	CrimCaseBO 		crimCaseBO;
	CountyBO 		countyBO;
	CaseTypeBO 		caseTypeBO;
	SummaryEventBO 	summaryEventBO;
	String 			judgeCommName;
	String 			headerRemarks;
	public KaseBO getKaseBO() {
		if (kaseBO == null)
			kaseBO = new KaseBO("D");
		
		return kaseBO;
	}
	public void setKaseBO(KaseBO kaseBO) {
		this.kaseBO = kaseBO;
	}
	public ProfileBO getProfileBO() {
		return profileBO;
	}
	public void setProfileBO(ProfileBO profileBO) {
		this.profileBO = profileBO;
	}
	public CivilCaseBO getCivilCaseBO() {
		return civilCaseBO;
	}
	public void setCivilCaseBO(CivilCaseBO civilCaseBO) {
		this.civilCaseBO = civilCaseBO;
	}
	public CrimCaseBO getCrimCaseBO() {
		return crimCaseBO;
	}
	public void setCrimCaseBO(CrimCaseBO crimCaseBO) {
		this.crimCaseBO = crimCaseBO;
	}
	public CountyBO getCountyBO() {
		return countyBO;
	}
	public void setCountyBO(CountyBO countyBO) {
		this.countyBO = countyBO;
	}
	public CaseTypeBO getCaseTypeBO() {
		return caseTypeBO;
	}
	public void setCaseTypeBO(CaseTypeBO caseTypeBO) {
		this.caseTypeBO = caseTypeBO;
	}
	public SummaryEventBO getSummaryEventBO() {
		return summaryEventBO;
	}
	public void setSummaryEventBO(SummaryEventBO summaryEventBO) {
		this.summaryEventBO = summaryEventBO;
	}
	public String getJudgeCommName() {
		return judgeCommName;
	}
	public void setJudgeCommName(String judgeCommName) {
		this.judgeCommName = judgeCommName;
	}
	public String getHeaderRemarks() {
		return headerRemarks;
	}
	public void setHeaderRemarks(String headerRemarks) {
		this.headerRemarks = headerRemarks;
	}
	
}
