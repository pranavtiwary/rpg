package com.rpg.das.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * All user games
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class UserGame implements Serializable{
	
	private static final long serialVersionUID = -2571747125774113929L;
	private int gameId;
	private List<GameCharacter> characters= new ArrayList<>();
	
	public UserGame(int selectedGameId) {
		gameId=selectedGameId;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public List<GameCharacter> getCharacters() {
		return characters;
	}
	public void addCharacters(GameCharacter characters) {
		this.characters.add(characters);
	}
	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", characters=" + characters + "]";
	}
	
}
