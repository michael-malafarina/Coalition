package ability.base.chivalry.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Chivalry;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class GuardedStrike extends SingleTarget
{
	public void setup()
	{
		discipline = new Chivalry();
		name = "Guarded Strike";
		basicAttack = true;
		
		level = 0;
		damage = 2;
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
		
	}
	
	public int getTargetGuardEstimate()
	{
		return 0;
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
		return "Deal "+ getDamageText() +" [SHARP]sharp []damage and " + getGuardText() + "[GUARD] guard[].";

	}
}
