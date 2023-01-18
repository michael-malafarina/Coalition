package unit.classes.coalition;

import ability.disciplines.Chivalry;
import ui.Images;
import unit.PlayerUnit;

public class Knight extends PlayerUnit
{	
	public static final int MAX_HEALTH = 8;
	public static final int GUARD = 4;
	public static final int ENERGY = 1;
	public static final int MAX_MOVE = 5;
	public static final int SPEED = 9;

	public Knight()
	{
		super();
		setSpriteSheet(Images.knight);
		
		setMaxHealthPermanent(MAX_HEALTH);
		setStartingGuard(GUARD);
		setStartingEnergy(ENERGY);
		setMovePermanent(MAX_MOVE);
		setSpeedPermanent(SPEED);
		
		addDiscipline(new Chivalry(this));

		setStartingAbilities();
	}
	



}
