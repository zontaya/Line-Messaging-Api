/**
 * JtracDeliveryWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cct.linebot.web.jtracdelivery.ws;

public interface JtracDeliveryWebService extends java.rmi.Remote {
    public void addFriend(String lineUserId, String email, boolean active) throws java.rmi.RemoteException;
    public void recivceMessage(String lineUserId, String message, String channelAccessToken) throws java.rmi.RemoteException;
}
