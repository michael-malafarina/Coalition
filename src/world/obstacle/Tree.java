package world.obstacle;

import ui.Images;
import world.Cell;

public class Tree extends Obstacle
{
	public Tree(Cell c)
	{
		super();
		height = 2;
		sheet = Images.treePine;	
		setCell(c);
	}

}
