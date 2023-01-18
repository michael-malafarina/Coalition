package modifier.conditions;

import ability.Tag;
import ui.Images;

public class Marked extends Condition
{
	public Marked() 
	{
		name = "Marked";
		icon = Images.iconMarked;
		passiveDamageRecievedBonus = 1;
		tags.add(Tag.DEBUFF);
		tags.add(Tag.ALL);		
	}
		
	public void endTurn()
	{
		tick();
	}
	
	public String getDescription() 
	{
		return "+1 damage taken from all attacks";
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
