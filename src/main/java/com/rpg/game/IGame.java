package com.rpg.game;

import java.util.List;

import com.rpg.das.entity.GameCharacter;
import com.rpg.das.entity.GameRace;
import com.rpg.das.entity.User;
import com.rpg.das.entity.UserCharacter;
import com.rpg.utility.IOUtility;

public interface IGame {

	String getName();

	int getId();

	public default GameCharacter createNewCharacterFlow(int selectedGameId) {
		IOUtility.writeOnConsole("Please choose Name : ");
		String charName=IOUtility.readLine();
		IOUtility.writeOnConsole("Please choose Race : ");
		StringBuilder sb= new StringBuilder();
		for (GameRace race : getRaces()) {
			sb.append("Please choose : ");
			sb.append(race.getRaceId());
			sb.append(" for ");
			sb.append(race);
			sb.append("\n");
		}
		int selectedRaceId=0;
		do{
			IOUtility.writeOnConsole(sb.toString());
			try{
				selectedRaceId = Integer.parseInt(IOUtility.readLine());
			}catch(NumberFormatException ex){}
		}while(selectedRaceId<1 || selectedRaceId>getRaces().size());
		GameRace race = getRaces().get(selectedRaceId-1);
		UserCharacter gameCharacter = new UserCharacter(selectedGameId);
		gameCharacter.setAgilityPercentage(race.getStartingAgilityPercentage());
		gameCharacter.setCurrentHealth(race.getStartingHealth());
		gameCharacter.setCurrentPower(race.getStartingPower());
		gameCharacter.setCharName(charName);
		gameCharacter.setRace(race);
		return gameCharacter;
	}

	List<GameRace> getRaces();
	
	public void buildConsoleForNewGame(User user, GameCharacter userChar,  int userGameIndex, int userCharIndex);
}
