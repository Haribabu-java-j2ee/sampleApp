package gov.utcourts.notifications.dataaccess;

import gov.utcourts.notifications.common.SmartException;
import gov.utcourts.notifications.common.SystemException;
import gov.utcourts.notifications.common.XMLConfig;

import java.sql.*;
import java.util.Properties;

/**
 * JDBCConnect provides connectivity to our WebSphere Commerce Suite
 * database through the JDBC Oracle thin driver.  It uses properties
 * controlled by IDXConfig to obtain the database URL, Username
 * and Password.
 **/
public class JDBCConnect {
	public static Connection getJDBCConnectCommonDB() throws SmartException {
		String url = XMLConfig.getProperty("PROPERTY.data_sources.common_ds.JDBCURL");
		String user = XMLConfig.getProperty("PROPERTY.data_sources.common_ds.username");
		String pass = XMLConfig.getProperty("PROPERTY.data_sources.common_ds.password");
		Connection conn = null;
		try {
			Properties connectionProperties = new Properties();
			connectionProperties.setProperty("user",user);
			connectionProperties.setProperty("password",pass);
			connectionProperties.setProperty("informixLockModeWait","100");
			connectionProperties.setProperty("ifxIFX_AUTOFREE","true");
			connectionProperties.setProperty("STMT_CACHE", "1");
			Class.forName("com.informix.jdbc.IfxDriver");
			conn = DriverManager.getConnection(url, connectionProperties);
		} catch (ClassNotFoundException e) {
			throw new SystemException(e, "The jdbc informix drivers are not in the CLASSPATH.");
		} catch (SQLException e) {
			throw new SystemException(e, "Could not connect to database.  The database appears to be off-line.");
		}
		return conn;
	}
	
	public static Connection getJDBCConnection(int systemId) throws SmartException {
		String url = XMLConfig.getProperty("PROPERTY.data_sources.system" + systemId + ".JDBCURL");
		String user = XMLConfig.getProperty("PROPERTY.data_sources.system" + systemId + ".username");
		String pass = XMLConfig.getProperty("PROPERTY.data_sources.system" + systemId + ".password");
		Connection conn = null;
		try {
			Properties connectionProperties = new Properties();
			connectionProperties.setProperty("user",user);
			connectionProperties.setProperty("password",pass);
			connectionProperties.setProperty("informixLockModeWait","100");
			connectionProperties.setProperty("ifxIFX_AUTOFREE","true");
			connectionProperties.setProperty("STMT_CACHE", "1");
			Class.forName("com.informix.jdbc.IfxDriver");
			conn = DriverManager.getConnection(url, connectionProperties);
		} catch (ClassNotFoundException e) {
			throw new SystemException(e, "The jdbc informix drivers are not in the CLASSPATH.");
		} catch (SQLException e) {
			throw new SystemException(e, "Could not connect to database.  The database appears to be off-line.");
		}
		return conn;
	}
	public static Connection getJDBCConnectionByCourt(int systemId, String court) throws SmartException {
		String url = XMLConfig.getProperty("PROPERTY.data_sources.system" + systemId + ".court"+court+".JDBCURL");
		String user = XMLConfig.getProperty("PROPERTY.data_sources.system" + systemId + ".court"+court+".username");
		String pass = XMLConfig.getProperty("PROPERTY.data_sources.system" + systemId + ".court"+court+".password");
		Connection conn = null;
		try {
			Properties connectionProperties = new Properties();
			connectionProperties.setProperty("user",user);
			connectionProperties.setProperty("password",pass);
			connectionProperties.setProperty("informixLockModeWait","100");
			connectionProperties.setProperty("ifxIFX_AUTOFREE","true");
			connectionProperties.setProperty("STMT_CACHE", "1");
			Class.forName("com.informix.jdbc.IfxDriver");
			conn = DriverManager.getConnection(url, connectionProperties);
		} catch (ClassNotFoundException e) {
			throw new SystemException(e, "The jdbc informix drivers are not in the CLASSPATH.");
		} catch (SQLException e) {
			throw new SystemException(e, "Could not connect to database.  The database appears to be off-line.");
		}
		return conn;
	}
}