var ROLES = 'Roles';
var SYSTEM_AREAS = 'SystemAreas';
var PAGES = "Pages";
var ROLES = "Roles";
var ACCESS_LEVELS = "AccessLevels";
var PAGE_ROLES = "PageRoles";
var PAGE_AREAS = 'PageAreas';
var PAGE_SYSTEMS = "PageSystems";

var pagesPopup = null;
function showPages(courtType) {
	if (pagesPopup != null)
		pagesPopup.close();

	var title = "Define " + PAGES;
	var cornId = "modal" + PAGES + "Primary";
	var url = "/CorisWEB/AdminPageServlet?mode=find&courtType=" + courtType;
			
	pagesPopup = appUX.pop.modeless(cornId, title, url, 800, 500, appUX.pop.styles.PRIMARY);
}

var modifyPagePopup = null;
function modifyPage(mode, title, pageId, courtType) {
	if (modifyPagePopup != null) 
		modifyPagePopup.close();
		
	var cornId = "modal" + PAGES + "Secondary";
	var url = "/CorisWEB/AdminPageServlet?mode=" + mode + "&courtType=" + courtType + "&pageId=" + pageId; 
	
	modifyPagePopup = appUX.pop.modeless(cornId, title, url, 500, 225, appUX.pop.styles.SECONDARY);
}

var publishPagePopup = null;
function publishPage(mode, title, pageId, courtType) {
	if (publishPagePopup != null)
		publishPagePopup.close();
		
	var cornId = "modal" + ROLES + "Secondary";
	var url = "/CorisWEB/AdminPageServlet?mode=" + mode + "&courtType=" + courtType + "&pageId=" + pageId; 
	
	publishPagePopup = appUX.pop.modeless(cornId, title, url, 800, 650, appUX.pop.styles.SECONDARY);
}

var rolePopup = null;
function showRoles(courtType) {
	if (rolePopup != null)
		rolePopup.close();

	var title = "Define Roles";
	var cornId = "modal" + ROLES + "Primary";
	var url = "/CorisWEB/AdminRoleServlet?mode=find&courtType=" + courtType;
			
	rolePopup = appUX.pop.modeless(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var modifyRolePopup = null;
function modifyRole(mode, title, roleId, courtType) {
	if (modifyRolePopup != null)
		modifyRolePopup.close();
		
	var cornId = "modal" + ROLES + "Secondary";
	var url = "/CorisWEB/AdminRoleServlet?mode=" + mode + "&courtType=" + courtType + "&roleId=" + roleId; 
	
	modifyRolePopup = appUX.pop.modeless(cornId, title, url, 500, 175, appUX.pop.styles.SECONDARY);
}

var publishRolePopup = null;
function publishRole(mode, title, roleId, courtType) {
	if (publishRolePopup != null)
		publishRolePopup.close();
		
	var cornId = "modal" + ROLES + "Secondary";
	var url = "/CorisWEB/AdminRoleServlet?mode=" + mode + "&courtType=" + courtType + "&roleId=" + roleId; 
	
	publishRolePopup = appUX.pop.modeless(cornId, title, url, 800, 600, appUX.pop.styles.SECONDARY);
}

var accessLevelsPopup = null;
function showAccessLevels(courtType) {
	if (accessLevelsPopup != null)
		accessLevelsPopup.close();
	
	var title = "Define Access Levels";
	var cornId = "modal" + ACCESS_LEVELS + "Primary";
	var url = "/CorisWEB/AdminAccessLevelServlet?mode=find&courtType=" + courtType;
	
	accessLevelsPopup = appUX.pop.modeless(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var modifyAccessLevelPopup = null;
function modifyAccessLevel(mode, title, accessLevelId, courtType) {
	if (modifyAccessLevelPopup != null)
		modifyAccessLevelPopup.close();
		
	var cornId = "modal" + PAGES + "Secondary";
	var url = "/CorisWEB/AdminAccessLevelServlet?mode=" + mode + "&courtType=" + courtType + "&accessLevelId=" + accessLevelId; 
	
	modifyAccessLevelPopup = appUX.pop.modeless(cornId, title, url, 500, 100, appUX.pop.styles.SECONDARY);
}

var pageRolesPopup = null;
function showPageRoles(title, courtType, pageId) {
	if (pageRolesPopup != null)
		pageRolesPopup.close();
	
	var cornId = "modal" + PAGE_ROLES + "Primary";
	var url = "/CorisWEB/AdminPageRoleServlet?mode=find&courtType=" + courtType + "&pageId=" + pageId; 
	
	pageRolesPopup = appUX.pop.modeless(cornId, title, url, 500, 300, appUX.pop.styles.PRIMARY);
}

var modifyPageRolePopup = null;
function modifyPageRole(mode, title, courtType, pageId, pageRoleXrefId) {
	if (modifyPageRolePopup != null)
		modifyPageRolePopup.close();
		
	var cornId = "modal" + PAGE_ROLES + "Secondary";
	var url = "/CorisWEB/AdminPageRoleServlet?mode=" + mode + "&courtType=" + courtType + "&pageId=" + pageId + "&pageRoleXrefId=" + pageRoleXrefId; 
	
	modifyPageRolePopup = appUX.pop.modeless(cornId, title, url, 460, 150, appUX.pop.styles.SECONDARY);
}

var systemAreasPopup = null;
function showSystemAreas(courtType) {
	if (systemAreasPopup != null)
		systemAreasPopup.close();
	
	var title = "Define System Areas";
	var cornId = "modal" + SYSTEM_AREAS + "Primary";
	var url = "/CorisWEB/AdminSystemAreaServlet?mode=find&courtType=" + courtType;
	
	systemAreasPopup = appUX.pop.modeless(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var modifySystemAreaPopup = null;
function modifySystemArea(mode, title, areaId, courtType) {
	if (modifySystemAreaPopup != null)
		modifySystemAreaPopup.close();
		
	var cornId = "modal" + SYSTEM_AREAS + "Secondary";
	var url = "/CorisWEB/AdminSystemAreaServlet?mode=" + mode + "&courtType=" + courtType + "&areaId=" + areaId;
	
	modifySystemAreaPopup = appUX.pop.modeless(cornId, title, url, 500, 100, appUX.pop.styles.SECONDARY);
}

var pageSystemPopup = null;
function showPageSystems(title, courtType, pageId) {
	if (pageSystemPopup != null)
		pageSystemPopup.close();
	
	var cornId = "modal" + PAGE_SYSTEMS + "Primary";
	var url = "/CorisWEB/AdminPageSystemServlet?mode=find&courtType=" + courtType + "&pageId=" + pageId; 
	
	pageSystemPopup = appUX.pop.modeless(cornId, title, url, 800, 300, appUX.pop.styles.PRIMARY);
}

var modifyPageSystemPopup = null;
function modifyPageSystem(mode, title, courtType, pageId, pageSystemXrefId) {
	if (modifyPageSystemPopup != null)
		modifyPageSystemPopup.close();
		
	var cornId = "modal" + PAGE_SYSTEMS + "Secondary";
	var url = "/CorisWEB/AdminPageSystemServlet?mode=" + mode + "&courtType=" + courtType + "&pageId=" + pageId + "&pageSystemXrefId=" + pageSystemXrefId; 
	
	modifyPageSystemPopup = appUX.pop.modeless(cornId, title, url, 460, 175, appUX.pop.styles.SECONDARY);
}

// start ---------------------------- court profiles 
var PROFILES = 'Profiles';
var DEFAULTS = 'Defaults';

var profilesPopup = null;
function showProfiles() {
	if (profilesPopup != null)
		profilesPopup.close();

	var title = "Court Profile Maintenance";
	var cornId = "modal" + PROFILES + "Primary";
	var url = "/CorisWEB/AdminCourtProfileServlet?mode=find";
			
	profilesPopup = appUX.pop.modeless(cornId, title, url, 500, 500, appUX.pop.styles.PRIMARY);
}

var modifyProfilePopup = null;
function modifyCourtProfile(mode, title, locnCode, courtType) {
	if (modifyProfilePopup != null)
		modifyProfilePopup.close();
		
	var cornId = "modal" + PROFILES + "Secondary";
	var url = "/CorisWEB/AdminCourtProfileServlet?mode=" + mode + "&courtType=" + courtType + "&locnCode=" + locnCode; 
	
	modifyProfilePopup = appUX.pop.modeless(cornId, title, url, 500, 675, appUX.pop.styles.SECONDARY);
}

var courtDefaultsPopup = null;
function showCourtDefaults(locnCode, courtType) {
	if (courtDefaultsPopup != null)
		courtDefaultsPopup.close();

	var title = "Court Defaults";
	var cornId = "modal" + DEFAULTS + "Primary";
	var url = "/CorisWEB/AdminCourtProfileServlet?mode=defaults&locnCode=" + locnCode + "&courtType=" + courtType;
			
	courtDefaultsPopup = appUX.pop.modeless(cornId, title, url, 450, 550, appUX.pop.styles.PRIMARY);
}
// end ------------------------------ court profiles
