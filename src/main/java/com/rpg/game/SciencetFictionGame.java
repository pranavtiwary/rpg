package com.rpg.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rpg.das.entity.GameCharacter;
import com.rpg.das.entity.GameRace;
import com.rpg.das.entity.User;

/**
 * SciencetFictionGame
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class SciencetFictionGame implements IGame {

	@Override
	public String getName() {
		return "ScienceFiction";
	}

	@Override
	public int getId() {
		return 2;
	}
	
	private static final List<GameRace> races;
	static{
		races = new ArrayList<>();
	}

	public List<GameRace> getRaces() {
		return Collections.unmodifiableList(races);
	}

	@Override
	public void buildConsoleForNewGame(User user, GameCharacter userChar, int userGameIndex, int userCharIndex) {
		// TODO Auto-generated method stub
		
	}

}
