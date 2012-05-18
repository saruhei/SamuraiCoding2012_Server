package org.samuraicoding.server.javaee;

import java.util.ArrayList;


public class Match {
	private String[] strUrls;
	private int matchNum;
	/*
	 * matchNum: number for identifying match
	 * strUrls:  variable number of webapp url. 
	 */
	public Match(int matchNum, String... strUrls){
		this.matchNum = matchNum;
		this.strUrls = new String[strUrls.length];
		int i = 0;
		for(String strUrl: strUrls){
			strUrls[i++] = strUrl;
			if(i >= Configuration.LIMIT_TEAM_NUMBER)
				break;
		}
	}	
	
	public int getTeamCount(){
		return strUrls.length;
	}
	
	// null checking shoud be done for return value
	public String getStrUrl(int index){
		try{
			return strUrls[index];			
		}catch(ArrayIndexOutOfBoundsException ae){
			return "";
		}
	}
	
	public String[] getStrUrls(){
		return strUrls;
	}
	
	public int getMatchNum(){
		return matchNum;
	}

}
