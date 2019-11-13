package gov.utcourts.corisweb.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attypresent.AttyPresentBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;

public class CorisReportAttorneyAppearanceReportGenerator extends ReportBaseHandler {

	private CorisReportAttorneyAppearanceReportCriteria myCriteria;
	private static Logger logger = Logger.getLogger(CorisReportAttorneyAppearanceReportGenerator.class.getName());
	private static String FILE_NAME = "CorisReportAttorneyAppearanceReportGenerator";
	
	public CorisReportAttorneyAppearanceReportGenerator(ReportBaseSearchCriteria criteria) {
		super(criteria);
		myCriteria = (CorisReportAttorneyAppearanceReportCriteria) criteria;
	}

	/**
	 * Generates the report based on the format selected (PDF, Excel, etc.)
	 * 
	 * @param List<?> searchResults
	 * @return byte[]
	 */
	@SuppressWarnings("unchecked")
	@Override
	public byte[] generateReport(List<?> searchResults) {
		if ("pdf".equalsIgnoreCase(myCriteria.getReportformat())) {
			return generatePdf((List<AttyPresentBO>) searchResults);
		} else if ("xlsx".equalsIgnoreCase(myCriteria.getReportformat())) {
			return generateExcel((List<AttyPresentBO>) searchResults);
		}
		return null;
	}
	
	/**
	 * Generates the Excel format of the report
	 * 
	 * @param List<AttyPresentBO> searchResults
	 * @return byte[]
	 */
	private byte[] generateExcel(List<AttyPresentBO> searchResults) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(myCriteria.getReportTitle());
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (AttyPresentBO data : searchResults) {
				row = sheet.createRow(++rowCount);
				generateExcelRowData(row, data);
			}
			workbook.write(baos);
		} catch (Exception e) {
			logger.error(FILE_NAME+" generateExcel(List<AttyPresentBO> searchResults)", e);
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				logger.error(FILE_NAME+" generateExcel(List<AttyPresentBO> searchResults)", e);
			}
		}
		
		return baos.toByteArray();
	}

	/**
	 * Populates the Excel column header rows.
	 * 
	 * @param Row row
	 * @param XSSFSheet sheet
	 */
	@Override
	protected void populateExcelColumnHeaders(Row row, XSSFSheet sheet) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		// column headers
		StringArrayDescriptor columnHeaders;
		columnHeaders = new StringArrayDescriptor(
				"Judge Name", 
				"Attorney Name", 
				"Attorney Bar Number", 
				"Attorney Bar State",
				"Location",
				"Count Misc", 
				"Count Trial",
				"Attorney Email Address"
		);			
		int i = 0;
		for (String header : columnHeaders.getValues()) {
			Cell cell = row.createCell(i);
			cell.setCellValue(header);
			cell.setCellStyle(cStyle);
			i++;
		}
	}
	
	/**
	 * Creates the rows for the Excel report.
	 * 
	 * @param Row row
	 * @param AttyPresentBO result
	 * @throws Exception
	 */
	protected void generateExcelRowData(Row row, AttyPresentBO result) throws Exception {

		Cell cell;
		
		String judgeFirstName = "";
		if (!TextUtil.isEmpty((String) result.get("judge_first_name"))) {
			judgeFirstName = TextUtil.print(", "+result.get("judge_first_name"));
		} else {
			judgeFirstName = "";
		}

		String attorneyFirstName = "";
		if (!TextUtil.isEmpty((String) result.get("attorney_first_name"))) {
			attorneyFirstName = TextUtil.print(", "+result.get("attorney_first_name"));
		} else {
			attorneyFirstName = "";
		}
			
		//Judge Name
		cell = row.createCell(0);
		cell.setCellValue(result.get("judge_last_name") + judgeFirstName);

		//Attorney Name 
		cell = row.createCell(1);
		cell.setCellValue(result.get("attorney_last_name") + attorneyFirstName);
		
		//Attorney Bar Number
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(result.get(AttyPresentBO.BARNUM)));

		//Attorney Bar State
		cell = row.createCell(3);
		cell.setCellValue(TextUtil.print(result.get(AttyPresentBO.BARSTATE)));

		//Location
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.print(result.get(LocationBO.LOCNDESCR)));

		//Count Misc
		cell = row.createCell(5);
		int count_misc = Integer.parseInt((String) result.get("count_misc")) - Integer.parseInt((String) result.get("count_trial"));
		cell.setCellValue(TextUtil.print(count_misc));

		//Count Trial
		cell = row.createCell(6);
		cell.setCellValue(TextUtil.print(result.get("count_trial")));

		//Email Address
		cell = row.createCell(7);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.EMAILADDRESS)));

		judgeFirstName = null;
		attorneyFirstName = null;

	}
	
	/**
	 * Generates the PDF format of the report.
	 * 
	 * @param List<AttyPresentBO> searchResults
	 * @return byte[]
	 */
	public byte[] generatePdf(List<AttyPresentBO> searchResults) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			Document document = new Document(PageSize.LETTER.rotate());
			PdfPTable table = null;
			
			//needed for paging
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			writer.setPageEvent(this);

			//setup
			document.setMargins(36, 36, 36, 36); //left, right, top, bottom, 36=1/2 inch, 72=1 inch
			document.addCreationDate();
			document.addCreator("Utah State Court -- AOC");
			
			//popup window title
			document.addTitle(myCriteria.getReportTitle());
			
			document.open();

			//title for the first page
			table = populatePdfReportTitle();
			document.add(table);
			
			//content
			table = generatePdfResultTable(searchResults);
			document.add(table);
			
			document.close();
			
		} catch (Exception e) {
			logger.error(FILE_NAME+" generatePdf(List<AttyPresentBO> searchResults)", e);
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				logger.error(FILE_NAME+" generatePdf(List<AttyPresentBO> searchResults)", e);
			}
		}
		return baos.toByteArray();
	}
	
	/**
	 * Creates the iText table for the PDF report.
	 * 
	 * @param List<AttyPresentBO> searchResults
	 * @return PdfPTable
	 */
	public PdfPTable generatePdfResultTable(List<AttyPresentBO> searchResults) {

		//setup table
		PdfPTable table;
		table = new PdfPTable(8); //8 columns
		float[] widths;
		widths = new float[8]; //8 columns
		widths[0] = 18.0f;
		widths[1] = 18.0f;
		widths[2] = 10.0f;
		widths[3] = 10.0f;
		widths[4] = 20.0f;
		widths[5] = 7.0f;
		widths[6] = 7.0f;
		widths[7] = 10.0f;
		table.setWidthPercentage(100);
		try {
			table.setWidths(widths);
		} catch (DocumentException e) {
			logger.error(FILE_NAME+" generatePdfResultTable(List<AttyPresentBO> searchResults)", e);
		}
		
		//header for top of each page
		populatePdfTableColumnHeaders(table);
		
		//table content
		generatePdfReportContents(table, searchResults);
		
		return table;
	}
	
	/**
	 * Creates the iText table for the content of the PDF report.
	 * 
	 * @param PdfPTable table
	 * @param List<AttyPresentBO> searchResults
	 */
	private void generatePdfReportContents(PdfPTable table, List<AttyPresentBO> searchResults) {
		if (searchResults != null && searchResults.size() > 0) {
			try {
				String judgeFirstName = "";
				String attorneyFirstName = "";
				for (AttyPresentBO attyPresentBO : searchResults) {
					if (!TextUtil.isEmpty((String) attyPresentBO.get("judge_first_name"))) {
						judgeFirstName = TextUtil.print(", "+attyPresentBO.get("judge_first_name"));
					} else {
						judgeFirstName = "";
					}
					if (!TextUtil.isEmpty((String) attyPresentBO.get("attorney_first_name"))) {
						attorneyFirstName = TextUtil.print(", "+attyPresentBO.get("attorney_first_name"));
					} else {
						attorneyFirstName = "";
					}
					int count_misc = Integer.parseInt((String) attyPresentBO.get("count_misc")) - Integer.parseInt((String) attyPresentBO.get("count_trial"));
					addCell(table, TextUtil.print(attyPresentBO.get("judge_last_name")) + judgeFirstName, NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attyPresentBO.get("attorney_last_name")) + attorneyFirstName, NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attyPresentBO.get(AttyPresentBO.BARNUM)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attyPresentBO.get(AttyPresentBO.BARSTATE)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attyPresentBO.get(LocationBO.LOCNDESCR)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(count_misc), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attyPresentBO.get("count_trial")), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attyPresentBO.get(AttorneyBO.EMAILADDRESS)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
				}
				judgeFirstName = null;
				attorneyFirstName = null;
			} catch (Exception e) {
				logger.error(FILE_NAME+" generatePdfReportContents(PdfPTable table, List<attyPresentBO> searchResults)", e);
			}
		}
	}
	
	/**
	 * Creates the iText column headers to be displayed at the top of each page for the PDF report
	 * 
	 * @param PdfPTable table
	 */
	@Override
	protected void populatePdfTableColumnHeaders(PdfPTable table) {

		//header row
		addCell(table, "Judge Name", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Attorney Name", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Attorney Bar Number", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Attorney Bar State", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Location", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Count Misc", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Count Trial", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Attorney Email Address", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);

		table.setHeaderRows(1);
		
	}
	
	/**
	 * Creates the iText title header to be displayed at the top of the first page of the report.
	 * 
	 * @return PdfPTable
	 */
	@Override
	protected PdfPTable populatePdfReportTitle() {
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setWidthPercentage(100);
		try {
			headerTable.setWidths(new float[] {1.0f});

			//first page header rows
			SimpleDateFormat dateFormat = Constants.dateFormatCoris;
			addCell(headerTable, myCriteria.getReportTitle(), BOLDFONT, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
			addCell(headerTable, dateFormat.format(myCriteria.getStartDate())+" to "+dateFormat.format(myCriteria.getEndDate()), BOLDFONT, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
			addCell(headerTable, myCriteria.getLocnDescr()+", STATE OF UTAH", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
			
		} catch (DocumentException e) {
			logger.error(FILE_NAME+" populatePdfReportTitle()", e);
		}
		return headerTable;
	}

	/**
	 * Adds a cell to the iText table that is being created.
	 * 
	 * @param PdfPTable table
	 * @param String remarks
	 * @param Font fontStyle
	 * @param int alignment
	 * @param int border
	 * @param int colSpan
	 */
	public static void addCell(PdfPTable table, String remarks, Font fontStyle, int alignment, int border, int colSpan) {
		PdfPCell theCell = new PdfPCell();
		theCell.setColspan(colSpan);
		Paragraph paragraph = new Paragraph(8, remarks, fontStyle);
		paragraph.setAlignment(alignment);
		theCell.addElement(paragraph);
		theCell.setBorder(border);
		theCell.setPaddingTop(2f);
		table.addCell(theCell);
		theCell = null;
		paragraph = null;
	}
	
}