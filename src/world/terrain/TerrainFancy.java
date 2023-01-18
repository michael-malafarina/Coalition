package world.terrain;

import core.Main;
import world.Cell;

public class TerrainFancy extends Terrain
{	
	TerrainFancy(Cell cell) 
	{
		super(cell);
	}

	public boolean northMatch()
	{
		return !cell.hasNorthCell() || cell.getNorthCell().getTerrain().isTerrainType(getClass());
	}
	
	public boolean eastMatch()
	{
		return(!cell.hasEastCell() || cell.getEastCell().getTerrain().isTerrainType(getClass()));
	}
	
	public boolean southMatch()
	{
		return !cell.hasSouthCell() || cell.getSouthCell().getTerrain().isTerrainType(getClass());
	}
	
	public boolean westMatch()
	{
		return !cell.hasWestCell() || cell.getWestCell().getTerrain().isTerrainType(getClass());
	}
	
	public void setTerrainImage(int y, int rotation)
	{
		image = tileset.getSprite(sheetColumn,  y);
		
		image.setCenterOfRotation(Main.getCellSize() / 2,  Main.getCellSize() / 2); 

		
//		image.setCenterOfRotation(getCell().getXPixel() + Main.getCellSize() * Main.getGameScale() / 2, 
//							     getCell().getXPixel() + Main.getCellSize() * Main.getGameScale() / 2); 
		image.rotate(rotation);
	}
	
	
	public void setTerrainImage(int y, boolean flipHorizontal, boolean flipVertical)
	{
		image = tileset.getSprite(sheetColumn,  y).getFlippedCopy(flipHorizontal, flipVertical);
		
//		image.setCenterOfRotation(Main.getCellSize() / 2,  Main.getCellSize() / 2); 

		
//		image.setCenterOfRotation(getCell().getXPixel() + Main.getCellSize() * Main.getGameScale() / 2, 
//							     getCell().getXPixel() + Main.getCellSize() * Main.getGameScale() / 2); 
//		image.rotate(rotation);
	}
	
	public void style()
	{
		// Match on four sides

		if(northMatch() && eastMatch() && southMatch() && westMatch())
		{
			super.style();
		}
	
		/*** Three match ***/

		// SOUTH OPEN - Same to my north, east, and west
		else if(northMatch() && eastMatch() && westMatch())
		{
			setTerrainImage(1, 90);
		}

		// WEST OPEN - Same to my north, east, and south
		else if(northMatch() && eastMatch() && southMatch())
		{
			setTerrainImage(1, 180);

		}
		
		
		// NORTH OPEN - Same to my east, south, and west
		else if(eastMatch() && southMatch() && westMatch())
		{
			setTerrainImage(1, 270);

		}

		// EAST OPEN - Same to my north, south, and west
		else if(northMatch() && southMatch() && westMatch())
		{
			setTerrainImage(1, 0);
		}

		/*** Two grass ***/

		// Corners
		
		// Grass to my north and east
		else if(northMatch() && eastMatch())
		{
			setTerrainImage(2, 180);
		}

		// Grass to my north and west
		else if(northMatch() && westMatch())
		{
			setTerrainImage(2, 90);

		}


		// Grass to my south and west
		else if(southMatch() && westMatch())
		{
			setTerrainImage(2, 0);

		}
		
		// Grass to my south and east
		else if(eastMatch() && southMatch())
		{
			setTerrainImage(2, 270);

		}
		
		// Opposites
		
		// Grass to my north AND south
		else if(northMatch() && southMatch())
		{
			setTerrainImage(3, 0);

		}

		// Grass to my east AND west
		else if(eastMatch() && westMatch())
		{
			setTerrainImage(1, 90);
		}
		

		/*** One grass  ***/

		// Grass to my north
		else if(northMatch())
		{
			setTerrainImage(4, 90);

		}
		
		// Grass to my east
		else if(eastMatch())
		{
			setTerrainImage(4, 180);
		}

		// Grass to my south
		else if(southMatch())
		{
			setTerrainImage(4, 270);

		}

		// Grass to my west
		else if(westMatch())
		{
			setTerrainImage(4, 0);

		}

		/*** No grass around ***/

		else
		{
			setTerrainImage(5, 0);
	
		}
	}
	
}
