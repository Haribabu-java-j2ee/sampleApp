package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

import gov.utcourts.carecommon.dataaccess.attorneybarload.AttorneybarloadBO;
import gov.utcourts.coriscommon.constants.ConstantsConnectionProperties;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.EvelopeLabelsPdf;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

/**
* Servlet implementation class CorisAttorneyDetailsServlet
*/
@WebServlet("/CorisAttorneyDetailsServlet")
public class CorisAttorneyDetailsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisAttorneyDetailsServlet.class.getName());
	private static String errorMessage = "";
	private static String errorMessageFailedConn = "";
	private static String errorMessageFailedUpdate = "";
	private static String successMessage = "";

	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CorisAttorneyDetailsServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageMode mode = PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode"));
		JSONObject retValObj = new JSONObject();
		errorMessage = "";
		errorMessageFailedConn = "";
		errorMessageFailedUpdate = "";
		successMessage = "";
		boolean useJson = false;
		try {
			request.setAttribute("parentCornId", URLEncryption.getParamAsString(request, "parentCornId"));
			request.setAttribute("action", URLEncryption.getParamAsString(request, "action"));
			switch (mode) {
				case ADD:
					useJson = true;
					updateAttorneyData(request, response);
					break;
				case UPDATE:
					useJson = true;
					updateAttorneyData(request, response);
					break;
				case DELETE:
					useJson = true;
					updateAttorneyData(request, response);
					break;
				case PRINT:
					printAttorneyEnvelopeLabels(request,response);
					break;
				default:
					String page = "/jsp/corisAttorneyDetails.jsp";
					AttorneyBO attorney = attorneyDetails(request);
					request.setAttribute("attorneyDetails", attorney);
					getServletContext().getRequestDispatcher(page).forward(request, response);
					break;
			}	
		} catch (Exception e){
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error("CorisAttorneyDetailsServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
		if(!TextUtil.isEmpty(errorMessageFailedConn)){
			errorMessage += errorMessageFailedConn;
		}
		if(!TextUtil.isEmpty(errorMessageFailedUpdate)){
			errorMessage += errorMessageFailedUpdate;
		}
		if (!TextUtil.isEmpty(errorMessage)) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", errorMessage);
		}else{
			retValObj.put("error", false);
		}
		if (!TextUtil.isEmpty(successMessage)) {
			retValObj.put("success", true);
			retValObj.put("successMessage", successMessage);
		}else{
			retValObj.put("success", false);
		}
		if (useJson) {
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(retValObj.toString());
		}
		retValObj = null;
		errorMessage = null;
		errorMessageFailedConn = null;
		errorMessageFailedUpdate = null;
		successMessage = null;
		mode = null;
	}
	
	private void printAttorneyEnvelopeLabels(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttorneyBO attorney = attorneyDetails(request);
		String labelSelected = URLEncryption.getParamAsString(request, "labelTemplate");
		int startRow = URLEncryption.getParamAsInt(request, "startRow");
		int startCol = URLEncryption.getParamAsInt(request, "startCol");
		int numOfLbl = URLEncryption.getParamAsInt(request, "labelNum");
		EvelopeLabelsPdf labelPdf =  new EvelopeLabelsPdf();
		byte[] labelData = null;
		String fileTitle = "";
		if(!"9x12".equals(labelSelected) && !"9x3.5".equals(labelSelected)){
			labelData =	labelPdf.generateLabels(labelSelected, attorney, startRow, startCol, numOfLbl);
			fileTitle = "AttorneyMailingLabels";
		}else {
			labelData = labelPdf.generateEnvelope(labelSelected,attorney);
			fileTitle = "AttorneyMailingEnvelope";
		}

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename="+fileTitle+".pdf");
		
		ServletOutputStream sout = response.getOutputStream();
		sout.write(labelData);
		sout.flush();
		sout.close();
	}

	private void updateAttorneyData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttorneyBO attorneyBO = getAttorneyData(request);
    	Connection conn = null;

    	//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it's own try so if it fails, the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (URLEncryption.getParamFromCheckBox(request,"productionDistrict")){
	    	try {
	    		//System.out.println("CORIS_PRODUCTION_DISTRICT_DB");
				conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_PRODUCTION_DISTRICT_DB);
				if (conn != null && attorneyBO != null) {
					dataBaseInsertUpdateDeleteAttorneyInformation(conn, "D", attorneyBO, request, response, "Coris Production District");
				}
			} catch (Exception e) {
				errorMessageFailedConn += "Attorney changes failed for Coris Production District.<br>";
				logger.info("Attorney Update CORIS_PRODUCTION_DISTRCIT_DB Connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_PRODUCTION_DISTRICT_DB Connection Close", e);
				}
				conn = null;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it's own try so if it fails, the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (URLEncryption.getParamFromCheckBox(request,"productionJustice")){
	    	try {
	    		//System.out.println("CORIS_PRODUCTION_JUSTICE_DB");
				conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_PRODUCTION_JUSTICE_DB);
				if (conn != null && attorneyBO != null) {
					dataBaseInsertUpdateDeleteAttorneyInformation(conn, "J", attorneyBO, request, response, "Coris Production Justice");
				}
			} catch (Exception e) {
				errorMessageFailedConn += "Attorney changes failed for Coris Production Justice.<br>";
				logger.info("Attorney Update CORIS_PRODUCTION_JUSTICE_DB Connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_PRODUCTION_JUSTICE_DB Connection Close", e);
				}
				conn = null;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it's own try so if it fails, the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (URLEncryption.getParamFromCheckBox(request,"productionCare")){
	    	try {
	    		//System.out.println("CARE_PRODUCTION_DB");
				conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CARE_PRODUCTION_DB);
				if (conn != null && attorneyBO != null) {
					dataBaseCareInsertAttorneyBarLoadInformation(conn, attorneyBO);
				}
			} catch (Exception e) {
				errorMessageFailedConn += "Attorney changes failed for Production Care.<br>";
				logger.info("Attorney Update CARE_PRODUCTION_DB Connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CARE_PRODUCTION_DB Connection Close", e);
				}
				conn = null;
			}
		}
		//////////////////////////////////////////////////////////////////////////////////
		// No Email Address on Test or Development Server so that emails don't get accidently sent out during testing
		//////////////////////////////////////////////////////////////////////////////////
		attorneyBO.setEmailAddress(null);
		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it's own try so if it fails, the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (URLEncryption.getParamFromCheckBox(request,"testDistrict")){
	    	try {
	    		//System.out.println("CORIS_TEST_DISTRICT_DB");
				conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_TEST_DISTRICT_DB);
				if (conn != null && attorneyBO != null) {
					dataBaseInsertUpdateDeleteAttorneyInformation(conn, "D", attorneyBO, request, response, "Coris Test District");
				}
			} catch (Exception e) {
				errorMessageFailedConn += "Attorney changes failed for Coris Test District.<br>";
				logger.error("Attorney Update CORIS_TEST_DISTRCIT_DB Connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_TEST_DISTRICT_DB Connection Close", e);
				}
				conn = null;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it's own try so if it fails, the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (URLEncryption.getParamFromCheckBox(request,"testJustice")){
	    	try {
	    		//System.out.println("CORIS_TEST_JUSTICE_DB");
				conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_TEST_JUSTICE_DB);
				if (conn != null && attorneyBO != null) {
					dataBaseInsertUpdateDeleteAttorneyInformation(conn, "J", attorneyBO, request, response, "Coris Test Justice");
				}
			} catch (Exception e) {
				errorMessageFailedConn += "Attorney changes failed for Coris Test Justice.<br>";
				logger.info("Attorney Update CORIS_TEST_JUSTICE_DB Connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_TEST_JUSTICE_DB Connection Close", e);
				}
				conn = null;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it's own try so if it fails, the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (URLEncryption.getParamFromCheckBox(request,"training")){
	    	try {
	    		//System.out.println("CORIS_TRAINING_DB");
				conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_TRAINING_DB);
				if (conn != null && attorneyBO != null) {
					dataBaseInsertUpdateDeleteAttorneyInformation(conn, "D", attorneyBO, request, response, "Coris Training");
				}
			} catch (Exception e) {
				errorMessageFailedConn += "Attorney changes failed for Coris Training.<br>";
				logger.info("Attorney Update CORIS_TRAINING_JUSTICE_DB Connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_TRAINING_DB Connection Close", e);
				}
				conn = null;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it's own try so if it fails, the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (URLEncryption.getParamFromCheckBox(request,"verify")){
	    	try {
	    		//System.out.println("CORIS_VERIFY_DB");
				conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_VERIFY_DB);
				if (conn != null && attorneyBO != null) {
					dataBaseInsertUpdateDeleteAttorneyInformation(conn, "D", attorneyBO, request, response, "Coris Verify");
				}
			} catch (Exception e) {
				errorMessageFailedConn += "Attorney changes failed for Coris Verify.<br>";
				logger.info("Attorney Update CORIS_VERIFY_DB fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_VERIFY_DB Connection Close", e);
				}
				conn = null;
			}
		}		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it's own try so if it fails, the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (URLEncryption.getParamFromCheckBox(request,"development")){
	    	try {
	    		//System.out.println("CORIS_DEVELOPMENT_DB");
				conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_DEVELOPMENT_DB);
				if (conn != null && attorneyBO != null) {
					dataBaseInsertUpdateDeleteAttorneyInformation(conn, "D", attorneyBO, request, response, "Coris Development");
				}
			} catch (Exception e) {
				errorMessageFailedConn += "Attorney changes failed for Coris Development.<br>";
				logger.info("Attorney Update CORIS_DEVELOPMENT_DB fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_DEVELOPMENT_DB Connection Close", e);
				}
				conn = null;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it's own try so if it fails, the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (URLEncryption.getParamFromCheckBox(request,"testCare")){
	    	try {
	    		//System.out.println("CARE_TEST_DB");
				conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CARE_TEST_DB);
				if (conn != null && attorneyBO != null) {
					dataBaseCareInsertAttorneyBarLoadInformation(conn, attorneyBO);
				}
			} catch (Exception e) {
				errorMessageFailedConn += "Attorney changes failed for Test Care.<br>";
				logger.info("Attorney Update CARE_TEST_DB Connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CARE_TEST_DB Connection Close", e);
				}
				conn = null;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it's own try so if it fails, the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (URLEncryption.getParamFromCheckBox(request,"developmentCare")){
	    	try {
	    		//System.out.println("CARE_DEVELOPEMNT_DB");
				conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CARE_DEVELOPMENT_DB);
				if (conn != null && attorneyBO != null) {
					dataBaseCareInsertAttorneyBarLoadInformation(conn, attorneyBO);
				}
			} catch (Exception e) {
				errorMessageFailedConn += "Attorney changes failed for Development Care.<br>";
				logger.info("Attorney Update CARE_DEVEVELOPMENT_DB Connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CARE_DEV_DB Connection Close", e);
				}
				conn = null;
			}
		}
	}

	private AttorneyBO getAttorneyData(HttpServletRequest request) throws Exception {
		AttorneyBO attorney = attorneyDetails(request);
		attorney.setAddress1(URLEncryption.getParamAsStringFromRequest(request, "address1"));
		attorney.setAddress2(URLEncryption.getParamAsStringFromRequest(request, "address2"));
		attorney.setAddress3(URLEncryption.getParamAsStringFromRequest(request, "address3"));
		attorney.setBusPhone(URLEncryption.getParamAsStringFromRequest(request, "busPhone"));
		attorney.setBarStatus(URLEncryption.getParamAsStringFromRequest(request, "barStatus"));
		attorney.setCellPhone(URLEncryption.getParamAsStringFromRequest(request, "cellPhone"));
		attorney.setCity(URLEncryption.getParamAsStringFromRequest(request, "city"));
		attorney.setCountry(URLEncryption.getParamAsStringFromRequest(request, "country"));
		attorney.setEmailAddress(URLEncryption.getParamAsStringFromRequest(request, "email"));
		attorney.setFaxNum(URLEncryption.getParamAsStringFromRequest(request, "faxNum"));
		attorney.setFirstName(URLEncryption.getParamAsStringFromRequest(request, "firstName"));
		attorney.setHomePhone(URLEncryption.getParamAsStringFromRequest(request, "homePhone"));
		attorney.setLastName(URLEncryption.getParamAsStringFromRequest(request, "lastName"));
		attorney.setMiddleInitial(URLEncryption.getParamAsStringFromRequest(request, "midInit"));
		attorney.setOrganization(URLEncryption.getParamAsStringFromRequest(request, "organization"));
		attorney.setPrefix(URLEncryption.getParamAsStringFromRequest(request, "prefix"));
		attorney.setStateCode(URLEncryption.getParamAsStringFromRequest(request, "state"));
		attorney.setZipCode(URLEncryption.getParamAsStringFromRequest(request, "zipCode"));
		return attorney;
	}

    public static void dataBaseInsertUpdateDeleteAttorneyInformation(Connection conn, String courtType, AttorneyBO attorneyBO, HttpServletRequest request, HttpServletResponse response, String selectedServer) throws Exception {
    	String action = URLEncryption.getParamAsString(request, "action");
    	int barNum = URLEncryption.getParamAsInt(request, "barNum");
    	String barState = URLEncryption.getParamAsString(request, "barState");
    	
		//////////////////////////////////////////////////////////////////////////
		// Does Attorney Exist? Delete or Update else Insert
		//////////////////////////////////////////////////////////////////////////
		try {
			/////////////////////////////////////
			//DELETE
			/////////////////////////////////////
			if("deleteAttorney".equals(action) && barNum > 0 && !TextUtil.isEmpty(barState)){
				int casesFound = 0;
				AttyPartyBO casesCountBO = new AttyPartyBO(courtType)
						.where(AttyPartyBO.BARNUM,  barNum)
						.where(AttyPartyBO.BARSTATE,  barState)
						.count(AttyPartyBO.BARNUM)
						.setUseConnection(conn)
						.find();
				casesFound = casesCountBO.getCount(); 
				if(casesFound > 0){
					errorMessageFailedUpdate += "Cannot delete attorney on "+selectedServer+" because attorney is or has been attached to at least one case.<br>";
				}else{
					new AttorneyBO(courtType)
						.where(AttorneyBO.BARNUM, barNum)
						.where(AttorneyBO.BARSTATE, barState)
						.setUseConnection(conn)
						.delete();
					successMessage += "Attorney deleted on "+selectedServer+".<br>";
				}
			}else if(barNum > 0 && !TextUtil.isEmpty(barState)){
				/////////////////////////////////////
				//UPDATE
				/////////////////////////////////////
				if (barNum > 0 && !"add".equals(action)){
					attorneyBO.where(AttorneyBO.BARNUM, barNum);
					attorneyBO.where(AttorneyBO.BARSTATE, barState);
					attorneyBO.setUseConnection(conn);
					attorneyBO.update();
					successMessage += "Attorney updated on "+selectedServer+".<br>";
				/////////////////////////////////////
				//INSERT
				/////////////////////////////////////
				}else if("add".equals(action)){
					attorneyBO.setBarNum(barNum);
					attorneyBO.setBarState(barState);
					attorneyBO.setUseConnection(conn);
					attorneyBO.insert();
					successMessage += "Attorney added on "+selectedServer+".<br>";
				}else{
					errorMessageFailedUpdate += "Attorney not added on "+selectedServer+" because Bar Number "+attorneyBO.getBarNum()+" and Bar State "+attorneyBO.getBarState()+" already exists.<br>";
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessageFailedUpdate += "Unable to save changes on "+selectedServer+".<br>";
		}
	}

    public static void dataBaseCareInsertAttorneyBarLoadInformation(Connection conn, AttorneyBO attorneyBO) {
		//////////////////////////////////////////////////////////////////////////
		// Does Attorney Exist? Update else Insert
		//////////////////////////////////////////////////////////////////////////
		try {
			new AttorneybarloadBO()
			.setBarnumber(String.valueOf(attorneyBO.getBarNum()))
			.setPrefix(TextUtil.makeProper(attorneyBO.getPrefix()))
			.setLastname(TextUtil.makeProper(attorneyBO.getLastName()))
			.setMiddlename(TextUtil.makeProper(attorneyBO.getMiddleInitial()))
			.setFirstname(TextUtil.makeProper(attorneyBO.getFirstName()))
			.setFirmtitle(TextUtil.makeProper(attorneyBO.getOrganization()))
			.setAddrline1(TextUtil.makeProper(attorneyBO.getAddress1()))
			.setAddrline2(TextUtil.makeProper(attorneyBO.getAddress2()))
			.setCity(TextUtil.makeProper(attorneyBO.getCity()))
			.setState(TextUtil.makeProper(attorneyBO.getStateCode()))
			.setZip(attorneyBO.getZipCode())
			.setPhone(attorneyBO.getBusPhone())
			.setFax(attorneyBO.getFaxNum())
			.setStatus(attorneyBO.getBarStatus())
			.setEmail(attorneyBO.getEmailAddress())
			.setUseConnection(conn)
			.insert();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	private AttorneyBO attorneyDetails(HttpServletRequest request) throws Exception {
		int barNum = URLEncryption.getParamAsInt(request, "barNum");
		String barState = URLEncryption.getParamAsString(request, "barState");
		String courtType = URLEncryption.getParamAsString(request, "courtType"); //get passed in value, if it exists
		if (courtType == null || "".equals(courtType)) {
			courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE); //default	
		}
		request.setAttribute("courtType", courtType);
		return new AttorneyBO(courtType).where(AttorneyBO.BARNUM, barNum).where(AttorneyBO.BARSTATE, barState).find();
	}

}
