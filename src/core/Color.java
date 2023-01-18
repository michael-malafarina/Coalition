package core;


public class Color extends org.newdawn.slick.Color
{
	public Color(int r, int g, int b) 
	{
		super(r, g, b);
	}
	
	public Color(int r, int g, int b, int a) 
	{
		super(r, g, b, a);
	}
	

	public Color(float r, float g, float b) 
	{
		super(r, g, b);
	}
	
	public Color(float r, float g, float b, float a) 
	{
		super(r, g, b, a);
	}
	
	public void setAlpha(float alpha)
	{
		a = alpha;
	}
	
	public final static Color white = new Color(255, 255, 255);

	
	public static  Color getScalingTint(Color tint, float percent)
	{
		// Add in tint based on its scaling
		float r = 1 + tint.r * percent - percent;
		float g = 1 + tint.g * percent - percent;
		float b = 1 + tint.b * percent - percent;
				
		return new Color(r, g, b);
	}
	
	public  static Color getScalingTint(Color original, Color tint, float percent)
	{		
		float r = original.r + tint.r * percent - percent;
		float g = original.g + tint.g * percent - percent;
		float b = original.b + tint.b * percent - percent;
					
		return new Color(r, g, b);
	}
	
	public Color darker()
	{
		float red = r - .70f;
		float gre = g - .70f;
		float blu = b - .70f;
		
		return new Color(red, gre, blu, a);
	}
	
	
	public Color lighter()
	{
		float red = r + .70f;
		float gre = g + .70f;
		float blu = b + .70f;
		
		return new Color(red, gre, blu, a);
	}
	
	
}
