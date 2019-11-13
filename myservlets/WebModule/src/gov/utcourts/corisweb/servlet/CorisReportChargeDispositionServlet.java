package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.jdmttype.JdmtTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.summaryevent.SummaryEventBO;
import gov.utcourts.coriscommon.dataaccess.trans.TransBO;
import gov.utcourts.coriscommon.dto.CorisChargeDispositionDTO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CorisReportChargeDisposition;
import gov.utcourts.corisweb.report.CorisReportChargeDispositionSearchCriteria;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;


@WebServlet("/CorisReportChargeDispositionServlet")
public class CorisReportChargeDispositionServlet extends ReportBaseServlet {
	private static final long serialVersionUID = -3335412659844L;
	private Connection conn = null;
	private static Logger logger = Logger.getLogger(CorisReportChargeDispositionServlet.class.getName());

	public static final String SEARCH_PAGE = "/jsp/corisReportChargeDisposition.jsp";
	
	/**
	  * @see HttpServlet#HttpServlet()
	*/
	public CorisReportChargeDispositionServlet() {
	     super();
	}

	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CorisReportChargeDispositionSearchCriteria criteria;
			
			PageMode mode = PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode"));
			
			JSONObject retValObj = new JSONObject();
			retValObj.put("success", true);
			
			criteria = this.generateReportCriteria(request);
			
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
				default:
					screenSetUp(request, response);
					break;
			}	
			mode = null;
		} catch (Exception e){
			logger.error("CorisAttorneyAttachDetachBatchServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
		
	protected void screenSetUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			String locnCode = URLEncryption.getParamAsString(request, "locnCode");
			if (TextUtil.isEmpty(locnCode)){
				locnCode = (String) request.getSession().getAttribute(SessionVariables.LOCN_CODE);
			}
			
			String courtType = URLEncryption.getParamAsString(request, "courtType");
			if (TextUtil.isEmpty(courtType)){
				courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
			}
			
			/////////////////////////////////////////////////////////////////////
			//send to request
			/////////////////////////////////////////////////////////////////////
			request.setAttribute("locationDistrictList", WebReportUtil.getLocationList(request, "D"));
			request.setAttribute("locationJusticeList", WebReportUtil.getLocationList(request, "J"));
			request.setAttribute("jdmtTypeListBO", getJudgmentTypeList(request, "D"));
			request.setAttribute("caseTypeListBO", getCaseTypeList(request, "D"));
			request.setAttribute("locnCode", locnCode);
			request.setAttribute("courtType", courtType);
			request.setAttribute("logName", logName);
			request.setAttribute("ChargeDispositionStatus", URLEncryption.getParamAsString(request, "ChargeDispositionStatus"));
			request.setAttribute("filing", false);
			request.setAttribute("disposition", false);
			
			getServletContext().getRequestDispatcher(SEARCH_PAGE).forward(request, response);
			
			/////////////////////////////////////////////////////////////////////
			//CleanUp
			/////////////////////////////////////////////////////////////////////
			logName = null;
			locnCode = null;
			courtType = null;
			
		} catch (Exception e) {
			logger.error("performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<CorisChargeDispositionDTO> findReportDataResults(CorisReportChargeDispositionSearchCriteria chargeDispositionReportSearchCriteria) throws Exception {
		List<CorisChargeDispositionDTO> corisChargeDispositionListDTO = new ArrayList<CorisChargeDispositionDTO>();
		List<CorisChargeDispositionDTO> corisReturnChargeDispositionListDTO = new ArrayList<CorisChargeDispositionDTO>();
		try {
			
			/////////////////////////////////////////////////////////////////////
			//Connection
			/////////////////////////////////////////////////////////////////////
			if ("D".equals(chargeDispositionReportSearchCriteria.getCourtType())){;
				conn = DatabaseConnection.getConnection(KaseBO.CORIS_DISTRICT_READONLY_DB);
			} else {
				conn = DatabaseConnection.getConnection(KaseBO.CORIS_JUSTICE_READONLY_DB);
			}
			
			KaseBO searchCriteria = new KaseBO(chargeDispositionReportSearchCriteria.getCourtType());
			
			searchCriteria
			.includeTables(
					new CaseTypeBO(chargeDispositionReportSearchCriteria.getCourtType()),
					new PartyCaseBO(chargeDispositionReportSearchCriteria.getCourtType()).includeFields(TransBO.NO_FIELDS),
					new PartyBO(chargeDispositionReportSearchCriteria.getCourtType()),
					new ChargeBO(chargeDispositionReportSearchCriteria.getCourtType()).includeFields(TransBO.NO_FIELDS)
			)
			.addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE)
			.addForeignKey(KaseBO.INTCASENUM, PartyCaseBO.INTCASENUM)
			.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
			.addForeignKey(KaseBO.INTCASENUM, ChargeBO.INTCASENUM)
			.addForeignKey(PartyCaseBO.PARTYNUM, ChargeBO.PARTYNUM)
			.where(ChargeBO.JDMTDATE, Exp.BETWEEN, chargeDispositionReportSearchCriteria.getStartDate(), Exp.AND, chargeDispositionReportSearchCriteria.getEndDate())
			.where(KaseBO.LOCNCODE, chargeDispositionReportSearchCriteria.getLocnCode())
			.where(KaseBO.COURTTYPE, chargeDispositionReportSearchCriteria.getCourtType());
			
			
			if (!TextUtil.isEmpty(chargeDispositionReportSearchCriteria.getCaseType())){
				searchCriteria.where(CaseTypeBO.CASETYPE, chargeDispositionReportSearchCriteria.getCaseType());
			}
			
			
			searchCriteria.orderBy(KaseBO.CASENUM)
			.limit(0)
			.setReturnNull(true)
			.setResultContainer(new CorisChargeDispositionDTO());
			
			
			corisChargeDispositionListDTO = (List<CorisChargeDispositionDTO>) searchCriteria
			.setUnique()
			.searchAndGetResults(
					KaseBO.LOCNCODE, 
					KaseBO.COURTTYPE, 
					KaseBO.INTCASENUM,
					KaseBO.CASENUM,
					KaseBO.CASETYPE,
					KaseBO.FILINGDATE,
					CaseTypeBO.DESCR,
					PartyBO.LASTNAME,
					PartyBO.FIRSTNAME
			);

			if (corisChargeDispositionListDTO != null){
				for (CorisChargeDispositionDTO corisChargeDispositionDTO : corisChargeDispositionListDTO){
					
					ChargeBO searchChargeCriteria = new ChargeBO(chargeDispositionReportSearchCriteria.getCourtType());

					searchChargeCriteria
					.where(ChargeBO.INTCASENUM, corisChargeDispositionDTO.getIntCaseNum());

					if (!TextUtil.isEmpty(chargeDispositionReportSearchCriteria.getJdmtCode())){
						searchChargeCriteria.where(ChargeBO.JDMTCODE, chargeDispositionReportSearchCriteria.getJdmtCode());
					}
					
					searchCriteria
					.limit(0)
					.setReturnNull(true)
					.orderBy(ChargeBO.SEQ);
					
					List<ChargeBO> chargeListBO = (List<ChargeBO>) searchChargeCriteria
					.search();
					
//					List<ChargeBO> chargeListBO = (List<ChargeBO>) new ChargeBO(corisChargeDispositionDTO.getCourtType())
//					.where(ChargeBO.INTCASENUM, corisChargeDispositionDTO.getIntCaseNum())
//					.where(ChargeBO.JDMTCODE, chargeDispositionReportSearchCriteria.getJdmtCode())
//					.orderBy(ChargeBO.SEQ)
//					.search();
					
					if (chargeListBO != null){
						if (chargeListBO.size() > 0){
						
							corisChargeDispositionDTO.setChargeList(chargeListBO);
							
							List<SummaryEventBO> summaryEventListBO = new SummaryEventBO(corisChargeDispositionDTO.getCourtType())
							.where(SummaryEventBO.INTCASENUM, corisChargeDispositionDTO.getIntCaseNum())
							.where(SummaryEventBO.FUNCID, "CHARGE")
							.where(Exp.UPPER, Exp.LEFT_PARENTHESIS, SummaryEventBO.DESCR, Exp.RIGHT_PARENTHESIS, Exp.MATCHES, "CHARGE*")
							.setReturnNull(true)
							.orderBy(SummaryEventBO.CREATEDATETIME)
							.search(SummaryEventBO.CREATEDATETIME, SummaryEventBO.DESCR, SummaryEventBO.KEY1);
		
							corisChargeDispositionDTO.setSummaryEventList(summaryEventListBO);
							corisReturnChargeDispositionListDTO.add(corisChargeDispositionDTO);
							
							summaryEventListBO = null;
						}
					}	
					
					searchChargeCriteria = null;
					chargeListBO = null;
					corisChargeDispositionDTO = null;
				}	
			}
			
			/////////////////////////////////////////////////////////////////////
			//Close Connection
			/////////////////////////////////////////////////////////////////////
			conn.close();
			conn = null;
			
			/////////////////////////////////////////////////////////////////////
			//CleanUp
			/////////////////////////////////////////////////////////////////////
			searchCriteria = null;
			
		} catch (Exception e) {
			logger.error("getCorisChargeDispositionData(HttpServletRequest request, HttpServletResponse response)", e);
		}
		
		
		corisChargeDispositionListDTO = null;
		return corisReturnChargeDispositionListDTO;
	}
	
	@Override
	CorisReportChargeDispositionSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		
		CorisReportChargeDispositionSearchCriteria chargeDispositionReportSearchCriteria = new CorisReportChargeDispositionSearchCriteria(request);
		chargeDispositionReportSearchCriteria.setStartDate(URLEncryption.getParamAsDate(request, "startDate"));
		chargeDispositionReportSearchCriteria.setEndDate(URLEncryption.getParamAsDate(request, "endDate"));
		chargeDispositionReportSearchCriteria.setJdmtCode(URLEncryption.getParamAsString(request, "jdmtType"));
		chargeDispositionReportSearchCriteria.setCaseType(URLEncryption.getParamAsString(request, "caseType"));
		chargeDispositionReportSearchCriteria.setReportFileName(chargeDispositionReportSearchCriteria.getLocnDescr().toUpperCase());
		
		return chargeDispositionReportSearchCriteria;
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static List<JdmtTypeBO> getJudgmentTypeList(HttpServletRequest request, String courtType) throws Exception {
		List<JdmtTypeBO> jdmtTypeListBO = new JdmtTypeBO(courtType)
		.where(JdmtTypeBO.CATEGORY, Exp.IN,new StringArrayDescriptor("R","A"))
		.orderBy(JdmtTypeBO.DESCR)
		.limit(0)
		.search(JdmtTypeBO.DESCR, JdmtTypeBO.JDMTCODE);
		
		return jdmtTypeListBO;
	}
	
	public static List<CaseTypeBO> getCaseTypeList(HttpServletRequest request, String courtType) throws Exception {
		List<CaseTypeBO> caseTypeListBO = new CaseTypeBO(courtType)
		.where(CaseTypeBO.CATEGORY, Exp.IN,new StringArrayDescriptor("R"))
		.orderBy(CaseTypeBO.DESCR)
		.limit(0)
		.search(CaseTypeBO.DESCR, CaseTypeBO.CASETYPE);
		
		return caseTypeListBO;
	}
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emailReport(HttpServletRequest request, CorisReportChargeDispositionSearchCriteria criteria) throws Exception {
		String subject = "Coris ChargeDisposition Report";
		String content = "Attached please find the ChargeDisposition report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
		
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		@SuppressWarnings("unchecked")
		List<CorisChargeDispositionDTO> corisChargeDispositionListDTO = (List<CorisChargeDispositionDTO>) getReportData(criteria);
		return new CorisReportChargeDisposition(criteria).generateReport(corisChargeDispositionListDTO);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return this.findReportDataResults((CorisReportChargeDispositionSearchCriteria) criteria);
	}
}
