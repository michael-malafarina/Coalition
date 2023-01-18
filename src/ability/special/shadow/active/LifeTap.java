package ability.special.shadow.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Shadow;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class LifeTap extends SingleTarget
{
	public void setup() 
	{
		discipline = new Shadow();
		name = "Life Tap";
		signatureAbility = true;
		level = 1;
		energy = 4;

		damage = 2;
		healing = 2;
		range = 4;

		tags.add(Tag.SHADOW);
		tags.add(Tag.MAGICAL);

	}
	
	public void use(Unit target)
	{
		super.use(target);
		damage(target);
		heal();
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
		return "Deals " + getDamageText() + " [SHADOW]shadow[] damage and you [HEAL] heal " + getHealingText() + ".";
	}
}
