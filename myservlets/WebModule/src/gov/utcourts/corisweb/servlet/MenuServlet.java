package gov.utcourts.corisweb.servlet;

import gov.utcourts.corisweb.util.UserUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends BaseServlet {
	private static final long serialVersionUID = 32657795412L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		request.setAttribute(UserUtil.CRITICAL_MESSAGE, UserUtil.getCriticalMessage(request));
    		request.setAttribute(UserUtil.USER_FULL_NAME, UserUtil.getCurrentUserFullName(request));
    		getServletContext().getRequestDispatcher("/jsp/menu.jsp").forward(request, response);
    	} catch (Exception e) {
    		throw new ServletException(e.getMessage());
    	}
    }

}
