package gov.utcourts.corisweb.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.CorisSecurityCommon;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;

public class CorisAttorneysForCaseReportGenerator extends ReportBaseHandler {

	private CorisAttorneysForCaseReportCriteria myCriteria;
	private static Logger logger = Logger.getLogger(CorisAttorneysForCaseReportGenerator.class.getName());
	private static String FILE_NAME = "CorisAttorneysForCaseReportGenerator";
	
	public CorisAttorneysForCaseReportGenerator(ReportBaseSearchCriteria criteria) {
		super(criteria);
		myCriteria = (CorisAttorneysForCaseReportCriteria) criteria;
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
			return generatePdf((List<KaseBO>) searchResults);
		} else if ("xlsx".equalsIgnoreCase(myCriteria.getReportformat())) {
			return generateExcel((List<KaseBO>) searchResults);
		}
		return null;
	}
	
	/**
	 * Generates the Excel format of the report
	 * 
	 * @param List<KaseBO> searchResults
	 * @return byte[]
	 */
	private byte[] generateExcel(List<KaseBO> searchResults) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(myCriteria.getReportTitle());
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (KaseBO data : searchResults) {
				row = sheet.createRow(++rowCount);
				generateExcelRowData(row, data);
			}
			workbook.write(baos);
		} catch (Exception e) {
			logger.error(FILE_NAME+" generateExcel(List<KaseBO> searchResults)", e);
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				logger.error(FILE_NAME+" generateExcel(List<KaseBO> searchResults)", e);
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
		if (myCriteria.isIncludeDetached()) {
			columnHeaders = new StringArrayDescriptor(
					"Party Type", 
					"Party Name", 
					"Attorney Name", 
					"Bar No", 
					"Bar St", 
					"Business Phone", 
					"Attach Date", 
					"Attached By",
					"Detach Date",
					"Detached By"
			);
		} else {
			columnHeaders = new StringArrayDescriptor(
					"Party Type", 
					"Party Name", 
					"Attorney Name", 
					"Bar No", 
					"Bar St", 
					"Business Phone", 
					"Attach Date", 
					"Attached By"
			);			
		}			
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
	 * @param KaseBO result
	 * @throws Exception
	 */
	protected void generateExcelRowData(Row row, KaseBO result) throws Exception {

		Cell cell;
		 
		String partyFirstName = "";
		String attorneyFirstName = "";
		if (!TextUtil.isEmpty((String) result.get(PartyBO.FIRSTNAME))) {
			partyFirstName = TextUtil.print(", "+result.get(PartyBO.FIRSTNAME));
		} else {
			partyFirstName = "";
		}
		if (!TextUtil.isEmpty((String) result.get(AttorneyBO.FIRSTNAME))) {
			attorneyFirstName = TextUtil.print(", "+result.get(AttorneyBO.FIRSTNAME));
		} else {
			attorneyFirstName = "";
		}
			
		//Party Type
		cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(result.get(PartyCaseBO.PARTYCODE)));
		
		//Party Name 
		HashMap<String, Boolean> userAccess = CorisSecurityCommon.getUserSecurityLevels(myCriteria, null);
		if (CorisSecurityCommon.hasPartyAccess(userAccess, result.getCaseType(), (String) result.get(PartyCaseBO.PARTYCODE))) { 
			cell = row.createCell(1);
			cell.setCellValue(result.get(PartyBO.LASTNAME) + partyFirstName);
		} else {
			cell = row.createCell(1);
			cell.setCellValue("***, ***");
		}		

		//Attorney Name 
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.LASTNAME)) + attorneyFirstName);
		
		//Bar No 
		cell = row.createCell(3);
		cell.setCellValue(((Integer) result.get(AttyPartyBO.BARNUM) > 0)?TextUtil.print(result.get(AttyPartyBO.BARNUM)):"");
		
		//Bar St 
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.print(result.get(AttyPartyBO.BARSTATE)));

		//Business Phone 
		cell = row.createCell(5);
		cell.setCellValue(TextUtil.print(result.get(AttorneyBO.BUSPHONE)));

		//Attach Date
		cell = row.createCell(6);
		cell.setCellValue(TextUtil.print(result.get(AttyPartyBO.ATTACHDATETIME)));

		//Attached By
		cell = row.createCell(7);
		cell.setCellValue(TextUtil.print(result.get("attached_logname")));

		if (myCriteria.isIncludeDetached()) {
			
			//Detach Date
			cell = row.createCell(8);
			cell.setCellValue(TextUtil.print(result.get(AttyPartyBO.DETACHDATETIME)));

			//Detached By
			cell = row.createCell(9);
			cell.setCellValue(TextUtil.print(result.get("detached_logname")));

		}
		
		partyFirstName = null;
		attorneyFirstName = null;

	}
	
	/**
	 * Generates the PDF format of the report.
	 * 
	 * @param List<KaseBO> searchResults
	 * @return byte[]
	 */
	public byte[] generatePdf(List<KaseBO> searchResults) {
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
			logger.error(FILE_NAME+" generatePdf(List<KaseBO> searchResults)", e);
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				logger.error(FILE_NAME+" generatePdf(List<KaseBO> searchResults)", e);
			}
		}
		return baos.toByteArray();
	}
	
	/**
	 * Creates the iText table for the PDF report.
	 * 
	 * @param List<KaseBO> searchResults
	 * @return PdfPTable
	 */
	public PdfPTable generatePdfResultTable(List<KaseBO> searchResults) {

		//setup table
		PdfPTable table;
		if (myCriteria.isIncludeDetached()) {
			table = new PdfPTable(10); //10 columns
		} else {
			table = new PdfPTable(8); //8 columns
		}
		float[] widths;
		if (myCriteria.isIncludeDetached()) {
			widths = new float[10]; //10 columns
		} else {
			widths = new float[8]; //8 columns
		}
		widths[0] = 7.0f;
		widths[1] = 14.0f;
		widths[2] = 14.0f;
		widths[3] = 8.0f;
		widths[4] = 5.0f;
		widths[5] = 10.0f;
		widths[6] = 10.0f;
		widths[7] = 10.0f;
		if (myCriteria.isIncludeDetached()) {
			widths[8] = 11.0f;
			widths[9] = 11.0f;
		}
		table.setWidthPercentage(100);
		try {
			table.setWidths(widths);
		} catch (DocumentException e) {
			logger.error(FILE_NAME+" generatePdfResultTable(List<KaseBO> searchResults)", e);
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
	 * @param List<KaseBO> searchResults
	 */
	private void generatePdfReportContents(PdfPTable table, List<KaseBO> searchResults) {
		if (searchResults != null && searchResults.size() > 0) {
			try {
				String partyFirstName = "";
				String attorneyFirstName = "";
				for (KaseBO kaseBO : searchResults) {
					
					if (!TextUtil.isEmpty((String) kaseBO.get(PartyBO.FIRSTNAME))) {
						partyFirstName = TextUtil.print(", "+kaseBO.get(PartyBO.FIRSTNAME));
					} else {
						partyFirstName = "";
					}
					if (!TextUtil.isEmpty((String) kaseBO.get(AttorneyBO.FIRSTNAME))) {
						attorneyFirstName = TextUtil.print(", "+kaseBO.get(AttorneyBO.FIRSTNAME));
					} else {
						attorneyFirstName = "";
					}
					
					addCell(table, TextUtil.print(kaseBO.get(PartyCaseBO.PARTYCODE)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					
					HashMap<String, Boolean> userAccess = CorisSecurityCommon.getUserSecurityLevels(myCriteria, null);
					if (CorisSecurityCommon.hasPartyAccess(userAccess, kaseBO.getCaseType(), (String) kaseBO.get(PartyCaseBO.PARTYCODE))) { 
						addCell(table, kaseBO.get(PartyBO.LASTNAME) + partyFirstName, NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					} else {
						addCell(table, "***, ***", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					}		

					addCell(table, TextUtil.print(kaseBO.get(AttorneyBO.LASTNAME)) + attorneyFirstName, NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, ((Integer) kaseBO.get(AttyPartyBO.BARNUM) > 0)?TextUtil.print(kaseBO.get(AttyPartyBO.BARNUM)):"", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get(AttyPartyBO.BARSTATE)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get(AttorneyBO.BUSPHONE)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get(AttyPartyBO.ATTACHDATETIME)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get("attached_logname")), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					if (myCriteria.isIncludeDetached()) {
						addCell(table, TextUtil.print(kaseBO.get(AttyPartyBO.DETACHDATETIME)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
						addCell(table, TextUtil.print(kaseBO.get("detached_logname")), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					}
				}
				partyFirstName = null;
				attorneyFirstName = null;
			} catch (Exception e) {
				logger.error(FILE_NAME+" generatePdfReportContents(PdfPTable table, List<KaseBO> searchResults)", e);
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
		addCell(table, "Party Type", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Party Name", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Attorney Name", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Bar No", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Bar St", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Business Phone", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Attach Date", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Attached By", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		if (myCriteria.isIncludeDetached()) {
			addCell(table, "Detach Date", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
			addCell(table, "Detached By", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		}

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
			addCell(headerTable, "Case "+myCriteria.getCaseNum(), BOLDFONT, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
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