package ability;

import java.util.ArrayList;

import unit.Unit;
import world.Cell;

public abstract class MultiTarget extends ActivatedAbility
{	
//	ArrayList<Unit> targets;
	protected int numTargets;
	
	public int getNumTargets()
	{
		return numTargets;
	}
		
	public boolean canUse(Cell cell)
	{
		return super.canUse() && cell.hasUnit();
	}
	
	public boolean canUse(Unit target)
	{
		return canUse(target.getCell());
	}
	
	public void use(Unit u)
	{
		System.out.println("Error: Ability.Multitarget takes multiple targets");
	}
	
	public void use(Cell c)
	{
		System.out.println("Error: Ability.Multitarget takes multiple targets");
	}
	
	public void use(ArrayList<Unit> targets)
	{
		super.use();	
		
		for(Unit u : targets)
		{
			animation(u);
		}
		
	}
		
	// Self animation is optional
	public void animation()
	{
		
	}
	
	abstract public void animation(Unit target);
}
