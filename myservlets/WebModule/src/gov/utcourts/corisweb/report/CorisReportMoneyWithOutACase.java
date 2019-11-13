package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.jdmttype.JdmtTypeBO;
import gov.utcourts.coriscommon.dataaccess.offense.OffenseBO;
import gov.utcourts.coriscommon.dataaccess.summaryevent.SummaryEventBO;
import gov.utcourts.coriscommon.dto.CorisMoneyWithOutACaseDTO;
import gov.utcourts.coriscommon.report.ReportBaseHandler;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.PersonnelRepository;
import gov.utcourts.coriscommon.util.TextUtil;
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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CorisReportMoneyWithOutACase extends ReportBaseHandler {

	private static Logger logger = Logger.getLogger(CorisReportMoneyWithOutACase.class.getName());
	
	private CorisReportMoneyWithOutACaseSearchCriteria thisCriteria;
	
	public static final LineDash solid = new SolidLine();
	public static final LineDash dotted = new DottedLine();
	public static final LineDash dashed = new DashedLine();
	
	public CorisReportMoneyWithOutACase(ReportBaseSearchCriteria criteria) {
		super(criteria);
		thisCriteria = (CorisReportMoneyWithOutACaseSearchCriteria) criteria;
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
	public byte[] generateReport(List<?> corisMoneyWithOutACaseListDTO) {
		if ("pdf".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generatePdfReport((List<CorisMoneyWithOutACaseDTO>)corisMoneyWithOutACaseListDTO);
		} else if ("xlsx".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generateExcelReport((List<CorisMoneyWithOutACaseDTO>) corisMoneyWithOutACaseListDTO);
		}
		return null;
	}
	
	/**
	 * @param docList
	 * @return
	 */
	private byte[] generateExcelReport(List<CorisMoneyWithOutACaseDTO> corisMoneyWithOutACaseListDTO) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Money Without A Case Report ");
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (CorisMoneyWithOutACaseDTO corisCorisMoneyWithOutACaseDTO: corisMoneyWithOutACaseListDTO) {
				row = sheet.createRow(++rowCount);
				rowCount = generateExcelRowData(row, rowCount, sheet, corisCorisMoneyWithOutACaseDTO);
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			
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
		cell.setCellValue("PAYOR");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("DATE PAID");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("REFERENCE NO.");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("TENDER");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("AMOUNT");
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
	protected int generateExcelRowData(Row row, int rowCount, XSSFSheet sheet, CorisMoneyWithOutACaseDTO corisCorisMoneyWithOutACaseDTO) throws Exception {
		PersonnelRepository personnelRepository = new PersonnelRepository();
		
		Cell cell = row.createCell(0);
		
		if (TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getFirstName())){
			cell.setCellValue(TextUtil.print(corisCorisMoneyWithOutACaseDTO.getLastName()));
		} else {
			cell.setCellValue(TextUtil.print(corisCorisMoneyWithOutACaseDTO.getLastName()+ ", " + corisCorisMoneyWithOutACaseDTO.getFirstName()));
		}

		
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(TextUtil.printDate(corisCorisMoneyWithOutACaseDTO.getTransDateTime())));
		
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(TextUtil.print(corisCorisMoneyWithOutACaseDTO.getAccountNumber())));

		cell = row.createCell(3);
		cell.setCellValue(TextUtil.print(TextUtil.print(corisCorisMoneyWithOutACaseDTO.getAccountNumber())));

		cell = row.createCell(4);
		if ("CA".equals(corisCorisMoneyWithOutACaseDTO.getTenderType())){
			cell.setCellValue(TextUtil.print("Cash"));
		} else if ("CH".equals(corisCorisMoneyWithOutACaseDTO.getTenderType())){
			cell.setCellValue(TextUtil.print("Check"));
		} else if ("CC".equals(corisCorisMoneyWithOutACaseDTO.getTenderType())){
			cell.setCellValue(TextUtil.print("Card"));
		} else if ("CR".equals(corisCorisMoneyWithOutACaseDTO.getTenderType())){
			cell.setCellValue(TextUtil.print("Credit"));
		} else if ("NM".equals(corisCorisMoneyWithOutACaseDTO.getTenderType())){
			cell.setCellValue(TextUtil.print("Non-Cash"));
		}
		cell = row.createCell(5);
		cell.setCellValue(TextUtil.print(Constants.accountingDecimalFormatter.format(corisCorisMoneyWithOutACaseDTO.getAmtPaid())));
		

		if (!TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getAddress1())){
			cell = row.createCell(6);
			cell.setCellValue(TextUtil.print(corisCorisMoneyWithOutACaseDTO.getAddress1()));
		}

		if (!TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getAddress2())){
			cell = row.createCell(7);
			cell.setCellValue(TextUtil.print(corisCorisMoneyWithOutACaseDTO.getAddress2()));
		}

		if (!TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getCity()) && !TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getState()) && !TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getZip())){
			cell = row.createCell(8);
			cell.setCellValue(TextUtil.print(corisCorisMoneyWithOutACaseDTO.getCity() + ", " + corisCorisMoneyWithOutACaseDTO.getState() + " " + corisCorisMoneyWithOutACaseDTO.getZip()));
		}

		if (!TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getCountry())){
			cell = row.createCell(9);
			cell.setCellValue(TextUtil.print(corisCorisMoneyWithOutACaseDTO.getCountry()));
		}

		if (!TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getCheckStubDescr())){
			cell = row.createCell(10);
			cell.setCellValue(TextUtil.print(corisCorisMoneyWithOutACaseDTO.getCheckStubDescr()));
		}
		
		/////////////////////////////////////////////////////////////////////
		//CleanUp
		/////////////////////////////////////////////////////////////////////
		cell = null;
		
		return rowCount;

	}
	
	/**
	 * @param corisMoneyWithOutACaseListDTO
	 * @return
	 */
	private byte[] generatePdfReport(List<CorisMoneyWithOutACaseDTO> corisMoneyWithOutACaseListDTO) {
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
			document.addTitle("CORIS Money Without A Case Report");
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(corisMoneyWithOutACaseListDTO, thisCriteria.getCourtType());
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
	private PdfPTable generatePdfResultTable(List<CorisMoneyWithOutACaseDTO> corisMoneyWithOutACaseListDTO, String courtType) {
		PdfPTable table = new PdfPTable(new float[] {40, 15, 15, 15, 15});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		// start result rows
		generatePdfReportContents(table, corisMoneyWithOutACaseListDTO, courtType);
		return table;
	}
	
	/**
	 * @param table
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 */
	private void generatePdfReportContents(PdfPTable table, List<CorisMoneyWithOutACaseDTO> corisMoneyWithOutACaseListDTO, String courtType) {
		LineDash left = null;
		LineDash right = null;
		LineDash top = null;
		LineDash bottom = null;
		PersonnelRepository personnelRepository = new PersonnelRepository();

		
		if (corisMoneyWithOutACaseListDTO == null || corisMoneyWithOutACaseListDTO.size() == 0){
			addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 5);
			addCellCustomBorder(table, "Nothing to report", BOLDFONT, Element.ALIGN_CENTER, left, right, top, bottom, 5);
		} else 	{	
			for(CorisMoneyWithOutACaseDTO corisCorisMoneyWithOutACaseDTO :corisMoneyWithOutACaseListDTO){
				
				if (TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getFirstName())){
					addCellCustomBorder(table, TextUtil.print(corisCorisMoneyWithOutACaseDTO.getLastName()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				} else {
					addCellCustomBorder(table, TextUtil.print(corisCorisMoneyWithOutACaseDTO.getLastName() +", " + corisCorisMoneyWithOutACaseDTO.getFirstName()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				}
				addCellCustomBorder(table, TextUtil.printDate(corisCorisMoneyWithOutACaseDTO.getTransDateTime()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.print(corisCorisMoneyWithOutACaseDTO.getAccountNumber()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				
				if ("CA".equals(corisCorisMoneyWithOutACaseDTO.getTenderType())){
					addCellCustomBorder(table, TextUtil.print("Cash"), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				} else if ("CH".equals(corisCorisMoneyWithOutACaseDTO.getTenderType())){
					addCellCustomBorder(table, TextUtil.print("Check"), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				} else if ("CC".equals(corisCorisMoneyWithOutACaseDTO.getTenderType())){
					addCellCustomBorder(table, TextUtil.print("Card"), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				} else if ("CR".equals(corisCorisMoneyWithOutACaseDTO.getTenderType())){
					addCellCustomBorder(table, TextUtil.print("Credit"), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				} else if ("NM".equals(corisCorisMoneyWithOutACaseDTO.getTenderType())){
					addCellCustomBorder(table, TextUtil.print("Non-Cash"), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				}

				addCellCustomBorder(table, TextUtil.print(Constants.accountingDecimalFormatter.format(corisCorisMoneyWithOutACaseDTO.getAmtPaid())), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				
				top = null;
				
				if (!TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getAddress1())){
					addCellCustomBorder(table, TextUtil.print(corisCorisMoneyWithOutACaseDTO.getAddress1()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 5);
				}
				
				if (!TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getAddress2())){
					addCellCustomBorder(table, TextUtil.print(corisCorisMoneyWithOutACaseDTO.getAddress2()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 5);
				}
				
				if (!TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getCity()) && !TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getState()) && !TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getZip())){
					addCellCustomBorder(table, TextUtil.print(corisCorisMoneyWithOutACaseDTO.getCity() + ", " + corisCorisMoneyWithOutACaseDTO.getState() + " " + corisCorisMoneyWithOutACaseDTO.getZip()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 5);
				}
				
				if (!TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getCountry())){
					addCellCustomBorder(table, TextUtil.print(corisCorisMoneyWithOutACaseDTO.getCountry()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 5);
				}
				
				if (!TextUtil.isEmpty(corisCorisMoneyWithOutACaseDTO.getCheckStubDescr())){
					addCellCustomBorder(table, TextUtil.print(corisCorisMoneyWithOutACaseDTO.getCheckStubDescr()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 5);
				}
				
				top = solid;
				corisCorisMoneyWithOutACaseDTO = null;
			}
		}
		personnelRepository = null;
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfTableColumnHeaders(com.itextpdf.text.pdf.PdfPTable)
	 */
	@Override
	protected void populatePdfTableColumnHeaders(PdfPTable table) {
		
		addCellCustomBorder(table, "PAYOR #", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "DATE PAID", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "REFERENCE NO.", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "TENDER", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "AMOUNT", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
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
			addCellCustomBorder(table, "TRUST/MONEY WITHOUT A CASE", BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
			if ("P".equals(thisCriteria.getPayorType())){
				addCellCustomBorder(table, "TYPE PAYOR", BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
				
			} else {
				addCellCustomBorder(table, "TYPE DEFENDANT", BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
				
			}
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
