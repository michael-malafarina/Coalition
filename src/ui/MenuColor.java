package ui;

import core.Color;

public enum MenuColor 
{
	AVERAGE, HIGH, LOW,
	HEALTH, GUARD, WARDING,
	
	BASE, CHARGES, DISABLE, HIGHLIGHT, TITLE,
	
	OFFENSIVE_ABILITY, SUPPORT_ABILITY,
	OFFENSIVE_ABILITY_LIGHT, SUPPORT_ABILITY_LIGHT,
	
	MENU_BACKGROUND, MENU_OUTLINE,
	UNIT_BACKGROUND, UNIT_BACKGROUND_SELECT;
	
	public Color getColor()
	{
		switch(this)
		{
		case AVERAGE:
			return new Color(255, 255, 100);
		case HIGH:
			return new Color(100, 255, 100);
		case LOW:
			return new Color(255, 100, 100);
		
		case HEALTH:
			return new Color(255, 70, 70);
		case GUARD:
			return new Color(120, 180, 255);

		case BASE:
			return new Color(255, 255, 255);
		case DISABLE:
			return new Color(180, 180, 180);
		case HIGHLIGHT:
			return new Color(255, 255, 100);
		case CHARGES:
			return new Color(150, 220, 150);
						
		case TITLE:
			return new Color(255, 255, 255);
			
		case OFFENSIVE_ABILITY_LIGHT:
			return new Color(255, 120, 50, 100);
		case SUPPORT_ABILITY_LIGHT:
			return new Color(70, 200, 100, 100);
			
		case OFFENSIVE_ABILITY:
			return new Color(230, 20, 0);
		case SUPPORT_ABILITY:
			return new Color(0, 255, 50);
			
		case UNIT_BACKGROUND:
			return new Color(40, 40, 70);
		case UNIT_BACKGROUND_SELECT:
			return new Color(120, 120, 40);
			
		case MENU_BACKGROUND:
			return new Color(35, 35, 40, 240);
		case MENU_OUTLINE:
			return new Color(10, 10, 10, 255);
						
		default:
			return null;
		}
		
	}
	
}
