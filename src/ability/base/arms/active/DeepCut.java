package ability.base.arms.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Arms;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Bleed;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class DeepCut extends SingleTarget
{
	public void setup()
	{
		discipline = new Arms();
		name = "Deep Cut";
		
		level = 3;
		energy = 3;
		
		damage = 2;
		range = 1;
		duration = 3;
		conditions.add(Bleed.class);
		
		tags.add(Tag.MELEE);
		tags.add(Tag.SHARP);
		tags.add(Tag.PHYSICAL);

	}
	
	
	public void use(Unit target)
	{
		super.use(target);
		damage(target);
		applyConditions(target);
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
		return "Deal " + getDamageText() + " [SHARP]sharp[] damage and apply [BLEED]bleed[] for " + getDurationText() + " " + getTurnText();
	}
}
