package gov.utcourts.corisweb.pageregistry;

import gov.utcourts.corisweb.session.SessionVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class PagePermissions {
	
	private static Logger logger = Logger.getLogger(PagePermissions.class);
	
	private static HashMap<String, List<PageAccess>> cacheMap = new HashMap<String, List<PageAccess>>();
	private static String court = "";
	
	/**
	 * PagePermissions Constructor.
	 *
	 */
	private PagePermissions() {
	}

	/**
	 * This method builds a HashMap with all the available page urls with their
	 * roles and accesslevels.
	 * @throws Exception
	 */
	public synchronized static void buildCache(String courtType) throws Exception {
		cacheMap = new PageRegistryUtil().retrieveAllPageAccessWithRoles(courtType);
		court = courtType;		
	}
	
	/**
	 * retrieving a list of access roles for a specific pageurl.
	 * @param pageUrl
	 * @return List<SimplePageAccess>
	 */
	public static List<PageAccess> getPageAccessesByUrlPermissions(String pageUrl, String courtType) {
		List<PageAccess> retVal = null;
		try {
			if (!isValidCache(courtType)) {
				buildCache(courtType);
			}
			retVal = (List<PageAccess>) cacheMap.get(pageUrl);
			if (retVal == null) {
				retVal = new ArrayList<PageAccess>();
			}
		} catch (Exception e) {
			logger.error("Exception in getPageAccessesByUrlPermissions",e);
		}
		return retVal;
	}

	private static boolean isValidCache(String courtType) {
		if (cacheMap == null || cacheMap.size() == 0 && (courtType.equals(court))) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a Permission object representing the permissions the given user roles have for the given url.
	 * @param url the url for which security will be checked.
	 * @param userRoles the user roles which will be evaluated for security
	 * @return the permission object representing the types of access the user roles have.
	 */
	@SuppressWarnings("unchecked")
	public static Permission getPermissions(HttpServletRequest request, String url) {
		boolean read = false;
		boolean update = false;
		boolean access = false;
		boolean insert = false;
		boolean delete = false;
		
		HttpSession session = request.getSession(false);
		List<Integer> userRoles = (List<Integer>) session.getAttribute(SessionVariables.USER_ROLE);
		String courtType = (String) session.getAttribute(SessionVariables.COURT_TYPE);
		
		if (userRoles != null) {
			Iterator accesses = getPageAccessesByUrlPermissions(url, courtType).iterator();
			PageAccess pageAccess = null;
			while (accesses.hasNext()) {
				// check the roles for the page against the user roles
				pageAccess = (PageAccess) accesses.next();
				if (userRoles.contains(pageAccess.getApplicId()) && "Y".equals(pageAccess.isPageAvailable())) {  // the user is in the group
					access = true;
					// for now we will assume they have access, later will need to be determined by domain check for the level
					switch (pageAccess.getAccessLevelId()) {
						case 1 :
							read = true;
							break;
						case 2 :
							update = true;
							break;
						case 3 :
							update = true;
							insert = true;
							break;
						case 4 :
							update = true;
							insert = true;
							delete = true;
							break;
						default :
							read = update = delete = insert = false;
							break;
					}
				}
			}
		}
		return new Permission(read, update, access, insert, delete);
	}
}
