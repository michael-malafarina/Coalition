package ability.disciplines;

import ability.Discipline;
import ability.special.time.active.Flux;
import ability.special.time.active.HasteAbility;
import ability.special.time.active.Quicken;
import ability.special.time.active.SlowAbility;
import ability.special.time.active.TimeStop;
import ability.special.time.passive.Acceleration;
import ability.special.time.passive.Overclock;
import core.Color;
import ui.Images;
import unit.PlayerUnit;
import unit.UnitColor;

public class Time extends Discipline
{
	public Time(PlayerUnit owner)
	{
		super(owner);
		addAbilities();
		icon = Images.iconTime;
	}
	
	public Time()
	{
		this(null);
	}
	
	public void addAbilities()
	{
		// Active
		addAbility(Flux.class);
		addAbility(HasteAbility.class);
		addAbility(TimeStop.class);
		addAbility(SlowAbility.class);
		addAbility(Quicken.class);

		// Passive
		addAbility(Acceleration.class);
		addAbility(Overclock.class);

	}
	
	public Color getColorMenuDark()
	{
		return new Color(40, 10, 80);
	}
	
	public Color getColorMenuLight()
	{
		return new Color(120, 30, 220);
	}
	
	public Color getColorUnitPrimary()
	{
		return UnitColor.PURPLE.getColor();
	}

	public Color getColorUnitSecondary()
	{
		return UnitColor.GOLD.getColor();
	}
	
}
