package ability.unused;

import ability.PassiveAbility;
import ability.Tag;

public class BuffBro extends PassiveAbility
{
	public void setup() 
	{
		passiveDamageBonus = 1;
		tags.add(Tag.SHARP);
		tags.add(Tag.BLUNT);
	}
	
	public void startTurn()
	{
		
	}

	public String getDescription() 
	{
		return "I am stronk";
	}

	public void sound() 
	{
		
	}

	public void animation()
	{
		
	}

}
