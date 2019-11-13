package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.CorisSecurityCommon;
import gov.utcourts.coriscommon.util.DateUtil;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.CorisAttorneysForCaseReportCriteria;
import gov.utcourts.corisweb.report.CorisAttorneysForCaseReportGenerator;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.CorisAttorneyUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SortCustomDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;

/**
* Servlet implementation class CorisAttorneysForCaseServlet
*/
@WebServlet("/CorisAttorneysForCaseServlet")
public class CorisAttorneysForCaseServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisAttorneysForCaseServlet.class.getName());
	private static String FILE_NAME = "CorisAttorneysForCaseServlet";
	private static String REPORT_TITLE = "Attorneys for Case Report";
	private static String REPORT_FILE_NAME = "Attorneys_for_Case_Report";
	private static String errorMessage = "";
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CorisAttorneysForCaseServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject retValObj = new JSONObject();
		CorisAttorneysForCaseReportCriteria myCriteria = null;
		boolean useJSON = true;
		try {
			myCriteria = (CorisAttorneysForCaseReportCriteria) generateReportCriteria(request);
			request.setAttribute("myCriteria", myCriteria);
			if (myCriteria.getLogName() == null || "".equals(myCriteria.getLogName())) {
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				switch (myCriteria.getMode()) {
					case SAVE:
						useJSON = false;
						saveReport(request, response, myCriteria);
						break;
					case EMAIL:
						useJSON = false;
						emailReport(response, myCriteria);
						break;
					case UPDATE:
						if ("updateAttachDatetime".equals(myCriteria.getAction())) {
							updateAttachDatetime(request, response, myCriteria);
						} else if ("updateDetachDatetime".equals(myCriteria.getAction())) {
							updateDetachDatetime(request, response, myCriteria);
						}else if ("detachAttorney".equals(myCriteria.getAction())) {
							detachAttorney(request, response, myCriteria);
						}
						break;
					case FIND:
						useJSON = false;
						find(request, response, myCriteria);
						break;
					default:
						useJSON = false;
						find(request, response, myCriteria);
						break;
				}	
			}
		} catch (Exception e) {
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error(FILE_NAME+" performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
		if (!TextUtil.isEmpty(errorMessage)) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", errorMessage);
		} else {
			retValObj.put("error", false);
		}
		if (useJSON) {
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(retValObj.toString());
		}
		retValObj = null;
		errorMessage = null;
	}
	
	/**
	 * Searches for all attorneys on a case and displays the results.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param CorisAttorneysForCaseReportCriteria criteria
	 * @throws Exception
	 */
	private void find(HttpServletRequest request, HttpServletResponse response, CorisAttorneysForCaseReportCriteria criteria) throws Exception {
		try {
			request.setAttribute("locationDistrictList", CorisAttorneyUtil.getLocationList(request, "D"));
			request.setAttribute("locationJusticeList", CorisAttorneyUtil.getLocationList(request, "J"));
			request.setAttribute("attyList", (List<KaseBO>) getReportData(criteria));
			request.setAttribute("userAccess", CorisSecurityCommon.getUserSecurityLevels(criteria, null));
		} catch (Exception e) {
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error(FILE_NAME+" find(HttpServletRequest request, HttpServletResponse response, CorisAttorneysForCaseReportCriteria criteria)", e);
		}
		getServletContext().getRequestDispatcher("/jsp/corisAttorneysForCase.jsp").forward(request, response);
	}
	
	/**
	 * Updates the atty_party.attach_datetime column.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param CorisAttorneysForCaseReportCriteria myCriteria
	 * @throws Exception
	 */
	private void updateAttachDatetime(HttpServletRequest request, HttpServletResponse response, CorisAttorneysForCaseReportCriteria myCriteria) throws Exception {
		try {
			AttyPartyBO attyPartyBO = new AttyPartyBO(myCriteria.getCourtType());
			attyPartyBO.where(AttyPartyBO.ATTYPARTYID, myCriteria.getAttyPartyId());
			if (myCriteria.getAttachDatetime() != null) {
				attyPartyBO.setAttachDatetime(myCriteria.getAttachDatetime(), Constants.dateFormatDatetimeMilliseconds);
			} else {
				attyPartyBO.setAttachDatetime(null);
			}
			if (myCriteria.getUserid() > 0 && myCriteria.getAttachDatetime() != null) {
				attyPartyBO.setAttachUserid(myCriteria.getUserid());
			} else {
				attyPartyBO.setAttachUserid(null);
			}
			attyPartyBO.update();
			attyPartyBO = null;
		} catch (Exception e) {
			errorMessage = "There was an error updating the attach date and changes have not been saved.";
			logger.error(FILE_NAME+" updateAttachDatetime(HttpServletRequest request, HttpServletResponse response, CorisAttorneysForCaseReportCriteria myCriteria)", e);
		}
	}
	
	/**
	 * Updates the atty_party.detach_datetime column.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param CorisAttorneysForCaseReportCriteria myCriteria
	 * @throws Exception
	 */
	private void updateDetachDatetime(HttpServletRequest request, HttpServletResponse response, CorisAttorneysForCaseReportCriteria myCriteria) throws Exception {
		try {
			AttyPartyBO attyPartyBO = new AttyPartyBO(myCriteria.getCourtType());
			attyPartyBO.where(AttyPartyBO.ATTYPARTYID, myCriteria.getAttyPartyId());
			if (myCriteria.getDetachDatetime() != null) {
				attyPartyBO.setDetachDatetime(myCriteria.getDetachDatetime(), Constants.dateFormatDatetimeMilliseconds);
			} else {
				attyPartyBO.setDetachDatetime(null);
			}
			if (myCriteria.getLogName() != null && myCriteria.getDetachDatetime() != null) {
				attyPartyBO.setDetachUserid(myCriteria.getUserid());
			} else {
				attyPartyBO.setDetachUserid(null);
			}
			attyPartyBO.update();
			attyPartyBO = null;
		} catch (Exception e) {
			errorMessage = "There was an error when updating the detach date and changes have not been saved.";
			logger.error(FILE_NAME+" updateDetachDatetime(HttpServletRequest request, HttpServletResponse response, CorisAttorneysForCaseReportCriteria myCriteria)", e);
		}
	}
	
	/**
	 * Sets the date for the atty_party.detach_datetime column.
	 * 
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @param CorisAttorneysForCaseReportCriteria myCriteria
	 * @throws ServletException
	 * @throws IOException
	 */
	public void detachAttorney(HttpServletRequest request, HttpServletResponse response, CorisAttorneysForCaseReportCriteria myCriteria) throws ServletException, IOException {
		try {
			AttyPartyBO attyPartyUpdateBO = new AttyPartyBO(myCriteria.getCourtType());
			attyPartyUpdateBO.setDetachDatetime(new Date());
			attyPartyUpdateBO.setDetachUserid(myCriteria.getUserid());
			attyPartyUpdateBO.where(AttyPartyBO.ATTYPARTYID, myCriteria.getAttyPartyId());
			attyPartyUpdateBO.update();
			attyPartyUpdateBO = null;
			//update the party_case.pro_se column to null, if there are no other attorneys attached to this case/party
			AttyPartyBO attyPartyInfoBO = new AttyPartyBO(myCriteria.getCourtType())
					.where(AttyPartyBO.ATTYPARTYID, myCriteria.getAttyPartyId())
					.find();			
			int count = new AttyPartyBO(myCriteria.getCourtType())
					.where(AttyPartyBO.INTCASENUM, attyPartyInfoBO.getIntCaseNum())
					.where(AttyPartyBO.PARTYNUM, attyPartyInfoBO.getPartyNum())
					.where(AttyPartyBO.PARTYCODE, attyPartyInfoBO.getPartyCode())
					.where(AttyPartyBO.DETACHDATETIME, Exp.IS_NULL)
					.find(AttyPartyBO.INTCASENUM.count())
					.getCount();
			if(count == 0){
				new PartyCaseBO(myCriteria.getCourtType())
					.setProSe(null)
					.where(PartyCaseBO.INTCASENUM, attyPartyInfoBO.getIntCaseNum())
					.where(PartyCaseBO.PARTYCODE, attyPartyInfoBO.getPartyCode())
					.where(PartyCaseBO.PARTYNUM, attyPartyInfoBO.getPartyNum())
					.update();
			}
		} catch (Exception e) {
			errorMessage = "There was an error detaching the attorney and changes have not been saved.";
			logger.error(FILE_NAME+" detachAttorney(HttpServletRequest request, HttpServletResponse response, CorisAttorneysForCaseReportCriteria myCriteria)", e);
		}
	}

	/**
	 * Emails the Attorneys for Case search results to the logged in user according to the format they selected (PDF, XLSX, etc.)
	 * 
	 * @param HttpServletResponse response
	 * @param CorisAttorneysForCaseReportCriteria criteria
	 * @throws Exception
	 */
	private void emailReport(HttpServletResponse response, CorisAttorneysForCaseReportCriteria criteria) throws Exception {
		try {
			this.emailReport(REPORT_TITLE, "Attached please find "+REPORT_TITLE+" in " + criteria.getReportformat() + " format.", this.generateReport(criteria), criteria);
		} catch (Exception e) {
			errorMessage = "There was an error emailing the report.";
			logger.error(FILE_NAME+" emailReport(CorisAttorneysForCaseReportCriteria criteria)", e);
		}
	}
	
	/**
	 * Query to get the search results for the Attorneys for Case report.
	 * 
	 * @param ReportBaseSearchCriteria filter
	 * @return List<?>
	 * @throws Exception
	 */
	@Override
	List<?> getReportData(ReportBaseSearchCriteria filter) throws Exception {
		List<KaseBO> attyList = null;
		CorisAttorneysForCaseReportCriteria criteria = (CorisAttorneysForCaseReportCriteria) filter;
		if (!TextUtil.isEmpty(criteria.getCaseNum())) {
			
			PartyCaseBO partyCaseBO = new PartyCaseBO(criteria.getCourtReadOnlyDB()).as("pc")
					.includeFields(
							PartyCaseBO.PARTYCODE,
							PartyCaseBO.PARTYNUM,
							PartyCaseBO.PROSE
					);
			
			AttyPartyBO attyPartyBO = new AttyPartyBO(criteria.getCourtReadOnlyDB()).as("ap");
					if (!criteria.isIncludeDetached()) {
						attyPartyBO.where(AttyPartyBO.DETACHDATETIME, Exp.IS_NULL);
					}
					if (criteria.getAttachDatetimeStart() != null || criteria.getAttachDatetimeEndPlusOne() != null) {
						if (criteria.getAttachDatetimeStart() != null && criteria.getAttachDatetimeEndPlusOne() != null) {
							attyPartyBO.where(Exp.LEFT_PARENTHESIS, AttyPartyBO.ATTACHDATETIME, 
									Exp.GREATER_THAN_OR_EQUAL_TO, criteria.getAttachDatetimeStart(), 
									Exp.AND, AttyPartyBO.ATTACHDATETIME, 
									Exp.LESS_THAN_OR_EQUAL_TO, criteria.getAttachDatetimeEndPlusOne(), Exp.RIGHT_PARENTHESIS);
						} else if(criteria.getAttachDatetimeStart() != null) {
							attyPartyBO.where(AttyPartyBO.ATTACHDATETIME, Exp.GREATER_THAN_OR_EQUAL_TO, criteria.getAttachDatetimeStart());
						}
					}
					attyPartyBO.includeFields(
							AttyPartyBO.BARNUM, 
							AttyPartyBO.BARSTATE, 
							AttyPartyBO.ATTYPARTYID,
							AttyPartyBO.INTCASENUM,
							AttyPartyBO.ATTACHDATETIME,
							AttyPartyBO.DETACHDATETIME,
							AttyPartyBO.ATTACHUSERID,
							AttyPartyBO.DETACHUSERID
					);
			
			AttorneyBO attorneyBO = new AttorneyBO(criteria.getCourtReadOnlyDB()).as("a")
					.includeFields(
							new FieldConstant("a.last_name").as("attorney_last_name"), 
							new FieldConstant("a.first_name").as("attorney_first_name"),
							AttorneyBO.BUSPHONE
					);
			
			PartyBO partyBO = new PartyBO(criteria.getCourtReadOnlyDB()).as("pty")
					.includeFields(
							new FieldConstant("pty.last_name").as("party_last_name"),
							new FieldConstant("pty.first_name").as("party_first_name")
					);
			
			PersonnelBO personnelAttBO = new PersonnelBO(criteria.getCourtReadOnlyDB()).as("pAtt")
					.includeFields(new FieldConstant("pAtt.logname").as("attached_logname"));

			PersonnelBO personnelDetBO = new PersonnelBO(criteria.getCourtReadOnlyDB()).as("pDet")
					.includeFields(new FieldConstant("pDet.logname").as("detached_logname"));

			KaseBO kaseBO = new KaseBO(criteria.getCourtReadOnlyDB()).as("k")
					.includeTables(
						partyCaseBO,
						attyPartyBO,
						partyBO,
						attorneyBO,
						personnelAttBO,
						personnelDetBO
					)
					.addJoin(JoinType.LEFT, PartyCaseBO.TABLE.getTableName(), PartyCaseBO.INTCASENUM, KaseBO.INTCASENUM)
					.addJoin(JoinType.LEFT, attyPartyBO, AttyPartyBO.PARTYNUM, PartyCaseBO.PARTYNUM, new Expression(AttyPartyBO.INTCASENUM, Exp.EQUALS, KaseBO.INTCASENUM))
					.addJoin(JoinType.LEFT, PartyBO.TABLE.getTableName(), PartyBO.PARTYNUM, PartyCaseBO.PARTYNUM)
					.addJoin(JoinType.LEFT, attorneyBO, AttorneyBO.BARNUM, AttyPartyBO.BARNUM, new Expression(AttorneyBO.BARSTATE, Exp.EQUALS, AttyPartyBO.BARSTATE))
				    .addJoin(JoinType.LEFT, personnelAttBO.TABLE.getTableName(), new FieldConstant("pAtt.userid_srl"), AttyPartyBO.ATTACHUSERID)
				    .addJoin(JoinType.LEFT, "personnel pDet", new FieldConstant("pDet.userid_srl"), AttyPartyBO.DETACHUSERID)			
					.where(KaseBO.CASENUM, criteria.getCaseNum())
					.where(KaseBO.LOCNCODE, criteria.getLocnCode())
					.where(KaseBO.COURTTYPE, criteria.getCourtType())
					.limit(Constants.MAX_RESULTS);
			
			// order by
			List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(filter);
			if (orderByPairs != null) {
				for (OrderByPair orderByPair : orderByPairs) {
					switch(orderByPair.getColumnPosition()) {
						case 0:	
							kaseBO.orderBy(AttyPartyBO.PARTYCODE, orderByPair.getDirectionType());
							break;
						case 1:	
							kaseBO.orderBy(PartyBO.LASTNAME);
							kaseBO.orderBy(PartyBO.FIRSTNAME, orderByPair.getDirectionType());
							break;
						case 2:	
							kaseBO.orderBy(AttorneyBO.LASTNAME);
							kaseBO.orderBy(AttorneyBO.FIRSTNAME, orderByPair.getDirectionType());
							break;
						case 3:	
							kaseBO.orderBy(AttyPartyBO.BARNUM, orderByPair.getDirectionType());
							break;
						case 4:	
							kaseBO.orderBy(AttyPartyBO.BARSTATE, orderByPair.getDirectionType());
							break;
						case 5:	
							kaseBO.orderBy(AttorneyBO.BUSPHONE, orderByPair.getDirectionType());
							break;
						case 6:	
							kaseBO.orderBy(AttyPartyBO.ATTACHDATETIME, orderByPair.getDirectionType());
							break;
						case 7:	
							kaseBO.orderBy(new SortCustomDescriptor("pAtt.logname " + orderByPair.getDirectionTypeAsString()));
							break;
						case 8:
							kaseBO.orderBy(AttyPartyBO.DETACHDATETIME, orderByPair.getDirectionType());
							break;
						case 9:	
							kaseBO.orderBy(new SortCustomDescriptor("pDet.logname " + orderByPair.getDirectionTypeAsString()));
							break;
					}
				}
			}
			attyList = kaseBO.search();
		}
		return attyList;
	}
	
	/**
	 * Sets all the criteria from the incoming form submission (like case number, date range, court type, location) so it can be used when generating or emailing a report. 
	 * 
	 * @param HttpServletRequest request
	 * @return ReportBaseSearchCriteria
	 * @throws Exception 
	 */
	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisAttorneysForCaseReportCriteria criteria = new CorisAttorneysForCaseReportCriteria(request);
		criteria.setMode(PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode")));
		criteria.setCourtType(URLEncryption.getParamAsString(request, "courtType"));
		if (criteria.getCourtType() == null || "".equals(criteria.getCourtType())) {
			criteria.setCourtType((String) request.getSession().getAttribute(SessionVariables.COURT_TYPE));	
		}
		criteria.setLocnCode(URLEncryption.getParamAsString(request, "locnCode"));
		if (criteria.getLocnCode() == null || "".equals(criteria.getLocnCode())) {
			criteria.setLocnCode((String) request.getSession().getAttribute(SessionVariables.LOCN_CODE));	
		}
		criteria.setLogName((String) request.getSession().getAttribute(SessionVariables.LOG_NAME));
		criteria.setCaseNum(URLEncryption.getParamAsString(request, "caseNum"));
		criteria.setReportFileName(REPORT_FILE_NAME);
		criteria.setReportTitle(REPORT_TITLE);
		criteria.setAction(URLEncryption.getParamAsString(request, "action"));
		if ("on".equals(URLEncryption.getParamAsString(request, "checkboxIncludeDetached"))) {
			criteria.setIncludeDetached(true);
		} else {
			criteria.setIncludeDetached(false);
		}
		criteria.setAttachDatetimeStart(URLEncryption.getParamAsDate(request, "attachDatetimeStart"));
		criteria.setAttachDatetimeEnd(URLEncryption.getParamAsDate(request, "attachDatetimeEnd"));
		criteria.setAttachDatetimeEndPlusOne(DateUtil.addDays(URLEncryption.getParamAsDate(request, "attachDatetimeEnd"), 1)); //always add one day to the end date because it's setting the HH:MM:SS to 00:00:00 instead of 23:59:59
		criteria.setAttyPartyId(URLEncryption.getParamAsInt(request, "attyPartyId"));
		criteria.setAttachDatetime(URLEncryption.getParamAsDate(request, "attachDatetime"));
		criteria.setDetachDatetime(URLEncryption.getParamAsDate(request, "detachDatetime"));
		criteria.setUserid(new PersonnelBO(criteria.getCourtType()).where(PersonnelBO.LOGNAME, criteria.getLogName()).find().getUseridSrl());
		criteria.setDateFormat(Constants.dateFormatDatetimeMilliseconds);
		criteria.setSortColumn(URLEncryption.getParamAsInt(request, "sortColumn"));
		criteria.setSortDirection(URLEncryption.getParamAsString(request, "sortDirection"));
		return criteria;
	}

	/**
	 * Generates the Attorneys for Case report in whatever format was selected (PDF, XLSX, etc.).
	 * 
	 * @param ReportBaseSearchCriteria criteria
	 * @return byte[]
	 * @throws Exception 
	 */
	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		return new CorisAttorneysForCaseReportGenerator(criteria).generateReport((List<KaseBO>) getReportData(criteria));
	}
	
}
