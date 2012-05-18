package org.samuraicoding2012.javaee;

import java.util.HashMap;

import org.samuraicoding2012.javaee.utility.HttpRequestSender;
import org.samuraicoding2012.javaee.utility.Response;


public class RailsSender
{
	
	public void post()
	{
		HttpRequestSender sender = HttpRequestSender.getInstance();
		String railsServerUrl = "http://localhost/";
		HashMap<String, String> params = new HashMap<String, String>();
		Response response = sender.request(railsServerUrl, HttpRequestSender.METHOD.POST, params);
		if (response.getResponseCode()==200) {
			
		} else {
			// Failed
		}
	}
	
}
