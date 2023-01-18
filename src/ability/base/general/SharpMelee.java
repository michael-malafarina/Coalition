package ability.base.general;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.General;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class SharpMelee extends SingleTarget
{
	public SharpMelee(int damage)
	{
		super();
		this.damage = damage;
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
		
		tags.add(Tag.MELEE);
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
		Sounds.slash.play();
	}

	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animSlash, target));
	}

	public String getDescription() 
	{
		return "Deals " + getDamageText() + " [SHARP]sharp[] damage.  Apply "
				+ " [BLEED]bleed[] for " + getDurationText() + " " + getTurnText();
	}
}
