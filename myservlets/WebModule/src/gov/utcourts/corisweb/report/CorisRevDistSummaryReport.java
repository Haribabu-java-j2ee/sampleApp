package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.casefeature.CaseFeatureBO;
import gov.utcourts.coriscommon.dataaccess.schooldistrict.SchoolDistrictBO;
import gov.utcourts.coriscommon.dataaccess.transdist.TransDistBO;
import gov.utcourts.coriscommon.dto.NsfChecksDTO;
import gov.utcourts.coriscommon.dto.RevenueDetailDTO;
import gov.utcourts.coriscommon.dto.RevenueDistributionSummaryDTO;
import gov.utcourts.coriscommon.dto.RevenueHeLeaDTO;
import gov.utcourts.coriscommon.dto.RevenueProsecDTO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.corisinterface.util.CorisCaseHistoryCommon;

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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CorisRevDistSummaryReport extends ReportBaseHandler {
	private static Logger logger = Logger.getLogger(CorisRevDistSummaryReport.class.getName());
	CorisReportRevDistSumSearchCriteria rptCriteria;

	
	double heleaAccum = 0;
	double rvLeaAccum = 0;
	double ybSchlTotal = 0;
   	double ybStateTotal = 0;
   	double stateFnedew = 0;
   	double stateHe = 0;
   	double stateIr = 0;
   	double stateTr = 0;
   	double zero = 0;
   	double totalNsf=0;
   	double totalRev = 0;
   	double prosecFnedew = 0;
	double prosecHe = 0;
	double prosecIr = 0;
	double prosecTr = 0;

	public CorisRevDistSummaryReport(ReportBaseSearchCriteria criteria) {
		super(criteria);
		rptCriteria = (CorisReportRevDistSumSearchCriteria)criteria;
	}

	@Override
	public byte[] generateReport(List<?> reportData) throws Exception {
		RevenueDistributionSummaryDTO rptData = reportData.size() > 0? (RevenueDistributionSummaryDTO)reportData.get(0):new RevenueDistributionSummaryDTO();
		
		if("pdf".equalsIgnoreCase(rptCriteria.getReportformat())){
			return generatePdfReport(rptData);
		}else if("xlsx".equalsIgnoreCase(rptCriteria.getReportformat())){
			return generateExcelReport(rptData);
		}
		return null;
	}

	private byte[] generateExcelReport(RevenueDistributionSummaryDTO rptData) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Revenue Distribution Summary Report");
		int rowCount = 0;

		try {
			rowCount = createExcelReportTitle(sheet);

			rowCount = generateExcelDistDetails(sheet, ++rowCount, rptData.getDistrDetails());
			
			rowCount = generateExcelRevProsecs(sheet, ++rowCount, rptData.getRevenueProsecs());
			
			if(rptData.getYbTotal() > 0){
				rowCount = generateExcelSchoolFines(sheet, ++rowCount, rptData.getYbDistributions());
			}
			
			rowCount = generateExcelHeLeas(sheet, ++rowCount, rptData.getRevenueHeLeas());
			
			rowCount = generateExCelTotals(sheet, ++rowCount, rptData.getYbTotal());
			
			rowCount = generateExcelDeposits(sheet, ++rowCount, rptData);

			rowCount  = generateExcelNsfReversals(sheet, ++rowCount, rptData.getNsfChecks());

			generateExcelEnding(sheet, ++rowCount);
			
			for(int c=0; c < 8; c++){
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

	private void generateExcelEnding(XSSFSheet sheet, int r) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Row row = sheet.createRow(r);
		Cell cell = row.createCell(0);
		cell.setCellValue("___________________");
		cell.setCellStyle(cStyle);
		cell = row.createCell(1);
		cell.setCellValue("___________________");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(++r);
		cell = row.createCell(0);
		cell.setCellValue("Prepared By");
		cell.setCellStyle(cStyle);
		cell = row.createCell(1);
		cell.setCellValue("Phone #");
		cell.setCellStyle(cStyle);
	}

	private int generateExcelNsfReversals(XSSFSheet sheet, int r, List<NsfChecksDTO> nsfChecks) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		cStyle.setDataFormat((short) 7);
		Row row = sheet.createRow(r);
		Cell cell = row.createCell(0);
		cell.setCellValue("NSF REVERSALS:");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(++r);
		cell = row.createCell(1);
		cell.setCellValue("Payor");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("Case");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("Amount");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("Type");
		cell.setCellStyle(cStyle);
		CellStyle cs = getExcelCurrencyStyle(sheet);
		for(NsfChecksDTO nsf:nsfChecks) {
			totalNsf +=nsf.getTransAmt();
			row = sheet.createRow(++r);
			cell = row.createCell(1);
			cell.setCellValue(nsf.getFirstName() + " " + nsf.getLastName());
			cell = row.createCell(2);
			cell.setCellValue(nsf.getCaseNum());
			cell = row.createCell(3);
			cell.setCellStyle(cs);
			cell.setCellValue(nsf.getTransAmt());
			cell = row.createCell(4);
			cell.setCellValue("N".equals(nsf.getOutType())?"NSF":"Disputed Card");
		}
		
		row = sheet.createRow(++r);
		cell = row.createCell(1);
		cell.setCellValue("TOTAL NSF REVERSALS:");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellStyle(cStyle);
		cell.setCellValue(totalNsf);
		
		return ++r;
	}

	private int generateExcelDeposits(XSSFSheet sheet, int r, RevenueDistributionSummaryDTO rptData) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Row row = sheet.createRow(r);
		Cell cell = row.createCell(0);
		cell.setCellValue("DEPOSIT SUMMARY");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(++r);
		cell = row.createCell(0);
		cell.setCellValue("DEPOSIT:");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(++r);
		cell = row.createCell(1);
		cell.setCellValue("Journal");
		cell.setCellStyle(cStyle);
		cell = row.createCell(2);
		cell.setCellValue("Journal Date");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellValue("Amount");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(++r);
		cell = row.createCell(1);
		cell.setCellValue("TOTAL DEPOSITS:");
		cell.setCellStyle(cStyle);
	
		return ++r;
	}

	private int generateExCelTotals(XSSFSheet sheet, int r, double ybTotal) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		cStyle.setDataFormat((short) 7);
		Row row = sheet.createRow(r);
		Cell cell = row.createCell(0);
		cell.setCellValue("STATE SUBTOTALS:");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(1);
		cell.setCellValue(stateFnedew);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(2);
		cell.setCellValue(ybStateTotal);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue(stateHe);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(4);
		cell.setCellValue(stateIr);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(5);
		cell.setCellValue(0.0);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(6);
		cell.setCellValue(stateTr);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(7);
		cell.setCellValue(stateFnedew + ybStateTotal + stateHe + stateIr + stateTr);
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(++r);
		cell = row.createCell(0);
		cell.setCellValue("GRAND TOTALS:");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(1);
		cell.setCellValue(prosecFnedew + stateFnedew);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(2);
		cell.setCellValue(ybTotal);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue(prosecHe + heleaAccum + stateHe);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(4);
		cell.setCellValue(prosecIr + stateIr);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(5);
		cell.setCellValue(rvLeaAccum);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(6);
		cell.setCellValue(prosecTr + stateTr);
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(7);
		cell.setCellValue(prosecFnedew + prosecHe + prosecIr + prosecTr + stateFnedew + stateHe + stateIr + stateTr +ybTotal + heleaAccum + rvLeaAccum);
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(++r);
		cell = row.createCell(1);
		cell.setCellValue("FN+ED+EW+PN");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("YB");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("HE");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("IR");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(5);
		cell.setCellValue("RV");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(6);
		cell.setCellValue("TR");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(7);
		cell.setCellValue("Total");
		cell.setCellStyle(cStyle);
		
		return ++r;
	}

	private int generateExcelHeLeas(XSSFSheet sheet, int r, List<RevenueHeLeaDTO> revenueHeLeas) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Row row = sheet.createRow(r);
		Cell cell = row.createCell(0);
		cell.setCellValue("HIGHER ED CAMPUS LEA");
		cell.setCellStyle(cStyle);
		CellStyle cs = getExcelCurrencyStyle(sheet);
		for(RevenueHeLeaDTO helea:revenueHeLeas){
			heleaAccum +=helea.getHeLeaAmt();
			rvLeaAccum +=helea.getRevLeaAmt();
			row = sheet.createRow(++r);
			cell = row.createCell(3);
			cell.setCellStyle(cs);
			cell.setCellValue(helea.getLea());
			cell = row.createCell(4);
			cell.setCellValue(helea.getLeaDescr());
			cell = row.createCell(5);
			cell.setCellStyle(cs);
			cell.setCellValue(!"J".equals(rptCriteria.getCourtType())?helea.getHeLeaAmt()/2:helea.getHeLeaAmt());
			cell = row.createCell(6);
			cell.setCellStyle(cs);
			cell.setCellValue(helea.getRevLeaAmt());
			cell = row.createCell(7);
			cell.setCellStyle(cs);
			cell.setCellValue(helea.getHeLeaAmt() + helea.getRevLeaAmt());
		}
		cStyle.setDataFormat((short) 7);
		row = sheet.createRow(++r);
		cell = row.createCell(0);
		cell.setCellValue("HIGHER ED / LEA SUBTOTAL:");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellValue(heleaAccum);
		cell.setCellStyle(cStyle);
		cell = row.createCell(5);
		cell.setCellValue(rvLeaAccum);
		cell.setCellStyle(cStyle);
		cell = row.createCell(7);
		cell.setCellValue(heleaAccum + rvLeaAccum);
		cell.setCellStyle(cStyle);
		
		return ++r;
	}

	private int generateExcelSchoolFines(XSSFSheet sheet, int r, List<TransDistBO> ybDistributions) throws Exception {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Row row = sheet.createRow(r);
		Cell cell = row.createCell(0);
		cell.setCellValue("20% FINE FOR SCHOOL BUS OFFENSES");
		cell.setCellStyle(cStyle);
		CellStyle cs = getExcelCurrencyStyle(sheet);
		for(TransDistBO trans:ybDistributions){
			ybSchlTotal +=trans.getAmtPaid().doubleValue();
			row = sheet.createRow(++r);
			cell = row.createCell(0);
			cell.setCellValue(trans.get(CaseFeatureBO.FEATUREVALUE) + " " + trans.get(SchoolDistrictBO.SCHOOLSHORTNAME));
			cell = row.createCell(3);
			cell.setCellStyle(cs);
			cell.setCellValue((Double)trans.get("amtSum"));
		}
		cStyle.setDataFormat((short) 7);
		row = sheet.createRow(++r);
		cell = row.createCell(0);
		cell.setCellValue("SCHOOL SUBTOTALS:");
		cell.setCellStyle(cStyle);
		cell = row.createCell(2);
		cell.setCellValue(ybSchlTotal);
		cell.setCellStyle(cStyle);
		cell = row.createCell(7);
		cell.setCellValue(ybSchlTotal);
		cell.setCellStyle(cStyle);
		return ++r;
	}

	private int generateExcelRevProsecs(XSSFSheet sheet, int r, List<RevenueProsecDTO> revenueProsecs) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Row row = sheet.createRow(r);
		Cell cell = row.createCell(0);
		cell.setCellValue("DISTRIBUTION SPLITS");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(++r);
		cell = row.createCell(0);
		cell.setCellValue("PROSECUTOR");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("FN+ED+EW+PN");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("YB");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("HE");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("IR");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(5);
		cell.setCellValue("RV");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(6);
		cell.setCellValue("TR");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(7);
		cell.setCellValue("Total");
		cell.setCellStyle(cStyle);
		double fnedewAmt, heAmt, irAmt;
		CellStyle cs = getExcelCurrencyStyle(sheet);
		for(RevenueProsecDTO dto:revenueProsecs){
			fnedewAmt = dto.getFnAmt().doubleValue()/2 + dto.getEdAmt().doubleValue()/2 + dto.getEwAmt().doubleValue()/2 + dto.getPnAmt().doubleValue()/2;
			heAmt = "J".equals(rptCriteria.getCourtType())?dto.getHeAmt().doubleValue()/2:0;
			irAmt = dto.getIrAmt().doubleValue()/2;
			prosecFnedew += fnedewAmt;
			prosecHe +=heAmt;
			prosecIr +=irAmt;
			prosecTr +=dto.getTrAmt().doubleValue();
			stateFnedew += fnedewAmt;
			stateIr += dto.getIrAmt().doubleValue()/2;
			stateTr += dto.getTrAmt().doubleValue()/2;
			
			row = sheet.createRow(++r);
			cell = row.createCell(0);
			cell.setCellValue(dto.getProsecAgency() + " " + dto.getProsecDescr());
			cell = row.createCell(1);
			cell.setCellStyle(cs);
			cell.setCellValue(fnedewAmt);
			cell = row.createCell(2);
			cell.setCellValue("");
			cell = row.createCell(3);
			cell.setCellStyle(cs);
			cell.setCellValue(heAmt);
			cell = row.createCell(4);
			cell.setCellStyle(cs);
			cell.setCellValue(irAmt);
			cell = row.createCell(5);
			cell.setCellStyle(cs);
			cell.setCellValue(0.0);
			cell = row.createCell(6);
			cell.setCellStyle(cs);
			cell.setCellValue(dto.getTrAmt().doubleValue());
			cell = row.createCell(7);
			cell.setCellStyle(cs);
			cell.setCellValue(fnedewAmt + heAmt + irAmt + dto.getTrAmt().doubleValue());

		}
		cStyle.setDataFormat((short) 7);
		row = sheet.createRow(++r);
		cell = row.createCell(0);
		cell.setCellValue("PROSECUTOR SUBTOTALS:");
		cell.setCellStyle(cStyle);
		cell = row.createCell(1);
		cell.setCellValue(prosecFnedew);
		cell.setCellStyle(cStyle);
		cell = row.createCell(2);
		cell.setCellValue("");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellValue(prosecHe);
		cell.setCellStyle(cStyle);
		cell = row.createCell(4);
		cell.setCellValue(prosecIr);
		cell.setCellStyle(cStyle);
		cell = row.createCell(5);
		cell.setCellValue(0.0);
		cell.setCellStyle(cStyle);
		cell = row.createCell(6);
		cell.setCellValue(prosecTr);
		cell.setCellStyle(cStyle);
		cell = row.createCell(7);
		cell.setCellValue(prosecFnedew + prosecHe + prosecIr + prosecTr);
		cell.setCellStyle(cStyle);
		
		return ++r;
	}

	private int generateExcelDistDetails(XSSFSheet sheet, int r, List<RevenueDetailDTO> distrDetails) {
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Row row = sheet.createRow(r);
		Cell cell = row.createCell(0);
		cell.setCellValue("REVENUE APPLIED TO:");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("___________________");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("___________________");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(++r);
		cell = row.createCell(1);
		cell.setCellValue("Org #");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("for week ending");
		cell.setCellStyle(cStyle);
		r++;
		row = sheet.createRow(++r);
		cell = row.createCell(0);
		cell.setCellValue("Account Number");
		cell.setCellStyle(cStyle);
		cell = row.createCell(1);
		cell.setCellValue("Description");
		cell.setCellStyle(cStyle);
		cell = row.createCell(2);
		cell.setCellValue("Revenue Code");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellValue("Amount");
		cell.setCellStyle(cStyle);
		cell = row.createCell(4);
		cell.setCellValue("Quantity");
		cell.setCellStyle(cStyle);
		CellStyle cs = getExcelCurrencyStyle(sheet);
		for(RevenueDetailDTO dto:distrDetails){
			totalRev += dto.getRevenue().doubleValue();
			row = sheet.createRow(++r);
			cell = row.createCell(0);
			cell.setCellValue(dto.getAcctNum());
			cell = row.createCell(1);
			cell.setCellValue(dto.getDescr());
			cell = row.createCell(2);
			cell.setCellValue(dto.getDistrbCode());
			cell = row.createCell(3);
			cell.setCellValue(dto.getRevenue().doubleValue());
			cell.setCellStyle(cs);
			cell = row.createCell(4);
			cell.setCellValue(dto.getCount());
			if("ST".equals(dto.getDistrbCode())) { 
				double stCnty = dto.getRevenue().doubleValue()*0.625;
				double stJuvSec = dto.getRevenue().doubleValue()*0.25;
				row = sheet.createRow(++r);
				cell = row.createCell(0);
				cell.setCellValue("County - 62.5%");
				cell = row.createCell(3);
				cell.setCellValue(stCnty);
				cell.setCellStyle(cs);
				
				row = sheet.createRow(++r);
				cell = row.createCell(0);
				cell.setCellValue("Court Security - 25%");
				cell = row.createCell(3);
				cell.setCellValue(stJuvSec);
				cell.setCellStyle(cs);
				
				row = sheet.createRow(++r);
				cell = row.createCell(0);
				cell.setCellValue("Technology - 12.5%");
				cell = row.createCell(3);
				cell.setCellValue(dto.getRevenue().doubleValue() - stCnty - stJuvSec);	
				cell.setCellStyle(cs);
			}
		}
		
		row = sheet.createRow(++r);
		cell = row.createCell(1);
		cell.setCellValue("TOTAL REVENUE:");
		cell.setCellStyle(cStyle);
		cell = row.createCell(3);
		cell.setCellValue(totalRev);
		cell.setCellStyle(cStyle);
		cell.setCellStyle(cs);
		
		return ++r;
	}

	private int createExcelReportTitle(XSSFSheet sheet) {
		Row row = sheet.createRow(1); //skip the first row as old report does
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		Cell cell = row.createCell(0);
		cell.setCellValue(rptCriteria.getLocnDescr());
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(2); //skip the first row as old report does
		cell = row.createCell(0);
		cell.setCellValue("Document Report");
		cell.setCellStyle(cStyle);
		
		row = sheet.createRow(3); //skip the first row as old report does
		cell = row.createCell(0);
		cell.setCellValue("Journal Range: from " + rptCriteria.getStartJournal() + " to " + rptCriteria.getEndJournal());
		cell.setCellStyle(cStyle);
		
		return 5; //skip one row to start column header
	}

	private byte[] generatePdfReport(RevenueDistributionSummaryDTO rptData) {
		
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
			document.addTitle("Revenue Distribution Summary Report");
			document.setMargins(20f, 22f, 25f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			// report result table
			table = generatePdfResultTable(document, rptData);
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

	private PdfPTable generatePdfResultTable(Document document, RevenueDistributionSummaryDTO rptData) throws Exception {
		PdfPTable table = new PdfPTable(new float[] {20, 22, 12, 10, 10, 8, 8, 10});
		table.setWidthPercentage(100);
		addPdfEmptyLine(table);
		generatePdfDistDetails(table, rptData.getDistrDetails());
		addPdfEmptyLine(table);
		generatePdfRevProsecs(table,rptData.getRevenueProsecs());
		addPdfEmptyLine(table);
		if(rptData.getYbTotal() > 0){
			generatePdfSchoolFines(table,rptData.getYbDistributions());
			addPdfEmptyLine(table);
		}
		generatePdfHeLeas(table,rptData.getRevenueHeLeas());
		addPdfEmptyLine(table);
		generatePdfTotals(document,table, rptData.getYbTotal());
		addPdfEmptyLine(table);
		generatePdfDeposits(table, rptData);
		addPdfEmptyLine(table);
		generatePdfNsfReversals(table, rptData.getNsfChecks());
		addPdfEmptyLine(table);
		generatePdfEnding(table);
		
		return table;
	}

	private void generatePdfEnding(PdfPTable table) {
		PdfPCell c = getPdfContentCell("___________________");
		c.setColspan(2);
		table.addCell(c);
		c.setColspan(6);
		table.addCell(c);
		
		c = getPdfContentCell("Prepared By");
		c.setColspan(2);
		table.addCell(c);
		c = getPdfContentCell("Phone #");
		c.setColspan(6);
		table.addCell(c);
		
	}

	private void generatePdfNsfReversals(PdfPTable table, List<NsfChecksDTO> nsfChks) throws DocumentException {
		PdfPCell cell = getPdfContentCell("NSF REVERSALS:");
		cell.setColspan(8);
		table.addCell(cell);
		
		table.addCell(getPdfHeaderCell(""));
		table.addCell(getPdfHeaderCell("Payor"));
		table.addCell(getPdfHeaderCell("Case"));
		table.addCell(getPdfHeaderCell("Amount"));
		cell = getPdfHeaderCell("Type");
		cell.setColspan(4);
		table.addCell(cell);
		
		for(NsfChecksDTO nsf:nsfChks) {
			totalNsf +=nsf.getTransAmt();
			table.addCell(getPdfContentCell(""));
			table.addCell(getPdfContentCell(nsf.getFirstName() + " " + nsf.getLastName()));
			table.addCell(getPdfContentCell(nsf.getCaseNum()));
			table.addCell(getPdfContentCell(String.format("%.2f",nsf.getTransAmt())));
			cell = getPdfContentCell("N".equals(nsf.getOutType())?"NSF":"Disputed Card");
			cell.setColspan(4);
			table.addCell(cell);
		}
		
		addPdfDottedLine(table);
		
		table.addCell(getPdfContentCell(""));
		table.addCell(getPdfContentCell("TOTAL NSF REVERSALS:"));
		table.addCell(getPdfContentCell(""));
		cell = getPdfContentCell(String.format("%.2f", totalNsf));
		cell.setColspan(5);
		table.addCell(cell);
		
	}

	private void generatePdfDeposits(PdfPTable table, RevenueDistributionSummaryDTO rptData) {
		PdfPCell cell = getPdfContentCell("DEPOSIT SUMMARY");
		cell.setColspan(8);
		table.addCell(cell);
		cell = getPdfContentCell("DEPOSITS:");
		cell.setColspan(8);
		table.addCell(cell);
		
		table.addCell(getPdfHeaderCell(""));
		table.addCell(getPdfHeaderCell("Journal"));
		table.addCell(getPdfHeaderCell("Journal Date"));
		cell = getPdfHeaderCell("Amount");
		cell.setColspan(5);
		table.addCell(cell);

		table.addCell(getPdfContentCell(""));
		cell = getPdfContentCell("TOTAL DEPOSITS:");
		cell = getPdfContentCell("");
		cell.setColspan(6);
		table.addCell(cell);
	}

	private void generatePdfTotals(Document doc, PdfPTable table, double ybTotal) throws DocumentException {
		table.addCell(getPdfHeaderCell("STATE SUBTOTALS:"));
		table.addCell(getPdfHeaderCell(String.format("%.2f",stateFnedew)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",ybStateTotal)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",stateHe)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",stateIr)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",0.0)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",stateTr)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",stateFnedew + ybStateTotal + stateHe + stateIr + stateTr)));
		
		addPdfEmptyLine(table);
		
		table.addCell(getPdfHeaderCell("GRAND TOTALS:"));
		table.addCell(getPdfHeaderCell(String.format("%.2f",prosecFnedew + stateFnedew)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",ybTotal)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",prosecHe + heleaAccum + stateHe)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",prosecIr + stateIr)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",rvLeaAccum)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",prosecTr + stateTr)));
		table.addCell(getPdfHeaderCell(String.format("%.2f",prosecFnedew + prosecHe + prosecIr + prosecTr + stateFnedew + stateHe + stateIr + stateTr +ybTotal + heleaAccum + rvLeaAccum)));
		
		addPdfEmptyLine(table);
		
		table.addCell(getPdfContentCell(""));
		table.addCell(getPdfHeaderCell("FN+ED+EW+PN"));
		table.addCell(getPdfHeaderCell("YB"));
		table.addCell(getPdfHeaderCell("HE"));
		table.addCell(getPdfHeaderCell("IR"));
		table.addCell(getPdfHeaderCell("RV"));
		table.addCell(getPdfHeaderCell("TR"));
		table.addCell(getPdfHeaderCell("Total"));
		
	}

	private void generatePdfSchoolFines(PdfPTable table, List<TransDistBO> ybDistributions) throws Exception {
		PdfPCell cell = getPdfContentCell("20% FINE FOR SCHOOL BUS OFFENSES");
		cell.setColspan(8);
		table.addCell(cell);
		
		for(TransDistBO trans:ybDistributions){
			ybSchlTotal +=trans.getAmtPaid().doubleValue();
			cell = getPdfContentCell(trans.get(CaseFeatureBO.FEATUREVALUE) + " " + trans.get(SchoolDistrictBO.SCHOOLSHORTNAME));
			cell.setColspan(3);
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(cell);
			cell = getPdfContentCell(String.format("%.2f",(Double)trans.get("amtSum")));
			cell.setColspan(5);
			table.addCell(cell);
		}
		addPdfDottedLine(table);
		
		cell = getPdfContentCell("SCHOOL SUBTOTALS:");
		cell.setColspan(2);
		table.addCell(cell);
		cell = getPdfContentCell(String.format("%.2f",ybSchlTotal));
		cell.setColspan(4);
		table.addCell(cell);
		table.addCell(getPdfContentCell(""));
		table.addCell(getPdfContentCell(String.format("%.2f", ybSchlTotal)));
		
	}

	private void generatePdfHeLeas(PdfPTable table, List<RevenueHeLeaDTO> revenueHeLeas) throws DocumentException {
		PdfPCell cell = getPdfContentCell("HIGHER ED CAMPUS LEA");
		cell.setColspan(8);
		table.addCell(cell);
		
		for(RevenueHeLeaDTO helea:revenueHeLeas) {
			heleaAccum +=helea.getHeLeaAmt();
			rvLeaAccum +=helea.getRevLeaAmt();
			cell = getPdfContentCell("");
			cell.setColspan(3);
			table.addCell(cell);
			table.addCell(getPdfContentCell(helea.getLea()));
			table.addCell(getPdfContentCell(helea.getLeaDescr()));
			table.addCell(getPdfContentCell(String.format("%.2f",(!"J".equals(rptCriteria.getCourtType())?helea.getHeLeaAmt()/2:helea.getHeLeaAmt()))));
			table.addCell(getPdfContentCell(String.format("%.2f",helea.getRevLeaAmt())));
			cell = getPdfContentCell(String.format("%.2f", helea.getHeLeaAmt() + helea.getRevLeaAmt()));
			table.addCell(cell);
		}
		
		addPdfDottedLine(table);
		
		table.addCell(getPdfContentCell("HIGHER ED / LEA SUBTOTAL:"));
		cell = getPdfContentCell("");
		cell.setColspan(2);
		table.addCell(cell);
		table.addCell(getPdfContentCell(String.format("%.2f",heleaAccum)));
		table.addCell(getPdfContentCell(""));
		table.addCell(getPdfContentCell(String.format("%.2f",rvLeaAccum)));
		table.addCell(getPdfContentCell(""));
		table.addCell(getPdfContentCell(String.format("%.2f",heleaAccum + rvLeaAccum)));
	}

	private void generatePdfRevProsecs(PdfPTable table, List<RevenueProsecDTO> revProsecs) throws DocumentException {
		PdfPCell cell = getPdfContentCell("DISTRIBUTION SPLITS");
		cell.setColspan(8);
		table.addCell(cell);
		
		table.addCell(getPdfHeaderCell("PROSECUTOR"));
		table.addCell(getPdfHeaderCell("FN+ED+EW+PN"));
		table.addCell(getPdfHeaderCell("YB"));
		table.addCell(getPdfHeaderCell("HE"));
		table.addCell(getPdfHeaderCell("IR"));
		table.addCell(getPdfHeaderCell("RV"));
		table.addCell(getPdfHeaderCell("TR"));
		table.addCell(getPdfHeaderCell("Total"));
		
		double fnedewAmt, heAmt, irAmt;
		for(RevenueProsecDTO dto:revProsecs){ 
			fnedewAmt = dto.getFnAmt().doubleValue()/2 + dto.getEdAmt().doubleValue()/2 + dto.getEwAmt().doubleValue()/2 + dto.getPnAmt().doubleValue()/2;
			heAmt = "J".equals(rptCriteria.getCourtType())?dto.getHeAmt().doubleValue()/2:0;
			irAmt = dto.getIrAmt().doubleValue()/2;
			prosecFnedew += fnedewAmt;
			prosecHe +=heAmt;
			prosecIr +=irAmt;
			prosecTr +=dto.getTrAmt().doubleValue();
			stateFnedew += fnedewAmt;
			stateIr += dto.getIrAmt().doubleValue()/2;
			stateTr += dto.getTrAmt().doubleValue()/2;
			
			table.addCell(getPdfContentCell(dto.getProsecAgency() + " " + dto.getProsecDescr()));
			table.addCell(getPdfContentCell(String.format("%.2f", fnedewAmt)));
			table.addCell(getPdfContentCell(""));
			table.addCell(getPdfContentCell(String.format("%.2f",heAmt)));
			table.addCell(getPdfContentCell(String.format("%.2f",irAmt)));
			table.addCell(getPdfContentCell(String.format("%.2f",0.0)));
			table.addCell(getPdfContentCell(String.format("%.2f",dto.getTrAmt().doubleValue())));
			table.addCell(getPdfContentCell(String.format("%.2f", fnedewAmt + heAmt + irAmt + dto.getTrAmt().doubleValue())));
		}
		
		addPdfDottedLine(table);
		
		table.addCell(getPdfContentCell("PROSECUTOR SUBTOTALS:"));
		table.addCell(getPdfContentCell(String.format("%.2f",prosecFnedew)));
		table.addCell(getPdfContentCell(""));
		table.addCell(getPdfContentCell(String.format("%.2f",prosecHe)));
		table.addCell(getPdfContentCell(String.format("%.2f",prosecIr)));
		table.addCell(getPdfContentCell("0.00"));
		table.addCell(getPdfContentCell(String.format("%.2f",prosecTr)));
		table.addCell(getPdfContentCell(String.format("%.2f",prosecFnedew + prosecHe + prosecIr + prosecTr)));
		
	}

	private void generatePdfDistDetails(PdfPTable table, List<RevenueDetailDTO> distrDetails) throws DocumentException {
		PdfPCell cell = getPdfContentCell("REVENUE APPLIED TO:");
		cell.setColspan(2);
		table.addCell(cell);
		cell = getPdfContentCell("_______________");
		cell.setColspan(2);
		table.addCell(cell);
		table.addCell(cell);
		cell = getPdfContentCell("");
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = getPdfContentCell("");
		cell.setColspan(2);
		table.addCell(cell);
		cell = getPdfContentCell("Org #");
		cell.setColspan(2);
		table.addCell(cell);
		cell = getPdfContentCell("for Week Ending");
		cell.setColspan(2);
		table.addCell(cell);
		cell = getPdfContentCell("");
		cell.setColspan(2);
		table.addCell(cell);

		
		addPdfEmptyLine(table);
		
		table.addCell(getPdfHeaderCell("Account Number"));
		table.addCell(getPdfHeaderCell("Description"));
		table.addCell(getPdfHeaderCell("Revenue Code"));
		table.addCell(getPdfHeaderCell("Amount"));
		cell = getPdfHeaderCell("Quantity");
		cell.setColspan(4);
		table.addCell(cell);
		
		for(RevenueDetailDTO dto:distrDetails){
			totalRev += dto.getRevenue().doubleValue();
			table.addCell(getPdfContentCell(dto.getAcctNum()));
			table.addCell(getPdfContentCell(dto.getDescr()));
			table.addCell(getPdfContentCell(dto.getDistrbCode()));
			table.addCell(getPdfContentCell(String.valueOf(dto.getRevenue())));
			table.addCell(getPdfContentCell(String.valueOf(dto.getCount())));
			cell = getPdfContentCell("");
			cell.setColspan(3);
			table.addCell(cell);
			if("ST".equals(dto.getDistrbCode())) { 
				double stCnty = dto.getRevenue().doubleValue()*0.625;
				double stJuvSec = dto.getRevenue().doubleValue()*0.25;
				
				cell = getPdfContentCell("County - 62.5%");
				cell.setColspan(3);
				table.addCell(cell);
				cell = getPdfContentCell(String.format("%.2f", stCnty));
				cell.setColspan(5);
				table.addCell(cell);
				
				cell = getPdfContentCell("Court Security - 25%");
				cell.setColspan(3);
				table.addCell(cell);
				cell = getPdfContentCell(String.format("%.2f", stJuvSec));
				cell.setColspan(5);
				table.addCell(cell);
				
				cell = getPdfContentCell("Technology - 12.5%");
				cell.setColspan(3);
				table.addCell(cell);
				cell = getPdfContentCell(String.valueOf(dto.getRevenue().doubleValue() - stCnty - stJuvSec));
				cell.setColspan(5);
				table.addCell(cell);
				
			}
		}
		
		addPdfDottedLine(table);
		
		table.addCell(getPdfContentCell(""));
		table.addCell(getPdfContentCell("TOTAL REVENUE:"));
		table.addCell(getPdfContentCell(""));
		cell = getPdfContentCell(String.format("%.2f", totalRev));
		cell.setColspan(5);
		table.addCell(cell);
	}

	@Override
	protected PdfPTable populatePdfReportTitle() throws Exception {
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
			paragraf = new Paragraph(16, "Revenue Distribution Summary Report", headerFont);
			paragraf.setAlignment(Element.ALIGN_CENTER);
			headerCell = new PdfPCell(paragraf);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			paragraf = new Paragraph(16, "FROM " + rptCriteria.getStartJournal() + " to " + rptCriteria.getEndJournal(), headerFont);
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
	protected void populatePdfTableColumnHeaders(PdfPTable table) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void populateExcelColumnHeaders(Row row, XSSFSheet sheet) throws Exception {
		// TODO Auto-generated method stub

	}

	private void addPdfEmptyLine(PdfPTable table){
		PdfPCell cell = getPdfContentCell("\n");
		cell.setColspan(8);
		table.addCell(cell);
	}
	
	private void addPdfDottedLine(PdfPTable table){
		PdfPCell c = getPdfContentCell("");
		c.setColspan(8);
		c.setCellEvent(new RptDottedCell());
		table.addCell(c);
	}
	
	class RptDottedCell implements PdfPCellEvent {
	    public void cellLayout(PdfPCell cell, Rectangle position,
	        PdfContentByte[] canvases) {
	        PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
	        canvas.saveState();
	        canvas.setLineDash(3f, 3f);
	        canvas.moveTo(position.getLeft(), position.getTop());
	        canvas.lineTo(position.getRight(), position.getTop());
	        canvas.stroke();
	        canvas.restoreState();
	    }
	}
}
