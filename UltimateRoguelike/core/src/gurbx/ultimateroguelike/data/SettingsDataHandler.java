package gurbx.ultimateroguelike.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;

public class SettingsDataHandler {
	public static Settings settings;
	
	public static void save() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("settings.sav"));
			out.writeObject(settings);
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	public static void load() {
		try {
			if (!fileExists()) {
				init();
				return;
			}
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("settings.sav"));
			settings = (Settings) in.readObject();
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			Gdx.app.exit();
		}
		
	}
	
	public static boolean fileExists() {
		File f = new File("settings.sav");
		return f.exists();
	}

	public static void init() {
		settings = new Settings();
		settings.initialize();
		save();
	}
}
