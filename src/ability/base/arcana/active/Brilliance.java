package ability.base.arcana.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Arcana;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Clarity;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class Brilliance extends SingleTarget
{
	public void setup() 
	{
		discipline = new Arcana();
		name = "Brilliance";
		signatureAbility = true;
		isSupport = true;
		canTargetSelf = true;

		conditions.add(Clarity.class);
		level = 1;
		energy = 3;
		
		duration = 3;
		range = 4;

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
		Sounds.teleport.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animTeleport, target));
	}
	
	public String getDescription() 
	{
		return "Target gains clarity for " + getDuration() + " turns.";
	}
}
