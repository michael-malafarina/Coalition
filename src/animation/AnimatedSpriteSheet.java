package animation;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimatedSpriteSheet extends SpriteSheet
{
	private int frames;
	private int duration;
	private boolean looping;
	private int frameRate;
	
	public AnimatedSpriteSheet(String path, int tw, int th, int duration, int frames, int frameRate, boolean looping) throws SlickException 
	{
		super(path, tw, th);
		
		this.frames = frames;
		this.duration = duration;
		this.frameRate = frameRate;
		this.looping = looping;
		
		if(!looping)
		{
			this.frameRate = duration / frames;
		}
	}
	
	public AnimatedSpriteSheet(String path, int tw, int th, int duration, int frames) throws SlickException 
	{
		this(path, tw, th, duration, frames, 0, false);
	}
	
	public int getFrames()						{	return frames;				}
	public int getDuration()					{	return duration;			}
	public int getFrameRate()					{	return frameRate;			}
	public boolean isLooping()					{	return looping;				}

	public void setFrames(int frames)			{	this.frames = frames;		}
	public void setDuration(int duration)		{	this.duration = duration;	}
	public void setFrameRate(int frameRate)		{	this.frameRate = frameRate;	}
	public void setLooping(boolean looping)		{	this.looping = looping;		}


}
