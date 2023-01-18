package ability.base.general;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.General;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class SharpRanged extends SingleTarget
{
	public SharpRanged(int damage, int range)
	{
		super();
		this.damage = damage;
		this.range = range;
	}
	
	public void setup()
	{
		discipline = new General();
		name = "Attack";
		basicAttack = true;

		level = 0;
		energy = 0;
		
		damage = 2;
		range = 1;
		
		tags.add(Tag.RANGED);
		tags.add(Tag.SHARP);
		tags.add(Tag.PHYSICAL);

	}
	
	
	public void use(Unit target)
	{
		super.use(target);
		damage(target);
		applyConditions(target);
	}
	
	public void sound()
	{
		Sounds.arrow.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animSlash, target));
	}

	public String getDescription() 
	{
		return "Deals " + getDamageText() + " [SHARP]sharp[] damage.";
	}
}
