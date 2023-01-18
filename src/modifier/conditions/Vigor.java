package modifier.conditions;

import ability.Tag;
import ui.Images;

public class Vigor extends Condition
{
	public Vigor() 
	{
		name = "Vigor";
		icon = Images.iconVigor;
		passiveHealingRecievedBonus = 1;
		tags.add(Tag.VIGOR);
		tags.add(Tag.BUFF);
	}
		
	public void endTurn()
	{
		tick();
	}
	
	public String getDescription() 
	{
		return "+1 healing recieved";
	}

	public void sound() 
	{

	}

	public void animation() 
	{

	}



}
