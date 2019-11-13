package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.domesticmodifications.DomesticModificationsBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CorisDomModiPendingReport;
import gov.utcourts.corisweb.report.CorisDomModiPendingReportSearchCriteria;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

/**
 * Servlet implementation class CorisReportDomModiPendingServlet
 */
@WebServlet("/CorisReportDomModiPendingServlet")
public class CorisReportDomModiPendingServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisReportDomModiPendingServlet.class.getName());

    /**
     * Default constructor. 
     */
    public CorisReportDomModiPendingServlet() {
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
		JSONObject retValObj = new JSONObject();
		
		try {
			CorisDomModiPendingReportSearchCriteria criteria = (CorisDomModiPendingReportSearchCriteria) this.generateReportCriteria(request);
			request.setAttribute("rptCriteria", criteria);
			switch (mode) {
				case SAVE:
					saveReport(request, response, criteria);
					break;
				case EMAIL:
					emailReport(request, criteria);
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(retValObj.toString());
					break;
				case FIND:
					request.setAttribute("reportData", getReportData(criteria)); //search database to get list of BOs
					request.getRequestDispatcher("/jsp/corisReportDomModiPendingResults.jsp").forward(request, response);
					break;
				default:
					request.getRequestDispatcher("/jsp/corisReportDomModiPending.jsp?").forward(request, response);
					break;
			}
			retValObj.put("success", true);
		} catch (Exception e) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", "Failed to " + mode + " Domestic Modification Pending report. " + e.getMessage());
			logger.error("Failed to " + mode + " No OTN report: " + e.getMessage());
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(retValObj.toString());
		}
		
	}

	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisDomModiPendingReportSearchCriteria criteria = new CorisDomModiPendingReportSearchCriteria(request);
		criteria.setStartDate(URLEncryption.getParamAsDate(request, "startDate"));
		criteria.setEndDate(URLEncryption.getParamAsDate(request, "endDate"));
		criteria.setReportFileName("Domestic Modification Pending Report");
		criteria.setOrderBy(URLEncryption.getParamAsString(request, "orderBy"));
		return criteria;
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		CorisDomModiPendingReportSearchCriteria rptCriteria = (CorisDomModiPendingReportSearchCriteria)criteria;
		return new CorisDomModiPendingReport(rptCriteria).generateReport(getReportData(rptCriteria));
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		CorisDomModiPendingReportSearchCriteria rptCriteria = (CorisDomModiPendingReportSearchCriteria)criteria;
		List<KaseBO> rptData = new KaseBO(criteria.getCourtReadOnlyDB())
								.includeFields(KaseBO.CASENUM,KaseBO.INTCASENUM, KaseBO.ASSNJUDGEID,KaseBO.ASSNCOMMID, KaseBO.USERIDSRL,KaseBO.COURTTYPE)
								.where(KaseBO.LOCNCODE,criteria.getLocnCode())
								.includeTables(new DomesticModificationsBO(criteria.getCourtReadOnlyDB())
											.includeFields(DomesticModificationsBO.DOCUMENTNUM, DomesticModificationsBO.STARTDATE)
											.where(DomesticModificationsBO.STARTDATE, Exp.BETWEEN, rptCriteria.getStartDate(), Exp.AND, rptCriteria.getEndDate())
											.where(DomesticModificationsBO.DMSTATUS,"P"))
								.addForeignKey(KaseBO.INTCASENUM,DomesticModificationsBO.INTCASENUM).orderBy(DomesticModificationsBO.STARTDATE)
								.toString(BaseConstants.PRINT + BaseConstants.RUN).search();
						
		return rptData;
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emailReport(HttpServletRequest request, CorisDomModiPendingReportSearchCriteria criteria) throws Exception {
		String subject = "Domestic Modification Pending Report";
		String content = "Attached please find the Domestic Modification Pending report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = generateReport(criteria);
		emailReport(subject, content, rprtAttachment, criteria);
		
	}
}
