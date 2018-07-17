package gurbx.ultimateroguelike.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.data.Settings;
import gurbx.ultimateroguelike.data.SettingsDataHandler;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.xtests.WorldMapTestRenderer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//Pack texutres
//		TexturePacker.process("img_raw/player", "img_packed", "playerPack");
//		TexturePacker.process("img_raw/enemies", "img_packed", "enemiesPack");
//		TexturePacker.process("img_raw/dungeon", "img_packed", "dungeonPack");
//		TexturePacker.process("img_raw/menu", "img_packed", "menuPack");
		
		SettingsDataHandler.load();
		
		//Run application
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SettingsDataHandler.settings.screenWidth;
		config.height = SettingsDataHandler.settings.screenHeight;
		new LwjglApplication(new Application(), config);
	}
}
