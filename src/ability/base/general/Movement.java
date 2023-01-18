package ability.base.general;

import ability.PassiveAbility;
import ability.disciplines.General;

public class Movement extends PassiveAbility
{
	public void setup() 
	{
		discipline = new General();
		name = "Movement";
		hidden = true;
		level = 4;
	}
	
	public void learn()
	{
		super.learn();
		getOwner().gainStartingEnergy(1);
	}

	public String getDescription() 
	{
		return "Increase your movement by 1";
	}

	public void sound()
	{
		
	}

	public void animation() 
	{
		
	}

}
