package states.combat;

import java.util.ArrayList;
import java.util.Collections;

import unit.Unit;

public class InitiativeQueue 
{
	public final static int MAX_INITIATIVE = 100;

	ArrayList<Unit> units;
//	private Unit nextActor;
	private Unit highest;

	public void begin(ArrayList<Unit> units)
	{
		this.units = units;
	}

	public void tick()
	{
		
		if(units == null || units.size() == 0)
		{
			return;
		}
		
		for(Unit u : units)
		{
			u.tickInitiative();
			
//			System.out.println(u.getInitiative() + ": " + u);
			
			if(u.isDead())
			{
				u.resetInitiative();
			}

		}
		//System.out.println("--");

		Collections.sort(units);
		Collections.reverse(units);


		highest = units.get(0);
		
	
		if(highest.getInitiative() >= MAX_INITIATIVE)
		{
			//System.out.println(highest.getInitiative() + ": " + highest);

			
			CombatManager.setActiveUnit(highest);
			highest.loseInitiative(100);
			//nextActor = highest;
		}
		else
		{
			tick();
		}
		
		


	}
	
//	public Unit getNextActor()
//	{
//		return nextActor;
//	}


}
