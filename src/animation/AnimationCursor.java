package animation;

import core.Color;

public class AnimationCursor extends Animation 
{	
	AnimatedSpriteSheet animSheet;
	
	public AnimationCursor(AnimatedSpriteSheet sheet, int x, int y, Color color) 
	{
		super(x, y, sheet.getDuration(), sheet.getFrames(), color, sheet.isLooping());
		this.animSheet = sheet;
		this.frameRate = sheet.getFrameRate();
		this.sheet = sheet;
		looping = true;
	}
			
	public AnimationCursor(AnimatedSpriteSheet sheet, int x, int y) 
	{
		this(sheet, x, y, Color.white);
	}

	public void offsetPosition(float xOff, float yOff)
	{
		x -= xOff;
		y -= yOff;
	}
	
	public AnimatedSpriteSheet getImage()
	{
		return animSheet;
	}
	
	public void update(int x, int y, Color c)
	{
		super.update();
	
		this.x = x;
		this.y = y;
		this.color = c;
		
		if(image == null)
		{
			return;
		}
	}
		
}
