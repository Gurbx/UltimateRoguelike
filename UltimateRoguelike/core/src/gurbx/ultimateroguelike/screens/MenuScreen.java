package gurbx.ultimateroguelike.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
import gurbx.ultimateroguelike.menu.MainMenu;
import gurbx.ultimateroguelike.menu.SettingsMenu;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.sound.SoundHandler;
import gurbx.ultimateroguelike.utils.sound.Sounds;

public class MenuScreen implements Screen {
	private final Application app;

	private Skin skin;
	private Stage stage;
	
	public MainMenu mainMenu;
	public SettingsMenu settingsMenu;
	
	public MenuScreen(Application app) {
		this.app = app;
	}

	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("ui/uiskin.json")); //LOAD WITH ASSETLOADER
		stage = new Stage(app.uiViewport);
		
		mainMenu = new MainMenu(Constants.VIRTUAL_UI_WIDTH*0.5f ,Constants.VIRTUAL_UI_HEIGHT*0.5f, stage, skin, app);
		settingsMenu = new SettingsMenu(Constants.VIRTUAL_UI_WIDTH*0.5f ,Constants.VIRTUAL_UI_HEIGHT*0.5f, stage, skin, app);
		
		SoundHandler.playMusic();
		
		Gdx.input.setInputProcessor(stage);
	}
	
	private void update(float delta) {
		stage.act(delta);
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
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
		stage.dispose();
		skin.dispose();
	}
}
