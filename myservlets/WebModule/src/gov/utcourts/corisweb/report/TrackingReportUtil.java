package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;

public class TrackingReportUtil {
	
	public static String getJudgeCommName(ReportBaseSearchCriteria searchCriteria, String judgeComm) {
		if (!TextUtil.isEmpty(judgeComm)) {
			try {
				return new PersonnelBO(searchCriteria.getCourtType())
				.where(PersonnelBO.LOCNCODE, searchCriteria.getLocnCode())
				.where(PersonnelBO.COURTTYPE, searchCriteria.getCourtType())
				.where(PersonnelBO.LOGNAME, judgeComm)
				.find()
				.getLastName();
			} catch(Exception e) {
				return "";
			}
		} else {
			return "Judge";
		}
	}
}
