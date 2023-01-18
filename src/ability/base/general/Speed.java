package ability.base.general;

import ability.PassiveAbility;
import ability.disciplines.General;

public class Speed extends PassiveAbility
{
	public void setup() 
	{
		discipline = new General();
		name = "Speed";
		hidden = true;
		level = 4;
	}
	
	public void learn()
	{
		super.learn();
		getOwner().gainSpeedPermanent(3);
	}

	public String getDescription() 
	{
		return "Increase your speed by 2";
	}

	public void sound()
	{
		
	}

	public void animation() 
	{
		
	}

}
