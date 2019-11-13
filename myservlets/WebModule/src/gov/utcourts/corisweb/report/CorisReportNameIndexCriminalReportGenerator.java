package gov.utcourts.corisweb.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.disptype.DispTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.offense.OffenseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.servlet.CorisReportNameIndexCriminalServlet;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;

public class CorisReportNameIndexCriminalReportGenerator extends ReportBaseHandler {

	private CorisReportNameIndexCriminalReportCriteria myCriteria;
	private static Logger logger = Logger.getLogger(CorisReportNameIndexCriminalReportGenerator.class.getName());
	private static String FILE_NAME = "CorisReportNameIndexCriminalReportGenerator";
	
	public CorisReportNameIndexCriminalReportGenerator(ReportBaseSearchCriteria criteria) {
		super(criteria);
		myCriteria = (CorisReportNameIndexCriminalReportCriteria) criteria;
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
		int rowCount = -1;
		Row row = null;
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Cell cellheader;
		Cell cellFooter;
		SimpleDateFormat dateFormat = Constants.dateFormatCoris;
		try {
			
			//top of page header rows
			row = sheet.createRow(++rowCount); // create header row
			cellheader = row.createCell(0);
			if ("F".equals(myCriteria.getTypeOfDate())) {
				cellheader.setCellValue("Filing Date: "+dateFormat.format(myCriteria.getReportDateStart())+" to "+dateFormat.format(myCriteria.getReportDateEnd()));
			} else if ("D".equals(myCriteria.getTypeOfDate())) {
				cellheader.setCellValue("Disposition Date: "+dateFormat.format(myCriteria.getReportDateStart())+" to "+dateFormat.format(myCriteria.getReportDateEnd()));
			}
			cellheader.setCellStyle(cStyle);

			row = sheet.createRow(++rowCount);  // create header row
			cellheader = row.createCell(0);
			cellheader.setCellValue(myCriteria.getLocnDescr()+", STATE OF UTAH");
			cellheader.setCellStyle(cStyle);
			
			row = sheet.createRow(++rowCount); // create header row
			populateExcelColumnHeaders(row, sheet);
			
			//data result rows
			for (KaseBO data : searchResults) {
				row = sheet.createRow(++rowCount);
				generateExcelRowData(row, data);
			}
			
			//footer totals
			List<CaseTypeBO> caseTypeListBO = CorisReportNameIndexCriminalServlet.getCaseTypeList(myCriteria);
			Map<String, Integer> totalCounts = CorisReportNameIndexCriminalServlet.getFooterTotals(myCriteria, searchResults);
			int totalCases = 0;
			if (totalCounts != null && caseTypeListBO != null) {
				for (CaseTypeBO caseTypeBO : caseTypeListBO) {
					if (totalCounts.get(caseTypeBO.getCaseType()) != null) {
						row = sheet.createRow(++rowCount);
						cellFooter = row.createCell(0);
						cellFooter.setCellValue("Total "+TextUtil.print(caseTypeBO.getDescr()+":"));
						cellFooter = row.createCell(1);
						cellFooter.setCellValue(totalCounts.get(caseTypeBO.getCaseType()));
						totalCases += totalCounts.get(caseTypeBO.getCaseType());
					}
				}
				if (totalCases > 0) {
					row = sheet.createRow(++rowCount);
					cellFooter = row.createCell(0);
					cellFooter.setCellValue("Total Cases:");
					cellFooter = row.createCell(1);
					cellFooter.setCellValue(totalCases);
				}
			}
			
			cellFooter = null;
			row = null;
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
		Cell cell;
		// column headers
		StringArrayDescriptor columnHeaders;
		columnHeaders = new StringArrayDescriptor(
				"Defendant Name", 
				"Filed By", 
				"Filing Type",
				"Case Number", 
				"Case Type", 
				"Sheriff Number", 
				"Charge (*Multiple Charges)", 
				"Filed Date", 
				"Disposition",
				"Disposition Date"
		);
		int i = 0;
		for (String header : columnHeaders.getValues()) {
			cell = row.createCell(i);
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
		
		SimpleDateFormat dateFormat = Constants.dateFormatCoris;
		
		String defendantFirstName = "";
		if (!TextUtil.isEmpty((String) result.get(PartyBO.FIRSTNAME))) {
			defendantFirstName = TextUtil.print(", "+result.get(PartyBO.FIRSTNAME));
		}
			
		//Defendant Name
		cell = row.createCell(0);
		if ("X".equals(result.getCaseSecurity())) {
			cell.setCellValue("Protected");
		} else {
			cell.setCellValue(result.get(PartyBO.LASTNAME) + defendantFirstName);
		}
		
		//Filed By 
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(result.get(PersonnelBO.LOGNAME)));

		//Filing Type
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(result.get(KaseBO.FILINGTYPE)));

		//Case Number
		cell = row.createCell(3);
		cell.setCellValue(TextUtil.print(result.get(KaseBO.CASENUM)));
		
		//Case Type 
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.print(result.get(CaseTypeBO.CASETYPE)));

		//Sheriff Number
		cell = row.createCell(5);
		cell.setCellValue(TextUtil.print(result.get(CrimCaseBO.SHERIFFNUM)));

		//Charge (* Multiple charges)
		cell = row.createCell(6);
		cell.setCellValue(TextUtil.print(result.get(OffenseBO.DESCR)));

		//Filed Date
		cell = row.createCell(7);
		cell.setCellValue((result.get(KaseBO.FILINGDATE) != null)?TextUtil.print(dateFormat.format(result.get(KaseBO.FILINGDATE))):"");

		//Disposition
		cell = row.createCell(8);
		cell.setCellValue(TextUtil.print(result.get(DispTypeBO.DESCR)));

		//Disposition Date
		cell = row.createCell(9);
		cell.setCellValue((result.get(KaseBO.DISPDATE) != null)?TextUtil.print(dateFormat.format(result.get(KaseBO.DISPDATE))):"");

		defendantFirstName = null;
		dateFormat = null;

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
		table = new PdfPTable(10); //10 columns
		float[] widths;
		widths = new float[10]; //10 columns
		widths[0] = 15.0f;
		widths[1] = 7.0f;
		widths[2] = 4.0f;
		widths[3] = 8.0f;
		widths[4] = 8.0f;
		widths[5] = 8.0f;
		widths[6] = 20.0f;
		widths[7] = 8.0f;
		widths[8] = 11.0f;
		widths[9] = 8.0f;
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
				SimpleDateFormat dateFormat = Constants.dateFormatCoris;
				String defendantFirstName = "";
				for (KaseBO kaseBO : searchResults) {
					if (!TextUtil.isEmpty((String) kaseBO.get(PartyBO.FIRSTNAME))) {
						defendantFirstName = TextUtil.print(", "+kaseBO.get(PartyBO.FIRSTNAME));
					}
					if ("X".equals(kaseBO.getCaseSecurity())) {
						addCell(table, "Protected", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					} else {
						addCell(table, kaseBO.get(PartyBO.LASTNAME) + defendantFirstName, NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					}
					addCell(table, TextUtil.print(kaseBO.get(PersonnelBO.LOGNAME)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.getFilingType()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.getCaseNum()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get(CaseTypeBO.DESCR)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get(CrimCaseBO.SHERIFFNUM)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get(OffenseBO.DESCR)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, (kaseBO.getFilingDate() != null)?TextUtil.print(dateFormat.format(kaseBO.getFilingDate())):"", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get(DispTypeBO.DESCR)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, (kaseBO.getDispDate() != null)?TextUtil.print(dateFormat.format(kaseBO.getDispDate())):"", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
				}

				//footer totals
				List<CaseTypeBO> caseTypeListBO = CorisReportNameIndexCriminalServlet.getCaseTypeList(myCriteria);
				Map<String, Integer> totalCounts = CorisReportNameIndexCriminalServlet.getFooterTotals(myCriteria, searchResults);
				int totalCases = 0;
				if (totalCounts != null && caseTypeListBO != null) {
					for (CaseTypeBO caseTypeBO : caseTypeListBO) {
						if (totalCounts.get(caseTypeBO.getCaseType()) != null) {
							addCell(table, "Total "+TextUtil.print(caseTypeBO.getDescr()+": "+totalCounts.get(caseTypeBO.getCaseType())), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 10);
							totalCases += totalCounts.get(caseTypeBO.getCaseType());
						}
					}
					if (totalCases > 0) {
						addCell(table, TextUtil.print("Total Cases: "+ totalCases), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 10);
					}
				} 
				
				totalCounts = null;
				caseTypeListBO = null;
				defendantFirstName = null;
				dateFormat = null;
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
		addCell(table, "Defendant Name", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Filed By", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Filing Type", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Case Number", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Case Type", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Sheriff Number", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Charge (* Multiple charges)", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Filed Date", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Disposition", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Disposition Date", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);

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
		SimpleDateFormat dateFormat = Constants.dateFormatCoris;
		try {
			headerTable.setWidths(new float[] {1.0f});

			//first page header rows
			addCell(headerTable, myCriteria.getReportTitle(), BOLDFONT, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
			if ("F".equals(myCriteria.getTypeOfDate())) {
				addCell(headerTable, "Filing Date: "+dateFormat.format(myCriteria.getReportDateStart())+" to "+dateFormat.format(myCriteria.getReportDateEnd()), BOLDFONT, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
			} else if ("D".equals(myCriteria.getTypeOfDate())) {
				addCell(headerTable, "Disposition Date: "+dateFormat.format(myCriteria.getReportDateStart())+" to "+dateFormat.format(myCriteria.getReportDateEnd()), BOLDFONT, Element.ALIGN_CENTER, PdfPCell.NO_BORDER, 1);
			}
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