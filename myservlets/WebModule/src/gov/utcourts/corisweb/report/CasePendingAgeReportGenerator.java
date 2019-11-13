package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisinterface.util.CorisCaseHistoryCommon;
import gov.utcourts.corisweb.report.CasePendingAgeReportSearchCriteria.JudgeCommInfo;
import gov.utcourts.corisweb.report.CasePendingAgeReportSearchCriteria.ReportCounter;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;

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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class CasePendingAgeReportGenerator extends ReportBaseHandler {

	private static Logger logger = Logger.getLogger(CasePendingAgeReportGenerator.class.getName());
	
	private CasePendingAgeReportSearchCriteria thisCriteria;
	public static final String REPORT_NAME = "Age of Pending Caseload Report";
	public static final int NUMBER_OF_COLUMNS = 9;
	
	private static final StringArrayDescriptor COLUMN_HEADERS = new StringArrayDescriptor("", "0-30", "31-60", "61-90", "91-180", "181-360", "361-720", "Over 720", "Average");
	private static final float[] PDF_COLUMN_WIDTHS = new float[] {20, 10, 10, 10, 10, 10, 10, 10, 10};
		
	public CasePendingAgeReportGenerator(ReportBaseSearchCriteria criteria) {
		super(criteria);
		thisCriteria = (CasePendingAgeReportSearchCriteria) criteria;
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#generateReport(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public byte[] generateReport(List<?> searchResults) throws Exception {
		if ("pdf".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generatePdf((List<ReportCounter>) searchResults);
		} else if ("xlsx".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generateExcel((List<ReportCounter>) searchResults);
		}
		return null;
	}
	
	/**
	 * @param List<ReportCounter>
	 * @return
	 */
	private byte[] generateExcel(List<ReportCounter> searchResults) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			thisCriteria.setSearchResults(searchResults);
			
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(REPORT_NAME);
			int rowNumber = populateExcelReportTitle(sheet);
			populateExcelColumnHeaders(sheet.createRow(++rowNumber), sheet);
			generateExcelRowData(rowNumber, sheet);
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
	
	protected int populateExcelReportTitle(XSSFSheet sheet) {
		// header line
		int rowNumber = 0;
		Row row = sheet.createRow(++rowNumber);
		Cell cell = row.createCell(3);
		cell.setCellValue(thisCriteria.getLocnDescr());

		row = sheet.createRow(++rowNumber);
		cell = row.createCell(3);
		cell.setCellValue(REPORT_NAME);

		// blank line
		++rowNumber;  

		// judge
		row = sheet.createRow(++rowNumber);
		cell = row.createCell(0);
		cell.setCellValue(getJudgeText(0));
		
		// pending date
		row = sheet.createRow(++rowNumber);
		cell = row.createCell(0);
		cell.setCellValue("Report on Cases Pending on " + Constants.dateFormatCoris.format(thisCriteria.getPendingDate()));
		
		// all / active / inactive
		row = sheet.createRow(++rowNumber);
		cell = row.createCell(0);
		cell.setCellValue(getCaseTypesDescription());
		
		return ++rowNumber;
	}
	
	private String getCaseTypesDescription() {
		String typeOfCase = "Active and Inactive Cases";
		if ("I".equalsIgnoreCase(thisCriteria.getActive()))
			typeOfCase = "Inactive Cases Only";
		else if ("V".equalsIgnoreCase(thisCriteria.getActive()))
			typeOfCase = "Active Cases Only";
		return typeOfCase;
	}
	
	private String getJudgeText(int position) {
		String judgeText = "ALL Judges/Commissioners";
		if (thisCriteria.getSelectedJudgeCount() > 0) 
			judgeText = thisCriteria.getSearchResults().get(position).judge.m_judge_name;
		
		return judgeText;
	}
	
	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populateExcelColumnHeaders(org.apache.poi.ss.usermodel.Row, org.apache.poi.xssf.usermodel.XSSFSheet)
	 */
	@Override
	protected void populateExcelColumnHeaders(Row row, XSSFSheet sheet) {
		
		// column headers
		int i = 0;
		CellStyle cStyle = getExcelColumnHeaderCellStyle(sheet);
		for (String header : COLUMN_HEADERS.getValues()) {
			Cell cell = row.createCell(i);
			cell.setCellValue(header);
			cell.setCellStyle(cStyle);
			i++;
		}
	}

	/**
	 * @param row
	 * @param ReportCounter result
	 * @throws Exception
	 */
	protected void generateExcelRowData(int rowNumber, XSSFSheet sheet) throws Exception {
		int columnNumber = 0;
		Row row = null; 
		List<ReportCounter> searchResults = thisCriteria.getSearchResults();
		
		if (searchResults != null && searchResults.size() > 0) {
			
			// total line
			addTotalLine(++rowNumber, sheet);
				
			for (ReportCounter reportCounter : searchResults) {
				
				try {
					JudgeCommInfo judge = reportCounter.judge;
					
					int l_avg_days = 0;
					int l_g_avg_days = 0;
					int l_gt_avg_days = 0;
					String lrpt_category = "";
					String lrpt_cat_name = "";
					int m_noreport = 1;
					
				 	if (reportCounter.m_listcase == 1) {
		
				 		// do nothing
				 		
				 	} else {
				 		
				 		if (m_noreport > 0) {
				 			
				 			lrpt_category = null;
				 			
				 			for (int arr=0; arr < reportCounter.mcasecount_t; arr++) {
				 				if (TextUtil.isEmpty(lrpt_category) || !lrpt_category.equalsIgnoreCase(reportCounter.ma_casecount.get(arr).rpt_category)) {
				 					if (!TextUtil.isEmpty(lrpt_category)) {
				 						
				 						if (reportCounter.g_case_days > 0 && reportCounter.g_pend_cases > 0) {
				 		                    l_g_avg_days = reportCounter.g_case_days / reportCounter.g_pend_cases;
				 						} else {
				 		                    l_g_avg_days = 0;
				 						}
				 						
				 						row = sheet.createRow(++rowNumber);
				 						columnNumber = 0;
				 						
				 						Cell cell = row.createCell(columnNumber);
				 						cell.setCellValue(TextUtil.print(lrpt_cat_name));

				 						cell = row.createCell(++columnNumber);
				 						cell.setCellValue(reportCounter.gcount30);
				 						
				 						cell = row.createCell(++columnNumber);
				 						cell.setCellValue(reportCounter.gcount60);
				 						
				 						cell = row.createCell(++columnNumber);
				 						cell.setCellValue(reportCounter.gcount90);
				 						
				 						cell = row.createCell(++columnNumber);
				 						cell.setCellValue(reportCounter.gcount180);

				 						cell = row.createCell(++columnNumber);
				 						cell.setCellValue(reportCounter.gcount360);
		
				 						cell = row.createCell(++columnNumber);
				 						cell.setCellValue(reportCounter.gcount720);
				 						
				 						cell = row.createCell(++columnNumber);
				 						cell.setCellValue(reportCounter.gcountover);
				 						
				 						cell = row.createCell(++columnNumber);
				 						cell.setCellValue(l_g_avg_days);
				 						
				 						// total line
				 						addTotalLine(++rowNumber, sheet);
		
										reportCounter.g_case_days = 0;
			 							reportCounter.g_pend_cases = 0;
			 							l_g_avg_days = 0;
			 							reportCounter.gcount30 = 0;
			 							reportCounter.gcount60 = 0;
			 							reportCounter.gcount90 = 0;
			 							reportCounter.gcount180 = 0;
			 							reportCounter.gcount360 = 0;
			 							reportCounter.gcount720 = 0;
			 							reportCounter.gcountover = 0;
				 					}
		
				 					lrpt_category = reportCounter.ma_casecount.get(arr).rpt_category;
				 		            lrpt_cat_name = reportCounter.ma_casecount.get(arr).rpt_cat_name;
				 					
				 		            row = sheet.createRow(++rowNumber);
				 		            
			 						Cell cell = row.createCell(0);
			 						cell.setCellValue(TextUtil.print(lrpt_cat_name));

			 						cell = row.createCell(6);
			 						cell.setCellValue(TextUtil.print(judge.m_judge_name));
				 				}
				 				
				 				reportCounter.m_case_days = reportCounter.ma_casecount.get(arr).casedays;
				 				reportCounter.m_pend_cases = reportCounter.ma_casecount.get(arr).pendcases;
				 				reportCounter.g_pend_cases = reportCounter.g_pend_cases + reportCounter.m_pend_cases;
				 				reportCounter.g_case_days = reportCounter.g_case_days + reportCounter.m_case_days;
				 				reportCounter.gt_pend_cases = reportCounter.gt_pend_cases + reportCounter.m_pend_cases;
				 				reportCounter.gt_case_days = reportCounter.gt_case_days + reportCounter.m_case_days;
				 				
				 		        if (reportCounter.m_case_days > 0 && reportCounter.m_pend_cases > 0) {
				 		        	l_avg_days = reportCounter.m_case_days / reportCounter.m_pend_cases;
				 		        } else {
				 		        	l_avg_days = 0;
				 		        }
				 		        
				 		        row = sheet.createRow(++rowNumber);
		 						columnNumber = 0;
		 						
		 						Cell cell = row.createCell(columnNumber);
		 						cell.setCellValue(TextUtil.print(reportCounter.ma_casecount.get(arr).descr));

		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.ma_casecount.get(arr).count30);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.ma_casecount.get(arr).count60);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.ma_casecount.get(arr).count90);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.ma_casecount.get(arr).count180);

		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.ma_casecount.get(arr).count360);

		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.ma_casecount.get(arr).count720);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.ma_casecount.get(arr).countover);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(l_avg_days);
		 						
				 		        reportCounter.gcount30 = reportCounter.gcount30 + reportCounter.ma_casecount.get(arr).count30;
				 		        reportCounter.gcount60 = reportCounter.gcount60 + reportCounter.ma_casecount.get(arr).count60;
				 		        reportCounter.gcount90 = reportCounter.gcount90 + reportCounter.ma_casecount.get(arr).count90;
				 		        reportCounter.gcount180 = reportCounter.gcount180 + reportCounter.ma_casecount.get(arr).count180;
				 		        reportCounter.gcount360 = reportCounter.gcount360 + reportCounter.ma_casecount.get(arr).count360;
				 		        reportCounter.gcount720 = reportCounter.gcount720 + reportCounter.ma_casecount.get(arr).count720;
				 		        reportCounter.gcountover = reportCounter.gcountover + reportCounter.ma_casecount.get(arr).countover;
				 		        
				 		        reportCounter.gtcount30 = reportCounter.gtcount30 + reportCounter.ma_casecount.get(arr).count30;
				 		        reportCounter.gtcount60 = reportCounter.gtcount60 + reportCounter.ma_casecount.get(arr).count60;
				 		        reportCounter.gtcount90 = reportCounter.gtcount90 + reportCounter.ma_casecount.get(arr).count90;
				 		        reportCounter.gtcount180 = reportCounter.gtcount180 + reportCounter.ma_casecount.get(arr).count180;
				 		        reportCounter.gtcount360 = reportCounter.gtcount360 + reportCounter.ma_casecount.get(arr).count360;
				 		        reportCounter.gtcount720 = reportCounter.gtcount720 + reportCounter.ma_casecount.get(arr).count720;
				 		        reportCounter.gtcountover = reportCounter.gtcountover + reportCounter.ma_casecount.get(arr).countover;
				 		        
				 		        reportCounter.m_case_days = 0;
				 		        reportCounter.m_pend_cases = 0;
				 				
				 			} // for	
		
				 			if (reportCounter.g_pend_cases > 0)
				 				l_g_avg_days = reportCounter.g_case_days / reportCounter.g_pend_cases;

				 				// total line
	 							addTotalLine(++rowNumber, sheet);

				 		        row = sheet.createRow(++rowNumber);
		 						columnNumber = 0;
		 						
		 						Cell cell = row.createCell(columnNumber);
		 						cell.setCellValue(TextUtil.print("TOTAL " + lrpt_cat_name));
	
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gcount30);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gcount60);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gcount90);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gcount180);
	
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gcount360);
	
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gcount720);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gcountover);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(l_g_avg_days);
	 						
		 						// total line
		 						addTotalLine(++rowNumber, sheet);
				 				
				 				reportCounter.g_case_days = 0;
				 				reportCounter.g_pend_cases = 0;
				 				l_g_avg_days = 0;
				 				
				 				if (reportCounter.gt_case_days > 0 && reportCounter.gt_pend_cases > 0) {
				 					l_gt_avg_days = reportCounter.gt_case_days / reportCounter.gt_pend_cases;
				 				} else {
				 					l_gt_avg_days = 0;
				 				}
				 				
				 				row = sheet.createRow(++rowNumber);
		 						columnNumber = 0;
		 						
		 						cell = row.createCell(columnNumber);
		 						cell.setCellValue(TextUtil.print("GRAND TOTAL"));
	
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gtcount30);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gtcount60);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gtcount90);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gtcount180);
	
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gtcount360);
	
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gtcount720);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(reportCounter.gtcountover);
		 						
		 						cell = row.createCell(++columnNumber);
		 						cell.setCellValue(l_gt_avg_days);
		 						
				 				reportCounter.gt_case_days = 0;
				 				reportCounter.gt_pend_cases = 0;
				 				l_g_avg_days = 0;
				 				reportCounter.gtcount30 = 0;
				 				reportCounter.gtcount60 = 0;
				 				reportCounter.gtcount90 = 0;
				 				reportCounter.gtcount180 = 0;
				 				reportCounter.gtcount360 = 0;
				 				reportCounter.gtcount720 = 0;
				 				reportCounter.gtcountover = 0;
				 				
				 				// total line
		 						addGrandTotalLine(++rowNumber, sheet);
		 						
		 						// blank line
		 						++rowNumber;
				 		}
				 		
				 	}  // m_listcase = 1 
					
				} catch (Exception e) {
					logger.error("Failed to generate excel report content.", e);
				}
				
			}	// for 
		} // if
	}
	
	/**
	 * @param List<ReportCounter> searchResults
	 * @return byte[]
	 */
	private byte[] generatePdf(List<ReportCounter> searchResults) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfPTable table = null;
		
		try {
			thisCriteria.setSearchResults(searchResults);
			
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
			document.addTitle(REPORT_NAME);
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(searchResults);
			document.add(table);
			document.close();
			
		} catch (Exception e) {
			logger.error("Failed to generate PDF report. ", e);
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
	
	/**
	 * @param List<ReportCounter> searchResults
	 * @return PdfPTable
	 */
	private PdfPTable generatePdfResultTable(List<ReportCounter> searchResults) {
		PdfPTable table = new PdfPTable(PDF_COLUMN_WIDTHS);
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		generatePdfReportContents(table, searchResults);
		return table;
	}
	
	/**
	 * @param PdfPTable table
	 * @param List<ReportCounter> searchResults
	 */
	private void generatePdfReportContents(PdfPTable table, List<ReportCounter> searchResults) {
		
		// blank line
		PdfPCell blankLine = new PdfPCell(new Phrase("\n"));
		blankLine.setBorder(PdfPCell.NO_BORDER);
		blankLine.setColspan(NUMBER_OF_COLUMNS);
		
		// dotted line
		Paragraph paragraph = new Paragraph();
		DottedLineSeparator dottedlineSeparator = new DottedLineSeparator();
		dottedlineSeparator.setOffset(-2);
		dottedlineSeparator.setGap(2f);
		dottedlineSeparator.setAlignment(DottedLineSeparator.ALIGN_TOP);
		paragraph.add(dottedlineSeparator);
		PdfPCell dottedLine = new PdfPCell(paragraph);
		dottedLine.setColspan(NUMBER_OF_COLUMNS);
		dottedLine.setBorder(PdfPCell.NO_BORDER);
		dottedLine.setVerticalAlignment(PdfPCell.ALIGN_TOP);
			
		if (searchResults != null && searchResults.size() > 0) {
			for (ReportCounter reportCounter : searchResults) {
				try {
					JudgeCommInfo judge = reportCounter.judge;
					
					int l_avg_days = 0;
					int l_g_avg_days = 0;
					int l_gt_avg_days = 0;
					String lrpt_category = "";
					String lrpt_cat_name = "";
					int m_noreport = 1;
					
				 	if (reportCounter.m_listcase == 1) {

				 		// do nothing
				 		
				 	} else {
				 		
				 		if (m_noreport > 0) {
				 			
				 			lrpt_category = null;
				 			
				 			for (int arr=0; arr < reportCounter.mcasecount_t; arr++) {
				 				if (TextUtil.isEmpty(lrpt_category) || !lrpt_category.equalsIgnoreCase(reportCounter.ma_casecount.get(arr).rpt_category)) {
				 					if (!TextUtil.isEmpty(lrpt_category)) {
				 						
				 						if (reportCounter.g_case_days > 0 && reportCounter.g_pend_cases > 0) {
				 		                    l_g_avg_days = reportCounter.g_case_days / reportCounter.g_pend_cases;
				 						} else {
				 		                    l_g_avg_days = 0;
				 						}

				 						table.addCell(getPdfContentCell(TextUtil.print(lrpt_cat_name)));
										table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount30), Element.ALIGN_RIGHT));
										table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount60), Element.ALIGN_RIGHT));
										table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount90), Element.ALIGN_RIGHT));
										table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount180), Element.ALIGN_RIGHT));
										table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount360), Element.ALIGN_RIGHT));
										table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount720), Element.ALIGN_RIGHT));
										table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcountover), Element.ALIGN_RIGHT));
										table.addCell(getPdfContentCell(Integer.toString(l_g_avg_days), Element.ALIGN_RIGHT));
										

										// dotted line
				 						table.addCell(dottedLine);
				 						
										// blank line
										table.addCell(blankLine);

										reportCounter.g_case_days = 0;
			 							reportCounter.g_pend_cases = 0;
			 							l_g_avg_days = 0;
			 							reportCounter.gcount30 = 0;
			 							reportCounter.gcount60 = 0;
			 							reportCounter.gcount90 = 0;
			 							reportCounter.gcount180 = 0;
			 							reportCounter.gcount360 = 0;
			 							reportCounter.gcount720 = 0;
			 							reportCounter.gcountover = 0;
				 					}

				 					lrpt_category = reportCounter.ma_casecount.get(arr).rpt_category;
				 		            lrpt_cat_name = reportCounter.ma_casecount.get(arr).rpt_cat_name;
				 					
									table.addCell(getPdfContentCell(TextUtil.print(lrpt_cat_name)));
									table.addCell(getPdfContentCell(""));
									table.addCell(getPdfContentCell(""));
									table.addCell(getPdfContentCell(""));
									table.addCell(getPdfContentCell(""));
									table.addCell(getPdfContentCell(""));
									PdfPCell cell = getPdfContentCell(TextUtil.print(judge.m_judge_name), Element.ALIGN_RIGHT);
									cell.setColspan(3);
									table.addCell(cell);
				 				}
				 				
				 				reportCounter.m_case_days = reportCounter.ma_casecount.get(arr).casedays;
				 				reportCounter.m_pend_cases = reportCounter.ma_casecount.get(arr).pendcases;
				 				reportCounter.g_pend_cases = reportCounter.g_pend_cases + reportCounter.m_pend_cases;
				 				reportCounter.g_case_days = reportCounter.g_case_days + reportCounter.m_case_days;
				 				reportCounter.gt_pend_cases = reportCounter.gt_pend_cases + reportCounter.m_pend_cases;
				 				reportCounter.gt_case_days = reportCounter.gt_case_days + reportCounter.m_case_days;
				 				
				 		        if (reportCounter.m_case_days > 0 && reportCounter.m_pend_cases > 0) {
				 		        	l_avg_days = reportCounter.m_case_days / reportCounter.m_pend_cases;
				 		        } else {
				 		        	l_avg_days = 0;
				 		        }
				 		        
				 		        table.addCell(getPdfContentCell(TextUtil.print(reportCounter.ma_casecount.get(arr).descr), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.ma_casecount.get(arr).count30), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.ma_casecount.get(arr).count60), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.ma_casecount.get(arr).count90), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.ma_casecount.get(arr).count180), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.ma_casecount.get(arr).count360), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.ma_casecount.get(arr).count720), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.ma_casecount.get(arr).countover), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(l_avg_days), Element.ALIGN_RIGHT));
								
				 		        reportCounter.gcount30 = reportCounter.gcount30 + reportCounter.ma_casecount.get(arr).count30;
				 		        reportCounter.gcount60 = reportCounter.gcount60 + reportCounter.ma_casecount.get(arr).count60;
				 		        reportCounter.gcount90 = reportCounter.gcount90 + reportCounter.ma_casecount.get(arr).count90;
				 		        reportCounter.gcount180 = reportCounter.gcount180 + reportCounter.ma_casecount.get(arr).count180;
				 		        reportCounter.gcount360 = reportCounter.gcount360 + reportCounter.ma_casecount.get(arr).count360;
				 		        reportCounter.gcount720 = reportCounter.gcount720 + reportCounter.ma_casecount.get(arr).count720;
				 		        reportCounter.gcountover = reportCounter.gcountover + reportCounter.ma_casecount.get(arr).countover;
				 		        
				 		        reportCounter.gtcount30 = reportCounter.gtcount30 + reportCounter.ma_casecount.get(arr).count30;
				 		        reportCounter.gtcount60 = reportCounter.gtcount60 + reportCounter.ma_casecount.get(arr).count60;
				 		        reportCounter.gtcount90 = reportCounter.gtcount90 + reportCounter.ma_casecount.get(arr).count90;
				 		        reportCounter.gtcount180 = reportCounter.gtcount180 + reportCounter.ma_casecount.get(arr).count180;
				 		        reportCounter.gtcount360 = reportCounter.gtcount360 + reportCounter.ma_casecount.get(arr).count360;
				 		        reportCounter.gtcount720 = reportCounter.gtcount720 + reportCounter.ma_casecount.get(arr).count720;
				 		        reportCounter.gtcountover = reportCounter.gtcountover + reportCounter.ma_casecount.get(arr).countover;
				 		        
				 		        reportCounter.m_case_days = 0;
				 		        reportCounter.m_pend_cases = 0;
				 				
				 			} // for	

				 			if (reportCounter.g_pend_cases > 0)
				 				l_g_avg_days = reportCounter.g_case_days / reportCounter.g_pend_cases;
				 			
					 			table.addCell(getPdfContentCell(TextUtil.print("TOTAL " + lrpt_cat_name)));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount30), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount60), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount90), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount180), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount360), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcount720), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gcountover), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(l_g_avg_days), Element.ALIGN_RIGHT));
				 				
								// dotted line
		 						table.addCell(dottedLine);
		 						
								// blank line
								table.addCell(blankLine);
				 				
				 				reportCounter.g_case_days = 0;
				 				reportCounter.g_pend_cases = 0;
				 				l_g_avg_days = 0;
				 				
				 				if (reportCounter.gt_case_days > 0 && reportCounter.gt_pend_cases > 0) {
				 					l_gt_avg_days = reportCounter.gt_case_days / reportCounter.gt_pend_cases;
				 				} else {
				 					l_gt_avg_days = 0;
				 				}
				 				
				 				table.addCell(getPdfContentCell(TextUtil.print("GRAND TOTAL ")));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gtcount30), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gtcount60), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gtcount90), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gtcount180), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gtcount360), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gtcount720), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(reportCounter.gtcountover), Element.ALIGN_RIGHT));
								table.addCell(getPdfContentCell(Integer.toString(l_gt_avg_days), Element.ALIGN_RIGHT));
								
								// dotted line
		 						table.addCell(dottedLine);
		 						
								// blank line
								table.addCell(blankLine);
				 				
				 				reportCounter.gt_case_days = 0;
				 				reportCounter.gt_pend_cases = 0;
				 				l_g_avg_days = 0;
				 				reportCounter.gtcount30 = 0;
				 				reportCounter.gtcount60 = 0;
				 				reportCounter.gtcount90 = 0;
				 				reportCounter.gtcount180 = 0;
				 				reportCounter.gtcount360 = 0;
				 				reportCounter.gtcount720 = 0;
				 				reportCounter.gtcountover = 0;
				 		}
				 		
				 	}  // m_listcase = 1 
					
				} catch (Exception e) {
					logger.error("Failed to generate Pdf report content.", e);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfTableColumnHeaders(com.itextpdf.text.pdf.PdfPTable)
	 */
	@Override
	protected void populatePdfTableColumnHeaders(PdfPTable table) {
		// column headers
		for (String header : COLUMN_HEADERS.getValues()) 
			table.addCell(getPdfHeaderCell(header, Element.ALIGN_RIGHT));
		
		PdfPCell c = new PdfPCell(new Phrase("\n"));
		c.setColspan(NUMBER_OF_COLUMNS);
		c.setBorder(PdfPCell.NO_BORDER);
		c.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		table.addCell(c);
		table.setHeaderRows(2);
	}
	
	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfReportTitle()
	 */
	@Override
	protected PdfPTable populatePdfReportTitle() throws Exception {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		try {
			table.setWidths(new float[] {1.0f});
			
			// spacer rows
			for (int i = 0; i < 2; i++)
				CorisCaseHistoryCommon.addCell(table, " ", NORMALFONT, Element.ALIGN_LEFT, PdfPCell.NO_BORDER, 1);
			
			// header line
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 14f, Font.NORMAL);
			Paragraph paragraph = new Paragraph(16, thisCriteria.getLocnDescr(), headerFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			PdfPCell headerCell = new PdfPCell(paragraph);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			paragraph = new Paragraph(16, REPORT_NAME, headerFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			headerCell = new PdfPCell(paragraph);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			table.addCell(headerCell);
			
			// blank line
			headerCell = new PdfPCell(new Phrase("\n"));
			headerCell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(headerCell);
			
			// judge
			Paragraph judge = new Paragraph(16, getJudgeText(0), headerFont);
			headerCell = new PdfPCell(judge);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			table.addCell(headerCell);
			
			// pending date
			Paragraph pendingDate = new Paragraph(16, "Report on Cases Pending on " + Constants.dateFormatCoris.format(thisCriteria.getPendingDate()), headerFont);
			headerCell = new PdfPCell(pendingDate);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			table.addCell(headerCell);
			
			// all / active / inactive
			Paragraph typeOfCases = new Paragraph(16, getCaseTypesDescription(), headerFont);
			headerCell = new PdfPCell(typeOfCases);
			headerCell.setBorder(PdfPCell.NO_BORDER);
			headerCell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			table.addCell(headerCell);
			
			// blank line
			headerCell = new PdfPCell(new Phrase("\n"));
			headerCell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(headerCell);
			
		} catch (DocumentException e) {
			throw new Exception("Failed to populate pdf report title. " + e.getMessage());
		}
		
		return table;
	}
	
	private void addTotalLine(int rowNumber, XSSFSheet sheet) {
		Row row = sheet.createRow(rowNumber);
		for (int col=0; col < NUMBER_OF_COLUMNS; col++) {
			Cell cell = row.createCell(col);
			cell.setCellValue("-----------");
		}
	}
	
	private void addGrandTotalLine(int rowNumber, XSSFSheet sheet) {
		Row row = sheet.createRow(rowNumber);
		for (int col=0; col < NUMBER_OF_COLUMNS; col++) {
			Cell cell = row.createCell(col);
			cell.setCellValue("========");
		}
	}
}
