package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO;
import gov.utcourts.coriscommon.dataaccess.pagerolexref.PageroleXrefBO;
import gov.utcourts.coriscommon.dataaccess.pagesystemxref.PagesystemXrefBO;
import gov.utcourts.coriscommon.util.SyncHelper;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class AdminPageServlet
*/
@WebServlet("/AdminPageServlet")
public class AdminPageServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -12425413985L;
	
	private static final String SEARCH_PAGE = "/jsp/adminPages.jsp";
	private static final String MODIFY_PAGE = "/jsp/adminModifyPage.jsp";
	private static final String PUBLISH_PAGE = "/AdminPublishPagesServlet";
	
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AdminPageServlet() {
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
			int pageId = TextUtil.getParamAsInt(request, "pageId");
			
			if ("find".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;

				// parent menus
				List<PageDefnBO> parentMenuDescriptions = new PageDefnBO(courtType)
				.includeTables(
					new PagesystemXrefBO(courtType)
				)
				.addForeignKey(PageDefnBO.PAGEID, PagesystemXrefBO.PAGEID)
				.search(PageDefnBO.DESCRIPTION, PagesystemXrefBO.PAGESYSTEMXREFID);
				
				HashMap<Integer, String> parentMenus = new HashMap<Integer, String>();
				for (PageDefnBO pageBO : parentMenuDescriptions) {
					parentMenus.put((Integer) pageBO.get(PagesystemXrefBO.PAGESYSTEMXREFID), (String) pageBO.get(PageDefnBO.DESCRIPTION));
				}
				request.setAttribute("parentMenus", parentMenus);
				parentMenus = null;
				
				// pages
				request.setAttribute("results", new PageDefnBO(courtType)
					.includeTables(
						new PagesystemXrefBO(courtType).setOuter()
					)
					.addForeignKey(PageDefnBO.PAGEID, PagesystemXrefBO.PAGEID)
					.orderBy(PagesystemXrefBO.PARENTPAGESYSTEMXREFID, PageDefnBO.DESCRIPTION)
					.search(PageDefnBO.ALL_FIELDS, PagesystemXrefBO.PARENTPAGESYSTEMXREFID)
				);
			} else if ("delete".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;
				new PagesystemXrefBO(courtType).where(PagesystemXrefBO.PAGEID, pageId).delete();
				new PageroleXrefBO(courtType).where(PageroleXrefBO.PAGEID, pageId).delete();
				new PageDefnBO(courtType).where(PageDefnBO.PAGEID, pageId).delete();
				request.setAttribute("results", new PageDefnBO(courtType).search());
				
				SyncHelper.syncPages();
			} else if ("add".equalsIgnoreCase(mode)) {
			} else if ("edit".equalsIgnoreCase(mode)) {
				request.setAttribute("page", new PageDefnBO(courtType).where(PageDefnBO.PAGEID, pageId).find());
			} else if ("update".equalsIgnoreCase(mode)) {
				String description = TextUtil.getParamAsString(request, "description");
				String pageUrl = TextUtil.getParamAsString(request, "pageUrl");
				String pageType = TextUtil.getParamAsString(request, "pageType");
				String isAvailable = TextUtil.getParamAsString(request, "isAvailable");
				if (pageId > 0)
					new PageDefnBO(courtType)
					.where(PageDefnBO.PAGEID, pageId)
					.setDescription(description)
					.setPageurl(pageUrl)
					.setPagetype(pageType)
					.setIsavailable(isAvailable)
					.setVersiondate(new Date())
					.update();
				else
					new PageDefnBO(courtType)
					.setDescription(description)
					.setPageurl(pageUrl)
					.setPagetype(pageType)
					.setIsavailable(isAvailable)
					.setVersiondate(new Date())
					.insert();
				
				SyncHelper.syncPages();
			} else if ("publish".equalsIgnoreCase(mode)) {
				page = PUBLISH_PAGE;
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
