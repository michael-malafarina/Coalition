package ability.special.time.passive;

import ability.PassiveAbility;
import ability.disciplines.Time;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;

public class Acceleration extends PassiveAbility
{
	public void setup() 
	{
		discipline = new Time();
		name = "Acceleration";
		level = 4;
	}
	
	public void startTurn()
	{
		getOwner().gainSpeedEncounter(1);
	}

	public String getDescription() 
	{
		return "At the start of your turn gain 1 speed";
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
