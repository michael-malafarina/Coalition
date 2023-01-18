package unit.classes.empire;

import ability.base.arcana.active.ArcaneMissile;
import ui.Images;
import unit.classes.coalition.Wizard;

public class EmpireWizard extends ComputerUnitRanged
{
	public EmpireWizard()
	{
		super();
		setSpriteSheet(Images.wizard);

		maxHealth = Wizard.MAX_HEALTH;
		maxMove = Wizard.MAX_MOVE;
		speed = Wizard.SPEED;
		
		addAbility(new ArcaneMissile());

	}
}
