package unit.factions.undead;

import ability.base.general.EndTurn;
import ability.base.general.SharpRanged;
import ui.Images;
import unit.UnitColor;
import unit.classes.empire.ComputerUnitRanged;

public class SkeletonArcher extends ComputerUnitRanged
{
	public static final int MAX_HEALTH = 4;
	public static final int GUARD = 0;
	public static final int ENERGY = 1;
	public static final int MAX_MOVE = 5;
	public static final int SPEED = 12;
	public static final int DAMAGE = 1;
	public static final int RANGE = 3;

	public SkeletonArcher()
	{
		super();
		
			
		setSpriteSheet(Images.skeletonArcher);

		addAbility(new SharpRanged(DAMAGE, RANGE));
		
		setMaxHealthPermanent(MAX_HEALTH);
		setStartingGuard(GUARD);
		setStartingEnergy(ENERGY);
		setMovePermanent(MAX_MOVE);
		setSpeedPermanent(SPEED);
		
		setColorPrimary(UnitColor.WHITE);
		setColorSecondary(UnitColor.RED);

		addAbility(new EndTurn());

	}

	
	
}
