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
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.sound.SoundHandler;
import gurbx.ultimateroguelike.utils.sound.Sounds;

public class MenuScreen implements Screen {
	private final Application app;
	
	private Stage stage;
	private TextureAtlas atlas;
	
	private Button playButton;
//	private Table table;
	
	public MenuScreen(Application app) {
		this.app = app;
	}

	@Override
	public void show() {
		stage = new Stage(app.uiViewport);
		atlas = app.assets.get("img_packed/menuPack.atlas", TextureAtlas.class);
		initButtons();
		Gdx.input.setInputProcessor(stage);
	}

	private void initButtons() {
        Skin skin = new Skin(atlas);
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
//        style.font = app.font1;
        style.up = skin.getDrawable("buttonPlay");
        style.over = skin.getDrawable("buttonPlayHover");
        style.down = skin.getDrawable("button");
        
        playButton = new ImageButton(style);
        playButton.setPosition(Constants.VIRTUAL_UI_WIDTH*0.5f-49, Constants.VIRTUAL_UI_HEIGHT*0.5f - 40);
        playButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	SoundHandler.playSound(Sounds.OUT_OF_AMMO);
            	app.setScreen(app.playScreen);
            };
        });
        stage.addActor(playButton);
	}
	
	private void update(float delta) {
		stage.act();
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1);
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
	}

}
