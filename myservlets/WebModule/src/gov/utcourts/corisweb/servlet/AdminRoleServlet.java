package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.dataaccess.application.ApplicationBO;
import gov.utcourts.coriscommon.dataaccess.pagerolexref.PageroleXrefBO;
import gov.utcourts.coriscommon.dataaccess.roleapplicxref.RoleapplicXrefBO;
import gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO;
import gov.utcourts.courtscommon.dispatcher.TransactionDispatcher;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.SyncHelper;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.DeleteDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.TransactionDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dispatcher.BaseTransactionDispatcher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class AdminRoleServlet
*/
@WebServlet("/AdminRoleServlet")
public class AdminRoleServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -212115563L;
	
	private static final String ROLES_PAGE = "/jsp/adminRoles.jsp";
	private static final String ROLES_RESULTS_PAGE = "/jsp/adminRoleResults.jsp";
	private static final String ROLES_ADD_EDIT_PAGE = "/jsp/adminXrefRoleAddEdit.jsp";
	private static final String ROLES_MODIFY_PAGE = "/jsp/adminModifyRole.jsp";
	private static final String PUBLISH_PAGE = "/AdminPublishRolesServlet";
	
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AdminRoleServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String courtType = SyncHelper.DISTRICT; 	// SyncHelper will keep D and J in sync
			request.setAttribute("courtType", courtType);
			
			PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
			switch (mode) {
				case ADD:
					add(request, response);
					break;
				case UPDATE:
					update(request, response);
					break;
				case SAVE:
					save(request, response);
					break;
				case EDIT:
					edit(request, response);
					break;
				case DELETE:
					delete(request, response);
					break;
				case DISPLAYRESULTS:
					displayResults(request, response);
					break;
				case PUBLISH:
					publish(request, response);
					break;
				default:
					getServletContext().getRequestDispatcher(ROLES_PAGE).forward(request, response);
					break;
			}	
			mode = null;
			
			// cleanup
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getLists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String courtType = SyncHelper.DISTRICT; 	// SyncHelper will keep D and J in sync
			
			// role / application xref records
			request.setAttribute("xrefList", new RoleapplicXrefBO(courtType)
				.includeTables(
					new RoleDefnBO(courtType),
					new ApplicationBO(courtType)
				)
				.addForeignKey(RoleapplicXrefBO.ROLEID, RoleDefnBO.ROLEID)
				.addForeignKey(RoleapplicXrefBO.APPLICID, ApplicationBO.APPLICID)
				.search(RoleapplicXrefBO.ALL_FIELDS, RoleDefnBO.DESCRIPTION, ApplicationBO.APPLICNAME, ApplicationBO.APPLICDESCR)
			);
			
			// role defn records
			request.setAttribute("roleDefnList", new RoleDefnBO(courtType).orderBy(RoleDefnBO.DESCRIPTION).search());
			
			// application records
			request.setAttribute("applicationList", new ApplicationBO(courtType).where(new Expression(ApplicationBO.APPLICNAME, Exp.NOT_EQUALS, "DELETE THIS")).orderBy(ApplicationBO.APPLICNAME).search(ApplicationBO.APPLICID, ApplicationBO.APPLICNAME));
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void displayResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getLists(request, response);
			getServletContext().getRequestDispatcher(ROLES_RESULTS_PAGE).forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getServletContext().getRequestDispatcher(ROLES_MODIFY_PAGE).forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String courtType = SyncHelper.DISTRICT; 	// SyncHelper will keep D and J in sync
			int roleId = URLEncryption.getParamAsInt(request, "roleId");
			
			// delete role, pagerole_xref and roleapplicxref in a transaction
			TransactionDescriptor roleapplicXref = new DeleteDescriptor().setDeleteFromTable(new RoleapplicXrefBO(courtType).where(RoleapplicXrefBO.ROLEID, roleId));
			TransactionDescriptor pageRoleXref = new DeleteDescriptor().setDeleteFromTable(new PageroleXrefBO(courtType).where(PageroleXrefBO.ROLEID, roleId));
			TransactionDescriptor roleDefn = new DeleteDescriptor().setDeleteFromTable(new RoleDefnBO(courtType).where(RoleDefnBO.ROLEID, roleId));
			new TransactionDispatcher(pageRoleXref, roleapplicXref, roleDefn).execute();
			
			SyncHelper.syncRoles();
			
			request.setAttribute("results", new RoleDefnBO(courtType).search());	
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String courtType = SyncHelper.DISTRICT; 	// SyncHelper will keep D and J in sync
			int roleId = URLEncryption.getParamAsInt(request, "roleId");
			int overrideRoleId = URLEncryption.getParamAsInt(request, "overrideRoleId");
			String description = TextUtil.getParamAsString(request, "description");
			if (!TextUtil.isEmpty(description)) {
				if (roleId > 0) {
					new RoleDefnBO(courtType).where(RoleDefnBO.ROLEID, roleId).setDescription(description).update();
				} else {
					if (overrideRoleId > 0)
						new RoleDefnBO(courtType).setRoleId(overrideRoleId).setDescription(description).insert();
					else 
						new RoleDefnBO(courtType).setDescription(description).insert();
				}
				SyncHelper.syncRoles();
			} else {
				request.setAttribute("role", new RoleDefnBO(courtType).where(RoleDefnBO.ROLEID, roleId).find());
				getServletContext().getRequestDispatcher(ROLES_MODIFY_PAGE).forward(request, response);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String courtType = SyncHelper.DISTRICT; 	// SyncHelper will keep D and J in sync
			int roleId = URLEncryption.getParamAsInt(request, "roleId");
			request.setAttribute("roleDescription", new RoleDefnBO(courtType).where(RoleDefnBO.ROLEID, roleId).find().getDescription());
			
			getLists(request, response);
			getServletContext().getRequestDispatcher(ROLES_ADD_EDIT_PAGE).forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void publish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			getServletContext().getRequestDispatcher(PUBLISH_PAGE).forward(request, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String courtType = SyncHelper.DISTRICT; 	// SyncHelper will keep D and J in sync
			int roleId = TextUtil.getParamAsInt(request, "roleId");
			if (roleId > 0) {
				// delete all records for this particular roleId
				new RoleapplicXrefBO(courtType).where(RoleapplicXrefBO.ROLEID, roleId).delete();
				
				List<ApplicationBO> applicationList = new ApplicationBO(courtType).search();

				// insert the checked boxes
				for (ApplicationBO applicationBO : applicationList) {
					if ("on".equals(TextUtil.getParamAsString(request, "application-" + applicationBO.getApplicId()))) {
						new RoleapplicXrefBO(courtType)
						.setApplicId(applicationBO.getApplicId())
						.setRoleId(roleId)
						.insert();
					}
				}
				
				applicationList = null;
				
				SyncHelper.syncRoleApplicXref();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
