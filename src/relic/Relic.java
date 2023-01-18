package relic;

import ability.PassiveAbility;
import modifier.Modifier;

abstract public class Relic extends Modifier
{
	// A relic provides a Passive Ability to the user
	PassiveAbility ability;
	
	
	// Relic may also need to track data like it value and rarity for generation
	
}
