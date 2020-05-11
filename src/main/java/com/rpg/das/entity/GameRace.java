package com.rpg.das.entity;

import java.io.Serializable;

/**
 * Type of game char for a game instance
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class GameRace implements Serializable{

	private static final long serialVersionUID = -4857834466721540876L;
	private int raceId;
	private String raceName;
	private int maxHealth;
	private int maxPower;
	private int maxAgilityPercentage;

	private int startingHealth;
	private int startingPower;
	private int startingAgilityPercentage;

	public GameRace(int raceId, String raceName, int maxHealth, int maxPower, int maxAgilityPercentage, int startingHealth,
			int startingPower, int startingAgilityPercentage) {
		super();
		this.raceId = raceId;
		this.raceName = raceName;
		this.maxHealth = maxHealth;
		this.maxPower = maxPower;
		this.maxAgilityPercentage = maxAgilityPercentage;
		this.startingHealth = startingHealth;
		this.startingPower = startingPower;
		this.startingAgilityPercentage = startingAgilityPercentage;
	}
	public int getStartingHealth() {
		return startingHealth;
	}
	public int getStartingPower() {
		return startingPower;
	}
	public int getStartingAgilityPercentage() {
		return startingAgilityPercentage;
	}
	public int getRaceId() {
		return raceId;
	}
	public String getRaceName() {
		return raceName;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public int getMaxPower() {
		return maxPower;
	}
	public int getMaxAgilityPercentage() {
		return maxAgilityPercentage;
	}
	public String toConsoleString() {
		return "RaceName=" + raceName + ", MaxHealth=" + maxHealth + ", MaxPower="
				+ maxPower + ", MaxAgilityPercentage=" + maxAgilityPercentage + ", StartingHealth=" + startingHealth
				+ ", StartingPower=" + startingPower + ", StartingAgilityPercentage=" + startingAgilityPercentage;
	}

	@Override
	public String toString() {
		return "Race [raceId=" + raceId + ", raceName=" + raceName + ", maxHealth=" + maxHealth + ", maxPower="
				+ maxPower + ", maxAgilityPercentage=" + maxAgilityPercentage + ", startingHealth=" + startingHealth
				+ ", startingPower=" + startingPower + ", startingAgilityPercentage=" + startingAgilityPercentage + "]";
	}
	public int getMaxHealthAtLevel(int level) {
		int health=startingHealth + (level*5);
		if(health>maxHealth)
			return maxHealth;
		else
			return health;
	}
	public int getMaxPowerAtLevel(int level) {
		int pow=startingPower + (level*2);
		if(pow>maxPower)
			return maxPower;
		else
			return pow;
	}
	public int getMaxAgilityAtLevel(int level) {
		int agility=startingAgilityPercentage + (level*1);
		if(agility>maxAgilityPercentage)
			return maxAgilityPercentage;
		else
			return agility;
	}

}
