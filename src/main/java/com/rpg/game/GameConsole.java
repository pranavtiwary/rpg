package com.rpg.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rpg.das.entity.CharacterType;
import com.rpg.das.entity.GameCharacter;
import com.rpg.das.entity.User;
import com.rpg.das.entity.UserCharacter;
import com.rpg.das.entity.UserGame;
import com.rpg.service.IUserService;
import com.rpg.service.impl.UserServiceImpl;
import com.rpg.utility.IOUtility;
import com.rpg.utility.ReaderUtility;

/**
 * GameConsole : Hold logic to display scree to game
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class GameConsole {

	private IUserService userService;

	public static final int SCREEN_MAX_X=10;
	public static final int SCREEN_MAX_Y=10;

	public static final String MOVEMENT_MSG="Please use 1,2,3,4 for Movement : \n"
			+ "Select 1 for move Up\nSelect 2 for move Down\nSelect 3 for move Left\nSelect 4 for move Right\n"
			+ "Select 5 to save and exit\nSelect 6 to exit\n"
			+ "Select 7 to Attack\n";

	private List<GameCharacter> chars;
	private User user;
	private int userGameIndex;
	private int userCharIndex;

	public GameConsole(List<GameCharacter> chars){
		this.chars=chars;
		this.userService=new UserServiceImpl();
	}

	public GameConsole(List<GameCharacter> gameChars, User user, int userGameIndex, int userCharIndex) {
		this.chars=gameChars;
		this.userService=new UserServiceImpl();
		this.user=user;
		this.userGameIndex=userGameIndex;
		this.userCharIndex=userCharIndex;
	}

	public void displayScreen() {
		IOUtility.writeOnConsole("\n\t\t\tGame Console \n");
		Map<String,List<GameCharacter>> map= new HashMap<>();
		int userLocationInList=-1, count=0;;
		for (GameCharacter gameCharacter : chars) {
			if(gameCharacter.getType()==CharacterType.USER){
				userLocationInList=count;
			}
			StringBuilder sb=new StringBuilder();
			sb.append(gameCharacter.getxLocation());
			sb.append("-");
			sb.append(gameCharacter.getyLocation());
			List<GameCharacter> list = map.get(sb.toString());
			if(null==list || list.size()<=0){
				list= new ArrayList<>();
			}
			list.add(gameCharacter);
			map.put(sb.toString(), list);
			count++;
		}
		UserCharacter userChar=null;
		GameCharacter npcAtUserLocation=null;
		StringBuilder charDescription= new StringBuilder();
		StringBuilder consoleString= new StringBuilder();
		for(int i=0;i<SCREEN_MAX_X;i++){
			for(int j=0;j<SCREEN_MAX_Y;j++){
				StringBuilder sb=new StringBuilder();
				sb.append(i);
				sb.append("-");
				sb.append(j);
				List<GameCharacter> list = map.get(sb.toString());
				if(null==list || list.size()==0){
					consoleString.append("-");
					consoleString.append("\t");
					continue;
				}else if(list.size()==1){
					GameCharacter gameCharacter = list.get(0);
					if(CharacterType.NPC ==gameCharacter.getType()){
						consoleString.append("E");
					}else if(CharacterType.USER ==gameCharacter.getType()){
						userChar=(UserCharacter) gameCharacter;
						consoleString.append("U");
					}
				}else{
					GameCharacter gameCharacterOne = list.get(0);
					GameCharacter gameCharacterTwo = list.get(1);
					consoleString.append("EU");
					if(CharacterType.USER ==gameCharacterOne.getType()){
						userChar=(UserCharacter) gameCharacterOne;
						npcAtUserLocation=gameCharacterTwo;
					}else{
						userChar=(UserCharacter) gameCharacterTwo;
						npcAtUserLocation=gameCharacterOne;
					}
				}
				consoleString.append("\t");
			}
			consoleString.append("\n");
		}
		charDescription.append("Your Health : "+userChar.getCurrentHealth());
		charDescription.append("\nYour Power : "+userChar.getCurrentPower());
		charDescription.append("\nYour Agility : "+userChar.getAgilityPercentage());
		charDescription.append("\nYour Experience : "+userChar.getExperience());
		charDescription.append("\nYour Level : "+userChar.getLevel());
		String userLocationInMap=userChar.getxLocation()+"-"+userChar.getyLocation();
		charDescription.append("\nYour Location : "+userLocationInMap);
		if(null!=npcAtUserLocation){
			charDescription.append("\n\nEnemy Health : "+npcAtUserLocation.getCurrentHealth());
			charDescription.append("\nEnemy Power : "+npcAtUserLocation.getCurrentPower());
			charDescription.append("\nEnemy Agility : "+npcAtUserLocation.getAgilityPercentage());
			charDescription.append("\nEnemy Location : "+npcAtUserLocation.getxLocation()+"-"+npcAtUserLocation.getyLocation());
		}
		IOUtility.writeOnConsole(charDescription.toString());
		IOUtility.writeOnConsole("\n");
		IOUtility.writeOnConsole(consoleString.toString());
		getNextPosition(userChar,userLocationInList, npcAtUserLocation);
	}

	public void getNextPosition(GameCharacter userCharacter, int userLocationInList, GameCharacter npcAtUserLocation) {
		int movement = ReaderUtility.readIntegerUntil(1, 7, MOVEMENT_MSG);
		chars.remove(userLocationInList);
		int y,x;
		switch (movement) {
		case 1:
			//UP
			x = userCharacter.getxLocation();
			if(x>0){
				userCharacter.setxLocation(x-1);
			}
			break;
		case 2:
			//DOWN
			x = userCharacter.getxLocation();
			if(x<9){
				userCharacter.setxLocation(x+1);
			}
			break;
		case 3:
			//LEFT
			y = userCharacter.getyLocation();
			if(y>0){
				userCharacter.setyLocation(y-1);
			}
			break;
		case 4:
			//RIGHT
			y = userCharacter.getyLocation();
			if(y<9){
				userCharacter.setyLocation(y+1);
			}
			break;
		case 5:
			//save
			userCharacter.saveCurrentGame(chars);
			List<UserGame> games = user.getGames();
			UserGame userGame = games.get(userGameIndex);
			userGame.getCharacters().remove(userCharIndex);
			userGame.getCharacters().add(userCharIndex, userCharacter);
			userService.saveUser(user);
			IOUtility.writeOnConsole("Saving and Existing Game\n");
			return;
		case 6:
			//exit
			IOUtility.writeOnConsole("Existing Game\n");
			return;
		case 7:
			//attack
			if(null==npcAtUserLocation){
				IOUtility.writeOnConsole("Nothing to attack\n");
			}else{
				attack(userCharacter, npcAtUserLocation);
			}
			break;
		default:
			throw new IllegalStateException("movement not supported");
		}
		chars.add(userCharacter);
		displayScreen();
	}

	private void attack(GameCharacter userCharacter, GameCharacter npcAtUserLocation) {
		int indexOfNpcKilled=-1;
		UserCharacter userChar=(UserCharacter) userCharacter;
		for (GameCharacter gameCharacter : chars) {
			indexOfNpcKilled++;
			if(CharacterType.NPC==gameCharacter.getType() &&
					gameCharacter.getxLocation()==npcAtUserLocation.getxLocation() &&
					gameCharacter.getyLocation()==npcAtUserLocation.getyLocation()){
				break;
			}
		}
		chars.remove(indexOfNpcKilled);
		int health=calculateHeath(userChar.getAgilityPercentage(), userChar.getCurrentHealth(), 
				npcAtUserLocation.getCurrentPower());
		userChar.setCurrentHealth(health);
		int experience=calculateExp(npcAtUserLocation.getCurrentPower(), userChar.getCurrentPower(), userChar.getExperience());
		userChar.setExperience(experience);
		//all dead or experience =10, then increase level;
		if(experience==10 || chars.size()==1){
			userChar.incrementLevel();
		}

	}

	private int calculateExp(int npcCurrentPower, int userCurrentPower, int experience) {
		if(npcCurrentPower>userCurrentPower){
			experience=experience+3;
		}
		if(npcCurrentPower==userCurrentPower){
			experience=experience+2;
		}
		if(npcCurrentPower<userCurrentPower){
			experience=experience+1;
		}
		return experience;
	}

	private int calculateHeath(int useraAgilityPercentage, int userCurrentHealth, int npcCurrentPower) {
		int k = (int)(userCurrentHealth*(useraAgilityPercentage/100.0f));
		userCurrentHealth = userCurrentHealth - k;
		return userCurrentHealth;
	}
}
