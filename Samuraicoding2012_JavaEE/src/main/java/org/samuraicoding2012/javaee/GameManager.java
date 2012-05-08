package org.samuraicoding2012.javaee;

import java.util.ArrayList;

public class GameManager {
	private Match match;
	private AiConnectionManager[] conManagers;
	public GameManager(Match match){
		this.match = match;
	}
	public void initialize(){
		int nTeam = match.getTeamCount();
		conManagers = new AiConnectionManager[nTeam];
		for(int i=0; i<nTeam; i++){
			conManagers[i] = new AiConnectionManager(match.getStrUrl(i));
		}
	}
}
