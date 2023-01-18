package modifier.conditions;

import ability.Tag;
import ui.Images;

public class Clarity extends Condition
{
	public Clarity() 
	{
		name = "Clarity";
		icon = Images.iconClarity;
		tags.add(Tag.BUFF);
	}
		
	public void startTurn()
	{
		tick();
		getOwner().gainCharge(1);
		animation();
		sound();
	}
	
	public String getDescription() 
	{
		return "Gain 1 energy at start of turn";
	}

	public void sound() 
	{
//		Sounds.grunt.play();
	}

	public void animation() 
	{
//		AnimationManager.add(new AnimationCell(Images.animSlash, getOwner()));
	}



}
