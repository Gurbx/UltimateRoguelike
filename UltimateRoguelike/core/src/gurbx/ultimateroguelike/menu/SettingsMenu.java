package gurbx.ultimateroguelike.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.data.Settings;
import gurbx.ultimateroguelike.data.SettingsDataHandler;
import gurbx.ultimateroguelike.utils.sound.SoundHandler;
import gurbx.ultimateroguelike.utils.sound.Sounds;

public class SettingsMenu {
	private final Application app;
	private float x, y;
	
	private Stage stage;
	private Skin skin;
	
	private Table table;
	private Slider soundSlider, musicSlider;
	private Label soundVolumeLabel, musicVolumeLabel;
	
	private TextButton backButton;
	
	public SettingsMenu(float x, float y, Stage stage, Skin skin, Application app) {
		this.app = app;
		this.skin = skin;
		this.stage = stage;
		this.x = x;
		this.y = y;
		initAudio();
		inintButtons();
		initTable();
		table.setVisible(false);
	}

	private void initAudio() {
		//Labels
		soundVolumeLabel = new Label(""+ (int) (SettingsDataHandler.settings.soundVolume*100f), skin);
		musicVolumeLabel = new Label(""+ (int) (SettingsDataHandler.settings.musicVolume*100f), skin);
		
		//SOUND
		//slider
		soundSlider = new Slider(0, 100, 1, false, skin);
		soundSlider.setValue(SettingsDataHandler.settings.soundVolume*100f);
		soundSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				SettingsDataHandler.settings.soundVolume = soundSlider.getValue()/100f;
				soundVolumeLabel.setText(""+ (int) (SettingsDataHandler.settings.soundVolume*100f));
				
				if (soundSlider.getValue() == 0) {
					SettingsDataHandler.settings.soundMuted = true;
				} else { 
					SettingsDataHandler.settings.soundMuted = false;
				}
			}
		});
		musicSlider = new Slider(0, 100, 1, false, skin);
	}
	
	private void inintButtons() {
		//Apply
		backButton = new TextButton("Back", skin);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SettingsDataHandler.save();
				SoundHandler.playSound(Sounds.OUT_OF_AMMO);
				app.menuScreen.mainMenu.show();
				hide();
			}
		});
		
	}

	private void initTable() {
		table = new Table(skin);
		table.setPosition(x, y);
		
		table.add(new Label("Sound", skin)).pad(5);
		table.add(soundSlider).size(128, 16).pad(5);
		table.add(soundVolumeLabel).pad(5);
		table.row();
		table.add(new Label("Music", skin)).pad(5);
		table.add(musicSlider).size(128, 16).pad(5);
		table.add(musicVolumeLabel).pad(5);
		table.row();
		table.add(backButton);
		
		stage.addActor(table);
	}

	public void show() {
		table.setVisible(true);
		table.addAction(Actions.alpha(0f));
		table.addAction(Actions.moveTo(x+800f, y));
		
		MoveToAction action = Actions.action(MoveToAction.class);
		action.setPosition(x, y);
		action.setDuration(0.5f);
		action.setInterpolation(Interpolation.pow3);
		table.addAction(Actions.sequence(Actions.delay(0.0f), Actions.parallel(action, Actions.fadeIn(0.5f))));
	}
	
	public void hide() {
		table.addAction(Actions.moveTo(x, y));
		
		MoveToAction action = Actions.action(MoveToAction.class);
		action.setPosition(x+800f, y);
		action.setDuration(0.5f);
		action.setInterpolation(Interpolation.pow3);
		table.addAction(Actions.parallel(action, Actions.fadeOut(0.5f)));
	}
}
