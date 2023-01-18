package modifier;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import ability.ActivatedAbility;
import ability.Tag;
import core.Color;
import core.Main;
import unit.Unit;

public abstract class Modifier 
{
	public static final int ICON_SIZE = 9;

	protected String name;
	protected Unit owner;
	protected Image icon;
	
	protected boolean hidden;
	
	protected int passiveHealingBonus;
	protected int passiveHealingRecievedBonus;
	protected int passiveDamageBonus;
	protected int passiveDamageRecievedBonus;
	protected int passiveGuardGainBonus;
	protected int passivePushBonus;
	protected int passiveShredBonus;
	protected int speedBonus;
	protected float speedScalar;
	protected float durationScalar;
	protected boolean ignoresGuard;
	
	public String getName()						{	return name;				}	
	public Unit getOwner()						{ 	return owner;				}
	public Unit self()							{ 	return owner;				}

	public boolean isExpired()					{	return false;				}
	public boolean ignoresGuard()				{	return ignoresGuard;		}
	public boolean isHidden()					{	return hidden;				}

	public boolean hasHealingBonus()			{	return passiveHealingBonus != 0;	}
	public boolean hasHealingRecievedBonus()	{	return passiveHealingRecievedBonus != 0;	}
	public boolean hasDamageBonus()				{	return passiveDamageBonus != 0;	}
	public boolean hasDamageRecievedBonus()		{	return passiveDamageRecievedBonus != 0;	}
	public boolean hasGuardBonus()				{	return passiveGuardGainBonus != 0;		}
	public boolean hasPushBonus()				{	return passivePushBonus != 0;		}
	public boolean hasShredBonus()				{	return passiveShredBonus != 0;		}
	public boolean hasSpeedBonus()				{	return speedBonus != 1;	}
	public boolean hasSpeedScalar()				{	return speedScalar != 1;	}
	public boolean hasDurationScalar()			{	return durationScalar != 1;	}
	
	public int getHealingBonus()				{	return passiveHealingBonus;			}
	public int getHealingRecievedBonus()		{	return passiveHealingRecievedBonus;	}

	public boolean hasIcon()					{	return getIcon() != null;	}

	
	public Image getIcon()						{	return icon ;		}	

	public static int getIconSize()					{	return ICON_SIZE * Main.getGameScale();	}
	public int getDamageBonus()					{	return passiveDamageBonus;		}
	public int getDamageRecievedBonus()			{	return passiveDamageRecievedBonus;		}

	public int getGuardBonus()					{	return passiveGuardGainBonus;		}
	public int getShredBonus()					{	return passiveShredBonus;		}
	public int getSpeedBonus()					{	return speedBonus;				}
	public int getPushBonus()					{	return passivePushBonus;		}
	
	public float getDurationScalar()				{	return durationScalar;			}
	public float getSpeedScalar()					{	return speedScalar;				}

	abstract public void sound();
	abstract public void animation();
	
	protected ArrayList<Tag> tags;
	
	public Modifier()
	{
		tags = new ArrayList<Tag>();
		durationScalar = 1;	
	}
	
	abstract public String getDescription();
	public String getDescriptionShort()
	{
		return getDescription();
	}
	
	public boolean modifiesDamage(ArrayList<Tag> tags)
	{
		if(hasDamageBonus())  
		{
			for(Tag t : tags)  
			{
				if(hasTag(t) || hasTag(Tag.ALL))
				{
					return true;
				}
			}	
			
			return false;
		}
		else
		{
			return false;
		}
	}
	
	public int getDamageModifier(ArrayList<Tag> tags)
	{
		if(modifiesDamage(tags))
		{
			for(Tag t : tags)  
			{
				if(hasTag(t) || hasTag(Tag.ALL))
				{
					return getDamageBonus();
				}
			}	
			
			return 0;

		}
		return 0;
	}	
	
	public boolean modifiesDamageRecieved(ArrayList<Tag> tags)
	{		
		if(hasDamageRecievedBonus())  
		{
			for(Tag t : tags)  
			{
				if(hasTag(t) || hasTag(Tag.ALL))
				{
					return true;
				}
			}	
			
			return false;
		}
		else
		{
			return false;
		}
	}
	
	public int getDamageRecievedModifier(ArrayList<Tag> tags)
	{
		if(modifiesDamageRecieved(tags))
		{
			for(Tag t : tags)  
			{
				if(hasTag(t) || hasTag(Tag.ALL))
				{
					return getDamageRecievedBonus();
				}
			}	
			
			return 0;

		}
		return 0;
	}
	

	
	public String toString()
	{
		return getName();
	}
	
	
	public boolean hasTag(Tag tag)
	{
		for(Tag t : tags)
		{
			if(t == tag)
			{
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Tag> getTags()
	{
		return tags;
	}
	
	public Color getColor()
	{
		for(Tag t : tags)
		{
			if(t.getColor() != null)
			{
				return t.getColor();
			}
		}
		return Color.white;
	}
	
	public void setOwner(Unit unit)
	{
		owner = unit;
	}
	
	public void startCombat()
	{
		
	}
	
	public void startTurn()
	{
		
	}
	
	public void startTurnAura(Unit u)
	{
		
	}
	
	public void endTurn()
	{
		
	}
	
	public void endTurnAura(Unit u)
	{
		
	}
	
	public void onHealingRecieved()
	{
		
	}
	
	public void onDamageTaken()
	{
		
	}
	
	public void onAbilityUsed(ActivatedAbility ability)
	{
		
	}
	
	// CONDITIONS ARE A KIND OF MODIFIER TOO, THEY JUST HAVE A DURATION / END AT BATTLE END unlike abilities and relics
	
	

}
