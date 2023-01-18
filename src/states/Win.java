package states;


import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ability.Ability;
import core.CampaignManager;
import core.Color;
import core.Main;
import ui.Fonts;
import ui.Text;
import ui.UI;
import unit.PlayerUnit;
import unit.manager.CoalitionManager;

public class Win extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;
	
//	LearnAbilityPanel abilityPanel;
	int upgradeIndex;
	
	public Win(int id) 
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
				
//		if(abilityPanel.isDone())
//		{
//			upgradeIndex++;
//			
//			if(upgradeIndex == CoalitionManager.getUnits().size())
//			{
//				CampaignManager.advanceStage();
//				sbg.enterState(Main.COMBAT_ID);
//			}
//			else
//			{
//				upgradeNextAbility();
//			}
//			
//		}

	}
	
	public void upgradeNextAbility()
	{
		PlayerUnit unit = (PlayerUnit) CoalitionManager.getUnits().get(upgradeIndex);
		ArrayList<Ability> abilities = new ArrayList<Ability>();
		
//		Ability a = unit.getRandomAbilityFromRandomDiscipline();
//		Ability b = unit.getRandomAbilityFromRandomDiscipline();
//		Ability c = unit.getRandomAbilityFromRandomDiscipline();
//
//
//		
//		
//		while(a.getName() == b.getName())
//		{
//			b = unit.getRandomAbilityFromRandomDiscipline();
//		}
//		
//		while(a.getName() == c.getName() || b.getName() == c.getName())
//		{
//			c = unit.getRandomAbilityFromRandomDiscipline();
//		}
//		
//		abilities.add(a);
//		abilities.add(b);
//		abilities.add(c);		
//
//		
//		abilityPanel = new LearnAbilityPanel(unit, abilities);
//		UI.addPanel(abilityPanel);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		Text.setColor(Color.white);
		Text.alignCenter();
		Text.alignMiddle();
		Text.setFont(Fonts.titleText);
		Text.draw("You win! ", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2 - 200);
		Text.setFont(Fonts.largeText);
		Text.draw(" +3 skill points per character", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
		Text.draw("Press space to try again with more challenge...", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2 + 200);
		
		
		UI.render(g);

	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{		
		CoalitionManager.addSkillPoints(3);
		CampaignManager.advanceStage();
//		upgradeIndex = 0;
//		upgradeNextAbility();
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
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
