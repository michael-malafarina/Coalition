package ability.unused;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Chivalry;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class PhalanxAttack extends SingleTarget
{
	public void setup()
	{
		discipline = new Chivalry();
		name = "Guardian's Strike";
		basicAttack = true;
		damage = 1;
		range = 1;
		guard = 1;
		tags.add(Tag.MELEE);
		tags.add(Tag.SHARP);
		tags.add(Tag.PHYSICAL);

	}
	
	
	public void use(Unit target)
	{
		super.use(target);
		damage(target);
		guard();
		animationGuard(getOwner());
		
		if(getOwner().hasAllyWest())
		{
			getOwner().getUnitWest().gainGuard(getGuard());
			animationGuard(getOwner().getUnitWest());
		}
		if(getOwner().hasAllyEast())
		{
			getOwner().getUnitEast().gainGuard(getGuard());
			animationGuard(getOwner().getUnitEast());
		}
		if(getOwner().hasAllyNorth())
		{
			getOwner().getUnitNorth().gainGuard(getGuard());
			animationGuard(getOwner().getUnitNorth());
		}
		if(getOwner().hasAllySouth())
		{
			getOwner().getUnitSouth().gainGuard(getGuard());
			animationGuard(getOwner().getUnitSouth());
		}
	}
	
	
	
	public void sound()
	{
		Sounds.slash.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animSlash, target));
	}
	
	public void animationGuard(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animShield, target));
	}

	public String getDescription() 
	{
		return "Deals "+ getDamageText() +" [SHARP]sharp []damage, then you and each adjacent ally gain " + getGuardText() + "[GUARD] guard[]";

	}
}
