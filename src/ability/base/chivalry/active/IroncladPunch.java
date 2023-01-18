package ability.base.chivalry.active;

import ability.SingleTarget;
import ability.Tag;
import ability.disciplines.Chivalry;
import animation.AnimationCell;
import animation.AnimationManager;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class IroncladPunch extends SingleTarget
{
	public void setup()
	{
		discipline = new Chivalry();
		name = "Ironclad Punch";

		level = 4;
		energy = 4;
		
		damage = 0;
		range = 1;
		
		tags.add(Tag.MELEE);
		tags.add(Tag.BLUNT);
		tags.add(Tag.PHYSICAL);

	}
		
	
	public void use(Unit target)
	{
		super.use(target);
		
		damage(target);
	}
	
	public void sound()
	{
		Sounds.bashHeavy.play();
	}

	public int getDamage()				
	{
		if(getOwner() == null) return getBaseDamage();
		else return getOwner().getModifiers().getModifiedDamage(getOwner().getGuard(), tags);
	}
	
	public void animation(Unit target)
	{
		AnimationManager.add(new AnimationCell(Images.animBlunt, target));
	}
	
	public String getDescription() 
	{
		return "Deal 1 [BLUNT]blunt[] damage for every [GUARD]guard[] you currently have.";
	}
}
