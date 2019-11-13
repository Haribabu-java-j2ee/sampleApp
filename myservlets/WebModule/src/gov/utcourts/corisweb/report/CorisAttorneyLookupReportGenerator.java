package gov.utcourts.corisweb.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;

public class CorisAttorneyLookupReportGenerator extends ReportBaseHandler {

	private CorisAttorneyLookupReportCriteria myCriteria;
	private static Logger logger = Logger.getLogger(CorisAttorneyLookupReportGenerator.class.getName());
	private static String FILE_NAME = "CorisAttorneyLookupReportGenerator";
	
	public CorisAttorneyLookupReportGenerator(ReportBaseSearchCriteria criteria) {
		super(criteria);
		myCriteria = (CorisAttorneyLookupReportCriteria) criteria;
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
			return generatePdf((List<AttorneyBO>) searchResults);
		} else if ("xlsx".equalsIgnoreCase(myCriteria.getReportformat())) {
			return generateExcel((List<AttorneyBO>) searchResults);
		}
		return null;
	}
	
	/**
	 * Generates the Excel format of the report
	 * 
	 * @param List<AttorneyBO> searchResults
	 * @return byte[]
	 */
	private byte[] generateExcel(List<AttorneyBO> searchResults) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(myCriteria.getReportTitle());
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (AttorneyBO data : searchResults) {
				row = sheet.createRow(++rowCount);
				generateExcelRowData(row, data);
			}
			workbook.write(baos);
		} catch (Exception e) {
			logger.error(FILE_NAME+" generateExcel(List<AttorneyBO> searchResults)", e);
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				logger.error(FILE_NAME+" generateExcel(List<AttorneyBO> searchResults)", e);
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
				"Last Name", 
				"First Name", 
				"Bar Number", 
				"Bar State", 
				"Bar Status", 
				"City", 
				"State", 
				"Country",
				"Business Phone",
				"Email"
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
	 * @param AttorneyBO result
	 * @throws Exception
	 */
	protected void generateExcelRowData(Row row, AttorneyBO result) throws Exception {

		Cell cell;

		//Last Name 
		cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.LASTNAME)));
		//First Name 
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.FIRSTNAME)));
		//Bar Number 
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.BARNUM)));
		//Bar State 
		cell = row.createCell(3);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.BARSTATE)));
		//Bar Status 
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.BARSTATUS)));
		//City 
		cell = row.createCell(5);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.CITY)));
		//State 
		cell = row.createCell(6);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.STATECODE)));
		//Country
		cell = row.createCell(7);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.COUNTRY)));
		//Business Phone
		cell = row.createCell(8);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.BUSPHONE)));
		//Email
		cell = row.createCell(9);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.EMAILADDRESS)));

	}
	
	/**
	 * Generates the PDF format of the report.
	 * 
	 * @param List<AttorneyBO> searchResults
	 * @return byte[]
	 */
	public byte[] generatePdf(List<AttorneyBO> searchResults) {
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
			logger.error(FILE_NAME+" generatePdf(List<AttorneyBO> searchResults)", e);
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				logger.error(FILE_NAME+" generatePdf(List<AttorneyBO> searchResults)", e);
			}
		}
		return baos.toByteArray();
	}
	
	/**
	 * Creates the iText table for the PDF report.
	 * 
	 * @param List<AttorneyBO> searchResults
	 * @return PdfPTable
	 */
	public PdfPTable generatePdfResultTable(List<AttorneyBO> searchResults) {

		//setup table
		PdfPTable table;
		table = new PdfPTable(10); //10 columns
		float[] widths;
		widths = new float[10]; //10 columns
		widths[0] = 11.0f;
		widths[1] = 11.0f;
		widths[2] = 8.0f;
		widths[3] = 7.0f;
		widths[4] = 7.0f;
		widths[5] = 15.0f;
		widths[6] = 7.0f;
		widths[7] = 10.0f;
		widths[8] = 12.0f;
		widths[9] = 12.0f;
		table.setWidthPercentage(100);
		try {
			table.setWidths(widths);
		} catch (DocumentException e) {
			logger.error(FILE_NAME+" generatePdfResultTable(List<AttorneyBO> searchResults)", e);
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
	 * @param List<AttorneyBO> searchResults
	 */
	private void generatePdfReportContents(PdfPTable table, List<AttorneyBO> searchResults) {
		if (searchResults != null && searchResults.size() > 0) {
			try {
				for (AttorneyBO attorneyBO : searchResults) {
					addCell(table, TextUtil.print(attorneyBO.getLastName()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attorneyBO.getFirstName()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attorneyBO.getBarNum()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attorneyBO.getBarState()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attorneyBO.getBarStatus()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attorneyBO.getCity()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attorneyBO.getStateCode()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attorneyBO.getCountry()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attorneyBO.getBusPhone()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(attorneyBO.getEmailAddress()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
				}
			} catch (Exception e) {
				logger.error(FILE_NAME+" generatePdfReportContents(PdfPTable table, List<AttorneyBO> searchResults)", e);
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
		addCell(table, "Last Name", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "First Name", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Bar Number", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Bar State", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Bar Status", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "City", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "State", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Country", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Business Phone", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Email", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);

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
			addCell(headerTable, myCriteria.getReportTitle(), BOLDFONT, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
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