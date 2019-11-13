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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;

public class CorisAttorneyCasesReportGenerator extends ReportBaseHandler {

	private CorisAttorneyCasesReportCriteria myCriteria;
	
	private static Logger logger = Logger.getLogger(CorisAttorneyCasesReportGenerator.class.getName());
	private static String SERVLET_NAME = "CorisAttorneyCasesServlet";
	
	public CorisAttorneyCasesReportGenerator(ReportBaseSearchCriteria criteria) {
		super(criteria);
		myCriteria = (CorisAttorneyCasesReportCriteria) criteria;
	}

	@Override
	public byte[] generateReport(List<?> attyPartyCaseList) {
		if ("pdf".equals(myCriteria.getReportformat())) {
			return generatePdfDocReport((List<AttyPartyBO>) attyPartyCaseList);
		} else if("excel".equalsIgnoreCase(myCriteria.getReportformat())) {
			return generateExcelDocReport((List<AttyPartyBO>)attyPartyCaseList);
		}
		return null;
	}
	
	private byte[] generateExcelDocReport(List<AttyPartyBO> attyPartyCaseList) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Attorney Cases Report");
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (AttyPartyBO doc : attyPartyCaseList) {
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
		cell.setCellValue("Party Type");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellValue("Party First Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(4);
		cell.setCellValue("Party Last Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(5);
		cell.setCellValue("Judge First Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(6);
		cell.setCellValue("Judge Last Name");
		cell.setCellStyle(cStyle);
		cell = row.createCell(7);
		cell.setCellValue("Case Filing Date");
		cell.setCellStyle(cStyle);
		cell = row.createCell(8);
		cell.setCellValue("Attach Date");
		cell.setCellStyle(cStyle);
		cell = row.createCell(9);
		cell.setCellValue("Detach Date");
		cell.setCellStyle(cStyle);
		cell = row.createCell(10);
	}

	/**
	 * @param row
	 * @param doc
	 * @throws Exception
	 */
	protected void generateExcelRowData(Row row, AttyPartyBO doc) throws Exception {
		Cell cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(doc.get(KaseBO.CASENUM)));
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(doc.get(KaseBO.CASETYPE)));
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(doc.getPartyCode()));
		cell = row.createCell(3);
		cell.setCellValue(TextUtil.print(doc.get(PartyBO.FIRSTNAME)));
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.print(doc.get(PartyBO.LASTNAME)));
		cell = row.createCell(5);
		cell.setCellValue(TextUtil.print(doc.get(PersonnelBO.FIRSTNAME)));
		cell = row.createCell(6);
		cell.setCellValue(TextUtil.print(doc.get(PersonnelBO.LASTNAME)));
		cell = row.createCell(7);
		cell.setCellValue(TextUtil.print(doc.get(KaseBO.FILINGDATE)));
		cell = row.createCell(8);
		cell.setCellValue(TextUtil.print(doc.getAttachDatetime()));
		cell = row.createCell(9);
		cell.setCellValue(TextUtil.print(doc.getDetachDatetime()));
	}
	
	private byte[] generatePdfDocReport(List<AttyPartyBO> attyPartyCaseList) {
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
			document.addTitle("CORIS Attorney Cases Report");
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(attyPartyCaseList, myCriteria.getCourtType());
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
	private PdfPTable generatePdfResultTable(List<AttyPartyBO> attyPartyCaseList, String courtType) {
		PdfPTable table = new PdfPTable(new float[] {15, 15, 15, 20, 20, 20, 20, 20});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		// start result rows
		generatePdfReportContents(table, attyPartyCaseList, courtType);
		return table;
	}
	
	/**
	 * @param table
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 */
	private void generatePdfReportContents(PdfPTable table, List<AttyPartyBO> attyPartyCaseList, String courtType) {
		for(AttyPartyBO temp : attyPartyCaseList){
			try{
				String partyCode = (String) temp.getPartyCode();
				String caseNum = (String) temp.get(KaseBO.CASENUM);
				String caseType = (String) temp.get(KaseBO.CASETYPE);
				String party = (String) temp.get(PartyBO.LASTNAME);
				if(!TextUtil.isEmpty((String) temp.get(PartyBO.FIRSTNAME))){
					party += ", "+ temp.get(PartyBO.FIRSTNAME);
				}
				String judge = (String) temp.get(PersonnelBO.FIRSTNAME);
				if(!TextUtil.isEmpty((String) temp.get(PersonnelBO.FIRSTNAME))){
						judge += " " + temp.get(PersonnelBO.LASTNAME);
				}
				Date caseFileDate = (Date) temp.get(KaseBO.FILINGDATE);
				Date attachDate = (Date) temp.getAttachDatetime();
				Date detachDate = (Date) temp.getDetachDatetime();
				
				table.addCell(getPdfContentCell(TextUtil.print(caseNum)));
				table.addCell(getPdfContentCell(TextUtil.print(caseType)));
				table.addCell(getPdfContentCell(TextUtil.print(partyCode)));
				table.addCell(getPdfContentCell(TextUtil.print(party)));
				table.addCell(getPdfContentCell(TextUtil.print(judge)));
				table.addCell(getPdfContentCell(TextUtil.printDate(caseFileDate)));
				table.addCell(getPdfContentCell(TextUtil.printDate(attachDate)));
				table.addCell(getPdfContentCell(TextUtil.printDate(detachDate)));
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
		table.addCell(getPdfHeaderCell("Party Type"));
		table.addCell(getPdfHeaderCell("Party Name"));
		table.addCell(getPdfHeaderCell("Assigned Judge"));
		table.addCell(getPdfHeaderCell("Case Filing Date"));
		table.addCell(getPdfHeaderCell("Attach Date"));
		table.addCell(getPdfHeaderCell("Detach Date"));
		
		table.setHeaderRows(1);
		
	}
	
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
			location = new LocationBO(myCriteria.getCourtType())
					.where(LocationBO.COURTTYPE, myCriteria.getCourtType())
					.where(LocationBO.LOCNCODE, myCriteria.getLocnCode())
					.find(LocationBO.LOCNDESCR).getLocnDescr();

			table.setWidths(new float[] {1.0f});
			
			// header line
			Paragraph paragraf = new Paragraph(16, "Attorney Cases", BOLDFONT);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			paragraf = new Paragraph(16, "Location: " + location, BOLDFONT);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			if (!TextUtil.isEmpty(myCriteria.getCaseNum())) {
				paragraf = new Paragraph(16, "Case Num: " + myCriteria.getCaseNum(), BOLDFONT);
				paragraf.setAlignment(Element.ALIGN_CENTER);
				headerCell = new PdfPCell(paragraf);
				headerCell.setBorder(PdfPCell.NO_BORDER);
				headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				table.addCell(headerCell);
			}
			if (myCriteria.getAttachDatetimeStart() != null) {
				paragraf = new Paragraph(16, "Attach Date From: " + TextUtil.printDate(myCriteria.getAttachDatetimeStart()) + " To: " + TextUtil.printDate(myCriteria.getAttachDatetimeEnd()), BOLDFONT);
				paragraf.setAlignment(Element.ALIGN_CENTER);
				headerCell = new PdfPCell(paragraf);
				headerCell.setBorder(PdfPCell.NO_BORDER);
				headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				table.addCell(headerCell);
			}
			if (myCriteria.getCasesFiledFrom() != null) {
				paragraf = new Paragraph(16, "Cases filed From: " + TextUtil.printDate(myCriteria.getCasesFiledFrom()) + " To: " + TextUtil.printDate(myCriteria.getCasesFiledTo()), BOLDFONT);
				paragraf.setAlignment(Element.ALIGN_CENTER);
				headerCell = new PdfPCell(paragraf);
				headerCell.setBorder(PdfPCell.NO_BORDER);
				headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				table.addCell(headerCell);
			}
			paragraf = new Paragraph(16, "" , BOLDFONT);
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
