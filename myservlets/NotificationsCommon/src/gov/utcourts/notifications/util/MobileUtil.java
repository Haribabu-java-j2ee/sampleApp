package gov.utcourts.notifications.util;

import gov.utcourts.notifications.constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

public class MobileUtil {
	private static Logger log = Logger.getLogger(MobileUtil.class);
	
	private static HashMap<String, String> carrierMap = null;
	static {
		carrierMap = new HashMap<String, String>();
		carrierMap.put("tmobile", 	"tmomail.net");
		carrierMap.put("virgin", 	"vmobl.com");
		carrierMap.put("cingular", 	"cingularme.com");
		carrierMap.put("sprint", 	"messaging.sprintpcs.com");
		carrierMap.put("verizon", 	"vtext.com");
		carrierMap.put("nextel", 	"messaging.nextel.com");
		carrierMap.put("att", 		"txt.att.net");
	}
			
	public static JSONObject getMobileAddresses(String mobileNumber) {
		JSONObject jsonObject = null;
		try {
			String url = Constants.SMS_VERIFICATION_URL + "?out=json&api=T&user=" + Constants.SMS_VERIFICATION_USER + "&pass=" + Constants.SMS_VERIFICATION_PASSWORD + "&p1=" + mobileNumber;
			URL u = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) u.openConnection();
			httpURLConnection.setRequestMethod("GET");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
	           stringBuilder.append(line + '\n');
			}
	       
			String jsonString = stringBuilder.toString();
	       
			JSONObject obj = JSONObject.parse(jsonString);
			JSONObject objResponse = (JSONObject) obj.get("response");
			JSONArray jsonArray = (JSONArray) objResponse.get("results");
			jsonObject = (JSONObject) jsonArray.get(0);
	       
			
			/*	String status = jsonObject.get("status").toString();
				String number = jsonObject.get("number").toString();
				String wless = jsonObject.get("wless").toString();
				String carrierName = jsonObject.get("carrier_name").toString();
				String carrier_id = jsonObject.get("carrier_id").toString();
				String mmsAddress = jsonObject.get("mms_address").toString();
			*/
			httpURLConnection = null;
			bufferedReader = null;
			stringBuilder = null;
			u = null;
			obj = null;
			objResponse = null;
			jsonArray = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	       
        return jsonObject;
	}
	
	public static String getMMSAddressPlain(String mobileNumber) throws IOException {
		String smsAddress = "";
		
		String url = Constants.SMS_VERIFICATION_URL + "?out=json&api=T&user=" + Constants.SMS_VERIFICATION_USER + "&pass=" + Constants.SMS_VERIFICATION_PASSWORD + "&p1=" + mobileNumber;
		URL u = new URL(url);
		HttpURLConnection httpURLConnection = (HttpURLConnection) u.openConnection();
		httpURLConnection.setRequestMethod("GET");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
           stringBuilder.append(line + '\n');
		}
       
		String jsonString = stringBuilder.toString();
       
		JSONObject obj = JSONObject.parse(jsonString);
		JSONObject objResponse = (JSONObject) obj.get("response");
		JSONArray jsonArray = (JSONArray) objResponse.get("results");
		JSONObject jsonObject = (JSONObject) jsonArray.get(0);
       
		smsAddress = jsonObject.get("sms_address").toString();
		
		httpURLConnection = null;
		bufferedReader = null;
		stringBuilder = null;
		u = null;
		obj = null;
		objResponse = null;
		jsonArray = null;
		jsonObject = null;
	
	       
        return smsAddress;
	}
	
	/*
	 * main method
	 */
	public static void main(String[] args) {
		try { 
			JSONObject jsonObject = MobileUtil.getMobileAddresses("8016356195");
			String smsAddress = jsonObject.get("sms_address").toString();
			System.out.println("smsAddress: " + smsAddress);
		} catch (Exception e) {
			System.out.println("MobileUtil error: " + e.getMessage());
		}
	}
}
