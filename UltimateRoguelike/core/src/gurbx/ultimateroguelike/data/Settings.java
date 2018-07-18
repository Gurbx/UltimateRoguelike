package gurbx.ultimateroguelike.data;

import java.io.Serializable;

import gurbx.ultimateroguelike.utils.Constants;

public class Settings implements Serializable {
	//SOUND
	private float masterVolume;
	private float soundVolume;
	private float musicVolume;
	public boolean masterMuted;
	public boolean soundMuted;
	public boolean musicMuted;
	//RESOLUTION
	public boolean fullscreen;
	public int screenWidth;
	public int screenHeight;
	
	public void initialize() {
		masterVolume = 1f;
		soundVolume = 1f;
		musicVolume = 1f;
		masterMuted = false;
		soundMuted = false;
		musicMuted = false;
		
		fullscreen = false;
		screenWidth = Constants.VIRTUAL_WIDTH;
		screenHeight = Constants.VIRTUAL_HEIGHT;
	}
	
	public float getMasterVolume() {
		return masterVolume;
	}
	
	public void setMasterVolume(float masterVolume) {
		if (masterVolume <= 0) 
			this.masterVolume = 0;
		else if (masterVolume >= 1)
			this.masterVolume = 1;
		else 
			this.masterVolume = masterVolume;
	}

	public float getSoundVolume() {
		return soundVolume;
	}

	public void setSoundVolume(float soundVolume) {
		if (soundVolume <= 0) 
			this.soundVolume = 0;
		else if (soundVolume >= 1)
			this.soundVolume = 1;
		else 
			this.soundVolume = soundVolume;
	}

	public float getMusicVolume() {
		return musicVolume;
	}

	public void setMusicVolume(float musicVolume) {
		if (musicVolume <= 0) 
			this.musicVolume = 0;
		else if (musicVolume >= 1)
			this.musicVolume = 1;
		else 
			this.musicVolume = musicVolume;
	}
	
	
}
