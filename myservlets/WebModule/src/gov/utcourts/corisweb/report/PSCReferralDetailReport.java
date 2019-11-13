package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisinterface.report.CorisCaseHistorySingleReport;
import gov.utcourts.corisinterface.util.CorisCaseHistoryCommon;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO;
import gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO;
import gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO;
import gov.utcourts.problemsolving.dataaccess.psparticipant.PsParticipantBO;
import gov.utcourts.problemsolving.dataaccess.psphase.PsPhaseBO;
import gov.utcourts.problemsolving.dataaccess.psphasedefn.PsPhaseDefnBO;
import gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO;
import gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO;
import gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO;
import gov.utcourts.problemsolving.dataaccess.psreward.PsRewardBO;
import gov.utcourts.problemsolving.dataaccess.psrewarddefn.PsRewardDefnBO;
import gov.utcourts.problemsolving.dataaccess.pssanction.PsSanctionBO;
import gov.utcourts.problemsolving.dataaccess.pssanctiondefn.PsSanctionDefnBO;
import gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class PSCReferralDetailReport extends PdfPageEventHelper{
	
	private static Logger logger = Logger.getLogger(CorisCaseHistorySingleReport.class.getName());
	static Font font = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
	static final Font boldFont = FontFactory.getFont(FontFactory.HELVETICA, 11f, Font.BOLD);
	static final Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 11f, Font.NORMAL);
	
	static PdfTemplate template;
	static int	fontsize = 12;
	
	
	public byte[] generateReferralDetails(List<PsReferralBO> refCases) throws DocumentException, IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfPTable table = null; 

		try {	
			// ///////////////////////////////////////////////////////////////
			// Create new document
			// ///////////////////////////////////////////////////////////////
			Document document = new Document(PageSize.LETTER);
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			
			writer.setPageEvent(this);
			
		    // ///////////////////////////////////////////////////////////////
			// Document Set Up - Left, Right, Top, Bottom
			// ///////////////////////////////////////////////////////////////
			document.addCreationDate();
			document.addTitle("Problem Solving Court -- Referral Details");
			document.setPageSize(PageSize.LETTER);
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
	
			// ///////////////////////////////////////////////////////////////////////////////////
			// For Page Total
			// ///////////////////////////////////////////////////////////////////////////////////
			PdfContentByte cb = writer.getDirectContent();
			template = cb.createTemplate(50, 20);
			
			//Title
			table = generateTitleTab("Problem Solving Court");
			document.add(table);
			
			//Generate Referral Case general information
			table = generateSectionTitleTab("Referral", 1, BaseColor.GRAY);
			document.add(table);
			if (refCases.size() > 0) {
				PsReferralBO refBO = refCases.get(0);			
				table = generateCaseInfo(refBO);
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				table.setSpacingAfter(10f);
				document.add(table);
				
				//Generate Referral Details section
				table = generateSectionTitleTab("Details", 1, BaseColor.GRAY);
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				document.add(table);
				
				table = generateReferralInfo(refCases);
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				table.setSpacingAfter(10f);
				document.add(table);
				
				//Generate Action History Section
				table = generateSectionTitleTab("Referral History", 1, BaseColor.GRAY);
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				document.add(table);
				
				table = generateReferralHistoryInfo(refBO);
				table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				table.completeRow();
				document.add(table);
				
				List<PsPhaseBO> fases = getPhases(refBO.getPsReferralId());
				if(fases.size() > 0){
					table = generateSectionTitleTab("Phases", 1, BaseColor.GRAY);
					table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
					table.completeRow();
					document.add(table);
					
					table = generatePhasesInfo(fases);
					table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
					table.completeRow();
					document.add(table);
				}
				
				//Rewards
				List<PsRewardBO> rewards = getRewards(refBO.getPsReferralId());
				if (rewards.size() > 0) {
					table = generateSectionTitleTab("Incentives", 1, BaseColor.GRAY);
					table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
					table.completeRow();
					document.add(table);
					
					table = generateRewardsInfo(rewards);
					table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
					table.completeRow();
					document.add(table);
				}
				
				//Sanctions
				List<PsSanctionBO> sanctions = getSanctions(refBO.getPsReferralId());
				if (sanctions.size() > 0) {
					table = generateSectionTitleTab("Sanctions", 1, BaseColor.GRAY);
					table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
					table.completeRow();
					document.add(table);
					
					table = generateSanctionsInfo(sanctions);
					table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
					table.completeRow();
					document.add(table);
				}
			}
			
			closeDocument(document, writer);
		} catch (Exception e) {
			logger.error("Process table widths", e);
			e.printStackTrace();
		}
		
		return baos.toByteArray();
	}
	
	private PdfPTable generatePhasesInfo(List<PsPhaseBO> fases) {
		float[] widths = new float[2];
		widths[0] = 40.0f;
		widths[1] = 60.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
			
			for (PsPhaseBO fase : fases) {
				String faseCode = (String) fase.get(PsPhaseDefnBO.PSPHASECODE) + ": ";
				String faseDescr = TextUtil.print(fase.get(PsPhaseDefnBO.PSPHASEDESCR));
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormatCoris.format(fase.get(PsReferralHistoryBO.PSACTIONDATE)) + "  " + faseCode + faseDescr, normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
		} catch(Exception e) {
			logger.error("Failed to generate reward history", e);
			e.printStackTrace();
		}
		return table;
	}

	private List<PsPhaseBO> getPhases(int psReferralId) throws Exception {
		return new PsPhaseBO().includeFields(PsPhaseBO.ALL_FIELDS)
							  .includeTables(new PsReferralHistoryBO().includeFields(PsReferralHistoryBO.PSACTIONDATE,PsReferralHistoryBO.PSACTIONNOTE).where(PsReferralHistoryBO.PSREFERRALID, psReferralId),
									  	 new PsPhaseDefnBO().includeFields(PsPhaseDefnBO.PSPHASECODE,PsPhaseDefnBO.PSPHASEDESCR))
							  .addForeignKey(PsPhaseBO.PSHISTORYID, PsReferralHistoryBO.PSHISTORYID)
							  .addForeignKey(PsPhaseBO.PSPHASEDEFNID, PsPhaseDefnBO.PSPHASEDEFNID)
							  .orderBy(PsPhaseBO.PSPHASEDATE,DirectionType.DESC).search();
	}

	private List<PsRewardBO> getRewards(int referralId) throws Exception {
		return new PsRewardBO()
		.includeFields(PsRewardBO.ALL_FIELDS)
		.includeTables(
			new PsReferralHistoryBO().where(PsReferralHistoryBO.PSREFERRALID, referralId),
			new PsRewardDefnBO().includeFields(PsRewardDefnBO.PSREWARDCODE, PsRewardDefnBO.PSREWARDDESCR)
		)
		.addForeignKey(PsRewardBO.PSHISTORYID, PsReferralHistoryBO.PSHISTORYID)
		.addForeignKey(PsRewardBO.PSREWARDDEFNID, PsRewardDefnBO.PSREWARDDEFNID)
		.search();
	}
	
	private List<PsSanctionBO> getSanctions(int referralId) throws Exception {
		return new PsSanctionBO()
		.includeFields(PsSanctionBO.ALL_FIELDS)
		.includeTables(
			new PsReferralHistoryBO().where(PsReferralHistoryBO.PSREFERRALID, referralId),
			new PsSanctionDefnBO().includeFields(PsSanctionDefnBO.PSSANCTIONCODE, PsSanctionDefnBO.PSSANCTIONDESCR)
		)
		.addForeignKey(PsSanctionBO.PSHISTORYID, PsReferralHistoryBO.PSHISTORYID)
		.addForeignKey(PsSanctionBO.PSSANCTIONDEFNID, PsSanctionDefnBO.PSSANCTIONDEFNID)
		.search();
	}

	private PdfPTable generateReferralHistoryInfo(PsReferralBO refBO) throws Exception {
		float[] widths = new float[2];
		widths[0] = 40.0f;
		widths[1] = 60.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
			
			List<PsReferralHistoryBO> refHist = new PsReferralHistoryBO()
					.where(PsReferralHistoryBO.PSREFERRALID, refBO.getPsReferralId())
					.orderBy(PsReferralHistoryBO.PSACTIONDATE, DirectionType.DESC)
					.search();
			for(PsReferralHistoryBO hist:refHist){
				String action = new PsActionDefnBO().where(PsActionDefnBO.PSACTIONDEFNID, hist.getPsActionDefnId()).find(PsActionDefnBO.PSACTIONDESCR).getPsActionDescr();
				String note = !TextUtil.isEmpty(hist.getPsActionNote()) ? " - " + TextUtil.print(hist.getPsActionNote()) : "";
				String clerk = hist.getPsLogname();
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormatCoris.format(hist.getPsActionDate()) + "  " + action + note + " (clerk: " + clerk + ")", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
		} catch(Exception e) {
			logger.error("Failed to generate referral history", e);
			e.printStackTrace();
		}
		return table;
		
	}
	
	private PdfPTable generateRewardsInfo(List<PsRewardBO> rewards) throws Exception {
		float[] widths = new float[2];
		widths[0] = 40.0f;
		widths[1] = 60.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
			
			for (PsRewardBO reward : rewards) {
				String rewardDescription = (String) reward.get(PsRewardDefnBO.PSREWARDDESCR);
				String rewardNote = !TextUtil.isEmpty(reward.getPsRewardNote()) ? " - " + TextUtil.print(reward.getPsRewardNote()) : "";
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormatCoris.format(reward.getPsRewardDate()) + "  " + rewardDescription + rewardNote, normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
		} catch(Exception e) {
			logger.error("Failed to generate reward history", e);
			e.printStackTrace();
		}
		return table;
	}

	private PdfPTable generateSanctionsInfo(List<PsSanctionBO> sanctions) throws Exception {
		float[] widths = new float[2];
		widths[0] = 40.0f;
		widths[1] = 60.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
			
			for (PsSanctionBO sanction : sanctions) {
				String sanctionDescription = (String) sanction.get(PsSanctionDefnBO.PSSANCTIONDESCR);
				int psAmount = (Integer) sanction.getPsSanctionAmount();
				String psUnit = (String) sanction.getPsSanctionUnit();
				if(psAmount > 0 && !TextUtil.isEmpty(psUnit)){
					sanctionDescription += " ("+psAmount+" "+psUnit+")";
				}
				String sanctionNote = !TextUtil.isEmpty(sanction.getPsSanctionNote()) ? " - " + TextUtil.print(sanction.getPsSanctionNote()) : "";
				CorisCaseHistoryCommon.addCell(table, Constants.dateFormatCoris.format(sanction.getPsSanctionDate()) + "  " + sanctionDescription + sanctionNote, normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
		} catch(Exception e) {
			logger.error("Failed to generate sanction history", e);
			e.printStackTrace();
		}
		return table;
	}
	
	private PdfPTable generateSectionTitleTab(String tabText, int numberOfBlankRows, BaseColor backgroundColor){

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(new float[]{1.0f});
			
			// spacer rows
			for (int i=0; i < numberOfBlankRows; i++)
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			// header line
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 14f, Font.NORMAL);
			headerFont.setColor(BaseColor.WHITE);
			Paragraph paragraf = new Paragraph(16, tabText, headerFont);
			paragraf.setAlignment(Element.ALIGN_LEFT);
			PdfPCell headerCell = new PdfPCell(paragraf);
			headerCell.setBackgroundColor(backgroundColor);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(headerCell);
			
		}catch(DocumentException e){
			logger.error("Failed to generate section tab");
			e.printStackTrace();
		}
		
		return table;
	}
	
	private PdfPTable generateTitleTab(String tabText){

		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(new float[]{1.0f});
			
			// spacer rows
			for (int i=0; i < 2; i++)
				CorisCaseHistoryCommon.addCell(table, " ", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			// header line
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 14f, Font.NORMAL);
			Paragraph paragraf = new Paragraph(16, tabText, headerFont);
			paragraf.setAlignment(Element.ALIGN_LEFT);
			PdfPCell headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(headerCell);
			
		}catch(DocumentException e){
			logger.error("Failed to generate title tab");
			e.printStackTrace();
		}
		
		return table;
	}

	private PdfPTable generateReferralInfo(List<PsReferralBO> refCases) {
		
		float[] widths = new float[2];
		widths[0] = 30.0f;
		widths[1] = 70.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
			
			PsReferralBO vo = refCases.get(0);
			CorisCaseHistoryCommon.addCell(table, "Referral Date:", boldFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, Constants.dateFormatCoris.format(vo.getPsReferralDate()), normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			CorisCaseHistoryCommon.addCell(table, "Accepted:", boldFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			PsStatusDefnBO status = new PsStatusDefnBO().where(PsStatusDefnBO.PSSTATUSDEFNID, vo.getPsStatusDefnId()).find();
			CorisCaseHistoryCommon.addCell(table, "Admitted".equalsIgnoreCase(status.getPsStatusDescr())?"Yes":"", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			CorisCaseHistoryCommon.addCell(table, "First Appearence Date:", boldFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			List<PsReferralHistoryBO> firstApps = new PsReferralHistoryBO()
											.includeFields(PsReferralHistoryBO.PSACTIONDATE)
											.where(PsReferralHistoryBO.PSREFERRALID,vo.getPsReferralId())
											.includeTables(new PsActionDefnBO().where(PsActionDefnBO.PSACTIONCODE,"FIRSTAPP"))
											.addForeignKey(PsReferralHistoryBO.PSACTIONDEFNID, PsActionDefnBO.PSACTIONDEFNID)
											.search();
			CorisCaseHistoryCommon.addCell(table, firstApps.size()>0?Constants.dateFormatCoris.format(firstApps.get(0).getPsActionDate()):"", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			table.addCell(generateFailAndSuccessDateCell(vo));

			CorisCaseHistoryCommon.addCell(table, "Referring Cases:", boldFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			for(PsReferralBO caseRef:refCases){
				String caseNum = new KaseBO((String)caseRef.get(PsReferralCaseBO.JURISDICTION))
											.where(KaseBO.INTCASENUM,caseRef.get(PsReferralCaseBO.CASEIDENTIFIER))
											.find(KaseBO.CASENUM).getCaseNum();
				String cellTxt = "     " + caseNum + " - " + caseRef.get(CourtProfileBO.COURTTITLE);
				CorisCaseHistoryCommon.addCell(table, cellTxt, normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 2);
			}
			
		}catch(Exception e){
			logger.error("Failed to generate case referral detail info", e);
			e.printStackTrace();
		}
		return table;
	}


	private PdfPTable generateCaseInfo(PsReferralBO ref){
		
		float[] widths = new float[2];
		widths[0] = 30.0f;
		widths[1] = 70.0f;

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(widths);
		
			PsParticipantBO participant = new PsParticipantBO().includeFields(PsParticipantBO.ALL_FIELDS).where(PsParticipantBO.PSPARTICIPANTID, ref.getPsParticipantId()).find();
			CorisCaseHistoryCommon.addCell(table, "Name:", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, participant.getPsFirstName() + " " + participant.getPsLastName(), boldFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			CorisCaseHistoryCommon.addCell(table, "Referral Number:", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, String.valueOf(ref.getPsReferralId()), boldFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			CorisCaseHistoryCommon.addCell(table, "Problem Solving Court:", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, (String)ref.get(PsCourtDefnBO.PSCODEDESCR), boldFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			CorisCaseHistoryCommon.addCell(table, "Court Location:", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			CorisCaseHistoryCommon.addCell(table, (String)ref.get(CourtProfileBO.COURTTITLE), boldFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

		}catch(Exception e){
			logger.error("Failed to generate referral case info", e);
			e.printStackTrace();
		}
		return table;
	}
	
	private static void closeDocument(Document document, PdfWriter writer) throws DocumentException, IOException {
		populateTemplate(writer);
		document.close();
	}
	
	private static void populateTemplate(PdfWriter writer) throws DocumentException, IOException {
		template.beginText();
		template.setFontAndSize(normalFont.getBaseFont(), fontsize);
		template.showText(Integer.toString(writer.getPageNumber()));
		template.endText();
	}
	
	public void onEndPage(PdfWriter writer, Document document) {
		Date today = Calendar.getInstance().getTime();
		String signatureDate = Constants.dateTimeFormat.format(today);
		Rectangle pg = document.getPageSize();
    	PdfContentByte cb = writer.getDirectContent();
    	String footerPage = "Page " + writer.getPageNumber() + " of ";
    	cb.beginText();
    	cb.setFontAndSize(normalFont.getBaseFont(), fontsize);
    	cb.showTextAligned(PdfContentByte.ALIGN_LEFT,signatureDate, pg.getLeft() + 40, 2, 0);
    	cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, footerPage, pg.getRight() - 65, 2, 0);
    	cb.endText();
    	
        cb.addTemplate(template, pg.getRight() - 65, 2);
    	if (writer.getPageNumber() > 1 ){
    		cb.moveTo(40, 760);
    		cb.lineTo(565, 760);
    		cb.closePathStroke();
    	}
	}	
	
	private PdfPCell generateFailAndSuccessDateCell(PsReferralBO vo){
		float[] widths = new float[4];
		widths[0] = 30.0f;
		widths[1] = 20.0f;
		widths[2] = 30.0f;
		widths[3] = 20.0f;

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		PdfPCell cell = new PdfPCell(table);
		try {
			table.setWidths(widths);
			
			CorisCaseHistoryCommon.addCell(table, "Terminate/Unsuccessful Date:", boldFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			List<PsReferralHistoryBO> fail = new PsReferralHistoryBO()
													.includeFields(PsReferralHistoryBO.PSACTIONDATE)
													.where(PsReferralHistoryBO.PSREFERRALID,vo.getPsReferralId())
													.includeTables(new PsActionDefnBO().where(PsActionDefnBO.PSACTIONCODE,"TERMINAT"))
													.addForeignKey(PsReferralHistoryBO.PSACTIONDEFNID, PsActionDefnBO.PSACTIONDEFNID)
													.search();
			CorisCaseHistoryCommon.addCell(table, fail.size()>0?Constants.dateFormatCoris.format(fail.get(0).getPsActionDate()):"", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			CorisCaseHistoryCommon.addCell(table, "Successful Completion Date:", boldFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			List<PsReferralHistoryBO> success = new PsReferralHistoryBO()
													.includeFields(PsReferralHistoryBO.PSACTIONDATE)
													.where(PsReferralHistoryBO.PSREFERRALID,vo.getPsReferralId())
													.includeTables(new PsActionDefnBO().where(PsActionDefnBO.PSACTIONCODE,"GRADUATE"))
													.addForeignKey(PsReferralHistoryBO.PSACTIONDEFNID, PsActionDefnBO.PSACTIONDEFNID)
													.search();
			CorisCaseHistoryCommon.addCell(table, success.size()>0?Constants.dateFormatCoris.format(success.get(0).getPsActionDate()):"", normalFont, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
		}catch(Exception e){
			
		}
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setColspan(2);
		return cell;
	}

}
