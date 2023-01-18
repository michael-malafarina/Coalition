package states.combat;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import ability.ActivatedAbility;
import ability.AreaTarget;
import ability.MultiTarget;
import ability.SelfTarget;
import ability.SingleTarget;
import ability.base.general.EndTurn;
import ui.Camera;
import ui.Images;
import ui.Mouse;
import unit.ComputerUnit;
import unit.PlayerUnit;
import unit.Unit;
import unit.manager.CoalitionManager;
import unit.manager.EmpireManager;
import world.Cell;
import world.Map;

public class CombatManager
{

	/**************************** DATA ****************************/

	private static InitiativeQueue queue;
	private static ArrayList<Unit> units;
	private static Unit activeUnit;			// The unit currently taking its turn
	private static Unit focusUnit;			// The unit being targeted or highlighted
	private static Unit movementUnit;		// The unit who has their movement being shown
	private static ArrayList<Cell> targetCellsPreview;			
	private static ArrayList<Cell> targetCells;			
	private static ActivatedAbility activeAbility;
	private static ActivatedAbility delayedAbility;
	private static ControlState mode;

	private static int multiClickTimer = 0;				// Locks multitarget spells until done
	private static int pauseAfterAbilityTimer = 0;		// Locks all actions until this is done
	public static int abilityDelayTimer = 0;

	private final static int PAUSE_AFTER_ABILITY_TIME = 60;
	private final static int MULTICLICK_TIMER_MAX = 10;

	/**************************** ACCESSORS ****************************/

	public static Unit getActiveUnit()						{	return activeUnit;				}
	public static Unit getFocusUnit()						{	return focusUnit;				}
	public static Unit getMovementUnit()					{	return movementUnit;			}


	public static ArrayList<Unit> getTargetUnits()			
	{	
		ArrayList<Unit> units = new ArrayList<Unit>();
		for(Cell c : targetCells)
		{
			if(c.hasUnit())
			{
				units.add(c.getUnit());
			}
		}
		return units;
	}


	public static ArrayList<Unit> getTargetUnitsPreview()			
	{	
		ArrayList<Unit> units = new ArrayList<Unit>();
		for(Cell c : targetCellsPreview)
		{
			if(c.hasUnit())
			{
				units.add(c.getUnit());
			}
		}
		return units;
	}


	public static ArrayList<Cell> getTargetCells()			{	return targetCells;				}
	public static ActivatedAbility getActiveAbility()		{	return activeAbility;			}
	public static ActivatedAbility getDelayedAbility()		{	return delayedAbility;			}

	public static boolean isTargetUnit(Unit u)				{	return getTargetUnits().contains(u);	}
	public static boolean hasFocusUnit()					{	return focusUnit != null;		}
	public static boolean hasMovementUnit()					{	return movementUnit != null;	}

	public static boolean hasActiveUnit()					{	return activeUnit != null;		}
	public static boolean hasActiveAbility()				{	return activeAbility != null;	}
	public static boolean hasDelayedAbility()				{	return delayedAbility != null;	}

	public static void setActiveUnit(Unit u)				{	activeUnit = u; reset();			}//	onCamera(); }
	public static void setFocusUnit(Unit u)					{	focusUnit = u;					}

	public static ControlState getControlState()			{ 	return mode;					}

	/**************************** MUTATORS ****************************/

	public static void setTargetCells(ArrayList<Cell> targets) {	targetCells = targets;			} 

	public static void init()
	{
		units = new ArrayList<Unit>();
		queue = new InitiativeQueue();
		targetCells = new ArrayList<Cell>();
		targetCellsPreview = new ArrayList<Cell>();

	}

	public static void begin()
	{
		EmpireManager.beginCombat();

		activeUnit = null;
		focusUnit = null;
		activeAbility = null;
		delayedAbility = null;
		targetCells.clear();

		mode = ControlState.OBSERVATION;

		for(Unit u : units)
		{
			u.reset();
		}

		Map.loadMap();

		units.clear();
		units.addAll(CoalitionManager.getUnits());
		units.addAll(EmpireManager.getUnits());

		CoalitionManager.setStartLocations();
		EmpireManager.setStartLocations();


		for(Unit u : units)
		{
			u.startCombat();
		}

		queue.begin(units);


	}

	public static void update()
	{	
		updateTriggers();

		if(pauseAfterAbilityTimer == 0)
		{
			// Manage turns - universal
			nextTurn();
			endTurn();

			if(getActiveUnit() instanceof PlayerUnit)
			{

				startInMovementMode();
				assignFocus();
				previewMovement();
				ability();
				previewDamage();
				updateMovementMode();
			}

		}
	}

	private static void nextTurn()
	{				
		if(getActiveUnit() == null)
		{
			queue.tick();
			getActiveUnit().startTurn();
		}

		// Retry if it dies from damage over time effects
		if(getActiveUnit().isDead())
		{
			getActiveUnit().die();
			activeUnit = null;
		}


	}


	private static void endTurn()
	{				
		if(getActiveUnit() == null)
		{
			return;
		}

		// End turn if the unit acts OR if they are out of movement while prevented from attacking
		if(getActiveUnit().getCurActions() == 0 || (!getActiveUnit().canMove() && !getActiveUnit().canAct()))
		{
			getActiveUnit().endTurn();			
			activeUnit = null;

		}
		//		if((getActiveUnit() instanceof ComputerUnit))
		//		{
		//			System.out.println(((ComputerUnit)getActiveUnit()).isDone());
		//		}
		if((getActiveUnit() instanceof ComputerUnit && ((ComputerUnit)getActiveUnit()).isDone()))
		{
			getActiveUnit().endTurn();			
			activeUnit = null;
		}


	}

	private static void updateTriggers()
	{
		if(pauseAfterAbilityTimer > 0)
		{
			pauseAfterAbilityTimer--;
		}

		if(multiClickTimer > 0)
		{
			multiClickTimer--;
		}

		if(abilityDelayTimer > 0)
		{
			abilityDelayTimer--;
		}
		else
		{
			useAbility(getDelayedAbility());
			delayedAbility = null;
		}
	}

	private static void updateMovementMode()
	{
		if(mode == ControlState.MOVEMENT)
		{
			// Activate an basic ability if you can't move
			if(hasActiveUnit() && getActiveUnit().isAlive() && !getActiveUnit().canMove())
			{				
				startAbilityMode(getActiveUnit().getAbility(1));
			}
		}

	}

	public static void render(Graphics g)
	{
		g.drawString("" + mode, 20, 20);
		g.drawString("" + activeAbility, 20, 50);
	}

	public static void keyPressed(int key, char c)
	{		
		// Ignore all key events while the game is locked for animation
		if(pauseAfterAbilityTimer > 0 || getActiveUnit() instanceof ComputerUnit)
		{
			return;
		}

		ActivatedAbility chosenAbility = null;

		// Determine which ability (if any) is being used
		if(key >= 2 && key <= 11 && getActiveUnit().hasActivatedAbility(key - 1))			// Slick keycodes are one above the number key
		{
			chosenAbility = getActiveUnit().getAbility(key - 1);
			targetCells.clear();
			targetCellsPreview.clear();
		}

		if(key == Input.KEY_E)
		{
			if(getActiveAbility() instanceof EndTurn)
			{
				getActiveAbility().use();
			}
			else if(getActiveUnit() != null)
			{				
				chosenAbility = (ActivatedAbility) getActiveUnit().getAbility(EndTurn.class);
			}
		}

		// If no valid ability is selected, do nothing
		if(chosenAbility == null)
		{
			return;
		}
		// If the same ability was selected again, it cancels your action
		else if(chosenAbility == getActiveAbility())		
		{
			startMovementMode();
		}
		// Otherwise, activate the ability
		else
		{
			startAbilityMode(chosenAbility);
		}
	}

	public static void mousePressed(int button, int x, int y)
	{		
		// Ignore all mouse events while the game is locked for animation
		if(pauseAfterAbilityTimer > 0)
		{
			return;
		}

		if(button == 0)
		{

			movement();


			//			if(Mouse.getCell() != null && Mouse.getCell().hasUnit())
			//			{
			//				reset();
			//			}

		}

		if(button == 1)
		{
			startMovementMode();
		}

		if(button == 2)
		{
			onCamera();
		}
	}


	private static void ability()
	{
		if(mode == ControlState.ABILITY)
		{
			targetCellsPreview.clear();

			for(Unit u : units)
			{
				if(u.isMouseOver())
				{					
					if(getActiveAbility() instanceof SingleTarget && ((SingleTarget) getActiveAbility()).canUse(u))
					{
						targetCellsPreview.add(u.getCell());

						if(u.isClicked())
						{
							targetCells.add(u.getCell());
							getActiveUnit().attackAnimation(u.getCell(), getActiveAbility());
							triggerAbility();
						}

					}
					else if(getActiveAbility() instanceof MultiTarget && ((MultiTarget) getActiveAbility()).canUse(u))
					{				
						targetCellsPreview.addAll(targetCells);
						targetCellsPreview.add(u.getCell());

						if(u.isClicked() && multiClickTimer == 0)
						{
							multiClickTimer = MULTICLICK_TIMER_MAX;
							targetCells.add(u.getCell());
							//System.out.println(getActiveAbility());						

							if(getTargetUnits().size() == ((MultiTarget) getActiveAbility()).getNumTargets())
							{
								getActiveUnit().attackAnimation(u.getCell(), getActiveAbility());
								triggerAbility();									
							}

						}
					}
					else if(getActiveAbility() instanceof SelfTarget && getActiveAbility().canUse())
					{
						targetCellsPreview.add(getActiveAbility().getOwner().getCell());
						if(u.isClicked())
						{
							targetCells.add(u.getCell());
							triggerAbility();
						}
					}					
				}
			}

			for(Cell c : Map.getCells())
			{
				if(c == Mouse.getCell())
				{
					if(getActiveAbility() instanceof AreaTarget && ((AreaTarget) getActiveAbility()).canUse(c))
					{
						AreaTarget a = (AreaTarget) getActiveAbility();
						targetCellsPreview = a.getTargets(c);

						if(c.isClicked())
						{
							targetCells = a.getTargets(c);
							getActiveUnit().attackAnimation(c, getActiveAbility());
							triggerAbility();
						}
						return;
					}

				}
			}
		}
	}
	private static void triggerAbility()
	{
		abilityPause();

		getActiveUnit().expendAction();
		getActiveAbility().sound();

		if(getActiveAbility().getAnimationDelay() == 0)
		{
			useAbility(getActiveAbility());
		}
		else
		{
			addDelayedAbility(getActiveAbility());
		}


		reset();
	}

	public static void abilityPause()
	{
		pauseAfterAbilityTimer = PAUSE_AFTER_ABILITY_TIME;
	}

	public static void useAbility(ActivatedAbility a)
	{
		if(a instanceof SingleTarget)
		{
			((SingleTarget) a).use(getTargetUnits().get(0));
			targetCells.clear();

		}
		else if(a instanceof MultiTarget)
		{
			((MultiTarget) a).use(getTargetUnits());
			targetCells.clear();
		}
		else if(a instanceof SelfTarget)
		{
			a.use();
			targetCells.clear();
		}
		else if(a instanceof AreaTarget)
		{
			((AreaTarget) a).use(getTargetCells());
			targetCells.clear();
		}



	}

	public static void addDelayedAbility(ActivatedAbility a)
	{

		delayedAbility = getActiveAbility();
		abilityDelayTimer = getActiveAbility().getAnimationDelay();
	}

	private static void movement()
	{
		if(mode == ControlState.MOVEMENT)
		{
			for(Cell c : Map.getCells())
			{
				if(c.isClicked() && c.isMoveTarget())
				{
					getActiveUnit().moveTo(c);
				}
			}

		}		
	}

	private static void startInMovementMode()
	{
		if(hasActiveUnit() && mode != ControlState.ABILITY)
		{
			startMovementMode();
		} 
	}

	private static void previewDamage()
	{
		if(!hasActiveAbility())
		{
			return;
		}

		//System.out.println("previewDamage");

		for(Unit u : units)
		{	
			u.showEstimatedDamage(false);
		}

		for(Unit u : getTargetUnitsPreview())
		{	
			if(u.isAlive())
			{
				//	System.out.println("hi" + u);
				//	if(u.getCell().isMouseOver() && u.getCell().isAbilityTarget())
				{
					u.showHealthbar(1);
					u.showEstimatedDamage(true);
				}
			}
		}

	}

	private static void previewMovement()
	{
		if(hasMovementUnit())
		{
			movementUnit = null;
		}

		for(Unit u : units)
		{	
			if(u.isAlive())
			{
				if(u.getCell().isMouseOver() && !u.isActive())
				{
					movementUnit = u;
				}
			}
		}
	}



	private static void assignFocus()
	{
		focusUnit = null;

		for(Unit u : units)
		{	
			if(u.isAlive() && u.getCell().isMouseOver())
			{
				focusUnit = u;
			}
		}
	}

	public static void onCamera()
	{
		reset();
		Camera.centerCamera(CombatManager.getActiveUnit());
	}

	private static void reset()
	{
		mode = ControlState.OBSERVATION;
		Mouse.setMouseCursor(Images.cursorPointer);

		activeAbility = null;
		Map.clearAbilityTargets();
		Map.clearMoveTargets();		
	}

	private static void startMovementMode()
	{
		mode = ControlState.MOVEMENT;		
		activeAbility = null;
		targetCells.clear();

		Mouse.setMouseCursor(Images.cursorPointer);

		Map.clearAbilityTargets();
		Map.clearMoveTargets();
		Map.applyMoveTargets(getActiveUnit());

		if(getMovementUnit() != null)
		{
			Map.applyMoveTargets(getMovementUnit());
		}

	}



	private static void startAbilityMode(ActivatedAbility chosenAbility)
	{		
		//		if(!chosenAbility.canUse())
		//		{
		//			return;
		//		}

		Map.clearMoveTargets();		

		if(movementUnit != null)
		{
			Map.applyMoveTargets(getMovementUnit());
		}


		if(chosenAbility.isInstant() && chosenAbility.canUse())
		{
			useAbility(chosenAbility);
		}
		else
		{
			Mouse.setMouseCursor(Images.cursorAttack);
			mode = ControlState.ABILITY;

			//			System.out.println("CM - Start Ability Mode" + chosenAbility);

			activeAbility = chosenAbility;
			Map.applyAbilityTargets(getActiveUnit());
		}


	}

}
