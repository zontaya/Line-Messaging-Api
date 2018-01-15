package com.cct.linebot.web.jtracdelivery.ws;

public class JtracDeliveryWebServiceProxy implements JtracDeliveryWebService {
  private String _endpoint = null;
  private JtracDeliveryWebService jtracDeliveryWebService = null;
  
  public JtracDeliveryWebServiceProxy() {
    _initJtracDeliveryWebServiceProxy();
  }
  
  public JtracDeliveryWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initJtracDeliveryWebServiceProxy();
  }
  
  private void _initJtracDeliveryWebServiceProxy() {
    try {
      jtracDeliveryWebService = (new JtracDeliveryWebServiceServiceLocator()).getJtracDeliveryWebServicePort();
      if (jtracDeliveryWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)jtracDeliveryWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)jtracDeliveryWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (jtracDeliveryWebService != null)
      ((javax.xml.rpc.Stub)jtracDeliveryWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public JtracDeliveryWebService getJtracDeliveryWebService() {
    if (jtracDeliveryWebService == null)
      _initJtracDeliveryWebServiceProxy();
    return jtracDeliveryWebService;
  }
  
  public void addFriend(String lineUserId, String email, boolean active) throws java.rmi.RemoteException{
    if (jtracDeliveryWebService == null)
      _initJtracDeliveryWebServiceProxy();
    jtracDeliveryWebService.addFriend(lineUserId, email, active);
  }
  
  public void recivceMessage(String lineUserId, String message, String channelAccessToken) throws java.rmi.RemoteException{
    if (jtracDeliveryWebService == null)
      _initJtracDeliveryWebServiceProxy();
    jtracDeliveryWebService.recivceMessage(lineUserId, message, channelAccessToken);
  }
  
  
}