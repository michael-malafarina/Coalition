package ability;

import java.util.ArrayList;

import ability.base.general.EndTurn;
import core.Color;
import modifier.Upgrade;
import modifier.conditions.Condition;
import unit.Unit;
import world.Cell;

abstract public class Ability extends Upgrade implements Comparable<Ability>
{
	private int index;

	protected Discipline discipline;
	protected int damage = 0;
	protected int duration = 0;
	protected int healing = 0;
	protected int level = 1;
	protected int range = 0;
	protected int rangeSecondary = 0;
	protected int guard = 0;
	protected int shred = 0;
	protected int push = 0;
	protected int delay = 0;
	protected int energize = 0;
	protected int advance = 0;
	protected int attacks = 1;
	protected boolean pushFollow = false;
	protected ArrayList<Object> conditions;

	protected boolean canTargetSelf = false;
	protected ArrayList<Unit> secondaryTargets;

	private boolean learned;

	abstract public void setup();
	
	Ability() 
	{
		super();
		secondaryTargets = new ArrayList<Unit>();
		conditions = new ArrayList<Object>();
		setup();
		
		if(discipline != null)
		{
			icon = discipline.getIcon();
		}
	}
	
    /*************** Accessors ***************/

	
	public int getBaseDamage()						{	return damage;								}
	public int getBaseHealing()						{	return healing;								}
	public int getBaseDuration()					{	return duration;							}
	public int getBasePush()						{	return push;								}
	public int getBaseGuard()						{	return guard;								}
	public int getBaseShred()						{	return shred;								}
	public boolean isLearned()						{	return learned;								}

	public boolean followPush()						{	return pushFollow;							}
	
	public int getAttacks()							{	return attacks;								}
	public int getCost()							{	return level;								}
	
	public int getEnergize()						{	return energize;							}
	public int getAdvance()							{	return advance;								}
	public int getDelay()							{	return delay;								}

	
	public int getRange()							{	return range;								}
	public int getRangeSecondary()					{	return rangeSecondary;						}
	
	
	public boolean inRange(Unit u)					{	return getOwner().getDistance(u) <= range;	}							
	public boolean inRange(Cell c)					{	return getOwner().getDistance(c) <= range;	}							

	public boolean hasSecondaryTarget()				{	return !secondaryTargets.isEmpty();			}
	public Unit getSecondaryTarget()				{	return secondaryTargets.get(0);				}

	public ArrayList<Unit> getScondaryTargets()		{	return secondaryTargets;					}
	public ArrayList<Object> getConditions()		{	return conditions;							}
	
	
	public Discipline getDiscipline()				{	return discipline;								}
	
	public Color getDisciplineColorDark()		{	return discipline.getColorMenuDark();	}
	public Color getDisciplineColorLight()		{	return discipline.getColorMenuLight();	}

	public void learn()					
	{
		learned = true;
		

		if(this instanceof PassiveAbility)
		{
			getOwner().getModifiers().add(this);
		}
	}
	
	public int getIndex()							{	return index;			}

	// Modified Attributes
	
	public int getDamage()				
	{
		if(getOwner() == null) return getBaseDamage();
		else return getOwner().getModifiers().getModifiedDamage(damage, tags);
	}
	public int getHealing()				
	{
		if(getOwner() == null) return getBaseHealing();
		else return getOwner().getModifiers().getModifiedHealing(healing, tags);
	}
	public int getDuration()				
	{
		if(getOwner() == null) return getBaseDuration();
		else return getOwner().getModifiers().getModifiedDuration(duration, tags);
	}	
	public int getGuard()				
	{
		if(getOwner() == null) return getBaseGuard();
		else return getOwner().getModifiers().getModifiedGuard(guard, tags);
	}	
	public int getShred()				
	{
		if(getOwner() == null) return getBaseShred();
		return getOwner().getModifiers().getModifiedShred(shred, tags);
	}	
	public int getPush()				
	{
		if(getOwner() == null) return getBasePush();
		else return getOwner().getModifiers().getModifiedPush(push, tags);
	}	

	
    /*************** Text Display Methods ***************/

	
	public String getModifiedText(int modified, int base)
	{
		if(modified > base)
		{
			return "[HIGH]"+modified+"[]";
		}
		else if(modified < base)
		{
			return "[LOW]"+modified+"[]";
		}
		else
		{
			return "[AVERAGE]"+modified+"[]";
		}
	}
	
	public String getAdvanceText()		{		return getModifiedText(getAdvance(), getAdvance());			}
	public String getDelayText()		{		return getModifiedText(getDelay(), getDelay());				}
	public String getEnergizeText()		{		return getModifiedText(getEnergize(), getEnergize());		}	
	public String getDamageText()		{		return getModifiedText(getDamage(), getBaseDamage());		}
	public String getHealingText()		{		return getModifiedText(getHealing(), getBaseHealing());		}	
	public String getDurationText()		{		return getModifiedText(getDuration(), getBaseDuration());	}
	public String getPushText()			{		return getModifiedText(getPush(), getBasePush());			}
	public String getGuardText()		{		return getModifiedText(getGuard(), getBaseGuard());			}	
	public String getShredText()		{		return getModifiedText(getShred(), getBaseShred());			}	

	public String getTurnText()				{	return getDuration() > 1 ? "turns" : "turn";	}
	
    /*************** Application Methods ***************/
    
	public void addSecondaryTarget(Unit secondary)
	{
		secondaryTargets.add(secondary);
	}
	
	public void clearTargets()
	{
		secondaryTargets.clear();
	}
	
    public void damage() 			  	{	damage(self());    							}
    public void heal()  				{   heal(self());  								}
    public void advance()  				{   advance(self());    						}
    public void delay()  				{   delay(self());    							}
    public void guard()    				{   guard(self());    							}
    public void energize()				{	energize(self());							}
    
    public void damage(Unit target)    	{   target.takeDamage(this, getDamage());    	}
    public void delay(Unit target)  	{   target.loseInitiative(getDelay());    		}
    public void advance(Unit target)  	{   target.gainInitiative(getAdvance());    	}
    public void energize(Unit target)  	{   target.gainCharge(getEnergize());   			}

    public void heal(Unit target)   	{   target.regainHealth(this, getHealing());    }
    public void push(Unit target)		{	target.push(this,  getPush(), pushFollow); 	}
    public void guard(Unit target)    	{   target.gainGuard(getGuard());   			}
    public void shred(Unit target)    	{   target.loseGuard(getShred());    			}
    
    public void damage(ArrayList<Cell> targets)
    {
    	for(Cell c : targets)
    	{
    		if(c.hasUnit())
    		{
    			damage(c.getUnit());
    		}
    	}
    }
    
    public void heal(ArrayList<Cell> targets)
    {
    	for(Cell c : targets)
    	{
    		if(c.hasUnit())
    		{
    			heal(c.getUnit());
    		}
    	}
    }
    
	public void setIndex(int i)
	{
		index = i;
	}
    
    public void applyConditions()
    {
    	applyConditions(self());
    }
    
    public void applyConditions(Unit target)
    {
		for(Object c : conditions)
		{
			target.applyCondition(conditionFactory(c), this);
		}
    }
    
    /************* Utility Methods **************/
	
    public Condition conditionFactory(Object o)
	{
		Class<? extends Condition> clazz = (Class<? extends Condition>) o;

		Condition c = null;
		
		try
		{
			// When I create a new condition, set its duration to the actual duration after modifiers
			c = clazz.newInstance();
			c.setDuration(getDuration());
		}
		catch (InstantiationException | IllegalAccessException e) 
		{
			e.printStackTrace();
		}	
		
		return c;
	}
    
    
    public int compareTo(Ability other)
	{		
    	// Activated First
    	if(this instanceof ActivatedAbility && other instanceof PassiveAbility)
    	{
    		return -1;
    	}
    	else if(this instanceof PassiveAbility && other instanceof ActivatedAbility)
    	{
    		return 1;
    	}    	
    	
    	// Learned First
		if(isLearned() && !other.isLearned())
    	{
    		return -1;
    	}
    	else if(!isLearned() && other.isLearned())
    	{
    		return 1;
    	}
		
		// End Turn Last
    	if(this instanceof EndTurn)
    	{
    		return 1;
    	}
    	if(other instanceof EndTurn)
    	{
    		return -1;
    	}
    	
    	// Basics to the top!
    	if(this instanceof ActivatedAbility && ((ActivatedAbility)(this)).isBasicAttack())
    	{
    		return -1;
    	}
    	
    	if(other instanceof ActivatedAbility && ((ActivatedAbility)(other)).isBasicAttack())
    	{
    		return 1;
    	}
    	
    	// Low cost first
    	if(getCost() < other.getCost())
    	{
    		return -1;
    	}
    	else if(getCost() > other.getCost())
    	{
    		return 1;
    	}
    	
    		

	
		return 0;
		

	}
    

}
