package modifier;

import java.util.ArrayList;

import ability.Ability;
import ability.ActivatedAbility;
import ability.Tag;
import modifier.conditions.Condition;
import unit.Unit;
import unit.manager.UnitManager;

public class ModifierSet 
{
	// What kinds of triggers do we have:
	
	// Start of combat --> self only
	// Start of turn --> self and aura  (friendly / hostile)
	// End of turn --> self and aura (friendly / hostile)
	// I move next to a target self and aura  (friendly / hostile)

	// Use ability with various sub-types (attack, etc?)
	// Always on
	
	private ArrayList<Modifier> modifiers;
	private Unit owner;
	
	public ModifierSet(Unit owner)
	{
		this.owner = owner;
		modifiers = new ArrayList<Modifier>();
	}
	
	
	public boolean isOwner()						{	return owner != null;				}
	public Unit getOwner()							{	return owner;						}
	public ArrayList<Modifier> getModifiers()		{	return modifiers;					}
	public ArrayList<Modifier> getVisibleModifiers()	
	{	
		ArrayList<Modifier> visible = new ArrayList<Modifier>();
		for(Modifier m : modifiers)
		{
			if(!m.isHidden())
			{
				visible.add(m);
			}
		}
		return visible;		
	}

	
	public boolean modifiesDamage(int damage, ArrayList<Tag> tags)
	{
		for(Modifier m : modifiers)
		{
			if(m.modifiesDamage(tags))
			{
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Condition> getConditions()
	{
		ArrayList<Condition> conditions = new ArrayList<Condition>();
		
		for(Modifier m : modifiers)
		{
			if(m instanceof Condition)
			{
				conditions.add((Condition)m);
			}
		}
		
		return conditions;
	}
		
	public boolean hasDebuff()
	{
		return getDebuff() != null;
	}
	
	public Condition getDebuff()
	{
		for(Condition c : getConditions())
		{
			if(c.hasTag(Tag.DEBUFF))
			{
				return c;
			}
		}
		return null;
	}
	
	public boolean hasCondition(Class<? extends Condition> clazz)
	{
		return getCondition(clazz) != null;
	}
	
	public Condition getCondition(Class<? extends Condition> clazz)
	{
		for(Condition c : getConditions())
		{
			if(clazz.isInstance(c))
			{
				return c;
			}
		}
		return null;
	}
	

	
	/***************** Modified Attributes ************************/
	
	public int getModifiedDamage(int baseDamage, ArrayList<Tag> tags)
	{
		int modifiedDamage = baseDamage;

		for(Modifier m : modifiers)
		{
			if(m.modifiesDamage(tags))
			{
				modifiedDamage += m.getDamageModifier(tags);
			}
		}
		
		return modifiedDamage;
	}
	
	public int getModifiedDamageRecieved(int baseDamage, ArrayList<Tag> tags)
	{
		int modifiedDamage = baseDamage;

		for(Modifier m : modifiers)
		{
			if(m.modifiesDamageRecieved(tags))
			{
				modifiedDamage += m.getDamageRecievedModifier(tags);
			}
		}
		
		return modifiedDamage;
	}
	
	public int getModifiedHealing(int baseHealing, ArrayList<Tag> tags)
	{
		int modifiedHealing = baseHealing;
		
		for(Modifier m : modifiers)
		{
			if(m.hasHealingBonus())
			{
				modifiedHealing += m.getHealingBonus();
			}
		}
		
		return modifiedHealing;
	}		

	public int getModifiedHealingRecieved(int baseHealing, ArrayList<Tag> tags)
	{
		int modifiedHealing = baseHealing;
		
		for(Modifier m : modifiers)
		{
			if(m.hasHealingRecievedBonus())
			{
				modifiedHealing += m.getHealingRecievedBonus();
			}
		}
		
		return modifiedHealing;
	}	
	
	public int getModifiedSpeed(int baseSpeed, ArrayList<Tag> tags)
	{
		int modifiedSpeed = baseSpeed;
		
		for(Modifier m : modifiers)
		{
			if(m.hasSpeedScalar())
			{
				modifiedSpeed *= m.getSpeedScalar();
			}
			if(m.hasSpeedBonus())
			{
				modifiedSpeed += m.getSpeedBonus(); 
			}
		}
		
		return modifiedSpeed;
	}	
	
	public int getModifiedDuration(int baseDuration, ArrayList<Tag> tags)
	{
		int modifiedDuration = baseDuration;
		
		for(Modifier m : modifiers)
		{
			if(m.hasDurationScalar())
			{
				modifiedDuration *= m.getDurationScalar();
			}
		}
		
		return modifiedDuration;
	}		
	
	public int getModifiedGuard(int baseGuard, ArrayList<Tag> tags)
	{
		int modifiedGuard = baseGuard;
		
		for(Modifier m : modifiers)
		{
			if(m.hasGuardBonus())
			{
				modifiedGuard += baseGuard + m.getGuardBonus();
			}
		}
		
		return modifiedGuard;
	}	
	
	
	public int getModifiedShred(int baseShred, ArrayList<Tag> tags)
	{
		int modifiedShred = baseShred;
		
		for(Modifier m : modifiers)
		{
			if(m.hasShredBonus())
			{
				modifiedShred += baseShred + m.getShredBonus();
			}
		}
		
		return modifiedShred;
	}		
		
	public int getModifiedPush(int basePush, ArrayList<Tag> tags)
	{
		int modifiedPush = basePush;
		
		for(Modifier m : modifiers)
		{
			if(m.hasPushBonus())
			{
				modifiedPush += basePush + m.getPushBonus();
			}
		}
		
		return modifiedPush;
	}		
		
	/*************************** MUTATORS ************************/

	
	public void add(Ability a)					{	modifiers.add(a);					}

	public void add(Condition c)				
	{	
		for(Modifier m : modifiers)
		{
			if(m instanceof Condition && c.getClass().isInstance(m))
			{
				((Condition) m).addDuration(c.getDuration());
				return;
			}
		}
		
		modifiers.add(c);	
	}

	
	public Condition removeDebuff()
	{
		Condition c = null;
		
		//System.out.println("removing debuff on" + this.getOwner());
		
		if(hasDebuff())
		{
			c = getDebuff();
			
			//System.out.println("debuff is " +c);

			modifiers.remove(c);
			
			//System.out.println("ti be removed ");

			getOwner().showHealthbar();
		}
		
		return c;
	}
	
	public void removeAllConditions()
	{
		for(int i = 0; i < modifiers.size(); i++) 
		{
			if(modifiers.get(i) instanceof Condition)
			{
				modifiers.remove(i);
				i--;
			}
		}
	}
	
	public void removeAllConditions(Tag t)
	{
		for(int i = 0; i < modifiers.size(); i++) 
		{
			if(modifiers.get(i) instanceof Condition && modifiers.get(i).hasTag(t))
			{
				modifiers.remove(i);
				i--;
			}
		}
	}
	
	
	public void update()
	{
		
	}
	
	public void startCombat()
	{
		for(Unit u : UnitManager.getAllUnits())
		{
			if(getOwner().isSelf(u))
			{
				triggerStartCombat();
			}
		}
	}
	
	public void damageTaken()
	{
		triggerOnDamageTaken();
	}
	
	public void healingRecieved()
	{
		triggerOnHealingRecieved();
	}
	
	public void usedAbility(ActivatedAbility ability)
	{
		triggerAbilityUsed(ability);
	}
	
	public void startTurn()
	{
		for(Unit u : UnitManager.getAllUnits())
		{
			if(getOwner().isSelf(u))
			{
				triggerStartTurn();
			}
			else 
			{
				triggerStartTurnAura(u);
			}
		}
		
	}
	
	public void endTurn()
	{
		for(Unit u : UnitManager.getAllUnits())
		{
			if(getOwner().isSelf(u))
			{
				triggerEndTurn();
			}
			else 
			{
				triggerEndTurnAura(u);
			}
		}
		
		clearExpired();
	}
	
	
	private void triggerStartCombat()
	{
		for(int i = 0; i < modifiers.size(); i++)
		{
			Modifier m = modifiers.get(i);
			
			m.startCombat();
			
		}
	}
	
	private void triggerOnDamageTaken()
	{
		for(int i = 0; i < modifiers.size(); i++)
		{
			Modifier m = modifiers.get(i);
			
			m.onDamageTaken();
		}
	}
	
	private void triggerOnHealingRecieved()
	{
		for(int i = 0; i < modifiers.size(); i++)
		{
			Modifier m = modifiers.get(i);
			
			m.onHealingRecieved();

		}
	}
	
	private void triggerStartTurn()
	{
		for(int i = 0; i < modifiers.size(); i++)
		{
			Modifier m = modifiers.get(i);
			
			m.startTurn();
		}
	}
	
	private void triggerAbilityUsed(ActivatedAbility ability)
	{
		for(int i = 0; i < modifiers.size(); i++)
		{
			Modifier m = modifiers.get(i);
			
			m.onAbilityUsed(ability);

		}
	}
	
	// Apply other unit's aura effects on me
	private void triggerStartTurnAura(Unit other)
	{
		for(Modifier o : other.getModifiers().modifiers)
		{
			o.startTurnAura(getOwner());
		}	
	}
	
	// Apply effects on myself
	private void triggerEndTurn()
	{
		for(int i = 0; i < modifiers.size(); i++)
		{
			Modifier m = modifiers.get(i);
			
			m.endTurn();
		}
	}
	
	// Apply other unit's aura effects on me
	private void triggerEndTurnAura(Unit other)
	{
		for(Modifier o : other.getModifiers().modifiers)
		{
			o.endTurnAura(getOwner());
		}	
	}
	
	public void clearExpired()
	{
		for(int i = 0; i < modifiers.size(); i++)
		{
			Modifier m = modifiers.get(i);
						
			if(m.isExpired())
			{
				modifiers.remove(i);
				i--;
			}
		}
	}
	
}
