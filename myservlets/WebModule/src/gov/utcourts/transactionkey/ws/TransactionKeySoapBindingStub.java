/**
 * TransactionKeySoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf071807.01 v22318082452
 */

package gov.utcourts.transactionkey.ws;

public class TransactionKeySoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements gov.utcourts.transactionkey.ws.TransactionKey {
    public TransactionKeySoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[1];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("gov.utcourts.transactionkey.ws.TransactionKeySoapBinding")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","gov.utcourts.transactionkey.ws.TransactionKeySoapBinding");
        }
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
        javaType = gov.utcourts.transactionkey.ReturnCode.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://transactionkey.utcourts.gov", "ReturnCode");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _generateKeyOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getgenerateKeyOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "requestedSystem"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callingSystem"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "key"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("inputPosition","0");
        _params0[0].setOption("partName","string");
        _params0[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("inputPosition","1");
        _params0[1].setOption("partName","string");
        _params0[2].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("inputPosition","2");
        _params0[2].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "generateKeyReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://transactionkey.utcourts.gov", "ReturnCode"), gov.utcourts.transactionkey.ReturnCode.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partQNameString","{http://transactionkey.utcourts.gov}ReturnCode");
        _returnDesc0.setOption("outputPosition","0");
        _returnDesc0.setOption("partName","ReturnCode");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _generateKeyOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("generateKey", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.transactionkey.utcourts.gov", "generateKey"), _params0, _returnDesc0, _faults0, "generateKey");
        _generateKeyOperation0.setOption("inoutOrderingReq","false");
        _generateKeyOperation0.setOption("buildNum","cf071807.01");
        _generateKeyOperation0.setOption("usingAddressing","false");
        _generateKeyOperation0.setOption("outputName","generateKeyResponse");
        _generateKeyOperation0.setOption("ResponseNamespace","http://ws.transactionkey.utcourts.gov");
        _generateKeyOperation0.setOption("targetNamespace","http://ws.transactionkey.utcourts.gov");
        _generateKeyOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.transactionkey.utcourts.gov", "generateKeyResponse"));
        _generateKeyOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.transactionkey.utcourts.gov", "TransactionKey"));
        _generateKeyOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.transactionkey.utcourts.gov", "TransactionKeyService"));
        _generateKeyOperation0.setOption("inputName","generateKeyRequest");
        _generateKeyOperation0.setOption("ResponseLocalPart","generateKeyResponse");
        _generateKeyOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.transactionkey.utcourts.gov", "generateKeyRequest"));
        _generateKeyOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _generateKeyOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _generateKeyOperation0;

    }

    private int _generateKeyIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getgenerateKeyInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_generateKeyIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(TransactionKeySoapBindingStub._generateKeyOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("generateKey");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_generateKeyIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public gov.utcourts.transactionkey.ReturnCode generateKey(java.lang.String requestedSystem, java.lang.String callingSystem, java.lang.String key) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getgenerateKeyInvoke0(new java.lang.Object[] {requestedSystem, callingSystem, key}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (gov.utcourts.transactionkey.ReturnCode) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (gov.utcourts.transactionkey.ReturnCode) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), gov.utcourts.transactionkey.ReturnCode.class);
        }
    }

    private static void _staticInit() {
        _generateKeyOperation0 = _getgenerateKeyOperation0();
    }

    static {
       _staticInit();
    }
}