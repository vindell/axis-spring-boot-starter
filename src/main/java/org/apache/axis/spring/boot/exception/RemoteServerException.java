package org.apache.axis.spring.boot.exception;

import java.rmi.RemoteException;

/**
 * 服务器端异常信息
 */
public class RemoteServerException extends RemoteException {
	
    private static final long serialVersionUID = 1L;
    private String message;
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RemoteServerException() {
        System.out.println("Remote Servier Exception ");
    }
    
    public void showMessage() {
        System.out.println(this.message);
    }
}
