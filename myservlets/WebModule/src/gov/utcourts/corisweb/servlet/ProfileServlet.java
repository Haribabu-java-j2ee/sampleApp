package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends BaseServlet {
	private static final long serialVersionUID = -63251142L;
	private static Logger logger = Logger.getLogger(ProfileServlet.class);
       
    private static String PROFILE_PAGE = "/jsp/profile.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
    }

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
		switch (mode) {
			case DEFAULT:
			case FIND: find(request, response);  	break;
		}
	}
	
	public void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getServletContext().getRequestDispatcher(PROFILE_PAGE).forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
