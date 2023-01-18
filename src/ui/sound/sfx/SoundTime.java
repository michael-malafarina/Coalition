package ui.sound.sfx;

public class SoundTime
{
	private SmartSound sound;
	private int timeLeft;
	private float pitch;
	private float volume;
	
	public SoundTime(SmartSound sound, int timeLeft, float pitch, float volume)
	{
		this.sound = sound;
		this.timeLeft = timeLeft;
		this.pitch = pitch;
		this.volume = volume;
	}
	
	public SoundTime(SmartSound sound, int timeLeft)
	{
		this.sound = sound;
		this.timeLeft = timeLeft;
		pitch = 1;
		volume = 1;
	}
	
	public void update()
	{
		if(timeLeft == 0)
		{
			sound.play(pitch, volume);
		}
		
		timeLeft--;
	}
	
	public boolean isDone()
	{
		return timeLeft < 0;
	}

}
