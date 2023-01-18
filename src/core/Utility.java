package core;

import unit.Unit;
import world.Cell;

public class Utility 
{
	
//	private static int lastMouseTileX;
//	private static int lastMouseTileY;
//	
//	public static void update()
//	{
//		if(Combat.grid != null)
//		{
//			lastMouseTileX = getMouseTileX();
//			lastMouseTileY = getMouseTileY();
//		}
//
//	}
	
//	public static boolean isMouseOnNewTile()
//	{
//		return lastMouseTileX != getMouseTileX() || lastMouseTileY != getMouseTileY(); 
//	}
//	
//	public static boolean hasMouseTile()
//	{
//		return Combat.grid.inBounds(getMouseTileX(), getMouseTileY());
//	}
//	
//	public static Tile getMouseTile()
//	{
//		if(Combat.grid.inBounds(getMouseTileX(), getMouseTileY()))
//		{
//			return Combat.grid.getTile(getMouseTileX(), getMouseTileY()) ;
//		}
//		else
//		{
//			return null;
//		}
//	}
//	
//	public static int getMouseTileX()
//	{
//		return (Gdx.input.getX() - Combat.grid.getViewOffsetX()) / Values.TILE_SIZE ;
//
//	}
//
//	public static int getMouseTileY()
//	{
//		return ((Settings.getResolutionY() - Gdx.input.getY() - Combat.grid.getViewOffsetY())) / Values.TILE_SIZE ;
//	}

	private static String[] names = {"Ulrich", "Jonas", "Hannah", "Martha", "Claudia", "Agnes", "Noah", 
			"Helge", "Aleksander", "Peter", "Charlotte", "Bartosz",
			"Fitz", "Simmons", "Daisy", "Coulson", "May", "YoYo", "Mac", "Deke", 
			"Hunter", "Bobby", "Tripp", "Ward", "Sousa", "Jiayang", "Robbie", 
			"Ruby", "Andrew", "Talbot", "Lincoln", "Hale", "Radcliffe", "Gonzales",
			"Victoria", "Garrett", "Whitehall", "Gideon", "Nathanial", "Piper", "Davis", 
			"Flint", "Mace", "Aida", "Koenig", "Cal", "Raina", "Alya"
			};
	
	public static String getRandomName()
	{
		int r  = (int) (Math.random() * names.length);
		return names[r];
	}
	
	
//	public static int getDistance(GameObject a, GameObject b)
//	{
//		if(a == null || b == null)
//		{
//			return Integer.MAX_VALUE;
//		}
//		else
//		{
//			return  Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
//		}
//	}
	
	
	public static int getDistance(Unit a, Unit b)	
	{
		return getDistance(a.getCell(), b.getCell());
	}
	
	public static int getDistance(Cell a, Cell b)
	{
		return getDistance(a.getX(), a.getY(), b.getX(), b.getY());
	}
	
	
	public static int getDistance(int x1, int y1, int x2, int y2)
	{
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	
	public static int random(int max) 
	{
		return (int) (Math.random() * max);
	}
	
	public static float random(float max) 
	{
		return (int) (Math.random() * max);
	}

	public static int random(int min, int max) 
	{
		return (int) (Math.random() * (max - min + 1)) + min;
	}

	public static float random(double min, double max) 
	{
		return (float) (min + (Math.random() * (max - min)));
	}

//	public static boolean hasInstance(ArrayList<Unit> units, Class<? extends Unit> clazz) 
//	{
//		for (Unit u : units) {
//			if (clazz.isAssignableFrom(u.getClass())) {
//				return true;
//			}
//		}
//		return false;
//	}
	
//	public static Color modifyAlpha(Color c, int alpha)
//	{
//		return new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
//	}
//	
//	public static Color blend(Color c1, Color c2)
//	{
//		return new Color((int)((c1.getRed() + c2.getRed())/2), 
//						 (int)((c1.getGreen() + c2.getGreen())/2), 
//						 (int)((c1.getBlue() + c2.getBlue())/2), 
//						 (int)((c1.getAlpha() + c2.getAlpha())/ 2));
//	}
//	
//	public static Color getRandomColor(int min, int max)
//	{
//		return new Color(Utility.random(min, max), Utility.random(min, max), Utility.random(min, max));
//	}
//	
//	public static float scaleBetween(float n, float minOut, float maxOut, float minIn, float maxIn) 
//	{
//		 return (maxOut - minOut) * (n - minIn) / (maxIn - minIn) + minOut;
//	}
//	
//	public static float scaleBetweenBounded(float n, float minOut, float maxOut, float minIn, float maxIn) 
//	{
//		float val = scaleBetween(n, minOut, maxOut, minIn, maxIn);
//		return Math.max(Math.min(val,  maxOut), minOut);
//	}
//	
}

