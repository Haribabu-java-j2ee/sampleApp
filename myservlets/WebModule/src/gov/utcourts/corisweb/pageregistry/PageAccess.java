package gov.utcourts.corisweb.pageregistry;

public class PageAccess {
	public  String access;
	public int accessLevelId;
	public int pageId;
	public String pageName;
	public String pageUrl;
	public int applicId;
	public String applicName;
	public String areaName;
	private String isPageAvailable;
	
	public String isPageAvailable() {
		return isPageAvailable;
	}
	public void setPageAvailable(String isPageAvailable) {
		this.isPageAvailable = isPageAvailable;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public int getAccessLevelId() {
		return accessLevelId;
	}
	public void setAccessLevelId(int accessLevelId) {
		this.accessLevelId = accessLevelId;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public int getApplicId() {
		return applicId;
	}
	public void setApplicId(int applicId) {
		this.applicId = applicId;
	}
	public String getApplicName() {
		return applicName;
	}
	public void setApplicName(String applicName) {
		this.applicName = applicName;
	}
	public String getAreamName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
