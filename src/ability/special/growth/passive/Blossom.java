package ability.special.growth.passive;

import ability.ActivatedAbility;
import ability.PassiveAbility;
import ability.Tag;
import ability.disciplines.Growth;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;

public class Blossom extends PassiveAbility
{
	public void setup() 
	{
		discipline = new Growth();
		name = "Blossom";
		level = 3;
	}
	
	public void onAbilityUsed(ActivatedAbility ability)
	{
		if(ability.hasTag(Tag.HEAL))
		{
			getOwner().regainHealth(this, 1);
		}
	}
	
	public String getDescription() 
	{
		return "When you use an ability with the HEAL tag, you heal 1";
	}

	public String getDescriptionShort() 
	{
		return "On using a HEAL ability, you heal 1";
	}
	
	public void sound() 
	{
		Sounds.heal.play();
	}

	public void animation() 
	{
		AnimationManager.add(new AnimationCell(Images.animHeal, getOwner()));
	}

}
