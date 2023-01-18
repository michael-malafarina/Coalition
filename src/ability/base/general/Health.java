package ability.base.general;

import ability.PassiveAbility;
import ability.disciplines.General;

public class Health extends PassiveAbility
{
	public void setup() 
	{
		discipline = new General();
		name = "Health";
		hidden = true;
		level = 3;
	}
	
	public void learn()
	{
		super.learn();
		getOwner().gainMaxHealthPermanent(1);
	}

	public String getDescription() 
	{
		return "Increase your maximum health by 1";
	}

	public void sound()
	{
		
	}

	public void animation() 
	{
		
	}

}
