package gurbx.ultimateroguelike.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.systems.AnimationSystem;
import gurbx.ultimateroguelike.systems.Box2DDebugSystem;
import gurbx.ultimateroguelike.systems.MovementSystem;
import gurbx.ultimateroguelike.systems.PhysicsSystem;
import gurbx.ultimateroguelike.systems.PlayerSystem;
import gurbx.ultimateroguelike.systems.RenderSystem;
import gurbx.ultimateroguelike.world.Dungeon;
import gurbx.ultimateroguelike.world.generator.DungeonGenerator;

public class GameScreen implements Screen {
	protected final Application app;
	protected PooledEngine engine;
	private boolean isInitialized = false;
	
	protected World world;
	protected Dungeon dungeon;
	
	protected TextureAtlas atlas;
	protected TextureAtlas enemyAtlas;
	protected TextureAtlas dungeonAtlas;
	
	//Disposable
	private Box2DDebugSystem debugSystem;
	
	public GameScreen(Application app) {
		this.app = app;
	}

	@Override
	public void show() {
		initTextureAtlases();
		
		engine = new PooledEngine();
		world = new World(new Vector2(0,0), false);
		dungeon = DungeonGenerator.generateWorld(dungeonAtlas, world);
		
		PlayerSystem playerSystem = new PlayerSystem(app.camera);
		engine.addSystem(playerSystem);
		
		MovementSystem movementSystem = new MovementSystem();
		engine.addSystem(movementSystem);
		
		PhysicsSystem physicsSystem = new PhysicsSystem(world);
		engine.addSystem(physicsSystem);
		
		AnimationSystem animationSystem = new AnimationSystem();
		engine.addSystem(animationSystem);
		
		RenderSystem renderSystem = new RenderSystem(app.batch, app.camera, dungeon);
		engine.addSystem(renderSystem);
		
		debugSystem = new Box2DDebugSystem(world, app.camera);
//		engine.addSystem(debugSystem);
		
		isInitialized = true;	
	}

	private void initTextureAtlases() {
		atlas =  app.assets.get("img_packed/generalPack.atlas", TextureAtlas.class);
		enemyAtlas = app.assets.get("img_packed/enemiesPack.atlas", TextureAtlas.class);
		dungeonAtlas = app.assets.get("img_packed/dungeonPack.atlas", TextureAtlas.class);
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
