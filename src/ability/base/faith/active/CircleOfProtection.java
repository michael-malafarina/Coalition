package ability.base.faith.active;

import ability.SelfTarget;
import ability.Tag;
import ability.disciplines.Faith;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class CircleOfProtection extends SelfTarget
{
	public void setup() 
	{
		discipline = new Faith();
		name = "Circle of Protection";
		isSupport = true;

		level = 4;
		energy = 5;
		
		guard = 2;
		range = 0;
		rangeSecondary = 3;

		tags.add(Tag.MAGICAL);

	}
	
	public void use()
	{
		
		
		guard();
		
		for(Unit u : getOwner().getAlliesWithin(getRangeSecondary()))
		{
			addSecondaryTarget(u);
			guard(u);
		}
		
		super.use();

		clearTargets();
		
	}
	
	public void sound()
	{
		Sounds.heal.play();
	}

	public void animation()
	{
		AnimationManager.add(new AnimationCell(Images.animShield, self()));
		
		for(Unit u : secondaryTargets)
		{
			AnimationManager.add(new AnimationCell(Images.animShield, u));
		}
	}
	
	public String getDescription() 
	{
		return "You and each ally within " + getRangeSecondary() + " gain " + getGuardText() + " [GUARD]guard[]";
	}
}
