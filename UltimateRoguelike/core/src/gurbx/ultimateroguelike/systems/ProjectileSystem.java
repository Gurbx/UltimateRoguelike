package gurbx.ultimateroguelike.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import gurbx.ultimateroguelike.components.BodyComponent;
import gurbx.ultimateroguelike.components.ProjectileComponent;

public class ProjectileSystem extends IteratingSystem {

	public ProjectileSystem() {
		super(Family.all(BodyComponent.class, ProjectileComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		BodyComponent bodyComp = BodyComponent.MAPPER.get(entity);
		ProjectileComponent projectileComp = ProjectileComponent.MAPPER.get(entity);
		
		//Acceleration and deceleration
		projectileComp.currentSpeed += projectileComp.acceleration * deltaTime;
		if (projectileComp.currentSpeed >= projectileComp.maxSpeed) projectileComp.currentSpeed = projectileComp.maxSpeed;
		projectileComp.currentSpeed -= projectileComp.deceleration * deltaTime;
		if (projectileComp.currentSpeed <= projectileComp.minSPeed) projectileComp.currentSpeed = projectileComp.minSPeed;
		
		Vector2 impulse = projectileComp.direction.nor().scl(projectileComp.currentSpeed);
		
//		bodyComp.body.applyLinearImpulse(impulse, bodyComp.body.getWorldCenter(), true);
		bodyComp.body.setLinearVelocity(impulse);
	}

}
