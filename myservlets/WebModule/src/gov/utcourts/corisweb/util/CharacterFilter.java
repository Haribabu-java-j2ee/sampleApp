package gov.utcourts.corisweb.util;

import gov.utcourts.notifications.util.TextUtil;

import org.apache.xml.utils.XMLChar;

public class CharacterFilter {

	public static String filter(String input) {
		
		if (!TextUtil.isEmpty(input)) {
			
			// Delete All ASCII Values < 32 or > 125
			input = input.replaceAll("[^\\x20-\\x7d]", "");
	
			// Delete the &
			//input = input.replaceAll("[\\x26]", "");
	
			// Delete the *
			//input = input.replaceAll("[\\x2a]", "");
	
			// Final Check
			for (int i = 0; i < input.length(); i++) {
			     if (!XMLChar.isValid(input.charAt(i))) {
			    	 input = input.replace(input.charAt(i), ' ');
			    	 System.out.println("Invalid XML character " + input.charAt(i));    	
			     }
			}
		}
		return input;
	}
	
}
