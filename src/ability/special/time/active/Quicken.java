package ability.special.time.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Time;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Haste;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class Quicken extends SingleTarget
{
	public void setup() 
	{
		discipline = new Time();
		name = "Quicken";
		canTargetSelf = true;
		isSupport = true;

		level = 2;
		energy = 4;

		range = 4;
		advance = 75;

		conditions.add(Haste.class);

		tags.add(Tag.MAGICAL);
		tags.add(Tag.RANGED);

	}
	
	public void use(Unit target)
	{
		super.use(target);
		
		advance(target);
	}
	
	public void sound()
	{
		Sounds.haste.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animTeleport, target));
	}
	
	public String getDescription() 
	{
		return "Advance target's turn by " + getAdvanceText() + " initiative.";
	}
}
