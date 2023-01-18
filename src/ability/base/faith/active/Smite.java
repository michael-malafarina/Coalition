package ability.base.faith.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Faith;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class Smite extends SingleTarget
{
	public void setup() 
	{
		discipline = new Faith();
		name = "Smite";
		
		level = 4;
		energy = 4;
		
		damage = 4;
		range = 4;

		tags.add(Tag.HOLY);
		tags.add(Tag.MAGICAL);


	}
	
	public void use(Unit target)
	{
		super.use(target);
		damage(target);
	}
	
	public void sound()
	{
		Sounds.fireball.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animHoly, target));
	}
	
	public String getDescription() 
	{
		return "Deals " + getDamageText() + " [HOLY]holy[] damage";
	}
}
