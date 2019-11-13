package gov.utcourts.applicationauthen.ws;

public class ApplicationAuthenticationProxy implements gov.utcourts.applicationauthen.ws.ApplicationAuthentication {
  private boolean _useJNDI = true;
  private boolean _useJNDIOnly = false;
  private String _endpoint = null;
  private gov.utcourts.applicationauthen.ws.ApplicationAuthentication __applicationAuthentication = null;
  
  public ApplicationAuthenticationProxy() {
    _initApplicationAuthenticationProxy();
  }
  
  private void _initApplicationAuthenticationProxy() {
  
    if (_useJNDI || _useJNDIOnly) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __applicationAuthentication = ((gov.utcourts.applicationauthen.ws.ApplicationAuthenticationService)ctx.lookup("java:comp/env/service/ApplicationAuthenticationService")).getApplicationAuthentication();
      }
      catch (javax.naming.NamingException namingException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("JNDI lookup failure: javax.naming.NamingException: " + namingException.getMessage());
          namingException.printStackTrace(System.out);
        }
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__applicationAuthentication == null && !_useJNDIOnly) {
      try {
        __applicationAuthentication = (new gov.utcourts.applicationauthen.ws.ApplicationAuthenticationServiceLocator()).getApplicationAuthentication();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__applicationAuthentication != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__applicationAuthentication)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__applicationAuthentication)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __applicationAuthentication = null;
    
  }
  
  public void useJNDIOnly(boolean useJNDIOnly) {
    _useJNDIOnly = useJNDIOnly;
    __applicationAuthentication = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__applicationAuthentication == null)
      _initApplicationAuthenticationProxy();
    if (__applicationAuthentication != null)
      ((javax.xml.rpc.Stub)__applicationAuthentication)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public gov.utcourts.applicationauthen.ReturnCode validate(java.lang.String requestedSystem, java.lang.String callingSystem, java.lang.String key, java.lang.String transactionId, java.lang.String transactionKey) throws java.rmi.RemoteException{
    if (__applicationAuthentication == null)
      _initApplicationAuthenticationProxy();
    return __applicationAuthentication.validate(requestedSystem, callingSystem, key, transactionId, transactionKey);
  }
  
  
  public gov.utcourts.applicationauthen.ws.ApplicationAuthentication getApplicationAuthentication() {
    if (__applicationAuthentication == null)
      _initApplicationAuthenticationProxy();
    return __applicationAuthentication;
  }
  
}