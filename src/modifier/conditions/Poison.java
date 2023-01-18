package modifier.conditions;

import ability.Tag;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;

public class Poison extends Condition
{
	public Poison() 
	{
		name = "Poison";
		icon = Images.iconPoison;

		ignoresGuard = true;
		tags.add(Tag.POISON);
		tags.add(Tag.DEBUFF);
	}
		
	public void endTurn()
	{
		tick();
		getOwner().takeDamage(this, 1);
		animation();
		sound();
	}
	
	public String getDescription() 
	{
		return "1 damage at end of turn";
	}

	public void sound() 
	{
		Sounds.grunt.play();
	}

	public void animation() 
	{
		AnimationManager.add(new AnimationCell(Images.animSlash, getOwner()));
	}



}
