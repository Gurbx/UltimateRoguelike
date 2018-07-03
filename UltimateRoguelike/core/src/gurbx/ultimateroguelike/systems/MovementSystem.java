package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.MovementComponent;

public class MovementSystem extends IteratingSystem {

	public MovementSystem() {
		super(Family.all(BodyComponent.class, MovementComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		BodyComponent bodyComp = BodyComponent.MAPPER.get(entity);
		MovementComponent moveComp = MovementComponent.MAPPER.get(entity);
		
		if (moveComp.velocity.len() > 0) {
			moveComp.velocity = moveComp.velocity.nor().scl(moveComp.speed * deltaTime);
		}
		
		bodyComp.body.applyLinearImpulse(moveComp.velocity, bodyComp.body.getWorldCenter(), true);		
	}
}
