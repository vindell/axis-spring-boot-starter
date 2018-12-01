package org.apache.axis.spring.boot.handler;

import java.rmi.RemoteException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public interface InvokeHandler<T> {

	/**
	 * 对Service进行预处理
	 */
	public void handleServ(Service service);
	
	/**
	 * 对Call进行预处理
	 */
	public T handleCall(Call call,Object[] args) throws RemoteException;
	
}
