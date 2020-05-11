package com.rpg.das.entity;

import java.io.Serializable;
/**
 * User Game character
 * @author pranav.tiwary@vuclip.com
 *
 */
public class UserCharacter extends GameCharacter implements Serializable{

	private static final long serialVersionUID = -1741100765830047835L;
	private String charName;
	private int experience;
	private int level;
	private GameRace race;

	public UserCharacter(int selectedGameId){
		super();
		this.experience=0;
		this.level=1;
		setType(CharacterType.USER);
		setGameId(selectedGameId);
	}

	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCharName() {
		return charName;
	}
	public void setCharName(String charName) {
		this.charName = charName;
	}
	public GameRace getRace() {
		return race;
	}
	public void setRace(GameRace race) {
		this.race = race;
	}

	@Override
	public String toString() {
		return "UserCharacter [charName=" + charName + ", experience=" + experience + ", level=" + level + ", race="
				+ race + ", super()=" + super.toString() + "]";
	}

	public void incrementLevel() {
		level++;
		experience=0;
			setCurrentHealth(getRace().getMaxHealthAtLevel(level));
			setCurrentPower(getRace().getMaxPowerAtLevel(level));
			setAgilityPercentage(getRace().getMaxAgilityAtLevel(level));
	}


}
