package ui.menu;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import ability.ActivatedAbility;
import ability.PassiveAbility;
import states.PartyMenu;
import ui.Fonts;
import ui.Text;
import ui.UI;
import ui.elements.MenuPanel;

public class MenuAbilitiesPanel extends MenuPanel
{
	ArrayList<ActivatedAbility> activatedAbilities;
	ArrayList<PassiveAbility> passiveAbilities;

	ArrayList<MenuAbilityButton> activeMenuAbilityButtons;
	ArrayList<MenuAbilityButton> passiveMenuAbilityButtons;

	public MenuAbilitiesPanel() 
	{
		super(UI.width(.2f), UI.height(.1f), UI.width(.6f), UI.height(.9f));
	}
	
	public void update()
	{
		super.update();
		
		activatedAbilities = PartyMenu.getCurrentUnit().getAbilities().getAllActivatedAbilities();
		passiveAbilities = PartyMenu.getCurrentUnit().getAbilities().getAllPassiveAbilities();

		activeMenuAbilityButtons = new ArrayList<MenuAbilityButton>();
		passiveMenuAbilityButtons = new ArrayList<MenuAbilityButton>();
	
		float indent = getWidth() * .05f;
		
		for(int i = 0; i < activatedAbilities.size(); i++)
		{		
			ActivatedAbility a = activatedAbilities.get(i);
			float x = getX() + indent;
			float yPos = i + 1;
			if(!a.isLearned())
			{
				yPos++;
			}
			float y = getY() + Text.getHeight() * 1.8f * (yPos - .5f) + getHeight() * .05f;
			Text.setFont(Fonts.mediumText);
			
			MenuAbilityButton b = new MenuAbilityButton(a, x, y, getWidth()/2 - indent * 2, Text.getHeight());
			b.update();
			activeMenuAbilityButtons.add(b);

		}
		
		for(int i = 0; i < passiveAbilities.size(); i++)
		{		
			PassiveAbility a = passiveAbilities.get(i);

			float x = getX() + getWidth() / 2 + indent;
			float yPos = i + 1;
			if(!a.isLearned())
			{
				yPos++;
			}
			float y = getY() + Text.getHeight() * 1.8f * (yPos - .5f) + getHeight() * .05f;
			Text.setFont(Fonts.mediumText);
			
			MenuAbilityButton b = new MenuAbilityButton(a, x, y, getWidth()/2 - indent * 2, Text.getHeight());
			b.update();
			activeMenuAbilityButtons.add(b);
			
		}
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		for(MenuAbilityButton m : activeMenuAbilityButtons)
		{			
			m.render(g);
		}
		
		for(MenuAbilityButton m : passiveMenuAbilityButtons)
		{			
			m.render(g);
		}
	}
	

}
