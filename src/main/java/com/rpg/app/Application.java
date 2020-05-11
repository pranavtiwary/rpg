package com.rpg.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rpg.app.workflow.UserWorkFlow;
import com.rpg.das.entity.GameCharacter;
import com.rpg.das.entity.User;
import com.rpg.das.entity.UserCharacter;
import com.rpg.das.entity.UserGame;
import com.rpg.game.IGame;
import com.rpg.game.adapter.GameAdapter;
import com.rpg.utility.IOUtility;
/**
 * Main Entry class
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class Application {

	private UserWorkFlow userFlow;

	public Application(){
		this.userFlow=new UserWorkFlow();
	}

	public static void main(String[] args){
		try{
			Application app= new Application();
			app.launchApp();
		}catch(Exception ex){
			System.out.println("Error in App");
			ex.printStackTrace();
		}
	}


	private void launchApp() {
		IOUtility.writeOnConsole("Welcome to Role Playing Game\n");
		int userMode=0;
		do{
			IOUtility.writeOnConsole("Press 1 to create user profile\nPress 2 to login\n");
			try{
				userMode = Integer.parseInt(IOUtility.readLine());
			}catch(NumberFormatException ex){}
		}while(userMode<1 || userMode>2);
		User user=null;
		switch(userMode){
		case 1:
			user = userFlow.createUser();
			break;
		case 2:
			user = userFlow.loginUser();
			return;
		}
		launchGame(user);
	}

	private void launchGame(User user) {
		List<UserGame> games = user.getGames();
		Map<Integer, UserCharacter> map = new HashMap<>();
		int index=0;;
		StringBuilder sb= new StringBuilder();
		sb.append("Please choose : ");
		int selectedGameIndex=-1;
		for (UserGame game : games) {
			selectedGameIndex++;
			List<GameCharacter> characters = game.getCharacters();
			for (GameCharacter gameCharacter : characters) {
				UserCharacter userChar=(UserCharacter) gameCharacter;
				sb.append(++index);
				sb.append(" for ");
				sb.append(userChar.getCharName());
				sb.append(" of Race ");
				sb.append(userChar.getRace());
				sb.append("\n");
				map.put(new Integer(index), userChar);
			}
		}
		int selectedCharIndex=0;
		do{
			IOUtility.writeOnConsole(sb.toString());
			try{
				selectedCharIndex = Integer.parseInt(IOUtility.readLine());
			}catch(NumberFormatException ex){
			}
		}while(selectedCharIndex<0 || selectedCharIndex>index);
		UserCharacter userCharacter = map.get(new Integer(selectedCharIndex));
		IGame game=GameAdapter.getGameByid(userCharacter.getGameId());
		game.buildConsoleForNewGame(user, userCharacter, selectedGameIndex, selectedCharIndex-1);
	}
}