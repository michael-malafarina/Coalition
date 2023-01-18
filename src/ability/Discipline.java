package ability;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import core.Color;
import unit.PlayerUnit;

public abstract class Discipline 
{
	protected int id;
	protected Image icon;
	protected PlayerUnit owner;
	protected ArrayList<Object> abilities;
	
	protected Discipline(PlayerUnit owner)
	{
		abilities = new ArrayList<Object>();
		this.owner = owner;
	}
	
	abstract public Color getColorMenuDark();
	abstract public Color getColorMenuLight();
	abstract public Color getColorUnitPrimary();
	abstract public Color getColorUnitSecondary();

	public Image getIcon()
	{
		return icon;
	}
	
	public String getName()
	{
		return this.getClass().getSimpleName();
	}
	
	public void addAbility(Class<? extends Ability> ability)
	{
		abilities.add(ability);
	}
	
	public boolean hasBasicAttack()
	{
		return getBasicAttack() != null;
	}
	
	public Ability getBasicAttack()
	{
		for(Object o : abilities)
		{
			Ability a = abilityFactory(o);
//			System.out.println("Checking " + a);


			if(a instanceof ActivatedAbility && ((ActivatedAbility)a).isBasicAttack())
			{
//				System.out.println("Returning " + a);
				return a;
			}
		}
		
		return null;
	}
	
	public Ability getSignatureAbility()
	{
		for(Object o : abilities)
		{
			Ability a = abilityFactory(o);
//			System.out.println("Checking Sig " + a);

			if(a instanceof ActivatedAbility && ((ActivatedAbility)a).isSignatureAbility())
			{			
//				System.out.println("Returning Sig " + a);

				return a;
			}
		}
		
		return null;
	}
	
	public boolean hasAbilityLeft()
	{
		return abilities.size() > 0;
	}
		
	public ArrayList<Ability> getAbilities()
	{
		ArrayList<Ability> actualAbilities = new ArrayList<Ability>();
		
		for(Object o : abilities)
		{
			Ability a = abilityFactory(o);
			actualAbilities.add(a);
			
		}
		
		return actualAbilities;
	}
	
//	public void removeAbility(Class <? extends Ability> clazz)
//	{
//		abilities.remove(clazz);
//	}
	
	public Ability abilityFactory(Object o)
	{
		Class<? extends Ability> clazz = (Class<? extends Ability>) o;

		Ability a = null;
		
		try
		{
			// When I create a new condition, set its duration to the actual duration after modifiers
			a = clazz.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e) 
		{
			e.printStackTrace();
		}	
		
		return a;
	}
	
	public String toString()
	{
		return getClass().getSimpleName();
	}
}
