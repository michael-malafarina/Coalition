package states;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.CampaignManager;
import core.Color;
import core.Main;
import ui.Images;
import ui.Mouse;
import ui.setupParty.CandidatePool;
import ui.setupParty.SetupStartButton;
import ui.sound.AudioManager;
import unit.manager.CoalitionManager;
import unit.manager.EmpireManager;

public class StartingParty extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;

	CandidatePool candidates;
	SetupStartButton start;

	public StartingParty(int id) 
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
		
		candidates = new CandidatePool(5, 3);
		start = new SetupStartButton(candidates, sbg);
		
		
		
		// Determines your opposing faction

	
	
		
//		CoalitionManager.addUnit(u);

		

		AudioManager.update();
		
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		candidates.update();
		start.update();
		//sbg.enterState(Main.COMBAT_ID);
	}
	


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.setColor(new Color(20, 20, 60));
		g.fillRect(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
		
		
		candidates.render(g);
		start.render(g);
		/********* Recruits *************/

//		for(int i = 0; i < candidates.size(); i++)
//		{
//			renderCandidate(candidates.get(i), i, 0);
//		}
//		
		
		/********* Current Coalition *************/
		
//		g.setColor(new Color(20, 20, 20, 90));
//		g.fillRect(0, Main.getScreenHeight() * .80f, Main.getScreenWidth(), Main.getScreenHeight() * .2f);
//		
//		float y = Main.getScreenHeight() * .9f;
//
//		Text.alignCenter();
//		Text.alignMiddle();
//		Text.setFont(Fonts.titleText);
//		Text.draw("Coalition ", Main.getScreenWidth() * .10f, y + Main.getCellSize() / 2 - 10);
//		
//		for(int i = 0; i < CoalitionManager.getSize(); i++)
//		{
//			Unit u = CoalitionManager.getUnits().get(i);
//			float x = i * Main.getScreenWidth() * .05f + Main.getScreenWidth() * .025f + Main.getScreenWidth() * .20f;
//			
//			u.getPortrait().drawCentered(x, y);
//			
//		}
		
		
		g.drawString("Recruit", 50, 50);
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
		
//		if(key >= 2 && key <= candidates.size() + 1)
//		{
//			CoalitionManager.addUnit(candidates.get(key-2));
//			candidates.remove(key-2);
//		//	setCandidates();
//		}
		
	//	System.out.println(CoalitionManager.getSize());
		
		if(CoalitionManager.getSize() == 3)
		{
			sbg.enterState(Main.COMBAT_ID);
		}
	}
	
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}
	
	


}
