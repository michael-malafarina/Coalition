package ui.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ability.Discipline;
import core.Color;
import core.Main;
import states.PartyMenu;
import ui.Fonts;
import ui.MenuColor;
import ui.Text;
import ui.elements.UIButton;
import unit.Unit;

public class MenuUnitButton extends UIButton 
{
	Unit unit;
	
	MenuUnitButton(Unit u, float x, float y, float w, float h)
	{
		super(x, y, w, h);
		
		unit = u;		
	}

	public void onMouseOver() 
	{
	}

	public void onClick() 
	{
		PartyMenu.setCurrentUnit(unit);
	}
	
	public void render(Graphics g)
	{		

		if(unit == PartyMenu.getCurrentUnit())			
		{	
			Text.setColor(MenuColor.HIGHLIGHT);	
			g.setColor(MenuColor.UNIT_BACKGROUND_SELECT.getColor());
		}
		else
		{
			g.setColor(MenuColor.UNIT_BACKGROUND.getColor());
		}
		
		g.fillRect(getX(), getY(), getWidth(), getHeight());

		
		
		outline = new Color(10, 10, 10, 255);
		int edge = 2 * Main.getGameScale();
		g.setLineWidth(edge);
		g.setColor(outline);
		g.drawRect(x,  y,  w,  h);
		Text.setColor(MenuColor.BASE);	
		

		if(isMouseOver())
		{
			g.setColor(new Color(100, 100, 100, 100));
			g.fillRect(getX()+edge, getY()+edge, getWidth()-edge*2, getHeight()-edge);
		}
		
		// Name
		Text.setFont(Fonts.titleText);
		Text.alignCenter();
		Text.alignMiddle();
		Text.draw(unit.getName(), getX() + getWidth() * .60f, y + getHeight() * .25f);
		
		// Class
		Text.setFont(Fonts.mediumText);
		Text.draw(unit.getType(), getX() + getWidth() * .60f, y + getHeight() * .50f);

		// Skill Points
		Text.draw(""+unit.getSkillPoints(), getX() + getWidth() * .92f, y + getHeight() * .44f);
		Text.setFont(Fonts.mediumText);
		Text.draw("SP", getX() + getWidth() * .92f, y + getHeight() * .56f);

		
		if(unit.getPortrait() != null)
		{
//			ability.getIcon().setFilter(Image.FILTER_NEAREST);
			unit.getPortrait().drawCentered(getX() + getWidth() * .2f, getY() + getHeight() * .35f, 1);
		}
		
		int size = unit.getDisciplines().size() - 1;
		for(int i = 0; i < size; i++)
		{
			Discipline d = unit.getDisciplines().get(i);
			
			float x = getX() + getWidth() * .6f ;

			if(size > 1)
			{
				float percent = (float) i / (float) (size-1);
				float width = getWidth() * .05f * size;
				x += percent * width - width/2;
			}
			
			d.getIcon().setFilter(Image.FILTER_NEAREST);
			Image img = d.getIcon().getScaledCopy(Main.getGameScale());
			img.drawCentered(x, getY() + getHeight() * .75f);
		}



	}
	

}
