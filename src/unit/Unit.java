package unit;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;

import ability.Ability;
import ability.AbilitySet;
import ability.ActivatedAbility;
import ability.Discipline;
import ability.Tag;
import core.Color;
import core.Main;
import core.Settings;
import core.Utility;
import modifier.Modifier;
import modifier.ModifierSet;
import modifier.conditions.Condition;
import modifier.conditions.Haste;
import modifier.conditions.Slow;
import states.combat.CombatManager;
import states.combat.ControlState;
import ui.Mouse;
import ui.combat.healthbar.UnitBars;
import unit.manager.CoalitionManager;
import unit.manager.EmpireManager;
import world.Cell;
import world.Map;


public class Unit extends Entity implements Comparable<Unit>
{	
	private final int PUSH_TIME_PER_CELL = 10;

	private AbilitySet abilities;
	
	protected String name;
	protected String type;
	protected int startingGuard;
	protected int startingEnergy;
	protected int skillPoints;
	protected int curMove;
	protected int maxMove;
	protected int maxMoveBase;
	protected int maxHealthBase;
	protected int speed;
	protected int speedBase;
	protected int initiative;
	protected int curActions;
	protected int maxActions;
	private int teamID;
	private int controlID;
	protected Faction faction;

	protected UnitImage imageBase;
	protected UnitImage image;
	
	protected UnitImage portrait;

	private Color colorPrimary;
	private Color colorSecondary;
	private Color colorPrimaryTinted;
	private Color colorSecondaryTinted;

	private UnitBars healthbar;

	// Attack information
	private int attackAnimationTimer;
	private Direction attackDirection;
	private ActivatedAbility attackingAbility;

	private int pushAnimationTimer;
	private int pushesLeft;
	private Ability pushSource;
	private Cell pushOrigin;
	private boolean pushFollow;
	private ModifierSet modifiers;
	
	private boolean showEstimatedDamage;
	
	protected ArrayList<Discipline> disciplines;
	
	/************************** CONSTRUCTOR **************************/

	public Unit()
	{
		super();

		disciplines = new ArrayList<Discipline>();
		colorPrimary = Color.white;
		colorSecondary = Color.white;
		modifiers = new ModifierSet(this);

		healthbar = new UnitBars(this);

		abilities = new AbilitySet(this);
		maxHealth = 1;
		curHealth = maxHealth;
		height = 2;
		speed = 1;
		maxActions = 1;
		curActions = 1;
		name = Utility.getRandomName();
		type = getClass().getSimpleName();

	}


	/************************** ACCESSORS **************************/

	// Booleans

	public boolean isNeutral()						{	return teamID == 0;			}
	public boolean isCoalition()					{	return teamID == 1;			}
	public boolean isEmpire()						{	return teamID == 2;			}
	public boolean isPlayer()						{	return controlID == 1;		}
	public boolean isComputer()						{	return controlID == 2;		}
	public boolean isFriendly(Unit u)				{	return (isCoalition() && u.isCoalition()) || (isEmpire() && u.isEmpire()); }
	public boolean isHostile(Unit u)				{	return (isCoalition() && u.isEmpire()) || (isEmpire() && u.isCoalition()); }
	public boolean isSelf(Unit u)					{	return  u == this; 								}
	public boolean isActive()						{	return CombatManager.getActiveUnit() == this;	}
	public boolean isFocus()						{	return CombatManager.getFocusUnit() == this;	}
	public boolean isTarget()						{	return CombatManager.isTargetUnit(this);		}
	public boolean canMove()						{	return curMove > 0 && isAlive();				}
	public boolean canAct()							{	return curActions > 0 && isAlive();				}
	public boolean hasGuard()						{	return guard > 0;								}
	public boolean hasActivatedAbility(int id)		{	return abilities.hasActivatedAbility(id);		}
	public boolean hasAllyWithin(int range)			{	return getNearestAlly(range) != null;			}
	public boolean hasDebuffedAllyWithin(int range)	{	return getNearestDebuffedAlly(range) != null;			}
	public boolean hasHurtAllyWithin(int range)		{	return getNearestHurtAlly(range) != null;			}

	
	public boolean hasDebuff()						{	return modifiers.hasDebuff();					}
	public boolean showingEstimatedDamage()			{	return showEstimatedDamage;						}
	// Getters
	
	public UnitImage getPortrait()				{	return portrait;								}
	public String getType()						{	return type;									}
	public int getCurMove()						{	return curMove;									}
	public int getMaxMove()						{	return maxMove;									}
	public int getMaxMoveBase()					{	return maxMoveBase;								}
	public int getSpeed()						{	return speed;									}
	public int getSpeedBase()					{	return speedBase;								}
	public int getMaxHealthBase()				{	return maxHealthBase;							}
	
	public int getInitiative()					{	return initiative;								}
	public int getCurActions()					{	return curActions;								}
	public int getMaxActions()					{	return maxActions;								}
	public int getSkillPoints()					{	return skillPoints;								}

	public Color getColorPrimary() 				{	return colorPrimary;							}
	public Color getColorSecondary() 			{	return colorSecondary;							}
	public Faction getFaction()					{	return faction;									}
	public String getName()						{	return name;	}

	public AbilitySet getAbilities()			{	return abilities;								}	
	public ActivatedAbility getAbility(int id)	{	return abilities.getActivatedAbility(id); 					}
	public ActivatedAbility getBasicAttack()	{	return abilities.getActivatedAbility(1);	}

	public int getStartingGuard()				{	return startingGuard;	}
	public int getStartingEnergy()				{	return startingEnergy;	}

	public int getDistance(Unit unit)			{	return Utility.getDistance(this,  unit);			}
	public int getDistance(Cell cell)			{	return Utility.getDistance(this.getCell(),  cell);			}

	public boolean hasUnitWest()				{	return getCell().hasWestCell() && getCell().getWestCell().hasUnit();	}
	public Unit getUnitWest()					{	return hasUnitWest() ? getCell().getWestCell().getUnit() : null;		}
	public boolean hasEnemyWest()				{	return hasUnitWest() && isHostile(getUnitWest());						}
	public boolean hasAllyWest()				{	return hasUnitWest() && isFriendly(getUnitWest());						}

	public boolean hasUnitEast()				{	return getCell().hasEastCell() && getCell().getEastCell().hasUnit();	}
	public Unit getUnitEast()					{	return hasUnitEast() ? getCell().getEastCell().getUnit() : null;		}
	public boolean hasEnemyEast()				{	return hasUnitEast() && isHostile(getUnitEast());						}
	public boolean hasAllyEast()				{	return hasUnitEast() && isFriendly(getUnitEast());						}

	public boolean hasUnitNorth()				{	return getCell().hasNorthCell() && getCell().getNorthCell().hasUnit();	}
	public Unit getUnitNorth()					{	return hasUnitNorth() ? getCell().getNorthCell().getUnit() : null;		}
	public boolean hasEnemyNorth()				{	return hasUnitNorth() && isHostile(getUnitNorth());						}
	public boolean hasAllyNorth()				{	return hasUnitNorth() && isFriendly(getUnitNorth());						}

	public boolean hasUnitSouth()				{	return getCell().hasSouthCell() && getCell().getSouthCell().hasUnit();	}
	public Unit getUnitSouth()					{	return hasUnitSouth() ? getCell().getSouthCell().getUnit() : null;		}
	public boolean hasEnemySouth()				{	return hasUnitSouth() && isHostile(getUnitSouth());						}
	public boolean hasAllySouth()				{	return hasUnitSouth() && isFriendly(getUnitSouth());						}

	public Discipline getDiscipline(int index)	{	return disciplines.get(index);		}
public ArrayList<Discipline> getDisciplines()	{	return disciplines;}

	public ArrayList<Unit> getFriendlyUnits()
	{
		return isCoalition() ? CoalitionManager.getUnits() : EmpireManager.getUnits();
	}

	public int getFramerate() 		
	{		
		if(this.modifiers.hasCondition(Slow.class))	return frameRate * 2;
		else if(modifiers.hasCondition(Haste.class)) return frameRate / 4;
		else return frameRate;		
	}


	public Ability getAbility(Class <? extends Ability> clazz)	{	return abilities.getAbility(clazz);	}	
	public boolean hasAbility(Class <? extends Ability> clazz)	{	return abilities.hasAbility(clazz);	}


	public boolean adjacentToEnemy()
	{
		ArrayList<Cell> adjCells = getCell().getNeighbors();

		for(Cell c : adjCells)
		{
			if(c.hasUnit() && isHostile(c.getUnit()))
			{
				return true;
			}
		}

		return false;
	}

	public int compareTo(Unit other)
	{
		if(getInitiative() < other.getInitiative())
		{
			return -1;
		}
		else if(getInitiative() > other.getInitiative())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}


	public ArrayList<Unit> getAlliesWithin(int range)
	{
		ArrayList<Unit> allies = new ArrayList<Unit>();
		
		for(Unit u : getFriendlyUnits())
		{
			if(u.isAlive() && getDistance(u) <= range && u != this)
			{
				allies.add(u);
			}
		}
		
		return allies;

	}
	

	public Unit getNearestAlly(int range)
	{
		ArrayList<Unit> allies = getFriendlyUnits();
		Unit nearest = null;
		int nearestDistance = Integer.MAX_VALUE;

		for(Unit u : allies)
		{
			if(u.isAlive())
			{
				int dist = getDistance(u);
				//			System.out.println(dist);
				//			System.out.println((dist < range) + " "+ (dist < nearestDistance) + " " + (u != this));

				if(dist <= range && dist < nearestDistance && u != this)
				{
					nearest = u;
					nearestDistance = dist;
				}
			}
		}

		return nearest;
	}


	public Unit getNearestDebuffedAlly(int range)
	{
		ArrayList<Unit> allies = getFriendlyUnits();
		Unit nearest = null;
		int nearestDistance = Integer.MAX_VALUE;


		for(Unit u : allies)
		{
			if(u.isAlive())
			{
				int dist = getDistance(u);

				if(u.hasDebuff() && dist <= range && dist < nearestDistance && u != this)
				{
					nearest = u;
					nearestDistance = dist;
				}
			}
		}

		return nearest;
	}

	
	public Unit getNearestHurtAlly(int range)
	{
		ArrayList<Unit> allies = getFriendlyUnits();
		Unit nearest = null;
		int nearestDistance = Integer.MAX_VALUE;

		for(Unit u : allies)
		{
			if(u.isAlive())
			{
				int dist = getDistance(u);

				if(u.getCurHealth() < u.getMaxHealth() && dist <= range && dist < nearestDistance && u != this)
				{
					nearest = u;
					nearestDistance = dist;
				}
			}
		}

		return nearest;
	}


	/************************** MUTATORS **************************/





	public void applyCondition(Condition c, Modifier source)		
	{	
		modifiers.add(c);
		c.setOwner(this);
		c.setSource(source);
	}
	public ModifierSet getModifiers()			{	return modifiers;	}



	public void setColorPrimary(Color c) 		{	this.colorPrimary = c;							}
	public void setColorSecondary(Color c) 		{	this.colorSecondary = c;						}
	public void setColorPrimary(UnitColor c) 	{	setColorPrimary(c.getColor());					}
	public void setColorSecondary(UnitColor c) 	{	setColorSecondary(c.getColor());				}

	public void expendAction()					{ 	curActions--;									}
	public void expendAllActions()				{ 	curActions = 0;									}

	public void expendMove(int amount)			{ 	curMove = Math.max(0, curMove -= amount);		}

	public void tickInitiative()				{	initiative += speed;							}
	public void loseInitiative(int amount)		{	initiative -= amount;							}
	public void gainInitiative(int amount)		{	initiative += amount;							}
	
	public void spendSkillPoints(int amount)	{	skillPoints -= amount;							}
	public void gainSkillPoints(int amount)		{	skillPoints += amount;							}
	
	public void resetInitiative()				{	initiative = 0;									}
	public void gainSpeedEncounter(int amount)	{	speed += amount; 								}
	public void gainSpeedPermanent(int amount)	{	speed += amount; speedBase += amount;			}
	public void gainMoveEncounter(int amount)	{	maxMove += amount; 								}
	public void gainMovePermanent(int amount)	{	maxMove += amount; maxMoveBase += amount;		}
	public void gainMaxHealthEncounter(int amount)	{	maxHealth += amount; 								}
	public void gainMaxHealthPermanent(int amount)	{	maxHealth += amount; maxHealthBase += amount;		}
	public void gainStartingGuard(int amount)		{	startingGuard += amount;	}
	public void gainStartingEnergy(int amount)		{	startingGuard += amount;	}

	
	
	public void setMaxHealthPermanent(int amount)	{	maxHealth = amount; maxHealthBase = amount;	}
	public void setSpeedPermanent(int amount)	{	speed = amount; speedBase = amount;	}
	public void setMovePermanent(int amount)		{	maxMove = amount; maxMoveBase = amount;	}
	public void setStartingGuard(int amount)		{	startingGuard = amount;	}
	public void setStartingEnergy(int amount)	{	startingEnergy = amount;	}

	
	
	public void restoreMovement()				{   curMove = maxMove;								}
	public void restoreActions()				{ 	curActions = maxActions;						}
	public void restoreHealth()					{ 	curHealth = maxHealth;							}
	public void restoreGuard()					{ 	guard = startingGuard;							}
	public void restoreEnergy()					{ 	abilities.resetCharge();						}

	public void removeAllConditions()			{ 	modifiers.removeAllConditions();				}

	public void showEstimatedDamage(boolean b)	{	showEstimatedDamage = b;						}

	public void setNeutral()				{	teamID = 0;		}
	public void setFriendly()				{	teamID = 1;		}
	public void setEnemy()					{	teamID = 2;		}
	public void setPlayer()					{	controlID = 1;	}
	public void setComputer()				{	controlID = 2;	}

	public void addDisciplineFull(Discipline d)
	{
		type = d.getClass().getSimpleName() + " " + getClass().getSimpleName();
		setColorPrimary(d.getColorUnitPrimary());
		setColorSecondary(d.getColorUnitSecondary());
		addDiscipline(d);
	}
	
	public void addDiscipline(Discipline d)
	{
		disciplines.add(d);
		ArrayList<Ability> newAbilities = d.getAbilities();
		
		for(Ability a : newAbilities)
		{
			abilities.addAbility(a);
		
		}
	}
	
	
	public void setSpriteSheet(SpriteSheet sheet)
	{
		this.sheet = sheet;
		portrait = new UnitImage(this, sheet);
	}
	
	public Condition removeDebuff()			{	return modifiers.removeDebuff();		}

	public void setFaction(Faction f)		
	{	
		faction = f;	
		//setColorSecondary(f.getSecondaryColor());
	}



	public void reset()
	{
		// Reset modified attributes
		speed = speedBase;
		maxHealth = maxHealthBase;
		maxMove = maxMoveBase;
		
		
		// Reset normal resources
		restoreMovement();
		restoreActions();
		restoreHealth();
		restoreGuard();
		restoreEnergy();
		resetInitiative();
		removeAllConditions();
		

		
		if(getCell() != null && getCell().hasUnit())
		{
			getCell().removeUnit();
		}

		attackAnimationTimer = 0;
		tintTimer = 0;
		timer = 0;
	}

	public void die()
	{
		resetInitiative();
		attackAnimationTimer = 0;
		tintTimer = 0;
		timer = 0;
	}

	public void update()
	{
		super.update();

		//System.out.println("[UNIT] " + this);


		damageTint();
		healthbar.update();

		if(attackAnimationTimer > 0)
		{
			attackAnimationTimer--;
		}

		updatePush();


		if(isDead() && tintTimer == 0)
		{
			getCell().removeUnit();
			setCell(null);
		}

		//		if(isClicked())
		//		{
		//			CombatManager.setFocusUnit(this);
		//		}
		//		
		//		if(isFocus())
		//		{
		//			Map.applyMoveTargets(this);
		//			//ArrayList<Cell> moveCells = Map.cellsInMovementRange(this);
		//		}

	}

	public void updatePush()
	{

		if(pushAnimationTimer > 0)
		{
			pushAnimationTimer--;
		}

		if(pushAnimationTimer == 0 && pushesLeft > 0)
		{		
			//			System.out.println(pushesLeft);

			push(pushSource, pushOrigin, pushesLeft, pushFollow);
		}

		if(pushAnimationTimer == 0 && pushesLeft == 0)
		{
			endPush();
		}
	}

	public void startCombat()
	{
		restoreHealth();
		restoreMovement();
		restoreGuard();

		if(getCell().getX() > Map.getWidth() / 2)
		{
			faceWest();
		}
		else
		{
			faceEast();
		}
		
		modifiers.startCombat();
	}

	public void startTurn()
	{
		modifiers.startTurn();


		//		if(adjacentToEnemy())
		//		{
		//			curMove = 1;
		//		}
	}

	public void endTurn()
	{
		modifiers.endTurn();

		abilities.gainCharge(1);
		restoreMovement();
		restoreActions();
	}

	public void gainCharge(int amount)
	{
		abilities.gainCharge(amount);
	}

	public void damageTint()
	{
		if(tintTimer > 0)
		{
			float percent = (float) Math.pow((float) tintTimer / (float) TINT_TIME, 2);
			colorPrimaryTinted = Color.getScalingTint(colorPrimary, tint, percent);
			colorSecondaryTinted = Color.getScalingTint(colorSecondary, tint, percent);
		}
		else
		{
			colorPrimaryTinted = colorPrimary;
			colorSecondaryTinted = colorSecondary;
		}
	}
	
	

	public void animation()
	{
		if(getFramerate() > 0 && timer % getFramerate() == 0)
		{
			currentFrame++;
		}


		if(looping && currentFrame >= frames)
		{
			currentFrame = 0;
		}

		if(sheet != null && currentFrame < frames)
		{
			imageBase = new UnitImage(this, sheet, currentFrame);
			image = imageBase;
		}
		else if(currentFrame == 0 && frames == 0)
		{
			imageBase = new UnitImage(this, sheet, 0);
			image = imageBase;		
		}
		else
		{
			sheet = null;
			imageBase = null;
			image = null;
		}


		if(sheet != null)
		{

			if(faceEast)
			{
				image = imageBase.getFlippedCopy(true, false);
			}
		}
	}

	public void render(Graphics g)
	{	

		if(isDead() && tintTimer == 0)
		{
			return;
		}

		// Outline the active unit
		renderActiveMarker(g);
		renderTargetMarker(g);

		// Unit render based on state
		renderMove();
		renderAbilityTarget();
		renderAttack();
		renderBasic();

	} 
	
	public void renderSecond(Graphics g)
	{

		if(isFocus() || showingHealthbar() || Main.getInput().isKeyDown(Input.KEY_SPACE) || Settings.healthbarAlwaysVisible)
		{
			//if(!hasUnitNorth())
			//{
				healthbar.render(g);
			//}
		}

	}
	
	public void renderThird(Graphics g)
	{

		renderDamagePreview();

		renderDebug(g);

	}


	public void renderMove()
	{
		if(CombatManager.getControlState() == ControlState.MOVEMENT && isActive())
		{

			// If I can't move or the mouse is outside of the valid move position, draw a default image
			if(!canMove() || !(Map.inBounds(Mouse.getX(), Mouse.getY()) && Map.getCell(Mouse.getX(), Mouse.getY()).isMoveTarget()))
			{
				image.draw(getXPixel(), getYPixel() - getHeightPixelOffset(), getWidthPixel(),  getHeightPixel(), color, colorPrimaryTinted, colorSecondaryTinted);
				return;
			}


			// Draw character at original location, but faded
			int gray = 160;
			image.draw(getXPixel(), getYPixel() - getHeightPixelOffset(), getWidthPixel(),  getHeightPixel(), new Color(gray, gray, gray, gray), new Color(gray, gray, gray, gray), new Color(gray, gray, gray, gray));

			// Draw character at destination		
			UnitImage destination = image;
			if((Mouse.getX() > getX() && isFacingWest()) || (Mouse.getX() < getX() && isFacingEast()) )
			{
				destination = image.getFlippedCopy(true, false);
			}
			destination.draw(Mouse.getXPixelGrid(), Mouse.getYPixelGrid() - getHeightPixelOffset(), getWidthPixel(),  getHeightPixel(), color, colorPrimaryTinted, colorSecondaryTinted);

		}


	}


	public void renderAbilityTarget()
	{
		if(CombatManager.getControlState() == ControlState.ABILITY && isActive())
		{		
			// Flip toward target
			UnitImage pivot = image;
			if((Mouse.getX() > getX() && isFacingWest()) || (Mouse.getX() < getX() && isFacingEast()) )
			{
				pivot = image.getFlippedCopy(true, false);
			}

			pivot.draw(getXPixel(), getYPixel() - getHeightPixelOffset(), getWidthPixel(),  getHeightPixel());
		}
	}


	public void renderAttack()
	{
		if(attackAnimationTimer == 0)
		{
			return;
		}


		// Determine force of shove animation

		int distance = (int) (Main.getCellSize() * .7f);

		if(attackingAbility.hasTag(Tag.MELEE))
		{
			distance *= 2;
		}

		float percent = 1 - ((float) attackAnimationTimer / (float) ATTACK_TIME);

		if(percent > .5)
		{
			percent = 1 - percent;
		}

		percent = (float) Math.pow(percent, 1.8);
		int offset = (int) (distance * percent);
		int tempX = getXPixel();
		int tempY = getYPixel();

		// Determine direction

		if(attackDirection == Direction.WEST)			{	tempX -= offset;	}
		else if(attackDirection == Direction.EAST)		{	tempX += offset;	}		
		else if(attackDirection == Direction.NORTH)		{	tempY -= offset;	}
		else if(attackDirection == Direction.SOUTH)		{	tempY += offset;	}

		image.draw(tempX, tempY - getHeightPixelOffset(), getWidthPixel(),  getHeightPixel(), color, colorPrimaryTinted, colorSecondaryTinted);

	}

	public void renderBasic()
	{
		if(attackAnimationTimer > 0)
		{
			return;
		}

		if(CombatManager.getControlState() == ControlState.OBSERVATION || !isActive())
		{
			image.draw(getXPixel(), getYPixel() - getHeightPixelOffset(), getWidthPixel(),  getHeightPixel(), color, colorPrimaryTinted, colorSecondaryTinted);
		}
	}
	
	public void renderDamagePreview()
	{
//		ActivatedAbility a = CombatManager.getActiveAbility();
//
//		if(showingEstimatedDamage() && a != null)
//		{
//			
//			int damage = this.calculateDamage(a, a.getTargetDamageEstimate());
//			int healing = this.calculateHealing(a, a.getTargetHealingEstimate());
//			
//			Text.setFont(Fonts.titleText);
//			Text.shadowOn();
//			
//			Image num;
//			
//			if(damage > healing)
//			{
//				num = Images.numbers[damage-healing];
//				num.setFilter(Image.FILTER_NEAREST);
//				num.draw(getXPixel(), getYPixel(), Main.getGameScale(), Color.yellow);
//			}
//			if(healing > damage)
//			{
//				num = Images.numbers[healing-damage];
//				num.setFilter(Image.FILTER_NEAREST);
//				num.draw(getXPixel(), getYPixel(), Main.getGameScale(), Tag.HEAL.getColor());
//			}
//			
//			
//		}
	}

	public void renderActiveMarker(Graphics g)
	{
		if(isActive())
		{
			int gray = 155 + getTimer() % 50 * 2;

			if(getTimer() % 100 >= 50)
			{
				gray = 155 + 100 - getTimer() % 50 * 2;
			}

			//			// Center
			g.setColor(new Color(gray, gray, gray, 100));
			g.fillRoundRect(getXPixel(), getYPixel(), Main.getCellSize(), Main.getCellSize(), 6);
			//			
			//			// Outline
			//			g.setColor(new Color(gray, gray, gray, 100));
			//			g.setLineWidth(3);
			//			g.drawRoundRect(getXPixel(), getYPixel(), Cell.CELL_SIZE, Cell.CELL_SIZE, 6);

			g.setColor(new Color(0, 0, 0));
			int indent = Main.getGameScale();
			g.setLineWidth(Main.getGameScale() * 2);
			g.drawRect(getXPixel()+indent, getYPixel()+indent, 
					   Main.getCellSize()-indent*2, Main.getCellSize()-indent*2);

			g.setColor(new Color(gray, gray, gray, 255));

			//			g.setColor(new Color(255, 255, 255));
			g.setLineWidth(Main.getGameScale());
			g.drawRect(getXPixel()+indent, getYPixel()+indent, 
					    Main.getCellSize() - indent*2, Main.getCellSize() - indent*2);

		}
	}
	
	
	public void renderTargetMarker(Graphics g)
	{
		if(isTarget())
		{
			int orange = 155 + getTimer() % 50 * 2;

			if(getTimer() % 100 >= 50)
			{
				orange = 155 + 100 - getTimer() % 50 * 2;
			}

			g.setColor(new Color(orange, orange, 0, 100));
			g.fillRoundRect(getXPixel(), getYPixel(), Main.getCellSize(), Main.getCellSize(), 6);
	

			g.setColor(new Color(0, 0, 0));
			g.setLineWidth(5);
			g.drawRect(getXPixel(), getYPixel(), Main.getCellSize(), Main.getCellSize());

			g.setColor(new Color(orange, orange, 0, 255));
			g.setLineWidth(3);
			g.drawRect(getXPixel(), getYPixel(), Main.getCellSize(), Main.getCellSize());

		}
	}


	public void setCell(Cell c)
	{
		if(getCell() != null)
		{
			if(getCell().getUnit() == this)
			{
				getCell().removeUnit();
			}
			else if(getCell().getPassingUnit() == this)
			{
				getCell().removePassingUnit();
			}
		}

		super.setCell(c);

		if(getCell() != null)
		{
			getCell().addUnit(this);
		}

	}

	public void push(Ability source, int distance)
	{
		push(source, distance, false);
	}

	public void push(Ability source, int distance, boolean following)
	{
		push(source, source.getOwner().getCell(), distance, following);
	}

	public void push(Ability source, Cell origin, int distance, boolean following)
	{
		// Ignore irrelevant pushes
		if(distance <= 0 || isDead())
		{
			return;
		}

		Cell destination = getNextPushDestination(origin);

		// No effect pushing off map or toward water, push ends
		if(destination == null)
		{
			endPush();
			//			System.out.println("PUSH: No valid destination");

		}

		else if(!destination.getTerrain().canEnter())
		{
			endPush();
			//			System.out.println("PUSH: Bad terrain ");
		}

		// Both units take damage, push ends
		else if(destination.hasUnit())
		{
			takeDamage(source, 1);
			destination.getUnit().takeDamage(source, 1);
			endPush();
			//			System.out.println("PUSH: Into unit");

		}

		// If pushed into obstacle, you take damage and push ends
		else if(destination.hasObstacle())
		{
			takeDamage(source, 1);
			endPush();
			//			System.out.println("PUSH: Into obstacle");

		}

		// Push executes and moves you to new cell
		else if(destination.canEnter())
		{
			Cell oldCell = getCell();

			// Store all pushes beyond the first and set a timer to execute
			if(distance >= 1)
			{
				//				System.out.println("PUSH: Continues");

				pushSource = source;
				pushOrigin = oldCell;
				pushesLeft = distance - 1;
				pushFollow = following;
				pushAnimationTimer = PUSH_TIME_PER_CELL;
			}		

			//			System.out.println("PUSH: Open cell");

			setCell(destination);

			//	System.out.println("PUSH: " + following);

			if(following)
			{
				source.getOwner().setCell(oldCell);
			}

		}
	}

	private void endPush()
	{
		pushSource = null;
		pushOrigin = null;
		pushesLeft = 0;
		pushAnimationTimer = 0;
		pushFollow = false;
	}

	private Cell getNextPushDestination(Cell origin)
	{
		//System.out.println(getCell() + " vs. origin: " + origin);

		if(getCell().getWestCell() == origin && getCell().hasEastCell())
		{
			return getCell().getEastCell();
		}
		else if(getCell().getEastCell() == origin  && getCell().hasWestCell())
		{
			return getCell().getWestCell();
		}
		else if(getCell().getSouthCell() == origin && getCell().hasNorthCell())
		{
			return getCell().getNorthCell();
		}
		else if(getCell().getNorthCell() == origin && getCell().hasSouthCell())
		{
			return getCell().getSouthCell();
		}
		else
		{
			return null;	// for out of bounds cells
		}
	}

	
	public void addAbility(Ability a)
	{
		abilities.addAbility(a);
		a.setOwner(this);
	}

	public void attackAnimation(Cell target, ActivatedAbility ability)
	{
		attackAnimationTimer = ATTACK_TIME;
		attackDirection = Direction.getDirection(this.getCell(), target);
		attackingAbility = ability;


		if(target.getX() > getX())
		{
			faceEast();
		}
		else if(target.getX() < getX())
		{
			faceWest();
		}

		// write code to determine if the cell is up, down, left, or right of me
		// move my location toward that quickly
		// set attacking Timer in here and animation lock the combat manager
	}

	public void moveTo(Cell c)
	{
		if((c.getX() > getX() && isFacingWest()))
		{
			faceEast();
		}
		else if((c.getX() < getX() && isFacingEast()) )
		{
			faceWest();
		}


		//int dist = c.getWeight();

		if(hasEnoughMovementToEnter(c))
		{
			if(this instanceof PlayerUnit)
			{
				curMove = 0;		

			}
			else
			{
				curMove -= c.getMoveCost();
			}

			//curMove -= dist;		// Allowing only one move per turn
			setCell(c);
		}


	}


	public boolean hasEnoughMovementToEnter(Cell c)
	{
		return c.getWeight() <= getCurMove() || (c.getMoveCost() > 1 && c.getWeight() - getCurMove() == 1);

	}
	
	public void takeDamage(Modifier source, int amount)
	{		
		amount = modifiers.getModifiedDamageRecieved(amount, source.getTags());
		modifiers.damageTaken();
		super.takeDamage(source, amount);
	}
	
	public int calculateDamage(ActivatedAbility source, int amount) 		
	{	
		int newAmount = 0;
		
		for(int i = 0; i < source.getAttacks(); i++)
		{
			newAmount += modifiers.getModifiedDamageRecieved(amount, source.getTags());		
		}
		
		return newAmount;
	}
	
	public void regainHealth(Modifier source, int amount)
	{
		amount = modifiers.getModifiedHealingRecieved(amount, source.getTags());
		modifiers.healingRecieved();
		super.regainHealth(source, amount);
	}
	
	public int calculateHealing(ActivatedAbility source, int amount) 		
	{	
		return amount = modifiers.getModifiedHealingRecieved(amount, source.getTags());		
	}	
	
	public void loseHealth(int amount)
	{
		showHealthbar();

		curHealth -= amount;

		if(curHealth < 0)
		{
			curHealth = 0;
		}
	}




}
