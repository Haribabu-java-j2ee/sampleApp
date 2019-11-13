package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class AdminServlet
*/
@WebServlet("/AdminServlet")
public class AdminServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -42124888796L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AdminServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			// initialize some things (or set them to null if there isn't an incoming value)
			String displayHeader = TextUtil.getParamAsString(request, "displayHeader");
			
			//set the page
			String page = "/jsp/admin.jsp"; // default page
			if ("true".equals(displayHeader)) {
				page = "/jsp/adminHeader.jsp"; // display the header and top nav bar
			}

			// display the selected page
			getServletContext().getRequestDispatcher(page).forward(request, response);
			
			// cleanup
			page = null;
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
