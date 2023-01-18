package ui.combat;

import org.newdawn.slick.Graphics;

import core.Color;
import core.Main;
import ui.Fonts;
import ui.Text;
import ui.UI;
import ui.combat.healthbar.Guardbar;
import ui.combat.healthbar.Healthbar;
import ui.elements.UIPanel;
import unit.Unit;

public class UnitPanel extends UIPanel
{
	Unit owner;
	Healthbar health;
	Guardbar guard;
	
	public UnitPanel(float x, float y, float w, float h) 
	{
		super(x, y, w, h);

	}
	
	public void setOwner(Unit owner)
	{
		if(this.owner != owner)
		{
			this.owner = owner;
			health = new Healthbar(owner);
			guard = new Guardbar(owner);
		}
	}

	public void update()
	{
		
		
		if(owner != null)
		{		
			health.update(getX() + getWidth() * .28f-5, getY() + getHeight() * .18f, getWidth() * .07f, 5 * Main.getGameScale());	
			guard.update(getX() + getWidth() * .28f-5, getY() + getHeight() * .11f, getWidth() * .07f, 4 * Main.getGameScale());	

			
			//System.out.println(getX() + getWidth() * .28f + " | " +  getY() + getHeight() * .18f);
		}
//		w = Math.round(Main.getScreenWidth() * .15f);
//		h = Math.round(Main.getScreenWidth() * .08f);
	}
	
	public void render(Graphics g)
	{
		if(owner == null)
		{
			return;
		}
		
		super.render(g);
	
		
		
		Text.setFont(Fonts.mediumText);
		
		
		renderPortrait();
		renderHealth(g);
		renderStats(g);
		
	}

	public void renderPortrait()
	{
		owner.getPortrait().drawCentered(getX() + UI.height(.04f),  getY()  + UI.height(.04f), 1);
	}
	
	public void renderHealth(Graphics g)
	{
		health.render(g);
		
		guard.render(g);
		
//		int boxSize = 16;
//		int spacing = boxSize + 5;
//		
//		int x = Math.round(getX() + getWidth() * .28f);
//		int y = Math.round(getY() + getHeight() * .18f);
//
//		for(int i = 0; i < owner.getMaxHealth(); i++)
//		{
//			g.setColor(new Color(140, 140, 140));
//			g.fillRect(x + i * spacing, y, boxSize, boxSize);
//		}
//		
//		for(int i = 0; i < owner.getCurHealth(); i++)
//		{
//			g.setColor(new Color(255, 70, 70));
//			g.fillRect(x + i * spacing, y, boxSize, boxSize);
//		}
//		
//		for(int i = 0; i < owner.getMaxHealth(); i++)
//		{
//			g.setColor(Color.black);
//			g.setLineWidth(1);
//			g.drawRect(x + i * spacing, y, boxSize, boxSize);
//		}
	}
	
	public void renderStats(Graphics g)
	{
		Text.setColor(new Color(255, 255, 255));

		Text.alignLeft();
		Text.alignMiddle();

		// Initiative		
		Text.draw("Initiative", getX() + getWidth() * .25f, getY() + getHeight() * .45f);
		Text.draw(""+owner.getInitiative(), getX() + getWidth() * .60f,  getY() + getHeight() * .45f);

		// Speed
		Text.draw("Speed", getX() + getWidth() * .25f, getY() + getHeight() * .60f);
		Text.draw(""+owner.getSpeed(), getX() + getWidth() * .60f,  getY() + getHeight() * .60f);
				
		// Move
		Text.draw("Movement", getX() + getWidth() * .25f, getY() + getHeight() * .75f);
		Text.draw(""+owner.getMaxMove(), getX() + getWidth() * .60f,  getY() + getHeight() * .75f);
		

	}
}
