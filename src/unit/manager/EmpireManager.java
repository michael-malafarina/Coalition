package unit.manager;

import java.util.ArrayList;

import core.CampaignManager;
import core.Utility;
import unit.EnemySet;
import unit.Unit;
import unit.factions.EnemyFaction;
import unit.factions.Factions;
import world.Cell;
import world.Map;

public class EmpireManager
{
	protected static ArrayList<Unit> units;
	protected static EnemyFaction faction;

	public static void init()
	{
		units = new ArrayList<Unit>();
		faction = Factions.getEnemyFaction();
	}

	public static EnemyFaction getFaction()			{		return faction;	}
	public void setFaction(EnemyFaction f)			{		faction = f;	}

	public static ArrayList<Unit> getUnits()	{		return units;	}
	public static void addUnit(Unit u)			{		units.add(u);	}

	public static void clear()					{		units.clear();	}	


	public static void setStartLocations()
	{	
		for(Unit u : units)
		{			
			setStartLocation(u);
		}
	}

	public static void setStartLocation(Unit u)
	{
	//	int infiniteBreaker = 0;
		
		Cell c = Map.getRandomOpenCell();
			
		
		if(Utility.getDistance(c, Map.getStartLocation()) > 4)
		{
			u.setCell(c);
		}
		else
		{
			setStartLocation(u);
		}


	}

	public static void beginCombat()
	{	

		clear();


		EnemySet enemies = getFaction().buildEnemySet(CampaignManager.getCombatDifficulty());

		for(Unit u : enemies.getUnits())
		{

			addUnit(u);
			//	System.out.println("adding " + u);

		}




	}

	public static boolean isDefeated()
	{
		return UnitManager.isDefeated(units);
	}




}
