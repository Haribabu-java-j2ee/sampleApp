package gov.utcourts.corisweb.servlet;

import gov.utcourts.commonauth.dataaccess.DatabaseConnection;
import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.casefeature.CaseFeatureBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.govtype.GovTypeBO;
import gov.utcourts.coriscommon.dataaccess.journal.JournalBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.schooldistrict.SchoolDistrictBO;
import gov.utcourts.coriscommon.dataaccess.transdist.TransDistBO;
import gov.utcourts.coriscommon.dto.NsfChecksDTO;
import gov.utcourts.coriscommon.dto.RevenueDetailDTO;
import gov.utcourts.coriscommon.dto.RevenueDistributionSummaryDTO;
import gov.utcourts.coriscommon.dto.RevenueHeLeaDTO;
import gov.utcourts.coriscommon.dto.RevenueProsecDTO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CorisReportRevDistSumSearchCriteria;
import gov.utcourts.corisweb.report.CorisRevDistSummaryReport;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StoredProcedureDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dispatcher.BaseStoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.StoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.parameters.InputParameters;
import gov.utcourts.courtscommon.dispatcher.worker.OutputContainer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

/**
 * Servlet implementation class CorisReportRevDistSumServlet
 */
@WebServlet("/CorisReportRevDistSumServlet")
public class CorisReportRevDistSumServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisReportDomViolServlet.class.getName());
    /**
     * Default constructor. 
     */
    public CorisReportRevDistSumServlet() {
        // TODO Auto-generated constructor stub
    }
    
    /* (non-Javadoc)
     * @see gov.utcourts.corisweb.servlet.BaseServlet#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
		JSONObject retValObj = new JSONObject();
		
		try {
			CorisReportRevDistSumSearchCriteria criteria = (CorisReportRevDistSumSearchCriteria) this.generateReportCriteria(request);
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
					request.getRequestDispatcher("/jsp/corisReportRevDistSumResults.jsp").forward(request, response);
					break;
				default:
					request.getRequestDispatcher("/jsp/corisReportRevDistSum.jsp?").forward(request, response);
					break;
			}
			retValObj.put("success", true);
		} catch (Exception e) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", "Failed to " + mode + " Revenue Distribution Summary report. " + e.getMessage());
			logger.error("Failed to " + mode + " No OTN report: " + e.getMessage());
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(retValObj.toString());
		}
		
	}

	private void emailReport(HttpServletRequest request, CorisReportRevDistSumSearchCriteria criteria) throws Exception {
		String subject = "Revenue Distribution Summary Report";
		String content = "Attached please find the Revenue Distribution Summary report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
		
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.corisweb.servlet.ReportBaseServlet#generateReportCriteria(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisReportRevDistSumSearchCriteria criteria = new CorisReportRevDistSumSearchCriteria(request);
		criteria.setStartJournal(URLEncryption.getParamAsInt(request, "startJournal"));
		criteria.setEndJournal(URLEncryption.getParamAsInt(request, "endJournal"));
		criteria.setEndDate(URLEncryption.getParamAsDate(request, "endDate"));
		criteria.setReportFileName("Revenue Distribution Summary");
		criteria.setReportName("Revenue Distribution Summary Report");
		return criteria;
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.corisweb.servlet.ReportBaseServlet#generateReport(gov.utcourts.coriscommon.report.ReportBaseSearchCriteria)
	 */
	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		@SuppressWarnings("unchecked")
		List<RevenueDistributionSummaryDTO> rptData = (List<RevenueDistributionSummaryDTO>) this.getReportData(criteria);
		CorisRevDistSummaryReport rpt = new CorisRevDistSummaryReport(criteria);
		return rpt.generateReport(rptData);
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.corisweb.servlet.ReportBaseServlet#getReportData(gov.utcourts.coriscommon.report.ReportBaseSearchCriteria)
	 */
	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		CorisReportRevDistSumSearchCriteria rptCriteria = (CorisReportRevDistSumSearchCriteria)criteria;
		int userId = new PersonnelBO(rptCriteria.getCourtReadOnlyDB()).includeFields(PersonnelBO.USERIDSRL)
							.where(PersonnelBO.LOCNCODE,rptCriteria.getLocnCode()).where(PersonnelBO.LOGNAME,rptCriteria.getLogName()).find().getUseridSrl();
		
		List<RevenueDistributionSummaryDTO> allData = new ArrayList<RevenueDistributionSummaryDTO>();
		RevenueDistributionSummaryDTO rptData = new RevenueDistributionSummaryDTO();
		//part 2
		rptData.setDistrDetails(getTransDataDetail(rptCriteria,userId));
		//part 4
		rptData.setRevenueProsecs(getRevenueProsec(rptCriteria, userId));
		//part 5 (51 52)
		rptData.setYbTotal(getYBTotal(rptCriteria, userId).doubleValue());
		if(rptData.getYbTotal() > 0){
			rptData.setYbDistributions(getYBDistributions(rptCriteria, userId));
		}
		//part part 10 (4-b)
		rptData.setRevenueHeLeas(getRevenueHeLeas(rptCriteria, userId));
		//part 6 get deposits --Skipped because the original stored procedure get_deposits.sql has all the code commented out and returns nothing.
		//rptData.setDeposits(getDeposits(rptCriteria, userId))
		
		//part 7
		rptData.setTotalReverse(getTotalReverse(rptCriteria, userId));
		rptData.setTotalAdjustment(getTotalAdjustment(rptCriteria, userId));
		//part 8
		rptData.setNsfChecks(getNsfChecks(rptCriteria,userId));
		allData.add(rptData);
		
		return allData;
	}

	private double getTotalAdjustment(CorisReportRevDistSumSearchCriteria rptCriteria, int userId) throws Exception {
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
    			"get_ttl_adjustment",
    			"D".equals(rptCriteria.getCourtType())?JournalBO.CORIS_DISTRICT_READONLY_DB:JournalBO.CORIS_JUSTICE_READONLY_DB,
    			new InputParameters().addParameters(
    				new TypeInteger().setValue(userId),
    				new TypeInteger().setValue(rptCriteria.getStartJournal()),
    				new TypeInteger().setValue(rptCriteria.getEndJournal())
    			),
    			new OutputContainer().addResultTypes(
    				new TypeInteger(),new TypeBigDecimal()		 
    				 
    			)
    		);
        
    	BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure)
    															.toString(BaseConstants.PRINT + BaseConstants.RUN).executeQuery();
		@SuppressWarnings("unchecked")
		List<OutputContainer> results = storedProcedureFactory.getResults();   
		if(results!=null && results.size()>0)
		{
			return ((BigDecimal)results.get(0).getFields().get(1).getValue()).doubleValue();

		}
		
		return 0.0;
	}

	private double getTotalReverse(CorisReportRevDistSumSearchCriteria rptCriteria, int userId) throws Exception {
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
    			"get_ttl_reversals",
    			"D".equals(rptCriteria.getCourtType())?JournalBO.CORIS_DISTRICT_READONLY_DB:JournalBO.CORIS_JUSTICE_READONLY_DB,
    			new InputParameters().addParameters(
    				new TypeInteger().setValue(userId),
    				new TypeInteger().setValue(rptCriteria.getStartJournal()),
    				new TypeInteger().setValue(rptCriteria.getEndJournal())
    			),
    			new OutputContainer().addResultTypes(
    				new TypeInteger(),new TypeBigDecimal()		 
    				 
    			)
    		);
        
    	BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure)
    															.toString(BaseConstants.PRINT + BaseConstants.RUN).executeQuery();
		@SuppressWarnings("unchecked")
		List<OutputContainer> results = storedProcedureFactory.getResults();   
		if(results!=null && results.size()>0)
		{
			return ((BigDecimal)results.get(0).getFields().get(1).getValue()).doubleValue();

		}
		
		return 0.0;
	}

	private List<RevenueHeLeaDTO> getRevenueHeLeas(CorisReportRevDistSumSearchCriteria rptCriteria, int userId) throws Exception {
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
    			"get_rev_he_lea",
    			"D".equals(rptCriteria.getCourtType())?JournalBO.CORIS_DISTRICT_READONLY_DB:JournalBO.CORIS_JUSTICE_READONLY_DB,
    			new InputParameters().addParameters(
    				new TypeInteger().setValue(userId),
    				new TypeInteger().setValue(rptCriteria.getStartJournal()),
    				new TypeInteger().setValue(rptCriteria.getEndJournal())
    			),
    			new OutputContainer().addResultTypes(
    				new TypeInteger(),new TypeString(), new TypeString(),new TypeBigDecimal(),new TypeBigDecimal()		 
    				 
    			)
    		);
        
    	BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure)
    															.toString(BaseConstants.PRINT + BaseConstants.RUN).executeQuery();
    	List<RevenueHeLeaDTO> heLeas = new ArrayList<RevenueHeLeaDTO>();
    	RevenueHeLeaDTO heLea = null;
		@SuppressWarnings("unchecked")
		List<OutputContainer> results = storedProcedureFactory.getResults();   
		if(results!=null && results.size()>0)
		{
			for (OutputContainer row : results) {
				heLea = new RevenueHeLeaDTO();
				heLea.setStatus((Integer)row.getFields().get(0).getValue());
				heLea.setLea((String)row.getFields().get(1).getValue());
				heLea.setLeaDescr((String)row.getFields().get(2).getValue());
				heLea.setRevLeaAmt((Double)row.getFields().get(3).getValue());
				heLea.setHeLeaAmt((Double)row.getFields().get(4).getValue());
				
				heLeas.add(heLea);
			}
		}
		
		return heLeas;
	}

	private BigDecimal getYBTotal(CorisReportRevDistSumSearchCriteria rptCriteria, int userId) throws Exception {
		return (BigDecimal) new TransDistBO(rptCriteria.getCourtReadOnlyDB()).sum(TransDistBO.AMTPAID)
					.where(TransDistBO.DISTCODE,"YB")
					.includeTables(new JournalBO(rptCriteria.getCourtReadOnlyDB()).includeFields(JournalBO.NO_FIELDS)
							.where(JournalBO.JOURNALNUM, Exp.BETWEEN, rptCriteria.getStartJournal(),Exp.AND,rptCriteria.getEndJournal())
							.where(JournalBO.LOCNCODE, rptCriteria.getLocnCode())
							.where(JournalBO.COURTTYPE, rptCriteria.getCourtType()))
					.addForeignKey(JournalBO.INTJOURNALNUM,TransDistBO.INTJOURNALNUM)
					.toString(BaseConstants.PRINT + BaseConstants.RUN).find()
					.getSum();
	}

	/**
	 * @param rptCriteria
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private List<TransDistBO> getYBDistributions(CorisReportRevDistSumSearchCriteria rptCriteria, int userId) throws Exception {

		return new TransDistBO(rptCriteria.getCourtReadOnlyDB())
				.includeFields(new Expression(Exp.SUM, Exp.LEFT_PARENTHESIS,TransDistBO.AMTPAID, Exp.RIGHT_PARENTHESIS).as("amtSum"))
				.where(TransDistBO.DISTCODE, "YB")
				.includeTables(
						new JournalBO(rptCriteria.getCourtReadOnlyDB()).includeFields(JournalBO.NO_FIELDS).where(JournalBO.LOCNCODE, rptCriteria.getLocnCode())
								.where(JournalBO.COURTTYPE, rptCriteria.getCourtType())
								.where(JournalBO.JOURNALNUM, Exp.BETWEEN, rptCriteria.getStartJournal(), Exp.AND, rptCriteria.getEndJournal()),
						new AccountBO(rptCriteria.getCourtReadOnlyDB()).includeFields(AccountBO.NO_FIELDS),
						new CaseFeatureBO(rptCriteria.getCourtReadOnlyDB()).includeFields(CaseFeatureBO.FEATUREVALUE).where(CaseFeatureBO.FEATURECODE, "school_bus_code"),
						new SchoolDistrictBO(rptCriteria.getCourtReadOnlyDB()).includeFields(SchoolDistrictBO.SCHOOLSHORTNAME))
				.addForeignKey(TransDistBO.INTJOURNALNUM, JournalBO.INTJOURNALNUM).addForeignKey(CaseFeatureBO.INTCASENUM, AccountBO.INTCASENUM)
				.addForeignKey(TransDistBO.ACCTNUM, AccountBO.ACCTNUM).addForeignKey(SchoolDistrictBO.SCHOOLCODE, CaseFeatureBO.FEATUREVALUE)
				.orderBy(CaseFeatureBO.FEATUREVALUE, SchoolDistrictBO.SCHOOLSHORTNAME).toString(BaseConstants.PRINT + BaseConstants.RUN).search();
	}

	/**
	 * Original report part 4
	 * @param rptCriteria
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	private List<RevenueProsecDTO> getRevenueProsec(CorisReportRevDistSumSearchCriteria rptCriteria, int userId) throws Exception {
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
    			"get_rev_prosec",
    			"D".equals(rptCriteria.getCourtType())?JournalBO.CORIS_DISTRICT_READONLY_DB:JournalBO.CORIS_JUSTICE_READONLY_DB,
    			new InputParameters().addParameters(
    				new TypeInteger().setValue(userId),
    				new TypeInteger().setValue(rptCriteria.getStartJournal()),
    				new TypeInteger().setValue(rptCriteria.getEndJournal())
    			),
    			new OutputContainer().addResultTypes(
    				new TypeInteger(),new TypeString(), new TypeString(), 
    				new TypeBigDecimal(),new TypeBigDecimal(),new TypeBigDecimal(),new TypeBigDecimal(),new TypeBigDecimal(),new TypeBigDecimal(),new TypeBigDecimal(),new TypeBigDecimal()		 
    				 
    			)
    		);
        
    	BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure)
    															.toString(BaseConstants.PRINT + BaseConstants.RUN).executeQuery();
    	List<RevenueProsecDTO> revProsecs = new ArrayList<RevenueProsecDTO>();
    	RevenueProsecDTO revProsec = null;
		@SuppressWarnings("unchecked")
		List<OutputContainer> results = storedProcedureFactory.getResults();   
		if(results!=null && results.size()>0)
		{
			for (OutputContainer row : results) {
				revProsec = new RevenueProsecDTO();
				revProsec.setStatus((Integer)row.getFields().get(0).getValue());
				revProsec.setProsecAgency((String)row.getFields().get(1).getValue());
				revProsec.setProsecDescr((String)row.getFields().get(2).getValue());
				revProsec.setFnAmt((BigDecimal)row.getFields().get(3).getValue());
				revProsec.setPnAmt((BigDecimal)row.getFields().get(4).getValue());
				revProsec.setHeAmt((BigDecimal)row.getFields().get(5).getValue());
				revProsec.setEdAmt((BigDecimal)row.getFields().get(6).getValue());
				revProsec.setEwAmt((BigDecimal)row.getFields().get(7).getValue());
				revProsec.setTrAmt((BigDecimal)row.getFields().get(8).getValue());
				revProsec.setIrAmt((BigDecimal)row.getFields().get(9).getValue());
				
				revProsecs.add(revProsec);
			}
		}
		
		return revProsecs;
	}

	/**
	 * Original report part 8
	 * @param rptCriteria
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private List<NsfChecksDTO> getNsfChecks(CorisReportRevDistSumSearchCriteria rptCriteria, int userId) throws Exception {
			StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
    			"get_nsf_checks",
    			"D".equals(rptCriteria.getCourtType())?JournalBO.CORIS_DISTRICT_READONLY_DB:JournalBO.CORIS_JUSTICE_READONLY_DB,
    			new InputParameters().addParameters(
    				new TypeInteger().setValue(userId),
    				new TypeString().setValue(rptCriteria.getLocnCode()),
    				new TypeString().setValue(rptCriteria.getCourtType()),
    				new TypeInteger().setValue(rptCriteria.getStartJournal()),
    				new TypeInteger().setValue(rptCriteria.getEndJournal())
    			),
    			new OutputContainer().addResultTypes(
    				new TypeInteger(),new TypeString(), new TypeString(), new TypeString(), new TypeString(), new TypeBigDecimal(),new TypeInteger(),new TypeString()		 
    				 
    			)
    		);
        
    	BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure)
    															.toString(BaseConstants.PRINT + BaseConstants.RUN).executeQuery();
    	List<NsfChecksDTO> nsfChks = new ArrayList<NsfChecksDTO>();
    	NsfChecksDTO nsfDto = null;
		@SuppressWarnings("unchecked")
		List<OutputContainer> results = storedProcedureFactory.getResults();   
		if(results!=null && results.size()>0)
		{
			for (OutputContainer row : results) {
				nsfDto = new NsfChecksDTO();
				nsfDto.setStatus((Integer)row.getFields().get(0).getValue());
				nsfDto.setCaseNum((String)row.getFields().get(1).getValue());
				nsfDto.setLastName((String)row.getFields().get(2).getValue());
				nsfDto.setFirstName((String)row.getFields().get(3).getValue());
				nsfDto.setPayorPartyNum((String)row.getFields().get(4).getValue());
				nsfDto.setTransAmt(((BigDecimal)row.getFields().get(5).getValue()).doubleValue());
				nsfDto.setJournalNum((Integer)row.getFields().get(6).getValue());
				nsfDto.setOutType((String)row.getFields().get(7).getValue());
				nsfChks.add(nsfDto);
			}
		}
		
		return nsfChks;
	}

	/**
	 * Original report part 2
	 * @param rptCriteria
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private List<RevenueDetailDTO> getTransDataDetail(CorisReportRevDistSumSearchCriteria rptCriteria, int userId) throws Exception{
		
		StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
    			"get_rev_detail",
    			"D".equals(rptCriteria.getCourtType())?TransDistBO.CORIS_DISTRICT_READONLY_DB:TransDistBO.CORIS_JUSTICE_READONLY_DB,
    			new InputParameters().addParameters(
    				new TypeInteger().setValue(userId),
    				new TypeInteger().setValue(rptCriteria.getStartJournal()),
    				new TypeInteger().setValue(rptCriteria.getEndJournal())
    			),
    			new OutputContainer().addResultTypes(
    				new TypeInteger(),new TypeString(), new TypeString(), new TypeString(), new TypeBigDecimal(),new TypeInteger(),new TypeBigDecimal()		 
    				 
    			)
    		);
        
    	BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure)
    															.toString(BaseConstants.PRINT + BaseConstants.RUN).executeQuery();
    	List<RevenueDetailDTO> details = new ArrayList<RevenueDetailDTO>();
    	RevenueDetailDTO revDto = null;
		@SuppressWarnings("unchecked")
		List<OutputContainer> results = storedProcedureFactory.getResults();   
		if(results!=null && results.size()>0)
		{
			for (OutputContainer row : results) {
				revDto = new RevenueDetailDTO();
				revDto.setStatus((Integer)row.getFields().get(0).getValue());
				revDto.setDistrbCode((String)row.getFields().get(1).getValue());
				revDto.setAcctNum((String)row.getFields().get(2).getValue());
				revDto.setDescr((String)row.getFields().get(3).getValue());
				revDto.setRevenue((BigDecimal)row.getFields().get(4).getValue());
				revDto.setCount((Integer)row.getFields().get(5).getValue());
				revDto.setCredit((BigDecimal)row.getFields().get(6).getValue());
				//part 2 subsection for distribution code 'TM'
				if("TM".equalsIgnoreCase(revDto.getDistrbCode())){
					revDto.setTmRevProsecs(getTmRevProc(rptCriteria, userId, revDto.getDistrbCode()));
				}
				details.add(revDto);
			}
		}
		
		return details;
	}

	private List<TransDistBO> getTmRevProc(CorisReportRevDistSumSearchCriteria rptCriteria, int userId, String distrbCode) throws Exception {
		return new TransDistBO(rptCriteria.getCourtReadOnlyDB())
				.includeFields(TransDistBO.AMTPAID.sum(), TransDistBO.AMTPAID.count())
				.where(TransDistBO.DISTCODE, distrbCode)
				.includeTables(new AccountBO(rptCriteria.getCourtReadOnlyDB()).includeFields(AccountBO.NO_FIELDS),
						new CrimCaseBO(rptCriteria.getCourtReadOnlyDB()).includeFields(CrimCaseBO.PROSECAGENCY),
						new GovTypeBO(rptCriteria.getCourtReadOnlyDB()).includeFields(GovTypeBO.DESCR),
						new JournalBO(rptCriteria.getCourtReadOnlyDB())
								.includeFields(JournalBO.NO_FIELDS).where(JournalBO.JOURNALNUM, Exp.BETWEEN,rptCriteria.getStartJournal(),Exp.AND,rptCriteria.getEndJournal()),
						new PersonnelBO(rptCriteria.getCourtReadOnlyDB()).includeFields(PersonnelBO.NO_FIELDS))
				.addForeignKey(JournalBO.LOCNCODE, PersonnelBO.LOCNCODE)
				.addForeignKey(JournalBO.COURTTYPE, PersonnelBO.COURTTYPE)
				.addForeignKey(TransDistBO.INTJOURNALNUM, JournalBO.INTJOURNALNUM)
				.addForeignKey(AccountBO.ACCTNUM,TransDistBO.ACCTNUM)
				.addForeignKey(CrimCaseBO.INTCASENUM,AccountBO.INTCASENUM)
				.addForeignKey(GovTypeBO.GOVCODE,CrimCaseBO.PROSECAGENCY)
				.groupBy(CrimCaseBO.PROSECAGENCY, GovTypeBO.DESCR)
				.orderBy(GovTypeBO.DESCR)
				.toString(BaseConstants.PRINT + BaseConstants.RUN)
				.search();
		
	}

}
