package states;


import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ability.Discipline;
import ability.DisciplineManager;
import ability.disciplines.General;
import core.CampaignManager;
import core.Color;
import core.Main;
import ui.Fonts;
import ui.Images;
import ui.Mouse;
import ui.Text;
import ui.sound.AudioManager;
import unit.Faction;
import unit.Unit;
import unit.manager.CoalitionManager;
import unit.manager.EmpireManager;

public class Recruit extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;


	ArrayList<Unit> candidates;

	public Recruit(int id) 
	{
		this.id = id;
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{		
		this.sbg = sbg;

		CampaignManager.init();
		EmpireManager.init();
		CoalitionManager.init();
		
		candidates = new ArrayList<Unit>();

		
		
		
		// Determines your opposing faction

	
		setCandidates();
	
		
//		CoalitionManager.addUnit(u);

		

		AudioManager.update();
		
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		//sbg.enterState(Main.COMBAT_ID);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.setColor(new Color(20, 20, 60));
		g.fillRect(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
		
		/********* Recruits *************/

		for(int i = 0; i < candidates.size(); i++)
		{
			Unit u = candidates.get(i);
			int x = i * Main.getScreenWidth() / 3 + Main.getScreenWidth() / 7;
			int y = 200;
			
			u.getPortrait().drawCentered(x, y + 300, 3);
			
			Text.alignCenter();
			Text.alignMiddle();
			Text.setFont(Fonts.titleText);
			Text.draw(""+(i+1),x, y);
			Text.draw(u.getType(), x, y + 80);
			
//			Text.draw(""+u.getPrimaryDiscipline(), x, y + UI.height(.5f));
//			Text.draw(""+u.getSecondaryDiscipline(), x, y + UI.height(.55f));

		}
		
		
		/********* Current Coalition *************/
		
		g.setColor(new Color(20, 20, 20, 90));
		g.fillRect(0, Main.getScreenHeight() * .80f, Main.getScreenWidth(), Main.getScreenHeight() * .2f);
		
		float y = Main.getScreenHeight() * .9f;

		Text.alignCenter();
		Text.alignMiddle();
		Text.setFont(Fonts.titleText);
		Text.draw("Coalition ", Main.getScreenWidth() * .10f, y + Main.getCellSize() / 2 - 10);
		
		for(int i = 0; i < CoalitionManager.getSize(); i++)
		{
			Unit u = CoalitionManager.getUnits().get(i);
			float x = i * Main.getScreenWidth() * .05f + Main.getScreenWidth() * .025f + Main.getScreenWidth() * .20f;
			
			u.getPortrait().drawCentered(x, y);
			
		}
		
		
		g.drawString("Recruit", 50, 50);
	}
	
	public void setCandidates()
	{
		candidates.clear();
		for(int i = 0; i < 3; i++)
		{
			Faction f = CoalitionManager.getFaction();
			Unit u = f.getNewUnit();
			Discipline d = DisciplineManager.getRandomDiscipline();
			u.addDisciplineFull(d);
			u.addDiscipline(new General());
			candidates.add(u);
		}
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		Mouse.setMouseCursor(Images.cursorPointer);

		// This code happens when you enter a gameState.  
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{

	}

	public void keyPressed(int key, char c)
	{

		//System.out.println(key + " pressed");
		
		if(key >= 2 && key <= 4)
		{
			CoalitionManager.addUnit(candidates.get(key-2));
			setCandidates();
		}
		
	//	System.out.println(CoalitionManager.getSize());
		
		if(CoalitionManager.getSize() == 4)
		{
			sbg.enterState(Main.COMBAT_ID);
		}
	}
	
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}
	
	


}
