package gurbx.ultimateroguelike;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gurbx.ultimateroguelike.screens.LoadingScreen;
import gurbx.ultimateroguelike.screens.PlayScreen;
import gurbx.ultimateroguelike.screens.ScreenDispatcher;
import gurbx.ultimateroguelike.utils.Constants;

public class Application extends Game {
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public Viewport viewport;
	public AssetManager assets;
	
	public ScreenDispatcher screenDispatcher;
	
	public LoadingScreen loadingScreen;
	public PlayScreen playScreen;
	
	@Override
	public void create () {
		assets = new AssetManager();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new StretchViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, camera);
		viewport.apply();
		camera.position.set(Constants.VIRTUAL_WIDTH/2, Constants.VIRTUAL_HEIGHT/2, 0);
		
		//Init screens
		loadingScreen = new LoadingScreen(this);
		playScreen = new PlayScreen(this);
		setScreen(loadingScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		assets.dispose();
		batch.dispose();
		loadingScreen.dispose();
		playScreen.dispose();
	}
}
