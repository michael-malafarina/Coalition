package ability;

import java.util.ArrayList;

import ability.disciplines.Fire;
import ability.disciplines.Growth;
import ability.disciplines.Shadow;
import ability.disciplines.Time;
import core.Utility;

public class DisciplineManager 
{
	private static ArrayList<Object> disciplines;
	
	public static void init()
	{
		disciplines = new ArrayList<Object>();
		
		disciplines.add(Fire.class);
		disciplines.add(Time.class);
		disciplines.add(Shadow.class);
		disciplines.add(Growth.class);


	}
	public static Discipline getRandomDiscipline()
	{
		return disciplineFactory(disciplines.get(Utility.random(disciplines.size())));
	}
	
	public static Discipline disciplineFactory(Object o)
	{
		Class<? extends Discipline> clazz = (Class<? extends Discipline>) o;

		Discipline a = null;
		
		try
		{
			// When I create a new condition, set its duration to the actual duration after modifiers
			a = clazz.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e) 
		{
			e.printStackTrace();
		}	
		
		return a;
	}
}
