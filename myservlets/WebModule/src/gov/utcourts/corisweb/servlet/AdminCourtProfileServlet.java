package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.utcourts.coriscommon.dataaccess.county.CountyBO;
import gov.utcourts.coriscommon.dataaccess.custodylocation.CustodyLocationBO;
import gov.utcourts.coriscommon.dataaccess.govtype.GovTypeBO;
import gov.utcourts.coriscommon.dataaccess.persapplic.PersApplicBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.state.StateBO;
import gov.utcourts.coriscommon.util.LocationUtil;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;

/**
* Servlet implementation class AdminCourtProfileServlet
*/
@WebServlet("/AdminCourtProfileServlet")
public class AdminCourtProfileServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -121451121225L;
	
	private static final String SEARCH_PAGE = "/jsp/adminCourtProfiles.jsp";
	private static final String MODIFY_PAGE = "/jsp/adminModifyCourtProfile.jsp";
	private static final String DEFAULTS_PAGE = "/jsp/adminModifyCourtProfileDefaults.jsp";
	
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AdminCourtProfileServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String page = MODIFY_PAGE; 
			String mode = TextUtil.getParamAsString(request, "mode");
			String courtType = TextUtil.getParamAsString(request, "courtType");
			String locnCode = TextUtil.getParamAsString(request, "locnCode");

			ProfileBO districtProfile = new ProfileBO(courtType).where(ProfileBO.LOCNCODE, locnCode).where(ProfileBO.COURTTYPE, "D").setReturnNull(true).find();
			if (districtProfile != null) {
				request.setAttribute("profile", districtProfile);
			} else {
				ProfileBO justiceProfile = new ProfileBO(courtType).where(ProfileBO.LOCNCODE, locnCode).where(ProfileBO.COURTTYPE, "J").setReturnNull(true).find();
				request.setAttribute("profile", justiceProfile);
			}
			
			if ("find".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;
				
				String logname = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
				
				List<String> locationsD = getLocationsByLognameAndPersApplic(logname, "D");
				List<String> locationsJ = getLocationsByLognameAndPersApplic(logname, "J");
				
				List<ProfileBO> results = null;
				if (locationsD != null && locationsD.size() > 0) {
					List<ProfileBO> districtResults = new ProfileBO("D").where(ProfileBO.LOCNCODE, Exp.IN, locationsD).orderBy(ProfileBO.COURTTITLE).search();
					if (districtResults != null && districtResults.size() > 0) {
						results = districtResults;
					}
				}
				if (locationsJ != null && locationsJ.size() > 0) {
					List<ProfileBO> justiceResults = new ProfileBO("J").where(ProfileBO.LOCNCODE, Exp.IN, locationsJ).orderBy(ProfileBO.COURTTITLE).search();
					if (justiceResults != null && justiceResults.size() > 0) {
						if (results != null) {
							results.addAll(justiceResults);
						} else {
							results = justiceResults;
						}
					}
				}
				request.setAttribute("results", results);
			} else if ("delete".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;
				// TOO MANY REFERENCES TO DELETE ? -- new ProfileBO(courtType).where(ProfileBO.LOCNCODE, locnCode).where(ProfileBO.COURTTYPE, courtType).delete();
				//request.setAttribute("results", new ProfileBO(courtType).orderBy(ProfileBO.LOCNCODE).search());	
			} else if ("add".equalsIgnoreCase(mode) || "edit".equalsIgnoreCase(mode)) {
				request.setAttribute("countyCodes", new CountyBO(courtType).orderBy(CountyBO.CNTYCODE).search(CountyBO.CNTYCODE, CountyBO.NAME));
				request.setAttribute("stateCodes", new StateBO(courtType).orderBy(StateBO.STATECODE).search(StateBO.STATECODE, StateBO.NAME));
			} else if ("update".equalsIgnoreCase(mode)) {
				String action = TextUtil.getParamAsString(request, "action");
				String court = TextUtil.getParamAsString(request, "court");
				
				ProfileBO profileBO = new ProfileBO(courtType)
				.setCourtTitle(TextUtil.getParamAsString(request, "courtTitle"))
				.setAddress1(TextUtil.getParamAsString(request, "address1"))
				.setAddress2(TextUtil.getParamAsString(request, "address2"))
				.setCity(TextUtil.getParamAsString(request, "city"))
				.setStateCode(TextUtil.getParamAsString(request, "stateCode"))
				.setZipCode(TextUtil.getParamAsString(request, "zipCode"))
				.setCntyCode(TextUtil.getParamAsString(request, "countyCode"))
				.setDrLicPrecinct(TextUtil.getParamAsString(request, "drLicPrecinct"))
				.setGeneralPhone(TextUtil.getParamAsString(request, "generalPhone"))
				.setCriminalPhone(TextUtil.getParamAsString(request, "criminalPhone"))
				.setCivilPhone(TextUtil.getParamAsString(request, "civilPhone"))
				.setJuryPhone(TextUtil.getParamAsString(request, "juryPhone"))
				.setInterpreterPhone(TextUtil.getParamAsString(request, "interpreterPhone"))
				.setDebtcollPhone(TextUtil.getParamAsString(request, "debtCollPhone"))
				.setAdaName(TextUtil.getParamAsString(request, "adaName"))
				.setAdaAddress(TextUtil.getParamAsString(request, "adaAddress"))
				.setAdaPhone(TextUtil.getParamAsString(request, "adaPhone"));
				
				if ("edit".equals(action)) {
					profileBO
					.where(ProfileBO.LOCNCODE, locnCode)
					.where(ProfileBO.COURTTYPE, court)
					.update();
				} else {
					profileBO
					.setLocnCode(locnCode)
					.setCourtType(court)
					.insert();
				}
				
				profileBO = null;
			} else if ("defaults".equalsIgnoreCase(mode)) {
				page = DEFAULTS_PAGE;
				
				request.setAttribute("judges", new PersonnelBO(courtType)
					.where(PersonnelBO.LOCNCODE, locnCode)
					.where(PersonnelBO.COURTTYPE, courtType)
					.where(PersonnelBO.REMOVEDFLAG, "N")
					.where(PersonnelBO.TYPE, "J")
					.orderBy(PersonnelBO.LOGNAME)
					.search(PersonnelBO.USERIDSRL, PersonnelBO.LOGNAME)
				);
				
				request.setAttribute("clerks", new PersonnelBO(courtType)
					.where(PersonnelBO.LOCNCODE, locnCode)
					.where(PersonnelBO.COURTTYPE, courtType)
					.where(PersonnelBO.REMOVEDFLAG, "N")
					.where(PersonnelBO.TYPE, "K")
					.orderBy(PersonnelBO.LOGNAME)
					.search(PersonnelBO.USERIDSRL, PersonnelBO.LOGNAME)
				);
				
				request.setAttribute("custodyLocations", new CustodyLocationBO(courtType)
					.orderBy(CustodyLocationBO.CUSTODYTYPECODE, CustodyLocationBO.CUSTODYLOCATIONNAME)
					.search(CustodyLocationBO.CUSTODYLOCATIONCODE, CustodyLocationBO.CUSTODYTYPECODE, CustodyLocationBO.CUSTODYLOCATIONNAME)
				);
				
				request.setAttribute("govTypes", new GovTypeBO(courtType).where(GovTypeBO.REMOVEDFLAG, Exp.EQUALS, "N").orderBy(GovTypeBO.GOVCODE).search(GovTypeBO.GOVCODE));

				
			} else if ("updateDefaults".equalsIgnoreCase(mode)) {
				page = DEFAULTS_PAGE;
				
				new ProfileBO(courtType)
				.where(ProfileBO.LOCNCODE, locnCode)
				.where(ProfileBO.COURTTYPE, TextUtil.getParamAsString(request, "court"))
				.setJudgeId(TextUtil.getParamAsInt(request, "judgeId"))
				.setAdminId(TextUtil.getParamAsInt(request, "clerkId"))
				.setWarrExpDeflt(TextUtil.getParamAsInt(request, "warrExpDeflt"))
				.setWarrGracePeriod(TextUtil.getParamAsInt(request, "warrGracePeriod"))
				.setFtaGracePeriod(TextUtil.getParamAsInt(request, "ftaGracePeriod"))
				.setDelinqPeriod(TextUtil.getParamAsInt(request, "delinqPeriod"))
				.setAltDelinqPeriod(TextUtil.getParamAsInt(request, "altDelinqPeriod"))
				.setOverpayRetLimit(TextUtil.getParamAsBigDecimal(request, "overpayRetLimit"))
				.setMediaType(TextUtil.getParamAsString(request, "mediaType"))
				.setDefltCustodyLocn(TextUtil.getParamAsString(request, "custodyLocationCode"))
				.setNcicCourtOri(TextUtil.getParamAsString(request, "ncicCourtOri"))
				.setNcicOriDescr(TextUtil.getParamAsString(request, "ncicOriDescr"))
				.setReceiptCopies(TextUtil.getParamAsInt(request, "receiptCopies"))
				.setDefltGovCod(TextUtil.getParamAsString(request, "govCode"))
				.setSupervisorCntVer("on".equalsIgnoreCase(TextUtil.getParamAsString(request, "supervisorCntVer")) ? "Y" : "N")
				.setReversalVerify("on".equalsIgnoreCase(TextUtil.getParamAsString(request, "reversalVerify")) ? "Y" : "N")
				.setChildSupportLbl("on".equalsIgnoreCase(TextUtil.getParamAsString(request, "childSupportLbl")) ? "Y" : "N")
				.setCertNoticeLbl("on".equalsIgnoreCase(TextUtil.getParamAsString(request, "certNoticeLbl")) ? "Y" : "N")
				.setDepositSlips("on".equalsIgnoreCase(TextUtil.getParamAsString(request, "depositSlips")) ? "Y" : "N")
				.setDefltSentTrack("on".equalsIgnoreCase(TextUtil.getParamAsString(request, "defltSentTrack")) ? "Y" : "N")
				.setCreditCardAccept("on".equalsIgnoreCase(TextUtil.getParamAsString(request, "creditCardAccept")) ? "Y" : "N")
				.setViewReceipt("on".equalsIgnoreCase(TextUtil.getParamAsString(request, "viewReceipt")) ? "Y" : "N")
				.setPetnModJudge("on".equalsIgnoreCase(TextUtil.getParamAsString(request, "petnModJudge")) ? "Y" : "N")
				.setPostJdmtIntFlag(TextUtil.getParamAsString(request, "postJdmtIntFlag"))
				.setSurcharge(TextUtil.getParamAsString(request, "surcharge"))
				.setPrintPath(TextUtil.getParamAsString(request, "printPath"))
				.setExecutePath(TextUtil.getParamAsString(request, "executePath"))
				.update();
			}

			request.setAttribute("courtType", courtType);
			getServletContext().getRequestDispatcher(page).forward(request, response);
			
			// cleanup
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static List<String> getLocationsByLognameAndPersApplic(String logname, String courtType) throws Exception {
		List<String> locations = new ArrayList<String>();
		try {
			List<Integer> userids = new ArrayList<Integer>();
			List<PersonnelBO> personnelListBO = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logname).search(PersonnelBO.USERIDSRL);
			for (PersonnelBO result : personnelListBO) {
				userids.add(result.getUseridSrl());
			}			
			if (userids != null && userids.size() > 0) {
				List<PersonnelBO> results = new PersonnelBO(courtType)
						.includeTables(
								new PersApplicBO(courtType)
										.where(PersApplicBO.APPLICID, 20)
						)
						.addJoin(JoinType.LEFT_OUTER_JOIN, PersApplicBO.TABLE.getTableName(), PersApplicBO.USERIDSRL, PersonnelBO.USERIDSRL)
						.where(PersonnelBO.LOGNAME, logname)
						.where(PersonnelBO.USERIDSRL, Exp.IN, userids)
						.search(PersonnelBO.LOCNCODE);
				if (results != null && results.size() > 0) {
					for (PersonnelBO result : results) {
						locations.add(result.getLocnCode());
					}
				}
			}
		} catch(Exception e) {
			System.out.println("getLocationsByLognameAndPersApplic LocationUtil Exception: " + e.getMessage());
			throw e;
		}
		return locations;
	}
	
}
