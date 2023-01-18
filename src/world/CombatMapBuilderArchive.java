package world;

import java.util.ArrayList;

import org.newdawn.slick.geom.Point;

import core.Main;
import core.Utility;
import world.noise.PerlinNoise;
import world.obstacle.Obstacle;
import world.obstacle.Tree;
import world.terrain.Dirt;
import world.terrain.Grass;
import world.terrain.Mud;
import world.terrain.Terrain;
import world.terrain.Water;

public class CombatMapBuilderArchive
{
	private int rows;
	private int cols;
	
	private char[][] styleMap;
	private char[][] blockMap;
	
	private Cell[][] cells;

	
	private Point startLocation;
	
//	public static int ROWS_BASE = 8;
//	public static int COLS_BASE = 14;
//	public static int ROWS_PER_PARTY_MEMBER = 2;
//	public static int COLS_PER_PARTY_MEMBER = 3;
//	
	// Map is built out of 8 x 8 sectors
	
	public CombatMapBuilderArchive()
	{
//		sectorRows = 2;
//		sectorCols = 4;
//		sectors = new Sector[sectorCols][sectorRows];



	}
			
	public String getRandomMapFile()
	{
		return "maps/3/"+Utility.random(2);
	}
	

	
	
		
	public void loadMap()
	{
		rows = 14;
		cols = 22;
//		rows = ROWS_BASE + ROWS_PER_PARTY_MEMBER * CoalitionManager.getSize();
//		cols = COLS_BASE + COLS_PER_PARTY_MEMBER * CoalitionManager.getSize();
		cells = new Cell[cols][rows];
		styleMap = new char[cols][rows];
		blockMap = new char[cols][rows];
		
		startLocation = new Point(cols/4, rows/2);

		PerlinNoise.resetSeed(); // make the next random map from the given seed?  test this works
				
		for(int y = 0; y < rows; y++)
		{
			for(int x = 0; x < cols; x++)
			{
				cells[x][y] = new Cell(x, y);
				Cell c = cells[x][y];
				
				float SCALE = .1f;
				float noise = (float) PerlinNoise.noise(x * SCALE, y * SCALE);
				
				generateTerrain(c, noise);
	
//				float SCALE = .1f;
//				float noise = (float) PerlinNoise.noise(x * SCALE, y * SCALE);
				
	
				generateTreesAndWater(c, noise);
				generateScatteredTrees(c);

			}	
		}
		
		clearObstaclesInRegion((int) startLocation.getX() - 2, (int) startLocation.getY() - 2, 
							  (int) startLocation.getX() + 2, (int) startLocation.getY() + 2);		// clear for spawn locations
	}
	

	
//	public Sector getRandomSector(Cell[][] cells, int xPos, int yPos)
//	{
//		int r = Utility.random(4);
//		
//		switch(r)
//		{
//		case 0: return new HeavyForest(cells, xPos, yPos);
//		case 1: return new LightForest(cells, xPos, yPos);
//		case 2: return new Grassland(cells, xPos, yPos);
//		case 3: return new Muddy(cells, xPos, yPos);
//		default:	return null;
//		}
//	}
//	
	
	private void setTerrain(Terrain t)
	{
		t.getCell().setTerrain(t);
	}
	
	private void setTerrain(Cell c, char type)
	{
		if(type == 'a')
		{
			setTerrain(new Grass(c));
		}
		else if(type == 'b')
		{
			setTerrain(new Dirt(c));
		}

	}
	
	private void addObstacle(Obstacle o)
	{
		o.getCell().addObstacle(o);
	}
	
	
	
	private void addObstacle(Cell c, char type)
	{
		if(type == '1')
		{
			setTerrain(new Water(c));
		}
		else if(type == '2')
		{
			addObstacle(new Tree(c));
		}
		else if(type == ';')
		{
			setTerrain(new Mud(c));
		}
	}
	
	public void clearObstaclesInRegion(int left, int top, int right, int bottom)
	{
		for(int y = 0; y < rows; y++)
		{
			for(int x = 0; x < cols; x++)
			{

				if(x >= left && x <= right && y >= top && y <= bottom)
				{
					///System.out.println(x + " " + y);
					cells[x][y].removeObstacle();
				}
			}
		}
	}
	
	public void generateTerrain(Cell c, float noise)
	{		
		if(noise < .5)					
		{
			if(noise < .4 && Math.random() > .3)
			{
				c.setTerrain(new Mud(c));
			}
			else
			{
				c.setTerrain(new Dirt(c));
			}
		}			
		else									
		{
			c.setTerrain(new Grass(c));
		}
	}
	
	public void generateTreesAndWater(Cell c, float noise)
	{
		
		if(noise < .30)					
		{
			if(Math.random() < .95)
			{
				c.setTerrain(new Water(c));
			}
		}			
		else if(noise > .62)								
		{
			if(Math.random() < .80)
			{
				c.addObstacle(new Tree(c));
			}
		}
		else if(noise > .54 && noise < .58)								
		{
			if(Math.random() < .4)
			{
				c.addObstacle(new Tree(c));
			}
		}
	}
	
	public void generateScatteredTrees(Cell c)
	{
		if(c.getTerrain() instanceof Water)
		{
			return;
		}
		if(Math.random() < .01)
		{
			c.addObstacle(new Tree(c));
		}
	}
	
	public ArrayList<Cell> getCells()	
	{
		ArrayList<Cell> listCells = new ArrayList<Cell>();
		for(int y = 0; y < rows; y++)
		{
			for(int x = 0; x < cols; x++)
			{
				listCells.add(cells[x][y]);
			}
		}
		return listCells;
	}
	public int getRows()						{	return rows;			}
	public int getCols()						{	return cols;			}
	public int getWidth()						{	return getCols();		}
	public int getHeight()						{	return getRows();		}
	public int getWidthPixel()					{	return getWidth() * Main.getCellSize();		}
	public int getHeightPixel()					{	return getHeight() * Main.getCellSize();		}
	public Cell getStartLocation()				{	return cells[(int)startLocation.getX()][(int)startLocation.getY()] ;	}

	
	

}
