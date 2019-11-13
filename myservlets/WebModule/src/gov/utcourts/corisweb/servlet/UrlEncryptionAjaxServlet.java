package gov.utcourts.corisweb.servlet;

import gov.utcourts.corisweb.util.URLEncryption;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UrlEncryptionAjaxServlet
 */
@WebServlet("/UrlEncryptionAjaxServlet")
public class UrlEncryptionAjaxServlet extends BaseServlet {
	private static final long serialVersionUID = 3812409609463478587L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UrlEncryptionAjaxServlet() {
		super();
	}
	
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servlet = request.getParameter("servlet");
		String params = request.getParameter("params");
		
		URLEncryption cryptor = new URLEncryption(servlet);
		String url = cryptor.urlEncrypt(URLDecoder.decode(params,"UTF-8"));
		cryptor = null;
		
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(url);
	}
}