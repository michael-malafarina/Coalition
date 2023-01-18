package ui.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ability.Ability;
import ability.ActivatedAbility;
import core.Color;
import core.Main;
import ui.Fonts;
import ui.Text;
import ui.UI;
import ui.elements.MenuPanel;

public class MenuDescription extends MenuPanel
{
	private static Ability focus;
	
	public final int WIDTH = 32;
	
	public MenuDescription() 
	{
		super(UI.width(.8f), 0, UI.width(.2f),  UI.height(1f));
	}
	
	public void update()
	{
		super.update();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		if(focus == null)
		{
			return;
		}
		
		Text.setFont(Fonts.titleText);
		Text.alignLeft();
		Text.alignTop();
		Text.draw(""+focus.getName(), getX() + getWidth() * .05f, getY() + getHeight() * .05f);
		
		
		if(focus.hasIcon())
		{
			focus.getIcon().setFilter(Image.FILTER_NEAREST);
			Image img = focus.getIcon().getScaledCopy(Main.getGameScale() * 2);
			img.draw(getX() + getWidth() * .75f, getY() + getHeight() * .05f);
		}
		
		float y = .10f;
		float step = .025f;
		
		Text.setFont(Fonts.mediumText);

		if(focus.getDiscipline() != null)
		{
			Text.setColor(focus.getDisciplineColorLight());
			Text.draw(""+focus.getDiscipline(),  getX() + getWidth() * .05f, getY() + getHeight() * y, 30);
			y += step;
		}
		
		if(focus.getCost() > 0)
		{
			Text.setColor(Color.white);
			Text.draw("Level "+focus.getCost(),  getX() + getWidth() * .05f, getY() + getHeight() * y, 30);
			y += step;
		}
		
		if(focus instanceof ActivatedAbility && ((ActivatedAbility)focus).getEnergyCost() > 0)
		{
			Text.setColor(Color.white);
			Text.draw("Power "+((ActivatedAbility)focus).getEnergyCost(),  getX() + getWidth() * .05f, getY() + getHeight() * y, 30);
			y += step;
		}

	
		y+= step;
		Text.setColor(Color.white);
		Text.setFont(Fonts.mediumText);
		Text.draw(""+focus.getDescription(),  getX() + getWidth() * .05f, getY() + getHeight() * y, WIDTH);

	}
	
	public static void setFocus(Ability f)
	{
		focus = f;
	}
	
	

}
