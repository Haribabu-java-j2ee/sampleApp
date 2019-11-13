package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dto.CorisUnclaimedPropertyDTO;
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

public class CorisReportUnclaimedProperty extends ReportBaseHandler {

	private static Logger logger = Logger.getLogger(CorisReportUnclaimedProperty.class.getName());
	
	private CorisReportUnclaimedPropertySearchCriteria thisCriteria;
	
	public static final LineDash solid = new SolidLine();
	public static final LineDash dotted = new DottedLine();
	public static final LineDash dashed = new DashedLine();
	
	public CorisReportUnclaimedProperty(ReportBaseSearchCriteria criteria) {
		super(criteria);
		thisCriteria = (CorisReportUnclaimedPropertySearchCriteria) criteria;
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
	public byte[] generateReport(List<?> corisUnclaimerdPropertyDTO) {
		if ("pdf".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generatePdfReport((List<CorisUnclaimedPropertyDTO>)corisUnclaimerdPropertyDTO);
		} else if ("xlsx".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generateExcelReport((List<CorisUnclaimedPropertyDTO>) corisUnclaimerdPropertyDTO);
		}
		return null;
	}
	
	/**
	 * @param docList
	 * @return
	 */
	private byte[] generateExcelReport(List<CorisUnclaimedPropertyDTO> corisUnclaimerdPropertyDTO) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Unclaimed Proerpty Report ");
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (CorisUnclaimedPropertyDTO corisUnclaimedPropertyDTO: corisUnclaimerdPropertyDTO) {
				row = sheet.createRow(++rowCount);
				rowCount = generateExcelRowData(row, rowCount, sheet, corisUnclaimedPropertyDTO);
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			
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
		cell.setCellValue("CASE NUMBER");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("CHECK #");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("ISSUE DATE");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("OWNER");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("AMOUNT");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(5);
		cell.setCellValue("DESCRIPTION");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(6);
		cell.setCellValue("LAST TRANSACTION DATE");
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
	protected int generateExcelRowData(Row row, int rowCount, XSSFSheet sheet, CorisUnclaimedPropertyDTO corisUnclaimedPropertyDTO) throws Exception {
		
		Cell cell = row.createCell(0);
		
		cell.setCellValue(TextUtil.print(corisUnclaimedPropertyDTO.getCaseNum()));

		
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(corisUnclaimedPropertyDTO.getCheckNum()));
		
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.printDate(corisUnclaimedPropertyDTO.getTransDateTime()));
		
		cell = row.createCell(3);
		if (TextUtil.isEmpty(corisUnclaimedPropertyDTO.getFirstName())){
			cell.setCellValue(TextUtil.print(corisUnclaimedPropertyDTO.getLastName()));
		} else {
			cell.setCellValue(TextUtil.print(corisUnclaimedPropertyDTO.getLastName() + " " + corisUnclaimedPropertyDTO.getFirstName()));
		}
		cell = row.createCell(4);
		cell.setCellValue(TextUtil.print(Constants.receiptDecimalFormatter.format(corisUnclaimedPropertyDTO.getAmtPaid())));
		
		cell = row.createCell(5);
		cell.setCellValue(TextUtil.print(corisUnclaimedPropertyDTO.getAcctDescr()));
		
		cell = row.createCell(6);
		cell.setCellValue(TextUtil.printDate(corisUnclaimedPropertyDTO.getLastTransDateTime()));
		
		/////////////////////////////////////////////////////////////////////
		//CleanUp
		/////////////////////////////////////////////////////////////////////
		cell = null;
		
		return rowCount;

	}
	
	/**
	 * @param corisUnclaimerdPropertyDTO
	 * @return
	 */
	private byte[] generatePdfReport(List<CorisUnclaimedPropertyDTO> corisUnclaimerdPropertyDTO) {
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
			document.addTitle("Unclaimed Property Report");
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(corisUnclaimerdPropertyDTO, thisCriteria.getCourtType());
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
	private PdfPTable generatePdfResultTable(List<CorisUnclaimedPropertyDTO> corisUnclaimerdPropertyDTO, String courtType) {
		PdfPTable table = new PdfPTable(new float[] {10, 10, 10, 30, 15, 15, 10});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		// start result rows
		generatePdfReportContents(table, corisUnclaimerdPropertyDTO, courtType);
		return table;
	}
	
	/**
	 * @param table
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 */
	private void generatePdfReportContents(PdfPTable table, List<CorisUnclaimedPropertyDTO> corisUnclaimerdPropertyDTO, String courtType) {
		LineDash left = null;
		LineDash right = null;
		LineDash top = null;
		LineDash bottom = null;
		
		if (corisUnclaimerdPropertyDTO == null || corisUnclaimerdPropertyDTO.size() == 0){
			addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 5);
			addCellCustomBorder(table, "Nothing to report", BOLDFONT, Element.ALIGN_CENTER, left, right, top, bottom, 5);
		} else 	{	
			for(CorisUnclaimedPropertyDTO corisUnclaimedPropertyDTO :corisUnclaimerdPropertyDTO){
				
				addCellCustomBorder(table, TextUtil.print(corisUnclaimedPropertyDTO.getCaseNum()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.print(corisUnclaimedPropertyDTO.getCheckNum()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.printDate(corisUnclaimedPropertyDTO.getTransDateTime()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				if (TextUtil.isEmpty(corisUnclaimedPropertyDTO.getFirstName())){
					addCellCustomBorder(table, TextUtil.print(corisUnclaimedPropertyDTO.getLastName()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				} else {
					addCellCustomBorder(table, TextUtil.print(corisUnclaimedPropertyDTO.getLastName() + " " + corisUnclaimedPropertyDTO.getFirstName()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				}
				addCellCustomBorder(table, TextUtil.print(Constants.receiptDecimalFormatter.format(corisUnclaimedPropertyDTO.getAmtPaid())), NORMALFONT, Element.ALIGN_RIGHT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.print(corisUnclaimedPropertyDTO.getAcctDescr()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.printDate(corisUnclaimedPropertyDTO.getLastTransDateTime()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				corisUnclaimedPropertyDTO = null;
			}
		}
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfTableColumnHeaders(com.itextpdf.text.pdf.PdfPTable)
	 */
	@Override
	protected void populatePdfTableColumnHeaders(PdfPTable table) {
		
		addCellCustomBorder(table, "CASE #", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "CHECK #", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "ISSUE DATE", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "OWNER", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "AMOUNT", BOLDFONT, Element.ALIGN_CENTER, null, null, null, solid, 1);
		addCellCustomBorder(table, "TYPE", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "DATE", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
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
			addCellCustomBorder(table, "UNCLAIMED PROPERTY", BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
			addCellCustomBorder(table, "JOURNAL "  + thisCriteria.getStartJournal() + " to " +  thisCriteria.getEndJournal(), BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
			addCellCustomBorder(table, "DATE "  + thisCriteria.getStartDate() + " to " +  thisCriteria.getEndDate(), BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
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
