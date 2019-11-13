package gov.utcourts.coriscommon.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CorisUtil {
	/**
	 * This method is used for setting the properties of a business object from 
	 * a value object.  This method will only work if the business object inherits 
	 * the value object and the value object's getters and setters follow the 
	 * standard of boolean getters are is and all methods use the same case, 
	 * excepting the first letter being upper case
	 * 
	 * UPDATE: Only process when bo and vo are not null. // Dave 2008.03.13
	 * 
	 * @param bo A Business object that inherits from the provided vo.
	 * @param vo A Value Object.
	 * @throws Exception
	 */
	public static void setPropertiesForObject(Object bo, Object vo) throws Exception {
		if (bo != null && vo != null) {
			Class class1 = bo.getClass();
			Class classVO = vo.getClass();
			// Only run this if the vo is not really a bo
			if (!class1.getName().equals(classVO.getName())) {
			  Field[] fields = classVO.getDeclaredFields();
			  Class[] c = new Class[0];
			  Object[] o = new Object[0];
			  for (int i = 0; i < fields.length; i++) {
				try {
				String name = fields[i].getName();
				String char1 = name.substring(0, 1);
				name = char1.toUpperCase() + name.substring(1);
				Class returnType = fields[i].getType();
				Method getter = null;
				if (returnType.getName().equals("boolean")) {
					getter = classVO.getMethod("is" + name, c);
				} else {
					getter = classVO.getMethod("get" + name, c);
				}
				Class[] paramTypes = { returnType };
				Method setter = class1.getMethod("set" + name, paramTypes);
				Object obj = getter.invoke(vo, o);
				Object[] params = { obj };
				setter.invoke(bo, params);
				}
				catch (Exception e) {
					// Do Nothing 
				}
			  }
			} else {
				bo = vo;
			}
		}
	}

}
