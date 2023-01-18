package ability.base.chivalry.active;

import ability.SelfTarget;
import ability.Tag;
import ability.disciplines.Chivalry;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;

public class Fortify extends SelfTarget
{
	public void setup()
	{
		discipline = new Chivalry();
		name = "Fortify";
		isSupport = true;

		level = 3;
		energy = 4;
		guard = 4;		
		tags.add(Tag.BUFF);
	}
		
	public void use()
	{
		super.use();
		guard();
	}
	
	public void sound()
	{
		Sounds.armorUp.play();
	}

	public void animation()
	{
		AnimationManager.add(new AnimationCell(Images.animShield, getOwner()));
	}
	
	public String getDescription() 
	{
		return "Gain [GUARD]" + getGuard() + "[] [GUARD]guard[].";
	}
}
