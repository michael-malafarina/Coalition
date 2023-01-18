package ui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import core.Main;
import core.Settings;
import unit.Unit;
import world.Map;

public class Camera 
{	
	final public static int EDGE = 6;
	
	static int viewOffsetX = 0;
	static int viewOffsetY = 0;
	
	static boolean panning = false;
	static int oldMouseX;
	static int oldMouseY;
	
//	public void centerCamera(GameObject o)
//	{		
//		viewOffsetX = -o.getX() * Cell.CELL_SIZE + Main.getScreenWidth()/2;
//		viewOffsetY = -o.getY() * Cell.CELL_SIZE + Main.getScreenHeight()/2;
//	}
	
	private static void enforceEdges()
	{
		
		int buffer = Main.getCellSize() * EDGE;
		
		int eastEdge = Main.getScreenWidth() - Map.getWidthPixel() - buffer;
		int westEdge = buffer;

		int southEdge = Main.getScreenHeight() - Map.getHeightPixel() - buffer;
		int northEdge = buffer;
		
		if(viewOffsetX < eastEdge)
		{
			viewOffsetX = eastEdge;
		}
		
		if(viewOffsetX > westEdge)
		{
			viewOffsetX = westEdge;
		}
	
		if(viewOffsetY < southEdge)
		{
			viewOffsetY = southEdge;
		}
		
		if(viewOffsetY > northEdge)
		{
			viewOffsetY = northEdge;
		}
	}
	
	private static void addViewOffset(float x, float y)
	{	
		viewOffsetX -= x;
		viewOffsetY -= y;

		enforceEdges();
	}

	public static void init()
	{
//		viewOffsetX = (Main.getScreenWidth() + Map.getWidth() * Main.getCellSize()) / 2;
	}
	
	public static void centerCamera(int x, int y)
	{
		viewOffsetX = Main.getScreenWidth() / 2 - x - Main.getCellSize() / 2;
		viewOffsetY = Main.getScreenHeight() / 2 - y - Main.getCellSize() / 2;

		enforceEdges();
		
		//viewOffsetY = -y + Main.getCellSize()/2 + Main.getScreenHeight() ;
		
	//	System.out.println(viewOffsetX);
		
		//enforceEdges();

		
//		addViewOffset(-x + Main.getScreenWidth()/2, -y + Main.getScreenHeight()/2);
	}

	public static void centerCamera(Unit u)
	{
		centerCamera(u.getXPixel(), u.getYPixel());
	}
	
//	public static void setViewOffsetX(int value)
//	{
//		viewOffsetX = value;
//	}
//
//	public static void setViewOffsetY(int value)
//	{
//		viewOffsetY = value;
//	}
	
	public static int getViewOffsetX()
	{
		return viewOffsetX;
	}
	
	public static int getViewOffsetY()
	{
		return viewOffsetY;
	}
	
	public static void panRight(int amount)
	{
		addViewOffset(amount, 0);
	}
	
	public static void panLeft(int amount)
	{
		addViewOffset(-amount, 0);
	}
	
	public static void panUp(int amount)
	{
		addViewOffset(0, -amount);
	}
	
	public static void panDown(int amount)
	{
		addViewOffset(0, amount);
	}
	
	public static void panCamera(float deltaX, float deltaY) 
	{		
		if(Main.getInput().isMouseButtonDown(1))
		{
			if(!panning)
			{
				oldMouseX = Mouse.getX();
				oldMouseY = Mouse.getY();
				panning = true;
				Mouse.setGrabbed(true);
			}
	
			if(Settings.invertCameraDrag)
			{
				deltaX *= -1;
				deltaY *= -1;
			}
			
			addViewOffset(deltaX, deltaY);		
		}
		else
		{
			
			if(panning)
			{
				Mouse.setCursorPosition(oldMouseX, oldMouseY);
				panning = false;
				Mouse.setGrabbed(false);
			}
		}
	}
	
	
	public static void enable(Graphics g)
	{
		g.translate(viewOffsetX, viewOffsetY);
	}
	
	
	public static void disable(Graphics g)
	{
		g.translate(-viewOffsetX, -viewOffsetY);
	}
	
	
	public static void update()
	{
		if(UI.hasFocus()) return;
		
		if(Main.getInput().isKeyDown(Input.KEY_W))		{	panUp(Settings.cameraPanSpeed);			}
		if(Main.getInput().isKeyDown(Input.KEY_A))		{	panLeft(Settings.cameraPanSpeed);		}
		if(Main.getInput().isKeyDown(Input.KEY_S))		{	panDown(Settings.cameraPanSpeed);		}
		if(Main.getInput().isKeyDown(Input.KEY_D))		{	panRight(Settings.cameraPanSpeed);		}
	
	}
	
	public static void mouseMoved(int oldX, int oldY, int newX, int newY) 
	{
		panCamera(newX - oldX, newY - oldY);
	}

	public static void mouseDragged(int oldX, int oldY, int newX, int newY) 
	{
		panCamera(newX - oldX, newY - oldY);
	}
	
}
