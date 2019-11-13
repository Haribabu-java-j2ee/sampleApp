package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO;
import gov.utcourts.coriscommon.dataaccess.pagerolexref.PageroleXrefBO;
import gov.utcourts.coriscommon.dataaccess.pagesystemxref.PagesystemXrefBO;
import gov.utcourts.coriscommon.dataaccess.systemareadefn.SystemareaDefnBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.SyncHelper;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.DeleteDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.TransactionDescriptor;
import gov.utcourts.courtscommon.dataaccess.connection.ConnectionProperties;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dispatcher.TransactionDispatcher;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class AdminPublishPagesServlet
*/
@WebServlet("/AdminPublishPagesServlet")
public class AdminPublishPagesServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -9654421954L;
	
	private static final String PUBLISH_PAGE = "/jsp/adminPublishPages.jsp";
	
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AdminPublishPagesServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
			switch (mode) {
				case COMPARE:
					compare(request, response);
					break;
				case PUBLISH:
					publish(request, response);
					break;
				case DELETE:
					delete(request, response);
					break;
				default:
					SyncHelper.getServerLists(request);
					getServletContext().getRequestDispatcher(PUBLISH_PAGE).forward(request, response);
					break;
			}	
			mode = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void compare(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromServer = URLEncryption.getParamAsString(request, "fromServer");
		String toServer = URLEncryption.getParamAsString(request, "toServer");
		StringBuilder error = new StringBuilder();
		
		try {
			SyncHelper.getServerLists(request);
			
			request.setAttribute("fromServer", fromServer);
			request.setAttribute("toServer", toServer);

			error.append(getCompareResults("from", fromServer, request));
			error.append(getCompareResults("to", toServer, request));
		} catch (Exception e) {
			error.append(e.getMessage());
		}
		
		request.setAttribute(Constants.ERROR_MESSAGE, error.toString());
		getServletContext().getRequestDispatcher(PUBLISH_PAGE).forward(request, response);
	}
	
	public void publish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromServer = URLEncryption.getParamAsString(request, "fromServer");
		String toServer = URLEncryption.getParamAsString(request, "toServer");
		int pageId = URLEncryption.getParamAsInteger(request, "pageId");

		StringBuilder error = new StringBuilder();
		try {
			if (pageId > 0) {
				request.setAttribute("fromServer", fromServer);
				request.setAttribute("toServer", toServer);
				
				if (!fromServer.equals(toServer))
					error.append(getPublishResults(fromServer, toServer, pageId, request));

				// refresh compare
				SyncHelper.getServerLists(request);
				
				request.setAttribute("fromServer", fromServer);
				request.setAttribute("toServer", toServer);

				error.append(getCompareResults("from", fromServer, request));
				error.append(getCompareResults("to", toServer, request));
			} else {
				SyncHelper.getServerLists(request);
			}
		} catch (Exception e) {
			error.append(e.getMessage());
		}
		
		request.setAttribute(Constants.ERROR_MESSAGE, error.toString());
		getServletContext().getRequestDispatcher(PUBLISH_PAGE).forward(request, response);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromServer = URLEncryption.getParamAsString(request, "fromServer");
		String toServer = URLEncryption.getParamAsString(request, "toServer");
		int pageId = URLEncryption.getParamAsInteger(request, "pageId");

		StringBuilder error = new StringBuilder();
		try {
			if (pageId > 0) {
				if (!fromServer.equals(toServer))
					error.append(deletePage(toServer, pageId, request));

				// refresh compare
				SyncHelper.getServerLists(request);
				
				request.setAttribute("fromServer", fromServer);
				request.setAttribute("toServer", toServer);

				error.append(getCompareResults("from", fromServer, request));
				error.append(getCompareResults("to", toServer, request));
			} else {
				SyncHelper.getServerLists(request);
			}
		} catch (Exception e) {
			error.append(e.getMessage());
		}
		
		request.setAttribute(Constants.ERROR_MESSAGE, error.toString());
		getServletContext().getRequestDispatcher(PUBLISH_PAGE).forward(request, response);
	}
	
	public static String getCompareResults(String prefix, String dbServer, HttpServletRequest request) {
		String errorMessage = "";
		try {
			String courtType = SyncHelper.DISTRICT; 	
			
			ConnectionProperties connection_properties = new ConnectionProperties(dbServer, "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml"); 
			Connection conn = DatabaseConnection.getConnection(connection_properties);
			
			// parent menus
			List<PageDefnBO> parentMenuDescriptions = new PageDefnBO(courtType)
			.includeTables(
				new PagesystemXrefBO(courtType)
			)
			.addForeignKey(PageDefnBO.PAGEID, PagesystemXrefBO.PAGEID)
			.setUseConnection(conn)
			.search(PageDefnBO.DESCRIPTION, PageDefnBO.ISAVAILABLE, PagesystemXrefBO.PAGESYSTEMXREFID);
			
			HashMap<Integer, String> parentMenus = new HashMap<Integer, String>();
			for (PageDefnBO pageBO : parentMenuDescriptions) {
				parentMenus.put((Integer) pageBO.get(PagesystemXrefBO.PAGESYSTEMXREFID), (String) pageBO.get(PageDefnBO.DESCRIPTION));
			}
			request.setAttribute("parentMenus", parentMenus);
			parentMenus = null;
			
			// page defn records
			request.setAttribute(prefix + "PageDefnList", new PageDefnBO(courtType)
				.includeTables(
					new PagesystemXrefBO(courtType),
					new SystemareaDefnBO(courtType)
				)
				.addForeignKey(PageDefnBO.PAGEID, PagesystemXrefBO.PAGEID)
				.addForeignKey(SystemareaDefnBO.AREAID, PagesystemXrefBO.AREAID)
				.orderBy(PageDefnBO.PAGEID)
				.setUseConnection(conn)
				.search(PageDefnBO.ALL_FIELDS, PagesystemXrefBO.PARENTPAGESYSTEMXREFID, SystemareaDefnBO.DESCRIPTION.as("area_description"))
			);
			
			conn.close();
			
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		return errorMessage;
	}
	
	public static String getPublishResults(String fromServer, String toServer, int pageId, HttpServletRequest request) {
		String errorMessage = "";
		try {
			String courtType = SyncHelper.DISTRICT; 	
			
			ConnectionProperties from_connection_properties = new ConnectionProperties(fromServer, "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml"); 
			Connection fromConn = DatabaseConnection.getConnection(from_connection_properties);
			
			ConnectionProperties to_connection_properties = new ConnectionProperties(toServer, "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml"); 
			Connection toConn = DatabaseConnection.getConnection(to_connection_properties);
			
			// check to see if the page exists
			PageDefnBO toPageDefnBO = new PageDefnBO(courtType).where(PageDefnBO.PAGEID, pageId).setUseConnection(toConn).setReturnNull(true).find();
			if (toPageDefnBO == null) { 
				// copy and insert
				PageDefnBO fromPageDefnBO = new PageDefnBO(courtType).where(PageDefnBO.PAGEID, pageId).setUseConnection(fromConn).find();
				fromPageDefnBO.setUseConnection(toConn).insert();
			} else {
				// update
				PageDefnBO fromPageDefnBO = new PageDefnBO(courtType).where(PageDefnBO.PAGEID, pageId).setUseConnection(fromConn).find();
				
				toPageDefnBO
				.setIsavailable(fromPageDefnBO.getIsavailable())
				.setDescription(fromPageDefnBO.getDescription())
				.setUseConnection(toConn)
				.update();
			}
		
			// delete the page system xref and reinsert 
			new PagesystemXrefBO(courtType).where(PagesystemXrefBO.PAGEID, pageId).setUseConnection(toConn).delete();
			List<PagesystemXrefBO> fromSystemXrefs = new PagesystemXrefBO(courtType).where(PagesystemXrefBO.PAGEID, pageId).setUseConnection(fromConn).search();
			if (fromSystemXrefs.size() > 0) {
				for (PagesystemXrefBO xref : fromSystemXrefs) {
					xref.setUseConnection(toConn).insert();
				}
			}

			// delete the page role xref and reinsert 
			new PageroleXrefBO(courtType).where(PageroleXrefBO.PAGEID, pageId).setUseConnection(toConn).delete();
			List<PageroleXrefBO> fromRoleXrefs = new PageroleXrefBO(courtType).where(PageroleXrefBO.PAGEID, pageId).setUseConnection(fromConn).search();
			if (fromRoleXrefs.size() > 0) {
				for (PageroleXrefBO xref : fromRoleXrefs) {
					xref.setUseConnection(toConn).insert();
				}
			}

			fromConn.close();
			toConn.close();
			
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		return errorMessage;
	}
	
	public static String deletePage(String toServer, int pageId, HttpServletRequest request) {
		String errorMessage = "";
		try {
			String courtType = SyncHelper.DISTRICT; 	
			
			ConnectionProperties to_connection_properties = new ConnectionProperties(toServer, "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml"); 
			Connection toConn = DatabaseConnection.getConnection(to_connection_properties);
			
			// check to see if the page exists
			PageDefnBO pageDefnBO = new PageDefnBO(courtType).where(PageDefnBO.PAGEID, pageId).setUseConnection(toConn).setReturnNull(true).find();
			if (pageDefnBO != null) {
		
				// delete page, pagerole_xref and pagesystem_xref in a transaction
				TransactionDescriptor pagesystem_xref = new DeleteDescriptor().setDeleteFromTable(new PagesystemXrefBO(courtType).where(PagesystemXrefBO.PAGEID, pageId));
				TransactionDescriptor pageRoleXref = new DeleteDescriptor().setDeleteFromTable(new PageroleXrefBO(courtType).where(PageroleXrefBO.PAGEID, pageId));
				TransactionDescriptor pageDefn = new DeleteDescriptor().setDeleteFromTable(new PageDefnBO(courtType).where(PageDefnBO.PAGEID, pageId));
				new TransactionDispatcher(pagesystem_xref, pageRoleXref, pageDefn).setUseConnection(toConn).execute();
				
			}
			
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		return errorMessage;
	}
	
}
