package unit;

public enum EnemyType 
{
	MINION, STANDARD, ELITE, BOSS;
	
	
	public int getValue()
	{
		return getValue(this);
	}
	
	
	public int getValue(EnemyType type)
	{
		switch(type)
		{
			case MINION: return 1;
			case STANDARD: return 2;
			case ELITE: return 4;
			case BOSS: return 8;
			default: return 0;			
		}
	}
}
