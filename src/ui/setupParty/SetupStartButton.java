package ui.setupParty;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import core.Main;
import ui.Fonts;
import ui.MenuColor;
import ui.Text;
import ui.UI;
import ui.elements.UIButton;
import unit.Unit;
import unit.manager.CoalitionManager;

public class SetupStartButton extends UIButton
{
	CandidatePool p;
	StateBasedGame sbg;
	
	public SetupStartButton(CandidatePool p, StateBasedGame sbg)
	{
		super(UI.width(.4f), UI.height(.9f), UI.width(.2f), UI.height(.05f));
		this.p = p;
		this.sbg = sbg;
	}
	
	public void render(Graphics g)
	{
		
		if(isReady())		
		{	
			background = MenuColor.UNIT_BACKGROUND_SELECT.getColor();
		}
		else
		{
			background = MenuColor.UNIT_BACKGROUND.getColor();
		}
		
		super.render(g);

		
		
		Text.alignCenter();
		Text.alignMiddle();
		Text.setFont(Fonts.titleText);
		Text.draw("Start", getX()+getWidth()/2, getY() + getHeight() * .5f);
	}

	@Override
	public void onMouseOver() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onClick() 
	{
		if(isReady())
		{
			for(Unit u : p.getSelectedCandidates())
			{
				CoalitionManager.addUnit(u);
			}
		
			sbg.enterState(Main.COMBAT_ID);
		}
		
	}
	
	public boolean isReady()
	{
		return !p.hasSpace();
	}
	
}
