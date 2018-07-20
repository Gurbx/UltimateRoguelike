package gurbx.ultimateroguelike.menu;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.data.DungeonData;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.particles.ParticleEffects;
import gurbx.ultimateroguelike.world.generator.DungeonGenerator;

public class PlayScreenLoader {
	private final Application app;
	private boolean loadPlay;
	
	private Label loadingInfo;
	
	public PlayScreenLoader(Application app) {
		this.app = app;
		loadPlay = false;
	}
	
	public void setLoadPlay(boolean loadPlay) {
		this.loadPlay = loadPlay;
	}
	
	public void show(Stage stage, Skin skin) {
		loadingInfo = new Label("Loading...", skin);
		float x = 60;
		float y = 10;
		loadingInfo.setPosition(x, y);
		loadingInfo.setAlignment(Align.center);
		stage.addActor(loadingInfo);
		
		
		loadingInfo.addAction(Actions.fadeIn(0.5f));
	}
	
	public void loadPlay() {
		app.dungeonData = new DungeonData();
		//IMG ATLASES
		app.assets.load("img_packed/playerPack.atlas", TextureAtlas.class);
		app.assets.load("img_packed/enemiesPack.atlas", TextureAtlas.class);
		app.assets.load("img_packed/dungeonPack.atlas", TextureAtlas.class);
//		app.assets.load("img_packed/menuPack.atlas", TextureAtlas.class);
		
		//PARTICLES
		for (ParticleEffects effect : ParticleEffects.values()) {
			app.assets.load(effect.path, ParticleEffect.class);
		}
	}
	
	public void update(float delta) {
		if (!loadPlay) return;
		app.assets.update();
		if (app.assets.getProgress() >= 1) {
//			app.soundHandler.initSounds(app);
//			app.setScreen(app.playScreen);
			loadingInfo.setText("Generating Dungeon...");
			app.dungeonData.dungeonTiles = DungeonGenerator.generateDungeonTiles();
			app.setScreen(app.playScreen);
		} else {
			loadingInfo.setText("Loading assets... " + (int) (app.assets.getProgress()*100) + "%");
		}
	}
}
