package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.constants.ConstantsConnectionProperties;
import gov.utcourts.coriscommon.dataaccess.accessleveldefn.AccesslevelDefnBO;
import gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO;
import gov.utcourts.coriscommon.dataaccess.pagerolexref.PageroleXrefBO;
import gov.utcourts.coriscommon.dataaccess.pagesystemxref.PagesystemXrefBO;
import gov.utcourts.coriscommon.dataaccess.roleapplicxref.RoleapplicXrefBO;
import gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO;
import gov.utcourts.coriscommon.dataaccess.systemareadefn.SystemareaDefnBO;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class SyncHelper {
	
	public static final String DISTRICT = "D";
	public static final String JUSTICE = "J";

	public static void syncRoles() {
		try {
			List<RoleDefnBO> fromResults = new RoleDefnBO(DISTRICT).search();
			List<RoleDefnBO> toResults = new RoleDefnBO(JUSTICE).search();
			
			// insert / update
			for (int i=0; i < fromResults.size(); i++) {
				boolean found = false;
				
				RoleDefnBO from = fromResults.get(i);
				RoleDefnBO to = null;
				
				for (int j=0; j < toResults.size(); j++) {
					to = toResults.get(j);
					if (to.getRoleId() == from.getRoleId()) {
						found = true;
						break;
					}
				}
				 
				if (found) {
					// update
					if (!from.toString().equalsIgnoreCase(to.toString())) {
						RoleDefnBO bo =	new RoleDefnBO(JUSTICE);
						bo.setPropertyList(from.getPropertyList());
						bo.update();
					}
				} else {
					// insert
					RoleDefnBO bo =	new RoleDefnBO(JUSTICE);
					bo.setPropertyList(from.getPropertyList());
					bo.insert();
				}
			}
			
			fromResults = new RoleDefnBO(DISTRICT).search();
			toResults = new RoleDefnBO(JUSTICE).search();
			
			// delete
			if (fromResults.size() < toResults.size()) {
				
				for (int i=0; i < toResults.size(); i++) {
					RoleDefnBO from = null;
					RoleDefnBO to = toResults.get(i);
					
					int notFound = to.getRoleId();
					
					for (int j=0; j < fromResults.size(); j++) {
						from = fromResults.get(j);
						if (to.getRoleId() == from.getRoleId()) {
							notFound = 0;
							break;
						} 
					}

					if (notFound > 0) {
						new PageroleXrefBO(JUSTICE).where(PageroleXrefBO.ROLEID, notFound).delete();
						new RoleapplicXrefBO(JUSTICE).where(RoleapplicXrefBO.ROLEID, notFound).delete();
						new RoleDefnBO(JUSTICE).where(RoleDefnBO.ROLEID, notFound).delete();
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println(".syncRoles SyncHelper Exception: " + e.getMessage());
		}
	}
	
	public static void syncAccessLevels() {
		try {
			List<AccesslevelDefnBO> fromResults = new AccesslevelDefnBO(DISTRICT).search();
			List<AccesslevelDefnBO> toResults = new AccesslevelDefnBO(JUSTICE).search();
			
			// insert / update
			for (int i=0; i < fromResults.size(); i++) {
				boolean found = false;
				
				AccesslevelDefnBO from = fromResults.get(i);
				AccesslevelDefnBO to = null;
				
				for (int j=0; j < toResults.size(); j++) {
					to = toResults.get(j);
					if (to.getAccesslevelid() == from.getAccesslevelid()) {
						found = true;
						break;
					}
				}
				 
				if (found) {
					// update
					if (!from.toString().equalsIgnoreCase(to.toString())) {
						AccesslevelDefnBO bo = new AccesslevelDefnBO(JUSTICE);
						bo.setPropertyList(from.getPropertyList());
						bo.update();
					}
				} else {
					// insert
					AccesslevelDefnBO bo = new AccesslevelDefnBO(JUSTICE);
					bo.setPropertyList(from.getPropertyList());
					bo.insert();
				}
			}
			
			fromResults = new AccesslevelDefnBO(DISTRICT).search();
			toResults = new AccesslevelDefnBO(JUSTICE).search();
			
			// delete
			if (fromResults.size() < toResults.size()) {
				
				for (int i=0; i < toResults.size(); i++) {
					AccesslevelDefnBO from = null;
					AccesslevelDefnBO to = toResults.get(i);
					
					int notFound = to.getAccesslevelid();
					
					for (int j=0; j < fromResults.size(); j++) {
						from = fromResults.get(j);
						if (to.getAccesslevelid() == from.getAccesslevelid()) {
							notFound = 0;
							break;
						} 
					}

					if (notFound > 0) {
						new PageroleXrefBO(JUSTICE).where(AccesslevelDefnBO.ACCESSLEVELID, notFound).delete();
						new AccesslevelDefnBO(JUSTICE).where(AccesslevelDefnBO.ACCESSLEVELID, notFound).delete();
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println(".syncAccessLevels SyncHelper Exception: " + e.getMessage());
		}
	}
	
	public static void syncSystemAreas() {
		try {
			List<SystemareaDefnBO> fromResults = new SystemareaDefnBO(DISTRICT).search();
			List<SystemareaDefnBO> toResults = new SystemareaDefnBO(JUSTICE).search();
			
			// insert / update
			for (int i=0; i < fromResults.size(); i++) {
				boolean found = false;
				
				SystemareaDefnBO from = fromResults.get(i);
				SystemareaDefnBO to = null;
				
				for (int j=0; j < toResults.size(); j++) {
					to = toResults.get(j);
					if (to.getAreaid() == from.getAreaid()) {
						found = true;
						break;
					}
				}
				 
				if (found) {
					// update
					if (!from.toString().equalsIgnoreCase(to.toString())) {
						SystemareaDefnBO bo = new SystemareaDefnBO(JUSTICE);
						bo.setPropertyList(from.getPropertyList());
						bo.update();
					}
				} else {
					// insert
					SystemareaDefnBO bo = new SystemareaDefnBO(JUSTICE);
					bo.setPropertyList(from.getPropertyList());
					bo.insert();
				}
			}
			
			fromResults = new SystemareaDefnBO(DISTRICT).search();
			toResults = new SystemareaDefnBO(JUSTICE).search();
			
			// delete
			if (fromResults.size() < toResults.size()) {
				
				for (int i=0; i < toResults.size(); i++) {
					SystemareaDefnBO from = null;
					SystemareaDefnBO to = toResults.get(i);
					
					int notFound = to.getAreaid();
					
					for (int j=0; j < fromResults.size(); j++) {
						from = fromResults.get(j);
						if (to.getAreaid() == from.getAreaid()) {
							notFound = 0;
							break;
						} 
					}

					if (notFound > 0) {
						new PagesystemXrefBO(JUSTICE).where(PagesystemXrefBO.AREAID, notFound).delete();
						new SystemareaDefnBO(JUSTICE).where(SystemareaDefnBO.AREAID, notFound).delete();
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println(".syncSystemAreas SyncHelper Exception: " + e.getMessage());
		}
	}
	
	public static void syncPages() {
		try {
			List<PageDefnBO> fromResults = new PageDefnBO(DISTRICT).search();
			List<PageDefnBO> toResults = new PageDefnBO(JUSTICE).search();
			
			// insert / update
			for (int i=0; i < fromResults.size(); i++) {
				boolean found = false;
				
				PageDefnBO from = fromResults.get(i);
				PageDefnBO to = null;
				
				for (int j=0; j < toResults.size(); j++) {
					to = toResults.get(j);
					if (to.getPageid() == from.getPageid()) {
						found = true;
						break;
					}
				}
				 
				if (found) {
					// update
					if (!from.toString().equalsIgnoreCase(to.toString())) {
						PageDefnBO bo =	new PageDefnBO(JUSTICE);
						bo.setPropertyList(from.getPropertyList());
						bo.update();
					}
				} else {
					// insert
					PageDefnBO bo =	new PageDefnBO(JUSTICE);
					bo.setPropertyList(from.getPropertyList());
					bo.insert();
				}
			}
			
			fromResults = new PageDefnBO(DISTRICT).search();
			toResults = new PageDefnBO(JUSTICE).search();
			
			// delete
			if (fromResults.size() < toResults.size()) {
				
				for (int i=0; i < toResults.size(); i++) {
					PageDefnBO from = null;
					PageDefnBO to = toResults.get(i);
					
					int notFound = to.getPageid();
					
					for (int j=0; j < fromResults.size(); j++) {
						from = fromResults.get(j);
						if (to.getPageid() == from.getPageid()) {
							notFound = 0;
							break;
						} 
					}

					if (notFound > 0) {
						new PagesystemXrefBO(JUSTICE).where(PagesystemXrefBO.PAGEID, notFound).delete();
						new PageroleXrefBO(JUSTICE).where(PageroleXrefBO.PAGEID, notFound).delete();
						new PageDefnBO(JUSTICE).where(PageDefnBO.PAGEID, notFound).delete();
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println(".syncPages SyncHelper Exception: " + e.getMessage());
		}
	}
	
	public static void syncRoleApplicXref() {
		try {
			List<RoleapplicXrefBO> fromResults = new RoleapplicXrefBO(DISTRICT).search();
			
			// delete
			for (RoleapplicXrefBO from : fromResults) {
				new RoleapplicXrefBO(JUSTICE).where(RoleapplicXrefBO.ROLEID, from.getRoleId()).delete();
			}
			
			// insert
			for (RoleapplicXrefBO from : fromResults) {
				RoleapplicXrefBO bo = new RoleapplicXrefBO(JUSTICE);
				bo.setPropertyList(from.getPropertyList());
				bo.insert();
			}

		} catch (Exception e) {
			System.out.println(".syncRoleApplicXref SyncHelper Exception: " + e.getMessage());
		}
	}
	
	public static void syncPageRoleXref() {
		try {
			List<PageroleXrefBO> fromResults = new PageroleXrefBO(DISTRICT).search();
			
			// delete
			for (PageroleXrefBO from : fromResults) {
				new PageroleXrefBO(JUSTICE).where(PageroleXrefBO.ROLEID, from.getRoleId()).delete();
			}
			
			// insert
			for (PageroleXrefBO from : fromResults) {
				PageroleXrefBO bo = new PageroleXrefBO(JUSTICE);
				bo.setPropertyList(from.getPropertyList());
				bo.insert();
			}

		} catch (Exception e) {
			System.out.println(".syncPageRoleXref SyncHelper Exception: " + e.getMessage());
		}
	}
	
	public static void syncPageSystemXref() {
		try {
			List<PagesystemXrefBO> fromResults = new PagesystemXrefBO(DISTRICT).search();
			
			// delete
			for (PagesystemXrefBO from : fromResults) {
				new PagesystemXrefBO(JUSTICE).where(PagesystemXrefBO.PAGEID, from.getPageid()).delete();
			}
			
			// insert
			for (PagesystemXrefBO from : fromResults) {
				PagesystemXrefBO bo = new PagesystemXrefBO(JUSTICE);
				bo.setPropertyList(from.getPropertyList());
				bo.insert();
			}
		} catch (Exception e) {
			System.out.println(".syncPageSystemXref SyncHelper Exception: " + e.getMessage());
		}
	}
	
	public static void getServerLists(HttpServletRequest request) throws ServletException, IOException {
		try {
			
			// server list
			List<String> dbServers = new ArrayList<String>();
			dbServers.add("coris_district_db");
			dbServers.add("coris_justice_db");
			dbServers.add("coris_test_db");
			dbServers.add(ConstantsConnectionProperties.CORIS_DEVELOPMENT_DB.getDatasourceName());
			dbServers.add(ConstantsConnectionProperties.CORIS_PRODUCTION_DISTRICT_DB.getDatasourceName());
			dbServers.add(ConstantsConnectionProperties.CORIS_PRODUCTION_JUSTICE_DB.getDatasourceName());
			dbServers.add(ConstantsConnectionProperties.CORIS_TEST_DISTRICT_DB.getDatasourceName());
			dbServers.add(ConstantsConnectionProperties.CORIS_TEST_JUSTICE_DB.getDatasourceName());
			dbServers.add(ConstantsConnectionProperties.CORIS_TRAINING_DB.getDatasourceName());
			dbServers.add(ConstantsConnectionProperties.CORIS_VERIFY_DB.getDatasourceName());
			request.setAttribute("dbServers", dbServers);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static String insertPageBlankRows(int row, List<PageDefnBO> list1, List<PageDefnBO> list2) {
		StringBuilder blankRow = new StringBuilder();
		blankRow.append("<tr><td>");
		blankRow.append("<table>");
		blankRow.append("	<tr>");
		blankRow.append("		<td width=\"325px\" nowrap>");
		blankRow.append("		</td>");
		blankRow.append("		<td align=\"right\" height=\"24\">");
		blankRow.append("		</td>");
		blankRow.append("	</tr>");
		blankRow.append("</table>");
		blankRow.append("</td></tr>");

		StringBuilder sb = new StringBuilder();
		if (list1 == null || list2 == null) {
			// do nothing
		} else {
			if (row < list1.size()) {
				int pageId = list1.get(row).getPageid();
				boolean rowFound = false;
				for (int j = 0; j < list2.size(); j++) {
					if (list2.get(j).getPageid() == pageId) {
						rowFound = true;
						break;
					}
				}
				if (!rowFound)
					sb.append(blankRow.toString());
			}
		}
		
		blankRow = null;
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		DatabaseConnection.setUseJdbc();
		syncRoles();
	}
	
}
