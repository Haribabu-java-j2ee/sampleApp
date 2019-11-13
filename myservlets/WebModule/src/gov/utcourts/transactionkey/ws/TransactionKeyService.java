/**
 * TransactionKeyService.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf071807.01 v22318082452
 */

package gov.utcourts.transactionkey.ws;

public interface TransactionKeyService extends javax.xml.rpc.Service {
    public gov.utcourts.transactionkey.ws.TransactionKey getTransactionKey() throws javax.xml.rpc.ServiceException;

    public java.lang.String getTransactionKeyAddress();

    public gov.utcourts.transactionkey.ws.TransactionKey getTransactionKey(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
