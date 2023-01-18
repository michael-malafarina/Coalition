package core;

public class CampaignManager
{
	private static int stage;
	
	public static void init()
	{
		stage = 1;
	}
	
	public static int getStage()
	{
		return stage;
	}
	
	public static void advanceStage()
	{
		stage++;
	}
	
	public static int getCombatDifficulty()
	{
//		int difficulty = (stage + 3) * 3;
//
//
//		return difficulty;
		
		
		return 0;
	}
}
