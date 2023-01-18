package ability.unused;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Fire;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class ArcLightning extends SingleTarget
{
	public void setup() 
	{
		discipline = new Fire();
		name = "Arc Lightning";
		
		level = 3;
		damage = 2;
		range = 5;
		energy = 4;
		
		tags.add(Tag.LIGHTNING);
		tags.add(Tag.MAGICAL);


	}
	
	public void use(Unit target)
	{
		super.use(target);
		damage(target);
		if(target.getNearestAlly(3) != null)
		{
			damage(target.getNearestAlly(3));
		}
	}
	
	public void sound()
	{
		Sounds.fireball.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animFlame, target));
	//	AnimationManager.add(new AnimationCell(Images.animFlame, target.getNearestAlly(3)));  crashes null

	}
	
	public String getDescription() 
	{
		return "Deals " + getDamageText() + " [FIRE]lightning[] damage to the target and another nearby";
	}
}
