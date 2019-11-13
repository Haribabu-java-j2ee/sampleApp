package gov.utcourts.coriscommon.util;

/**
 * Represents a Blob not Found exception.
 * 
 * @author Brian Holt- Sirius Computer Solutions.
 */
public class BlobNotFoundException extends Exception {
    private static final long serialVersionUID = 4635770294419134492L;

    /**
     * Default constructor.
     */
    public BlobNotFoundException() {
        super();
        fillInStackTrace();
    }
}
