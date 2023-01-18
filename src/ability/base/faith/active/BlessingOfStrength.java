package ability.base.faith.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Faith;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Might;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class BlessingOfStrength extends SingleTarget
{
	public void setup() 
	{
		discipline = new Faith();
		name = "Blessing of Strength";
		canTargetSelf = true;
		isSupport = true;

		level = 3;
		range = 4;
		duration = 3;
		energy = 4;

		conditions.add(Might.class);

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
		Sounds.heal.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animHoly, target));
	}
	
	public String getDescription() 
	{
		return "Target gains might for " + getDurationText() + " rounds.";
	}
}
