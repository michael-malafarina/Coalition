package ability.disciplines;

import ability.Discipline;
import ability.special.fire.active.Emberstorm;
import ability.special.fire.active.Fireball;
import ability.special.fire.active.Pyroclasm;
import ability.special.fire.active.ScorchingRay;
import ability.special.fire.passive.Firelord;
import core.Color;
import ui.Images;
import unit.PlayerUnit;
import unit.UnitColor;

public class Fire extends Discipline
{
	public Fire(PlayerUnit owner)
	{
		super(owner);
		addAbilities();
		icon = Images.iconFlame;

	}
	
	public Fire()
	{
		this(null);
	}
	
	public void addAbilities()
	{
		addAbility(ScorchingRay.class);
		addAbility(Pyroclasm.class);
		addAbility(Fireball.class);
		addAbility(Emberstorm.class);

		addAbility(Firelord.class);

	}
	
	public Color getColorMenuDark()
	{
		return new Color(80, 40, 10);
	}
	
	public Color getColorMenuLight()
	{
		return new Color(240, 120, 30);
	}
	
	public Color getColorUnitPrimary()
	{
		return UnitColor.RED.getColor();
	}

	public Color getColorUnitSecondary()
	{
		return UnitColor.ORANGE.getColor();
	}
}
