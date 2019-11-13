package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.acctbond.AcctBondBO;
import gov.utcourts.coriscommon.dataaccess.acctfee.AcctFeeBO;
import gov.utcourts.coriscommon.dataaccess.accttrust.AcctTrustBO;
import gov.utcourts.coriscommon.dataaccess.bondtype.BondTypeBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.feetype.FeeTypeBO;
import gov.utcourts.coriscommon.dataaccess.journal.JournalBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.nonmonbonddetl.NonmonBondDetlBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.trans.TransBO;
import gov.utcourts.coriscommon.dataaccess.transdetl.TransDetlBO;
import gov.utcourts.coriscommon.dataaccess.transdist.TransDistBO;
import gov.utcourts.coriscommon.dataaccess.trusttype.TrustTypeBO;
import gov.utcourts.coriscommon.sp.GetCaseTitle;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisinterface.util.CorisCaseHistoryCommon;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.notifications.util.CorisNotificationEmailCommon;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/ReceiptServlet")
public class ReceiptServlet extends BaseServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ReceiptServlet.class.getName());
	static final Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 9f, Font.NORMAL);
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String caseNum = URLEncryption.getParamAsString(request, "caseNum");
		String logName = URLEncryption.getParamAsString(request, "logName");
		String locnCode = URLEncryption.getParamAsString(request, "locnCode");
		String courtType = URLEncryption.getParamAsString(request, "courtType");
		int intJournalNum = Integer.valueOf(URLEncryption.getParamAsString(request, "key1"));
		int transNum = Integer.valueOf(URLEncryption.getParamAsString(request, "key2"));
		String duplicate = URLEncryption.getParamAsString(request, "duplicate");

		try {
			generateReceipt(caseNum, locnCode, courtType, intJournalNum, transNum, duplicate, request, response, logName);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void generateReceipt(String caseNum, String locnCode, String courtType, int intJournalNum, int transNum, String duplicate, HttpServletRequest request, HttpServletResponse response, String logName) throws DocumentException, IOException{
		Boolean creditCardPayment = false;
		int nmbPayOff = 0;
		int jns = 0;
		// ///////////////////////////////////////////////////////////////
		// Create table
		// ///////////////////////////////////////////////////////////////
		float[] widths = new float[3];
		widths[0] = 22.0f;
		widths[1] = 13.0f;
		widths[2] = 65.0f;

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
			
			// ///////////////////////////////////////////////////////////////
			// Create new document
			// ///////////////////////////////////////////////////////////////
			Document document = new Document(PageSize.LETTER);
			PdfWriter writer = null;
			ByteArrayOutputStream outputStream = null;

			// ///////////////////////////////////////////////////////////////////////////////////
			// Email
			// ///////////////////////////////////////////////////////////////////////////////////
			String email = URLEncryption.getParamAsString(request, "email");
			
			
			if ("Y".equals(email.toUpperCase())){
				outputStream = new ByteArrayOutputStream();
				writer = PdfWriter.getInstance(document, outputStream);
				
			}else {
				writer = PdfWriter.getInstance(document, response.getOutputStream());
			}
			
			// ///////////////////////////////////////////////////////////////
			// Document Set Up - Left, Right, Top, Bottom
			// ///////////////////////////////////////////////////////////////
			document.setMargins(40f, 50f, 30f, 30f);
			document.open();

			// ///////////////////////////////////////////////////////////////////////////////////
			// Cell Set Up
			// ///////////////////////////////////////////////////////////////////////////////////
			PdfPCell caseHistoryCell = new PdfPCell();

			caseHistoryCell.setPaddingLeft(20f);
			caseHistoryCell.setBorder(PdfPCell.NO_BORDER);

			/////////////////////////////////////////////////////
			// Get ProfileVO
			/////////////////////////////////////////////////////
			ProfileBO profileBO = new ProfileBO(courtType)
			.where(ProfileBO.LOCNCODE, locnCode)
			.where(ProfileBO.COURTTYPE, courtType)
			.find(ProfileBO.COURTTITLE);
			
			CorisCaseHistoryCommon.addCell(table, profileBO.getCourtTitle(), normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
			CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);

			/////////////////////////////////////////////////////
			// Get Todays Date
			/////////////////////////////////////////////////////
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			Date today = Calendar.getInstance().getTime();        
			if ("Y".equals(duplicate)){
				CorisCaseHistoryCommon.addCell(table, "DUPLICATE RECEIPT " + df.format(today), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
			} else {
				CorisCaseHistoryCommon.addCell(table, "RECEIPT " + df.format(today), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
			}
			
			/////////////////////////////////////////////////////
			// Get TransVO
			/////////////////////////////////////////////////////
			TransBO transBO = new TransBO(courtType)
			.where(TransBO.INTJOURNALNUM, intJournalNum)
			.where(TransBO.TRANSNUM, transNum)
			.find();
			
			//if ("Joint/Several Payment".matches(transBO.getNote())){
			//	jns = 1;
			//}

			if (!TextUtil.isEmpty(transBO.getOutType())){
				if ("D".equals(transBO.getOutType())){
					/////////////////////////////////////////////////////
					// Get Non Monetary Bond Pay Off ????
					/////////////////////////////////////////////////////
					FieldOperationDescriptor count = new FieldOperationDescriptor(TransDistBO.APPLYCODE, FieldOperationType.COUNT, new TypeInteger());
					
					TransDistBO transDistBO = new TransDistBO(courtType)
					.setFieldOperations(count)
					.where(TransDistBO.INTJOURNALNUM, transBO.getIntJournalNum())
					.where(TransDistBO.TRANSNUM, transBO.getTransNum())
					.where(TransDistBO.APPLYCODE, "OP")
					.find(TransDistBO.APPLYCODE);
					
					nmbPayOff = (Integer)transDistBO.get(count);
	
					transDistBO = null;
				}
			}
			
			/////////////////////////////////////////////////////
			// Get JournalVO
			/////////////////////////////////////////////////////
			JournalBO journalBO = new JournalBO(courtType)
			.where(JournalBO.INTJOURNALNUM, intJournalNum)
			.find(JournalBO.JOURNALNUM);
			
			/////////////////////////////////////////////////////
			// Get PersonnelVO
			/////////////////////////////////////////////////////
			PersonnelBO personnelClerkBO = new PersonnelBO(courtType)
			.where(PersonnelBO.USERIDSRL, transBO.getUseridSrl())
			.find(PersonnelBO.LOGNAME);
						
			CorisCaseHistoryCommon.addCell(table, df.format(transBO.getTransDatetime()), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, " Clerk: " + personnelClerkBO.getLogname(), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			personnelClerkBO = null;
			
			CorisCaseHistoryCommon.addCell(table, "Receipt Number: " + journalBO.getJournalNum() + String.format("%04d", transBO.getTransNum()), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);

			/////////////////////////////////////////////////////
			// Get PartyVO
			/////////////////////////////////////////////////////
			PartyBO payorPartyBO = new PartyBO(courtType)
			.where(PartyBO.PARTYNUM, transBO.getPayorPartyNum())
			.find(PartyBO.LASTNAME, PartyBO.FIRSTNAME);
			if (payorPartyBO != null){
				if (!TextUtil.isEmpty(payorPartyBO.getFirstName())){
					CorisCaseHistoryCommon.addCell(table, "Payor: " + payorPartyBO.getLastName() + ", " + payorPartyBO.getFirstName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				} else {
					CorisCaseHistoryCommon.addCell(table, "Payor: " + payorPartyBO.getLastName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				}
			} else {
				CorisCaseHistoryCommon.addCell(table, "Payor: ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
			}
			
			/////////////////////////////////////////////////////
			// Get TransVO
			/////////////////////////////////////////////////////
			List<TransDetlBO> transDetlListBO = new TransDetlBO(courtType)
			.where(TransDetlBO.INTJOURNALNUM, intJournalNum)
			.where(TransDetlBO.TRANSNUM, transNum)
			.search();
			
			CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
			
			CorisCaseHistoryCommon.addCell(table, "Received ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
			for(TransDetlBO transDetlBO : transDetlListBO){
				
				BigDecimal balance = new BigDecimal(0);
				balance = balance.add(transDetlBO.getRevnAmt()).add(transDetlBO.getTrustAmt());
				
				if ("CA".equals(transDetlBO.getTenderType())){
					CorisCaseHistoryCommon.addCell(table, "Cash", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(balance), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else if ("CH".equals(transDetlBO.getTenderType())){
					CorisCaseHistoryCommon.addCell(table, "Check " + transDetlBO.getTenderDescr(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(balance), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else if ("CC".equals(transDetlBO.getTenderType())){
					CorisCaseHistoryCommon.addCell(table, transDetlBO.getTenderDescr(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(balance), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					creditCardPayment = true;
				} else if ("CR".equals(transDetlBO.getTenderType())){
					CorisCaseHistoryCommon.addCell(table, "Credit", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(balance), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else if ("NM".equals(transDetlBO.getTenderType())){
					if (nmbPayOff == 0){
						if ("E".equals(transBO.getOutType())){
							balance = balance.negate();
						}
						CorisCaseHistoryCommon.addCell(table, "Non-monetary", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(balance), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					}
				}
				balance = null;
			}
			
			/////////////////////////////////////////////////////
			// Get KaseVO
			/////////////////////////////////////////////////////
			KaseBO kaseBO = new KaseBO(courtType)
			.where(KaseBO.LOCNCODE, locnCode)
			.where(KaseBO.COURTTYPE, courtType)
			.where(KaseBO.CASENUM, caseNum)
			.find();

			/////////////////////////////////////////////////////
			// Get CaseVO
			/////////////////////////////////////////////////////
			CaseTypeBO caseTypeBO = new CaseTypeBO(courtType)
			.where(CaseTypeBO.CASETYPE, kaseBO.getCaseType())
			.find();
			
			CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
			CorisCaseHistoryCommon.addCell(table, "Case: " + kaseBO.getCaseNum() + " " + caseTypeBO.getDescr(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
			
			if (kaseBO.getAssnJudgeId() > 0 ){
				/////////////////////////////////////////////////////
				// Get PersonnelVO
				/////////////////////////////////////////////////////
				PersonnelBO personnelJudgeBO = new PersonnelBO(courtType)
				.where(PersonnelBO.USERIDSRL, kaseBO.getAssnJudgeId())
				.find(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME);
				CorisCaseHistoryCommon.addCell(table, "Judge: " + personnelJudgeBO.getLastName() + ", " + personnelJudgeBO.getFirstName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				personnelJudgeBO = null;
			} 
			
			if (kaseBO.getAssnCommId() > 0 ){
				PersonnelBO personneCommissionaireBO = new PersonnelBO(courtType)
				.where(PersonnelBO.USERIDSRL, kaseBO.getAssnCommId())
				.find(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME);
				CorisCaseHistoryCommon.addCell(table, "Commissioner: " + personneCommissionaireBO.getLastName() + ", " + personneCommissionaireBO.getFirstName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				personneCommissionaireBO = null;
			}
			
			if ("P".equals(caseTypeBO.getCategory())){
				/////////////////////////////////////////////////////
				// Get Defendant PartyCaseVO
				/////////////////////////////////////////////////////
				PartyCaseBO partyCaseBO = new PartyCaseBO(courtType)
				.where(PartyCaseBO.INTCASENUM, kaseBO.getIntCaseNum())
				.where(PartyCaseBO.PARTYCODE, "PET")
				.find(PartyCaseBO.PARTYNUM);
				/////////////////////////////////////////////////////
				// Get Defendant PartyVO
				/////////////////////////////////////////////////////
				PartyBO partyBO = new PartyBO(courtType)
				.where(PartyBO.PARTYNUM, partyCaseBO.getPartyNum())
				.find(PartyBO.LASTNAME, PartyBO.FIRSTNAME);
				
				if (!TextUtil.isEmpty(partyBO.getFirstName())){
					CorisCaseHistoryCommon.addCell(table, "Petitioner: " + partyBO.getLastName() + ", " + partyBO.getFirstName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				} else {
					CorisCaseHistoryCommon.addCell(table, "Petitioner: " + partyBO.getLastName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				}
			} else if ("V".equals(caseTypeBO.getCategory())){
				/////////////////////////////////////////////////////
				// Get Defendant PartyCaseVO
				/////////////////////////////////////////////////////
				PartyCaseBO partyCaseBO = new PartyCaseBO(courtType)
				.where(PartyCaseBO.INTCASENUM, kaseBO.getIntCaseNum())
				.where(PartyCaseBO.PARTYCODE, "DEF")
				.find(PartyCaseBO.PARTYNUM);
				/////////////////////////////////////////////////////
				// Get Defendant PartyVO
				/////////////////////////////////////////////////////
				PartyBO partyBO = new PartyBO(courtType)
				.where(PartyBO.PARTYNUM, partyCaseBO.getPartyNum())
				.find(PartyBO.LASTNAME, PartyBO.FIRSTNAME);
				
				if (!TextUtil.isEmpty(partyBO.getFirstName())){
					CorisCaseHistoryCommon.addCell(table, "Defendant: " + partyBO.getLastName() + ", " + partyBO.getFirstName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				} else {
					CorisCaseHistoryCommon.addCell(table, "Defendant: " + partyBO.getLastName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				}
			} else if ("R".equals(caseTypeBO.getCategory()) || "S".equals(caseTypeBO.getCategory())){
				/////////////////////////////////////////////////////
				// Get Defendant PartyCaseVO
				/////////////////////////////////////////////////////
				PartyCaseBO partyCaseBO = new PartyCaseBO(courtType)
				.where(PartyCaseBO.INTCASENUM, kaseBO.getIntCaseNum())
				.where(PartyCaseBO.PARTYCODE, "DEF")
				.find(PartyCaseBO.PARTYNUM);
				/////////////////////////////////////////////////////
				// Get Defendant PartyVO
				/////////////////////////////////////////////////////
				PartyBO partyBO = new PartyBO(courtType)
				.where(PartyBO.PARTYNUM, partyCaseBO.getPartyNum())
				.find(PartyBO.LASTNAME, PartyBO.FIRSTNAME);
				
				if (!TextUtil.isEmpty(partyBO.getFirstName())){
					CorisCaseHistoryCommon.addCell(table, "Defendant: " + partyBO.getLastName() + ", " + partyBO.getFirstName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				} else {
					CorisCaseHistoryCommon.addCell(table, "Defendant: " + partyBO.getLastName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				}
				
				/////////////////////////////////////////////////////
				// Get Plantiff PartyCaseVO
				/////////////////////////////////////////////////////
				partyCaseBO = new PartyCaseBO(courtType)
				.where(PartyCaseBO.INTCASENUM, kaseBO.getIntCaseNum())
				.where(PartyCaseBO.PARTYCODE, "PLA")
				.find(PartyCaseBO.PARTYNUM);
				/////////////////////////////////////////////////////
				// Get Defendant PartyVO
				/////////////////////////////////////////////////////
				partyBO = new PartyBO(courtType)
				.where(PartyBO.PARTYNUM, partyCaseBO.getPartyNum())
				.find(PartyBO.LASTNAME, PartyBO.FIRSTNAME);
				
				if (!TextUtil.isEmpty(partyBO.getFirstName())){
					CorisCaseHistoryCommon.addCell(table, "Plaintiff: " + partyBO.getLastName() + ", " + partyBO.getFirstName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				} else {
					CorisCaseHistoryCommon.addCell(table, "Plaintiff: " + partyBO.getLastName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				}
			}
			PersonnelBO userBO = new PersonnelBO(courtType)
			.where(PersonnelBO.LOGNAME, logName)
			.where(PersonnelBO.LOCNCODE, locnCode)
			.where(PersonnelBO.COURTTYPE, courtType)
			.find();
			
			
			String caseTitle = 	GetCaseTitle.getCaseTitle(userBO.getLogname(), kaseBO.getIntCaseNum(), courtType, null);
			CorisCaseHistoryCommon.addCell(table, caseTitle, normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
			
			/////////////////////////////////////////////////////
			// Get TransDistBO Information For Break Down
			/////////////////////////////////////////////////////
			FieldOperationDescriptor sumpaid = new FieldOperationDescriptor(TransDistBO.AMTPAID, FieldOperationType.SUM, new TypeBigDecimal());
			FieldOperationDescriptor sumcredit = new FieldOperationDescriptor(TransDistBO.AMTCREDIT, FieldOperationType.SUM, new TypeBigDecimal());
			
			List<TransDistBO>  transDistListBO = new TransDistBO(courtType)
			.setFieldOperations(sumpaid, sumcredit)
			.where(
				new FindDescriptor(TransDistBO.INTJOURNALNUM,intJournalNum),
				new FindDescriptor(TransDistBO.TRANSNUM, transNum),
				new FindDescriptor(TransDistBO.DISTCODE).setCustomSearch("<> 'TE'")
				
			)
			.groupBy(TransDistBO.ACCTNUM)    
			.orderBy(TransDistBO.ACCTNUM)
			.search(TransDistBO.ACCTNUM);
			
			for(TransDistBO transDistBO : transDistListBO){
				BigDecimal paid = new BigDecimal(0);
				
				paid = paid.add((BigDecimal) transDistBO.get(sumpaid)).add((BigDecimal) transDistBO.get(sumcredit));
				
				/////////////////////////////////////////////////////
				// Get AccountVO
				/////////////////////////////////////////////////////
				AccountBO accountBO = new AccountBO(courtType)
				.where(AccountBO.ACCTNUM, transDistBO.getAcctNum())
				.find();
				
				if ("B".equals(accountBO.getAcctType())){
					CorisCaseHistoryCommon.addCell(table, "BAIL POSTED ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(paid), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else if ("F".equals(accountBO.getAcctType())){
					/////////////////////////////////////////////////////
					// Get AcctFeeVO
					/////////////////////////////////////////////////////
					AcctFeeBO acctFeeBO = new AcctFeeBO(courtType)
					.where(AcctFeeBO.ACCTNUM, accountBO.getAcctNum())
					.find();
					
					/////////////////////////////////////////////////////
					// Get AcctFeeVO
					/////////////////////////////////////////////////////
					FeeTypeBO feeTypeBO = new FeeTypeBO(courtType)
					.where(FeeTypeBO.FEECODE, acctFeeBO.getFeeCode())
					.where(FeeTypeBO.LASTEFFECTDATE, acctFeeBO.getFeeEffectDate())
					.find();
					
					CorisCaseHistoryCommon.addCell(table, feeTypeBO.getDescr(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(paid), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					acctFeeBO = null;
					feeTypeBO = null;
					
				} else if ("I".equals(accountBO.getAcctType())){
					CorisCaseHistoryCommon.addCell(table, "FINE ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(paid), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else if ("O".equals(accountBO.getAcctType())){
					/////////////////////////////////////////////////////
					// Get AcctBondVO
					/////////////////////////////////////////////////////
					AcctBondBO acctBondBO = new AcctBondBO(courtType)
					.where(AcctBondBO.ACCTNUM, accountBO.getAcctNum())
					.find();
					
					/////////////////////////////////////////////////////
					// Get BondTypeVO
					/////////////////////////////////////////////////////
					BondTypeBO bondTypeBO = new BondTypeBO(courtType)
					.where(BondTypeBO.BONDCODE, acctBondBO.getBondCode())
					.find();
					
					if ("PR".equals(bondTypeBO.getBondCode())){
						/////////////////////////////////////////////////////
						// Get nonmon bond detl
						/////////////////////////////////////////////////////
						NonmonBondDetlBO nonmonBondDetlBO = new NonmonBondDetlBO(courtType)
						.where(NonmonBondDetlBO.ACCTNUM, accountBO.getAcctNum())
						.find();
						CorisCaseHistoryCommon.addCell(table, nonmonBondDetlBO.getDescr(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
						nonmonBondDetlBO = null;
					} else {
						CorisCaseHistoryCommon.addCell(table, bondTypeBO.getDescr() + " Bond", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					}
					
					CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(paid), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					acctBondBO = null;
					bondTypeBO = null;
					
					
				} else if ("T".equals(accountBO.getAcctType())){
					/////////////////////////////////////////////////////
					// Get AcctFeeVO
					/////////////////////////////////////////////////////
					AcctTrustBO acctTrustBO = new AcctTrustBO(courtType)
					.where(AcctTrustBO.ACCTNUM, accountBO.getAcctNum())
					.find();
					
					/////////////////////////////////////////////////////
					// Get AcctFeeVO
					/////////////////////////////////////////////////////
					TrustTypeBO trustTypeBO = new TrustTypeBO(courtType)
					.where(TrustTypeBO.TRUSTCODE, acctTrustBO.getTrustCode())
					.find();
					
					CorisCaseHistoryCommon.addCell(table, trustTypeBO.getDescr(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(paid), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					
					acctTrustBO = null;
					trustTypeBO = null;
				}
				paid = null;
				accountBO = null;
				transDistBO = null;
			}	
			
			if (creditCardPayment){
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 3);
				
				CorisCaseHistoryCommon.addCell(table, "Sign ____________________________", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				
				if (!TextUtil.isEmpty(payorPartyBO.getFirstName())){
					CorisCaseHistoryCommon.addCell(table, "        " + payorPartyBO.getFirstName() + " " + payorPartyBO.getLastName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				} else {
					CorisCaseHistoryCommon.addCell(table, "        " + payorPartyBO.getLastName(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
				}
			}
			
			if ("N".equals(duplicate) || TextUtil.isEmpty(duplicate)){
				if (nmbPayOff > 0){
				
					TransDistBO transDistBO = new TransDistBO(courtType)
					.where(TransDistBO.INTJOURNALNUM,intJournalNum)
					.where(TransDistBO.TRANSNUM, transNum)
					.where(TransDistBO.APPLYCODE, "OP")
					.find();
	
					/////////////////////////////////////////////////////
					// Get AccountVO
					/////////////////////////////////////////////////////
					AccountBO accountBO = new AccountBO(courtType)
					.where(AccountBO.ACCTNUM, transDistBO.getAcctNum())
					.find();
					
					/////////////////////////////////////////////////////
					// Get AcctBondVO
					/////////////////////////////////////////////////////
					AcctBondBO acctBondBO = new AcctBondBO(courtType)
					.where(AcctBondBO.ACCTNUM, accountBO.getAcctNum())
					.find();
					
					/////////////////////////////////////////////////////
					// Get BondTypeVO
					/////////////////////////////////////////////////////
					BondTypeBO bondTypeBO = new BondTypeBO(courtType)
					.where(BondTypeBO.BONDCODE, acctBondBO.getBondCode())
					.find();
				
					/////////////////////////////////////////////////////
					// Print Balances
					/////////////////////////////////////////////////////
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
					CorisCaseHistoryCommon.addCell(table, "Balances ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
					
					BigDecimal balance = new BigDecimal(0);
					balance = balance.add(accountBO.getAmtDue())
					                 .subtract(accountBO.getAmtPaid())
					                 .subtract(acctBondBO.getExonRefundAmt());
					
					CorisCaseHistoryCommon.addCell(table, bondTypeBO.getDescr(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(balance), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
					CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
					balance = null;
				}
				
				/////////////////////////////////////////////////////
				// Get Fine Account Balance
				/////////////////////////////////////////////////////
				List<AccountBO> accountListBO = new AccountBO(courtType)
				.where(AccountBO.INTCASENUM, kaseBO.getIntCaseNum())
				.search();
				
				Boolean balanceHeader = true;
				
				BigDecimal balance = new BigDecimal(0);
			
				for (AccountBO accountBO : accountListBO){
					balance = balance.add(accountBO.getAmtDue())
					.subtract(accountBO.getAmtPaid())
					.subtract(accountBO.getAmtCredit());
					if (balance.compareTo(BigDecimal.ZERO) > 0){
						
						if (balanceHeader){
							CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
							CorisCaseHistoryCommon.addCell(table, "Balances", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
							balanceHeader = false;
						}	
						if ("I".equals(accountBO.getAcctType())){
							CorisCaseHistoryCommon.addCell(table, "Fine", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(balance), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
						}else if ("F".equals(accountBO.getAcctType())){
							/////////////////////////////////////////////////////
							// Get AcctFeeVO
							/////////////////////////////////////////////////////
							AcctFeeBO acctFeeBO = new AcctFeeBO(courtType)
							.where(AcctFeeBO.ACCTNUM, accountBO.getAcctNum())
							.find();
							
							/////////////////////////////////////////////////////
							// Get AcctFeeVO
							/////////////////////////////////////////////////////
							FeeTypeBO feeTypeBO = new FeeTypeBO(courtType)
							.where(FeeTypeBO.FEECODE, acctFeeBO.getFeeCode())
							.where(FeeTypeBO.LASTEFFECTDATE, acctFeeBO.getFeeEffectDate())
							.find();
							
							CorisCaseHistoryCommon.addCell(table, feeTypeBO.getDescr(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(balance), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							acctFeeBO = null;
							feeTypeBO = null;
						}else if ("T".equals(accountBO.getAcctType())){
							/////////////////////////////////////////////////////
							// Get AcctFeeVO
							/////////////////////////////////////////////////////
							AcctTrustBO acctTrustBO = new AcctTrustBO(courtType)
							.where(AcctTrustBO.ACCTNUM, accountBO.getAcctNum())
							.find();
							
							/////////////////////////////////////////////////////
							// Get AcctFeeVO
							/////////////////////////////////////////////////////
							TrustTypeBO trustTypeBO = new TrustTypeBO(courtType)
							.where(TrustTypeBO.TRUSTCODE, acctTrustBO.getTrustCode())
							.find();
							
							CorisCaseHistoryCommon.addCell(table, trustTypeBO.getDescr(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, Constants.receiptDecimalFormatter.format(balance), normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_RIGHT, PdfPCell.NO_BORDER, 1);
							acctTrustBO = null;
							trustTypeBO = null;
						}
					}
					accountBO = null; 
					balance = new BigDecimal(0);
				}
				accountListBO = null;
			}
			
			if (!TextUtil.isEmpty(transBO.getNote())){
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 3);
				CorisCaseHistoryCommon.addCell(table, "Note: " + transBO.getNote(), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if ("Y".equals(duplicate)){
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 3);
				CorisCaseHistoryCommon.addCell(table, "******** DUPLICATE RECEIPT ********", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			} else {
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 3);
				CorisCaseHistoryCommon.addCell(table, "******** SAVE THIS RECEIPT ********", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 2);
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			}
			
			if ("J".equals(courtType)){
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
			}
			
			// ///////////////////////////////////////////////////////////////
			// Add Cell
			// ///////////////////////////////////////////////////////////////
			table.addCell(caseHistoryCell);

			// ///////////////////////////////////////////////////////////////
			// Clean Up
			// ///////////////////////////////////////////////////////////////
			caseHistoryCell = null;

			// ///////////////////////////////////////////////////////////////
			// New Page
			// ///////////////////////////////////////////////////////////////
			document.newPage();

			// ///////////////////////////////////////////////////////////////
			// Just in case we don't have enough cells
			// tell the table to "fill in the blanks"
			// ///////////////////////////////////////////////////////////////
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
			table.completeRow();

			try {
				document.add(table);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			document.addTitle("Receipt");
			document.close();
			
			if ("Y".equals(email.toUpperCase())){
				// ///////////////////////////////////////////////////////////////
				// Personnel Information
				// ///////////////////////////////////////////////////////////////
				PersonnelBO personnelBO = new PersonnelBO(courtType)
				.where(PersonnelBO.LOGNAME, logName)
				.where(PersonnelBO.LOCNCODE, locnCode)
				.where(PersonnelBO.COURTTYPE, courtType)
				.find(PersonnelBO.EMAILADDRESS);
				// ///////////////////////////////////////////////////////////////
				// Attachment
				// ///////////////////////////////////////////////////////////////
				byte[] attachment = outputStream.toByteArray();
				
				// ///////////////////////////////////////////////////////////////
				// Send Email
				// ///////////////////////////////////////////////////////////////
				CorisNotificationEmailCommon.corisNotificationEmail(
					kaseBO.getIntCaseNum(),
					Constants.SYSTEM_ID,
			        attachment, 
			        profileBO.getCourtTitle() +	" Case Number " + caseNum + " " + " Receipt", 
			        "", 
			        personnelBO.getEmailAddress(), 
			        Constants.RETURN_EMAIL,
			        null,
			        null,
			        courtType, 
			        caseNum+locnCode+courtType+".pdf", 
			        false
				);
				
				attachment = null;
				personnelBO = null;
			}
			// ///////////////////////////////////////////////////////////////
			// Clean Up
			// ///////////////////////////////////////////////////////////////
			table = null;
			document = null;
			writer = null;
			transBO = null;
			journalBO = null;
			payorPartyBO = null;
			df=null;
			today = null;
			
			
		} catch (Exception e) {
			logger.error("Process receipt(CorisCaseHistoryDTO corisCaseHistoryDTO, HttpServletRequest request, HttpServletResponse response)",e);
		}
		table = null;
		widths = null;
	}
}
