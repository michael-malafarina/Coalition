package ui.sound.music;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import core.Settings;

public class Song 
{
	private String file;
	private String artist;
	private String title;
	private Music music;
	
	public Song(String artist, String title, String file)
	{
		this.artist = artist;
		this.title = title;
		this.file = file;		
	}
	
	Song(String artist, String title, String file, boolean isSpecial)
	{
		this.artist = artist;
		this.title = title;
		this.file = file;		
	}
	
	public boolean isLoaded()
	{
		return music != null;
	}
	
	public void loadMusic() throws SlickException
	{		
		music = new Music(file);
//		music.loop();
	}
	
	public void playMusic()
	{
		music.play();
		music.setVolume(Settings.musicVolume);

	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getArtist()
	{
		return artist;
	}
	
	public Music getMusic() throws SlickException
	{
		return music;
	}

}
