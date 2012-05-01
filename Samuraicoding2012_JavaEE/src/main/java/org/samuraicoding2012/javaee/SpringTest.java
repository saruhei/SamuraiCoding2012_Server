package org.samuraicoding2012.javaee;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class SpringTest
{
	public static void main(String[] args) throws Exception {
		Resource res = new FileSystemResource("servlet-context.xml");
		XmlBeanFactory factory = new XmlBeanFactory(res);
		HttpRequestSender sender = (HttpRequestSender)factory.getBean("HttpRequestSender");
	}
}
