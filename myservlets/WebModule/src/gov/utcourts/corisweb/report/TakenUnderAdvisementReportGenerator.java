package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.tracking.TrackingBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.DateUtil;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisinterface.util.CorisCaseHistoryCommon;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;

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

public class TakenUnderAdvisementReportGenerator extends ReportBaseHandler {

	private static Logger logger = Logger.getLogger(TakenUnderAdvisementReportGenerator.class.getName());
	
	private TakenUnderAdvisementReportSearchCriteria thisCriteria;
	public static final String REPORT_NAME = "Under Advisement Report";
	public String reportName = REPORT_NAME;
	private static final StringArrayDescriptor COLUMN_HEADERS = new StringArrayDescriptor("Review Date", "Case", "", "Name", "Set Date", "Clerk", "Judge", "Aged Days");
	private static final float[] PDF_COLUMN_WIDTHS = new float[] {12, 13, 6, 30, 12, 8, 11, 8};
		
	public TakenUnderAdvisementReportGenerator(ReportBaseSearchCriteria criteria) {
		super(criteria);
		thisCriteria = (TakenUnderAdvisementReportSearchCriteria) criteria;
		reportName = criteria.getReportName();
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#generateReport(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public byte[] generateReport(List<?> searchResults) throws Exception {
		if ("pdf".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generatePdf((List<TrackingBO>) searchResults);
		} else if ("xlsx".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generateExcel((List<TrackingBO>) searchResults);
		}
		return null;
	}
	
	/**
	 * @param List<TrackingBO>
	 * @return
	 */
	private byte[] generateExcel(List<TrackingBO> searchResults) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(getReportName());
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (TrackingBO data : searchResults) {
				row = sheet.createRow(++rowCount);
				generateExcelRowData(row, data);
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
		
		// column headers
		int i = 0;
		for (String header : COLUMN_HEADERS.getValues()) {
			Cell cell = row.createCell(i);
			cell.setCellValue(header);
			cell.setCellStyle(cStyle);
			i++;
		}
	}

	/**
	 * @param row
	 * @param doc
	 * @throws Exception
	 */
	protected void generateExcelRowData(Row row, TrackingBO result) throws Exception {
		// review date
		Cell cell = row.createCell(0);
		cell.setCellValue(TextUtil.printDate(result.getReviewDate()));
		
		// case number
		cell = row.createCell(1);
		cell.setCellValue((String) result.get(KaseBO.CASENUM));

		// case type
		cell = row.createCell(2);
		cell.setCellValue((String) result.get(KaseBO.CASETYPE));

		// case name
		StringBuilder caseName = new StringBuilder();
		caseName.append(result.get(PartyBO.LASTNAME));
		if (!TextUtil.isEmpty((String) result.get(PartyBO.FIRSTNAME))) {
			caseName.append(", ").append(result.get(PartyBO.FIRSTNAME));
		}
		cell = row.createCell(3);
		cell.setCellValue(TextUtil.print(caseName.toString()));
		caseName = null;
		
		// set date
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.printDate((Date) result.get(TrackingBO.REVIEWDATE)));
		
		// clerk
		cell = row.createCell(5);
		cell.setCellValue((String) result.get("clerk_logname"));
		
		// judge / comm
		cell = row.createCell(6);
		cell.setCellValue((String) result.get("judge_logname"));
		
		// aged days
		Date setDate = (Date) result.get(TrackingBO.CREATEDATETIME);
		int agedDays = DateUtil.getDaysBetween(new Date(), setDate);
		cell = row.createCell(7);
		cell.setCellValue(Integer.toString(agedDays));
		setDate = null;
	}
	
	/**
	 * @param docList
	 * @return
	 */
	private byte[] generatePdf(List<TrackingBO> searchResults) throws Exception {
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
			document.addTitle(getReportName());
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			// ///////////////////////////////////////////////////////////////
			// Document Set Up - Left, Right, Top, Bottom
			// ///////////////////////////////////////////////////////////////
			document.addTitle(getReportName());
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(searchResults);
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
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 * @return
	 */
	private PdfPTable generatePdfResultTable(List<TrackingBO> searchResults) {
		PdfPTable table = new PdfPTable(PDF_COLUMN_WIDTHS);
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		generatePdfReportContents(table, searchResults);
		return table;
	}
	
	/**
	 * @param table
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 */
	private void generatePdfReportContents(PdfPTable table, List<TrackingBO> searchResults) {
		if (searchResults != null && searchResults.size() > 0) {
			for (TrackingBO result : searchResults) {
				try {
					// review date
					table.addCell(getPdfContentCell(TextUtil.printDate(result.getReviewDate())));
					
					// case number
					table.addCell(getPdfContentCell(TextUtil.print(result.get(KaseBO.CASENUM))));
	
					// case type
					table.addCell(getPdfContentCell(TextUtil.print(result.get(KaseBO.CASETYPE))));
	
					// case name
					StringBuilder caseName = new StringBuilder();
					caseName.append(result.get(PartyBO.LASTNAME));
					if (!TextUtil.isEmpty((String) result.get(PartyBO.FIRSTNAME))) {
						caseName.append(", ").append(result.get(PartyBO.FIRSTNAME));
					}
					table.addCell(getPdfContentCell(caseName.toString()));
					caseName = null;
	
					// set date
					table.addCell(getPdfContentCell(TextUtil.printDate((Date) result.get(TrackingBO.REVIEWDATE))));
					
					// clerk
					table.addCell(getPdfContentCell((String) result.get("clerk_logname")));
					
					// judge / comm
					table.addCell(getPdfContentCell((String) result.get("judge_logname")));
					
					// aged days
					Date setDate = (Date) result.get(TrackingBO.CREATEDATETIME);
					int agedDays = DateUtil.getDaysBetween(new Date(), setDate);
					table.addCell(getPdfContentCell(Integer.toString(agedDays)));
					setDate = null;
				} catch (Exception e) {
					logger.error("Failed to generate Pdf report content.", e);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfTableColumnHeaders(com.itextpdf.text.pdf.PdfPTable)
	 */
	@Override
	protected void populatePdfTableColumnHeaders(PdfPTable table) {
		// add dotted line to separate header and table results
		Paragraph p = new Paragraph();
		DottedLineSeparator dottedline = new DottedLineSeparator();
		dottedline.setOffset(-2);
		dottedline.setGap(2f);
		dottedline.setAlignment(DottedLineSeparator.ALIGN_TOP);
		p.add(dottedline);
		
		PdfPCell c = new PdfPCell(p);
		c.setColspan(11);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		
		// column headers
		for (String header : COLUMN_HEADERS.getValues()) 
			table.addCell(getPdfHeaderCell(header));
		
		table.addCell(c);
		table.setHeaderRows(2);
	}
	
	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfReportTitle()
	 */
	@Override
	protected PdfPTable populatePdfReportTitle() throws Exception {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(new float[] {1.0f});
			
			// spacer rows
			for (int i = 0; i < 2; i++)
				CorisCaseHistoryCommon.addCell(table, " ", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			// header line
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 14f, Font.NORMAL);
			Paragraph paragraph = new Paragraph(16, thisCriteria.getLocnDescr(), headerFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCell = new PdfPCell(paragraph);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			paragraph = new Paragraph(16, getReportName(), headerFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			headerCell = new PdfPCell(paragraph);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			
			if (thisCriteria.getDateFrom() != null || thisCriteria.getDateTo() != null) {
				
				if (thisCriteria.getDateFrom() != null && thisCriteria.getDateTo() != null) {
					paragraph = new Paragraph(16, "from " + TextUtil.printDate(thisCriteria.getDateFrom()) + " through " + TextUtil.printDate(thisCriteria.getDateTo()), headerFont);
				} else if (thisCriteria.getDateFrom() != null && thisCriteria.getDateTo() == null) {
					paragraph = new Paragraph(16, "from " + TextUtil.printDate(thisCriteria.getDateFrom()), headerFont);
				} else if (thisCriteria.getDateFrom() == null && thisCriteria.getDateTo() == null) {
					paragraph = new Paragraph(16, "through " + TextUtil.printDate(thisCriteria.getDateTo()), headerFont);
				}	
				
				paragraph.setAlignment(Element.ALIGN_CENTER);
				headerCell = new PdfPCell(paragraph);
			} else {
				headerCell = new PdfPCell(new Phrase("\n"));
			}
			
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

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
}
