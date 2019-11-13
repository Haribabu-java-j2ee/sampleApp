package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dto.CorisWarrantWithPaymentsMadeDTO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;

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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CorisReportWarrantWithPaymentsMadeReport extends ReportBaseHandler {

	private static Logger logger = Logger.getLogger(CorisWarrantReport.class.getName());
	
	private CorisReportWarrantWithPaymentsMadeSearchCriteria thisCriteria;
	
	public static final LineDash solid = new SolidLine();
	public static final LineDash dotted = new DottedLine();
	public static final LineDash dashed = new DashedLine();
	
	public CorisReportWarrantWithPaymentsMadeReport(ReportBaseSearchCriteria criteria) {
		super(criteria);
		thisCriteria = (CorisReportWarrantWithPaymentsMadeSearchCriteria) criteria;
	}

	public static class CustomBorder implements PdfPCellEvent {
	    protected LineDash left;
	    protected LineDash right;
	    protected LineDash top;
	    protected LineDash bottom;
	    public CustomBorder(LineDash left, LineDash right,
	            LineDash top, LineDash bottom) {
	        this.left = left;
	        this.right = right;
	        this.top = top;
	        this.bottom = bottom;
	    }
	    public void cellLayout(PdfPCell cell, Rectangle position,
	        PdfContentByte[] canvases) {
	        PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
	        if (top != null) {
	            canvas.saveState();
	            top.applyLineDash(canvas);
	            canvas.moveTo(position.getRight(), position.getTop());
	            canvas.lineTo(position.getLeft(), position.getTop());
	            canvas.stroke();
	            canvas.restoreState();
	        }
	        if (bottom != null) {
	            canvas.saveState();
	            bottom.applyLineDash(canvas);
	            canvas.moveTo(position.getRight(), position.getBottom());
	            canvas.lineTo(position.getLeft(), position.getBottom());
	            canvas.stroke();
	            canvas.restoreState();
	        }
	        if (right != null) {
	            canvas.saveState();
	            right.applyLineDash(canvas);
	            canvas.moveTo(position.getRight(), position.getTop());
	            canvas.lineTo(position.getRight(), position.getBottom());
	            canvas.stroke();
	            canvas.restoreState();
	        }
	        if (left != null) {
	            canvas.saveState();
	            left.applyLineDash(canvas);
	            canvas.moveTo(position.getLeft(), position.getTop());
	            canvas.lineTo(position.getLeft(), position.getBottom());
	            canvas.stroke();
	            canvas.restoreState();
	        }
	    }
	}
	
	
	public static interface LineDash {
	    public void applyLineDash(PdfContentByte canvas);
	}
	public static class SolidLine implements LineDash {
	    public void applyLineDash(PdfContentByte canvas) { }
	}

	public static class DottedLine implements LineDash {
	    public void applyLineDash(PdfContentByte canvas) {
	        canvas.setLineCap(PdfContentByte.LINE_CAP_ROUND);
	        canvas.setLineDash(0, 4, 2);
	    }
	}

	public static class DashedLine implements LineDash {
	    public void applyLineDash(PdfContentByte canvas) {
	        canvas.setLineDash(3, 3);
	    }
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#generateReport(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public byte[] generateReport(List<?> corisWarrantWithPaymentsMadeListDTO) {
		if ("pdf".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generatePdfReport((List<CorisWarrantWithPaymentsMadeDTO>) corisWarrantWithPaymentsMadeListDTO);
		} else if ("xlsx".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generateExcelReport((List<CorisWarrantWithPaymentsMadeDTO>) corisWarrantWithPaymentsMadeListDTO);
		}
		return null;
	}
	
	/**
	 * @param docList
	 * @return
	 */
	private byte[] generateExcelReport(List<CorisWarrantWithPaymentsMadeDTO> corisWarrantWithPaymentsMadeListDTO) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Warrant " + thisCriteria.getWarrantType());
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (CorisWarrantWithPaymentsMadeDTO corisWarrantWithPaymentsMadeDTO: corisWarrantWithPaymentsMadeListDTO) {
				row = sheet.createRow(++rowCount);
				generateExcelRowData(row, corisWarrantWithPaymentsMadeDTO);
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			
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
		
		/////////////////////////////////////////////////////////////////////
		//CleanUp
		/////////////////////////////////////////////////////////////////////
		workbook = null;
		sheet = null;
		rowCount = 0;
		row = null;
		
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
		cell.setCellValue("Defendant");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("Amount");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("Judge Name");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("Status");
		cell.setCellStyle(cStyle);
		
		
		/////////////////////////////////////////////////////////////////////
		//CleanUp
		/////////////////////////////////////////////////////////////////////
		cStyle = null;
		
	}

	/**
	 * @param row
	 * @param doc
	 * @throws Exception
	 */
	protected void generateExcelRowData(Row row, CorisWarrantWithPaymentsMadeDTO corisWarrantWithPaymentsMadeDTO) throws Exception {
		Cell cell = row.createCell(0);
		
		cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(TextUtil.print(corisWarrantWithPaymentsMadeDTO.getCaseNum())));
		
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(TextUtil.print(corisWarrantWithPaymentsMadeDTO.getDefLastName() + ", " + corisWarrantWithPaymentsMadeDTO.getDefFirstName())));

		cell = row.createCell(2);
		cell.setCellValue(Constants.receiptDecimalFormatter.format(corisWarrantWithPaymentsMadeDTO.getAmtPaid()));
		
		cell = row.createCell(3);
		cell.setCellValue(corisWarrantWithPaymentsMadeDTO.getJudgeLastName() +"' " + corisWarrantWithPaymentsMadeDTO.getJudgeFirstName());
		
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.print(corisWarrantWithPaymentsMadeDTO.getStatus()));
		
		/////////////////////////////////////////////////////////////////////
		//CleanUp
		/////////////////////////////////////////////////////////////////////
		cell = null;

	}
	
	/**
	 * @param corisWarrantListDTO
	 * @return
	 */
	private byte[] generatePdfReport(List<CorisWarrantWithPaymentsMadeDTO> corisWarrantWithPaymentsMadeListDTO) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			// ///////////////////////////////////////////////////////////////
			// Create new table
			// ///////////////////////////////////////////////////////////////
			PdfPTable table = null;
			
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
			document.addCreator("Utah State Court");
			document.addTitle("CORIS Warrant With Payments Made Report");
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(corisWarrantWithPaymentsMadeListDTO, thisCriteria.getCourtType());
			document.add(table);
			document.close();
			
			/////////////////////////////////////////////////////////////////////
			//CleanUp
			/////////////////////////////////////////////////////////////////////
			table = null;
			document = null;
			writer = null;
			
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
	private PdfPTable generatePdfResultTable(List<CorisWarrantWithPaymentsMadeDTO> corisWarrantWithPaymentsMadeListDTO, String courtType) {
		PdfPTable table = new PdfPTable(new float[] {15, 20, 20, 20, 25});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		// start result rows
		generatePdfReportContents(table, corisWarrantWithPaymentsMadeListDTO, courtType);
		return table;
	}
	
	/**
	 * @param table
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 */
	private void generatePdfReportContents(PdfPTable table, List<CorisWarrantWithPaymentsMadeDTO> corisWarrantWithPaymentsMadeListDTO, String courtType) {
		LineDash left = null;
		LineDash right = null;
		LineDash top = null;
		LineDash bottom = null;

		
		if (corisWarrantWithPaymentsMadeListDTO == null){
			addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 9);
			addCellCustomBorder(table, "Nothing to report", BOLDFONT, Element.ALIGN_CENTER, left, right, top, bottom, 9);
		} else 	{	
			for(CorisWarrantWithPaymentsMadeDTO corisWarrantWithPaymentsMadeDTO : corisWarrantWithPaymentsMadeListDTO){
				bottom = solid;
				
				addCellCustomBorder(table, TextUtil.print(corisWarrantWithPaymentsMadeDTO.getCaseNum()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.print(corisWarrantWithPaymentsMadeDTO.getDefLastName() + ", " + corisWarrantWithPaymentsMadeDTO.getDefFirstName()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.print(Constants.receiptDecimalFormatter.format(corisWarrantWithPaymentsMadeDTO.getAmtPaid())), NORMALFONT, Element.ALIGN_RIGHT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.print(corisWarrantWithPaymentsMadeDTO.getJudgeLastName() +", " + corisWarrantWithPaymentsMadeDTO.getJudgeFirstName()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.print(corisWarrantWithPaymentsMadeDTO.getStatus()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				
				corisWarrantWithPaymentsMadeDTO = null;
			}
		}
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfTableColumnHeaders(com.itextpdf.text.pdf.PdfPTable)
	 */
	@Override
	protected void populatePdfTableColumnHeaders(PdfPTable table) {
		
		addCellCustomBorder(table, "CASE #", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "NAME", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "PAID AMOUNT", BOLDFONT, Element.ALIGN_RIGHT, null, null, null, solid, 1);
		addCellCustomBorder(table, "JUDGE", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "STATUS", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
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
			
			addCellCustomBorder(table, TextUtil.print(thisCriteria.getLocnDescr()), BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
			
			addCellCustomBorder(table, thisCriteria.getWarrantType().toUpperCase() + " WARRANT WITH PAYMENTS MADE " + thisCriteria.getWarrantType().toUpperCase() + " REPORT", BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
			
			addCellCustomBorder(table, "FROM " + TextUtil.printDate(thisCriteria.getStartDate()) + " TO " + TextUtil.printDate(thisCriteria.getEndDate()), BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
			addCellCustomBorder(table, "", BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
			
		} catch (DocumentException e) {
			logger.error("Failed to populate Pdf report title.", e);
			e.printStackTrace();
		}
		
		return table;
	}
	
	public static void addCellCustomBorder(PdfPTable table, String remarks, Font fontStyle, int alignment, LineDash left, LineDash right, LineDash top, LineDash bottom, int colSpan) {
		PdfPCell theCell = new PdfPCell();
		theCell.setColspan(colSpan);
		Paragraph paragraph = new Paragraph(16, remarks, fontStyle);
		paragraph.setAlignment(alignment);
		theCell.addElement(paragraph);
		theCell.setBorder(PdfPCell.NO_BORDER); // Has to be No  Border
											  // left, right, top , bottom
	    theCell.setCellEvent(new CustomBorder(left, right, top, bottom));
		theCell.setPaddingTop(5f);
		table.addCell(theCell);
		theCell = null;
		paragraph = null;
	       
	}
}
