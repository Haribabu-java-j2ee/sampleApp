package gov.utcourts.notifications.util;

/**
 * Represents a row locked exception.
 */
public class RowLockedException extends Exception {
	public static final long serialVersionUID = 98775411;
	
	/**
	 * Default constructor.
	 */
	public RowLockedException(){
		super();
		fillInStackTrace();
	}

}
