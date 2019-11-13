package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.jdmttype.JdmtTypeBO;
import gov.utcourts.coriscommon.dataaccess.offense.OffenseBO;
import gov.utcourts.coriscommon.dataaccess.summaryevent.SummaryEventBO;
import gov.utcourts.coriscommon.dto.CorisChargeDispositionDTO;
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

public class CorisReportChargeDisposition extends ReportBaseHandler {

	private static Logger logger = Logger.getLogger(CorisReportChargeDisposition.class.getName());
	
	private CorisReportChargeDispositionSearchCriteria thisCriteria;
	
	public static final LineDash solid = new SolidLine();
	public static final LineDash dotted = new DottedLine();
	public static final LineDash dashed = new DashedLine();
	
	public CorisReportChargeDisposition(ReportBaseSearchCriteria criteria) {
		super(criteria);
		thisCriteria = (CorisReportChargeDispositionSearchCriteria) criteria;
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
	public byte[] generateReport(List<?> corisChargeDispositionListDTO) {
		if ("pdf".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generatePdfReport((List<CorisChargeDispositionDTO>)corisChargeDispositionListDTO);
		} else if ("xlsx".equalsIgnoreCase(thisCriteria.getReportformat())) {
			return generateExcelReport((List<CorisChargeDispositionDTO>) corisChargeDispositionListDTO);
		}
		return null;
	}
	
	/**
	 * @param docList
	 * @return
	 */
	private byte[] generateExcelReport(List<CorisChargeDispositionDTO> corisChargeDispositionListDTO) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Charge Disposition Report ");
		int rowCount = 0;
		Row row = null;
		try {
			row = sheet.createRow(0); // create header row
			populateExcelColumnHeaders(row, sheet);
			for (CorisChargeDispositionDTO corisCorisChargeDispositionDTO: corisChargeDispositionListDTO) {
				row = sheet.createRow(++rowCount);
				rowCount = generateExcelRowData(row, rowCount, sheet, corisCorisChargeDispositionDTO);
			}
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			
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
		cell.setCellValue("Filing Date");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("Case Description");
		cell.setCellStyle(cStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("Defendant");
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
	protected int generateExcelRowData(Row row, int rowCount, XSSFSheet sheet, CorisChargeDispositionDTO corisCorisChargeDispositionDTO) throws Exception {
		PersonnelRepository personnelRepository = new PersonnelRepository();
		
		Cell cell = row.createCell(0);
		
		cell = row.createCell(0);
		cell.setCellValue(TextUtil.print(TextUtil.print(corisCorisChargeDispositionDTO.getCaseNum())));
		
		cell = row.createCell(1);
		cell.setCellValue(TextUtil.print(TextUtil.print(corisCorisChargeDispositionDTO.getFilingDate())));
		
		cell = row.createCell(2);
		cell.setCellValue(TextUtil.print(TextUtil.print(corisCorisChargeDispositionDTO.getDescr())));

		
		if (TextUtil.isEmpty(corisCorisChargeDispositionDTO.getFirstName())){
			cell.setCellValue(TextUtil.print(corisCorisChargeDispositionDTO.getLastName()));
		} else {
			cell.setCellValue(TextUtil.print(corisCorisChargeDispositionDTO.getLastName() + ", " + corisCorisChargeDispositionDTO.getFirstName()));
		}
		
		
		for (ChargeBO chargeBO : corisCorisChargeDispositionDTO.getChargeList()){
			row = sheet.createRow(++rowCount);
			
			cell = row.createCell(0);
			CellStyle cellStyle = row.getSheet().getWorkbook().createCellStyle();

		    cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		    cell.setCellStyle(cellStyle);
			cell.setCellValue(TextUtil.print(TextUtil.print(chargeBO.getSeq())));
			
		    cellStyle = null;
			
			OffenseBO offenseBO = new OffenseBO(corisCorisChargeDispositionDTO.getCourtType())
			.where(OffenseBO.OFFSVIOLCODE, chargeBO.getOffsViolCode())
			.where(OffenseBO.LASTEFFECTDATE, chargeBO.getOffsEffectDate())
			.find(OffenseBO.DESCR);
			
			cell = row.createCell(1);
			cell.setCellValue(TextUtil.print(TextUtil.print(chargeBO.getOffsViolCode()) + " " + TextUtil.print(offenseBO.getDescr())));
			offenseBO = null;
			
			if (corisCorisChargeDispositionDTO.getSummaryEventList() == null){
				row = sheet.createRow(++rowCount);
				JdmtTypeBO jdmtTypeBO = new JdmtTypeBO(corisCorisChargeDispositionDTO.getCourtType())
				.where(JdmtTypeBO.JDMTCODE, chargeBO.getJdmtCode())
				.find(JdmtTypeBO.DESCR);
				
				cell = row.createCell(0);
				cell.setCellValue(TextUtil.print(" "));
				cell = row.createCell(1);
				cell.setCellValue(TextUtil.printDate(chargeBO.getJdmtDate()));
				cell = row.createCell(2);
				cell.setCellValue(TextUtil.print(" "));
				cell = row.createCell(3);
				cell.setCellValue(TextUtil.print(jdmtTypeBO.getDescr()));
				
				jdmtTypeBO = null;
			} else {	
				String chargeSeq = "";
				for (SummaryEventBO summaryEventBO : corisCorisChargeDispositionDTO.getSummaryEventList()){
					chargeSeq = "CHARGE " + String.valueOf(chargeBO.getSeq());
					if (summaryEventBO.getDescr().toUpperCase().contains(chargeSeq)){
						
						row = sheet.createRow(++rowCount);
						cell = row.createCell(0);
						cell.setCellValue(TextUtil.print(" "));
						cell = row.createCell(1);
						cell.setCellValue(TextUtil.print(TextUtil.printDate(summaryEventBO.getCreateDatetime())));
						cell = row.createCell(2);
						cell.setCellValue(TextUtil.print(CorisCaseHistoryCommon.getFullName(Integer.valueOf(summaryEventBO.getKey1()), corisCorisChargeDispositionDTO.getCourtType(), personnelRepository, null)));
						cell = row.createCell(3);
						cell.setCellValue(TextUtil.print(TextUtil.print(summaryEventBO.getDescr())));
					}
					summaryEventBO = null;
					chargeSeq = null;
				}
			}
			chargeBO = null;	

		}
		
		/////////////////////////////////////////////////////////////////////
		//CleanUp
		/////////////////////////////////////////////////////////////////////
		cell = null;
		
		return rowCount;

	}
	
	/**
	 * @param corisChargeDispositionListDTO
	 * @return
	 */
	private byte[] generatePdfReport(List<CorisChargeDispositionDTO> corisChargeDispositionListDTO) {
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
			document.addTitle("CORIS Charge Disposition Report");
			document.setMargins(15f, 22f, 10f, 30f);
			document.open();
			
			// Title
			table = populatePdfReportTitle();
			document.add(table);
			
			// report result table
			table = generatePdfResultTable(corisChargeDispositionListDTO, thisCriteria.getCourtType());
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
	private PdfPTable generatePdfResultTable(List<CorisChargeDispositionDTO> corisChargeDispositionListDTO, String courtType) {
		PdfPTable table = new PdfPTable(new float[] {10, 11, 25, 25, 10, 10, 10});
		table.setWidthPercentage(100);
		populatePdfTableColumnHeaders(table);
		// start result rows
		generatePdfReportContents(table, corisChargeDispositionListDTO, courtType);
		return table;
	}
	
	/**
	 * @param table
	 * @param docList
	 * @param courtType
	 * @param withoutImgOnly
	 */
	private void generatePdfReportContents(PdfPTable table, List<CorisChargeDispositionDTO> corisChargeDispositionListDTO, String courtType) {
		LineDash left = null;
		LineDash right = null;
		LineDash top = null;
		LineDash bottom = null;
		PersonnelRepository personnelRepository = new PersonnelRepository();

		
		if (corisChargeDispositionListDTO == null || corisChargeDispositionListDTO.size() == 0){
			addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 9);
			addCellCustomBorder(table, "Nothing to report", BOLDFONT, Element.ALIGN_CENTER, left, right, top, bottom, 9);
		} else 	{	
			for(CorisChargeDispositionDTO corisCorisChargeDispositionDTO :corisChargeDispositionListDTO){
				addCellCustomBorder(table, TextUtil.print(corisCorisChargeDispositionDTO.getCaseNum()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.printDate(corisCorisChargeDispositionDTO.getFilingDate()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				addCellCustomBorder(table, TextUtil.print(corisCorisChargeDispositionDTO.getDescr()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				
				if (TextUtil.isEmpty(corisCorisChargeDispositionDTO.getFirstName())){
					addCellCustomBorder(table, TextUtil.print(corisCorisChargeDispositionDTO.getLastName()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 3);
				} else {
					addCellCustomBorder(table, TextUtil.print(corisCorisChargeDispositionDTO.getLastName() + ", " + corisCorisChargeDispositionDTO.getFirstName()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 3);
				}
				
				addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
				
				top = null;
				
				for (ChargeBO chargeBO : corisCorisChargeDispositionDTO.getChargeList()){
					try {
						top = null;
						addCellCustomBorder(table, TextUtil.print(chargeBO.getSeq()), NORMALFONT, Element.ALIGN_RIGHT, left, right, top, bottom, 1);
						
						top = dashed;
						
						OffenseBO offenseBO = new OffenseBO(courtType)
						.where(OffenseBO.OFFSVIOLCODE, chargeBO.getOffsViolCode())
						.where(OffenseBO.LASTEFFECTDATE, chargeBO.getOffsEffectDate())
						.find(OffenseBO.DESCR);
						
						addCellCustomBorder(table, TextUtil.print(chargeBO.getOffsViolCode()) + " " + TextUtil.print(offenseBO.getDescr()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 5);
						
						top = null;
						addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
						offenseBO = null;

						
						if (corisCorisChargeDispositionDTO.getSummaryEventList() == null){
							JdmtTypeBO jdmtTypeBO = new JdmtTypeBO(courtType)
							.where(JdmtTypeBO.JDMTCODE, chargeBO.getJdmtCode())
							.find(JdmtTypeBO.DESCR);
							
							addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
							addCellCustomBorder(table, TextUtil.printDate(chargeBO.getJdmtDate()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
							addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
							addCellCustomBorder(table, TextUtil.print(jdmtTypeBO.getDescr()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 3);
							addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
							jdmtTypeBO = null;
						} else {	
							String chargeSeq = "";
							for (SummaryEventBO summaryEventBO : corisCorisChargeDispositionDTO.getSummaryEventList()){
								chargeSeq = "CHARGE " + String.valueOf(chargeBO.getSeq());
								if (summaryEventBO.getDescr().toUpperCase().contains(chargeSeq)){
									addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
									addCellCustomBorder(table, TextUtil.printDate(summaryEventBO.getCreateDatetime()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
									addCellCustomBorder(table, CorisCaseHistoryCommon.getFullName(Integer.valueOf(summaryEventBO.getKey1()), courtType, personnelRepository, null), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
									addCellCustomBorder(table, TextUtil.print(summaryEventBO.getDescr()), NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 3);
									addCellCustomBorder(table, " ", NORMALFONT, Element.ALIGN_LEFT, left, right, top, bottom, 1);
								}
								summaryEventBO = null;
								chargeSeq = null;
							}
						}
						
					} catch (Exception e) {
						logger.error("generatePdfReportContents(PdfPTable table, List<CorisChargeDispositionDTO> corisChargeDispositionListDTO, String courtType) " + e.getMessage());
					}
					chargeBO =null;
				}
				top = solid;
				corisCorisChargeDispositionDTO = null;
			}
		}
		personnelRepository = null;
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.coriscommon.report.PdfAndExcelReportBaseHandler#populatePdfTableColumnHeaders(com.itextpdf.text.pdf.PdfPTable)
	 */
	@Override
	protected void populatePdfTableColumnHeaders(PdfPTable table) {
		
		addCellCustomBorder(table, "CASE #", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "FILING DATE", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "CASE DESCRIPTION", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 1);
		addCellCustomBorder(table, "DEFENDANT", BOLDFONT, Element.ALIGN_LEFT, null, null, null, solid, 4);
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
			addCellCustomBorder(table, "CHARGE DISPOSITION REPORT", BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
			addCellCustomBorder(table, "FROM " + TextUtil.printDate(thisCriteria.getStartDate()) + " TO " + TextUtil.printDate(thisCriteria.getEndDate()), BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
			if (!TextUtil.isEmpty(thisCriteria.getJdmtCode()) || !TextUtil.isEmpty(thisCriteria.getCaseType())){
				StringBuilder title = new StringBuilder();
				if (!TextUtil.isEmpty(thisCriteria.getJdmtCode())){
					try {
						JdmtTypeBO jdmtTypeBO = new JdmtTypeBO(thisCriteria.getCourtType())
						.where(JdmtTypeBO.JDMTCODE, thisCriteria.getJdmtCode())
						.find(JdmtTypeBO.DESCR);
						title.append("Judgment Type: ");
						title.append(jdmtTypeBO.getDescr());
						title.append(" ");
						jdmtTypeBO = null;
					} catch (Exception e) {
						logger.error("Jdmt Code populatePdfReportTitle() " + e.getMessage());
					}
				}
				if (!TextUtil.isEmpty(thisCriteria.getCaseType())){
					try {
						CaseTypeBO caseTypeBO = new CaseTypeBO(thisCriteria.getCourtType())
						.where(CaseTypeBO.CASETYPE, thisCriteria.getCaseType())
						.find(CaseTypeBO.DESCR);
						title.append("Case Type: ");
						title.append(caseTypeBO.getDescr());
						title.append(" ");
						caseTypeBO = null;
					} catch (Exception e) {
						logger.error("Case Type populatePdfReportTitle() " + e.getMessage());
					}
				}
				addCellCustomBorder(table, title.toString(), BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
				title = null;
			} else {
				addCellCustomBorder(table, "", BOLDFONT, Element.ALIGN_CENTER, null, null, null, null, 9);
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
