package gurbx.ultimateroguelike.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import gurbx.ultimateroguelike.Application;

public class LoadingScreen implements Screen {
	private final Application app;

	public LoadingScreen(Application app) {
		this.app = app;
	}
	
	@Override
	public void show() {
		app.assets.load("img_packed/generalPack.atlas", TextureAtlas.class);
		app.assets.load("img_packed/enemiesPack.atlas", TextureAtlas.class);
		app.assets.load("img_packed/dungeonPack.atlas", TextureAtlas.class);
	}
	
	private void update(float delta) {
		app.assets.update();
		System.out.println(app.assets.getProgress());
		if (app.assets.getProgress() >= 1) {
			app.setScreen(app.playScreen);
		}
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		app.batch.begin();
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
