package gov.utcourts.corisweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.utcourts.coriscommon.util.TextUtil;

/**
* Servlet implementation class PSCHomeServlet
*/
@WebServlet("/PSCHomeServlet")
public class PSCHomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
     
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public PSCHomeServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			//initialize some things (or set them to null if there isn't an incoming value)
//			String displayHeader = TextUtil.getParamAsString(request, "displayHeader");
			
			//set the page
			String page = "/jsp/pscHome.jsp"; //default page
//			if("true".equals(displayHeader)){
//				page = "/jsp/pscHeader.jsp"; //display the header and top nav bar
//			}

			//display the selected page
			getServletContext().getRequestDispatcher(page).forward(request, response);
			
			//cleanup
			page = null;

		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
