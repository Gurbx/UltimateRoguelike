package gurbx.ultimateroguelike.utils.sound;

import java.util.HashMap;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.data.SettingsDataHandler;

public class SoundHandler {
	private static HashMap<Sounds, Sound> sounds;
//	private static ArrayList<TimedSound> timedSounds;
	
	private static Music music;
	
	public SoundHandler() {
		//Sounds
		sounds = new HashMap<Sounds, Sound>();
//		timedSounds = new ArrayList<TimedSound>();
	}
	
	public static void initSounds(Application app) {
		for (Sounds soundType : Sounds.values()) {
			Sound sound = app.assets.get(soundType.path, Sound.class);
			sounds.put(soundType, sound);
		}
		music = app.assets.get("music/spacewaltz.mp3", Music.class); //TODO
	}
	
	public static void playSound(Sounds sound) {
		sounds.get(sound).play(sound.volume * SettingsDataHandler.settings.getSoundVolume()
				* SettingsDataHandler.settings.getMasterVolume());
	}
	
//	public static void playSound(Sounds sound, float duration)  {
//		if (SettingsDataHandler.settings.soundMuted) return;
//		timedSounds.add(new TimedSound(sounds.get(sound), duration, sound.getVolume()));
//	}
	
	public static void playMusic() {
		music.stop();
		if (music.isPlaying() == false) {
			music.setVolume(SettingsDataHandler.settings.getMusicVolume() * SettingsDataHandler.settings.getMasterVolume());
			music.play();
			music.setLooping(true);
		}
	}
	
	public static void updateMusicVolume() {
		if (music.isPlaying()) {
			music.setVolume(SettingsDataHandler.settings.getMusicVolume() * SettingsDataHandler.settings.getMasterVolume());
		}	
	}
	
	public void update(float delta) {
//		for (int i = 0; i < timedSounds.size(); i++) {
//			timedSounds.get(i).update(delta);
//			if (timedSounds.get(i).finishedPlaying()) {
//				timedSounds.remove(i);
//			}
// 		}
	}
	
	public static void muteSounds() {
	}
	
	
	public void dispose() {
		for (Sounds key : sounds.keySet()) {
			sounds.get(key).dispose();
		}
		music.dispose();
	}
}
