package gov.utcourts.notifications.dataaccess;

import gov.utcourts.notifications.common.SmartException;
import gov.utcourts.notifications.common.SystemException;
import gov.utcourts.notifications.common.XMLConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class JNDIConnectCommonDB {
	private static final Logger log = Logger.getLogger(JNDIConnectCommonDB.class.getName());
	
	static DataSource ds = null;
	
	private static String source = XMLConfig.getProperty("PROPERTY.data_sources.common_ds.datasource");
	
	public static Connection getConnection() throws SmartException {
		Connection conn = null;
		try	{
			Class.forName("com.ibm.websphere.naming.WsnInitialContextFactory");
			Hashtable<String, String> parms = new Hashtable<String, String>();
			parms.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
			Context ctx = new InitialContext(parms);
			ds = (DataSource)ctx.lookup(source);
			
			if (ds == null) {
				log.error("Datasource in JNDIConnect is NULL");
			}
			conn = ds.getConnection();
			
			parms = null;
			ctx = null;
		} catch (SQLException e) {
			throw new SystemException(e, "Couldn't establish a database connection.  The database is currently off-line.");
		} catch(ClassNotFoundException e) {
			throw new SystemException(e, "Couldn't establish a database connection.  Unable to find class.");
		} catch(NamingException e) {
			throw new SystemException(e, "Couldn't establish a database connection.  Unable to find datasource.");
		}
		return conn;
	}
}
