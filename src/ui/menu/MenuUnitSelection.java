package ui.menu;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import ui.UI;
import ui.elements.MenuPanel;
import unit.Unit;
import unit.manager.CoalitionManager;

public class MenuUnitSelection extends MenuPanel
{
	ArrayList<MenuUnitButton> menuUnits;
	
	public MenuUnitSelection() 
	{
		super(0, UI.height(.2f), UI.width(.20f), UI.height(.8f));

	}
	
	public void update()
	{
		super.update();
		menuUnits = new ArrayList<MenuUnitButton>();
		
		for(int i = 0; i < CoalitionManager.getUnits().size(); i++)
		{
			Unit u = CoalitionManager.getUnits().get(i);
			
			float x = getX();
			float y = getY() + i * getHeight() / 6;
			float w = getWidth();
			float h = getHeight() / 6;
			MenuUnitButton m = new MenuUnitButton(u, x, y, w, h);
			menuUnits.add(m);
			
			m.update();
		}
		

	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		for(MenuUnitButton m : menuUnits)
		{
			m.render(g);
		}
	}

}
