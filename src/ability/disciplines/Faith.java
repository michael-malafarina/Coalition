package ability.disciplines;

import ability.Discipline;
import ability.base.faith.active.BlessingOfStrength;
import ability.base.faith.active.CircleOfProtection;
import ability.base.faith.active.HealingStrike;
import ability.base.faith.active.Mending;
import ability.base.faith.active.SacredFlame;
import ability.base.faith.active.Smite;
import core.Color;
import ui.Images;
import unit.PlayerUnit;

public class Faith extends Discipline
{
	public Faith(PlayerUnit owner)
	{
		super(owner);
		addAbilities();
		icon = Images.iconFaith;

	}
	
	public Faith()
	{
		this(null);
	}
	
	public void addAbilities()
	{
		addAbility(BlessingOfStrength.class);
		addAbility(CircleOfProtection.class);
		addAbility(Mending.class);
		addAbility(HealingStrike.class);
		addAbility(SacredFlame.class);
		addAbility(Smite.class);
	}
	
	public Color getColorMenuDark()
	{
		return new Color(10, 60, 80);
	}
	
	public Color getColorMenuLight()
	{
		return new Color(30, 180, 240);
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
