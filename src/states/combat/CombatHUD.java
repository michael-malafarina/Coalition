package states.combat;

import org.newdawn.slick.Graphics;

import ui.UI;
import ui.combat.AbilityListPanel;
import ui.combat.AbilityUsePanel;
import ui.combat.Panel;
import ui.combat.UnitPanel;
import ui.modifierList.ModifierListPanel;

public class CombatHUD 
{
	private static UnitPanel active;
	private static UnitPanel focus;
	private static AbilityListPanel activeAbilityList;
	private static AbilityListPanel focusAbilityList;

	private static Panel abilityUse;

	private static ModifierListPanel activeModifierListPanel;
	private static ModifierListPanel focusModifierListPanel;

	public static void init()
	{

		activeAbilityList = new AbilityListPanel(UI.width(.02f), UI.height(.02f), UI.width(.20f));
		focusAbilityList = new AbilityListPanel(UI.width(.77f), UI.height(.02f), UI.width(.20f));

		abilityUse = new AbilityUsePanel();
		
		active = new UnitPanel(UI.width(.02f), UI.height(.86f), UI.width(.20f), UI.height(.12f));
		focus = new UnitPanel(UI.width(.77f), UI.height(.86f), UI.width(.20f), UI.height(.12f));
		activeModifierListPanel = new ModifierListPanel(UI.width(.02f), UI.height(.84f), UI.width(.20f));
		focusModifierListPanel = new ModifierListPanel(UI.width(.77f), UI.height(.84f), UI.width(.20f));

	}
	
	public static void start()
	{
		
	}
	
	public static void update()
	{
		focus.setOwner(CombatManager.getFocusUnit());
		focus.update();
		active.setOwner(CombatManager.getActiveUnit());
		active.update();
		activeAbilityList.setOwner(CombatManager.getActiveUnit());
		activeAbilityList.update();
		focusAbilityList.setOwner(CombatManager.getFocusUnit());
		focusAbilityList.update();
		
		abilityUse.update();
		activeModifierListPanel.setOwner(CombatManager.getActiveUnit());
		activeModifierListPanel.update();
		focusModifierListPanel.setOwner(CombatManager.getFocusUnit());
		focusModifierListPanel.update();
	}
	
	public static void render(Graphics g)
	{
		
		active.render(g);
		focus.render(g);
		activeAbilityList.render(g);


		abilityUse.render(g);
		activeModifierListPanel.render(g);
		
		if(CombatManager.getActiveUnit() != CombatManager.getFocusUnit())
		{
			focusAbilityList.render(g);
			focusModifierListPanel.render(g);
		}


	}
}
