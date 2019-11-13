package gov.utcourts.corisweb.pageregistry;

import gov.utcourts.coriscommon.dataaccess.accessleveldefn.AccesslevelDefnBO;
import gov.utcourts.coriscommon.dataaccess.application.ApplicationBO;
import gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO;
import gov.utcourts.coriscommon.dataaccess.pagerolexref.PageroleXrefBO;
import gov.utcourts.coriscommon.dataaccess.pagesystemxref.PagesystemXrefBO;
import gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO;
import gov.utcourts.coriscommon.dataaccess.systemareadefn.SystemareaDefnBO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PageRegistryUtil {
	
	public HashMap<String, List<PageAccess>> retrieveAllPageAccessWithRoles(String courtType) throws Exception {
		return retrievePageAccess(courtType);
	}
	
	@SuppressWarnings("unchecked")
	private HashMap<String, List<PageAccess>> retrievePageAccess(String courtType) throws Exception {
		HashMap<String, List<PageAccess>> pageAccessMap = new HashMap<String, List<PageAccess>>();
		
		try {
			// setup the search, and set the result container
			List<PageAccess> pageRegistry = new PageDefnBO(courtType).as(PageDefnBO.DESCRIPTION, "pageName").as(PageDefnBO.ISAVAILABLE, "pageAvailable")
			.includeTables(
				new PageroleXrefBO(courtType).orderBy(PageroleXrefBO.ACCESSLEVELID, DirectionType.DESC),
				new PagesystemXrefBO(courtType),
				new SystemareaDefnBO(courtType).as(SystemareaDefnBO.DESCRIPTION, "areaName"),
				new AccesslevelDefnBO(courtType).as(AccesslevelDefnBO.DESCRIPTION, "access"),
				new ApplicationBO(courtType).as(RoleDefnBO.DESCRIPTION, "roleNameName")
			)
			.addForeignKey(PageroleXrefBO.PAGEID, PageDefnBO.PAGEID)
			.addForeignKey(PagesystemXrefBO.PAGEID, PageDefnBO.PAGEID)
			.addForeignKey(SystemareaDefnBO.AREAID, PagesystemXrefBO.AREAID)
			.addForeignKey(AccesslevelDefnBO.ACCESSLEVELID, PageroleXrefBO.ACCESSLEVELID)
			.addForeignKey(RoleDefnBO.ROLEID, PageroleXrefBO.ROLEID)
			.where(new Expression(PageDefnBO.ISAVAILABLE, Exp.EQUALS, "Y"))
			.setOuter(
				new PagesystemXrefBO(courtType),
				new SystemareaDefnBO(courtType)
			)
			.setResultContainer(new PageAccess())
			.searchAndGetResults();
			
			if (pageRegistry != null && pageRegistry.size() > 0) {
				for (PageAccess access : (List<PageAccess>) pageRegistry) {
					if ("Y".equals(access.isPageAvailable())) {
						List<PageAccess> pal = (List<PageAccess>) pageAccessMap.get(access.getPageUrl());
						if (pal == null) {
							pal = new ArrayList<PageAccess>();
							pal.add(access);					
						} else {
							pal.add(access);					
						}
						pageAccessMap.put(access.getPageUrl(), pal);
					}
				}
			}
			pageRegistry = null;
			
			return pageAccessMap;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void main(String[] args) {
		try {
			DatabaseConnection.setUseJdbc();
			
			HashMap<String,List<PageAccess>> pageAccess = new PageRegistryUtil().retrieveAllPageAccessWithRoles("D");
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
	}
}
