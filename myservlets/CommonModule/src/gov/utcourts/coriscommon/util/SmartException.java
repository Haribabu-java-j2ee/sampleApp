package gov.utcourts.coriscommon.util;

import java.io.CharArrayWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

/**
 * SmartException is the parent class for SystemException, UserException and all of their derivatives. It is called 'SmartException' because it has two separate
 * error messages: a technical description, and a friendly description. The technical is used for internal logging, and auditing purposes, and is not intended for
 * the user. The friendly description is a user-friendly error message. If the context is in production mode, SmartException reports the friendly description. If in
 * a non-production context, SmartException will report the technical description of the exception.
 * 
 * @author Sirius Computer Solutions, Inc.
 **/
public class SmartException extends Exception {

    private static final long serialVersionUID = 6332992914664075604L;
    static final Logger logger = Logger.getLogger(SmartException.class);

    /** Whether or not the code is being deployed in production. */
    private static final boolean PRODUCTION = false;

    /** The user-friendly description of the exception. */
    private final String userMessage;

    /** The original exception for which this instance was created (optional). */
    private final Exception origin;

    /**
     * Constructs a SmartException using the given user message and exception origin.
     **/
    public SmartException(Exception origin, String userMessage) {
        super(origin.getMessage());
        this.userMessage = userMessage;
        this.origin = origin;
        printSelf();
    }

    /**
     * Constructs a SmartException using the given user and technical messages.
     **/
    public SmartException(Exception origin, String userMessage, String techMessage) {
        super(techMessage);
        this.userMessage = userMessage;
        this.origin = origin;
        printSelf();
    }

    /**
     * Constructs a SmartException using the given message as both the user and technical message.
     **/
    public SmartException(String userMessage) {
        this(null, userMessage, userMessage);
    }

    /**
     * Constructs a SmartException using the given user and technical messages.
     **/
    public SmartException(String userMessage, String techMessage) {
        this(null, userMessage, techMessage);
    }

    /**
     * Return the friendly description in production mode, otherwise return the technical description.
     **/
    @Override
    public String getMessage() {
        if (PRODUCTION) {
            return userMessage;
        } else {
            return super.getMessage();
        }
    }

    /**
     * Returns the origin of the exception if it is defined, otherwise returns reference to self.
     **/
    public Exception getOrigin() {
        if (origin != null) {
            return origin;
        } else {
            return this;
        }
    }

    /**
     * Return the stack trace of a given exception as a String.
     **/
    public static String getStackTrace(Exception e) {
        if (e == null) {
            return "No exception";
        }
        CharArrayWriter trace = new CharArrayWriter();
        e.printStackTrace(new PrintWriter(trace));
        return trace.toString();
    }

    /**
     * Return the technical description (techMessage) of the exception.
     **/
    public String getTechMessage() {
        return super.getMessage();
    }

    /**
     * Return the user-friendly description of the exception. The one that would be used most conditions.
     **/
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * Internal method shared by two constructors; prints information about the exception to System.err.
     **/
    private void printSelf() {
        String className = this.getClass().getName();
        logger.error(className + " thrown: " + getTechMessage());
        if (System.getProperty("debug") != null) {
            printStackTrace();
        }
    }

    /** Method to print the stack trace. **/
    @Override
    public void printStackTrace() {
        if (origin != null) {
            origin.printStackTrace();
        } else {
            super.printStackTrace();
        }
    }

    /** Method to print the stack trace. **/
    @Override
    public void printStackTrace(PrintStream s) {
        if (origin != null) {
            origin.printStackTrace(s);
        } else {
            super.printStackTrace(s);
        }
    }

    /** Method to print the stack trace. **/
    @Override
    public void printStackTrace(PrintWriter s) {
        if (origin != null) {
            origin.printStackTrace(s);
        } else {
            super.printStackTrace(s);
        }
    }

    /**
     * Return the friendly description in production mode, otherwise return the technical description.
     **/
    @Override
    public String toString() {
        if (PRODUCTION) {
            return userMessage;
        } else {
            return super.getMessage();
        }
    }
}
