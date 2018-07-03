package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;
import gurbx.ultimateroguelike.components.MovementComponent;
import gurbx.ultimateroguelike.components.PlayerComponent;
import gurbx.ultimateroguelike.components.StateComponent;
import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;
import gurbx.ultimateroguelike.factories.ProjectileFactory;
import gurbx.ultimateroguelike.utils.Constants;

public class PlayerCombatSystem extends IteratingSystem {
	private Engine engine;
	private OrthographicCamera camera;
	private TextureAtlas atlas;
	private World world;
	private RayHandler rayHandler;
	
	private float shootOffset = 24;

	public PlayerCombatSystem(Engine engine, OrthographicCamera camera, TextureAtlas atlas, World world, RayHandler rayHandler) {
		super(Family.all(PlayerComponent.class, StateComponent.class, TransformComponent.class).get());
		this.engine = engine;
		this.camera = camera;
		this.atlas = atlas;
		this.world = world;
		this.rayHandler = rayHandler;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		//SHOOT
		if (Gdx.input.justTouched()) {
			TransformComponent transComp = TransformComponent.MAPPER.get(entity);
			
			Vector2 mousePos = getMousePosInGameWorld();
			Vector2 direction = mousePos.sub(transComp.position);
			direction.nor();
			
			Entity projectile = ProjectileFactory.createProjectile(
					transComp.position.x * Constants.PPM + (direction.x * shootOffset),
					transComp.position.y * Constants.PPM + (direction.y * shootOffset),
					direction, atlas, world, rayHandler);
			engine.addEntity(projectile);
			
			CameraSystem.shakeScreen(5, 5, false);
			
		}
		
	}
	
	
	private Vector2 getMousePosInGameWorld() {
		Vector3 mPos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		return new Vector2(mPos.x / Constants.PPM, mPos.y / Constants.PPM); 
	}
}
