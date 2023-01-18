package ability.base.arms.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Arms;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Marked;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class CarefulStudy extends SingleTarget
{
	public void setup()
	{
		discipline = new Arms();
		name = "Careful Study";

		level = 2;
		energy = 4;
		
		guard = 1;
		range = 3;
		duration = 3;

		
		conditions.add(Marked.class);
		
		tags.add(Tag.RANGED);
		tags.add(Tag.SHARP);
		tags.add(Tag.PHYSICAL);
		tags.add(Tag.READY);

	}
	
	
	public void use(Unit target)
	{
		super.use(target);
		
		applyConditions(target);
		guard();
		
		AnimationManager.add(new AnimationCell(Images.animShield, self()));

	}
	
	public void sound()
	{
		Sounds.armorUp.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animSlash, target));
		
	}

	public String getDescription() 
	{
		return "Gain " + getGuardText() + " [GUARD]guard[].  Mark the target for " + getDurationText() + " " + getTurnText() + ". Ready.";
	}
}
