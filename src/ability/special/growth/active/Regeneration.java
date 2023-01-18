package ability.special.growth.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Growth;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Regen;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class Regeneration extends SingleTarget
{
	public void setup() 
	{
		discipline = new Growth();
		name = "Regeneration";
		isSupport = true;

		level = 2;
		energy = 4;
		duration = 4;

		range = 4;
		canTargetSelf = true;
		
		conditions.add(Regen.class);
		
		tags.add(Tag.HEAL);
		tags.add(Tag.MAGICAL);

	}
	
	public void use(Unit target)
	{
		super.use(target);
		
		applyConditions(target);
	}
	
	public void sound()
	{
		Sounds.heal.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animHeal, target));
	}
	
	public String getDescription() 
	{
		return "Applies regen for " + getDurationText() + " turns.";
	}
}
