package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.notifications.NoticeRequestBO;
import gov.utcourts.notifications.common.NoticeType;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ReportBaseServlet extends BaseServlet {
	
	private static final long serialVersionUID = -3818542787162555768L;
	
	abstract ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception;
	
	abstract byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception;
	
	abstract List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception;
	
	protected void emailReport(String subject, String content, byte[] rprtAttachment, ReportBaseSearchCriteria criteria) throws Exception {
		// construct the text body part
		PersonnelBO user = new PersonnelBO(criteria.getCourtType()).includeFields(PersonnelBO.USERIDSRL, PersonnelBO.EMAILADDRESS).where(PersonnelBO.LOGNAME, criteria.getLogName())
				.where(PersonnelBO.EMAILADDRESS, Exp.IS_NOT_NULL).find();
		
		if (!TextUtil.isEmpty(user.getEmailAddress())) {
			NoticeRequestBO noticeRequestBO = new NoticeRequestBO();
			
			noticeRequestBO.setSystemId(Constants.SYSTEM_ID);
			noticeRequestBO.setNoticeType(NoticeType.EMAIL);
			noticeRequestBO.setCaseMgmtId(String.valueOf(user.getUseridSrl())); //TODO: verify unique constraints?
			noticeRequestBO.setCaseMgmtTable("personnel"); //TODO:?
			noticeRequestBO.setSendTo(user.getEmailAddress());
			noticeRequestBO.setSendFrom("courts@utcourts.gov"); //TODO: get it from property file
			noticeRequestBO.setSubject(subject);
			noticeRequestBO.setContent(content);
			noticeRequestBO.setAttachment(rprtAttachment);
			noticeRequestBO.setFilename(criteria.getReportFileName() + ("pdf".equalsIgnoreCase(criteria.getReportformat()) ? ".pdf" : ".xlsx"));
			noticeRequestBO.setCourt(criteria.getCourtType());
			NoticeRequestBO.insert(noticeRequestBO, "static".equals("jndi"));
			noticeRequestBO = null;
		} else {
			throw new Exception("User Email address is not set.");
		}
	}
	
	protected void saveReport(HttpServletRequest request, HttpServletResponse response, ReportBaseSearchCriteria criteria) throws Exception {
		byte[] reportData = this.generateReport(criteria);
		if(reportData != null){
			String format = criteria.getReportformat();
			String fileName = criteria.getReportFileName();
			response.setContentType("application/" + ("pdf".equalsIgnoreCase(format) ? "pdf" : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
			response.setHeader("Content-Disposition", ("pdf".equalsIgnoreCase(format) ? "inline; filename="+ fileName +".pdf" : "attachment; filename="+ fileName +".xlsx"));
			ServletOutputStream sout = response.getOutputStream();
			sout.write(reportData);
			sout.flush();
			sout.close();
		}else {
			throw new Exception("Failed to generate report " + criteria.getReportFileName());
		}
	}
	
}
