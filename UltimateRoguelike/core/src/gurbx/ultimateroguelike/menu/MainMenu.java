package gurbx.ultimateroguelike.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.sound.SoundHandler;
import gurbx.ultimateroguelike.utils.sound.Sounds;

public class MainMenu {
	private float x, y;
	private final Application app;
	private ImageButton playButton, settingsButton, exitButton;
	private Stage stage;
	private TextureAtlas atlas;
	
	public MainMenu(float x, float y, Stage stage, TextureAtlas atlas, Application app) {
		this.x = x;
		this.y = y;
		this.app = app;
		this.stage = stage;
		this.atlas = atlas;
		initButtons();
	}

	private void initButtons() {
		Skin skin = new Skin(atlas);
		
		//PLAY
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("buttonPlay");
        style.over = skin.getDrawable("buttonPlayHover");
        style.down = skin.getDrawable("button");
        
        playButton = new ImageButton(style);
        playButton.setPosition(x, y);
        playButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	SoundHandler.playSound(Sounds.OUT_OF_AMMO);
            	app.setScreen(app.playScreen);
            };
        });
        
        
        stage.addActor(playButton);
		
	}
	
	

}
