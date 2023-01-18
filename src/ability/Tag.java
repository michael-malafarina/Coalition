package ability;

import core.Color;

public enum Tag 
{
	MELEE, RANGED, AREA, 
	
	PHYSICAL, MAGICAL, BUFF, DEBUFF,
	SHARP, BLUNT, FORCE,
	FIRE, COLD, LIGHTNING,
	HOLY, PSYCHIC, SHADOW,
	
	BLEED, POISON, VIGOR, HASTE, SLOW,
	
	HEAL, CLEANSE, GUARD, 
	
	READY,
	
	ALL;				
	
	public Color getColor()
	{
		// PRIMARY DAMAGE TYPES FIRST
		if(this == SHARP) return new Color(230, 50, 50);
		if(this == BLUNT) return new Color(180, 140, 100);
		if(this == FORCE) return new Color(200, 140, 255);

		if(this == FIRE) return new Color(255, 125, 0);
		if(this == COLD) return new Color(170, 170, 255);
		if(this == LIGHTNING) return new Color(255, 255, 125);

		if(this == HOLY) return new Color(255, 255, 200);
		if(this == PSYCHIC) return new Color(255, 140, 200);
		if(this == SHADOW) return new Color(180, 180, 180);


		// HEALING TYPES
		if(this == HEAL) return new Color(50, 210, 210);
		if(this == CLEANSE) return new Color(50, 210, 210);

		// SECONDARY DAMAGE TYPES NEXT
		if(this == BLEED) return new Color(230, 50, 50);		
		if(this == POISON) return new Color(50, 230, 50);		

		if(this == GUARD) return new Color(50, 120, 255);		

		
		// BUFFS
		if(this == VIGOR) return new Color(50, 210, 210);		
		if(this == HASTE) return new Color(255, 0, 255);		
		if(this == SLOW) return new Color(255, 255, 0);		


		
		else return null;
	}
	
	
	
}
