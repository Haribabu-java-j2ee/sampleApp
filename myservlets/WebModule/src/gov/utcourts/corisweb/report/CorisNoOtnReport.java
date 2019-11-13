package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dto.NoOtnReportDTO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisinterface.util.CorisCaseHistoryCommon;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class CorisNoOtnReport extends ReportBaseHandler {
	private static Logger logger = Logger.getLogger(CorisNoOtnReport.class.getName());
	private CorisNoOtnReportSearchCriteria rptCriteria;
	
	public CorisNoOtnReport(ReportBaseSearchCriteria criteria) {
		super(criteria);
		rptCriteria = (CorisNoOtnReportSearchCriteria) criteria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public byte[] generateReport(List<?> reportData) throws Exception {
		if ("pdf".equalsIgnoreCase(rptCriteria.getReportformat())) {
			return generatePdfDocReport((List<NoOtnReportDTO>)reportData);
		} else if("xlsx".equalsIgnoreCase(rptCriteria.getReportformat())) {
			return generateExcelDocReport((List<NoOtnReportDTO>)reportData);
		}
		return null;
	}

	private byte[] generateExcelDocReport(List<NoOtnReportDTO> rptData) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Document Report");
		int rowCount = 0;
		Row row = null;
		List<KaseBO> kases = rptData.size()>0? rptData.get(0).getKases():new ArrayList<KaseBO>();
		try {
			rowCount = createExcelReportTitle(sheet);
			row = sheet.createRow(rowCount); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (KaseBO kase : kases ) {
				if(isNoOTNCitiationCase(kase)){
					row = sheet.createRow(++rowCount);
					generateExcelRowData(row, kase);
				}
			}
			generateExcelSummaryRowData(sheet, ++rowCount, rptData.get(0));
			for(int c=0; c < 3; c++){
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
	
private int createExcelReportTitle(XSSFSheet sheet) {
		
		Row row = sheet.createRow(1); //skip the first row as old report does
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Cell cell = row.createCell(0);
		cell.setCellValue(rptCriteria.getLocnDescr());
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(2); //skip the first row as old report does
		cell = row.createCell(0);
		cell.setCellValue("No OTN Report");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(3); //skip the first row as old report does
		cell = row.createCell(0);
		cell.setCellValue("Report Date Range: from " + TextUtil.printDate(rptCriteria.getStartDate()) + " to " + TextUtil.printDate(rptCriteria.getEndDate()));
		cell.setCellStyle(cStyle);
		
		return 5; //skip one row to start column header
	}

	private void generateExcelSummaryRowData(XSSFSheet sheet, int rowCount, NoOtnReportDTO dto) {
		rowCount++;
		Row row = sheet.createRow(rowCount);
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Cell cell = row.createCell(0);
		cell.setCellValue("Summary:");
		cell.setCellStyle(cStyle);

		createSummaryExcelRows(sheet, rowCount+1, "Total Case Eligible:", dto.getTotalCases(), cStyle);
		createSummaryExcelRows(sheet, rowCount+2, "Cases with OTNs:", dto.getTotalOtns(), cStyle);
		createSummaryExcelRows(sheet, rowCount+3, "Cases with Valid Citations (No OTN):", dto.getTotalCitations(), cStyle);
		createSummaryExcelRows(sheet, rowCount+4, "Cases with neither OTN nor Citation:", dto.getCasesWithout(), cStyle);
		createSummaryExcelRows(sheet, rowCount+5, "Cases with Date of Arrest:", dto.getCasesWithArrestDate(), cStyle);
		createSummaryExcelRows(sheet, rowCount+6, "Cases with Birth Dates:", dto.getCasesWithDob(), cStyle);
		createSummaryExcelRows(sheet, rowCount+7, "Cases with DOB and OTN:", dto.getCasesWithDobOtn(), cStyle);
		createSummaryExcelRows(sheet, rowCount+8, "Cases with DOA and OTN:", dto.getCasesWithDoaOtn(), cStyle);
		createSummaryExcelRows(sheet, rowCount+9, "Total Charges:", dto.getTotalCharges(), cStyle);
		createSummaryExcelRows(sheet, rowCount+10, "Total Felony Charges:", dto.getTotalFelonyCharges(), cStyle);
		createSummaryExcelRows(sheet, rowCount+11, "Total Misdem Charges:", dto.getTotalMisdemCharges(), cStyle);
	}
	
	private void createSummaryExcelRows(XSSFSheet sheet, int rowCount, String c1v, int c2v, CellStyle cStyle){
		Row row = sheet.createRow(rowCount);
		Cell cell = row.createCell(0);
		cell.setCellValue(c1v);
		cell.setCellStyle(cStyle);
		cell = row.createCell(1);
		cell.setCellValue(c2v);
	}

	private void generateExcelRowData(Row row, KaseBO kase) {
		Cell cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(kase.getCaseNum()));
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(kase.get(PartyBO.FIRSTNAME)) + " " + kase.get(PartyBO.LASTNAME));
		cell = row.createCell(2);
		if("N".equalsIgnoreCase((String) kase.get(PartyCaseBO.OTNAVAILABLE))){
			cell.setCellValue("(OTN Not Available)");
		}
	}

	private byte[] generatePdfDocReport(List<NoOtnReportDTO> rptData) {
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
			document.addTitle("No OTN Report");
			document.setMargins(20f, 22f, 25f, 30f);
			document.open();
			
			// Title
			NoOtnReportDTO result = rptData.get(0);
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(result.getKases());
			document.add(table);
			//summary table
			table = populateSummeryTable(result);
			document.add(table);
			document.close();
			
		} catch (Exception e) {
			logger.error("Failed to generate PDF report.", e);
			e.printStackTrace();
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return baos.toByteArray();
	}

	private PdfPTable populateSummeryTable(NoOtnReportDTO result) {
		PdfPTable table = new PdfPTable(new float[] {50f,50f});
		table.setWidthPercentage(100);
		Paragraph p = new Paragraph();
		DottedLineSeparator dottedline = new DottedLineSeparator();
		dottedline.setOffset(-2);
		dottedline.setGap(2f);
		dottedline.setAlignment(DottedLineSeparator.ALIGN_TOP);
		p.add(dottedline);
		
		PdfPCell cell = new PdfPCell(p);
		cell.setColspan(12);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		table.addCell(cell);
		
		Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 12f, Font.BOLDITALIC);
		p = new Paragraph("Summary:", headerFont);
		cell = new PdfPCell(p);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		
		addSummaryPdfCell(table,"Total Cases Eligible:", result.getTotalCases());
		addSummaryPdfCell(table,"Cases with OTNs:", result.getTotalOtns());
		addSummaryPdfCell(table,"Cases with Valid Citations (No OTN):", result.getTotalCitations());
		addSummaryPdfCell(table,"Cases with neither OTN nor Citation:", result.getCasesWithout());
		addSummaryPdfCell(table,"Cases with Date of Arrest:", result.getCasesWithArrestDate());
		addSummaryPdfCell(table,"ases with Birth Dates:", result.getCasesWithDob());
		addSummaryPdfCell(table,"Cases with DOB and OTN:", result.getCasesWithDobOtn());
		addSummaryPdfCell(table,"Cases with DOA and OTN:", result.getCasesWithDoaOtn());
		addSummaryPdfCell(table,"Total Charges:", result.getTotalCharges());
		addSummaryPdfCell(table,"Total Felony Charges:", result.getTotalFelonyCharges());
		addSummaryPdfCell(table,"Total Misdem Charges:", result.getTotalMisdemCharges());	
		return table;
	}

	private void addSummaryPdfCell(PdfPTable table, String label, int val) {
		PdfPCell cell = getPdfHeaderCell(label);
		cell.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cell);
		cell = getPdfContentCell(" "+ val);
		table.addCell(cell);
	}

	private PdfPTable generatePdfResultTable(List<KaseBO> kases) {
		PdfPTable table = new PdfPTable(new float[] {20, 40, 40});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		generatePdfReportContents(table, kases);
		return table;
	}

	@Override
	protected PdfPTable populatePdfReportTitle() {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		try {
			table.setWidths(new float[] {1.0f});
			
			// spacer rows
//			for (int i = 0; i < 2; i++)
			CorisCaseHistoryCommon.addCell(table, " ", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			// header line
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 14f, Font.NORMAL);
			Paragraph paragraf = new Paragraph(16, rptCriteria.getLocnDescr(), headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			paragraf = new Paragraph(16, "BCI cases with neither OTN nor valid Citation", headerFont);
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
			e.printStackTrace();
		}
		return table;
	}

	@Override
	protected void populatePdfTableColumnHeaders(PdfPTable table) {
		table.addCell(getPdfHeaderCell("Case Number"));
		table.addCell(getPdfHeaderCell("Defendant Name"));
		table.addCell(getPdfHeaderCell("OTN Available"));
		table.setHeaderRows(1);
	}
	
	private void generatePdfReportContents(PdfPTable table, List<KaseBO> kases) {
		try {
			for (KaseBO kase : kases) {
				if (isNoOTNCitiationCase(kase)) {
					table.addCell(getPdfContentCell(kase.getCaseNum()));
					table.addCell(getPdfContentCell(TextUtil.print(kase.get(PartyBO.FIRSTNAME)) + " " + kase.get(PartyBO.LASTNAME)));
					if("N".equalsIgnoreCase((String) kase.get(PartyCaseBO.OTNAVAILABLE))){
						table.addCell(getPdfContentCell("(OTN Not Available)"));
					}else {
						table.addCell(getPdfContentCell(""));
					}
				}
			}
		} catch (Exception e) {
			logger.error("Failed to generate Pdf report content.", e);
		}

	}
	
	public static boolean isNoOTNCitiationCase(KaseBO kase){
		return (TextUtil.isEmpty((String) kase.get(PartyCaseBO.OTN)) || "0".equals((String) kase.get(PartyCaseBO.OTN)) || "N".equalsIgnoreCase((String) kase.get(PartyCaseBO.OTNAVAILABLE))) 
		&& (TextUtil.isEmpty((String) kase.get(CrimCaseBO.CITNUM)) || "0".equals((String) kase.get(CrimCaseBO.CITNUM)));
//		return (!TextUtil.isEmpty((String) kase.get(PartyCaseBO.OTNAVAILABLE)) && "N".equalsIgnoreCase((String) kase.get(PartyCaseBO.OTNAVAILABLE))) && (TextUtil.isEmpty((String) kase.get(CrimCaseBO.CITNUM)) || "0".equals((String) kase.get(CrimCaseBO.CITNUM)));
	}

	@Override
	protected void populateExcelColumnHeaders(Row row, XSSFSheet sheet) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Cell cell = row.createCell(0);
		cell.setCellValue("Case Number");
		cell.setCellStyle(cStyle);
		cell = row.createCell(1);
		cell.setCellValue("Defendant Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(2);
		cell.setCellValue("OTN Available");
		cell.setCellStyle(cStyle);
	}

}
