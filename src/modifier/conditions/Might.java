package modifier.conditions;

import ability.Tag;
import ui.Images;

public class Might extends Condition
{
	public Might() 
	{
		name = "Might";
		icon = Images.iconMight;
		passiveDamageBonus = 1;
		tags.add(Tag.BUFF);
		tags.add(Tag.PHYSICAL);		
	}
		
	public void endTurn()
	{
		tick();
	}
	
	public String getDescription() 
	{
		return "+1 physical damage";
	}

	public void sound() 
	{
	//	Sounds.grunt.play();
	}

	public void animation() 
	{
		//AnimationManager.add(new AnimationCell(Images.animSlash, getOwner()));
	}



}
