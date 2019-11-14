package gov.utcourts.notifications.common;

public enum NoticeType {
	TEXT	("T"), 
	EMAIL   ("E");
	
	public String type;
	
	private NoticeType(String type) {
		this.type = type;
	}
	
	public static NoticeType resolve(String type) {
		if ("E".equalsIgnoreCase(type)) {
			return EMAIL;
		} else if ("T".equalsIgnoreCase(type)) {
			return TEXT;
		} 		
		return null;
	}
}