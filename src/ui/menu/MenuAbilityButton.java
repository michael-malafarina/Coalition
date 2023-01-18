package ui.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ability.Ability;
import ability.ActivatedAbility;
import ability.Tag;
import core.Color;
import core.Main;
import ui.MenuColor;
import ui.Text;
import ui.elements.UIButton;

public class MenuAbilityButton extends UIButton 
{
	Ability ability;
	
	MenuAbilityButton(Ability a, float x, float y, float w, float h)
	{
		super(x, y, w, h);
		
		ability = a;		
	}

	public void onMouseOver() 
	{
		MenuDescription.setFocus(ability);
	}

	public void onClick() 
	{
		if(!ability.isLearned() && ability.getOwner().getSkillPoints() >= ability.getCost())
		{
			ability.getOwner().spendSkillPoints(ability.getCost());
			ability.learn();
		}
	}
	
	public void render(Graphics g)
	{		
		// Background Color
		Color c = ability.getDisciplineColorDark();


		
		if(ability.isLearned())
		{
			renderBar(g, new Color(40, 40, 40));
			renderBar(g, new Color(c.r, c.g, c.b, .7f));
			Text.setColor(MenuColor.HIGHLIGHT);
		}
		else if(ability.getOwner().getSkillPoints() >= ability.getCost())
		{
			renderBar(g, new Color(40, 40, 40));
			renderBar(g, new Color(c.r, c.g, c.b, .7f));
			Text.setColor(MenuColor.BASE);
		}
		else
		{
			renderBar(g, new Color(30, 30, 30));
			renderBar(g, new Color(c.r, c.g, c.b, .2f));
			Text.setColor(MenuColor.DISABLE);
		}		

		if(isMouseOver())
		{
			renderBar(g, new Color(100, 100, 100, 100));
		}
		
		Text.alignLeft();
		Text.alignTop();
		Text.draw(""+ability, getX() + getWidth() * .08f, y);
		
		if(ability.getIcon() != null)
		{
			ability.getIcon().setFilter(Image.FILTER_NEAREST);
			ability.getIcon().draw(getX(), y , Main.getGameScale());
		}
		
		if(ability.getCost() > 0 && !ability.isLearned())
		{
			Text.draw(""+ability.getCost(), getX() + getWidth() * .55f, y);
		}
		
		if(ability instanceof ActivatedAbility)
		{
			ActivatedAbility a = (ActivatedAbility) ability;
			
			for(int i = 0; i < a.getEnergyCost(); i++)
			{
				float x = getX() + getWidth() * .65f;
				float size = Text.getHeight() * .8f;
				float spacing = i * size * 1.3f;
				
				if(i < a.getOwner().getStartingEnergy() || a.hasTag(Tag.READY))
				{
					g.setColor(MenuColor.HIGHLIGHT.getColor());
				}
				else
				{
					g.setColor(MenuColor.DISABLE.getColor());
				}
				g.fillRect(x + spacing, getY() + (getHeight()-size)/2, size, size);
				g.setLineWidth(Main.getGameScale());
				g.setColor(Color.black);
				g.drawRect(x + spacing, getY() + (getHeight()-size)/2, size, size);

			}
		}

	}
	
	private void renderBar(Graphics g, Color c)
	{
		g.setColor(c);
		g.setLineWidth(Main.getGameScale());
		g.fillRoundRect(getX() - getWidth() * .05f, getY() - getHeight() * .20f, getWidth() * 1.1f, getHeight() * 1.4f, 12);	
				
		g.setColor(new Color((c.r + .5f)/3, (c.g+.5f)/3, (c.b+.5f)/3, 1f));
		g.drawRoundRect(getX() - getWidth() * .05f, getY() - getHeight() * .20f, getWidth() * 1.1f, getHeight() * 1.4f, 12);	

	}
}
