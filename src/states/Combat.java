package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import animation.AnimationManager;
import core.Main;
import core.Settings;
import states.combat.CombatHUD;
import states.combat.CombatManager;
import ui.Camera;
import ui.UI;
import ui.sound.AudioManager;
import unit.manager.CoalitionManager;
import unit.manager.EmpireManager;
import world.Map;

public class Combat extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;
	
	
	public Combat(int id) 
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
		gc.setShowFPS(false);

		
		Camera.init();
		Map.init();	
		CombatManager.init();	
		CombatHUD.init();
		Log.setVerbose(false);

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	

		Camera.update();
		Map.update();
		CombatManager.update();
		

		

		CombatHUD.update();
		AudioManager.update();
		AnimationManager.update();
		UI.update();
		

		if(CoalitionManager.isDefeated())
		{
			sbg.enterState(Main.LOSE_ID);
		}

		if(EmpireManager.isDefeated())
		{
			sbg.enterState(Main.WIN_ID);
		}

		
	}
	


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		//gc.setMouseGrabbed(true);
		
	//	System.out.println(UIManager.hasFocus());
		
		Camera.enable(g);
		

		Map.render(g);
		CombatManager.render(g);
		//Mouse.renderCursor(g);
		AnimationManager.render(g);

		Camera.disable(g);
		CombatHUD.render(g);
		
		UI.render(g);
		
	}
	

	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		if(EmpireManager.isDefeated())
		{
			CombatManager.begin();
		}
	}
	
	
	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		AnimationManager.reset();
	}

	public void keyPressed(int key, char c)
	{		
		if(key == Input.KEY_SPACE)
		{
			sbg.enterState(Main.WIN_ID);
			EmpireManager.clear();
		}
		
		if(key == Input.KEY_ESCAPE || key == Input.KEY_C)
		{
			sbg.enterState(Main.PARTY_ID);
		}
		
		if(key == Input.KEY_G)
		{
			Settings.showGrid = !Settings.showGrid;
		}
		
		if(key == Input.KEY_B)
		{
			Settings.debugMode = !Settings.debugMode;
		}
		
		CombatManager.keyPressed(key, c);
	}
		
	public void mousePressed(int button, int x, int y)
	{
		CombatManager.mousePressed(button, x, y);
	}
		
	public void mouseMoved(int oldX, int oldY, int newX, int newY) 
	{
	//	Camera.mouseMoved(oldX, oldY, newX, newY);
	}

	public void mouseDragged(int oldX, int oldY, int newX, int newY) 
	{
	//	Camera.mouseDragged(oldX, oldY, newX, newY);	
	}
	
	


}
