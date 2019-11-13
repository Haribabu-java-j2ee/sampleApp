package gov.utcourts.coriscommon.common;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.util.FileUtils;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * XMLConfig is a simple class that loads a global configuration file
 **/
public class XMLConfig {

    private static final Logger logger = Logger.getLogger(FileUtils.class);
    private static String propFileName = Constants.CORIS_COMMON_PROPERTIES;
    private static Properties properties;

    private XMLConfig() {
    }

    static {
        try {
            XMLProperties xmlLoader = new XMLProperties();
            properties = xmlLoader.getPropertiesObject(propFileName);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static void main(String[] args) {
        logger.info("Properties file: " + Constants.CORIS_COMMON_PROPERTIES);
        logger.info("Properties: " + properties);
    }
}
