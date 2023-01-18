package ability.base.general;

import ability.PassiveAbility;
import ability.disciplines.General;

public class Guard extends PassiveAbility
{
	public void setup() 
	{
		discipline = new General();
		name = "Guard";
		hidden = true;
		level = 2;
	}
	
	public void learn()
	{
		super.learn();
		getOwner().setStartingGuard(1);
		getOwner().gainGuard(1);
	}

	public String getDescription() 
	{
		return "Increase your starting guard by 1";
	}

	public void sound()
	{
		
	}

	public void animation() 
	{
		
	}

}
