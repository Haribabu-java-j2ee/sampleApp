/**
 * ApplicationAuthenticationService.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf071807.01 v22318082452
 */

package gov.utcourts.applicationauthen.ws;

public interface ApplicationAuthenticationService extends javax.xml.rpc.Service {
    public gov.utcourts.applicationauthen.ws.ApplicationAuthentication getApplicationAuthentication() throws javax.xml.rpc.ServiceException;

    public java.lang.String getApplicationAuthenticationAddress();

    public gov.utcourts.applicationauthen.ws.ApplicationAuthentication getApplicationAuthentication(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
