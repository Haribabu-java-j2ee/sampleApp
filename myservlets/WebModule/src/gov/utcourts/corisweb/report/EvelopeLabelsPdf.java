package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.util.TextUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class EvelopeLabelsPdf extends PdfPageEventHelper {
	
	static Font font = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL);
	static final Font boldFont = FontFactory.getFont(FontFactory.HELVETICA, 11f, Font.BOLD);
	static final Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10f, Font.NORMAL);
	static int	fontsize = 12;
	
	public byte[] generateLabels(String labelSelected, AttorneyBO attorney, int startRow, int startCol, int numOfLbl) throws DocumentException, IOException {
		Document document = new Document(PageSize.LETTER);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// This is your new pdf doc
		PdfWriter writer = PdfWriter.getInstance(document, os);
		writer.setPageEvent(this);

		document.setPageSize(PageSize.LETTER);
		document.setMargins(18f, 18f, 30f, 30f);
		document.open();
		// Load it into a reader
		/*PdfReader reader = new PdfReader(template);*/

		// Get the page of the template you like
		/*PdfImportedPage page = writer.getImportedPage(reader, reader.getNumberOfPages());*/

		// Now you can add it to you report
//		PdfContentByte cb = writer.getDirectContent();
//		cb.addTemplate(page, 0, 0);
//		cb.createTemplate(100, 80);

		// Now do the usual addition of text atop the template
		PdfPTable table = populateLabels(labelSelected, attorney, startRow, startCol, numOfLbl);
		document.add(table);
		document.addTitle("Attorney Mailing Labels");
		document.close();
		return os.toByteArray();

	}
	
//	private static void closeDocument(PdfImportedPage page, Document document, PdfWriter writer) throws DocumentException, IOException {
//		populateTemplate(page, writer);
//		document.close();
//		
//	}
	
	private static void populateTemplate(PdfImportedPage page, PdfWriter writer) throws DocumentException, IOException {
		page.beginText();
		page.setFontAndSize(normalFont.getBaseFont(), fontsize);
		page.showText(Integer.toString(writer.getPageNumber()));
		page.endText();
	}

	private PdfPTable populateLabels(String labelTemplate, AttorneyBO attorney, int startRow, int startCol, int numOfLbl) {
		PdfPTable table = null;
		int colNum = 0;
		int rowNum = 0;
		if("3x10".equalsIgnoreCase(labelTemplate)){ //.contains("Avery5160")
			colNum = 3;
			rowNum = 10;
		}else if("2x10".equalsIgnoreCase(labelTemplate)){ //labelTemplate.contains("Avery5161")){
			colNum = 2;
			rowNum = 10;
		}else if("2x5".equalsIgnoreCase(labelTemplate)) { //labelTemplate.contains("Avery5163")){
			colNum = 2;
			rowNum = 5;
		}
		float[] widths = new float[colNum];
		for(int i=0; i<colNum; i++){
			widths[i] = 100.00f/colNum;
		}
		table = new PdfPTable(colNum);
		table.setWidthPercentage(100);
		try {
			table.setWidths(widths);
			int printed = 0;
			for(int r=0; r<rowNum; r++){
				for(int c=0; c<colNum; c++){
					
					if(r==startRow-1 && c==startCol-1){ //start to print attorney label
						addCell(table, attorney, labelTemplate);
						printed = 1;
					}else if(r >= startRow-1 && printed > 0 && printed < numOfLbl){ 
						addCell(table, attorney, labelTemplate);
						printed++;
					}else {
						addCell(table, null, labelTemplate);
					}
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}

	private void addCell(PdfPTable table, AttorneyBO attorney, String labelSize) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(PdfPCell.NO_BORDER);
		if ("2x5".equals(labelSize)){
			cell.setFixedHeight(144f);
		} else {
			cell.setFixedHeight(72f);
		}
		cell.setPaddingTop(5f);
		cell.setPaddingLeft(20f);
		
		if(attorney != null){
			addLebalParagraph(cell, attorney);
		}
		table.addCell(cell);
		
	}

	private void addLebalParagraph(PdfPCell cell, AttorneyBO attorney) {
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Paragraph.ALIGN_LEFT);
		paragraph.setFont(normalFont);
		paragraph.add(new Phrase(attorney.getFirstName() + " " + attorney.getLastName()));
		paragraph.add(Chunk.NEWLINE);
		if(!TextUtil.isEmpty(attorney.getOrganization())){
			paragraph.add(new Phrase(attorney.getOrganization()));
			paragraph.add(Chunk.NEWLINE);
		}
		paragraph.add(new Phrase(attorney.getAddress1()));
		paragraph.add(Chunk.NEWLINE);
		paragraph.add(new Phrase(attorney.getCity() + " " + attorney.getStateCode() + " " + attorney.getZipCode()));
		paragraph.add(Chunk.NEWLINE);
		cell.addElement(paragraph);
	}

	public byte[] generateEnvelope(String labelSelected, AttorneyBO attorney) throws DocumentException {
		// TODO Auto-generated method stub
		int xIndex = labelSelected.indexOf("x");
		float width = Float.parseFloat(labelSelected.substring(0,xIndex));
		float height = Float.parseFloat(labelSelected.substring(xIndex + 1));
		float envHeight = height*72;
		float envWidth = width*72;
		Rectangle envelope = null; 
		if("9x12".equals(labelSelected)){
			envelope = new Rectangle(envHeight,envWidth); //landscape for envelop flip the height and width
		}else {
			envelope = new Rectangle(envWidth,envHeight);
		}
		Document document = new Document(envelope, 10f, 10f, 100f, 0f);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// This is your new pdf doc
		PdfWriter writer = PdfWriter.getInstance(document, os);
		writer.setPageEvent(this);

		document.setMargins(envelope.getWidth()*0.4f, 0f, envelope.getHeight()*0.4f, 0f);
		
		document.setPageSize(envelope);
		document.open();
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setFont(normalFont);
		paragraph.add(new Phrase(attorney.getFirstName() + " " + attorney.getLastName()));
		paragraph.add(Chunk.NEWLINE);
		if(!TextUtil.isEmpty(attorney.getOrganization())){
			paragraph.add(new Phrase(attorney.getOrganization()));
			paragraph.add(Chunk.NEWLINE);
		}
		paragraph.add(new Phrase(attorney.getAddress1()));
		paragraph.add(Chunk.NEWLINE);
		paragraph.add(new Phrase(attorney.getCity() + " " + attorney.getStateCode() + " " + attorney.getZipCode()));
		paragraph.add(Chunk.NEWLINE);
		document.add(paragraph);
		/*PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100.0f);
		table.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
		
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setMinimumHeight(50);
		
		if(attorney != null){
			Paragraph para = new Paragraph("Test", font);
	        para.setLeading(0, 1);
	        cell.addElement(para);
//			addLebalParagraph(cell, attorney);
		}
		table.addCell(cell);
		document.add(table);*/
		document.addTitle("Attorney Mailing Envelope");
		document.close();
		
		return os.toByteArray();
	}


}
