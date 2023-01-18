package ability.special.growth.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Growth;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Vigor;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class Tend extends SingleTarget
{
	public void setup() 
	{
		discipline = new Growth();
		name = "Tend";
		signatureAbility = true;
		isSupport = true;

		level = 1;
		healing = 2;
		energy = 3;
		duration = 3;
		range = 4;
		canTargetSelf = true;
		
		conditions.add(Vigor.class);
		
		tags.add(Tag.HEAL);
		tags.add(Tag.MAGICAL);

	}
	
	public void use(Unit target)
	{
		super.use(target);
		
		heal(target);
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
		return "[HEAL]Heal[] the target for " + getHealingText() + " health and grants vigor for " + getDuration() + " turns.";
	}
}
