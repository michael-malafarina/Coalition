package ability.disciplines;

import ability.Discipline;
import ability.base.general.Energy;
import ability.base.general.Guard;
import ability.base.general.Health;
import ability.base.general.Movement;
import ability.base.general.Speed;
import core.Color;
import ui.Images;
import unit.PlayerUnit;

public class General extends Discipline
{
	
	public General(PlayerUnit owner)
	{
		super(owner);
		addAbilities();
		icon = Images.iconArms;
	}
	
	public General()
	{
		this(null);
	}
	
	public void addAbilities()
	{
		// Active

		// Passive
		addAbility(Health.class);
		addAbility(Guard.class);
		addAbility(Energy.class);
		addAbility(Movement.class);
		addAbility(Speed.class);

	}

	public Color getColorMenuDark()
	{
		return new Color(80, 50, 30);
	}
	
	public Color getColorMenuLight()
	{
		return new Color(190, 130, 100);
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
