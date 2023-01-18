package ability.special.shadow.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Shadow;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class LifeTransference extends SingleTarget
{
	public void setup() 
	{
		discipline = new Shadow();
		name = "Life Transference";
		level = 2;
		energy = 3;

		healing = 4;
		range = 4;

		tags.add(Tag.HEAL);
		tags.add(Tag.SHADOW);
		tags.add(Tag.MAGICAL);

	}
	
	public void use(Unit target)
	{
		super.use(target);
		heal(target);
		getOwner().loseHealth(2);
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
		return "[HEAL]Heals[] one ally " + getHealingText() + ". You lose 2 health.";

	}
}
