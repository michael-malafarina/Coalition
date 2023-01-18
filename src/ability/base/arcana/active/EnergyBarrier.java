package ability.base.arcana.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Arcana;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class EnergyBarrier extends SingleTarget
{
	public void setup() 
	{
		discipline = new Arcana();
		name = "Energy Barrier";
		canTargetSelf = true;
		isSupport = true;
		
		level = 3;
		energy = 3;
		
		guard = 2;
		energize = 1;
		range = 4;


		tags.add(Tag.BUFF);
		tags.add(Tag.MAGICAL);
		tags.add(Tag.RANGED);

	}
	
	public void use(Unit target)
	{
		super.use(target);
		guard(target);
		energize(target);
	}
	
	public void sound()
	{
		Sounds.teleport.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animShield, target));
	}
	
	public String getDescription() 
	{
		//System.out.println("EnergyBarrier is supprot? " + isSupport);
		return "Target gains " + getGuardText() + " guard and " + getEnergizeText() + " energy.";
	}
}
