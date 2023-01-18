package ui.menu;

import org.newdawn.slick.Graphics;

import core.CampaignManager;
import core.Color;
import ui.Fonts;
import ui.Text;
import ui.UI;
import ui.elements.MenuPanel;

public class MenuProgression extends MenuPanel
{
	public MenuProgression() 
	{
		super(0, 0, UI.width(.2f), UI.height(.20f));
	}
	
	public void update()
	{
		super.update();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		Text.setFont(Fonts.mediumText);
		Text.setColor(Color.white);
		Text.alignLeft();
		Text.alignTop();
		Text.draw("Stage:      " + CampaignManager.getStage(), getX() + getWidth() * .05f, getY() + getHeight() * .05f);
		Text.draw("Difficulty: " + CampaignManager.getCombatDifficulty(), getX() + getWidth() * .05f, getY() + getHeight() * .15f);

	}
	

}
