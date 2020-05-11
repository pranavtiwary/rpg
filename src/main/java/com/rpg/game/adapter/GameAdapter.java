package com.rpg.game.adapter;

import java.util.ArrayList;
import java.util.List;

import com.rpg.game.IGame;
import com.rpg.game.SciencetFictionGame;
import com.rpg.game.WorldOfWarCraftGame;

/**
 * Keep track of all loaded instance
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class GameAdapter {

	private static final List<IGame> games;
	static{
		games=new ArrayList<>();
		games.add(new WorldOfWarCraftGame());
		games.add(new SciencetFictionGame());
	}

	public static List<IGame> listOfGames() {
		return new ArrayList<>(games);
	}

	public static IGame getGameByid(int selectedGameId) {
		return games.get(selectedGameId);
	}
}
