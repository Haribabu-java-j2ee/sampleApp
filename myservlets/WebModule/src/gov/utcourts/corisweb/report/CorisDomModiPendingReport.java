package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.domesticmodifications.DomesticModificationsBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisinterface.util.CorisCaseHistoryCommon;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 */
public class CorisDomModiPendingReport extends ReportBaseHandler {
	private static Logger logger = Logger.getLogger(CorisDomModiPendingReport.class.getName());
	CorisDomModiPendingReportSearchCriteria rptCriteria;
	
	/**
	 * @param criteria
	 */
	public CorisDomModiPendingReport(ReportBaseSearchCriteria criteria) {
		super(criteria);
		rptCriteria = (CorisDomModiPendingReportSearchCriteria) criteria;
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.ReportBaseHandler#generateReport(java.util.List)
	 */
	@Override
	public byte[] generateReport(List<?> reportData) throws Exception {
		@SuppressWarnings("unchecked")
		List<KaseBO> kases = (List<KaseBO>)reportData;
		if ("pdf".equalsIgnoreCase(rptCriteria.getReportformat())) {
			return generatePdfDocReport(kases);
		} else if("xlsx".equalsIgnoreCase(rptCriteria.getReportformat())) {
			return generateExcelDocReport(kases);
		}
		return null;
	}

	/**
	 * @param kases
	 * @return
	 * @throws Exception
	 */
	private byte[] generatePdfDocReport(List<KaseBO> kases) throws Exception {
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
			document.addCreator("Utah State Court -- AOC");
			document.addTitle("Domestic Modification Pending Report");
			document.setMargins(20f, 22f, 25f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(kases);
			document.add(table);
			document.close();
			
		} catch (Exception e) {
			logger.error("Failed to generate PDF report.", e);
			throw e;
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return baos.toByteArray();
	}
	
	/**
	 * @param kases
	 * @return
	 * @throws Exception
	 */
	private PdfPTable generatePdfResultTable(List<KaseBO> kases) throws Exception {
		PdfPTable table = new PdfPTable(new float[] {15, 15, 20, 20, 15, 15});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		generatePdfReportContents(table, kases);
		return table;
	}

	/**
	 * @param table
	 * @param kases
	 * @throws Exception
	 */
	private void generatePdfReportContents(PdfPTable table, List<KaseBO> kases) throws Exception {
		HashMap<String, String> logNames = null;
		List<PartyBO> parties = null;
		for(KaseBO kase:kases){
			logNames = CorisDomModiPendingReport.getLogNameMap(kase.getUseridSrl(), kase.getAssnJudgeId(), kase.getAssnCommId(), rptCriteria.getCourtReadOnlyDB());
			parties = CorisDomModiPendingReport.getCaseParties(kase.getIntCaseNum(), rptCriteria.getCourtReadOnlyDB());
			long pendingDays = (long)(Calendar.getInstance().getTimeInMillis() - ((Date)kase.get(DomesticModificationsBO.STARTDATE)).getTime())/86400000;
			table.addCell(getPdfContentCell(kase.getCaseNum()));
			table.addCell(getPdfContentCell(logNames.get("J")));
			table.addCell(getPdfContentCell(logNames.get("C")));
			table.addCell(getPdfContentCell(TextUtil.printDate((Date)kase.get(DomesticModificationsBO.STARTDATE))));
			table.addCell(getPdfContentCell(String.valueOf(pendingDays)));
			table.addCell(getPdfContentCell(logNames.get("K")));
			for(PartyBO party:parties){
				table.addCell(getPdfContentCell(""));
				PdfPCell cell = getPdfContentCell(party.get(PartyCaseBO.PARTYCODE) + " " + party.getFirstName() + " " + party.getLastName());
				cell.setColspan(5);
				table.addCell(cell);
				
			}
		}
		
	}

	/**
	 * @param kases
	 * @return
	 * @throws Exception
	 */
	private byte[] generateExcelDocReport(List<KaseBO> kases) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Domestic Modification Pending Report");
		int rowCount = 0;
		Row row = null;

		try {
			rowCount = createExcelReportTitle(sheet);
			row = sheet.createRow(rowCount); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (KaseBO kase : kases ) {
				row = sheet.createRow(++rowCount);
				generateExcelRowData(row, kase);
				List<PartyBO> parties = CorisDomModiPendingReport.getCaseParties(kase.getIntCaseNum(), rptCriteria.getCourtReadOnlyDB());
				for(PartyBO p:parties){
					row = sheet.createRow(++rowCount);
					Cell cell = row.createCell(1);
					cell.setCellValue(p.get(PartyCaseBO.PARTYCODE) + " " + p.getFirstName() + " " + p.getLastName());
				}
			}
			for(int c=0; c < 5; c++){
				sheet.autoSizeColumn(c);
			}
			workbook.write(baos);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		
		return baos.toByteArray();
	}

	

	/**
	 * @param row
	 * @param kase
	 * @throws Exception
	 */
	private void generateExcelRowData(Row row, KaseBO kase) throws Exception {
		HashMap<String,String> logNames = CorisDomModiPendingReport.getLogNameMap(kase.getUseridSrl(), kase.getAssnJudgeId(), kase.getAssnCommId(), rptCriteria.getCourtReadOnlyDB());
		long pendingDays = (long)(Calendar.getInstance().getTimeInMillis() - ((Date)kase.get(DomesticModificationsBO.STARTDATE)).getTime())/86400000;
		
		Cell cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(kase.getCaseNum()));
		cell = row.createCell(1);
		cell.setCellValue(logNames.get("J"));
		cell = row.createCell(2);
		cell.setCellValue(logNames.get("C"));
		cell = row.createCell(3);
		cell.setCellValue(TextUtil.printDate((Date)kase.get(DomesticModificationsBO.STARTDATE)));
		cell = row.createCell(4);
		cell.setCellValue(pendingDays);
		cell = row.createCell(5);
		cell.setCellValue(logNames.get("K"));
		
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.ReportBaseHandler#populatePdfReportTitle()
	 */
	@Override
	protected PdfPTable populatePdfReportTitle() throws Exception {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(new float[] {1.0f});
			
			CorisCaseHistoryCommon.addCell(table, " ", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			// header line
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 14f, Font.NORMAL);
			Paragraph paragraf = new Paragraph(16, rptCriteria.getLocnDescr(), headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			paragraf = new Paragraph(16, "Domestic Modification Pending Report", headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			paragraf = new Paragraph(16, "FROM " + TextUtil.printDate(rptCriteria.getStartDate()) + " to " + TextUtil.printDate(rptCriteria.getEndDate()), headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			headerCell = new PdfPCell(new Phrase("\n"));
			headerCell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(headerCell);
			
		} catch (DocumentException e) {
			logger.error("Failed to populate Pdf report title.", e);
			throw e;
		}
		
		return table;
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.ReportBaseHandler#populatePdfTableColumnHeaders(com.itextpdf.text.pdf.PdfPTable)
	 */
	@Override
	protected void populatePdfTableColumnHeaders(PdfPTable table) throws Exception {
		table.addCell(getPdfHeaderCell("Case Number"));
		table.addCell(getPdfHeaderCell("Judge"));
		table.addCell(getPdfHeaderCell("Commissioner"));
		table.addCell(getPdfHeaderCell("Start Date"));
		table.addCell(getPdfHeaderCell("Pending Days"));
		table.addCell(getPdfHeaderCell("Filed By"));
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.ReportBaseHandler#populateExcelColumnHeaders(org.apache.poi.ss.usermodel.Row, org.apache.poi.xssf.usermodel.XSSFSheet)
	 */
	@Override
	protected void populateExcelColumnHeaders(Row row, XSSFSheet sheet) throws Exception {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Cell cell = row.createCell(0);
		cell.setCellValue("Case Number");
		cell.setCellStyle(cStyle);
		cell = row.createCell(1);
		cell.setCellValue("Judge");
		cell.setCellStyle(cStyle);
		cell = row.createCell(2);
		cell.setCellValue("Commissioner");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellValue("Start Date");
		cell.setCellStyle(cStyle);
		cell = row.createCell(4);
		cell.setCellValue("Pending Days");
		cell.setCellStyle(cStyle);
		cell = row.createCell(5);
		cell.setCellValue("Filed By");
		cell.setCellStyle(cStyle);

	}
	/**
	 * @param sheet
	 * @return
	 */
	private int createExcelReportTitle(XSSFSheet sheet) {
		
		Row row = sheet.createRow(1); //skip the first row as old report does
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Cell cell = row.createCell(0);
		cell.setCellValue(rptCriteria.getLocnDescr());
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(2); //skip the first row as old report does
		cell = row.createCell(0);
		cell.setCellValue("Domestic Modification Pending Report");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(3); //skip the first row as old report does
		cell = row.createCell(0);
		cell.setCellValue("Report Date Range: from " + TextUtil.printDate(rptCriteria.getStartDate()) + " to " + TextUtil.printDate(rptCriteria.getEndDate()));
		cell.setCellStyle(cStyle);
		
		return 5; //skip one row to start column header
	}

	/**
	 * @param userId
	 * @param jId
	 * @param cId
	 * @param dbType
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, String> getLogNameMap(int userId, int jId, int cId, String dbType) throws Exception{
		HashMap<String, String> logNames = new HashMap<String, String>();
		
		List<PersonnelBO> persons = new PersonnelBO(dbType).includeFields(PersonnelBO.LOGNAME, PersonnelBO.TYPE)
										.where(new Expression(PersonnelBO.USERIDSRL, Exp.EQUALS, userId,
												Exp.OR, PersonnelBO.USERIDSRL, Exp.EQUALS, jId,
												Exp.OR, PersonnelBO.USERIDSRL, Exp.EQUALS, cId))
										.search();
		for(PersonnelBO p:persons){
			logNames.put(p.getType(), p.getLogname());
		}
		
		return logNames;
		
	}
	
	/**
	 * @param intCaseNum
	 * @param courtType
	 * @return
	 * @throws Exception
	 */
	public static List<PartyBO> getCaseParties(int intCaseNum, String courtType) throws Exception{
		return new PartyBO(courtType).includeFields(PartyBO.FIRSTNAME, PartyBO.LASTNAME)
									.includeTables(new PartyCaseBO(courtType).includeFields(PartyCaseBO.PARTYCODE)
														.where(PartyCaseBO.INTCASENUM,intCaseNum)
														.where(PartyCaseBO.PARTYCODE, Exp.IN, new String[]{"PET","RES","PLA","DEF"}))
									.addForeignKey(PartyBO.PARTYNUM, PartyCaseBO.PARTYNUM).search();
	}

}
