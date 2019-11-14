package gov.utcourts.notifications.dataaccess;

import gov.utcourts.notifications.util.TextUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public abstract class BaseVO implements Attributable {
	
	/*
	 * Method reflection
	 */
	public static void setPropertiesForObject(Object bo, Object vo) throws Exception {
		if (bo != null && vo != null) {
			Class class1 = bo.getClass();
			Class classVO = vo.getClass();
			Field[] fields = classVO.getDeclaredFields();
			for (int i=0; i < (fields != null ? fields.length : 0); i++) {
				try {
					String name = fields[i].getName();
					Class returnType = fields[i].getType();
					
					String prefix = (returnType.toString().contains("boolean")) ? "is" : "get";
					String char1 = name.substring(0, 1);
					name = name.substring(1);
					name = char1.toUpperCase() + name;
					
					Method getter = classVO.getMethod(prefix + name, (Class[])null);
					Class[] paramTypes = { returnType };
					Method setter = class1.getMethod("set" + name, paramTypes);
					Object obj = getter.invoke(vo, (Object[])null);
					Object[] params = { obj };
					setter.invoke(bo, params);
				} catch (Exception e) {
					// System.out.println("BaseVO.setPropertiesForObject -- method not on copied object -- " + e.getMessage());
				}
			}
		}
	}
	
	/*
	 * used for storing miscellaneous page objects
	 * 
	 */
	protected HashMap<String, String> attributes = new HashMap<String, String>();
	
	public String getAttribute(String key) {
		String returnValue = attributes.get(key.toLowerCase());
		return TextUtil.isEmpty(returnValue) ? "" : returnValue;
	}
	
	public int getAttributeAsInt(String key) {
		String returnValue = attributes.get(key.toLowerCase());
		return TextUtil.isEmpty(returnValue) ? 0 : Integer.parseInt(returnValue);
	}
	
	public BaseVO setAttribute(String key, String value) {
		attributes.put(key.toLowerCase(), ((value == null) ? "" : value));
		return this;
	}
	
	public HashMap<String, String> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}
}