package gov.utcourts.corisweb.util;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.axis.encoding.Base64;

public class Base64Encoding {

	public static HashMap<String, String> urlDecodeByte64(String encryptedParameters) {
		HashMap<String, String> decryptedParameters = new HashMap<String, String>(); 
		try {
			String decrypted = new String(Base64.decode(encryptedParameters));
			if (decrypted != null && decrypted.length() > 0) {
				StringTokenizer st = new StringTokenizer(decrypted, "&");
				if (st.hasMoreTokens()) {
					while (st.hasMoreTokens()) {
						decryptedParameters = URLEncryption.addPair(st.nextToken(), decryptedParameters);
					}
				} else {
					decryptedParameters = URLEncryption.addPair(decrypted, decryptedParameters);
				}
			}
		} catch (Exception e) {
			System.err.println(e);	  
		}		
		return decryptedParameters;
	}
	
	public static HashMap<String, String> parseQueryString(String queryString) {
		//Important to decode it later. Otherwise String "Hello & Kevin" will be truncated
		HashMap<String, String> map = new HashMap<String, String>(); 
		try {
			if (queryString != null && queryString.length() > 0) {
				StringTokenizer st = new StringTokenizer(queryString, "&");
				if (st.hasMoreTokens()) {
					while (st.hasMoreTokens()) {
						map = URLEncryption.addPair(URLDecoder.decode(st.nextToken(), "UTF-8") , map);
					}
				} else {
					String decoded = URLDecoder.decode(queryString, "UTF-8");
					map = URLEncryption.addPair(decoded, map);
				}
			}
		} catch (Exception e) {
			System.err.println(e);	  
		}		
		return map;
	}
}
