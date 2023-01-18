package ui;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

import core.Main;

public class Fonts 
{
	public static TrueTypeFont tinyText;
	public static TrueTypeFont smallText;
	public static TrueTypeFont smallTextLight;
	public static TrueTypeFont mediumText;
	public static TrueTypeFont largeText;
	public static TrueTypeFont titleText;
	
	public static int scale(int size)
	{
		if(Main.getScreenHeight() >= 1440)
		{
			return Math.round((float) 1.15 * (float) size);
		}
		else
		{
			return size;
		}
	}
	
	public static void loadFonts() 
	{
		tinyText = new TrueTypeFont(new Font("Consolas", Font.BOLD, scale(14)), false);
		smallText = new TrueTypeFont(new Font("Consolas", Font.BOLD, scale(16)), false);
		smallTextLight = new TrueTypeFont(new Font("Consolas", Font.PLAIN, scale(16)), false);
		mediumText = new TrueTypeFont(new Font("Consolas", Font.BOLD, scale(18)), false);
		largeText = new TrueTypeFont(new Font("Consolas", Font.BOLD, scale(20)), false);
		titleText = new TrueTypeFont(new Font("Consolas", Font.BOLD, scale(24)), false);
		
	}
}
