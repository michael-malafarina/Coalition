package ability.special.growth.passive;

import ability.PassiveAbility;
import ability.disciplines.Growth;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;

public class OakenResilience extends PassiveAbility
{
	public void setup() 
	{
		discipline = new Growth();
		name = "Oaken Resilience";
		level = 6;
	}
	
	public void onDamageTaken()
	{
		getOwner().gainGuard(1);
	}
	
	public String getDescription() 
	{
		return "When you take damage, gain 1 guard";
	}
	
	public void sound() 
	{
		Sounds.armorUp.play();
	}

	public void animation() 
	{
		AnimationManager.add(new AnimationCell(Images.animShield, getOwner()));
	}

}
