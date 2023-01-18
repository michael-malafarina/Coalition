package unit.classes.empire;

import ability.base.faith.active.HealingStrike;
import ui.Images;
import unit.classes.coalition.Cleric;

public class EmpireCleric extends ComputerUnitMelee
{
	public EmpireCleric()
	{
		super();
		setSpriteSheet(Images.cleric);

		maxHealth = Cleric.MAX_HEALTH;
		maxMove = Cleric.MAX_MOVE;
		speed = Cleric.SPEED;
		
		addAbility(new HealingStrike());

	}
}
