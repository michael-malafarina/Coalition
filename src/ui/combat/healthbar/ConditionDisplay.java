package ui.combat.healthbar;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import core.Main;
import modifier.conditions.Condition;
import unit.Unit;

public class ConditionDisplay 
{
	protected Unit unit;
	ArrayList<Condition> conditions;
	int x;
	int y;
	int h;
	
	
	
	ConditionDisplay(Unit unit)
	{
		this.unit = unit;
		conditions = unit.getModifiers().getConditions();

	}
	
	public void update()
	{
		conditions = unit.getModifiers().getConditions();
		
		int index = 2;
		
		if(unit.getGuard() > 0)
		{
			index = 3;
		}
		h = 4 * Main.getGameScale();
		x = unit.getXPixel() + 1 * Main.getGameScale();
		y = unit.getYPixel() - Main.getCellSize() / 2 - h * index - 5 * Main.getGameScale();
		//y = unit.getYPixel() + Main.getCellSize() / 2 - h * index - 3 * Main.getGameScale();

		//if()
		
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < conditions.size(); i++)
		{
			Condition c = conditions.get(i);
			c.renderIcon(g, x + i * (Condition.ICON_SIZE + 1 )* Main.getGameScale(), y);
		}
	}
}
