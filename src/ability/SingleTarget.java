package ability;

import unit.Unit;
import world.Cell;

public abstract class SingleTarget extends ActivatedAbility
{	
	
	public boolean canUse(Cell cell)
	{
		return super.canUse() && cell.hasUnit() && cell.isAbilityTarget();
	}
	
	public boolean canUse(Unit target)
	{
		return canUse(target.getCell());
	}
	
	public void use(Cell c)
	{
		use(c.getUnit());
	}
	
	public void use(Unit target)
	{
		super.use();	
		animation(target);
	}
	
	// Self animation is optional
	public void animation()
	{
		
	}
	
	abstract public void animation(Unit target);
}
