package gov.utcourts.notifications.util;

public class InvalidFileTypeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>DAOException</code> with no specified detail
	 * message. 
	 */
	public InvalidFileTypeException() {
		super();
	}

	/**
	 * Constructs a <code>DAOException</code> with the specified detail
	 * message. 
	 *
	 * @param   message   the detail message.
	 */
	public InvalidFileTypeException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <code>DAOException</code> with the message from
	 * another <code>Exception </code>.
	 *
	 * @param   exception   the exception containing the message.
	 */
	public InvalidFileTypeException(Exception exc) {
		super(exc.getMessage());
	}
}
