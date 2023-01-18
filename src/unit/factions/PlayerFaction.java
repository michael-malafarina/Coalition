package unit.factions;


import java.util.ArrayList;

import unit.Faction;

public abstract class PlayerFaction extends Faction
{
	protected ArrayList<Object> unitTypes;
	
	public PlayerFaction()
	{
		unitTypes = new ArrayList<Object>();

	}
	
	public ArrayList<Object> getUnitTypes()
	{
		
		return unitTypes;

	}
	
	
}
