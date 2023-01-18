package ui.combat.healthbar;

import org.newdawn.slick.Graphics;

import unit.Unit;

public class UnitBars 
{
	Healthbar health;
	Guardbar armor;
	ConditionDisplay conditions;
	
	public UnitBars(Unit unit)
	{
		health = new Healthbar(unit);
		armor = new Guardbar(unit);
		conditions = new ConditionDisplay(unit);

	}
	
	public void render(Graphics g)
	{
		health.render(g);
		armor.render(g);
		conditions.render(g);
	}
	
	public void update()
	{
		health.update();
		armor.update();
		conditions.update();
	}
}
