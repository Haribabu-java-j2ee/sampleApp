/**
 * ApplicationAuthenticationServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf071807.01 v22318082452
 */

package gov.utcourts.applicationauthen.ws;

public class ApplicationAuthenticationServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("validate", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc validate0Op = _validate0Op();
        list0.add(validate0Op);

        operationDescriptions.put("ApplicationAuthentication",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _validate0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc validate0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "requestedSystem"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callingSystem"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "key"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "transactionId"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "transactionKey"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
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
        _params0[3].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("inputPosition","3");
        _params0[3].setOption("partName","string");
        _params0[4].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("inputPosition","4");
        _params0[4].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "validateReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://applicationauthen.utcourts.gov", "ReturnCode"), gov.utcourts.applicationauthen.ReturnCode.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partQNameString","{http://applicationauthen.utcourts.gov}ReturnCode");
        _returnDesc0.setOption("outputPosition","0");
        _returnDesc0.setOption("partName","ReturnCode");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        validate0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("validate", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.applicationauthen.utcourts.gov", "validate"), _params0, _returnDesc0, _faults0, null);
        validate0Op.setOption("inoutOrderingReq","false");
        validate0Op.setOption("buildNum","cf071807.01");
        validate0Op.setOption("usingAddressing","false");
        validate0Op.setOption("outputName","validateResponse");
        validate0Op.setOption("ResponseNamespace","http://ws.applicationauthen.utcourts.gov");
        validate0Op.setOption("targetNamespace","http://ws.applicationauthen.utcourts.gov");
        validate0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.applicationauthen.utcourts.gov", "validateResponse"));
        validate0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.applicationauthen.utcourts.gov", "ApplicationAuthentication"));
        validate0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.applicationauthen.utcourts.gov", "ApplicationAuthenticationService"));
        validate0Op.setOption("inputName","validateRequest");
        validate0Op.setOption("ResponseLocalPart","validateResponse");
        validate0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.applicationauthen.utcourts.gov", "validateRequest"));
        validate0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return validate0Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://applicationauthen.utcourts.gov", "ReturnCode"),
                         gov.utcourts.applicationauthen.ReturnCode.class);

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
