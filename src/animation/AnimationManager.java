package animation;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class AnimationManager 
{
	private static ArrayList<DelayedAnimation> delayedAnimations;
	private static ArrayList<Animation> animations;
	
	public static void init()
	{
		reset();
	}
	
//	public static void leave()
//	{
//		animations = null;
//		delayedAnimations = null;
//	}
	
	public static void reset()
	{
		animations = new ArrayList<Animation>();
		delayedAnimations = new ArrayList<DelayedAnimation>();
	}
	
	public static void render(Graphics g)
	{

		
		for (Animation a : animations) 
		{
			a.render(g);
		}
		
	}
	
	public static void update()
	{
		for(int i = 0; i < delayedAnimations.size(); i++)
		{
			delayedAnimations.get(i).update();
			
			if(delayedAnimations.get(i).isDone())
			{
				delayedAnimations.remove(i);
				i--;
			}
		}
		
		
		for(int i = 0; i < animations.size(); i++)
		{
//			if(animations.get(i) != null)
//			{
				animations.get(i).update();
//			}
			
			if(animations.get(i).isDone())
			{
				animations.remove(i);
				i--;
			}
		}
	}
		
	public static void add(Animation a)
	{
		animations.add(a);
	}
	
	public static void add(DelayedAnimation a)
	{
		delayedAnimations.add(a);
	}
}
