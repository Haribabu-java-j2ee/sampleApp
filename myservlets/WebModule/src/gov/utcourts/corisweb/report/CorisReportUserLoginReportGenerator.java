package gov.utcourts.corisweb.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.stay.StayBO;
import gov.utcourts.coriscommon.dataaccess.staytype.StayTypeBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;

public class CorisReportUserLoginReportGenerator extends ReportBaseHandler {

	private CorisReportUserLoginReportCriteria myCriteria;
	
	private static Logger logger = Logger.getLogger(CorisReportCaseStayLookupReportGenerator.class.getName());
	private static String SERVLET_NAME = "UserLoginReportServlet";
	
	public CorisReportUserLoginReportGenerator(ReportBaseSearchCriteria criteria) {
		super(criteria);
		myCriteria = (CorisReportUserLoginReportCriteria) criteria;
	}

	@Override
	public byte[] generateReport(List<?> userLoginList) {
 		if ("pdf".equals(myCriteria.getReportformat())) {
			return generatePdfDocReport((List<PersonnelBO>) userLoginList);
		} else if("excel".equalsIgnoreCase(myCriteria.getReportformat())) {
			return generateExcelDocReport((List<PersonnelBO>)userLoginList);
		}
		return null;
	}
	
	private byte[] generateExcelDocReport(List<PersonnelBO> userLoginList) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Cases On Stay Report");
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (PersonnelBO doc : userLoginList) {
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
		cell.setCellValue("Last Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(1);
		cell.setCellValue("First Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(2);
		cell.setCellValue("Log Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellValue("Last Login");
		cell.setCellStyle(cStyle);
		cell = row.createCell(4);
		cell.setCellValue("Location");
		cell.setCellStyle(cStyle);
		cell = row.createCell(5);
	}

	/**
	 * @param row
	 * @param doc
	 * @throws Exception
	 */
	protected void generateExcelRowData(Row row, PersonnelBO doc) throws Exception {
		Cell cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(doc.getLastName()));
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(doc.getFirstName()));
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(doc.getLogname()));
		cell = row.createCell(3);
		cell.setCellValue(TextUtil.printDatetime(doc.getLastLogin()));
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.print(doc.get(ProfileBO.COURTTITLE)));
	}
	
	private byte[] generatePdfDocReport(List<PersonnelBO> userLoginList) {
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
			document.addTitle("CORIS User Login Report");
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(userLoginList, myCriteria.getCourtType());
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
	private PdfPTable generatePdfResultTable(List<PersonnelBO> userLoginList, String courtType) {
		PdfPTable table = new PdfPTable(new float[] {10, 15, 10, 10, 25});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		// start result rows
		generatePdfReportContents(table, userLoginList, courtType);
		return table;
	}
	
	/**
	 * @param table
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 */
	private void generatePdfReportContents(PdfPTable table, List<PersonnelBO> userLoginList, String courtType) {
		for(PersonnelBO temp : userLoginList){
			try{
				String lastName = (String) temp.get(PersonnelBO.LASTNAME);
				String firstName = (String) temp.get(PersonnelBO.FIRSTNAME);
				String logName = (String) temp.get(PersonnelBO.LOGNAME);
				Date lastLogin = (Date) temp.get(PersonnelBO.LASTLOGIN);
				String location = (String) temp.get(ProfileBO.COURTTITLE);
			
			table.addCell(getPdfContentCell(TextUtil.print(lastName)));
			table.addCell(getPdfContentCell(TextUtil.print(firstName)));
			table.addCell(getPdfContentCell(TextUtil.print(logName)));
			table.addCell(getPdfContentCell(TextUtil.printDatetime(lastLogin)));
			table.addCell(getPdfContentCell(TextUtil.print(location)));
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
		table.addCell(getPdfHeaderCell("Last Name"));
		table.addCell(getPdfHeaderCell("First Name"));
		table.addCell(getPdfHeaderCell("Log Name"));
		table.addCell(getPdfHeaderCell("Date/Time"));
		table.addCell(getPdfHeaderCell("Location"));
		
		table.setHeaderRows(1);
	}
	
	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfReportTitle()
	 */
	@Override
	protected PdfPTable populatePdfReportTitle() {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
	
		try {
			table.setWidths(new float[] {1.0f});
			
			// header line
			Font headerFont = FontFactory.getFont(FontFactory.COURIER, 12f, Font.BOLD);
			Paragraph paragraf = new Paragraph(16, "User Login Report", headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
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
