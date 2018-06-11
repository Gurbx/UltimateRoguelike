package gurbx.ultimateroguelike.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.systems.RenderSystem;

public class GameScreen implements Screen {
	private final Application app;
	private boolean isInitialized = false;
	private PooledEngine engine;
	
	public GameScreen(Application app) {
		this.app = app;
	}

	@Override
	public void show() {
		engine = new PooledEngine();
		
		RenderSystem renderSystem = new RenderSystem(app.batch, app.camera);
		engine.addSystem(renderSystem);
		
		isInitialized = true;	
	}

	protected void update(float delta) {
		engine.update(delta);
	}
	
	@Override
	public void render(float delta) {
		update(delta);
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
		// TODO Auto-generated method stub
		
	}

}
