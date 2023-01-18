package unit.manager;

import java.util.ArrayList;

import unit.Unit;
import world.Cell;
import world.Map;

public class UnitManager 
{
	static void startLocations(ArrayList<Unit> units, int x, int y)
	{			
		for(Unit u : units)
		{

			while(!Map.getCell(x, y).canEnter())
			{
				Cell c = Map.getCell(x, y).getRandomNeighbor();
				x = c.getX();
				y = c.getY();
			}
			
			u.setCell(Map.getCell(x, y));
			
		//	u.setCell(Map.getCell(1, 1));
		}
	}
	
	static boolean isDefeated(ArrayList<Unit> units)
	{
		for(Unit u : units)
		{
			if(u.isAlive())
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	public static ArrayList<Unit> getAllUnits()
	{
		ArrayList <Unit> units = new ArrayList<Unit>();
		
		units.addAll(CoalitionManager.getUnits());
		units.addAll(EmpireManager.getUnits());

		return units;
		
	}
	
	public static ArrayList<Unit> getCoalitionUnits()
	{
		return CoalitionManager.getUnits();
	}
	
	public static ArrayList<Unit> getEmpireUnits()
	{
		return EmpireManager.getUnits();
	}
	
	public static int getHighestMaxHealth()
	{
		int highestMaxHealth = 0;
		
		for(Unit u : getAllUnits())
		{
			if(u.getMaxHealth() > highestMaxHealth)
			{
				highestMaxHealth = u.getMaxHealth();
			}
		}
		
		return highestMaxHealth;
	}

	
	
}
