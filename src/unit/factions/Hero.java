package unit.factions;

import unit.UnitColor;
import unit.classes.coalition.Cleric;
import unit.classes.coalition.Knight;
import unit.classes.coalition.Veteran;
import unit.classes.coalition.Wizard;

public class Hero extends PlayerFaction
{

	Hero()
	{
		super();
		
		primaryColor = UnitColor.BLUE;
		secondaryColor = UnitColor.RED;
		
		unitTypes.add(Knight.class);
		unitTypes.add(Veteran.class);
		unitTypes.add(Cleric.class);
		unitTypes.add(Wizard.class);


	}
	

	
}
