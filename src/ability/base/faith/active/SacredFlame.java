package ability.base.faith.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Faith;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class SacredFlame extends SingleTarget
{
	public void setup() 
	{		
		discipline = new Faith();
		name = "Sacred Flame";
		
		level = 2;
		energy = 3;

		damage = 2;
		range = 5;
		rangeSecondary = 999;
		
		tags.add(Tag.HOLY);
		tags.add(Tag.MAGICAL);

	}
	
	public void use(Unit target)
	{
		Unit u = self().getNearestDebuffedAlly(getRangeSecondary());
		
		if(u != null)
		{
			addSecondaryTarget(u);
		}
		
		super.use(target);
		damage(target);
		
		if(hasSecondaryTarget())
		{
			getSecondaryTarget().removeDebuff();
		}
		
		clearTargets();
	}
	
	public void sound()
	{
		Sounds.fireball.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animHoly, target));
		
		if(hasSecondaryTarget())
		{
			AnimationManager.add(new AnimationCell(Images.animCleanse, getSecondaryTarget()));
		}
	}
	
	public String getDescription() 
	{
		return "Deals " + getDamageText() + " [HOLY]holy[] damage. The nearest afflicted ally is [CLEANSE]cleansed[] once.";
	}
}
