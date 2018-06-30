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
import gurbx.ultimateroguelike.components.StateComponent;
import gurbx.ultimateroguelike.components.TextureComponent;
import gurbx.ultimateroguelike.components.TransformComponent;
import gurbx.ultimateroguelike.utils.Constants;


public class PlayerMovementSystem extends IteratingSystem {	
	private MovementComponent moveComp;
	private TransformComponent transComp;
	private StateComponent stateComp;
	private TextureComponent textureComp;
	
	public PlayerMovementSystem() {
		super(Family.all(PlayerComponent.class, MovementComponent.class, StateComponent.class, TextureComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		moveComp = MovementComponent.MAPPER.get(entity);
		transComp = TransformComponent.MAPPER.get(entity);
		stateComp = StateComponent.MAPPER.get(entity);
		textureComp = TextureComponent.MAPPER.get(entity);
		
		//MOVEMENT 
		handleMovement();
		
		//STATES
		handleStates();
	}

	private void handleMovement() {
		moveComp.velocity.x = 0;
		moveComp.velocity.y = 0;
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			moveComp.velocity.x += -1;
			if (textureComp.flipX != true) textureComp.flipX = true;
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			moveComp.velocity.x += 1;
			if (textureComp.flipX == true) textureComp.flipX = false;
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			moveComp.velocity.y += -1;
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			moveComp.velocity.y += 1;
		}
	}

	private void handleStates() {		
		if ((moveComp.velocity.x == 0 && moveComp.velocity.y == 0) == false) {
			if (stateComp.getState().equals(StateComponent.RUN) == false) {
				stateComp.set(StateComponent.RUN);
			}
		} else if (stateComp.getState().equals(StateComponent.IDLE) == false) {
			stateComp.set(StateComponent.IDLE);
		}
	}
	
}
