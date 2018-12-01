package org.apache.axis.spring.boot;


import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.spring.boot.exception.LocalClientException;
import org.apache.axis.spring.boot.exception.RemoteServerException;


public class TryExceptionClient {

    /**
     * <b>function:</b>捕捉服务器端异常信息的WebService的客户端
     * @author hoojo
     * @createDate Dec 17, 2010 00:12:56 AM
     * @param args
     * @throws ServiceException 
     * @throws RemoteException 
     */
    public static void main(String[] args) {
        String url = "http://localhost:8080/AxisWebService/services/ThrowException";
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            
            /**
             * 注册异常类信息和序列化类
             * ns:CustomException 和 wsdd 配置文件中的typeMapping中的xmlns:myNSD="ns:CustomException"的对应
             * Exception 和 wsdd 配置文件中的typeMapping中的qname="myNSD:Exception"的Exception对应
             */
            QName qn = new QName("ns:CustomException", "Exception");
            /**
             * 这里配置的LocalClientException，会将服务器端的RemoteServerException转换成本地的异常信息LocalClientException
             */
            call.registerTypeMapping(LocalClientException.class, qn, 
                new BeanSerializerFactory(LocalClientException.class,qn), 
                new BeanDeserializerFactory(LocalClientException.class, qn));
            call.setOperationName(new QName(url, "doException"));
            
            call.setTargetEndpointAddress(url);
            call.invoke(new Object[]{});
        } catch (RemoteServerException e) {
            e.showMessage();
            System.out.println("RemoteServerException:" + e.getMessage());
            e.printStackTrace();
        } catch (LocalClientException e) {
            e.showMessage();
            System.out.println("LocalClientException:" + e.getMessage());
            e.printStackTrace();
        } catch (RemoteException e) {
            System.out.println("RemoteException:" + e.getMessage());
            e.printStackTrace();
        } catch (ServiceException e) {
            System.out.println("ServiceException:" + e.getMessage());
            e.printStackTrace();
        }
    }
}