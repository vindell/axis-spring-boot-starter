package org.apache.axis.spring.boot.exception;

import java.rmi.RemoteException;

/**
 * 本地客户端异常信息
 */
public class LocalClientException extends RemoteException {

    private static final long serialVersionUID = 3;
    private String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalClientException() {
        System.out.println("Local Client Exception ");
    }
    
    public void showMessage() {
        System.out.println(this.message);
    }
}