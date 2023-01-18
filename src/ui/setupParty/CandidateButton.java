package ui.setupParty;

import org.newdawn.slick.Graphics;

import ui.Fonts;
import ui.MenuColor;
import ui.Text;
import ui.elements.UIButton;
import unit.Unit;

public class CandidateButton extends UIButton
{
	CandidatePool p;
	Unit u;
	boolean selected;
	
	public CandidateButton(Unit u, CandidatePool p, float x, float y, float w, float h)
	{
		super(x, y, w, h);
		this.u = u;
		this.p = p;
	}
	
	public Unit getUnit()
	{
		return u;
	}
	
	public void render(Graphics g)
	{
		if(isSelected())		
		{	
			background = MenuColor.UNIT_BACKGROUND_SELECT.getColor();
		}
		else
		{
			background = MenuColor.UNIT_BACKGROUND.getColor();
		}
		
		
		super.render(g);
		

		
		
		u.getPortrait().drawCentered(getX()+getWidth()/2, getY() + getHeight() * .5f, 2);
		
		Text.alignCenter();
		Text.alignMiddle();
		Text.setFont(Fonts.titleText);
		Text.draw(u.getName(), getX()+getWidth()/2, getY() + getHeight() * .15f);
		Text.draw(u.getType(), getX()+getWidth()/2, getY() + getHeight() * 1.25f);
	}

	@Override
	public void onMouseOver() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onClick() 
	{
		if(!isSelected() && p.hasSpace())
		{
			selected = true;
		}
		else
		{
			selected = false;
		}
		
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
}
