package gov.utcourts.notifications.util;

/**
 * Represents a Blob not Found exception.
 */
public class BlobNotFoundException extends Exception {
	public static final long serialVersionUID = 545454125;
	
	/**
	 * Default constructor.
	 */
	public BlobNotFoundException(){
		super();
		fillInStackTrace();
	}
}
