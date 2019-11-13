package gov.utcourts.corisweb.tablecompare;

import gov.utcourts.coriscommon.constants.ConstantsConnectionProperties;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.country.CountryBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.connection.ConnectionProperties;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * Servlet implementation class TableCompareProcessUtils
 */
public class TableCompareProcessUtils {
	
	
	private static final long serialVersionUID = 8751358626902358760L;
	
	private static Logger logger = Logger.getLogger(AttorneyTableCompareProcess.class);
	
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public TableCompareProcessUtils() {
        super();
    }
    
	
	//////////////////////////////////////////////////////////////////////////////////
	// Each connection gets it own try so if its fails the other's still get updated
	//////////////////////////////////////////////////////////////////////////////////
	public static Connection connectToDatabase(ConnectionProperties CORIS_DATABASE) throws ServletException, IOException {
		Connection conn = null;
		try{
			conn = DatabaseConnection.getConnection(CORIS_DATABASE);
		} catch (Exception e) {
				logger.info("Process Table Compare connection fail");
		}
		return conn;
	}
}
