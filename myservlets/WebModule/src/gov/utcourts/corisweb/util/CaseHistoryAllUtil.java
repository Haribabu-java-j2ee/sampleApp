package gov.utcourts.corisweb.util;

public class CaseHistoryAllUtil {
	
	public static String getEncryptedUrl(String caseNum, String courtType, String locnCode, String logName) {
		URLEncryption urlCaseHistoryAllCryptor = new URLEncryption("/CorisWEB/CaseSummarySearchLookupServlet");
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("funcId=all&mode=getPDF");
		urlBuilder.append("&logName=").append(logName);
		urlBuilder.append("&courtType=").append(courtType);
		urlBuilder.append("&caseNum=").append(caseNum);
		urlBuilder.append("&locnCode=").append(locnCode);
		return urlCaseHistoryAllCryptor.urlEncrypt(urlBuilder.toString()); 
	}
	
}
