package gurbx.ultimateroguelike.screens;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import box2dLight.RayHandler;
import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.systems.AnimationSystem;
import gurbx.ultimateroguelike.systems.Box2DDebugSystem;
import gurbx.ultimateroguelike.systems.CameraSystem;
import gurbx.ultimateroguelike.systems.DamageSystem;
import gurbx.ultimateroguelike.systems.DeathSystem;
import gurbx.ultimateroguelike.systems.LightSystem;
import gurbx.ultimateroguelike.systems.MovementSystem;
import gurbx.ultimateroguelike.systems.PhysicsSystem;
import gurbx.ultimateroguelike.systems.PlayerMovementSystem;
import gurbx.ultimateroguelike.systems.RenderSystem;
import gurbx.ultimateroguelike.utils.CollisionListener;
import gurbx.ultimateroguelike.utils.CollisionSystem;
import gurbx.ultimateroguelike.world.Dungeon;
import gurbx.ultimateroguelike.world.generator.DungeonGenerator;

public class GameScreen implements Screen {
	protected final Application app;
	protected PooledEngine engine;
	private boolean isInitialized = false;
	
	protected World world;
	protected RayHandler rayHandler;
	protected Dungeon dungeon;
	
	protected TextureAtlas atlas;
	protected TextureAtlas enemyAtlas;
	protected TextureAtlas dungeonAtlas;
	
	//Collision
	private final List<CollisionListener> collisionListeners;
	
	//Disposable
	private Box2DDebugSystem debugSystem;
	private LightSystem lightSystem;
	
	public GameScreen(Application app) {
		this.app = app;
		collisionListeners = new LinkedList<CollisionListener>();
	}

	@Override
	public void show() {
		initTextureAtlases();
		
		engine = new PooledEngine();
		world = new World(new Vector2(0,0), false);
		rayHandler = new RayHandler(world);
		dungeon = DungeonGenerator.generateWorld(dungeonAtlas, world);
		
		CollisionSystem collisionSystem = new CollisionSystem(world, collisionListeners);
		
		DamageSystem damageSystem = new DamageSystem();
		collisionListeners.add(damageSystem);
		engine.addSystem(damageSystem);
		
		DeathSystem deathSystem = new DeathSystem(world);
		engine.addSystem(deathSystem);
		
		PlayerMovementSystem playerSystem = new PlayerMovementSystem();
		engine.addSystem(playerSystem);
		
		MovementSystem movementSystem = new MovementSystem();
		engine.addSystem(movementSystem);
		
		PhysicsSystem physicsSystem = new PhysicsSystem(world);
		engine.addSystem(physicsSystem);
		
		CameraSystem cameraSystem = new CameraSystem(app.camera);
		engine.addSystem(cameraSystem);
		
		AnimationSystem animationSystem = new AnimationSystem();
		engine.addSystem(animationSystem);
		
		RenderSystem renderSystem = new RenderSystem(app.batch, app.camera, dungeon);
		engine.addSystem(renderSystem);
		
		//Add light system if not debug mode
		if (Application.DEBUG) {
			debugSystem = new Box2DDebugSystem(world, app.camera);
			engine.addSystem(debugSystem);
		} else {
			lightSystem = new LightSystem(rayHandler, app.camera);
			engine.addSystem(lightSystem);
		}
		
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
		
		//Dispose disposable components
		for (Entity e : engine.getEntities()) {
			for (Component c : e.getComponents()) {
				if (c instanceof Disposable) ((Disposable) c).dispose();
			}
		}
		
		if (debugSystem!= null) debugSystem.dispose();
		if (lightSystem != null) lightSystem.dispose();
		rayHandler.dispose();
	}

}
