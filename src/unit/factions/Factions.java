package unit.factions;

import java.util.ArrayList;

import core.Utility;

public class Factions 
{
	public static ArrayList<EnemyFaction> factions;

	public static Undead undead;
	public static Hero hero;
	
	private static EnemyFaction enemyFaction;
	
	public static void init()
	{
		undead = new Undead();
		hero = new Hero();
		
		factions = new ArrayList<EnemyFaction>();
		factions.add(undead);

		enemyFaction = factions.get(Utility.random(0, factions.size() - 1));
		
	}
	
	public static EnemyFaction getEnemyFaction()
	{
		return enemyFaction;
	}
	
	
}
