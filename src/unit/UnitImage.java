package unit;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import core.Color;
import core.Main;

public class UnitImage
{
	private Unit owner;
	
	private Image imageBase;
	private Image imagePrimary;
	private Image imageSecondary;
	

	
	public UnitImage(Unit owner, Image base, Image primary, Image secondary) 
	{	
		this.owner = owner;
		this.imageBase = base;
		this.imagePrimary = primary;
		this.imageSecondary = secondary;
	}
	
	public UnitImage(Unit owner, SpriteSheet sheet, int frame) 
	{	
		this(owner, sheet.getSprite(frame, 0), sheet.getSprite(frame, 1), sheet.getSprite(frame, 2));
	}
	
	public UnitImage(Unit owner, SpriteSheet sheet) 
	{	
		this(owner, sheet.getSprite(0, 0), sheet.getSprite(0, 1), sheet.getSprite(0, 2));
	}
	
	public Color getColorPrimary() 				{	return owner.getColorPrimary();				}
	public Color getColorSecondary() 			{	return owner.getColorSecondary();			}
	
//	public void setColorPrimary(Color c) 		{	owner.setColorPrimary(c);}
//	public void setColorSecondary(Color c) 		{	owner.setColorSecondary(c);	}
	
	
	public void draw(float x, float y, float w, float h, Color colorBase, Color colorPrimary, Color colorSecondary)
	{
		imageBase.draw(x, y, w, h, colorBase);
	
		imagePrimary.draw(x, y, w, h, colorPrimary);
		
		imageSecondary.draw(x, y, w, h, colorSecondary);
	}
	
	public void draw(float x, float y, float w, float h)
	{
		draw(x, y, w, h, Color.white, getColorPrimary(), getColorSecondary());
	}
	
	public void draw(float x, float y)
	{
		int w = Main.getCellSize() * Main.getGameScale();
		int h = w * 2;
		draw(x, y, w, h);
	}
	
	public void draw(float x, float y, int scale)
	{
		int w = Main.getCellSize() * scale;
		int h = w * 2;
		draw(x, y, w, h);
	}
	
	public void drawCentered(float x, float y)
	{
		drawCentered(x, y, 1);
	}
	
	public void drawCentered(float x, float y, int scale)
	{
		int w = Main.getCellSize() * scale;
		int h = w * 2;
		draw(x - w / 2, y - h / 2, w, h);
	}
	
	public UnitImage getFlippedCopy(boolean h, boolean v)
	{
		Image a = imageBase.getFlippedCopy(h, v);
		Image b = imagePrimary.getFlippedCopy(h, v);
		Image c = imageSecondary.getFlippedCopy(h, v);
		
		return new UnitImage(owner, a, b, c);
	}
	




}
