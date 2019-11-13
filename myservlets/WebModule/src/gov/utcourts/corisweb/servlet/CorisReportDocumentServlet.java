package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.civilcase.CivilCaseBO;
import gov.utcourts.coriscommon.dataaccess.document.DocumentBO;
import gov.utcourts.coriscommon.dataaccess.documenttype.DocumentTypeBO;
import gov.utcourts.coriscommon.dataaccess.documenttypeprofile.DocumentTypeProfileBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.CorisDocumentReport;
import gov.utcourts.corisweb.report.DocumentReportSearchCriteria;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

/**
 * Servlet implementation class CorisReportDocumentServlet
 */
@WebServlet("/CorisReportDocumentServlet")
public class CorisReportDocumentServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisReportDocumentServlet.class.getName());
	
	/**
	 * @see BaseServlet#BaseServlet()
	 */
	public CorisReportDocumentServlet() {
		super();
	}
	
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
		JSONObject retValObj = new JSONObject();
		
		try {
			DocumentReportSearchCriteria criteria = this.generateReportCriteria(request);
			request.setAttribute("rptCriteria", criteria);
			switch (mode) {
				case SAVE: //for both save as pdf and excel buttons
					saveReport(request, response, criteria);
					break;
				case EMAIL: //for both email pdf and excel buttons
					emailReport(request, criteria);
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(retValObj.toString());
					break;
				case FIND: //for Search button on first part of the screen
					request.setAttribute("docList", getReportData(criteria));
					request.setAttribute("courtType", criteria.getCourtType());
					request.setAttribute("withoutImgOnly", criteria.isDocWithoutImgOnly());
					request.getRequestDispatcher("/jsp/corisReportDocumentResults.jsp").forward(request, response);
					break;
				default:
					String locnCode = (String) request.getSession().getAttribute(SessionVariables.LOCN_CODE);
					String courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
					request.setAttribute("personnels",
							new PersonnelBO(courtType).includeFields(PersonnelBO.ALL_FIELDS)
							.where(PersonnelBO.LOCNCODE, locnCode).where(PersonnelBO.REMOVEDFLAG, Exp.NOT_EQUALS, "Y")
							.orderBy(PersonnelBO.LASTNAME).search());
					request.getRequestDispatcher("/jsp/corisReportDocument.jsp?locnCode="+locnCode + "&courtType=" + courtType).forward(request, response);
					break;
			}
			retValObj.put("success", true);
		} catch (Exception e) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", "Failed to " + mode + " document report. " + e.getMessage());
			logger.error("Failed to " + mode + " document report: " + e.getMessage());
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(retValObj.toString());
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		// setup search criteria parameters from request
		DocumentReportSearchCriteria myCriteria = (DocumentReportSearchCriteria)criteria;
		List<DocumentBO> docResults = new ArrayList<DocumentBO>();
		
		DocumentBO docBO = null;
		if (myCriteria.isDocWithoutImgOnly()) {
			docBO = getDocsWithoutImgOnlyQuery(myCriteria);
		} else {
			docBO = getDocsQuery(myCriteria);
		}
		if (myCriteria.getClerkId() > 0) {
			docBO.where(DocumentBO.CLERKID, myCriteria.getClerkId());
		}
		setOrderBy(docBO, myCriteria);
		docResults = docBO.search();
		return docResults;
	}
	
	@Override
	DocumentReportSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		DocumentReportSearchCriteria criteria = new DocumentReportSearchCriteria(request);
		criteria.setClerkId(URLEncryption.getParamAsInt(request, "clerkId"));
		criteria.setStartDate(URLEncryption.getParamAsDate(request, "startDate"));
		criteria.setEndDate(URLEncryption.getParamAsDate(request, "endDate"));
		criteria.setDocWithoutImgOnly("on".equalsIgnoreCase(request.getParameter("withoutImageOnly")));
		criteria.setOrderBy(URLEncryption.getParamAsString(request, "orderBy"));
		criteria.setReportFileName("documentReport");
		return criteria;
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		@SuppressWarnings("unchecked")
		List<DocumentBO> docResults = (List<DocumentBO>) getReportData(criteria);
		return new CorisDocumentReport(criteria).generateReport(docResults);
	}
	
	/**
	 * @param courtType
	 * @return
	 */
	private DocumentBO getDocsQuery(DocumentReportSearchCriteria criteria) {
		String courtDB = criteria.getCourtReadOnlyDB();
		return new DocumentBO(courtDB)//.limit(Constants.MAX_RESULTS)
				.includeFields(DocumentBO.ENTRYDATETIME, DocumentBO.DOCSECURITY, DocumentBO.DMDOCID, DocumentBO.INTCASENUM, DocumentBO.DOCUMENTNUM, DocumentBO.TITLE)
				.where(Exp.DATE,Exp.LEFT_PARENTHESIS,DocumentBO.ENTRYDATETIME, Exp.RIGHT_PARENTHESIS, Exp.BETWEEN, criteria.getStartDate(), Exp.AND, criteria.getEndDate())
				.includeTables(new KaseBO(courtDB).includeFields(KaseBO.CASENUM, KaseBO.CASESECURITY, KaseBO.CASETYPE).where(KaseBO.LOCNCODE, criteria.getLocnCode()),
						new PersonnelBO(courtDB).includeFields(PersonnelBO.LOGNAME), new DocumentTypeBO(courtDB),
						new DocumentTypeProfileBO(courtDB).includeFields(DocumentTypeProfileBO.DDLBENTRY), new CivilCaseBO(courtDB).includeFields(CivilCaseBO.DISCOVERYTIER).setOuter())
				.addForeignKey(KaseBO.INTCASENUM, CivilCaseBO.INTCASENUM).addForeignKey(DocumentBO.INTCASENUM, KaseBO.INTCASENUM).addForeignKey(DocumentTypeBO.CATEGORY, DocumentBO.CATEGORY)
				.addForeignKey(DocumentBO.DETAILCODE, DocumentTypeProfileBO.DOCDETAILCODE).addForeignKey(DocumentBO.CLERKID, PersonnelBO.USERIDSRL);
	}
	
	/**
	 * @param courtType
	 * @return
	 */
	private DocumentBO getDocsWithoutImgOnlyQuery(DocumentReportSearchCriteria criteria) {
		String courtDB = criteria.getCourtReadOnlyDB();
		return new DocumentBO(courtDB)//.limit(Constants.MAX_RESULTS)
				.includeFields(DocumentBO.ENTRYDATETIME, DocumentBO.DOCSECURITY, DocumentBO.DMDOCID, DocumentBO.INTCASENUM, DocumentBO.DOCUMENTNUM, DocumentBO.TITLE)
				.where(Exp.DATE,Exp.LEFT_PARENTHESIS,DocumentBO.ENTRYDATETIME, Exp.RIGHT_PARENTHESIS, Exp.BETWEEN, criteria.getStartDate(), Exp.AND, criteria.getEndDate())
				.includeTables(
						new KaseBO(courtDB).includeFields(KaseBO.CASENUM, KaseBO.CASESECURITY, KaseBO.CASETYPE)
								.where(KaseBO.CASETYPE, Exp.NOT_IN, new String[] {"SL", "TL"})
								.where(KaseBO.LOCNCODE,criteria.getLocnCode()),
						new PersonnelBO(courtDB).includeFields(PersonnelBO.LOGNAME), new DocumentTypeBO(courtDB),
						new DocumentTypeProfileBO(courtDB).includeFields(DocumentTypeProfileBO.DDLBENTRY), 
						new CivilCaseBO(courtDB).includeFields(CivilCaseBO.DISCOVERYTIER).setOuter())
				.addForeignKey(KaseBO.INTCASENUM, CivilCaseBO.INTCASENUM).addForeignKey(DocumentBO.INTCASENUM, KaseBO.INTCASENUM).addForeignKey(DocumentTypeBO.CATEGORY, DocumentBO.CATEGORY)
				.addForeignKey(DocumentBO.DETAILCODE, DocumentTypeProfileBO.DOCDETAILCODE).addForeignKey(DocumentBO.CLERKID, PersonnelBO.USERIDSRL);
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emailReport(HttpServletRequest request, DocumentReportSearchCriteria criteria) throws Exception {
		String subject = "Coris Document Report";
		String content = "Attached please find the document report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
		
	}

	/**
	 * @param orderBy
	 * @return
	 */
	private void setOrderBy(DocumentBO docBO, DocumentReportSearchCriteria reportCriteria) {
		List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(reportCriteria);
		if (orderByPairs != null) {
			for (OrderByPair orderByPair : orderByPairs) {
				switch(orderByPair.getColumnPosition()) {
					case 0:	docBO.orderBy(DocumentBO.ENTRYDATETIME, orderByPair.getDirectionType()); break;
					case 1: docBO.orderBy(KaseBO.CASENUM, orderByPair.getDirectionType()); break;
					case 2: docBO.orderBy(KaseBO.CASETYPE, orderByPair.getDirectionType()); break;
					case 3: docBO.orderBy(CivilCaseBO.DISCOVERYTIER, orderByPair.getDirectionType()); break;
					case 5: docBO.orderBy(PersonnelBO.LOGNAME,orderByPair.getDirectionType()); break;
					case 7: docBO.orderBy(KaseBO.CASESECURITY, orderByPair.getDirectionType()); break;
					case 8: docBO.orderBy(DocumentBO.DOCSECURITY, orderByPair.getDirectionType()); break;
					case 9: docBO.orderBy(DocumentBO.DMDOCID, orderByPair.getDirectionType()); break;
					case 10: docBO.orderBy(DocumentTypeProfileBO.DDLBENTRY, orderByPair.getDirectionType()); break;
					case 11: docBO.orderBy(DocumentBO.TITLE, orderByPair.getDirectionType()); break;
				}
			}
		}
	}


	
}
