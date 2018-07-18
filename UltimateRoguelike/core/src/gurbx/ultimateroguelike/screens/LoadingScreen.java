package gurbx.ultimateroguelike.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.utils.Constants;
import gurbx.ultimateroguelike.utils.particles.ParticleEffects;
import gurbx.ultimateroguelike.utils.sound.Sounds;

public class LoadingScreen implements Screen {
	private final Application app;

	public LoadingScreen(Application app) {
		this.app = app;
	}
	
	@Override
	public void show() {
		//IMG ATLASES
//		app.assets.load("img_packed/playerPack.atlas", TextureAtlas.class);
//		app.assets.load("img_packed/enemiesPack.atlas", TextureAtlas.class);
//		app.assets.load("img_packed/dungeonPack.atlas", TextureAtlas.class);
//		app.assets.load("img_packed/menuPack.atlas", TextureAtlas.class);
//		
//		//PARTICLES
//		for (ParticleEffects effect : ParticleEffects.values()) {
//			app.assets.load(effect.path, ParticleEffect.class);
//		}
		
		//SOUNDS
		for (Sounds soundType : Sounds.values()) {
			 app.assets.load(soundType.path, Sound.class);
		}
		//MUSIC
		app.assets.load("music/spacewaltz.mp3", Music.class);
	}
	
	private void update(float delta) {
		app.assets.update();
		if (app.assets.getProgress() >= 1) {
			app.soundHandler.initSounds(app);
			app.setScreen(app.menuScreen);
		}
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		app.batch.setProjectionMatrix(app.uiCamera.combined);
		app.batch.begin();
		app.font1.draw(app.batch, "LOADING...", 0, 0);
		app.batch.end();	
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		System.out.println("loading screen disposed");
	}

}
