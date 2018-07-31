package gurbx.ultimateroguelike.screens;

import javax.xml.soap.Text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.menu.HomeMenu;
import gurbx.ultimateroguelike.menu.InventoryTestMenu;
import gurbx.ultimateroguelike.menu.PlayScreenLoader;
import gurbx.ultimateroguelike.menu.SettingsMenu;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.sound.SoundHandler;
import gurbx.ultimateroguelike.utils.sound.Sounds;

public class MenuScreen implements Screen {
	private final Application app;
	private TextureAtlas menuAtlas;
	
	private Skin skin;
	private Stage stage;
	
	public HomeMenu mainMenu;
	public SettingsMenu settingsMenu;
	
	private ParticleEffect rainEffect;
	
	private PlayScreenLoader playLoader;
	
	//TEST
	InventoryTestMenu inventoryTestMenu;
	
	public MenuScreen(Application app) {
		this.app = app;
		playLoader = new PlayScreenLoader(app);
	}

	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("ui/uiskin.json")); //LOAD WITH ASSETLOADER
		stage = new Stage(app.uiViewport);
		menuAtlas = app.assets.get("img_packed/menuPack.atlas", TextureAtlas.class);
		
//		mainMenu = new HomeMenu(Constants.VIRTUAL_UI_WIDTH*0.5f ,Constants.VIRTUAL_UI_HEIGHT*0.5f, stage, skin, app);
//		settingsMenu = new SettingsMenu(Constants.VIRTUAL_UI_WIDTH*0.5f ,Constants.VIRTUAL_UI_HEIGHT*0.5f, stage, skin, app);
		
		inventoryTestMenu = new InventoryTestMenu(Constants.VIRTUAL_UI_WIDTH*0.5f ,Constants.VIRTUAL_UI_HEIGHT*0.5f, stage, skin,
				app, menuAtlas);
		
		SoundHandler.playMusic();
		
		playLoader.setLoadPlay(false);
		
		rainEffect = new ParticleEffect();
		rainEffect.load(Gdx.files.internal("particles/rain.p"), Gdx.files.internal("particles"));
		rainEffect.start();
		rainEffect.setPosition(Constants.VIRTUAL_UI_WIDTH*0.5f, Constants.VIRTUAL_HEIGHT*0.5f);
		
		Gdx.input.setInputProcessor(stage);
	}
	
	private void update(float delta) {
		playLoader.update(delta);
		stage.act(delta);
		rainEffect.update(delta);
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		app.batch.setProjectionMatrix(app.camera.combined);
		app.camera.update();
		app.batch.begin();
//		rainEffect.draw(app.batch);
		app.batch.end();
		
		//TEST
		app.batch.setProjectionMatrix(app.uiCamera.combined);
		app.uiCamera.update();
		app.batch.begin();
		inventoryTestMenu.render(app.batch);
		app.batch.end();
		
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		menuAtlas.dispose();
		stage.dispose();
		skin.dispose();
		rainEffect.dispose();
	}

	public void loadPlay() {
		mainMenu.hideDown();
		playLoader.show(stage, skin);
		playLoader.loadPlay();
		playLoader.setLoadPlay(true);
	}
}
