package org.samuraicoding.server.javaee;

import java.util.HashMap;
import java.util.Map;

import org.samuraicoding.server.utility.Response;

public class GameManager {
	private Match match;
	private int nTeam;
	private AiConnectionManager[] conManagers;
	public GameManager(Match match){
		this.match = match;
		initialize();
	}
	private void initialize(){
		nTeam = match.getTeamCount();
		conManagers = new AiConnectionManager[nTeam];
		for(int i=0; i<nTeam; i++){
			conManagers[i] = new AiConnectionManager(match.getStrUrl(i));
		}
	}
	public void proceedGame(){
		String action = "";
		for(int i=0; i<nTeam; i++){
			String gameData = GameEngineStub.proceed(action);
			/*
			 * when the match is finished
			 * expected value is : "score:10,30,25,30,50,60,20"
			 * and something like that
			 */
			if(gameData.startsWith("score:")){
				gameData.replaceAll("score:", "");
				String scoreStrs[] = gameData.split(",");
				int scores[] = new int[scoreStrs.length];
				for(String scoreStr : scoreStrs){
					try{
						scores[i++] = Integer.parseInt(scoreStr);
					}catch(NumberFormatException nfe){
						nfe.printStackTrace();
					}
				}
			}
			Map<String,String> params = new HashMap<String,String>();
			params.put("gameData", gameData);
			Response res = conManagers[i].request(params);
			if(!res.isException())
				action = res.getData();
			else
				action = null;
		}
	}
}
