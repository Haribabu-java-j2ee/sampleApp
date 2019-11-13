package gov.utcourts.corisweb.servlet;

import gov.utcourts.courtscommon.constants.BaseConstants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public abstract class BaseServlet extends HttpServlet implements BaseConstants {
	private static final long serialVersionUID = 32451177;

	static Logger log = Logger.getLogger(BaseServlet.class);

	public BaseServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}  	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	protected abstract void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException;
	
	/**
	 * Convenience method used to forward a request from a servlet to another resource (servlet, JSP file, or HTML file).
	 * @param url String - The relative path to the resource.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void forward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{
		log.debug(".forward(url, request, response) URL: [" + url + "]");
		if (url == null || url.length() < 1) {
			throw new ServletException("Missing URL.");
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}
