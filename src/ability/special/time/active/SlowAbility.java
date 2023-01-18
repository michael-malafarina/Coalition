package ability.special.time.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Time;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Slow;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class SlowAbility extends SingleTarget
{
	public void setup() 
	{
		discipline = new Time();
		name = "Slow";
		canTargetSelf = true;
		
		level = 3;
		energy = 3;
		
		range = 4;
		duration = 4;

		conditions.add(Slow.class);
		
		tags.add(Tag.SLOW);
		tags.add(Tag.DEBUFF);
		tags.add(Tag.MAGICAL);
		tags.add(Tag.RANGED);

	}
	
	public void use(Unit target)
	{
		super.use(target);
		applyConditions(target);

	}
	
	public void sound()
	{
		Sounds.slow.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animCold, target));
	}
	
	public String getDescription() 
	{
		return "Apply slow for " + getDurationText() + " turns.";
	}
}
