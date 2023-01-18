package ui.sound.sfx;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import core.Settings;

public class SmartSound 
{	
	private Sound[] sounds;
	private String file;

	public SmartSound(String file)
	{
		sounds = new Sound[1];
		this.file = file;
		load();
	}
	
	public SmartSound(String file, int size)
	{
		sounds = new Sound[size];
		this.file = file;
		load();
	}

	//public void playOnce
	
	public void play(float pitch, float volume) 
	{
		if(sounds.length == 1)
		{
			sounds[0].play(pitch, volume);
		}
		else
		{
			int r = (int) (Math.random() * sounds.length);
			sounds[r].play(pitch, volume);	
		}
	}
	
	public void play(float pitch)
	{
		if(Settings.sfxOn)
		{
			play(pitch, Settings.soundVolume);
		}
	}
	
	public void play()
	{
		if(Settings.sfxOn)
		{
			play(1, Settings.soundVolume);
		}
	}

	public void load()
	{
		try
		{
			// Load individual sounds
			if(sounds.length == 1)
			{
				sounds[0] = new Sound("res/sfx/" + file + ".wav");
				return;
			}
			
			// Load sets
			for(int i = 0; i < sounds.length; i++)
			{
				sounds[i] = new Sound("res/sfx/" + file + i + ".wav");
			}
		}	
		catch (SlickException e) 
		{
			e.printStackTrace();
		}


	}

}
