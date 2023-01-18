package ui.menu;

import org.newdawn.slick.Graphics;

import core.Color;
import core.Main;
import states.PartyMenu;
import ui.Fonts;
import ui.MenuColor;
import ui.Text;
import ui.UI;
import ui.elements.MenuPanel;
import unit.Unit;

public class MenuAttributes extends MenuPanel
{
	Unit unit;
	public MenuAttributes() 
	{
		super(UI.width(.2f), 0, UI.width(.6f), UI.height(.10f));
	}
	
	public void update()
	{
		super.update();
		unit = PartyMenu.getCurrentUnit();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		// Name
		Text.setColor(Color.white);
		Text.setFont(Fonts.titleText);
		Text.alignLeft();
		Text.alignMiddle();
		Text.draw(unit.getName(), x + getWidth() * .025f, y + getHeight() * .50f);
		
		
		// Draw Attributes
		drawAttribute(g, MenuColor.HEALTH.getColor(), "Health", ""+unit.getMaxHealth(), getX() + getWidth() * .40f, getY() + getHeight() * .50f);
		drawAttribute(g, MenuColor.GUARD.getColor(), "Guard",  ""+unit.getStartingGuard(), getX() + getWidth() * .50f, getY() + getHeight() * .50f);
		drawAttribute(g, new Color(255, 255, 0), "Energy", ""+unit.getStartingEnergy(), getX() + getWidth() * .60f, getY() + getHeight() * .50f);		
		drawAttribute(g, new Color(255, 120, 0), "Speed", ""+unit.getSpeed(), getX() + getWidth() * .70f, getY() + getHeight() * .50f);
		drawAttribute(g, new Color(0, 200, 255),"Move",  ""+unit.getMaxMove(), getX() + getWidth() * .80f, getY() + getHeight() * .50f);
		drawAttribute(g, new Color(255, 0, 255),"SP",  ""+unit.getSkillPoints(), getX() + getWidth() * .90f, getY() + getHeight() * .50f);
	}
	
	public void drawAttribute(Graphics g, Color c, String label, String data, float x, float y)
	{
		g.setLineWidth(Main.getGameScale());
		//g.setColor(c);

		
		// Draw Background
		float xPos = x - getWidth() * .04f;
		float yPos = y - getHeight() * .26f;
		float w = getWidth() * .08f;
		float h = getHeight() * .50f;
		g.setColor(new Color(40, 40, 40));
		g.fillRoundRect(xPos, yPos, w, h, Main.getGameScale() * 4);
		g.setColor(new Color(c.r, c.g, c.b, .05f));
		g.fillRoundRect(xPos, yPos, w, h, Main.getGameScale() * 4);
		g.setColor(c.darker(.5f));
		g.drawRoundRect(xPos, yPos, w, h, Main.getGameScale() * 4);

		// Draw Data
		Text.setColor(c);
		Text.setFont(Fonts.titleText);
		Text.alignCenter();
		Text.alignMiddle();
		Text.draw(data, x, y - getHeight() * .08f);
		
		// Draw Label
		Text.setColor(Color.white);
		Text.setFont(Fonts.smallTextLight);
		Text.alignCenter();
		Text.alignMiddle();
		Text.draw(label, x, y + getHeight() * .14f);


	}
}
