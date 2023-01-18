package unit.classes.empire;

import ability.base.chivalry.active.GuardedStrike;
import ui.Images;
import unit.classes.coalition.Knight;

public class EmpireKnight extends ComputerUnitMelee
{
	public EmpireKnight()
	{
		super();
		
		setSpriteSheet(Images.knight);

		maxHealth = Knight.MAX_HEALTH;
		startingGuard = Knight.GUARD;
		maxMove = Knight.MAX_MOVE;
		speed = Knight.SPEED;

		addAbility(new GuardedStrike());

	}
}
