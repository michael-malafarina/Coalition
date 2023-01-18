package ability;

import org.newdawn.slick.geom.Point;

import animation.AnimatedSpriteSheet;
import states.combat.CombatManager;
import ui.Images;
import unit.Unit;
import world.Cell;

public abstract class ActivatedAbility extends Ability 
{

	protected boolean instant = false;

	protected int animDelay;		// will be used for ranged attacks / animation delay
	protected int delay;		
	
	protected int charge;	
	protected int energy;
	protected boolean isSupport;		// is the primary target being healed/buffed
	protected boolean basicAttack;
	protected boolean signatureAbility;

	ActivatedAbility()
	{
		super();
	}	
	
	public AnimatedSpriteSheet getTargetImage()
	{
		return Images.cursorSingleTarget;
	}
	public Point getCursorOffset()
	{
		return new Point(0, 0);
	}

	public boolean isBasicAttack()					{	return basicAttack;							}
	public boolean isSignatureAbility()				{	return signatureAbility;					}

	public abstract void use(Cell cell);
	public abstract void use(Unit unit);
	public abstract boolean canUse(Cell cell);
	public abstract boolean canUse(Unit unit);

	public int getNumAttacks()						{	return attacks;			}
	public int getCharge()							{	return charge;			}
	public int getEnergyCost()						{	return energy;			}
	public int getDelay()							{	return delay;			}
	public int getAnimationDelay()					{	return animDelay;		}

	public boolean isActive()						{	return this == CombatManager.getActiveAbility(); }
	public boolean isInstant()						{	return instant;			}
	public boolean canTargetSelf()					{	return canTargetSelf;	} 
	public boolean isSupport()						{	return isSupport;		}

	public boolean isCharged()    	{    	return charge >= energy;    }
	public boolean canUse()			{		return getOwner().canAct() && isCharged();	}

	public void learn()
	{
		super.learn();
		
		resetCharge();
	}
	
	public int getTargetDamageEstimate()
	{
		return getDamage();
	}

	public int getTargetHealingEstimate()
	{
		return getHealing();
	}
	
	public int getTargetGuardEstimate()
	{
		return getGuard();
	}
	
	public int getTargetShredEstimate()
	{
		return getShred();
	}

	public void gainCharge(int amount)		
	{	
		charge += amount;

		if(charge > energy)
		{
			charge = energy;
		}
	}

	public void setCharge(int amount)
	{
		charge = amount;

		if(charge > energy)
		{
			charge = energy;
		}
	}

	public void resetCharge()
	{
		if(hasTag(Tag.READY))
		{
			setCharge(getEnergyCost());
		}
		else if(owner != null)
		{
			setCharge(getOwner().getStartingEnergy());
		}
		else
		{
			clearCharge();
		}
	}

	public void clearCharge()		
	{	
		charge = 0;	
	}


	public void use()
	{
		getOwner().getModifiers().usedAbility(this);	// tell modifier set that ability was used
		charge = 0;	
		animation();
	}	



	




}
