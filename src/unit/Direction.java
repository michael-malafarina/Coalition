package unit;

import world.Cell;

public enum Direction 
{
	NORTH, SOUTH, EAST, WEST, NONE;
	
	public static Direction getDirection(Unit origin, Unit target)
	{
		return getDirection(origin.getCell(), target.getCell());
	}
	
	public static Direction getDirection(Cell origin, Cell target)
	{
		int xDiff =  Math.abs(origin.getX() - target.getX());
		int yDiff =  Math.abs(origin.getY() - target.getY());

		// x Dimension is more significant
		if(xDiff >= yDiff)
		{
			if(origin.getX() > target.getX())
			{
				return WEST;
			}
			else
			{
				return EAST;
			}
		}
		else
		{
			if(origin.getY() > target.getY())
			{
				return NORTH;
			}
			else if(origin.getY() < target.getY())
			{
				return SOUTH;
			}
			else
			{
				return NONE;
			}
		}
	}
}
