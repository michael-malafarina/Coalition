package ability.base.chivalry.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Chivalry;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class BatteringRam extends SingleTarget
{
	public void setup()
	{
		discipline = new Chivalry();
		name = "Battering Ram";
		
		level = 3;
		energy = 3;
		
		damage = 0;
		range = 1;
		push = 4;
		pushFollow = true;
		
		tags.add(Tag.MELEE);
		tags.add(Tag.BLUNT);
		tags.add(Tag.PHYSICAL);
	}		
	
	public void use(Unit target)
	{
		super.use(target);
		
		damage(target);
		push(target);
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
		return "Push the target " + getPushText() + " and follow.";
	}
}
