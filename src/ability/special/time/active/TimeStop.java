package ability.special.time.active;

import ability.SelfTarget;
import ability.Tag;
import ability.disciplines.Time;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class TimeStop extends SelfTarget
{
	public void setup() 
	{
		discipline = new Time();
		name = "Time Stop";
		isSupport = true;

		level = 6;
		energy = 7;

		advance = 250;

		tags.add(Tag.MAGICAL);
		tags.add(Tag.RANGED);

	}
	
	public void use()
	{	
		super.use();
		advance();
	}
	
	public void sound()
	{
		Sounds.haste.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animTeleport, target));
	}
	
	public String getDescription() 
	{
		return "You advance your initiative by " + getAdvanceText() + ", acting twice immediately.";
	}

	public void animation() 
	{
		AnimationManager.add(new AnimationCell(Images.animFlame, getOwner()));
	}
}
