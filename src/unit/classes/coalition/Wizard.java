package unit.classes.coalition;

import ability.disciplines.Arcana;
import ui.Images;
import unit.PlayerUnit;

public class Wizard extends PlayerUnit
{	
	public static final int MAX_HEALTH = 6;
	public static final int GUARD = 0;
	public static final int ENERGY = 3;
	public static final int MAX_MOVE = 4;
	public static final int SPEED = 11;

	public Wizard()
	{
		super();
		setSpriteSheet(Images.wizard);
		
		setMaxHealthPermanent(MAX_HEALTH);
		setStartingGuard(GUARD);
		setStartingEnergy(ENERGY);
		setMovePermanent(MAX_MOVE);
		setSpeedPermanent(SPEED);

		addDiscipline(new Arcana(this));

		setStartingAbilities();

	}
	
//	public Knight(Faction faction)
//	{
//		this();
//		setFaction(faction);
//	}

}
