package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.ConstantsConnectionProperties;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.attypresent.AttyPresentBO;
import gov.utcourts.coriscommon.dataaccess.continuance.ContinuanceBO;
import gov.utcourts.coriscommon.dataaccess.prosecutor.ProsecutorBO;
import gov.utcourts.coriscommon.dataaccess.state.StateBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.ConnectionProperties;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

/**
* Servlet implementation class CorisAttorneyBarNumberFixServlet
* 
* NOTE: This class will replace the upd_bar_num.sql stored procedure
* 
*/
@WebServlet("/CorisAttorneyBarNumberFixServlet")
public class CorisAttorneyBarNumberFixServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CorisAttorneyBarNumberFixServlet.class.getName());
     
	private static final String DEFAULT_PAGE = "/jsp/corisAttorneyBarNumberFix.jsp";
	
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CorisAttorneyBarNumberFixServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
			switch (mode) {
				case FIND:
					find(request, response);
					break;
				case UPDATE:
					fixBarNumber(request, response);
					break;
				default:
					defaultPage(request, response);
					break;
			}	
			mode = null;
		} catch (Exception e){
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error("CorisAttorneyBarNumberFixServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}

	private void defaultPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getStates(request, response);
		getServletContext().getRequestDispatcher(DEFAULT_PAGE).forward(request, response);
	}

	private void find(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject retValObj = new JSONObject();
		try {
			String courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
			
			// incorrect attorney
			int incorrectBarNum = URLEncryption.getParamAsInt(request, "incorrectBarNum");
			String incorrectBarState = URLEncryption.getParamAsString(request, "incorrectBarState");
			AttorneyBO incorrectAttorney = new AttorneyBO(courtType).where(AttorneyBO.BARNUM, incorrectBarNum).where(Exp.lower(AttorneyBO.BARSTATE), Exp.EQUALS, incorrectBarState.toLowerCase()).find();
			retValObj.put("incorrectAttyName", TextUtil.print(incorrectAttorney.getFirstName()) + " " + TextUtil.print(incorrectAttorney.getLastName()));
			retValObj.put("incorrectAttyAddr1", incorrectAttorney.getAddress1());
			retValObj.put("incorrectAttyAddr2", incorrectAttorney.getAddress2());
			retValObj.put("incorrectAttyCity", incorrectAttorney.getCity());
			retValObj.put("incorrectAttyState", incorrectAttorney.getStateCode());
			retValObj.put("incorrectAttyZip", incorrectAttorney.getZipCode());
			incorrectAttorney = null;
			
			// correct attorney
			int correctBarNum = URLEncryption.getParamAsInt(request, "correctBarNum");
			String correctBarState = URLEncryption.getParamAsString(request, "correctBarState");
			AttorneyBO correctAttorney = new AttorneyBO(courtType).where(AttorneyBO.BARNUM, correctBarNum).where(Exp.lower(AttorneyBO.BARSTATE), Exp.EQUALS, correctBarState.toLowerCase()).find();
			retValObj.put("correctAttyName", TextUtil.print(correctAttorney.getFirstName()) + " " + TextUtil.print(correctAttorney.getLastName()));
			retValObj.put("correctAttyAddr1", correctAttorney.getAddress1());
			retValObj.put("correctAttyAddr2", correctAttorney.getAddress2());
			retValObj.put("correctAttyCity", correctAttorney.getCity());
			retValObj.put("correctAttyState", correctAttorney.getStateCode());
			retValObj.put("correctAttyZip", correctAttorney.getZipCode());
			correctAttorney = null;

		} catch (Exception e) {
			retValObj.put("errorMessage", e.getMessage());
		}
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		response.getWriter().write(retValObj.toString());
	}

	private void fixBarNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject retValObj = new JSONObject();
		String courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
		
		// incorrect attorney
		int incorrectBarNum = URLEncryption.getParamAsInt(request, "incorrectBarNum");
		String incorrectBarState = URLEncryption.getParamAsString(request, "incorrectBarState");
		
		// correct attorney
		int correctBarNum = URLEncryption.getParamAsInt(request, "correctBarNum");
		String correctBarState = URLEncryption.getParamAsString(request, "correctBarState");

		List<ConnectionProperties> connectionPropertiesList = getConnections(request);
		if (connectionPropertiesList.size() > 0) {
			for (ConnectionProperties connectionProperties : connectionPropertiesList) {
				Connection conn = null;
				try {
					conn = DatabaseConnection.getConnection(connectionProperties);
					processFixForConnection(conn, courtType, incorrectBarNum, incorrectBarState, correctBarNum, correctBarState);
				} catch (Exception e) {
					retValObj.put("errorMessage", e.getMessage());
				} finally {
					try {
						if (conn != null)
							conn.close();
					} catch (SQLException e) {
						logger.error("Coris Attorney Bar Number Fix - " + connectionProperties.getDatasourceName(), e);
					}
					conn = null;
				}
			}
		}
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		response.getWriter().write(retValObj.toString());
	}
	
	private static List<ConnectionProperties> getConnections(HttpServletRequest request) {
		List<ConnectionProperties> properties = new ArrayList<ConnectionProperties>();
		
		if (URLEncryption.getParamAsBoolean(request, "productionDistrict")) 
			properties.add(ConstantsConnectionProperties.CORIS_PRODUCTION_DISTRICT_DB);
		
		if (URLEncryption.getParamAsBoolean(request, "productionJustice")) 
			properties.add(ConstantsConnectionProperties.CORIS_PRODUCTION_JUSTICE_DB);
		
		if (URLEncryption.getParamAsBoolean(request, "testDistrict")) 
			properties.add(ConstantsConnectionProperties.CORIS_TEST_DISTRICT_DB);
		
		if (URLEncryption.getParamAsBoolean(request, "testJustice")) 
			properties.add(ConstantsConnectionProperties.CORIS_TEST_JUSTICE_DB);
		
		if (URLEncryption.getParamAsBoolean(request, "verify")) 
			properties.add(ConstantsConnectionProperties.CORIS_VERIFY_DB);
		
		if (URLEncryption.getParamAsBoolean(request, "training")) 
			properties.add(ConstantsConnectionProperties.CORIS_TRAINING_DB);
		
		if (URLEncryption.getParamAsBoolean(request, "development")) 
			properties.add(ConstantsConnectionProperties.CORIS_DEVELOPMENT_DB);
		
		return properties;
	}
	
	private void getStates(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
		try {
			request.setAttribute("statesList", new StateBO(courtType).orderBy(StateBO.NAME).search());
		} catch (Exception e) {
			logger.error("CorisAttorneyBarNumberFixServlet getStates(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
	private static void processFixForConnection(Connection conn, String courtType, int incorrectBarNum, String incorrectBarState, int correctBarNum, String correctBarState) throws Exception {
		
		int PRINT_SQL = BaseConstants.PRINT + BaseConstants.RUN;
		
		// atty_party
		List<AttyPartyBO> fixAttyPartyList = new AttyPartyBO(courtType).where(AttyPartyBO.BARNUM, incorrectBarNum).where(AttyPartyBO.BARSTATE, incorrectBarState).setUseConnection(conn).search();
		if (fixAttyPartyList.size() > 0) {
			
			AttyPartyBO newAttyParty = null;
			for (AttyPartyBO fixAttyParty : fixAttyPartyList) {
				newAttyParty = new AttyPartyBO(courtType)
				.where(AttyPartyBO.BARNUM, correctBarNum)
				.where(AttyPartyBO.BARSTATE, correctBarState)
				.where(AttyPartyBO.INTCASENUM, fixAttyParty.getIntCaseNum())
				.setReturnNull(true)
				.toString(PRINT_SQL)
				.find();
				if (newAttyParty != null) {
					
					// delete duplicate
					new AttyPartyBO(courtType)
					.where(AttyPartyBO.BARNUM, incorrectBarNum)
					.where(AttyPartyBO.BARSTATE, incorrectBarState)
					.where(AttyPartyBO.INTCASENUM, fixAttyParty.getIntCaseNum())
					.toString(PRINT_SQL)
					.setUseConnection(conn)
					.delete();
					
				} else {
							
					// update
					new AttyPartyBO(courtType)
					.where(AttyPartyBO.BARNUM, incorrectBarNum)
					.where(AttyPartyBO.BARSTATE, incorrectBarState)
					.where(AttyPartyBO.INTCASENUM, fixAttyParty.getIntCaseNum())
					.toString(PRINT_SQL)
					.setUseConnection(conn)
					.update(
						AttyPartyBO.BARNUM.setValue(correctBarNum),
						AttyPartyBO.BARSTATE.setValue(correctBarState)
					);
					
				}
				
				newAttyParty = null;
			}
		}
		
		// atty_present
		List<AttyPresentBO> fixAttyPresentList = new AttyPresentBO(courtType).where(AttyPresentBO.BARNUM, incorrectBarNum).where(AttyPresentBO.BARSTATE, incorrectBarState).search();
		if (fixAttyPresentList.size() > 0) {
			
			AttyPresentBO newAttyPresent = null;
			for (AttyPresentBO fixAttyPresent : fixAttyPresentList) {
				newAttyPresent = new AttyPresentBO(courtType)
				.where(AttyPresentBO.BARNUM, correctBarNum)
				.where(AttyPresentBO.BARSTATE, correctBarState)
				.where(AttyPresentBO.INTCASENUM, fixAttyPresent.getIntCaseNum())
				.where(AttyPresentBO.MEID, fixAttyPresent.getMeId())
				.setReturnNull(true)
				.toString(PRINT_SQL)
				.find();
				
				if (newAttyPresent != null) {
					
					// delete duplicate
					new AttyPresentBO(courtType)
					.where(AttyPresentBO.BARNUM, incorrectBarNum)
					.where(AttyPresentBO.BARSTATE, incorrectBarState)
					.where(AttyPresentBO.INTCASENUM, fixAttyPresent.getIntCaseNum())
					.where(AttyPresentBO.MEID, fixAttyPresent.getMeId())
					.toString(PRINT_SQL)
					.setUseConnection(conn)
					.delete();
					
				} else {
							
					// update
					new AttyPresentBO(courtType)
					.where(AttyPresentBO.BARNUM, incorrectBarNum)
					.where(AttyPresentBO.BARSTATE, incorrectBarState)
					.where(AttyPresentBO.INTCASENUM, fixAttyPresent.getIntCaseNum())
					.where(AttyPresentBO.MEID, fixAttyPresent.getMeId())
					.toString(PRINT_SQL)
					.setUseConnection(conn)
					.update(
						AttyPresentBO.BARNUM.setValue(correctBarNum),
						AttyPresentBO.BARSTATE.setValue(correctBarState)
					);
					
				}
				
				newAttyPresent = null;
			}
			
		}
		
		// continuance
		List<ContinuanceBO> fixContinuanceList = new ContinuanceBO(courtType).where(ContinuanceBO.BARNUM, incorrectBarNum).where(ContinuanceBO.BARSTATE, incorrectBarState).search();
		if (fixContinuanceList.size() > 0) {
			
			ContinuanceBO newConintuance = null;
			for (ContinuanceBO fixContinuance : fixContinuanceList) {
				newConintuance = new ContinuanceBO(courtType)
				.where(ContinuanceBO.BARNUM, correctBarNum)
				.where(ContinuanceBO.BARSTATE, correctBarState)
				.where(ContinuanceBO.INTCASENUM, fixContinuance.getIntCaseNum())
				.setReturnNull(true)
				.toString(PRINT_SQL)
				.find();
				
				if (newConintuance != null) {
					
					// delete duplicate
					new ContinuanceBO(courtType)
					.where(ContinuanceBO.BARNUM, incorrectBarNum)
					.where(ContinuanceBO.BARSTATE, incorrectBarState)
					.where(ContinuanceBO.INTCASENUM, fixContinuance.getIntCaseNum())
					.toString(PRINT_SQL)
					.setUseConnection(conn)
					.delete();
					
				} else {
							
					// update
					new ContinuanceBO(courtType)
					.where(ContinuanceBO.BARNUM, incorrectBarNum)
					.where(ContinuanceBO.BARSTATE, incorrectBarState)
					.where(ContinuanceBO.INTCASENUM, fixContinuance.getIntCaseNum())
					.toString(PRINT_SQL)
					.setUseConnection(conn)
					.update(
						ContinuanceBO.BARNUM.setValue(correctBarNum),
						ContinuanceBO.BARSTATE.setValue(correctBarState)
					);
					
				}
				
				newConintuance = null;
			}
		}
		
		// DELETE FROM prosecutor
		new ProsecutorBO(courtType).where(ProsecutorBO.BARNUM, incorrectBarNum).where(ProsecutorBO.BARSTATE, incorrectBarState).toString(PRINT_SQL).setUseConnection(conn).delete();

	    // DELETE FROM attorney
	    new AttorneyBO(courtType).where(AttorneyBO.BARNUM, incorrectBarNum).where(AttorneyBO.BARSTATE, incorrectBarState).toString(PRINT_SQL).setUseConnection(conn).delete();
	}
	
}
