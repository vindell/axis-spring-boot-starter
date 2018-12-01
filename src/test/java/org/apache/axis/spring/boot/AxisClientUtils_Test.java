package org.apache.axis.spring.boot;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.spring.boot.utils.AxisClientUtils;
import org.apache.axis.utils.XMLUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;
import org.w3c.dom.Element;


public class AxisClientUtils_Test {

	@Test
	public  void tets() throws MalformedURLException, RemoteException, ServiceException {
		
		String nameSpace="http://ems.zz.com";
        String endpoint = "http://10.71.33.168:8081/ws/api/ems/logistics/query";
        
        String mailNum = "9700264462100";
        String userId = "d5d2ad6f5b634585d5d2ad6f5b63458";
		
        Parameter[] par = new Parameter[] {
        		new Parameter("mailNum", XMLType.XSD_STRING, mailNum),
        		new Parameter("userId", XMLType.XSD_STRING, userId)
        };
        
        try {
			Object res = AxisClientUtils.invoke(endpoint, new QName(nameSpace, "getDataByAxis"), false, XMLType.XSD_STRING, par);
			System.out.println(res);
        } catch (AxisFault e) {
        	
        	//System.out.println("dumpToString:" + e.dumpToString());
        	System.out.println("getMessage:" +e.getMessage());
			System.out.println("getFaultActor:" +e.getFaultActor());
			System.out.println("getFaultNode:" +e.getFaultNode());
			System.out.println("getFaultReason:" +e.getFaultReason());
			System.out.println("getFaultRole:" +e.getFaultRole());
			System.out.println("getFaultString:" +XMLUtils.xmlEncodeString(e.getFaultString()));
			System.out.println("getFaultCode:" +e.getFaultCode());
			System.out.println("==========================");
			if (e.getFaultDetails() != null) {
	            for (int i=0; i < e.getFaultDetails().length; i++) {
	                Element el = (Element) e.getFaultDetails()[i];
	                System.out.println("getNamespaceURI:" + (null == el.getNamespaceURI() ? "" : el.getNamespaceURI()));
	                System.out.println("getLocalName:" + (null == el.getLocalName() ? "" : el.getLocalName()));
	                System.out.println(StringEscapeUtils.unescapeHtml4(XMLUtils.getInnerXMLString(el)));
	                
	            }
	        }
			System.out.println("==========================");
			System.out.println(e.getFaultActor());
			//e.printStackTrace();
			if(e.getFaultReason().contains("java.net.ConnectException")) {
				System.out.println("服务在维护");
			}
		}
        
	}
	
}
