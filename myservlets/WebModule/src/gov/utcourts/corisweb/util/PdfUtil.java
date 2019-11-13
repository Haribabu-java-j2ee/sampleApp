package gov.utcourts.corisweb.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class PdfUtil {
	private static Logger logger = Logger.getLogger(PdfUtil.class);

	public enum AllowedMimeType {
		DOC	("application/msword"),
		PDF	("application/pdf"),
		WPD	("application/wordperfect"),
		TXT	("text/plain"),
		HTM	("text/html"),
		HTML("text/html"),
		XML	("text/html"),
		GIF	("image/gif"),
		TIF	("image/tiff"),
		TIFF("image/tiff"),
		JPEG("image/jpeg"),
		JPG	("image/jpeg"),
		BMP	("image/bmp"),
		RTF	("application/msword");

		private String mimeString;
		
		public String getMimeString() {
			return this.mimeString;
		}
		
		private void setMimeString(String mimeString) {
			this.mimeString = mimeString;
		}
		
		private AllowedMimeType(String mimeString) {
			this.setMimeString(mimeString);
		}
	}
	
	public static void showPDF(byte[] bytes, HttpServletResponse response) throws ServletException, IOException {
		show(AllowedMimeType.PDF, bytes, response);
	}
	
    public static void showPDFError(byte[] bytes, HttpServletResponse response) throws ServletException, IOException {
    	show(AllowedMimeType.HTML, bytes, response);
	}
    
    public static void show(AllowedMimeType mimeType, byte[] bytes, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream sout = response.getOutputStream();
		try {
			response.setContentType(mimeType.getMimeString());
			sout.write(bytes);
			sout.flush();
		} catch (IOException ioe) {
			logger.error("Exception in show function.", ioe);
			throw new ServletException("Unable to write to the response.");
		} finally {
			try {
				sout.close();
			} catch (IOException ioe) {
				// ignore
			}
		}	
	}
	
}
