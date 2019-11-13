package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.pagesystemxref.PagesystemXrefBO;
import gov.utcourts.coriscommon.dataaccess.systemareadefn.SystemareaDefnBO;
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
* Servlet implementation class AdminSystemAreaServlet
*/
@WebServlet("/AdminSystemAreaServlet")
public class AdminSystemAreaServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -21145412235L;
	
	private static final String SEARCH_PAGE = "/jsp/adminSystemAreas.jsp";
	private static final String MODIFY_PAGE = "/jsp/adminModifySystemArea.jsp";
	
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AdminSystemAreaServlet() {
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
			int areaId = TextUtil.getParamAsInt(request, "areaId");
			
			if ("find".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;
				request.setAttribute("results", new SystemareaDefnBO(courtType).search());
			} else if ("delete".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;
				new PagesystemXrefBO(courtType).where(PagesystemXrefBO.AREAID, areaId).delete();
				new SystemareaDefnBO(courtType).where(SystemareaDefnBO.AREAID, areaId).delete();
				request.setAttribute("results", new SystemareaDefnBO(courtType).search());	
				
				SyncHelper.syncSystemAreas();
			} else if ("add".equalsIgnoreCase(mode)) {
			} else if ("edit".equalsIgnoreCase(mode)) {
				request.setAttribute("area", new SystemareaDefnBO(courtType).where(SystemareaDefnBO.AREAID, areaId).find());
			} else if ("update".equalsIgnoreCase(mode)) {
				String description = TextUtil.getParamAsString(request, "description");
				if (areaId > 0)
					new SystemareaDefnBO(courtType).where(SystemareaDefnBO.AREAID, areaId).setDescription(description).update();
				else
					new SystemareaDefnBO(courtType).setDescription(description).insert();
				
				SyncHelper.syncSystemAreas();
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
