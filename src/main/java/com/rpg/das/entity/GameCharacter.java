package com.rpg.das.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Game Character can be User or NPC
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public abstract class GameCharacter implements Serializable{

	private static final long serialVersionUID = -3946615045973247255L;
	
	private int currentHealth;
	private int currentPower;
	private int agilityPercentage;
	private int xLocation;
	private int yLocation;
	private CharacterType type;
	private int gameId;
	private List<GameCharacter> savedGame= null;
	
	public GameCharacter(){
		xLocation=0;
		yLocation=0;
	}
	
	public int getxLocation() {
		return xLocation;
	}
	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}
	public int getyLocation() {
		return yLocation;
	}
	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	public int getCurrentPower() {
		return currentPower;
	}
	public void setCurrentPower(int currentPower) {
		this.currentPower = currentPower;
	}
	public int getAgilityPercentage() {
		return agilityPercentage;
	}
	public void setAgilityPercentage(int agilityPercentage) {
		this.agilityPercentage = agilityPercentage;
	}

	public CharacterType getType() {
		return type;
	}

	public void setType(CharacterType type) {
		this.type = type;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	@Override
	public String toString() {
		return "GameCharacter [currentHealth=" + currentHealth + ", currentPower=" + currentPower
				+ ", agilityPercentage=" + agilityPercentage + ", xLocation=" + xLocation + ", yLocation=" + yLocation
				+ ", type=" + type + ", gameId=" + gameId + "]";
	}

	public void saveCurrentGame(List<GameCharacter> chars) {
		savedGame=new ArrayList<>();
		savedGame.addAll(chars);
	}
}
