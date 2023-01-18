package world.terrain;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import core.Main;
import ui.Images;
import world.Cell;

/*
 * Terrain rules
 * 
 * 
 * Each tileset has a core terrain, accent terrain, and deep terrain.
 * Ex:  Forest is dirt (core), grass (accent), and water (deep)
 * Accent terrain cannot be next to deep terrain
 * 
 * 
 */

public class Terrain 
{
	protected Cell cell;
	protected Image image;
	protected SpriteSheet tileset;	
	protected int sheetColumn;
	protected boolean canEnter;
	protected int moveCost;

	
	Terrain(Cell cell)
	{
		this.cell = cell;
		tileset = Images.tilesetForest;
		sheetColumn = 0;
		canEnter = true;
		moveCost = 1;
	}
	
	public boolean canEnter()
	{
		return canEnter;
	}
	
	public int getMoveCost()
	{
		return moveCost;
	}
	
	public Cell getCell()
	{
		return cell;
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics g)
	{
		image.draw(getCell().getXPixel(), getCell().getYPixel(), Main.getCellSize(), Main.getCellSize());
	}
	
	public boolean isTerrainType(Class<? extends Terrain> clazz)
	{
		return clazz.isInstance(this);
	}
	
	
	public void style()
	{
		
		image = tileset.getSprite(sheetColumn+0, 0);

//		
//		double r = Math.random();
//
//		if(r < .80)
//		{
//		}
//		else if(r < .87)
//		{
//			image = tileset.getSprite(sheetColumn+0, 1);
//		}
//		else if(r < .94)
//		{
//			image = tileset.getSprite(sheetColumn+0, 2);
//		}
//		else if(r < 1)
//		{
//			image = tileset.getSprite(sheetColumn+0, 3);
//		}
		
//		isCenterTile = true;
	}
	
	
}
