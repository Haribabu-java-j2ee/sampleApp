/**
 * TransactionKeyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf071807.01 v22318082452
 */

package gov.utcourts.transactionkey.ws;

public class TransactionKeyServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, gov.utcourts.transactionkey.ws.TransactionKeyService {

    public TransactionKeyServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://ws.transactionkey.utcourts.gov",
           "TransactionKeyService"));

        context.setLocatorName("gov.utcourts.transactionkey.ws.TransactionKeyServiceLocator");
    }

    public TransactionKeyServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("gov.utcourts.transactionkey.ws.TransactionKeyServiceLocator");
    }

    // Use to get a proxy class for transactionKey
    private final java.lang.String transactionKey_address = "https://localhost:9443/TransactionKeyWS/services/TransactionKey";

    public java.lang.String getTransactionKeyAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return transactionKey_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("TransactionKey");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return transactionKey_address;
        }
    }

    private java.lang.String transactionKeyPortName = "TransactionKey";

    // The WSDD port name defaults to the port name.
    private java.lang.String transactionKeyWSDDPortName = "TransactionKey";

    public java.lang.String getTransactionKeyWSDDPortName() {
        return transactionKeyWSDDPortName;
    }

    public void setTransactionKeyWSDDPortName(java.lang.String name) {
        transactionKeyWSDDPortName = name;
    }

    public gov.utcourts.transactionkey.ws.TransactionKey getTransactionKey() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getTransactionKeyAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getTransactionKey(endpoint);
    }

    public gov.utcourts.transactionkey.ws.TransactionKey getTransactionKey(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        gov.utcourts.transactionkey.ws.TransactionKey _stub =
            (gov.utcourts.transactionkey.ws.TransactionKey) getStub(
                transactionKeyPortName,
                (String) getPort2NamespaceMap().get(transactionKeyPortName),
                gov.utcourts.transactionkey.ws.TransactionKey.class,
                "gov.utcourts.transactionkey.ws.TransactionKeySoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(transactionKeyWSDDPortName);
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
            if (gov.utcourts.transactionkey.ws.TransactionKey.class.isAssignableFrom(serviceEndpointInterface)) {
                return getTransactionKey();
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
        if ("TransactionKey".equals(inputPortName)) {
            return getTransactionKey();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        transactionKeyWSDDPortName = prefix + "/" + transactionKeyPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.transactionkey.utcourts.gov", "TransactionKeyService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "TransactionKey",
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
        if  (portName.getLocalPart().equals("TransactionKey")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "generateKey", "generateKeyRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
    }
}
