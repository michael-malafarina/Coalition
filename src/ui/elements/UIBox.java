package ui.elements;

import org.newdawn.slick.Graphics;

import core.Color;
import core.Main;
import ui.MenuColor;

public abstract class UIBox 
{
	protected Color background;
	protected Color outline;
	protected boolean isDone;
	
	protected float x;
	protected float y;
	protected float w;
	protected float h;
	
	public float getX()			{	return x;	}
	public float getY()			{	return y;	}

	public float getWidth()		{	return w;	}
	public float getHeight()	{	return h;	}
	
	public UIBox(float x, float y, float w, float h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		background = MenuColor.MENU_BACKGROUND.getColor();
		outline =MenuColor.MENU_OUTLINE.getColor();
		
//		background = new Color(30, 30, 30, 190);
//		outline = new Color(30, 30, 30, 255);
	}
	
	public UIBox(float x, float y)
	{
		this(x, y, 50, 50);
	}
		
	public void update()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.setLineWidth(2 * Main.getGameScale());
		g.setColor(background);
		g.fillRect(x,  y,  w,  h);
		g.setColor(outline);
		g.drawRect(x,  y,  w,  h);

	}
	
	public void centerAt(float x, float y, float w, float h)
	{

		this.x = x - w / 2;
		this.h = y - h / 2;
		this.w = w;
		this.h = h;
	}
	
	public boolean isDone()
	{
		return isDone;
	}
	
	
}
