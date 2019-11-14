package gov.utcourts.coriscommon.util;


/**
 * SystemException is a subclass of <code>SmartException</code>, and should be thrown when the system (NOT the user) is the cause of the exception. For example, if
 * you try to connect to an SQL database and fail, you should throw a SystemException (or subclass of it). You can specify both the friendly and technical
 * descriptions, or you can just specify the technical description, and the friendly description (used in production run modes) will be defaulted to the generic
 * error message. The technical error description you provide will be logged, so you need not worry about logging the error message before you throw the
 * SystemException.
 **/
public class SystemException extends SmartException {

    private static final long serialVersionUID = 462623288769060110L;

    /**
     * Construct a SystemException with the given origin exception and user message.
     **/
    public SystemException(Exception origin, String userMessage) {
        super(origin, userMessage);
    }

    /**
     * Construct a SystemException with the given origin exception, user and technical messages.
     **/
    public SystemException(Exception origin, String userMessage, String techMessage) {
        super(origin, userMessage, techMessage);
    }

    /**
     * Construct a SystemException with the given user message.
     **/
    public SystemException(String userMessage) {
        super(userMessage);
    }

    /**
     * Construct a SystemException with the given user and technical messages.
     **/
    public SystemException(String userMessage, String techMessage) {
        super(userMessage, techMessage);
    }
}