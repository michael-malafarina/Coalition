package ability.base.chivalry.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Chivalry;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class DrivingAssault extends SingleTarget
{
	public void setup()
	{
		discipline = new Chivalry();
		name = "Driving Assault";
		signatureAbility = true;

		level = 1;
		damage = 2;
		range = 1;
		energy = 2;
		push = 1;
		pushFollow = true;
		
		tags.add(Tag.MELEE);
		tags.add(Tag.BLUNT);
		tags.add(Tag.PHYSICAL);

	}
		
	
	public void use(Unit target)
	{
		super.use(target);
		
		damage(target);
		push(target);
	}
	
	public void sound()
	{
		Sounds.bashHeavy.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animSlash, target));
	}
	
	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [SHARP]sharp[] damage.  Push " + getPushText() + " and follow.";
	}
}
