package com.rpg.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rpg.das.entity.GameCharacter;
import com.rpg.das.entity.GameRace;
import com.rpg.das.entity.NPCChar;
import com.rpg.das.entity.User;
import com.rpg.utility.CommonUtility;

/**
 * World of war craft game
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class WorldOfWarCraftGame implements IGame {
	
	public static int MAX_NPC_HEALTH=10;
	public static int MAX_NPC_ATTACK=5;
	

	@Override
	public String getName() {
		return "World of Warcraft";
	}

	@Override
	public int getId() {
		return 1;
	}

	private static final List<GameRace> races;
	static{
		races = new ArrayList<>();
		races.add(new GameRace(1,"Elf",80,70,30,30,10,15));
		races.add(new GameRace(2,"Human",70,90,25,25,15,12));
		races.add(new GameRace(3,"Dwarf",60,100,40,20,30,8));
	}

	public List<GameRace> getRaces() {
		return Collections.unmodifiableList(races);
	}
	
	public void buildConsoleForNewGame(User user, GameCharacter userChar,  int userGameIndex, int userCharIndex){
		List<GameCharacter> gameChars=new ArrayList<>();
		gameChars.add(userChar);
		do{
			int x = CommonUtility.getRandomXInRange();
			int y = CommonUtility.getRandomYInRange();
			int hp = CommonUtility.getRandomNPCHealth(MAX_NPC_HEALTH);
			int pow = CommonUtility.getRandomNPCPower(MAX_NPC_ATTACK);
			GameCharacter npcChar=new NPCChar();
			npcChar.setAgilityPercentage(0);
			npcChar.setCurrentHealth(hp);
			npcChar.setCurrentPower(pow);
			npcChar.setxLocation(x);
			npcChar.setyLocation(y);
			gameChars.add(npcChar);
		}while(gameChars.size()!=10);
		GameConsole console= new GameConsole(gameChars, user, userGameIndex, userCharIndex);
		console.displayScreen();
	}
}
