package ability.special.fire.active;

import java.util.ArrayList;

import ability.MultiTarget;
import ability.Tag;
import ability.disciplines.Fire;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class ScorchingRay extends MultiTarget
{
	public void setup() 
	{
		discipline = new Fire();
		name = "Scorching Ray";
		
		level = 2;
		damage = 1;
		range = 4;
		energy = 3;
		numTargets = 3;

		tags.add(Tag.FIRE);
		tags.add(Tag.MAGICAL);

	}
	
	public void use(ArrayList<Unit> targets)
	{
		super.use(targets);
		for(Unit u : targets)
		{
			damage(u);
		}
	}
	
	public void sound()
	{
		Sounds.fireball.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animFlame, target));
	}
	
	public String getDescription() 
	{
		return "Deals " + getDamageText() + " [FIRE]fire[] damage to " + numTargets + " targets";
	}
}
