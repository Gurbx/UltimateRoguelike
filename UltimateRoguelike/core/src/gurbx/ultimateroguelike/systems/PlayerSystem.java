package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import gurbx.ultimateroguelike.components.MovementComponent;
import gurbx.ultimateroguelike.components.PlayerComponent;
import gurbx.ultimateroguelike.components.TransformComponent;
import gurbx.ultimateroguelike.utils.Constants;


public class PlayerSystem extends IteratingSystem {
	private OrthographicCamera camera;
	private final float LERP = 10f;
	
	private MovementComponent moveComp;
	private TransformComponent transComp;

	public PlayerSystem(OrthographicCamera camera) {
		super(Family.all(PlayerComponent.class, MovementComponent.class).get());
		this.camera = camera;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		moveComp = MovementComponent.MAPPER.get(entity);
		transComp = TransformComponent.MAPPER.get(entity);
		
		//MOVEMENT 
		moveComp.velocity.x = 0;
		moveComp.velocity.y = 0;
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			moveComp.velocity.x = -1;
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			moveComp.velocity.x = 1;
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			moveComp.velocity.y = -1;
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			moveComp.velocity.y = 1;
		}
		
		//CAMERA
		handleCamera(deltaTime);
	}

	private void handleCamera(float deltaTime) {
		Vector3 position = camera.position;
		position.x += (transComp.position.x * Constants.PPM  - position.x) * LERP * deltaTime;
		position.y += (transComp.position.y * Constants.PPM  - position.y) * LERP * deltaTime;
	}
}
