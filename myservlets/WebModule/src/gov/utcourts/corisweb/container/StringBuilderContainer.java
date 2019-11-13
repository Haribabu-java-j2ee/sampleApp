package gov.utcourts.corisweb.container;

import gov.utcourts.courtscommon.util.TextUtil;

public class StringBuilderContainer {
	private StringBuilder stringBuilder = null;
	
	public StringBuilderContainer append(String message){
		if (TextUtil.isEmpty(message))
			return this;
		if (getStringBuilder().length() > 0)
			getStringBuilder().append("<br>");
		getStringBuilder().append(message);
		return this;
	}
	@Override
	public String toString(){
		return getStringBuilder().toString();
	}
	
	private StringBuilder getStringBuilder(){
		if (stringBuilder == null) 
			stringBuilder = new StringBuilder();
		return stringBuilder;
	}
}
