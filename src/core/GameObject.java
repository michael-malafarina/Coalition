package core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public abstract class GameObject 
{
	protected int timer;
	protected int currentFrame;
		
	protected SpriteSheet sheet;
	protected Image imageBase;
	protected Image image;
	protected boolean looping;
	
	protected int frames;
	protected int frameRate;

	
	public GameObject()
	{

	}
	
	public int getTimer()			{	return timer;				}
	public int getFrame()			{	return currentFrame;		}
	
	public int getFramerate()		{	return 1;					}
	
	public void update()
	{
		timer++;
		
		animation();

		
	}

	public void animation()
	{
		if(frameRate > 0 && timer % frameRate == 0)
		{
			currentFrame++;
		}

		if(looping && currentFrame >= frames)
		{
			currentFrame = 0;
		}

		
		if(sheet != null && currentFrame < frames)
		{
			imageBase = sheet.getSprite(currentFrame, 0);
			image = sheet.getSprite(currentFrame, 0);
		}
		else if(currentFrame == 0 && frames == 0)
		{
			imageBase = sheet.getSprite(0, 0);
			image = sheet.getSprite(0, 0);		
		}
		else
		{
			sheet = null;
			imageBase = null;
			image = null;
		}
	}

	abstract public void render(Graphics g);

}
