package ability.special.fire.passive;

import ability.PassiveAbility;
import ability.Tag;
import ability.disciplines.Fire;

public class Firelord extends PassiveAbility
{
	public void setup() 
	{
		discipline = new Fire();
		name = "Firelord";
		level = 6;

		passiveDamageBonus = 1;
		tags.add(Tag.FIRE);
	}
	
	public void startTurn()
	{
		
	}

	public String getDescription() 
	{
		return "Increase fire damage by one";
	}

	public void sound() 
	{
		
	}

	public void animation()
	{
		
	}

}
