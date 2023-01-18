package ability.special.fire.active;

import java.util.ArrayList;

import ability.AreaTarget;
import ability.EffectShape;
import ability.Tag;
import ability.disciplines.Fire;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import world.Cell;

public class Pyroclasm extends AreaTarget
{
	public void setup() 
	{
		discipline = new Fire();
		name = "Pyroclasm";

		level = 5;
		damage = 3;
		range = 6;
		energy = 5;
		
		size = 3;
		
		shape = EffectShape.BURST;

		tags.add(Tag.FIRE);
		tags.add(Tag.MAGICAL);

	}
	
	public void use(ArrayList<Cell> targets)
	{
		super.use(targets);
		damage(targets);
	}
	
	public void sound()
	{
		Sounds.fireball.play();
	}

	public void animation(Cell target)
	{
		AnimationManager.add(new AnimationCell(Images.animFlame, target));
	}
	
	public String getDescription() 
	{
		return "Deals " + getDamageText() + " [FIRE]fire[] damage";
	}



}
