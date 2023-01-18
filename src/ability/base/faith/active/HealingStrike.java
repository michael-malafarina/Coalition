package ability.base.faith.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Faith;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class HealingStrike extends SingleTarget
{
	public void setup() 
	{		
		discipline = new Faith();
		name = "Healing Strike";
		basicAttack = true;
		
		damage = 2;
		healing = 1;

		range = 1;
		rangeSecondary = 3;

		tags.add(Tag.HOLY);
		tags.add(Tag.HEAL);
		tags.add(Tag.MAGICAL);

	}
	
	public void use(Unit target)
	{
		Unit u = self().getNearestHurtAlly(getRangeSecondary());
		
		if(u != null)
		{
			addSecondaryTarget(u);
		}
		
		super.use(target);
		damage(target);
		
		if(hasSecondaryTarget())
		{
			heal(getSecondaryTarget());
		}
		
		clearTargets();
	}
	
	
	public void sound()
	{
		Sounds.bashHeavy.play();
	}
	
	public int getTargetHealingEstimate()
	{
		return 0;
	}
	

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animHoly, target));
		
		if(hasSecondaryTarget())
		{
			AnimationManager.add(new AnimationCell(Images.animHeal, getSecondaryTarget()));
		}
	}
	
	public String getDescription() 
	{
		return "Deals " + getDamageText() + " [HOLY]holy[] damage. The nearest damaged ally within [AVERAGE]" + 
				getRangeSecondary() + "[] of you [HEAL]heals[] " + getHealingText() + ".";
	}
}
