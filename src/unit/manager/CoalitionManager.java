package unit.manager;

import java.util.ArrayList;

import unit.Faction;
import unit.Unit;
import unit.factions.Factions;
import world.Map;

public class CoalitionManager 
{
	protected static ArrayList<Unit> units;
	
	
	public static void init()
	{
		units = new ArrayList<Unit>();
	}
	
	public static Faction getFaction()
	{
		return Factions.hero;
	}
	
	public static ArrayList<Unit> getUnits()
	{
		return units;
	}
		
	public static void addUnit(Unit u)
	{
		units.add(u);
	}
	
	public static void clear()
	{
		units.clear();
	}
	
	public static boolean isDefeated()
	{
		return UnitManager.isDefeated(units);
	}
	
	public static void setStartLocations()
	{	
		UnitManager.startLocations(units, (int) Map.getStartLocation().getX(), (int) Map.getStartLocation().getY());
		
		//System.out.println("Party @ Start" + units.size());

	}
	
	public static int getSize()
	{
		return units.size();
	}
	
	public static void update()
	{
		for(Unit u : units)
		{
			u.getAbilities().sortAbilities();
		}
	}
	
	public static void addSkillPoints(int amount)
	{
		for(Unit u : units)
		{
			u.gainSkillPoints(amount);
		}
	}
	

	
//	public static void restore()
//	{
//		for(Unit u : units)
//		{
//			u.restoreHealth();
//			u.restoreCharges();
//		}
//	}
}
