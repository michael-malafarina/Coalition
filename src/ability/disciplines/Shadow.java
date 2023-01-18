package ability.disciplines;

import ability.Discipline;
import ability.special.shadow.active.LifeTransference;
import ability.special.shadow.active.LifeTap;
import ability.special.shadow.active.ShadowStrike;
import ability.special.shadow.active.UnholyPact;
import core.Color;
import ui.Images;
import unit.PlayerUnit;
import unit.UnitColor;

public class Shadow extends Discipline
{
	public Shadow(PlayerUnit owner)
	{
		super(owner);
		addAbilities();
		icon = Images.iconShadow;
	}
	
	public Shadow()
	{
		this(null);
	}
	
	public void addAbilities()
	{
		addAbility(LifeTransference.class);
		addAbility(LifeTap.class);
		addAbility(ShadowStrike.class);
		addAbility(UnholyPact.class);
	}
	
	public Color getColorMenuDark()
	{
		return new Color(60, 60, 60);
	}
	
	public Color getColorMenuLight()
	{
		return new Color(220, 220, 220);
	}
	
	public Color getColorUnitPrimary()
	{
		return UnitColor.BLACK.getColor();
	}

	public Color getColorUnitSecondary()
	{
		return UnitColor.INDIGO.getColor();
	}
	
}
