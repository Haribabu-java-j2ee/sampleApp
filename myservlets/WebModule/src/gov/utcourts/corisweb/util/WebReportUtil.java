package gov.utcourts.corisweb.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.courtscommon.util.TextUtil;

public class WebReportUtil {


	public static void initBaseReportCriteria(ReportBaseSearchCriteria criteria, HttpServletRequest request) throws Exception {
		criteria.setReportformat(URLEncryption.getParamAsString(request, "format"));
		criteria.setLocnCode(request.getParameter("locnCode") == null? (String) request.getSession().getAttribute(SessionVariables.LOCN_CODE): URLEncryption.getParamAsString(request, "locnCode"));
		criteria.setCourtType(request.getParameter("courtType") == null? (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE):URLEncryption.getParamAsString(request, "courtType"));
		criteria.setCourtReadOnlyDB((criteria.getCourtType() == null ? "D" : criteria.getCourtType().toUpperCase()) + "_READONLY");
		criteria.setLogName((String) request.getSession().getAttribute(SessionVariables.LOG_NAME));
		criteria.setLocnDescr(new ProfileBO(criteria.getCourtType()).includeFields(ProfileBO.COURTTITLE).where(ProfileBO.LOCNCODE, criteria.getLocnCode()).find(ProfileBO.COURTTITLE).getCourtTitle());
		criteria.setOrderBy(URLEncryption.getParamAsString(request, "orderBy"));
	}

	public static List<OrderByPair> getOrderByPairs(ReportBaseSearchCriteria criteria) {
		List<OrderByPair> orderByPairs = null;
		String orderBy = criteria.getOrderBy();
		if (!TextUtil.isEmpty(orderBy)) {
			orderByPairs = new ArrayList<OrderByPair>();
			orderBy = orderBy.substring(1, orderBy.length() - 1);
			orderBy = orderBy.replaceAll("\\],", "");
			String[] pairs = orderBy.split("\\[");
			for (int i = 1; i < pairs.length; i++) {
				String direction = ((String) pairs[i]).contains("asc") ? "asc" : "desc";
				String[] values = pairs[i].split(",");
				orderByPairs.add(new OrderByPair(Integer.parseInt(values[0]), direction));
			}
		}
		return orderByPairs;
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	public static List<LocationBO> getLocationList(HttpServletRequest request, String courtType) throws Exception {
		List<LocationBO> locationListBO = new LocationBO(courtType)
				.includeTables(
						new PersonnelBO(courtType)
								.where(PersonnelBO.COURTTYPE, courtType)
								.where(PersonnelBO.LOGNAME, request.getSession().getAttribute(SessionVariables.LOG_NAME))
								.where(PersonnelBO.REMOVEDFLAG, "N")
				)
				.addForeignKey(LocationBO.LOCNCODE, PersonnelBO.LOCNCODE)
				.addForeignKey(LocationBO.COURTTYPE, PersonnelBO.COURTTYPE)
				.orderBy(LocationBO.LOCNDESCR)
				.limit(0)
				.search(LocationBO.LOCNCODE, LocationBO.LOCNDESCR);
		return locationListBO;
	}
	
}
