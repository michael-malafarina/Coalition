package modifier.conditions;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import core.Main;
import modifier.Modifier;
import ui.Images;
import unit.Unit;

public abstract class Condition extends Modifier
{
	// each effect may tick up or down differently
		// ex:  bleed applies damage at start of target's turn  (when it ticks)
	    //      slow only ticks at end of target's turn
	// instant effects apply at start of turn
	
	protected Modifier source;

	
	Condition()
	{
		super();
		icon = Images.iconBleed;
	}
	
	private boolean sameTurnCastingOver;
	protected int duration;
	protected int turnsLeft;
	
	public int getDuration()	{	return duration;		}	
	public int getTurnsLeft()	{	return turnsLeft;		}
	public boolean isExpired()	{	return turnsLeft == 0;	}
	
	public Modifier getSource()	{	return source;			}
	
	public void setSource(Modifier m)
	{
		source = m;
	}
	
	public void tick()		
	{	
		// Don't count down if it's self-cast 
		if(source != null && source.getOwner() != null && source.getOwner().isActive() && getDuration() == getTurnsLeft() && !sameTurnCastingOver)
		{
			sameTurnCastingOver = true;
			return;
		}
		turnsLeft--;		
	}
		
	public void setDuration(int turns)
	{
		duration = turns;
		turnsLeft = turns;
	}
	
	public void addDuration(int turns)
	{
		duration += turns;
		turnsLeft += turns;
	}
	
	public void setOwner(Unit u)
	{
		owner = u;
	}
	
	public void renderIcon(Graphics g, int x, int y)
	{
		int w = ICON_SIZE * Main.getGameScale();
		int h = ICON_SIZE * Main.getGameScale();
//		
//		x = x - w / 2;
//		y = y - h / 2;

		getIcon().setFilter(Image.FILTER_NEAREST);
		getIcon().draw(x, y, w, h);

		//g.setColor(new Color(0, 0, 0, 100));
		//g.fillRect(x, y - 4 * Main.getGameScale(), w,  6 * Main.getGameScale());
		
//		Text.setFont(Fonts.tinyText);
//		Text.alignCenter();
//		//Text.alignMiddle();
//		Text.shadowOn();
//		Text.shadowSize(1);
//		Text.draw(""+getTurnsLeft(), x + w / 4, y - 2 * Main.getGameScale());
//		Text.shadowOff();
	}
	


	
}
