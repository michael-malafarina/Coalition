package world;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import core.Main;
import core.Settings;
import core.Utility;
import states.combat.CombatManager;
import ui.Images;
import ui.MenuColor;
import ui.Mouse;
import unit.Unit;
import world.obstacle.Obstacle;
import world.terrain.Terrain;

public class Cell 
{
	
	protected Terrain terrain;
	protected Unit unit;
	protected Unit passingUnit;
	
	protected Obstacle obstacle;
	private int x;
	private int y;
	
	
	
	private boolean moveTarget;
	private boolean moveTargetActive;
	private boolean moveTargetOther;
	
	private boolean abilityTarget;
	
	// Pathfinding stuff
	
	private int weight;
	private boolean needToUpdateNeighbors;
	
	public Cell(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		weight = -1;
		needToUpdateNeighbors = false;
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/****************************** ACCESSORS *******************************/
	
	public int getX()					{	return x;				}
	public int getY()					{	return y;				}
	public int getXPixel()				{	return x * Main.getCellSize();	}
	public int getYPixel()				{	return y * Main.getCellSize();	}
	
	
	public boolean hasNoAdversary()		{	return !hasUnit() || !hasAdversary() ; }
	public boolean hasNoFriendly()		{	return !hasUnit() || !hasFriendly() ; }

	private boolean hasActiveUnit()		{	return CombatManager.hasActiveUnit(); }
	private Unit getActiveUnit()		{	return hasActiveUnit() ? CombatManager.getActiveUnit() : null; }
	public boolean hasSelf()			{	return hasUnit() && hasActiveUnit() && getUnit() == getActiveUnit();	}
	public boolean hasAdversary()		{	return hasUnit() && hasActiveUnit() && getUnit().isHostile(getActiveUnit());	}
	public boolean hasFriendly()		{	return hasUnit() && hasActiveUnit() && getUnit().isFriendly(getActiveUnit());	}

	public boolean canEnter()			{	return getTerrain().canEnter() && !hasObstacle() && (!hasUnit());}
	public boolean isMoveTarget()		{	return moveTarget;				}
	public boolean isAbilityTarget()	{	return abilityTarget;			}

	public boolean hasUnit()			{	return unit != null;			}
	public boolean hasOtherUnit(Unit u)	{	return hasUnit() && unit != u;	}

	public boolean hasObstacle()		{	return obstacle != null;		}

	public boolean hasWestCell()		{	return Map.inBounds(x-1, y);	}
	public boolean hasEastCell()		{	return Map.inBounds(x+1, y);	}
	public boolean hasSouthCell()		{	return Map.inBounds(x, y+1);	}
	public boolean hasNorthCell()		{	return Map.inBounds(x, y-1);	}
	
	public boolean hasNorthWestTile()	{	return Map.inBounds(x-1, y-1);	}
	public boolean hasNorthEastTile()	{	return Map.inBounds(x+1, y-1);	}
	public boolean hasSouthWestTile()	{	return Map.inBounds(x-1, y+1);	}
	public boolean hasSouthEastTile()	{	return Map.inBounds(x+1, y+1);	}
	
	public Cell getWestCell()			{	return hasWestCell() ? Map.getCell(x-1, y) : null;		}
	public Cell getEastCell()			{	return hasEastCell() ? Map.getCell(x+1, y) : null;		}
	public Cell getSouthCell()			{	return hasSouthCell() ? Map.getCell(x, y+1) : null;		}
	public Cell getNorthCell()			{	return hasNorthCell() ? Map.getCell(x, y-1) : null;		}
		
	public Cell getNorthWestTile()		{	return hasNorthWestTile() ? Map.getCell(x-1, y-1) : null;		}
	public Cell getNorthEastTile()		{	return hasNorthEastTile() ? Map.getCell(x+1, y-1) : null;		}
	public Cell getSouthWestTile()		{	return hasSouthWestTile() ? Map.getCell(x-1, y+1) : null;		}
	public Cell getSouthEastTile()		{	return hasSouthEastTile() ? Map.getCell(x+1, y+1) : null;		}
		
	public int getMoveCost()			{	return terrain.getMoveCost();	}
	public Terrain getTerrain()			{	return terrain;		}
	public Unit getUnit()				{	return unit;		}
	public Unit getPassingUnit()		{	return passingUnit;		}
	public Obstacle getObstacle()		{	return obstacle;		}
	public boolean isUnreachable()		{	return weight >= 998;		}
	
	public int getWeight()						{		return weight;					}
	public boolean needToUpdateNeighbors()		{		return needToUpdateNeighbors;	}

	/****************************** MUTATORS *******************************/
	
	public void update()
	{		
		terrain.update();
		


		
		if(hasUnit())
		{
			//System.out.println("[CELL] " + this + getUnit());
			
			unit.update();
		}
		
		if(hasObstacle())
		{
			obstacle.update();
		}	
	

	}
	
	public void renderTerrain(Graphics g)	
	{
		terrain.render(g);
		
		if(Settings.showGrid)
		{
//			g.setLineWidth(1);
//			g.setColor(new Color(50, 50, 50, 50));
			g.setLineWidth(2);
			g.setColor(new Color(50, 50, 50, 100));
			g.drawRect(getXPixel(), getYPixel(), Main.getCellSize(), Main.getCellSize());
		}
		
		if(Settings.debugMode)
		{
			g.setColor(new Color(255, 255, 255, 255));
//			g.drawString(getX() + " " + getY(), getXPixel()+12, getYPixel()+28);
			g.drawString(""+getWeight(), getXPixel()+2, getYPixel()+28);
		}
	}
	
	public void renderEntities(Graphics g)
	{
		if(hasUnit())
		{
			unit.render(g);
		}
		
		if(hasObstacle())
		{
			obstacle.render(g);
		}
	}
	
	public void renderEntitiesSecond(Graphics g)
	{
		if(hasUnit())
		{
			unit.renderSecond(g);
		}
		
		if(hasObstacle())
		{
			obstacle.renderSecond(g);
		}
	}
	
	public void renderEntitiesThird(Graphics g)
	{
		if(hasUnit())
		{
			unit.renderThird(g);
		}

	}
	
	public void renderOverlay(Graphics g)
	{		
		
		if(isMouseOver())
		{			
			g.setColor(new Color(255, 255, 255, 30));
			g.fillRect(getXPixel(), getYPixel(), Main.getCellSize(), Main.getCellSize());
		}
		
		
		Color overlayColor = null;

		
		if(isMoveTarget())
		{
			// Player can move
			if(moveTargetActive)
			{
				overlayColor = new Color(50, 105, 255, 100);
				Images.cellOverlay.draw(getXPixel(), getYPixel(), Main.getGameScale(), overlayColor);
			}
			
			// Shows other player --- second overlay style that stacks better //////
			if(moveTargetOther && !CombatManager.hasActiveAbility())
			{
				overlayColor = new Color(80, 80, 80, 100);				
				Images.cellOverlay.draw(getXPixel(), getYPixel(), Main.getGameScale(), overlayColor);
			}

		}
		
		if(isAbilityTarget())
		{
			// Can use
			if(CombatManager.getActiveAbility().canUse())
			{
				if(CombatManager.getActiveAbility().isSupport())
				{
					overlayColor = MenuColor.SUPPORT_ABILITY_LIGHT.getColor();
				}
				else
				{
					overlayColor = MenuColor.OFFENSIVE_ABILITY_LIGHT.getColor();
				}
				//overlayColor = new Color(255, 105, 50, 100);
			}
			
			// Cannot use but shows targets
			else
			{
				overlayColor = new Color(80, 80, 80, 100);
			}
			
			Images.cellOverlay.draw(getXPixel(), getYPixel(), Main.getGameScale(), overlayColor);


		}
		

	}
	
	
	public void setTerrain(Terrain t)
	{
		terrain = t;
	}
	
	public void enableMoveTarget(boolean isActive)
	{
		if(!hasUnit())
		{
			moveTarget = true;
			if(isActive)
			{
				moveTargetActive = true;
			}
			else
			{
				moveTargetOther = true;
			}
		}
		
	}
	
	public void disableMoveTarget()
	{
		moveTarget = false;
		moveTargetActive = false;
		moveTargetOther = false;
	}
	
	public void enableAbilityTarget()
	{
		abilityTarget = true;		
	}
		
	public void disableAbilityTarget()
	{
		abilityTarget = false;
	}
	
	
	
	public void style()
	{
		terrain.style();
	}
	
	
	public void removeUnit()
	{
		unit = null;
	}
	
	public void removePassingUnit()
	{
		passingUnit = null;
	}
	
	public void addUnit(Unit u)
	{
		if(!hasUnit())
		{
			unit = u;
		}
		else
		{
			passingUnit = u;
		}
	}
	
	public void removeObstacle()
	{
		obstacle = null;
	}
	
	public void addObstacle(Obstacle o)
	{
		obstacle = o;
	}

	public boolean isMouseOver()
	{
		int mX = Mouse.getX();
		int mY = Mouse.getY();

		// Check my square
		if(mX == getX() && mY == getY())
		{
			return true;
		}

		return false;

	}

	public boolean isClicked()
	{
		return Main.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && isMouseOver();
	}

//	public void onMouseOver()
//	{
//
//	}
//
//	public void onClick()
//	{
//
//	}


	/**********
	 *  PATHFINDING
	 * 
	 */
	

	

	
	public void updateNeighbors(boolean movement)
	{
		needToUpdateNeighbors = false;			// This needs to happen before calls to neighbors
		
		if(hasWestCell() )		{	getWestCell().setWeight(this, movement);		}
		if(hasEastCell())		{	getEastCell().setWeight(this, movement);		}
		if(hasNorthCell())		{	getNorthCell().setWeight(this, movement);		}
		if(hasSouthCell())		{	getSouthCell().setWeight(this, movement);		}
	
	}
	
//	public void updateNeighbors(boolean movement)
//	{
//		needToUpdateNeighbors = false;			// This needs to happen before calls to neighbors
//		
//		if(hasWestCell() && !getWestCell().isUnreachable())		{	getWestCell().setWeight(this, movement);		}
//		if(hasEastCell() && !getEastCell().isUnreachable())		{	getEastCell().setWeight(this, movement);		}
//		if(hasNorthCell() && !getNorthCell().isUnreachable())	{	getNorthCell().setWeight(this, movement);		}
//		if(hasSouthCell() && !getSouthCell().isUnreachable())	{	getSouthCell().setWeight(this, movement);		}
//	
//	}
	
	public void setOrigin(boolean movement)
	{
		weight = 0;
		updateNeighbors(movement);
	}
	
	
	
	public void setWeight(Cell parent, boolean movementMode)
	{
		int cost = 1;
		
		if(movementMode)
		{
			cost = getTerrain().getMoveCost();
		}
			
		if(weight == -1 || weight > parent.getWeight() + cost)
		{
			if(movementMode && hasAdversary())	
			{
				weight = 999;
			}
			else if(!getTerrain().canEnter() || hasObstacle() || parent.getWeight() == 999 || weight == 999)
			{
				weight = 999;
			}
			else
			{
//				System.out.println(weight + " " + parent.getWeight());
				weight = parent.getWeight() + cost;
				needToUpdateNeighbors = true;
			}		
		}

		if(needToUpdateNeighbors())
		{
			updateNeighbors(movementMode);
		}
	}
	
	public void resetWeight()
	{		
		weight = -1;	
	}
	
	public void lockUnreachable()
	{
		if(weight == -1)
		{
			weight = 999;
		}
	}
	
	public ArrayList<Cell> getNeighbors()
	{
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		if(hasWestCell()) neighbors.add(getWestCell());
		if(hasEastCell()) neighbors.add(getEastCell());
		if(hasNorthCell()) neighbors.add(getNorthCell());
		if(hasSouthCell()) neighbors.add(getSouthCell());
		return neighbors;
	}
	
	public ArrayList<Cell> getNeighborsShuffled()
	{
		ArrayList<Cell> neighbors = getNeighbors();
		ArrayList<Cell> shuffled = new ArrayList<Cell>();
		
		while(!neighbors.isEmpty())
		{
			int r = Utility.random(neighbors.size());
			shuffled.add(neighbors.remove(r));
		}
		
		return shuffled;
	}
	
	public Cell getLowestWeightNeighbor()
	{
		int lowVal = 999;
		Cell lowCell = null;
		
		for(Cell c : getNeighbors())
		{
			if(c != null)
			{
			//	System.out.println("its not null");

				
				if(c.getWeight() < lowVal)
				{
				//	System.out.println("weight is smol");

					
					if(c.getTerrain().canEnter())
					{
					//	System.out.println("terrain checks out");

						
						if(!c.hasObstacle())
						{
					//		System.out.println("no obstacle here");

							
					
							
//							
//							if(getActiveUnit() instanceof PlayerUnit && hasNoAdversary())
//							{
////					//			System.out.println("setting a new low cell");
////
//								lowVal = c.getWeight();
//								lowCell = c;
//							}
//							else
								
							if(hasNoAdversary())
							{
								lowVal = c.getWeight();
								lowCell = c;
							}
							
//							
//							if(getActiveUnit() instanceof ComputerUnit && hasNoFriendly())
//							{
//								lowVal = c.getWeight();
//								lowCell = c;
//							}
						}
					}
				}
			}
		}
		
		//System.out.println(lowCell);
		if(lowCell == null || lowCell.getWeight() >= getWeight())
		{
			return null;
		}
		else
		{	
			return lowCell;
		}
		
		
	}
	
	public Cell getRandomNeighbor()
	{
		return getNeighbors().get(Utility.random(0, getNeighbors().size() - 1));
	}
	
	public String toString()
	{
		return getX() + ", " + getY();
	}
	
//	public void removeBlockedLines()
//	{
//		if(hasActiveUnit() && CombatManager.hasActiveAbility())
//		{
//			if(Map.abilityLineBlockedByObstacle(getActiveUnit().getCell(), this))
//			{
//				disableAbilityTarget();
//			}
//		}
//	}
		
	

	
}
