package unit.classes.empire;

import unit.ComputerUnit;

public class ComputerUnitMelee extends ComputerUnit
{
	public void movement()
	{
		moveTowardNearestEnemy();	
	
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
