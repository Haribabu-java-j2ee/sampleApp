package gov.utcourts.corisweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends BaseServlet {
	private static final long serialVersionUID = 6244784415573428466L;
	
	private static final String LOGOUT_PAGE = "/CorisWEB/logout.jsp";
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }
    
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null){
			session.invalidate();
		}
		response.sendRedirect(LOGOUT_PAGE);
	}
}
