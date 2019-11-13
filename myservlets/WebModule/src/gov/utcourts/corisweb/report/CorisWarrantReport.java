package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dto.CorisWarrantDTO;
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

public class CorisWarrantReport extends ReportBaseHandler {

	private static Logger logger = Logger.getLogger(CorisWarrantReport.class.getName());
	
	private CorisWarrantReportSearchCriteria thisCriteria;
	
	public static final LineDash solid = new SolidLine();
	public static final LineDash dotted = new DottedLine();
	public static final LineDash dashed = new DashedLine();
	
	public CorisWarrantReport(ReportBaseSearchCriteria criteria) {
		super(criteria);
		thisCriteria = (CorisWarrantReportSearchCriteria) criteria;
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
	public byte[] generateReport(List<?> corisWarrantListDTO) {
		if ("pdf".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generatePdfReport((List<CorisWarrantDTO>)corisWarrantListDTO);
		} else if ("xlsx".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generateExcelReport((List<CorisWarrantDTO>) corisWarrantListDTO);
		}
		return null;
	}
	
	/**
	 * @param docList
	 * @return
	 */
	private byte[] generateExcelReport(List<CorisWarrantDTO> corisWarrantListDTO) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Warrant " + thisCriteria.getWarrantType() + " " + thisCriteria.getWarrantStatus());
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (CorisWarrantDTO corisWarrantDTO: corisWarrantListDTO) {
				row = sheet.createRow(++rowCount);
				generateExcelRowData(row, corisWarrantDTO);
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
		cell.setCellValue("Birthdate");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("Bail Amount");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("Judge Name");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(5);
		cell.setCellValue("Order Date");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(6);
		cell.setCellValue("Issue Date");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(7);
		cell.setCellValue("Expiration Date");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(8);
		cell.setCellValue("Recall Date");
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
	protected void generateExcelRowData(Row row, CorisWarrantDTO corisWarrantDTO) throws Exception {
		Cell cell = row.createCell(0);
		
		cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(TextUtil.print(corisWarrantDTO.getCaseNum())));
		
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(TextUtil.print(corisWarrantDTO.getDefLastName() + ", " + corisWarrantDTO.getDefFirstName())));

		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(TextUtil.printDate(corisWarrantDTO.getBirthDate())));

		cell = row.createCell(3);
		cell.setCellValue(Constants.receiptDecimalFormatter.format(corisWarrantDTO.getBailAmt()));
		
		cell = row.createCell(4);
		cell.setCellValue(corisWarrantDTO.getJudgeLastName() +"' " + corisWarrantDTO.getJudgeFirstName());
		
		cell = row.createCell(5);
		cell.setCellValue(TextUtil.printDate(corisWarrantDTO.getOrderDate()));
		
		
		cell = row.createCell(6);
		cell.setCellValue(TextUtil.printDate(corisWarrantDTO.getIssueDate()));
		
		
		cell = row.createCell(7);
		cell.setCellValue(TextUtil.printDate(corisWarrantDTO.getExpDate()));
		
		cell = row.createCell(8);
		cell.setCellValue(TextUtil.printDate(corisWarrantDTO.getRecallDate()));
		
		/////////////////////////////////////////////////////////////////////
		//CleanUp
		/////////////////////////////////////////////////////////////////////
		cell = null;

	}
	
	/**
	 * @param corisWarrantListDTO
	 * @return
	 */
	private byte[] generatePdfReport(List<CorisWarrantDTO> corisWarrantListDTO) {
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
			document.addTitle("CORIS Warrant Report");
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(corisWarrantListDTO, thisCriteria.getCourtType());
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
	private PdfPTable generatePdfResultTable(List<CorisWarrantDTO> corisWarrantListDTO, String courtType) {
		PdfPTable table = new PdfPTable(new float[] {10, 15, 10, 10, 15, 10, 10, 10, 10});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		// start result rows
		generatePdfReportContents(table, corisWarrantListDTO, courtType);
		return table;
	}
	
	/**
	 * @param table
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 */
	private void generatePdfReportContents(PdfPTable table, List<CorisWarrantDTO> corisWarrantListDTO, String courtType) {
		LineDash left = null;
		LineDash right = null;
		LineDash top = null;
		LineDash bottom = null;

		
		if (corisWarrantListDTO == null){
			addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 9);
			addCellCustomBorder(table, "Nothing to report", BOLDFONT, Element.ALIGN_CENTER, left, right, top, bottom, 9);
		} else 	{	
			for(CorisWarrantDTO corisWarrantDTO :corisWarrantListDTO){
				bottom = solid;
				
				if (thisCriteria.isAddress()){
					if (!TextUtil.isEmpty(corisWarrantDTO.getAddress1()) &&
						!TextUtil.isEmpty(corisWarrantDTO.getCity()) &&
						!TextUtil.isEmpty(corisWarrantDTO.getStateCode())&&
						!TextUtil.isEmpty(corisWarrantDTO.getZipCode())){
							bottom = null;
					}
				}
				if (thisCriteria.isVehicle()){
					if (corisWarrantDTO.getVehicle() != null){
						bottom = null;
					}
				}
				
				if (thisCriteria.isCharges()){
					if (corisWarrantDTO.getChargeList() != null){
						bottom = null;
					}
				}
				
				addCellCustomBorder(table, TextUtil.print(corisWarrantDTO.getCaseNum()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				
				bottom = solid;
				if (thisCriteria.isAddress()){
					if (!TextUtil.isEmpty(corisWarrantDTO.getAddress1()) &&
						!TextUtil.isEmpty(corisWarrantDTO.getCity()) &&
						!TextUtil.isEmpty(corisWarrantDTO.getStateCode())&&
						!TextUtil.isEmpty(corisWarrantDTO.getZipCode())){
								bottom = dashed;
					}
				}
				if (thisCriteria.isVehicle()){
					if (corisWarrantDTO.getVehicle() != null){
						bottom = dashed;
					}
				}
				
				if (thisCriteria.isCharges()){
					if (corisWarrantDTO.getChargeList() != null){
						bottom = dashed;
					}
				}
					
				addCellCustomBorder(table, TextUtil.print(corisWarrantDTO.getDefLastName() + ", " + corisWarrantDTO.getDefFirstName()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.printDate(corisWarrantDTO.getBirthDate()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.print(Constants.receiptDecimalFormatter.format(corisWarrantDTO.getBailAmt())), NORMALFONT, Element.ALIGN_RIGHT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.print(corisWarrantDTO.getJudgeLastName() +", " + corisWarrantDTO.getJudgeFirstName()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.printDate(corisWarrantDTO.getOrderDate()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.printDate(corisWarrantDTO.getIssueDate()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.printDate(corisWarrantDTO.getExpDate()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				
				bottom = solid;
				if (thisCriteria.isAddress()){
					if (!TextUtil.isEmpty(corisWarrantDTO.getAddress1()) &&
						!TextUtil.isEmpty(corisWarrantDTO.getCity()) &&
						!TextUtil.isEmpty(corisWarrantDTO.getStateCode())&&
						!TextUtil.isEmpty(corisWarrantDTO.getZipCode())){
							bottom = null;
					}
				}
				
				if (thisCriteria.isVehicle()){
					if (corisWarrantDTO.getVehicle() != null){
						bottom = null;
					}
				}
				
				if (thisCriteria.isCharges()){
					if (corisWarrantDTO.getChargeList() != null){
						bottom = null;
					}
				}			
				
				addCellCustomBorder(table, TextUtil.printDate(corisWarrantDTO.getRecallDate()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				
				if (thisCriteria.isAddress()){
					if (!TextUtil.isEmpty(corisWarrantDTO.getAddress1()) &&
						!TextUtil.isEmpty(corisWarrantDTO.getCity()) &&
						!TextUtil.isEmpty(corisWarrantDTO.getStateCode())&&
						!TextUtil.isEmpty(corisWarrantDTO.getZipCode())){
							StringBuilder partyAddress = new StringBuilder();
							partyAddress.append(TextUtil.isEmpty(corisWarrantDTO.getAddress1()) ? " " : corisWarrantDTO.getAddress1().trim() + " ") 
							.append(TextUtil.isEmpty(corisWarrantDTO.getAddress2()) ? " " : corisWarrantDTO.getAddress2().trim()+ " ") 
							.append(TextUtil.isEmpty(corisWarrantDTO.getCity()) ? " " : corisWarrantDTO.getCity().trim()+ " ") 
							.append(TextUtil.isEmpty(corisWarrantDTO.getStateCode()) ? " " : corisWarrantDTO.getStateCode().trim()+ " ") 
							.append(TextUtil.isEmpty(corisWarrantDTO.getZipCode()) ? " " : corisWarrantDTO.getZipCode().trim()+ " ") 
							.append(TextUtil.isEmpty(corisWarrantDTO.getHomePhone()) ? " " : corisWarrantDTO.getHomePhone().trim()+ " ") 
							.append(TextUtil.isEmpty(corisWarrantDTO.getBusPhone()) ? " " : corisWarrantDTO.getBusPhone().trim()+ " "); 
							
							bottom = solid;
							if (thisCriteria.isVehicle()){
								if (corisWarrantDTO.getVehicle() != null){
									bottom = null;
								}
							}
							
							if (thisCriteria.isCharges()){
								if (corisWarrantDTO.getChargeList() != null){
									bottom = null;
								}
							}
							addCellCustomBorder(table, TextUtil.print(" "), NORMALFONT, Element.ALIGN_RIGHT, left, right, top, bottom, 1);
							
							bottom = solid;
							if (thisCriteria.isVehicle()){
								if (corisWarrantDTO.getVehicle() != null){
									bottom = dashed;
								}
							}
							
							if (thisCriteria.isCharges()){
								if (corisWarrantDTO.getChargeList() != null){
									bottom = dashed;
								}
							}
							addCellCustomBorder(table, "Address: " + TextUtil.print(partyAddress.toString()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 7);
							
							bottom = solid;
							if (thisCriteria.isVehicle()){
								if (corisWarrantDTO.getVehicle() != null){
									bottom = null;
								}
							}
							
							if (thisCriteria.isCharges()){
								if (corisWarrantDTO.getChargeList() != null){
									bottom = null;
								}
							}
							addCellCustomBorder(table, TextUtil.print(" "), NORMALFONT, Element.ALIGN_RIGHT, left, right, top, bottom, 1);
							partyAddress = null;
					}
				}
				
				if (thisCriteria.isVehicle()){
					if (corisWarrantDTO.getVehicle() != null){
						StringBuilder vehicleDescr = new StringBuilder();
						vehicleDescr.append(TextUtil.isEmpty(corisWarrantDTO.getVehicle().getYear()) ? " " : corisWarrantDTO.getVehicle().getYear().trim() + " ") 
						.append(TextUtil.isEmpty(corisWarrantDTO.getVehicle().getMake()) ? " " : corisWarrantDTO.getVehicle().getMake().trim()+ " ") 
						.append(TextUtil.isEmpty(corisWarrantDTO.getVehicle().getModel()) ? " " : corisWarrantDTO.getVehicle().getModel().trim()+ " ") 
						.append(TextUtil.isEmpty(corisWarrantDTO.getVehicle().getColor()) ? " " : corisWarrantDTO.getVehicle().getColor().trim()+ " ") 
						.append(TextUtil.isEmpty(corisWarrantDTO.getVehicle().getPlateStateCode()) ? " " : corisWarrantDTO.getVehicle().getPlateStateCode().trim()+ " ") 
						.append(TextUtil.isEmpty(corisWarrantDTO.getVehicle().getPlateNum()) ? " " : corisWarrantDTO.getVehicle().getPlateNum().trim()+ " ") 
						.append(corisWarrantDTO.getVehicle().getPlateExpDate() + " "); 
						
						bottom = solid;
						if (thisCriteria.isCharges()){
							if (corisWarrantDTO.getChargeList() != null){
								bottom = null;
							}
						}
						addCellCustomBorder(table, TextUtil.print(" "), NORMALFONT, Element.ALIGN_RIGHT, left, right, top, bottom, 1);
						
						bottom = solid;
						if (thisCriteria.isCharges()){
							if (corisWarrantDTO.getChargeList() != null){
								bottom = dashed;
							}
						}
						
						addCellCustomBorder(table, "Vehicle: " + TextUtil.print(vehicleDescr.toString()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 7);
						
						bottom = solid;
						if (thisCriteria.isCharges()){
							if (corisWarrantDTO.getChargeList() != null){
								bottom = null;
							}
						}
						addCellCustomBorder(table, TextUtil.print(" "), NORMALFONT, Element.ALIGN_RIGHT, left, right, top, bottom, 1);
						
						vehicleDescr = null;
					}
				}
			
				if (thisCriteria.isCharges()){
					for(ChargeBO chargeBO: corisWarrantDTO.getChargeList()){
						StringBuilder chargeDescr = new StringBuilder();
						
						chargeDescr.append(chargeBO.getSeq() + " ") 
						.append(TextUtil.isEmpty(chargeBO.getViolCode()) ? " " : chargeBO.getViolCode() + " ") 
						.append(TextUtil.isEmpty(chargeBO.getSeverity()) ? " " : chargeBO.getSeverity() + " ")
						.append(TextUtil.printDate(chargeBO.getViolDatetime())); 
						
						
						bottom = null;
						if (corisWarrantDTO.getChargeList().size() == (corisWarrantDTO.getChargeList().indexOf(chargeBO) + 1) ){
							bottom = solid;
						}
													
						addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
						addCellCustomBorder(table, "Charge : " + chargeDescr.toString(), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 7);
						addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
						
						chargeDescr = null;
					}
				}
				corisWarrantDTO = null;
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
		addCellCustomBorder(table, "BIRTHDATE", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "AMOUNT", BOLDFONT, Element.ALIGN_RIGHT, null, null, null, solid, 1);
		addCellCustomBorder(table, "JUDGE", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "ORDERED", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "ISSUED", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "EXPIRED", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "RECALL", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
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
			
			if (!"All".equals(thisCriteria.getWarrantType())){
				addCellCustomBorder(table, thisCriteria.getWarrantType().toUpperCase() + " WARRANT " + thisCriteria.getWarrantStatus().toUpperCase() + " REPORT", BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
			} else {
				addCellCustomBorder(table, "WARRANT " + thisCriteria.getWarrantStatus().toUpperCase() + " REPORT", BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
			}
			
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
