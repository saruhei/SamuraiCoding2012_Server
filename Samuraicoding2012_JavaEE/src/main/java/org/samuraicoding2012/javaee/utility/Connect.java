package org.samuraicoding2012.javaee.utility;

import java.util.HashMap;

import org.samuraicoding2012.javaee.utility.HttpRequestSender.METHOD;

public class Connect {
	
	
	public String connecting(String strUrl, METHOD method, HashMap<Object, Object> params) throws Exception{
		
		HttpRequestSender hs = new HttpRequestSender();
		return hs.request(strUrl, method, params);
		
	}

}
