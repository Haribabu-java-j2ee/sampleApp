package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.accessleveldefn.AccesslevelDefnBO;
import gov.utcourts.coriscommon.dataaccess.pagerolexref.PageroleXrefBO;
import gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO;
import gov.utcourts.coriscommon.util.SyncHelper;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.IntegerArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class AdminPageRoleServlet
*/
@WebServlet("/AdminPageRoleServlet")
public class AdminPageRoleServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -456983411554L;
	
	private static final String SEARCH_PAGE = "/jsp/adminPageRoles.jsp";
	private static final String MODIFY_PAGE = "/jsp/adminModifyPageRole.jsp";
	
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AdminPageRoleServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String page = MODIFY_PAGE; 
			String mode = URLEncryption.getParamAsString(request, "mode");
			String courtType = SyncHelper.DISTRICT; 	// SyncHelper will keep D and J in sync
			int pageId = URLEncryption.getParamAsInt(request, "pageId");
			int pageRoleXrefId = URLEncryption.getParamAsInt(request, "pageRoleXrefId");

			if ("find".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;
				List<PageroleXrefBO> results = new PageroleXrefBO(courtType)
				.includeTables(new RoleDefnBO(courtType).includeFields(RoleDefnBO.DESCRIPTION))
				.addForeignKey(PageroleXrefBO.ROLEID, RoleDefnBO.ROLEID)
				.where(PageroleXrefBO.PAGEID, pageId)
				.search(PageroleXrefBO.PAGEID, PageroleXrefBO.PAGEROLEXREFID, RoleDefnBO.DESCRIPTION);
				request.setAttribute("results", results);
			} else if ("delete".equalsIgnoreCase(mode)) {
				page = SEARCH_PAGE;
				new PageroleXrefBO(courtType).where(PageroleXrefBO.PAGEROLEXREFID, pageRoleXrefId).delete();
				request.setAttribute("results", new PageroleXrefBO(courtType).where(PageroleXrefBO.PAGEID, pageId).search());
				
				SyncHelper.syncPageRoleXref();
			} else if ("add".equalsIgnoreCase(mode)) {
				List<PageroleXrefBO> existingPageRolesXrefs = new PageroleXrefBO(courtType).where(PageroleXrefBO.PAGEID, pageId).search();
				IntegerArrayDescriptor existingRolesIds = new IntegerArrayDescriptor();
				if (existingPageRolesXrefs != null && existingPageRolesXrefs.size() > 0) {
					for (PageroleXrefBO pageRoleXref : existingPageRolesXrefs)
						existingRolesIds.add(pageRoleXref.getRoleId());
				}
				existingPageRolesXrefs = null;
				
				RoleDefnBO roles = new RoleDefnBO(courtType);
				if (existingRolesIds != null && existingRolesIds.size() > 0) {
					roles.where(new Expression(RoleDefnBO.ROLEID, Exp.NOT_IN, existingRolesIds));
				}
				List<RoleDefnBO> availableRoles = roles.search();	
				
				request.setAttribute("pageId", pageId);
				request.setAttribute("availableRoles", availableRoles);	
				request.setAttribute("availableAccessLevels", new AccesslevelDefnBO(courtType).orderBy(AccesslevelDefnBO.ACCESSLEVELID.desc()).search());
			} else if ("edit".equalsIgnoreCase(mode)) {
				request.setAttribute("availableRoles", new RoleDefnBO(courtType).orderBy(RoleDefnBO.DESCRIPTION.desc()).search());
				request.setAttribute("availableAccessLevels", new AccesslevelDefnBO(courtType).orderBy(AccesslevelDefnBO.ACCESSLEVELID.desc()).search());
				request.setAttribute("pageRole", new PageroleXrefBO(courtType).where(PageroleXrefBO.PAGEROLEXREFID, pageRoleXrefId).find());
			} else if ("update".equalsIgnoreCase(mode)) {
				int roleId = TextUtil.getParamAsInt(request, "roleId");
				int accessLevelId = TextUtil.getParamAsInt(request, "accessLevelId");
				
				if (pageRoleXrefId > 0)
					new PageroleXrefBO(courtType)
					.where(PageroleXrefBO.PAGEROLEXREFID, pageRoleXrefId)
					.setAccesslevelid(accessLevelId)
					.setRoleId(roleId)
					.update();
				else
					new PageroleXrefBO(courtType)
					.setPageid(pageId)
					.setRoleId(roleId)
					.setAccesslevelid(accessLevelId)
					.insert();
				
				SyncHelper.syncPageRoleXref();
			}

			request.setAttribute("courtType", courtType);
			getServletContext().getRequestDispatcher(page).forward(request, response);
			
			// cleanup
			page = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
