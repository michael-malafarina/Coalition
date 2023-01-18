package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import states.Combat;
import states.Lose;
import states.PartyMenu;
import states.Recruit;
import states.StartingParty;
import states.Title;
import states.Win;

public class Main extends StateBasedGame 
{
	public final static int FRAMES_PER_SECOND = 60;
	private static AppGameContainer appgc;
	
    public static final int TITLE_ID  = 0;
    public static final int RECRUIT_ID  = 1;
    public static final int COMBAT_ID  = 2;
    public static final int WIN_ID  = 3;
    public static final int LOSE_ID  = 4;
    public static final int PARTY_ID  = 5;
    public static final int START_PARTY_ID  = 6;

    private BasicGameState game;  
    private BasicGameState title;  
    private BasicGameState recruit;  
    private BasicGameState win;  
    private BasicGameState lose;  
    private BasicGameState party;
    private BasicGameState startParty;
    
	public Main(String name) 
	{
		super(name);
		
		title = new Title(TITLE_ID);
		recruit = new Recruit(RECRUIT_ID);
		game = new Combat(COMBAT_ID);
		win = new Win(WIN_ID);
		lose = new Lose(LOSE_ID);
		party = new PartyMenu(PARTY_ID);
		startParty = new StartingParty(START_PARTY_ID);

	}

	public static int getScreenWidth()
	{
		return appgc.getScreenWidth();
	}
	
	public static int getScreenHeight()
	{
		return appgc.getScreenHeight();
	}
	
	public static Input getInput()
	{
		return appgc.getInput();
	}
	
	public static int getCellSize()
	{
		return 32 * getGameScale();
	}
	
	public static GameContainer getGameContainer()
	{
		return appgc;
	}
	
	public static int getGameScale()
	{
		if(getScreenHeight() >= 1440)
		{
			return 3;
		}
		else if(getScreenHeight() >= 1080)
		{
			return 2;
		}
		else
		{
			return 1;
		}
	}
	
	public void initStatesList(GameContainer gc) throws SlickException 
	{
		addState(title);
		addState(game);
		addState(recruit);
		addState(win);
		addState(lose);
		addState(party);
		addState(startParty);
	}

	public static void main(String[] args) 
	{
		try 
		{
			appgc = new AppGameContainer(new Main("Project Steelheart"));
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		
			appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), false);
			appgc.setTargetFrameRate(FRAMES_PER_SECOND);
			appgc.start();
			appgc.setVSync(true);
			appgc.setShowFPS(false);
			Log.setVerbose(false);

		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}

	}
}