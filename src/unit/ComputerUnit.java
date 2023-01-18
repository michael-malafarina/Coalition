package unit;

import java.util.ArrayList;
import java.util.HashMap;

import ability.Ability;
import ability.ActivatedAbility;
import core.Utility;
import states.combat.CombatManager;
import unit.manager.CoalitionManager;
import unit.manager.EmpireManager;
import world.Cell;
import world.Map;


public abstract class ComputerUnit extends Unit
{	
	private java.util.Map<Cell, Integer> cellSafety;
	private Cell safestCellInRangeOfNearestEnemy;
	
	
	/******************* DATA **********************/

	public EnemyType type;
	public static final int STEP_DELAY = 15;
	private int stepTimer;
	
	private boolean movementComplete;
	private boolean actionComplete;

	private Cell oldCell;
	
	/******************* CONSTRUCTORS **********************/

	public ComputerUnit()
	{
		super();
		type = EnemyType.STANDARD;

		cellSafety = new HashMap<Cell, Integer>();
		
		setComputer();
		setEnemy();

	}
	
	/******************* ABSTRACT **********************/

	
	public abstract void movement();
	public abstract void action();
	
	/******************* ACCESSORS **********************/

	
	public boolean isDone()						{	return movementComplete && actionComplete;		}
	
	public ArrayList<Unit> getEnemies()			{	return CoalitionManager.getUnits();	}
	public ArrayList<Unit> getAllies()			{	return EmpireManager.getUnits();	}
	private boolean isAdjacent(Unit target)		{	return target != null && target.isAlive() &&  isAdjacent(target.getCell());		}
	private boolean isAdjacent(Cell target)		{	return Utility.getDistance(getCell(),  target) == 1;							}
	
	public int getValue()						{	return type.getValue();					}
//	private boolean isAdjacentToEnemy()		
//	{	
//		if(hasUnitWest() && getUnitWest().isHostile(this)) return true;
//		else if(hasUnitEast() && getUnitEast().isHostile(this)) return true;
//		else if(hasUnitNorth() && getUnitNorth().isHostile(this)) return true;
//		else if(hasUnitSouth() && getUnitSouth().isHostile(this)) return true;
//		else return false;
//		already wrote this duh
//	}
	
	public void calculateMoveWeights()	{		Map.calculateDistances(this, true);	}

	public Unit findNearestEnemy()
	{

		calculateMoveWeights();

		Unit nearestEnemy = null;
		int lowestWeight = Integer.MAX_VALUE;

		for(Unit u : getEnemies())
		{
			if(u.isAlive() && u.getCell().getWeight() < lowestWeight)
			{
				nearestEnemy = u;
				lowestWeight = u.getCell().getWeight();
			}
		}

		return nearestEnemy;

	}
	
	public void calculateSafety()
	{
		cellSafety.clear();
		
		for(Unit u : getEnemies())
		{
			Map.calculateDistances(u, true);
			
	
			for(Cell c : Map.getCells())
			{
				if(cellSafety.containsKey(c))
				{
					cellSafety.replace(c, cellSafety.get(c) + c.getWeight());
				}
				else
				{
					cellSafety.put(c, c.getWeight());
				}
			}
		}
	}
	
	
	public Cell getSafestCellInRangeOfNearestEnemy()
	{
		return safestCellInRangeOfNearestEnemy;
	}
	
	public void calculateSafestCellInRangeOfNearestEnemy(Ability a)
	{
		Unit nearestEnemy = findNearestEnemy();
		Map.clearMoveTargets();
		Map.applyMoveTargets(this);
		
		ArrayList<Cell> cells = getMoveCells();
		Cell safestCell = cell;
		int safestRating = 0;
		
//		System.out.println(this + " calculateSafestCellInRangeOfNearestEnemy ");
//		System.out.println(this + " cells " + cells);

		// Only checking cells that are valid move targets
		for(Cell c : cells)
		{
			
			// pretend I am in the destination cell and calculate distances for my ability
			Cell tempCell = cell;
			cell = c;
			
			Map.calculateDistances(this, false);
		//	System.out.println(this + " nearest enemy's weight " + nearestEnemy.getCell().getWeight());

			cell = tempCell;

			// Can still target enemy with ability from here, may be a candidate
			if(nearestEnemy.getCell().getWeight() <= a.getRange())
			{
			//	System.out.println(this + " enemy is in range of " + c);

		
				// See if it beats the record
				
//				System.out.print(this + " is safety rating " + cellSafety.get(c));
//				System.out.println("vs safestRating " + safestRating);

				// problem is here somehow, it's not beating the record?  but it is?

				if(cellSafety.get(c) >= safestRating)
				{

					safestRating = cellSafety.get(c);
					safestCell = c;
					
//					System.out.println(this + " new safety rating " + safestRating);
//					System.out.println(this + " new safest cell " + safestCell);

				}
			}
				
				
		}
		
		// may need to add contingency if nothing is in range to approach
		
		// Go back to movement calculations
		calculateMoveWeights();
		
		// No valid location
		if(safestRating == 0)
		{
			safestCellInRangeOfNearestEnemy = null;
		}
		else
		{
			safestCellInRangeOfNearestEnemy = safestCell;
		}
		
		
//		System.out.println(this + " my starting cell " + cell);
//		System.out.println(this + " safest cell " + safestCell);
 
	}
	
	
	
	public ArrayList<Cell> getMoveCells()
	{
		ArrayList<Cell> moveCells = new ArrayList<Cell>();
		
		for(Cell c : Map.getCells())
		{
			if(c.isMoveTarget())
			{
				moveCells.add(c);
			}
		}
		
		return moveCells;
	}
	
	
//	public Cell getCellClosestTo(Cell target)
//	{
//		ArrayList<Cell> allCells = Map.getCells();
//		ArrayList<Cell> moveCells = getMoveCells();
//		
//		//System.out.println(this + "has this many move cell options" + getMoveCells().size());
//		
//		Cell nearest = null;
//		int lowestDistance = Integer.MAX_VALUE;
//		
//		for(Cell c : moveCells)
//		{
//			int dist = Map.getDistance(c, target, true);
////			int dist = Utility.getDistance(c, target);
//			if(dist < lowestDistance)
//			{
//				nearest = c;
//				lowestDistance = dist; 
//			}
//		}
//		
//		//System.out.println(this + " has a nearest cell of..." + nearest);
//
//		
//		return nearest;
//	}
	
	public Cell getCellClosestTo(Cell target)
	{
		ArrayList<Cell> moveCells = getMoveCells();
		
		//System.out.println(this + "has this many move cell options" + getMoveCells().size());
		
		Cell nearest = null;
		int lowestDistance = Integer.MAX_VALUE;
		
		for(Cell c : moveCells)
		{
			int dist = Utility.getDistance(c, target);
			if(dist < lowestDistance)
			{
				nearest = c;
				lowestDistance = dist; 
			}
		}
		
		//System.out.println(this + " has a nearest cell of..." + nearest);

		
		return nearest;
	}
	

	
	public void moveToCell(Cell c)
	{	
		Cell nextCell = getNextStepToward(c);

		
		// This means we're there
		if(nextCell != null && nextCell == c)
		{
			//System.out.println("I reached my destination");

			setCell(c);
			stepTimer = STEP_DELAY;

			endMove();
			return;
		}
		else if(nextCell != null)
		{
			//System.out.println(this + "Moving to next cell" +  curMove);
			cell = nextCell;
			stepTimer = STEP_DELAY;

			curMove -= nextCell.getMoveCost();
		}
		else
		{
		//	System.out.println("Next cell is null");
			stepTimer = STEP_DELAY;

			
			endMove();
			return;
		}
		
	}
		

	
	
	private Cell getNextStepToward(Cell target)
	{		
		// PROBLEM IS IN THIS METHOD.  JUMPING TO NEAREST UNIT.
		
		
		Cell current = target;
		calculateMoveWeights();
		
		//System.out.println(this + " starts out at  " + current);
		
		while(current != null)
		{
			//System.out.println(this + " is looking at this cell  " + current);
			
			Cell next = current.getLowestWeightNeighbor();
		
			if(Utility.getDistance(getCell(), current) == 1)
			{	
				return current;
			}
			else if(next == null)
			{
				return current;
			}
			else
			{
				current = next;
			}
		}

		return null;
	}

	/******************* MUTATORS **********************/
	
	public void update()
	{
	//	this.setDebugMessage(this.getClass().getSimpleName());
		
		super.update();

		if(stepTimer > 0)
		{
			stepTimer--;	
		}

		if(isActive())
		{
			
			//System.out.println("AI Turn " + this + " move: " + curMove);
	
			if(!movementComplete)
			{
				movementPhase();

			}
			else if(!actionComplete)
			{
				actionPhase();
			}
		}

	}
	
	public void movementPhase()
	{
		this.setDebugMessage(curMove + " " + movementComplete);
		
		
		if(stepTimer > 0)
		{
			return;
		}
		
		if(curMove <= 0)
		{
			endMove();
		}

		if(curMove == maxMove)
		{
			Map.clearMoveTargets();
			Map.applyMoveTargets(this);
		}
		movement();
		
			
		
//		Map.clearMoveTargets();
//		Map.applyMoveTargets(this);

	}
	

		

	public void actionPhase()
	{
		Map.clearMoveTargets();
	//	System.out.println("acting");
		
		action();
		endAction();
	}

	public void startTurn()		// called by Combat Manager
	{
		super.startTurn();
		
		
		oldCell = getCell();
		
		stepTimer = STEP_DELAY;
		calculateSafety();
		calculateSafestCellInRangeOfNearestEnemy(getBasicAttack());

		Map.clearMoveTargets();
		Map.applyMoveTargets(this);

	}


	public void endTurn()		// called by Combat Manager
	{
		super.endTurn();
		movementComplete = false;
		actionComplete = false;
	}

	
	// Movement Methods
	

	
	public void moveTowardNearestEnemy()
	{
		
		Cell nearest = findNearestEnemy().getCell();
		
	//	System.out.println("ADJ: " + isAdjacent(nearest) + " | my cell is safe" + !getCell().hasOtherUnit(this));
		if(hasAdjacentEnemy() && !getCell().hasOtherUnit(this))
		{
			endMove();
		}
		else
		{
			//System.out.println(this + "movin to " + nearest);

			moveToCell(getCellClosestTo(nearest));
		}
	}
	
	public void kite()
	{
		if(getSafestCellInRangeOfNearestEnemy() != null)
		{
			moveToCell(getSafestCellInRangeOfNearestEnemy());
		}
		else
		{
			moveTowardNearestEnemy();
		}
	}
	
	public void endMove()
	{
		movementComplete = true;
		curMove = 0;
		stepTimer = STEP_DELAY;
		setCell(cell);
		if(oldCell != getCell())
		{
			oldCell.removeUnit();

		}
	}
	
	public void endAction()
	{
		actionComplete = true;
		curActions = 0;
	}
	
	// Action Methods

	public boolean hasAdjacentEnemy()
	{
		return !getAdjacentEnemies().isEmpty();
	}
	
	public ArrayList<Unit> getAdjacentEnemies()
	{
		ArrayList<Unit> adjacentEnemies = new ArrayList<Unit>();

		for(Unit u : getEnemies())
		{
			if(isAdjacent(u))
			{
				adjacentEnemies.add(u);
			}
		}
		
		return adjacentEnemies;
	}
	
	public boolean hasEnemyInRange(Ability a)
	{
		for(Unit u : getEnemies())
		{
			if(u.isAlive() && a.inRange(u))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean hasEnemyInRangeOfCell(Cell c, Ability a)
	{
		for(Unit u : getEnemies())
		{
			if(u.isAlive() && Utility.getDistance(c, u.getCell()) < a.getRange())
			{
				return true;
			}
		}
		return false;
	}
	
	public void basicAttackEnemyInRange()
	{
		ActivatedAbility a = getBasicAttack();
		
		Unit lowestUnit = null;
		int lowestDefense = Integer.MAX_VALUE;
		
		for(Unit u : getEnemies())
		{
			if(u.isAlive() && a.inRange(u) && u.getDefense() < lowestDefense)
			{
				lowestUnit = u;
				lowestDefense = u.getDefense();
			}
		}
		
		useAbility(a, lowestUnit);
	}
	
//	public void basicAttackAdjacentEnemy()			// deprecated?
//	{
//		Unit lowestUnit = null;
//		int lowestDefense = Integer.MAX_VALUE;
//		
//		for(Unit u : getAdjacentEnemies())
//		{
//			if(u.getCurHealth() + u.getArmor() < lowestDefense)
//			{
//				lowestUnit = u;
//				lowestDefense = u.getCurHealth() + u.getArmor();
//			}
//		}
//		
//		useAbility(1, lowestUnit);
//	
//	}
	
	
	public void useAbility(ActivatedAbility a)
	{
		useAbility(a, this.getCell());
	}
	
	public void useAbility(Class <? extends Ability> clazz)
	{
		useAbility(clazz, this.getCell());
	}
	
	public void useAbility(Class <? extends Ability> clazz, Unit target)
	{
		useAbility(clazz, target.getCell());
	}
	
	public void useAbility(Class <? extends Ability> clazz, Cell target)
	{
		useAbility((ActivatedAbility) getAbility(clazz), target);
	}
	
	public void useAbility(ActivatedAbility a, Unit target)
	{
		useAbility(a, target.getCell());
	}
	
	public void useAbility(ActivatedAbility a, Cell target)
	{
		ArrayList<Cell> targets = new ArrayList<Cell>();
		targets.add(target);
		useAbility(a, targets);
	}
	
	public void useAbility(Class <? extends Ability> clazz, ArrayList<Cell> targets)
	{
		useAbility((ActivatedAbility) getAbility(clazz), targets);
	}
	
	public void useAbility(ActivatedAbility a, ArrayList<Cell> targets)
	{
	
		//System.out.println("using " + a + " on " + target);
		
		// Animation and Sound
 		if(targets != null)
		{
			attackAnimation(targets.get(0), a);
		}
		a.sound();
		CombatManager.abilityPause();

		// Delayed abilities
		if(a.getAnimationDelay() == 0)
		{
			CombatManager.setTargetCells(targets);
			CombatManager.useAbility(a);
		}
		else
		{
			CombatManager.setTargetCells(targets);
			CombatManager.addDelayedAbility(a);
		}

		// End
		endAction();
	//	reset();

	}

}
