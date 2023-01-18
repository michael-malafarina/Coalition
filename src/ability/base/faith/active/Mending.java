package ability.base.faith.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Faith;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class Mending extends SingleTarget
{
	public void setup() 
	{
		discipline = new Faith();
		name = "Mending";
		signatureAbility = true;
		isSupport = true;

		level = 1;
		healing = 2;
		guard = 1;
		energy = 3;
		range = 3;
		canTargetSelf = true;
		
		tags.add(Tag.HEAL);

	}
	
	public void use(Unit target)
	{
		super.use(target);
		
		heal(target);
		guard(target);
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
		return "[HEAL]Heal[] the target for " + getHealingText() + " health and the target gains " + getGuardText() + " [GUARD]guard[]";
	}
}
