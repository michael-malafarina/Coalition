package modifier.conditions;

import ability.Tag;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;

public class Regen extends Condition
{
	public Regen() 
	{
		name = "Regen";
		icon = Images.iconRegen;

		ignoresGuard = true;
		tags.add(Tag.HEAL);
		tags.add(Tag.BUFF);
	}
		
	public void endTurn()
	{
		tick();
		getOwner().regainHealth(this, 1);
		animation();
		sound();
	}
	
	public String getDescription() 
	{
		return "1 healing at end of turn";
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
