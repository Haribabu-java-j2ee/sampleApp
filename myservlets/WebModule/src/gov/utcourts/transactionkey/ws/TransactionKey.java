/**
 * TransactionKey.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf071807.01 v22318082452
 */

package gov.utcourts.transactionkey.ws;

public interface TransactionKey extends java.rmi.Remote {
    public gov.utcourts.transactionkey.ReturnCode generateKey(java.lang.String requestedSystem, java.lang.String callingSystem, java.lang.String key) throws java.rmi.RemoteException;
}
