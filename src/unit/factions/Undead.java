package unit.factions;


import unit.factions.undead.Skeleton;
import unit.factions.undead.SkeletonArcher;
import unit.factions.undead.SkeletonKnight;

public class Undead extends EnemyFaction
{
	Undead()
	{
		minions.add(Skeleton.class);
		standards.add(SkeletonArcher.class);
		elites.add(SkeletonKnight.class);

	}



	

	
	
}
