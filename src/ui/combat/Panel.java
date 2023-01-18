package ui.combat;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Panel 
{
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public int getX()	{		return x;	}
	public int getY()	{		return y;	}

	public int getWidth()		{	return width;	}
	public int getHeight()	{	return height;	}

	//	public void getUnit()
	//	{
	//		return 
	//	}

	public void renderBackground(Graphics g)
	{
		g.setColor(new Color(30, 30, 30, 190));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		
		g.setColor(new Color(30, 30, 30, 255));
		g.setLineWidth(5);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		
	}
	


	public void update()
	{

	}

	public void render(Graphics g)
	{

	}

}
