package modifier.conditions;

import ability.Tag;
import ui.Images;

public class Haste extends Condition
{
	public Haste() 
	{
		name = "Haste";
		icon = Images.iconHaste;
		speedScalar = 1.3f;
		tags.add(Tag.HASTE);
		tags.add(Tag.BUFF);
	}
		
	public void endTurn()
	{
		tick();
	}
	
	public String getDescription() 
	{
		return "+30% speed";
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
