package modifier.conditions;

import ability.Tag;
import ui.Images;

public class Decay extends Condition
{
	public Decay() 
	{
		name = "Decay";
		icon = Images.iconDecay;
		passiveHealingRecievedBonus = -1;
		tags.add(Tag.BUFF);
	}
		
	public void endTurn()
	{
		tick();
	}
	
	public String getDescription() 
	{
		return "-1 healing recieved";
	}

	public void sound() 
	{

	}

	public void animation() 
	{

	}



}
