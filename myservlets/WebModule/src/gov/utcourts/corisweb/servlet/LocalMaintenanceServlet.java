package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.util.TextUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LocalMaintenanceServlet
 */
@WebServlet("/LocalMaintenanceServlet")
public class LocalMaintenanceServlet extends BaseServlet {
	private static final long serialVersionUID = -5211421101L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocalMaintenanceServlet() {
        super();
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	try {
			
			// initialize some things (or set them to null if there isn't an incoming value)
			String displayHeader = TextUtil.getParamAsString(request, "displayHeader");
			
			// set the page
			String page = "/jsp/localMaintenance.jsp"; //default page
			if ("true".equals(displayHeader)){
				page = "/jsp/localMaintenanceHeader.jsp";
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
