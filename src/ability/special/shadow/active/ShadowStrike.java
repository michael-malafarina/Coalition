package ability.special.shadow.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Shadow;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class ShadowStrike extends SingleTarget
{
	public void setup() 
	{
		discipline = new Shadow();
		name = "Shadow Strike";
		level = 2;
		energy = 3;

		damage = 4;
		range = 4;

		tags.add(Tag.SHADOW);
		tags.add(Tag.MAGICAL);

	}
	
	public void use(Unit target)
	{
		super.use(target);
		damage(target);
		getOwner().loseHealth(1);
	}
	
	public void sound()
	{
		Sounds.magicImpact.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animDark, target));
	}
	
	public String getDescription() 
	{
		return "Deals " + getDamageText() + " [SHADOW]shadow damage[]. You lose 1 health.";

//		return "Deals " + getDamageText() + " [SHADOW]shadow[] damage and applies blind " + getDurationText() + ". You lose 1 health.";
	}
}
