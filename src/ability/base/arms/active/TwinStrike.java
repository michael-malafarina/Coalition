package ability.base.arms.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Arms;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class TwinStrike extends SingleTarget
{
	public void setup()
	{
		discipline = new Arms();
		name = "Twin Strike";
		
		level = 4;
		energy = 3;

		damage = 2;
		range = 3;
		attacks = 2;
		
		tags.add(Tag.RANGED);
		tags.add(Tag.SHARP);
		tags.add(Tag.PHYSICAL);

	}
	
	
	public void use(Unit target)
	{
		super.use(target);
		
	
		damage(target);
		damage(target);

	}
	
	public void sound()
	{
		Sounds.slash.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animSlash, target));
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [SHARP]sharp[] damage twice.";
	}
}
