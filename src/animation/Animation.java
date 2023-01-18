package animation;

import org.newdawn.slick.Graphics;

import core.Color;
import core.GameObject;
import core.Main;

abstract public class Animation extends GameObject
{
	protected int duration;
	protected float x;
	protected float y;
	protected float size;
	protected Color color; 

	public Animation(float x, float y, int duration, int frames, Color color, boolean looping) 
	{	
		this.x = x;
		this.y = y;
		this.duration = duration;
		this.frames = frames;
		this.color = color;
		
		if(!looping)
		{
			frameRate = duration / frames;
		}
	}
	

	
	public Animation(float x, float y, int duration, int frames)
	{
		this(x, y, duration, frames, Color.white, false);
	}
	
	public Animation(float x, float y, int duration, int frames, Color color)
	{
		this(x, y, duration, frames, color, false);
	}

	public boolean isDone() 
	{
		// Negative values represent an object that never goes away
		if(duration < 0)
		{
			return false;
		}
		else
		{
			return getTimer() > duration;
		}

	}

	public void update()
	{
		super.update();
	}
	
	public int getFadeAlphaValue()
	{
		return (int) (255.0f * percentLeft());
	}
	
	public float percentLeft()
	{
		return 1 - ((float) getTimer()) / ((float) duration);
	}
	
	public float percentComplete()
	{
		return ((float) getTimer()) / ((float) duration);
	}	
	
	public void render(Graphics g)
	{
		if(image != null)
		{
	
			image.draw(x, y, image.getWidth() * Main.getGameScale(), image.getHeight() * Main.getGameScale(), color);
		}
		else if(frames - getFrame() > frameRate)
		{
			g.drawString("NO IMAGE", x, y);
		}
	}

}
