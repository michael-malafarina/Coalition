package unit.classes.empire;

import unit.ComputerUnit;

public class ComputerUnitRanged extends ComputerUnit
{

	public void movement()
	{
		//System.out.println(this + " is moving");
		kite();
		
//		else
//		{
//			endMove();
//		}	
	}
	
	public void action()
	{
		if(hasEnemyInRange(getBasicAttack()))
		{
			basicAttackEnemyInRange();
		}
		else
		{
			endAction();
		}

	}
}
