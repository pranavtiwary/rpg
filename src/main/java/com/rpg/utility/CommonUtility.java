package com.rpg.utility;

import java.util.Random;

import com.rpg.game.GameConsole;

/**
 * Common utlity
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class CommonUtility {

	public static int getRandomXInRange() {
		Random r = new Random();
		return r.nextInt((GameConsole.SCREEN_MAX_X - 1) + 1) + 1;
	}
	
	public static int getRandomYInRange() {
		Random r = new Random();
		return r.nextInt((GameConsole.SCREEN_MAX_Y - 1) + 1) + 1;
	}

	public static int getRandomNPCHealth(int max) {
		Random r = new Random();
		return r.nextInt((max - 1) + 1) + 1;
	}
	
	public static int getRandomNPCPower(int max) {
		Random r = new Random();
		return r.nextInt((max - 1) + 1) + 1;
	}
}
