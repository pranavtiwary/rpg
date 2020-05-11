package com.rpg.das.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User class hold user info
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 3522947114559171702L;
	
	private String name;
	private String pwd;
	private List<UserGame> games= new ArrayList<>();
	
	public User(String name, String pwd) {
		this.name=name;
		this.pwd=pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public List<UserGame> getGames() {
		return games;
	}
	public void addGames(UserGame game) {
		this.games.add(game);
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", pwd=" + pwd + ", games=" + games + "]";
	}
}
