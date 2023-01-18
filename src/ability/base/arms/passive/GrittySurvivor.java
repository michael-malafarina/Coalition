package ability.base.arms.passive;

import ability.PassiveAbility;
import ability.disciplines.Arms;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;

public class GrittySurvivor extends PassiveAbility
{
	public void setup() 
	{
		discipline = new Arms();
		name = "Gritty Survivor";
		level = 5;

	}
	
	public void startTurn()
	{
		if(getOwner().getCurHealth() <= getOwner().getMaxHealth() / 2)
		{
			getOwner().regainHealth(this, 1);
			sound();
			animation();
		}
	}

	public String getDescription() 
	{
		return "When you start your turn under half health, heal 1.";
	}

	public String getDescriptionShort() 
	{
		return "Heal 1 at start of turn if below half";
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
