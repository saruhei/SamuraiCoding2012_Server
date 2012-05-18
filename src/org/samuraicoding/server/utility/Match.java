package org.samuraicoding.server.utility;

import org.samuraicoding.server.javaee.Configuration;


public class Match {
	private String[] strUrls;
	private int matchNum;
	private int[] scores;
	private boolean isFinished;
	public boolean isFinished()
	{
		return isFinished;
	}

	public void setFinished(boolean isFinished)
	{
		this.isFinished = isFinished;
	}

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
	public int[] getScores(){
		return scores;
	}
	public int getScore(int index){
		try{
			return scores[index];
		}catch(ArrayIndexOutOfBoundsException ae){
			return -1;
		}catch(NullPointerException ne){
			return -1;
		}
	}
	public void setScores(int[] scores){
		this.scores = scores;
	}

}
