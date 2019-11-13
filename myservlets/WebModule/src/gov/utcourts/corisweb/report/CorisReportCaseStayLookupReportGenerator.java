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
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Document;
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

import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.stay.StayBO;
import gov.utcourts.coriscommon.dataaccess.staytype.StayTypeBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;

public class CorisReportCaseStayLookupReportGenerator extends ReportBaseHandler {

	private CorisReportCaseStayLookupReportCriteria myCriteria;
	
	private static Logger logger = Logger.getLogger(CorisReportCaseStayLookupReportGenerator.class.getName());
	private static String SERVLET_NAME = "CaseStayLookupServlet";
	
	public CorisReportCaseStayLookupReportGenerator(ReportBaseSearchCriteria criteria) {
		super(criteria);
		myCriteria = (CorisReportCaseStayLookupReportCriteria) criteria;
	}

	@Override
	public byte[] generateReport(List<?> caseStayList) {
		if ("pdf".equals(myCriteria.getReportformat())) {
			return generatePdfDocReport((List<StayBO>) caseStayList);
		} else if("excel".equalsIgnoreCase(myCriteria.getReportformat())) {
			return generateExcelDocReport((List<StayBO>)caseStayList);
		}
		return null;
	}
	
	private byte[] generateExcelDocReport(List<StayBO> caseStayList) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Cases On Stay Report");
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (StayBO doc : caseStayList) {
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
		cell.setCellValue("Case Type");
		cell.setCellStyle(cStyle);
		cell = row.createCell(2);
		cell.setCellValue("Stay Begin Date");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellValue("Stay End Date");
		cell.setCellStyle(cStyle);
		cell = row.createCell(4);
		cell.setCellValue("Reason");
		cell.setCellStyle(cStyle);
		cell = row.createCell(5);
		cell.setCellValue("Judge First Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(6);
		cell.setCellValue("Judge Last Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(7);
		cell.setCellValue("Commissioner First Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(8);
		cell.setCellValue("Commissioner Last Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(9);
	}

	/**
	 * @param row
	 * @param doc
	 * @throws Exception
	 */
	protected void generateExcelRowData(Row row, StayBO doc) throws Exception {
		String courtType = myCriteria.getCourtType();
		String caseDescr = (String) doc.get(CaseTypeBO.DESCR);
		String judgeFName = "";
		String judgeLName = "";
		String commFName = "";
		String commLName = "";
		
		if (doc.get(KaseBO.ASSNJUDGEID) != null && (Integer) doc.get(KaseBO.ASSNJUDGEID) > 0){
			judgeLName = (String) doc.get("judge_last_name"); 
			if(!TextUtil.isEmpty((String) doc.get("judge_first_name"))){
				judgeFName = (String) doc.get("judge_first_name");
			}
		}
		
		if (doc.get(KaseBO.ASSNCOMMID) != null && (Integer) doc.get(KaseBO.ASSNCOMMID) > 0) {
			commLName = (String) doc.get("commissioner_last_name"); 
			if(!TextUtil.isEmpty((String) doc.get("commissioner_first_name"))){
				commFName = (String) doc.get("commissioner_first_name");
			}
		}
		String stayReason = (String) doc.get("stay_code_descr");
		
		Cell cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(doc.get(KaseBO.CASENUM)));
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(caseDescr));
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(doc.getBeginDate()));
		cell = row.createCell(3);
		cell.setCellValue(TextUtil.print(doc.getEndDate()));
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.print(stayReason));
		cell = row.createCell(5);
		cell.setCellValue(TextUtil.print(judgeFName));
		cell = row.createCell(6);
		cell.setCellValue(TextUtil.print(judgeLName));
		cell = row.createCell(7);
		cell.setCellValue(TextUtil.print(commFName));
		cell = row.createCell(8);
		cell.setCellValue(TextUtil.print(commLName));
	}
	
	private byte[] generatePdfDocReport(List<StayBO> caseStayList) {
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
			document.addTitle("CORIS Cases Placed On Stay Report");
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(caseStayList, myCriteria.getCourtType());
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
	private PdfPTable generatePdfResultTable(List<StayBO> caseStayList, String courtType) {
		PdfPTable table = new PdfPTable(new float[] {10, 15, 10, 10, 15, 20, 20});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		// start result rows
		generatePdfReportContents(table, caseStayList, courtType);
		return table;
	}
	
	/**
	 * @param table
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 */
	private void generatePdfReportContents(PdfPTable table, List<StayBO> caseStayList, String courtType) {
		for(StayBO temp : caseStayList){
			try{
				StringBuilder judge = new StringBuilder();
				StringBuilder commissioner = new StringBuilder();
				String caseNum = (String) temp.get(KaseBO.CASENUM);
				String caseDescr = (String) temp.get(CaseTypeBO.DESCR);
				Date stayBeginDate = (Date) temp.get(StayBO.BEGINDATE);
				Date stayEndDate = (Date) temp.get(StayBO.ENDDATE);
				String stayReason = (String) temp.get("stay_code_descr");
				if (temp.get(KaseBO.ASSNJUDGEID) != null && (Integer) temp.get(KaseBO.ASSNJUDGEID) > 0) {
					judge.append((String) temp.get("judge_last_name")); 
					if(!TextUtil.isEmpty((String) temp.get("judge_first_name"))){
						judge.append(", ").append(temp.get("judge_first_name"));
					}
				}	
				if (temp.get(KaseBO.ASSNCOMMID) != null && (Integer) temp.get(KaseBO.ASSNCOMMID) > 0) {
					commissioner.append((String) temp.get("commissioner_last_name")); 
					if(!TextUtil.isEmpty((String) temp.get("commissioner_first_name"))){
						commissioner.append(", ").append(temp.get("commissioner_first_name"));
					}
				}
			
			table.addCell(getPdfContentCell(TextUtil.print(caseNum)));
			table.addCell(getPdfContentCell(TextUtil.print(caseDescr)));
			table.addCell(getPdfContentCell(TextUtil.print(stayBeginDate)));
			table.addCell(getPdfContentCell(TextUtil.print(stayEndDate)));
			table.addCell(getPdfContentCell(TextUtil.print(stayReason)));
			table.addCell(getPdfContentCell(TextUtil.print(judge)));
			table.addCell(getPdfContentCell(TextUtil.print(commissioner)));
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
		table.addCell(getPdfHeaderCell("Case Type"));
		table.addCell(getPdfHeaderCell("Stay Begin Date"));
		table.addCell(getPdfHeaderCell("Stay End Date"));
		table.addCell(getPdfHeaderCell("Reason"));
		table.addCell(getPdfHeaderCell("Judge"));
		table.addCell(getPdfHeaderCell("Commissioner"));
		
		table.setHeaderRows(1);
	}
	
	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfReportTitle()
	 */
	@Override
	protected PdfPTable populatePdfReportTitle() {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);

		String caseDescr = "";
		String location = "";
		String stayDescr = "";
		String judgeCommFName = "";
		String judgeCommLName = "";
		String stayReason = "";
	
		try {
			
			Document document = new Document(PageSize.LETTER);
			DateFormat stayDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			location = new LocationBO(myCriteria.getCourtType())
				.where(LocationBO.COURTTYPE, myCriteria.getCourtType())
				.where(LocationBO.LOCNCODE, myCriteria.getLocnCode())
				.find(LocationBO.LOCNDESCR).getLocnDescr();
			
			caseDescr = new CaseTypeBO(myCriteria.getCourtType())
				.where(CaseTypeBO.CASETYPE, myCriteria.getCaseType())
				.find(CaseTypeBO.DESCR).getDescr();
			
			stayDescr = new StayTypeBO(myCriteria.getCourtType())
				.where(StayTypeBO.STAYCODE, myCriteria.getStayReason())
				.find(StayTypeBO.DESCR).getDescr();
			
			judgeCommFName = new PersonnelBO(myCriteria.getCourtType())
				.where(PersonnelBO.USERIDSRL, myCriteria.getJudgeCommId())
				.find(PersonnelBO.FIRSTNAME).getFirstName();
			judgeCommLName = new PersonnelBO(myCriteria.getCourtType())
				.where(PersonnelBO.USERIDSRL, myCriteria.getJudgeCommId())
				.find(PersonnelBO.LASTNAME).getLastName();

			table.setWidths(new float[] {1.0f});
			
			// header line
			Font headerFont = FontFactory.getFont(FontFactory.COURIER, 12f, Font.BOLD);
			Paragraph paragraf = new Paragraph(16, "Cases Placed On Stay", headerFont);
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
			paragraf = new Paragraph(16, "Cases Placed on Stay From: " + TextUtil.printDate(myCriteria.getStayDateFrom()) + " To: " + TextUtil.printDate(myCriteria.getStayDateTo()), headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			if (!TextUtil.isEmpty(myCriteria.getCaseType())) {
				paragraf = new Paragraph(16, "Case Type: " + caseDescr, headerFont);
				paragraf.setAlignment(Element.ALIGN_CENTER);
				headerCell = new PdfPCell(paragraf);
				headerCell.setBorder(PdfPCell.NO_BORDER);
				headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				table.addCell(headerCell);
			} else {
				paragraf = new Paragraph(16, "All Case Types", headerFont);
				paragraf.setAlignment(Element.ALIGN_CENTER);
				headerCell = new PdfPCell(paragraf);
				headerCell.setBorder(PdfPCell.NO_BORDER);
				headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				table.addCell(headerCell);
			}
			if (!TextUtil.isEmpty(myCriteria.getStayReason())) {
				paragraf = new Paragraph(16, "Stay Reason: " + stayDescr, headerFont);
				paragraf.setAlignment(Element.ALIGN_CENTER);
				headerCell = new PdfPCell(paragraf);
				headerCell.setBorder(PdfPCell.NO_BORDER);
				headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				table.addCell(headerCell);
			} else {
				paragraf = new Paragraph(16, "All Stay Reaons", headerFont);
				paragraf.setAlignment(Element.ALIGN_CENTER);
				headerCell = new PdfPCell(paragraf);
				headerCell.setBorder(PdfPCell.NO_BORDER);
				headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				table.addCell(headerCell);
			}
			if (myCriteria.getJudgeCommId() > 0) {
				paragraf = new Paragraph(16, "Judge/Commissioner: " + judgeCommFName + " " + judgeCommLName, headerFont);
				paragraf.setAlignment(Element.ALIGN_CENTER);
				headerCell = new PdfPCell(paragraf);
				headerCell.setBorder(PdfPCell.NO_BORDER);
				headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				table.addCell(headerCell);
			} else {
				paragraf = new Paragraph(16, "All Judge/Commissioners", headerFont);
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
