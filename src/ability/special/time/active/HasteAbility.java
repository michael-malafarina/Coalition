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

public class HasteAbility extends SingleTarget
{
	public void setup() 
	{
		discipline = new Time();
		name = "Haste";
		canTargetSelf = true;
		isSupport = true;
		signatureAbility = true;

		level = 1;
		energy = 3;

		range = 4;
		duration = 3;

		conditions.add(Haste.class);

		tags.add(Tag.HASTE);
		tags.add(Tag.BUFF);
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
		Sounds.haste.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animTeleport, target));
	}
	
	public String getDescription() 
	{
		return "Apply haste for " + getDurationText() + " turns.";
	}
}
