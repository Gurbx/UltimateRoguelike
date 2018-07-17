package gurbx.ultimateroguelike;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gurbx.ultimateroguelike.data.Settings;
import gurbx.ultimateroguelike.data.SettingsDataHandler;
import gurbx.ultimateroguelike.screens.LoadingScreen;
import gurbx.ultimateroguelike.screens.MenuScreen;
import gurbx.ultimateroguelike.screens.PlayScreen;
import gurbx.ultimateroguelike.screens.ScreenDispatcher;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.sound.SoundHandler;

public class Application extends Game {
	public final static boolean DEBUG = false;
	public final static boolean SOUND_ON = true;
	
	public SpriteBatch batch;
	public OrthographicCamera camera, uiCamera;
	public Viewport viewport, uiViewport;
	public AssetManager assets;
	
	public ScreenDispatcher screenDispatcher;
	
	public LoadingScreen loadingScreen;
	public PlayScreen playScreen;
	public MenuScreen menuScreen;
	
	public SoundHandler soundHandler;
	
	//FONTS
	public static BitmapFont font1;
	
	@Override
	public void create () {
		assets = new AssetManager();
		batch = new SpriteBatch();
		
		camera = new OrthographicCamera();
		uiCamera = new OrthographicCamera();
		
		viewport = new StretchViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, camera);
		viewport.apply();
		camera.position.set(Constants.VIRTUAL_WIDTH/2, Constants.VIRTUAL_HEIGHT/2, 0);
		
		uiViewport = new StretchViewport(Constants.VIRTUAL_UI_WIDTH, Constants.VIRTUAL_UI_HEIGHT, uiCamera);
		uiViewport.apply();
		uiCamera.position.set(Constants.VIRTUAL_UI_WIDTH/2, Constants.VIRTUAL_UI_HEIGHT/2, 0);
		
		soundHandler = new SoundHandler();
		
		initFonts();
		
		//Init screens
		loadingScreen = new LoadingScreen(this);
		playScreen = new PlayScreen(this);
		menuScreen = new MenuScreen(this);
		setScreen(loadingScreen);
	}

	private void initFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/fonts/notomono-regular.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		parameter.borderWidth = 1f;
		parameter.borderColor = Color.DARK_GRAY;
		font1 = generator.generateFont(parameter); 
		generator.dispose();
	}

	@Override
	public void render () {
		super.render();
		soundHandler.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		super.dispose();
		font1.dispose();
		soundHandler.dispose();
		assets.dispose();
		batch.dispose();
		loadingScreen.dispose();
		playScreen.dispose();
		menuScreen.dispose();
	}
}
