package org.samuraicoding2012.javaee.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HttpRequestSender
{
	static HttpRequestSender self;

	public enum METHOD
	{
		GET, POST
	}

	public <K extends Object, V extends Object> Response request(String strUrl, METHOD method,
			Map<K, V> params)
	{
		switch (method)
		{
			case GET:
				return getRequest(strUrl, params);
			case POST:
				return postRequest(strUrl, params);
			default:
				return null;
		}
	}

	private <K extends Object, V extends Object> Response getRequest(
			String strUrl, Map<K, V> params)
	{
		StringBuffer json = new StringBuffer();
		String isLine;
		Set<Entry<K, V>> entries = params.entrySet();
		strUrl += "?";
		Response res = new Response();
		for (Entry<K, V> entry : entries)
		{
			K key = entry.getKey();
			V value = entry.getValue();
			strUrl += key.toString() + "=" + value.toString() + "&";
		}
		strUrl = strUrl.substring(0, strUrl.length() - 1);
		Debugger.print("GET: " + strUrl);
		try{
			URL url = new URL(strUrl);
			HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setRequestMethod("GET");
			urlconn.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlconn.getInputStream()));
			while ((isLine = reader.readLine()) != null)
			{
				json.append(isLine);
			}
			reader.close();
			int responseCode = urlconn.getResponseCode();
			res.setResponseCode(responseCode);
			res.setData(json.toString());
		}catch(Exception e){
			res.setException(e);
			res.setException(true);
		}
		return res;
	}

	private <K extends Object, V extends Object> Response postRequest(
			String strUrl, Map<K, V> params)
	{
		StringBuffer json = new StringBuffer();
		String isLine;
		String paramStr = hash2String(params);
		Response res = new Response();
		Debugger.print("POST: " + strUrl);
		Debugger.print("POST PARAMS: " + paramStr);
		try{
			URL url = new URL(strUrl);
			HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
			urlconn.setRequestMethod("POST");
			urlconn.setDoOutput(true);
			urlconn.connect();
			OutputStreamWriter osw = new OutputStreamWriter(
					urlconn.getOutputStream());
			osw.write(paramStr);
			osw.flush();
			osw.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlconn.getInputStream()));
			while ((isLine = reader.readLine()) != null)
			{
				json.append(isLine);
			}
			reader.close();
			int responseCode = urlconn.getResponseCode();
			res.setResponseCode(responseCode);
			res.setData(json.toString());
		}catch(Exception e){
			res.setException(e);
			res.setException(true);
		}
		return res;
	}
	
	private  <K extends Object, V extends Object>  String hash2String(Map<K,V> params){
		StringBuilder ret = new StringBuilder();
		Set<Entry<K,V>> set = params.entrySet();
		for(Entry<K,V> entry : set){
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			ret.append(key+"="+value+"&");
		}
		ret.deleteCharAt(ret.length()-1);
		return ret.toString();
	}
	
	public static synchronized HttpRequestSender getInstance()
	{
		if (self == null)
		{
			self = new HttpRequestSender();
		}
		return self;
	}
}
