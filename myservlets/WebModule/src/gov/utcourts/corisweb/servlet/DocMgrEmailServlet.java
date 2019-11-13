package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.docmgr.base.DocumentBatchFTP;
import gov.utcourts.docmgr.objects.DocBO;
import gov.utcourts.docmgr.util.DocDirectUtil;
import gov.utcourts.notifications.util.CorisNotificationEmailCommon;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;

/**
* Servlet implementation class DocMgrEmailServlet
*/
@WebServlet("/DocMgrEmailServlet")
public class DocMgrEmailServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
     
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public DocMgrEmailServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] mergedPdf = null;
		
		mergedPdf = pdfImageBytes(request, response);
		
		try {
			emailUser(mergedPdf, request, response);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected byte[] pdfImageBytes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] mergedPdf = null;
		try {

			String callingSystem = URLEncryption.getParamAsString(request,"systemName");
			String docId = URLEncryption.getParamAsString(request, "docId");

		    DocBO dmDoc = DocBO.findDocumentByDocId(Integer.parseInt(docId));
		    List<byte[]> docs = DocumentBatchFTP.mergeDoc(dmDoc);
		    mergedPdf = DocDirectUtil.mergePDF(docs, false);
		  
		} catch (Exception e){
			e.printStackTrace();
		}
	    return mergedPdf ; 
	}
	
	public static void emailUser(byte[] attachment, HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException{
		try {
			
			String callingSystem = URLEncryption.getParamAsString(request,"systemName");
			String caseNum = URLEncryption.getParamAsString(request, "caseNum");
			String locnCode = URLEncryption.getParamAsString(request, "locnCode");
			String courtType = URLEncryption.getParamAsString(request, "courtType");
			String logName = URLEncryption.getParamAsString(request, "logName");
			String docId = URLEncryption.getParamAsString(request, "docId");
			
			// ///////////////////////////////////////////////////////////////
			// Get Kase Information 
			// ///////////////////////////////////////////////////////////////
			KaseBO kaseBO = new KaseBO(courtType)
			.where(KaseBO.CASENUM, caseNum)
			.where(KaseBO.LOCNCODE, locnCode)
			.where(KaseBO.COURTTYPE, courtType)
			.find();
			
			// ///////////////////////////////////////////////////////////////
			// Get Persons Email Address
			// ///////////////////////////////////////////////////////////////
			PersonnelBO personnelBO = new PersonnelBO(courtType)
			.where(PersonnelBO.LOCNCODE, locnCode)
			.where(PersonnelBO.COURTTYPE, courtType)
			.where(PersonnelBO.LOGNAME, logName)
			.find(PersonnelBO.EMAILADDRESS);
			
			// ///////////////////////////////////////////////////////////////
			// Get Persons Email Address
			// ///////////////////////////////////////////////////////////////
			ProfileBO profileBO = new ProfileBO(courtType)
			.where(ProfileBO.LOCNCODE, locnCode)
			.where(ProfileBO.COURTTYPE, courtType)
			.find(ProfileBO.COURTTITLE);
			
			//corisNotificationEmail(int caseMgtId,  byte[] bytes, String subject, String content, String emailAddress, String courtType, String fileName, boolean isStatic)			// ///////////////////////////////////////////////////////////////
			// Send Email
			// ///////////////////////////////////////////////////////////////
			CorisNotificationEmailCommon.corisNotificationEmail(
				kaseBO.getIntCaseNum(),
				Constants.SYSTEM_ID,
				attachment, 
				profileBO.getCourtTitle() + 
					" Case Number " + caseNum + " " + "Document Image", 
				"", 
		        personnelBO.getEmailAddress(), 
		        Constants.RETURN_EMAIL,
		        null,
		        null,
		        courtType, 
		        kaseBO.getCaseNum()+kaseBO.getLocnCode()+kaseBO.getCourtType()+".pdf", 
		        false
		    );
			
			// ///////////////////////////////////////////////////////////////
			// Clean Up
			// ///////////////////////////////////////////////////////////////
			attachment = null;
			profileBO = null;
			personnelBO = null;
			kaseBO = null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
