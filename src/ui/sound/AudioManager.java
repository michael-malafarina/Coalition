package ui.sound;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import core.Settings;
import ui.sound.music.Song;
import ui.sound.sfx.SoundTime;

public class AudioManager 
{	
	private static Song gameMusic;
	private static ArrayList<SoundTime> delayedSounds;
	
	/************** Setup **************/
	
	public static void loadSFX() throws SlickException 
	{
		Sounds.loadSFX();
		delayedSounds = new ArrayList<SoundTime>();
	}
	
	public static void loadMusic() throws SlickException 
	{
		Sounds.loadSongList();
		playGameMusic();
	}
	
	public static void leave()
	{
		delayedSounds.clear();
		gameMusic = null;
	}
	
	/************** Music **************/

	public static Song getGameMusic()
	{
		return gameMusic;
	}
	

	public static void playGameMusic() throws SlickException
	{
		if(Settings.musicOn) 
		{
			gameMusic = Sounds.getRandomSong();		
			
			if(!gameMusic.isLoaded())
			{
				gameMusic.loadMusic();
			}		
			
			gameMusic.playMusic();
		}
	}
	
	public static void setMusicVolume(float volume)
	{
		try 
		{
			if(getGameMusic() != null && getGameMusic().getMusic() != null)
			{
				getGameMusic().getMusic().setVolume(volume);
			}
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public static void update()
	{		
		try
		{
			if(Settings.musicOn && !getGameMusic().getMusic().playing())
			{
				gameMusic = null;
				
				playGameMusic();
			}
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	/************** SFX **************/
	
	public static void addDelayedSound(SoundTime st)
	{
		delayedSounds.add(st);
	}

}
