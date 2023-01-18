package ability.base.chivalry.passive;

import ability.PassiveAbility;
import ability.disciplines.Chivalry;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;

public class KnightlyBearing extends PassiveAbility
{
	public void setup() 
	{
		discipline = new Chivalry();
		name = "Knightly Bearing";
		level = 4;
	}
	
	public void startTurn()
	{
		if(getOwner().getCurHealth() == getOwner().getMaxHealth())
		{
			getOwner().gainGuard(1);
			sound();
			animation();
		}
	}

	public String getDescription() 
	{
		return "When you start your turn at maximum health, gain one guard.";
	}
	
	public String getDescriptionShort() 
	{
		return "Gain 1 guard at start of turn if max health";
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
