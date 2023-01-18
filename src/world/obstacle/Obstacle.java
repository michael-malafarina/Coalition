package world.obstacle;

import unit.Entity;
import world.Cell;

public class Obstacle extends Entity
{
	public Obstacle()
	{
		super();
		frames = 0;
	}
	
	public void setCell(Cell c)
	{
		if(getCell() != null)
		{
			getCell().removeObstacle();
		}
		
		super.setCell(c);
		
		getCell().addObstacle(this);
	}
	
}
