package ui.combat;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ability.ActivatedAbility;
import core.Main;
import ui.Fonts;
import ui.MenuColor;
import ui.Text;
import ui.elements.UIPanel;
import unit.Unit;

public class AbilityListPanel extends UIPanel
{
	Unit owner;
	ArrayList<ActivatedAbility> abilities;
		
	public AbilityListPanel(float x, float y, float w) 
	{
		super(x, y, w, 0);
		abilities = new ArrayList<ActivatedAbility>();
	}
	
	public void setOwner(Unit owner)
	{
		this.owner = owner;
	}
	
	public void update()
	{
		super.update();
		
		
		if(owner != null)
		{
			abilities = owner.getAbilities().getActivatedAbilities();
		}
		
		h = abilities.size() * Math.round(Main.getScreenHeight() * .030f);

	}
	
	public void render(Graphics g)
	{
		if(owner == null)
		{
			return;
		}
	
		super.render(g);

		//Text.shadowOn();
		Text.setFont(Fonts.mediumText);
		Text.alignLeft();
		Text.alignMiddle();
		
		
		// Index and Name
		for(ActivatedAbility a : abilities)
		{			
			if(!a.canUse())			{	Text.setColor(MenuColor.DISABLE);		}
			else if(a.isActive())	{	Text.setColor(MenuColor.HIGHLIGHT);		}
			else					{	Text.setColor(MenuColor.BASE);			}
			
			float x = getX();
			float y = getY() + Text.getHeight() * 1.5f * (a.getIndex() + 1 - .5f) + getHeight() * .05f;

			if(owner.isActive() && owner.isPlayer())
			{
				g.setLineWidth(Main.getGameScale());
				float indent = Text.getHeight() * .6f;

				if(a.isActive())
				{		
					g.setColor(new Color(75, 75, 75, 255));

				}
				else
				{
					g.setColor(new Color(50, 50, 50, 255));
				}
				
				g.fillRect(x - indent, y - indent * 1.1f, indent * 2, indent * 2);
				
				
				if(a.isActive())
				{			
					g.setColor(new Color(MenuColor.HIGHLIGHT.getColor()));
				}	
				else
				{
					g.setColor(outline);
				}
				
				g.drawRect(x - indent, y - indent * 1.1f, indent * 2, indent * 2);
				
		
				
				
				Text.alignCenter();
				Text.alignMiddle();
				
				if((a.getIndex()+1) == abilities.size() )
				{
					Text.draw("E", x, y);
				}
				else
				{
					Text.draw(""+(a.getIndex()+1), x, y);
				}
			
			}
			
			Text.alignLeft();
			Text.alignMiddle();
			
			Text.draw(""+a, getX() + getWidth() * .13f, y);
			
			if(a.getIcon() != null)
			{
				a.getIcon().setFilter(Image.FILTER_NEAREST);
				a.getIcon().draw(getX() + getWidth() * .05f, y - Text.getHeight()/2, Main.getGameScale());
			}

		}
		
		// Charges
		for(ActivatedAbility a : abilities)
		{
			int boxHeight = Text.getHeight() * 4 / 5;
			int boxWidth = boxHeight;

			int spacing = boxWidth;
			float x = getX() + getWidth() * .65f;
			float y = getY() + Text.getHeight() * 1.5f * (a.getIndex() + 1 - .5f) + getHeight() * .05f - boxHeight/2;
			
			
		//	System.out.println("ABILITY LIST" + a + " " + a.getCost() + " " + a.getCharge());
			
			for(int i = 0; i < a.getEnergyCost(); i++)
			{
				g.setColor(new Color(140, 140, 140));
				g.fillRect(x + i * spacing, y, boxWidth, boxHeight);
			}
			
			for(int i = 0; i < a.getCharge(); i++)
			{
				g.setColor(MenuColor.CHARGES.getColor());
				g.fillRect(x + i * spacing, y, boxWidth, boxHeight);
			}
			
			for(int i = 0; i < a.getEnergyCost(); i++)
			{
				g.setColor(Color.black);
				g.setLineWidth(1 * Main.getGameScale());
				g.drawRect(x + i * spacing, y, boxWidth, boxHeight);
			}
	
		//	g.drawString(""+a, getX() + 85, getY() + 30 * a.getIndex());
		}
	}

}
