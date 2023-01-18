package ability.base.general;

import ability.SelfTarget;
import ui.Images;

public class EndTurn extends SelfTarget
{
	public void setup() 
	{
		name = "End Turn";
		icon = Images.iconCheck;
		isSupport = true;
		charge = -1;
	}
	
	public void use()
	{
		getOwner().expendMove(getOwner().getCurMove());
		getOwner().expendAllActions();
	}

	public boolean canUse()
	{
		return true;
	}
	
	public void sound()
	{

	}

	public void animation() 
	{
		
	}
	
	public String getDescription() 
	{
		return "Press [AVERAGE]E[] to end your turn";
	}

	
}
