package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.acctadj.AcctAdjBO;
import gov.utcourts.coriscommon.dataaccess.acctbail.AcctBailBO;
import gov.utcourts.coriscommon.dataaccess.acctbond.AcctBondBO;
import gov.utcourts.coriscommon.dataaccess.acctfee.AcctFeeBO;
import gov.utcourts.coriscommon.dataaccess.accttrust.AcctTrustBO;
import gov.utcourts.coriscommon.dataaccess.acctwaiver.AcctWaiverBO;
import gov.utcourts.coriscommon.dataaccess.amendinfo.AmendInfoBO;
import gov.utcourts.coriscommon.dataaccess.appellate.AppellateBO;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attrtype.AttrTypeBO;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.bondco.BondCoBO;
import gov.utcourts.coriscommon.dataaccess.bondtype.BondTypeBO;
import gov.utcourts.coriscommon.dataaccess.calendar.CalendarBO;
import gov.utcourts.coriscommon.dataaccess.caseme.CaseMeBO;
import gov.utcourts.coriscommon.dataaccess.casemedocument.CaseMeDocumentBO;
import gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.checkdetl.CheckDetlBO;
import gov.utcourts.coriscommon.dataaccess.chrgattr.ChrgAttrBO;
import gov.utcourts.coriscommon.dataaccess.civilcase.CivilCaseBO;
import gov.utcourts.coriscommon.dataaccess.county.CountyBO;
import gov.utcourts.coriscommon.dataaccess.courtroom.CourtroomBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.docansparty.DocAnsPartyBO;
import gov.utcourts.coriscommon.dataaccess.docanswer.DocAnswerBO;
import gov.utcourts.coriscommon.dataaccess.docissue.DocIssueBO;
import gov.utcourts.coriscommon.dataaccess.docmotion.DocMotionBO;
import gov.utcourts.coriscommon.dataaccess.docorder.DocOrderBO;
import gov.utcourts.coriscommon.dataaccess.docreturn.DocReturnBO;
import gov.utcourts.coriscommon.dataaccess.document.DocumentBO;
import gov.utcourts.coriscommon.dataaccess.domviollink.DomViolLinkBO;
import gov.utcourts.coriscommon.dataaccess.evidence.EvidenceBO;
import gov.utcourts.coriscommon.dataaccess.feetype.FeeTypeBO;
import gov.utcourts.coriscommon.dataaccess.govtype.GovTypeBO;
import gov.utcourts.coriscommon.dataaccess.hearingtype.HearingTypeBO;
import gov.utcourts.coriscommon.dataaccess.jdmttype.JdmtTypeBO;
import gov.utcourts.coriscommon.dataaccess.judgehist.JudgeHistBO;
import gov.utcourts.coriscommon.dataaccess.judgment.JudgmentBO;
import gov.utcourts.coriscommon.dataaccess.judgmentcreditor.JudgmentCreditorBO;
import gov.utcourts.coriscommon.dataaccess.judgmentdebtor.JudgmentDebtorBO;
import gov.utcourts.coriscommon.dataaccess.judgmentdetl.JudgmentDetlBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.lea.LeaBO;
import gov.utcourts.coriscommon.dataaccess.mediation.MediationBO;
import gov.utcourts.coriscommon.dataaccess.mediationoutcome.MediationOutcomeBO;
import gov.utcourts.coriscommon.dataaccess.mediator.MediatorBO;
import gov.utcourts.coriscommon.dataaccess.offense.OffenseBO;
import gov.utcourts.coriscommon.dataaccess.officer.OfficerBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.pleatype.PleaTypeBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.securitytype.SecurityTypeBO;
import gov.utcourts.coriscommon.dataaccess.severitytype.SeverityTypeBO;
import gov.utcourts.coriscommon.dataaccess.stay.StayBO;
import gov.utcourts.coriscommon.dataaccess.staytype.StayTypeBO;
import gov.utcourts.coriscommon.dataaccess.timepay.TimepayBO;
import gov.utcourts.coriscommon.dataaccess.tracking.TrackingBO;
import gov.utcourts.coriscommon.dataaccess.trackingtype.TrackingTypeBO;
import gov.utcourts.coriscommon.dataaccess.trans.TransBO;
import gov.utcourts.coriscommon.dataaccess.transdetl.TransDetlBO;
import gov.utcourts.coriscommon.dataaccess.transdist.TransDistBO;
import gov.utcourts.coriscommon.dataaccess.transrevrs.TransRevrsBO;
import gov.utcourts.coriscommon.dataaccess.trusttype.TrustTypeBO;
import gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO;
import gov.utcourts.coriscommon.dataaccess.warrreasontype.WarrReasonTypeBO;
import gov.utcourts.coriscommon.dto.CaseMeDataDTO;
import gov.utcourts.coriscommon.dto.CorisCaseHistoryDTO;
import gov.utcourts.coriscommon.dto.PrintCaseMeDataDTO;
import gov.utcourts.coriscommon.dto.SummaryEventCaseHistoryDTO;
import gov.utcourts.coriscommon.sp.CorisAccountStoredProcedureServlet;
import gov.utcourts.coriscommon.sp.CorisChargeStoredProcedureServlet;
import gov.utcourts.coriscommon.sp.GetCaseTitle;
import gov.utcourts.coriscommon.util.CorisSecurityCommon;
import gov.utcourts.coriscommon.util.PartyRepository;
import gov.utcourts.coriscommon.util.PersonnelRepository;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisinterface.util.CorisCaseHistoryCommon;
import gov.utcourts.corisinterface.util.CorisCaseMeValueCommon;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereCustomDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.formatter.DateFormats;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.docmgr.util.DocDirectUtil;
import gov.utcourts.notifications.util.CorisNotificationEmailCommon;
import gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO;
import gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO;
import gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class CorisCaseHistoryAllReport implements BaseConstants {
	
	private static Logger logger = Logger.getLogger(CorisCaseHistoryAllReport.class.getName());
	
	static final Font font = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL);
	private String caseNumberHeader = "";
	private String caseTypeDescription = "";
	private String caseSecurity = "";
	private String logName;
	private PdfTemplate template;
	private int	fontsize = 11;
	private boolean inquiryUser = false;
	private boolean caseAccess = false;
	
	private boolean privateProtectedAccess = false;
	private boolean expungedAccess = false;
	private boolean mentalAccess = false;
	private boolean sealedAccess = false;
	private boolean safeGuardedAccess = false;
	
	private Connection conn = null;
	private String afterDaysPast = "0";

	private PersonnelRepository personnelRepository = new PersonnelRepository();
	private PartyRepository partyRepository = new PartyRepository();
	
	public class reportMyHeaderFooter extends PdfPageEventHelper {
		public void onEndPage(PdfWriter writer, Document document) {
			Date today = Calendar.getInstance().getTime();
			String signatureDate = Constants.dateTimeFormat.format(today);
			Rectangle pg = document.getPageSize();
	    	PdfContentByte cb = writer.getDirectContent();
	    	String footerPage = "Page " + writer.getPageNumber() + " of ";
	    	String headerPageLine1 = "CASE NUMBER: " + caseNumberHeader  + " " + caseTypeDescription;
	    	
	    	BaseFont bf = null; 
	    	try {
				bf = BaseFont.createFont(BaseFont.COURIER, BaseFont.WINANSI, BaseFont.EMBEDDED);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	cb.beginText();
	    	cb.setFontAndSize(bf, fontsize);
	    	cb.showTextAligned(PdfContentByte.ALIGN_LEFT, signatureDate, pg.getLeft() + 5, 4, 0);
	    	cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, footerPage, pg.getRight() - 30, 4, 0);
	    	if (writer.getPageNumber() > 1 ){
	    		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, headerPageLine1, pg.getLeft() + 5, 770, 0);
	    		if (!TextUtil.isEmpty(caseSecurity)){
		    		cb.showTextAligned(PdfContentByte.ALIGN_RIGHT,"**** " + caseSecurity + " ****", pg.getRight() - 16, 770, 0);
	    		}
	    	}
	    	cb.endText();
	        cb.addTemplate(template, pg.getRight() - 30, 4);
	    	if (writer.getPageNumber() > 1 ){
	    		cb.moveTo(5, 765);
	    		cb.lineTo(605, 765);
	    		cb.closePathStroke();
	    	}
		}		
	}
	
	/**
	  * @see HttpServlet#HttpServlet()
	*/
	public CorisCaseHistoryAllReport() {
	     super();
	}
	  
	public void createReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] chba = null;
		try {
			String caseNum = URLEncryption.getParamAsString(request, "caseNum");
			String locnCode = URLEncryption.getParamAsString(request, "locnCode");
			String courtType = URLEncryption.getParamAsString(request, "courtType");
			logName = URLEncryption.getParamAsString(request, "logName");
			
			String email = URLEncryption.getParamAsString(request, "email");
			String emailType = URLEncryption.getParamAsString(request, "emailType");
			
			// ///////////////////////////////////////////////////////////////
			// All Case Numbers
			// ///////////////////////////////////////////////////////////////
			String caseNumbers[];
			caseNumbers = caseNum.split("[|]");
			
			// ///////////////////////////////////////////////////////////////
			// Create new document
			// ///////////////////////////////////////////////////////////////
			List<byte[]> documentList = new ArrayList<byte[]>();
			byte[]  casehistoryPdf = null;

			for (int i=0; i < caseNumbers.length; i++){
				if (!TextUtil.isEmpty(caseNumbers[i])){
					casehistoryPdf = processCaseHistoryAll(caseNumbers[i], locnCode, courtType, logName, emailType, "0", response);
					if (casehistoryPdf != null && casehistoryPdf.length > 0){
						documentList.add(casehistoryPdf);
					}
				}
			}
			
			chba = DocDirectUtil.mergePDF(documentList, false);
			
			if ("Y".equals(email.toUpperCase())){
				// ///////////////////////////////////////////////////////////////
				// Kase Information
				// ///////////////////////////////////////////////////////////////
				KaseBO kaseBO = new KaseBO(courtType)
				.where(KaseBO.CASENUM, caseNum)
				.where(KaseBO.LOCNCODE, locnCode)
				.where(KaseBO.COURTTYPE, courtType)
				.setUseConnection(conn)
				.find(KaseBO.INTCASENUM);
				
				// ///////////////////////////////////////////////////////////////
				// Personnel Information
				// ///////////////////////////////////////////////////////////////
				PersonnelBO personnelBO = new PersonnelBO(courtType)
				.where(PersonnelBO.LOGNAME, logName)
				.where(PersonnelBO.LOCNCODE, locnCode)
				.where(PersonnelBO.COURTTYPE, courtType)
				.setUseConnection(conn)
				.find(PersonnelBO.EMAILADDRESS);
				
				// ///////////////////////////////////////////////////////////////
				// Profile Information
				// ///////////////////////////////////////////////////////////////
				ProfileBO profileBO = new ProfileBO(courtType)
				.where(ProfileBO.LOCNCODE, locnCode)
				.where(ProfileBO.COURTTYPE, courtType)
				.setUseConnection(conn)
				.find(ProfileBO.COURTTITLE);
				
				// ///////////////////////////////////////////////////////////////
				// Send Email
				// ///////////////////////////////////////////////////////////////
				CorisNotificationEmailCommon.corisNotificationEmail(
					kaseBO.getIntCaseNum(),
					Constants.SYSTEM_ID,
					chba, 
			        profileBO.getCourtTitle() + 
						" Case Number " + caseNum, 
			        "", 
			        personnelBO.getEmailAddress(), 
			        Constants.RETURN_EMAIL,
			        null,
			        null,
			        courtType, 
			        caseNum+locnCode+courtType+".pdf", 
			        false
				);
				personnelBO = null;
				profileBO = null;
				kaseBO = null;
			} else {	
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "inline; filename=sample.pdf");
				response.getOutputStream().write(chba);
				response.getOutputStream().flush();			
			}	
						
			chba = null;
			documentList = null;
			casehistoryPdf = null;
			
		} catch (Exception e) {
			logger.error("performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
	public byte[] processCaseHistoryAll(String caseNum, String locnCode, String courtType, String logname, String emailType, String daysPast, HttpServletResponse response) {
		byte[] chba = null;
		
		try {
			/////////////////////////////////////////////////////
			// Connect to database
			/////////////////////////////////////////////////////
			if ("D".equals(courtType)){;
				conn = DatabaseConnection.getConnection(AccountBO.CORIS_DISTRICT_DB);
			} else {
				conn = DatabaseConnection.getConnection(AccountBO.CORIS_JUSTICE_DB);
			}
			
			/////////////////////////////////////////////////////
			// Case Data Initialized
			/////////////////////////////////////////////////////
			CorisCaseHistoryDTO corisCaseHistoryDTO = new CorisCaseHistoryDTO();
			
			/////////////////////////////////////////////////////
			// Kase Data
			/////////////////////////////////////////////////////
			corisCaseHistoryDTO.setKaseBO(new KaseBO(courtType).
			where(KaseBO.CASENUM, caseNum).
			where(KaseBO.LOCNCODE, locnCode).
			setUseConnection(conn).
			find());
			
			/////////////////////////////////////////////////////
			// Globals Initialized
			/////////////////////////////////////////////////////
			caseNumberHeader = caseNum;
			logName = logname;
			afterDaysPast = daysPast;
			
			if (corisCaseHistoryDTO.getKaseBO() == null){
				return caseNotFound(caseNum, locnCode, courtType, logName, response);
			}
			
			/////////////////////////////////////////////////////
			// User Security
			/////////////////////////////////////////////////////
			inquiryUser = CorisSecurityCommon.checkSecurityAccess(logName, "Inquirylogon", locnCode, courtType, conn);
			boolean locnAccess = CorisSecurityCommon.checkLocationAccess(logName, locnCode, courtType, conn);
			if (!locnAccess) {
				inquiryUser = true; 
			}
			
			privateProtectedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "privateprotected", locnCode, courtType, conn);
			sealedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "sealed", locnCode, courtType, conn);
			expungedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "expunged", locnCode, courtType, conn);
			mentalAccess = CorisSecurityCommon.checkSecurityAccess(logName, "mental", locnCode, courtType, conn);
			safeGuardedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "safeguarded", locnCode, courtType, conn);
			
			if ("PUBLIC".equals(emailType)){
				inquiryUser = true; 
				privateProtectedAccess = false;
				sealedAccess = false;
				expungedAccess = false;
				mentalAccess = false;
				safeGuardedAccess = false;
			}
			
			
			/////////////////////////////////////////////////////
			// Judge Comm Data
			/////////////////////////////////////////////////////
			if (corisCaseHistoryDTO.getKaseBO().getAssnJudgeId() > 0){
				corisCaseHistoryDTO.setJudgeCommName(CorisCaseHistoryCommon.getJudgeCommName(corisCaseHistoryDTO.getKaseBO().getAssnJudgeId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn));
			}
			if (corisCaseHistoryDTO.getKaseBO().getAssnCommId() > 0){
				corisCaseHistoryDTO.setJudgeCommName(CorisCaseHistoryCommon.getJudgeCommName(corisCaseHistoryDTO.getKaseBO().getAssnCommId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn));
			}
			/////////////////////////////////////////////////////
			// Case Access
			/////////////////////////////////////////////////////
			caseAccess = false;
			if ("U".equals(corisCaseHistoryDTO.getKaseBO().getCaseSecurity())){
				caseAccess = true;
			} else if ("V".equals(corisCaseHistoryDTO.getKaseBO().getCaseSecurity())){
				caseAccess = true;
			} else if ("S".equals(corisCaseHistoryDTO.getKaseBO().getCaseSecurity())){
				caseAccess = sealedAccess;
			} else if ("X".equals(corisCaseHistoryDTO.getKaseBO().getCaseSecurity())){
				caseAccess = expungedAccess;
			} else if ("O".equals(corisCaseHistoryDTO.getKaseBO().getCaseSecurity())){
				caseAccess = privateProtectedAccess;
			}
			
			if (caseAccess){
				if("IC".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) || "IS".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
					caseAccess = mentalAccess;
				}else if ("AD".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) || "GA".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
					caseAccess = CorisSecurityCommon.grantAccess(logName, corisCaseHistoryDTO.getKaseBO().getCaseSecurity(), corisCaseHistoryDTO.getKaseBO().getLocnCode(), corisCaseHistoryDTO.getKaseBO().getCourtType(), null);
					if (inquiryUser || !caseAccess){
						caseAccess = false;
					}
				}
			}
			
			///////////////////////////////////////////////////////////////////////////////
			//No case Access
			///////////////////////////////////////////////////////////////////////////////
			if (!caseAccess){
				return denyCaseAccess(caseNum, locnCode, courtType, logName, response);
			}
			
			///////////////////////////////////////////////////////////////////////////////
			//Get Profile Information
			///////////////////////////////////////////////////////////////////////////////
			corisCaseHistoryDTO.setProfileBO(new ProfileBO(courtType).
			where(ProfileBO.LOCNCODE, locnCode).
			where(ProfileBO.COURTTYPE, courtType).
			setUseConnection(conn).
			find()); 
			
			///////////////////////////////////////////////////////////////////////////////
			//Get County Information
			///////////////////////////////////////////////////////////////////////////////
			corisCaseHistoryDTO.setCountyBO(new CountyBO(courtType).
			where(CountyBO.CNTYCODE,corisCaseHistoryDTO.getProfileBO().getCntyCode()).
			setUseConnection(conn).
			find(CountyBO.NAME));
		
			/////////////////////////////////////////////////////
			// Case Type Data
			/////////////////////////////////////////////////////
			corisCaseHistoryDTO.setCaseTypeBO(new CaseTypeBO(courtType).
			where(CaseTypeBO.CASETYPE, corisCaseHistoryDTO.getKaseBO().getCaseType()).
			setUseConnection(conn).
			find());
				
			///////////////////////////////////////////////////////////////////////////////
			// Civil Case Get Civil Case Information
			///////////////////////////////////////////////////////////////////////////////
			if (!"R".equals(corisCaseHistoryDTO.getCaseTypeBO().getCategory())){
				corisCaseHistoryDTO.setCivilCaseBO(new CivilCaseBO(courtType).
				where(KaseBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum()).
				setUseConnection(conn).
				find()); 
			}	
			///////////////////////////////////////////////////////////////////////////////
			//Criminal Case Get Criminal Case Information
			///////////////////////////////////////////////////////////////////////////////
			if ("R".equals(corisCaseHistoryDTO.getCaseTypeBO().getCategory())){
				corisCaseHistoryDTO.setCrimCaseBO(new CrimCaseBO(courtType).
				where(CrimCaseBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum()).
				setUseConnection(conn).
				find());
			}
			chba = generateCaseHistoryAll(corisCaseHistoryDTO, response);
			corisCaseHistoryDTO = null;
		} catch (Exception e) {
			logger.error("processCaseHistoryAll(String caseNum, String locnCode, String courtType, String logName)", e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("Case History All Connection Close", e);
			}
			conn = null;
		}
		
		return chba;
	}
	
    private void closeDocument(Document document, PdfWriter writer) throws DocumentException, IOException {
		populateTemplate(writer);
		document.close();
	}

	private void populateTemplate(PdfWriter writer) throws DocumentException, IOException {
		template.beginText();
		template.setFontAndSize(font.getBaseFont(), fontsize);
		template.showText(Integer.toString(writer.getPageNumber()));
		template.endText();
	}
	
	public byte[] generateCaseHistoryAll(CorisCaseHistoryDTO corisCaseHistoryDTO, HttpServletResponse response) throws DocumentException, IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[1];
		widths[0] = 100.0f;

		PdfPTable table = new PdfPTable(1);
		
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			// ///////////////////////////////////////////////////////////////
			// Create new document
			// ///////////////////////////////////////////////////////////////
			Document document = new Document(PageSize.LETTER);
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			
			reportMyHeaderFooter event = new reportMyHeaderFooter();
			writer.setPageEvent(event);
			
		    // ///////////////////////////////////////////////////////////////
			// Document Set Up - Left, Right, Top, Bottom
			// ///////////////////////////////////////////////////////////////
			document.setMargins(6f, 22f, 30f, 30f);
			document.open();
	
			// ///////////////////////////////////////////////////////////////////////////////////
			// For Page Total
			// ///////////////////////////////////////////////////////////////////////////////////
			PdfContentByte cb = writer.getDirectContent();
			template = cb.createTemplate(50, 20);
			
			// ///////////////////////////////////////////////////////////////////////////////////
			// Cell Set Up
			// ///////////////////////////////////////////////////////////////////////////////////
			PdfPCell caseHistoryCell = new PdfPCell();
			
			caseHistoryCell.setPaddingLeft(20f);
			caseHistoryCell.setBorder(PdfPCell.NO_BORDER);
			
			// ///////////////////////////////////////////////////////////////
			// Case History Court Information
			// ///////////////////////////////////////////////////////////////
			table = getCaseHistoryCourtInformation(corisCaseHistoryDTO);
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			document.add(table);
			
			// ///////////////////////////////////////////////////////////////
			// Case History Header
			// ///////////////////////////////////////////////////////////////
			table = getCaseHistoryHeader(corisCaseHistoryDTO);
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			document.add(table);
			
			// ///////////////////////////////////////////////////////////////
			// Linked Kase Information
			// ///////////////////////////////////////////////////////////////
			table = generateCaseHistoryLinkedKase(corisCaseHistoryDTO);
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			document.add(table);
			
			// ///////////////////////////////////////////////////////////////
			// Linked Defendant Information
			// ///////////////////////////////////////////////////////////////
			if ("R".equals(corisCaseHistoryDTO.getCaseTypeBO().getCategory())){
				table = generateCaseHistoryLinkedDefendant(corisCaseHistoryDTO);
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				document.add(table);
			}
			
			// ///////////////////////////////////////////////////////////////
			// Linked Domestic Violence Cases Information
			// ///////////////////////////////////////////////////////////////
			if ("R".equals(corisCaseHistoryDTO.getCaseTypeBO().getCategory())){
				table = generateCaseHistoryLinkedDomesticViolenceCases(corisCaseHistoryDTO);
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				document.add(table);
			}	

			
			// ///////////////////////////////////////////////////////////////
			// Case History Charges
			// ///////////////////////////////////////////////////////////////
			if ("R".equals(corisCaseHistoryDTO.getCaseTypeBO().getCategory())){
				table = generateCaseHistoryCharge(corisCaseHistoryDTO);
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				document.add(table);
			}
			
			
			
			
			// ///////////////////////////////////////////////////////////////
			// Case History Judge
			// ///////////////////////////////////////////////////////////////
			table = generateCaseHistoryAssignedJudge(corisCaseHistoryDTO);
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			document.add(table);
			
			// ///////////////////////////////////////////////////////////////
			// Case History Parties
			// ///////////////////////////////////////////////////////////////
			table = generateCaseHistoryParties(corisCaseHistoryDTO);
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			document.add(table);
	
			// ///////////////////////////////////////////////////////////////
			// Case History Defendant Information
			// ///////////////////////////////////////////////////////////////
			if ("R".equals(corisCaseHistoryDTO.getCaseTypeBO().getCategory())){
				table = generateCaseHistoryDefendantInformation(corisCaseHistoryDTO);
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				document.add(table);
			}
			
			// ///////////////////////////////////////////////////////////////
			// Case History Account Information
			// ///////////////////////////////////////////////////////////////
			// ///////////////////////////////////////////////////////////////
			// Create table
			// ///////////////////////////////////////////////////////////////
			List<PdfPTable> returnTablesList = generateCaseHistoryAccountInformation(corisCaseHistoryDTO);
			
			for (PdfPTable pdfPTable :returnTablesList ){
				pdfPTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				pdfPTable.completeRow();
				document.add(pdfPTable);
			}
			
			// ///////////////////////////////////////////////////////////////
			// Case Case History Case Note
			// ///////////////////////////////////////////////////////////////
			table = generateCaseHistoryCaseNote(corisCaseHistoryDTO);
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			document.add(table);
			
			// ///////////////////////////////////////////////////////////////
			// Case Case History Proceedings
			// ///////////////////////////////////////////////////////////////
			generateCaseHistoryProceedings(corisCaseHistoryDTO, document);
			
			document.addTitle("Coris Case History - Case Number " + corisCaseHistoryDTO.getKaseBO().getCaseNum());
			closeDocument(document, writer);
		} catch (Exception e) {
			logger.error("Process table widths", e);
		}
		
		return baos.toByteArray();
	}
	
	public PdfPTable getCaseHistoryCourtInformation(CorisCaseHistoryDTO corisCaseHistoryDTO){
		/////////////////////////////////////////////////////////////////
		// Create table
		/////////////////////////////////////////////////////////////////
		float[] widths = new float[1];
		widths[0] = 100.0f;

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("Process CaseHistoryAll table set widths ", e);
		}		
		
		CorisCaseHistoryCommon.addCell(table, corisCaseHistoryDTO.getProfileBO().getCourtTitle(), font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
		CorisCaseHistoryCommon.addCell(table, corisCaseHistoryDTO.getCountyBO().getName().trim() + " COUNTY, STATE OF UTAH", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		return table;
	}
	
	public PdfPTable getCaseHistoryHeader(CorisCaseHistoryDTO corisCaseHistoryDTO){
		float[] widths = new float[2];
		widths[0] = 70.0f;
		widths[1] = 30.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			/////////////////////////////////////////////////////////////////
			// SecurityTypeBO
			/////////////////////////////////////////////////////////////////
			SecurityTypeBO securityTypeBO = CorisSecurityCommon.getSecurityType(corisCaseHistoryDTO.getKaseBO().getCaseSecurity(), corisCaseHistoryDTO.getKaseBO().getCourtType(), conn);
	 
			/////////////////////////////////////////////////////////////////
			// AppellateBO
			/////////////////////////////////////////////////////////////////
			AppellateBO appellateBO = new AppellateBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(AppellateBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum()).
			setUseConnection(conn).
			find(AppellateBO.APPELCASENUM);
			
			////////////////////////////////////////////////////////////////////////////
			// Appealed Line
			////////////////////////////////////////////////////////////////////////////
			if (!TextUtil.isEmpty(appellateBO.getAppelCaseNum())){
				CorisCaseHistoryCommon.addCell(table, "APPEALED: CASE # " + appellateBO.getAppelCaseNum(), font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			} else{
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			}
			////////////////////////////////////////////////////////////////////////////
			// Versus Line
			////////////////////////////////////////////////////////////////////////////
			
			CorisCaseHistoryCommon.addCell(table, GetCaseTitle.getCaseTitle(logName, corisCaseHistoryDTO.getKaseBO().getIntCaseNum(), corisCaseHistoryDTO.getKaseBO().getCaseType(), conn), font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			
			if (TextUtil.isEmpty(securityTypeBO.getCode()) || "U".equals(securityTypeBO.getCode())){
				CorisCaseHistoryCommon.addCell(table, "CASE NUMBER " + corisCaseHistoryDTO.getKaseBO().getCaseNum() + " " +  corisCaseHistoryDTO.getCaseTypeBO().getDescr(), font, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
				caseSecurity = null;
			} else {
				CorisCaseHistoryCommon.addCell(table, "CASE NUMBER " + corisCaseHistoryDTO.getKaseBO().getCaseNum() + " " +  corisCaseHistoryDTO.getCaseTypeBO().getDescr(), font, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
				CorisCaseHistoryCommon.addCell(table, "**** " + securityTypeBO.getDescr() + " ****", font, Element.ALIGN_RIGHT, PdfPCell.BOTTOM, 1);
				caseSecurity = securityTypeBO.getDescr();
			}
			caseTypeDescription = corisCaseHistoryDTO.getCaseTypeBO().getDescr();
			
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			
			
			/////////////////////////////////////////////////////////////////
			// Clean Up
			/////////////////////////////////////////////////////////////////
			widths = null;
			securityTypeBO = null;
			appellateBO = null;
		} catch (Exception e) {
			logger.error("Process CaseHistoryAll set table widths ", e);
		}		
		
		return table;
	}
	
	public PdfPTable generateCaseHistoryLinkedKase(CorisCaseHistoryDTO corisCaseHistoryDTO) throws DocumentException, IOException{
		
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[2];
		widths[0] = 10.0f;
		widths[1] = 90.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			if (corisCaseHistoryDTO.getKaseBO().getLinkedCaseId() > 0){
				// ///////////////////////////////////////////////////////////////
				// Get Linked Case Information
				// ///////////////////////////////////////////////////////////////
				List<KaseBO> kaseLinkedListBO = new KaseBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(KaseBO.LINKEDCASEID, corisCaseHistoryDTO.getKaseBO().getLinkedCaseId()).
				where(KaseBO.LOCNCODE, corisCaseHistoryDTO.getKaseBO().getLocnCode()).
				where(KaseBO.COURTTYPE, corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(new Expression(KaseBO.INTCASENUM, Exp.NOT_EQUALS, corisCaseHistoryDTO.getKaseBO().getIntCaseNum())).
				setUseConnection(conn).
				setReturnNull(true).
				search(KaseBO.CASENUM);
				
				if (kaseLinkedListBO != null){
					if (kaseLinkedListBO.size() > 0){
						StringBuilder sbTitle = new StringBuilder("This case is linked to the following cases: ");
						for (KaseBO kaseLinkedBO :kaseLinkedListBO ){
							sbTitle.append(kaseLinkedBO.getCaseNum());
							if (kaseLinkedListBO.indexOf(kaseLinkedBO) < kaseLinkedListBO.size() - 1){
								sbTitle.append(", ");
							}
						}
						CorisCaseHistoryCommon.addCell(table, sbTitle.toString(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
						sbTitle = null;
						table.setWidths(widths);
					}
				}
			}	
		} catch (Exception e) {
			logger.error("Process generateCaseHistoryLinkedKase(CorisCaseHistoryDTO corisCaseHistoryDTO)", e);

		}
			
		return table;
	}

	public PdfPTable generateCaseHistoryLinkedDefendant(CorisCaseHistoryDTO corisCaseHistoryDTO) throws DocumentException, IOException{
		
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[2];
		widths[0] = 10.0f;
		widths[1] = 90.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		// ///////////////////////////////////////////////////////////////
		// Get Linked Party Information
		// ///////////////////////////////////////////////////////////////
		try {
			
			if (corisCaseHistoryDTO.getKaseBO().getLinkedDefId() > 0 ){
				List<KaseBO> kaseDefLinkedListBO = new KaseBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(KaseBO.LINKEDDEFID, corisCaseHistoryDTO.getKaseBO().getLinkedDefId()).
				where(KaseBO.LOCNCODE, corisCaseHistoryDTO.getKaseBO().getLocnCode()).
				where(KaseBO.COURTTYPE, corisCaseHistoryDTO.getKaseBO().getCourtType()).
				setUseConnection(conn).
				setReturnNull(true).
				search(KaseBO.CASENUM, KaseBO.INTCASENUM);
				
				if (kaseDefLinkedListBO != null){
					if (kaseDefLinkedListBO.size() > 0){
						StringBuilder sbDefTitle = new StringBuilder("Defendants ");
						for (KaseBO kaseDefLinkedBO :kaseDefLinkedListBO ){
							// ///////////////////////////////////////////////////////////////
							// Get Party Case Information
							// ///////////////////////////////////////////////////////////////
							PartyCaseBO partyCaseBO = new PartyCaseBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
							where(PartyCaseBO.INTCASENUM, kaseDefLinkedBO.getIntCaseNum()).
							where(PartyCaseBO.PARTYCODE, "DEF").
							setUseConnection(conn).
							find(PartyCaseBO.PARTYNUM);
							
							// ///////////////////////////////////////////////////////////////
							// Get Party Name Information
							// ///////////////////////////////////////////////////////////////
							PartyBO partyBO = new PartyBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
							where(PartyBO.PARTYNUM, partyCaseBO.getPartyNum()).
							setUseConnection(conn).
							find(PartyBO.FIRSTNAME, PartyBO.LASTNAME);  

							sbDefTitle.append(CorisCaseHistoryCommon.getPartyName(partyBO));
							if (kaseDefLinkedListBO.indexOf(kaseDefLinkedBO) < kaseDefLinkedListBO.size() - 1){
								sbDefTitle.append(", ");
							}
							
							partyCaseBO = null;
							partyBO = null;
						}
						sbDefTitle.append(" are linked.");
						CorisCaseHistoryCommon.addCell(table, sbDefTitle.toString(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
						sbDefTitle = null;
						table.setWidths(widths);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Process generateCaseHistoryLinkedDefendants(CorisCaseHistoryDTO corisCaseHistoryDTO)", e);

		}
			
		return table;
	}
	
	public PdfPTable generateCaseHistoryLinkedDomesticViolenceCases(CorisCaseHistoryDTO corisCaseHistoryDTO) throws DocumentException, IOException{
		
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[2];
		widths[0] = 10.0f;
		widths[1] = 90.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			// ///////////////////////////////////////////////////////////////
			// Get Linked Domestic Violence Case Information
			// ///////////////////////////////////////////////////////////////
			List<DomViolLinkBO> domViolLinkListBO = new DomViolLinkBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(DomViolLinkBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum()).
			setUseConnection(conn).
			setReturnNull(true).
			search(DomViolLinkBO.DVCASENUM);
			
			if (domViolLinkListBO != null){
				if (domViolLinkListBO.size() > 0){
					StringBuilder sbTitle = new StringBuilder("This case is linked to the following domestic violence case(s): ");
					for (DomViolLinkBO domViolLinkBO :domViolLinkListBO){
						sbTitle.append(domViolLinkBO.getDvCaseNum());
						if (domViolLinkListBO.indexOf(domViolLinkBO) < domViolLinkListBO.size() - 1){
							sbTitle.append(", ");
						}
					}
					CorisCaseHistoryCommon.addCell(table, sbTitle.toString(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					sbTitle = null;
					table.setWidths(widths);
				}
			}
		} catch (Exception e) {
			logger.error("Process generateCaseHistoryLinkedDomesticViolenceCases(CorisCaseHistoryDTO corisCaseHistoryDTO)", e);

		}
			
		return table;
	}
	
	public PdfPTable generateCaseHistoryCharge(CorisCaseHistoryDTO corisCaseHistoryDTO) throws DocumentException, IOException{
		BigDecimal caseRecommendedBailAmount = new BigDecimal(0);
		BigDecimal accidentAttribueFeeCharge = new BigDecimal(30);
		
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[2];
		widths[0] = 10.0f;
		widths[1] = 90.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			CorisCaseHistoryCommon.addCell(table, "CHARGES", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			
			// ///////////////////////////////////////////////////////////////
			// Domestic Violence
			// ///////////////////////////////////////////////////////////////
			if ("Y".equals(CorisCaseHistoryCommon.checkDomViolence(corisCaseHistoryDTO, conn))){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "This case involves domestic violence.", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
			
			List<ChargeBO> chargeListBO = new ChargeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(ChargeBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum()).
			orderBy(ChargeBO.SEQ).
			setUseConnection(conn).
			search();						

			// ///////////////////////////////////////////////////////////////
			// Loop through charges
			// ///////////////////////////////////////////////////////////////
			int accidCount = 0;
			int minChargeSeqNumber = 0;
			
			//////////////////////////////////////////////////////////////////////////
			// Count Accident Charge Attribute
			//////////////////////////////////////////////////////////////////////////
			FieldOperationDescriptor attrCount = new FieldOperationDescriptor(ChrgAttrBO.INTCASENUM, FieldOperationType.COUNT, new TypeInteger());
			ChrgAttrBO chrgAttrCountBO = new ChrgAttrBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
			.setFieldOperations(attrCount)
			.where(ChrgAttrBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum())
			.where(ChrgAttrBO.ATTRTYPE, "C")
			.where(ChrgAttrBO.ATTRCODE, "AC")
			.setUseConnection(conn)
			.find(ChargeBO.INTCASENUM);
			
			accidCount = (Integer) chrgAttrCountBO.get(attrCount);
			chrgAttrCountBO = null;
			
			//////////////////////////////////////////////////////////////////////////
			// Min Change Number
			//////////////////////////////////////////////////////////////////////////
			FieldOperationDescriptor chargeMin = new FieldOperationDescriptor(ChargeBO.SEQ, FieldOperationType.MIN, new TypeInteger());
			
			ChargeBO chargeMinBO = new ChargeBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
			.setFieldOperations(chargeMin)
			.where(ChargeBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum())
			.addWhereDescriptors(new WhereCustomDescriptor("(jdmt_code IS NULL OR jdmt_code IN(select jdmt_code from jdmt_type where type = 'G'))"))
			.setUseConnection(conn)
			.find(ChargeBO.SEQ);

			minChargeSeqNumber = (Integer) chargeMinBO.get(chargeMin);
			
			if (accidCount == 0){
				accidentAttribueFeeCharge = BigDecimal.ZERO;
			}	
			
			chargeMinBO = null;
			
			for (ChargeBO chargeBO :chargeListBO){
				String chargeDescr = "";
				String attrList = "";
				BigDecimal chargeRecommendedBailAmount = new BigDecimal(0);
				String mandAppear = "N";
				
				// ///////////////////////////////////////////////////////////////
				// Get Severity Type Data
				// ///////////////////////////////////////////////////////////////
				SeverityTypeBO severityTypeBO = new SeverityTypeBO(corisCaseHistoryDTO.getKaseBO().
				getCourtType()).
				where(SeverityTypeBO.SEVERITYCODE, chargeBO.getSeverity()).
				setUseConnection(conn).
				find(SeverityTypeBO.DESCR);
				
				// ///////////////////////////////////////////////////////////////
				// Get Offense Data
				// ///////////////////////////////////////////////////////////////
				OffenseBO offenseBO = new OffenseBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(OffenseBO.OFFSVIOLCODE, chargeBO.getOffsViolCode()).
				where(OffenseBO.LASTEFFECTDATE, chargeBO.getOffsEffectDate()).
				setUseConnection(conn).
				find(OffenseBO.SPECPROCATTR, OffenseBO.OFFSVIOLCODE ,OffenseBO.DESCR);						
	
				// ///////////////////////////////////////////////////////////////
				// Build Charge Description
				// ///////////////////////////////////////////////////////////////
				chargeDescr = "Charge " + String.valueOf(chargeBO.getSeq()).trim() + " - " + chargeBO.getViolCode() + " - ";
				
				if ("S".equals(offenseBO.getSpecProcAttr()) || "C".equals(offenseBO.getSpecProcAttr())){
					chargeDescr +=  offenseBO.getDescr().trim();
					if (!TextUtil.isEmpty(chargeBO.getSpValue1()) && !TextUtil.isEmpty(chargeBO.getSpValue2())){
						chargeDescr +=  " " + chargeBO.getSpValue1() + " in a " + chargeBO.getSpValue2();
					}
				} else {
					
					if ("A".equals(chargeBO.getInchoateFlag())){
						chargeDescr += "ATTEMPTED - " + offenseBO.getDescr().trim(); 
					} else if ("C".equals(chargeBO.getInchoateFlag())){
						chargeDescr += "CONSPIRACY - " + offenseBO.getDescr().trim(); 
					} else if ("S".equals(chargeBO.getInchoateFlag())){
						chargeDescr += "SOLICITATION - " + offenseBO.getDescr().trim(); 
					} else {
						chargeDescr += offenseBO.getDescr().trim();
					}
				} 	
	
				// ///////////////////////////////////////////////////////////////
				// Get Charge Attribute Data
				// ///////////////////////////////////////////////////////////////
				List<ChrgAttrBO> chrgAttrListBO = new ChrgAttrBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(ChrgAttrBO.INTCASENUM, chargeBO.getIntCaseNum()).
				where(ChrgAttrBO.SEQ, chargeBO.getSeq()).
				where(ChrgAttrBO.ATTRTYPE, "C").
				setUseConnection(conn).
				search(ChrgAttrBO.ATTRTYPE, ChrgAttrBO.ATTRCODE);
				
				
				// ///////////////////////////////////////////////////////////////
				// Loop through Charge Attribute Data
				// ///////////////////////////////////////////////////////////////
				for(ChrgAttrBO chrgAttrBO: chrgAttrListBO){
					// ///////////////////////////////////////////////////////////////
					// Get Attribute Type Data
					// ///////////////////////////////////////////////////////////////
					AttrTypeBO attrTypeBO = new AttrTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(AttrTypeBO.ATTRCODE, chrgAttrBO.getAttrCode()).
					setUseConnection(conn).
					find(AttrTypeBO.DESCR);						
					attrList += attrTypeBO.getDescr() + ". ";
					attrTypeBO = null;
					chrgAttrBO = null;
				}
				
				if (accidCount == 0){
				}
				
				if (chargeBO.getAmendDate() != null){
					// ///////////////////////////////////////////////////////////////
					// Get Original Severity Type Data
					// ///////////////////////////////////////////////////////////////
					SeverityTypeBO origSeverityTypeBO = new SeverityTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(SeverityTypeBO.SEVERITYCODE, chargeBO.getOrigSeverity()).
					setUseConnection(conn).
					find(SeverityTypeBO.DESCR);
	
					chargeDescr += " - " + origSeverityTypeBO.getDescr();
					
					if (TextUtil.isEmpty(chargeBO.getPre402Severity()) && TextUtil.isEmpty(chargeBO.getPreenhancSeverity())){
						chargeDescr += " (amended to) ";
					} else{
						chargeDescr += " (402 amended to) ";
					}
					origSeverityTypeBO = null;
				}
					
				chargeDescr += " - " + severityTypeBO.getDescr();
				
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, chargeDescr, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(chargeBO.getViolDatetime());
				if (calendar.get(Calendar.HOUR) == 0 && calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0){
					CorisCaseHistoryCommon.addCell(table, "Offense Date: " + Constants.longTextFormat.format(chargeBO.getViolDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else {
					CorisCaseHistoryCommon.addCell(table, "Offense Date and Time: " + Constants.longTextFormat.format(chargeBO.getViolDatetime()) + " at " + Constants.timeFormat.format(chargeBO.getViolDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				
				calendar = null;
				
				if (!TextUtil.isEmpty(chargeBO.getViolLocn())){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Location: " + chargeBO.getViolLocn(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				
				if (!TextUtil.isEmpty(attrList)){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Attributes: " + attrList, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				
				// ///////////////////////////////////////////////////////////////
				// Get Plea Type Data
				// ///////////////////////////////////////////////////////////////
				if (!TextUtil.isEmpty(chargeBO.getPleaCode())){
					PleaTypeBO pleaTypeBO = null;
					pleaTypeBO = new PleaTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(PleaTypeBO.PLEACODE, chargeBO.getPleaCode()).
					setUseConnection(conn).
					find(PleaTypeBO.DESCR);
					
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Plea: " + Constants.longTextFormat.format(chargeBO.getPleaDate()) + " " + pleaTypeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					pleaTypeBO = null;
				}
				
				// ///////////////////////////////////////////////////////////////
				// Get Judgment Type Data
				// ///////////////////////////////////////////////////////////////
				if (!TextUtil.isEmpty(chargeBO.getJdmtCode())){
					JdmtTypeBO jdmtTypeBO = new JdmtTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(JdmtTypeBO.JDMTCODE, chargeBO.getJdmtCode()).
					setUseConnection(conn).
					find(JdmtTypeBO.TYPE, JdmtTypeBO.DESCR);
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Disposition: " + Constants.longTextFormat.format(chargeBO.getJdmtDate()) + " " + jdmtTypeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					// ///////////////////////////////////////////////////////////////
					// Accident Flag Applies On First Guilty Charge OR No Judgment
					// ///////////////////////////////////////////////////////////////
					jdmtTypeBO = null;
				}

				// ///////////////////////////////////////////////////////////////
				// Mandatory Appearance Bail Data
				// ///////////////////////////////////////////////////////////////
				if (chargeBO.getJdmtDate() == null){
					mandAppear = CorisChargeStoredProcedureServlet.checkMandatroyAppearanaceByChargeSP(0, chargeBO.getIntCaseNum(), corisCaseHistoryDTO.getKaseBO().getLocnCode(), corisCaseHistoryDTO.getKaseBO().getCourtType(), chargeBO.getSeq(), conn);
					
					chargeRecommendedBailAmount = CorisAccountStoredProcedureServlet.calculateBailForfitureByChargeSP(0, chargeBO.getIntCaseNum(), chargeBO.getSeq(), corisCaseHistoryDTO.getKaseBO().getCourtType(), conn);

					if (minChargeSeqNumber == chargeBO.getSeq()){
						chargeRecommendedBailAmount = chargeRecommendedBailAmount.subtract(accidentAttribueFeeCharge);
					}
					
					if ("Y".equals(mandAppear)){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "Mandatory Appearance", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					}
					
					if(chargeRecommendedBailAmount.compareTo(BigDecimal.ZERO) > 0){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "Recommended Bail Amount: " + Constants.accountingDecimalFormatter.format(chargeRecommendedBailAmount), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					}
					
					caseRecommendedBailAmount = caseRecommendedBailAmount.add(chargeRecommendedBailAmount);
					attrList = "";
					offenseBO = null;
					severityTypeBO = null;
					chargeDescr = "";
					chargeRecommendedBailAmount = new BigDecimal(0);
				}
				
				// ///////////////////////////////////////////////////////////////
				// Spacing
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
			
			// ///////////////////////////////////////////////////////////////
			// fee[0] is Delinquent Enhancement
			// fee[1] is Warrant Enhancement
			// ///////////////////////////////////////////////////////////////
			BigDecimal[] fee = {BigDecimal.ZERO, BigDecimal.ZERO};
			fee = CorisAccountStoredProcedureServlet.calculateEnhanceAmtSP(0, corisCaseHistoryDTO.getKaseBO().getIntCaseNum(), corisCaseHistoryDTO.getKaseBO().getCourtType(), conn);
			
			// ///////////////////////////////////////////////////////////////
			// If Not Delinquent - No Delinquent Enhancement
			// ///////////////////////////////////////////////////////////////
			if (corisCaseHistoryDTO.getCrimCaseBO().getDelinqNoticeDate() == null){
				fee[0] = new BigDecimal(0);
			}
			
			// ///////////////////////////////////////////////////////////////
			// If No Warrant Fee Flag - No Warrant Enhancement
			// ///////////////////////////////////////////////////////////////
			int warrantFeeFlagCount = 0;
			// count
			FieldOperationDescriptor count = new FieldOperationDescriptor(WarrantBO.WARRNUM, FieldOperationType.COUNT, new TypeInteger());
			
			WarrantBO warrantBO = new WarrantBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			setFieldOperations(count).
			where(WarrantBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum()).
			where(WarrantBO.FEEFLAG, "Y").
			// groupBy(WarrantBO.WARRNUM).  -- removed group by -- causes null to be returned if no results found
			setUseConnection(conn).
			setReturnNull(true).
			find(WarrantBO.WARRNUM);
			
			if (warrantBO != null){
				warrantFeeFlagCount = (Integer) warrantBO.get(count);
				warrantBO = null;
			}
				
			if (warrantFeeFlagCount == 0 || "FS".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
				fee[1] = new BigDecimal(0);
			}

			if(caseRecommendedBailAmount.compareTo(BigDecimal.ZERO) > 0){
				if (fee[0].compareTo(BigDecimal.ZERO) > 0 && fee[1].compareTo(BigDecimal.ZERO) > 0) {
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					BigDecimal totalFee = new BigDecimal(0);
					totalFee = totalFee.add(fee[0]).add(fee[1]); 
					CorisCaseHistoryCommon.addCell(table, "Warrant and Delinquent Enhancements: " + Constants.accountingDecimalFormatter.format(totalFee), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					totalFee = new BigDecimal(0);
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					caseRecommendedBailAmount  = caseRecommendedBailAmount.add(fee[0]).add(fee[1]);
				} else if (fee[0].compareTo(BigDecimal.ZERO) > 0) {
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Delinquent Enhancement: " + Constants.accountingDecimalFormatter.format(fee[0]), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					caseRecommendedBailAmount = caseRecommendedBailAmount.add(fee[0]);
				} else if (fee[1].compareTo(BigDecimal.ZERO) > 0) {
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Warrant Enhancement: " + Constants.accountingDecimalFormatter.format(fee[1]), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					caseRecommendedBailAmount = caseRecommendedBailAmount.add(fee[1]);
				}
			}
			
			if(caseRecommendedBailAmount.compareTo(BigDecimal.ZERO) > 0){
				if (accidCount > 0 && minChargeSeqNumber > 0){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Accident Attribute Fee: " + Constants.accountingDecimalFormatter.format(accidentAttribueFeeCharge), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					caseRecommendedBailAmount = caseRecommendedBailAmount.add(accidentAttribueFeeCharge);
				}
				
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Total Recommended Bail Amount: " + Constants.accountingDecimalFormatter.format(caseRecommendedBailAmount), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
	
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
		
			/////////////////////////////////////////////////////////////////
			// Clean Up
			/////////////////////////////////////////////////////////////////
			chargeListBO = null;
			widths = null;
		} catch (Exception e) {
			logger.error("Process generateCaseHistoryCharge(CorisCaseHistoryDTO corisCaseHistoryDTO)", e);
		}
		
		return table;
	}
	
	public PdfPTable generateCaseHistoryAssignedJudge(CorisCaseHistoryDTO corisCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[2];
		widths[0] = 10.0f;
		widths[1] = 90.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("Process CaseHistoryCharges Set Table Widths ", e);
		}
		
		if (corisCaseHistoryDTO.getKaseBO().getAssnJudgeId() > 0){
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, "CURRENT ASSIGNED JUDGE", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getJudgeCommName(corisCaseHistoryDTO.getKaseBO().getAssnJudgeId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		}
		if (corisCaseHistoryDTO.getKaseBO().getAssnCommId() > 0){
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, "CURRENT ASSIGNED COMMISSIONER", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getJudgeCommName(corisCaseHistoryDTO.getKaseBO().getAssnCommId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		}	
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		/////////////////////////////////////////////////////////////////
		// Clean Up
		/////////////////////////////////////////////////////////////////
		widths = null;
		
		return table;
	}
	public PdfPTable generateCaseHistoryParties(CorisCaseHistoryDTO corisCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[2];
		widths[0] = 10.0f;
		widths[1] = 90.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, "PARTIES", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			
			// ///////////////////////////////////////////////////////////////
			// Get Party Case
			// ///////////////////////////////////////////////////////////////
			List<PartyCaseBO> partyCaseListBO = new PartyCaseBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(PartyCaseBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum()).
			orderBy(PartyCaseBO.PARTYCODE).
			setUseConnection(conn).
			search();
	
			// ///////////////////////////////////////////////////////////////
			// split up for sorting capability
			// ///////////////////////////////////////////////////////////////
			List<PartyCaseBO> partyCasePlaPetListBO = new ArrayList<PartyCaseBO>();
			List<PartyCaseBO> partyCaseDefResListBO = new ArrayList<PartyCaseBO>();
			List<PartyCaseBO> partyCaseOtherListBO = new ArrayList<PartyCaseBO>();
			for (PartyCaseBO partyCaseBO: partyCaseListBO){
				if ("PET".equals(partyCaseBO.getPartyCode()) || "PLA".equals(partyCaseBO.getPartyCode())){
					partyCasePlaPetListBO.add(partyCaseBO);
				} else if ("DEF".equals(partyCaseBO.getPartyCode()) || "RES".equals(partyCaseBO.getPartyCode())){
					partyCaseDefResListBO.add(partyCaseBO);
				} else {
					partyCaseOtherListBO.add(partyCaseBO);
				}
				partyCaseBO = null;
			}
			
			// ///////////////////////////////////////////////////////////////
			// Add back and clean up in order
			// ///////////////////////////////////////////////////////////////
			partyCaseListBO = new ArrayList<PartyCaseBO>();
			partyCaseListBO.addAll(partyCasePlaPetListBO);
			partyCasePlaPetListBO = null;
			partyCaseListBO.addAll(partyCaseDefResListBO);
			partyCaseDefResListBO = null;
			partyCaseListBO.addAll(partyCaseOtherListBO);
			partyCaseOtherListBO = null;
			
			for (PartyCaseBO partyCaseBO: partyCaseListBO){

// 				Per Kim Allard Email Dated Wed, Feb 6, 2019 at 11:46 AM				
// 				Per Kristene Laterza Email Dated Mon, Mar 18, 2019 at 2:45 PM 		
//				UCJA 4-202.02.
//				(4)(O)      name of a minor (is private), except that the name of a minor party is public in the following district and justice court proceedings:
//				(4)(O)(i)   name change of a minor;
//				(4)(O)(ii)  guardianship or conservatorship for a minor;
//				(4)(O)(iii) felony, misdemeanor, or infraction;
//				(4)(O)(iv)  protective orders; and
//				(4)(O)(v)   custody orders and decrees;

				if (!"CO".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) &&		
					!"CS".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) &&		
					!"FD".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) &&		
					!"FS".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) &&		
					!"GU".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) &&
					!"IF".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) &&
					!"MD".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) &&
					!"MO".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) &&
					!"NA".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) &&
					!"PC".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())	&&	
					!"PN".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())	&&	
					!"PO".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())	&&	
					!"TN".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())	&&
					!"TC".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
					
					if (inquiryUser){
						if ("MIN".equals(partyCaseBO.getPartyCode())){
							continue;
						}
					}
				}
					
				if (inquiryUser){
					if ("ADP".equals(partyCaseBO.getPartyCode()) ||
						"BON".equals(partyCaseBO.getPartyCode()) ||
						"ICP".equals(partyCaseBO.getPartyCode()) ||
						"PTP".equals(partyCaseBO.getPartyCode()) ||
						"PYE".equals(partyCaseBO.getPartyCode()) ||
						"PYR".equals(partyCaseBO.getPartyCode()) ||
						"VIC".equals(partyCaseBO.getPartyCode()) ||
						"WIT".equals(partyCaseBO.getPartyCode())){
							continue;
						
					}
				}
				
				PartyBO partyBO = null;
				if (("NKA".equals(partyCaseBO.getPartyCode()) || "AKA".equals(partyCaseBO.getPartyCode())) && partyCaseBO.getRelatedPartyNum() > 0){
					PartyBO partyReleatedBO = CorisCaseHistoryCommon.getPartyByPartyNum(corisCaseHistoryDTO, partyCaseBO.getRelatedPartyNum(), partyRepository, conn);
					partyBO = CorisCaseHistoryCommon.getPartyByPartyNum(corisCaseHistoryDTO, partyCaseBO.getPartyNum(), partyRepository, conn);
					if (TextUtil.isEmpty(partyReleatedBO.getFirstName())){
						partyBO.setLastName(partyBO.getLastName() + "(" + partyReleatedBO.getLastName() + ")");
					} else {
						partyBO.setLastName(partyBO.getLastName() + "(" + partyReleatedBO.getFirstName() + " " + partyReleatedBO.getLastName()+ ")");
					}
					
					
				} else {
					partyBO = CorisCaseHistoryCommon.getPartyByPartyNum(corisCaseHistoryDTO, partyCaseBO.getPartyNum(), partyRepository, conn);
				}
				
				
				if ("Y".equals(partyCaseBO.getSafeguarded()) || inquiryUser){
					partyBO.setAddress1(null);
					partyBO.setAddress2(null);
					partyBO.setCity(null);
					partyBO.setStateCode(null);
					partyBO.setZipCode(null);
					partyBO.setSsn(null);
					partyBO.setDrLicNum(null);
					partyBO.setDrLicState(null);
					partyBO.setEmailAddress(null);
				}
				
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
				if ("Y".equals(partyCaseBO.getDismissed())){
					CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getPartyDescriptionByPartyCode(partyCaseBO.getPartyCode(), corisCaseHistoryDTO.getKaseBO().getCourtType(), conn) + " - " + CorisCaseHistoryCommon.getPartyName(partyBO) + " - DISMISSED", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else {
					CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getPartyDescriptionByPartyCode(partyCaseBO.getPartyCode(), corisCaseHistoryDTO.getKaseBO().getCourtType(), conn) + " - " + CorisCaseHistoryCommon.getPartyName(partyBO), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				
				if (!TextUtil.isEmpty(partyBO.getAddress1())){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, partyBO.getAddress1(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				
				if (!TextUtil.isEmpty(partyBO.getAddress2())){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, partyBO.getAddress2(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				if (!TextUtil.isEmpty(partyBO.getCity()) &&
					!TextUtil.isEmpty(partyBO.getStateCode()) &&
					!TextUtil.isEmpty(partyBO.getZipCode())){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, partyBO.getCity() + ", " + partyBO.getStateCode() + " " + partyBO.getZipCode(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				if (!TextUtil.isEmpty(partyBO.getEmailAddress())){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, partyBO.getEmailAddress(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				
				/////////////////////////////////////////////////////
				// Get AttyPartyVO
				/////////////////////////////////////////////////////
				List<AttyPartyBO> attyPartyListBO =	new AttyPartyBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(AttyPartyBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum()).
				where(AttyPartyBO.PARTYNUM, partyBO.getPartyNum()).
				where(AttyPartyBO.DETACHDATETIME, Exp.IS_NULL).
				setUseConnection(conn).
				search(AttyPartyBO.BARNUM, AttyPartyBO.BARSTATE);					
				
				for (AttyPartyBO attyPartyBO :attyPartyListBO){
					/////////////////////////////////////////////////////
					// Get AttorneyVO
					/////////////////////////////////////////////////////
					AttorneyBO attorneyBO = new AttorneyBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(AttorneyBO.BARNUM, attyPartyBO.getBarNum()).
					where(AttorneyBO.BARSTATE, attyPartyBO.getBarState()).
					setUseConnection(conn).
					find(AttorneyBO.FIRSTNAME, AttorneyBO.LASTNAME);						
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Represented by: " + attorneyBO.getFirstName() + " " + attorneyBO.getLastName(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					attorneyBO = null;
					attyPartyBO = null;
				}
				
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
				partyBO = null;
			}
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			/////////////////////////////////////////////////////////////////
			// Clean Up
			/////////////////////////////////////////////////////////////////
			widths = null;
			partyCaseListBO = null;
			
		} catch (Exception e) {
			logger.error("Process generateCaseHistoryParties(CorisCaseHistoryDTO corisCaseHistoryDTO)", e);
		}
		
		
		return table;
	}
	public PdfPTable generateCaseHistoryDefendantInformation(CorisCaseHistoryDTO corisCaseHistoryDTO) throws DocumentException, IOException{
		
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[2];
		widths[0] = 10.0f;
		widths[1] = 90.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		// ///////////////////////////////////////////////////////////////
		// Get Party Information
		// ///////////////////////////////////////////////////////////////
		try {
			table.setWidths(widths);
			
			// ///////////////////////////////////////////////////////////////
			// Get Party Information
			// ///////////////////////////////////////////////////////////////
			PartyBO partyBO = CorisCaseHistoryCommon.getPartyByPartyCode(corisCaseHistoryDTO,"DEF", partyRepository, conn);
			
			// ///////////////////////////////////////////////////////////////
			// Get Party Case Information
			// ///////////////////////////////////////////////////////////////
			PartyCaseBO partyCaseBO = new PartyCaseBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(PartyCaseBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum()).
			where(PartyCaseBO.PARTYNUM, partyBO.getPartyNum()).
			where(PartyCaseBO.PARTYCODE, "DEF").
			setUseConnection(conn).
			find();
			
			// ///////////////////////////////////////////////////////////////
			// Get Charge Information
			// ///////////////////////////////////////////////////////////////
			List<ChargeBO> chargeListBO = new ChargeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(ChargeBO.INTCASENUM, corisCaseHistoryDTO.getKaseBO().getIntCaseNum()).
			setUseConnection(conn).
			setReturnNull(true).
			search(ChargeBO.VIOLDATETIME);						
			
			CorisCaseHistoryCommon.addCell(table, "DEFENDANT INFORMATION", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, "Defendant Name: " + CorisCaseHistoryCommon.combinePartyName(partyBO.getFirstName(), partyBO.getLastName()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			if (!TextUtil.isEmpty(partyCaseBO.getOtn())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Offense Tracking Number: " + partyCaseBO.getOtn(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if (partyBO.getBirthDate() != null){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Date of Birth: " + Constants.longTextFormat.format(partyBO.getBirthDate()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if (!TextUtil.isEmpty(partyBO.getLanguage())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Language: " + partyBO.getLanguage(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if (!TextUtil.isEmpty(partyBO.getSsn())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Social Security Number: " + CorisCaseHistoryCommon.maskSsn(partyBO.getSsn()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if (!TextUtil.isEmpty(partyBO.getDrLicNum())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Drivers License Number: " + CorisCaseHistoryCommon.maskDln(partyBO.getDrLicNum()) + " " + partyBO.getDrLicState(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if (!TextUtil.isEmpty(partyCaseBO.getBookingNum())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Jail Booking Number: " + partyCaseBO.getBookingNum(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if (!TextUtil.isEmpty(corisCaseHistoryDTO.getCrimCaseBO().getLea())){
				LeaBO leaBO = new LeaBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(LeaBO.LEACODE, corisCaseHistoryDTO.getCrimCaseBO().getLea()).
				where(LeaBO.LOCNCODE, corisCaseHistoryDTO.getKaseBO().getLocnCode()).
				where(LeaBO.COURTTYPE, corisCaseHistoryDTO.getKaseBO().getCourtType()).
				setUseConnection(conn).
				setReturnNull(true).
				find(LeaBO.DESCR);
				
				if (leaBO != null){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Law Enforcement Agency: " + leaBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				leaBO = null;
			}
			
			if (!TextUtil.isEmpty(corisCaseHistoryDTO.getCrimCaseBO().getLeaCaseNum())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "LEA Case Number: " + corisCaseHistoryDTO.getCrimCaseBO().getLeaCaseNum(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if (corisCaseHistoryDTO.getCrimCaseBO().getOfficerSrl() > 0){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				OfficerBO officerBO = new OfficerBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(OfficerBO.OFFICERSRL, corisCaseHistoryDTO.getCrimCaseBO().getOfficerSrl()).
				setUseConnection(conn).
				find(OfficerBO.LASTNAME, OfficerBO.FIRSTNAME);
				
				String officerName = "";
					
				if (TextUtil.isEmpty(officerBO.getFirstName())){
					officerName =  officerBO.getLastName();
				} else {
					officerName =  officerBO.getFirstName() + " " + officerBO.getLastName();
				}
				
				CorisCaseHistoryCommon.addCell(table, "Officer Name: " + officerName, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				officerName = null;
				officerBO = null;
			}
			
			if (!TextUtil.isEmpty(corisCaseHistoryDTO.getCrimCaseBO().getProsecAgency())){
				GovTypeBO govTypeBO = new GovTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(GovTypeBO.GOVCODE, corisCaseHistoryDTO.getCrimCaseBO().getProsecAgency()).
				setUseConnection(conn).
				setReturnNull(true).
				find(GovTypeBO.DESCR);
				if (govTypeBO != null){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Prosecuting Agency: " + govTypeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				govTypeBO = null;
			}
			
			if (!TextUtil.isEmpty(corisCaseHistoryDTO.getCrimCaseBO().getProsecAgencyNum())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Agency Case Number: " + corisCaseHistoryDTO.getCrimCaseBO().getProsecAgencyNum(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if (!TextUtil.isEmpty(corisCaseHistoryDTO.getCrimCaseBO().getCitNum())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Citation Number: " + corisCaseHistoryDTO.getCrimCaseBO().getCitNum(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if (partyCaseBO.getArrestDate() != null){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Arrest Date: " + Constants.longTextFormat.format(partyCaseBO.getArrestDate()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if (chargeListBO != null){
				if (chargeListBO.get(0).getViolDatetime() != null){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Violation Date: " + Constants.dateFormat.format(chargeListBO.get(0).getViolDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
			}
			
			if (!TextUtil.isEmpty(corisCaseHistoryDTO.getCrimCaseBO().getSheriffNum())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Sheriff Number: " + corisCaseHistoryDTO.getCrimCaseBO().getSheriffNum(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			String psCodeDescr = "";
			List<PsReferralCaseBO> referralCaseListBO = new PsReferralCaseBO()
				.includeTables(
						new PsReferralBO().orderBy(PsReferralBO.CREATEDATETIME, DirectionType.ASC),
						new PsCourtDefnBO()
				)
				.addForeignKey(PsReferralCaseBO.PSREFERRALID, PsReferralBO.PSREFERRALID)
				.addForeignKey(PsReferralBO.PSCOURTDEFNID, PsCourtDefnBO.PSCOURTDEFNID)
				.where(PsReferralCaseBO.CASEIDENTIFIER, corisCaseHistoryDTO.getKaseBO().getIntCaseNum())
				.where(PsReferralCaseBO.JURISDICTION, corisCaseHistoryDTO.getKaseBO().getCourtType())
					// .setUseConnection(conn)  --   IN THE PROBLEM SOLVING DB
				.search(PsCourtDefnBO.PSCODEDESCR);
			for(PsReferralCaseBO psReferralCaseBO : referralCaseListBO){
				psCodeDescr = (String) psReferralCaseBO.get(PsCourtDefnBO.PSCODEDESCR);
				psReferralCaseBO = null;
			}
			
			if (!TextUtil.isEmpty(psCodeDescr)){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Defendant referred to: " + TextUtil.print(psCodeDescr), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			referralCaseListBO = null;
			
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			
			partyBO = null; 
			partyCaseBO = null; 
			chargeListBO = null;
		} catch (Exception e) {
			logger.error("Process generateCaseHistoryDefendantInformation(CorisCaseHistoryDTO corisCaseHistoryDTO)", e);

		}
			
		return table;
	}
	
	public List<PdfPTable> generateCaseHistoryAccountInformation(CorisCaseHistoryDTO corisCaseHistoryDTO) throws DocumentException, IOException{
		BigDecimal revenueAmtDueTotal = new BigDecimal(0);
		BigDecimal revenueAmtPaidTotal = new BigDecimal(0);
		BigDecimal revenueAmtCreditTotal = new BigDecimal(0);
		
		BigDecimal bailCashBondAmtPosted = new BigDecimal(0);
		BigDecimal bailCashBondAmtForfeited = new BigDecimal(0);
		BigDecimal bailCashBondAmtRefunded = new BigDecimal(0);
		
		BigDecimal bondAmtPosted = new BigDecimal(0);
		BigDecimal bondAmtForfeited = new BigDecimal(0);;
		BigDecimal bondAmtExonerated = new BigDecimal(0);
		
		BigDecimal trustAmtDueTotal = new BigDecimal(0);
		BigDecimal trustAmtPaidTotal = new BigDecimal(0);
		BigDecimal trustAmtCreditTotal = new BigDecimal(0);
		BigDecimal trustReoccuingOpenTotal = new BigDecimal(0);
		BigDecimal trustReoccuingAmtPaidTotal = new BigDecimal(0);
		
		String timePayDescription = "";
		String finalPayemnt = "";
		
		Date lastPymntDate = null;		
		
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[5];
		widths[0] = 10.0f;
		widths[1] = 36.0f;
		widths[2] = 18.0f;
		widths[3] = 18.0f;
		widths[4] = 18.0f;
		
		List<PdfPTable> returnTablesList = new ArrayList<PdfPTable>();

		PdfPTable tableAccount = new PdfPTable(5);
		tableAccount.setWidthPercentage(100);
		
		PdfPTable tableSum = new PdfPTable(5);
		tableSum.setWidthPercentage(100);
		
		try {
			tableAccount.setWidths(widths);
			
			
			List<AccountBO> accountOnTimePayListBO = new AccountBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(new FindDescriptor(AccountBO.INTCASENUM.getDbFieldName(), String.valueOf(corisCaseHistoryDTO.getKaseBO().getIntCaseNum())),new FindDescriptor(AccountBO.TIMEPAYNUM.getDbFieldName()).setCustomSearch("IS NOT NULL"),new FindDescriptor(AccountBO.ACCTTYPE.getDbFieldName()).setCustomSearch("IN ('F', 'I', 'T')")).
			orderBy(AccountBO.ACCTNUM).
			setUseConnection(conn).
			search();
			
			for (AccountBO accountBO: accountOnTimePayListBO){
				TimepayBO timepayBO = new TimepayBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(TimepayBO.TIMEPAYNUM, accountBO.getTimepayNum()).
				setUseConnection(conn).
				find();
				timePayDescription = "";
				
				timePayDescription = Constants.accountingDecimalFormatter.format(timepayBO.getPymtAmt());
				if ("W".equals(timepayBO.getFrequency())){
					timePayDescription += " weekly";
				} else if ("B".equals(timepayBO.getFrequency())){
					timePayDescription += " bi-weekly";
				} else if ("S".equals(timepayBO.getFrequency())){
					timePayDescription += " semi-monthly";
				} else if ("M".equals(timepayBO.getFrequency())){
					timePayDescription += " monthly";
				}
				timePayDescription += " on the " + timepayBO.getSchedDay();
				
				switch (timepayBO.getSchedDay()){
					case 1: 
						timePayDescription += "st.";
						break;
					case 2: 
						timePayDescription += "nd.";
						break;
					case 3: 
						timePayDescription += "rd.";
						break;
					case 21: 
						timePayDescription += "st.";
						break;
					case 22: 
						timePayDescription += "nd.";
						break;
					case 23: 
						timePayDescription += "rd.";
						break;
					case 31: 
						timePayDescription += "st.";
						break;
					default:
						timePayDescription += "th.";
						break;
				}
				
				lastPymntDate = CorisAccountStoredProcedureServlet.calculateLastPaymentDateSP(0, accountBO.getAcctNum(), corisCaseHistoryDTO.getKaseBO().getCourtType(), conn); 
				finalPayemnt = Constants.longTextFormat.format(timepayBO.getFinalPymtDate());
				timepayBO = null;
			}
			accountOnTimePayListBO = null;
	
			if (!TextUtil.isEmpty(timePayDescription)){
				CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 5);
				
				CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableAccount, "Time Pay: ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableAccount, timePayDescription, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				
				if (lastPymntDate != null){
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Most Recent Payment:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.longTextFormat.format(lastPymntDate), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
		
				if (finalPayemnt != null){
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Final Payment:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, finalPayemnt, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 5);
				}
			}	
	
			// ///////////////////////////////////////////////////////////////
			// Get Account Information for account types F,I,B,O,T 
			// ///////////////////////////////////////////////////////////////
			List<AccountBO> accountFIBOTListBO = new AccountBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(new FindDescriptor(AccountBO.INTCASENUM.getDbFieldName(), String.valueOf(corisCaseHistoryDTO.getKaseBO().getIntCaseNum())),
					  new FindDescriptor(AccountBO.ACCTTYPE.getDbFieldName()).setCustomSearch("IN ('F', 'I', 'B', 'O', 'T')")).
			orderBy(AccountBO.ACCTNUM).
			setUseConnection(conn).
			search();
			
			// ///////////////////////////////////////////////////////////////
			// Get Account Information for account types F,I,B,O,T 
			// ///////////////////////////////////////////////////////////////
			for (AccountBO accountBO: accountFIBOTListBO){
				// ///////////////////////////////////////////////////////////////
				// Get Fine Account Information
				// ///////////////////////////////////////////////////////////////
				if ("I".equals(accountBO.getAcctType())){
					// ///////////////////////////////////////////////////////////////
					// Add to Fee Account Total Information
					// ///////////////////////////////////////////////////////////////
					revenueAmtDueTotal = revenueAmtDueTotal.add(accountBO.getAmtDue()); 
					revenueAmtPaidTotal = revenueAmtPaidTotal.add(accountBO.getAmtPaid());
					revenueAmtCreditTotal = revenueAmtCreditTotal.add(accountBO.getAmtCredit());
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "REVENUE DETAIL - TYPE: FINE", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 4);
					if (accountBO.getAmtDue() == accountBO.getOrigAmtDue()){
						CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, "Amount Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(accountBO.getAmtDue()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					} else {
						CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, "Original Amount Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(accountBO.getOrigAmtDue()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
						
						CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, "Amended Amount Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(accountBO.getAmtDue()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
						
					}
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Amount Paid:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(accountBO.getAmtPaid()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Amount Credit:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(accountBO.getAmtCredit()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Balance:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					BigDecimal balance = new BigDecimal(0);
					
					balance = balance.add(accountBO.getAmtDue()).
									  subtract(accountBO.getAmtPaid()).
									  subtract(accountBO.getAmtCredit());

					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(balance), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 5);
				} else if ("B".equals(accountBO.getAcctType())){
					
					
					// ///////////////////////////////////////////////////////////////
					// Get Bail Account Information
					// ///////////////////////////////////////////////////////////////
					AcctBailBO acctBailBO = new AcctBailBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(AcctBailBO.ACCTNUM, accountBO.getAcctNum()).
					setUseConnection(conn).
					find();
					
					// ///////////////////////////////////////////////////////////////
					// Add to Bail Account Total Information
					// ///////////////////////////////////////////////////////////////
					bailCashBondAmtPosted = bailCashBondAmtPosted.add(acctBailBO.getBailAmt());
					bailCashBondAmtForfeited = bailCashBondAmtForfeited.add(acctBailBO.getForfeitAmt());
					bailCashBondAmtRefunded = bailCashBondAmtRefunded.add(acctBailBO.getRefundAmt());

					// ///////////////////////////////////////////////////////////////
					// Get Party Information
					// ///////////////////////////////////////////////////////////////
					PartyBO partyBO = CorisCaseHistoryCommon.getPartyByPartyNum(corisCaseHistoryDTO, acctBailBO.getPostPartyNum(), partyRepository, conn);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "BAIL/CASH BOND DETAIL - TYPE: BAIL", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 4);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Posted By:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					if ("".equals(partyBO.getFirstName()) || partyBO.getFirstName() == null){
						CorisCaseHistoryCommon.addCell(tableAccount, TextUtil.toTrim(partyBO.getLastName()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
					}else  {
						CorisCaseHistoryCommon.addCell(tableAccount, TextUtil.toTrim(partyBO.getFirstName()) + " " + TextUtil.toTrim(partyBO.getLastName()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
					}
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Posted:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(acctBailBO.getBailAmt()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 2);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Forfeited:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(acctBailBO.getForfeitAmt()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 2);

					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Refunded:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(acctBailBO.getRefundAmt()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 2);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Balance:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					BigDecimal balance = new BigDecimal(0);
					
					balance = balance.add(acctBailBO.getBailAmt()).
									  subtract(acctBailBO.getForfeitAmt()).
									  subtract(acctBailBO.getRefundAmt());

					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(balance), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 2);
					
					// ///////////////////////////////////////////////////////////////
					// Clean Up
					// ///////////////////////////////////////////////////////////////
					acctBailBO = null;
					partyBO = null;
				} else if ("O".equals(accountBO.getAcctType())){
					// ///////////////////////////////////////////////////////////////
					// Get Account Bond Type Information
					// ///////////////////////////////////////////////////////////////
					AcctBondBO acctBondBO = new AcctBondBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(AcctBondBO.ACCTNUM, accountBO.getAcctNum()).
					setUseConnection(conn).
					find();
		
					// ///////////////////////////////////////////////////////////////
					// Get Bond Type Information
					// ///////////////////////////////////////////////////////////////
					BondTypeBO bondTypeBO = new BondTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(BondTypeBO.BONDCODE, acctBondBO.getBondCode()).
					setUseConnection(conn).
					find();
					
					// ///////////////////////////////////////////////////////////////
					// Add To Bond Total Information
					// ///////////////////////////////////////////////////////////////
					if ("Y".equals(bondTypeBO.getCashFlag())){
						bailCashBondAmtPosted = bailCashBondAmtPosted.add(acctBondBO.getBondAmt());
						bailCashBondAmtForfeited = bailCashBondAmtForfeited.add(acctBondBO.getForfeitAmt());
						bailCashBondAmtRefunded = bailCashBondAmtRefunded.add(acctBondBO.getExonRefundAmt());
					} else {
						bondAmtPosted = bondAmtPosted.add(acctBondBO.getBondAmt());
						bondAmtForfeited = bondAmtForfeited.add(acctBondBO.getForfeitAmt());
						bondAmtExonerated = bondAmtExonerated.add(acctBondBO.getExonRefundAmt());
					}
					
					// ///////////////////////////////////////////////////////////////
					// Get Bond Company Information
					// ///////////////////////////////////////////////////////////////
					BondCoBO bondCoBO =	new BondCoBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(BondCoBO.LICNUM, acctBondBO.getBondCoId()).
					setUseConnection(conn).
					setReturnNull(true).
					find();
					
					// ///////////////////////////////////////////////////////////////
					// Get Party Information
					// ///////////////////////////////////////////////////////////////
					PartyBO partyBO = CorisCaseHistoryCommon.getPartyByPartyNum(corisCaseHistoryDTO, acctBondBO.getPostPartyNum(), partyRepository, conn);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "NONMONETARY BOND DETAIL - TYPE: "+ bondTypeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 4);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Posted By:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					
					if (bondCoBO != null){
						if (!TextUtil.isEmpty(acctBondBO.getBondNum())){
							CorisCaseHistoryCommon.addCell(tableAccount, TextUtil.toTrim(bondCoBO.getName())+ " (#" + TextUtil.toTrim(acctBondBO.getBondNum()) + ")", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
						} else {
							CorisCaseHistoryCommon.addCell(tableAccount, TextUtil.toTrim(bondCoBO.getName()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
						}
					} else {
						if ("".equals(partyBO.getFirstName()) || partyBO.getFirstName() == null){
							if (!TextUtil.isEmpty(acctBondBO.getBondNum())){
								CorisCaseHistoryCommon.addCell(tableAccount, TextUtil.toTrim(partyBO.getLastName()) + " (#" + TextUtil.toTrim(acctBondBO.getBondNum()) + ")", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
							} else {
								CorisCaseHistoryCommon.addCell(tableAccount, TextUtil.toTrim(partyBO.getLastName()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
							}
						}else  {
							if (!TextUtil.isEmpty(acctBondBO.getBondNum())){
								CorisCaseHistoryCommon.addCell(tableAccount, TextUtil.toTrim(partyBO.getFirstName()) + " " + TextUtil.toTrim(partyBO.getLastName())+ " (#" + TextUtil.toTrim(acctBondBO.getBondNum())+ ")", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
							} else {
								CorisCaseHistoryCommon.addCell(tableAccount, TextUtil.toTrim(partyBO.getFirstName()) + " " + TextUtil.toTrim(partyBO.getLastName()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
							}
						}
					}
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Posted:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(acctBondBO.getBondAmt()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 2);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Forfeited:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(acctBondBO.getForfeitAmt()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 2);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Exonerated:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(acctBondBO.getExonRefundAmt()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 2);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Balance:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					BigDecimal balance = new BigDecimal(0);
					
					balance = balance.add(acctBondBO.getBondAmt()).
									  subtract(acctBondBO.getForfeitAmt()).
									  subtract(acctBondBO.getExonRefundAmt());
					
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(balance), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 2);
					
					// ///////////////////////////////////////////////////////////////
					// Clean Up
					// ///////////////////////////////////////////////////////////////
					bondTypeBO = null;
					bondCoBO = null;
					acctBondBO = null;
					partyBO = null;
				} else if ("F".equals(accountBO.getAcctType())){
					// ///////////////////////////////////////////////////////////////
					// Add to Fee Account Total Information
					// ///////////////////////////////////////////////////////////////
					revenueAmtDueTotal = revenueAmtDueTotal.add(accountBO.getAmtDue()); 
					revenueAmtPaidTotal = revenueAmtPaidTotal.add(accountBO.getAmtPaid());
					revenueAmtCreditTotal = revenueAmtCreditTotal.add(accountBO.getAmtCredit());

					// ///////////////////////////////////////////////////////////////
					// Get Fee Account Information
					// ///////////////////////////////////////////////////////////////
					BigDecimal amtWaived = new BigDecimal(0);
					
					// ///////////////////////////////////////////////////////////////
					// Get Account Fee
					// ///////////////////////////////////////////////////////////////
					AcctFeeBO acctFeeBO = new AcctFeeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(AcctFeeBO.ACCTNUM, accountBO.getAcctNum()).
					setUseConnection(conn).
					find();
					
					// ///////////////////////////////////////////////////////////////
					// Get Account Waived Amount
					// ///////////////////////////////////////////////////////////////
					FieldOperationDescriptor sum = new FieldOperationDescriptor(AcctAdjBO.ADJAMT, FieldOperationType.SUM, new TypeBigDecimal());
					AcctAdjBO acctAdjBO = new AcctAdjBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
					.setFieldOperations(sum)
					.where(AcctAdjBO.ACCTNUM, accountBO.getAcctNum())
					.where(AcctAdjBO.ADJTYPE, "W")
					.groupBy(AcctAdjBO.ACCTNUM)
					.setUseConnection(conn)
					.find(AcctAdjBO.ADJAMT);
				
					if (acctAdjBO == null){
						amtWaived = (BigDecimal) amtWaived.ZERO;
					} else {
						amtWaived = (BigDecimal) acctAdjBO.get(sum);
					}
					
					if (amtWaived != null && amtWaived.compareTo(BigDecimal.ZERO) < 0 ){
						// * -1
						amtWaived = amtWaived.negate();
					}
					
					// ///////////////////////////////////////////////////////////////
					// Get Account Waiver
					// ///////////////////////////////////////////////////////////////
					AcctWaiverBO acctWaiverBO = new AcctWaiverBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(AcctWaiverBO.ACCTNUM, accountBO.getAcctNum()).
					setUseConnection(conn).
					setReturnNull(true).
					find();
					
					// ///////////////////////////////////////////////////////////////
					// Get Fee Type Description
					// ///////////////////////////////////////////////////////////////
					FeeTypeBO feeTypeBO = 
						new FeeTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
						where(FeeTypeBO.FEECODE, acctFeeBO.getFeeCode()).
						where(FeeTypeBO.LASTEFFECTDATE, acctFeeBO.getFeeEffectDate()).   //, Constants.dateFormatCoris).
						setUseConnection(conn).
						setReturnNull(true).
						find();

					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "REVENUE DETAIL - TYPE: " + feeTypeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 4);
					
					if (acctWaiverBO != null){
						if ("R".equals(acctWaiverBO.getWaiverStatus())){
							CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(tableAccount, "Fee Waiver Status - ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(tableAccount, "Requested", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
						}
						if ("W".equals(acctWaiverBO.getWaiverStatus())){
							CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(tableAccount, "Fee Waiver Status - ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(tableAccount, "Waived", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
						}
						if ("D".equals(acctWaiverBO.getWaiverStatus())){
							CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(tableAccount, "Fee Waiver Status - ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(tableAccount, "Denied", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
						}
						if ("P".equals(acctWaiverBO.getWaiverStatus())){
							CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(tableAccount, "Fee Waiver Status - ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(tableAccount, "Partial", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
						}
						if ("G".equals(acctWaiverBO.getWaiverStatus())){
							CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(tableAccount, "Fee Waiver Status - ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(tableAccount, "Government", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
						}
					}
					
					if (feeTypeBO != null){
						if (!"IR".equals(feeTypeBO.getFeeCode())){
							if (accountBO.getAmtDue() == accountBO.getOrigAmtDue()){
								CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(tableAccount, "Amount Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(accountBO.getAmtDue()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
							} else {
								CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(tableAccount, "Original Amount Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(accountBO.getOrigAmtDue()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
								
								CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(tableAccount, "Amended Amount Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(accountBO.getAmtDue()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
								
							}
						}
					}
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Amount Paid:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(accountBO.getAmtPaid()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Amount Credit:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(accountBO.getAmtCredit()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					if (amtWaived != null && amtWaived.compareTo(BigDecimal.ZERO) > 0 ){
						CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, "Amount Waived:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(amtWaived), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					}
					
					BigDecimal balance = new BigDecimal(0);
					
					balance = balance.add(accountBO.getAmtDue()).
									  subtract(accountBO.getAmtPaid()).
									  subtract(accountBO.getAmtCredit());
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Balance:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(balance), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					// ///////////////////////////////////////////////////////////////
					// Clean Up
					// ///////////////////////////////////////////////////////////////
					acctFeeBO = null;
					acctWaiverBO = null;
					feeTypeBO = null;
					amtWaived = new BigDecimal(0);
				} else if ("T".equals(accountBO.getAcctType())){
					// ///////////////////////////////////////////////////////////////
					// Get Account Trust Information
					// ///////////////////////////////////////////////////////////////
					AcctTrustBO acctTrustBO = new AcctTrustBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(AcctTrustBO.ACCTNUM, accountBO.getAcctNum()).
					setUseConnection(conn).
					find();
					
					// ///////////////////////////////////////////////////////////////
					// Get Trust Type Information
					// ///////////////////////////////////////////////////////////////
					TrustTypeBO trustTypeBO = new TrustTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(TrustTypeBO.TRUSTCODE, acctTrustBO.getTrustCode()).
					setUseConnection(conn).
					find();
					
					// ///////////////////////////////////////////////////////////////
					// Add to Account Trust Total Information
					// ///////////////////////////////////////////////////////////////
					if ("Y".equals(trustTypeBO.getOpenendFlag())){
						trustReoccuingOpenTotal = trustReoccuingOpenTotal.add(accountBO.getAmtDue());
						trustReoccuingAmtPaidTotal = trustReoccuingAmtPaidTotal.add(accountBO.getAmtPaid());
						
					} else{
						trustAmtDueTotal = trustAmtDueTotal.add(accountBO.getAmtDue()); 
						trustAmtPaidTotal = trustAmtPaidTotal.add(accountBO.getAmtPaid());
						trustAmtCreditTotal = trustAmtCreditTotal.add(accountBO.getAmtCredit());
					}
					
					// ///////////////////////////////////////////////////////////////
					// Get Party Information
					// ///////////////////////////////////////////////////////////////
					PartyBO partyBO = CorisCaseHistoryCommon.getPartyByPartyNum(corisCaseHistoryDTO, acctTrustBO.getPayeePartyNum(), partyRepository, conn);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "TRUST DETAIL" , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 4);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Trust Description:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, trustTypeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
					
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Receipent:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					if ("".equals(partyBO.getFirstName()) || partyBO.getFirstName() == null){
						CorisCaseHistoryCommon.addCell(tableAccount, TextUtil.toTrim(partyBO.getLastName()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
						
					}else  {
						CorisCaseHistoryCommon.addCell(tableAccount, TextUtil.toTrim(partyBO.getFirstName()) + " " + TextUtil.toTrim(partyBO.getLastName()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
						
					}
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Amount Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(accountBO.getAmtDue()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					
					BigDecimal paidIn = new BigDecimal(0);
					
					paidIn = paidIn.add(accountBO.getAmtPaid()).
									add(accountBO.getAmtCredit());

					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Paid In:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(paidIn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Paid Out:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(acctTrustBO.getAmtPaidOut()), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					// ///////////////////////////////////////////////////////////////
					// Clean Up
					// ///////////////////////////////////////////////////////////////
					acctTrustBO = null;
					partyBO = null;
				}
				
				// ///////////////////////////////////////////////////////////////
				// Any account Adjustments Interest Sum Them
				// ///////////////////////////////////////////////////////////////
				FieldOperationDescriptor sumInt = new FieldOperationDescriptor(AcctAdjBO.ADJAMT, FieldOperationType.SUM, new TypeBigDecimal());
				FieldOperationDescriptor maxInt = new FieldOperationDescriptor(AcctAdjBO.ADJDATETIME, FieldOperationType.MAX, new TypeDate().setDateFormat(new SimpleDateFormat("MMMMM dd, yyyy")));
				List<AcctAdjBO> acctAdjInterestListBO = new AcctAdjBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				setFieldOperations(sumInt, maxInt).
				where(AcctAdjBO.ACCTNUM, accountBO.getAcctNum()).
				where(AcctAdjBO.ADJTYPE, "I").
				groupBy(AcctAdjBO.REASON).
				orderBy(2).
				setUseConnection(conn).
				search(AcctAdjBO.REASON);
				
				// ///////////////////////////////////////////////////////////////
				// Any account Adjustments Non Interest Sum Them
				// ///////////////////////////////////////////////////////////////
				FieldOperationDescriptor sum = new FieldOperationDescriptor(AcctAdjBO.ADJAMT, FieldOperationType.SUM, new TypeBigDecimal());
				FieldOperationDescriptor max = new FieldOperationDescriptor(AcctAdjBO.ADJDATETIME, FieldOperationType.MAX, new TypeDate().setDateFormat(new SimpleDateFormat("MMMMM dd, yyyy")));
				List<AcctAdjBO> acctAdjSumListBO = new AcctAdjBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				setFieldOperations(sum, max).
				where(AcctAdjBO.ACCTNUM, accountBO.getAcctNum()).
				addWhereDescriptors(new WhereCustomDescriptor("(adj_type != 'I' OR adj_type IS NULL)")).
				groupBy(AcctAdjBO.ADJDATETIME ,AcctAdjBO.REASON).
				orderBy(2).
				setUseConnection(conn).
				search(AcctAdjBO.REASON);
				
				if (acctAdjSumListBO.size() > 0 || acctAdjInterestListBO.size() > 0){
					CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Account Adjustments Sum To Date", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Amount", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableAccount, "Reason", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					for (AcctAdjBO acctAdjBO: acctAdjSumListBO){
						CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, acctAdjBO.get(max).toString(), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(acctAdjBO.get(sum)), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, acctAdjBO.getReason(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
						acctAdjBO = null;
					}

					for (AcctAdjBO acctAdjBO: acctAdjInterestListBO){
						CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, acctAdjBO.get(maxInt).toString(), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, Constants.accountingDecimalFormatter.format(acctAdjBO.get(sumInt)), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(tableAccount, acctAdjBO.getReason(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
						acctAdjBO = null;
					}
				}
				acctAdjSumListBO = null;
				acctAdjInterestListBO = null;
				CorisCaseHistoryCommon.addCell(tableAccount, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 5);
			}
			
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			tableAccount.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			tableAccount.completeRow();
			
			tableSum.setWidths(widths);
			CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 5);
			CorisCaseHistoryCommon.addCell(tableSum, "ACCOUNT SUMMARY", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 5);
			
			if (revenueAmtDueTotal.compareTo(BigDecimal.ZERO) > 0){
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Total Revenue Amount Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(revenueAmtDueTotal), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Amount Paid:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(revenueAmtPaidTotal), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Amount Credit:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(revenueAmtCreditTotal), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Balance:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				
				BigDecimal balance = new BigDecimal(0);
				balance = balance.add(revenueAmtDueTotal).
				  subtract(revenueAmtPaidTotal).
				  subtract(revenueAmtCreditTotal);
				
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(balance), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
			
			if (bailCashBondAmtPosted.compareTo(BigDecimal.ZERO) > 0){
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Bail/Cash Bonds Amount Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(bailCashBondAmtPosted), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Amount Paid:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(bailCashBondAmtForfeited), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Amount Credit:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(bailCashBondAmtRefunded), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Balance:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				
				BigDecimal balance = new BigDecimal(0);
				balance = balance.add(bailCashBondAmtPosted).
				  subtract(bailCashBondAmtForfeited).
				  subtract(bailCashBondAmtRefunded);
				
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(balance), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
	
			if (bondAmtPosted.compareTo(BigDecimal.ZERO) > 0){
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Paper Bond Totals Posted:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(bondAmtPosted), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Forfeited:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(bondAmtForfeited), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Exonerated:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(bondAmtExonerated), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Balance:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				
				BigDecimal balance = new BigDecimal(0);
				balance = balance.add(bondAmtPosted).
				  subtract(bondAmtForfeited).
				  subtract(bondAmtExonerated);
	
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(balance), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
			
			if ((trustAmtDueTotal.compareTo(BigDecimal.ZERO) > 0) ||
			   (trustReoccuingOpenTotal.compareTo(BigDecimal.ZERO) > 0) ||
			   (trustReoccuingAmtPaidTotal.compareTo(BigDecimal.ZERO) > 0)){
					CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(tableSum, "Trust Totals:", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 4);
			
			}
			if (trustAmtDueTotal.compareTo(BigDecimal.ZERO) > 0){
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Trust Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(trustAmtDueTotal), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Amount Paid:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(trustAmtPaidTotal), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Amount Credit:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(trustAmtCreditTotal), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Trust Balance Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				BigDecimal balance = new BigDecimal(0);
				
				balance = balance.add(trustAmtDueTotal).
								  subtract(trustAmtPaidTotal).
								  subtract(trustAmtCreditTotal);
				
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(balance), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}	
			if 	(trustReoccuingOpenTotal.compareTo(BigDecimal.ZERO) > 0){
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Recurring/Openended Due:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(trustReoccuingOpenTotal), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
	
			if 	(trustReoccuingAmtPaidTotal.compareTo(BigDecimal.ZERO) > 0){
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, "Amount Paid:", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, Constants.accountingDecimalFormatter.format(trustReoccuingAmtPaidTotal), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(tableSum, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
				
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			tableSum.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			tableSum.completeRow();
			
			
			// ///////////////////////////////////////////////////////////////
			// Clean Up
			// ///////////////////////////////////////////////////////////////
			revenueAmtDueTotal = new BigDecimal(0);
			revenueAmtPaidTotal = new BigDecimal(0);
			revenueAmtCreditTotal = new BigDecimal(0);
			bailCashBondAmtPosted = new BigDecimal(0);
			bailCashBondAmtForfeited = new BigDecimal(0);
			bailCashBondAmtRefunded = new BigDecimal(0);
			bondAmtPosted = new BigDecimal(0);
			bondAmtForfeited = new BigDecimal(0);;
			bondAmtExonerated = new BigDecimal(0);
			trustAmtDueTotal = new BigDecimal(0);
			trustAmtPaidTotal = new BigDecimal(0);
			trustAmtCreditTotal = new BigDecimal(0);
			trustReoccuingOpenTotal = new BigDecimal(0);
			trustReoccuingAmtPaidTotal = new BigDecimal(0);
			lastPymntDate = null;
			finalPayemnt = "";
			timePayDescription = "";		
		} catch (Exception e) {
			logger.error("Process generateCaseHistoryAccountInformation(CorisCaseHistoryDTO corisCaseHistoryDTO) ", e);
		}
		
		returnTablesList.add(tableSum);
		returnTablesList.add(tableAccount);
		
		return returnTablesList;
	}
	public PdfPTable generateCaseHistoryCaseNote(CorisCaseHistoryDTO corisCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[2];
		widths[0] = 10.0f;
		widths[1] = 90.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("CaseHistoryAll Process CaseHistoryParties table setWidths ", e);
		}
		// ///////////////////////////////////////////////////////////////
		// Case Note
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
		CorisCaseHistoryCommon.addCell(table, "CASE NOTE", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
		
		CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		CorisCaseHistoryCommon.addCell(table, corisCaseHistoryDTO.getKaseBO().getNote(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		return table;
	}

	public void generateCaseHistoryProceedings(CorisCaseHistoryDTO corisCaseHistoryDTO, Document document) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			
			table.setWidths(widths);
			/////////////////////////////////////////////////////
			// Summary Event Data
			/////////////////////////////////////////////////////
			List<SummaryEventCaseHistoryDTO> summaryEventCaseHistoryListDTO = CorisCaseHistoryCommon.getCaseHistorySummaryEventData(corisCaseHistoryDTO, conn);
			
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
			CorisCaseHistoryCommon.addCell(table, "PROCEEDINGS", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
			document.add(table); 		
			table = null;
			
			Calendar calPast = null;
			
			if (!TextUtil.isEmpty(afterDaysPast)){
				if (!afterDaysPast.equals("0")){
					calPast = Calendar.getInstance();
					calPast.add(Calendar.DATE, (Integer.valueOf(afterDaysPast) * -1));
				}
			}
			
			
			for (SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO: summaryEventCaseHistoryListDTO){
				PdfPTable tableData = new PdfPTable(3);
				tableData.setWidthPercentage(100);
				
				if (calPast != null){
					if (summaryEventCaseHistoryDTO.getEventDatetime().compareTo(calPast.getTime()) < 0) {
						continue;
					}
				}

				if ("TRANSFERRED".equals(summaryEventCaseHistoryDTO.getKey4())){
					tableData = generateCaseHistorySummaryEventTransfered(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if ("CONSOLIDATED".equals(summaryEventCaseHistoryDTO.getKey4())){
					tableData = generateCaseHistorySummaryEventConsolidated(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);					
				} else if(!TextUtil.isEmpty((String) summaryEventCaseHistoryDTO.getChText())){
					tableData = generateCaseHistorySummaryEventHistNote(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("HISTNOTE".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventHistNote(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("ACCOUNT".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventAccount(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("AMNDINFO".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventAmndInfo(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("CALENDAR".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventCalendar(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("CASEDISP".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventCaseDisp(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("CASEFILE".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventCaseFiled(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("CCDSPNTC".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventCcdspntc(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("CHARGE".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventCharge(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("CHARGES".equals(summaryEventCaseHistoryDTO.getFuncId())){
					continue;
				} else if("DEFENDNT".equals(summaryEventCaseHistoryDTO.getFuncId())){
					continue;
				} else if("DISMISS".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventDismiss(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("DOCUMENT".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventDocument(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("EVIDENCE".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventEvidence(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("FORFNOTC".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventForfNotc(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("FTA/FTC".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventFtaFtc(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("JUDGHIST".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventJudgeHist(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("JUDGMENT".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventJudgment(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("MEDIATIO".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventMediation(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("MINUTE".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventMinuteHeader(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
					tableData.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
					tableData.completeRow();
					document.add(tableData);
					tableData = generateCaseHistorySummaryEventMinute(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("NAME/ADD".equals(summaryEventCaseHistoryDTO.getFuncId())){
					if (!inquiryUser){
						tableData = generateCaseHistorySummaryEventNameAdd(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
					}
				} else if("NOTICE".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventNoticeHeader(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
					tableData.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
					tableData.completeRow();
					document.add(tableData); 		
					tableData = generateCaseHistorySummaryEventNotice(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("NSF".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventNsf(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("STAY".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventStay(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("TRACKING".equals(summaryEventCaseHistoryDTO.getFuncId())){
					if (!inquiryUser){
						tableData = generateCaseHistorySummaryEventTracking(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
					}
				} else if("WARRANT".equals(summaryEventCaseHistoryDTO.getFuncId())){
					tableData = generateCaseHistorySummaryEventWarrant(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				} else if("".equals(summaryEventCaseHistoryDTO.getFuncId()) || summaryEventCaseHistoryDTO.getFuncId() == null){
					continue;
				} else{
					tableData = generateCaseHistorySummaryEventUnknown(corisCaseHistoryDTO, summaryEventCaseHistoryDTO);
				}
				// ///////////////////////////////////////////////////////////////
				// Just in case we don't have enough cells
				// tell the table to "fill in the blanks"
				// ///////////////////////////////////////////////////////////////
				tableData.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				tableData.completeRow();
				document.add(tableData);
				tableData = null;
			}
		} catch (Exception e) {
			logger.error("generateCaseHistoryProceedings(CorisCaseHistoryDTO corisCaseHistoryDTO, Document document)", e);
		}
	}
	
	public PdfPTable generateCaseHistorySummaryEventCaseFiled(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		String eventDescription = "";
		
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("CaseHistoryAll Process generateCaseHistorySummaryEventCaseFiled table setWidths ", e);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Date Time
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Description
		// ///////////////////////////////////////////////////////////////
		if (summaryEventCaseHistoryDTO.getDescr().contains("Citation filed")){
			eventDescription = "Filed: Citation";
		}else if (summaryEventCaseHistoryDTO.getDescr().contains("Complaint filed")){
			eventDescription = "Filed: Complaint";
		}else if (summaryEventCaseHistoryDTO.getDescr().contains("Petition filed")){
			eventDescription = "Filed: Petition";
		} else {
			eventDescription = summaryEventCaseHistoryDTO.getDescr();
		}
		
		if (!TextUtil.isEmpty(corisCaseHistoryDTO.getKaseBO().getRefNum())){
			if ("TL".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
				eventDescription = "Paperless Tax Lien";
			} else if ("SL".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
				eventDescription = "Paperless Child Support Lien";
			}
		}
		
		if (!"U".equals(corisCaseHistoryDTO.getKaseBO().getCaseSecurity())){
			if (!TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey1())){
				eventDescription = "**** " + summaryEventCaseHistoryDTO.getKey1() + " **** " + eventDescription.trim(); 
			}
		}
		
		CorisCaseHistoryCommon.addCell(table, eventDescription, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		
		// ///////////////////////////////////////////////////////////////
		// Logname
		// ///////////////////////////////////////////////////////////////
		if (inquiryUser){
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		} else {
			CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(corisCaseHistoryDTO.getKaseBO().getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		if (corisCaseHistoryDTO.getCrimCaseBO() != null){
			if ("Y".equals(corisCaseHistoryDTO.getCrimCaseBO().getInformation())){
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Filed: From an Information", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				// ///////////////////////////////////////////////////////////////
				// Logname
				// ///////////////////////////////////////////////////////////////
				if (inquiryUser){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				} else {
					CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(corisCaseHistoryDTO.getKaseBO().getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				}
				// ///////////////////////////////////////////////////////////////
				// Just in case we don't have enough cells
				// tell the table to "fill in the blanks"
				// ///////////////////////////////////////////////////////////////
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
			}
		}	
		
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventUnknown(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("CaseHistoryAll Process CaseHistoryParties table setWidths ", e);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Date Time
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Description
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table,summaryEventCaseHistoryDTO.getFuncId(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		
		// ///////////////////////////////////////////////////////////////
		// Logname
		// ///////////////////////////////////////////////////////////////
		if (inquiryUser){
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		} else {
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventJudgeHist(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{

		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
			
			if (!TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey1())){
				// ///////////////////////////////////////////////////////////////
				// Get judgeHist Data
				// ///////////////////////////////////////////////////////////////
				JudgeHistBO judgeHistBO = new JudgeHistBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(JudgeHistBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
				where(JudgeHistBO.JUDGEID, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
				setUseConnection(conn).
				find(JudgeHistBO.JUDGEID, JudgeHistBO.CLERKID);
				
				if (judgeHistBO.getClerkId() > 0){
					// ///////////////////////////////////////////////////////////////
					// Summary Event Date Time
					// ///////////////////////////////////////////////////////////////
					CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					
					// ///////////////////////////////////////////////////////////////
					// Event Description
					// ///////////////////////////////////////////////////////////////
					CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getTitleName(judgeHistBO.getJudgeId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn) + " assigned.", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					
					// ///////////////////////////////////////////////////////////////
					// Logname
					// ///////////////////////////////////////////////////////////////
					if (inquiryUser){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					} else {
						CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(judgeHistBO.getClerkId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					}
				}	
				// ///////////////////////////////////////////////////////////////
				// Just in case we don't have enough cells
				// tell the table to "fill in the blanks"
				// ///////////////////////////////////////////////////////////////
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				
				// ///////////////////////////////////////////////////////////////
				// Clean Up
				// ///////////////////////////////////////////////////////////////
				judgeHistBO = null;
			}
			
		} catch (Exception e) {
				logger.error("CaseHistoryAll generateCaseHistorySummaryEventJudgeHist(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) ", e);
		}
		return table;
	}
	
	public PdfPTable generateCaseHistorySummaryEventDocument(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
			
			// ///////////////////////////////////////////////////////////////
			// Get Document Data
			// ///////////////////////////////////////////////////////////////
			DocumentBO documentBO = new DocumentBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(DocumentBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
			where(DocumentBO.DOCUMENTNUM, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
			setUseConnection(conn).
			find();

			
			// ///////////////////////////////////////////////////////////////
			// No Processing 
			// ///////////////////////////////////////////////////////////////
			if (inquiryUser){
				if ("VI".equals(documentBO.getCategory())){
					documentBO = null;
					return table;
				}
			}	
			
			// ///////////////////////////////////////////////////////////////
			// Summary Event Date Time
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
	
			// ///////////////////////////////////////////////////////////////
			// Document Title Adjustments
			// ///////////////////////////////////////////////////////////////
			if ("E".equals(documentBO.getCategory())){
				documentBO.setTitle("Filed return: " + documentBO.getTitle());
			} else if ("J".equals(documentBO.getCategory())){
				documentBO.setTitle("Filed judgment: " + documentBO.getTitle());
			} else if ("H".equals(documentBO.getCategory())){
				documentBO.setTitle("Filed protective order: " + documentBO.getTitle());
			} else if ("O".equals(documentBO.getCategory())){
				documentBO.setTitle("Filed order: " + documentBO.getTitle());
			} else if ("Q".equals(documentBO.getCategory())){
				documentBO.setTitle("Filed order: " + documentBO.getTitle());
			} else if ("LE".equals(documentBO.getCategory())){
				documentBO.setTitle("Filed order: " + documentBO.getTitle());
			} else if ("I".equals(documentBO.getCategory())){
				documentBO.setTitle("Issued: " + documentBO.getTitle());
			} else if ("W".equals(documentBO.getCategory())){
				documentBO.setTitle("Issued: " + documentBO.getTitle());
			} else{
				documentBO.setTitle("Filed: " + documentBO.getTitle());
			}
			
			if (!"U".equals(documentBO.getDocSecurity())){
				// ///////////////////////////////////////////////////////////////
				// Get Security Type Data
				// ///////////////////////////////////////////////////////////////
				SecurityTypeBO securityTypeBO = CorisSecurityCommon.getSecurityType(documentBO.getDocSecurity(), corisCaseHistoryDTO.getKaseBO().getCourtType(), conn);
				
				// ///////////////////////////////////////////////////////////////
				// Set Document Title
				// ///////////////////////////////////////////////////////////////
				documentBO.setTitle("**** " + securityTypeBO.getDescr() + " **** " + documentBO.getTitle());
				securityTypeBO = null;
			}
			
			// ///////////////////////////////////////////////////////////////
			// Document Title
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, documentBO.getTitle(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			// ///////////////////////////////////////////////////////////////
			// Logname
			// ///////////////////////////////////////////////////////////////
			if (inquiryUser){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(documentBO.getClerkId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			}
	
			/////////////////////////////////////////////////////
			// Minute Access
			/////////////////////////////////////////////////////
			boolean documentAccess = false;
			if ("U".equals(documentBO.getDocSecurity())){
				documentAccess = true;
			} else if ("V".equals(documentBO.getDocSecurity())){
				documentAccess = privateProtectedAccess;
			} else if ("S".equals(documentBO.getDocSecurity())){
				documentAccess = sealedAccess;
			} else if ("X".equals(documentBO.getDocSecurity())){
				documentAccess = expungedAccess;
			} else if ("O".equals(documentBO.getDocSecurity())){
				documentAccess = privateProtectedAccess;
			}else if("IC".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) || "IS".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
				documentAccess = mentalAccess;
			}
			/////////////////////////////////////////////////////
			// Case Access Trumps Document Access
			/////////////////////////////////////////////////////
			if (documentAccess){
				documentAccess = caseAccess;
			}
			
			if (documentAccess){
				if ("X".equals(documentBO.getCategory()) ||
					"GI".equals(documentBO.getCategory()) ||	
					"GA".equals(documentBO.getCategory()) ||	
					"GF".equals(documentBO.getCategory()) ||	
					"GS".equals(documentBO.getCategory()) ||	
					"GC".equals(documentBO.getCategory())){
					
					// ///////////////////////////////////////////////////////////////
					// Get Document Motion
					// ///////////////////////////////////////////////////////////////
					DocMotionBO docMotionBO = new	DocMotionBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(DocMotionBO.DOCUMENTNUM, documentBO.getDocumentNum()).
					setUseConnection(conn).
					find();
					
					if (docMotionBO.getPartyNum() > 0 ){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "     Filed by: " + CorisCaseHistoryCommon.getPartyNameByPartyNum(corisCaseHistoryDTO, docMotionBO.getPartyNum(), partyRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					}
					docMotionBO = null;
				} else if ("O".equals(documentBO.getCategory()) ||
					"J".equals(documentBO.getCategory()) ||	
					"Q".equals(documentBO.getCategory()) ||	
					"H".equals(documentBO.getCategory()) ||	
					"V".equals(documentBO.getCategory()) ||	
					"MS".equals(documentBO.getCategory()) ||	
					"PV".equals(documentBO.getCategory()) ||	
					"FI".equals(documentBO.getCategory()) ||	
					"LE".equals(documentBO.getCategory()) ||	
					"PW".equals(documentBO.getCategory())){
					// ///////////////////////////////////////////////////////////////
					// Get Document Ordered
					// ///////////////////////////////////////////////////////////////
					DocOrderBO docOrderBO = new	DocOrderBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(DocOrderBO.DOCUMENTNUM, documentBO.getDocumentNum()).
					setUseConnection(conn).
					setReturnNull(true).
					find();
					
					if (docOrderBO != null){
						if (docOrderBO.getSignedDate() != null){
							CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, "     " + CorisCaseHistoryCommon.getTitleName(docOrderBO.getJudgeCommId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
							
							CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, "     Signed " + Constants.longTextFormat.format(docOrderBO.getSignedDate()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
						}
					}	
					docOrderBO = null;
				} else if ("E".equals(documentBO.getCategory())){
					// ///////////////////////////////////////////////////////////////
					// Get Document Returned
					// ///////////////////////////////////////////////////////////////
					DocReturnBO docReturnBO = new DocReturnBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(DocReturnBO.DOCUMENTNUM, documentBO.getDocumentNum()).
					setUseConnection(conn).
					setReturnNull(true).
					find();
					
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "     Party Served: " + docReturnBO.getPartyServed(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					String serviceDescription = "";
					
					if ("M".equals(docReturnBO.getServiceType())){
						serviceDescription = "Mail";
					} else if ("P".equals(docReturnBO.getServiceType())){
						serviceDescription = "Personal";
					} else if ("S".equals(docReturnBO.getServiceType())){
						serviceDescription = "Substitute";
					} else if ("N".equals(docReturnBO.getServiceType())){
						serviceDescription = "NonPersonal";
					} else if ("U".equals(docReturnBO.getServiceType())){
						serviceDescription = "Publication";
					}
					
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "     Service Type: " + serviceDescription, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					
					if (docReturnBO.getServiceDate() != null){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "     Service Date: " + Constants.longTextFormat.format(docReturnBO.getServiceDate()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					} else {
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "     Service Date: ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					}
						
					
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					if ("".equals(docReturnBO.getGarnishee()) || docReturnBO.getGarnishee() == null){
						CorisCaseHistoryCommon.addCell(table, "        Garnishee: ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					}else {
						CorisCaseHistoryCommon.addCell(table, "        Garnishee: " + docReturnBO.getGarnishee(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					}
					
					docReturnBO = null;
				} else if ("A".equals(documentBO.getCategory())){
					// ///////////////////////////////////////////////////////////////
					// Get Document Answer 
					// ///////////////////////////////////////////////////////////////
					DocAnswerBO docAnswerBO = new	DocAnswerBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(DocAnsPartyBO.DOCUMENTNUM, documentBO.getDocumentNum()).
					setUseConnection(conn).
					find();
					
					// ///////////////////////////////////////////////////////////////
					// Get Document Answer 
					// ///////////////////////////////////////////////////////////////
					List<DocAnsPartyBO> docAnsPartyListBO = new	DocAnsPartyBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(DocAnsPartyBO.DOCUMENTNUM, documentBO.getDocumentNum()).
					setUseConnection(conn).
					setReturnNull(true).
					search();
					
					for(DocAnsPartyBO docAnsPartyBO :docAnsPartyListBO){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "     Answer Party: " + CorisCaseHistoryCommon.getPartyNameByPartyNum(corisCaseHistoryDTO,docAnsPartyBO.getPartyNum(), partyRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					}
					
					if (docAnswerBO.getAmt().compareTo(BigDecimal.ZERO) > 0){						
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "           Amount: " + Constants.accountingDecimalFormatter.format(docAnswerBO.getAmt()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					}
					
					if (docAnswerBO.getNoticeDate() != null){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "      Notice Date: " + Constants.longTextFormat.format(docAnswerBO.getNoticeDate()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					}
					
					docAnswerBO = null;
					docAnsPartyListBO = null;
					
				} else if ("I".equals(documentBO.getCategory()) ||
					       "W".equals(documentBO.getCategory())){
					// ///////////////////////////////////////////////////////////////
					// Get Document Answer 
					// ///////////////////////////////////////////////////////////////
					DocIssueBO docIssueBO = new	DocIssueBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(DocIssueBO.DOCUMENTNUM, documentBO.getDocumentNum()).
					setUseConnection(conn).
					find();
					
					if (docIssueBO.getReturnDatetime() != null){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "     " + CorisCaseHistoryCommon.getTitleName(docIssueBO.getJudgeCommId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
						
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "     Hearing Date " + Constants.longDateTimeTextFormat.format(docIssueBO.getReturnDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					} else 	if (docIssueBO.getJudgeCommId() > 0){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "     " + CorisCaseHistoryCommon.getTitleName(docIssueBO.getJudgeCommId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					}

					docIssueBO = null;
				}
			}	
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			
			// ///////////////////////////////////////////////////////////////
			// Clean Up
			// ///////////////////////////////////////////////////////////////
			documentBO = null;
		} catch (Exception e) {
			logger.error("CaseHistoryAll generateCaseHistorySummaryEventDocument(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) ", e);
		}
		
		return table;
	}
	
	public PdfPTable generateCaseHistorySummaryEventNameAdd(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
			if (!inquiryUser){
				if (!"Party created".equals(summaryEventCaseHistoryDTO.getDescr())){
					// ///////////////////////////////////////////////////////////////
					// Summary Event Date Time
					// ///////////////////////////////////////////////////////////////
					CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					
					if (TextUtil.isEmpty((String) summaryEventCaseHistoryDTO.getChText())){
						CorisCaseHistoryCommon.addCell(table, "Note: " + summaryEventCaseHistoryDTO.getDescr().trim(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					} else {
						CorisCaseHistoryCommon.addCell(table, "Note: " + summaryEventCaseHistoryDTO.getChText(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					}
					
					// ///////////////////////////////////////////////////////////////
					// Logname
					// ///////////////////////////////////////////////////////////////
					if (inquiryUser){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					} else {
						CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(corisCaseHistoryDTO.getKaseBO().getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					}
				}
			}	
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
		
			// ///////////////////////////////////////////////////////////////
			// Clean Up
			// ///////////////////////////////////////////////////////////////
		} catch (Exception e) {
			logger.error("CaseHistoryAll Process generateCaseHistorySummaryEventNameAdd table setWidths ", e);
		}
		
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventHistNote(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			
			if (inquiryUser && "NAME/ADD".equals(summaryEventCaseHistoryDTO.getFuncId())){
				// ///////////////////////////////////////////////////////////////
				// Do not show
				// ///////////////////////////////////////////////////////////////
			} else if (inquiryUser && "EMAILADR".equals(summaryEventCaseHistoryDTO.getFuncId())){
				// ///////////////////////////////////////////////////////////////
				// Do not show
				// ///////////////////////////////////////////////////////////////
			} else {
				// ///////////////////////////////////////////////////////////////
				// Summary Event Date Time
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				
				if (TextUtil.isEmpty((String) summaryEventCaseHistoryDTO.getChText())){
					CorisCaseHistoryCommon.addCell(table, "Note: " + summaryEventCaseHistoryDTO.getDescr().trim(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else {
					CorisCaseHistoryCommon.addCell(table, "Note: " + summaryEventCaseHistoryDTO.getChText(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				
				// ///////////////////////////////////////////////////////////////
				// Logname
				// ///////////////////////////////////////////////////////////////
				if (inquiryUser){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				} else {
					CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName((Integer) summaryEventCaseHistoryDTO.getClerkId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				}
				
				// ///////////////////////////////////////////////////////////////
				// Just in case we don't have enough cells
				// tell the table to "fill in the blanks"
				// ///////////////////////////////////////////////////////////////
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				
				// ///////////////////////////////////////////////////////////////
				// Clean Up
				// ///////////////////////////////////////////////////////////////
				table.setWidths(widths);
			}
		} catch (Exception e) {
			logger.error("generateCaseHistorySummaryEventHistNote(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO)", e);
		}
			
		return table;
	}
	
	public PdfPTable generateCaseHistorySummaryEventTracking(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			// ///////////////////////////////////////////////////////////////
			// Tracking VO
			// ///////////////////////////////////////////////////////////////
			Date date1 = null;
			try{
				date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(summaryEventCaseHistoryDTO.getKey1());
			} catch (Exception e) {
				date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(summaryEventCaseHistoryDTO.getKey1());
			}
			TrackingBO trackingBO = new TrackingBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
					.where(TrackingBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum())
					.where(TrackingBO.CREATEDATETIME, date1)
					.where(TrackingBO.TRACKCODE, summaryEventCaseHistoryDTO.getKey2())
					.setUseConnection(conn)
					.setReturnNull(true)
					.find();
			
			if (trackingBO != null){
				
				// ///////////////////////////////////////////////////////////////
				// Tracking Type VO
				// ///////////////////////////////////////////////////////////////
				TrackingTypeBO trackingTypeBO = new TrackingTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
				.where(TrackingTypeBO.TRACKCODE, summaryEventCaseHistoryDTO.getKey2())
				.where(TrackingTypeBO.LOCNCODE, corisCaseHistoryDTO.getKaseBO().getLocnCode())
				.setUseConnection(conn).
				find(TrackingTypeBO.DESCR);
				
				// ///////////////////////////////////////////////////////////////
				// Summary Event Date Time
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				if (summaryEventCaseHistoryDTO.getDescr().contains("Tracking started")){
					CorisCaseHistoryCommon.addCell(table, "Tracking started for " + trackingTypeBO.getDescr() 
					+ ". Review date " 
					+ Constants.longTextFormat.format(trackingBO.getReviewDate())
					, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
		
				if (summaryEventCaseHistoryDTO.getDescr().contains("Tracking modified")){
					CorisCaseHistoryCommon.addCell(table, "Tracking " + trackingTypeBO.getDescr() + ". Changed to Review date " + Constants.longTextFormat.format(trackingBO.getReviewDate()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				
				if (summaryEventCaseHistoryDTO.getDescr().contains("Tracking ended")){
					CorisCaseHistoryCommon.addCell(table, "Tracking ended for " + trackingTypeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				
				// ///////////////////////////////////////////////////////////////
				// Clean Up
				// ///////////////////////////////////////////////////////////////
				trackingTypeBO = null;

			} else {
				// ///////////////////////////////////////////////////////////////
				// Summary Event Date Time
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				if ("".equals(summaryEventCaseHistoryDTO.getKey3()) || summaryEventCaseHistoryDTO.getKey3() == null){
					CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else {
					CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr() + " on " + summaryEventCaseHistoryDTO.getKey3(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
			}
			
			// ///////////////////////////////////////////////////////////////
			// Logname
			// 1st get it from the summary event key_4
			// 2nd get it from the tracking bo as a fail safe
			// ///////////////////////////////////////////////////////////////
			
			if (inquiryUser || trackingBO == null){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			} else {
				
				if (summaryEventCaseHistoryDTO.getDescr().contains("Tracking ended")){
					if (TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey4())){
						CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(trackingBO.getEndUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					} else {
						CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(Integer.valueOf(summaryEventCaseHistoryDTO.getKey4()), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				}
				} else {
					if (TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey4())){
						CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(trackingBO.getClerkId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					} else {
						CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(Integer.valueOf(summaryEventCaseHistoryDTO.getKey4()), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					}
				}
			}
			
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			
			// ///////////////////////////////////////////////////////////////
			// Clean Up
			// ///////////////////////////////////////////////////////////////
			trackingBO = null;

		} catch (Exception e) {
			logger.error("CaseHistoryAll Process generateCaseHistorySummaryEventTracking table setWidths ", e);
		}

		widths = null;
		return table;
	}
	
	public PdfPTable generateCaseHistorySummaryEventCalendar(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);

		try {
			table.setWidths(widths);
			
			// //////////////////////////////////////////////////////////////////////
			// Ensure Date is in the format of MM/dd/yyyy not M/d/yy Bad Coris Data
			// //////////////////////////////////////////////////////////////////////
			summaryEventCaseHistoryDTO.setKey1(CorisCaseHistoryCommon.checkDateFormat(summaryEventCaseHistoryDTO.getKey1(), "/"));
			
			// ///////////////////////////////////////////////////////////////
			// Get Calendar Information
			// ///////////////////////////////////////////////////////////////
			CalendarBO calendarBO = new CalendarBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
			.where(CalendarBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()) 
			.where(CalendarBO.HEARINGCODE, summaryEventCaseHistoryDTO.getKey2()) 
			.where(CalendarBO.APPEARDATE, Constants.dateFormatCoris.parse(summaryEventCaseHistoryDTO.getKey1()))
			.setUseConnection(conn)
			.setReturnNull(true)
			.find(); 
			
			if (calendarBO != null){
				// ///////////////////////////////////////////////////////////////
				// Get Hearing Type 
				// ///////////////////////////////////////////////////////////////
				HearingTypeBO hearingTypeBO = new HearingTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(HearingTypeBO.HEARINGCODE, calendarBO.getHearingCode()).
				where(HearingTypeBO.LOCNCODE, corisCaseHistoryDTO.getKaseBO().getLocnCode()).
				where(HearingTypeBO.COURTTYPE, corisCaseHistoryDTO.getKaseBO().getCourtType()).
				setUseConnection(conn).
				setReturnNull(true).
				find(HearingTypeBO.DESCR); 
				
				// ///////////////////////////////////////////////////////////////
				// Court Room 
				// ///////////////////////////////////////////////////////////////
				CourtroomBO courtroomBO = new CourtroomBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(CourtroomBO.COURTROOMCODE, calendarBO.getRoom()).
				where(CourtroomBO.LOCNCODE, corisCaseHistoryDTO.getKaseBO().getLocnCode()).
				where(CourtroomBO.COURTTYPE, corisCaseHistoryDTO.getKaseBO().getCourtType()).
				setUseConnection(conn).
				setReturnNull(true).
				find(); 
				
				// ///////////////////////////////////////////////////////////////
				// Summary Event Date Time
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				if ("Cancelled".equals(summaryEventCaseHistoryDTO.getKey3())){
					CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else if ("Modified".equals(summaryEventCaseHistoryDTO.getKey3())){
					CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else if (summaryEventCaseHistoryDTO.getDescr().contains("resched")){
					CorisCaseHistoryCommon.addCell(table, calendarBO.getEventDescr() +  " rescheduled on " + Constants.longTextFormat.format(calendarBO.getAppearDate()) + " at " + Constants.timeFormat.format(calendarBO.getTime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else {
					if (calendarBO != null){
						if (courtroomBO != null){
							CorisCaseHistoryCommon.addCell(table, calendarBO.getEventDescr() +  " scheduled on " + Constants.longTextFormat.format(calendarBO.getAppearDate()) + " at " + Constants.timeFormat.format(calendarBO.getTime()) + " in " + courtroomBO.getDescr() + " with " + CorisCaseHistoryCommon.getTitleName(calendarBO.getJudgeId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						} else {
							CorisCaseHistoryCommon.addCell(table, calendarBO.getEventDescr() +  " scheduled on " + Constants.longTextFormat.format(calendarBO.getAppearDate()) + " at " + Constants.timeFormat.format(calendarBO.getTime()) + " with " + CorisCaseHistoryCommon.getTitleName(calendarBO.getJudgeId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						}
					} else{ 
						if (courtroomBO != null){
							CorisCaseHistoryCommon.addCell(table, hearingTypeBO.getDescr() +  " scheduled on " + Constants.longTextFormat.format(calendarBO.getAppearDate()) + " at " + Constants.timeFormat.format(calendarBO.getTime()) + " in " + courtroomBO.getDescr() + " with " + CorisCaseHistoryCommon.getTitleName(calendarBO.getJudgeId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						} else {
							CorisCaseHistoryCommon.addCell(table, hearingTypeBO.getDescr() +  " scheduled on " + Constants.longTextFormat.format(calendarBO.getAppearDate()) + " at " + Constants.timeFormat.format(calendarBO.getTime()) + " with " + CorisCaseHistoryCommon.getTitleName(calendarBO.getJudgeId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						}
					}
				}
				// ///////////////////////////////////////////////////////////////
				// Logname
				// ///////////////////////////////////////////////////////////////
				if (inquiryUser){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else {
					
					if ("Cancelled".equals(summaryEventCaseHistoryDTO.getKey3())){
						CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(calendarBO.getCancelUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					} else if ("Modified".equals(summaryEventCaseHistoryDTO.getKey3())){
						CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(calendarBO.getCancelUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					} else if (summaryEventCaseHistoryDTO.getDescr().contains("resched")){
						CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(calendarBO.getCancelUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					} else {
						CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(calendarBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					}
				}
				if ("Cancelled".equals(summaryEventCaseHistoryDTO.getKey3())){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Reason: " + calendarBO.getCancelReason(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else if ("Modified".equals(summaryEventCaseHistoryDTO.getKey3())){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Reason: " + calendarBO.getCancelReason(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else if (summaryEventCaseHistoryDTO.getDescr().contains("resched")){
					// ///////////////////////////////////////////////////////////////
					// Get Calendar Information
					// ///////////////////////////////////////////////////////////////
					CalendarBO reschedCalendarBO = new CalendarBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
					.where(CalendarBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()) 
					.where(CalendarBO.CANCELDATETIME, calendarBO.getCreateDatetime())
					.setUseConnection(conn)
					.find(); 
					
					if (reschedCalendarBO != null){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, "Reason: " + reschedCalendarBO.getCancelReason(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					}
					
					reschedCalendarBO = null;
				}
				// ///////////////////////////////////////////////////////////////
				// Just in case we don't have enough cells
				// tell the table to "fill in the blanks"
				// ///////////////////////////////////////////////////////////////
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				
				calendarBO = null; 
				hearingTypeBO = null;
				courtroomBO = null;
			}
			
		} catch (Exception e) {
			logger.error("generateCaseHistorySummaryEventCalendar(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO)", e);
		}
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventAccount(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		try {
			table.setWidths(widths);
		
			// ///////////////////////////////////////////////////////////////
			// Get Account Information
			// ///////////////////////////////////////////////////////////////
			int acctNum; 
			if ("".equals(summaryEventCaseHistoryDTO.getKey2()) || summaryEventCaseHistoryDTO.getKey2() == null){
				acctNum = Integer.valueOf(summaryEventCaseHistoryDTO.getKey1());
			} else {
				acctNum = Integer.valueOf(summaryEventCaseHistoryDTO.getKey3());
			}
			if ("".equals(summaryEventCaseHistoryDTO.getKey2()) || summaryEventCaseHistoryDTO.getKey2() == null){
				AccountBO accountBO = new AccountBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(AccountBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
				where(AccountBO.ACCTNUM, acctNum).
				setUseConnection(conn).
				setReturnNull(true).
				find();
				
				
				if (accountBO != null){
				
					if (summaryEventCaseHistoryDTO.getDescr().toUpperCase().contains("CREATED")){
						if (accountBO.getOrigAmtDue().compareTo(BigDecimal.ZERO) > 0 ){
							accountBO.setAmtDue(accountBO.getOrigAmtDue());
						}
					}
					// ///////////////////////////////////////////////////////////////
					// Summary Event Date Time
					// ///////////////////////////////////////////////////////////////
					CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr() + " Total Due: " + Constants.accountingDecimalFormatter.format(accountBO.getAmtDue()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					if (summaryEventCaseHistoryDTO.getDescr().toUpperCase().contains("CREATED")){
						// ///////////////////////////////////////////////////////////////
						// Logname
						// ///////////////////////////////////////////////////////////////
						if (inquiryUser){
							CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						} else {
							CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(accountBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository,conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						}
					}
					
					if (summaryEventCaseHistoryDTO.getDescr().toUpperCase().contains("ADJUST")){
						String sEventDateTime = Constants.queryDateTimeFormat.format(summaryEventCaseHistoryDTO.getEventDatetime());
						Date dEventDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(sEventDateTime);
						List<AcctAdjBO> acctAdjListBO = new AcctAdjBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
						.where(AcctAdjBO.ACCTNUM, accountBO.getAcctNum())
						.where(AcctAdjBO.ADJDATETIME, dEventDateTime)
						.setUseConnection(conn)
						.search();
						// ///////////////////////////////////////////////////////////////
						// Account Adjust Reason
						// ///////////////////////////////////////////////////////////////
						int prevSeq = 0;
						String prevReason = "";
						for (AcctAdjBO acctAdjBO : acctAdjListBO){
							if (prevSeq == 0) {
								// ///////////////////////////////////////////////////////////////
								// Logname
								// ///////////////////////////////////////////////////////////////
								if (inquiryUser){
									CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								} else {
									CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(acctAdjBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository,conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								}
							}	
							
							if (!TextUtil.isEmpty(acctAdjBO.getReason())){
								if (!prevReason.equals(acctAdjBO.getReason())){
									if (prevSeq != acctAdjBO.getSeq()){
										CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
										CorisCaseHistoryCommon.addCell(table, "Reason: " + acctAdjBO.getReason(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
										CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
									}
								}
							}
							prevSeq = acctAdjBO.getSeq();
							prevReason = acctAdjBO.getReason();
							acctAdjBO = null;
						}
						prevSeq = 0;
						acctAdjListBO = null;
						accountBO = null;
					} else {
						// ///////////////////////////////////////////////////////////////
						// Summary Event Date Time
						// ///////////////////////////////////////////////////////////////
						CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						// ///////////////////////////////////////////////////////////////
						// Logname
						// ///////////////////////////////////////////////////////////////
						if (inquiryUser){
							CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						} else {
							CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(accountBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository,conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						}
					}
						
				}
			} else {
				String details = "";
				String longDetails = "";
				String lineDetails = "";
				
				BigDecimal amtPaid = new BigDecimal(0);
				BigDecimal amtCredit = new BigDecimal(0);
				
				// ///////////////////////////////////////////////////////////////
				// Trans Information
				// ///////////////////////////////////////////////////////////////
				TransBO transBO = new TransBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(TransBO.INTJOURNALNUM, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
				where(TransBO.TRANSNUM, Integer.valueOf(summaryEventCaseHistoryDTO.getKey2())).
				setReturnNull(true).
				setUseConnection(conn).
				find();
				
				// ///////////////////////////////////////////////////////////////
				// Trans Dist Information
				// ///////////////////////////////////////////////////////////////
				List<TransDistBO> transDistListBO = new TransDistBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(TransDistBO.INTJOURNALNUM, transBO.getIntJournalNum()).
				where(TransDistBO.TRANSNUM, transBO.getTransNum()).
				setReturnNull(true).
				setUseConnection(conn).
				search();
				
				int prevAcctNum = 0;
				for (TransDistBO transDistBO :transDistListBO){
					
					AccountBO accountBO = new AccountBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
					where(AccountBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
					where(AccountBO.ACCTNUM, transDistBO.getAcctNum()).
					setUseConnection(conn).
					find();
					
					
					if (accountBO.getIntCaseNum() == summaryEventCaseHistoryDTO.getIntCaseNum()){
						if ("B".equals(accountBO.getAcctType()) || "O".equals(accountBO.getAcctType())){
							
							if ("BA".equals(transDistBO.getApplyCode())){
								details = "Bail Posted";
							}else if ("BF".equals(transDistBO.getApplyCode())){
								details = "Bail Forfeited";
							}else if ("BR".equals(transDistBO.getApplyCode())){
								details = "Bail Refunded";
							}else if ("OA".equals(transDistBO.getApplyCode())){
								details = "Bond Posted";
							}else if ("OF".equals(transDistBO.getApplyCode())){
								details = "Bond Forfeited";
							}else if ("OR".equals(transDistBO.getApplyCode())){
								details = "Bond Transfer/Refunded";
							}else if ("TO".equals(transDistBO.getApplyCode())){
								details = "Trust Paid Out";
							}else if ("TE".equals(transDistBO.getApplyCode())){
								details = "Trust Endorsed";
							}else if ("OP".equals(transDistBO.getApplyCode())){
								details = "Paper Bond Payoff";
							}else if ("OX".equals(transDistBO.getApplyCode())){
								details = "Bond Exonerated";
							}else if ("AA".equals(transDistBO.getApplyCode())){
								details = "Account Adjusted";
							}
						}
			
						if ("O".equals(accountBO.getAcctType()) && accountBO.getAmtPaid().compareTo(BigDecimal.ZERO) < 0){
							// ///////////////////////////////////////////////////////////////
							// Get Account Bond Information
							// ///////////////////////////////////////////////////////////////
							AcctBondBO acctBondBO = new AcctBondBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
							where(AcctBondBO.ACCTNUM, accountBO.getAcctNum()).
							setUseConnection(conn).
							find();
							
							if ("TR".equals(acctBondBO.getDispCode())){
								details = "Bond Transfer";
							}
							
							acctBondBO = null;
						}
						if ("T".equals(accountBO.getAcctType())){
							// ///////////////////////////////////////////////////////////////
							// Get Account Trust Information
							// ///////////////////////////////////////////////////////////////
							AcctTrustBO acctTrustBO = new AcctTrustBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
							where(AcctTrustBO.ACCTNUM, accountBO.getAcctNum()).
							setUseConnection(conn).
							find();
							
							// ///////////////////////////////////////////////////////////////
							// Get Trust Type Information
							// ///////////////////////////////////////////////////////////////
							TrustTypeBO trustTypeBO = new TrustTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
							where(TrustTypeBO.TRUSTCODE, acctTrustBO.getTrustCode()).
							setUseConnection(conn).
							find();
							
							details = trustTypeBO.getDescr();
							trustTypeBO = null;
							acctTrustBO = null;
						}
						
						if ("I".equals(accountBO.getAcctType())){
							details = "Fine";
						}	
						
						if ("I".equals(accountBO.getAcctType()) || "F".equals(accountBO.getAcctType())){
							
							// ///////////////////////////////////////////////////////////////
							// Sum
							// ///////////////////////////////////////////////////////////////
							FieldOperationDescriptor sum = new FieldOperationDescriptor(TransDistBO.AMTPAID, FieldOperationType.SUM, new TypeBigDecimal());
							TransDistBO transDistSumBO = new TransDistBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
							.setFieldOperations(sum)
							.where(TransDistBO.INTJOURNALNUM, transDistBO.getIntJournalNum()) 
							.where(TransDistBO.TRANSNUM, transDistBO.getTransNum()) 
							.where(TransDistBO.ACCTNUM, accountBO.getAcctNum())
							.groupBy(TransDistBO.INTJOURNALNUM, TransDistBO.TRANSNUM, TransDistBO.ACCTNUM)
							.setUseConnection(conn)
							.find(TransDistBO.INTJOURNALNUM, TransDistBO.TRANSNUM, TransDistBO.ACCTNUM);
							
							amtPaid = (BigDecimal) transDistSumBO.get(sum);
							
							transDistSumBO = null;
							sum = null;
							
							// ///////////////////////////////////////////////////////////////
							// Credit
							// ///////////////////////////////////////////////////////////////
							FieldOperationDescriptor credit = new FieldOperationDescriptor(TransDistBO.AMTCREDIT, FieldOperationType.SUM, new TypeBigDecimal());
							TransDistBO transDistCreditBO = new TransDistBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
							.setFieldOperations(credit)
							.where(TransDistBO.INTJOURNALNUM, transDistBO.getIntJournalNum()) 
							.where(TransDistBO.TRANSNUM, transDistBO.getTransNum()) 
							.where(TransDistBO.ACCTNUM, accountBO.getAcctNum())
							.groupBy(TransDistBO.INTJOURNALNUM, TransDistBO.TRANSNUM, TransDistBO.ACCTNUM)
							.setUseConnection(conn)
							.find(TransDistBO.INTJOURNALNUM, TransDistBO.TRANSNUM, TransDistBO.ACCTNUM);
		
							amtCredit = (BigDecimal) transDistCreditBO.get(credit);
							if (amtCredit == null){
								amtCredit = new BigDecimal(0);
							}
		
		
							transDistCreditBO = null;
							credit = null;
							
						}
						if ("F".equals(accountBO.getAcctType())){
							// ///////////////////////////////////////////////////////////////
							// Get Account Fee
							// ///////////////////////////////////////////////////////////////
							AcctFeeBO acctFeeBO = new AcctFeeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
							where(AcctFeeBO.ACCTNUM, accountBO.getAcctNum()).
							setUseConnection(conn).
							find();
							
							// ///////////////////////////////////////////////////////////////
							// Get Fee Type Description
							// ///////////////////////////////////////////////////////////////
							FeeTypeBO feeTypeBO = new FeeTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
							.where(FeeTypeBO.FEECODE, acctFeeBO.getFeeCode())
							.where(FeeTypeBO.LASTEFFECTDATE, acctFeeBO.getFeeEffectDate())
							.setUseConnection(conn)
							.find(FeeTypeBO.DESCR);
							
							details = feeTypeBO.getDescr();
							
							acctFeeBO = null;
							feeTypeBO = null;
						}			
						
						
						if (transBO != null) {
							
							if (TextUtil.isEmpty(transBO.getOutType())){
								longDetails = "Payment Received:";
								if ("T".equals(accountBO.getAcctType())){
									if ("Bail/Bond Refund".equals(details)){
										if ("TO".equals(transDistBO.getApplyCode())){
											
											TransDetlBO transDetlBO = new TransDetlBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
											where(TransDetlBO.INTJOURNALNUM, transBO.getIntJournalNum()).
											where(TransDetlBO.TRANSNUM, transBO.getTransNum()).
											setUseConnection(conn).
											find();
			
											if ("CC".equals(transDetlBO.getTenderType())){
												longDetails = "Credit Card Refund:";
											}
											transDetlBO = null;
										}
									}
								}
							}
							
							if ("R".equals(transBO.getOutType())){
								// ///////////////////////////////////////////////////////////////
								// Trans Reverse Information
								// ///////////////////////////////////////////////////////////////
								TransRevrsBO transRevrsBO = new TransRevrsBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
								where(TransRevrsBO.INTJOURNALNUM, transBO.getIntJournalNum()).
								where(TransRevrsBO.TRANSNUM, transBO.getTransNum()).
								setUseConnection(conn).
								setReturnNull(true).
								find();
			
								if (transRevrsBO != null){
									// ///////////////////////////////////////////////////////////////
									// CheckDetlBO Information
									// ///////////////////////////////////////////////////////////////
									CheckDetlBO checkDetlBO = new CheckDetlBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
									where(CheckDetlBO.INTJOURNALNUM, transRevrsBO.getIntJournalNum()).
									where(CheckDetlBO.TRANSNUM, transRevrsBO.getTransNum()).
									setUseConnection(conn).
									setReturnNull(true).
									find();
										
									if (checkDetlBO != null){
										longDetails = "Void of trust payout:";
									} else {
										longDetails = "Payment Reversal:";
									}
									checkDetlBO = null;
								}
								transRevrsBO = null;
							}
						
							if ("N".equals(transBO.getOutType())){
								longDetails = "NSF Check Reversal:";
							} else if ("F".equals(transBO.getOutType())){
								longDetails = "Forfeited:";
							} else if ("O".equals(transBO.getOutType())){
								longDetails = "Trust Payment:";
							} else if ("B".equals(transBO.getOutType())){
								longDetails = "Disputed Credit Card:";
							} else if ("D".equals(transBO.getOutType())){
								longDetails = "Non-Monetary Bond:";
							}else if ("A".equals(transBO.getOutType())){
								longDetails = "Aborted Transaction:";
							}else if ("C".equals(transBO.getOutType())){
								longDetails = "Cancelled Transaction:";
							}else if ("E".equals(transBO.getOutType())){
								longDetails = "Endorsed Out:";
							}else if ("T".equals(transBO.getOutType())){
								if ("TO".equals(transDistBO.getApplyCode())){
									longDetails = "Transfer Out:";
								} else{
									if(accountBO.getAmtPaid().compareTo(BigDecimal.ZERO) > 0){
										longDetails = "Transfer In:";
									} else {
										longDetails = "Transfer Out:";
									}
								}
							}else if ("X".equals(transBO.getOutType())){
								longDetails = "Redistribution:";
							} 
						
							if (transDistBO != null){
								if (transDistBO.getAmtPaid().compareTo(BigDecimal.ZERO) < 0){
									if (!"R".equals(transBO.getOutType())){
										if (!"I".equals(accountBO.getAcctType())){
											if (!"F".equals(accountBO.getAcctType())){
												longDetails = "";
											}
										}	
									}
								}
							}
							
							if ("O".equals(transBO.getOutType())){
								// ///////////////////////////////////////////////////////////////
								// CheckDetlBO Information
								// ///////////////////////////////////////////////////////////////
								CheckDetlBO checkDetlBO = new CheckDetlBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
								where(CheckDetlBO.INTJOURNALNUM, transBO.getIntJournalNum()).
								where(CheckDetlBO.TRANSNUM, transBO.getTransNum()).
								setUseConnection(conn).
								find();
								
								lineDetails = details + " Check # " + checkDetlBO.getCheckNum() + " " + longDetails + " " + Constants.accountingDecimalFormatter.format(transDistBO.getAmtPaid());
								
								checkDetlBO = null;
								
							} else {
								if (TextUtil.isEmpty(details)){
									lineDetails = longDetails +  " " + Constants.accountingDecimalFormatter.format(transDistBO.getAmtPaid());
								}else {
									lineDetails = details + " " + longDetails +  " " + Constants.accountingDecimalFormatter.format(transDistBO.getAmtPaid());
								}
								
							}
						} else {
							if (TextUtil.isEmpty(details)){
								lineDetails = longDetails +  " " + Constants.accountingDecimalFormatter.format(transDistBO.getAmtPaid());
							}else {
								lineDetails = details + " " + longDetails +  " " + Constants.accountingDecimalFormatter.format(transDistBO.getAmtPaid());
							}
							
						}
	
						
						if ("I".equals(accountBO.getAcctType()) || "F".equals(accountBO.getAcctType())){
							if (prevAcctNum == transDistBO.getAcctNum()){
								continue;
							} 
								
							if (amtPaid.compareTo(BigDecimal.ZERO) > 0){
								
								CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(table, details + " " + longDetails +  " " + Constants.accountingDecimalFormatter.format(amtPaid) , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								
								// ///////////////////////////////////////////////////////////////
								// Logname
								// ///////////////////////////////////////////////////////////////
								if (inquiryUser){
									CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								} else {
									CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(transBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								}
	
							}
							if (amtCredit.compareTo(BigDecimal.ZERO) > 0){
								if (amtPaid.compareTo(BigDecimal.ZERO) == 0){
									CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
									CorisCaseHistoryCommon.addCell(table, details + " Credit Received: " + Constants.accountingDecimalFormatter.format(amtCredit) , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
									// ///////////////////////////////////////////////////////////////
									// Logname
									// ///////////////////////////////////////////////////////////////
									if (inquiryUser){
										CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
									} else {
										CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(transBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
									}
								} else {
									CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
									CorisCaseHistoryCommon.addCell(table, details + " Credit Received: " + Constants.accountingDecimalFormatter.format(amtCredit) , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
									CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								}
							}
							if (!TextUtil.isEmpty(transBO.getNote())){
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(table, "Note: " + transBO.getNote() , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							}
							
						} else {	
							
							
							if (!"E".equals(transBO.getOutType())){
								if (!"TO".equals(transBO.getOutType())){
									if (!"TE".equals(transBO.getOutType())){
										if (!"T".equals(transBO.getOutType())){
											CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
											CorisCaseHistoryCommon.addCell(table, lineDetails, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
											// ///////////////////////////////////////////////////////////////
											// Logname
											// ///////////////////////////////////////////////////////////////
											if (inquiryUser){
												CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
											} else {
												CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(transBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
											}
								
											if (amtCredit.compareTo(BigDecimal.ZERO) > 0){
												if ("X".equals(transBO.getOutType())){
													longDetails = "Credit Amt Adjusted:";						
												} else {
													longDetails = "Credit Received:";						
												}
												CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
												CorisCaseHistoryCommon.addCell(table, details + " " + longDetails +  " " + Constants.accountingDecimalFormatter.format(amtCredit) , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
												
												// ///////////////////////////////////////////////////////////////
												// Logname
												// ///////////////////////////////////////////////////////////////
												if (inquiryUser){
													CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
												} else {
													CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(transBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
												}
											}
											if (amtCredit.compareTo(BigDecimal.ZERO) < 0){
												if ("X".equals(transBO.getOutType())){
													longDetails = "Credit Amt Adjusted:";						
												} else {
													longDetails = "Credit Received:";						
												}
												CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
												CorisCaseHistoryCommon.addCell(table, details + " " + longDetails +  " " + Constants.accountingDecimalFormatter.format(amtCredit) , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
												
												// ///////////////////////////////////////////////////////////////
												// Logname
												// ///////////////////////////////////////////////////////////////
												if (inquiryUser){
													CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
												} else {
													CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(transBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
												}
												
											}
											if (amtCredit.compareTo(BigDecimal.ZERO) < 0){
												if ("X".equals(transBO.getOutType())){
													longDetails = "Credit Amt Adjusted:";						
												} else {
													longDetails = "Credit Received:";						
												}
												CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
												CorisCaseHistoryCommon.addCell(table, details + " " + longDetails +  " " + Constants.accountingDecimalFormatter.format(amtCredit) , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
												
												// ///////////////////////////////////////////////////////////////
												// Logname
												// ///////////////////////////////////////////////////////////////
												if (inquiryUser){
													CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
												} else {
													CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(transBO.getUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
												}
												
											}
										}
									}
								}
								
							}
							if (!TextUtil.isEmpty(transBO.getNote())){
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(table, "Note: " + transBO.getNote() , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							}
						}	
						prevAcctNum  = transDistBO.getAcctNum();
						details = "";
						longDetails = "";
						lineDetails = "";
						transDistBO = null;
						accountBO = null;
					}
					transBO = null;
					transDistListBO = null;
				}
			}
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			
		} catch (Exception e) {
			logger.error("Process generateCaseHistorySummaryEventAccount(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) ", e);
		}

		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventCaseDisp(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			// ///////////////////////////////////////////////////////////////
			// Summary Event Date Time
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			// ///////////////////////////////////////////////////////////////
			// Logname
			// ///////////////////////////////////////////////////////////////
			if (inquiryUser){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getKey1(), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			}
			
			PersonnelBO personnelBO = new PersonnelBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(PersonnelBO.USERIDSRL, corisCaseHistoryDTO.getKaseBO().getDispId()).
			setUseConnection(conn).
			setReturnNull(true).			
			find(PersonnelBO.TYPE);
			
			if (personnelBO != null){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				
				if ("J".equals(personnelBO.getType())){
					corisCaseHistoryDTO.setJudgeCommName(CorisCaseHistoryCommon.getJudgeCommName(corisCaseHistoryDTO.getKaseBO().getDispId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn));
					CorisCaseHistoryCommon.addCell(table, "Disposition Judge is " + corisCaseHistoryDTO.getJudgeCommName(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else if ("C".equals(personnelBO.getType())){
					corisCaseHistoryDTO.setJudgeCommName(CorisCaseHistoryCommon.getJudgeCommName(corisCaseHistoryDTO.getKaseBO().getDispId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn));
					CorisCaseHistoryCommon.addCell(table, "Disposition Commissioner is " + corisCaseHistoryDTO.getJudgeCommName(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				
			}
			
			personnelBO = null;
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			
		} catch (Exception e) {
			logger.error("generateCaseHistorySummaryEventCaseDisp(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO)", e);
		}
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventNoticeHeader(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
			// ///////////////////////////////////////////////////////////////
			// Case Me Document Data
			// ///////////////////////////////////////////////////////////////
			CaseMeDocumentBO caseMeDocumentBO = new CaseMeDocumentBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(CaseMeDocumentBO.MEID, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
			setUseConnection(conn).
			find();
			
			if ((caseMeDocumentBO == null) ||
				(!"D".equals(caseMeDocumentBO.getSignatureStatus()) &&
				!"R".equals(caseMeDocumentBO.getSignatureStatus()) &&
				!"F".equals(caseMeDocumentBO.getSignatureStatus()))){
				
				// ///////////////////////////////////////////////////////////////
				// Case Me Data
				// ///////////////////////////////////////////////////////////////
				CaseMeBO caseMeBO = new CaseMeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(CaseMeBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
				where(CaseMeBO.MEID, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
				setUseConnection(conn).
				find();
				
				// ///////////////////////////////////////////////////////////////
				// Summary Event Date Time
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				// ///////////////////////////////////////////////////////////////
				// Logname
				// ///////////////////////////////////////////////////////////////
				if (inquiryUser){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				} else {
					CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(caseMeBO.getClerkId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				}
			
				// ///////////////////////////////////////////////////////////////
				// Just in case we don't have enough cells
				// tell the table to "fill in the blanks"
				// ///////////////////////////////////////////////////////////////
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				
				caseMeBO = null;
				
			}
			caseMeDocumentBO = null;
		} catch (Exception e) {
			logger.error("generateCaseHistorySummaryEventNoticeHeader(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO)", e);
		}
		return table;
	}
	
	public PdfPTable generateCaseHistorySummaryEventNotice(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[1];
		widths[0] = 100.0f;

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			// ///////////////////////////////////////////////////////////////
			// Case Me Document Data
			// ///////////////////////////////////////////////////////////////
			CaseMeDocumentBO caseMeDocumentBO = new CaseMeDocumentBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(CaseMeDocumentBO.MEID, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
			setUseConnection(conn).
			find();
			
			if (caseMeDocumentBO == null){
				
			}
			
			if ((caseMeDocumentBO == null) ||
				(!"D".equals(caseMeDocumentBO.getSignatureStatus()) &&
				!"R".equals(caseMeDocumentBO.getSignatureStatus()) &&
				!"F".equals(caseMeDocumentBO.getSignatureStatus()))){
				
				// ///////////////////////////////////////////////////////////////
				// Case Me Data
				// ///////////////////////////////////////////////////////////////
				CaseMeBO caseMeBO = new CaseMeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(CaseMeBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
				where(CaseMeBO.MEID, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
				setUseConnection(conn).
				find();
				
				// ///////////////////////////////////////////////////////////////
				// Summary Event Date Time
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "Note: " + summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				// ///////////////////////////////////////////////////////////////
				// Logname
				// ///////////////////////////////////////////////////////////////
				if (inquiryUser){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				} else {
					CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(caseMeBO.getClerkId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				}
			
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				
				// ///////////////////////////////////////////////////////////////
				// get and print the case me value data data
				// ///////////////////////////////////////////////////////////////
				List<CaseMeDataDTO> caseMeDataListDTO = CorisCaseMeValueCommon.getCaseMeValueDataSP(Integer.valueOf(summaryEventCaseHistoryDTO.getKey1()), corisCaseHistoryDTO.getKaseBO(), conn);
				caseMeDataListDTO = CorisCaseMeValueCommon.caseMeValueDataRemoveHeaders(caseMeDataListDTO);
				List<PrintCaseMeDataDTO> printCaseMeDataListBO = CorisCaseMeValueCommon.getPrintTextData(caseMeDataListDTO);
				table = printCaseMeValueData(printCaseMeDataListBO);
				caseMeDataListDTO = null;
				printCaseMeDataListBO = null;
				
				// ///////////////////////////////////////////////////////////////
				// Just in case we don't have enough cells
				// tell the table to "fill in the blanks"
				// ///////////////////////////////////////////////////////////////
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				
				caseMeBO = null;
			}
			caseMeDocumentBO = null;
		} catch (Exception e) {
			logger.error("generateCaseHistorySummaryEventNotice(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO)", e);
		}

		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventTransfered(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("Process Set Table Widths", e);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Date Time
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		// ///////////////////////////////////////////////////////////////
		// Logname
		// ///////////////////////////////////////////////////////////////
		if (inquiryUser){
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		} else {
			CorisCaseHistoryCommon.addCell(table, "TRANSFERRED", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventConsolidated(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("Process Set Table Widths", e);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Date Time
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		// ///////////////////////////////////////////////////////////////
		// Logname
		// ///////////////////////////////////////////////////////////////
		if (inquiryUser){
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		} else {
			CorisCaseHistoryCommon.addCell(table, "CONSOLIDATE", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		return table;
	}
	
	public PdfPTable generateCaseHistorySummaryEventMinuteHeader(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			// ///////////////////////////////////////////////////////////////
			// Case Me Document Data
			// ///////////////////////////////////////////////////////////////
			CaseMeDocumentBO caseMeDocumentBO = new CaseMeDocumentBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(CaseMeDocumentBO.MEID, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
			setReturnNull(true).
			setUseConnection(conn).
			find(CaseMeDocumentBO.SIGNATURESTATUS);
			
			// ///////////////////////////////////////////////////////////////
			// Case Me Data
			// ///////////////////////////////////////////////////////////////
			CaseMeBO caseMeBO = new CaseMeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(CaseMeBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
			where(CaseMeBO.MEID, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
			setUseConnection(conn).
			find();
			
			/////////////////////////////////////////////////////
			// Minute Access
			/////////////////////////////////////////////////////
			boolean minuteAccess = false;
			if ("U".equals(caseMeBO.getMeSecurity())){
				minuteAccess = true;
			} else if ("V".equals(caseMeBO.getMeSecurity())){
				minuteAccess = privateProtectedAccess;
			} else if ("S".equals(caseMeBO.getMeSecurity())){
				minuteAccess = sealedAccess;
			} else if ("X".equals(caseMeBO.getMeSecurity())){
				minuteAccess = expungedAccess;
			} else if ("O".equals(caseMeBO.getMeSecurity())){
				minuteAccess = privateProtectedAccess;
			}else if("IC".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) || "IS".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
				minuteAccess = mentalAccess;
			}
			/////////////////////////////////////////////////////
			// Case Access Trumps Minute Access
			/////////////////////////////////////////////////////
			if (minuteAccess){
				minuteAccess = caseAccess;
			}
			
			/////////////////////////////////////////////////////
			// Inquiry User and minutes not ready
			/////////////////////////////////////////////////////
			if (inquiryUser){
				if ("D".equals(caseMeDocumentBO.getSignatureStatus()) ||
					"R".equals(caseMeDocumentBO.getSignatureStatus()) ||
					"F".equals(caseMeDocumentBO.getSignatureStatus())){
						minuteAccess = false;
				}
			} else {
				minuteAccess = true;
			}
			
			if (minuteAccess){
				CaseMeValueBO caseMeValueBO = new CaseMeValueBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(CaseMeValueBO.MEID, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
				where(CaseMeValueBO.MESCREENID,"RULINGS").
				setReturnNull(true).
				setUseConnection(conn).
				find();

				if (caseMeValueBO == null){
					summaryEventCaseHistoryDTO.setDescr("Minute Entry - " + summaryEventCaseHistoryDTO.getDescr());
				} else {
					summaryEventCaseHistoryDTO.setDescr("Ruling Entry - " + summaryEventCaseHistoryDTO.getDescr());
				}
				
				caseMeValueBO = null;
				
				// ///////////////////////////////////////////////////////////////
				// Summary Event Date Time
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				if (!"U".equals(caseMeBO.getMeSecurity())){
					// ///////////////////////////////////////////////////////////////
					// Get Security Type Data
					// ///////////////////////////////////////////////////////////////
					SecurityTypeBO securityTypeBO = CorisSecurityCommon.getSecurityType(caseMeBO.getMeSecurity(), corisCaseHistoryDTO.getKaseBO().getCourtType(), conn);
					
					// ///////////////////////////////////////////////////////////////
					// Set Document Title
					// ///////////////////////////////////////////////////////////////
					summaryEventCaseHistoryDTO.setDescr("**** " + securityTypeBO.getDescr() + " **** " + summaryEventCaseHistoryDTO.getDescr());
					securityTypeBO = null;
				}
	
				// ///////////////////////////////////////////////////////////////
				// Case Me  Title
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				
				// ///////////////////////////////////////////////////////////////
				// Logname
				// ///////////////////////////////////////////////////////////////
				if (inquiryUser){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				} else {
					CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(caseMeBO.getClerkId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				}
					
				// ///////////////////////////////////////////////////////////////
				// Just in case we don't have enough cells
				// tell the table to "fill in the blanks"
				// ///////////////////////////////////////////////////////////////
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				caseMeBO = null;
				
			}
			caseMeDocumentBO = null;
			caseMeBO = null;
			
		} catch (Exception e) {
			logger.error("generateCaseHistorySummaryEventMinuteHeader(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO)", e);
		}
			
		return table;
	}
	
	public PdfPTable generateCaseHistorySummaryEventMinute(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[1];
		widths[0] = 100.0f;

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			// ///////////////////////////////////////////////////////////////
			// Case Me Document Data
			// ///////////////////////////////////////////////////////////////
			CaseMeDocumentBO caseMeDocumentBO = new CaseMeDocumentBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(CaseMeDocumentBO.MEID, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
			setUseConnection(conn).
			find(CaseMeDocumentBO.SIGNATURESTATUS);
			
			// ///////////////////////////////////////////////////////////////
			// Case Me Data
			// ///////////////////////////////////////////////////////////////
			CaseMeBO caseMeBO = new CaseMeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(CaseMeBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
			where(CaseMeBO.MEID, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
			setUseConnection(conn).
			find(CaseMeBO.MESECURITY);
			
			/////////////////////////////////////////////////////
			// Minute Access
			/////////////////////////////////////////////////////
			boolean minuteAccess = false;
			if ("U".equals(caseMeBO.getMeSecurity())){
				minuteAccess = true;
			} else if ("V".equals(caseMeBO.getMeSecurity())){
				minuteAccess = privateProtectedAccess;
			} else if ("S".equals(caseMeBO.getMeSecurity())){
				minuteAccess = sealedAccess;
			} else if ("X".equals(caseMeBO.getMeSecurity())){
				minuteAccess = expungedAccess;
			} else if ("O".equals(caseMeBO.getMeSecurity())){
				minuteAccess = privateProtectedAccess;
			}else if("IC".equals(corisCaseHistoryDTO.getKaseBO().getCaseType()) || "IS".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
				minuteAccess = mentalAccess;
			}
			/////////////////////////////////////////////////////
			// Case Access Trumps Minute Access
			/////////////////////////////////////////////////////
			if (minuteAccess){
				minuteAccess = caseAccess;
			}
			
			/////////////////////////////////////////////////////
			// Inquiry User and minutes not ready
			/////////////////////////////////////////////////////
			if (inquiryUser){
				if ("D".equals(caseMeDocumentBO.getSignatureStatus()) ||
					"R".equals(caseMeDocumentBO.getSignatureStatus()) ||
					"F".equals(caseMeDocumentBO.getSignatureStatus())){
						minuteAccess = false;
				}
			}
			
			if (minuteAccess){
				// ///////////////////////////////////////////////////////////////
				// get and print the case me value data data
				// ///////////////////////////////////////////////////////////////
				List<CaseMeDataDTO> caseMeDataListDTO = CorisCaseMeValueCommon.getCaseMeValueDataSP(Integer.valueOf(summaryEventCaseHistoryDTO.getKey1()), corisCaseHistoryDTO.getKaseBO(), conn);
				List<CaseMeDataDTO> removedOutPutTextListBO = CorisCaseMeValueCommon.caseMeValueDataRemoveHeaders(caseMeDataListDTO);
				List<PrintCaseMeDataDTO> printCaseMeDataListBO = CorisCaseMeValueCommon.getPrintTextData(removedOutPutTextListBO);
				table = printCaseMeValueData(printCaseMeDataListBO);
				caseMeDataListDTO = null;
				removedOutPutTextListBO = null;
				printCaseMeDataListBO = null;
				
				
				// ///////////////////////////////////////////////////////////////
				// Just in case we don't have enough cells
				// tell the table to "fill in the blanks"
				// ///////////////////////////////////////////////////////////////
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				
			}
			caseMeBO = null;
			caseMeDocumentBO = null;
		} catch (Exception e) {
			logger.error("generateCaseHistorySummaryEventMinute(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO)", e);
		}

		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventMediation(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		table.setWidths(widths);
		
		try {
			// ///////////////////////////////////////////////////////////////
			// Get Mediation Data
			// ///////////////////////////////////////////////////////////////
			MediationBO mediationBO = new MediationBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(MediationBO.INTCASENUM, MediationBO.MEDIATIONNUM).
			setIntCaseNum(summaryEventCaseHistoryDTO.getIntCaseNum()).
			setMediationNum(Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
			setUseConnection(conn).
			find(MediationOutcomeBO.TYPE);
			
			// ///////////////////////////////////////////////////////////////
			// Get Mediation Data
			// ///////////////////////////////////////////////////////////////
			MediationOutcomeBO mediationOutcomeBO = new MediationOutcomeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(MediationOutcomeBO.TYPE).
			setType(mediationBO.getMediationOutcome()).
			setUseConnection(conn).
			find(MediationBO.MEDIATORID);
			
			// ///////////////////////////////////////////////////////////////
			// Get Mediator Data
			// ///////////////////////////////////////////////////////////////
			MediatorBO mediatorBO = new MediatorBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(MediatorBO.MEDIATORSRL).
			setMediatorSrl(mediationBO.getMediatorId()).
			setUseConnection(conn).
			find();
			
			
			// ///////////////////////////////////////////////////////////////
			// Summary Event Date Time
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			// ///////////////////////////////////////////////////////////////
			// Logname
			// ///////////////////////////////////////////////////////////////
			if (inquiryUser){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(mediationBO.getClerkId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			}
			
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			if (mediationBO.getReferralDate() != null){
				CorisCaseHistoryCommon.addCell(table, "Referral Date      : " + Constants.dateFormat.format(mediationBO.getReferralDate()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, "Referral Date      : ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
	
			if (mediationBO.getJudgeCommId() == 0){
				CorisCaseHistoryCommon.addCell(table, "Judge/Commissioner : ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, "Judge/Commissioner : " + CorisCaseHistoryCommon.getJudgeCommName(mediationBO.getJudgeCommId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			if (mediationBO.getCompletionDate() != null){
				CorisCaseHistoryCommon.addCell(table, "Completion Date    : " + Constants.dateFormat.format(mediationBO.getCompletionDate()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, "Completion Date    : ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			if ("".equals(mediatorBO.getLastName()) || mediatorBO.getLastName() == null){
				CorisCaseHistoryCommon.addCell(table, "Mediator           : ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, "Mediator           : " + mediatorBO.getFirstName() + " " + mediatorBO.getLastName(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			if ("".equals(mediationOutcomeBO.getDescr()) || mediationOutcomeBO.getDescr() == null){
				CorisCaseHistoryCommon.addCell(table, "Mediator Outcome   : ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, "Mediator Outcome   : " + mediationOutcomeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			
			mediationBO = null;
			mediationOutcomeBO = null; 
			mediatorBO = null;
		} catch (Exception e) {
			logger.error("Process generateCaseHistorySummaryEventMediation(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO)", e);
		}
				
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventCharge(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("Process Set Table Widths", e);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Date Time
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		// ///////////////////////////////////////////////////////////////
		// Logname
		// ///////////////////////////////////////////////////////////////
		if (inquiryUser){
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		} else {
			if (TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey1())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(Integer.valueOf(summaryEventCaseHistoryDTO.getKey1()), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			}
		}
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventAmndInfo(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			// ///////////////////////////////////////////////////////////////
			// Amend Information
			// ///////////////////////////////////////////////////////////////
			AmendInfoBO amendInfoBO = new AmendInfoBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(AmendInfoBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
			where(AmendInfoBO.PARTYNUM, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
			where(AmendInfoBO.PARTYCODE, summaryEventCaseHistoryDTO.getKey2()).
			where(AmendInfoBO.SEQ, Integer.valueOf(summaryEventCaseHistoryDTO.getKey4())).
			where(AmendInfoBO.INFONUM, Integer.valueOf(summaryEventCaseHistoryDTO.getKey3())).
			setUseConnection(conn).
			find(AmendInfoBO.VIOLCODE, AmendInfoBO.SEVERITY);
			
			// ///////////////////////////////////////////////////////////////
			// Charge Information
			// ///////////////////////////////////////////////////////////////
			ChargeBO chargeBO = new ChargeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(ChargeBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
			where(ChargeBO.SEQ, Integer.valueOf(summaryEventCaseHistoryDTO.getKey4())).
			setUseConnection(conn).
			find(ChargeBO.SEVERITY, ChargeBO.VIOLCODE);
			
			// ///////////////////////////////////////////////////////////////
			// Summary Event Date Time
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			if ("".equals(amendInfoBO.getViolCode()) || amendInfoBO.getViolCode() == null){
				CorisCaseHistoryCommon.addCell(table, "Charge " + amendInfoBO.getViolCode() + " Severity " + amendInfoBO.getSeverity() + " was removed", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, "Charge " + amendInfoBO.getViolCode() + " Severity " + amendInfoBO.getSeverity() + " was amended to " + chargeBO.getViolCode() + " severity " + chargeBO.getSeverity(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			// ///////////////////////////////////////////////////////////////
			// Logname
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
		
			amendInfoBO = null;
			chargeBO = null;
		
		} catch (Exception e) {
			logger.error("Process generateCaseHistorySummaryEventAmndInfo(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) ", e);
		}
		return table;
	}
	
	public PdfPTable generateCaseHistorySummaryEventJudgment(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
			if (inquiryUser){
				// ///////////////////////////////////////////////////////////////
				// Summary Event Date Time
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				// ///////////////////////////////////////////////////////////////
				// Summary Description
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				// ///////////////////////////////////////////////////////////////
				// Logname
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			} else {
				// ///////////////////////////////////////////////////////////////
				// CaseMeJudgment
				// ///////////////////////////////////////////////////////////////
//				CaseMeJudgmentBO caseMeJudgementBO = new CaseMeJudgmentBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
//				where(CaseMeJudgmentBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
//				where(CaseMeJudgmentBO.JDMTSEQ, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
//				setUseConnection(conn).
//				find();
				
				// ///////////////////////////////////////////////////////////////
				// Case Me Document Data
				// ///////////////////////////////////////////////////////////////
				CaseMeDocumentBO caseMeDocumentBO = new CaseMeDocumentBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(CaseMeDocumentBO.MEID, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
				setUseConnection(conn).
				find();
			
				if ((caseMeDocumentBO == null) ||
					(!"D".equals(caseMeDocumentBO.getSignatureStatus()) &&
					!"R".equals(caseMeDocumentBO.getSignatureStatus()) &&
					!"F".equals(caseMeDocumentBO.getSignatureStatus()))){
					
					// ///////////////////////////////////////////////////////////////
					// Judgment Data
					// ///////////////////////////////////////////////////////////////
					JudgmentBO judgmentBO = new JudgmentBO(corisCaseHistoryDTO.getKaseBO().getCourtType())
					.where(JudgmentBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()) 
					.where(JudgmentBO.JDMTSEQ, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1()))
					.setUseConnection(conn)
					.setReturnNull(true)			
					.find();
					
					if (judgmentBO != null){
						boolean samedate = CorisCaseHistoryCommon.isSameDay(judgmentBO.getLastUpdDate(), summaryEventCaseHistoryDTO.getEventDatetime());  
						
						if (samedate){
							
							StringBuilder stringBuilder = new StringBuilder();
							
							if (summaryEventCaseHistoryDTO.getDescr().contains("Judgment")){
								if (!"SL".equals(corisCaseHistoryDTO.getKaseBO().getCaseType())){
									stringBuilder.append("Judgment # ");
									stringBuilder.append(String.valueOf(judgmentBO.getJdmtSeq()));
									stringBuilder.append(" ");
									if (summaryEventCaseHistoryDTO.getDescr().contains("Entered")){
										stringBuilder.append("Entered ");
									} else {
										stringBuilder.append("Modified ");
									}
												
									stringBuilder.append(Constants.accountingDecimalFormatter.format(judgmentBO.getJdmtAmt()));
									stringBuilder.append(" ");
									
									if (judgmentBO.getDispDate() != null){
										stringBuilder.append("Disposition: ");
										stringBuilder.append(Constants.longTextFormat.format(judgmentBO.getDispDate()));
									}
								} else {
									stringBuilder.append(summaryEventCaseHistoryDTO.getDescr()); 
								}
								
								String finalString = stringBuilder.toString();
								
								// ///////////////////////////////////////////////////////////////
								// Summary Event Date Time
								// ///////////////////////////////////////////////////////////////
								CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								// ///////////////////////////////////////////////////////////////
								// Summary Description
								// ///////////////////////////////////////////////////////////////
								CorisCaseHistoryCommon.addCell(table, finalString, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								// ///////////////////////////////////////////////////////////////
								// Logname
								// ///////////////////////////////////////////////////////////////
								if (!TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey4())){
									CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(Integer.valueOf(summaryEventCaseHistoryDTO.getKey4()), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								} else {
									CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								}
								
								// ///////////////////////////////////////////////////////////////
								// Note
								// ///////////////////////////////////////////////////////////////
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(table, judgmentBO.getNote(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								
								
								
								// ///////////////////////////////////////////////////////////////
								// Get Max Detail Sequence
								// ///////////////////////////////////////////////////////////////
								FieldOperationDescriptor maxDetlSeq = new FieldOperationDescriptor(JudgmentDetlBO.DETLSEQ, FieldOperationType.MAX, new TypeInteger());
								JudgmentDetlBO maxJudgmentDetlBO = new JudgmentDetlBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
								setFieldOperations(maxDetlSeq).
								where(JudgmentDetlBO.INTCASENUM, judgmentBO.getIntCaseNum()).
								where(JudgmentDetlBO.JDMTSEQ, judgmentBO.getJdmtSeq()).
								groupBy(JudgmentDetlBO.DETLSEQ).
								setUseConnection(conn).
								find(JudgmentDetlBO.DETLSEQ);
								
								int maxJudgmentDetlCount = 0;

								maxJudgmentDetlCount = (Integer) maxJudgmentDetlBO.get(maxDetlSeq);
								maxJudgmentDetlBO = null;
								
								BigDecimal jdmtTotalAmt = new BigDecimal(0);
								
								for (int deltSeq = 1; deltSeq <= maxJudgmentDetlCount; deltSeq++){
									// ///////////////////////////////////////////////////////////////
									// Judgment Creditor
									// ///////////////////////////////////////////////////////////////
									List<JudgmentCreditorBO> judgmentCreditorListBO = new JudgmentCreditorBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
									where(JudgmentCreditorBO.INTCASENUM, judgmentBO.getIntCaseNum()).
									where(JudgmentCreditorBO.JDMTSEQ, judgmentBO.getJdmtSeq()).
									where(JudgmentCreditorBO.DETLSEQ, deltSeq).
									setUseConnection(conn).
									search();
									
									if (judgmentCreditorListBO.size() > 0 ){
									
										for (JudgmentCreditorBO judgmentCreditorBO :judgmentCreditorListBO){
											// ///////////////////////////////////////////////////////////////
											// Party Creditor
											// ///////////////////////////////////////////////////////////////
											PartyBO creditorPartyBO = CorisCaseHistoryCommon.getPartyByPartyNum(corisCaseHistoryDTO, judgmentCreditorBO.getPartyNum(), partyRepository, conn);
											
											// ///////////////////////////////////////////////////////////////
											// Creditor Table
											// ///////////////////////////////////////////////////////////////
											CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
											if (!TextUtil.isEmpty(creditorPartyBO.getFirstName())){
												CorisCaseHistoryCommon.addCell(table, "Creditor  : " + creditorPartyBO.getFirstName() + " " + creditorPartyBO.getLastName(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
											} else{
												CorisCaseHistoryCommon.addCell(table, "Creditor  : " + creditorPartyBO.getLastName(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
											}
											CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
											creditorPartyBO = null;
											judgmentCreditorBO = null;
										}
										
									}
									judgmentCreditorListBO = null;
									
									// ///////////////////////////////////////////////////////////////
									// Judgment Debtor
									// ///////////////////////////////////////////////////////////////
									List<JudgmentDebtorBO> judgmentDebtorListBO = new JudgmentDebtorBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
									where(JudgmentDebtorBO.INTCASENUM, judgmentBO.getIntCaseNum()).
									where(JudgmentDebtorBO.JDMTSEQ, judgmentBO.getJdmtSeq()).
									where(JudgmentDebtorBO.DETLSEQ, deltSeq).
									setUseConnection(conn).
									search();
	
									if (judgmentDebtorListBO.size() > 0){
										
										for (JudgmentDebtorBO judgmentDebtorBO :judgmentDebtorListBO){
											// ///////////////////////////////////////////////////////////////
											// Party Debtor
											// ///////////////////////////////////////////////////////////////
											PartyBO debtorPartyBO = CorisCaseHistoryCommon.getPartyByPartyNum(corisCaseHistoryDTO, judgmentDebtorBO.getPartyNum(), partyRepository, conn);
											
											// ///////////////////////////////////////////////////////////////
											// Table Debtor
											// ///////////////////////////////////////////////////////////////
											CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
											if (!TextUtil.isEmpty(debtorPartyBO.getFirstName())){
												CorisCaseHistoryCommon.addCell(table, "Debtor    : " + debtorPartyBO.getFirstName() + " " + debtorPartyBO.getLastName(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
											} else{
												CorisCaseHistoryCommon.addCell(table, "Debtor    : " + debtorPartyBO.getLastName(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
											}
											CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
											debtorPartyBO = null;
											judgmentDebtorBO = null;
										}
									}
									judgmentDebtorListBO = null;
									
									// ///////////////////////////////////////////////////////////////
									// Judgment Detail Data
									// ///////////////////////////////////////////////////////////////
									List<JudgmentDetlBO> judgmentDetlListBO = new JudgmentDetlBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
									where(JudgmentDetlBO.INTCASENUM, judgmentBO.getIntCaseNum()).
									where(JudgmentDetlBO.JDMTSEQ, judgmentBO.getJdmtSeq()).
									where(JudgmentDetlBO.DETLSEQ, deltSeq).
									setUseConnection(conn).
									search();
									
									for (JudgmentDetlBO judgmentDetlBO  :judgmentDetlListBO){
										
										jdmtTotalAmt = jdmtTotalAmt.add(judgmentDetlBO.getAmt());
										
										// ///////////////////////////////////////////////////////////////
										// Description
										// ///////////////////////////////////////////////////////////////
										CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
										CorisCaseHistoryCommon.addCell(table, Constants.accountingDecimalFormatter.format(judgmentDetlBO.getAmt()) + " " + judgmentDetlBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
										CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				
										judgmentDetlBO = null;
									}
									
									judgmentDetlListBO = null;									
									
								}	
								// ///////////////////////////////////////////////////////////////
								// Grand Total
								// ///////////////////////////////////////////////////////////////
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(table, Constants.accountingDecimalFormatter.format(jdmtTotalAmt) + " " + "Judgment Grand Total", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 3);
								
							} else {
								// ///////////////////////////////////////////////////////////////
								// Summary Event Date Time
								// ///////////////////////////////////////////////////////////////
								CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								// ///////////////////////////////////////////////////////////////
								// Summary Description
								// ///////////////////////////////////////////////////////////////
								CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
								// ///////////////////////////////////////////////////////////////
								// Logname
								// ///////////////////////////////////////////////////////////////
								if (!TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey4())){
									CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(Integer.valueOf(summaryEventCaseHistoryDTO.getKey4()), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								} else {
									CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
								}
							}
							judgmentBO = null;
						} else {
							// ///////////////////////////////////////////////////////////////
							// Summary Event Date Time
							// ///////////////////////////////////////////////////////////////
							CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							// ///////////////////////////////////////////////////////////////
							// Summary Description
							// ///////////////////////////////////////////////////////////////
							CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							// ///////////////////////////////////////////////////////////////
							// Logname
							// ///////////////////////////////////////////////////////////////
							if (!TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey4())){
								CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(Integer.valueOf(summaryEventCaseHistoryDTO.getKey4()), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							} else {
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							}							
						}
					}
				}
				
				// ///////////////////////////////////////////////////////////////
				// Just in case we don't have enough cells
				// tell the table to "fill in the blanks"
				// ///////////////////////////////////////////////////////////////
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				
				//caseMeJudgementBO = null;
				caseMeDocumentBO = null;
				
			}
		} catch (Exception e) {
			logger.error("generateCaseHistorySummaryEventJudgment(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO)", e);
		}
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventDismiss(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("Process Set Table Widths", e);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Date Time
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		// ///////////////////////////////////////////////////////////////
		// Logname
		// ///////////////////////////////////////////////////////////////
		if (inquiryUser){
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		} else {
			if (TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey4())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(Integer.valueOf(summaryEventCaseHistoryDTO.getKey4()), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			}
		}
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		return table;
	}
	
	
	public PdfPTable generateCaseHistorySummaryEventStay(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			// ///////////////////////////////////////////////////////////////
			// Stay VO
			// ///////////////////////////////////////////////////////////////
			StayBO stayBO = new StayBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(StayBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
			where(StayBO.SEQ, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
			setUseConnection(conn).
			find();
			
			// ///////////////////////////////////////////////////////////////
			// Stay Type VO
			// ///////////////////////////////////////////////////////////////
			StayTypeBO stayTypeBO = new StayTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(StayTypeBO.STAYCODE, stayBO.getStayCode()).
			setUseConnection(conn).
			find(StayTypeBO.DESCR);
			
			// ///////////////////////////////////////////////////////////////
			// Summary Event Date Time
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			if (stayBO.getEndDate() == null){
				CorisCaseHistoryCommon.addCell(table, "Stay Begins " + Constants.longTextFormat.format(stayBO.getBeginDate()) + " Reason: " + stayTypeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, "Stay Ends " + Constants.longTextFormat.format(stayBO.getBeginDate()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			// ///////////////////////////////////////////////////////////////
			// Logname
			// ///////////////////////////////////////////////////////////////
			if (inquiryUser){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getKey2(), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			}
			
			// ///////////////////////////////////////////////////////////////
			// Add Note
			// ///////////////////////////////////////////////////////////////
			if (stayBO.getEndDate() == null){
				if (!TextUtil.isEmpty(stayBO.getNoteText())){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, "Note: " + stayBO.getNoteText(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
			}
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			
			stayBO = null;
			stayTypeBO = null;
			
		} catch (Exception e) {
			logger.error("During generateCaseHistorySummaryEventStay(corisCaseHistoryDTO, summaryEventBO)", e);
		}
		
		widths = null;
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventNsf(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("Process Set Table Widths", e);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Date Time
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		// ///////////////////////////////////////////////////////////////
		// Logname
		// ///////////////////////////////////////////////////////////////
		if (inquiryUser){
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		} else {
			if (TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey4())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(Integer.valueOf(summaryEventCaseHistoryDTO.getKey4()), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			}
		}
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventWarrant(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			
			table.setWidths(widths);
			if ("".equals(summaryEventCaseHistoryDTO.getKey1()) || summaryEventCaseHistoryDTO.getKey1() == null){
				// ///////////////////////////////////////////////////////////////
				// Summary Event Date Time
				// ///////////////////////////////////////////////////////////////
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, "" + summaryEventCaseHistoryDTO.getChText(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				// ///////////////////////////////////////////////////////////////
				// Logname
				// ///////////////////////////////////////////////////////////////
				if (inquiryUser){
					CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				} else {
					CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(summaryEventCaseHistoryDTO.getClerkId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
				}
				
			} else {
			
				// ///////////////////////////////////////////////////////////////
				// Get Party Data
				// ///////////////////////////////////////////////////////////////
				PartyBO partyBO = CorisCaseHistoryCommon.getPartyByPartyCode(corisCaseHistoryDTO, "DEF", partyRepository, conn);
				
				// ///////////////////////////////////////////////////////////////
				// Get Warrant Data
				// ///////////////////////////////////////////////////////////////
				WarrantBO warrantBO = new WarrantBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
				where(WarrantBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
				where(WarrantBO.WARRNUM, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
				where(WarrantBO.PARTYNUM, partyBO.getPartyNum()).
				setUseConnection(conn).
				setReturnNull(true).
				find();

				if (warrantBO != null){
					
					// ///////////////////////////////////////////////////////////////
					// Summary Event Date Time
					// ///////////////////////////////////////////////////////////////
					CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					
					String warrantRemarks = "";
					
					if ("Warrant Issued".equals(summaryEventCaseHistoryDTO.getDescr())){
						warrantRemarks = "Warrant Issued on: " + Constants.longTextFormat.format(warrantBO.getIssueDate()) + " Warrant Num: " + warrantBO.getWarrNum();
					} else if ("Warrant Ordered".equals(summaryEventCaseHistoryDTO.getDescr())){
						warrantRemarks = "Warrant Ordered on: " + Constants.longTextFormat.format(warrantBO.getOrderDate()) + " Warrant Num: " + warrantBO.getWarrNum();
					} else if ("Automatic Warrant Ordered".equals(summaryEventCaseHistoryDTO.getDescr())){
						warrantRemarks = "Automatic Warrant Ordered on: " + Constants.longTextFormat.format(warrantBO.getOrderDate()) + " Warrant Num: " + warrantBO.getWarrNum();
					} else if ("Warrant Recalled".equals(summaryEventCaseHistoryDTO.getDescr())){
						warrantRemarks = "Warrant Recalled on : " + Constants.longTextFormat.format(warrantBO.getRecallDate()) + " Warrant Num: " + warrantBO.getWarrNum();
					} else if ("Warrant Cancelled".equals(summaryEventCaseHistoryDTO.getDescr())){
						warrantRemarks = "Warrant Cancelled on: " + Constants.longTextFormat.format(warrantBO.getRecallDate()) + " Warrant Num: " + warrantBO.getWarrNum();
					}
					
					if ("Y".equals(warrantBO.getBailFlag())){
						warrantRemarks += " Bail Allowed";
					} else if ("N".equals(warrantBO.getBailFlag())){
						warrantRemarks += " No Bail";
					} else if ("C".equals(warrantBO.getBailFlag())){
						warrantRemarks += " Cash Bail Only";
					}
					
					CorisCaseHistoryCommon.addCell(table, warrantRemarks, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					// ///////////////////////////////////////////////////////////////
					// Logname
					// ///////////////////////////////////////////////////////////////
					if (inquiryUser){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					} else {
						
						if ("Warrant Recalled".equals(summaryEventCaseHistoryDTO.getDescr())){
							CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(warrantBO.getRecallUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						} else if ("Warrant Ordered".equals(summaryEventCaseHistoryDTO.getDescr())){
							CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(warrantBO.getOrderUseridSrl(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						} else {
							CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(warrantBO.getIssueClerkId(), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						}
					}
					
					if ("Warrant Issued".equals(summaryEventCaseHistoryDTO.getDescr())||
					    "Warrant Ordered".equals(summaryEventCaseHistoryDTO.getDescr())){
						if (warrantBO.getBailAmt().doubleValue() > 0){
							CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, "Bail amount: " + Constants.accountingDecimalFormatter.format(warrantBO.getBailAmt()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						}
					}
					
					if ("Warrant Issued".equals(summaryEventCaseHistoryDTO.getDescr())){
						if (warrantBO.getIssueJudgeId() > 0){
							CorisCaseHistoryCommon.addCell(table, " " , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getTitleName(warrantBO.getIssueJudgeId(),corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, " " , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						}
					}
		
					if ("Warrant Issued".equals(summaryEventCaseHistoryDTO.getDescr())){
						if (!TextUtil.isEmpty(warrantBO.getIssueReasonCode())){
							WarrReasonTypeBO warrReasonTypeBO = new WarrReasonTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
							where(WarrReasonTypeBO.WARRREASONCODE, warrantBO.getIssueReasonCode()).
							where(WarrReasonTypeBO.LOCNCODE, corisCaseHistoryDTO.getKaseBO().getLocnCode()).
							where(WarrReasonTypeBO.COURTTYPE, corisCaseHistoryDTO.getKaseBO().getCourtType()).
							setUseConnection(conn).
							find();
							
							CorisCaseHistoryCommon.addCell(table, " " , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, "Issue Reason: " + warrReasonTypeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, " " , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							warrReasonTypeBO = null;
						}
					} else if ("Warrant Recalled".equals(summaryEventCaseHistoryDTO.getDescr())){
						if (!TextUtil.isEmpty(warrantBO.getRecallReasonCode())){
							WarrReasonTypeBO warrReasonTypeBO = new WarrReasonTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
							where(WarrReasonTypeBO.WARRREASONCODE, warrantBO.getRecallReasonCode()).
							where(WarrReasonTypeBO.LOCNCODE, corisCaseHistoryDTO.getKaseBO().getLocnCode()).
							where(WarrReasonTypeBO.COURTTYPE, corisCaseHistoryDTO.getKaseBO().getCourtType()).
							setUseConnection(conn).
							find();
							
							CorisCaseHistoryCommon.addCell(table, " " , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, "Recall Reason: " + warrReasonTypeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, " " , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							warrReasonTypeBO = null;
						}
					}else if ("Warrant Cancelled".equals(summaryEventCaseHistoryDTO.getDescr())){
						if (!TextUtil.isEmpty(warrantBO.getRecallReasonCode())){
							WarrReasonTypeBO warrReasonTypeBO = new WarrReasonTypeBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
							where(WarrReasonTypeBO.WARRREASONCODE, warrantBO.getRecallReasonCode()).
							where(WarrReasonTypeBO.LOCNCODE, corisCaseHistoryDTO.getKaseBO().getLocnCode()).
							where(WarrReasonTypeBO.COURTTYPE, corisCaseHistoryDTO.getKaseBO().getCourtType()).
							setUseConnection(conn).
							find();
							
							CorisCaseHistoryCommon.addCell(table, " " , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, "Cancel Reason: " + warrReasonTypeBO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, " " , font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							warrReasonTypeBO = null;
						}
					}
				}
				partyBO = null;
				warrantBO = null;
			}
		} catch (Exception e) {
			logger.error("generateCaseHistorySummaryEventWarrant(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO)", e);
		}

		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventCcdspntc(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("Process Set Table Widths", e);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Date Time
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		// ///////////////////////////////////////////////////////////////
		// Logname
		// ///////////////////////////////////////////////////////////////
		if (inquiryUser){
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		} else {
			if (TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey4())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(Integer.valueOf(summaryEventCaseHistoryDTO.getKey4()), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			}
		}
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventEvidence(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);

			// ///////////////////////////////////////////////////////////////
			// Get Evidence
			// ///////////////////////////////////////////////////////////////
			EvidenceBO evidenceBO = new EvidenceBO(corisCaseHistoryDTO.getKaseBO().getCourtType()).
			where(EvidenceBO.INTCASENUM, summaryEventCaseHistoryDTO.getIntCaseNum()).
			where(EvidenceBO.SEQ, Integer.valueOf(summaryEventCaseHistoryDTO.getKey1())).
			setUseConnection(conn).
			find();
			
			// ///////////////////////////////////////////////////////////////
			// Summary Event Date Time
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			// ///////////////////////////////////////////////////////////////
			// Data
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, "Received: " + Constants.longTextFormat.format(evidenceBO.getRecvdDate()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			// ///////////////////////////////////////////////////////////////
			// Logname
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			// ///////////////////////////////////////////////////////////////
			// Line 2
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			// ///////////////////////////////////////////////////////////////
			// Data
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, "Container: " + evidenceBO.getContainerDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			// ///////////////////////////////////////////////////////////////
			// Logname
			// ///////////////////////////////////////////////////////////////
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
		} catch (Exception e) {
			logger.error("Process generateCaseHistorySummaryEventEvidence(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO)", e);
		}
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventForfNotc(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("Process Set Table Widths", e);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Date Time
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		CorisCaseHistoryCommon.addCell(table, "Bail/Bond Forfeited Notice - " + summaryEventCaseHistoryDTO.getDescr(), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		// ///////////////////////////////////////////////////////////////
		// Logname
		// ///////////////////////////////////////////////////////////////
		if (inquiryUser){
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		} else {
			if (TextUtil.isEmpty(summaryEventCaseHistoryDTO.getKey1())){
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, CorisCaseHistoryCommon.getLogName(Integer.valueOf(summaryEventCaseHistoryDTO.getKey1()), corisCaseHistoryDTO.getKaseBO().getCourtType(), personnelRepository, conn), font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			}
		}
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		return table;
	}
	public PdfPTable generateCaseHistorySummaryEventFtaFtc(CorisCaseHistoryDTO corisCaseHistoryDTO, SummaryEventCaseHistoryDTO summaryEventCaseHistoryDTO) throws DocumentException, IOException{
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 14.0f;
		widths[1] = 72.0f;
		widths[2] = 14.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		} catch (Exception e) {
			logger.error("Process Set Table Widths", e);
		}
		
		// ///////////////////////////////////////////////////////////////
		// Summary Event Date Time
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, Constants.dateFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		CorisCaseHistoryCommon.addCell(table, summaryEventCaseHistoryDTO.getDescr() + " - " + Constants.longTextFormat.format(summaryEventCaseHistoryDTO.getEventDatetime()), font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
		// ///////////////////////////////////////////////////////////////
		// Logname
		// ///////////////////////////////////////////////////////////////
		CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
		
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();
		
		return table;
	}
	
	public byte[] denyCaseAccess(String caseNum, String locnCode, String courtType, String logName, HttpServletResponse response) throws DocumentException, IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[1];
		widths[0] = 100.0f;

		PdfPTable table = new PdfPTable(1);
		
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			// ///////////////////////////////////////////////////////////////
			// Create new document
			// ///////////////////////////////////////////////////////////////
			Document document = new Document(PageSize.LETTER);
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			
			reportMyHeaderFooter event = new reportMyHeaderFooter();
			writer.setPageEvent(event);
			
		    // ///////////////////////////////////////////////////////////////
			// Document Set Up - Left, Right, Top, Bottom
			// ///////////////////////////////////////////////////////////////
			document.setMargins(6f, 22f, 30f, 30f);
			document.open();
	
			// ///////////////////////////////////////////////////////////////////////////////////
			// For Page Total
			// ///////////////////////////////////////////////////////////////////////////////////
			PdfContentByte cb = writer.getDirectContent();
			template = cb.createTemplate(50, 20);
			
			// ///////////////////////////////////////////////////////////////////////////////////
			// Cell Set Up
			// ///////////////////////////////////////////////////////////////////////////////////
			PdfPCell caseHistoryCell = new PdfPCell();
			
			caseHistoryCell.setPaddingLeft(20f);
			caseHistoryCell.setBorder(PdfPCell.NO_BORDER);
			
			///////////////////////////////////////////////////////////////////////////////
			//Get Profile Information
			///////////////////////////////////////////////////////////////////////////////
			ProfileBO profileBO = new ProfileBO(courtType).
			where(ProfileBO.LOCNCODE, locnCode).
			setUseConnection(conn).
			find(); 
			
			///////////////////////////////////////////////////////////////////////////////
			//Get County Information
			///////////////////////////////////////////////////////////////////////////////
			CountyBO countyBO = new CountyBO(courtType).
			where(CountyBO.CNTYCODE, profileBO.getCntyCode()).
			find(CountyBO.NAME);
			
			CorisCaseHistoryCommon.addCell(table, profileBO.getCourtTitle(), font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, countyBO.getName() + " COUNTY, STATE OF UTAH", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, "Case Number " + caseNum, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.TOP, 2);
			
			CorisCaseHistoryCommon.addCell(table, "Access Denied - Insufficent Rights!", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			document.add(table);
			
			document.addTitle("Coris Case History - Case Number " + caseNum);
			closeDocument(document, writer);
			
			profileBO = null;
			countyBO = null;
			
		} catch (Exception e) {
			logger.error("denyCaseAccess(String caseNum, String locnCode, String courtType, String logName)", e);
		}
		
		return baos.toByteArray();

	}
	public byte[] caseNotFound(String caseNum, String locnCode, String courtType, String logName, HttpServletResponse response) throws DocumentException, IOException{
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[1];
		widths[0] = 100.0f;

		PdfPTable table = new PdfPTable(1);
		
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			// ///////////////////////////////////////////////////////////////
			// Create new document
			// ///////////////////////////////////////////////////////////////
			Document document = new Document(PageSize.LETTER);
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			
			reportMyHeaderFooter event = new reportMyHeaderFooter();
			writer.setPageEvent(event);
			
		    // ///////////////////////////////////////////////////////////////
			// Document Set Up - Left, Right, Top, Bottom
			// ///////////////////////////////////////////////////////////////
			document.setMargins(6f, 22f, 30f, 30f);
			document.open();
	
			// ///////////////////////////////////////////////////////////////////////////////////
			// For Page Total
			// ///////////////////////////////////////////////////////////////////////////////////
			PdfContentByte cb = writer.getDirectContent();
			template = cb.createTemplate(50, 20);
			
			// ///////////////////////////////////////////////////////////////////////////////////
			// Cell Set Up
			// ///////////////////////////////////////////////////////////////////////////////////
			PdfPCell caseHistoryCell = new PdfPCell();
			
			caseHistoryCell.setPaddingLeft(20f);
			caseHistoryCell.setBorder(PdfPCell.NO_BORDER);
			
			///////////////////////////////////////////////////////////////////////////////
			//Get Profile Information
			///////////////////////////////////////////////////////////////////////////////
			ProfileBO profileBO = new ProfileBO(courtType).
			where(ProfileBO.LOCNCODE, locnCode).
			setUseConnection(conn).
			find(); 
			
			///////////////////////////////////////////////////////////////////////////////
			//Get County Information
			///////////////////////////////////////////////////////////////////////////////
			CountyBO countyBO = new CountyBO(courtType).
			where(CountyBO.CNTYCODE, profileBO.getCntyCode()).
			find(CountyBO.NAME);
			
			CorisCaseHistoryCommon.addCell(table, profileBO.getCourtTitle(), font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, countyBO.getName() + " COUNTY, STATE OF UTAH", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, "Case Number " + caseNum, font, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.TOP, 2);
			
			CorisCaseHistoryCommon.addCell(table, "Case Not Found!", font, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();
			document.add(table);
			
			document.addTitle("Coris Case History - Case Number " + caseNum);
			closeDocument(document, writer);
			
			profileBO = null;
			countyBO = null;
			
		} catch (Exception e) {
			logger.error("caseNotFound(String caseNum, String locnCode, String courtType, String logName)", e);
		}
		
		return baos.toByteArray();
	}
	
	public PdfPTable printCaseMeValueData(List<PrintCaseMeDataDTO> printCaseMeDataListBO) throws DocumentException, IOException{
		int border = 0;
		
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[6];
		widths[0] = 14.0f;
		widths[1] = 4.0f;
		widths[2] = 33.0f;
		widths[3] = 2.0f;
		widths[4] = 33.0f;
		widths[5] = 14.0f;
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		try {
			table.setWidths(widths);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// ///////////////////////////////////////////////////////////////////////////////////
		// Cell Set Up
		// ///////////////////////////////////////////////////////////////////////////////////
		PdfPCell caseHistoryCell = new PdfPCell();
		caseHistoryCell.setPaddingLeft(20f);
		caseHistoryCell.setBorder(PdfPCell.NO_BORDER);
		
		// ///////////////////////////////////////////////////////////////////////////////////
		// Cell Set Up
		// ///////////////////////////////////////////////////////////////////////////////////
		for (PrintCaseMeDataDTO printCaseMeDataDTO: printCaseMeDataListBO){
			if (printCaseMeDataDTO.getBoldFont() != null){
				if (printCaseMeDataDTO.getLineSpacing() > 0 ){
					for (int i=0; i<printCaseMeDataDTO.getLineSpacing(); i++){
						CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, border, 6);
					}
				}
				
				// L C R
				// 0 0 0		
				if (TextUtil.isEmpty(printCaseMeDataDTO.getLeftText())){
					if (TextUtil.isEmpty(printCaseMeDataDTO.getCenterText())){
						if (TextUtil.isEmpty(printCaseMeDataDTO.getRightText())){
							continue;
						}
					}
				}
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, border, 1);
				
				if (TextUtil.isEmpty(printCaseMeDataDTO.getLeftText())){
					// L C R
					// 0 0 1		
					if (TextUtil.isEmpty(printCaseMeDataDTO.getCenterText())){
						if (!TextUtil.isEmpty(printCaseMeDataDTO.getRightText())){
							CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getRightText(), font, Element.ALIGN_LEFT, border, 4);
						}
					} else {
						// L C R
						// 0 1 0		
						if (TextUtil.isEmpty(printCaseMeDataDTO.getRightText())){
							CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getCenterText(), font, Element.ALIGN_CENTER, border, 4);
						} else {
						// L C R
						// 0 1 1		
							CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, border, 2);
							if (":".equals(TextUtil.toTrim(printCaseMeDataDTO.getCenterText()))){
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.LEFT, 1);
							} else {	
								CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getCenterText(), font, Element.ALIGN_LEFT, border, 1);
							}
							CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getRightText(), font, Element.ALIGN_LEFT, border, 1);
						}
					}
				} else {
					// L C R
					// 1 0 0		
					if (TextUtil.isEmpty(printCaseMeDataDTO.getCenterText())){
						if (TextUtil.isEmpty(printCaseMeDataDTO.getRightText())){
							if (printCaseMeDataDTO.getBoldFont()){
								CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getLeftText(), font, Element.ALIGN_LEFT, border, 4);
							} else {
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, border, 1);
								CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getLeftText(), font, Element.ALIGN_LEFT, border, 3);
							}
						}
						// L C R
						// 1 0 1		
						if (!TextUtil.isEmpty(printCaseMeDataDTO.getRightText())){
							CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getLeftText(), font, Element.ALIGN_LEFT, border, 2);
							CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, border, 1);
							CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getRightText(), font, Element.ALIGN_LEFT, border, 1);
						}
					} else {
					// L C R
					// 1 1 0		
						if (TextUtil.isEmpty(printCaseMeDataDTO.getRightText())){
							CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getLeftText(), font, Element.ALIGN_LEFT, border, 2);
							if (":".equals(TextUtil.toTrim(printCaseMeDataDTO.getCenterText()))){
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.LEFT, 1);
							} else {	
								CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getCenterText(), font, Element.ALIGN_LEFT, border, 1);
							}
							CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, border, 1);
						} else {
						// L C R
						// 1 1 1		
							CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getLeftText(), font, Element.ALIGN_LEFT, border, 2);
							if (":".equals(TextUtil.toTrim(printCaseMeDataDTO.getCenterText()))){
								CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_CENTER, PdfPCell.LEFT, 1);
							} else {	
								CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getCenterText(), font, Element.ALIGN_LEFT, border, 1);
							}
							CorisCaseHistoryCommon.addCell(table, printCaseMeDataDTO.getRightText(), font, Element.ALIGN_LEFT, border, 1);
						}
					}
				}
							
				CorisCaseHistoryCommon.addCell(table, " ", font, Element.ALIGN_LEFT, border, 1);
				printCaseMeDataDTO = null;
			}
		}
		// ///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		// ///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();

		return table;
	}

}
