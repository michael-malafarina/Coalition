package world.terrain;

import world.Cell;

public class Mud extends TerrainFancy
{
	public Mud(Cell cell) 
	{
		super(cell);
		sheetColumn = 2;
		moveCost = 2;
	}
}
