package ability;

import unit.Unit;
import world.Cell;

public abstract class SelfTarget extends ActivatedAbility
{
	public SelfTarget()
	{
		super();
		canTargetSelf = true;
	}
	
	public void use(Cell cell)
	{
		use();
	}
	
	
	public void use(Unit unit)
	{
		use();
	}
	
	public void use()
	{
		super.use();
		
	}
	
	public boolean canUse()
	{
		return super.canUse();
	}
	
	public boolean canUse(Cell cell)
	{
		return super.canUse() && cell == getOwner().getCell();
	}
	
	public boolean canUse(Unit unit)
	{
		return super.canUse() && unit == getOwner();
	}
	
	
}
