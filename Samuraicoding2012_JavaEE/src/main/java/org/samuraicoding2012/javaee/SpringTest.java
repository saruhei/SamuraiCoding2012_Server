package org.samuraicoding2012.javaee;

import java.util.HashMap;
import java.util.Map;

import org.samuraicoding2012.javaee.utility.HttpRequestSender;
import org.samuraicoding2012.javaee.utility.Response;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class SpringTest
{
	public static void main(String[] args) throws Exception {
		Resource res = new FileSystemResource("src\\main\\resources\\servlet-context.xml");
		XmlBeanFactory factory = new XmlBeanFactory(res);
		HttpRequestSender sender = (HttpRequestSender)factory.getBean("HttpRequestSender");
		
		HashMap<String, String> m=new HashMap<String, String>();
		m.put("key","value");
		String urlStr="http://localhost:8080/stub-servlet/GameEngineStub";
		Response str=sender.request(urlStr, HttpRequestSender.METHOD.POST, m);
		Thread.sleep(1000);
		System.out.println("-----");
		System.out.println(str);
	}
}
