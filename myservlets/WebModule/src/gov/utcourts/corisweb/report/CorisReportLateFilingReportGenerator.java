package gov.utcourts.corisweb.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.notifications.util.CalendarUtil;

public class CorisReportLateFilingReportGenerator extends ReportBaseHandler {

	private CorisReportLateFilingReportCriteria myCriteria;
	
	private static Logger logger = Logger.getLogger(CorisAttorneyCasesReportGenerator.class.getName());
	private static String SERVLET_NAME = "CorisAttorneyCasesServlet";
	
	public CorisReportLateFilingReportGenerator(ReportBaseSearchCriteria criteria) {
		super(criteria);
		myCriteria = (CorisReportLateFilingReportCriteria) criteria;
	}

	@Override
	public byte[] generateReport(List<?> lateCaseFilingList) {
		if ("pdf".equals(myCriteria.getReportformat())) {
			return generatePdfDocReport((List<KaseBO>) lateCaseFilingList);
		} else if("excel".equalsIgnoreCase(myCriteria.getReportformat())) {
			return generateExcelDocReport((List<KaseBO>)lateCaseFilingList);
		}
		return null;
	}
	
	private byte[] generateExcelDocReport(List<KaseBO> lateCaseFilingList) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Attorney Cases Report");
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (KaseBO doc : lateCaseFilingList) {
				row = sheet.createRow(++rowCount);
				generateExcelRowData(row, doc);
			}
			workbook.write(baos);
		} catch (Exception e) {
			logger.error(e.getMessage());
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
	
	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populateExcelColumnHeaders(org.apache.poi.ss.usermodel.Row, org.apache.poi.xssf.usermodel.XSSFSheet)
	 */
	@Override
	protected void populateExcelColumnHeaders(Row row, XSSFSheet sheet) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Cell cell = row.createCell(0);
		cell.setCellValue("Case Number");
		cell.setCellStyle(cStyle);
		cell = row.createCell(1);
		cell.setCellValue("Party First Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(2);
		cell.setCellValue("Party Last Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellValue("Citation");
		cell.setCellStyle(cStyle);
		cell = row.createCell(4);
		cell.setCellValue("Case Filed Date");
		cell.setCellStyle(cStyle);
		cell = row.createCell(5);
		cell.setCellValue("Violation Date");
		cell.setCellStyle(cStyle);
		cell = row.createCell(6);
		cell.setCellValue("Difference");
		cell.setCellStyle(cStyle);
	}

	/**
	 * @param row
	 * @param doc
	 * @throws Exception
	 */
	protected void generateExcelRowData(Row row, KaseBO doc) throws Exception {
		Cell cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(doc.get(KaseBO.CASENUM)));
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(doc.get(PartyBO.FIRSTNAME)));
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(doc.get(PartyBO.LASTNAME)));
		cell = row.createCell(3);
		cell.setCellValue(TextUtil.print(doc.get(CrimCaseBO.CITNUM)));
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.printDate(doc.getFilingDate()));
		cell = row.createCell(5);
		cell.setCellValue(TextUtil.printDate((Date) doc.get(ChargeBO.VIOLDATETIME)));
		cell = row.createCell(6);
		cell.setCellValue(TextUtil.print(Math.round(CalendarUtil.diffDays(doc.getFilingDate(), (Date) doc.get(ChargeBO.VIOLDATETIME)))));
	}
	
	private byte[] generatePdfDocReport(List<KaseBO> lateCaseFilingList) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfPTable table = null;
		
		try {
			// ///////////////////////////////////////////////////////////////
			// Create new document
			// ///////////////////////////////////////////////////////////////
			Document document = new Document(PageSize.LETTER.rotate());
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			writer.setPageEvent(this);
			
			// ///////////////////////////////////////////////////////////////
			// Document Set Up - Left, Right, Top, Bottom
			// ///////////////////////////////////////////////////////////////
			document.addCreationDate();
			document.addCreator("Utah State Court -- AOC");
			document.addTitle("CORIS Late Filing Report");
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			// ///////////////////////////////////////////////////////////////
			// Document Set Up - Left, Right, Top, Bottom
			// ///////////////////////////////////////////////////////////////
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(lateCaseFilingList, myCriteria.getCourtType());
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
	
	/**
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 * @return
	 */
	private PdfPTable generatePdfResultTable(List<KaseBO> lateCaseFilingList, String courtType) {
		PdfPTable table = new PdfPTable(new float[] {15, 15, 15, 20, 20, 20});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		// start result rows
		generatePdfReportContents(table, lateCaseFilingList, courtType);
		return table;
	}
	
	/**
	 * @param table
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 */
	private void generatePdfReportContents(PdfPTable table, List<KaseBO> lateCaseFilingList, String courtType) {
		for(KaseBO temp : lateCaseFilingList){
			try{
				String party = (String) temp.get(PartyBO.LASTNAME);
				if (!TextUtil.isEmpty((String) temp.get(PartyBO.FIRSTNAME))) {
					party += ", "+ temp.get(PartyBO.FIRSTNAME);
				}
				
				table.addCell(getPdfContentCell(TextUtil.print(temp.getCaseNum())));
				table.addCell(getPdfContentCell(TextUtil.print(party)));
				table.addCell(getPdfContentCell(TextUtil.print(temp.get(CrimCaseBO.CITNUM))));
				table.addCell(getPdfContentCell(TextUtil.printDate(temp.getFilingDate())));
				table.addCell(getPdfContentCell(TextUtil.printDate((Date) temp.get(ChargeBO.VIOLDATETIME))));
				table.addCell(getPdfContentCell(TextUtil.print(Math.round(CalendarUtil.diffDays(temp.getFilingDate(), (Date) temp.get(ChargeBO.VIOLDATETIME))))));
			} catch (Exception e) {
				logger.error("Failed to generate Pdf report content.", e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfTableColumnHeaders(com.itextpdf.text.pdf.PdfPTable)
	 */
	@Override
	protected void populatePdfTableColumnHeaders(PdfPTable table) {
		table.addCell(getPdfHeaderCell("Case Number"));
		table.addCell(getPdfHeaderCell("Party Name"));
		table.addCell(getPdfHeaderCell("Citation"));
		table.addCell(getPdfHeaderCell("Case Filed Date"));
		table.addCell(getPdfHeaderCell("Violation Date"));
		table.addCell(getPdfHeaderCell("Difference"));
		
		table.setHeaderRows(1);
	}
	
	public static void addCell(PdfPTable table, String remarks, Font fontStyle, int alignment, int border, int colSpan) {
		PdfPCell theCell = new PdfPCell();
		theCell.setColspan(colSpan);
		Paragraph paragraph = new Paragraph(16, remarks, fontStyle);
		paragraph.setAlignment(alignment);
		theCell.addElement(paragraph);
		theCell.setBorder(border);
		theCell.setPaddingTop(5f);
		table.addCell(theCell);
		theCell = null;
		paragraph = null;
	}
	
	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfReportTitle()
	 */
	@Override
	protected PdfPTable populatePdfReportTitle() {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		String location = "";
	
		try {
			location = new LocationBO(myCriteria.getCourtType())
					.where(LocationBO.COURTTYPE, myCriteria.getCourtType())
					.where(LocationBO.LOCNCODE, myCriteria.getLocnCode())
					.find(LocationBO.LOCNDESCR).getLocnDescr();

			table.setWidths(new float[] {1.0f});
			
			// header line
			Font headerFont = FontFactory.getFont(FontFactory.COURIER, 12f, Font.BOLD);
			Paragraph paragraf = new Paragraph(16, "Late Filing Report", headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			
			paragraf = new Paragraph(16, "Location: " + location, headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			if (myCriteria.getCaseFilingFrom() != null) {
				paragraf = new Paragraph(16, "Cases Filed From: " + TextUtil.printDate(myCriteria.getCaseFilingFrom()) + " To: " + TextUtil.printDate(myCriteria.getCaseFilingTo()), headerFont);
				paragraf.setAlignment(Element.ALIGN_CENTER);
				headerCell = new PdfPCell(paragraf);
				headerCell.setBorder(PdfPCell.NO_BORDER);
				headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				table.addCell(headerCell);
			}
			if (myCriteria.getViolationDateFrom() != null) {
				paragraf = new Paragraph(16, "Cases With Violation Dates From: " + TextUtil.printDate(myCriteria.getViolationDateFrom()) + " To: " + TextUtil.printDate(myCriteria.getViolationDateTo()), headerFont);
				paragraf.setAlignment(Element.ALIGN_CENTER);
				headerCell = new PdfPCell(paragraf);
				headerCell.setBorder(PdfPCell.NO_BORDER);
				headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				table.addCell(headerCell);
			}
			paragraf = new Paragraph(16, "" , headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.BOTTOM);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
		} catch (Exception e) {
			logger.error("Failed to populate Pdf report title.", e);
			e.printStackTrace();
		}
		
		return table;
	}

}
