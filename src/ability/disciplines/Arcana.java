package ability.disciplines;

import ability.Discipline;
import ability.base.arcana.active.ArcaneMissile;
import ability.base.arcana.active.Brilliance;
import ability.base.arcana.active.DimensionalSwap;
import ability.base.arcana.active.EnergyBarrier;
import core.Color;
import ui.Images;
import unit.PlayerUnit;

public class Arcana extends Discipline
{
	public Arcana(PlayerUnit owner)
	{
		super(owner);
		addAbilities();
		icon = Images.iconArcana;
	}
	
	public Arcana()
	{
		this(null);
	}
	
	public void addAbilities()
	{
		addAbility(ArcaneMissile.class);
		addAbility(EnergyBarrier.class);
		addAbility(DimensionalSwap.class);
		addAbility(Brilliance.class);
	}
	
	public Color getColorMenuDark()
	{
		return new Color(60, 10, 80);
	}
	
	public Color getColorMenuLight()
	{
		return new Color(180, 30, 240);
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
