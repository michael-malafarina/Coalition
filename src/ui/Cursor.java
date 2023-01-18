package ui;

import org.newdawn.slick.Image;

public class Cursor
{

	private Image image;
	private int hX;
	private int hY;
	
	
	Cursor(Image image, int hX, int hY)
	{
		this.image = image;
		this.hX = hX;
		this.hY = hY;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public int getHotspotX()
	{
		return hX;
	}
	
	public int getHotspotY()
	{
		return hY;
	}
	
}
