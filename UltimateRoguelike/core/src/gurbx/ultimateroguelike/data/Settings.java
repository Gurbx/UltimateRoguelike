package gurbx.ultimateroguelike.data;

import java.io.Serializable;

import gurbx.ultimateroguelike.utils.Constants;

public class Settings implements Serializable {
	//SOUND
	public float soundVolume;
	public float musicVolume;
	public boolean soundMuted;
	public boolean musicMuted;
	//RESOLUTION
	public boolean fullscreen;
	public int screenWidth;
	public int screenHeight;
	
	public void initialize() {
		soundVolume = 1f;
		musicVolume = 1f;
		soundMuted = false;
		musicMuted = false;
		
		fullscreen = false;
		screenWidth = Constants.VIRTUAL_WIDTH;
		screenHeight = Constants.VIRTUAL_HEIGHT;
	}
}
