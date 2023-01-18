package ability;

import java.util.ArrayList;

import org.newdawn.slick.geom.Point;

import animation.AnimatedSpriteSheet;
import core.Main;
import unit.Unit;
import world.Cell;

public abstract class AreaTarget extends ActivatedAbility
{	
	protected EffectShape shape;
	protected int size;
	
	public AreaTarget()
	{
		canTargetSelf = true;
	}
	
	public boolean canUse(Cell cell)
	{
		return super.canUse() && cell.isAbilityTarget();
	}
	
	public boolean canUse(Unit target)
	{
		return canUse(target.getCell());
	}
	
	public void use(Cell c)
	{
		use(c.getUnit());
	}
	
	public AnimatedSpriteSheet getTargetImage()
	{
		return shape.getTargetImage(size);
	}
	
	public Point getCursorOffset()
	{
		if(shape == EffectShape.BURST)
		{
			return new Point(size * Main.getCellSize(), size * Main.getCellSize());
		}
		else
		{
			return super.getCursorOffset();
		}
	}
	
	public void use(ArrayList<Cell> targets)
	{
		super.use();	

		for(Cell c : targets)
		{
			animation(c);
		}
	}
	
	public ArrayList<Cell> getTargets(Cell origin)
	{
		return shape.getTargets(origin, size);
	}
	
	// Self animation is optional
	public void animation()
	{
		
	}
	
	public void use(Unit unit) 
	{
		
	}
	
	abstract public void animation(Cell target);
}
