package animation;

import core.Color;
import core.Main;
import unit.Unit;
import world.Cell;

public class AnimationCell extends Animation 
{	
	Cell cell;
	


	public AnimationCell(AnimatedSpriteSheet sheet, Cell cell, Color color) 
	{
		super(cell.getXPixel(), cell.getYPixel(), sheet.getDuration(), sheet.getFrames(), color, sheet.isLooping());
		this.frameRate = sheet.getFrameRate();
		this.cell = cell;
		this.sheet = sheet;
	}
		
	public AnimationCell(AnimatedSpriteSheet sheet, Unit unit, Color color) 
	{
		this(sheet, unit.getCell(), color);
	}
	
	public AnimationCell(AnimatedSpriteSheet sheet, Unit unit) 
	{
		this(sheet, unit.getCell(), Color.white);
	}
	
	
	public AnimationCell(AnimatedSpriteSheet sheet, Cell cell) 
	{
		this(sheet, cell, Color.white);
	}
	
	public void update()
	{
		super.update();
		
		if(image == null)
		{
			return;
		}
		
		int extraHeight = image.getHeight() * Main.getGameScale() - Main.getCellSize();
		
	//	System.out.println(image.getHeight() * Main.getGameScale() + " " +  Main.getCellSize());
		
		
		if(extraHeight > 0)
		{
			y = cell.getYPixel() - extraHeight;
		}
		
	//	System.out.println(cell.getYPixel() - extraHeight);

	}
		
}
