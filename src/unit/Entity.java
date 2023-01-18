package unit;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import core.Color;
import core.GameObject;
import core.Main;
import core.Settings;
import modifier.Modifier;
import ui.Fonts;
import ui.Mouse;
import ui.Text;
import world.Cell;

public class Entity extends GameObject
{
	/************************ Constants ************************/

	final public static int ATTACK_TIME = 24;
	final public static int HEALTHBAR_SHOW_TIME = 90;

	protected final int TINT_TIME = 36;
	final private int FRAME_SPEED_BASE = 20;
	final private int FRAMES_BASE = 4;

	/************************ Data ************************/
	
	protected Cell cell;
	
	protected int height;
	protected int width;
	protected int curHealth;
	protected int maxHealth;
	protected int guard;
	
	protected boolean faceEast;
	
	// Tint information
	protected Color color;
	protected Color tint;
	protected int tintTimer;
	
	private int healthbarTimer;

	String debugMessage = "";

	

	/************************ Constructor ************************/

	public Entity()
	{
		super();
		frameRate = FRAME_SPEED_BASE;
		frames = FRAMES_BASE;
		width = 1;
		height = 1;
		tint = Color.white;
		looping = true;
		curHealth = 1;
		maxHealth = 1;
		faceEast();		

	}
	
	/************************ Accessors ************************/

	public int getCurHealth()		{ 	return curHealth;			}	
	public int getMaxHealth()		{	return maxHealth;			}
	public int getGuard()			{ 	return guard;				}	
	public int getDefense()			{	return getCurHealth() + getGuard();	}
	public int getX()				{	return cell.getX();			}
	public int getY()				{	return cell.getY();			}
	public int getXPixel()			{	return cell.getXPixel();	}
	public int getYPixel()			{	return cell.getYPixel();	}
	public int getXPixelCenter()	{	return cell.getXPixel() + Main.getCellSize()/2;	}
	public int getYPixelCenter()	{	return cell.getYPixel() + Main.getCellSize()/2;	}
	public Cell getCell()			{ 	return cell;				}
	
	public boolean isAlive()				{	return curHealth >= 1;							}
	public boolean isDead()					{	return !isAlive();								}
	
	public int getWidth()				{	return width;							}
	public int getWidthPixel()			{	return width * Main.getCellSize();			}
	public int getHeight()				{	return height;							}
	public int getHeightPixel()			{	return height * Main.getCellSize();			}
	
	public int getHeightPixelOffset()	{	return getHeightPixel() - Main.getCellSize();	}
	
	public boolean showingHealthbar()			{	return healthbarTimer > 0;						}

	public boolean isClicked()		{	return isAlive() && Main.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && isMouseOver(); 	}

	public boolean isMouseOver()
	{
		int mX = Mouse.getXPixel();
		int mY = Mouse.getYPixel();

		if(getCell() == null)
		{
			return false;
		}
		
		// Check my square
		if(mX >= getXPixel() && mX < getXPixel() + Main.getCellSize() && mY >= getYPixel() && mY < getYPixel() + Main.getCellSize())
		{
			return true;
		}

		// If there's a unit north of me, we won't check the upper part of my image
		if(getCell().hasNorthCell() &&  getCell().getNorthCell().hasUnit())
		{
			return false;
		}

		// Check half the square above if the area above is clear of units
		if(mX >= getXPixel() && mX < getXPixel() + Main.getCellSize() && mY >= getYPixel() -Main.getCellSize() / 2 && mY < getYPixel() + Main.getCellSize())
		{		
			return true;
		}

		return false;

	}
	
	public int getFramerate() 		{		return frameRate;		}

	
	/************************ Mutators ************************/
	
	public boolean isFacingEast()			{		return faceEast;	}
	public boolean isFacingWest()			{		return !faceEast;	}	
	public void faceEast()					{		faceEast = true;	}
	public void faceWest()					{		faceEast = false;	}
	public void setCell(Cell c)				{		cell = c;	}	
	public void showHealthbar()				{		if(timer > 5) healthbarTimer = HEALTHBAR_SHOW_TIME;		}
	public void showHealthbar(int frames)	{		if(healthbarTimer < frames) healthbarTimer = frames;		}

	public void hideHealthbar()				{	healthbarTimer = 0;		}
	
	public void update()
	{
		super.update();

		if(sheet != null && image != null)
		{

			if(faceEast)
			{
				image = imageBase.getFlippedCopy(true, false);
			}
		}
		
		if(healthbarTimer > 0)
		{
			healthbarTimer--;
		}
		
		// Timer to track color from damage effects
		if(tintTimer > 0)
		{
			tintTimer--;
			float percent = (float) Math.pow((float) tintTimer / (float) TINT_TIME, 2);
			color = Color.getScalingTint(tint, percent);
		}
		else
		{
			color = Color.white;
		}
		
	}

	public void render(Graphics g)
	{
		//System.out.println(image);
		
		if(image != null)
		{
			image.draw(getXPixel(), getYPixel() - getHeightPixelOffset(), getWidthPixel(),  getHeightPixel(), color);
		}
		else
		{
			g.setColor(new Color(100, 200, 100));
			g.fillRect(getXPixel(), getYPixel() - getHeightPixelOffset(), getWidthPixel(), getHeightPixel());
		}
		
	}
	
	public void renderSecond(Graphics g)
	{
		renderDebug(g);
	}
	
	public void setDebugMessage(String s)
	{
		debugMessage = s;
	}
	
	public void renderDebug(Graphics g)
	{
		if(Settings.debugMode)
		{
			Text.setFont(Fonts.mediumText);
			Text.setColor(Color.white);
			Text.alignCenter();
			Text.alignMiddle();
			Text.draw(debugMessage, getXPixel() + Main.getCellSize() / 2, getYPixel() + Main.getCellSize() / 2);
		}
		debugMessage = "";
	}
	

	
	public void gainGuard(int amount)
	{		
		showHealthbar();

		guard += amount;
		
		if(guard > maxHealth)
		{
			guard = maxHealth;
		}
		
	}

	
	public void loseGuard(int amount)
	{		
		showHealthbar();
		
		guard -= amount;
		
		if(guard < 0)
		{
			guard = 0;
		}
	}
	
	
	public void takeDamage(Modifier source, int amount)
	{		
		if(amount == 0)
		{
			return;
		}
	
		showHealthbar();

		if(guard > 0 && !source.ignoresGuard())
		{
			guard--;
			amount--;
		}
		
		curHealth -= amount;

		if(curHealth < 0)
		{
			curHealth = 0;
		}
		
		tint = source.getColor();
		tintTimer = TINT_TIME;
	}
	
	
	public void regainHealth(Modifier source, int amount)
	{
		if(isDead())
		{
			return;	
		}
		
		showHealthbar();
		
		curHealth += amount;

		if(curHealth >= maxHealth)
		{
			curHealth = maxHealth;
		}
	}



}
