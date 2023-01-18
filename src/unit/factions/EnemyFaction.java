package unit.factions;


import java.util.ArrayList;

import core.Utility;
import unit.EnemySet;
import unit.Faction;
import unit.Unit;

public abstract class EnemyFaction extends Faction
{
	ArrayList<Object> minions;
	ArrayList<Object> standards;
	ArrayList<Object> elites;
	ArrayList<Object> bosses;
	
	public EnemyFaction()
	{
		minions = new ArrayList<Object>();
		standards = new ArrayList<Object>();
		elites = new ArrayList<Object>();
		bosses = new ArrayList<Object>();
	}
	
	public ArrayList<Object> getUnitTypes()
	{
		ArrayList<Object> unitTypes = new ArrayList<Object>();
		
		unitTypes.addAll(minions);
		unitTypes.addAll(standards);
		unitTypes.addAll(elites);
		unitTypes.addAll(bosses);
		
		return unitTypes;

	}
	
	public Unit getMinion() 
	{
		return getType(minions);
	}

	public Unit getStandard() 
	{		
		return getType(standards);
	}

	public Unit getElite() 
	{
		return getType(elites);
	}

	public Unit getBoss() 
	{
		return getType(bosses);
	}
	
	public Unit getType(ArrayList<Object> list)
	{
		if(list.isEmpty()) return null;
		return getNewUnit(list.get(Utility.random(list.size())));
	}
	
	public EnemySet buildEnemySet(int value)
	{
		return new EnemySet(this, value);
	}
	
	
	
}
