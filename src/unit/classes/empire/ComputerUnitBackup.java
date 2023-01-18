package unit.classes.empire;

import java.util.ArrayList;

import ability.Ability;
import ability.ActivatedAbility;
import core.Utility;
import unit.Unit;
import unit.manager.CoalitionManager;
import unit.manager.EmpireManager;
import world.Cell;
import world.Map;

public abstract class ComputerUnitBackup extends Unit
{	

	ArrayList<Cell> path;
	public static final int STEP_DELAY = 15;
	private static final int TURN_TIME_LOCK_BREAKER = STEP_DELAY * 20;

	private int stepTimer;
	private int turnTime;
	
	
	private boolean movementComplete;
	private boolean actionComplete;

	public ComputerUnitBackup()
	{
		super();

		path = new ArrayList<Cell>();
		setComputer();
		setEnemy();

	}
	
	public boolean isDone()			{		return movementComplete && actionComplete;		}
	
	public ArrayList<Unit> getEnemies()		{		return CoalitionManager.getUnits();	}
	public ArrayList<Unit> getAllies()		{		return EmpireManager.getUnits();	}


	
	public void calculateMoveWeights()
	{
		Map.calculateDistances(this, true);
	}

	public Unit findNearestEnemy()
	{

		calculateMoveWeights();

		Unit nearestUnit = null;
		int lowestWeight = Integer.MAX_VALUE;

		for(Unit u : getEnemies())
		{
			if(u.isAlive() && u.getCell().getWeight() < lowestWeight)
			{
				nearestUnit = u;
				lowestWeight = u.getCell().getWeight();
			}
		}

		return nearestUnit;

	}
	
	
//	public ArrayList<Unit> sortByHealth()
//	{
//		
//	}
	
	public Unit findNearestOpenCellAdjacentToEnemy()
	{
		calculateMoveWeights();

		Unit nearest = null;
		int lowestWeight = Integer.MAX_VALUE;

		for(Unit u : getEnemies())		
		{
			if(u.isAlive())
			{
				// Check if the neighbors are valid
				ArrayList<Cell> neighbors = u.getCell().getNeighborsShuffled();
				for(Cell c : neighbors)
				{
					if(!c.hasUnit())
					{
						if(c.getWeight() < lowestWeight)
						{
							nearest = u;
							lowestWeight = c.getWeight();
						}
						else if(c.getWeight() == lowestWeight && u.getCurHealth() < nearest.getCurHealth())
						{
							nearest = u;
							lowestWeight = c.getWeight();
						}
					}
				}
			}
		}

		return nearest;

	}

	
	private boolean isAdjacent(Unit target)
	{
		return target != null && target.isAlive() &&  isAdjacent(target.getCell());
	}
	
	private boolean isAdjacent(Cell target)
	{
		return Utility.getDistance(getCell(),  target) == 1;
	}
	
	
	
	private Cell getNextStepToward(Cell target)
	{		
		Cell current = target;
		calculateMoveWeights();
		
//		Cell first = current.getLowestWeightNeighbor(true);
//		if(first == null)
//		{
//			
//		}
//		
		
		while(current != null)
		{
			Cell next = current.getLowestWeightNeighbor();
		
			if(Utility.getDistance(getCell(), current) == 1)
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
		this.setDebugMessage(this.getClass().getSimpleName());

		
		super.update();

		if(stepTimer > 0)
		{
			stepTimer--;	
		}
		
		if(isActive())
		{
			turnTime++;
		}
		
		if(turnTime > TURN_TIME_LOCK_BREAKER)
		{
			endMove();
			endAction();
			turnTime = 0;

		}


		if(isActive())
		{
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
	//	this.setDebugMessage(curMove + " " + movementComplete);
		
		if(stepTimer > 0)
		{
			return;
		}

		Map.clearMoveTargets();
		Map.applyMoveTargets(this);

		movement();
		
		Map.clearMoveTargets();
		Map.applyMoveTargets(this);

	}
	
	public abstract void movement();
	public abstract void action();
		

	public void actionPhase()
	{
		Map.clearMoveTargets();
	//	System.out.println("acting");
		
		action();
	}

	public void startTurn()		// called by Combat Manager
	{
		super.startTurn();
		
		stepTimer = STEP_DELAY;
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
		Unit nearestOpenCell = findNearestOpenCellAdjacentToEnemy();
		
		// If valid target isn't next to me, find the next step
		if(nearestOpenCell != null && !isAdjacent(nearestOpenCell))
		{
			Cell target = getNextStepToward(nearestOpenCell.getCell());
			
			
			// If target is valid, move to it and set animation timer
			if(target != null)
			{
				moveTo(target);
			}
			else 
			{
				endMove();
			}
			
			stepTimer = STEP_DELAY;

		}
	
		// End if next to target or out of movement
		if(hasAdjacentEnemy() || getCurMove() <= 0)
		{
			endMove();
		}
	}
	
	public void retreat()
	{
		Cell c = null;
			
		if(hasUnitWest() && getCell().getEastCell().canEnter())
		{
			c = getCell().getEastCell();
		}
		else if(hasUnitEast() && getCell().getWestCell().canEnter())
		{
			c = getCell().getWestCell();
		}
		else if(hasUnitNorth() && getCell().getSouthCell().canEnter())
		{
			c = getCell().getSouthCell();
		}
		else if(hasUnitSouth() && getCell().getNorthCell().canEnter())
		{
			c = getCell().getNorthCell();
		}
		
		
		if(c == null || getCurMove() <= 0)
		{
			endMove();
		}
		else
		{
			moveTo(c);
		}
		
		stepTimer = STEP_DELAY;

		

	}
	
	public void endMove()
	{
		movementComplete = true;
		curMove = 0;
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
		useAbility(a, this);
	}
	
	public void useAbility(Class <? extends Ability> clazz)
	{
		useAbility(clazz, this);
	}
	
	public void useAbility(Class <? extends Ability> clazz, Unit target)
	{
		useAbility((ActivatedAbility) getAbility(clazz), target);
	}
	
	public void useAbility(ActivatedAbility a, Unit target)
	{
		ArrayList<Unit> targets = new ArrayList<Unit>();
		targets.add(target);
		useAbility(a, targets);
	}
	
	public void useAbility(Class <? extends Ability> clazz, ArrayList<Unit> targets)
	{
		useAbility((ActivatedAbility) getAbility(clazz), targets);
	}
	
//	public void useAbility(int index)
//	{
//		useAbility(getAbility(index), this);
//	}
//	
//	public void useAbility(int index, Unit target)
//	{
//		useAbility(getAbility(index), target);
//	}
	
	public void useAbility(ActivatedAbility a, ArrayList<Unit> targets)
	{
	
		//System.out.println("using " + a + " on " + target);
		
		// Animation and Sound
// 		if(targets != null)			DISABLED FOR NOW OUT OF DATE
//		{
//			attackAnimation(targets.get(0), a);
//		}
//		a.sound();
//		CombatManager.abilityPause();
//
//		// Delayed abilities
//		if(a.getDelay() == 0)
//		{
//			CombatManager.useAbility(a, targets);
//		}
//		else
//		{
//			CombatManager.addDelayedAbility(a, targets);
//		}

		// End
		endAction();
	//	reset();

	}

}
