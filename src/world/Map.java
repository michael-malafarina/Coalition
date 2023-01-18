package world;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;

import ability.ActivatedAbility;
import animation.AnimationCursor;
import core.Color;
import core.Main;
import core.Utility;
import states.combat.CombatManager;
import states.combat.ControlState;
import ui.Camera;
import ui.MenuColor;
import ui.Mouse;
import unit.Unit;

public class Map 
{

	private static CombatMapBuilderArchive generator;

	private static int LINE_OBSTACLE_ROUNDING = 8;
	private static int LINE_OBSTACLE_UNDERSIZE = 4;

	private static int rows;
	private static int cols;

	private static ArrayList<Cell> cells;
	
	private static Color background;
	private int timer;
	private static AnimationCursor cursorTarget;
	
	private static Cell startLocation;	

	
	private static PathingGrid grid;
	
	public static void init()
	{
		generator = new CombatMapBuilderArchive();

		loadMap();
		
	}

	public static void loadMap()
	{
		background = new Color(20, 20, 60);

		generator.loadMap();
		//generator.loadMapRandom();

		rows = generator.getRows();
		cols = generator.getCols();
		cells = generator.getCells();
		startLocation = generator.getStartLocation();
		//System.out.println(cells);

		for(Cell c : getCells())
		{		
			c.style();
		}

		//	System.out.println("all cells styled");

	}

	public static int getRows()							{	return rows;			}
	public static int getCols()							{	return cols;			}
	public static int getWidth()						{	return getCols();		}
	public static int getHeight()						{	return getRows();		}
	public static int getWidthPixel()					{	return getWidth() * Main.getCellSize();		}
	public static int getHeightPixel()					{	return getHeight() * Main.getCellSize();		}

	public static Cell getStartLocation()				{	return startLocation;	}
	
	public static Cell getCell(int x, int y)			{	return cells.get(y * getCols() + x);	}

	public static Cell getRandomCell()
	{
		return cells.get(Utility.random(cells.size()));
	}
	
	public static Cell getRandomOpenCell()
	{
		Cell c;
		do 
		{
			c = getRandomCell();
		} while(!c.canEnter());
		return c;
	}
	
	public static ArrayList<Cell> getCells()
	{
		return cells;
	}

	public static boolean inBounds(int x, int y)	
	{			
		return x >= 0 && y >= 0 && x < getCols() && y < getRows();
	}

	public static void update()
	{
		for(Cell c : getCells())
		{
			c.update();
		}

		if(cursorTarget != null)
		{
			cursorTarget.update();
			
		}


	}

	public static void render(Graphics g)
	{
		g.setColor(background);
		g.fillRect(-Main.getCellSize() * Camera.EDGE, -Main.getCellSize() * Camera.EDGE, 
				getWidthPixel() + Main.getCellSize() * Camera.EDGE * 2, 
				getHeightPixel() + Main.getCellSize() * Camera.EDGE * 2);

		for(Cell c : getCells())
		{
			c.renderTerrain(g);
		}

		for(Cell c : getCells())
		{
			c.renderOverlay(g);
		}

		renderPlannedAbility(g);
		
		for(Cell c : getCells())
		{
			c.renderEntities(g);
		}

		for(Cell c : getCells())
		{
			c.renderEntitiesSecond(g);
		}
		
		for(Cell c : getCells())
		{
			c.renderEntitiesThird(g);
		}

		renderPlannedPath();

		grid.render();
		
	}


	public static void clearMoveTargets()
	{
		for(Cell c : getCells())
		{
			c.disableMoveTarget();
		}
	}

	public static void applyMoveTargets(Unit unit)
	{
		if(unit == null)
		{
			return;
		}
		
		grid = new PathingGrid(getCells(), unit.getCell());
		grid.calculateDistances();
		
		for(Cell c : getCells())
		{
			if(grid.canReachCell(c, unit.getCurMove()))
			{
				c.enableMoveTarget(unit.isActive());
			}
		}

	}

	public static void clearAbilityTargets()
	{
		for(Cell c : getCells())
		{
			c.disableAbilityTarget();
		}
	}

	public static void applyAbilityTargets(Unit unit)
	{
//		clearAbilityTargets();
//		calculateDistances(unit, false);
//
//		ArrayList<Cell> abilityCells = new ArrayList<Cell>();
//
//		ActivatedAbility a = CombatManager.getActiveAbility();
//
//		for(Cell c : getCells())
//		{
//
//
//
//			//int d = Utility.getDistance(c.getX(), c.getY(), unit.getX(), unit.getY());  save this for obstacles you can fire over later
//
//			//	System.out.println(a + " " + a.canTargetSelf());
//
//			//		if(c.containsSelf() && a.canTargetSelf())		
//
//			if((c.getWeight() <= a.getRange() && c.getWeight() > 0) || 
//					(c.hasSelf() && a.canTargetSelf()))		
//			{	
//				//System.out.println("hi");
//				c.enableAbilityTarget();
//				abilityCells.add(c);
//			}
//		}
//
//		for(Cell c : abilityCells)
//		{
//
//
//			if(abilityLineBlockedByObstacle(a.getOwner().getCell(), c))
//			{
//				c.disableAbilityTarget();
//
//			}
//		}
	}

	

	private static void renderPlannedPath()
	{
//		if(CombatManager.getControlState() != ControlState.MOVEMENT)
//		{
//			return;
//		}
//
//		Cell current = Mouse.getCell();
//
//		while(current != null && current.getWeight() != 0)
//		{
//			if(current.getWeight() <= CombatManager.getActiveUnit().getCurMove())
//			{
//
//				Images.cellDot.draw(current.getXPixel(), current.getYPixel(), Main.getGameScale());
//			}
//			else
//			{
//				Images.cellDot.draw(current.getXPixel(), current.getYPixel(), Main.getGameScale(), new Color(150, 150, 150, 150));
//			}
//			current = current.getLowestWeightNeighbor();
//			//System.out.println(current.getWeight());
//
//		}
	}

	public static void renderPlannedAbility(Graphics g)
	{
		if(CombatManager.getControlState() != ControlState.ABILITY || 
				!Mouse.inBounds() || 
			
				CombatManager.getActiveUnit() == null)
		{
			return;
		}
		
		Unit u = CombatManager.getActiveUnit();
		ActivatedAbility a = CombatManager.getActiveAbility();
		Color c;
		
		if(!a.canUse() || !Mouse.getCell().isAbilityTarget())
		{
			c = new Color(150, 150, 150);
		}
		else if(a.isSupport())
		{
			c = MenuColor.SUPPORT_ABILITY.getColor();
		}
		else
		{
			c = MenuColor.OFFENSIVE_ABILITY.getColor();
		}
		
		
//		g.setColor(Color.black);
//		g.setLineWidth(5);
//		g.drawLine(u.getXPixelCenter(),u.getYPixelCenter(), Mouse.getXPixelGridCentered(), Mouse.getYPixelGridCentered());
//		g.setColor(c);
//		g.setLineWidth(3);
//		g.drawLine(u.getXPixelCenter(), u.getYPixelCenter(), Mouse.getXPixelGridCentered(), Mouse.getYPixelGridCentered());
//		
	
		
		int x = Mouse.getXPixelGrid();
		int y = Mouse.getYPixelGrid();
			
		
		if(cursorTarget != null && cursorTarget.getImage() == a.getTargetImage())
		{
			cursorTarget.update(x, y, c);
			cursorTarget.offsetPosition(a.getCursorOffset().getX(), a.getCursorOffset().getY());
		}
		else
		{
			cursorTarget = new AnimationCursor(a.getTargetImage(), x, y, c);
//			System.out.println("offset in map for cursor " + a.getCursorOffset().getX());
			cursorTarget.offsetPosition(a.getCursorOffset().getX(), a.getCursorOffset().getY());
		}
		
		cursorTarget.render(g);	

		
		//
		//


	}


	public static boolean abilityLineBlockedByObstacle(Cell origin, Cell target)
	{

		Shape line = new Line(origin.getXPixel() + Main.getCellSize()/2, origin.getYPixel() + Main.getCellSize()/2, 
				target.getXPixel() + Main.getCellSize()/2, target.getYPixel() + Main.getCellSize()/2);

		for(Cell c : cells)
		{						
			Shape cellHitbox = new RoundedRectangle(c.getXPixel()+LINE_OBSTACLE_UNDERSIZE, c.getYPixel()+LINE_OBSTACLE_UNDERSIZE, 
					Main.getCellSize()-LINE_OBSTACLE_UNDERSIZE*2, Main.getCellSize()-LINE_OBSTACLE_UNDERSIZE*2, LINE_OBSTACLE_ROUNDING);

			//			if(c.getX() == 5 && c.getY() == 5)
			//			{
			//				System.out.println("---");
			//				System.out.println(c.hasObstacle());
			//				System.out.println(line.intersects(cellHitbox));
			//				System.out.println(target.isAbilityTarget());
			//			}

			if(line.intersects(cellHitbox) && c.hasObstacle() && target.isAbilityTarget())
			{
				return true;
			}

		}


		return false;
	}
}
