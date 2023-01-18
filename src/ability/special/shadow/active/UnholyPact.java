package ability.special.shadow.active;

import ability.SelfTarget;
import ability.Tag;
import ability.disciplines.Shadow;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;

public class UnholyPact extends SelfTarget
{
	public void setup() 
	{
		discipline = new Shadow();
		name = "Dark Pact";
		level = 3;
		energy = 4;

		energize = 2;
		
		tags.add(Tag.SHADOW);
		tags.add(Tag.MAGICAL);

	}
	
	public void use()
	{
		super.use();
		energize();
		getOwner().loseHealth(1);
	}
	
	public void sound()
	{
		Sounds.magicImpact.play();
	}

	public void animation()
	{
		AnimationManager.add(new AnimationCell(Images.animDark, getOwner()));
	}
	
	public String getDescription() 
	{
		return "Gain " + getEnergizeText() + " energy. You lose 1 health.";

//		return "Deals " + getDamageText() + " [SHADOW]shadow[] damage and applies blind " + getDurationText() + ". You lose 1 health.";
	}
}
