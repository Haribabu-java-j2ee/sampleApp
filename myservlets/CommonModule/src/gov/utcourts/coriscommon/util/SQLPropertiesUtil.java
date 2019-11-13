package gov.utcourts.coriscommon.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * A utility class which caches Properties objects containing the SQL for the Data Access Object layer.
 * 
 * @author Symatrix
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SQLPropertiesUtil {

    static final Logger logger = Logger.getLogger(SQLPropertiesUtil.class);
    private static HashMap propsFiles = new HashMap();

    /**
     * Constructor.
     */
    private SQLPropertiesUtil() {
    }

    /**
     * Retrieves the String value associated with <code>propertyName</code> from the property file for the class named <code>className</code>.
     * 
     * @param className
     *            The name of the class to which the property belongs
     * @param propertyName
     *            The name of the property value being requested
     * @return String associated with <code>propertyName</code> or <code>null</code> if none is found.
     */
    public static String getProperty(String className, String propertyName) throws java.lang.Exception {
        String property = null;
        Properties props = (Properties) propsFiles.get(className);
        if (props == null) {
            props = loadProperties(className);
        }
        if (props != null) {
            property = props.getProperty(propertyName);
        }
        return property;
    }

    /**
     * Loads the properties for <code>className</code> and stores it in the hash table.
     * 
     * @param className
     *            The name of the class for which we are loading the properties.
     * @return Properties file loaded for <code>className</code>.
     */
    public static Properties loadProperties(String className) throws Exception {
        String shortClassName = className.substring(className.lastIndexOf('.') + 1);
        ResourceBundle bundle = ResourceBundle.getBundle("gov.utcourts.coriscommon.properties." + shortClassName);
        Properties props = getPropertiesFromResourceBundle(bundle);
        propsFiles.put(className, props);
        return props;
    }

    /**
     * Converts <code>rb</code> into the corresponding Properties object.
     * 
     * @param rb
     *            The ResourceBundle to convert.
     * @return Properties object corresponding to <code>rb</code>.
     */
    public static Properties getPropertiesFromResourceBundle(ResourceBundle rb) {
        Properties retVal = null;
        if (rb != null) {
            retVal = new Properties();
            Enumeration enumeration = rb.getKeys();
            String key;
            String val;
            while (enumeration.hasMoreElements()) {
                key = (String) enumeration.nextElement();
                val = rb.getString(key);
                retVal.setProperty(key, val);
            }
        }
        return retVal;
    }
}
