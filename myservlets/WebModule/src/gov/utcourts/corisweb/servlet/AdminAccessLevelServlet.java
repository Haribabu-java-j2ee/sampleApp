package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.accessleveldefn.AccesslevelDefnBO;
import gov.utcourts.coriscommon.util.SyncHelper;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class AdminAccessLevelServlet
*/
@WebServlet("/AdminAccessLevelServlet")
public class AdminAccessLevelServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -6556442114L;
	
	private static final String SEARCH_PAGE = "/jsp/adminAccessLevels.jsp";
	private static final String MODIFY_PAGE = "/jsp/adminModifyAccessLevel.jsp";
	
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AdminAccessLevelServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String page = MODIFY_PAGE; 
			String mode = TextUtil.getParamAsString(request, "mode");
			String courtType = SyncHelper.DISTRICT; 	// SyncHelper will keep D and J in sync
			int accessLevelId = TextUtil.getParamAsInt(request, "accessLevelId");
			
			if ("find".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;
				request.setAttribute("results", new AccesslevelDefnBO(courtType).search());
			} else if ("delete".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;
				new AccesslevelDefnBO(courtType).where(AccesslevelDefnBO.ACCESSLEVELID, accessLevelId).delete();
				request.setAttribute("results", new AccesslevelDefnBO(courtType).search());	
				
				SyncHelper.syncAccessLevels();
			} else if ("add".equalsIgnoreCase(mode)) {
			} else if ("edit".equalsIgnoreCase(mode)) {
				request.setAttribute("accessLevel", new AccesslevelDefnBO(courtType).where(AccesslevelDefnBO.ACCESSLEVELID, accessLevelId).find());
			} else if ("update".equalsIgnoreCase(mode)) {
				String description = TextUtil.getParamAsString(request, "description");
				if (accessLevelId > 0)
					new AccesslevelDefnBO(courtType).where(AccesslevelDefnBO.ACCESSLEVELID, accessLevelId).setDescription(description).update();
				else
					new AccesslevelDefnBO(courtType).setDescription(description).insert();
				
				SyncHelper.syncAccessLevels();
			}

			request.setAttribute("courtType", courtType);
			getServletContext().getRequestDispatcher(page).forward(request, response);
			
			// cleanup
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
