package gov.utcourts.corisweb.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
import gov.utcourts.coriscommon.dataaccess.civilcase.CivilCaseBO;
import gov.utcourts.coriscommon.dataaccess.disptype.DispTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.CorisSecurityCommon;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.servlet.CorisReportNameIndexCivilServlet;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;

public class CorisReportNameIndexCivilReportGenerator extends ReportBaseHandler {

	private CorisReportNameIndexCivilReportCriteria myCriteria;
	private static Logger logger = Logger.getLogger(CorisReportNameIndexCivilReportGenerator.class.getName());
	private static String FILE_NAME = "CorisReportNameIndexCivilReportGenerator";
	
	public CorisReportNameIndexCivilReportGenerator(ReportBaseSearchCriteria criteria) {
		super(criteria);
		myCriteria = (CorisReportNameIndexCivilReportCriteria) criteria;
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
			List<CaseTypeBO> caseTypeListBO = CorisReportNameIndexCivilServlet.getCaseTypeList(myCriteria);
			Map<String, Integer> totalCounts = CorisReportNameIndexCivilServlet.getFooterTotals(myCriteria, searchResults);
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
				"Plaintiff Name", 
				"Filed By", 
				"Filed Date",
				"Filing Type",
				"Case Number", 
				"Case Type",
				"Case Title",
				"Amount in Controversy", 
				"Waiver Status", 
				"Amount Paid/Credit", 
				"Disposition",
				"Disposition Date",
				"Original Judge",
				"Current Judge",
				"Commissioner"
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
		HashMap<String, Boolean> caseAccessLevel = CorisSecurityCommon.getUserSecurityLevels(myCriteria, null);
		DecimalFormat currencyFormat = new DecimalFormat("$#,###,###.00");
		
		String defendantFirstName = "";
		if (!TextUtil.isEmpty((String) result.get("def_first_name"))) {
			defendantFirstName = TextUtil.print(", "+result.get("def_first_name"));
		}
			
		String plaintiffFirstName = "";
		if (!TextUtil.isEmpty((String) result.get("pla_first_name"))) {
			plaintiffFirstName = TextUtil.print(", "+result.get("pla_first_name"));
		}
			
		//Defendant Name
		cell = row.createCell(0);
		if (CorisSecurityCommon.hasPartyAccess(caseAccessLevel, result.getCaseType(), (String) result.get(PartyCaseBO.PARTYCODE))) {
			cell.setCellValue(result.get("def_last_name") + defendantFirstName);
		} else {
			cell.setCellValue("Protected");
		}
		
		//Plaintiff Name
		cell = row.createCell(1);
		if (CorisSecurityCommon.hasPartyAccess(caseAccessLevel, result.getCaseType(), (String) result.get(PartyCaseBO.PARTYCODE))) {
			cell.setCellValue(result.get("pla_last_name") + plaintiffFirstName);
		} else {
			cell.setCellValue("Protected");
		}
		
		//Filed By 
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(result.get(PersonnelBO.LOGNAME)));

		//Filed Date
		cell = row.createCell(3);
		cell.setCellValue((result.get(KaseBO.FILINGDATE) != null)?TextUtil.print(dateFormat.format(result.get(KaseBO.FILINGDATE))):"");

		//Filing Type 
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.print(result.get(KaseBO.FILINGTYPE)));

		//Case Number
		cell = row.createCell(5);
		cell.setCellValue(TextUtil.print(result.get(KaseBO.CASENUM)));
		
		//Case Type 
		cell = row.createCell(6);
		cell.setCellValue(TextUtil.print(result.get(CaseTypeBO.CASETYPE)));

		//Case Title 
		cell = row.createCell(7);
		cell.setCellValue(TextUtil.print(result.get("case_title")));

		//Amount in Controversy
		cell = row.createCell(8);
		cell.setCellValue((result.get(CivilCaseBO.AMTINCONTROVERSY) != null && !"0".equals(result.get(CivilCaseBO.AMTINCONTROVERSY)))?currencyFormat.format(result.get(CivilCaseBO.AMTINCONTROVERSY)):"");
		
		//Waiver Status
		cell = row.createCell(9);
		cell.setCellValue(TextUtil.print(result.get("waiver_status")));

		//Amount Paid/Credit
		cell = row.createCell(10);
		cell.setCellValue((result.get("amount_paid") != null && !"0".equals(result.get("amount_paid")))?currencyFormat.format(new BigDecimal((String) result.get("amount_paid"))):"");

		//Disposition
		cell = row.createCell(11);
		cell.setCellValue(TextUtil.print(result.get(DispTypeBO.DESCR)));

		//Disposition Date
		cell = row.createCell(12);
		cell.setCellValue((result.get(KaseBO.DISPDATE) != null)?TextUtil.print(dateFormat.format(result.get(KaseBO.DISPDATE))):"");

		//Original Judge
		cell = row.createCell(13);
		cell.setCellValue((TextUtil.print(result.get("original_judge_last_name"))) + ((!TextUtil.isEmpty((String) result.get("original_judge_first_name")))?", ":"") + (TextUtil.print(result.get("original_judge_first_name"))));

		//Current Judge
		cell = row.createCell(14);
		cell.setCellValue((TextUtil.print(result.get("current_judge_last_name"))) + ((!TextUtil.isEmpty((String) result.get("current_judge_first_name")))?", ":"") + (TextUtil.print(result.get("current_judge_first_name"))));

		//Commissioner
		cell = row.createCell(15);
		cell.setCellValue((TextUtil.print(result.get("commissioner_last_name"))) + ((!TextUtil.isEmpty((String) result.get("commissioner_first_name")))?", ":"") + (TextUtil.print(result.get("commissioner_first_name"))));

		defendantFirstName = null;
		plaintiffFirstName = null;
		dateFormat = null;
		caseAccessLevel = null;
		currencyFormat = null;

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
		table = new PdfPTable(16); //16 columns
		float[] widths;
		widths = new float[16]; //16 columns
		widths[0] = 8.0f;
		widths[1] = 8.0f;
		widths[2] = 6.0f;
		widths[3] = 7.0f;
		widths[4] = 3.0f;
		widths[5] = 7.0f;
		widths[6] = 6.0f;
		widths[7] = 10.0f;
		widths[8] = 5.0f;
		widths[9] = 4.0f;
		widths[10] = 5.0f;
		widths[11] = 6.0f;
		widths[12] = 7.0f;
		widths[13] = 6.0f;
		widths[14] = 6.0f;
		widths[15] = 6.0f;
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
				HashMap<String, Boolean> caseAccessLevel = CorisSecurityCommon.getUserSecurityLevels(myCriteria, null);
				DecimalFormat currencyFormat = new DecimalFormat("$#,###,###.00");				
				String defendantFirstName = "";
				String plaintiffFirstName = "";
				for (KaseBO kaseBO : searchResults) {
					if (!TextUtil.isEmpty((String) kaseBO.get("def_first_name"))) {
						defendantFirstName = TextUtil.print(", "+kaseBO.get("def_first_name"));
					}
					if (!TextUtil.isEmpty((String) kaseBO.get("pla_first_name"))) {
						plaintiffFirstName = TextUtil.print(", "+kaseBO.get("pla_first_name"));
					}
					if (CorisSecurityCommon.hasPartyAccess(caseAccessLevel, kaseBO.getCaseType(), (String) kaseBO.get(PartyCaseBO.PARTYCODE))) {
						addCell(table, kaseBO.get("def_last_name") + defendantFirstName, NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					} else {
						addCell(table, "Protected", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					}
					if (CorisSecurityCommon.hasPartyAccess(caseAccessLevel, kaseBO.getCaseType(), (String) kaseBO.get(PartyCaseBO.PARTYCODE))) {
						addCell(table, kaseBO.get("pla_last_name") + plaintiffFirstName, NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					} else {
						addCell(table, "Protected", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					}
					addCell(table, TextUtil.print(kaseBO.get(PersonnelBO.LOGNAME)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, (kaseBO.getFilingDate() != null)?TextUtil.print(dateFormat.format(kaseBO.getFilingDate())):"", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.getFilingType()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.getCaseNum()), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get(CaseTypeBO.DESCR)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get("case_title")), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, (kaseBO.get(CivilCaseBO.AMTINCONTROVERSY) != null && !"0".equals(kaseBO.get(CivilCaseBO.AMTINCONTROVERSY)))?currencyFormat.format(kaseBO.get(CivilCaseBO.AMTINCONTROVERSY)):"", NORMALFONT, Element.ALIGN_RIGHT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get("waiver_status")), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, (kaseBO.get("amount_paid") != null && !"0".equals(kaseBO.get("amount_paid")))?currencyFormat.format(new BigDecimal((String) kaseBO.get("amount_paid"))):"", NORMALFONT, Element.ALIGN_RIGHT, PdfPCell.BOTTOM, 1);
					addCell(table, TextUtil.print(kaseBO.get(DispTypeBO.DESCR)), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, (kaseBO.getDispDate() != null)?TextUtil.print(dateFormat.format(kaseBO.getDispDate())):"", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, (TextUtil.print(kaseBO.get("original_judge_last_name"))) + ((!TextUtil.isEmpty((String) kaseBO.get("original_judge_first_name")))?", ":"") + (TextUtil.print(kaseBO.get("original_judge_first_name"))), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, (TextUtil.print(kaseBO.get("current_judge_last_name"))) + ((!TextUtil.isEmpty((String) kaseBO.get("current_judge_first_name")))?", ":"") + (TextUtil.print(kaseBO.get("current_judge_first_name"))), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
					addCell(table, (TextUtil.print(kaseBO.get("commissioner_last_name"))) + ((!TextUtil.isEmpty((String) kaseBO.get("commissioner_first_name")))?", ":"") + (TextUtil.print(kaseBO.get("commissioner_first_name"))), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 1);
				}

				//footer totals
				List<CaseTypeBO> caseTypeListBO = CorisReportNameIndexCivilServlet.getCaseTypeList(myCriteria);
				Map<String, Integer> totalCounts = CorisReportNameIndexCivilServlet.getFooterTotals(myCriteria, searchResults);
				int totalCases = 0;
				if (totalCounts != null && caseTypeListBO != null) {
					for (CaseTypeBO caseTypeBO : caseTypeListBO) {
						if (totalCounts.get(caseTypeBO.getCaseType()) != null) {
							addCell(table, "Total "+TextUtil.print(caseTypeBO.getDescr()+": "+totalCounts.get(caseTypeBO.getCaseType())), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 16);
							totalCases += totalCounts.get(caseTypeBO.getCaseType());
						}
					}
					if (totalCases > 0) {
						addCell(table, TextUtil.print("Total Cases: "+ totalCases), NORMALFONT, Element.ALIGN_LEFT, PdfPCell.BOTTOM, 16);
					}
				} 
				
				totalCounts = null;
				caseTypeListBO = null;
				defendantFirstName = null;
				plaintiffFirstName = null;
				dateFormat = null;
				caseAccessLevel = null;
				currencyFormat = null;
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
		addCell(table, "Plaintiff Name", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Filed By", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Filed Date", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Filing Type", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Case Number", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Case Type", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Case Title", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Amount in Controversy", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Waiver Status", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Amount Paid/Credit", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Disposition", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Disposition Date", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Original Judge", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Current Judge", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);
		addCell(table, "Commissioner", BOLDFONT, Element.ALIGN_CENTER, PdfPCell.BOTTOM, 1);

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