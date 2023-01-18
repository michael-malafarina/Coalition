package ability.disciplines;

import ability.Discipline;
import ability.special.growth.active.Regeneration;
import ability.special.growth.active.Tend;
import ability.special.growth.active.Tranquility;
import ability.special.growth.passive.Blossom;
import ability.special.growth.passive.OakenResilience;
import core.Color;
import ui.Images;
import unit.PlayerUnit;
import unit.UnitColor;

public class Growth extends Discipline
{
	public Growth(PlayerUnit owner)
	{
		super(owner);
		addAbilities();
		icon = Images.iconGrowth;

	}
	
	public Growth()
	{
		this(null);
	}
	
	public void addAbilities()
	{
		addAbility(Regeneration.class);
		addAbility(Tend.class);
		addAbility(Tranquility.class);
		
		addAbility(Blossom.class);
		addAbility(OakenResilience.class);
	}
	
	public Color getColorMenuDark()
	{
		return new Color(10, 80, 10);
	}
	
	public Color getColorMenuLight()
	{
		return new Color(30, 240, 30);
	}
	
	public Color getColorUnitPrimary()
	{
		return UnitColor.HUNTER.getColor();
	}

	public Color getColorUnitSecondary()
	{
		return UnitColor.GREEN.getColor();
	}
}
