package ability.special.growth.active;

import java.util.ArrayList;

import ability.AreaTarget;
import ability.EffectShape;
import ability.Tag;
import ability.disciplines.Growth;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Regen;
import ui.Images;
import ui.sound.Sounds;
import world.Cell;

public class Tranquility extends AreaTarget
{
	public void setup() 
	{
		discipline = new Growth();
		name = "Tranquility";
		isSupport = true;

		level = 4;
		range = 5;
		energy = 5;
		duration = 2;
		size = 2;
		
		conditions.add(Regen.class);
		
		shape = EffectShape.BURST;

		tags.add(Tag.HEAL);
		tags.add(Tag.MAGICAL);

	}
	
	public void use(ArrayList<Cell> targets)
	{
		super.use(targets);
		
		for(Cell c : targets)
		{
			if(c.hasUnit() && c.getUnit().isFriendly(getOwner()))
			{
				applyConditions(c.getUnit());
			}
		}
	}
	
	public void sound()
	{
		Sounds.heal.play();
	}

	public void animation(Cell target)
	{
		AnimationManager.add(new AnimationCell(Images.animHeal, target));
	}
	
	public String getDescription() 
	{
		return "Each ally within the radius gains [HEAL]regen[] for " + getDurationText() + " turns";
	}
}
