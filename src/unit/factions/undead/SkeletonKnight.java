package unit.factions.undead;

import ability.base.general.EndTurn;
import ability.base.general.SharpMeleeBleed;
import ui.Images;
import unit.UnitColor;
import unit.classes.empire.ComputerUnitMelee;

public class SkeletonKnight extends ComputerUnitMelee
{
	public static final int MAX_HEALTH = 8;
	public static final int GUARD = 2;
	public static final int ENERGY = 1;
	public static final int MAX_MOVE = 4;
	public static final int SPEED = 10;
	public static final int DAMAGE = 2;

	public SkeletonKnight()
	{
		super();
			
		setSpriteSheet(Images.skeletonKnight);
		addAbility(new SharpMeleeBleed(DAMAGE));
		
		setMaxHealthPermanent(MAX_HEALTH);
		setStartingGuard(GUARD);
		setStartingEnergy(ENERGY);
		setMovePermanent(MAX_MOVE); 
		setSpeedPermanent(SPEED);
		
		setColorPrimary(UnitColor.WHITE);
		setColorSecondary(UnitColor.BLACK);

		addAbility(new EndTurn());
	}

	
	
}
