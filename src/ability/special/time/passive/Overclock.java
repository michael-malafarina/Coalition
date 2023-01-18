package ability.special.time.passive;

import ability.PassiveAbility;
import ability.disciplines.Time;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Bleed;
import modifier.conditions.Haste;
import ui.Images;
import ui.sound.Sounds;

public class Overclock extends PassiveAbility
{
	public void setup() 
	{
		discipline = new Time();
		name = "Overclock";
		hidden = true;
		
		level = 3;
		
		duration = 3;
		
		conditions.add(Haste.class);
		conditions.add(Bleed.class);
	}
	
	public void startCombat()
	{
		applyConditions();
	}

	public String getDescription() 
	{
		return "At the start of combat, gain Haste and Bleed for 3 turns";
	}
	
	public void sound() 
	{
		Sounds.haste.play();
	}

	public void animation() 
	{
		AnimationManager.add(new AnimationCell(Images.animTeleport, getOwner()));
	}

}
