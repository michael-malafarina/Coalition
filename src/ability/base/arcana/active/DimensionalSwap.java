package ability.base.arcana.active;

import java.util.ArrayList;

import ability.MultiTarget;
import ability.Tag;
import ability.disciplines.Arcana;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;
import world.Cell;

public class DimensionalSwap extends MultiTarget
{
	public void setup() 
	{
		discipline = new Arcana();
		name = "Dimensional Swap";
		canTargetSelf = true;
		isSupport = true;
		
		level = 2;
		range = 4;
		energy = 3;

		tags.add(Tag.MAGICAL);
		tags.add(Tag.RANGED);

	}
	
	public void use(ArrayList<Unit> targets)
	{
		super.use(targets);

		Unit a = targets.get(0);
		Unit b = targets.get(1);
		Cell aCell = a.getCell();
		Cell bCell = b.getCell();
		
		aCell.removeUnit();
		b.setCell(aCell);
		a.setCell(bCell);
	}
	
	public void sound()
	{
		Sounds.teleport.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animTeleport, target));
	}
	
	public String getDescription() 
	{
		return "Switch the location of any two units in range.";
	}

}
