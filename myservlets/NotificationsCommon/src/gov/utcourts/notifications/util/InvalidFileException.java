package gov.utcourts.notifications.util;

public class InvalidFileException extends Exception {
	private static final long serialVersionUID = 95644511;

	/**
	 * Constructs a <code>DAOException</code> with no specified detail
	 * message. 
	 */
	public InvalidFileException() {
		super();
	}

	/**
	 * Constructs a <code>DAOException</code> with the specified detail
	 * message. 
	 *
	 * @param   message   the detail message.
	 */
	public InvalidFileException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <code>DAOException</code> with the message from
	 * another <code>Exception </code>.
	 *
	 * @param   exception   the exception containing the message.
	 */
	public InvalidFileException(Exception exc) {
		super(exc.getMessage());
	}
}
