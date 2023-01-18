package ability.base.chivalry.active;

import java.util.ArrayList;

import ability.SelfTarget;
import ability.Tag;
import ability.disciplines.Chivalry;
import animation.AnimationCell;
import animation.AnimationManager;
import modifier.conditions.Condition;
import ui.Images;
import ui.sound.Sounds;

public class SteelYourself extends SelfTarget
{
	public void setup()
	{
		discipline = new Chivalry();
		name = "Steel Yourself";
		isSupport = true;
		
		level = 2;
		guard = 1;
		energy = 3;
		tags.add(Tag.BUFF);
	}
		
	public void use()
	{
		super.use();
		
		ArrayList<Condition> conditions = getOwner().getModifiers().getConditions();
		
		getOwner().gainGuard(conditions.size() * getGuard());
		getOwner().getModifiers().removeAllConditions(Tag.DEBUFF);
	}
	
	public void sound()
	{
		Sounds.heal.play();
	}

	public void animation()
	{
		AnimationManager.add(new AnimationCell(Images.animCleanse, getOwner()));
	}
	
	public String getDescription() 
	{
		return "Cleanse all debuffs.  For each debuff removed, [GUARD]gain[] " + getGuardText() + " guard.";
	}
}
