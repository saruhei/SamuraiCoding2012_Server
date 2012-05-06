package org.samuraicoding2012.javaee.utility;

import java.util.ArrayList;

public class Match {
	
	ArrayList<String> matchList = new ArrayList<String>();
	private int matchNum;
	
	public Match(int matchNum){
		this.matchNum = matchNum;
	}
	
	public void matching(String url){
		matchList.add(url);	
		
		if(matchList.size() == 4){
			GameTread gs = new GameTread();
			gs.startgame(matchList,matchNum);
		}
	}
	

}
