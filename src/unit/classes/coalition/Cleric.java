package unit.classes.coalition;

import ability.disciplines.Faith;
import ui.Images;
import unit.PlayerUnit;

public class Cleric extends PlayerUnit
{	
	public static final int MAX_HEALTH = 7;
	public static final int GUARD = 2;
	public static final int ENERGY = 2;
	public static final int MAX_MOVE = 5;
	public static final int SPEED = 9;

	public Cleric()
	{
		super();
		setSpriteSheet(Images.cleric);
		
		setMaxHealthPermanent(MAX_HEALTH);
		setStartingGuard(GUARD);
		setStartingEnergy(ENERGY);
		setMovePermanent(MAX_MOVE);
		setSpeedPermanent(SPEED);

		addDiscipline(new Faith(this));
		setStartingAbilities();


	}
	
//	public Knight(Faction faction)
//	{
//		this();
//		setFaction(faction);
//	}

}
