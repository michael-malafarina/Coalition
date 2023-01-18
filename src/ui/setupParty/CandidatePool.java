package ui.setupParty;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import ability.Discipline;
import ability.DisciplineManager;
import ability.disciplines.General;
import core.Main;
import ui.UI;
import unit.Faction;
import unit.Unit;
import unit.manager.CoalitionManager;

public class CandidatePool 
{
	ArrayList<CandidateButton> candidates;
	int maxSelected;
	
	public CandidatePool(int number, int maxSelected)
	{
		candidates = new ArrayList<CandidateButton>();
		setCandidates(number);
		this.maxSelected = maxSelected;
	}
	
	public int size()
	{
		return candidates.size();
	}
	
	public void setCandidates(int number)
	{
		candidates.clear();
			
		for(int i = 0; i < number; i++)
		{
			Faction f = CoalitionManager.getFaction();
			Unit u = f.getNewUnit();
			Discipline d = DisciplineManager.getRandomDiscipline();
			u.addDisciplineFull(d);
			u.addDiscipline(new General());
			
			float y = UI.height(.2f);
			float w = Main.getScreenWidth() / (number);
			float spacing = w * .4f;
			float x = i * w + spacing / 2;
			w = w - spacing;
			float h = UI.height(.3f);
			
			candidates.add(new CandidateButton(u, this, x, y, w, h));
		}
	}
	
	public void update()
	{
		for(CandidateButton c : candidates)
		{
			c.update();
		}
	}
	
	public void render(Graphics g)
	{
		for(CandidateButton c : candidates)
		{
			c.render(g);
		}
	}
	
	public int countSelected()
	{
		int count = 0;
		for(CandidateButton c : candidates)
		{
			if(c.isSelected())
			{
				count++;
			}
		}
		return count;
	}
	
	public ArrayList<Unit> getSelectedCandidates()
	{
		ArrayList<Unit> selectedUnits = new ArrayList<Unit>();
		for(CandidateButton c : candidates)
		{
			if(c.isSelected())
			{
				selectedUnits.add(c.getUnit());
			}
		}
		return selectedUnits;
	}
	
	public boolean hasSpace()
	{
		return countSelected() < maxSelected;
	}
}
