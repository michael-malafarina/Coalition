package states;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.Color;
import core.Main;
import ui.Fonts;
import ui.Text;

public class Lose extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;
	
	public Lose(int id) 
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
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		Text.setColor(Color.white);
		Text.alignCenter();
		Text.alignMiddle();
		Text.setFont(Fonts.titleText);
		Text.draw("You lose.", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2 - 100);
		Text.setFont(Fonts.largeText);
		Text.draw("Press space to continue with the same party", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2 + 50);
		Text.draw("Alt-F4 to close program + restart if you want a new random party", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2 + 100);
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
		if(key == Input.KEY_SPACE)
		{
			sbg.enterState(Main.COMBAT_ID);
		}	
	}
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}
	
	


}
