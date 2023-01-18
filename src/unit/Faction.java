package unit;

import java.util.ArrayList;

import core.Color;
import core.Utility;

abstract public class Faction 
{
	public final static int MIN_ENEMIES = 8;
	public final static int MAX_ENEMIES = 16;
	
	
	protected UnitColor primaryColor;
	protected UnitColor secondaryColor;
	
	public Faction()
	{
		
	}
	
	public Color getPrimaryColor()
	{
		return primaryColor.getColor();
	}
	
	public Color getSecondaryColor()
	{
		return secondaryColor.getColor();
	}
	
	
	public Unit getNewUnit()
	{
		Unit u = getRandomUnit();	
		u.setFaction(this);

		return u;
	}
	
	public Unit getNewUnit(Object o)
	{
		Unit u = factionUnitFactory(o);	
		u.setFaction(this);
		return u;
	}
	
	abstract public  ArrayList<Object> getUnitTypes();

	public Unit getRandomUnit()
	{
		return factionUnitFactory(getUnitTypes().get(Utility.random(getUnitTypes().size())));
	}
	
	public Unit factionUnitFactory(Object o)
	{
		Class<? extends Unit> clazz = (Class<? extends Unit>) o;

		Unit u = null;
		
		try
		{
			// When I create a new condition, set its duration to the actual duration after modifiers
			u = clazz.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e) 
		{
			e.printStackTrace();
		}	
		
//		System.out.println("Unit Factory produces a " + u);
//		System.out.println("   hp " +  u.getMaxHealth());

		return u;
	}


	
}
