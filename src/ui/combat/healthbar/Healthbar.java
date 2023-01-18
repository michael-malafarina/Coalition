package ui.combat.healthbar;

import ability.ActivatedAbility;
import core.Main;
import core.Settings;
import states.combat.CombatManager;
import ui.MenuColor;
import unit.Unit;
import unit.manager.UnitManager;

public class Healthbar extends Databar
{	
	public Healthbar(Unit unit)
	{
		super(unit);
	}
	
	public void update()
	{
		if(Settings.fixedHealthbars)
		{
			max = UnitManager.getHighestMaxHealth();
		}
		else
		{
			max = unit.getMaxHealth();
		}

		x = unit.getXPixel() + 2 * Main.getGameScale();
		h = 4 * Main.getGameScale();
		y = unit.getYPixel() - Main.getCellSize() / 2 - h * index - 2 * Main.getGameScale();
		w = (Main.getCellSize() - Main.getGameScale() * 2) / max;
		update(x, y, w, h);
	}	
	
	public void update(float x, float y, float w, float h)
	{
		super.update();
		
		ActivatedAbility a = CombatManager.getActiveAbility();

		if(a != null)
		{
			
			int damage = unit.calculateDamage(a, a.getTargetDamageEstimate());
			int healing = unit.calculateHealing(a, a.getTargetHealingEstimate());
			
			//System.out.println(damage + " " + healing);
			if(damage > healing)
			{
				lossAmount = damage - healing;
				gainAmount = 0;
				
				if(!a.ignoresGuard() && unit.getGuard() > 0)
				{
					lossAmount -= 1;
				}
			}
			else if(damage < healing)
			{
				gainAmount = healing - damage;
				lossAmount = 0;
			}
			else
			{
				gainAmount = 0;
				lossAmount = 0;
			}
		}
		else
		{
			gainAmount = 0;
			lossAmount = 0;
		}
		
		//gainAmount = 2;
		index = 0;
		current = unit.getCurHealth();
		max = unit.getMaxHealth();
		color = MenuColor.HEALTH.getColor();		
		renderEmpty = true;
		this.x = x;
		this.h = h;
		this.y = y;
		this.w = w;
		spacing = w;
	
	}
}
