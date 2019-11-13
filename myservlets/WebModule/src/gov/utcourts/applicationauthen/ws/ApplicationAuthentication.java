/**
 * ApplicationAuthentication.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf141738.01 v92117092224
 */

package gov.utcourts.applicationauthen.ws;

public interface ApplicationAuthentication extends java.rmi.Remote {
    public gov.utcourts.applicationauthen.ReturnCode validate(java.lang.String requestedSystem, java.lang.String callingSystem, java.lang.String key, java.lang.String transactionId, java.lang.String transactionKey) throws java.rmi.RemoteException;
}
