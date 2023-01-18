package world;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import core.Main;
import core.Utility;
import world.obstacle.Obstacle;
import world.obstacle.Tree;
import world.terrain.Dirt;
import world.terrain.Grass;
import world.terrain.Mud;
import world.terrain.Terrain;
import world.terrain.Water;

public class CombatMapBuilder
{
	private int rows;
	private int cols;
	
	private char[][] styleMap;
	private char[][] blockMap;
	
	private Cell[][] cells;
	
	private Cell startLocation;
		
	public CombatMapBuilder()
	{

	}
			
	public String getRandomMapFile()
	{
		return "maps/3/"+Utility.random(4);
	}
	
	public void loadMap()
	{
		rows = 14;
		cols = 22;
		cells = new Cell[cols][rows];
		styleMap = new char[cols][rows];
		blockMap = new char[cols][rows];
		
		//startLocation = new Point(cols/4, rows/2);
		
		try
		{
			Scanner scan = new Scanner(new File(getRandomMapFile()));

			ArrayList<String> lines = new ArrayList<String>();
			
			for(int y = 0; y < rows * 2; y++)
			{
				lines.add(scan.nextLine());
			}
			
//			for(int y = 0; y < lines.size(); y++)
//			{
//				System.out.println(lines.get(y));
//			}

			
			for(int y = 0; y < rows; y++)
			{
				for(int x = 0; x < cols; x++)
				{
					styleMap[x][y] = lines.get(y).charAt(x);
				}
			}
			
			for(int y = rows; y < rows * 2; y++)
			{
				for(int x = 0; x < cols; x++)
				{
					blockMap[x][y-rows] = lines.get(y).charAt(x);
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found!");
		}

		
		for(int y = 0; y < rows; y++)
		{
			for(int x = 0; x < cols; x++)
			{
				cells[x][y] = new Cell(x, y);
				Cell c = cells[x][y];

				setTerrain(c, styleMap[x][y]);
				
				addObstacle(c, blockMap[x][y]);
				setStartLocation(c, blockMap[x][y]);


			}	
		}
		
		flipRandomly();
	}
	
	
	private void setStartLocation(Cell c, char type)
	{
		if(type == '0')
		{
			startLocation = c;
		}
	}
	
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
					cells[x][y].removeObstacle();
				}
			}
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
	
	public void flipRandomly()
	{
		if(Math.random() > .5)
		{
			flipHorizontal();
		}
		if(Math.random() > .5)
		{
			flipVertical();
		}
	}
	
	public void flipHorizontal()
	{
		Cell[][] newCells = new Cell[cols][rows];
		for(int y = 0; y < rows; y++)
		{
			for(int x = 0; x < cols; x++)
			{
				newCells[cols-x-1][y] = cells[x][y];
				cells[x][y].setPosition(cols-x-1, y);
			}
		}
		cells = newCells;
	}
	
	public void flipVertical()
	{
		Cell[][] newCells = new Cell[cols][rows];
		for(int y = 0; y < rows; y++)
		{
			for(int x = 0; x < cols; x++)
			{
				newCells[x][rows-y-1] = cells[x][y];
				cells[x][y].setPosition(x, rows-y-1);
			}
		}
		cells = newCells;
	}
	
	public int getRows()						{	return rows;			}
	public int getCols()						{	return cols;			}
	public int getWidth()						{	return getCols();		}
	public int getHeight()						{	return getRows();		}
	public int getWidthPixel()					{	return getWidth() * Main.getCellSize();		}
	public int getHeightPixel()					{	return getHeight() * Main.getCellSize();		}
	public Cell getStartLocation()				{	return startLocation ;	}

	
	

}
