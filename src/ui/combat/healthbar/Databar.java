package ui.combat.healthbar;

import org.newdawn.slick.Graphics;

import core.Color;
import core.Main;
import unit.Unit;

abstract public class Databar 
{
	private int timer;
	protected Unit unit;
	protected int current;
	protected int max;
	protected float x;
	protected float y;
	protected Color color;
	protected float spacing;
	protected float w;
	protected float h;
	protected int index;
	protected boolean renderEmpty;
	protected int lossAmount;
	protected int gainAmount;

	Databar(Unit unit)
	{
		this.unit = unit;
	}

	public int getCurrent()				{	return current;		}	
	public int getMax()					{	return max;			}	
	public float getPercent()			{	return (float) current / (float) max;	}

	public void setCurrent(int current)	{	this.current = current;	}
	public void setMax(int max)			{	this.max = max;	}

	public void update()
	{
		timer++;
	}

	public void render(Graphics g)
	{

		for(int i = 0; i < max; i++)
		{
			if(renderEmpty || i < current)
			{
				g.setColor(new Color(80, 80, 80));
				g.fillRect(x + i * spacing, y, w, h);
			}
		}

		for(int i = 0; i < current; i++)
		{
			g.setColor(color);
			g.fillRect(x + i * spacing, y, w, h);
		}

		if(unit.showingEstimatedDamage())
		{
			// Healing
			for(int i = current; i < current + gainAmount && i < max; i++)
			{
				if(timer % 40 > 20)
				{
					g.setColor(new Color(0, 215, 0));
				}
				else
				{
					g.setColor(new Color(0, 255, 0));

				}
				g.fillRect(x + i * spacing, y, w, h);
				renderOutline(g, i);
			}
			
			// Damage
			for(int i = current - 1; i > current - 1 - lossAmount  && i >= 0; i--)
			{
				if(timer % 40 > 20)
				{
					g.setColor(new Color(215, 215, 140));
				}
				else
				{
					g.setColor(new Color(255, 255, 180));

				}
				g.fillRect(x + i * spacing, y, w, h);

			}
		}

		for(int i = 0; i < max; i++)
		{
			if(renderEmpty ||  i < current)
			{
				renderOutline(g, i);
			}
		}
	}
	
	public void renderOutline(Graphics g, int i)
	{
		g.setColor(Color.black);
		g.setLineWidth(1 * Main.getGameScale());
		g.drawRect(x + i * spacing, y, w, h);
	}


}
