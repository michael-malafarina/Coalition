package world.terrain;

import world.Cell;

public class Water extends TerrainFancyCorner
{
	public Water(Cell cell) 
	{
		super(cell);
		sheetColumn = 3;
		canEnter = false;
	}
}
