package gov.utcourts.notifications.dataaccess;

import gov.utcourts.notifications.constants.Constants;
import gov.utcourts.notifications.common.XMLConfig;
import gov.utcourts.notifications.util.AccountingNumber;
import gov.utcourts.notifications.util.AssessmentNumber;
import gov.utcourts.notifications.util.BlobNotFoundException;
import gov.utcourts.notifications.util.RowLockedException;
import gov.utcourts.notifications.util.SQLPropertiesUtil;
import gov.utcourts.notifications.util.TooManyResultsException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.log4j.Logger;

/**
 * This class is the base class for all DAO's to extend.
 * It contains basic functionality used by all or many of the DAO's including utility
 * methods for working with prepared statments, result sets, and connections.
 * The DAO's follow the DAO pattern outlined by Sun.
 */
public abstract class BaseDAO
	implements Constants {
		
	/**
	 * log object to perform logging.
	 */
	private static Logger log = Logger.getLogger(BaseDAO.class);
	/**
	 * connection to database to be used by implementing DAO classes.
	 */
	private java.sql.Connection con = null;
	/**
	 * preparedStatement to be used by implementing DAO classes.
	 */
	private java.sql.PreparedStatement pStmt;
	/**
	 * resultSet to be used by implementing DAO classes.
	 */
	private java.sql.ResultSet rs;
	
	/**
	 * The number of times we have retried a failed operation.
	 */ 
	protected int retryCount = 0;
	
	/**
	 * The number of times we will retry a failed operation.
	 */ 
	protected static final int maxRetryCount = 100;
	
	/**
	 * The number of milliseconds we will wait before retrying after a failed operation.
	 */ 
	protected static final int retrySleepMillis = 500;
	
	/**
	 * Error code for "Could not position within a table" error..
	 */
	private static final int INFORMIX_COULDNOTPOSITION_ERROR = -243;
	
	/**
	 * Error code for "Could not position within a table" error.
	 */
	private static final int INFORMIX_ROWLOCKED_ERROR = -107;
	
	/**
	 * Error code for "Duplicate Key" error.
	 */
	private static final int INFORMIX_DUPLICATEKEY_ERROR = -239;
	

	/**
	 * Constructor calls base class constructor.
	 */
	public BaseDAO() {
		super();
	}
	
	/**
	 * Closes the {@link #rs results set} and {@link #pStmt prepared statement} 
	 * and sets the {@link #con connection} to be null.
	 * 
	 * @throws Exception on error.
	 */
	public void close() throws Exception {
		try {
			closePartial();
			con = null;
		} catch (SQLException e) {
			log.error("Exception encoutered in BaseDAO.close()", e);
			throw e;
		}
	}
	
	/**
	 * Same as <code>close</code> but does not close the connection.
	 * 
	 * @throws Exception on error.
	 * @see #close()
	 */
	public void closePartial() throws Exception {
		try {
			if(rs != null){
				rs.close();
				rs = null;
			}
		}catch(Throwable e) {
			log.warn("Error closing resultSet:",e);
		}
		try {
			if(pStmt != null){
				pStmt.close();
				pStmt = null;
			}
		}catch (Throwable e) {
			log.warn("Error closing preparedStatement: ", e);
		}
		retryCount = 0;
	}
	/**
	 * Executes updates from the member {@link #pStmt prepared statement}.
	 * 
	 * @return the number of rows affected by the execution of the prepared statement.
	 * @throws Exception on error.
	 */
	protected int execute() throws Exception {
		log.debug(">>>Entering BaseDAO>>execute()");
		int retVal = 0;
		if ((con != null) && (pStmt != null)) {
			try {
				retVal = pStmt.executeUpdate();
			} catch (SQLException e) {
				if( isRowLockedException(e) && retryCount <= maxRetryCount ){							
					log.warn( "!!!!!!!!!!*****************execute(): row locked error, retrying time #" + retryCount++ );
					Thread.sleep(retrySleepMillis);
					retVal = execute();
				}
				else{
					log.error("!!! Exception in BaseDAO>>execute() !!!", e);
					throw e;
				}
			}
		}
		log.debug("<<<Exiting BaseDAO>>execute(), returning " + retVal);
		return retVal;
	}
	/**
	 * Executes Queries from the member prepared statement.
	 * 
	 * @throws Exception on error.
	 */
	protected void executeQuery() throws Exception {
		log.debug(">>>Entering BaseDAO>>executeQuery()");
		if ((con != null) && (pStmt != null)) {
			try {
				rs = pStmt.executeQuery();
			} catch (SQLException e) {
				if( isRowLockedException(e) && retryCount <= maxRetryCount ){							
					log.warn( "!!!!!!!!!!*****************executeQuery(): row locked error, retrying time #" + retryCount++ );
					Thread.sleep(retrySleepMillis);
					executeQuery();
				}
				else{
					log.error("!!! Exception in BaseDAO>>executeQuery() !!!", e);
					throw e;
				}
			}
		}
		log.debug("<<<Exiting BaseDAO>>executeQuery()");
	}
	/**
	 * Returns the Connection object.
	 * @return the connection object
	 */
	protected java.sql.Connection getCon() {
		return con;
	}
	
	/**
	 * Returns an Object from the ResultSet for the specified position.
	 * 
	 * @param pos The position of the value in the result set to be returned.
	 * @return object containing value in specified position.
	 * @throws Exception on error.
	 */
	protected Object getRSValue(int pos) throws Exception {
		try {
			return rs.getObject(pos);
		} catch (SQLException e) {
			log.error("Exception getting RSValue(int)", e);
			throw e;
		}
	}
		
	/**
	 * Returns a char from the ResultSet for the specified column name.
	 * 
	 * @param columnName Column name from which to retrieve boolean value.
	 * @return char value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 * 
	 * MODIFY rs.getChar in future
	 */
	protected char getRSValueChar(String columnName) throws Exception {
		try {

			char ch;
		    Object toCVT = rs.getObject(columnName);			
			
			String strCvt=toCVT.toString();
			ch =strCvt.charAt(0);
			if(strCvt.length()!=1)		{			ch='\0';			}

			toCVT = null;
			
			 return ch;
		} catch (SQLException e) {
			log.error("Exception in geting RSValueChar(String)", e);
			throw e;
		}
	}
	
// end rbs	
	

	
	/**
	 * Returns an Object from the ResultSet for the specified column.
	 * 
	 * @param name Column name from which to retrieve value
	 * @return object containing the value in the specified column
	 * @throws Exception upon unexpected error.
	 */
	protected Object getRSValue(String name) throws Exception {
		try {
			return rs.getObject(name);
		} catch (SQLException e) {
			log.error("Exception getting RSValue(String)", e);
			throw e;
		}
	}
	
	/**
	 * Returns an AccountingNumber from the ResultSet for the specified column name.  
	 * If the column's value is SQL NULL, an AccountingNumber of value zero is returned.
	 * 
	 * @param columnName Column name from which to retrieve double value.
	 * @return The AccountingNumber value contained by the column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected AccountingNumber getRSValueAccountingNumber(String columnName) throws Exception {
		AccountingNumber an = null;
		try {
			BigDecimal big = rs.getBigDecimal(columnName);
			if (big != null) {
				an = new AccountingNumber(big);
			}
			else {
				an = AccountingNumber.ZERO;
			}
			big = null;
			
			return(an);
		} catch (SQLException e) {
			log.error("Exception in getting getRSValueAccountingNumber", e);
			throw e;
		}
	}
	
	/**
	 * Returns an AccountingNumber from the ResultSet for the specified column position.  
	 * If the column's value is SQL NULL, an AccountingNumber of value zero is returned.
	 * 
	 * @param pos The position of the value in the result set to be returned.
	 * @return The AccountingNumber value contained by the column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected AccountingNumber getRSValueAccountingNumber(int pos) throws Exception {
		AccountingNumber an = null;
		try {
			BigDecimal big = rs.getBigDecimal(pos);
			if (big != null) {
				an = new AccountingNumber(big);
			}
			else {
				an = AccountingNumber.ZERO;
			}
			big = null;
			
			return(an);
		} catch (SQLException e) {
			log.error("Exception in getting getRSValueAccountingNumber", e);
			throw e;
		}
	}
	
	/**
	 * Returns a boolean from the ResultSet for the specified column name.
	 * 
	 * @param columnName Column name from which to retrieve boolean value.
	 * @return boolean value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected boolean getRSValueBoolean(String columnName) throws Exception {
		try {
			return rs.getBoolean(columnName);
		} catch (SQLException e) {
			log.error("Exception in geting RSValueBoolean(String)", e);
			throw e;
		}
	}
	
	protected boolean getRSValueBoolean(int columnName) throws Exception {
		try {
			return rs.getBoolean(columnName);
		} catch (SQLException e) {
			log.error("Exception in geting RSValueBoolean(String)", e);
			throw e;
		}
	}
	
	
	
	/**
	 * Returns a double from the ResultSet for the specified column name.
	 * 
	 * @param columnName Column name from which to retrieve double value.
	 * @return double value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected double getRSValueDouble(String columnName) throws Exception {
		try {
			return rs.getDouble(columnName);
		} catch (SQLException e) {
			log.error("Exception in getting RSValueDouble", e);
			throw e;
		}
	}
	
	/**
	 * Returns a double from the ResultSet for the specified column name.
	 * 
	 * @param columnName Column name from which to retrieve double value.
	 * @return double value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected double getRSValueDouble(int columnNumber) throws Exception {
		try {
			return rs.getDouble(columnNumber);
		} catch (SQLException e) {
			log.error("Exception in getting RSValueDouble", e);
			throw e;
		}
	}
	
	/**
	 * Returns a Date from the ResultSet for the specified column name. Note 
	 * that this method returns values accurate to the second only, meaning
	 * a value stored in the database with milliseconds is returned with
	 * milliseconds set to zero. If millisecond accuracy is desired, use 
	 * getRSValueDateTime().
	 * 
	 * @param columnName Column name from which to retrieve the Date value.
	 * @return date value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 * @see gov.utcourts.care.dataaccess.BaseDAO#getRSValueDateTime
	 */
	protected java.sql.Date getRSValueDate(String columnName) throws Exception {
		try {
			return rs.getDate(columnName);
		} catch (SQLException e) {
			log.error("Exception getting RSValueDate(String)", e);
			throw e;
		}
	}
	
	/**
	 * Returns a Date from the ResultSet for the specified column number.
	 * 
	 * @param columnNumber Column number from which to retrieve the Date value.
	 * @return The Date object contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected java.sql.Date getRSValueDate(int columnNumber) throws Exception {
		try {
			return rs.getDate(columnNumber);
		} catch (SQLException e) {
			log.error("Exception getting RSValueDate(" + columnNumber + ")", e);
			throw e;
		}
	}
	
	/**
	 * Returns a Date with millisecond accuracy from the ResultSet for the 
	 * specified column name. This method should be used instead of 
	 * getRSValueDate if timestamps equal to stored database values out to the
	 * millisecond are desired.
	 * 
	 * @param columnName Column name from which to retrieve the Date value.
	 * @return date value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 * @see gov.utcourts.care.dataaccess.BaseDAO#getRSValueDate
	 */
	protected java.sql.Date getRSValueDateTime(String columnName) throws Exception {
		try {
			// Note that because the Timestamp class stores millisecond and 
			// nanosecond values separate from it's long time value, in order
			// to construct a Date object from a Timestamp object with accurate
			// millisecond precision, the Timestamp's nanoseconds value must be
			// added to the time value returned with the getTime() method.
			Timestamp ts = rs.getTimestamp(columnName);
			java.sql.Date dt = null;
			if (ts != null)
			{
				long time = ts.getTime();
				time += ts.getNanos() / 1000000;
				dt = new java.sql.Date(time);
			}
			ts = null;
			
			return dt;
		} catch (SQLException e) {
			log.error("Exception getting RSValueDateTime(String)", e);
			throw e;
		}
	}
	
	/**
	 * Returns a Date with millisecond accuracy from the ResultSet for the 
	 * specified column name. This method should be used instead of 
	 * getRSValueDate if timestamps equal to stored database values out to the
	 * millisecond are desired.
	 * 
	 * @param columnName Column name from which to retrieve the Date value.
	 * @return date value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 * @see gov.utcourts.care.dataaccess.BaseDAO#getRSValueDate
	 */
	protected java.sql.Date getRSValueDateTime(int columnNum) throws Exception {
		try {
			// Note that because the Timestamp class stores millisecond and 
			// nanosecond values separate from it's long time value, in order
			// to construct a Date object from a Timestamp object with accurate
			// millisecond precision, the Timestamp's nanoseconds value must be
			// added to the time value returned with the getTime() method.
			Timestamp ts = rs.getTimestamp(columnNum);
			java.sql.Date dt = null;
			if (ts != null)
			{
				long time = ts.getTime();
				time += ts.getNanos() / 1000000;
				dt = new java.sql.Date(time);
			}
			ts = null;
			
			return dt;
		} catch (SQLException e) {
			log.error("Exception getting RSValueDateTime(String)", e);
			throw e;
		}
	}
	
	/**
	 * Returns a float from the ResultSet for the specified column name.
	 * 
	 * @param columnName Column name from which to retrieve the float value.
	 * @return float value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected float getRSValueFloat(String columnName) throws Exception {
		try {
			return rs.getFloat(columnName);
		} catch (SQLException e) {
			log.error("Exception in getting RSValueFloat", e);
			throw e;
		}
	}
	
	/**
	 * Returns an int from the ResultSet for the specified column name.
	 * 
	 * @param columnName Column name from which to retrieve the int value.
	 * @return int value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected int getRSValueInt(String columnName) throws Exception {
		try {
			return rs.getInt(columnName);
		} catch (SQLException e) {
			log.error("Exception in getting RSValueInt", e);
			throw e;
		}
	}
	
	/**
	 * Returns a long from the ResultSet for the specified column name.
	 * 
	 * @param columnName Column name from which to retrieve the long value.
	 * @return long value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected long getRSValueLong(String columnName) throws Exception {
		try {
			return rs.getLong(columnName);
		} catch (SQLException e) {
			log.error("Exception in getting RSValueLong", e);
			throw e;
		}
	}
	
	/**
	 * Returns a long from the ResultSet for the specified column number.
	 * 
	 * @param columnNumber Column number from which to retrieve the long value.
	 * @return long value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected long getRSValueLong(int columnNumber) throws Exception {
		try {
			return rs.getLong(columnNumber);
		} catch (SQLException e) {
			log.error("Exception in getting RSValueLong", e);
			throw e;
		}
	}
	
	/**
	 * Returns an int from the ResultSet for the specified column number.
	 * 
	 * @param columnNumber Column number from which to retrieve the int value.
	 * @return int value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected int getRSValueInt(int columnNumber) throws Exception {
		try {
			return rs.getInt(columnNumber);
		} catch (SQLException e) {
			log.error("Exception in getting RSValueInt", e);
			throw e;
		}
	}
	
	/**
	 * Returns a string from the ResultSet for the specified column name.
	 * 
	 * @param name Column name from which to retrieve the string value.
	 * @return string value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected String getRSValueString(String name) throws Exception {
		try {
			String s = rs.getString(name);
			if (s != null)
				s = s.trim();
			return s;
		} catch (SQLException e) {
			log.error("Exception in getting RSValueString", e);
			throw e;
			//}
		}
	}
	
	/**
	 * Returns a string from the ResultSet for the specified column name.
	 * 
	 * @param name Column name from which to retrieve the string value.
	 * @return string value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected String getRSValueStringNoTrim(String name) throws Exception {
		try {
			String s = rs.getString(name);
//			if (s != null)
//				s = s.trim();
			return s;
		} catch (SQLException e) {
			log.error("Exception in getting RSValueString", e);
			throw e;
			//}
		}
	}
	
	/**
	 * Returns a string from the ResultSet for the specified column name.
	 * 
	 * @param columnNumber Column number from which to retrieve the int value.
	 * @return string value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected String getRSValueString(int columnNumber) throws Exception {
		try {
			String s = rs.getString(columnNumber);
			if (s != null)
				s = s.trim();
			return s;
		} catch (SQLException e) {
			log.error("Exception in getting RSValueString", e);
			throw e;
			//}
		}
	}
	
	/**
	 * Returns a Timestamp from the ResultSet for the specified column name.
	 * 
	 * @param columnName Column name from which to retrieve the Timestamp value.
	 * @return timestamp value contained by column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected Timestamp getRSValueTimeStamp(String columnName) throws Exception {
		try {
			
			return rs.getTimestamp(columnName);
		} catch (SQLException e) {
			log.error("Exception getting RSValueTimeStamp(String)", e);
			throw e;
		}
	}
	
	/**
	 * Returns a String from teh ResultSet.
	 * This mehtod differs from the getRSValueString method in the fact that it will throw a 
	 * BlobNotFoundException, and should be used for all TEXT type fields.
	 * @param columnName Column name from which to retrieve the text value.
	 * @return String value contained from the column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected String getRSValueText(String columnName) throws Exception {
		try {
			/*Blob blob = rs.getBlob(columnName);
			InputStream ip = blob.getBinaryStream();
			byte[] bytes = new byte[(int)blob.length()];
			ip.read(bytes);
			String retVal = new String(bytes);
			ip.close();
			return retVal;*/
			String s = rs.getString(columnName);
			if (s != null)
				s = s.trim();
			return s;
		} catch (SQLException e) {
			if (e.getErrorCode() == -79701) {
				throw new BlobNotFoundException();
			} else {
				log.error("Exception getting getRSValueText(String)", e);
				throw e;
			}
		}
	}
	
	/**
	 * Returns a byte[] populated with the blob contents from the ResultSet.
	 * @param columnName Column name from which to retrieve the text value.
	 * @return byte[] value contained from the column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected byte[] getRSValueBlob(String columnName) throws Exception {
		try {
			Blob blob = rs.getBlob(columnName);

			byte[] bytes = null;
			if (blob != null) {
				InputStream ip = blob.getBinaryStream();
				bytes = new byte[(int)blob.length()];
				ip.read(bytes);
				ip.close();
				ip = null;
				blob = null;
			}
			return bytes;
		} catch (SQLException e) {
			if (e.getErrorCode() == -79701) {
				throw new BlobNotFoundException();
			} else {
				log.error("Exception getting getRSValueBlob(String)", e);
				throw e;
			}
		}
	}
	/**
	 * Returns a byte[] populated with the blob contents from the ResultSet.
	 * @param columnNum Column number from which to retrieve the text value.
	 * @return byte[] value contained from the column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected byte[] getRSValueBlob(int columnNum) throws Exception {
		try {
			Blob blob = rs.getBlob(columnNum);

			InputStream ip = blob.getBinaryStream();
			byte[] bytes = new byte[(int)blob.length()];
			ip.read(bytes);
			ip.close();
			ip = null;
			blob = null;

			return bytes;
		} catch (SQLException e) {
			if (e.getErrorCode() == -79701) {
				throw new BlobNotFoundException();
			} else {
				log.error("Exception getting getRSValueBlob(String)", e);
				throw e;
			}
		}
	}	
	
	/**
	 * Returns a String from the ResultSet without triming.
	 * This mehtod differs from the getRSValueString method in the fact that it will throw a 
	 * BlobNotFoundException, and should be used for all TEXT type fields.
	 * @param columnName Column name from which to retrieve the text value.
	 * @return String value contained from the column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected String getRSValueTextNoTrim(String columnName) throws Exception {
		try {
			byte[] bytes = rs.getBytes(columnName);
			if(bytes != null){
				String s = new String(bytes);
				bytes = null;
				return s;
			} else {
				return null;
			}
		} catch (SQLException e) {
			if (e.getErrorCode() == -79701) {
				throw new BlobNotFoundException();
			} else {
				log.error("Exception getting getRSValueText(String)", e);
				throw e;
			}
		}
	}
	
	/**
	 * Returns a boolean indicating if there are more results in the ResultSet.
	 * 
	 * @return boolean, true if there are more results, false if not.
	 * @throws Exception upon unexpected error.
	 */
	protected boolean hasMoreResults() throws Exception {
		boolean retVal = false;
		try {
			if (rs == null) {
				log.debug("rs is null");
			} else {
				retVal = rs.next();
			}
		} catch (SQLException e) {
			if( isRowLockedException( e ) ){
				throw new RowLockedException();
			} else if (e.getErrorCode() == -79701) {
				throw new BlobNotFoundException();
			} else{
				log.error("Exception in hasMoreResults", e);
				throw e;
			}
		}
		return retVal;
	}
	
	/**
	 * Sets the member connection with the specified connection.
	 * 
	 * @param newCon connection to be used for database communication
	 */
	public void setCon(java.sql.Connection newCon) {
		con = newCon;
	}
	
	/**
	 * Sets the PreparedStatement SQL.
	 * 
	 * @param sql the string representation of the sql to use in the prepared statement.
	 * @throws Exception upon unexpected error.
	 */
	protected void setPStmt(String sql) throws Exception {
		try {
			if (con != null)
				pStmt = con.prepareStatement(sql);
			else
				throw new Exception("Connection not Set in DAO!!");
		} catch (Exception e) {
			log.debug(sql);
			log.error("Exception in setting PreparedStatement sql", e);
			throw e;
		}
	}
	
	/**
	 * Sets the data type of the specified position in the prepared statement.
	 * The object param is tested to determine data type.
	 * 
	 * @param pos The position in the member prepared statement to be set.
	 * @param o The object to be tested to determine the datatype.
	 * @throws Exception upon unexpected error.
	 */
	protected void setPStmtValue(int pos, Object o) throws Exception {
		//log.debug(">>>Entering BaseDAO>>setPStmtValue( " + pos + ", " + o + " )");
		try {
			if (pStmt == null)
				throw new Exception("Prepared Statement is null");

			if (o == null)
				pStmt.setObject(pos, o);
			else
				if (o instanceof String)
					pStmt.setString(pos, (String) o);
				else
					if (o instanceof Short)
						pStmt.setShort(pos, ((Short) o).shortValue());
					else
						if (o instanceof Integer)
							pStmt.setInt(pos, ((Integer) o).intValue());
						else
							if (o instanceof Double)
								pStmt.setDouble(pos, ((Double) o).doubleValue());
							else
								if (o instanceof Float)
									pStmt.setFloat(pos, ((Float) o).floatValue());
								else
									if (o instanceof Long)
										pStmt.setLong(pos, ((Long) o).longValue());
									else
									if (o instanceof java.sql.Date)
										pStmt.setDate(pos, (java.sql.Date) o);
									else
										if(o instanceof java.sql.Time)
										pStmt.setTime(pos, (java.sql.Time)o);
									else
									if (o instanceof java.util.Date)
										pStmt.setTimestamp(pos, new java.sql.Timestamp(((java.util.Date) o).getTime()));
									else if(o instanceof java.sql.Blob)
										pStmt.setBlob(pos,(java.sql.Blob) o);
									else if (o instanceof AccountingNumber)
										pStmt.setBigDecimal(pos, ((AccountingNumber)o).getBigDecimal());
									else if (o instanceof AssessmentNumber)
										pStmt.setBigDecimal(pos, ((AssessmentNumber)o).getBigDecimal());
									else
										pStmt.setObject(pos, o);
		} catch (SQLException e) {
			log.error(
				"!!!Exception encountered in BaseDAO>>setPStmtValue( " + pos + ", " + o + " )",
				e);
			throw e;
		}
		//log.debug("<<<Exiting BaseDAO>>setPStmtValue( " + pos + ", " + o + " )");
	}
	/**
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the receiver
	 */
	public String toString() {
		// Insert code to print the receiver here.
		// This implementation forwards the message to super. You may replace or supplement this.
		return super.toString();
	}

	/**
	  * Returns the long value for an auto-generated field in column 1.
	  * 
	  * @return long value for an auto-generated field in column 1.
	  * @throws Exception upon unexpected error.
	  */
	public long getIdentityValueLong() throws Exception {

		log.debug(">>>Entering BaseDAO>>getIdentityValue()");

		long retVal = INVALID_ID;
		try {
			String sql = null;
			try {
				sql = SQLPropertiesUtil.getProperty(getClass().getName(),
						"sql.identity");
			} catch (Exception e) {
				SQLPropertiesUtil.getProperty("DefaultBaseDAO",
						"sql.identity");
			}
			log.debug(getClass().getName() + "	sql.identity = " + sql);
			setPStmt(sql);
			executeQuery();
			if (hasMoreResults()) {
				retVal = getRSValueLong(1);
			}
		} catch (Exception e) {
			log.error("Exception in getIdentityValueLong", e);
			throw e;
		} finally {
			close();
		}
		log.debug("<<<Exiting BaseDAO>>getIdentityValue(), returning " + retVal);

		return retVal;
	}
	
	/**
	  * Returns the long value for an auto-generated SERIAL8 field in column 1.
	  * @return The long value for an auto-generated SERIAL8 field in column 1.
	  * @throws Exception upon unexpected error.
	  */
	public long getIdentityValueSerial8() throws Exception {
		log.debug(">>>Entering BaseDAO>>getIdentityValueSerial8()");
		long retVal = INVALID_ID;
		try {
			String sql = null;
			try {
				sql = SQLPropertiesUtil.getProperty(getClass().getName(),
						"sql.identity.8");
			} catch (Exception e) {
				SQLPropertiesUtil.getProperty("DefaultBaseDAO.properties",
						"sql.identity.8");
			}
			log.debug(".getIdentityValueSerial8() [sql.identity.8] SQL: " + sql);
			
			setPStmt(sql);
			
			executeQuery();
			if (hasMoreResults()) {
				retVal = getRSValueLong(1);
			}
		}
		catch (Exception e) {
			log.error("Exception in getIdentityValueSerial8", e);
			throw e;
		}
		finally {
			close();
			log.debug("<<<Exiting BaseDAO>>getIdentityValueSerial8(), returning " + retVal);
		}
		return retVal;
	}
	
	/**
	  * Returns the int value for an auto-generated (serial) field.
	  * 
	  * NOTE:  This method closes the Connection object.
	  * 
	  * @return int The value for an auto-generated (serial) field.
	  * @throws Exception upon unexpected error.
	  */
	public int getIdentityValueInt() throws Exception {
		log.debug(">>>Entering BaseDAO>>getIdentityValueInt()");
		try {
			return(getIdentityValueIntNoClose());
		}
		finally {
			close();
			log.debug("<<<Exiting BaseDAO>>getIdentityValueInt()");
		}
	}
	
	
	/**
	  * Returns the int value for the number of items in the result set.
	  * 
	  * NOTE:  This method closes the Connection object.
	  * 
	  * @return int The value for the count of the result set.
	  * @throws Exception upon unexpected error.
	  */
	public int getResultSetCount() throws Exception {
		log.debug(">>>Entering BaseDAO>>getResultSetCount()");
		int count = 0;
		try {
			while(hasMoreResults()){
				getRSValue(1);
				count += 1;
			}
			
			return count;
		}
		finally {
			close();
			log.debug("<<<Exiting BaseDAO>>getResultSetCount()");
		}
	}
	
	
	/**
	  * Returns the int value for the number of items in the result set.
	  * 
	  * NOTE:  This method closes the Connection object.
	  * 
	  * @return int The value for the count of the result set.
	  * @throws Exception upon unexpected error.
	  */
	public int getResultSetCountConnectionNotClosed() throws Exception {
		log.debug(">>>Entering BaseDAO>>getResultSetCount()");
		int count = 0;
		try {
			while(hasMoreResults()){
				getRSValue(1);
				count += 1;
			}
		
			return count;
		}
		finally {
			
			log.debug("<<<Exiting BaseDAO>>getResultSetCount()");
		}
	}
	
	/**
	  * Returns the int value for an auto-generated (serial) field without
	  * closing the Connection object.
	  * 
	  * @return int The value for an auto-generated (serial) field.
	  * @throws Exception upon unexpected error.
	  */
	public int getIdentityValueIntNoClose() throws Exception {
		log.debug(">>>Entering BaseDAO>>getIdentityValueIntNoClose()");
		int retVal = INVALID_ID_INT;
		try {
			String sql = null;
			try {
				sql = SQLPropertiesUtil.getProperty(BaseDAO.class.getName(),
						"sql.identity");
			} catch (Exception e) {
				SQLPropertiesUtil.getProperty("DefaultBaseDAO.properties",
						"sql.identity");
			}
			
			log.debug(getClass().getName()+ "  sql.identity = " + sql);
			
			setPStmt(sql);
			
			executeQuery();
			if (hasMoreResults()) {
				retVal = getRSValueInt(1);
			}
		}catch (SQLException exception){
			log.error(" SQL Exception code "+exception.getErrorCode(), exception);
			throw exception;
		}catch (Exception e) {
			log.error("Exception in getIdentityValueIntNoClose", e);
			throw e;
		}
		finally {
			closePartial();
			log.debug("<<<Exiting BaseDAO>>getIdentityValueIntNoClose() [RETURNS]: " + retVal);
		}
		return retVal;
	}
	
	/**
	 * Used to do a count, throws a TooManyResultsException if the count is too high, 
	 * else it runs the query and fills the result set.
	 * 
	 * @throws TooManyResultsException if the number of records in the result set exceed the 
	 * max_results property.
	 * @param countSql the sql statement to run the count.
	 * @param sql the actual sql query to run if the count passes.
	 * @param psValues a collection of values for the prepared statement.
	 * @throws Exception upon unexpected error.
	 */
	protected void doSearch(String countSql,String sql, Collection<String> psValues) throws Exception{
		setPStmt(countSql);
		java.util.Iterator<String> it = psValues.iterator();
	 	for(int i=1 ; it.hasNext() ; i++){
	 		setPStmtValue(i,it.next());
	 	}
	 	executeQuery();
	 	long count = 0;
	 	while(hasMoreResults()){
	 		count += getRSValueLong(1);
	 	}
	 	if(count > Long.parseLong(XMLConfig.getProperty("PROPERTY.max_results"))){
	 		throw new TooManyResultsException();
	 	}
	 	closePartial();
	 	setPStmt(sql);
		it = psValues.iterator();
		for (int i = 1; it.hasNext(); i++) {
			setPStmtValue(i, it.next());
		}
		it = null;
		
		executeQuery();
		
	}
	/**
	 * Runs the query and fills the result set.
	 * @param sql the actual sql query to run if the count passes.
	 * @param psValues a collection of values for the prepared statement.
	 * @throws Exception upon unexpected error.
	 */
	protected void doSearchNoException(String sql, Collection<String> psValues) throws Exception{
	 	setPStmt(sql);
		Iterator<String> it = psValues.iterator();
		for (int i = 1; it.hasNext(); i++) {
			setPStmtValue(i, it.next());
		}
		it = null;
		
		executeQuery();
	}
	
	/**
	 * Runs the query and fills the result set.
	 * @param sql the actual sql query to run if the count passes.
	 * @param psValues a collection of values for the prepared statement.
	 * @throws Exception upon unexpected error.
	 */
	protected int doSearchNoExceptionReturnCount(String sql, Collection<String> psValues) throws Exception{
		setPStmt(sql);
		Iterator<String> it = psValues.iterator();
		int count = 0;
		for (int i = 1; it.hasNext(); i++) {
			setPStmtValue(i, it.next());
		}
		it = null;
		
		executeQuery();
		
		while(hasMoreResults()){
			getRSValue(1);
			count += 1;
		}
		
		
		return count;
	}
	
	/**
	 * Used to create a statement and run the supplied sql in the case that it makes no sense to use
	 * a prepared statement.  When a prepared statement can be used, use setPstmt and executeQuery().
	 * @param sql the complete sql to be executed.
	 * @throws Exception upon unexpected error.
	 */
	protected void executeQuery(String sql) throws Exception {
		try {
			if (con != null) {
				Statement stmt = con.createStatement();
				try {
					rs = stmt.executeQuery(sql);
				}catch (SQLException e) {
					if( isRowLockedException(e) && retryCount <= maxRetryCount ){							
						log.warn( "!!!!!!!!!!*****************executeQuery(String sql): row locked error, retrying time #" + retryCount++ );
						Thread.sleep(retrySleepMillis);
						executeQuery(sql);
					}
					else{
						log.error("!!! Exception in BaseDAO>>executeQuery(String sql) !!!", e);
						throw e;
					}
				}
				stmt = null;
			}
			else {
				throw new Exception("Connection not Set in DAO!!");
			}
		} catch (Exception e) {
			log.debug(sql);
			log.error("Exception in setting PreparedStatement sql", e);
			throw e;
		}
		log.debug("<<<Exiting BaseDAO>>executeQuery()");
	}
	
	/**
	 * Used to build the middle part of a sql statement.  
	 * The pre sql (the select statement) must be added first and then any "order by" is added after this method.
	 * 
	 * @param preSql the string representing the select statement (select + items to be selected).
	 * @param tables a collection of strings representing the tables in the "from" clause -- "from" 
	 * is added by this method.
	 * @param where a collection of strings, each representing a condition in the "where" clause.  This method
	 * adds "where" before this list and "and" between each condition.
	 * @return the completed sql statement constructed from the parameters.
	 * 
	 */
	protected String buildSQL(String fields, Collection<String> tables, Collection<String> where) {
		return buildSQL(fields, tables, null, where);
	}
	protected String buildSQL(String fields, Collection<String> tables, Collection<String> join, Collection<String> where) {
		StringBuffer sb = new StringBuffer(fields);		
		sb.append(" from ");
		
		// tables
		Collection<String> uniqueTables = new TreeSet<String>(tables);
		Iterator it = uniqueTables.iterator();
		sb.append((String) it.next());
		while (it.hasNext()) {
			sb.append(", ");
			sb.append((String) it.next());
		}
		
		// joins
		if (join != null) {
			it = join.iterator();
			while (it.hasNext()) {
				sb.append(" " + (String)it.next() + " ");
			}
		}
		
		// where
		it = where.iterator();
		if (it.hasNext()) {
			sb.append(" where ");
			sb.append((String) it.next());
			while (it.hasNext()) {
				sb.append(" and ");
				sb.append((String) it.next());
			}
		}
		return sb.toString();
	}
	
	/**
	 * This is a Utility Method for setting a primitave int as an Object.
	 * If the int is less than or equal to 0, null will be returned.
	 * @param val the value of the int.
	 * @return the Integer object representation of the value, null if the
	 * int parameter is less than or equal to 0.
	 */
	public Integer getInteger(int val){
		if(val > 0){
			return new Integer(val);
		} else {
			return null;
		}
	}
	
	/**
	 * This is a Utility Method for setting a primitave int as an Object.
	 * If the int is less than or equal to 0, 0 will be returned.
	 * @param val the value of the int.
	 * @return the Integer object representation of the value, null if the
	 * int parameter is less than or equal to 0.
	 */
	public Integer getIntegerIfLessThanOrEqualToZeroReturnZero(int val){
		if(val > 0){
			return new Integer(val);
		} else {
			return new Integer(0);
		}
	}
	
	/**
	 * This is a Utility Method for setting a primitave long as an Object.
	 * If the long is less than or equal to 0, null will be returned.
	 * @param val the value of the long
	 * @return the object representation of the value, null if the long is less than or equal to 0.
	 */
	public Long getLong(long val){
		if(val > 0){
			return new Long(val);
		} else {
			return null;
		}
	}
	
	/**
	 * This is a Utility Method for setting a primitave float as an Object.
	 * If the float is less than or equal to 0, null will be returned.
	 * @param val the value of the float
	 * @return the object representation of the value, null if the float is less than or equal to 0.
	 */
	public Float getFloat(float val){
		if(val != 0){
			return new Float(val);
		} else {
			return null;
		}
	}
	
	/**
	 * This is a Utility Method for setting a primitave double as an Object.
	 * If the double is less than or equal to 0, null will be returned.
	 * @param val the value of the double
	 * @return the object representation of the value, null if the double is equal to 0.
	 */
	public Double getDouble(double val){
		if(val > 0.0001){
			return new Double(val);
		} else {
			return null;
		}
	}
	
	
	/**
	 * This is a Utility Method for setting a primitave double as an Object.
	 * If the double is less than or equal to 0, zero will be returned.
	 * @param val the value of the double
	 * @return the object representation of the value, null if the double is equal to 0.
	 */
	public Double getDoubleIfLessThanOrEqualToZeroReturnZero(double val){
		if(val > 0.0001){
			return new Double(val);
		} else {
			return new Double(0);
		}
	}
	
	public ResultSetMetaData getRSMetaData(){
		ResultSetMetaData ret = null;
		try {
			ret=rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
  

	
	/**
	 * Sets a TEXT informix value into the prepared statement.
	 * @param pos the position in the prepared statement
	 * @param value the value to be set
	 * @throws Exception upon unexpected error.
	 */
	protected void setText(int pos, String value) throws Exception{
		if (value == null || value.length() == 0) {
			pStmt.setNull(pos, Types.LONGVARCHAR);
		} else {
			pStmt.setBytes(pos, value.getBytes());
		}
	}
	
	/**
	 * Sets a TEXT informix value into the prepared statement.
	 * @param pos the position in the prepared statement
	 * @param value the value to be set
	 * @throws Exception upon unexpected error.
	 */
	protected void setTextInstructions(int pos, String value) throws Exception{
		if (value == null || value.length() == 0) {
			pStmt.setNull(pos, Types.LONGVARCHAR);
		} else {
			pStmt.setBytes(pos, value.getBytes());
		}
	}

	/**
	 * Sets a BLOB informix value into the prepared statement.
	 * @param pos the position in the prepared statement
	 * @param blob the value to be set
	 * @throws Exception upon unexpected error.
	 */
	protected void setBlob(int pos, byte[] blob) throws Exception{
		if (blob == null || blob.length == 0) {
			pStmt.setNull(pos, Types.BLOB);
		} else {
			ByteArrayInputStream inStream = new ByteArrayInputStream(blob);
			pStmt.setBinaryStream(pos, new ByteArrayInputStream(blob), inStream.available());
			inStream = null;
		}
	}
	
	
	/**
	 * Returns whether or not <code>e</code> indicates that a row locking exception
	 * has been returned by Informix.
	 * @param e Exception to check
	 * @return boolean, true if <code>e</code> is a row locking exception, false otherwise
	 */
	protected boolean isRowLockedException( SQLException e ){
		boolean retVal = false;
		if( e.getErrorCode() == INFORMIX_COULDNOTPOSITION_ERROR ){
			Exception nextException = e.getNextException();
			if( nextException != null  && nextException instanceof SQLException ){
				if( ((SQLException)nextException).getErrorCode() == INFORMIX_ROWLOCKED_ERROR ){
					retVal = true;
				}
			}
		}
		return retVal;
	}

	/**
	 * Identifies an Informix duplicate key violation.
	 * 
	 * -239 Could not insert new row - duplicate value in a UNIQUE INDEX column. 
	 * The row that is being inserted (or being updated to have a new primary key)
	 * contains a duplicate value of some row that already exists, in a column or
	 * columns that are constrained to have unique values. 
	 * 
	 * @param e A caught SQLException.
	 * @return true/false This SQLException is from a duplicate key violation.
	 */
	protected boolean duplicateKeyExits(SQLException e) {
		boolean isDup = false;
		if (e.getErrorCode() == INFORMIX_DUPLICATEKEY_ERROR) {
			isDup = true;
		}
		return(isDup);
	}


	/**
	 * Converts a boolean to the informix boolean character value.
	 */
	protected String boolToChar(boolean bool) {
		return (bool) ? "Y" : "N";
	}
	/**
	 * Converts an informix boolean character value to a boolean.
	 */
	protected boolean charToBool(String st) {
		return (st != null && st.equalsIgnoreCase("Y")) ? true : false;
	}
	
	/**
	 * Returns an AssessmentNumber from the ResultSet for the specified column name.  
	 * If the column's value is SQL NULL, an AssessmentNumber of value zero is returned.
	 * 
	 * @param columnName Column name from which to retrieve double value.
	 * @return The AssessmentNumber value contained by the column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected AssessmentNumber getRSValueAssessmentNumber(String columnName) throws Exception {
		AssessmentNumber an = null;
		try {
			BigDecimal big = rs.getBigDecimal(columnName);
			if (big != null) {
				an = new AssessmentNumber(big);
			}
			else {
				an = null;
			}
			big = null;
			
			return(an);
		} catch (SQLException e) {
			log.error("Exception in getting getRSAssessmentNumber", e);
			throw e;
		}
	}
	
	/**
	 * Returns an AssessmentNumber from the ResultSet for the specified column position.  
	 * If the column's value is SQL NULL, an AssessmentNumber of value zero is returned.
	 * 
	 * @param pos The position of the value in the result set to be returned.
	 * @return The AssessmentNumber value contained by the column indicated.
	 * @throws Exception upon unexpected error.
	 */
	protected AssessmentNumber getRSValueAssessmentNumber(int pos) throws Exception {
		AssessmentNumber an = null;
		try {
			BigDecimal big = rs.getBigDecimal(pos);
			if (big != null) {
				an = new AssessmentNumber(big);
			}
			else {
				an = null;
			}
			big = null;
			
			return(an);
		} catch (SQLException e) {
			log.error("Exception in getting getRSValueAssessmentNumber", e);
			throw e;
		}
	}	
	
	/**
     * Set the isolation level to dirty read.
     * 
     * @throws Exception upon unexpected error.
     */
    protected void setIsolationDirtyRead() throws Exception {
        setPStmt("set isolation to dirty read");
        execute();
        closePartial();
    }

    /**
     * Set the isolation level to committed read.
     * 
     * @throws Exception upon unexpected error.
     */
    protected void setIsolationCommittedRead() throws Exception {
        setPStmt("set isolation to committed read");
        execute();
        closePartial();
    }

}