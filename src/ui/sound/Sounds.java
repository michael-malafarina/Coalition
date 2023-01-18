package ui.sound;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import core.Utility;
import ui.sound.music.Song;
import ui.sound.sfx.SmartSound;

public class Sounds 
{

	// Abilities
	public static SmartSound armorUp;
	public static SmartSound grunt;

	public static SmartSound slash;
	public static SmartSound slashHeavy;
	public static SmartSound firebolt;
	public static SmartSound fireball;
	public static SmartSound bashHeavy;
	public static SmartSound startCombat;
	public static SmartSound winTheme;
	public static SmartSound heal;
	public static SmartSound teleport;
	public static SmartSound slow;
	public static SmartSound haste;
	public static SmartSound magicImpact;
	public static SmartSound arrow;

	
	static ArrayList<Song> songs;

	public static void loadGameMusicFile(Song m) throws SlickException
	{
		m.loadMusic();
	}
	
	public static Song getRandomSong()
	{
		return songs.get(Utility.random(0, songs.size()-1));	
	}
	
	static void loadSFX() throws SlickException 
	{
		Sounds.armorUp = new SmartSound("armorUp");
		Sounds.grunt = new SmartSound("grunt");
		Sounds.slash = new SmartSound("slash");
		Sounds.slashHeavy = new SmartSound("slashHeavy");
		Sounds.firebolt = new SmartSound("firebolt");		
		Sounds.fireball = new SmartSound("fireball");		
		Sounds.bashHeavy = new SmartSound("bashHeavy");		
		Sounds.startCombat = new SmartSound("startCombat");		
		Sounds.winTheme = new SmartSound("winTheme");		
		Sounds.heal = new SmartSound("heal");	
		Sounds.teleport = new SmartSound("teleport");			
		Sounds.slow = new SmartSound("slow");			
		Sounds.haste = new SmartSound("haste");		
		Sounds.magicImpact = new SmartSound("magicImpact");		
		Sounds.arrow = new SmartSound("arrow");		

	}
	
	static void loadSongList() throws SlickException 
	{		
		String path = "res/music/";
		
		songs = new ArrayList<Song>();
		
		songs.add(new Song("???", "Driving Force", path + "drivingForce.wav"));		
		songs.add(new Song("???", "Ice And Fire", path + "iceAndFire.wav"));		
		songs.add(new Song("???", "Knight Power", path + "knightPower.wav"));
		songs.add(new Song("???", "Protection of Kingdom", path + "protectionOfKingdom.wav"));

	}


	
}
