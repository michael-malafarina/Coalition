package ability;

import java.util.ArrayList;

import animation.AnimatedSpriteSheet;
import core.Utility;
import ui.Images;
import world.Cell;
import world.Map;

public enum EffectShape 
{
	BURST;
	
	ArrayList<Cell> getTargets(Cell origin, int size)
	{
		if(this == BURST)
		{
			return getTargetsBurst(origin, size);
		}
		else
		{
			return null;
		}
	}

	public ArrayList<Cell> getTargetsBurst(Cell origin, int size)
	{
		ArrayList<Cell> targets = new ArrayList<Cell>();
		
		for(Cell c : Map.getCells())
		{
			if(Utility.getDistance(c, origin) <= size)
			{
				targets.add(c);
			}
		}
		
		return targets;

	}
	
	public AnimatedSpriteSheet getTargetImage(int size)
	{
		if(this == BURST)
		{
			return Images.cursorBurst[size];
		}
		else
		{
			return null;
		}
	}
	
	
}
