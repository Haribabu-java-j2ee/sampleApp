package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.bci.BciBO;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.document.DocumentBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.offense.OffenseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dto.NoOtnReportDTO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.CorisNoOtnReport;
import gov.utcourts.corisweb.report.CorisNoOtnReportSearchCriteria;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

/**
 * Servlet implementation class CorisReportNoOtnServlet
 */
@WebServlet("/CorisReportNoOtnServlet")
public class CorisReportNoOtnServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisReportNoOtnServlet.class.getName());

    /**
     * Default constructor. 
     */
    public CorisReportNoOtnServlet() {
        // TODO Auto-generated constructor stub
    }

	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisNoOtnReportSearchCriteria criteria = new CorisNoOtnReportSearchCriteria(request);
		criteria.setAllDistrictCourt("on".equalsIgnoreCase(URLEncryption.getParamAsString(request, "allCourts"))?true:false);
		criteria.setAllOnLocalDB("on".equalsIgnoreCase(URLEncryption.getParamAsString(request, "allLocalDb"))?true:false);
		criteria.setThisCourtOnly("on".equalsIgnoreCase(URLEncryption.getParamAsString(request, "thisCourtOnly"))?true:false);
		criteria.setStartDate(URLEncryption.getParamAsDate(request, "startDate"));
		criteria.setEndDate(URLEncryption.getParamAsDate(request, "endDate"));
		criteria.setOrderBy(URLEncryption.getParamAsString(request, "orderBy"));
		criteria.setReportFileName("NoOtnReport");
		return criteria;
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		@SuppressWarnings("unchecked")
		List<KaseBO> results = (List<KaseBO>) getReportData(criteria);
		return new CorisNoOtnReport(criteria).generateReport(results);
	}

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
		JSONObject retValObj = new JSONObject();
		
		try {
			CorisNoOtnReportSearchCriteria criteria = (CorisNoOtnReportSearchCriteria) this.generateReportCriteria(request);
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
					request.getRequestDispatcher("/jsp/corisReportNoOtnResults.jsp").forward(request, response);
					break;
				default:
					request.getRequestDispatcher("/jsp/corisReportNoOtn.jsp?").forward(request, response);
					break;
			}
			retValObj.put("success", true);
		} catch (Exception e) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", "Failed to " + mode + " document report. " + e.getMessage());
			logger.error("Failed to " + mode + " No OTN report: " + e.getMessage());
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(retValObj.toString());
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emailReport(HttpServletRequest request, CorisNoOtnReportSearchCriteria criteria) throws Exception {
		String subject = "No OTN Report";
		String content = "Attached please find the No OTN report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = generateReport(criteria);
		emailReport(subject, content, rprtAttachment, criteria);
		
	}

	@SuppressWarnings("null")
	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		CorisNoOtnReportSearchCriteria myCriteria = (CorisNoOtnReportSearchCriteria)criteria;
		List<NoOtnReportDTO> results = new ArrayList<NoOtnReportDTO>();
		List<ProfileBO> rptLocnCodes = new ArrayList<ProfileBO>();
		if(myCriteria.isAllOnLocalDB()){
			rptLocnCodes = new ProfileBO(criteria.getCourtReadOnlyDB())
										.includeFields(ProfileBO.LOCNCODE,ProfileBO.COURTTITLE,ProfileBO.CITY)
										.includeTables(new PersonnelBO(myCriteria.getCourtReadOnlyDB()).where(PersonnelBO.LOGNAME,criteria.getLogName()))
										.addForeignKey(ProfileBO.LOCNCODE, PersonnelBO.LOCNCODE).toString(BaseConstants.PRINT+BaseConstants.RUN)
										.search();
		}else if(myCriteria.isThisCourtOnly()){
			rptLocnCodes.add(new ProfileBO(criteria.getCourtReadOnlyDB())
				.includeFields(ProfileBO.LOCNCODE,ProfileBO.COURTTITLE,ProfileBO.CITY).where(ProfileBO.LOCNCODE,criteria.getLocnCode()).toString(BaseConstants.PRINT+BaseConstants.RUN).find());
		}else if(myCriteria.isAllDistrictCourt()){ // with all the locations, it will take long, long and long time to run the report
			rptLocnCodes = new ProfileBO("D_READONLY")
			.includeFields(ProfileBO.LOCNCODE,ProfileBO.COURTTITLE,ProfileBO.CITY).toString(BaseConstants.PRINT+BaseConstants.RUN)
			.search();
		}
		
		results.add(generateNoOtnReportData(rptLocnCodes,myCriteria));		
		
		return results;
	}

	/**
	 * @param rptLocns
	 * @param myCriteria
	 * @return
	 * @throws Exception
	 */
	private NoOtnReportDTO generateNoOtnReportData(List<ProfileBO> rptLocns, CorisNoOtnReportSearchCriteria myCriteria) throws Exception {
		List<String> locnList = new ArrayList<String>();
		for(ProfileBO locn:rptLocns){
			locnList.add(locn.getLocnCode());
		}
		List<Integer> intCaseNumList = getIntCaseNumListFromLocations(locnList, myCriteria);
		List<KaseBO> bciCases = getCaseList(intCaseNumList, myCriteria);
		List<ChargeBO> charges = getCaseCharges(intCaseNumList, myCriteria);
		return buildNoOtnDto(bciCases,charges,myCriteria);
	}

	/**
	 * @param bciCases
	 * @param charges
	 * @param criteria
	 * @return
	 */
	private NoOtnReportDTO buildNoOtnDto(List<KaseBO> bciCases, List<ChargeBO> charges, CorisNoOtnReportSearchCriteria criteria) {
		NoOtnReportDTO rptDto = new NoOtnReportDTO();
		int totalCharges = 0;
		int totalMisDem = 0;
		int totalFelony = 0;
		int totalCases = 0;
		int totalArrestDates = 0;
		int totalOtnArrestDates = 0;
		int totalBirthDates =0;
		int totalOtnBirthDates = 0;
		int totalOtn = 0;
		int totalCitations = 0;
		int totalWithout = 0; //without otn or citations
		
		//calculate charges
		for(ChargeBO charge:charges){
			if(charge.getDrLicRptDate()!=null && (criteria.getStartDate().after(charge.getDrLicRptDate()) || charge.getDrLicRptDate().after(criteria.getEndDate()))){
				continue;
			}
			totalCharges++;
			if("MA".equalsIgnoreCase(charge.getSeverity()) || "MB".equalsIgnoreCase(charge.getSeverity())){
				totalMisDem++;
			}else {
				totalFelony++;
			}
		}
		
		//now loop through case results
		if(totalCharges > 0){
			for(KaseBO kase:bciCases){
				totalCases++;
				if(kase.get(PartyCaseBO.ARRESTDATE)!=null){
					totalArrestDates++;
				}
				if(kase.get(PartyBO.BIRTHDATE)!=null){
					totalBirthDates++;
				}
				if(!TextUtil.isEmpty((String)kase.get(PartyCaseBO.OTN)) && !"0".equals((String)kase.get(PartyCaseBO.OTN)) && !"N".equals((String)kase.get(PartyCaseBO.OTNAVAILABLE))){
					totalOtn++;
					if(kase.get(PartyBO.BIRTHDATE)!=null){
						totalOtnBirthDates++;
					}
					if(kase.get(PartyCaseBO.ARRESTDATE)!=null){
						totalOtnArrestDates++;
					}
				}else if(!TextUtil.isEmpty((String)kase.get(CrimCaseBO.CITNUM)) && !"0".equals((String)kase.get(CrimCaseBO.CITNUM))){
					totalCitations++;
				}else {
					totalWithout++;
				}
			}
		}
		rptDto.setKases(bciCases);
		rptDto.setCasesWithArrestDate(totalArrestDates);
		rptDto.setCasesWithDoaOtn(totalOtnArrestDates);
		rptDto.setCasesWithDob(totalBirthDates);
		rptDto.setCasesWithDobOtn(totalOtnBirthDates);
		rptDto.setCasesWithout(totalWithout);
		rptDto.setTotalCases(totalCases);
		rptDto.setTotalCharges(totalCharges);
		rptDto.setTotalFelonyCharges(totalFelony);
		rptDto.setTotalCitations(totalCitations);
		rptDto.setTotalMisdemCharges(totalMisDem);
		rptDto.setTotalOtns(totalOtn);
		return rptDto;
	}

	/**
	 * @param intCaseNumList
	 * @param myCriteria
	 * @return
	 * @throws Exception
	 */
	private List<KaseBO> getCaseList(List<Integer> intCaseNumList, CorisNoOtnReportSearchCriteria myCriteria) throws Exception {
		KaseBO kaseBo = new KaseBO(myCriteria.getCourtReadOnlyDB())
					.includeFields(KaseBO.CASENUM)
					.where(KaseBO.CASETYPE, Exp.IN, new String[]{"FS","MS","MC","MD","MO","TC","PC","TN","PN"} )
				 	.where(KaseBO.INTCASENUM, Exp.IN,intCaseNumList)
					.includeTables(new CrimCaseBO(myCriteria.getCourtReadOnlyDB())
											.includeFields(CrimCaseBO.CITNUM)
											.where(CrimCaseBO.INTCASENUM, Exp.IN,intCaseNumList),
								   new PartyCaseBO(myCriteria.getCourtReadOnlyDB())
											.includeFields(PartyCaseBO.OTN,PartyCaseBO.ARRESTDATE,PartyCaseBO.OTNAVAILABLE)
											.where(PartyCaseBO.PARTYCODE,"DEF"),
								   new PartyBO(myCriteria.getCourtReadOnlyDB())
											.includeFields(PartyBO.FIRSTNAME,PartyBO.LASTNAME,PartyBO.BIRTHDATE)
										    .where(PartyBO.PARTYNUM,Exp.IN,Exp.select(
												   new ChargeBO(myCriteria.getCourtReadOnlyDB()).includeFields(ChargeBO.PARTYNUM)
												   .where(ChargeBO.INTCASENUM,Exp.IN,intCaseNumList))))
					.addForeignKey(CrimCaseBO.INTCASENUM, KaseBO.INTCASENUM)
					.addForeignKey(CrimCaseBO.INTCASENUM, PartyCaseBO.INTCASENUM)
					.addForeignKey(PartyBO.PARTYNUM,PartyCaseBO.PARTYNUM);
		
		setOrderBy(kaseBo, myCriteria);
		
		return kaseBo.toString(BaseConstants.PRINT+BaseConstants.RUN).search();
	}

	/**
	 * @param rptLocns
	 * @param myCriteria
	 * @return
	 * @throws Exception
	 */
	private List<Integer> getIntCaseNumListFromLocations(List<String> rptLocns, CorisNoOtnReportSearchCriteria myCriteria) throws Exception {
		boolean emptyEndDate = myCriteria.getEndDate()==null;
		KaseBO kaseBo = new KaseBO(myCriteria.getCourtReadOnlyDB())
							.includeFields(KaseBO.INTCASENUM)
							.where(KaseBO.LOCNCODE, Exp.IN, rptLocns).setUnique()
							.includeTables(new ChargeBO(myCriteria.getCourtReadOnlyDB()).includeFields(ChargeBO.NO_FIELDS)
								.where(Exp.DATE, Exp.LEFT_PARENTHESIS,ChargeBO.DRLICRPTDATE, Exp.RIGHT_PARENTHESIS, Exp.BETWEEN,myCriteria.getStartDate(), Exp.AND, emptyEndDate?Calendar.getInstance().getTime():myCriteria.getEndDate()))
							.addForeignKey(KaseBO.INTCASENUM, ChargeBO.INTCASENUM);
		if(myCriteria.getEndDate() == null){
			kaseBo.union(new BciBO(myCriteria.getCourtReadOnlyDB()).includeFields(BciBO.INTCASENUM)
						  	.includeTables(new KaseBO(myCriteria.getCourtReadOnlyDB()).where(KaseBO.LOCNCODE, Exp.IN, rptLocns))
						  	.addForeignKey(BciBO.INTCASENUM, KaseBO.INTCASENUM));
		}
		List<KaseBO> bciCases = kaseBo.toString(BaseConstants.PRINT+BaseConstants.RUN).search();
		List<Integer> intCaseNums = new ArrayList<Integer>();
		for(KaseBO cbicase:bciCases){
			intCaseNums.add(cbicase.getIntCaseNum());
		}

		return intCaseNums;
	}
	
	/**
	 * @param intCaseNumList
	 * @param myCriteria
	 * @return
	 * @throws Exception
	 */
	private List<ChargeBO> getCaseCharges(List<Integer> intCaseNumList, CorisNoOtnReportSearchCriteria myCriteria) throws Exception{
		return new ChargeBO(myCriteria.getCourtReadOnlyDB())
					.includeFields(ChargeBO.SEVERITY,ChargeBO.JDMTDATE,ChargeBO.DRLICRPTDATE)
					.where(ChargeBO.INTCASENUM,Exp.IN,intCaseNumList)
					.where(ChargeBO.JDMTDATE,Exp.IS_NOT_NULL)
					.where(ChargeBO.JDMTCODE,Exp.IS_NOT_NULL)
					.where(ChargeBO.JDMTCODE, Exp.NOT_IN, new String[]{"TR","TV","BO"})
					.where(ChargeBO.SEVERITY, Exp.IN, new String[]{"MB","MA", "F3","F2","F1","CA"})
					.where(Exp.DATE, Exp.LEFT_PARENTHESIS, ChargeBO.DRLICRPTDATE, Exp.RIGHT_PARENTHESIS, Exp.BETWEEN, myCriteria.getStartDate(), Exp.AND, myCriteria.getEndDate()==null?Calendar.getInstance().getTime():myCriteria.getEndDate())
					.includeTables(new OffenseBO(myCriteria.getCourtReadOnlyDB()).includeFields(OffenseBO.NO_FIELDS).as("o1")
									.where(OffenseBO.BCIRPT,"Y")
									.where(OffenseBO.LASTEFFECTDATE,Exp.EQUALS,
											Exp.select(
													new OffenseBO(myCriteria.getCourtReadOnlyDB()).as("o2").min(OffenseBO.LASTEFFECTDATE)
													.where(OffenseBO.OFFSVIOLCODE,Exp.EQUALS,ChargeBO.OFFSVIOLCODE)
													.where(OffenseBO.LASTEFFECTDATE,Exp.GREATER_THAN_OR_EQUAL_TO,ChargeBO.OFFSEFFECTDATE)
													)
										 )
								  )
					.addForeignKey(OffenseBO.OFFSVIOLCODE, ChargeBO.OFFSVIOLCODE).toString(BaseConstants.PRINT+BaseConstants.RUN).search();
					
	}
	
	private void setOrderBy(KaseBO kase, CorisNoOtnReportSearchCriteria criteria){
		List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(criteria);
		if (orderByPairs != null) {
			for (OrderByPair orderByPair : orderByPairs) {
				switch(orderByPair.getColumnPosition()) {
					case 0:	kase.orderBy(KaseBO.INTCASENUM, orderByPair.getDirectionType()); break;
					case 1: kase.orderBy(PartyBO.LASTNAME, orderByPair.getDirectionType()); break;
				}
			}
		}
	}

}
