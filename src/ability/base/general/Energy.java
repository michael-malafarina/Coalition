package ability.base.general;

import ability.PassiveAbility;
import ability.disciplines.General;

public class Energy extends PassiveAbility
{
	public void setup() 
	{
		discipline = new General();
		name = "Energy";
		hidden = true;
		level = 5;
	}
	
	public void learn()
	{
		super.learn();
		getOwner().gainStartingEnergy(1);
		getOwner().gainCharge(1);

	}

	public String getDescription() 
	{
		return "Increase your starting energy by 1";
	}

	public void sound()
	{
		
	}

	public void animation() 
	{
		
	}

}
