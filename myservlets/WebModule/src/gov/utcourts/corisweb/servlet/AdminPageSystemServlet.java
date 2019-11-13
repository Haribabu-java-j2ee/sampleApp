package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO;
import gov.utcourts.coriscommon.dataaccess.pagesystemxref.PagesystemXrefBO;
import gov.utcourts.coriscommon.dataaccess.systemareadefn.SystemareaDefnBO;
import gov.utcourts.coriscommon.util.SyncHelper;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class AdminPageSystemServlet
*/
@WebServlet("/AdminPageSystemServlet")
public class AdminPageSystemServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -1211442552L;
	
	private static final String SEARCH_PAGE = "/jsp/adminPageSystems.jsp";
	private static final String MODIFY_PAGE = "/jsp/adminModifyPageSystem.jsp";
	
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AdminPageSystemServlet() {
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
			int pageSystemXrefId = TextUtil.getParamAsInt(request, "pageSystemXrefId");

			if ("find".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;
				List<PagesystemXrefBO> results = new PagesystemXrefBO(courtType)
				.includeTables(new SystemareaDefnBO(courtType).includeFields(SystemareaDefnBO.DESCRIPTION))
				.addForeignKey(PagesystemXrefBO.AREAID, SystemareaDefnBO.AREAID)
				.where(PagesystemXrefBO.PAGEID, pageId)
				.search(PagesystemXrefBO.ALL_FIELDS, SystemareaDefnBO.DESCRIPTION);
				
				// get parent menu description
				for (int i=0; i < results.size(); i++) {
					if (results.get(i).getParentpagesystemxrefid() > 0) {
						PagesystemXrefBO pagesystemXrefBO = new PagesystemXrefBO(courtType)
							.includeTables(new PageDefnBO(courtType).includeFields(PageDefnBO.DESCRIPTION))
							.addForeignKey(PagesystemXrefBO.PAGEID, PageDefnBO.PAGEID)
							.where(PagesystemXrefBO.PAGESYSTEMXREFID, results.get(i).getParentpagesystemxrefid());
						
						String description = "";
						List<PagesystemXrefBO> xrefs = pagesystemXrefBO.search();
						if (xrefs.size() > 0)
							description = (String) xrefs.get(0).get(PageDefnBO.DESCRIPTION);
						
						results.get(i).set("description", description);
					} else {
						results.get(i).set("description", "");
					}
				}

				request.setAttribute("results", results);
			} else if ("delete".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;
				new PagesystemXrefBO(courtType).where(PagesystemXrefBO.PAGESYSTEMXREFID, pageSystemXrefId).delete();
				request.setAttribute("results", new PagesystemXrefBO(courtType).where(PagesystemXrefBO.PAGEID, pageId).search());
				
				SyncHelper.syncPageSystemXref();
			} else if ("add".equalsIgnoreCase(mode)) {
				request.setAttribute("pageId", pageId);
				request.setAttribute("availableSystems", new SystemareaDefnBO(courtType).search());	
			} else if ("edit".equalsIgnoreCase(mode)) {
				request.setAttribute("availableSystems", new SystemareaDefnBO(courtType).search());
				PagesystemXrefBO pagesystemXrefBO = new PagesystemXrefBO(courtType).where(PagesystemXrefBO.PAGESYSTEMXREFID, pageSystemXrefId).find();
				request.setAttribute("pageSystem", pagesystemXrefBO);
				request.setAttribute("areaDescription", new SystemareaDefnBO(courtType).where(SystemareaDefnBO.AREAID, pagesystemXrefBO.getAreaid()).find().getDescription());
			} else if ("update".equalsIgnoreCase(mode)) {
				int areaId = TextUtil.getParamAsInt(request, "areaId");
				int parentPageSystemXrefId = TextUtil.getParamAsInt(request, "parentPageSysemXrefId");
				double displayOrder = TextUtil.getParamAsDouble(request, "displayOrder");
				
				if (pageSystemXrefId == 0) {
					PagesystemXrefBO pageSystemXrefBO = new PagesystemXrefBO(courtType)
					.setPageid(pageId)
					.setAreaid(areaId);
					
					if (parentPageSystemXrefId == 0)
						pageSystemXrefBO.setAsNull(PagesystemXrefBO.PARENTPAGESYSTEMXREFID);
					else
						pageSystemXrefBO.setParentpagesystemxrefid(parentPageSystemXrefId);
					
					pageSystemXrefBO.setDisplayorder(new BigDecimal(displayOrder))
					.insert();
				} else {
					PagesystemXrefBO pageSystemXrefBO = new PagesystemXrefBO(courtType)
					.where(PagesystemXrefBO.PAGESYSTEMXREFID, pageSystemXrefId);
									
					if (parentPageSystemXrefId == 0)
						pageSystemXrefBO.setAsNull(PagesystemXrefBO.PARENTPAGESYSTEMXREFID);
					else
						pageSystemXrefBO.setParentpagesystemxrefid(parentPageSystemXrefId);
					
					pageSystemXrefBO.setDisplayorder(new BigDecimal(displayOrder))
					.update();
				}
				
				SyncHelper.syncPageSystemXref();
			}

			request.setAttribute("availablePageSystemXrefs", new PagesystemXrefBO(courtType)
				.includeTables(
					new PageDefnBO(courtType).as(PageDefnBO.DESCRIPTION, "page_description"),
					new SystemareaDefnBO(courtType).as(SystemareaDefnBO.DESCRIPTION, "area_description").setOuter()
				)
				.addForeignKey(PagesystemXrefBO.PAGEID, PageDefnBO.PAGEID)
				.addForeignKey(PagesystemXrefBO.AREAID, SystemareaDefnBO.AREAID)
				.where(new Expression(PagesystemXrefBO.PAGESYSTEMXREFID, Exp.NOT_EQUALS, pageSystemXrefId))
				.orderBy(SystemareaDefnBO.DESCRIPTION, PageDefnBO.DESCRIPTION)
				.search(PagesystemXrefBO.PAGESYSTEMXREFID, PageDefnBO.PAGEID, PageDefnBO.DESCRIPTION, SystemareaDefnBO.DESCRIPTION)
			); 
			
			request.setAttribute("courtType", courtType);
			getServletContext().getRequestDispatcher(page).forward(request, response);
			
			// cleanup
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
