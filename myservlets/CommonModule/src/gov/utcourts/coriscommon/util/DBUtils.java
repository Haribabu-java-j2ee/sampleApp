package gov.utcourts.coriscommon.util;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public class DBUtils {

    static final Logger logger = Logger.getLogger(DBUtils.class);

    private DBUtils() {

    }

    /**
     * Close the Closeable object.
     * 
     * @param closeable
     * @throws SystemException
     */
    public static void closeCloseable(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            logger.error(e);
        }
    }

    /**
     * Close the Connection
     * 
     * @param con
     * @throws SystemException
     */
    public static void closeConn(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Close the File Output Stream
     * 
     * @param fos
     * @throws SystemException
     */
    public static void closeFos(FileOutputStream fos) {
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Close the PreparedStatement
     * 
     * @param pStmt
     * @throws SystemException
     */
    public static void closePstmt(PreparedStatement pStmt) {
        if (pStmt != null) {
            try {
                pStmt.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }

    }

    /**
     * Close the ResultSet
     * 
     * @param rs
     * @throws SystemException
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Close the Statement
     * 
     * @param stmt
     * @throws SystemException
     */
    public static void closeStmt(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    public static void closeDataSource(BasicDataSource dataSource) {
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
}
