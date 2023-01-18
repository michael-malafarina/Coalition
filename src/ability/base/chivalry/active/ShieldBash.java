package ability.base.chivalry.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Chivalry;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Slow;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class ShieldBash extends SingleTarget
{
	public void setup()
	{
		discipline = new Chivalry();
		name = "Shield Bash";

		level = 2;
		energy = 3;
		
		damage = 2;
		range = 1;
		duration = 2;
		
		conditions.add(Slow.class);
		
		tags.add(Tag.MELEE);
		tags.add(Tag.BLUNT);
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
		Sounds.bashHeavy.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animBlunt, target));
	}
	
	public String getDescription() 
	{
		return "Deals "+ getDamageText() +" [BLUNT]blunt []damage and apply slow for " + getDuration() +" turns";
	}
}
