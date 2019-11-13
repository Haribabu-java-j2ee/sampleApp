package gov.utcourts.corisweb.exception;

public class OsdcWebException extends java.lang.Exception  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String message;

    public OsdcWebException(
           java.lang.String message) {
        this.message = message;
    }

    public java.lang.String getMessage() {
        return message;
    }

}
