package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.chrgattr.ChrgAttrBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisinterface.util.CorisCaseHistoryCommon;

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

public class CorisDomViolReport extends ReportBaseHandler {
	private static Logger logger = Logger.getLogger(CorisDomViolReport.class.getName());
	private CorisDomViolReportCriteria rptCriteria;
	
	public CorisDomViolReport(ReportBaseSearchCriteria criteria) {
		super(criteria);
		rptCriteria = (CorisDomViolReportCriteria)criteria;
	}

	@Override
	public byte[] generateReport(List<?> reportData) throws Exception {
		@SuppressWarnings("unchecked")
		List<KaseBO> results = (List<KaseBO>)reportData;
		if ("pdf".equalsIgnoreCase(rptCriteria.getReportformat())) {
			return generatePdfDocReport(results);
		} else if("xlsx".equalsIgnoreCase(rptCriteria.getReportformat())) {
			return generateExcelDocReport(results);
		}
		return null;
	}
	
	private byte[] generatePdfDocReport(List<KaseBO> results) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfPTable table = null;
		
		try {
			// ///////////////////////////////////////////////////////////////
			// Create new document
			// ///////////////////////////////////////////////////////////////
			Document document = new Document(PageSize.LETTER);
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			writer.setPageEvent(this);
			
			// ///////////////////////////////////////////////////////////////
			// Document Set Up - Left, Right, Top, Bottom
			// ///////////////////////////////////////////////////////////////
			document.addCreationDate();
			document.addCreator("Utah State Court -- AOC");
			document.addTitle("Domestic Violence Report");
			document.setMargins(20f, 22f, 25f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(results);
			document.add(table);
			//summary table
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
	
	private PdfPTable generatePdfResultTable(List<KaseBO> results) throws Exception {
		PdfPTable table = new PdfPTable(new float[] {15, 6, 25, 15, 15, 9, 15});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		generatePdfReportContents(table, results);
		return table;
	}

	private void generatePdfReportContents(PdfPTable table, List<KaseBO> results) throws Exception {
		for(KaseBO kase:results) { 
			String lvl = CorisDomViolReport.calcDomViolLevel(kase.getIntCaseNum(),rptCriteria.getCourtReadOnlyDB());
			if(lvl != null && CorisDomViolReport.isDvCharge(kase.getIntCaseNum(),(Integer)kase.get(ChargeBO.SEQ), rptCriteria.getCourtReadOnlyDB())){
				table.addCell(getPdfContentCell(kase.getCaseNum()));
				table.addCell(getPdfContentCell(kase.getCaseType()));
				table.addCell(getPdfContentCell(kase.get(PartyBO.LASTNAME) + ", " + kase.get(PartyBO.FIRSTNAME)));
				table.addCell(getPdfContentCell(TextUtil.printDate(kase.getFilingDate())));
				table.addCell(getPdfContentCell(TextUtil.printDate((Date)kase.get(ChargeBO.JDMTDATE))));
				table.addCell(getPdfContentCell(String.valueOf(kase.get(ChargeBO.SEQ))));
				table.addCell(getPdfContentCell(lvl));
			}
		}
	}

	private byte[] generateExcelDocReport(List<KaseBO> kases) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Document Report");
		int rowCount = 0;
		Row row = null;
		try {
			rowCount = createExcelReportTitle(sheet);
			row = sheet.createRow(rowCount); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (KaseBO kase : kases ) {
				String lvl = CorisDomViolReport.calcDomViolLevel(kase.getIntCaseNum(),rptCriteria.getCourtReadOnlyDB());
				if(lvl != null && CorisDomViolReport.isDvCharge(kase.getIntCaseNum(),(Integer)kase.get(ChargeBO.SEQ), rptCriteria.getCourtReadOnlyDB())){
					row = sheet.createRow(++rowCount);
					generateExcelRowData(row, kase,lvl);
				}
			}
			
			for(int c=0; c < 7; c++){
				sheet.autoSizeColumn(c);
			}
			workbook.write(baos);
		} catch (Exception e) {
			logger.error(e.getMessage());
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


	private int createExcelReportTitle(XSSFSheet sheet) {
		
		Row row = sheet.createRow(1); //skip the first row as old report does
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Cell cell = row.createCell(0);
		cell.setCellValue(rptCriteria.getLocnDescr());
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(2); //skip the first row as old report does
		cell = row.createCell(0);
		cell.setCellValue("Domestic Violence Report");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(3); //skip the first row as old report does
		cell = row.createCell(0);
		cell.setCellValue("Case " + (rptCriteria.isDispositionDateType()?"Disposition":"Filing") + " Date Range: from " + TextUtil.printDate(rptCriteria.getStartDate()) + " to " + TextUtil.printDate(rptCriteria.getEndDate()));
		cell.setCellStyle(cStyle);
		
		return 5; //skip one row to start column header
	}

	private void generateExcelRowData(Row row, KaseBO kase, String lvl) {
		Cell cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(kase.getCaseNum()));
		cell = row.createCell(1);
		cell.setCellValue(kase.getCaseType());
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(kase.get(PartyBO.FIRSTNAME)) + " " + kase.get(PartyBO.LASTNAME));
		cell = row.createCell(3);
		cell.setCellValue(TextUtil.printDate(kase.getFilingDate()));
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.printDate((Date)kase.get(ChargeBO.JDMTDATE)));
		cell = row.createCell(5);
		cell.setCellValue((Integer)kase.get(ChargeBO.SEQ));
		cell = row.createCell(6);
		cell.setCellValue(lvl);
		
	}

	@Override
	protected PdfPTable populatePdfReportTitle() throws DocumentException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		try {
			table.setWidths(new float[] {1.0f});

			CorisCaseHistoryCommon.addCell(table, " ", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			// header line
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 14f, Font.NORMAL);
			Paragraph paragraf = new Paragraph(16, rptCriteria.getLocnDescr(), headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			paragraf = new Paragraph(16, "Domesitc Violence Report", headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			paragraf = new Paragraph(16, "FROM " + TextUtil.printDate(rptCriteria.getStartDate()) + " to " + TextUtil.printDate(rptCriteria.getEndDate()), headerFont);
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
			throw e;
		}
		return table;
	}

	@Override
	protected void populatePdfTableColumnHeaders(PdfPTable table) {
		table.addCell(getPdfHeaderCell("Case Number"));
		table.addCell(getPdfHeaderCell("Type"));
		table.addCell(getPdfHeaderCell("Defendant Name"));
		table.addCell(getPdfHeaderCell("Filing Date"));
		table.addCell(getPdfHeaderCell("Disposition Date"));
		table.addCell(getPdfHeaderCell("Charge"));
		table.addCell(getPdfHeaderCell("DV Level"));
		table.setHeaderRows(1);
	}

	@Override
	protected void populateExcelColumnHeaders(Row row, XSSFSheet sheet) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("Case Number");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("Type");
		cell.setCellStyle(cStyle);

		cell = row.createCell(2);
		cell.setCellValue("Defendant Name");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("Filing Date");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("Disp Date");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(5);
		cell.setCellValue("Charge");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(6);
		cell.setCellValue("DV Level");
		cell.setCellStyle(cStyle);
	}
	
	public static String calcDomViolLevel(int intCaseNum, String courtDBType) throws Exception{
		String devLevel = null;
		if(new KaseBO(courtDBType).includeFields(KaseBO.INTCASENUM)
								.where(KaseBO.INTCASENUM,intCaseNum)
								.where(KaseBO.DOMVIOLENCE,"Y").search().size() > 0){
			devLevel = "Case";
			
		}
		
		if(new ChrgAttrBO(courtDBType).includeFields(ChrgAttrBO.INTCASENUM)
				.where(ChrgAttrBO.INTCASENUM,intCaseNum)
				.where(ChrgAttrBO.ATTRCODE,"DV").search().size() > 0){
			if("Case".equals(devLevel)){
				devLevel="Case/Charge";
			}else {
				devLevel = "Charge";
			}
		}
	
		return devLevel;
	}
	
	public static boolean isDvCharge(int intCaseNum, int seq, String courtType) throws Exception{
		return new ChrgAttrBO(courtType).includeFields(ChrgAttrBO.INTCASENUM)
							.where(ChrgAttrBO.INTCASENUM,intCaseNum)
							.where(ChrgAttrBO.SEQ,seq)
							.where(ChrgAttrBO.ATTRCODE,"DV")
							.where(ChrgAttrBO.PARTYCODE,"DEF").search().size() > 0;
							
	}

}
