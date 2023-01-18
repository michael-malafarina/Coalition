package unit.classes.empire;

import ability.base.arms.active.Slash;
import ui.Images;
import unit.classes.coalition.Veteran;

public class EmpireWarrior extends ComputerUnitMelee
{
	public EmpireWarrior()
	{
		super();
		
		setSpriteSheet(Images.warrior);

		maxHealth = Veteran.MAX_HEALTH;
		maxMove = Veteran.MAX_MOVE;
		speed = Veteran.SPEED;
		guard = Veteran.GUARD;

		addAbility(new Slash());

	}
}
