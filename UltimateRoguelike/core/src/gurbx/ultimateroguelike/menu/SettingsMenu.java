package gurbx.ultimateroguelike.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
	private CheckBox soundCheckBox, musicCheckBox;
	
	private TextButton applyButton;
	
	public SettingsMenu(float x, float y, Stage stage, Skin skin, Application app) {
		this.app = app;
		this.skin = skin;
		this.stage = stage;
		this.x = x;
		this.y = y;
		initSliders();
		inintButtons();
		initTable();
	}

	private void initSliders() {
		//SOUND
		//slider
		soundSlider = new Slider(0, 100, 1, false, skin);
		soundSlider.setValue(SettingsDataHandler.settings.soundVolume*100f);
		soundSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				SettingsDataHandler.settings.soundVolume = soundSlider.getValue()/100f;
				
				if (soundSlider.getValue() == 0) {
					SettingsDataHandler.settings.soundMuted = true;
					soundCheckBox.setChecked(true);
				} else { 
					soundCheckBox.setChecked(false);
					SettingsDataHandler.settings.soundMuted = false;
				}
			}
		});
		//checkbox
		soundCheckBox = new CheckBox("Mute", skin);
		soundCheckBox.setChecked(SettingsDataHandler.settings.soundMuted);
		soundCheckBox.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				SettingsDataHandler.settings.soundMuted = soundCheckBox.isChecked();
			}
		});
		
		musicSlider = new Slider(0, 100, 1, false, skin);
		musicCheckBox = new CheckBox("Mute", skin);
	}
	
	private void inintButtons() {
		applyButton = new TextButton("Apply", skin);
		applyButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				SettingsDataHandler.save();
			}
		});
		
	}

	private void initTable() {
		table = new Table(skin);
		table.setPosition(x, y);
		
		table.add(new Label("Sound", skin)).pad(5);
		table.add(soundSlider).size(128, 16).pad(5);
		table.add(soundCheckBox).pad(5);
		table.row();
		table.add(new Label("music", skin)).pad(5);
		table.add(musicSlider).size(128, 16).pad(5);
		table.add(musicCheckBox).pad(5);
		table.row();
		table.add(applyButton);
		
		stage.addActor(table);
	}
}
