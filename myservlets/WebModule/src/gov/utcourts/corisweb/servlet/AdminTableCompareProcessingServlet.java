package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.tablecompare.AttorneyTableCompareProcess;
import gov.utcourts.corisweb.tablecompare.CountryTableCompareProcess;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.notifications.util.CorisNotificationEmailCommon;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
/**
 * Servlet implementation class AdminTableCompareProcessingServlet
 */
@WebServlet("/AdminTableCompareProcessingServlet")
public class AdminTableCompareProcessingServlet extends BaseServlet implements BaseConstants {
	
	
	private static final long serialVersionUID = 8751358626902358760L;
	
	private static Logger logger = Logger.getLogger(AdminTableCompareProcessingServlet.class);
	
	private static final String FIND_PAGE = "/jsp/adminTableCompareProcessing.jsp";
	private static final String	CLOSE_PAGE = "/jsp/ClosePopin.jsp";
	 
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminTableCompareProcessingServlet() {
        super();
    }
    
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TableCompareProcessing(request, response);
	}
	
	/**
	 * Handle Table Compare Process
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	private void TableCompareProcessing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(SessionVariables.LOG_NAME);
		String mode = URLEncryption.getParamAsString(request, "mode");
		String emailAddress = "";
		
		logger.info("<----- Process Table Compare Start ----->");
		
		boolean attorney = URLEncryption.getParamFromCheckBox(request, "attorney");
		boolean country = URLEncryption.getParamFromCheckBox(request, "country");
		
		// ///////////////////////////////////////////////////////////////
		// Personnel Information
		// ///////////////////////////////////////////////////////////////
		try {
			PersonnelBO personnelBO = new PersonnelBO("D")
			.where(PersonnelBO.LOGNAME, (String) session.getAttribute(SessionVariables.LOG_NAME))
			.setDistinct()
			.find(PersonnelBO.EMAILADDRESS);
			emailAddress = personnelBO.getEmailAddress();
			personnelBO = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (attorney){
			StringBuilder strBuilder = new StringBuilder("");
			strBuilder = AttorneyTableCompareProcess.TableCompareAttorney(request, response);
			TableCompareProcessingEmail("Attorney Table Compare Process Results", strBuilder, emailAddress);
			request.setAttribute("process","SUCCESS");
			strBuilder=null;
		}
		if (country){
			StringBuilder strBuilder = new StringBuilder("");
			strBuilder = CountryTableCompareProcess.TableCompareCountry(request, response);
			TableCompareProcessingEmail("Country Table Compare Process Results", strBuilder, emailAddress);
			request.setAttribute("process","SUCCESS");
			strBuilder=null;
		}
		
		forward(FIND_PAGE , request, response);
		session = null;
		userName = null;
		emailAddress = null;
		
		logger.info("<----- Processing Table Compare Complete ----->");
	}
	
	private void TableCompareProcessingEmail(String subject, StringBuilder results, String emailAddress) throws ServletException, IOException {
		CorisNotificationEmailCommon.corisNotificationEmail(999999999, 
							   Constants.SYSTEM_ID,  
				               null, 
				               subject, 
				               results.toString(), 
				               emailAddress,
				               Constants.RETURN_EMAIL,
				               "",
				               Constants.RETURN_EMAIL,
				               "D", 
				               null, 
				               true);
	}
}
