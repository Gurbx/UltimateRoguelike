package gurbx.ultimateroguelike.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//Pack texutres
//		TexturePacker.process("img_raw", "img_packed", "generalPack");
		
		//Run application
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new Application(), config);
	}
}
