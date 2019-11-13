package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CorisDomViolReport;
import gov.utcourts.corisweb.report.CorisDomViolReportCriteria;
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
 * Servlet implementation class CorisReportDomViolServlet
 */
@WebServlet("/CorisReportDomViolServlet")
public class CorisReportDomViolServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisReportDomViolServlet.class.getName());
    /**
     * Default constructor. 
     */
    public CorisReportDomViolServlet() {
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
		JSONObject retValObj = new JSONObject();
		
		try {
			CorisDomViolReportCriteria criteria = (CorisDomViolReportCriteria) this.generateReportCriteria(request);
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
					request.getRequestDispatcher("/jsp/corisReportDomViolResults.jsp").forward(request, response);
					break;
				default:
					request.getRequestDispatcher("/jsp/corisReportDomViol.jsp?").forward(request, response);
					break;
			}
			retValObj.put("success", true);
		} catch (Exception e) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", "Failed to " + mode + " document report. " + e.getMessage());
			logger.error("Failed to " + mode + " Domestic Violence report: " + e.getMessage());
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(retValObj.toString());
		}
		
	}

	private void emailReport(HttpServletRequest request, CorisDomViolReportCriteria criteria) throws Exception {
		String subject = "Domestic Violence Report";
		String content = "Attached please find the Domestic Violence Report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = generateReport(criteria);
		emailReport(subject, content, rprtAttachment, criteria);
	}

	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisDomViolReportCriteria criteria = new CorisDomViolReportCriteria(request);
		criteria.setStartDate(URLEncryption.getParamAsDate(request, "startDate"));
		criteria.setEndDate(URLEncryption.getParamAsDate(request, "endDate"));
		criteria.setFilingDateType("on".equalsIgnoreCase(URLEncryption.getParamAsString(request, "fileDate"))?true:false);
		criteria.setDispositionDateType("on".equalsIgnoreCase(URLEncryption.getParamAsString(request, "dispDate"))?true:false);
		criteria.setReportFileName("Domestic Violence Report");
		return criteria;
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		List<KaseBO> reportData = (List<KaseBO>)getReportData(criteria);
		return new CorisDomViolReport(criteria).generateReport(reportData);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		CorisDomViolReportCriteria rptCriteria = (CorisDomViolReportCriteria) criteria;
		List<KaseBO> results = null;
		if(rptCriteria.isFilingDateType()){
			results = new KaseBO(rptCriteria.getCourtReadOnlyDB())
							.includeFields(KaseBO.INTCASENUM, KaseBO.CASENUM, KaseBO.CASETYPE,KaseBO.FILINGDATE)
							.where(KaseBO.LOCNCODE,rptCriteria.getLocnCode())
							.where(KaseBO.FILINGDATE, Exp.BETWEEN, rptCriteria.getStartDate(), Exp.AND, rptCriteria.getEndDate())
							.includeTables(new PartyBO(rptCriteria.getCourtReadOnlyDB())
							                   .includeFields(PartyBO.FIRSTNAME,PartyBO.LASTNAME),
							               new PartyCaseBO(rptCriteria.getCourtReadOnlyDB())
												.where(PartyCaseBO.PARTYCODE,"DEF"),
							               new ChargeBO(rptCriteria.getCourtReadOnlyDB())
							                   .includeFields(ChargeBO.SEQ,ChargeBO.JDMTDATE))
						   .addForeignKey(KaseBO.INTCASENUM,PartyCaseBO.INTCASENUM)
						   .addForeignKey(KaseBO.INTCASENUM,ChargeBO.INTCASENUM)
						   .addForeignKey(PartyCaseBO.PARTYNUM,PartyBO.PARTYNUM)
						   .toString(BaseConstants.PRINT + BaseConstants.RUN).search();
		}else if(rptCriteria.isDispositionDateType()){
			results = new KaseBO(rptCriteria.getCourtReadOnlyDB())
			.includeFields(KaseBO.INTCASENUM, KaseBO.CASENUM, KaseBO.CASETYPE,KaseBO.FILINGDATE)
			.where(KaseBO.LOCNCODE,rptCriteria.getLocnCode())
			.includeTables(new PartyBO(rptCriteria.getCourtReadOnlyDB())
			                   .includeFields(PartyBO.FIRSTNAME,PartyBO.LASTNAME),
			               new PartyCaseBO(rptCriteria.getCourtReadOnlyDB())
								.where(PartyCaseBO.PARTYCODE,"DEF"),
			               new ChargeBO(rptCriteria.getCourtReadOnlyDB())
			                   .includeFields(ChargeBO.SEQ,ChargeBO.JDMTDATE)
			                   .where(ChargeBO.JDMTDATE, Exp.BETWEEN, rptCriteria.getStartDate(), Exp.AND, rptCriteria.getEndDate()))
		   .addForeignKey(KaseBO.INTCASENUM,PartyCaseBO.INTCASENUM)
		   .addForeignKey(KaseBO.INTCASENUM,ChargeBO.INTCASENUM)
		   .addForeignKey(PartyCaseBO.PARTYNUM,PartyBO.PARTYNUM)
		   .toString(BaseConstants.PRINT + BaseConstants.RUN).search();
		}
		return results;
	}

}
