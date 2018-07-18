package gurbx.ultimateroguelike.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.sound.SoundHandler;
import gurbx.ultimateroguelike.utils.sound.Sounds;

public class MainMenu {
	private final Application app;
	private float x, y;
	
	private Stage stage;
	private Skin skin;
	
	private Table table;
	private TextButton playButton, settingsButton, exitButton;
	
	public MainMenu(float x, float y, Stage stage, Skin skin, Application app) {
		this.app = app;
		this.stage = stage;
		this.skin = skin;
		this.x = x;
		this.y = y;
		initButtons();
		initTable();
		fadeInActions();
	}


	private void initButtons() {
		//PLAY
		playButton = new TextButton("Play", skin);
		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundHandler.playSound(Sounds.OUT_OF_AMMO);
//				app.setScreen(app.playScreen);
				app.menuScreen.loadPlay();
			}
		});
		
		//SETTINGS
		settingsButton = new TextButton("Settings", skin);
		settingsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundHandler.playSound(Sounds.OUT_OF_AMMO);
				app.menuScreen.settingsMenu.show();
				hideRight();
				
			}
		});
		
		//EXIT
		exitButton = new TextButton("Exit", skin);
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SoundHandler.playSound(Sounds.OUT_OF_AMMO);
				Gdx.app.exit();
			}
		});
	}
	
	private void initTable() {
		table = new Table(skin);
		table.setPosition(x, y);
		
		table.add(playButton).size(128, 32).pad(10);
		table.row();
		table.add(settingsButton).size(128, 32).pad(10);
		table.row();
		table.add(exitButton).size(128, 32).pad(10);
		
		stage.addActor(table);
	}
	
	public void fadeInActions() {
		table.addAction(Actions.alpha(0f));
		table.addAction(Actions.moveTo(x, y+20f));
		
		MoveToAction action = Actions.action(MoveToAction.class);
		action.setPosition(x, y);
		action.setDuration(0.75f);
		action.setInterpolation(Interpolation.fade);
		table.addAction(Actions.sequence(Actions.delay(0.5f), Actions.parallel(action, Actions.fadeIn(0.75f))));
	}
//	
//	public void hide() {
//		table.addAction(Actions.moveTo(x, y));
//		
//		MoveToAction action = Actions.action(MoveToAction.class);
//		action.setPosition(x, y-360);
//		action.setDuration(0.5f);
//		action.setInterpolation(Interpolation.pow4);
//		table.addAction(action);
//	}
	
	public void show() {
		table.setVisible(true);
		table.addAction(Actions.alpha(0f));
		table.addAction(Actions.moveTo(x-800f, y));
		
		MoveToAction action = Actions.action(MoveToAction.class);
		action.setPosition(x, y);
		action.setDuration(0.5f);
		action.setInterpolation(Interpolation.pow3);
		table.addAction(Actions.sequence(Actions.delay(0.0f), Actions.parallel(action, Actions.fadeIn(0.5f))));
	}
	
	public void hideRight() {
		table.addAction(Actions.moveTo(x, y));
		
		MoveToAction action = Actions.action(MoveToAction.class);
		action.setPosition(x-800f, y);
		action.setDuration(0.5f);
		action.setInterpolation(Interpolation.pow3);
		table.addAction(Actions.parallel(action, Actions.fadeOut(0.5f)));
	}
	
	public void hideDown() {
		table.addAction(Actions.moveTo(x, y));
		
		MoveToAction action = Actions.action(MoveToAction.class);
		action.setPosition(x, y-800);
		action.setDuration(0.5f);
		action.setInterpolation(Interpolation.pow3);
		table.addAction(Actions.parallel(action, Actions.fadeOut(0.5f)));
	}
}
