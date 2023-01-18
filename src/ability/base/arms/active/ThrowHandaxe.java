package ability.base.arms.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Arms;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class ThrowHandaxe extends SingleTarget
{
	public void setup()
	{
		discipline = new Arms();
		name = "Throw Handaxe";
		
		level = 3;
		energy = 4;

		damage = 3;
		range = 3;
		
		tags.add(Tag.RANGED);
		tags.add(Tag.SHARP);
		tags.add(Tag.PHYSICAL);
		tags.add(Tag.READY);
	}
	
	
	public void use(Unit target)
	{
		super.use(target);
		damage(target);
	}
	
	public void sound()
	{
		Sounds.slashHeavy.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animSlash, target));
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [SHARP]sharp[] damage.  Ready.";
	}
}
