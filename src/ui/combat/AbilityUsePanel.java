package ui.combat;

import org.newdawn.slick.Graphics;

import ability.ActivatedAbility;
import core.Main;
import states.combat.CombatManager;
import ui.Fonts;
import ui.MenuColor;
import ui.Text;

public class AbilityUsePanel extends Panel
{
	ActivatedAbility ability;
		
	public final int DESC_WIDTH = 50;
	
	public void update()
	{
		super.update();
		
		if(CombatManager.hasActiveAbility())
		{
			ability = CombatManager.getActiveAbility();
		}	
		else if(CombatManager.hasDelayedAbility())
		{
			ability = CombatManager.getDelayedAbility();
		}
		else
		{
			ability = null;
			return;
		}
		
		
		
		
		width = Math.round(Main.getScreenWidth() * .30f);
		height = Math.round(Main.getScreenHeight() * .10f);
		x = Math.round(Main.getScreenWidth() * .5f) - width/2;
		y = Math.round(Main.getScreenHeight() * .02f);
		
	}
	
	public void render(Graphics g)
	{

		if(ability == null)
		{
			return;
		}
		
		renderBackground(g);

		
		
		Text.alignCenter();
		Text.alignMiddle();
		Text.setColor(MenuColor.TITLE);

		//Text.shadowOn();
		//Text.shadowSize(2);
		
		Text.setFont(Fonts.titleText);
		Text.draw(ability.getName(), x + width *.5f, y + height * .30f, DESC_WIDTH);
		//Text.shadowOff();
		
		//Text.shadowSize(1);


		Text.setColor(MenuColor.BASE);
		Text.setFont(Fonts.smallText);
		
		int descHeight = Text.getHeight(ability.getDescription(), DESC_WIDTH);

		
		Text.draw(ability.getDescription(), x + width *.5f, y + height * .78f - descHeight / 2, DESC_WIDTH);

		//Text.outlineOff();

		
	}

}
