/**
 * JtracDeliveryWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cct.linebot.web.jtracdelivery.ws;



public class JtracDeliveryWebServiceServiceLocator extends org.apache.axis.client.Service implements JtracDeliveryWebServiceService {

    public JtracDeliveryWebServiceServiceLocator() {
    }


    public JtracDeliveryWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public JtracDeliveryWebServiceServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for JtracDeliveryWebServicePort
    private String JtracDeliveryWebServicePort_address = "ws url******";

    public String getJtracDeliveryWebServicePortAddress() {
        return JtracDeliveryWebServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private String JtracDeliveryWebServicePortWSDDServiceName = "JtracDeliveryWebServicePort";

    public String getJtracDeliveryWebServicePortWSDDServiceName() {
        return JtracDeliveryWebServicePortWSDDServiceName;
    }

    public void setJtracDeliveryWebServicePortWSDDServiceName(String name) {
        JtracDeliveryWebServicePortWSDDServiceName = name;
    }

    public com.cct.linebot.web.jtracdelivery.ws.JtracDeliveryWebService getJtracDeliveryWebServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(JtracDeliveryWebServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getJtracDeliveryWebServicePort(endpoint);
    }

    public com.cct.linebot.web.jtracdelivery.ws.JtracDeliveryWebService getJtracDeliveryWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cct.linebot.web.jtracdelivery.ws.JtracDeliveryWebServicePortBindingStub _stub = new com.cct.linebot.web.jtracdelivery.ws.JtracDeliveryWebServicePortBindingStub(portAddress, this);
            _stub.setPortName(getJtracDeliveryWebServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setJtracDeliveryWebServicePortEndpointAddress(String address) {
        JtracDeliveryWebServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cct.linebot.web.jtracdelivery.ws.JtracDeliveryWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cct.linebot.web.jtracdelivery.ws.JtracDeliveryWebServicePortBindingStub _stub = new com.cct.linebot.web.jtracdelivery.ws.JtracDeliveryWebServicePortBindingStub(new java.net.URL(JtracDeliveryWebServicePort_address), this);
                _stub.setPortName(getJtracDeliveryWebServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("JtracDeliveryWebServicePort".equals(inputPortName)) {
            return getJtracDeliveryWebServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.jtracdelivery.web.linebot.cct.com/", "JtracDeliveryWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.jtracdelivery.web.linebot.cct.com/", "JtracDeliveryWebServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("JtracDeliveryWebServicePort".equals(portName)) {
            setJtracDeliveryWebServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
