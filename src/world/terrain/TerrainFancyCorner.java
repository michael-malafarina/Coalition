package world.terrain;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import core.Main;
import world.Cell;

public class TerrainFancyCorner extends TerrainFancy
{
	Image nw;
	Image ne;
	Image se;
	Image sw;

	TerrainFancyCorner(Cell cell) 
	{
		super(cell);
	}

	public boolean northEastMatch()
	{
		return cell.hasNorthEastTile() && cell.getNorthEastTile().getTerrain().isTerrainType(getClass());
	}
	
	public boolean northWestMatch()
	{
		return cell.hasNorthWestTile() && cell.getNorthWestTile().getTerrain().isTerrainType(getClass());
	}
	
	public boolean southWestMatch()
	{
		return cell.hasSouthWestTile() && cell.getSouthWestTile().getTerrain().isTerrainType(getClass());
	}
	
	public boolean southEastMatch()
	{
		return cell.hasSouthEastTile() && cell.getSouthEastTile().getTerrain().isTerrainType(getClass());
	}
	
	
	
	public void style()
	{
		super.style();
		
		if(cell.hasNorthWestTile() && !northWestMatch() && northMatch() && westMatch())
		{
			nw = tileset.getSprite(sheetColumn, 6);
			nw.setCenterOfRotation(Main.getCellSize() / 2,  Main.getCellSize() / 2); 
			nw.rotate(270);

		}
		
		if(cell.hasNorthEastTile() && !northEastMatch() && northMatch() && eastMatch() )
		{
			ne = tileset.getSprite(sheetColumn, 6);
			ne.setCenterOfRotation(Main.getCellSize() / 2,  Main.getCellSize() / 2); 
			ne.rotate(0);

		}
		
		if(cell.hasSouthEastTile() && !southEastMatch() && southMatch() && eastMatch() )
		{
			se = tileset.getSprite(sheetColumn, 6);
			se.setCenterOfRotation(Main.getCellSize() / 2,  Main.getCellSize() / 2); 
			se.rotate(90);
		}
		
		if(cell.hasSouthWestTile() && !southWestMatch() && southMatch() && westMatch() )
		{
			sw = tileset.getSprite(sheetColumn, 6);
			sw.setCenterOfRotation(Main.getCellSize() / 2,  Main.getCellSize() / 2); 
			sw.rotate(180);
		}
		
		
		
//		if((cell.getNorthEastTile() != null && !cell.getNorthEastTile().getTerrain().isTerrainType(getClass() && 
//				cell.getEastTile() != null && cell.getEast
//				
//				))) 
//		{
//			corner = tileset.getSprite(sheetColumn+5, 1);
//		}
//		
	}
	
	public void render(Graphics g)
	{
		super.render(g);
		
		if(nw != null)	{	nw.draw(cell.getXPixel(), cell.getYPixel(), Main.getCellSize(), Main.getCellSize());	}	
		if(ne != null)	{	ne.draw(cell.getXPixel(), cell.getYPixel(), Main.getCellSize(), Main.getCellSize());	}	
		if(se != null)	{	se.draw(cell.getXPixel(), cell.getYPixel(), Main.getCellSize(), Main.getCellSize());	}			
		if(sw != null)	{	sw.draw(cell.getXPixel(), cell.getYPixel(), Main.getCellSize(), Main.getCellSize());	}		
	}
	
}
