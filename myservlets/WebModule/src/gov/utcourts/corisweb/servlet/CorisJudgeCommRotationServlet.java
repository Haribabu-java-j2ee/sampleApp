package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dto.CategoryDTO;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.CorisJudgeCommRotationUtil;
import gov.utcourts.corisweb.util.PdfUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.notifications.util.TextUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/**
* Servlet implementation class CorisJudgeCommRotationServlet
*/
@WebServlet("/CorisJudgeCommRotationServlet")
public class CorisJudgeCommRotationServlet extends BaseServlet implements BaseConstants {
	
	private static final long serialVersionUID = 5451558888L;
    
	private static final String MAIN_PAGE = "/jsp/corisJudgeCommRotation.jsp";
	
	/**
	  * @see HttpServlet#HttpServlet()
	  */
	public CorisJudgeCommRotationServlet() {
		super();	
	}
  	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String mode = URLEncryption.getParamAsString(request, "mode");
			if ("changeLocation".equalsIgnoreCase(mode)) {
				changeLocation(request, response);
			} else if ("changeCaseType".equalsIgnoreCase(mode)) {
				changeCaseType(request, response);
			} else if ("calc".equalsIgnoreCase(mode)) {
				calc(request, response);
			} else if ("process".equalsIgnoreCase(mode)) {
				process(request, response);
			} else {
				getLocnList(request);
				getDefaultLocation(request);
				getCategoryList(request);
				displaySearch(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("errorMessage", e.getMessage());
			getServletContext().getRequestDispatcher(MAIN_PAGE).forward(request, response);
		}
	}
	
	private void changeLocation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			JSONObject retValObj = new JSONObject();
			
			String courtLocn = URLEncryption.getParamAsString(request, "courtLocn");
			String judgeComm = URLEncryption.getParamAsString(request, "judgeComm");
			
			if (!TextUtil.isEmpty(courtLocn)) {
				// get courttype and locncode from courtLocn 
				String courtType = courtLocn.substring(0, 1);
				String locnCode = courtLocn.substring(1, courtLocn.length());
				
				// retrieve judges/commissioners -- mimics get_personnel stored procedure
				List<PersonnelBO> judgesCommissioners = null;
				if ("judge".equalsIgnoreCase(judgeComm)) {
					judgesCommissioners = new PersonnelBO(courtType)
					.where(PersonnelBO.TYPE, Exp.MATCHES, "J")
					.where(PersonnelBO.REMOVEDFLAG, "N")
					.where(Exp.LEFT_PARENTHESIS, PersonnelBO.COURTTYPE, Exp.EQUALS, courtType, Exp.OR, PersonnelBO.COURTTYPE, Exp.EQUALS, "B", Exp.RIGHT_PARENTHESIS)
					.where(PersonnelBO.LOCNCODE, locnCode)
					//.orderBy(PersonnelBO.SORTORDER, PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME)
					.orderBy(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME)
					.search(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME, PersonnelBO.LOGNAME, PersonnelBO.USERIDSRL, PersonnelBO.SORTORDER);
				} else if ("commissioner".equalsIgnoreCase(judgeComm)){
					judgesCommissioners = new PersonnelBO(courtType)
					.where(PersonnelBO.TYPE, Exp.MATCHES, "C")
					.where(PersonnelBO.REMOVEDFLAG, "N")
					.where(Exp.LEFT_PARENTHESIS, PersonnelBO.COURTTYPE, Exp.EQUALS, courtType, Exp.OR, PersonnelBO.COURTTYPE, Exp.EQUALS, "B", Exp.RIGHT_PARENTHESIS)
					.where(PersonnelBO.LOCNCODE, locnCode)
					.orderBy(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME)
					.search(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME, PersonnelBO.LOGNAME, PersonnelBO.USERIDSRL);	
				}
				
				JSONArray retValArr = new JSONArray();
				for (PersonnelBO judge : judgesCommissioners) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("logName", judge.getLogname());
					jsonObject.put("useridSrl", judge.getUseridSrl());
					jsonObject.put("firstName", judge.getFirstName());
					jsonObject.put("lastName", judge.getLastName());
					retValArr.add(jsonObject);
				}
				retValObj.put("judgeCommList", retValArr);
			}
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "0");
			response.getWriter().write(retValObj.toString());
		
			retValObj = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void changeCaseType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			JSONObject retValObj = new JSONObject();
			
			String courtLocn = URLEncryption.getParamAsString(request, "courtLocn");
			
			if (!TextUtil.isEmpty(courtLocn)) {
				String courtType = courtLocn.substring(0, 1);
				List<CaseTypeBO> caseTypes = new CaseTypeBO(courtType).where(Exp.LEFT_PARENTHESIS, CaseTypeBO.VALIDCOURT, Exp.EQUALS, "B", Exp.OR, CaseTypeBO.VALIDCOURT, Exp.EQUALS, courtType, Exp.RIGHT_PARENTHESIS).orderBy(CaseTypeBO.DESCR).search(CaseTypeBO.CASETYPE, CaseTypeBO.DESCR);
				
				JSONArray retValArr = new JSONArray();
				for (CaseTypeBO caseType : caseTypes) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("caseType", caseType.getCaseType());
					jsonObject.put("description", caseType.getDescr());
					retValArr.add(jsonObject);
				}
				retValObj.put("caseTypeList", retValArr);
			}
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "0");
			response.getWriter().write(retValObj.toString());
		
			retValObj = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void calc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			JSONObject retValObj = new JSONObject();
			
			String courtLocn = URLEncryption.getParamAsString(request, "courtLocn");
			String judgeComm = URLEncryption.getParamAsString(request, "judgeComm");
			
			int judgeFrom = URLEncryption.getParamAsInt(request, "judgeFrom");
			String caseType = URLEncryption.getParamAsString(request, "caseType");
			String category = URLEncryption.getParamAsString(request, "category");
			double percentage = URLEncryption.getParamAsDouble(request, "percentage");
			String reassignDisposedCases = URLEncryption.getParamAsString(request, "reassignDisposedCases");
			
			if (!TextUtil.isEmpty(courtLocn)) {
				// get courttype and locncode from courtLocn 
				String courtType = courtLocn.substring(0, 1);
				String locnCode = courtLocn.substring(1, courtLocn.length());
				
				KaseBO kaseBO = new KaseBO(courtType)
				.count(KaseBO.ALL_FIELDS)
				.where(KaseBO.COURTTYPE, courtType)
				.where(KaseBO.LOCNCODE, locnCode);
				
				if (judgeFrom > 0) {
					if ("judge".equalsIgnoreCase(judgeComm)) 
						kaseBO.where(KaseBO.ASSNJUDGEID, judgeFrom);
					else
						kaseBO.where(KaseBO.ASSNCOMMID, judgeFrom);
				}
		
				if (!TextUtil.isEmpty(caseType))
					kaseBO.where(KaseBO.CASETYPE, caseType);
				
				if (!TextUtil.isEmpty(category))
					kaseBO.where(KaseBO.CASETYPE, Exp.IN, Exp.select(new CaseTypeBO(courtType).includeFields(CaseTypeBO.CASETYPE).where(CaseTypeBO.CATEGORY, category)));
					
				if ("false".equalsIgnoreCase(reassignDisposedCases))
					kaseBO.where(KaseBO.DISPDATE, Exp.IS_NULL, Exp.AND, KaseBO.DISPCODE, Exp.IS_NULL);
				
				int totalEligible = kaseBO.find().getCount();
				long totalReassignable = (Long) Math.round(totalEligible * (percentage / 100));
					
				retValObj.put("totalEligible", totalEligible);
				retValObj.put("totalReassignable", totalReassignable);
				retValObj.put("totalRemaining", totalEligible - totalReassignable);
			}
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "0");
			response.getWriter().write(retValObj.toString());
		
			retValObj = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String courtLocn = URLEncryption.getParamAsString(request, "courtLocn");
			int judgeFrom = URLEncryption.getParamAsInt(request, "judgeFrom");
			int judgeTo = URLEncryption.getParamAsInt(request, "judgeTo");
			String caseType = URLEncryption.getParamAsString(request, "caseType");
			String category = URLEncryption.getParamAsString(request, "category");
			String reassignDisposedCases = URLEncryption.getParamAsString(request, "reassignDisposedCases");
			int rotateAmount = URLEncryption.getParamAsInt(request, "rotateAmount");
			String judgeOrCommisioner = "judge".equals(URLEncryption.getParamAsString(request, "judgeComm")) ? "J" : "C";
			
			byte[] bytes = null;
			if (!TextUtil.isEmpty(courtLocn)) {
				// get courttype and locncode from courtLocn 
				String courtType = courtLocn.substring(0, 1);
				String locnCode = courtLocn.substring(1, courtLocn.length());

				String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME); 
				int useridSrl = CorisJudgeCommRotationUtil.getUseridSrl(logName, locnCode, courtType); 
				
				bytes = CorisJudgeCommRotationUtil.processRotation(useridSrl, judgeOrCommisioner, judgeFrom, judgeTo, locnCode, courtType, category, caseType, reassignDisposedCases, rotateAmount);
			}
			
			try {
				if (bytes != null)
					PdfUtil.showPDF(bytes, response);
			} catch (Exception e) {
				String msg = "Error occurred displaying pdf file -- " + e.getMessage();
				PdfUtil.showPDFError(msg.getBytes(), response);
			}	
			bytes = null;
			
		} catch (Exception e) {
			PdfUtil.showPDFError(e.getMessage().getBytes(), response);
		}
	}
	
	private void displaySearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			getServletContext().getRequestDispatcher(MAIN_PAGE).forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	private void getLocnList(HttpServletRequest request) throws Exception {
		try {
			String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			List<LocationBO> distLocationList = getLocations("D", logName);
			List<LocationBO> justLocationList = getLocations("J", logName);
			List<LocationBO> allLocations = new ArrayList<LocationBO>(distLocationList);
			allLocations.addAll(new ArrayList<LocationBO>(justLocationList));
			Collections.sort(allLocations, new Comparator<LocationBO>() {
     			@Override
     			public int compare(LocationBO s1, LocationBO s2) {
     				return s1.getLocnDescr().compareTo(s2.getLocnDescr());
     			}
     		});
			request.setAttribute("locationList", allLocations);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void getDefaultLocation(HttpServletRequest request) throws Exception {
		try {
			String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			String defaultCourtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
			String defaultLocnCode = new PersonnelBO(defaultCourtType).where(PersonnelBO.LOGNAME, logName).where(PersonnelBO.DEFLTLOGIN, "Y").find(PersonnelBO.LOCNCODE).getLocnCode();
			request.setAttribute("defaultLocnCode", defaultLocnCode);
			request.setAttribute("defaultCourtType", defaultCourtType);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void getCategoryList(HttpServletRequest request) throws Exception {
		try {
			List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
			categoryList.add(new CategoryDTO("V", "Civil"));
			categoryList.add(new CategoryDTO("R", "Criminal"));
			categoryList.add(new CategoryDTO("D", "Domestic"));
			categoryList.add(new CategoryDTO("P", "Probate"));
			categoryList.add(new CategoryDTO("S", "Small Claims"));
			request.setAttribute("categoryList", categoryList);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private static List<LocationBO> getLocations(String courtType, String logName) throws Exception {
		return new LocationBO(courtType)
		.includeTables(new PersonnelBO(courtType).where(PersonnelBO.COURTTYPE, courtType).where(PersonnelBO.LOGNAME, logName))
		.addForeignKey(PersonnelBO.LOCNCODE, LocationBO.LOCNCODE)
		.setOuter()
		.orderBy(LocationBO.LOCNDESCR)
		.search(LocationBO.LOCNCODE, LocationBO.LOCNDESCR, LocationBO.COURTTYPE);
	}
	
}