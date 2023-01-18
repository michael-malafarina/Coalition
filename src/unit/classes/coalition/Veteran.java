package unit.classes.coalition;

import ability.disciplines.Arms;
import ui.Images;
import unit.PlayerUnit;

public class Veteran extends PlayerUnit
{	
	public static final int MAX_HEALTH = 8;
	public static final int GUARD = 2;
	public static final int ENERGY = 1;
	public static final int MAX_MOVE = 5;
	public static final int SPEED = 10;

	public Veteran()
	{
		super();
		setSpriteSheet(Images.warrior);
		
		setMaxHealthPermanent(MAX_HEALTH);
		setStartingGuard(GUARD);
		setStartingEnergy(ENERGY);
		setMovePermanent(MAX_MOVE);
		setSpeedPermanent(SPEED);
		
		addDiscipline(new Arms(this));

		setStartingAbilities();
		

	}
	

}
