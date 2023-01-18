package ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Main;
import world.Cell;
import world.Map;

public class Mouse
{
	static Cursor cursor;
	
	public static int getX()					{	return getXPixel() / Main.getCellSize();	}
	
	public static int getXPixel()				{	return Main.getInput().getMouseX() - Camera.getViewOffsetX();	}
	
	public static int getXPixelGrid()			{	return getX() * Main.getCellSize();	}
	public static int getXPixelGridCentered()	{	return getXPixelGrid() + Main.getCellSize() / 2;}

	public static int getY()					{	return getYPixel() / Main.getCellSize();	}
	public static int getYPixel()				{	return Main.getInput().getMouseY() - Camera.getViewOffsetY();	}
	public static int getYPixelGrid()			{	return getY() * Main.getCellSize();	}
	public static int getYPixelGridCentered()	{	return getYPixelGrid() + Main.getCellSize() / 2;}

	// Ignores camera
	public static int getXPixelScreen()			{	return Main.getInput().getMouseX();	}
	public static int getYPixelScreen()			{	return Main.getInput().getMouseY();	}

	
	public static boolean inBounds()
	{
		return Map.inBounds(getX(),  getY());
	}
	
	public static Cell getCell()
	{
		return inBounds() ? Map.getCell(getX(),  getY()) : null;
	}
	
	public static void renderCursor(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillOval(getXPixel()-5,  getYPixel()-5,  10,  10);
	}
	
	public static void setMouseCursor(Cursor c)
	{
		if(cursor == c)
		{
			return;
		}		
		
		cursor = c;
		
		try
		{
			Main.getGameContainer().setMouseCursor(cursor.getImage(), cursor.getHotspotX(), cursor.getHotspotY());
		}
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
}
