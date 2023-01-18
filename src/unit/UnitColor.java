package unit;

import core.Color;
import core.Utility;

public enum UnitColor 
{
	AZURE, BLUE, BLACK, BRONZE, BROWN, CRIMSON, EMERALD, GRAY, 
	GREEN, GOLD, HUNTER, ICE, INDIGO, ORANGE, PURPLE, VIOLET, 
	RED, SILVER, TAN, TEAL, WHITE, YELLOW;

	public Color getColor()
	{
		switch(this)
		{
		case AZURE:
			return new Color(100, 180, 255);
		case BLUE:
			return new Color(60, 90, 255);
		case BLACK:
			return new Color(80, 80, 80);
		case BRONZE:		
			return new Color(200, 190, 120);
		case BROWN:		
			return new Color(165, 80, 80);
		case CRIMSON:		
			return new Color(130, 50, 50);
		case EMERALD:		
			return new Color(40, 220, 40);
		case GRAY:
			return new Color(150, 150, 150);
		case GREEN:		
			return new Color(120, 220, 120);
		case GOLD:		
			return new Color(255, 255, 150);
		case HUNTER:		
			return new Color(60, 120, 60);
		case ICE:
			return new Color(150, 200, 255);
		case INDIGO:				
			return new Color(75, 40, 110);
		case ORANGE:				
			return new Color(255, 125, 30);
		case PURPLE:				
			return new Color(180, 80, 140);
		case VIOLET:				
			return new Color(120, 90, 170);
		case RED:				
			return new Color(225, 40, 40);
		case SILVER:
			return new Color(200, 200, 200);
		case TAN:
			return new Color(210, 170, 140);
		case TEAL:
			return new Color(0, 140, 120);
		case WHITE:
			return new Color(255, 255, 255);
		case YELLOW:		
			return new Color(255, 255, 90);
		default:
			return null;
		}

	}

	Color getRandomColor()
	{
		return values()[Utility.random(0, values().length)].getColor();
	}
}
