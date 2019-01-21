package org.apache.axis.spring.boot.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.spring.boot.Parameter;
import org.apache.axis.spring.boot.handler.InvokeHandler;



public final class AxisClientUtils {

	//实例化Service对象
	protected static Service service = new Service();
	
	/**
	 * @param endpoint		: 调用地址
	 * @param optName		: WSDL里面描述的操作名
	 * @param usesoap		: 是否使用SOAP模式
	 * @param returnType	: 返回参数类型，如：org.apache.axis.encoding.XMLType.XSD_STRING
	 * @param params		: 参数
	 * @return 返回结果
	 * @throws ServiceException ServiceException
	 * @throws MalformedURLException MalformedURLException
	 * @throws RemoteException RemoteException
	 */
	public static Object invoke(String endpoint, QName optName,boolean usesoap,QName returnType,Parameter ... params) throws ServiceException, MalformedURLException, RemoteException{
		//创建调用对象
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(endpoint));
		// WSDL里面描述的操作名
		call.setOperationName(optName);
		//设置参数
		Object[] args = new Object[params.length];
		for (int i = 0; i < params.length; i++) {
			Parameter param = params[i];
			//组织参数
			args[i] = param.getValue();
			//设置远程调用接口类型
			call.addParameter(param.getName(), param.getXmlType(), param.getMode());
		}
		call.setReturnType(returnType);
		call.setUseSOAPAction(usesoap);
		//执行调用，并返回结果
		return call.invoke(args);
	}
	
	public static <T> T invoke(String targetURL,InvokeHandler<T> handler,Parameter ... params) throws ServiceException, MalformedURLException, RemoteException{
		//创建调用对象
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new URL(targetURL));
		//设置参数
		Object[] args = new Object[params.length];
		for (int i = 0; i < params.length; i++) {
			Parameter paramModel = params[i];
			//组织参数
			args[i] = paramModel.getValue();
			//设置远程调用接口类型
			call.addParameter(paramModel.getName(), paramModel.getXmlType(), paramModel.getMode());
		}
		//执行调用，并返回结果
		return handler.handleCall(call,args);
	}
	
}
