package ability;

import java.util.ArrayList;
import java.util.Collections;

import ability.base.general.EndTurn;
import unit.Unit;

public class AbilitySet
{
	Unit owner;
	
	ArrayList<Ability> abilities;
	ArrayList<ActivatedAbility> activatedAbilities;
	ArrayList<PassiveAbility> passiveAbilities;

	public AbilitySet(Unit owner)
	{
		this.owner = owner;
		abilities = new ArrayList<Ability>();
		activatedAbilities = new ArrayList<ActivatedAbility>();
		passiveAbilities = new ArrayList<PassiveAbility>();
	}
	
	public ArrayList<Ability> getAbilities()					{		return abilities;			}
	
	public ArrayList<ActivatedAbility> getAllActivatedAbilities()	
	{	
		ArrayList<ActivatedAbility> realAbilities = new ArrayList<ActivatedAbility>();
		for(ActivatedAbility a: activatedAbilities)
		{
			if(!(a instanceof EndTurn))
			{
				realAbilities.add(a);
			}
		}
		return realAbilities;
	}
	public ArrayList<PassiveAbility> getAllPassiveAbilities()		{		return passiveAbilities;	}
	
	public ArrayList<ActivatedAbility> getActivatedAbilities()	
	{		
		ArrayList<ActivatedAbility> learnedAbilities = new ArrayList<ActivatedAbility>();
		for(ActivatedAbility a: activatedAbilities)
		{
			if(a.isLearned())
			{
				learnedAbilities.add(a);
			}
		}
		return learnedAbilities;
	}

	public ArrayList<PassiveAbility> getPassiveAbilities()	
	{		
		ArrayList<PassiveAbility> learnedAbilities = new ArrayList<PassiveAbility>();
		for(PassiveAbility a: passiveAbilities)
		{
			if(a.isLearned())
			{
				learnedAbilities.add(a);
			}
		}
		return learnedAbilities;
	}

	public boolean hasActivatedAbility(int id)					{	return id <= activatedAbilities.size();			}
	public ActivatedAbility getActivatedAbility(int id)			{	return activatedAbilities.get(id - 1); 			}

	
	public boolean hasAbility(Class <? extends Ability> clazz)	{	return getAbility(clazz) != null;	}
	
	public Ability getAbility(Class <? extends Ability> clazz)			
	{
		for(Ability a : abilities)
		{
			if(clazz.isInstance(a))
			{
				return a;
			}
		}

		return null;
	}	
	
	public void gainCharge(int amount)
	{
		for(ActivatedAbility a : activatedAbilities)
		{
			a.gainCharge(amount);
		}
	}
	
	public void setCharge(int amount)
	{
		for(ActivatedAbility a : activatedAbilities)
		{
			a.setCharge(amount);
		}
	}
	
	public void resetCharge()
	{
		for(ActivatedAbility a : activatedAbilities)
		{
			a.resetCharge();
		}
	}
	
	 	
	public void clearCharge(ActivatedAbility a)
	{
		a.clearCharge();
	}
	
	public void addAbility(Ability ability)
	{			
		
		
		if(ability instanceof ActivatedAbility)
		{
			int index = activatedAbilities.size();
						
			if(hasAbility(EndTurn.class))
			{
				index -= 1;
				
				((ActivatedAbility)getAbility(EndTurn.class)).setIndex(index + 1);
			}
						
			ActivatedAbility a = (ActivatedAbility) ability;
			a.setIndex(index);
			activatedAbilities.add(index, a);
			abilities.add(index, ability);
			a.setOwner(owner);
			a.resetCharge();
			
			
			if(a.isSignatureAbility() || a.isBasicAttack() || a instanceof EndTurn)
			{
				a.learn();
			}
		}
		else
		{
			
			PassiveAbility a = (PassiveAbility) ability;
			passiveAbilities.add(a);
			abilities.add(a);
			
			a.setOwner(owner);

		}
		

//		System.out.println("it still is..." + a.getCharge());

		//abilities.add(ability);

		sortAbilities();
	}
	

	
	public void sortAbilities()
	{
		Collections.sort(abilities);
		Collections.sort(activatedAbilities);
		Collections.sort(passiveAbilities);

		for(int i = 0; i < activatedAbilities.size(); i++)
		{
			Ability a = activatedAbilities.get(i);
			a.setIndex(i);
		}
		
		for(int i = 0; i < passiveAbilities.size(); i++)
		{
			Ability a= passiveAbilities.get(i);
			a.setIndex(i);
		}
		
	}
	
	
}
