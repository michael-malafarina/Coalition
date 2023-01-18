package ability.special.time.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Time;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class Flux extends SingleTarget
{
	public void setup() 
	{
		discipline = new Time();
		name = "Flux";
		
		level = 2;
		energy = 3;

		damage = 2;
		range = 4;
		delay = 30;

		tags.add(Tag.FORCE);
		tags.add(Tag.MAGICAL);
		tags.add(Tag.RANGED);

	}
	
	public void use(Unit target)
	{
		super.use(target);
		damage(target);
		delay(target);
	}
	
	public void sound()
	{
		Sounds.magicImpact.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animTeleport, target));
	}
	
	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [FORCE]force[] damage and delay the target's next turn by " + getDelayText();
	}
}
