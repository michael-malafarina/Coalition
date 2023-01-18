package ui.elements;

import org.newdawn.slick.Graphics;

import core.Color;
import core.Main;
import ui.Mouse;
import ui.Text;

public abstract class UIButton extends UIBox
{	
	protected String label;
	boolean clicked;

	
	public UIButton(String label, float x, float y, float w, float h)
	{
		super(x, y, w, h);

		this.label = label;
		background = new Color(50, 50, 50, 255);
		outline = new Color(150, 150, 150, 255);
	}
	
	public UIButton(float x, float y, float w, float h)
	{
		this("", x, y, w, h);
	}
	
	abstract public void onMouseOver();
	abstract public void onClick();
	
	public boolean isMouseOver()
	{
		return Mouse.getXPixelScreen() > x && Mouse.getXPixelScreen() < x + w &&  Mouse.getYPixelScreen() > y &&  Mouse.getYPixelScreen() < y + h;
	}
	
	public boolean isClicked()
	{			
	//	System.out.println(clicked);

		if(isMouseOver() && Main.getGameContainer().getInput().isMousePressed(0))
		{
			//System.out.println("CLACK");

			
			clicked = true;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean wasClicked()
	{
		return clicked;
	}
		
	public void update()
	{
		if(isMouseOver())
		{
			onMouseOver();
		}
		if(isClicked())
		{
			onClick();
		}
	}
	
	public void render(Graphics g)
	{
		g.setLineWidth(Main.getGameScale());

		
		g.setColor(background);
		if(isMouseOver())
		{
			g.setColor(background.brighter());
		}
		
		g.fillRect(x,  y,  w,  h);
		
		g.setColor(outline);
		g.drawRect(x,  y,  w,  h);
		
		Text.alignCenter();
		Text.alignMiddle();
		Text.draw(label, x  + w * .5f, y + h * .5f);

	}
	
	
}
