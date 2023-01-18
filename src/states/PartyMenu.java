package states;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.Main;
import ui.Images;
import ui.Mouse;
import ui.UI;
import ui.elements.UIPanel;
import ui.menu.MenuAbilitiesPanel;
import ui.menu.MenuAttributes;
import ui.menu.MenuDescription;
import ui.menu.MenuProgression;
import ui.menu.MenuUnitSelection;
import unit.Unit;
import unit.manager.CoalitionManager;

public class PartyMenu extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;

	private static Unit current;

	UIPanel activeAbilities;
	UIPanel description;
	UIPanel attributes;
	UIPanel party;
	UIPanel progression;

	public static Unit getCurrentUnit()			{		return current;	}
	public static void setCurrentUnit(Unit u)	{		current = u;	}

	public PartyMenu(int id) 
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
		UI.update();	

		CoalitionManager.update();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		UI.render(g);
	}

	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{		
		current = CoalitionManager.getUnits().get(0);

		Mouse.setMouseCursor(Images.cursorPointer);

		activeAbilities = new MenuAbilitiesPanel();
		description = new MenuDescription();
		attributes = new MenuAttributes();
		party = new MenuUnitSelection();
		progression = new MenuProgression();

		UI.addPanel(progression);
		UI.addPanel(activeAbilities);
		UI.addPanel(description);
		UI.addPanel(attributes);
		UI.addPanel(party);
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		UI.clear();
	}

	public void keyPressed(int key, char c)
	{
		if(key == Input.KEY_ESCAPE || key == Input.KEY_C)
		{
			sbg.enterState(Main.COMBAT_ID);
		}

		if(key == Input.KEY_P)
		{
			CoalitionManager.addSkillPoints(1);
		}	
	}
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}




}
