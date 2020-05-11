package com.rpg.das.entity;
/**
 * NPC char
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class NPCChar extends GameCharacter{
	private static final long serialVersionUID = -5573742263915679586L;

	public NPCChar() {
		setType(CharacterType.NPC);
	}
}
