package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.civilcase.CivilCaseBO;
import gov.utcourts.coriscommon.dataaccess.document.DocumentBO;
import gov.utcourts.coriscommon.dataaccess.documenttypeprofile.DocumentTypeProfileBO;
import gov.utcourts.coriscommon.dataaccess.efilingactivity.EfilingActivityBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisinterface.util.CorisCaseHistoryCommon;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;

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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CorisDocumentReport extends ReportBaseHandler {

	private static Logger logger = Logger.getLogger(CorisDocumentReport.class.getName());
	public static String JUDGE_NAME = "JudgeName";
	public static String EFILE_ACTIVITIES = "efileActivities";
	private DocumentReportSearchCriteria thisCriteria;
	
	public CorisDocumentReport(ReportBaseSearchCriteria criteria) {
		super(criteria);
		thisCriteria = (DocumentReportSearchCriteria) criteria;
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#generateReport(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public byte[] generateReport(List<?> docList) {
		if ("pdf".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generatePdfDocReport((List<DocumentBO>)docList);
		} else if("xlsx".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generateExcelDocReport((List<DocumentBO>)docList);
		}
		return null;
	}
	
	/**
	 * @param docList
	 * @return
	 */
	private byte[] generateExcelDocReport(List<DocumentBO> docList) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Document Report");
		int rowCount = 0;
		Row row = null;
		try {
			rowCount = createExcelReportTitle(sheet);
			row = sheet.createRow(rowCount); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (DocumentBO doc : docList) {
				row = sheet.createRow(++rowCount);
				generateExcelRowData(row, doc);
			}
			for(int c=0; c < 11; c++){
				sheet.autoSizeColumn(c);
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
	
	private int createExcelReportTitle(XSSFSheet sheet) {
		
		Row row = sheet.createRow(1); //skip the first row as old report does
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Cell cell = row.createCell(0);
		cell.setCellValue(thisCriteria.getLocnDescr());
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(2); //skip the first row as old report does
		cell = row.createCell(0);
		cell.setCellValue("Document Report");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(3); //skip the first row as old report does
		cell = row.createCell(0);
		cell.setCellValue("Report Date Range: from " + TextUtil.printDate(thisCriteria.getStartDate()) + " to " + TextUtil.printDate(thisCriteria.getEndDate()));
		cell.setCellStyle(cStyle);
		
		return 5; //skip one row to start column header
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populateExcelColumnHeaders(org.apache.poi.ss.usermodel.Row, org.apache.poi.xssf.usermodel.XSSFSheet)
	 */
	@Override
	protected void populateExcelColumnHeaders(Row row, XSSFSheet sheet) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Cell cell = row.createCell(0);
		cell.setCellValue("Date");
		cell.setCellStyle(cStyle);
		cell = row.createCell(1);
		cell.setCellValue("CaseNum");
		cell.setCellStyle(cStyle);
		cell = row.createCell(2);
		cell.setCellValue("Type");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellValue("Tier");
		cell.setCellStyle(cStyle);
		cell = row.createCell(4);
		cell.setCellValue("Judge");
		cell.setCellStyle(cStyle);
		cell = row.createCell(5);
		cell.setCellValue("Clerk");
		cell.setCellStyle(cStyle);
		cell = row.createCell(6);
		cell.setCellValue("E");
		cell.setCellStyle(cStyle);
		cell = row.createCell(7);
		cell.setCellValue("Case Sec");
		cell.setCellStyle(cStyle);
		cell = row.createCell(8);
		cell.setCellValue("Doc Sec");
		cell.setCellStyle(cStyle);
		cell = row.createCell(9);
		cell.setCellValue("Img");
		cell.setCellStyle(cStyle);
		cell = row.createCell(10);
		cell.setCellValue("Document Type");
		cell.setCellStyle(cStyle);
		cell = row.createCell(11);
		cell.setCellValue("Title");
		cell.setCellStyle(cStyle);
		
	}

	/**
	 * @param row
	 * @param doc
	 * @throws Exception
	 */
	protected void generateExcelRowData(Row row, DocumentBO doc) throws Exception {
		String courtType = thisCriteria.getCourtType();
		HashMap<String, String> judgeAndEActivities = getJudgeNameAndEfileActivities(courtType, doc.getIntCaseNum(), doc.getDocumentNum());
		Cell cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(doc.getEntryDatetime()));
		cell = row.createCell(1);
		cell.setCellValue((String)doc.get(KaseBO.CASENUM));
		cell = row.createCell(2);
		cell.setCellValue((String)doc.get(KaseBO.CASETYPE));
		cell = row.createCell(3);
		cell.setCellValue(TextUtil.print(doc.get(CivilCaseBO.DISCOVERYTIER)));
		cell = row.createCell(4);
		cell.setCellValue(judgeAndEActivities.get(JUDGE_NAME));
		cell = row.createCell(5);
		cell.setCellValue(TextUtil.print(doc.get(PersonnelBO.LOGNAME)));
		cell = row.createCell(6);
		cell.setCellValue(judgeAndEActivities.get(EFILE_ACTIVITIES));
		cell = row.createCell(7);
		cell.setCellValue(Constants.CASE_SEC_CODE_MAP.get(doc.get(KaseBO.CASESECURITY)));
		cell = row.createCell(8);
		cell.setCellValue(Constants.DOC_SEC_CODE_MAP.get(doc.getDocSecurity()));
		cell = row.createCell(9);
		cell.setCellValue((doc.getDmDocid() > 0 && !thisCriteria.isDocWithoutImgOnly() ? "" : " N "));
		cell = row.createCell(10);
		cell.setCellValue((String) doc.get(DocumentTypeProfileBO.DDLBENTRY));
		cell = row.createCell(11);
		cell.setCellValue(doc.getTitle());
	}
	
	/**
	 * @param docList
	 * @return
	 */
	private byte[] generatePdfDocReport(List<DocumentBO> docList) {
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
			document.addTitle("CORIS Document Report");
			document.setMargins(20f, 22f, 25f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(docList, thisCriteria.getCourtType(), thisCriteria.isDocWithoutImgOnly());
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
	private PdfPTable generatePdfResultTable(List<DocumentBO> docList, String courtType, boolean withoutImgOnly) {
		PdfPTable table = new PdfPTable(new float[] {9, 10, 5, 5, 8, 7, 3, 8, 8, 4, 13, 20});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		// start result rows
		generatePdfReportContents(table, docList, courtType, withoutImgOnly);
		return table;
	}
	
	/**
	 * @param table
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 */
	private void generatePdfReportContents(PdfPTable table, List<DocumentBO> docList, String courtType, boolean withoutImgOnly) {
		for (DocumentBO doc : docList) {
			try {
				HashMap<String, String> judgeAndEActivities = getJudgeNameAndEfileActivities(courtType, doc.getIntCaseNum(), doc.getDocumentNum());
				table.addCell(getPdfContentCell(TextUtil.printDate(doc.getEntryDatetime())));
				table.addCell(getPdfContentCell((String)doc.get(KaseBO.CASENUM)));
				table.addCell(getPdfContentCell((String)doc.get(KaseBO.CASETYPE)));
				table.addCell(getPdfContentCell(TextUtil.print(doc.get(CivilCaseBO.DISCOVERYTIER))));
				table.addCell(getPdfContentCell(judgeAndEActivities.get(JUDGE_NAME)));
				table.addCell(getPdfContentCell(TextUtil.print(doc.get(PersonnelBO.LOGNAME))));
				table.addCell(getPdfContentCell(judgeAndEActivities.get(EFILE_ACTIVITIES)));
				table.addCell(getPdfContentCell(Constants.CASE_SEC_CODE_MAP.get(doc.get(KaseBO.CASESECURITY))));
				table.addCell(getPdfContentCell(Constants.DOC_SEC_CODE_MAP.get(doc.getDocSecurity())));
				table.addCell(getPdfContentCell((doc.getDmDocid() > 0 && !withoutImgOnly) ? "" : " N "));
				table.addCell(getPdfContentCell((String) doc.get(DocumentTypeProfileBO.DDLBENTRY)));
				table.addCell(getPdfContentCell(doc.getTitle()));
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
		table.addCell(getPdfHeaderCell("Date"));
		table.addCell(getPdfHeaderCell("CaseNum"));
		table.addCell(getPdfHeaderCell("Type"));
		table.addCell(getPdfHeaderCell("Tier"));
		table.addCell(getPdfHeaderCell("Judge"));
		table.addCell(getPdfHeaderCell("Clerk"));
		table.addCell(getPdfHeaderCell("E"));
		table.addCell(getPdfHeaderCell("Case Sec"));
		table.addCell(getPdfHeaderCell("Doc Sec"));
		table.addCell(getPdfHeaderCell("Img"));
		table.addCell(getPdfHeaderCell("Doc Type"));
		table.addCell(getPdfHeaderCell("Title"));
		
		// add dotted line to separate header and table results
//		Paragraph p = new Paragraph();
//		DottedLineSeparator dottedline = new DottedLineSeparator();
//		dottedline.setOffset(-2);
//		dottedline.setGap(2f);
//		dottedline.setAlignment(DottedLineSeparator.ALIGN_TOP);
//		p.add(dottedline);
//		
//		PdfPCell c = new PdfPCell(p);
//		c.setColspan(12);
//		c.setBorder(PdfPCell.NO_BORDER);
//		c.setVerticalAlignment(PdfPCell.ALIGN_TOP);
//		table.addCell(c);
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
			
			// spacer rows
//			for (int i = 0; i < 2; i++)
				CorisCaseHistoryCommon.addCell(table, " ", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			// header line
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 14f, Font.NORMAL);
			Paragraph paragraf = new Paragraph(16, thisCriteria.getLocnDescr(), headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			paragraf = new Paragraph(16, "Document Report", headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			paragraf = new Paragraph(16, "FROM " + TextUtil.printDate(thisCriteria.getStartDate()) + " to " + TextUtil.printDate(thisCriteria.getEndDate()), headerFont);
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
	
	private HashMap<String, String> getJudgeNameAndEfileActivities(String courtType, int intCaseNum, int docNum) throws Exception {
		HashMap<String, String> result = new HashMap<String, String>();
		
		PersonnelBO personnelBO = new PersonnelBO(courtType).includeFields(PersonnelBO.LOGNAME)
				.includeTables(new KaseBO(courtType).includeFields(KaseBO.INTCASENUM).where(KaseBO.INTCASENUM, intCaseNum))
				.addForeignKey(PersonnelBO.USERIDSRL, KaseBO.ASSNJUDGEID).orderBy(PersonnelBO.LOGNAME, DirectionType.DESC);
		EfilingActivityBO efileBO = new EfilingActivityBO(courtType).includeFields(EfilingActivityBO.EFILACTIVITYSRL)
						.where(EfilingActivityBO.INTCASENUM, intCaseNum).where(EfilingActivityBO.EFILACTIVITYTYPE,"DOCUMENT")
						.where(EfilingActivityBO.EVENTID,String.valueOf(docNum));
		
		setDocumentReportOrderBy(personnelBO, efileBO);
		
		String judgeName = personnelBO.find(PersonnelBO.LOGNAME).getLogname();
		List<EfilingActivityBO> efileActivities = efileBO.search();
		result.put(EFILE_ACTIVITIES, efileActivities.size() > 0 ? "E" : "");
		result.put(JUDGE_NAME, judgeName);

		return result;
	}
	
	private void setDocumentReportOrderBy(PersonnelBO personnelBO, EfilingActivityBO efileBO) {
		List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(thisCriteria);
		if (orderByPairs != null) {
			for (OrderByPair orderByPair : orderByPairs) {
				switch (orderByPair.getColumnPosition()) {
				case 4:
					personnelBO.orderBy(PersonnelBO.LOGNAME, orderByPair.getDirectionType());
					break;
				case 6:
					efileBO.orderBy(EfilingActivityBO.EFILACTIVITYSRL, orderByPair.getDirectionType());
					break;
				}
			}
		}
	}
	
}
