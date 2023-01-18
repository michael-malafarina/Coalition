package ability.base.arms.active;

import ability.SelfTarget;
import ability.Tag;
import ability.disciplines.Arms;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Vigor;
import ui.Images;
import ui.sound.Sounds;

public class SecondWind extends SelfTarget
{
	public void setup()
	{
		discipline = new Arms();
		name = "Second Wind";
		signatureAbility = true;

		level = 1;
		energy = 5;
		
		healing = 4;			
		duration = 3;
		conditions.add(Vigor.class);
		
		tags.add(Tag.HEAL);
		tags.add(Tag.BUFF);

	}
		

	public void use()
	{
		super.use();
//		boolean gainVigor = false;
//		
//		if(getOwner().getCurHealth() < getOwner().getMaxHealth())
//		{
//			gainVigor = true;
//		}
		
		heal();
//		
//		if(gainVigor)
//		{
			applyConditions();
//		}
	}
	
	public void sound()
	{
		Sounds.heal.play();
	}

	public void animation()
	{
		AnimationManager.add(new AnimationCell(Images.animHeal, getOwner()));
	}
	
	public String getDescription() 
	{
		return "You [HEAL]heal[] for " + getHealingText() + " and gain [VIGOR]vigor[] for " + getDurationText() + " " + getTurnText();
	}
}
