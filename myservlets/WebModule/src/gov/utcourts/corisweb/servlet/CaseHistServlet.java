package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.casehist.CaseHistBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.CharacterFilter;
import gov.utcourts.courtscommon.constants.BaseConstants;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.json.java.JSONObject;

/**
* Servlet implementation class CaseHistServlet
*/
@WebServlet("/CaseHistServlet")
public class CaseHistServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -4215111253L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public CaseHistServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String mode = TextUtil.getParamAsString(request, "mode");
			
			String courtType = TextUtil.getParamAsString(request, "courtType");
			String caseNum = TextUtil.getParamAsString(request, "caseNum");
			String locnCode = TextUtil.getParamAsString(request, "locnCode");
			String chText = TextUtil.getParamAsString(request, "chText");
			String eventDatetime = TextUtil.getParamAsString(request, "eventDatetime");
			String createDatetime = TextUtil.getParamAsString(request, "createDatetime");
			String newEventDate = TextUtil.getParamAsString(request, "newEventDate");
			String newEventTime = TextUtil.getParamAsString(request, "newEventTime");
			String userInputEventDatetime = newEventDate + " " + newEventTime;
			
			int intCaseNum = new KaseBO(courtType).where(KaseBO.CASENUM, caseNum).where(KaseBO.LOCNCODE, locnCode).find(KaseBO.INTCASENUM).getIntCaseNum();

			// filter for bad input
			chText = CharacterFilter.filter(chText);
			
			DateFormat dateFormat = Constants.dateFormatDatetimeMilliseconds;
			DateFormat userInputdateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm a");

			// retrieve the clerkid (userid_srl) of the person logged in
			int clerkId = new PersonnelBO(courtType)
			.where(PersonnelBO.LOGNAME, (String) request.getSession().getAttribute(SessionVariables.LOG_NAME))
			.where(PersonnelBO.LOCNCODE, locnCode)
			.where(PersonnelBO.COURTTYPE, courtType)
			.find(PersonnelBO.USERIDSRL)
			.getUseridSrl();
			
			if ("delete".equals(mode)) {
				
				// set the removed flag on the case hist note being deleted
				new CaseHistBO(courtType)
				.where(CaseHistBO.INTCASENUM, intCaseNum)
				.where(CaseHistBO.ENTRYDATETIME, dateFormat.parse(eventDatetime))
				.where(CaseHistBO.CREATEDATETIME, dateFormat.parse(createDatetime))
				.setRemovedFlag("Y")
				.update();
				
			} else if ("update".equals(mode)) {

				Date eventTime = userInputdateFormat.parse(userInputEventDatetime);
				eventTime = convertTimeTo24Hour(eventTime, userInputEventDatetime);
				
				blockFutureDate(eventTime);
				
				new CaseHistBO(courtType)
				.where(CaseHistBO.INTCASENUM, intCaseNum)
				.where(CaseHistBO.CREATEDATETIME, dateFormat.parse(createDatetime))
				.setPrimaryKey(CaseHistBO.ENTRYDATETIME, eventTime)
				.setChText(chText)
				.update();
				
			} else if ("add".equals(mode)) {
				
				Date addDate = new Date();
				request.setAttribute("eventDatetime", dateFormat.format(addDate));
				request.setAttribute("createDatetime", dateFormat.format(addDate));
				request.setAttribute("chText", "");
				
			} else if ("edit".equals(mode)) {
				
				String histNote = new CaseHistBO(courtType)
				.where(CaseHistBO.INTCASENUM, intCaseNum)
				.where(CaseHistBO.ENTRYDATETIME, dateFormat.parse(eventDatetime))
				.where(CaseHistBO.CREATEDATETIME, dateFormat.parse(createDatetime))
				.find().getChText();

				request.setAttribute("histNote", histNote);
				
			} else if ("insert".equals(mode) && !TextUtil.isEmpty(chText)) {

				Date eventTime = userInputdateFormat.parse(userInputEventDatetime);
				eventTime = convertTimeTo24Hour(eventTime, userInputEventDatetime);
				
				blockFutureDate(eventTime);
				
				// create a new case history note
				new CaseHistBO(courtType)
				.setIntCaseNum(intCaseNum)
				.setEntryDatetime(eventTime)
				.setCreateDatetime(dateFormat.parse(createDatetime))
				.setFuncId("HISTNOTE")
				.setClerkId(clerkId)
				.setChText(chText)
				.insert();
				
			}
			
			// cleanup
			mode = null;
			courtType = null;
			caseNum = null;
			locnCode = null;
			eventDatetime = null;
			createDatetime = null;
			chText = null;
		} catch (Exception e) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("error", true);
			jsonObj.put("message", e.getMessage());
			
			throw new ServletException(e.getMessage());	
		}
		
		// display the selected page
		getServletContext().getRequestDispatcher("/jsp/histNotePopup.jsp").forward(request, response);

	}
	
	private Date convertTimeTo24Hour(Date eventTime, String inputEventDatetime) {
		Calendar calendar = GregorianCalendar.getInstance(); 
		calendar.setTime(eventTime);    
		int hourOfTheDay = calendar.get(Calendar.HOUR_OF_DAY); 
		
		if (inputEventDatetime.endsWith("PM") && hourOfTheDay < 12)
			eventTime = new Date(eventTime.getTime() + 12 * 3600*1000);
		
		return eventTime;
	}
	
	private void blockFutureDate(Date eventTime) throws Exception {
		if (eventTime.after(new Date()))
			throw new Exception("Error: Future date not allowed.");
	}
}
