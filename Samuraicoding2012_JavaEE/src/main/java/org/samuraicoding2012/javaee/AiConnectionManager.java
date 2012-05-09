package org.samuraicoding2012.javaee;

import java.util.Map;

import org.samuraicoding2012.javaee.utility.HttpRequestSender;
import org.samuraicoding2012.javaee.utility.Response;

public class AiConnectionManager {
	
	private String strUrl;
	private long overTimeSum;
	
	public AiConnectionManager(String strUrl){
		this.strUrl = strUrl;
		this.overTimeSum = 0;
	}
	
	private long getLatency(){
		return 0;
	}
	
	public long getOverTimeSum(){
		return overTimeSum;
	}
	
	public Response request(Map<String, String> params){
		HttpRequestSender hs = HttpRequestSender.getInstance();
		// In addition to game data, sum of the overtime should be sent.
		params.put("overTimeSum",(overTimeSum+""));
		long start = System.currentTimeMillis();
		Response res = hs.request(strUrl, HttpRequestSender.METHOD.GET, params);
		long calcTime = System.currentTimeMillis() - start - getLatency();
		long overTime = calcTime - Configuration.AI_CALC_TIME;
		if(overTime > 0){
			overTimeSum += overTime;
		}
		// If sum of the overtime exceed limit, response should be null.
		if(overTimeSum > Configuration.LIMIT_OVERTIME){
			res.setData(null);
		}
		return res;
	}

}
