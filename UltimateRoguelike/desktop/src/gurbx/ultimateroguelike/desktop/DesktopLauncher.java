package gurbx.ultimateroguelike.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.xtests.WorldMapTestRenderer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//Pack texutres
//		TexturePacker.process("img_raw/player", "img_packed", "playerPack");
//		TexturePacker.process("img_raw/enemies", "img_packed", "enemiesPack");
//		TexturePacker.process("img_raw/dungeon", "img_packed", "dungeonPack");
		TexturePacker.process("img_raw/ui", "img_packed", "uiPack");
		
		//Run application
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new Application(), config);
	}
}
