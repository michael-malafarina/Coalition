package ui;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import core.Main;
import ui.elements.UIPanel;

public class UI
{
	static boolean hasFocus = true;
	private static ArrayList<UIPanel> panels;
	
	public static void init()
	{
		panels = new ArrayList<UIPanel>();
	}
	
	public static int width(float percent)
	{
		return (Math.round(Main.getScreenWidth() * percent));
	}
	
	public static int height(float percent)
	{
		return (Math.round(Main.getScreenHeight() * percent));
	}
	
	
	public static void update()
	{
		if(panels.size() == 0)
		{
			hasFocus = false;
		}
		else
		{
			hasFocus = true;
		}
		
		for(UIPanel p : panels)
		{
			p.update();
			
		}
		
		for(int i = 0; i < panels.size(); i++)
		{
			UIPanel p = panels.get(i);
			
			
			if(p.isDone())
			{
				panels.remove(p);
				i--;
			}
		}
	}
	
	public static void render(Graphics g)
	{
		for(UIPanel p : panels)
		{
			p.render(g);
		}
	}

	public static boolean hasFocus()
	{
		return hasFocus;
	}
	
	public static void addPanel(UIPanel p)
	{
		panels.add(p);

	}
	
	public static void removePanel(UIPanel p)
	{
		panels.remove(p);
	}
	
	public static void clear()
	{
		panels.clear();
	}
}
