package unit;

import java.util.ArrayList;

import core.Utility;
import unit.factions.EnemyFaction;

public class EnemySet 
{
	private EnemyFaction faction;
	private ArrayList<Unit> units;
	
	private int value;

	
	private float percentMinion;		// Any fight
	private float percentStandard;		// Any fight
	private float percentElite;		// Any fight
	
	private int countMinion;
	private int countStandard;
	private int countElite;
	
	public EnemySet(EnemyFaction faction, int value)
	{
		this.faction = faction;
		this.value = value;
		units = new ArrayList<Unit>();
		
		setDistribution();
		spendRemainingValue();
		setUnits();
	}
	
	private void setDistribution()
	{		
		percentStandard = Utility.random(.2f, .5f);
		percentMinion = Utility.random(0, 1 - percentStandard);
		percentElite = Utility.random(0, 1 - percentStandard - percentElite);

		countElite = (int) (percentElite * value / EnemyType.ELITE.getValue());
		countStandard = (int) (percentStandard * value / EnemyType.STANDARD.getValue());
		countMinion = (int) (percentMinion * value / EnemyType.MINION.getValue());


	}
	
	private void spendRemainingValue()
	{
		// Determine unspent value 
		int unspentValue = value;
		unspentValue -= countElite * EnemyType.ELITE.getValue();
		unspentValue -= countStandard * EnemyType.STANDARD.getValue();
		unspentValue -= countMinion * EnemyType.MINION.getValue();

		while(unspentValue >= EnemyType.ELITE.getValue())
		{
			countElite++;
			unspentValue -= EnemyType.ELITE.getValue();
		}
		while(unspentValue >= EnemyType.STANDARD.getValue())
		{
			countStandard++;
			unspentValue -= EnemyType.STANDARD.getValue();
		}
		while(unspentValue >= 0)
		{
			countMinion++;
			unspentValue -= EnemyType.MINION.getValue();
		}
	}
	
	private void setUnits()
	{
		for(int i = 0; i < countElite; i++)
		{
			units.add(faction.getElite());
		}
		for(int i = 0; i < countStandard; i++)
		{
			units.add(faction.getStandard());
		}
		for(int i = 0; i < countMinion; i++)
		{
			units.add(faction.getMinion());
		}
	}
	
	public ArrayList<Unit> getUnits()
	{
		return units;
		
	}
	
	
	

}
