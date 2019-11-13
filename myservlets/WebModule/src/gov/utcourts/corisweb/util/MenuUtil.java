package gov.utcourts.corisweb.util;

import gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO;
import gov.utcourts.coriscommon.dataaccess.pagerolexref.PageroleXrefBO;
import gov.utcourts.coriscommon.dataaccess.pagesystemxref.PagesystemXrefBO;
import gov.utcourts.coriscommon.dataaccess.systemareadefn.SystemareaDefnBO;
import gov.utcourts.corisweb.util.RoleUtil.Role;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.IntegerArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.coriscommon.util.TextUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

public class MenuUtil {

	public static final String MENU_LEFT = "menuLeft";
	public static final String MENU_RIGHT = "menuRight";
	public static final int ROOT_MENU_ID = 0;
	
	/**
	 * create the menu content
	 */
	public static String createMenu(String courtType, List<Integer> roles) throws Exception {
		JSONObject menuObject = new JSONObject();
		if (roles != null && roles.size() > 0) { 
			HashMap<String, List<PageDefnBO>> cachedLeft = new HashMap<String, List<PageDefnBO>>();
			HashMap<String, List<PageDefnBO>> cachedRight = new HashMap<String, List<PageDefnBO>>();
			
			// left menu
			List<PageDefnBO> leftPages = getMenuResults(courtType, roles, MENU_LEFT, ROOT_MENU_ID, cachedLeft);
			if (leftPages != null && leftPages.size() > 0)
				menuObject.put(MENU_LEFT, getJsonMenuItems(leftPages, courtType, roles, MENU_LEFT, ROOT_MENU_ID, cachedLeft));     	
			
			// right menu
			List<PageDefnBO> rightPages = getMenuResults(courtType, roles, MENU_RIGHT, ROOT_MENU_ID, cachedRight);
			if (rightPages != null && rightPages.size() > 0) 
				menuObject.put(MENU_RIGHT, getJsonMenuItems(rightPages, courtType, roles, MENU_RIGHT, ROOT_MENU_ID, cachedRight));
			
			cachedLeft = null;
			cachedRight = null;
		}	
		return menuObject.toString();
	}
	
	/*
	 * recursive method to create the json menu tree
	 */
	private static JSONArray getJsonMenuItems(List<PageDefnBO> pages, String courtType, List<Integer> roles, String menuSide, int parentPageSystemXrefId, HashMap<String, List<PageDefnBO>> visited) throws Exception {
		JSONArray jsonMenuItems = new JSONArray();
		if (pages != null && pages.size() > 0) {
			for (PageDefnBO page : pages) {
				JSONObject menuItem = new JSONObject();
				
				menuItem.put("text", page.getDescription());
				menuItem.put("url", page.getPageurl());
				menuItem.put("type", TextUtil.print(page.getPagetype()));
			
				int pageSystemXrefId = (Integer) page.get(PagesystemXrefBO.PAGESYSTEMXREFID);
				List<PageDefnBO> childPages = getChildPages(courtType, roles, pageSystemXrefId, visited);
				if (childPages.size() > 0) {
					for (PageDefnBO childPage : childPages) 
						menuItem.put("subs", getJsonMenuItems(childPages, courtType, roles, menuSide, (Integer) childPage.get(PagesystemXrefBO.PAGESYSTEMXREFID), visited));
				} else {
					menuItem.put("subs", new JSONObject());
				}
				
				jsonMenuItems.add(menuItem);
			}
		}
		return jsonMenuItems;
	}
	
	private static List<PageDefnBO> getMenuResults(String courtType, List<Integer> roles, String menuSide, int parentPageSystemXrefId, HashMap<String, List<PageDefnBO>> cachedPages) throws Exception {
		List<PageDefnBO> retrievedPages = cachedPages.get("P" + parentPageSystemXrefId);
		if (retrievedPages != null && retrievedPages.size() > 0) {
			return retrievedPages;
		} else {
			
			/*
			 * get a list of pages that have roles associated with them
			 */
			List<PageDefnBO> pagesWithRoles = new PageDefnBO(courtType).as(PageDefnBO.DESCRIPTION, "pageDescription").where(PageDefnBO.ISAVAILABLE, "Y")
			.includeTables(
				new SystemareaDefnBO(courtType).as(SystemareaDefnBO.DESCRIPTION, "areaDescription").where(SystemareaDefnBO.DESCRIPTION, menuSide),
				new PageroleXrefBO(courtType).includeFields(PageroleXrefBO.PAGEROLEXREFID, PageroleXrefBO.ROLEID).where(PageroleXrefBO.PAGEROLEXREFID, Exp.IS_NOT_NULL).where(PageroleXrefBO.ROLEID, Exp.IN, new IntegerArrayDescriptor(roles)),
				
				(parentPageSystemXrefId == 0)
				
				? new PagesystemXrefBO(courtType)
					  .includeFields(PagesystemXrefBO.PAGESYSTEMXREFID, PagesystemXrefBO.PARENTPAGESYSTEMXREFID, PagesystemXrefBO.DISPLAYORDER)
					  .where(               																								// parentPageSystemXrefId = 0 or parentPageSystemXrefId is null
						Exp.LEFT_PARENTHESIS,
							PagesystemXrefBO.PARENTPAGESYSTEMXREFID, Exp.EQUALS, 0,  
							Exp.OR,
							PagesystemXrefBO.PARENTPAGESYSTEMXREFID, Exp.IS_NULL,
						Exp.RIGHT_PARENTHESIS
				      )
				      .orderBy(PagesystemXrefBO.DISPLAYORDER)
				      
				: new PagesystemXrefBO(courtType)
				      .includeFields(PagesystemXrefBO.PAGESYSTEMXREFID, PagesystemXrefBO.PARENTPAGESYSTEMXREFID, PagesystemXrefBO.DISPLAYORDER)
				      .where(PagesystemXrefBO.PARENTPAGESYSTEMXREFID, parentPageSystemXrefId)
				      .orderBy(PagesystemXrefBO.DISPLAYORDER)		// parentPageSystemXrefId > 0
			)
			.addForeignKey(PagesystemXrefBO.PAGEID, PageDefnBO.PAGEID)
			.addForeignKey(SystemareaDefnBO.AREAID, PagesystemXrefBO.AREAID)
			.addForeignKey(PageroleXrefBO.PAGEID, PageDefnBO.PAGEID)
			//.toString(1+2)
			.search();
			
			/*
			 * get a list of pages that DO NOT have roles associated with them
			 */
			List<PageDefnBO> pagesWithoutRoles = new PageDefnBO(courtType).as(PageDefnBO.DESCRIPTION, "pageDescription").where(PageDefnBO.ISAVAILABLE, "Y")
			.where(PageDefnBO.PAGEID, Exp.NOT_IN, 
				Exp.select(
					new PageDefnBO(courtType).setDistinct().includeFields(PageDefnBO.PAGEID)
					.where(PageDefnBO.ISAVAILABLE, "Y")
					.includeTables(
						new SystemareaDefnBO(courtType).includeFields(SystemareaDefnBO.NO_FIELDS).where(SystemareaDefnBO.DESCRIPTION, menuSide),
						new PageroleXrefBO(courtType).includeFields(PageroleXrefBO.NO_FIELDS).where(PageroleXrefBO.PAGEROLEXREFID, Exp.IS_NOT_NULL),
						
						(parentPageSystemXrefId == 0)
						
						? new PagesystemXrefBO(courtType)
						  .includeFields(PagesystemXrefBO.NO_FIELDS)
						  .where(               																								// parentPageSystemXrefId = 0 or parentPageSystemXrefId is null
							Exp.LEFT_PARENTHESIS,
								PagesystemXrefBO.PARENTPAGESYSTEMXREFID, Exp.EQUALS, 0,  
								Exp.OR,
								PagesystemXrefBO.PARENTPAGESYSTEMXREFID, Exp.IS_NULL,
							Exp.RIGHT_PARENTHESIS
					      )
					      
					    : new PagesystemXrefBO(courtType)
					      .includeFields(PagesystemXrefBO.NO_FIELDS)
					      .where(PagesystemXrefBO.PARENTPAGESYSTEMXREFID, parentPageSystemXrefId)
					)
					.addForeignKey(PagesystemXrefBO.PAGEID, PageDefnBO.PAGEID)
					.addForeignKey(SystemareaDefnBO.AREAID, PagesystemXrefBO.AREAID)
					.addForeignKey(PageroleXrefBO.PAGEID, PageDefnBO.PAGEID)
				)
			)
			.includeTables(
				new SystemareaDefnBO(courtType).as(SystemareaDefnBO.DESCRIPTION, "areaDescription").where(SystemareaDefnBO.DESCRIPTION, menuSide),
				new PageroleXrefBO(courtType).includeFields(PageroleXrefBO.PAGEROLEXREFID, PageroleXrefBO.ROLEID).setOuter().where(Exp.LEFT_PARENTHESIS, PageroleXrefBO.ROLEID, Exp.IN, new IntegerArrayDescriptor(roles), Exp.OR, PageroleXrefBO.ROLEID, Exp.IS_NULL, Exp.RIGHT_PARENTHESIS),
				
				(parentPageSystemXrefId == 0)
				
				? new PagesystemXrefBO(courtType)
					  .includeFields(PagesystemXrefBO.PAGESYSTEMXREFID, PagesystemXrefBO.PARENTPAGESYSTEMXREFID, PagesystemXrefBO.DISPLAYORDER)
					  .where(               																								// parentPageSystemXrefId = 0 or parentPageSystemXrefId is null
						Exp.LEFT_PARENTHESIS,
							PagesystemXrefBO.PARENTPAGESYSTEMXREFID, Exp.EQUALS, 0,  
							Exp.OR,
							PagesystemXrefBO.PARENTPAGESYSTEMXREFID, Exp.IS_NULL,
						Exp.RIGHT_PARENTHESIS
				      )
				      .orderBy(PagesystemXrefBO.DISPLAYORDER)
				      
				: new PagesystemXrefBO(courtType)
				      .includeFields(PagesystemXrefBO.PAGESYSTEMXREFID, PagesystemXrefBO.PARENTPAGESYSTEMXREFID, PagesystemXrefBO.DISPLAYORDER)
				      .where(PagesystemXrefBO.PARENTPAGESYSTEMXREFID, parentPageSystemXrefId)
				      .orderBy(PagesystemXrefBO.DISPLAYORDER)		// parentPageSystemXrefId > 0
			)
			.addForeignKey(PagesystemXrefBO.PAGEID, PageDefnBO.PAGEID)
			.addForeignKey(SystemareaDefnBO.AREAID, PagesystemXrefBO.AREAID)
			.addForeignKey(PageroleXrefBO.PAGEID, PageDefnBO.PAGEID)
			//.toString(1+2)
			.search();
			
			/*
			 * combine the results
			 */
			List<PageDefnBO> pages = null;
			if (pagesWithRoles.size() > 0)
				pages = new ArrayList<PageDefnBO>(pagesWithRoles);
			else 
				pages = new ArrayList<PageDefnBO>();
			
			if (pagesWithoutRoles.size() > 0)
				pages.addAll(new ArrayList<PageDefnBO>(pagesWithoutRoles));
			
			Collections.sort(pages, new Comparator<PageDefnBO>() {
     			@Override
     			public int compare(PageDefnBO s1, PageDefnBO s2) {
     				return ((BigDecimal) s1.get(PagesystemXrefBO.DISPLAYORDER)).compareTo((BigDecimal) s2.get(PagesystemXrefBO.DISPLAYORDER));
     			}
     		});
			
			List<PageDefnBO> uniquePages = new ArrayList<PageDefnBO>();
			List<Integer> alreadyAdded = new ArrayList<Integer>();
			for (PageDefnBO page : pages) {
				if (!alreadyAdded.contains(page.getPageid())) {
					alreadyAdded.add(page.getPageid());
					uniquePages.add(page);
				}
			}
			
			cachedPages.put("P" + parentPageSystemXrefId, uniquePages);
			
			return uniquePages;
		}
	}
	
	public static List<PageDefnBO> getChildPages(String courtType, List<Integer> roles, int pageSystemXrefId, HashMap<String, List<PageDefnBO>> cachedPages) throws Exception {
		List<PageDefnBO> retrievedPages = cachedPages.get("C" + pageSystemXrefId);
		if (retrievedPages != null) {
			return retrievedPages;
		} else {
			List<PageDefnBO> pages = new PageDefnBO(courtType).includeFields(PageDefnBO.ALL_FIELDS)
			.includeTables(
				new PagesystemXrefBO(courtType).includeFields(PagesystemXrefBO.PAGESYSTEMXREFID).orderBy(PagesystemXrefBO.DISPLAYORDER),
				new PageroleXrefBO(courtType).includeFields(PageroleXrefBO.PAGEROLEXREFID).setOuter().where(Exp.LEFT_PARENTHESIS, PageroleXrefBO.ROLEID, Exp.IN, new IntegerArrayDescriptor(roles), Exp.OR, PageroleXrefBO.ROLEID, Exp.IS_NULL, Exp.RIGHT_PARENTHESIS)
			)
			.addForeignKey(PageDefnBO.PAGEID, PagesystemXrefBO.PAGEID)
			.addForeignKey(PageDefnBO.PAGEID, PageroleXrefBO.PAGEID)
			.where(PagesystemXrefBO.PARENTPAGESYSTEMXREFID, pageSystemXrefId)
			.where(PageDefnBO.ISAVAILABLE, "Y")
			// .toString(1+2)
			.search();
			
			List<PageDefnBO> uniquePages = new ArrayList<PageDefnBO>();
			List<Integer> alreadyAdded = new ArrayList<Integer>();
			if (pages.size() > 0) {
				for (PageDefnBO page : pages) {
					if (!alreadyAdded.contains(page.getPageid())) {
						alreadyAdded.add(page.getPageid());
						uniquePages.add(page);
					}
				}
			}
			
			cachedPages.put("C" + pageSystemXrefId, uniquePages);
			
			return uniquePages;
		}
	}
		
	
	public static void main(String[] args) {
		try {
			DatabaseConnection.setUseJdbc();
			
			List<Integer> roles = new ArrayList<Integer>();
			roles.add(Role.UNASSIGNED.roleId);   // IT Admin
			
			JSONObject menuObject = new JSONObject();
			String courtType = "D";
			
			HashMap<String, List<PageDefnBO>> cachedPages = new HashMap<String, List<PageDefnBO>>();
			
//			List<PageDefnBO> leftPages = getMenuResults(courtType, roles, MENU_LEFT, ROOT_MENU_ID, cachedPages);
//			menuObject.put(MENU_LEFT, getJsonMenuItems(leftPages, courtType, roles, MENU_LEFT, ROOT_MENU_ID, cachedPages));
//			System.out.println(menuObject.toString());
			
			List<PageDefnBO> leftPages = getMenuResults(courtType, roles, MENU_RIGHT, ROOT_MENU_ID, cachedPages);
			for (PageDefnBO pageDefnBO : leftPages)
				System.out.println(pageDefnBO.getPageid());
			
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}
	}
}
