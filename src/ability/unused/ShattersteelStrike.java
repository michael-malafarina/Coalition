package ability.unused;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Arms;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class ShattersteelStrike extends SingleTarget
{
	public void setup()
	{
		discipline = new Arms();
		name = "Shattersteel Strike";
		
		shred = 2;
		damage = 2;
		energy = 3;
		range = 1;
		duration = 1;

		tags.add(Tag.MELEE);
		tags.add(Tag.SHARP);
		tags.add(Tag.PHYSICAL);

	}
			
	public void use(Unit target)
	{
		super.use(target);
		shred(target);
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
		return "Shred "+ getShredText() + " guard. Then deal " + getDamageText() + " [SHARP]sharp[] damage.";
	}
}
