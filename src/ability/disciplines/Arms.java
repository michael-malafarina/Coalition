package ability.disciplines;

import ability.Discipline;
import ability.base.arms.active.CarefulStudy;
import ability.base.arms.active.DeepCut;
import ability.base.arms.active.SecondWind;
import ability.base.arms.active.Slash;
import ability.base.arms.active.ThrowHandaxe;
import ability.base.arms.active.TwinStrike;
import ability.base.arms.passive.GrittySurvivor;
import core.Color;
import ui.Images;
import unit.PlayerUnit;

public class Arms extends Discipline
{
	
	public Arms(PlayerUnit owner)
	{
		super(owner);
		addAbilities();
		icon = Images.iconArms;
	}
	
	public Arms()
	{
		this(null);
	}
	
	public void addAbilities()
	{
		// Active
		addAbility(CarefulStudy.class);
		addAbility(DeepCut.class);
		addAbility(SecondWind.class);
		addAbility(Slash.class);
		addAbility(ThrowHandaxe.class);
		addAbility(TwinStrike.class);
		
		// Passive
		addAbility(GrittySurvivor.class);

	}

	public Color getColorMenuDark()
	{
		return new Color(80, 20, 10);
	}
	
	public Color getColorMenuLight()
	{
		return new Color(200, 60, 30);
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
