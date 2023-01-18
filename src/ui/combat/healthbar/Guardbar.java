package ui.combat.healthbar;

import org.newdawn.slick.Graphics;

import ability.ActivatedAbility;
import core.Main;
import core.Settings;
import states.combat.CombatManager;
import ui.MenuColor;
import unit.Unit;
import unit.manager.UnitManager;

public class Guardbar extends Databar
{	
	public Guardbar(Unit unit)
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
		h = 3 * Main.getGameScale();
		y = unit.getYPixel() - Main.getCellSize() / 2 - h * index - 2 * Main.getGameScale();
		
		//w = Main.getCellSize() / max ;

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
			int guard = a.getTargetGuardEstimate();
			int shred = a.getTargetShredEstimate();

			if(guard > shred)
			{
				gainAmount = guard - shred;
				lossAmount = 0;
			}
			else if(shred > guard)
			{
				gainAmount = 0;
				lossAmount = shred - guard;
			}
			else
			{
				gainAmount = 0;
				lossAmount = 0;
			}

			if(damage >= 1 && !a.ignoresGuard() && unit.getGuard() > 0)
			{				
				lossAmount += 1;
			}
			
		}
		else
		{
			gainAmount = 0;
			lossAmount = 0;
		}
		
		index = 1;
		current = unit.getGuard();
		max = unit.getMaxHealth();
		color = MenuColor.GUARD.getColor();
		renderEmpty = false;
		this.x = x;
		this.h = h;
		this.y = y;
		this.w = w;
		spacing = w;
	
	}
	
	public void render(Graphics g)
	{
//		if(current > 0)
		//{
			super.render(g);
		//}
	}
}
