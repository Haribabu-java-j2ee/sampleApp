/**
 * ApplicationAuthenticationServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf071807.01 v22318082452
 */

package gov.utcourts.applicationauthen.ws;

public class ApplicationAuthenticationServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, gov.utcourts.applicationauthen.ws.ApplicationAuthenticationService {

    public ApplicationAuthenticationServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://ws.applicationauthen.utcourts.gov",
           "ApplicationAuthenticationService"));

        context.setLocatorName("gov.utcourts.applicationauthen.ws.ApplicationAuthenticationServiceLocator");
    }

    public ApplicationAuthenticationServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("gov.utcourts.applicationauthen.ws.ApplicationAuthenticationServiceLocator");
    }

    // Use to get a proxy class for applicationAuthentication
    private final java.lang.String applicationAuthentication_address = "https://localhost:9443/ApplicationAuthenWS/services/ApplicationAuthentication";

    public java.lang.String getApplicationAuthenticationAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return applicationAuthentication_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("ApplicationAuthentication");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return applicationAuthentication_address;
        }
    }

    private java.lang.String applicationAuthenticationPortName = "ApplicationAuthentication";

    // The WSDD port name defaults to the port name.
    private java.lang.String applicationAuthenticationWSDDPortName = "ApplicationAuthentication";

    public java.lang.String getApplicationAuthenticationWSDDPortName() {
        return applicationAuthenticationWSDDPortName;
    }

    public void setApplicationAuthenticationWSDDPortName(java.lang.String name) {
        applicationAuthenticationWSDDPortName = name;
    }

    public gov.utcourts.applicationauthen.ws.ApplicationAuthentication getApplicationAuthentication() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getApplicationAuthenticationAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getApplicationAuthentication(endpoint);
    }

    public gov.utcourts.applicationauthen.ws.ApplicationAuthentication getApplicationAuthentication(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        gov.utcourts.applicationauthen.ws.ApplicationAuthentication _stub =
            (gov.utcourts.applicationauthen.ws.ApplicationAuthentication) getStub(
                applicationAuthenticationPortName,
                (String) getPort2NamespaceMap().get(applicationAuthenticationPortName),
                gov.utcourts.applicationauthen.ws.ApplicationAuthentication.class,
                "gov.utcourts.applicationauthen.ws.ApplicationAuthenticationSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(applicationAuthenticationWSDDPortName);
        }
        return _stub;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (gov.utcourts.applicationauthen.ws.ApplicationAuthentication.class.isAssignableFrom(serviceEndpointInterface)) {
                return getApplicationAuthentication();
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("WSWS3273E: Error: There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        String inputPortName = portName.getLocalPart();
        if ("ApplicationAuthentication".equals(inputPortName)) {
            return getApplicationAuthentication();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        applicationAuthenticationWSDDPortName = prefix + "/" + applicationAuthenticationPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.applicationauthen.utcourts.gov", "ApplicationAuthenticationService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "ApplicationAuthentication",
               "http://schemas.xmlsoap.org/wsdl/soap/");
        }
        return port2NamespaceMap;
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            String serviceNamespace = getServiceName().getNamespaceURI();
            for (java.util.Iterator i = getPort2NamespaceMap().keySet().iterator(); i.hasNext(); ) {
                ports.add(
                    com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                        serviceNamespace,
                        (String) i.next()));
            }
        }
        return ports.iterator();
    }

    public javax.xml.rpc.Call[] getCalls(javax.xml.namespace.QName portName) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
        if  (portName.getLocalPart().equals("ApplicationAuthentication")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "validate", "validateRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
    }
}
