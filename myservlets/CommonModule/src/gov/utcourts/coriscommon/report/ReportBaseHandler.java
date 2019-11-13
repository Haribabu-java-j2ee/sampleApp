package gov.utcourts.coriscommon.report;

import gov.utcourts.coriscommon.util.TextUtil;

import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class ReportBaseHandler extends PdfPageEventHelper {
	protected static final Font BOLDFONT = FontFactory.getFont(FontFactory.HELVETICA, 8f, Font.BOLD);
	protected static final Font NORMALFONT = FontFactory.getFont(FontFactory.HELVETICA, 8f, Font.NORMAL);
//	protected int totalPg = 0;
	protected PdfTemplate template;
	private ReportBaseSearchCriteria criteria;
	
	public ReportBaseHandler(ReportBaseSearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public void onEndPage(PdfWriter writer, Document document) {
//		totalPg++;
		PdfContentByte cb = writer.getDirectContent();
		Phrase timeStampFooter = new Phrase(TextUtil.printDatetime(new Date(), "MM-dd-yyyy hh:mm a"), BOLDFONT);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, timeStampFooter, document.left(), document.bottom() - 10, 0);
		String pageNumTxt = String.format("Page %d of", writer.getPageNumber());
		Phrase pageNumFooter = new Phrase(pageNumTxt, BOLDFONT);
		cb.addTemplate(template, document.right() -  BOLDFONT.getBaseFont().getWidthPoint(pageNumTxt, 9), document.bottom() - 10);
		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, pageNumFooter, (document.right() - template.getBoundingBox().getWidth() - 10), document.bottom() - 10, 0);
	}
	
	/**
	 * Creates the PdfTemplate that will hold the total number of pages.
	 * 
	 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument( com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
	 */
	public void onOpenDocument(PdfWriter writer, Document document) {
		template = writer.getDirectContent().createTemplate(45, 50);
	}
	
	/**
	 * Fills out the total number of pages before the document is closed.
	 * 
	 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument( com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
	 */
	public void onCloseDocument(PdfWriter writer, Document document) {
		template.beginText();
		template.setFontAndSize(BOLDFONT.getBaseFont(), 8f);
		template.showText(Integer.toString(writer.getPageNumber()-1));
		template.endText();
	}
	
	/**
	 * @param phrase
	 * @return
	 */
	protected PdfPCell getPdfContentCell(String phrase) {
		PdfPCell cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(phrase));
		return cell;
	}
	
	/**
	 * @param phrase
	 * @return
	 */
	protected PdfPCell getPdfContentCell(String phrase, int alignment) {
		PdfPCell cell = new PdfPCell();
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPhrase(new Phrase(phrase));
		cell.setHorizontalAlignment(alignment); 
		return cell;
	}
	
	/**
	 * @param phrase
	 * @return
	 */
	protected PdfPCell getPdfHeaderCell(String phrase) {
		PdfPCell hCell = new PdfPCell(new Phrase(phrase, BOLDFONT));
		hCell.setBorder(PdfPCell.BOTTOM);
		return hCell;
	}
	
	/**
	 * @param phrase
	 * @return
	 */
	protected PdfPCell getPdfHeaderCell(String phrase, int alignment) {
		PdfPCell hCell = new PdfPCell(new Phrase(phrase, BOLDFONT));
		hCell.setBorder(PdfPCell.BOTTOM);
		hCell.setHorizontalAlignment(alignment); 
		return hCell;
	}
		
	/**
	 * @param sheet
	 * @return
	 */
	protected CellStyle getExcelColumnHeaderCellStyle(XSSFSheet sheet) {
		CellStyle cStyle = sheet.getWorkbook().createCellStyle();
		XSSFFont font = sheet.getWorkbook().createFont();
		font.setBold(true);
		cStyle.setFont(font);
		return cStyle;
	}
	
	/**
	 * @param sheet
	 * @return
	 */
	protected CellStyle getExcelRowCellStyle(XSSFSheet sheet) {
		CellStyle cStyle = sheet.getWorkbook().createCellStyle();
		//TODO: set cStyle customized properties, e.g. cell width etc.
		return cStyle;
	}
	
	/**
	 * @param sheet
	 * @return
	 */
	protected CellStyle getExcelCurrencyStyle(XSSFSheet sheet) {
		CellStyle cs = sheet.getWorkbook().createCellStyle();
		XSSFDataFormat df = sheet.getWorkbook().createDataFormat();
		cs.setDataFormat(df.getFormat("$#,#0.00"));
		return cs;
	}
	
	
	public ReportBaseSearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(ReportBaseSearchCriteria criteria) {
		this.criteria = criteria;
	}

	public abstract byte[] generateReport(List<?> reportData) throws Exception;
	protected abstract PdfPTable populatePdfReportTitle() throws Exception;
	protected abstract void populatePdfTableColumnHeaders(PdfPTable table) throws Exception;
	protected abstract void populateExcelColumnHeaders(Row row, XSSFSheet sheet) throws Exception;	
}
