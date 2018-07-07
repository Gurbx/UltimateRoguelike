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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.utils.Constants;

public class MenuScreen implements Screen {
	private final Application app;
	
	private Stage stage;
	private TextureAtlas atlas;
	
	private Button playButton;
	
	public MenuScreen(Application app) {
		this.app = app;
	}

	@Override
	public void show() {
		stage = new Stage(app.uiViewport);
		atlas = app.assets.get("img_packed/uiPack.atlas", TextureAtlas.class);
		initButtons();
	}

	private void initButtons() {
        Skin skin = new Skin(atlas);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = app.font1;
        style.up = skin.getDrawable("button");
        style.down = skin.getDrawable("button");
        style.over = skin.getDrawable("button");
        
        playButton = new TextButton("PLAY", style);
        playButton.setPosition(Constants.VIRTUAL_UI_WIDTH*0.5f-49, Constants.VIRTUAL_UI_HEIGHT*0.5f - 40);
        playButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//            	app.setScreen(app.introScreen);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
