package unit.factions.undead;

import ability.base.general.EndTurn;
import ability.base.general.SharpMelee;
import ui.Images;
import unit.UnitColor;
import unit.classes.empire.ComputerUnitMelee;

public class Skeleton extends ComputerUnitMelee
{
	public static final int MAX_HEALTH = 2;
	public static final int GUARD = 0;
	public static final int ENERGY = 1;
	public static final int MAX_MOVE = 5;
	public static final int SPEED = 8;
	public static final int DAMAGE = 1;
	
	public Skeleton()
	{
		super();
			
		setSpriteSheet(Images.skeleton);

		addAbility(new SharpMelee(DAMAGE));
		
		setMaxHealthPermanent(MAX_HEALTH);
		setStartingGuard(GUARD);
		setStartingEnergy(ENERGY);
		setMovePermanent(MAX_MOVE);
		setSpeedPermanent(SPEED);
		
		setColorPrimary(UnitColor.WHITE);
		setColorSecondary(UnitColor.YELLOW);

		addAbility(new EndTurn());

	}

	
	
}
