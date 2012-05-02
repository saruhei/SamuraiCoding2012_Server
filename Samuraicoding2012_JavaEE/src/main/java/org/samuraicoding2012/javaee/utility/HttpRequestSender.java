package org.samuraicoding2012.javaee.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
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

	public <K extends Object, V extends Object> String request(String strUrl, METHOD method,
			HashMap<K, V> params) throws Exception
	{
		switch (method)
		{
			case GET:
				return getRequest(strUrl, params);
			case POST:
				return postRequest(strUrl, params);
			default:
				return "";
		}
	}

	private <K extends Object, V extends Object> String getRequest(
			String strUrl, Map<K, V> params) throws Exception
	{
		StringBuffer json = new StringBuffer();
		String isLine;
		Set<Entry<K, V>> entries = params.entrySet();
		strUrl += "?";
		for (Entry<K, V> entry : entries)
		{
			K key = entry.getKey();
			V value = entry.getValue();
			strUrl += key.toString() + "=" + value.toString() + "&";
		}
		strUrl = strUrl.substring(0, strUrl.length() - 1);
		Debugger.print("GET: " + strUrl);
		URL url = new URL(strUrl);
		HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
		urlconn.setRequestMethod("GET");
		urlconn.connect();//接続，同時にリクエスト？？
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlconn.getInputStream()));
		while ((isLine = reader.readLine()) != null)//レスポンス受信
		{
			json.append(isLine);
		}
		reader.close();
		int responseCode = urlconn.getResponseCode();
		return responseCode + ":" + json.toString();
	}

	private <K extends Object, V extends Object> String postRequest(
			String strUrl, Map<K, V> params) throws Exception
	{
		StringBuffer json = new StringBuffer();
		String isLine;
		//Map<String,String>m=new HashMap<String, String>();
		String paramStr = hash2String2(params);
		Debugger.print("POST: " + strUrl);
		Debugger.print("POST PARAMS: " + paramStr);
		URL url = new URL(strUrl);
		HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
		urlconn.setRequestMethod("POST");
		urlconn.setDoOutput(true);
		urlconn.connect();
		OutputStreamWriter osw = new OutputStreamWriter(
				urlconn.getOutputStream());
		osw.write(paramStr);//パラメータを文字列化したやつをpost?リクエスト送信。
		osw.flush();
		osw.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlconn.getInputStream()));
		while ((isLine = reader.readLine()) != null)//レスポンス受信
		{
			json.append(isLine);
		}
		reader.close();
		int responseCode = urlconn.getResponseCode();

		return responseCode + ":" + json.toString();
	}
	
	private String hash2String(Map<String,String> params){
		StringBuilder ret = new StringBuilder();
		Set<Entry<String,String>> set = params.entrySet();
		for(Entry<String,String> entry : set){
			String key = entry.getKey();
			String value = entry.getValue();
			ret.append(key+"="+value+",");
		}
		ret.deleteCharAt(ret.length()-1);
		return ret.toString();
	}
	private  <K extends Object, V extends Object>  String hash2String2(Map<K,V> params){
		StringBuilder ret = new StringBuilder();
		Set<Entry<K,V>> set = params.entrySet();
		for(Entry<K,V> entry : set){
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();
			ret.append(key+"="+value+",");
		}
		ret.deleteCharAt(ret.length()-1);
		return ret.toString();
	}
	
	//シングルトン
	public static synchronized HttpRequestSender getInstance()
	{
		if (self == null)
		{
			self = new HttpRequestSender();
		}
		return self;
	}
}
