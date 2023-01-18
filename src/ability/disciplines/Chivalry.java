package ability.disciplines;

import ability.Discipline;
import ability.base.chivalry.active.BatteringRam;
import ability.base.chivalry.active.DrivingAssault;
import ability.base.chivalry.active.Fortify;
import ability.base.chivalry.active.GuardedStrike;
import ability.base.chivalry.active.IroncladPunch;
import ability.base.chivalry.active.ShieldBash;
import ability.base.chivalry.active.SteelYourself;
import ability.base.chivalry.passive.KnightlyBearing;
import core.Color;
import ui.Images;
import unit.PlayerUnit;

public class Chivalry extends Discipline
{
	public Chivalry(PlayerUnit owner)
	{
		super(owner);
		addAbilities();
		icon = Images.iconChivalry;

	}
	
	public Chivalry()
	{
		this(null);
	}
	
	public void addAbilities()
	{
		// Active
		addAbility(BatteringRam.class);
		addAbility(DrivingAssault.class);
		addAbility(Fortify.class);
		addAbility(GuardedStrike.class);
		addAbility(ShieldBash.class);
		addAbility(IroncladPunch.class);
		addAbility(SteelYourself.class);

		// Passive
		addAbility(KnightlyBearing.class);

	}
	
	public Color getColorMenuDark()
	{
		return new Color(20, 30, 80);
	}

	public Color getColorMenuLight() 
	{
		return new Color(80, 90, 240);
	}
	
	public Color getColorUnitPrimary()
	{
		return new Color(255, 255, 255);
	}

	public Color getColorUnitSecondary()
	{
		return new Color(255, 255, 255);
	}
}
