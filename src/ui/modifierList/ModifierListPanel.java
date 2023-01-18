package ui.modifierList;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import ability.PassiveAbility;
import core.Color;
import modifier.Modifier;
import modifier.ModifierSet;
import modifier.conditions.Condition;
import ui.Fonts;
import ui.Text;
import ui.elements.UIPanel;
import unit.Unit;

public class ModifierListPanel extends UIPanel
{
	ArrayList<PassiveAbility> passives;
	ArrayList<Condition> conditions;

	Unit owner;
	
	float bottomX;
	float bottomY;
		
	public ModifierListPanel(float x, float y, float w)
	{
		super(x, y);
		
		this.w = w;
		
		bottomX = x;
		bottomY = y;
				
		passives = new ArrayList<PassiveAbility>();
		conditions = new ArrayList<Condition>();
		

		
		//centerAt(x,  y,  w,  h);		
	}
	
	public void setOwner(Unit owner)
	{
		this.owner = owner;
	}
	
	public void update()
	{
		if(owner == null)
		{
			return;
		}
		
		passives.clear();
		conditions.clear();
		
		ModifierSet mods = owner.getModifiers();
		ArrayList<Modifier> modifiers = mods.getVisibleModifiers();
		//System.out.println("printing modifiers for " + this);

		
		for(Modifier m : modifiers)
		{
			//System.out.println(m);

			
			if(m instanceof PassiveAbility)
			{
				passives.add((PassiveAbility)m);
			}
			if(m instanceof Condition )
			{
				conditions.add((Condition)m);
			}
		}
	}
	
	public void render(Graphics g)
	{		
		if(owner == null || (passives.isEmpty() && conditions.isEmpty()))
		{
			return;
		}
		
		super.render(g);
		
		Text.alignLeft();
		Text.alignTop();
		Text.setFont(Fonts.smallText);
		
		int lh = Text.getHeight("a");		// letter height
		int ph = (int) (lh * 1.6f);			// padding height
		int pw = lh;
		int spacing = (int) (lh * 1.5f);
		
		h = ph + (lh + spacing) * (passives.size() + conditions.size());
	
		y = bottomY - h;
		
		float curX = x;
		float curY = y;
			
		for(int i = 0; i < conditions.size(); i++)
		{
			Condition cur = conditions.get(i);
			
			curX = x + pw/2;
			curY = y + i * (lh+spacing) + ph /2 + lh/2 ;
	
			if(cur.hasIcon())
			{
				cur.getIcon().draw(curX, curY, Modifier.getIconSize(), Modifier.getIconSize());
			}
			
			curX = x + Modifier.getIconSize() + pw;			
			Text.setFont(Fonts.smallText);
			Text.setColor(Color.white);
			Text.draw(""+cur.getName() + " (" + cur.getTurnsLeft() + " turns)", curX, curY);
			
			curY = curY + lh;	
			Text.setFont(Fonts.tinyText);
			Text.setColor(new Color(175, 175, 175));
			Text.draw(""+cur.getDescriptionShort(), curX, curY);		
		}
	
		for(int i = 0; i < passives.size(); i++)
		{		
			PassiveAbility cur = passives.get(i);

			curX = x + pw/2;
			curY = y + (i+conditions.size()) * (lh+spacing) + ph / 2 + lh/2;
			Text.setFont(Fonts.smallText);
			Text.setColor(Color.white);
			Text.draw(""+cur.getName(), curX, curY);
			
			curY = curY + lh;
			Text.setFont(Fonts.tinyText);
			Text.setColor(new Color(175, 175, 175));
			Text.draw(""+cur.getDescriptionShort(), curX, curY);		

		}
	
	}
	

	
}
