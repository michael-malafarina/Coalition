package unit;

import ability.Ability;
import ability.base.general.EndTurn;

public class PlayerUnit extends Unit
{	
	
	public PlayerUnit()
	{
		super();
		setPlayer();
		setFriendly();
	}
	
	public void addAbility(Ability a)
	{
		
		super.addAbility(a);
	
	}	
	
	public void setStartingAbilities()
	{
//		Discipline primary = getDiscipline(0);
		
		
//		primary.getBasicAttack().learn();
//		primary.getSignatureAbility().learn();


		addAbility(new EndTurn());
	}
	


}
