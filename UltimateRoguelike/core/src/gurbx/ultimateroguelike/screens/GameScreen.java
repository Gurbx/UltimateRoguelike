package gurbx.ultimateroguelike.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.systems.AnimationSystem;
import gurbx.ultimateroguelike.systems.Box2DDebugSystem;
import gurbx.ultimateroguelike.systems.PhysicsSystem;
import gurbx.ultimateroguelike.systems.RenderSystem;

public class GameScreen implements Screen {
	protected final Application app;
	protected PooledEngine engine;
	private boolean isInitialized = false;
	
	protected World world;
	
	//Disposable
	private Box2DDebugSystem debugSystem;
	
	public GameScreen(Application app) {
		this.app = app;
	}

	@Override
	public void show() {
		engine = new PooledEngine();
		world = new World(new Vector2(0,0), false);
		
		RenderSystem renderSystem = new RenderSystem(app.batch, app.camera);
		engine.addSystem(renderSystem);
		
		PhysicsSystem physicsSystem = new PhysicsSystem(world);
		engine.addSystem(physicsSystem);
		
		debugSystem = new Box2DDebugSystem(world, app.camera);
		engine.addSystem(debugSystem);
		
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
		if (debugSystem!= null) debugSystem.dispose();
	}

}
