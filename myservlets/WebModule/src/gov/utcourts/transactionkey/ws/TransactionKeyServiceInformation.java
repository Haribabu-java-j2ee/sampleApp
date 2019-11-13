/**
 * TransactionKeyServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf071807.01 v22318082452
 */

package gov.utcourts.transactionkey.ws;

public class TransactionKeyServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

    private static java.util.Map operationDescriptions;
    private static java.util.Map typeMappings;

    static {
         initOperationDescriptions();
         initTypeMappings();
    }

    private static void initOperationDescriptions() { 
        operationDescriptions = new java.util.HashMap();

        java.util.Map inner0 = new java.util.HashMap();

        java.util.List list0 = new java.util.ArrayList();
        inner0.put("generateKey", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc generateKey0Op = _generateKey0Op();
        list0.add(generateKey0Op);

        operationDescriptions.put("TransactionKey",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _generateKey0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc generateKey0Op = null;
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
        generateKey0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("generateKey", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.transactionkey.utcourts.gov", "generateKey"), _params0, _returnDesc0, _faults0, null);
        generateKey0Op.setOption("inoutOrderingReq","false");
        generateKey0Op.setOption("buildNum","cf071807.01");
        generateKey0Op.setOption("usingAddressing","false");
        generateKey0Op.setOption("outputName","generateKeyResponse");
        generateKey0Op.setOption("ResponseNamespace","http://ws.transactionkey.utcourts.gov");
        generateKey0Op.setOption("targetNamespace","http://ws.transactionkey.utcourts.gov");
        generateKey0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.transactionkey.utcourts.gov", "generateKeyResponse"));
        generateKey0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.transactionkey.utcourts.gov", "TransactionKey"));
        generateKey0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.transactionkey.utcourts.gov", "TransactionKeyService"));
        generateKey0Op.setOption("inputName","generateKeyRequest");
        generateKey0Op.setOption("ResponseLocalPart","generateKeyResponse");
        generateKey0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.transactionkey.utcourts.gov", "generateKeyRequest"));
        generateKey0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return generateKey0Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://transactionkey.utcourts.gov", "ReturnCode"),
                         gov.utcourts.transactionkey.ReturnCode.class);

        typeMappings = java.util.Collections.unmodifiableMap(typeMappings);
    }

    public java.util.Map getTypeMappings() {
        return typeMappings;
    }

    public Class getJavaType(javax.xml.namespace.QName xmlName) {
        return (Class) typeMappings.get(xmlName);
    }

    public java.util.Map getOperationDescriptions(String portName) {
        return (java.util.Map) operationDescriptions.get(portName);
    }

    public java.util.List getOperationDescriptions(String portName, String operationName) {
        java.util.Map map = (java.util.Map) operationDescriptions.get(portName);
        if (map != null) {
            return (java.util.List) map.get(operationName);
        }
        return null;
    }

}
