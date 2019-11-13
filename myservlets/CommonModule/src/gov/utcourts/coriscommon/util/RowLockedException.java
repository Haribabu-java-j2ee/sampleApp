package gov.utcourts.coriscommon.util;

/**
 * Represents a row locked exception.
 * 
 * @author Sirius Computer Solutions, Inc.
 */
public class RowLockedException extends Exception {
    private static final long serialVersionUID = -6983051707774378056L;

    /**
     * Default constructor.
     */
    public RowLockedException() {
        super();
        fillInStackTrace();
    }
}
