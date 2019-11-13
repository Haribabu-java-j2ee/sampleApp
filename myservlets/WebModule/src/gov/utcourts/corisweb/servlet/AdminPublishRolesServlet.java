package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.application.ApplicationBO;
import gov.utcourts.coriscommon.dataaccess.pagerolexref.PageroleXrefBO;
import gov.utcourts.coriscommon.dataaccess.roleapplicxref.RoleapplicXrefBO;
import gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.SyncHelper;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.DeleteDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.TransactionDescriptor;
import gov.utcourts.courtscommon.dataaccess.connection.ConnectionProperties;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dispatcher.BaseTransactionDispatcher;
import gov.utcourts.courtscommon.dispatcher.TransactionDispatcher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class AdminPublishRolesServlet
*/
@WebServlet("/AdminPublishRolesServlet")
public class AdminPublishRolesServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = -123546287954L;
	
	private static final String PUBLISH_PAGE = "/jsp/adminPublishRoles.jsp";
	
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AdminPublishRolesServlet() {
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
		int roleId = URLEncryption.getParamAsInteger(request, "roleId");

		StringBuilder error = new StringBuilder();
		try {
			if (roleId > 0) {
				request.setAttribute("fromServer", fromServer);
				request.setAttribute("toServer", toServer);
				
				if (!fromServer.equals(toServer))
					error.append(getPublishResults(fromServer, toServer, roleId, request));

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
		int roleId = URLEncryption.getParamAsInteger(request, "roleId");

		StringBuilder error = new StringBuilder();
		try {
			if (roleId > 0) {
				if (!fromServer.equals(toServer))
					error.append(deleteRole(toServer, roleId, request));

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
			
			// role / application xref records
			request.setAttribute(prefix + "XrefList", new RoleapplicXrefBO(courtType)
				.includeTables(
					new RoleDefnBO(courtType),
					new ApplicationBO(courtType)
				)
				.addForeignKey(RoleapplicXrefBO.ROLEID, RoleDefnBO.ROLEID)
				.addForeignKey(RoleapplicXrefBO.APPLICID, ApplicationBO.APPLICID)
				.setUseConnection(conn)
				.search(RoleapplicXrefBO.ALL_FIELDS, RoleDefnBO.DESCRIPTION, ApplicationBO.APPLICID, ApplicationBO.APPLICNAME, ApplicationBO.APPLICDESCR)
			);
			
			// role defn records
			request.setAttribute(prefix + "RoleDefnList", new RoleDefnBO(courtType).setUseConnection(conn).orderBy(RoleDefnBO.DESCRIPTION).search());
			
			conn.close();
			
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		return errorMessage;
	}
	
	public static String getPublishResults(String fromServer, String toServer, int roleId, HttpServletRequest request) {
		String errorMessage = "";
		try {
			String courtType = SyncHelper.DISTRICT; 	
			
			ConnectionProperties from_connection_properties = new ConnectionProperties(fromServer, "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml"); 
			Connection fromConn = DatabaseConnection.getConnection(from_connection_properties);
			
			ConnectionProperties to_connection_properties = new ConnectionProperties(toServer, "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml"); 
			Connection toConn = DatabaseConnection.getConnection(to_connection_properties);
			
			// check to see if the role exists
			RoleDefnBO toRoleDefnBO = new RoleDefnBO(courtType).where(RoleDefnBO.ROLEID, roleId).setUseConnection(toConn).setReturnNull(true).find();
			if (toRoleDefnBO == null) { 
				// copy and insert
				RoleDefnBO fromRoleDefnBO = new RoleDefnBO(courtType).where(RoleDefnBO.ROLEID, roleId).setUseConnection(fromConn).find();
				fromRoleDefnBO.setUseConnection(toConn).insert();
			} else {
				// update
				RoleDefnBO fromRoleDefnBO = new RoleDefnBO(courtType).where(RoleDefnBO.ROLEID, roleId).setUseConnection(fromConn).find();
				
				toRoleDefnBO
				.setDescription(fromRoleDefnBO.getDescription())
				.setUseConnection(toConn)
				.update();
			}
		
			// delete the role/applic xrefs and reinsert 
			new RoleapplicXrefBO(courtType).where(RoleapplicXrefBO.ROLEID, roleId).setUseConnection(toConn).delete();
			List<RoleapplicXrefBO> fromXrefs = new RoleapplicXrefBO(courtType).where(RoleapplicXrefBO.ROLEID, roleId).setUseConnection(fromConn).search();
			if (fromXrefs.size() > 0) {
				for (RoleapplicXrefBO xref : fromXrefs) {
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
	
	public static String deleteRole(String toServer, int roleId, HttpServletRequest request) {
		String errorMessage = "";
		try {
			String courtType = SyncHelper.DISTRICT; 	
			
			ConnectionProperties to_connection_properties = new ConnectionProperties(toServer, "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml"); 
			Connection toConn = DatabaseConnection.getConnection(to_connection_properties);
			
			// check to see if the role exists
			RoleDefnBO roleDefnBO = new RoleDefnBO(courtType).where(RoleDefnBO.ROLEID, roleId).setUseConnection(toConn).setReturnNull(true).find();
			if (roleDefnBO != null) {
		
				// delete role, pagerole_xref and roleapplicxref in a transaction
				TransactionDescriptor roleapplicXref = new DeleteDescriptor().setDeleteFromTable(new RoleapplicXrefBO(courtType).where(RoleapplicXrefBO.ROLEID, roleId));
				TransactionDescriptor pageRoleXref = new DeleteDescriptor().setDeleteFromTable(new PageroleXrefBO(courtType).where(PageroleXrefBO.ROLEID, roleId));
				TransactionDescriptor roleDefn = new DeleteDescriptor().setDeleteFromTable(new RoleDefnBO(courtType).where(RoleDefnBO.ROLEID, roleId));
				new TransactionDispatcher(pageRoleXref, roleapplicXref, roleDefn).setUseConnection(toConn).execute();
				
			}
			
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		return errorMessage;
	}
		
}
