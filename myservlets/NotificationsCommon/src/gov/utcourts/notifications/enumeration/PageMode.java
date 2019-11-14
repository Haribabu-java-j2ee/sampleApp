package gov.utcourts.notifications.enumeration;

import gov.utcourts.notifications.util.TextUtil;

public enum PageMode {
	ADD     				("add"),
	EDIT    				("edit"),
	FIND    				("find"),
	DELETE  				("delete"),
	CLOSE   				("close"),
	NOTIFY      			("notify"),
	SUBSCRIBE				("subscribe"),
	REFRESH					("refresh"),
	EMAIL_VERIFICATION		("email_verification"),
	GENERATE_CODE			("generate_code"),
	EMAIL_VERIFIED			("emailVerified"),			// returned from CommonEmailVerifyWEB
	ENTER_CODE				("enter_code"),
	VERIFY_CODE				("verify_code"),
	MOBILE_VERIFICATION		("mobile_verification"),
	MODIFY_MOBILE			("modify_mobile"),
	MODIFY_EMAIL			("modify_email");
	
	PageMode (String value) {
		this.value = value;
	}

	public String value;
	
	/**
	 * Resolve the enumeration from a string value
	 * @param ofDefinition String 
	 * @return DState
	 */
	public static PageMode resolveEnumFromString(String requestAttributeString) {
		if (requestAttributeString != null) {
			if (requestAttributeString.toLowerCase().contains(ADD.value.toLowerCase())) 
				return ADD;
			else if (requestAttributeString.toLowerCase().contains(EDIT.value.toLowerCase()))
				return EDIT;
			else if (requestAttributeString.toLowerCase().contains(FIND.value.toLowerCase()))
				return FIND;
			else if (requestAttributeString.toLowerCase().contains(DELETE.value.toLowerCase()))
				return DELETE;
			else if (requestAttributeString.toLowerCase().contains(CLOSE.value.toLowerCase()))
				return CLOSE;
			else if (requestAttributeString.toLowerCase().contains(NOTIFY.value.toLowerCase()))
				return NOTIFY;
			else if (requestAttributeString.toLowerCase().contains(SUBSCRIBE.value.toLowerCase()))
				return SUBSCRIBE;
			else if (requestAttributeString.toLowerCase().contains(EMAIL_VERIFICATION.value.toLowerCase()))
				return EMAIL_VERIFICATION;
			else if (requestAttributeString.toLowerCase().contains(GENERATE_CODE.value.toLowerCase()))
				return GENERATE_CODE;
			else if (requestAttributeString.toLowerCase().contains(ENTER_CODE.value.toLowerCase()))
				return ENTER_CODE;
			else if (requestAttributeString.toLowerCase().contains(EMAIL_VERIFIED.value.toLowerCase()))
				return EMAIL_VERIFIED;
			else if (requestAttributeString.toLowerCase().contains(MOBILE_VERIFICATION.value.toLowerCase()))
				return MOBILE_VERIFICATION;
			else if (requestAttributeString.toLowerCase().contains(VERIFY_CODE.value.toLowerCase()))
				return VERIFY_CODE;
			else if (requestAttributeString.toLowerCase().contains(MODIFY_MOBILE.value.toLowerCase()))
				return MODIFY_MOBILE;
			else if (requestAttributeString.toLowerCase().contains(MODIFY_EMAIL.value.toLowerCase()))
				return MODIFY_EMAIL;
			else if (requestAttributeString.toLowerCase().contains(REFRESH.value.toLowerCase()))
				return REFRESH;
		}
	    return FIND;
	}
	
	/**
	 * Resolve the enumeration by checking to see if the id is present
	 * @param id String 
	 * @return PageMode
	 */
	public static PageMode determineMode(String id) {
		if (TextUtil.isEmpty(id))
			return ADD;
		else if (Integer.parseInt(id) > 0)
			return EDIT;
		else 
			return ADD;
	}
}
