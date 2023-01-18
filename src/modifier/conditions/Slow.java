package modifier.conditions;

import ability.Tag;
import ui.Images;

public class Slow extends Condition
{
	public Slow() 
	{
		name = "Slow";
		icon = Images.iconSlow;
		speedScalar = 0.7f;
		tags.add(Tag.SLOW);
		tags.add(Tag.BUFF);
	}
		
	public void endTurn()
	{
		tick();
	}
	
	public String getDescription() 
	{
		return "-30% Speed";
	}

	public void sound() 
	{
	//	Sounds.grunt.play();
	}

	public void animation() 
	{
		//AnimationManager.add(new AnimationCell(Images.animSlash, getOwner()));
	}



}
