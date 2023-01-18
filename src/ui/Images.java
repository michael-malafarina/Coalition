package ui;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import animation.AnimatedSpriteSheet;

public class Images 
{
	public static SpriteSheet tilesetForest;
	
	public static SpriteSheet cleric;
	public static SpriteSheet knight;
	public static SpriteSheet warrior;
	public static SpriteSheet wizard;
	public static SpriteSheet skeleton;
	public static SpriteSheet skeletonArcher;
	public static SpriteSheet skeletonKnight;

	public static SpriteSheet treePine;
	
	public static Cursor cursorAttack;
	public static Cursor cursorPointer;
	
	public static AnimatedSpriteSheet animSlash;
	public static AnimatedSpriteSheet animBlunt;
	public static AnimatedSpriteSheet animFlame;
	public static AnimatedSpriteSheet animHeal;
	public static AnimatedSpriteSheet animCold;
	public static AnimatedSpriteSheet animShield;
	public static AnimatedSpriteSheet animHoly;
	public static AnimatedSpriteSheet animCleanse;
	public static AnimatedSpriteSheet animTeleport;
	public static AnimatedSpriteSheet animDark;

	public static AnimatedSpriteSheet[] cursorBurst;
	public static AnimatedSpriteSheet cursorSingleTarget;

	
	public static Image cellOverlay;
	public static Image cellDot;
	
	public static Image title;
	
	// Condition Icons
	public static Image iconBleed;
	public static Image iconHaste;
	public static Image iconSlow;
	public static Image iconVigor;
	public static Image iconDecay;
	public static Image iconMight;
	public static Image iconMarked;
	public static Image iconPoison;
	public static Image iconClarity;
	public static Image iconRegen;

	
	// Discipline Icons
	public static Image iconArcana;
	public static Image iconArms;
	public static Image iconChivalry;
	public static Image iconFlame;
	public static Image iconFaith;
	public static Image iconShadow;
	public static Image iconTime;
	public static Image iconGrowth;
	
	// Misc Icons
	public static Image iconCheck;

	
	public static Image[] numbers;
	
	public static void loadImages()
	{
		try 
		{
			loadBackground();
			loadUnits();
			loadObstacles();
			loadTilesets();
			loadAnimations();
			loadCursors();
			loadOverlays();
			loadIcons();

		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void loadBackground() throws SlickException
	{
		title = new Image("res/background/title.png");

	}
	
	
	public static void loadObstacles() throws SlickException
	{
		treePine = new SpriteSheet("res/world/obstacles/treePine.png", 32, 64, 0);
	}
	
	
	public static void loadUnits() throws SlickException
	{
		cleric = new SpriteSheet("res/unit/cleric.png", 32, 64, 0);
		knight = new SpriteSheet("res/unit/knight.png", 32, 64, 0);
		warrior = new SpriteSheet("res/unit/warrior.png", 32, 64, 0);
		wizard = new SpriteSheet("res/unit/wizard.png", 32, 64, 0);

		skeleton = new SpriteSheet("res/unit/undead/skeleton.png", 32, 64, 0);
		skeletonArcher = new SpriteSheet("res/unit/undead/skeletonArcher.png", 32, 64, 0);
		skeletonKnight = new SpriteSheet("res/unit/undead/skeletonKnight.png", 32, 64, 0);

	}
	
	public static void loadTilesets() throws SlickException
	{
		//tilesetForest = new SpriteSheet("res/world/tileset/tilesetForest.png", 64, 64, 0);
		tilesetForest = new SpriteSheet("res/world/tileset/tilesetForestNew.png", 32, 32, 0);
	}
	
	
	public static void loadAnimations() throws SlickException
	{
		//knight = new SpriteSheet("res/unit/knight.png", 64, 128, 0);
		animSlash = new AnimatedSpriteSheet("res/animations/effect/animSlash.png", 32, 64, 15, 5);
		animFlame = new AnimatedSpriteSheet("res/animations/effect/animFlame.png", 32, 64, 40, 5);
		animHeal = new AnimatedSpriteSheet("res/animations/effect/animHeal.png", 32, 64, 40, 5);
		animCold = new AnimatedSpriteSheet("res/animations/effect/animCold.png", 32, 64, 40, 5);
		animShield = new AnimatedSpriteSheet("res/animations/effect/animShield.png", 32, 64, 28, 4);
		animBlunt = new AnimatedSpriteSheet("res/animations/effect/animBlunt.png", 32, 64, 24, 4);
		animHoly = new AnimatedSpriteSheet("res/animations/effect/animHoly.png", 32, 64, 40, 5);
		animCleanse = new AnimatedSpriteSheet("res/animations/effect/animCleanse.png", 32, 64, 40, 5);
		animTeleport = new AnimatedSpriteSheet("res/animations/effect/animTeleport.png", 32, 64, 40, 5);
		animDark = new AnimatedSpriteSheet("res/animations/effect/animDark.png", 32, 64, 40, 5);

		
		
	}
	
	public static void loadCursors() throws SlickException
	{
		cursorAttack = new Cursor(new Image("res/cursor/sword2.png"), 7, 7);
		cursorPointer = new Cursor(new Image("res/cursor/pointer2.png"), 7, 7);

		cursorSingleTarget = new AnimatedSpriteSheet("res/ui/misc/cursorSingleTarget.png", 32, 32, 80, 4);

		cursorBurst = new AnimatedSpriteSheet[4];
		for(int i = 0; i < cursorBurst.length; i++)
		{
			cursorBurst[i] = new AnimatedSpriteSheet("res/ui/misc/cursorBurst" + i + ".png", (2*i+1) * 32, (2*i+1) * 32, 80, 4); 
		}
	//	cursorBurst[i] = new AnimatedSpriteSheet("res/ui/misc/cursorBurst" + i + ".png", 96, 96, 80, 4); 

	}
	
	public static void loadOverlays() throws SlickException
	{
		numbers = new Image[10];
		for(int i = 0; i < numbers.length; i++)
		{
			numbers[i] = new Image("res/ui/numbers/" + i + ".png");
		}
		
		cellOverlay = new Image("res/overlay/cellOverlay.png");
		cellDot = new Image("res/overlay/cellDot.png");

	}
	
	public static void loadIcons() throws SlickException
	{
		// Conditions
		iconBleed = new Image("res/conditions/iconBleed.png");
		iconHaste = new Image("res/conditions/iconHaste.png");
		iconSlow = new Image("res/conditions/iconSlow.png");
		iconVigor = new Image("res/conditions/iconVigor.png");
		iconDecay = new Image("res/conditions/iconDecay.png");
		iconMight = new Image("res/conditions/iconMight.png");
		iconMarked = new Image("res/conditions/iconMarked.png");
		iconPoison = new Image("res/conditions/iconPoison.png");
		iconClarity = new Image("res/conditions/iconClarity.png");
		iconRegen = new Image("res/conditions/iconRegen.png");

		// Discipline
		iconArcana = new Image("res/ui/disciplines/iconArcana.png");
		iconArms = new Image("res/ui/disciplines/iconArms.png");
		iconChivalry = new Image("res/ui/disciplines/iconChivalry.png");
		iconFlame = new Image("res/ui/disciplines/iconFlame.png");
		iconFaith = new Image("res/ui/disciplines/iconFaith.png");
		iconShadow = new Image("res/ui/disciplines/iconShadow.png");
		iconTime = new Image("res/ui/disciplines/iconTime.png");
		iconGrowth = new Image("res/ui/disciplines/iconGrowth.png");

		// Misc
		iconCheck = new Image("res/ui/misc/iconCheck.png");

	}
	 
	
//	920 x 540 --> 2x is 1920 x 1080
// 
	
	
}
