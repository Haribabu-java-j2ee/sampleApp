package gov.utcourts.transactionkey.ws;

public class TransactionKeyProxy implements gov.utcourts.transactionkey.ws.TransactionKey {
  private boolean _useJNDI = true;
  private boolean _useJNDIOnly = false;
  private String _endpoint = null;
  private gov.utcourts.transactionkey.ws.TransactionKey __transactionKey = null;
  
  public TransactionKeyProxy() {
    _initTransactionKeyProxy();
  }
  
  private void _initTransactionKeyProxy() {
  
    if (_useJNDI || _useJNDIOnly) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __transactionKey = ((gov.utcourts.transactionkey.ws.TransactionKeyService)ctx.lookup("java:comp/env/service/TransactionKeyService")).getTransactionKey();
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
    if (__transactionKey == null && !_useJNDIOnly) {
      try {
        __transactionKey = (new gov.utcourts.transactionkey.ws.TransactionKeyServiceLocator()).getTransactionKey();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__transactionKey != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__transactionKey)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__transactionKey)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __transactionKey = null;
    
  }
  
  public void useJNDIOnly(boolean useJNDIOnly) {
    _useJNDIOnly = useJNDIOnly;
    __transactionKey = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__transactionKey == null)
      _initTransactionKeyProxy();
    if (__transactionKey != null)
      ((javax.xml.rpc.Stub)__transactionKey)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public gov.utcourts.transactionkey.ReturnCode generateKey(java.lang.String requestedSystem, java.lang.String callingSystem, java.lang.String key) throws java.rmi.RemoteException{
    if (__transactionKey == null)
      _initTransactionKeyProxy();
    return __transactionKey.generateKey(requestedSystem, callingSystem, key);
  }
  
  
  public gov.utcourts.transactionkey.ws.TransactionKey getTransactionKey() {
    if (__transactionKey == null)
      _initTransactionKeyProxy();
    return __transactionKey;
  }
  
}