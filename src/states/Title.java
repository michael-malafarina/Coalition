package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ability.DisciplineManager;
import animation.AnimationManager;
import core.Main;
import ui.Fonts;
import ui.Images;
import ui.Text;
import ui.UI;
import ui.sound.AudioManager;
import unit.factions.Factions;

public class Title extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;

	public Title(int id) 
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
		// This code happens when you enter a game state for the *first time.*
		Images.loadImages();
		
		
		Fonts.loadFonts();
		AudioManager.loadSFX();
		AudioManager.loadMusic();
		AnimationManager.init();
		Text.init(gc.getGraphics());
		Factions.init();
		UI.init();
		
		DisciplineManager.init();

		
		sbg.enterState(Main.START_PARTY_ID);


	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		
		AudioManager.update();
		
		sbg.enterState(Main.START_PARTY_ID);

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
	//	Images.title.draw(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
//		g.drawString("Project Steelheart", 50, 50);
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char c)
	{
		sbg.enterState(Main.START_PARTY_ID);
	}
	
	public void mousePressed(int button, int x, int y)
	{
		sbg.enterState(Main.START_PARTY_ID);
	}
	
	


}
