package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dto.CorisMoneyWithOutACaseDTO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CorisReportMoneyWithOutACase;
import gov.utcourts.corisweb.report.CorisReportMoneyWithOutACaseSearchCriteria;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StoredProcedureDescriptor;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dispatcher.BaseStoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.StoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.parameters.InputParameters;
import gov.utcourts.courtscommon.dispatcher.worker.OutputContainer;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;


@WebServlet("/CorisReportMoneyWithOutACaseServlet")
public class CorisReportMoneyWithOutACaseServlet extends ReportBaseServlet {
	private static final long serialVersionUID = -3335412659844L;
	private Connection conn = null;
	private static Logger logger = Logger.getLogger(CorisReportMoneyWithOutACaseServlet.class.getName());

	public static final String SEARCH_PAGE = "/jsp/corisReportMoneyWithOutACase.jsp";
	
	/**
	  * @see HttpServlet#HttpServlet()
	*/
	public CorisReportMoneyWithOutACaseServlet() {
	     super();
	}

	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CorisReportMoneyWithOutACaseSearchCriteria criteria;
			
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
			logger.error("CorisReportMoneyWithOutACaseServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
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
			request.setAttribute("payorType", URLEncryption.getParamAsString(request, "payorType"));
			request.setAttribute("locnCode", locnCode);
			request.setAttribute("courtType", courtType);
			request.setAttribute("logName", logName);
			
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
	private List<CorisMoneyWithOutACaseDTO> findReportDataResults(CorisReportMoneyWithOutACaseSearchCriteria moneyWithOutACaseReportSearchCriteria) throws Exception {
		List<CorisMoneyWithOutACaseDTO> corisMoneyWithOutACaseListDTO = new ArrayList<CorisMoneyWithOutACaseDTO>();		
		try {
			
			/////////////////////////////////////////////////////////////////////
			//Connection
			/////////////////////////////////////////////////////////////////////
			if ("D".equals(moneyWithOutACaseReportSearchCriteria.getCourtType())){;
				conn = DatabaseConnection.getConnection(KaseBO.CORIS_DISTRICT_READONLY_DB);
			} else {
				conn = DatabaseConnection.getConnection(KaseBO.CORIS_JUSTICE_READONLY_DB);
			}
			
			PersonnelBO personnelBO = new PersonnelBO(moneyWithOutACaseReportSearchCriteria.getCourtType())
			.where(PersonnelBO.LOCNCODE, moneyWithOutACaseReportSearchCriteria.getLocnCode())
			.where(PersonnelBO.COURTTYPE, moneyWithOutACaseReportSearchCriteria.getCourtType())
			.setUseConnection(conn)
			.find(PersonnelBO.USERIDSRL);
			
			
			StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
				"get_money_wo_case",
				"D".equals(moneyWithOutACaseReportSearchCriteria.getCourtType()) ? AccountBO.CORIS_DISTRICT_DB : AccountBO.CORIS_JUSTICE_DB,
				new InputParameters().addParameters(
					new TypeInteger().setValue(personnelBO.getUseridSrl()),
					new TypeString().setValue(moneyWithOutACaseReportSearchCriteria.getPayorType())
				),
				new OutputContainer().addResultTypes(
					new TypeInteger(),
						new TypeInteger(),
						new TypeString(),
						new TypeString(),
						new TypeDate(),
						new TypeInteger(),
						new TypeString(),
						new TypeBigDecimal(),
						new TypeString(),
						new TypeString(),
						new TypeString(),
						new TypeString(),
						new TypeString(),
						new TypeString(),
						new TypeString(),
						new TypeString()
					)
				)
				.setUseConnection(conn);
				
			BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure).executeQuery();
				
			List<OutputContainer> resultsList = storedProcedureFactory.getResults();
				
			if (resultsList != null){
				for(OutputContainer result :resultsList){
					corisMoneyWithOutACaseListDTO.add(
						new CorisMoneyWithOutACaseDTO(
								(Integer) result.getFields().get(0).getValue(), 
								(Integer) result.getFields().get(1).getValue(),
								(String) result.getFields().get(2).getValue(),
								(String) result.getFields().get(3).getValue(),
								(Date) result.getFields().get(4).getValue(),
								(Integer) result.getFields().get(5).getValue(),
								(String) result.getFields().get(6).getValue(),
								(BigDecimal) result.getFields().get(7).getValue(),
								(String) result.getFields().get(8).getValue(),
								(String) result.getFields().get(9).getValue(),
								(String) result.getFields().get(10).getValue(),
								(String) result.getFields().get(11).getValue(),
								(String) result.getFields().get(12).getValue(),
								(String) result.getFields().get(13).getValue(),
								(String) result.getFields().get(14).getValue(),
								(String) result.getFields().get(15).getValue()
						)
					);
				}
			}
			
			/////////////////////////////////////////////////////////////////////
			//Close Connection
			/////////////////////////////////////////////////////////////////////
			conn.close();
			conn = null;
			
			/////////////////////////////////////////////////////////////////////
			//Clean Up
			/////////////////////////////////////////////////////////////////////
			personnelBO = null;


		} catch (Exception e) {
			logger.error("getCorisMoneyWithOutACaseData(HttpServletRequest request, HttpServletResponse response)", e);
		}
		
		return corisMoneyWithOutACaseListDTO;
	}
	
	@Override
	CorisReportMoneyWithOutACaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		
		CorisReportMoneyWithOutACaseSearchCriteria moneyWithOutACaseReportSearchCriteria = new CorisReportMoneyWithOutACaseSearchCriteria(request);
		moneyWithOutACaseReportSearchCriteria.setPayorType(URLEncryption.getParamAsString(request, "payorType"));
		moneyWithOutACaseReportSearchCriteria.setReportFileName(moneyWithOutACaseReportSearchCriteria.getLocnDescr().toUpperCase());
		
		return moneyWithOutACaseReportSearchCriteria;
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emailReport(HttpServletRequest request, CorisReportMoneyWithOutACaseSearchCriteria criteria) throws Exception {
		String subject = "Coris Money With Out A Case Report";
		String content = "Attached please find the MoneyWithOutACase report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
		
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		@SuppressWarnings("unchecked")
		List<CorisMoneyWithOutACaseDTO> corisMoneyWithOutACaseListDTO = (List<CorisMoneyWithOutACaseDTO>) getReportData(criteria);
		return new CorisReportMoneyWithOutACase(criteria).generateReport(corisMoneyWithOutACaseListDTO);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return this.findReportDataResults((CorisReportMoneyWithOutACaseSearchCriteria) criteria);
	}
}
