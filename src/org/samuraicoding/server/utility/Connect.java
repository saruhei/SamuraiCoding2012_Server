package org.samuraicoding.server.utility;

import java.util.HashMap;

import org.samuraicoding.server.utility.HttpRequestSender.METHOD;

public class Connect {
	
	
	public Response connecting(String strUrl, METHOD method, HashMap<Object, Object> params) throws Exception{
		
		HttpRequestSender hs = new HttpRequestSender();
		return hs.request(strUrl, method, params);
		
	}

}
