package org.apache.axis.spring.boot.handler;

import java.rmi.RemoteException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;


public class DefaultInvokeHandler implements InvokeHandler<Object> {

	@Override
	public void handleServ(Service service) {
		
	}

	@Override
	public Object handleCall(Call call, Object[] args) throws RemoteException {
		return call.invoke(args);
	}

}
